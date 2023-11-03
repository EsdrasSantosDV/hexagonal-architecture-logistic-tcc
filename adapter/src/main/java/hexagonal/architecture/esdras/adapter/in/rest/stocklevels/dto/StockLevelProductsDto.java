package hexagonal.architecture.esdras.adapter.in.rest.stocklevels.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Date;
import java.util.List;

@Data
@Schema(description = "DTO que representa os níveis de estoque para produtos em um determinado intervalo de datas")
public class StockLevelProductsDto {

    @NotNull(message = "A data de início não pode ser nula.")
    @PastOrPresent(message = "A data de início deve estar no passado ou no presente.")
    @Schema(description = "Data de início para o relatório de nível de estoque.", example = "2023-11-01T00:00:00.000Z")
    private Date startDate;

    @NotNull(message = "A data de término não pode ser nula.")
    @FutureOrPresent(message = "A data de término deve estar no futuro ou no presente.")
    @Schema(description = "Data de término para o relatório de nível de estoque.", example = "2023-11-30T00:00:00.000Z")
    private Date endDate;

    @Positive(message = "A margem deve ser positiva.")
    @Schema(description = "Porcentagem de margem de lucro.", example = "10.0")
    private Double margin;

    @NotNull(message = "Os níveis de estoque dos produtos não podem estar vazios.")
    @Schema(description = "Lista dos níveis de estoque dos produtos.")
    private List<@Valid ProductStockLevelDto> productsStock;

}