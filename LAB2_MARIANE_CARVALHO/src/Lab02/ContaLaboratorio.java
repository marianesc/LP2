package Lab02;

/**
 * Representação da conta do aluno em um laboratório de Ciência da Computação (LCC).
 *
 * @author Mariane Carvalho
 */
public class ContaLaboratorio {

    /**
     * Nome do laboratório que o aluno irá querer gerenciar.
     */
    private String nomeLaboratorio;
    /**
     * Cota determina o limite, em mb, de espaço disponível no sistema de armazenamento.
     */
    private int cota;
    /**
     * Espaço em mb já ocupado da cota disponível.
     */
    private int espacoOcupado;

    /**
     * Constroi uma conta no laboratório a partir de seu nome, com cota padrão de 2000mb.
     * Quando passado apenas o nome do laboratório, esse construtor chama o outro que é completo.
     *
     * @param nomeLaboratorio nome do laboratório.
     */
    public ContaLaboratorio (String nomeLaboratorio) {
        this(nomeLaboratorio, 2000);
    }

    /**
     * Constroi uma conta no laboratório a partir de seu nome e cota.
     *
     * @param nomeLaboratorio nome do laboratório.
     * @param cota  limite de espaço, em mb, disponível no sistema de armazenamento.
     */
    public ContaLaboratorio (String nomeLaboratorio, int cota) {
        this.nomeLaboratorio = nomeLaboratorio;
        this.cota = cota;
        this.espacoOcupado = 0;
    }

    /**
     * Adiciona a nova quantidade de mb que esta sendo consumido ao total que já está sendo ocupado da cota.
     *
     * @param mbytes quantidade de mb que está sendo consumido.
     */
    public void consomeEspaco(int mbytes) {
        this.espacoOcupado += mbytes;
    }

    /**
     * Subtrai a quantidade de mb que está sendo liberado do espaço ocupado.
     * Caso seja passado um valor maior que o ocupado, o espaço ocupado será zerado.
     *
     * @param mbytes quantidade de mb que está sendo liberado.
     */
    public void liberaEspaco(int mbytes) {
        this.espacoOcupado = Math.max(0, this.espacoOcupado - mbytes);
    }

    /**
     * Retorna true se a cota for atingida, caso não, retorna false.
     *
     * @return retorna um booleano de se o espaço ocupado é maior ou igual à cota disponível.
     */
    public boolean atingiuCota() {
        return this.espacoOcupado >= this.cota;
    }

    /**
     * Retorna a String que representa o ContaLaboratorio.
     * A representação segue o formato  "nomeDoLaboratório espaçoOcupado/cota".
     *
     * @return a representação em String do ContaLaboratorio.
     */
    public String toString(){
        return this.nomeLaboratorio + " " + this.espacoOcupado + "/" + this.cota;
    }
}
