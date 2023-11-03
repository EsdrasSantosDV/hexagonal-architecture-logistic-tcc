package hexagonal.architecture.esdras.domain.vo;

public record NfOutIdentityDomain(int value) {

    public NfOutIdentityDomain {
        if (value < 1) {
            throw new IllegalArgumentException("'o numero da nota fiscal' precisa ser um valor maior que 0");
        }
    }
}
