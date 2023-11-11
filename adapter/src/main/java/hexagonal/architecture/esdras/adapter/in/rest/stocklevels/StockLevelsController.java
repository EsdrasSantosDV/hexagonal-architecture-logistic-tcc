package hexagonal.architecture.esdras.adapter.in.rest.stocklevels;


import hexagonal.architecture.esdras.adapter.in.rest.common.ResultValidator;
import hexagonal.architecture.esdras.adapter.in.rest.stocklevels.dto.StockLevelProductsDto;
import hexagonal.architecture.esdras.adapter.in.rest.stocklevels.mapper.StockLevelMapper;
import hexagonal.architecture.esdras.application.port.input.stocklevels.InputPortGetStockLevels;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.Set;

@Path("/stock-levels")
@Produces(MediaType.APPLICATION_JSON)
public class StockLevelsController {

    private final InputPortGetStockLevels getStockLevelsUseCase;

    @Inject
    Validator validator;


    public StockLevelsController(InputPortGetStockLevels getStockLevelsUseCase) {
        this.getStockLevelsUseCase = getStockLevelsUseCase;
    }


    @POST
    @Path("/get-stock-levels")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Retorna os niveis de estoque",
            description = "Retorna os niveis de estoque."
    )
    @APIResponses(
            @APIResponse(
                    responseCode = "200",
                    description = "Retorna os niveis de estoque",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StockLevelProductsDto.class))
            )
    )
    public Response getStockLevels(@RequestBody(
            description = "Niveis de Estoque de Determinados Produtos",
            required = true,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = StockLevelProductsDto.class))
    ) StockLevelProductsDto request) {

        Set<ConstraintViolation<StockLevelProductsDto>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ResultValidator(violations)).build();
        }
        try {
            return Response.status(Response.Status.CREATED).entity(getStockLevelsUseCase.getStockLevels(StockLevelMapper.toGetStockCommand(request)).stocksLevelsByProduct()).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResultValidator("Ocorreu um erro no processamento da requisição." + e.getMessage())).build();
        }


    }

}
