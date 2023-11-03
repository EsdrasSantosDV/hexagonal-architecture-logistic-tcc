package hexagonal.architecture.esdras.application.port.input.stocklevels.exceptions;

public class StockNotFoundException extends Exception {
    public StockNotFoundException(String message) {
        super(message);
    }
}

