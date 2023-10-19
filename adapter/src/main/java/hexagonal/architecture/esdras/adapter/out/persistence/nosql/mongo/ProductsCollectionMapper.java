package hexagonal.architecture.esdras.adapter.out.persistence.nosql.mongo;

import hexagonal.architecture.esdras.domain.entity.ProductDomain;
import hexagonal.architecture.esdras.domain.vo.MoneyDomain;
import hexagonal.architecture.esdras.domain.vo.ProductCategoryDomain;
import hexagonal.architecture.esdras.domain.vo.ProductIdDomain;

import java.util.Currency;
import java.util.Optional;

public class ProductsCollectionMapper {

    public static ProductsMongoCollection domainToMongo(ProductDomain domain) {
        ProductsMongoCollection entity = new ProductsMongoCollection();
        entity.setId(domain.getId().value());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setCategory(domain.getCategory().name());
        entity.setHeight(domain.getHeight());
        entity.setWidth(domain.getWidth());
        entity.setDepth(domain.getDepth());
        entity.setStorageInstructions(domain.getStorageInstructions());
        entity.setRestrictions(domain.getRestrictions());

        if (domain.getPrice() != null) {
            entity.setPriceCurrency(domain.getPrice().currency().getCurrencyCode());
            entity.setPriceAmount(domain.getPrice().amount());
        }

        return entity;
    }

    static Optional<ProductDomain> productDomainOptional(ProductsMongoCollection jpaEntity) {
        return Optional.ofNullable(jpaEntity).map(ProductsCollectionMapper::mongoToDomain);
    }

    public static ProductDomain mongoToDomain(ProductsMongoCollection entity) {
        return ProductDomain.builder()
                .id(new ProductIdDomain(entity.getId()))
                .name(entity.getName())
                .description(entity.getDescription())
                .category(ProductCategoryDomain.valueOf(entity.getCategory()))
                .price(new MoneyDomain(Currency.getInstance(entity.getPriceCurrency()), entity.getPriceAmount()))
                .height(entity.getHeight())
                .width(entity.getWidth())
                .depth(entity.getDepth())
                .storageInstructions(entity.getStorageInstructions())
                .restrictions(entity.getRestrictions())
                .build();
    }

}
