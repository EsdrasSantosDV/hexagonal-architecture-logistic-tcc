package hexagonal.architecture.esdras.adapter.in.rest.products.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@NoArgsConstructor
public class ProductDto {
    @NotBlank(message = "Nome é obrigatório.")
    @Size(max = 255, message = "Nome não pode exceder 255 caracteres.")
    private String name;

    @Size(max = 1000, message = "Descrição não pode exceder 1000 caracteres.")
    private String description;

    @NotNull(message = "Categoria é obrigatória.")
    @Size(max = 255, message = "Categoria não pode exceder 255 caracteres.")
    private String category;

    @PositiveOrZero(message = "Altura não pode ser negativa.")
    private Double height;

    @PositiveOrZero(message = "Largura não pode ser negativa.")
    private Double width;

    @PositiveOrZero(message = "Profundidade não pode ser negativa.")
    private Double depth;

    @NotNull(message = "Currency cannot be null.")
    private Currency currency;

    @NotNull(message = "Amount cannot be null.")
    @PositiveOrZero(message = "Amount must be positive or zero.")
    private BigDecimal amount;

    private String storageInstructions;

    private String restrictions;

}
