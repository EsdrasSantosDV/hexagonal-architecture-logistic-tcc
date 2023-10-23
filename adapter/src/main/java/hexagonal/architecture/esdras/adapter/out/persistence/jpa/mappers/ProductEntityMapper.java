package hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers;

import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.ProductsEntityJpa;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;
import hexagonal.architecture.esdras.domain.vo.ProductIdDomain;

import java.util.Optional;

public class ProductEntityMapper {

    public ProductEntityMapper() {
    }

    public static ProductsEntityJpa domainToJpaEntity(ProductDomain domain) {
        ProductsEntityJpa entity = new ProductsEntityJpa();
        entity.setId(domain.getId().value());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setCategory(domain.getCategory());
        entity.setHeight(domain.getHeight());
        entity.setWidth(domain.getWidth());
        entity.setDepth(domain.getDepth());
        entity.setStorageInstructions(domain.getStorageInstructions());
        entity.setRestrictions(domain.getRestrictions());

        return entity;
    }

    public static Optional<ProductDomain> toModelEntityOptional(ProductsEntityJpa jpaEntity) {
        return Optional.ofNullable(jpaEntity).map(ProductEntityMapper::jpaEntityToDomain);
    }

    public static ProductDomain jpaEntityToDomain(ProductsEntityJpa entity) {
        return ProductDomain.builder()
                .id(new ProductIdDomain(entity.getId()))
                .name(entity.getName())
                .description(entity.getDescription())
                .category(entity.getCategory())
                .height(entity.getHeight())
                .width(entity.getWidth())
                .depth(entity.getDepth())
                .storageInstructions(entity.getStorageInstructions())
                .restrictions(entity.getRestrictions())
                .build();
    }


}
