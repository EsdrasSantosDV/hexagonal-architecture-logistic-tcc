package hexagonal.architecture.esdras.application.port.input.invoiceentry;

import hexagonal.architecture.esdras.application.port.input.invoiceentry.commands.SendNfCommand;
import hexagonal.architecture.esdras.application.port.input.invoiceentry.exceptions.ProductNotExistException;
import hexagonal.architecture.esdras.domain.entity.InvoiceEntryDomain;

public interface InputPortCreateInvoiceEntryUseCase {

    InvoiceEntryDomain createInvoiceEntry(SendNfCommand command) throws ProductNotExistException;

}
