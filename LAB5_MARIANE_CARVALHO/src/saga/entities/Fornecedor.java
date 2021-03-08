package saga.entities;

import saga.repositories.ProdutoRepositorio;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um fornecedor.
 *
 * @author Mariane Carvalho
 */
public class Fornecedor implements Comparable<Fornecedor>{

    /**
     * Nome do fornecedor.
     */
    private final String nome;
    /**
     * Email do fornecedor.
     */
    private String email;
    /**
     * Telefone do fornecedor.
     */
    private String telefone;
    /**
     * Repositório de produtos do fornecedor.
     */
    private ProdutoRepositorio produtos;

    /**
     * Constroi um fornecedor a partir de seu nome, email e telefone.
     *
     * @param nome Nome do fornecedor.
     * @param email Email do fornecedor.
     * @param telefone Telefone do fornecedor.
     */
    public Fornecedor(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.produtos = new ProdutoRepositorio();
    }

    /**
     * Gera o hashCode do nome do fornecedor.
     *
     * @return O hashCode do nome do fornecedor.
     */
    public int hashCode() {
        return this.nome.hashCode();
    }

    /**
     * Confere se um fornecedor é igual ao outro, comparando o nome.
     *
     * @param o Objeto a ser comparado.
     * @return Booleano se os objetos são iguais ou não.
     */
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Fornecedor fornecedor = (Fornecedor) o;
        return this.nome.equals(fornecedor.nome);
    }

    /**
     * Retorna a String que representa o fornecedor.
     * A representação apresentará nome, email e telefone do fornecedor.
     *
     * @return A representação em String de fornecedor.
     */
    public String toString() {
        String representacao = this.nome + " - " + this.email + " - " + this.telefone;
        return representacao;
    }

    /**
     * Altera o email do fornecedor.
     *
     * @param novoEmail Novo email do fornecedor.
     */
    public void setEmail(String novoEmail) {
        this.email = novoEmail;
    }

    /**
     * Altera o telefone do fornecedor.
     *
     * @param novoTelefone novo telefone do fornecedor.
     */
    public void setTelefone(String novoTelefone) {
        this.telefone = novoTelefone;
    }

    /**
     * Verifica se fornecedor possui certo atributo.
     *
     * @param atributoProcurado Atributo à ser procurado.
     * @return Boolean se cliente possui o atributo.
     */
    public static boolean contemAtributo(String atributoProcurado) {
        Field[] atributos = Fornecedor.class.getDeclaredFields();

        for (Field atributo : atributos) {
            if (atributo.getName().equals(atributoProcurado)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adiciona um produto ao repositório do fornecedor.
     *
     * @param produto Produto à ser adicionado.
     * @return Booleano se foi possivel adicionar.
     */
    public boolean adicionaProduto(Produto produto) {
        return this.produtos.adicionaProduto(produto);
    }

    /**
     * Obtém um produto do repositório do fornecedor.
     *
     * @param nome Nome do produto.
     * @param descricao Descrição do produto.
     * @return Retorna o produto ou null se não existir.
     */
    public Produto consultaProduto(String nome, String descricao) {
        return this.produtos.consultaProduto(nome, descricao);
    }

    /**
     * Obtém a lista de produtos dos repositório do fonecedor.
     *
     * @return A lista de produtos.
     */
    public List<Produto> consultaProdutos() {
        return new ArrayList<Produto>(this.produtos.consultaProdutos());
    }

    /**
     * Remove um produto do repositório do fornecedor.
     *
     * @param produto Produto à ser removido.
     */
    public void removeProduto(Produto produto) {
        this.produtos.removeProduto(produto);
    }

    /**
     * Compara que fornecedor vem primeiro através do nome.
     *
     * @param o Fornecedor a ser comparado.
     * @return Inteiro que representa se o nome é lexicograficamente maior(>0), menor(<0) ou igual(0) ao outro.
     */
    public int compareTo(Fornecedor o) {
        return this.nome.compareTo(o.nome);
    }

    /**
     * Obtém o nome do fornecedor.
     *
     * @return O nome do fornecedor.
     */
    public String getNome(){
        return this.nome;
    }
}
