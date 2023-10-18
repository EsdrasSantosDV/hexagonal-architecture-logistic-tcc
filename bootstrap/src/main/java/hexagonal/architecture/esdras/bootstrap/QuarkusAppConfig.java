package hexagonal.architecture.esdras.bootstrap;


import hexagonal.architecture.esdras.application.port.input.products.InputPortCreateProductUseCase;
import hexagonal.architecture.esdras.application.port.output.products.persistence.OutputPortProductRepository;
import hexagonal.architecture.esdras.application.service.products.CreateProductService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

@ApplicationScoped
class QuarkusAppConfig {

    @Inject
    Instance<OutputPortProductRepository> outputPortProductRepository;

    @Produces
    @ApplicationScoped
    InputPortCreateProductUseCase inputPortCreateProductUseCase() {
        return new CreateProductService(outputPortProductRepository.get());
    }


}
