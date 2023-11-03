package hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers;

import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.NfInvoiceOutEntityJpa;
import hexagonal.architecture.esdras.domain.entity.InvoiceOutDomain;
import hexagonal.architecture.esdras.domain.entity.ProductCoreDomain;
import hexagonal.architecture.esdras.domain.vo.MoneyDomain;
import hexagonal.architecture.esdras.domain.vo.NfOutIdentityDomain;

import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NfInvoiceOutMapper {

    private NfInvoiceOutMapper() {
    }

    public static NfInvoiceOutEntityJpa toJpaEntity(InvoiceOutDomain domain) {
        if (domain == null) {
            return null;
        }

        NfInvoiceOutEntityJpa entityJpa = new NfInvoiceOutEntityJpa();

        entityJpa.setId(domain.getId().value());
        entityJpa.setIssueDate(domain.getIssueDate());
        entityJpa.setIssuer(domain.getIssuer());
        entityJpa.setRecipient(domain.getRecipient());
        entityJpa.setPriceCurrency(domain.getTotalValue().currency().getCurrencyCode());
        entityJpa.setPriceAmount(domain.getTotalValue().amount());

        entityJpa.setProducts(domain.productsList().stream()
                .map(product -> ProductCoreMapper.toJpaEntityBiDirectionalOut(entityJpa, product))
                .collect(Collectors.toList()));


        return entityJpa;
    }


    public static InvoiceOutDomain toDomainEntity(NfInvoiceOutEntityJpa entityJpa) {
        if (entityJpa == null) {
            return null;
        }

        InvoiceOutDomain.InvoiceOutDomainBuilder builder = InvoiceOutDomain.builder();

        builder.id(new NfOutIdentityDomain(entityJpa.getId()));
        builder.issueDate(entityJpa.getIssueDate());
        builder.issuer(entityJpa.getIssuer());
        builder.recipient(entityJpa.getRecipient());
        builder.totalValue(new MoneyDomain(Currency.getInstance(entityJpa.getPriceCurrency()), entityJpa.getPriceAmount()));

        InvoiceOutDomain tempDomain = new InvoiceOutDomain(new NfOutIdentityDomain(entityJpa.getId()));
        entityJpa.getProducts().forEach(productEntity -> {
            ProductCoreDomain productDomain = ProductCoreMapper.toDomainEntity(productEntity);
            tempDomain.putProductIgnoringNotEnoughItemsInStock(productDomain, productDomain.getQuantity());
        });
        builder.products(tempDomain.getProducts());

        return builder.build();
    }

    public static Optional<InvoiceOutDomain> toDomainEntityOptional(NfInvoiceOutEntityJpa entityJpa) {
        return Optional.ofNullable(toDomainEntity(entityJpa));
    }


    static List<InvoiceOutDomain> toModelEntities(List<NfInvoiceOutEntityJpa> jpaEntities) {
        return jpaEntities.stream().map(NfInvoiceOutMapper::toDomainEntity).toList();
    }

}
