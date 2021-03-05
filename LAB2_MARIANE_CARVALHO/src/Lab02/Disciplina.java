package Lab02;
import java.util.Arrays;

/**
 * Representação da disciplina do aluno.
 *
 * @author Mariane Carvalho
 */
public class Disciplina {

    /**
     * Nome da disciplina.
     */
    private String nomeDisciplina;
    /**
     * Horas de estudo da disciplina.
     */
    private int horas;
    /**
     * Array de todas as notas da disciplina.
     */
    private double[] notas;

    /**
     * Constroi uma disciplina a partir de seu nome.
     *
     * @param nomeDisciplina nome da disciplina.
     */
    public Disciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
        this.horas = 0;
        this.notas = new double[4];
    }

    /**
     * Adiciona as horas estudadas.
     *
     * @param horas quantidade de horas estudadas.
     */
    public void cadastraHoras(int horas) {
        this.horas += horas;
    }

    /**
     * Atualiza o valor de cada nota.
     * As notas podem ser cadastradas mais de uma vez, sendo considerada a última cadastrada.
     *
     * @param nota a que nota o valor se refere.
     * @param valorNota o valor da nota.
     */
    public void cadastraNota(int nota, double valorNota) {
        this.notas[nota - 1] = valorNota;
    }

    /**
     * Verifica se o aluno foi aprovado ou não.
     * Retorna true se a média das notas for maior ou igual a 7, logo, será aprovado.
     * Retorna false se a média for menor que 7, logo, será reprovado.
     *
     * @return retorna um booleano de se a nota é maior ou igual à 7.
     */
    public boolean aprovado() {
        return media() >= 7;
    }

    /**
     * Cálcula a média das notas do aluno na disciplina.
     *
     * @return retorna a média.
     */
    private double media() {
        double soma = 0;
        for (double nota :this.notas) soma += nota;
        return soma / this.notas.length;
    }

    /**
     * Retorna a String que representa a Disciplina.
     * A representaçõa segue o formato "nomeDaDisciplina horasDeEstudo mediaDasNotas ArrayComAsNotas".
     *
     * @return a representação em String da Disciplina.
     */
    public String toString() {
        return this.nomeDisciplina + " " + this.horas + " " + media() + " " + Arrays.toString(this.notas);
    }

}
