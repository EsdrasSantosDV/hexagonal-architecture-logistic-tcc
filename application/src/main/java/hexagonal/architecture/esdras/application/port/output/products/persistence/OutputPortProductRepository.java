package hexagonal.architecture.esdras.application.port.output.products.persistence;

import hexagonal.architecture.esdras.domain.entity.ProductDomain;

import java.util.Optional;

public interface OutputPortProductRepository {
    ProductDomain save(ProductDomain productDomain);

    Optional<ProductDomain> findById(String id);


}
