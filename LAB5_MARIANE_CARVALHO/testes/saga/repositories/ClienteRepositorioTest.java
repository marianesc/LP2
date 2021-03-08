package saga.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saga.entities.Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ClienteRepositorioTest {

    private ClienteRepositorio clienteRepositorio;
    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void init() {
        this.clienteRepositorio = new ClienteRepositorio();
        this.cliente1 = new Cliente("00023827490", "Victor Emanuel", "vitao@ccc.ufcg.edu.br", "Labarc");
        this.cliente2 = new Cliente("64269141198", "Ana Amari", "ana_amari@ccc.ufcg.edu.br", "SPG");
        this.clienteRepositorio.adicionaCliente("00023827490", cliente1);
        this.clienteRepositorio.adicionaCliente("64269141198", cliente2);
    }

    @Test
    void testAdicionaCliente() {
        Cliente cliente3 = new Cliente("58217738123", "Lucio Correia", "lucio_correia@ccc.ufcg.edu.br", "SPLab");
        this.clienteRepositorio.adicionaCliente("58217738123", cliente3);
        assertEquals(cliente3, this.clienteRepositorio.consultaCliente("58217738123"));
    }

    @Test
    void testConsultaCliente() {
        assertEquals(this.cliente1, this.clienteRepositorio.consultaCliente("00023827490"));
    }

    @Test
    void testConsultaClienteInexistente() {
        assertNull(this.clienteRepositorio.consultaCliente("01123827490"));
    }

    @Test
    void testConsultaClientes() {
        Map<String, Cliente> clientes = new HashMap<>();
        clientes.put("00023827490", this.cliente1);
        clientes.put("64269141198", this.cliente2);
        List<Cliente> clientes1 = new ArrayList<>(clientes.values());
        assertEquals(clientes1, this.clienteRepositorio.consultaClientes());
    }

    @Test
    void testRemoveCliente() {
        this.clienteRepositorio.removeCliente("64269141198");
        assertEquals(1, this.clienteRepositorio.consultaClientes().size());
    }
}