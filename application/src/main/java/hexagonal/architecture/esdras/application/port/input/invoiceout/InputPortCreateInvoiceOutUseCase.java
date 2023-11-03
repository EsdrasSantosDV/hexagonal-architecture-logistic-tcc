package hexagonal.architecture.esdras.application.port.input.invoiceout;

import hexagonal.architecture.esdras.application.port.input.invoiceentry.exceptions.ProductNotExistException;
import hexagonal.architecture.esdras.application.port.input.invoiceout.commands.SendNfOutCommand;
import hexagonal.architecture.esdras.application.port.input.invoiceout.exceptions.ProductNotExistOutException;
import hexagonal.architecture.esdras.domain.entity.InvoiceOutDomain;

/*
 * InputPortCreateInvoiceOutUseCase
 * responsible for create unique InvoiceOut
 * @auhtor esdras
 */
public interface InputPortCreateInvoiceOutUseCase {
    InvoiceOutDomain creatInvoiceOutDomain(SendNfOutCommand command) throws ProductNotExistOutException, ProductNotExistException;

}
