package hexagonal.architecture.esdras.adapter.out.persistence.jpa;

import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.ProductsEntityJpa;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers.ProductEntityMapper;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.repositories.JpaProductEntityPanacheRepository;
import hexagonal.architecture.esdras.application.port.output.products.persistence.OutputPortProduct;
import hexagonal.architecture.esdras.domain.entity.ProductDomain;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@LookupIfProperty(name = "persistence", stringValue = "mysql")
@ApplicationScoped
public class JpaProductEntityAdapterRepository implements OutputPortProduct {

    private final JpaProductEntityPanacheRepository panacheRepository;


    public JpaProductEntityAdapterRepository(JpaProductEntityPanacheRepository panacheRepository) {
        this.panacheRepository = panacheRepository;
    }

    @Transactional
    @Override
    public ProductDomain save(ProductDomain productDomain) {
        ProductsEntityJpa jpaEntity = ProductEntityMapper.domainToJpaEntity(productDomain);

        panacheRepository.getEntityManager().merge(jpaEntity);

        return ProductEntityMapper.jpaEntityToDomain(jpaEntity);
    }

    @Override
    public Optional<ProductDomain> findById(String id) {
        ProductsEntityJpa product = panacheRepository.findById(id);
        return ProductEntityMapper.toModelEntityOptional(product);
    }


}
