package hexagonal.architecture.esdras.application.port.output.productcore.persistence;

import hexagonal.architecture.esdras.domain.entity.ProductCoreDomain;

import java.util.Optional;

public interface OutputPortProductCore {

    ProductCoreDomain save(ProductCoreDomain productCore);

    Optional<ProductCoreDomain> findBySku(String id);
}
