package hexagonal.architecture.esdras.adapter.out.persistence.jpa;

import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.NfInvoiceEntryEntityJpa;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers.NfInvoiceEntryMapper;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.repositories.JpaInvoiceEntryEntityPanacheRepository;
import hexagonal.architecture.esdras.application.port.output.nfinvoiceentry.persistence.OutputPortNfInvoiceEntry;
import hexagonal.architecture.esdras.domain.entity.InvoiceEntryDomain;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@LookupIfProperty(name = "persistence", stringValue = "mysql")
@ApplicationScoped
public class JpaInvoiceEntryEntityAdapterRepository implements OutputPortNfInvoiceEntry {

    private final JpaInvoiceEntryEntityPanacheRepository panacheRepository;

    public JpaInvoiceEntryEntityAdapterRepository(JpaInvoiceEntryEntityPanacheRepository panacheRepository) {
        this.panacheRepository = panacheRepository;
    }


    @Transactional
    @Override
    public InvoiceEntryDomain save(InvoiceEntryDomain invoiceEntryDomain) {
        NfInvoiceEntryEntityJpa jpaEntity = NfInvoiceEntryMapper.toJpaEntity(invoiceEntryDomain);
        panacheRepository.getEntityManager().merge(jpaEntity);

        return NfInvoiceEntryMapper.toDomainEntity(jpaEntity);
    }

    @Override
    public Optional<InvoiceEntryDomain> findById(int id) {
        NfInvoiceEntryEntityJpa jpaEntity = panacheRepository.findById(id);

        return NfInvoiceEntryMapper.toDomainEntityOptional(jpaEntity);

    }
}
