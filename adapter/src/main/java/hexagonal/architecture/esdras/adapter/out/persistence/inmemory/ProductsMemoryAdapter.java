package hexagonal.architecture.esdras.adapter.out.persistence.inmemory;

import hexagonal.architecture.esdras.application.port.output.products.persistence.OutputPortProductRepository;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;
import hexagonal.architecture.esdras.domain.vo.ProductIdDomain;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@LookupIfProperty(name = "persistence", stringValue = "inmemory", lookupIfMissing = true)
@ApplicationScoped
public class ProductsMemoryAdapter implements OutputPortProductRepository {


    private final Map<ProductIdDomain, ProductDomain> products = new ConcurrentHashMap<>();

    public ProductsMemoryAdapter() {
    }

    @Override
    public ProductDomain save(ProductDomain productDomain) {
        products.put(productDomain.getId(), productDomain);
        return products.get(productDomain.getId());
    }

    @Override
    public Optional<ProductDomain> findById(String id) {
        return Optional.ofNullable(products.get(new ProductIdDomain(id)));
    }
}
