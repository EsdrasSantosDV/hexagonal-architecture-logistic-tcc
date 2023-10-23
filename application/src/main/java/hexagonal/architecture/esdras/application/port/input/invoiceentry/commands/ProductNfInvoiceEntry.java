package hexagonal.architecture.esdras.application.port.input.invoiceentry.commands;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

public record ProductNfInvoiceEntry(
        String productGenericId,
        int quantity,
        Date dueDate,
        Currency currency,
        BigDecimal amount
) {
  
}