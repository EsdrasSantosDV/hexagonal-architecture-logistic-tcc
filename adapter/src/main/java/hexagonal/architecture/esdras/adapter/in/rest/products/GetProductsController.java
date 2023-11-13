package hexagonal.architecture.esdras.adapter.in.rest.products;


import hexagonal.architecture.esdras.adapter.in.rest.common.ResultValidator;
import hexagonal.architecture.esdras.adapter.in.rest.webmodel.ProductWebModel;
import hexagonal.architecture.esdras.application.port.input.products.InputPortGetProductsUseCase;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.stream.Collectors;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class GetProductsController {

    private final InputPortGetProductsUseCase getProductsUseCase;


    public GetProductsController(InputPortGetProductsUseCase getProductsUseCase) {
        this.getProductsUseCase = getProductsUseCase;
    }


    @GET
    @Path("/get-products")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Retorna todos os produtos",
            description = "Retorna todos os produtos."
    )
    @APIResponses(
            @APIResponse(
                    responseCode = "200",
                    description = "Retorna todos os produtos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductWebModel.class))
            )
    )
    public Response getProducts() {
        try {
            return Response
                    .status(Response.Status.CREATED)
                    .entity(
                            getProductsUseCase.getProducts().stream()
                                    .map(ProductWebModel::fromDomainModel)
                                    .collect(Collectors.toList())
                    )
                    .build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResultValidator("Ocorreu um erro no processamento da requisição." + e.getMessage())).build();
        }


    }


}
