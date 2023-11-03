package hexagonal.architecture.esdras.application.port.input.invoiceout.commands;


public record ProductNfInvoiceOut(
        String sku,
        int quantity
) {


}
