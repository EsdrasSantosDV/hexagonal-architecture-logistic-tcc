package hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers;

import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.NfInvoiceEntryEntityJpa;
import hexagonal.architecture.esdras.domain.entity.InvoiceEntryDomain;
import hexagonal.architecture.esdras.domain.entity.ProductCoreDomain;
import hexagonal.architecture.esdras.domain.vo.MoneyDomain;
import hexagonal.architecture.esdras.domain.vo.NfEntryIdDomain;

import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NfInvoiceEntryMapper {


    private NfInvoiceEntryMapper() {
    }

    public static NfInvoiceEntryEntityJpa toJpaEntity(InvoiceEntryDomain domain) {
        if (domain == null) {
            return null;
        }

        NfInvoiceEntryEntityJpa entityJpa = new NfInvoiceEntryEntityJpa();

        entityJpa.setId(domain.getId().value());
        entityJpa.setIssueDate(domain.getIssueDate());
        entityJpa.setIssuer(domain.getIssuer());
        entityJpa.setRecipient(domain.getRecipient());
        entityJpa.setPriceCurrency(domain.getTotalValue().currency().getCurrencyCode());
        entityJpa.setPriceAmount(domain.getTotalValue().amount());

        entityJpa.setProducts(domain.productsList().stream()
                .map(product -> ProductCoreMapper.toJpaEntityBiDirectional(entityJpa, product))
                .collect(Collectors.toList()));


        return entityJpa;
    }

    public static InvoiceEntryDomain toDomainEntity(NfInvoiceEntryEntityJpa entityJpa) {
        if (entityJpa == null) {
            return null;
        }

        InvoiceEntryDomain.InvoiceEntryDomainBuilder builder = InvoiceEntryDomain.builder();

        builder.id(new NfEntryIdDomain(entityJpa.getId()));
        builder.issueDate(entityJpa.getIssueDate());
        builder.issuer(entityJpa.getIssuer());
        builder.recipient(entityJpa.getRecipient());
        builder.totalValue(new MoneyDomain(Currency.getInstance(entityJpa.getPriceCurrency()), entityJpa.getPriceAmount()));

        InvoiceEntryDomain tempDomain = new InvoiceEntryDomain(new NfEntryIdDomain(entityJpa.getId()));
        entityJpa.getProducts().forEach(productEntity -> {
            ProductCoreDomain productDomain = ProductCoreMapper.toDomainEntity(productEntity);
            tempDomain.putProductIgnoringNotEnoughItemsInStock(productDomain, productDomain.getQuantity());
        });
        builder.products(tempDomain.getProducts());

        return builder.build();
    }

    public static Optional<InvoiceEntryDomain> toDomainEntityOptional(NfInvoiceEntryEntityJpa entityJpa) {
        return Optional.ofNullable(toDomainEntity(entityJpa));
    }


    static List<InvoiceEntryDomain> toModelEntities(List<NfInvoiceEntryEntityJpa> jpaEntities) {
        return jpaEntities.stream().map(NfInvoiceEntryMapper::toDomainEntity).toList();
    }


}
