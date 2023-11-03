package hexagonal.architecture.esdras.adapter.out.persistence.jpa.repositories;

import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.NfInvoiceOutEntityJpa;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JpaInvoiceOutEntityPanacheRepository implements PanacheRepositoryBase<NfInvoiceOutEntityJpa, Integer> {
}
