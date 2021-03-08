package saga.repositories;

import saga.entities.Produto;

import java.util.*;

/**
 * Classe responsável por guardar produtos.
 *
 * @author Mariane Carvalho
 */
public class ProdutoRepositorio {

    /**
     * Set de produtos.
     */
    private Set<Produto> produtos;

    /**
     * Contrutor que inicializa os atributos.
     */
    public ProdutoRepositorio() {
        this.produtos = new TreeSet<>();
    }

    /**
     * Adiciona um produto ao set.
     *
     * @param produto Produto à ser adicionado.
     * @return Booleano se foi adicionado ou não.
     */
    public boolean adicionaProduto(Produto produto) {
        boolean cadastro = this.produtos.add(produto);
        return cadastro;
    }

    /**
     * Obtém um produto do set através de seu nome e descrição.
     *
     * @param nome Nome do produto.
     * @param descricao Descrição do produto.
     * @return Retorna o produto ou null se não existir.
     */
    public Produto consultaProduto(String nome, String descricao) {
        Produto produto = new Produto(nome, descricao, 0);
        if (this.produtos.contains(produto)) {
            for (Produto produtoAtual : this.produtos) {
                if (produto.equals(produtoAtual)) {
                    return produtoAtual;
                }
            }
        }
        return null;
    }

    /**
     * Obtém a lista de produtos no set.
     *
     * @return A lista de produtos.
     */
    public List<Produto> consultaProdutos() {
        return new ArrayList<Produto>(this.produtos);
    }

    /**
     * Remove um produto do set.
     *
     * @param produto Produto à ser removido.
     */
    public void removeProduto(Produto produto) {
        this.produtos.remove(produto);
    }
}
