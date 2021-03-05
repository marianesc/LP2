package controle_de_alunos;

/**
 * Classe que representa um aluno.
 *
 * @author Mariane Carvalho
 */
public class Aluno {

    /**
     * Matrícula do aluno.
     */
    private String matricula;
    /**
     * Nome do aluno.
     */
    private String nome;
    /**
     * Curso do aluno.
     */
    private String curso;

    /**
     * Constroi um aluno através de sua matrícula, nome e curso.
     *
     * @param matricula Matricula do aluno.
     * @param nome Nome do aluno.
     * @param curso Curso do aluno.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    public Aluno(String matricula, String nome, String curso) throws IllegalArgumentException, NullPointerException{
        Verificador.verificaNull(matricula, "Matrícula não pode ser nula.");
        Verificador.verificaNull(nome, "Nome não pode ser nulo.");
        Verificador.verificaNull(curso, "Curso não pode ser nulo.");
        Verificador.verificaVazia(matricula, "Matrícula não pode ficar em branco.");
        Verificador.verificaVazia(nome, "Nome não pode ficar em branco.");
        Verificador.verificaVazia(curso, "Curso não pode ficar em branco.");
        Verificador.verificaSomenteDigitos(matricula, "Matrícula só pode conter números.");

        this.matricula = matricula;
        this.nome = nome;
        this.curso = curso;
    }

    /**
     * Gera o hashCode da matrícula do aluno.
     *
     * @return O hashCode da matrícula do aluno.
     */
    public int hashCode() {
        return this.matricula.hashCode();
    }

    /**
     * Confere se um aluno é igual ao outro, comparando a matrícula.
     *
     * @param o Objeto a ser comparado.
     * @return Booleano se os objetos são iguais ou não.
     */
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return this.matricula.equals(aluno.matricula);
    }

    /**
     * Retorna a String que representa o aluno.
     * A representação apresentará a matrícula, nome e curso do aluno.
     *
     * @return A representação em String de aluno.
     */
    public String toString() {
        return this.matricula + " - " + this.nome + " - " + this.curso;
    }
}
