package hexagonal.architecture.esdras.adapter.in.rest.invoiceout;

import hexagonal.architecture.esdras.adapter.in.rest.common.ResultValidator;
import hexagonal.architecture.esdras.adapter.in.rest.invoiceout.dto.NfInvoiceOutDto;
import hexagonal.architecture.esdras.adapter.in.rest.invoiceout.mapper.NfInvoiceOutMapper;
import hexagonal.architecture.esdras.adapter.in.rest.webmodel.NfInvoiceOutWebModel;
import hexagonal.architecture.esdras.application.port.input.invoiceout.InputPortCreateInvoiceOutUseCase;
import hexagonal.architecture.esdras.application.port.input.invoiceout.exceptions.ProductNotExistOutException;
import hexagonal.architecture.esdras.domain.entity.InvoiceOutDomain;
import hexagonal.architecture.esdras.domain.exceptions.CurrencyNfNotCompatibleWithProductException;
import hexagonal.architecture.esdras.domain.exceptions.InvoiceValueExceededException;
import hexagonal.architecture.esdras.domain.exceptions.QuantityInvalidException;
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

@Path("/nf")
@Produces(MediaType.APPLICATION_JSON)
public class CreateInvoiceOutController {
    private final InputPortCreateInvoiceOutUseCase createInvoiceOutUseCase;
    @Inject
    Validator validator;

    public CreateInvoiceOutController(InputPortCreateInvoiceOutUseCase createInvoiceOutUseCase) {
        this.createInvoiceOutUseCase = createInvoiceOutUseCase;
    }


    @POST
    @Path("/create-nf-out")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Cria uma nova entrada de nota fiscal",
            description = "Cria uma nova entrada de nota fiscal de saida."
    )
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "201",
                    description = "Cria uma nova entrada de nota fiscal",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = NfInvoiceOutDto.class))
            )
    })
    public Response createInvoiceOut(@RequestBody(
            description = "Dados da Nota Fiscal de Saida",
            required = true,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NfInvoiceOutDto.class))
    ) NfInvoiceOutDto request) {
        Set<ConstraintViolation<NfInvoiceOutDto>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ResultValidator(violations)).build();
        }

        try {
            InvoiceOutDomain entryDomain = createInvoiceOutUseCase.creatInvoiceOutDomain(NfInvoiceOutMapper.toSendNfOutCommand(request));
            return Response.status(Response.Status.CREATED).entity(NfInvoiceOutWebModel.fromDomainModel(entryDomain)).build();
        } catch (InvoiceValueExceededException | CurrencyNfNotCompatibleWithProductException |
                 QuantityInvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ResultValidator(e.getMessage())).build();
        } catch (ProductNotExistOutException e) {
            throw clientErrorException(
                    Response.Status.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResultValidator("Ocorreu um erro no processamento da requisição." + e.getMessage())).build();
        }

    }


}
