package hexagonal.architecture.esdras.application.service.products;

import hexagonal.architecture.esdras.application.port.input.invoiceout.InputPortCreateInvoiceOutUseCase;
import hexagonal.architecture.esdras.application.port.input.invoiceout.commands.ProductNfInvoiceOut;
import hexagonal.architecture.esdras.application.port.input.invoiceout.commands.SendNfOutCommand;
import hexagonal.architecture.esdras.application.port.input.invoiceout.exceptions.ProductNotExistOutException;
import hexagonal.architecture.esdras.application.port.output.nfinvoiceout.persistence.OutputPortNfInvoiceOut;
import hexagonal.architecture.esdras.application.port.output.productcore.persistence.OutputPortProductCore;
import hexagonal.architecture.esdras.application.port.output.products.persistence.OutputPortProduct;
import hexagonal.architecture.esdras.application.port.output.stock.persistence.OutputPortStock;
import hexagonal.architecture.esdras.domain.entity.InvoiceOutDomain;
import hexagonal.architecture.esdras.domain.entity.ProductCoreDomain;
import hexagonal.architecture.esdras.domain.entity.StockDomain;
import hexagonal.architecture.esdras.domain.exceptions.QuantityInvalidException;
import hexagonal.architecture.esdras.domain.vo.MoneyDomain;
import hexagonal.architecture.esdras.domain.vo.NfOutIdentityDomain;
import hexagonal.architecture.esdras.domain.vo.SkuProductDomain;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * CreateInvoiceOutService
 * <p>
 * This class is responsible for creating the invoice out
 * and updating the stock
 */
public class CreateInvoiceOutService implements InputPortCreateInvoiceOutUseCase {


    private final OutputPortProduct outputPortProductRepository;

    private final OutputPortNfInvoiceOut outputPortNfInvoiceOutRepository;

    private final OutputPortStock outputPortStockRepository;

    private final OutputPortProductCore outputPortProductCoreRepository;


    public CreateInvoiceOutService(OutputPortProduct productRepository, OutputPortNfInvoiceOut outputPortNfInvoiceOutRepository, OutputPortStock outputPortStockRepository, OutputPortProductCore outputPortProductCoreRepository) {
        this.outputPortProductRepository = productRepository;
        this.outputPortNfInvoiceOutRepository = outputPortNfInvoiceOutRepository;
        this.outputPortStockRepository = outputPortStockRepository;
        this.outputPortProductCoreRepository = outputPortProductCoreRepository;
    }

    @Override
    public InvoiceOutDomain creatInvoiceOutDomain(SendNfOutCommand command) throws ProductNotExistOutException {
        List<String> missingProducts = new ArrayList<>();
        Map<SkuProductDomain, ProductCoreDomain> productCoreDomains = getProductCoreDomainMap(command, missingProducts);
        validateMissingProducts(missingProducts);
        InvoiceOutDomain nfOut = buildInvoiceOutDomain(command);
        addProductsToInvoiceOut(nfOut, productCoreDomains, command);
        this.outputPortStockRepository.findByStockMain()
                .ifPresent(stock -> updateStockWithProducts(stock, productCoreDomains, command));
        return this.outputPortNfInvoiceOutRepository.save(nfOut);
    }

    private void updateStockWithProducts(StockDomain stock, Map<SkuProductDomain, ProductCoreDomain> productCoreDomainMap, SendNfOutCommand command) {
        command.productNfInvoiceOuts().forEach(invoiceOutEntry -> {

            ProductCoreDomain productCoreDomain = productCoreDomainMap.get(new SkuProductDomain(invoiceOutEntry.sku()));
            if (productCoreDomain != null) {
                stock.movementExit(productCoreDomain.getProduct(), invoiceOutEntry.quantity());
            }
        });
        this.outputPortStockRepository.save(stock);
    }

    private void addProductsToInvoiceOut(InvoiceOutDomain nfEntry, Map<SkuProductDomain, ProductCoreDomain> productCoreDomainMap, SendNfOutCommand command) {
        command.productNfInvoiceOuts().forEach(invoiceOutEntry -> {
            ProductCoreDomain productCoreDomain = productCoreDomainMap.get(new SkuProductDomain(invoiceOutEntry.sku()));
            if (productCoreDomain != null) {
                try {

                    nfEntry.addProduct(productCoreDomain, invoiceOutEntry.quantity());
                } catch (QuantityInvalidException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private InvoiceOutDomain buildInvoiceOutDomain(SendNfOutCommand command) {
        return outputPortNfInvoiceOutRepository.findById(command.id())
                .orElseGet(() -> InvoiceOutDomain.builder()
                        .id(new NfOutIdentityDomain(command.id()))
                        .issueDate(command.issueDate())
                        .issuer(command.issuer())
                        .recipient(command.recipient())
                        .totalValue(new MoneyDomain(Currency.getInstance(command.currency().getCurrencyCode()), command.amount()))
                        .build());
    }

    private void validateMissingProducts(List<String> missingProducts) throws ProductNotExistOutException {
        if (!missingProducts.isEmpty()) {
            throw new ProductNotExistOutException("Produtos não encontrados: " + String.join(", ", missingProducts));
        }
    }

    private Map<SkuProductDomain, ProductCoreDomain> getProductCoreDomainMap(SendNfOutCommand command, List<String> missingProducts) {
        return command.productNfInvoiceOuts().stream()
                .map(entry -> buildProductCoreDomain(entry, command, missingProducts))
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(ProductCoreDomain::getSku, Function.identity()));
    }

    private ProductCoreDomain buildProductCoreDomain(ProductNfInvoiceOut entry, SendNfOutCommand command, List<String> missingProducts) {
        String productId = entry.sku();
        Optional<ProductCoreDomain> productCoreDomainOptional = outputPortProductCoreRepository.findBySku(productId);
        if (productCoreDomainOptional.isEmpty()) {
            missingProducts.add(productId);
            return null;
        }
        return productCoreDomainOptional.get();
    }
}
