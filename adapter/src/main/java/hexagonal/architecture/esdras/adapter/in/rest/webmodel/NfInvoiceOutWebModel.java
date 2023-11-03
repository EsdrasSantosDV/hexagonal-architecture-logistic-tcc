package hexagonal.architecture.esdras.adapter.in.rest.webmodel;

import hexagonal.architecture.esdras.domain.entity.InvoiceOutDomain;

import java.util.Date;

public record NfInvoiceOutWebModel(
        Integer id,
        Date issueDate,
        String issuer,
        String recipient,
        MoneyWebModel totalValue

) {
    public static NfInvoiceOutWebModel fromDomainModel(InvoiceOutDomain entryDomain) {
        return new NfInvoiceOutWebModel(
                entryDomain.getId().value(),
                entryDomain.getIssueDate(),
                entryDomain.getIssuer(),
                entryDomain.getRecipient(),
                MoneyWebModel.fromDomainModel(entryDomain.getTotalValue())
        );
    }
}
