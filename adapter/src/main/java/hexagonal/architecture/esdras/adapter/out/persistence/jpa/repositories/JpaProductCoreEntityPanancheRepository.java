package hexagonal.architecture.esdras.adapter.out.persistence.jpa.repositories;


import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.ProductCoreEntityJpa;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JpaProductCoreEntityPanancheRepository implements PanacheRepositoryBase<ProductCoreEntityJpa, Integer> {
}
