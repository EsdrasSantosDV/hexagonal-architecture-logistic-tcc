package hexagonal.architecture.esdras.bootstrap;


import hexagonal.architecture.esdras.application.port.input.invoiceentry.InputPortCreateInvoiceEntryUseCase;
import hexagonal.architecture.esdras.application.port.input.products.InputPortCreateProductUseCase;
import hexagonal.architecture.esdras.application.port.output.nfinvoiceentry.persistence.OutputPortNfInvoiceEntry;
import hexagonal.architecture.esdras.application.port.output.productcore.persistence.OutputPortProductCore;
import hexagonal.architecture.esdras.application.port.output.products.persistence.OutputPortProduct;
import hexagonal.architecture.esdras.application.port.output.stock.persistence.OutputPortStock;
import hexagonal.architecture.esdras.application.service.products.CreateInvoiceService;
import hexagonal.architecture.esdras.application.service.products.CreateProductService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

@ApplicationScoped
class QuarkusAppConfig {

    @Inject
    Instance<OutputPortProduct> outputPortProductRepository;

    @Inject
    Instance<OutputPortProductCore> outputPortProductCoreRepository;

    @Inject
    Instance<OutputPortNfInvoiceEntry> outputPortNfInvoiceEntryRepository;

    @Inject
    Instance<OutputPortStock> outputPortStockRepository;

    @Produces
    @ApplicationScoped
    InputPortCreateProductUseCase inputPortCreateProductUseCase() {
        return new CreateProductService(outputPortProductRepository.get());
    }

    @Produces
    @ApplicationScoped
    InputPortCreateInvoiceEntryUseCase inputPortCreateInvoiceEntryUseCase() {
        return new CreateInvoiceService(outputPortProductRepository.get(), outputPortNfInvoiceEntryRepository.get(), outputPortStockRepository.get());
    }


}
