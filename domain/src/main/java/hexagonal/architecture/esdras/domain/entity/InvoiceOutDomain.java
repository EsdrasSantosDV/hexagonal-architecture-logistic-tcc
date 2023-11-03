package hexagonal.architecture.esdras.domain.entity;


import hexagonal.architecture.esdras.domain.exceptions.CurrencyNfNotCompatibleWithProductException;
import hexagonal.architecture.esdras.domain.exceptions.QuantityInvalidException;
import hexagonal.architecture.esdras.domain.vo.MoneyDomain;
import hexagonal.architecture.esdras.domain.vo.NfOutIdentityDomain;
import hexagonal.architecture.esdras.domain.vo.SkuProductDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class InvoiceOutDomain {
    private final NfOutIdentityDomain id;
    private Date issueDate;
    private String issuer;
    private String recipient;
    private Map<SkuProductDomain, ProductCoreDomain> products = new LinkedHashMap<>();
    private MoneyDomain totalValue;


    public void addProduct(ProductCoreDomain product, int quantity) throws QuantityInvalidException {
        if (!product.getPrice().currency().equals(totalValue.currency())) {
            throw new CurrencyNfNotCompatibleWithProductException();
        }
        if (products == null) {
            products = new LinkedHashMap<>();
        }
        Optional.ofNullable(products.get(product.getSku()))
                .ifPresentOrElse(
                        existingProduct -> existingProduct.decreaseQuantityBy(quantity),
                        () -> products.put(product.getSku(), product)
                );


    }


    public void putProductIgnoringNotEnoughItemsInStock(ProductCoreDomain product, int quantity) {
        products.put(product.getSku(), product);
    }

    public List<ProductCoreDomain> productsList() {
        return List.copyOf(products.values());
    }

    public boolean validationTotalValue() {
        return totalValue.equals(products.values().stream().map(ProductCoreDomain::calculateTotalPrice).reduce(MoneyDomain::add).orElse(null));
    }

    public MoneyDomain totalValueNFSum() {
        return products.values().stream().map(ProductCoreDomain::calculateTotalPrice).reduce(MoneyDomain::add).orElse(null);
    }


}
