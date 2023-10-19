package hexagonal.architecture.esdras.adapter.in.rest.products;

import hexagonal.architecture.esdras.adapter.in.rest.common.ResultValidator;
import hexagonal.architecture.esdras.adapter.in.rest.products.dto.ProductDto;
import hexagonal.architecture.esdras.adapter.in.rest.products.mapper.ProductMapper;
import hexagonal.architecture.esdras.adapter.in.rest.products.webmodel.ProductWebModel;
import hexagonal.architecture.esdras.application.port.input.products.InputPortCreateProductUseCase;
import hexagonal.architecture.esdras.application.port.input.products.exceptions.ProductAlreadyExistsException;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;
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

import static hexagonal.architecture.esdras.adapter.in.rest.common.ControllerCommons.clientErrorException;

/**
 * Controller responsible for creating a product.
 *
 * @author esdras
 */
@Path("/create-product")
@Produces(MediaType.APPLICATION_JSON)
public class CreateProductController {
    private final InputPortCreateProductUseCase createProductUseCase;

    @Inject
    Validator validator;


    public CreateProductController(InputPortCreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }


    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Cria um novo produto",
            description = "Cria um novo produto com base nos valores fornecidos."
    )
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "201",
                    description = "Produto criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductWebModel.class))
            )
    })
    public Response createProduct(@RequestBody(
            description = "Dados do produto a ser criado",
            required = true,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))
    ) ProductDto request) {
        Set<ConstraintViolation<ProductDto>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ResultValidator(violations)).build();
        }
        try {
            ProductDomain productDomain = createProductUseCase.createProduct(ProductMapper.dtoToDomain(request));
            return Response.ok(ProductWebModel.fromDomainModel(productDomain)).build();
        } catch (ProductAlreadyExistsException e) {
            throw clientErrorException(
                    Response.Status.BAD_REQUEST, "Produto ja possui cadastro");
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResultValidator("Ocorreu um erro no processamento da requisição.")).build();
        }
    }

}
