package saga.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saga.entities.*;
import saga.repositories.ClienteRepositorio;
import saga.repositories.FornecedorRepositorio;

import static org.junit.jupiter.api.Assertions.*;

class ContaControllerTest {

    private ContaController controller;
    private Conta conta;

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
        fornecedor1.adicionaProduto(produto1);

        Compra compra1 = new Compra("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00, "25/08/2019", "Victor Emanuel", "Helhao");
        cliente1.adicionaNaConta("Helhao", compra1);

        this.conta= new Conta("Helhao");
        this.conta.adicionaCompra(compra1);

        this.controller = new ContaController(clienteRepositorio, fornecedorRepositorio);
    }

    @Test
    void testGetDebitoCpfNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.getDebito(null, "Helhao");
        });
    }

    @Test
    void testGetDebitoCpfVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.getDebito("", "Helhao");
        });
    }

    @Test
    void testGetDebitoCpfInvalidoTamanho() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.getDebito("0002382749011", "Helhao");
        });
    }

    @Test
    void testGetDebitoCpfInvalidoLetra() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.getDebito("00a23827490", "Helhao");
        });
    }

    @Test
    void testGetDebitoFornecedorNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.getDebito("00023827490", null);
        });
    }

    @Test
    void testGetDebitoFornecedorVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.getDebito("00023827490", "");
        });
    }

    @Test
    void testGetDebitoFornecedorInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.getDebito("00023827490", "Joao");
        });
    }

    @Test
    void testGetDebitoClienteInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.getDebito("00000000000", "Helhao");
        });
    }

    @Test
    void testGetDebitoInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.getDebito("00023827490", "Marcos");
        });
    }

    @Test
    void testGetDebitoExistente() {
        assertEquals("5.00", this.controller.getDebito("00023827490" , "Helhao"));
    }

    @Test
    void testExibeContasCpfNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeContas(null, "Helhao");
        });
    }

    @Test
    void testExibeContasCpfVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeContas("", "Helhao");
        });
    }

    @Test
    void testExibeContasCpfInvalidoTamanho() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeContas("0002382749011", "Helhao");
        });
    }

    @Test
    void testExibeContasCpfInvalidoLetra() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeContas("00a23827490", "Helhao");
        });
    }

    @Test
    void testExibeContasFornecedorNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeContas("00023827490", null);
        });
    }

    @Test
    void testExibeContasFornecedorVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeContas("00023827490", "");
        });
    }

    @Test
    void testExibeContasClienteInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeContas("00000000000", "Helhao");
        });
    }

    @Test
    void testExibeContasFornecedorInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeContas("00023827490", "Joao");
        });
    }

    @Test
    void testExibeContasInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeContas("00023827490", "Marcos");
        });
    }

    @Test
    void testExibeContasExistente() {
        String representacao = "Cliente: Victor Emanuel | " + this.conta.toString();

        assertEquals(representacao, this.controller.exibeContas("00023827490", "Helhao"));
    }

    @Test
    void testExibeContasClientesCpfNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeContasClientes(null);
        });
    }

    @Test
    void testExibeContasClientesCpfVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeContasClientes("");
        });
    }

    @Test
    void testExibeContasClientesCpfInvalidoTamanho() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeContasClientes("0002382749011");
        });
    }

    @Test
    void testExibeContasClientesCpfInvalidoLetra() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeContasClientes("00a23827490");
        });
    }

    @Test
    void testExibeContasClientesClienteInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeContasClientes("00000000000");
        });
    }

    @Test
    void testExibeContasClientesClienteSemContas() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeContasClientes("64269141198");
        });
    }

    @Test
    void testExibeContasClientesClienteExistente() {
        String listagemEsperada = "Cliente: Victor Emanuel | " + this.conta.toString();

        assertEquals(listagemEsperada, this.controller.exibeContasClientes("00023827490"));
    }
}