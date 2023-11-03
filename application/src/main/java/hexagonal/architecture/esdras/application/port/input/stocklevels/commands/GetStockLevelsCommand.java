package hexagonal.architecture.esdras.application.port.input.stocklevels.commands;

import java.util.Date;
import java.util.List;

public record GetStockLevelsCommand(
        Date startDate,
        Date endDate,
        Double margin,
        List<ProductStockLevelCommand> productsLevels
) {
}
