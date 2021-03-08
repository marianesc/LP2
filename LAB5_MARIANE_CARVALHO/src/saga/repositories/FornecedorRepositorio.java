package saga.repositories;

import saga.entities.Fornecedor;

import java.util.*;

/**
 * Classe responsável por guardar fornecedores.
 *
 * @author Mariane Carvalho
 */
public class FornecedorRepositorio {

    /**
     * Mapa de fornecedores, onde cada fornecedor é identificado pelo seu nome.
     */
    private Map<String, Fornecedor> fornecedores;

    /**
     * Contrutor que inicializa os atributos.
     */
    public FornecedorRepositorio() {
        this.fornecedores = new TreeMap<>();
    }

    /**
     * Adiciona um fornecedor ao mapa.
     *
     * @param nome Nome do fornecedor.
     * @param fornecedor Fornecedor à ser adicionado.
     * @return True se adicionar, false se já existir.
     */
    public boolean adicionaFornecedor(String nome, Fornecedor fornecedor) {
        if(this.fornecedores.containsValue(fornecedor)) return false;
        this.fornecedores.put(nome, fornecedor);
        return true;
    }

    /**
     * Obtém um fornecedor do mapa através de seu nome.
     *
     * @param nome Nome do fornecedor.
     * @return O fornecedor.
     */
    public Fornecedor consultaFornecedor(String nome) {
        return this.fornecedores.get(nome);
    }

    /**
     * Obtém a lista de fornecedores no mapa.
     *
     * @return A lista de fornecedores.
     */
    public List<Fornecedor> consultaFornecedores() {
        return new ArrayList<Fornecedor>(this.fornecedores.values());
    }

    /**
     * Remove um fornecedor do mapa através de seu nome.
     *
     * @param nome Nome do fornecedor.
     */
    public void removeFornecedor(String nome) {
        this.fornecedores.remove(nome);
    }
}
