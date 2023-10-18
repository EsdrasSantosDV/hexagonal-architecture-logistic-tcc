package hexagonal.architecture.esdras.application.service.products;

import hexagonal.architecture.esdras.application.port.input.products.InputPortCreateProductUseCase;
import hexagonal.architecture.esdras.application.port.input.products.exceptions.ProductAlreadyExistsException;
import hexagonal.architecture.esdras.application.port.output.products.persistence.OutputPortProductRepository;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;

public class CreateProductService implements InputPortCreateProductUseCase {

    private final OutputPortProductRepository outputPortProductRepository;


    public CreateProductService(OutputPortProductRepository productRepository) {
        this.outputPortProductRepository = productRepository;
    }

    @Override
    public ProductDomain createProduct(ProductDomain productDomain) throws ProductAlreadyExistsException {
//        if (outputPortProductRepository.findById(productDomain.getId().value()).isPresent()) {
//            throw new ProductAlreadyExistsException();
//        }
        System.out.println("CreateProductService.createProduct");
        return outputPortProductRepository.save(productDomain);
    }
}
