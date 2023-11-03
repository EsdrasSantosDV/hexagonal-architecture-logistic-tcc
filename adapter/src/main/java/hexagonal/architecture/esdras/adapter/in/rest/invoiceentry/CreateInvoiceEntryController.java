package hexagonal.architecture.esdras.adapter.in.rest.invoiceentry;

import hexagonal.architecture.esdras.adapter.in.rest.common.ResultValidator;
import hexagonal.architecture.esdras.adapter.in.rest.invoiceentry.dto.NfInvoiceEntryDto;
import hexagonal.architecture.esdras.adapter.in.rest.invoiceentry.mapper.NfInvoiceEntryMapper;
import hexagonal.architecture.esdras.adapter.in.rest.webmodel.NfInvoiceEntryWebModel;
import hexagonal.architecture.esdras.application.port.input.invoiceentry.InputPortCreateInvoiceEntryUseCase;
import hexagonal.architecture.esdras.application.port.input.invoiceentry.exceptions.ProductNotExistException;
import hexagonal.architecture.esdras.domain.entity.InvoiceEntryDomain;
import hexagonal.architecture.esdras.domain.exceptions.CurrencyNfNotCompatibleWithProductException;
import hexagonal.architecture.esdras.domain.exceptions.InvoiceValueExceededException;
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
public class CreateInvoiceEntryController {
    private final InputPortCreateInvoiceEntryUseCase createInvoiceEntryUseCase;

    @Inject
    Validator validator;


    public CreateInvoiceEntryController(InputPortCreateInvoiceEntryUseCase createInvoiceEntryUseCase) {
        this.createInvoiceEntryUseCase = createInvoiceEntryUseCase;
    }


    @POST
    @Path("/create-nf")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Cria uma nova entrada de nota fiscal",
            description = "Cria uma nova entrada de nota fiscal."
    )
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "201",
                    description = "Cria uma nova entrada de nota fiscal",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = NfInvoiceEntryDto.class))
            )
    })
    public Response createInvoiceEntry(@RequestBody(
            description = "Dados da Nota Fiscal de Entrada",
            required = true,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NfInvoiceEntryDto.class))
    ) NfInvoiceEntryDto request) {
        Set<ConstraintViolation<NfInvoiceEntryDto>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ResultValidator(violations)).build();
        }

        try {
            InvoiceEntryDomain entryDomain = createInvoiceEntryUseCase.createInvoiceEntry(NfInvoiceEntryMapper.toSendNfCommand(request));
            return Response.status(Response.Status.CREATED).entity(NfInvoiceEntryWebModel.fromDomainModel(entryDomain)).build();
        } catch (InvoiceValueExceededException | CurrencyNfNotCompatibleWithProductException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ResultValidator(e.getMessage())).build();
        } catch (ProductNotExistException e) {
            throw clientErrorException(
                    Response.Status.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResultValidator("Ocorreu um erro no processamento da requisição." + e.getMessage())).build();
        }

    }


}
