package combo;

import produto.Produto;

/**
 * Classe que representa um combo.
 *
 * @author Mariane Carvalho
 */
public class Combo extends Produto {

    /**
     * Fator de promoção do combo.
     */
    private double fator;

    /**
     * Constroi um combo a partir de seu nome, descrição,
     * preço sem a promoção, e fator promocional.
     *
     * @param nome Nome do combo.
     * @param descricao Descrição do combo.
     * @param preco Preço sem desconto do combo.
     * @param fator Fator de promoção.
     */
    public Combo(String nome, String descricao, double preco, double fator) {
        super(nome, descricao, preco);
        this.fator = fator;
    }

    /**
     * Altera o valor do fator de promoção.
     *
     * @param novoValor Novo valor do fator.
     */
   public void setFator(double novoValor) {
        this.fator = novoValor;
   }

    /**
     * Pega o preço do combo com desconto.
     *
     * @return O preço do combo com desconto.
     */
    @Override
    public double getPreco() {
        return super.getPreco() - (super.getPreco() * this.fator);
    }
}
