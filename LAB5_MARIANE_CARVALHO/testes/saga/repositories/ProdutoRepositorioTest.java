package saga.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saga.entities.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoRepositorioTest {

    private ProdutoRepositorio produtoRepositorio;
    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    void init() {
        this.produtoRepositorio = new ProdutoRepositorio();
        this.produto1 = new Produto("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00);
        this.produto2 = new Produto("X-burguer", "Hamburguer de carne com queijo e calabresa", 4.50);
        this.produtoRepositorio.adicionaProduto(produto1);
        this.produtoRepositorio.adicionaProduto(produto2);
    }

    @Test
    void testAdicionaProduto() {
        Produto produto = new Produto("Rubacao", "Feijao com arroz e queijo coalho", 17.00);
        this.produtoRepositorio.adicionaProduto(produto);
        assertEquals(produto, this.produtoRepositorio.consultaProduto("Rubacao", "Feijao com arroz e queijo coalho"));
    }

    @Test
    void testConsultaProduto() {
        assertEquals(this.produto1, this.produtoRepositorio.consultaProduto("X-frango", "Hamburguer de frango com queijo e calabresa"));
    }

    @Test
    void testConsultaProdutoInexistente() {
        assertNull(this.produtoRepositorio.consultaProduto("X-bacon", "Hamburguer de bacon com queijo e calabresa"));
    }

    @Test
    void testConsultaProdutos() {
        Set<Produto> produtos = new TreeSet<>();
        produtos.add(this.produto1);
        produtos.add(this.produto2);
        List<Produto> produtos1 = new ArrayList<>(produtos);
        assertEquals(produtos1, this.produtoRepositorio.consultaProdutos());
    }

    @Test
    void testRemoveProduto() {
        this.produtoRepositorio.removeProduto(this.produto2);
        assertEquals(1, this.produtoRepositorio.consultaProdutos().size());
    }
}