package hexagonal.architecture.esdras.adapter.out.persistence.jpa;

import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.NfInvoiceOutEntityJpa;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers.NfInvoiceOutMapper;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.repositories.JpaInvoiceOutEntityPanacheRepository;
import hexagonal.architecture.esdras.application.port.output.nfinvoiceout.persistence.OutputPortNfInvoiceOut;
import hexagonal.architecture.esdras.domain.entity.InvoiceOutDomain;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@LookupIfProperty(name = "persistence", stringValue = "mysql")
@ApplicationScoped
public class JpaInvoiceOutEntityAdapterRepository implements OutputPortNfInvoiceOut {

    private final JpaInvoiceOutEntityPanacheRepository panacheRepository;

    public JpaInvoiceOutEntityAdapterRepository(JpaInvoiceOutEntityPanacheRepository panacheRepository) {
        this.panacheRepository = panacheRepository;
    }


    @Transactional
    @Override
    public InvoiceOutDomain save(InvoiceOutDomain invoiceOutDomain) {
        NfInvoiceOutEntityJpa jpaEntity = NfInvoiceOutMapper.toJpaEntity(invoiceOutDomain);
        panacheRepository.getEntityManager().merge(jpaEntity);

        return NfInvoiceOutMapper.toDomainEntity(jpaEntity);
    }

    @Override
    public Optional<InvoiceOutDomain> findById(int id) {
        NfInvoiceOutEntityJpa jpaEntity = panacheRepository.findById(id);

        return NfInvoiceOutMapper.toDomainEntityOptional(jpaEntity);
    }
}
