package saga.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saga.entities.Fornecedor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class FornecedorRepositorioTest {

    private FornecedorRepositorio fornecedorRepositorio;
    private Fornecedor fornecedor1;
    private Fornecedor fornecedor2;

    @BeforeEach
    void init() {
        this.fornecedorRepositorio = new FornecedorRepositorio();
        this.fornecedor1 = new Fornecedor("Helhao", "quiosque@gmail.com", "83 98736-5050");
        this.fornecedor2 = new Fornecedor("Marcos", "marcos@gmail.com", "83 99945-1294");
        this.fornecedorRepositorio.adicionaFornecedor("Helhao", this.fornecedor1);
        this.fornecedorRepositorio.adicionaFornecedor("Marcos", this.fornecedor2);
    }

    @Test
    void testAdicionaFornecedor() {
        Fornecedor fornecedor = new Fornecedor("Dona Alba", "alba@gmail.com", "83 99945-1294");
        this.fornecedorRepositorio.adicionaFornecedor("Dona Alba", fornecedor);
        assertEquals(fornecedor, this.fornecedorRepositorio.consultaFornecedor("Dona Alba"));
    }

    @Test
    void testConsultaFornecedor() {
        assertEquals(this.fornecedor1, this.fornecedorRepositorio.consultaFornecedor("Helhao"));
    }

    @Test
    void testConsultaFornecedorInexistente() {
        assertNull(this.fornecedorRepositorio.consultaFornecedor("Seu Olavo"));
    }

    @Test
    void testConsultaFornecedores() {
        Map<String, Fornecedor> fornecedores = new TreeMap<>();
        fornecedores.put("Helhao", this.fornecedor1);
        fornecedores.put("Marcos", this.fornecedor2);
        List<Fornecedor> fornecedores1 = new ArrayList<>(fornecedores.values());
        assertEquals(fornecedores1, this.fornecedorRepositorio.consultaFornecedores());
    }

    @Test
    void testRemoveFornecedor() {
        this.fornecedorRepositorio.removeFornecedor("Marcos");
        assertEquals(1, this.fornecedorRepositorio.consultaFornecedores().size());
    }
}