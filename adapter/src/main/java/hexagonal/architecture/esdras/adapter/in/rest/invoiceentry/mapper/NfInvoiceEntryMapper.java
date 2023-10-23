package hexagonal.architecture.esdras.adapter.in.rest.invoiceentry.mapper;


import hexagonal.architecture.esdras.adapter.in.rest.invoiceentry.dto.NfInvoiceEntryDto;
import hexagonal.architecture.esdras.adapter.in.rest.invoiceentry.dto.ProductNfInvoiceEntryDto;
import hexagonal.architecture.esdras.application.port.input.invoiceentry.commands.ProductNfInvoiceEntry;
import hexagonal.architecture.esdras.application.port.input.invoiceentry.commands.SendNfCommand;

import java.util.stream.Collectors;

public class NfInvoiceEntryMapper {


    public static SendNfCommand toSendNfCommand(NfInvoiceEntryDto dto) {
        return new SendNfCommand(
                dto.getId(),
                dto.getIssueDate(),
                dto.getRecipient(),
                dto.getIssuer(),
                dto.getCurrency(),
                dto.getAmount(),
                dto.getProductNfInvoiceEntryDto().stream()
                        .map(NfInvoiceEntryMapper::toProductNfInvoiceEntry)
                        .collect(Collectors.toList())
        );
    }


    private static ProductNfInvoiceEntry toProductNfInvoiceEntry(ProductNfInvoiceEntryDto dto) {
        return new ProductNfInvoiceEntry(
                dto.getProductGenericId(),
                dto.getQuantity(),
                dto.getDueDate(),
                dto.getCurrency(),
                dto.getAmount()
        );
    }
}
