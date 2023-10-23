package hexagonal.architecture.esdras.domain.entity;

import hexagonal.architecture.esdras.domain.exceptions.CurrencyNfNotCompatibleWithProductException;
import hexagonal.architecture.esdras.domain.exceptions.InvoiceValueExceededException;
import hexagonal.architecture.esdras.domain.exceptions.QuantityInvalidException;
import hexagonal.architecture.esdras.domain.vo.MoneyDomain;
import hexagonal.architecture.esdras.domain.vo.NfEntryIdDomain;
import hexagonal.architecture.esdras.domain.vo.SkuProductDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class InvoiceEntryDomain {
    private final NfEntryIdDomain id;
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
        } else {
            MoneyDomain newProductTotal = product.getPrice().multiply(quantity);
            MoneyDomain expectedTotalAfterAddition = totalValueNFSum().add(newProductTotal);
            if (expectedTotalAfterAddition.amount().compareTo(totalValue.amount()) > 0) {
                throw new InvoiceValueExceededException("A adição deste produto excede o valor total definido na nota fiscal.");
            }
        }
        ProductCoreDomain currentProduct = products.computeIfAbsent(product.getSku(), ignored -> product);
        currentProduct.increaseQuantityBy(quantity);
    }

    public void putProductIgnoringNotEnoughItemsInStock(ProductCoreDomain product, int quantity) {
        products.put(product.getSku(), product);
    }

    public List<ProductCoreDomain> productsList() {
        return List.copyOf(products.values());
    }

    public int numberOfItems() {
        return products.values().stream().mapToInt(ProductCoreDomain::getQuantity).sum();
    }

    public boolean validationTotalValue() {
        return totalValue.equals(products.values().stream().map(ProductCoreDomain::calculateTotalPrice).reduce(MoneyDomain::add).orElse(null));
    }

    public MoneyDomain totalValueNFSum() {
        return products.values().stream().map(ProductCoreDomain::calculateTotalPrice).reduce(MoneyDomain::add).orElse(null);
    }


}
