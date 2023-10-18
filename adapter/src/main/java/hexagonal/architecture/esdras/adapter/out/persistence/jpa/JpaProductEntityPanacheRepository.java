package hexagonal.architecture.esdras.adapter.out.persistence.jpa;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class JpaProductEntityPanacheRepository implements PanacheRepositoryBase<ProductsEntityJpa, String> {

    
}
