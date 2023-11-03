package hexagonal.architecture.esdras.application.port.input.stocklevels.commands;

public record ProductStockLevelCommand(
        String productId,
        Integer replacementTime
) {
}
