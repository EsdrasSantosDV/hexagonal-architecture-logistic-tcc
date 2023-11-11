package hexagonal.architecture.esdras.adapter.out.persistence.inmemory;

import hexagonal.architecture.esdras.application.port.output.products.persistence.OutputPortProduct;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;
import hexagonal.architecture.esdras.domain.vo.ProductIdDomain;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@LookupIfProperty(name = "persistence", stringValue = "inmemory", lookupIfMissing = true)
@ApplicationScoped
public class ProductsMemoryAdapter implements OutputPortProduct {


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

    @Override
    public List<ProductDomain> findByAllIds(List<String> ids) {
        return null;
    }

    @Override
    public List<ProductDomain> findAllFiltered() {
        return this.products.values().stream().toList();
    }
}
