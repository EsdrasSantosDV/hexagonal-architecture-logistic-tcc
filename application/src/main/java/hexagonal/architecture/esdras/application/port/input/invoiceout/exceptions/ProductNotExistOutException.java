package hexagonal.architecture.esdras.application.port.input.invoiceout.exceptions;

public class ProductNotExistOutException extends Exception{
    public ProductNotExistOutException(String message) {
        super(message);
    }
}
