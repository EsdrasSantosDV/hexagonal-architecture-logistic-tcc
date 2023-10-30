package hexagonal.architecture.esdras.adapter.out.persistence.jpa.repositories;


import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.StockEntityJpa;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class JpaStockPanancheRepository implements PanacheRepositoryBase<StockEntityJpa, String> {

    public Optional<StockEntityJpa> findStockMain() {
        return find("id", "STK012ASDJK129459128SDA12894012408021ASD").firstResultOptional();
    }
}
