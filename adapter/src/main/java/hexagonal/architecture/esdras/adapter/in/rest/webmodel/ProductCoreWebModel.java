package hexagonal.architecture.esdras.adapter.in.rest.webmodel;

import hexagonal.architecture.esdras.domain.entity.ProductCoreDomain;

import java.math.BigDecimal;

public record ProductCoreWebModel(
        String sku,
        String name,
        int quantity,
        BigDecimal amount,
        String currency
) {
    public static ProductCoreWebModel fromDomainModel(ProductCoreDomain productCoreDomain) {
        return new ProductCoreWebModel(
                productCoreDomain.getSku().value(),
                productCoreDomain.getProduct().getName(),
                productCoreDomain.getQuantity(),
                productCoreDomain.getPrice().amount(),
                productCoreDomain.getPrice().currency().getCurrencyCode()
        );
    }
}