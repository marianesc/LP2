package saga.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saga.entities.Cliente;
import saga.repositories.ClienteRepositorio;

import static org.junit.jupiter.api.Assertions.*;

class ClienteControllerTest {

    private ClienteController controller;
    private Cliente cliente;

    @BeforeEach
    void init() {
        ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
        this.cliente = new Cliente("00023827490", "Victor Emanuel", "vitao@ccc.ufcg.edu.br", "Labarc");
        clienteRepositorio.adicionaCliente("00023827490", this.cliente);

        this.controller = new ClienteController(clienteRepositorio);
    }

    @Test
    void testAdicionaClienteCpfNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCliente(null, "Ana Amari", "ana_amari@ccc.ufcg.edu.br", "SPG");
        });
    }

    @Test
    void testAdicionaClienteCpfVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCliente("", "Ana Amari", "ana_amari@ccc.ufcg.edu.br", "SPG");
        });
    }

    @Test
    void testAdicionaClienteCpfInvalidoTamanho() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCliente("6426914119800", "Ana Amari", "ana_amari@ccc.ufcg.edu.br", "SPG");
        });
    }

    @Test
    void testAdicionaClienteCpfInvalidoLetra() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCliente("6426914119a", "Ana Amari", "ana_amari@ccc.ufcg.edu.br", "SPG");
        });
    }

    @Test
    void testAdicionaClienteNomeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCliente("64269141198", null, "ana_amari@ccc.ufcg.edu.br", "SPG");
        });
    }

    @Test
    void testAdicionaClienteNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCliente("64269141198", "", "ana_amari@ccc.ufcg.edu.br", "SPG");
        });
    }

    @Test
    void testAdicionaClienteEmailNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCliente("64269141198", "Ana Amari", null, "SPG");
        });
    }

    @Test
    void testAdicionaClienteEmailVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCliente("64269141198", "Ana Amari", "", "SPG");
        });
    }

    @Test
    void testAdicionaClienteLocalizacaoNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCliente("64269141198", "Ana Amari", "ana_amari@ccc.ufcg.edu.br", null);
        });
    }

    @Test
    void testAdicionaClienteLocalizacaoVazia() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCliente("64269141198", "Ana Amari", "ana_amari@ccc.ufcg.edu.br", "");
        });
    }

    @Test
    void testAdicionaClienteExistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCliente("00023827490", "Victor Emanuel", "vitao@ccc.ufcg.edu.br", "Labarc");
        });
    }

    @Test
    void testAdicionaClienteInexistente() {
        Cliente cliente2 = new Cliente("64269141198", "Ana Amari", "ana_amari@ccc.ufcg.edu.br", "SPG");
        this.controller.adicionaCliente("64269141198", "Ana Amari", "ana_amari@ccc.ufcg.edu.br", "SPG");

        String representacao = this.controller.exibeCliente("64269141198");
        assertEquals(cliente2.toString(), representacao);
    }

    @Test
    void testExibeClienteCpfNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeCliente(null);
        });
    }

    @Test
    void testExibeClienteCpfVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeCliente("");
        });
    }

    @Test
    void testExibeClienteCpfInvalidoTamanho() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeCliente("0002382749011");
        });
    }

    @Test
    void testExibeClienteCpfInvalidoLetra() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeCliente("0002382749a");
        });
    }

    @Test
    void testExibeClienteInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeCliente("64269141198");
        });
    }

    @Test
    void testExibeClienteExistente() {
        assertEquals(this.cliente.toString(), this.controller.exibeCliente("00023827490"));
    }

    @Test
    void testEditaClienteCpfNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCliente(null, "nome", "Maria");
        });
    }

    @Test
    void testEditaClienteCpfVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCliente("", "nome", "Maria");
        });
    }

    @Test
    void testEditaClienteCpfInvalidoTamanho() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCliente("0002382749011", "nome", "Maria");
        });
    }

    @Test
    void testEditaClienteCpfInvalidoLetra() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCliente("0002382749a", "nome", "Maria");
        });
    }

    @Test
    void testEditaClienteAtributoNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCliente("00023827490", null, "Maria");
        });
    }

    @Test
    void testEditaClienteAtributoVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCliente("00023827490", "", "Maria");
        });
    }

    @Test
    void testEditaClienteAtributoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCliente("00023827490", "idade", "20");
        });
    }

    @Test
    void testEditaClienteNovoValorNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCliente("00023827490", "nome", null);
        });
    }

    @Test
    void testEditaClienteNovoValorVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCliente("00023827490", "nome", "");
        });
    }

    @Test
    void testEditaClienteInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCliente("64269141198", "nome", "Maria");
        });
    }

    @Test
    void testEditaClienteExistenteCpf() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCliente("00023827490", "cpf", "11123827490");
        });
    }

    @Test
    void testEditaClienteExistenteNome() {
        this.controller.editaCliente("00023827490", "nome", "Pedro");

        String representacaoEsperada = "Pedro - Labarc - vitao@ccc.ufcg.edu.br";
        String representacaoAtual = this.controller.exibeCliente("00023827490");
        assertEquals(representacaoEsperada, representacaoAtual);
    }

    @Test
    void testEditaClienteExistenteEmail() {
        this.controller.editaCliente("00023827490", "email", "pedro@gmail.com");

        String representacaoEsperada = "Victor Emanuel - Labarc - pedro@gmail.com";
        String representacaoAtual = this.controller.exibeCliente("00023827490");
        assertEquals(representacaoEsperada, representacaoAtual);
    }

    @Test
    void testEditaClienteExistenteLocalizacao() {
        this.controller.editaCliente("00023827490", "localizacao", "SPG");

        String representacaoEsperada = "Victor Emanuel - SPG - vitao@ccc.ufcg.edu.br";
        String representacaoAtual = this.controller.exibeCliente("00023827490");
        assertEquals(representacaoEsperada, representacaoAtual);
    }

    @Test
    void testExibeClientes() {
        Cliente cliente2 = new Cliente("64269141198", "Ana Amari", "ana_amari@ccc.ufcg.edu.br", "SPG");
        this.controller.adicionaCliente("64269141198", "Ana Amari", "ana_amari@ccc.ufcg.edu.br", "SPG");

        String representacaoAtual = this.controller.exibeClientes();
        String representacaoEsperada = cliente2.toString() + " | " + this.cliente.toString();

        assertEquals(representacaoEsperada, representacaoAtual);
    }

    @Test
    void testRemoveClienteCpfNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeCliente(null);
        });
    }

    @Test
    void testRemoveClienteCpfVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeCliente("");
        });
    }

    @Test
    void testRemoveClienteCpfInvalidoTamanho() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeCliente("0002382749011");
        });
    }

    @Test
    void testRemoveClienteCpfInvalidoLetra() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeCliente("0002382749a");
        });
    }

    @Test
    void testRemoveClienteInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeCliente("64269141198");
        });
    }

    @Test
    void testRemoveClienteExistente() {
        this.controller.removeCliente("00023827490");

        assertEquals("", this.controller.exibeClientes());
    }
}