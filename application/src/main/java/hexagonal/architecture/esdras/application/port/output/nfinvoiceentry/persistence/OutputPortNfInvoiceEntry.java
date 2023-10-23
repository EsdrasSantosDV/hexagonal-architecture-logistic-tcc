package hexagonal.architecture.esdras.application.port.output.nfinvoiceentry.persistence;

import hexagonal.architecture.esdras.domain.entity.InvoiceEntryDomain;

import java.util.Optional;

public interface OutputPortNfInvoiceEntry {
    InvoiceEntryDomain save(InvoiceEntryDomain invoiceEntryDomain);

    Optional<InvoiceEntryDomain> findById(int id);
}
