package hexagonal.architecture.esdras.adapter.in.rest.stocklevels.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Schema(description = "DTO que representa o nível de estoque para um único produto")
public class ProductStockLevelDto {

    @NotNull(message = "O ID do produto não pode ser nulo.")
    @Schema(description = "Identificador único do produto.", example = "29TU6ALL7ELZGX4BVVWYJEHQASHJH6")
    private String productId;

    @NotNull(message = "O tempo de reposição não pode ser nulo.")
    @Positive(message = "O tempo de reposição deve ser positivo.")
    @Schema(description = "Tempo em dias para repor o estoque do produto.", example = "15")
    private Integer replacementTime;
}