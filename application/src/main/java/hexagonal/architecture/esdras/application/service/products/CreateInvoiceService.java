package hexagonal.architecture.esdras.application.service.products;

import hexagonal.architecture.esdras.application.port.input.invoiceentry.InputPortCreateInvoiceEntryUseCase;
import hexagonal.architecture.esdras.application.port.input.invoiceentry.commands.ProductNfInvoiceEntry;
import hexagonal.architecture.esdras.application.port.input.invoiceentry.commands.SendNfCommand;
import hexagonal.architecture.esdras.application.port.input.invoiceentry.exceptions.ProductNotExistException;
import hexagonal.architecture.esdras.application.port.output.nfinvoiceentry.persistence.OutputPortNfInvoiceEntry;
import hexagonal.architecture.esdras.application.port.output.products.persistence.OutputPortProduct;
import hexagonal.architecture.esdras.domain.entity.InvoiceEntryDomain;
import hexagonal.architecture.esdras.domain.entity.ProductCoreDomain;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;
import hexagonal.architecture.esdras.domain.exceptions.QuantityInvalidException;
import hexagonal.architecture.esdras.domain.vo.MoneyDomain;
import hexagonal.architecture.esdras.domain.vo.NfEntryIdDomain;

import java.util.*;

public class CreateInvoiceService implements InputPortCreateInvoiceEntryUseCase {

    private final OutputPortProduct outputPortProductRepository;


    private final OutputPortNfInvoiceEntry outputPortNfInvoiceEntryRepository;

    public CreateInvoiceService(OutputPortProduct productRepository, OutputPortNfInvoiceEntry outputPortNfInvoiceEntryRepository) {
        this.outputPortProductRepository = productRepository;
        this.outputPortNfInvoiceEntryRepository = outputPortNfInvoiceEntryRepository;
    }


    @Override
    public InvoiceEntryDomain createInvoiceEntry(SendNfCommand command) throws ProductNotExistException {

        List<String> missingProducts = new ArrayList<>();

        List<ProductCoreDomain> productCoreDomains = command.productNfInvoiceEntries()
                .stream()
                .map(entry -> buildProductCoreDomain(entry, command, missingProducts))
                .filter(Objects::nonNull)
                .toList();

        if (!missingProducts.isEmpty()) {
            throw new ProductNotExistException("Produtos não encontrados: " + String.join(", ", missingProducts));
        }

        InvoiceEntryDomain nfEntry =
                outputPortNfInvoiceEntryRepository.findById(command.id())
                        .orElseGet(() -> InvoiceEntryDomain.builder()
                                .id(new NfEntryIdDomain(command.id()))
                                .issueDate(command.issueDate())
                                .issuer(command.issuer())
                                .recipient(command.recipient())
                                .totalValue(new MoneyDomain(Currency.getInstance(command.currency().getCurrencyCode()), command.amount()))
                                .build());


        for (ProductCoreDomain productCoreDomain : productCoreDomains) {
            try {
                nfEntry.addProduct(productCoreDomain, productCoreDomain.getQuantity());
            } catch (QuantityInvalidException e) {
                throw new RuntimeException(e);
            }
        }


        return this.outputPortNfInvoiceEntryRepository.save(nfEntry);
    }

    private ProductCoreDomain buildProductCoreDomain(ProductNfInvoiceEntry entry, SendNfCommand command, List<String> missingProducts) {
        String productId = entry.productGenericId();
        Optional<ProductDomain> productOpt = outputPortProductRepository.findById(productId);
        if (productOpt.isEmpty()) {
            missingProducts.add(productId);
            return null;
        }
        ProductCoreDomain productCoreDomain = ProductCoreDomain.builder()
                .product(productOpt.get())
                .dueDate(entry.dueDate())
                .price(new MoneyDomain(Currency.getInstance(entry.currency().getCurrencyCode()), entry.amount()))
                .build();

        productCoreDomain.dateNow();
        productCoreDomain.generateSkuFromProduct();
        productCoreDomain.setQuantity(entry.quantity());

        System.out.println(productCoreDomain);


        return productCoreDomain;
    }
}
