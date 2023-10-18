package hexagonal.architecture.esdras.adapter.out.persistence.nosql.mongo;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class ProductsCollectionPanancheRepository implements PanacheMongoRepository<ProductsMongoCollection> {

    public Optional<ProductsMongoCollection> findById(String id) {
        return find("_id", new ObjectId(id)).firstResultOptional();
    }

}
