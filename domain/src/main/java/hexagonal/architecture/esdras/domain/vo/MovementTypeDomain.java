package hexagonal.architecture.esdras.domain.vo;

public enum MovementTypeDomain {
    ENTRY("Entrada"),
    OUTGOING("Saída"),
    LOSS("Perda");

    private final String description;

    MovementTypeDomain(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
