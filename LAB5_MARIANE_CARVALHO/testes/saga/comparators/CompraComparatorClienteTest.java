package saga.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saga.entities.Compra;

import static org.junit.jupiter.api.Assertions.*;

class CompraComparatorClienteTest {

    private Compra compra1;
    private CompraComparatorCliente comparador;

    @BeforeEach
    void init() {
        compra1 = new Compra("X-burguer", "Hamburguer de carne com queijo e calabresa", 4.50, "24/03/2019", "Victor Emanuel", "Marcos");
        this.comparador = new CompraComparatorCliente();
    }

    @Test
    void testCompareMenor() {
        Compra compra2 = new Compra("X-burguer", "Hamburguer de carne com queijo e calabresa", 4.50, "24/03/2019", "Wilson Andre", "Marcos");
        int comparacao = this.comparador.compare(compra1, compra2);
        assertTrue(comparacao < 0);
    }

    @Test
    void testCompareMaior() {
        Compra compra2 = new Compra("X-burguer", "Hamburguer de carne com queijo e calabresa", 4.50, "24/03/2019", "Ana Amari", "Marcos");
        int comparacao = this.comparador.compare(compra1, compra2);
        assertTrue(comparacao > 0);
    }

    @Test
    void testCompareIgual() {
        Compra compra2 = new Compra("X-burguer", "Hamburguer de carne com queijo e calabresa", 4.50, "24/03/2019", "Victor Emanuel", "Marcos");
        int comparacao = this.comparador.compare(compra1, compra2);
        assertEquals(0, comparacao);
    }

    @Test
    void testCompareIgualMenor() {
        Compra compra2 = new Compra("X-burguer", "Hamburguer de carne com queijo e calabresa", 4.50, "24/03/2019", "Victor Emanuel", "Seu Olavo");
        int comparacao = this.comparador.compare(compra1, compra2);
        assertTrue(comparacao < 0);
    }

    @Test
    void testCompareIgualMaior() {
        Compra compra2 = new Compra("X-burguer", "Hamburguer de carne com queijo e calabresa", 4.50, "24/03/2019", "Victor Emanuel", "Dona Alba");
        int comparacao = this.comparador.compare(compra1, compra2);
        assertTrue(comparacao > 0);
    }

}