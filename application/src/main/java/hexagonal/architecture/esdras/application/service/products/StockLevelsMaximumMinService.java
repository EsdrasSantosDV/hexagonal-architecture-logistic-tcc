package hexagonal.architecture.esdras.application.service.products;

import hexagonal.architecture.esdras.application.port.input.stocklevels.InputPortGetStockLevels;
import hexagonal.architecture.esdras.application.port.input.stocklevels.commands.GetStockLevelsCommand;
import hexagonal.architecture.esdras.application.port.input.stocklevels.commands.ProductStockLevelCommand;
import hexagonal.architecture.esdras.application.port.input.stocklevels.commands.ResultStock;
import hexagonal.architecture.esdras.application.port.input.stocklevels.commands.ResultStockProducts;
import hexagonal.architecture.esdras.application.port.input.stocklevels.exceptions.StockNotFoundException;
import hexagonal.architecture.esdras.application.port.output.products.persistence.OutputPortProduct;
import hexagonal.architecture.esdras.application.port.output.stock.persistence.OutputPortStock;
import hexagonal.architecture.esdras.domain.entity.MovementDomain;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;
import hexagonal.architecture.esdras.domain.entity.StockDomain;
import hexagonal.architecture.esdras.domain.vo.ProductIdDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class StockLevelsMaximumMinService implements InputPortGetStockLevels {
    private final OutputPortProduct outputPortProductRepository;


    private final OutputPortStock outputPortStockRepository;


    public StockLevelsMaximumMinService(OutputPortProduct productRepository, OutputPortStock outputPortStockRepository) {
        this.outputPortProductRepository = productRepository;
        this.outputPortStockRepository = outputPortStockRepository;
    }


    @Override
    public ResultStockProducts getStockLevels(GetStockLevelsCommand getStockLevelsCommand) throws StockNotFoundException {
        Optional<StockDomain> domainStock = this.outputPortStockRepository.findByStockMain();
        if (domainStock.isEmpty()) {
            throw new StockNotFoundException("Stock não encontrado");
        }
        List<ProductDomain> listProducts = this.ProductsByGetStockLevel(getStockLevelsCommand);
        int Period = daysByPeriod(getStockLevelsCommand);
        Map<ProductIdDomain, List<MovementDomain>> movementsByProductEntry = domainStock.get().getMovementsByProductEntry();
        Map<ProductIdDomain, List<MovementDomain>> movementsByProductExit = domainStock.get().getMovementsByProductOutgoing();
        Map<ProductIdDomain, ProductStockLevelCommand> productReplacementTimeMap = transformListToMap(getStockLevelsCommand.productsLevels());

        List<ResultStock> results = new ArrayList<>();
        for (ProductDomain product : listProducts) {
            List<MovementDomain> movementsEntry = movementsByProductEntry.get(product.getId());
            List<MovementDomain> movementsExit = movementsByProductExit.get(product.getId());

            int stock = domainStock.get().getStock().get(product.getId()).getQuantity();
            int min = domainStock.get().minStock(movementsExit, productReplacementTimeMap.get(product.getId()).replacementTime(), getStockLevelsCommand.margin(), Period);
            int max = domainStock.get().maxStock(movementsEntry, movementsExit, min, getStockLevelsCommand.margin(), Period, productReplacementTimeMap.get(product.getId()).orderCost(), productReplacementTimeMap.get(product.getId()).storageCost());
            int orderPoint = domainStock.get().orderPoint(movementsEntry, movementsExit, min, Period, productReplacementTimeMap.get(product.getId()).replacementTime());
            ResultStock result = new ResultStock(
                    product.getId().value(),
                    product.getName(),
                    stock,
                    min,
                    max,
                    orderPoint
            );
            results.add(result);
        }

        return new ResultStockProducts(results);
    }

    public Map<ProductIdDomain, ProductStockLevelCommand> transformListToMap(List<ProductStockLevelCommand> productsLevels) {
        return productsLevels.stream()
                .collect(Collectors.toMap(
                        product -> new ProductIdDomain(product.productId()),
                        product -> product
                ));
    }

    private List<ProductDomain> ProductsByGetStockLevel(GetStockLevelsCommand getStockLevelsCommand) {
        List<String> ids = getStockLevelsCommand.productsLevels().stream().map(
                ProductStockLevelCommand::productId
        ).toList();
        return this.outputPortProductRepository.findByAllIds(ids)
                ;
    }

    private int daysByPeriod(GetStockLevelsCommand getStockLevelsCommand) {
        long diffIInMillie = Math.abs(getStockLevelsCommand.startDate().getTime() - getStockLevelsCommand.endDate().getTime());
        long diff = TimeUnit.DAYS.convert(diffIInMillie, TimeUnit.MILLISECONDS);
        return (int) diff;
    }

}
