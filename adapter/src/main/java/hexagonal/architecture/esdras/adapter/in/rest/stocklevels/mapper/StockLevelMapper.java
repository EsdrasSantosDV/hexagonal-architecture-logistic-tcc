package hexagonal.architecture.esdras.adapter.in.rest.stocklevels.mapper;

import hexagonal.architecture.esdras.adapter.in.rest.stocklevels.dto.ProductStockLevelDto;
import hexagonal.architecture.esdras.adapter.in.rest.stocklevels.dto.StockLevelProductsDto;
import hexagonal.architecture.esdras.application.port.input.stocklevels.commands.GetStockLevelsCommand;
import hexagonal.architecture.esdras.application.port.input.stocklevels.commands.ProductStockLevelCommand;

public class StockLevelMapper {
    public static GetStockLevelsCommand toGetStockCommand(StockLevelProductsDto dto) {
        return new GetStockLevelsCommand(
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getMargin(),
                dto.getProductsStock().stream().map(
                        StockLevelMapper::toProductStockLevelCommand
                ).collect(java.util.stream.Collectors.toList()

                ));
    }

    private static ProductStockLevelCommand toProductStockLevelCommand(ProductStockLevelDto dto) {
        return new ProductStockLevelCommand(
                dto.getProductId(),
                dto.getReplacementTime()
        );
    }


}
