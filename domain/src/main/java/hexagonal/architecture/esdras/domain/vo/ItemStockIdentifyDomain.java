package hexagonal.architecture.esdras.domain.vo;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public record ItemStockIdentifyDomain(String value) {

    private static final String ALPHABET = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    private static final String STOCK_FLAG = "STOCK";
    private static final int LENGTH_OF_RANDOM_PART = 10;

    public ItemStockIdentifyDomain {
        Objects.requireNonNull(value, "'value' cannot be null");
        if (value.isEmpty()) {
            throw new IllegalArgumentException("'value' cannot be empty");
        }
    }

    public static ItemStockIdentifyDomain generateRandomPart() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        char[] chars = new char[LENGTH_OF_RANDOM_PART];
        for (int i = 0; i < LENGTH_OF_RANDOM_PART; i++) {
            chars[i] = ALPHABET.charAt(random.nextInt(ALPHABET.length()));
        }
        return new ItemStockIdentifyDomain(STOCK_FLAG + new String(chars));
    }
}