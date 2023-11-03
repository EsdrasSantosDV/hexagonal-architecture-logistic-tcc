package hexagonal.architecture.esdras.application.port.input.stocklevels.commands;

import java.util.List;

public record ResultStockProducts(
        List<ResultStock> stocksLevelsByProduct
) {
}


