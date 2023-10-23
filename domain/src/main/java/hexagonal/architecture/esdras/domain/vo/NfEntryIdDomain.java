package hexagonal.architecture.esdras.domain.vo;


public record NfEntryIdDomain(int value) {

    public NfEntryIdDomain {
        if (value < 1) {
            throw new IllegalArgumentException("'o numero da nota fiscal' precisa ser um valor maior que 0");
        }
    }
}
