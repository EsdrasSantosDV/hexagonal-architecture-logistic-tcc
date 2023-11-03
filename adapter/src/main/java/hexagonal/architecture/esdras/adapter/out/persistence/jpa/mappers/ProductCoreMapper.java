package hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers;

import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.NfInvoiceEntryEntityJpa;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.NfInvoiceOutEntityJpa;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.ProductCoreEntityJpa;
import hexagonal.architecture.esdras.domain.entity.ProductCoreDomain;
import hexagonal.architecture.esdras.domain.vo.MoneyDomain;
import hexagonal.architecture.esdras.domain.vo.SkuProductDomain;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

public class ProductCoreMapper {

    private ProductCoreMapper() {
    }

    public static ProductCoreEntityJpa toJpaEntity(ProductCoreDomain productCoreDomain) {
        if (productCoreDomain == null) {
            return null;
        }

        ProductCoreEntityJpa entityJpa = new ProductCoreEntityJpa();

        entityJpa.setSku(productCoreDomain.getSku().value());
        entityJpa.setPriceCurrency(productCoreDomain.getPrice().currency().getCurrencyCode());
        entityJpa.setPriceAmount(productCoreDomain.getPrice().amount());
        entityJpa.setQuantity(productCoreDomain.getQuantity());
        entityJpa.setEntryDate(productCoreDomain.getEntryDate());
        entityJpa.setDueDate(productCoreDomain.getDueDate());
        entityJpa.setProduct(ProductEntityMapper.domainToJpaEntity(productCoreDomain.getProduct()));


        return entityJpa;
    }

    public static ProductCoreEntityJpa toJpaEntityBiDirectional(NfInvoiceEntryEntityJpa jpa, ProductCoreDomain productCoreDomain) {
        if (productCoreDomain == null) {
            return null;
        }

        ProductCoreEntityJpa entityJpa = new ProductCoreEntityJpa();

        entityJpa.setSku(productCoreDomain.getSku().value());
        entityJpa.setPriceCurrency(productCoreDomain.getPrice().currency().getCurrencyCode());
        entityJpa.setPriceAmount(productCoreDomain.getPrice().amount());
        entityJpa.setQuantity(productCoreDomain.getQuantity());
        entityJpa.setEntryDate(productCoreDomain.getEntryDate());
        entityJpa.setDueDate(productCoreDomain.getDueDate());
        entityJpa.setProduct(ProductEntityMapper.domainToJpaEntity(productCoreDomain.getProduct()));
        entityJpa.setNf_entry(jpa);

        return entityJpa;
    }

    public static ProductCoreEntityJpa toJpaEntityBiDirectionalOut(NfInvoiceOutEntityJpa jpa, ProductCoreDomain productCoreDomain) {
        if (productCoreDomain == null) {
            return null;
        }

        ProductCoreEntityJpa entityJpa = new ProductCoreEntityJpa();

        entityJpa.setSku(productCoreDomain.getSku().value());
        entityJpa.setPriceCurrency(productCoreDomain.getPrice().currency().getCurrencyCode());
        entityJpa.setPriceAmount(productCoreDomain.getPrice().amount());
        entityJpa.setQuantity(productCoreDomain.getQuantity());
        entityJpa.setEntryDate(productCoreDomain.getEntryDate());
        entityJpa.setDueDate(productCoreDomain.getDueDate());
        entityJpa.setProduct(ProductEntityMapper.domainToJpaEntity(productCoreDomain.getProduct()));
        entityJpa.setNf_out(jpa);

        return entityJpa;
    }

    public static ProductCoreDomain toDomainEntity(ProductCoreEntityJpa entityJpa) {
        if (entityJpa == null) {
            return null;
        }

        ProductCoreDomain.ProductCoreDomainBuilder builder = ProductCoreDomain.builder();

        builder.sku(new SkuProductDomain(entityJpa.getSku()));
        builder.price(new MoneyDomain(Currency.getInstance(entityJpa.getPriceCurrency()), entityJpa.getPriceAmount()));
        builder.quantity(entityJpa.getQuantity());
        builder.entryDate(entityJpa.getEntryDate());
        builder.dueDate(entityJpa.getDueDate());
        builder.product(ProductEntityMapper.toModelEntityOptional(entityJpa.getProduct()).orElse(null));


        return builder.build();
    }

    public static Optional<ProductCoreDomain> toDomainEntityOptional(ProductCoreEntityJpa entityJpa) {
        return Optional.ofNullable(toDomainEntity(entityJpa));
    }

    public static List<ProductCoreDomain> toModelEntities(List<ProductCoreEntityJpa> jpaEntities) {
        return jpaEntities.stream().map(ProductCoreMapper::toDomainEntity).toList();
    }
}
