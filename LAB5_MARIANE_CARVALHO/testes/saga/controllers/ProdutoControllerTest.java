package saga.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saga.entities.Cliente;
import saga.entities.Fornecedor;
import saga.entities.Produto;
import saga.repositories.ClienteRepositorio;
import saga.repositories.FornecedorRepositorio;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoControllerTest {

    private ProdutoController controller;
    private Produto produto;

    @BeforeEach
    void init() {
        FornecedorRepositorio fornecedorRepositorio = new FornecedorRepositorio();
        Fornecedor fornecedor1 = new Fornecedor("Helhao", "quiosque@gmail.com", "83 98736-5050");
        fornecedorRepositorio.adicionaFornecedor("Helhao", fornecedor1);

        this.produto = new Produto("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00);
        fornecedor1.adicionaProduto(produto);

        this.controller = new ProdutoController(fornecedorRepositorio);
    }

    @Test
    void testAdicionaProdutoFornecedorNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaProduto(null, "Rubacao", "Feijao com arroz e queijo coalho", 17.00);
        });
    }

    @Test
    void testAdicionaProdutoFornecedorVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaProduto("", "Rubacao", "Feijao com arroz e queijo coalho", 17.00);
        });
    }

    @Test
    void testAdicionaProdutoNomeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaProduto("Dona Alba", null, "Feijao com arroz e queijo coalho", 17.00);
        });
    }

    @Test
    void testAdicionaProdutoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaProduto("Dona Alba", "", "Feijao com arroz e queijo coalho", 17.00);
        });
    }

    @Test
    void testAdicionaProdutoDescricaoNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaProduto("Dona Alba", "Rubacao", null, 17.00);
        });
    }

    @Test
    void testAdicionaProdutoDescricaoVazia() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaProduto("Dona Alba", "Rubacao", "", 17.00);
        });
    }

    @Test
    void testAdicionaProdutoPresoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaProduto("Dona Alba", "Rubacao", "Feijao com arroz e queijo coalho", -17.00);
        });
    }

    @Test
    void testAdicionaProdutofornecedorInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaProduto("Joao", "X-frango", "Hamburguer de frango com queijo e calabresa", 5.00);
        });
    }

    @Test
    void testAdicionaProdutoExistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaProduto("Helhao", "X-frango", "Hamburguer de frango com queijo e calabresa", 5.00);
        });
    }

    @Test
    void testAdicionaProdutoInexistente() {
        Produto produto1= new Produto("Rubacao", "Feijao com arroz e queijo coalho", 17.00);
        this.controller.adicionaProduto("Helhao", "Rubacao", "Feijao com arroz e queijo coalho", 17.00);

        String representacao = this.controller.exibeProduto("Rubacao", "Feijao com arroz e queijo coalho", "Helhao");
        assertEquals(representacao, produto1.toString());
    }

    @Test
    void testExibeProdutoFornecedorNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeProduto("X-frango", "Hamburguer de frango com queijo e calabresa", null);
        });
    }

    @Test
    void testExibeProdutoFornecedorVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeProduto("X-frango", "Hamburguer de frango com queijo e calabresa", "");
        });
    }

    @Test
    void testExibeProdutoNomeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeProduto(null, "Hamburguer de frango com queijo e calabresa", "Helhao");
        });
    }

    @Test
    void testExibeProdutoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeProduto("", "Hamburguer de frango com queijo e calabresa", "Helhao");
        });
    }

    @Test
    void testExibeProdutoDescricaoNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeProduto("X-frango", null, "Helhao");
        });
    }

    @Test
    void testExibeProdutoDescricaoVazia() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeProduto("X-frango", "", "Helhao");
        });
    }

    @Test
    void testExibeProdutoFornecedorInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeProduto("X-frango", "Hamburguer de frango com queijo e calabresa", "Joao");
        });
    }

    @Test
    void testExibeProdutoInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeProduto("Rubacao", "Feijao com arroz e queijo coalho", "Helhao");
        });
    }

    @Test
    void testExibeProdutoExistente() {
        String representacao= this.controller.exibeProduto("X-frango", "Hamburguer de frango com queijo e calabresa", "Helhao");

        assertEquals(representacao, this.produto.toString());
    }

    @Test
    void testExibeProdutosFornecedorFornNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeProdutosFornecedor(null);
        });
    }

    @Test
    void testExibeProdutosFornecedorFornVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeProdutosFornecedor("");
        });
    }

    @Test
    void testExibeProdutosFornecedorFornInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeProdutosFornecedor("Joao");
        });
    }

    @Test
    void testExibeProdutosFornecedorExistente() {
        String representacao = "Helhao - " + this.produto.toString();

        assertEquals(representacao, this.controller.exibeProdutosFornecedor("Helhao"));
    }

    @Test
    void testExibeProdutos() {
        String listagemEsperada = "Helhao - " + this.produto.toString();

        assertEquals(listagemEsperada, this.controller.exibeProdutos());
    }

    @Test
    void testEditaProdutoNomeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaProduto(null, "Hamburguer de frango com queijo e calabresa", "Helhao", 5.00);
        });
    }

    @Test
    void testEditaProdutoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaProduto("", "Hamburguer de frango com queijo e calabresa", "Helhao", 5.00);
        });
    }

    @Test
    void testEditaProdutoDescricaoNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaProduto("X-frango", null, "Helhao", 5.00);
        });
    }

    @Test
    void testEditaProdutoDescricaoVazia() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaProduto("X-frango", "", "Helhao", 5.00);
        });
    }

    @Test
    void testEditaProdutoFornecedorNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaProduto("X-frango", "Hamburguer de frango com queijo e calabresa", null, 5.00);
        });
    }

    @Test
    void testEditaProdutoFornecedorVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaProduto("X-frango", "Hamburguer de frango com queijo e calabresa", "", 5.00);
        });
    }

    @Test
    void testEditaProdutoPrecoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaProduto("X-frango", "Hamburguer de frango com queijo e calabresa", "Helhao", -5.00);
        });
    }

    @Test
    void testEditaProdutoFornecedorInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaProduto("X-frango", "Hamburguer de frango com queijo e calabresa", "Joao", 5.00);
        });
    }

    @Test
    void testEditaProdutoProdInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaProduto("Rubacao", "Feijao com arroz e queijo coalho", "Helhao", 17.00);
        });
    }

    @Test
    void testEditaProdutoExistente() {
        this.controller.editaProduto("X-frango", "Hamburguer de frango com queijo e calabresa","Helhao", 6.00);

        String representacaoEsperada = "X-frango - Hamburguer de frango com queijo e calabresa - R$6,00";
        String representacaoAtual = this.controller.exibeProduto("X-frango", "Hamburguer de frango com queijo e calabresa", "Helhao");
        assertEquals(representacaoEsperada, representacaoAtual);
    }

    @Test
    void testRemoveProdutoNomeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeProduto(null, "Hamburguer de frango com queijo e calabresa", "Helhao");
        });
    }

    @Test
    void testRemoveProdutoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeProduto("", "Hamburguer de frango com queijo e calabresa", "Helhao");
        });
    }

    @Test
    void testRemoveProdutoDescricaoNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeProduto("X-frango", null, "Helhao");
        });
    }

    @Test
    void testRemoveProdutoDescricaoVazia() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeProduto("X-frango", "", "Helhao");
        });
    }

    @Test
    void testRemoveProdutoFornecedorNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeProduto("X-frango", "Hamburguer de frango com queijo e calabresa", null);
        });
    }

    @Test
    void testRemoveProdutoFornecedorVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeProduto("X-frango", "Hamburguer de frango com queijo e calabresa", "");
        });
    }

    @Test
    void testRemoveProdutoFornecedorInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeProduto("X-frango", "Hamburguer de frango com queijo e calabresa", "Joao");
        });
    }

    @Test
    void testRemoveProdutoProdInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeProduto("Rubacao", "Feijao com arroz e queijo coalho", "Helhao");
        });
    }

    @Test
    void testRemoveProdutoExistente() {
        this.controller.removeProduto("X-frango", "Hamburguer de frango com queijo e calabresa", "Helhao");

        String representacaoEsperada = "";
        String representacaoAtual = this.controller.exibeProdutosFornecedor("Helhao");
        assertEquals(representacaoEsperada, representacaoAtual);
    }
}