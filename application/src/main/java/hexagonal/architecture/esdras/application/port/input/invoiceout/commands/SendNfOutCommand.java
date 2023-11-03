package hexagonal.architecture.esdras.application.port.input.invoiceout.commands;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.List;

public record SendNfOutCommand(
        int id,
        Date issueDate,
        String recipient,
        String issuer,
        Currency currency,
        BigDecimal amount,
        List<ProductNfInvoiceOut> productNfInvoiceOuts
) {
}
