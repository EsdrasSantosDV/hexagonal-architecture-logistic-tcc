package hexagonal.architecture.esdras.adapter.in.rest.webmodel;

import hexagonal.architecture.esdras.domain.vo.MoneyDomain;

import java.math.BigDecimal;

public record MoneyWebModel(
        String currency,
        BigDecimal amount
) {
    public static MoneyWebModel fromDomainModel(MoneyDomain moneyDomain) {
        return new MoneyWebModel(
                moneyDomain.currency().getCurrencyCode(),
                moneyDomain.amount()
        );
    }
}