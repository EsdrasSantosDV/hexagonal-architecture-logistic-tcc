package hexagonal.architecture.esdras.adapter.in.rest.invoiceout.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.List;

@Data
@Schema(
        description = "DTO que representa uma nota fiscal de saida"
)
public class NfInvoiceOutDto {

    @NotNull(message = "id da nota fiscal é obrigatória.")
    @Schema(description = "numero da nota fiscal", example = "12")
    private int id;

    @PastOrPresent(message = "A data da emissão deve estar no passado ou no presente.")
    @NotNull(message = "A data da emissão da nota fiscal é obrigatória.")
    @Schema(description = "Data da emissão da nota fiscal.", example = "2021-09-01T00:00:00.000Z")
    private Date issueDate;

    @NotNull(message = "O nome do remetente é obrigatório.")
    @NotBlank(message = "O nome do remetente é obrigatório.")
    @Schema(description = "Nome do remetente.", example = "esdras santos de Oliveira")
    private String recipient;


    @NotNull(message = "O nome do destinatário é obrigatório.")
    @NotBlank(message = "O nome do destinatário é obrigatório.")
    @Schema(description = "Nome do destinatário.", example = "emanuelle")
    private String issuer;

    @NotNull(message = "Moeda da NF não pode ser nula.")
    @Schema(description = "Moeda da NF.", example = "USD")
    private Currency currency;

    @NotNull(message = "Valor Total da NF não pode ser nulo.")
    @PositiveOrZero(message = "Valor Total da NF não pode ser negativo.")
    @Schema(description = "Valor Total da Nf.", example = "50.00")
    private BigDecimal amount;

    @NotEmpty(message = "Pelo menos um produto deve ser fornecido.")
    @Schema(description = "Produtos da Nf.")
    private List<@Valid ProductNfInvoiceOutDto> productNfOutDto;
}
