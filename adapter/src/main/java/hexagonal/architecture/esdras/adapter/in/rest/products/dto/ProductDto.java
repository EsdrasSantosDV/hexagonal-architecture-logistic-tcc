package hexagonal.architecture.esdras.adapter.in.rest.products.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Data
@NoArgsConstructor
@Schema(
        description = "DTO que representa um produto"
)
public class ProductDto {
    @NotBlank(message = "Nome é obrigatório.")
    @Size(max = 255, message = "Nome não pode exceder 255 caracteres.")
    @Schema(description = "Nome do produto.", example = "Laptop Gamer")
    private String name;

    @Size(max = 1000, message = "Descrição não pode exceder 1000 caracteres.")
    @Schema(description = "Descrição do produto.", example = "Um laptop de alto desempenho para jogos.")
    private String description;

    @NotNull(message = "Categoria é obrigatória.")
    @Size(max = 255, message = "Categoria não pode exceder 255 caracteres.")
    @Schema(description = "Categoria do produto.", example = "ELECTRONICS")
    private String category;

    @PositiveOrZero(message = "Altura não pode ser negativa.")
    @Schema(description = "Altura do produto.", example = "2.5")
    private Double height;

    @PositiveOrZero(message = "Largura não pode ser negativa.")
    @Schema(description = "Largura do produto.", example = "30.0")
    private Double width;

    @PositiveOrZero(message = "Profundidade não pode ser negativa.")
    @Schema(description = "Profundidade do produto.", example = "25.0")
    private Double depth;

    @Schema(description = "Instruções de armazenamento do produto.", example = "Mantenha em local fresco e seco.")
    private String storageInstructions;

    @Schema(description = "Restrições do produto.", example = "esdras")
    private String restrictions;

}
