package saga.repositories;

import saga.entities.Cliente;

import java.util.*;

/**
 * Classe responsável por guardar clientes.
 *
 * @author Mariane Carvalho
 */
public class ClienteRepositorio {

    /**
     * Mapa de clientes, onde cada cliente é identificado pelo seu cpf.
     */
    private Map<String, Cliente> clientes;

    /**
     * Contrutor que inicializa os atributos.
     */
    public ClienteRepositorio() {
        this.clientes = new HashMap<>();
    }

    /**
     * Adiciona um cliente ao mapa.
     *
     * @param cpf cpf do cliente.
     * @param cliente Cliente à ser adicionado.
     * @return True se adicionar, false se já existir.
     */
    public boolean adicionaCliente(String cpf, Cliente cliente) {
        if(this.clientes.containsValue(cliente)) return false;
        this.clientes.put(cpf, cliente);
        return true;
    }

    /**
     * Obtém um cliente do mapa através de seu cpf.
     *
     * @param cpf cpf do cliente.
     * @return O cliente.
     */
    public Cliente consultaCliente(String cpf) {
        return this.clientes.get(cpf);
    }

    /**
     * Obtém a lista de clientes no mapa.
     *
     * @return A lista de clientes.
     */
    public List<Cliente> consultaClientes() {
        return new ArrayList<Cliente>(this.clientes.values());
    }

    /**
     * Remove um cliente do mapa através de seu cpf.
     *
     * @param cpf cpf do cliente.
     */
    public void removeCliente(String cpf) {
        this.clientes.remove(cpf);
    }
}
