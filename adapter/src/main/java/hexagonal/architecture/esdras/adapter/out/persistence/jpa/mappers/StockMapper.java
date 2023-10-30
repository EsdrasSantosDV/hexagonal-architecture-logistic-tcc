package hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers;


import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.ItemStockEntityJpa;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.MovementEntityJpa;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.StockEntityJpa;
import hexagonal.architecture.esdras.domain.entity.ItemStockDomain;
import hexagonal.architecture.esdras.domain.entity.StockDomain;
import hexagonal.architecture.esdras.domain.vo.ProductIdDomain;
import hexagonal.architecture.esdras.domain.vo.StockIdentityDomain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StockMapper {

    private StockMapper() {
    }

    public static StockEntityJpa toJpaEntity(StockDomain stockDomain) {
        if (stockDomain == null) {
            return null;
        }

        StockEntityJpa entityJpa = new StockEntityJpa();

        entityJpa.setId(stockDomain.getId().value());
        entityJpa.setName(stockDomain.getName());

        List<MovementEntityJpa> movements = stockDomain.getMovements().stream()
                .map(movementDomain -> MovementMapper.toJpaEntity(movementDomain, entityJpa))
                .collect(Collectors.toList());
        movements.forEach(movement -> movement.setStock(entityJpa));
        entityJpa.setMovements(movements);

        Set<ItemStockEntityJpa> items = stockDomain.getStock().values().stream()
                .map(ItemStockMapper::toJpaEntity)
                .collect(Collectors.toSet());
        items.forEach(item -> item.setStock(entityJpa));
        entityJpa.setItems(items);

        return entityJpa;
    }

    public static StockDomain toDomainEntity(StockEntityJpa entityJpa) {
        if (entityJpa == null) {
            return null;
        }

        StockDomain.StockDomainBuilder builder = StockDomain.builder();

        builder.id(new StockIdentityDomain(entityJpa.getId()));
        builder.name(entityJpa.getName());
        builder.movements(entityJpa.getMovements().stream()
                .map(MovementMapper::toDomainEntity)
                .collect(Collectors.toList()));

        Map<ProductIdDomain, ItemStockDomain> stockItems = entityJpa.getItems().stream()
                .map(ItemStockMapper::toDomainEntity)
                .collect(Collectors.toMap(ItemStockDomain::getProductId, item -> item));

        builder.stock(stockItems);

        return builder.build();
    }

    public static Optional<StockDomain> toDomainEntityOptional(StockEntityJpa entityJpa) {
        return Optional.ofNullable(toDomainEntity(entityJpa));
    }

    public static List<StockDomain> toModelEntities(List<StockEntityJpa> jpaEntities) {
        return jpaEntities.stream().map(StockMapper::toDomainEntity).collect(Collectors.toList());
    }
}