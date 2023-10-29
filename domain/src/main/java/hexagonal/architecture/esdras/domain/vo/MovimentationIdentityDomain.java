package hexagonal.architecture.esdras.domain.vo;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public record MovimentationIdentityDomain(String value) {
    private static final String ALPHABET = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    private static final String MOVEMENT_FLAG = "MOVE";
    private static final int LENGTH_OF_RANDOM_PART = 9;

    public MovimentationIdentityDomain {
        Objects.requireNonNull(value, "'value' cannot be null");
        if (value.isEmpty()) {
            throw new IllegalArgumentException("'value' cannot be empty");
        }
        value += generateRandomPart();
    }

    private static String generateRandomPart() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        char[] chars = new char[LENGTH_OF_RANDOM_PART];
        for (int i = 0; i < LENGTH_OF_RANDOM_PART; i++) {
            chars[i] = ALPHABET.charAt(random.nextInt(ALPHABET.length()));
        }
        return MOVEMENT_FLAG + new String(chars);
    }
}