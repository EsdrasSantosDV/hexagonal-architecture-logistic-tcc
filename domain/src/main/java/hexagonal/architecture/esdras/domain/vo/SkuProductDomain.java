package hexagonal.architecture.esdras.domain.vo;

import hexagonal.architecture.esdras.domain.entity.ProductDomain;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @param value - value of the SkuProductDomain
 *              responsible for indentify the product sku used in the logistics process
 */
public record SkuProductDomain(String value) {
    private static final int LENGTH_OF_RANDOM_PART = 10;

    private static final String ALPHABET = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    public SkuProductDomain {
        Objects.requireNonNull(value, "'valor' não pode ser nulo");
        if (value.isEmpty()) {
            throw new IllegalArgumentException("'valor' não pode ser vazio");
        }
    }

    public static SkuProductDomain fromProductDomain(ProductDomain product) {
        String namePrefix = product.getName().substring(0, 2).toUpperCase();
        String categoryPrefix = product.getCategory().toString().substring(0, 2).toUpperCase();
        String dimensions = String.format("%02d%02d%02d",
                product.getHeight().intValue(),
                product.getWidth().intValue(),
                product.getDepth().intValue());

        ThreadLocalRandom random = ThreadLocalRandom.current();
        char[] chars = new char[LENGTH_OF_RANDOM_PART];
        for (int i = 0; i < LENGTH_OF_RANDOM_PART; i++) {
            chars[i] = ALPHABET.charAt(random.nextInt(ALPHABET.length()));
        }
        return new SkuProductDomain(namePrefix + categoryPrefix + dimensions + new String(chars));
    }


}
