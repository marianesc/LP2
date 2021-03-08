package saga.entities;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Classe que representa um cliente.
 *
 * @author Mariane Carvalho
 */
public class Cliente implements Comparable<Cliente>{

    /**
     * CPF do cliente.
     */
    private final String cpf;
    /**
     * Nome do cliente.
     */
    private String nome;
    /**
     * Email do cliente.
     */
    private String email;
    /**
     * Local onde o cliente trabalha.
     */
    private String localizacao;
    /**
     * Mapa de contas com fornecedores do cliente.
     */
    private Map<String, Conta> contas;

    /**
     * Constroi um cliente a partir de seu cpf, nome, email, e local onde trabalha.
     *
     * @param cpf cpf do cliente.
     * @param nome Nome do cliente.
     * @param email Email do cliente
     * @param localizacao Local onde trabalha.
     */
    public Cliente(String cpf, String nome, String email, String localizacao) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.localizacao = localizacao;
        this.contas = new TreeMap<>();
    }

    /**
     * Gera o hashCode do cpf do cliente.
     *
     * @return O hashCode do cpf do cliente.
     */
    public int hashCode() {
        return this.cpf.hashCode();
    }

    /**
     * Confere se um cliente é igual ao outro, comparando o cpf.
     *
     * @param o Objeto a ser comparado.
     * @return Booleano se os objetos são iguais ou não.
     */
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return this.cpf.equals(cliente.cpf);
    }

    /**
     * Retorna a String que representa o cliente.
     * A representação apresentará nome, local de trabalho e email do cliente.
     *
     * @return A representação em String de cliente.
     */
    public String toString() {
        String representacao = this.nome + " - " + this.localizacao + " - " + this.email;
        return representacao;
    }

    /**
     * Altera o nome do cliente.
     *
     * @param novoNome Novo nome do cliente.
     */
    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    /**
     * Altera o email do cliente.
     *
     * @param novoEmail Novo email do cliente.
     */
    public void setEmail(String novoEmail) {
        this.email = novoEmail;
    }

    /**
     * Altera o local de trabalho do cliente.
     *
     * @param novaLocalizacao Novo local de trabalho do cliente.
     */
    public void setLocalizacao(String novaLocalizacao) {
        this.localizacao = novaLocalizacao;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Verifica se cliente possui certo atributo.
     *
     * @param atributoProcurado Atributo à ser procurado.
     * @return Boolean se cliente possui o atributo.
     */
    public static boolean contemAtributo(String atributoProcurado) {
        Field[] atributos = Cliente.class.getDeclaredFields();

        for (Field atributo : atributos) {
            if (atributo.getName().equals(atributoProcurado)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Compara que cliente vem primeiro através do nome.
     *
     * @param o Cliente à ser comparado.
     * @return Inteiro que representa se o nome é lexicograficamente maior(>0), menor(<0) ou igual(0) ao outro.
     */
    public int compareTo(Cliente o) {
        return this.nome.compareTo(o.nome);
    }

    /**
     * Adiciona uma compra à conta do cliente com um fornecedor.
     *
     * @param nomeFornecedor Nome do fornecedor.
     * @param compra Compra à ser adicionada.
     */
    public void adicionaNaConta(String nomeFornecedor, Compra compra) {
        if(!this.contas.containsKey(nomeFornecedor)) {
            Conta conta = new Conta(nomeFornecedor);
            this.contas.put(nomeFornecedor, conta);
        }
        this.contas.get(nomeFornecedor).adicionaCompra(compra);

    }

    /**
     * Obtém a conta do cliente com um fornecedor.
     *
     * @param nomeFornecedor Nome do fornecedor.
     * @return A conta.
     */
    public Conta consultaConta(String nomeFornecedor) {
        return this.contas.get(nomeFornecedor);
    }

    /**
     * Obtém a lista de contas do cliente.
     *
     * @return A lista de contas.
     */
    public List<Conta> consultaContas() {
        return new ArrayList<>(this.contas.values());
    }

}
