package hexagonal.architecture.esdras.application.port.output.products.persistence;

import hexagonal.architecture.esdras.domain.entity.ProductDomain;

import java.util.List;
import java.util.Optional;

public interface OutputPortProduct {
    ProductDomain save(ProductDomain productDomain);

    Optional<ProductDomain> findById(String id);


    List<ProductDomain> findByAllIds(List<String> ids);

}
