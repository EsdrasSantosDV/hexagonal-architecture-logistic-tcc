package hexagonal.architecture.esdras.adapter.out.persistence.jpa.repositories;

import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.ProductsEntityJpa;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class JpaProductEntityPanacheRepository implements PanacheRepositoryBase<ProductsEntityJpa, String> {

}
