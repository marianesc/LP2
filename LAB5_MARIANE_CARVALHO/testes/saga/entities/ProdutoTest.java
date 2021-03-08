package saga.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    private Produto produto1;
    private Produto produto2;
    private Produto produto3;

    @BeforeEach
    void init() {
        this.produto1 = new Produto("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00);
        this.produto2 = new Produto("X-burguer", "Hamburguer de carne com queijo e calabresa", 4.50);
        this.produto3 = new Produto("X-frango", "Hamburguer de frango com queijo e calabresa", 4.00);
    }

    @Test
    void testHashCodeIgual() {
        assertEquals(this.produto1.hashCode(), this.produto3.hashCode());
    }

    @Test
    void testHashCodeDiferente() {
        assertNotEquals(this.produto1.hashCode(), this.produto2.hashCode());
    }

    @Test
    void testEqualsTrue() {
        assertEquals(this.produto1, this.produto3);
    }

    @Test
    void testEqualsFalse() {
        assertNotEquals(this.produto1, this.produto2);
    }

    @Test
    void testToString() {
        String representacao = "X-frango - Hamburguer de frango com queijo e calabresa - R$5,00";
        assertEquals(representacao, this.produto1.toString());
    }

    @Test
    void testCompareToMaior() {
        int comparacao = this.produto1.compareTo(this.produto2);
        assertTrue(comparacao > 0);
    }

    @Test
    void testCompareToMenor() {
        int comparacao = this.produto2.compareTo(this.produto1);
        assertTrue(comparacao < 0);
    }

    @Test
    void testCompareToIgual() {
        int comparacao = this.produto3.compareTo(this.produto1);
        assertEquals(0, comparacao);
    }
}