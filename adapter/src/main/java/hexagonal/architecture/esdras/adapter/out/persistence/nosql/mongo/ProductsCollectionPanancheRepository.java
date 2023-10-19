package hexagonal.architecture.esdras.adapter.out.persistence.nosql.mongo;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ProductsCollectionPanancheRepository implements PanacheMongoRepository<ProductsMongoCollection> {

    public Optional<ProductsMongoCollection> findById(String id) {
        return find("_id", id).firstResultOptional();
    }

}
