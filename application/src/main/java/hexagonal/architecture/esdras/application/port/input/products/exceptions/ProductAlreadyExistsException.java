package hexagonal.architecture.esdras.application.port.input.products.exceptions;

public class ProductAlreadyExistsException extends Exception {
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
