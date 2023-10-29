package hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers;

import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.ItemStockEntityJpa;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.StockEntityJpa;
import hexagonal.architecture.esdras.domain.entity.ItemStockDomain;
import hexagonal.architecture.esdras.domain.vo.ItemStockIdentifyDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemStockMapper {

    private ItemStockMapper() {
    }

    public static ItemStockEntityJpa toJpaEntity(ItemStockDomain itemStockDomain) {
        if (itemStockDomain == null) {
            return null;
        }

        ItemStockEntityJpa entityJpa = new ItemStockEntityJpa();

        entityJpa.setId(itemStockDomain.getId().value());
        entityJpa.setQuantity(itemStockDomain.getQuantity());
        entityJpa.setProduct(ProductEntityMapper.domainToJpaEntity(itemStockDomain.getProduct()));
        return entityJpa;
    }


    public static ItemStockEntityJpa toJpaEntityBiDirectional(StockEntityJpa jpa, ItemStockDomain itemStockDomain) {
        if (itemStockDomain == null) {
            return null;
        }
        ItemStockEntityJpa entityJpa = new ItemStockEntityJpa();
        entityJpa.setId(itemStockDomain.getId().value());
        entityJpa.setQuantity(itemStockDomain.getQuantity());
        entityJpa.setProduct(ProductEntityMapper.domainToJpaEntity(itemStockDomain.getProduct()));
        entityJpa.setStock(jpa);
        return entityJpa;
    }


    public static ItemStockDomain toDomainEntity(ItemStockEntityJpa entityJpa) {
        if (entityJpa == null) {
            return null;
        }

        ItemStockDomain.ItemStockDomainBuilder builder = ItemStockDomain.builder();

        builder.id(new ItemStockIdentifyDomain(entityJpa.getId()));
        builder.quantity(entityJpa.getQuantity());
        builder.product(ProductEntityMapper.toModelEntityOptional(entityJpa.getProduct()).orElse(null));

        return builder.build();
    }

    public static Optional<ItemStockDomain> toDomainEntityOptional(ItemStockEntityJpa entityJpa) {
        return Optional.ofNullable(toDomainEntity(entityJpa));
    }

    public static List<ItemStockDomain> toModelEntities(List<ItemStockEntityJpa> jpaEntities) {
        return jpaEntities.stream().map(ItemStockMapper::toDomainEntity).collect(Collectors.toList());
    }


}
