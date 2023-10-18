package hexagonal.architecture.esdras.application.port.input.products;

import hexagonal.architecture.esdras.application.port.input.products.exceptions.ProductAlreadyExistsException;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;

/**
 * InputPortCreateProductUseCase
 * responsible for create unique Product
 *
 * @author esdras
 */
public interface InputPortCreateProductUseCase {
    ProductDomain createProduct(ProductDomain productDomain) throws ProductAlreadyExistsException;
}
