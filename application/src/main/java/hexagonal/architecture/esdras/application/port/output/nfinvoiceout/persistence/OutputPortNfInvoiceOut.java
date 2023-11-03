package hexagonal.architecture.esdras.application.port.output.nfinvoiceout.persistence;


import hexagonal.architecture.esdras.domain.entity.InvoiceOutDomain;

import java.util.Optional;

public interface OutputPortNfInvoiceOut {

    InvoiceOutDomain save(InvoiceOutDomain invoiceOutDomain);

    Optional<InvoiceOutDomain> findById(int id);
}
