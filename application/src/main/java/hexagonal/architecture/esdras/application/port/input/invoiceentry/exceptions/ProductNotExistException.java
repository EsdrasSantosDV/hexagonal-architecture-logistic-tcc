package hexagonal.architecture.esdras.application.port.input.invoiceentry.exceptions;

public class ProductNotExistException extends Exception {
    public ProductNotExistException(String message) {
        super(message);
    }
}
