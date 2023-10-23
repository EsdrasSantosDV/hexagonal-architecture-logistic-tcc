package hexagonal.architecture.esdras.adapter.in.rest.invoiceentry.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@Data
@Schema(
        description = "DTO que representa uma produto da Nf de entrada"
)
public class ProductNfInvoiceEntryDto {

    @NotNull(message = "Id do produto não pode ser nulo.")
    @NotBlank(message = "Id do produto não pode ser vazio.")
    @NotNull(message = "Id do produto não pode ser nulo.")
    @Schema(description = "Produto Generic Id", example = "YBTXJX3GT9S95JVUVMUBF4P5VWGCF4")
    private String productGenericId;

    @Schema(description = "Quantidade do produto", example = "2")
    @PositiveOrZero(message = "Quantidade do o não pode ser negativo.")
    private int quantity;

    @Schema(description = "Data de vencimento do produto", example = "2021-09-01T00:00:00.000Z")
    @FutureOrPresent(message = "A data de vencimento deve estar no futuro ou no presente.")
    private Date dueDate;


    @NotNull(message = "Moeda do Produto não pode ser nula.")
    @Schema(description = "Moeda do Produto", example = "USD")
    private Currency currency;

    @NotNull(message = "Valor do Produto não pode ser Nulo.")
    @PositiveOrZero(message = "Valor do Produto não pode ser negativo.")
    @Schema(description = "Valor do Produto.", example = "1.00")
    private BigDecimal amount;


}
