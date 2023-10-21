package hexagonal.architecture.esdras.domain.vo;

import hexagonal.architecture.esdras.domain.entity.ProductDomain;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public record SkuProduct(String value) {
    private static final String ALPHABET = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    private static final int LENGTH_OF_NEW_PRODUCT_IDS = 30;

    public SkuProduct {
        Objects.requireNonNull(value, "'valor' não pode ser nulo");
        if (value.isEmpty()) {
            throw new IllegalArgumentException("'valor' não pode ser vazio");
        }
    }

    public static SkuProduct randomSkuProduct() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        char[] chars = new char[LENGTH_OF_NEW_PRODUCT_IDS];
        for (int i = 0; i < LENGTH_OF_NEW_PRODUCT_IDS; i++) {
            chars[i] = ALPHABET.charAt(random.nextInt(ALPHABET.length()));
        }
        return new SkuProduct(new String(chars));
    }

    public static SkuProduct fromProductDomain(ProductDomain product) {
        String namePrefix = product.getName().substring(0, 2).toUpperCase();
        String categoryPrefix = product.getCategory().toString().substring(0, 2).toUpperCase();
        String dimensions = String.format("%02d%02d%02d",
                product.getHeight().intValue(),
                product.getWidth().intValue(),
                product.getDepth().intValue());

        return new SkuProduct(namePrefix + categoryPrefix + dimensions);
    }


}
