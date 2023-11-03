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
}


