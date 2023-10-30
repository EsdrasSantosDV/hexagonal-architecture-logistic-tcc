package hexagonal.architecture.esdras.application.service.products;

import hexagonal.architecture.esdras.application.port.input.invoiceentry.InputPortCreateInvoiceEntryUseCase;
import hexagonal.architecture.esdras.application.port.input.invoiceentry.commands.ProductNfInvoiceEntry;
import hexagonal.architecture.esdras.application.port.input.invoiceentry.commands.SendNfCommand;
import hexagonal.architecture.esdras.application.port.input.invoiceentry.exceptions.ProductNotExistException;
import hexagonal.architecture.esdras.application.port.output.nfinvoiceentry.persistence.OutputPortNfInvoiceEntry;
import hexagonal.architecture.esdras.application.port.output.products.persistence.OutputPortProduct;
import hexagonal.architecture.esdras.application.port.output.stock.persistence.OutputPortStock;
import hexagonal.architecture.esdras.domain.entity.InvoiceEntryDomain;
import hexagonal.architecture.esdras.domain.entity.ProductCoreDomain;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;
import hexagonal.architecture.esdras.domain.entity.StockDomain;
import hexagonal.architecture.esdras.domain.exceptions.QuantityInvalidException;
import hexagonal.architecture.esdras.domain.vo.MoneyDomain;
import hexagonal.architecture.esdras.domain.vo.NfEntryIdDomain;

import java.util.*;

public class CreateInvoiceService implements InputPortCreateInvoiceEntryUseCase {

    private final OutputPortProduct outputPortProductRepository;

    private final OutputPortNfInvoiceEntry outputPortNfInvoiceEntryRepository;

    private final OutputPortStock outputPortStockRepository;

    public CreateInvoiceService(OutputPortProduct productRepository, OutputPortNfInvoiceEntry outputPortNfInvoiceEntryRepository, OutputPortStock outputPortStockRepository) {
        this.outputPortProductRepository = productRepository;
        this.outputPortNfInvoiceEntryRepository = outputPortNfInvoiceEntryRepository;
        this.outputPortStockRepository = outputPortStockRepository;
    }


    @Override
    public InvoiceEntryDomain createInvoiceEntry(SendNfCommand command) throws ProductNotExistException {
        List<String> missingProducts = new ArrayList<>();
        List<ProductCoreDomain> productCoreDomains = getProductCoreDomains(command, missingProducts);

        validateMissingProducts(missingProducts);

        InvoiceEntryDomain nfEntry = buildInvoiceEntryDomain(command);
        addProductsToInvoiceEntry(nfEntry, productCoreDomains);

        this.outputPortStockRepository.findByStockMain()
                .ifPresent(stock -> updateStockWithProducts(stock, productCoreDomains));


        return this.outputPortNfInvoiceEntryRepository.save(nfEntry);
    }

    private void updateStockWithProducts(StockDomain stock, List<ProductCoreDomain> productCoreDomains) {
        for (ProductCoreDomain productCoreDomain : productCoreDomains) {
            ProductDomain product = productCoreDomain.getProduct();
            stock.movementEntry(product, productCoreDomain.getQuantity());
        }
        this.outputPortStockRepository.save(stock);
    }

    private List<ProductCoreDomain> getProductCoreDomains(SendNfCommand command, List<String> missingProducts) {
        return command.productNfInvoiceEntries()
                .stream()
                .map(entry -> buildProductCoreDomain(entry, command, missingProducts))
                .filter(Objects::nonNull)
                .toList();
    }

    private void validateMissingProducts(List<String> missingProducts) throws ProductNotExistException {
        if (!missingProducts.isEmpty()) {
            throw new ProductNotExistException("Produtos não encontrados: " + String.join(", ", missingProducts));
        }
    }

    private InvoiceEntryDomain buildInvoiceEntryDomain(SendNfCommand command) {
        return outputPortNfInvoiceEntryRepository.findById(command.id())
                .orElseGet(() -> InvoiceEntryDomain.builder()
                        .id(new NfEntryIdDomain(command.id()))
                        .issueDate(command.issueDate())
                        .issuer(command.issuer())
                        .recipient(command.recipient())
                        .totalValue(new MoneyDomain(Currency.getInstance(command.currency().getCurrencyCode()), command.amount()))
                        .build());
    }

    private void addProductsToInvoiceEntry(InvoiceEntryDomain nfEntry, List<ProductCoreDomain> productCoreDomains) {
        for (ProductCoreDomain productCoreDomain : productCoreDomains) {
            try {
                nfEntry.addProduct(productCoreDomain, productCoreDomain.getQuantity());
            } catch (QuantityInvalidException e) {
                throw new RuntimeException(e);
            }
        }
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


        return productCoreDomain;
    }
}
