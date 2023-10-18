package hexagonal.architecture.esdras.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class MoneyDomainTest {
    private static final Currency EUR = Currency.getInstance("EUR");
    private static final Currency DOLAR = Currency.getInstance("USD");

    private static final Currency REAL = Currency.getInstance("BRL");

    @DisplayName("Teste da criação do Objeto de Valor e do metodo of, que cria uma instancia de um money")
    @Test
    public void testMoneyCreation() {

        MoneyDomain money = new MoneyDomain(EUR, BigDecimal.valueOf(100));
        assertNotNull(money);
        assertEquals(EUR, money.currency());
        assertEquals(BigDecimal.valueOf(100), money.amount());

        MoneyDomain moneyDolar = new MoneyDomain(EUR, BigDecimal.valueOf(100));
        assertNotNull(moneyDolar);
        assertEquals(EUR, moneyDolar.currency());
        assertEquals(BigDecimal.valueOf(100), moneyDolar.amount());

        MoneyDomain moneyReal = new MoneyDomain(EUR, BigDecimal.valueOf(100));
        assertNotNull(moneyReal);
        assertEquals(EUR, moneyReal.currency());
        assertEquals(BigDecimal.valueOf(100), moneyReal.amount());

        MoneyDomain moneyOf = MoneyDomain.of(REAL, 100, 30);
        assertNotNull(moneyOf);
        BigDecimal expectedAmount = new BigDecimal("100.30");
        assertEquals(expectedAmount, moneyOf.amount());
        assertEquals(REAL, moneyOf.currency());

    }

    @DisplayName("Teste de adição com moedas diferentes")
    @Test
    public void testMoneyAddition() {
        MoneyDomain money1 = new MoneyDomain(EUR, BigDecimal.valueOf(50));
        MoneyDomain money2 = new MoneyDomain(EUR, BigDecimal.valueOf(75));
        MoneyDomain result = money1.add(money2);
        assertEquals(BigDecimal.valueOf(125), result.amount());

        MoneyDomain money3 = new MoneyDomain(DOLAR, BigDecimal.valueOf(50));
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> money1.add(money3), "Esperado que add() lançasse uma exceção, mas não lançou");

        assertTrue(thrown.getMessage().contains("não corresponde à moeda deste valor monetário"));

    }

    @DisplayName("Teste de multiplicação de um money")
    @Test
    public void testMoneyMultiplication() {
        MoneyDomain money = new MoneyDomain(EUR, BigDecimal.valueOf(20));
        MoneyDomain result = money.multiply(3);
        assertEquals(BigDecimal.valueOf(60), result.amount());
    }

    @DisplayName("Teste de escala de um money")
    @Test
    public void testMoneyScaleCheck() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MoneyDomain(EUR, BigDecimal.valueOf(123.456));
        });
    }
}