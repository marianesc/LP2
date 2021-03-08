package saga.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ContaTest {

    private Conta conta;

    @BeforeEach
    void init() {
        this.conta = new Conta("Seu Olavo");
        Compra compra1 = new Compra("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00, "23/03/2019", "Wilson Andre", "Seu Olavo");
        Compra compra2 = new Compra("X-burguer", "Hamburguer de carne com queijo e calabresa", 4.50, "24/03/2019", "Victor Emanuel", "Seu Olavo");
        this.conta.adicionaCompra(compra1);
        this.conta.adicionaCompra(compra2);
    }

    @Test
    void testAdicionaCompra() {
        Compra compra3 = new Compra("Rubacao", "Feijao com arroz e queijo coalho", 17.00, "25/03/2019", "Victor Emanuel", "Seu Olavo");
        this.conta.adicionaCompra(compra3);
        assertTrue(this.conta.consultaCompras().contains(compra3));
    }

    @Test
    void testGetDebito() {
        assertEquals(9.50, this.conta.getDebito());
    }

    @Test
    void testToString() {
        assertEquals("Seu Olavo | X-frango - 23-03-2019 | X-burguer - 24-03-2019", this.conta.toString());
    }

    @Test
    void testHashCodeIgual() {
        Conta conta2 = new Conta("Seu Olavo");
        assertEquals(conta2.hashCode(), this.conta.hashCode());
    }

    @Test
    void testHashCodeDiferente() {
        Conta conta2 = new Conta("Marcos");
        assertNotEquals(conta2.hashCode(), this.conta.hashCode());
    }

    @Test
    void testEqualsTrue() {
        Conta conta2 = new Conta("Seu Olavo");
        assertEquals(conta2, this.conta);
    }

    @Test
    void testEqualsFalse() {
        Conta conta2 = new Conta("Marcos");
        assertNotEquals(conta2, this.conta);
    }
}