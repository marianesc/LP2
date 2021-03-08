package produto;

/**
 * Classe que representa um produto, que tem nome, descricao e preco
 * 
 * @author Aluno de período anterior
 * @author Mariane Carvalho
 */
public class Produto {
	/**
	 * Nome do produto
	 */
	private String nome;

	/**
	 * Descricao do produto
	 */
	private String descricao;

	/**
	 * Preço do produto
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
	 * Retorna a String que representa o produto.
	 * A representação apresentará nome, descrição e preço do produto.
	 *
	 * @return A representação em String de produto.
	 */
	@Override
	public String toString() {
		return this.getNome() + " - " + this.getDescricao() + " - R$" + String.format("%.2f", this.getPreco());
	}

	/**
	 * Confere se um produto é igual ao outro, comparando o nome e a descrição.
	 *
	 * @param o Objeto a ser comparado.
	 * @return Booleano se os objetos são iguais ou não.
	 */
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Produto) {
			Produto p = (Produto) o;

			if ((this.getNome() + this.getDescricao()).equals(p.getNome() + p.getDescricao())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Gera o hashCode do nome e descrição do produto.
	 *
	 * @return O hashCode do nome e descrição do produto.
	 */
	@Override
	public int hashCode() {
		return (this.getNome() + this.getDescricao()).hashCode();
	}

	/**
	 * Obtém o nome do produto.
	 *
	 * @return O nome do produto.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Obtém a descrição do produto.
	 *
	 * @return A descrição do produto.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Obtém o preço do produto.
	 *
	 * @return O preço do produto.
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * Altera o preço do produto.
	 *
	 * @param preco Novo preço do produto.
	 */
	protected void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * Invoca o setPreco.
	 *
	 * @param novoValor Novo preço do produto.
	 */
	public void modificaProduto(double novoValor) {
		this.setPreco(novoValor);
	}

}
