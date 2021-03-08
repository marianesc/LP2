package saga.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saga.entities.Cliente;
import saga.entities.Compra;
import saga.entities.Fornecedor;
import saga.entities.Produto;
import saga.repositories.ClienteRepositorio;
import saga.repositories.FornecedorRepositorio;

import static org.junit.jupiter.api.Assertions.*;

class CompraControllerTest {

    private CompraController controller;
    private Compra compra1;
    private Compra compra2;
    private Compra compra3;

    @BeforeEach
    void init() {
        ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
        Cliente cliente1 = new Cliente("00023827490", "Victor Emanuel", "vitao@ccc.ufcg.edu.br", "Labarc");
        Cliente cliente2 = new Cliente("64269141198", "Ana Amari", "ana_amari@ccc.ufcg.edu.br", "SPG");
        clienteRepositorio.adicionaCliente("00023827490", cliente1);
        clienteRepositorio.adicionaCliente("64269141198", cliente2);

        FornecedorRepositorio fornecedorRepositorio = new FornecedorRepositorio();
        Fornecedor fornecedor1 = new Fornecedor("Helhao", "quiosque@gmail.com", "83 98736-5050");
        Fornecedor fornecedor2 = new Fornecedor("Marcos", "marcos@gmail.com", "83 99945-1294");
        fornecedorRepositorio.adicionaFornecedor("Helhao", fornecedor1);
        fornecedorRepositorio.adicionaFornecedor("Marcos", fornecedor2);

        Produto produto1 = new Produto("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00);
        Produto produto2 = new Produto("X-burguer", "Hamburguer de carne com queijo e calabresa", 4.50);
        fornecedor1.adicionaProduto(produto1);
        fornecedor1.adicionaProduto(produto2);
        fornecedor2.adicionaProduto(produto1);
        fornecedor2.adicionaProduto(produto2);

        this.compra1 = new Compra("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00, "23/03/2019", "Victor Emanuel", "Marcos");
        this.compra2 = new Compra("X-burguer", "Hamburguer de carne com queijo e calabresa", 4.50, "24/03/2019", "Victor Emanuel", "Helhao");
        this.compra3 = new Compra("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00, "23/03/2019", "Ana Amari", "Marcos");
        cliente1.adicionaNaConta("Marcos", this.compra1);
        cliente1.adicionaNaConta("Helhao", this.compra2);
        cliente2.adicionaNaConta("Marcos", this.compra3);

        this.controller = new CompraController(clienteRepositorio, fornecedorRepositorio);
    }

    @Test
    void testAdicionaCompraCpfNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra(null, "Helhao", "23/03/2019", "X-burguer", "Hamburguer de carne com queijo e calabresa");
        });
    }

    @Test
    void testAdicionaCompraCpfVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra("", "Helhao", "23/03/2019", "X-burguer", "Hamburguer de carne com queijo e calabresa");
        });
    }

    @Test
    void testAdicionaCompraCpfInvalidoTamanho() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra("0002382749011", "Helhao", "23/03/2019", "X-burguer", "Hamburguer de carne com queijo e calabresa");
        });
    }

    @Test
    void testAdicionaCompraCpfInvalidoLetra() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra("00a23827490", "Helhao", "23/03/2019", "X-burguer", "Hamburguer de carne com queijo e calabresa");
        });
    }

    @Test
    void testAdicionaCompraFornecedorNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra("00023827490", null, "23/03/2019", "X-burguer", "Hamburguer de carne com queijo e calabresa");
        });
    }

    @Test
    void testAdicionaCompraFornecedorVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra("00023827490", "", "23/03/2019", "X-burguer", "Hamburguer de carne com queijo e calabresa");
        });
    }

    @Test
    void testAdicionaCompraDataNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra("00023827490", "Helhao", null, "X-burguer", "Hamburguer de carne com queijo e calabresa");
        });
    }

    @Test
    void testAdicionaCompraDataVazia() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra("00023827490", "Helhao", "", "X-burguer", "Hamburguer de carne com queijo e calabresa");
        });
    }

    @Test
    void testAdicionaCompraDataInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra("00023827490", "Helhao", "2019/03/23", "X-burguer", "Hamburguer de carne com queijo e calabresa");
        });
    }

    @Test
    void testAdicionaCompraProdutoNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra("00023827490", "Helhao", "23/03/2019", null, "Hamburguer de carne com queijo e calabresa");
        });
    }

    @Test
    void testAdicionaCompraProdutoVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra("00023827490", "Helhao", "23/03/2019", "", "Hamburguer de carne com queijo e calabresa");
        });
    }

    @Test
    void testAdicionaCompraDescricaoNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra("00023827490", "Helhao", "23/03/2019", "X-burguer", null);
        });
    }

    @Test
    void testAdicionaCompraDescricaoVazia() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra("00023827490", "Helhao", "23/03/2019", "X-burguer", "");
        });
    }

    @Test
    void testAdicionaCompraClienteInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra("00000000000", "Helhao", "23/03/2019", "X-burguer", "Hamburguer de carne com queijo e calabresa");
        });
    }

    @Test
    void testAdicionaCompraFornecedorInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra("00023827490", "Joao", "23/03/2019", "X-burguer", "Hamburguer de carne com queijo e calabresa");
        });
    }

    @Test
    void testAdicionaCompraProdutoInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCompra("00023827490", "Helhao", "23/03/2019", "Mel", "Melzinho");
        });
    }

    @Test
    void testAdicionaCompra() {
        Compra compra = new Compra("X-burguer", "Hamburguer de carne com queijo e calabresa", 4.50, "25/03/2019", "Ana Amari", "Helhao");
        this.controller.adicionaCompra("64269141198", "Helhao", "25/03/2019", "X-burguer", "Hamburguer de carne com queijo e calabresa");

        this.controller.setCriterioOrdenacao("cliente");

        String representacao = compra.getRepresentacao("cliente") + " | " +
                this.compra3.getRepresentacao("cliente") +  " | " +
                this.compra2.getRepresentacao("cliente") +  " | " +
                this.compra1.getRepresentacao("cliente");

        assertEquals(representacao, this.controller.listarCompras());
    }

    @Test
    void testSetCriterioOrdenacaoNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.setCriterioOrdenacao(null);
        });
    }

    @Test
    void testSetCriterioOrdenacaoVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.setCriterioOrdenacao("");
        });
    }

    @Test
    void testSetCriterioOrdenacaoInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.setCriterioOrdenacao("pedido");
        });
    }

    @Test
    void testListarComprasCriterioCliente() {
        this.controller.setCriterioOrdenacao("cliente");

        String representacao = this.compra3.getRepresentacao("cliente") +  " | " +
                this.compra2.getRepresentacao("cliente") +  " | " +
                this.compra1.getRepresentacao("cliente");

        assertEquals(representacao, this.controller.listarCompras());
    }

    @Test
    void testListarComprasCriterioFornecedor() {
        this.controller.setCriterioOrdenacao("fornecedor");

        String representacao = this.compra2.getRepresentacao("fornecedor") +  " | " +
                this.compra3.getRepresentacao("fornecedor") +  " | " +
                this.compra1.getRepresentacao("fornecedor");

        assertEquals(representacao, this.controller.listarCompras());
    }

    @Test
    void testListarComprasCriterioData() {
        this.controller.setCriterioOrdenacao("data");

        String representacao = this.compra3.getRepresentacao("data") +  " | " +
                this.compra1.getRepresentacao("data") +  " | " +
                this.compra2.getRepresentacao("data");

        assertEquals(representacao, this.controller.listarCompras());
    }

    @Test
    void testListarComprasCriterioNaoDefinido() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.listarCompras();
        });
    }
}