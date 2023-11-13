package hexagonal.architecture.esdras.application.service.products;

import hexagonal.architecture.esdras.application.port.input.products.InputPortGetProductsUseCase;
import hexagonal.architecture.esdras.application.port.output.products.persistence.OutputPortProduct;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;

import java.util.List;

public class GetProductsService implements InputPortGetProductsUseCase {

    private final OutputPortProduct outputPortProductRepository;

    public GetProductsService(OutputPortProduct productRepository) {
        this.outputPortProductRepository = productRepository;
    }

    @Override
    public List<ProductDomain> getProducts() {
        return this.outputPortProductRepository.findAllFiltered();
    }
}
