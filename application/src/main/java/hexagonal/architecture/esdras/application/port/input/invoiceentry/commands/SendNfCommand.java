package hexagonal.architecture.esdras.application.port.input.invoiceentry.commands;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.List;

public record SendNfCommand(
        int id,
        Date issueDate,
        String recipient,
        String issuer,
        Currency currency,
        BigDecimal amount,
        List<ProductNfInvoiceEntry> productNfInvoiceEntries
) {
    public SendNfCommand(
            int id,
            Date issueDate,
            String recipient,
            String issuer,
            Currency currency,
            BigDecimal amount,
            List<ProductNfInvoiceEntry> productNfInvoiceEntries) {
        this.id = id;
        this.issueDate = issueDate;
        this.recipient = recipient;
        this.issuer = issuer;
        this.currency = currency;
        this.amount = amount;
        this.productNfInvoiceEntries = productNfInvoiceEntries;
    }
}


