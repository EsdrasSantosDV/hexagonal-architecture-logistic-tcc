package hexagonal.architecture.esdras.domain.vo;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ProductIdDomain
 * responsible for abstracting the product id used in the logistics process
 *
 * @author esdras
 */
public record ProductIdDomain(String value) {
    private static final String ALPHABET = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    private static final int LENGTH_OF_NEW_PRODUCT_IDS = 30;

    public ProductIdDomain {
        Objects.requireNonNull(value, "'valor' não pode ser nulo");
        if (value.isEmpty()) {
            throw new IllegalArgumentException("'valor' não pode ser vazio");
        }
    }

    public static ProductIdDomain randomProductIdDomain() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        char[] chars = new char[LENGTH_OF_NEW_PRODUCT_IDS];
        for (int i = 0; i < LENGTH_OF_NEW_PRODUCT_IDS; i++) {
            chars[i] = ALPHABET.charAt(random.nextInt(ALPHABET.length()));
        }
        return new ProductIdDomain(new String(chars));
    }
}
