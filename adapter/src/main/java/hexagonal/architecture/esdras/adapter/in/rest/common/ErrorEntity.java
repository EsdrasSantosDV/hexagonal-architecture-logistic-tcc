package hexagonal.architecture.esdras.adapter.in.rest.common;


public record ErrorEntity(int httpStatus, String errorMessage) {}
