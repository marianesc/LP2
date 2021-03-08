package saga.entities;

import java.util.Objects;

/**
 * Classe que representa uma compra.
 *
 * @author Mariane Carvalho
 */
public class Compra {

    /**
     * Nome do produto.
     */
    private String nomeProduto;
    /**
     * Descrição do produto.
     */
    private String descricaoProduto;
    /**
     * Data da compra.
     */
    private String data;
    /**
     * Preço do produto.
     */
    private double preco;
    /**
     * Nome do cliente.
     */
    private String nomeCliente;
    /**
     * Nome do fornecedor.
     */
    private String nomeFornecedor;

    /**
     * Constroi uma compra a partir do nome do produto, descrição e preço do produto,
     * data da compra, e nome do cliente e do fornecedor.
     *
     * @param nomeProduto Nome do produto.
     * @param descricaoProduto Descrição do produto.
     * @param preco Preço do produto.
     * @param data Data da compra.
     * @param nomeCliente Nome do cliente.
     * @param nomeFornecedor Nome do fornecedor.
     */
    public Compra(String nomeProduto, String descricaoProduto, double preco, String data, String nomeCliente, String nomeFornecedor) {
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.preco = preco;
        this.data = data;
        this.nomeCliente = nomeCliente;
        this.nomeFornecedor = nomeFornecedor;
    }

    /**
     * Obtém o preço da compra.
     *
     * @return O preço da compra.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Retorna a String que representa a compra.
     * A representação apresentará nome do produto e data da compra.
     *
     * @return A representação em String de compra.
     */
    public String toString() {
        String representacao = this.nomeProduto + " - " + this.data.replace('/', '-');
        return representacao;
    }

    /**
     * Obtém a descrição do produto.
     *
     * @return A descrição do produto.
     */
    public String getDescricaoProduto() {
        return this.descricaoProduto;
    }

    /**
     * Obtém a data da compra.
     *
     * @return A data da compra.
     */
    public String getData() {
        return this.data;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNomeCliente() {
        return this.nomeCliente;
    }

    /**
     * Obtém o nome do fornecedor.
     *
     * @return O nome do fornecedor.
     */
    public String getNomeFornecedor() {
        return this.nomeFornecedor;
    }

    /**
     * Retorna a String de representação de acordo com o critério de ordenação pedido.
     *
     * @param criterio Critério de ordenação pedido.
     * @return A representação em String.
     */
    public String getRepresentacao(String criterio) {
        switch (criterio) {
            case "cliente":
                return this.nomeCliente + ", " + this.nomeFornecedor + ", "
                        + this.descricaoProduto + ", " + this.data;
            case "fornecedor":
                return this.nomeFornecedor + ", " + this.nomeCliente + ", "
                        + this.descricaoProduto + ", " + this.data;
            case "data":
                return this.data + ", " + this.nomeCliente + ", "
                        + this.nomeFornecedor + ", " + this.descricaoProduto;
            default:
                return this.toString();
        }
    }

    /**
     * Confere se uma compra é igual à outra, comparando todos os seus atributos.
     *
     * @param o Objeto a ser comparado.
     * @return Booleano se os objetos são iguais ou não.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return Double.compare(compra.preco, preco) == 0 &&
                this.nomeProduto.equals(compra.nomeProduto) &&
                this.descricaoProduto.equals(compra.descricaoProduto) &&
                this.data.equals(compra.data) &&
                this.nomeCliente.equals(compra.nomeCliente) &&
                this.nomeFornecedor.equals(compra.nomeFornecedor);
    }

    /**
     * Gera o hashCode de todos os atributos de compra.
     *
     * @return O hashCode de todos os atributos de compra.
     */
    public int hashCode() {
        return Objects.hash(this.nomeProduto, this.descricaoProduto, this.data, this.preco, this.nomeCliente, this.nomeFornecedor);
    }
}
