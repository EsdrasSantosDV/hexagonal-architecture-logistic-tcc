package hexagonal.architecture.esdras.application.port.input.products;

import hexagonal.architecture.esdras.domain.entity.ProductDomain;

import java.util.List;

/**
 * InputPortGetProductsUseCase
 * Get Products filtered
 */
public interface InputPortGetProductsUseCase {

    List<ProductDomain> getProducts();
}
