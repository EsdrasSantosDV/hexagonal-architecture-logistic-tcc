package hexagonal.architecture.esdras.adapter.out.persistence.nosql.mongo;

import hexagonal.architecture.esdras.application.port.output.products.persistence.OutputPortProduct;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@LookupIfProperty(name = "persistence", stringValue = "nosql")
@ApplicationScoped
public class ProductsCollectionAdapterRepository implements OutputPortProduct {
    private final ProductsCollectionPanancheRepository mongoRepository;

    public ProductsCollectionAdapterRepository(ProductsCollectionPanancheRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }


    @Override
    public ProductDomain save(ProductDomain productDomain) {
        ProductsMongoCollection mongoCollection = ProductsCollectionMapper.domainToMongo(productDomain);
        mongoRepository.persist(mongoCollection);
        return ProductsCollectionMapper.mongoToDomain(mongoCollection);
    }

    @Override
    public Optional<ProductDomain> findById(String id) {
        ProductsMongoCollection product = mongoRepository.findById(id).orElse(null);

        return ProductsCollectionMapper.productDomainOptional(product);
    }

    @Override
    public List<ProductDomain> findByAllIds(List<String> ids) {
        return this.mongoRepository.findAll().stream().map(ProductsCollectionMapper::mongoToDomain).toList();
    }

    @Override
    public List<ProductDomain> findAllFiltered() {
        return this.mongoRepository.findAll().stream().map(ProductsCollectionMapper::mongoToDomain).toList();
    }


}
