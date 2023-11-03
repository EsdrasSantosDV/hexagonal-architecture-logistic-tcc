package hexagonal.architecture.esdras.application.service.products;

import hexagonal.architecture.esdras.application.port.input.stocklevels.InputPortGetStockLevels;
import hexagonal.architecture.esdras.application.port.input.stocklevels.commands.GetStockLevelsCommand;
import hexagonal.architecture.esdras.application.port.input.stocklevels.commands.ProductStockLevelCommand;
import hexagonal.architecture.esdras.application.port.input.stocklevels.exceptions.StockNotFoundException;
import hexagonal.architecture.esdras.application.port.output.products.persistence.OutputPortProduct;
import hexagonal.architecture.esdras.application.port.output.stock.persistence.OutputPortStock;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;
import hexagonal.architecture.esdras.domain.entity.StockDomain;

import java.util.List;
import java.util.Optional;

public class StockLevelsMaximumMinService implements InputPortGetStockLevels {
    private final OutputPortProduct outputPortProductRepository;


    private final OutputPortStock outputPortStockRepository;


    public StockLevelsMaximumMinService(OutputPortProduct productRepository, OutputPortStock outputPortStockRepository) {
        this.outputPortProductRepository = productRepository;
        this.outputPortStockRepository = outputPortStockRepository;
    }


    @Override
    public void getStockLevels(GetStockLevelsCommand getStockLevelsCommand) throws StockNotFoundException {
        Optional<StockDomain> domainStock = this.outputPortStockRepository.findByStockMain();
        if (domainStock.isEmpty()) {
            throw new StockNotFoundException("Stock não encontrado");
        }
        List<String> ids = getStockLevelsCommand.productsLevels().stream().map(
                ProductStockLevelCommand::productId
        ).toList();
        List<ProductDomain> listProducts = this.outputPortProductRepository.findByAllIds(ids);


    }
}
