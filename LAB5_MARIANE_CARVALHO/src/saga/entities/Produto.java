package saga.entities;

import java.util.Objects;

/**
 * Classe que representa um produto.
 *
 * @author Mariane Carvalho
 */
public class Produto implements Comparable<Produto>{

    /**
     * Nome do produto.
     */
    private String nome;
    /**
     * Descrição do produto.
     */
    private String descricao;
    /**
     * Preço do produto.
     */
    private double preco;

    /**
     * Constroi um produto a partir de seu nome, descrição e preço.
     *
     * @param nome Nome do produto.
     * @param descricao Descrição do produto.
     * @param preco Preço do produto.
     */
    public Produto(String nome, String descricao, double preco) {

        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    /**
     * Gera o hashCode do nome e descrição do produto.
     *
     * @return O hashCode do nome e descrição do produto.
     */
    public int hashCode() {
        return Objects.hash(this.nome, this.descricao);
    }

    /**
     * Confere se um produto é igual ao outro, comparando o nome e a descrição.
     *
     * @param o Objeto a ser comparado.
     * @return Booleano se os objetos são iguais ou não.
     */
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return this.nome.equals(produto.nome) && this.descricao.equals(produto.descricao);
    }

    /**
     * Altera o preço do produto.
     *
     * @param novoPreco Novo preço do produto.
     */
    public void setPreco(double novoPreco) {
        this.preco = novoPreco;
    }

    /**
     * Retorna a String que representa o produto.
     * A representação apresentará nome, descrição e preço do produto.
     *
     * @return A representação em String de produto.
     */
    public String toString() {
        String formataPreco = String.format("%.2f", this.preco);
        String representacao = this.nome + " - " + this.descricao + " - R$" + formataPreco;
        return representacao;
    }

    /**
     * Compara que produto vem primeiro através do nome.
     *
     * @param o Produto à ser comparado.
     * @return Inteiro que representa se o nome é lexicograficamente maior(>0), menor(<0) ou igual(0) ao outro.
     */
    public int compareTo(Produto o) {
        return this.nome.compareTo(o.nome);
    }

    /**
     * Obtém o preço do produto.
     *
     * @return O preço do produto.
     */
    public double getPreco() {
        return this.preco;
    }
}
