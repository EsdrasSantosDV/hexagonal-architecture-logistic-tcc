package hexagonal.architecture.esdras.adapter.in.rest.invoiceout.mapper;

import hexagonal.architecture.esdras.adapter.in.rest.invoiceout.dto.NfInvoiceOutDto;
import hexagonal.architecture.esdras.adapter.in.rest.invoiceout.dto.ProductNfInvoiceOutDto;
import hexagonal.architecture.esdras.application.port.input.invoiceout.commands.ProductNfInvoiceOut;
import hexagonal.architecture.esdras.application.port.input.invoiceout.commands.SendNfOutCommand;

public class NfInvoiceOutMapper {

    public static SendNfOutCommand toSendNfOutCommand(NfInvoiceOutDto dto) {
        return new SendNfOutCommand(
                dto.getId(),
                dto.getIssueDate(),
                dto.getRecipient(),
                dto.getIssuer(),
                dto.getCurrency(),
                dto.getAmount(),
                dto.getProductNfOutDto().stream()
                        .map(NfInvoiceOutMapper::toProductNfInvoiceOut)
                        .collect(java.util.stream.Collectors.toList())
        );
    }

    private static ProductNfInvoiceOut toProductNfInvoiceOut(ProductNfInvoiceOutDto dto) {
        return new ProductNfInvoiceOut(
                dto.getSku(),
                dto.getQuantity()
        );
    }


}
