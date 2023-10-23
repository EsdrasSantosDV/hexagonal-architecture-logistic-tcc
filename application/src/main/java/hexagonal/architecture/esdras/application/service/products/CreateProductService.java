package hexagonal.architecture.esdras.application.service.products;

import hexagonal.architecture.esdras.application.port.input.products.InputPortCreateProductUseCase;
import hexagonal.architecture.esdras.application.port.input.products.exceptions.ProductAlreadyExistsException;
import hexagonal.architecture.esdras.application.port.output.products.persistence.OutputPortProduct;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;

public class CreateProductService implements InputPortCreateProductUseCase {

    private final OutputPortProduct outputPortProductRepository;


    public CreateProductService(OutputPortProduct productRepository) {
        this.outputPortProductRepository = productRepository;
    }

    @Override
    public ProductDomain createProduct(ProductDomain productDomain) throws ProductAlreadyExistsException {
        if (outputPortProductRepository.findById(productDomain.getId().value()).isPresent()) {
            throw new ProductAlreadyExistsException("Produto ja possui cadastro");
        }
        return outputPortProductRepository.save(productDomain);
    }
}
