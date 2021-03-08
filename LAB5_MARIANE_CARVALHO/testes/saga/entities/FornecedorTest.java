package saga.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class FornecedorTest {

    private Fornecedor fornecedor1;
    private Fornecedor fornecedor2;
    private Fornecedor fornecedor3;
    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    void init() {
        this.fornecedor1 = new Fornecedor("Marcos", "marcos@gmail.com", "83 99151-3570");
        this.fornecedor2 = new Fornecedor("Helhao", "quiosque@gmail.com", "83 98736-5050");
        this.fornecedor3 = new Fornecedor("Marcos", "marcos@gmail.com", "83 99945-1294");
        this.produto1 = new Produto("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00);
        this.produto2 = new Produto("X-burguer", "Hamburguer de carne com queijo e calabresa", 4.50);
        this.fornecedor1.adicionaProduto(produto1);
        this.fornecedor1.adicionaProduto(produto2);
    }

    @Test
    void testHashCodeIgual() {
        assertEquals(this.fornecedor1.hashCode(), this.fornecedor3.hashCode());
    }

    @Test
    void testHashCodeDiferente() {
        assertNotEquals(this.fornecedor1.hashCode(), this.fornecedor2.hashCode());
    }

    @Test
    void testEqualsTrue() {
        assertEquals(this.fornecedor1, this.fornecedor3);
    }

    @Test
    void testEqualsFalse() {
        assertNotEquals(this.fornecedor1, this.fornecedor2);
    }

    @Test
    void testToString() {
        String representacao = "Marcos - marcos@gmail.com - 83 99151-3570";
        assertEquals(representacao, this.fornecedor1.toString());
    }

    @Test
    void testContemAtributoTrue() {
        assertTrue(Fornecedor.contemAtributo("nome"));
    }

    @Test
    void testContemAtributoFalse() {
        assertFalse(Fornecedor.contemAtributo("descricao"));
    }

    @Test
    void testAdicionaProduto() {
        Produto produto3 = new Produto("Rubacao", "Feijao com arroz e queijo coalho", 17.00);
        this.fornecedor1.adicionaProduto(produto3);
        assertEquals(produto3, this.fornecedor1.consultaProduto("Rubacao", "Feijao com arroz e queijo coalho"));
    }

    @Test
    void testConsultaProduto() {
        assertEquals(this.produto1, this.fornecedor1.consultaProduto("X-frango", "Hamburguer de frango com queijo e calabresa"));
    }

    @Test
    void testConsultaProdutoInexistente() {
        assertNull(this.fornecedor1.consultaProduto("X-bacon", "Hamburguer de bacon com queijo e calabresa"));
    }

    @Test
    void testConsultaProdutos() {
        Set<Produto> produtos = new TreeSet<>();
        produtos.add(this.produto1);
        produtos.add(this.produto2);
        List<Produto> produtos1  = new ArrayList<>(produtos);
        assertEquals(produtos1, this.fornecedor1.consultaProdutos());
    }

    @Test
    void testRemoveProduto() {
        this.fornecedor1.removeProduto(this.produto2);
        assertEquals(1, this.fornecedor1.consultaProdutos().size());
    }

    @Test
    void testCompareToMaior() {
        int comparacao = this.fornecedor1.compareTo(this.fornecedor2);
        assertTrue(comparacao > 0);
    }

    @Test
    void testCompareToMenor() {
        int comparacao = this.fornecedor2.compareTo(this.fornecedor1);
        assertTrue(comparacao < 0);
    }

    @Test
    void testCompareTo() {
        int comparacao = this.fornecedor1.compareTo(this.fornecedor3);
        assertEquals(0, comparacao);
    }
}