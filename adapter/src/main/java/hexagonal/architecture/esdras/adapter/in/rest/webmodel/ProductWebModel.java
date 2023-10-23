package hexagonal.architecture.esdras.adapter.in.rest.webmodel;

import hexagonal.architecture.esdras.domain.entity.ProductDomain;

public record ProductWebModel(
        String id,
        String name,
        String description,
        String category,
        Double height,
        Double width,
        Double depth,
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
                productDomain.getStorageInstructions(),
                productDomain.getRestrictions()
        );
    }
}
