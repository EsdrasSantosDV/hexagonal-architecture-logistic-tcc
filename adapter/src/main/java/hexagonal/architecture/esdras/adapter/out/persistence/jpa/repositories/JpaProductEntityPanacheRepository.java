package hexagonal.architecture.esdras.adapter.out.persistence.jpa.repositories;

import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.ProductsEntityJpa;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;


@ApplicationScoped
public class JpaProductEntityPanacheRepository implements PanacheRepositoryBase<ProductsEntityJpa, String> {

    public List<ProductsEntityJpa> findByAllIds(List<String> ids) {
        return list("id in ?1", ids);
    }
}
