package hexagonal.architecture.esdras.adapter.out.persistence.jpa;


import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.StockEntityJpa;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers.StockMapper;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.repositories.JpaStockPanancheRepository;
import hexagonal.architecture.esdras.application.port.output.stock.persistence.OutputPortStock;
import hexagonal.architecture.esdras.domain.entity.StockDomain;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@LookupIfProperty(name = "persistence", stringValue = "mysql")
@ApplicationScoped
public class JpaStockEntityAdapterRepository implements OutputPortStock {

    private final JpaStockPanancheRepository panacheRepository;

    public JpaStockEntityAdapterRepository(JpaStockPanancheRepository panacheRepository) {
        this.panacheRepository = panacheRepository;
    }

    @Transactional
    @Override
    public StockDomain save(StockDomain stockDomain) {
        StockEntityJpa jpaEntity = StockMapper.toJpaEntity(stockDomain);

        panacheRepository.getEntityManager().merge(jpaEntity);

        return StockMapper.toDomainEntity(jpaEntity);
    }

    @Override
    public Optional<StockDomain> findById(String id) {

        StockEntityJpa stock = panacheRepository.findById(id);
        return StockMapper.toDomainEntityOptional(stock);

    }

    @Override
    public Optional<StockDomain> findByStockMain() {
        StockEntityJpa stock = panacheRepository.findStockMain().orElse(null);
        return StockMapper.toDomainEntityOptional(stock);
    }
}
