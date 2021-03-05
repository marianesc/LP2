package Lab02;

/**
 * Representação da saúde física e mental do aluno.
 *
 * @author Mariane Carvalho
 */
public class Saude {

    /**
     * Como está a saúde física do aluno.
     */
    private String saudeFisica;
    /**
     * Como está a saúde mental do aluno.
     */
    private String saudeMental;

    /**
     * Constroi a saúde do aluno. Ambas, física e mental, inicializadas como boa.
     */
    public Saude() {
        this.saudeFisica = "boa";
        this.saudeMental = "boa";
    }

    /**
     * Altera o valor de saúde mental do aluno.
     *
     * @param valor como está a saúde mental do aluno.
     */
    public void defineSaudeMental(String valor) {
        this.saudeMental = valor;
    }

    /**
     * Altera o valor de saúde física do aluno.
     *
     * @param valor como está a saúde física do aluno.
     */
    public void defineSaudeFisica(String valor) {
        this.saudeFisica = valor;
    }

    /**
     * Retorna a String que representa a saúde do aluno.
     * Caso ambas, saúde mental e física, sejam fracas, a representação será "fraca".
     * Caso ambas sejam boas, a representação será "boa".
     * Caso sejam diferentes, será "ok".
     *
     * @return a representação em String de Saude.
     */
    public String getStatusGeral() {
        if (this.saudeFisica.equals("fraca") && this.saudeMental.equals("fraca")) return "fraca";
        else if (this.saudeFisica.equals("boa") && this.saudeMental.equals("boa")) return "boa";
        return "ok";
    }
}