package hexagonal.architecture.esdras.application.port.input.stocklevels.commands;

public record ResultStock(
        String productId,
        String name,

        Integer currentStock,

        Integer minStock,

        Integer maxStock,

        Integer orderPoint
) {

}