package saga.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompraTest {

    private Compra compra;

    @BeforeEach
    void init() {
        this.compra = new Compra("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00, "23/03/2019", "Wilson Andre", "Seu Olavo");
    }

    @Test
    void testToString() {
        assertEquals("X-frango - 23-03-2019", this.compra.toString());
    }

    @Test
    void testGetRepresentacaoCriterioCliente() {
        assertEquals("Wilson Andre, Seu Olavo, Hamburguer de frango com queijo e calabresa, 23/03/2019", this.compra.getRepresentacao("cliente"));
    }

    @Test
    void testGetRepresentacaoCriterioFornecedor() {
        assertEquals("Seu Olavo, Wilson Andre, Hamburguer de frango com queijo e calabresa, 23/03/2019", this.compra.getRepresentacao("fornecedor"));
    }

    @Test
    void testGetRepresentacaoCriterioData() {
        assertEquals("23/03/2019, Wilson Andre, Seu Olavo, Hamburguer de frango com queijo e calabresa", this.compra.getRepresentacao("data"));
    }

    @Test
    void testGetRepresentacaoCriterioNaoDefinido() {
        assertEquals("X-frango - 23-03-2019", this.compra.getRepresentacao("produto"));
    }

    @Test
    void testHashCodeIgual() {
        Compra compra2 = new Compra("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00, "23/03/2019", "Wilson Andre", "Seu Olavo");
        assertEquals(this.compra.hashCode(), compra2.hashCode());
    }

    @Test
    void testHashCodeDiferente() {
        Compra compra2 = new Compra("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00, "23/03/2019", "Wilson Andre", "Marcos");
        assertNotEquals(this.compra.hashCode(), compra2.hashCode());
    }

    @Test
    void testEqualsTrue() {
        Compra compra2 = new Compra("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00, "23/03/2019", "Wilson Andre", "Seu Olavo");
        assertEquals(this.compra, compra2);
    }

    @Test
    void testEqualsFalse() {
        Compra compra2 = new Compra("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00, "23/03/2019", "Wilson Andre", "Marcos");
        assertNotEquals(this.compra, compra2);
    }
}