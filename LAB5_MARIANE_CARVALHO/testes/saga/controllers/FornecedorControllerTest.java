package saga.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saga.entities.Fornecedor;
import saga.repositories.FornecedorRepositorio;

import static org.junit.jupiter.api.Assertions.*;

class FornecedorControllerTest {

    private FornecedorController controller;
    private Fornecedor fornecedor;

    @BeforeEach
    void init() {
        FornecedorRepositorio fornecedorRepositorio = new FornecedorRepositorio();
        this.fornecedor = new Fornecedor("Helhao", "quiosque@gmail.com", "83 98736-5050");
        fornecedorRepositorio.adicionaFornecedor("Helhao", this.fornecedor);

        this.controller = new FornecedorController(fornecedorRepositorio);
    }

    @Test
    void testAdicionaFornecedorNomeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaFornecedor(null, "marcos@gmail.com",  "83 99151-3570");
        });
    }

    @Test
    void testAdicionaFornecedorNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaFornecedor("", "marcos@gmail.com",  "83 99151-3570");
        });
    }

    @Test
    void testAdicionaFornecedorEmailNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaFornecedor("Marcos", null,  "83 99151-3570");
        });
    }

    @Test
    void testAdicionaFornecedorEmailVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaFornecedor("Marcos", "",  "83 99151-3570");
        });
    }

    @Test
    void testAdicionaFornecedorTelefoneNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaFornecedor("Marcos", "marcos@gmail.com",  null);
        });
    }

    @Test
    void testAdicionaFornecedorTelefoneVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaFornecedor("Marcos", "marcos@gmail.com",  "");
        });
    }

    @Test
    void testAdicionaFornecedorExistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaFornecedor("Helhao", "quiosque@gmail.com", "83 98736-5050");
        });
    }

    @Test
    void testAdicionaFornecedorInexistente() {
        Fornecedor fornecedor2 = new Fornecedor("Marcos", "marcos@gmail.com", "83 99151-3570");
        this.controller.adicionaFornecedor("Marcos", "marcos@gmail.com", "83 99151-3570");

        String representacao = this.controller.exibeFornecedor("Marcos");
        assertEquals(fornecedor2.toString(), representacao);
    }

    @Test
    void testExibeFornecedorNomeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeFornecedor(null);
        });
    }

    @Test
    void testExibeFornecedorNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeFornecedor("");
        });
    }

    @Test
    void testExibeFornecedorInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.exibeFornecedor("Marcos");
        });
    }

    @Test
    void testExibeFornecedorExistente() {
        String representacao = this.controller.exibeFornecedor("Helhao");

        assertEquals(this.fornecedor.toString(), representacao);
    }

    @Test
    void testEditaFornecedorNomeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaFornecedor(null, "telefone", "0000000");
        });
    }

    @Test
    void testEditaFornecedorNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaFornecedor("", "telefone", "0000000");
        });
    }

    @Test
    void testEditaFornecedorAtributoNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaFornecedor("Helhao", null, "0000000");
        });
    }

    @Test
    void testEditaFornecedorAtributoVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaFornecedor("Helhao", "", "0000000");
        });
    }

    @Test
    void testEditaFornecedorExistenteNome() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaFornecedor("Helhao", "nome", "Maria");
        });
    }

    @Test
    void testEditaFornecedorNovoValorNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaFornecedor("Helhao", "telefone", null);
        });
    }

    @Test
    void testEditaFornecedorNovoValorVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaFornecedor("Helhao", "telefone", "");
        });
    }

    @Test
    void testEditaFornecedorAtributoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaFornecedor("Helhao", "idade", "30");
        });
    }

    @Test
    void testEditaFornecedorInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaFornecedor("Marcos", "telefone", "0000000");
        });
    }

    @Test
    void testEditaFornecedorExistenteTelefone() {
        this.controller.editaFornecedor("Helhao", "telefone", "83000000000");

        String representacaoEsperada = "Helhao - quiosque@gmail.com - 83000000000";
        String representacaoAtual = this.controller.exibeFornecedor("Helhao");
        assertEquals(representacaoEsperada, representacaoAtual);
    }

    @Test
    void testEditaFornecedorExistenteEmail() {
        this.controller.editaFornecedor("Helhao", "email", "helio@gmail.com");

        String representacaoEsperada = "Helhao - helio@gmail.com - 83 98736-5050";
        String representacaoAtual = this.controller.exibeFornecedor("Helhao");
        assertEquals(representacaoEsperada, representacaoAtual);
    }

    @Test
    void testExibeFornecedores() {
        Fornecedor fornecedor2 = new Fornecedor("Marcos", "marcos@gmail.com",  "83 99151-3570");
        this.controller.adicionaFornecedor("Marcos", "marcos@gmail.com",  "83 99151-3570");

        String representacaoAtual = this.controller.exibeFornecedores();
        String representacaoEsperada = this.fornecedor.toString() + " | " + fornecedor2.toString();

        assertEquals(representacaoEsperada, representacaoAtual);
    }

    @Test
    void testRemoveFornecedorNomeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeFornecedor(null);
        });
    }

    @Test
    void testRemoveFornecedorNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeFornecedor("");
        });
    }

    @Test
    void testRemoveFornecedorInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.removeFornecedor("Marcos");
        });
    }

    @Test
    void testRemoveFornecedorExistente() {
        this.controller.removeFornecedor("Helhao");

        assertEquals("", this.controller.exibeFornecedores());
    }
}