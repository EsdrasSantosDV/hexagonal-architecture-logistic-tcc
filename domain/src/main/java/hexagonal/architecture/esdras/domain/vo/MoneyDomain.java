package hexagonal.architecture.esdras.domain.vo;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;


/**
 * MoneyDomain
 *responsible for abstracting the money currency product
 * @author esdras
 *
 */
public record MoneyDomain(Currency currency, BigDecimal amount){
    public MoneyDomain {
        Objects.requireNonNull(currency, "'currency' não pode ser nulo");
        Objects.requireNonNull(amount, "'amount' não pode ser nulo");
        if (amount.scale() > currency.getDefaultFractionDigits()) {
            throw new IllegalArgumentException(
                    ("A escala do valor %s é maior "
                            + "que o número de dígitos fracionários usados com a moeda %s")
                            .formatted(amount, currency));
        }
    }

    public static MoneyDomain of(Currency currency, int mayor, int minor) {
        int scale = currency.getDefaultFractionDigits();
        return new MoneyDomain(
                currency, BigDecimal.valueOf(mayor).add(BigDecimal.valueOf(minor, scale)));
    }

    public MoneyDomain add(MoneyDomain money) {
        if (!this.currency.equals(money.currency())) {
            throw new IllegalArgumentException(
                    "Moeda %s do aduendo não corresponde à moeda deste valor monetário %s"
                            .formatted(money.currency(), this.currency));
        }

        return new MoneyDomain(currency, amount.add(money.amount()));
    }

    public MoneyDomain multiply(int multiplyValue) {
        return new MoneyDomain(currency, amount.multiply(BigDecimal.valueOf(multiplyValue)));
    }
}
