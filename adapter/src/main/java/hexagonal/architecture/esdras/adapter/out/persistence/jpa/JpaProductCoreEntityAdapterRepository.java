package hexagonal.architecture.esdras.adapter.out.persistence.jpa;


import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.ProductCoreEntityJpa;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers.ProductCoreMapper;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.repositories.JpaProductCoreEntityPanancheRepository;
import hexagonal.architecture.esdras.application.port.output.productcore.persistence.OutputPortProductCore;
import hexagonal.architecture.esdras.domain.entity.ProductCoreDomain;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@LookupIfProperty(name = "persistence", stringValue = "mysql")
@ApplicationScoped
public class JpaProductCoreEntityAdapterRepository implements OutputPortProductCore {

    private final JpaProductCoreEntityPanancheRepository panacheRepository;

    public JpaProductCoreEntityAdapterRepository(JpaProductCoreEntityPanancheRepository panacheRepository) {
        this.panacheRepository = panacheRepository;
    }


    @Transactional
    @Override
    public ProductCoreDomain save(ProductCoreDomain productCore) {
        ProductCoreEntityJpa jpaEntity = ProductCoreMapper.toJpaEntity(productCore);


        panacheRepository.getEntityManager().merge(jpaEntity);
        return ProductCoreMapper.toDomainEntity(jpaEntity);
    }

    @Override
    public Optional<ProductCoreDomain> findBySku(Integer id) {
        ProductCoreEntityJpa product = panacheRepository.findById(id);
        return ProductCoreMapper.toDomainEntityOptional(product);
    }
}
