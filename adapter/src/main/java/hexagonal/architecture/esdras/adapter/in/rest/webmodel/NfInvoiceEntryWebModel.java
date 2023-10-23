package hexagonal.architecture.esdras.adapter.in.rest.webmodel;

import hexagonal.architecture.esdras.domain.entity.InvoiceEntryDomain;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public record NfInvoiceEntryWebModel(
        Integer id,
        Date issueDate,
        String issuer,
        String recipient,
        List<ProductCoreWebModel> products,
        MoneyWebModel totalValue
) {
    public static NfInvoiceEntryWebModel fromDomainModel(InvoiceEntryDomain entryDomain) {
        return new NfInvoiceEntryWebModel(
                entryDomain.getId().value(),
                entryDomain.getIssueDate(),
                entryDomain.getIssuer(),
                entryDomain.getRecipient(),
                entryDomain.getProducts().values().stream().map(ProductCoreWebModel::fromDomainModel).collect(Collectors.toList()),
                MoneyWebModel.fromDomainModel(entryDomain.getTotalValue())
        );
    }
}

