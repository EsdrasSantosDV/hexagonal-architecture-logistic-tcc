package hexagonal.architecture.esdras.adapter.in.rest.invoiceout.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Schema(
        description = "DTO que representa uma produto da Nf de saida"
)
public class ProductNfInvoiceOutDto {
    @NotNull(message = "Sku do produto não pode ser nulo.")
    @NotBlank(message = "Sku do produto não pode ser vazio.")
    @NotNull(message = "Sku do produto não pode ser nulo.")
    @Schema(description = "Sku do Produto", example = "QUTO032820")
    private String sku;

    @Schema(description = "Quantidade do produto", example = "2")
    @PositiveOrZero(message = "Quantidade do o não pode ser negativo.")
    private int quantity;
}
