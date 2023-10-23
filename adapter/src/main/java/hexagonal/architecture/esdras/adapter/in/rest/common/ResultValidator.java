package hexagonal.architecture.esdras.adapter.in.rest.common;

import jakarta.validation.ConstraintViolation;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ResultValidator {
    private final List<String> messages;
    private final boolean success;

    public ResultValidator(String message) {
        this.success = true;
        this.messages = Collections.singletonList(message);
    }

    public ResultValidator(Set<? extends ConstraintViolation<?>> violations) {
        this.success = false;
        this.messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

    public List<String> getMessages() {
        return messages;
    }

    public boolean isSuccess() {
        return success;
    }
}