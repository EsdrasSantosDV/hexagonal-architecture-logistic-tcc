package hexagonal.architecture.esdras.domain.exceptions;

public class QuantityInvalidException extends RuntimeException {
    public QuantityInvalidException(String message) {
        super(message);

    }
}
