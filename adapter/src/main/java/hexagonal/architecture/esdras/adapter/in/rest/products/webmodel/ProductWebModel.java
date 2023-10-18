package hexagonal.architecture.esdras.adapter.in.rest.products.webmodel;

import hexagonal.architecture.esdras.domain.entity.ProductDomain;

import java.math.BigDecimal;
import java.util.Currency;

public record ProductWebModel(
        String id,
        String name,
        String description,
        String category,
        Double height,
        Double width,
        Double depth,
        Currency currency,
        BigDecimal amount,
        String storageInstructions,
        String restrictions
) {
    public static ProductWebModel fromDomainModel(ProductDomain productDomain) {
        return new ProductWebModel(
                productDomain.getId().value(),
                productDomain.getName(),
                productDomain.getDescription(),
                productDomain.getCategory().name(),
                productDomain.getHeight(),
                productDomain.getWidth(),
                productDomain.getDepth(),
                productDomain.getPrice().currency(),
                productDomain.getPrice().amount(),
                productDomain.getStorageInstructions(),
                productDomain.getRestrictions()
        );
    }
}
