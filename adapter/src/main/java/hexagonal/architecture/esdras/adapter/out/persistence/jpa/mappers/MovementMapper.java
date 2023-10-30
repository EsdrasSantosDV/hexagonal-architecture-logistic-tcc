package hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers;

import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.MovementEntityJpa;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.StockEntityJpa;
import hexagonal.architecture.esdras.domain.entity.MovementDomain;
import hexagonal.architecture.esdras.domain.vo.MovementTypeDomain;
import hexagonal.architecture.esdras.domain.vo.MovimentationIdentityDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovementMapper {

    private MovementMapper() {
    }

    public static MovementEntityJpa toJpaEntity(MovementDomain movementDomain, StockEntityJpa stockEntityJpa) {
        if (movementDomain == null) {
            return null;
        }

        MovementEntityJpa entityJpa = new MovementEntityJpa();

        entityJpa.setId(movementDomain.getId().value());
        entityJpa.setType(movementDomain.getType().toString());
        entityJpa.setQuantity(movementDomain.getQuantity());
        entityJpa.setDateOfMovement(movementDomain.getDateOfMovement());
        entityJpa.setProduct(ProductEntityMapper.domainToJpaEntity(movementDomain.getProduct()));
        entityJpa.setStock(stockEntityJpa);

        return entityJpa;
    }

    public static MovementDomain toDomainEntity(MovementEntityJpa entityJpa) {
        if (entityJpa == null) {
            return null;
        }

        MovementDomain.MovementDomainBuilder builder = MovementDomain.builder();

        builder.id(new MovimentationIdentityDomain(entityJpa.getId()));
        builder.type(MovementTypeDomain.valueOf(entityJpa.getType()));
        builder.quantity(entityJpa.getQuantity());
        builder.dateOfMovement(entityJpa.getDateOfMovement());
        builder.product(ProductEntityMapper.toModelEntityOptional(entityJpa.getProduct()).orElse(null));

        return builder.build();
    }


    public static Optional<MovementDomain> toDomainEntityOptional(MovementEntityJpa entityJpa) {
        return Optional.ofNullable(toDomainEntity(entityJpa));
    }

    public static List<MovementDomain> toModelEntities(List<MovementEntityJpa> jpaEntities) {
        return jpaEntities.stream().map(MovementMapper::toDomainEntity).collect(Collectors.toList());
    }
}