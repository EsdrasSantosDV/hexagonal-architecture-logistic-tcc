package hexagonal.architecture.esdras.domain.exceptions;

public class CurrencyNfNotCompatibleWithProductException extends RuntimeException {
    public CurrencyNfNotCompatibleWithProductException() {
        super("A moeda do produto não é compatível com a moeda da nota fiscal");
    }

    public CurrencyNfNotCompatibleWithProductException(String message) {
        super(message);
    }


}
