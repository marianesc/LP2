package Lab02;

/**
 * Representação da conta do aluno em uma cantina.
 *
 * @author Mariane Carvalho
 */
public class ContaCantina {

    /**
     * Nome da cantina a qual a conta é referente.
     */
    private String nomeDaCantina;
    /**
     * Quantidade de itens comprados na cantina.
     */
    private int qtdItens;
    /**
     * Total de valor gasto, em centavos, na cantina.
     */
    private int valorGasto;
    /**
     * Quanto falta pagar, em centavos, para quitar a dívida na cantina.
     */
    private int faltaPagar;

    /**
     * Constroi uma conta em uma cantina a partir de seu nome.
     *
     * @param nomeDaCantina nome da cantina.
     */
    public ContaCantina(String nomeDaCantina) {
        this.nomeDaCantina = nomeDaCantina;
        this.qtdItens = 0;
        this.valorGasto = 0;
        this.faltaPagar = 0;
    }

    /**
     * Adiciona a quantidade de itens e o valor gasto a cada lanche.
     * Além de atualizar o valor que falta ser pago.
     *
     * @param qtdItens quantidade de itens que foram consumidos.
     * @param valorCentavos valor gasto em centavos.
     */
    public void cadastraLanche(int qtdItens, int valorCentavos) {
        this.qtdItens += qtdItens;
        this.valorGasto += valorCentavos;
        this.faltaPagar += valorCentavos;
    }

    /**
     * Desconta o valor, passado como parâmetro, do que falta pagar, se esse valor for menor ou igual ao que falta pagar.
     * Caso contrário, não é descontado.
     *
     * @param valorCentavos valor, em centavos, pago.
     */
    public void pagaConta(int valorCentavos) {
        if (valorCentavos <= this.faltaPagar) this.faltaPagar -= valorCentavos;
    }

    /**
     * Mostra quanto falta pagar, em centavos, para quitar a dívida.
     *
     * @return retorna o valor inteiro de quanto falta pagar para quitar a dívida.
     */
    public int getFaltaPagar( ) {
        return this.faltaPagar;
    }

    /**
     * Retorna a String que representa a ContaCantina.
     * A representaçõa segue o formato "nomeDaCantina quantidadeDeItens totalDeValorGasto"
     *
     * @return a representação em String da ContaCantina.
     */
    public String toString( ) {
        return this.nomeDaCantina + " " + this.qtdItens + " " + this.valorGasto;
    }
}
