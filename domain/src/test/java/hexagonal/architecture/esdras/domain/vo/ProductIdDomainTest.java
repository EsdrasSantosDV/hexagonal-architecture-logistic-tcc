package hexagonal.architecture.esdras.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductIdDomainTest {
    @Test
    @DisplayName("Verificar se exceção é lançada quando o valor é null")
    void testProductIdDomainWithNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> new ProductIdDomain(null),
                "Esperado lançamento de exceção quando valor é null"
        );
    }

    @Test
    @DisplayName("Verificar se exceção é lançada quando o valor é vazio")
    void testProductIdDomainWithEmptyValue() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ProductIdDomain(""),
                "Esperado lançamento de exceção quando valor é vazio"
        );
    }

    @Test
    @DisplayName("Verificar geração de ProductIdDomain aleatório")
    void testRandomProductIdDomainGeneration() {
        ProductIdDomain randomId = ProductIdDomain.randomProductIdDomain();

        assertNotNull(randomId, "Random ID não deve ser null");
        assertNotNull(randomId.value(), "O valor do random ID não deve ser null");
        assertFalse(randomId.value().isBlank(), "O valor do random ID não deve ser branco ou vazio");


        assertEquals(30, randomId.value().length(), "O comprimento do ID deve ser 8");

        assertTrue(randomId.value().chars().allMatch(c -> "23456789ABCDEFGHJKLMNPQRSTUVWXYZ".indexOf(c) != -1),
                "Todos os caracteres do ID devem estar dentro dos caracteres permitidos"
        );
    }
}