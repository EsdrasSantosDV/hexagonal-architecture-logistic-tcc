package hexagonal.architecture.esdras.domain.exceptions;

public class InvoiceValueExceededException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvoiceValueExceededException() {
        super("O valor da nota fiscal excede o valor máximo permitido.");
    }

    public InvoiceValueExceededException(String message) {
        super(message);
    }
    
}