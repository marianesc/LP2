package controle_de_alunos;

import java.util.HashSet;

/**
 * Classe que representa um grupo de alunos.
 *
 * @author Mariane Carvalho
 */
public class Grupo {

    /**
     * Nome do grupo
     */
    private String nome;
    /**
     * Conjunto de alunos que compõem o grupo.
     */
    private HashSet<Aluno> alunos;


    /**
     * Constroi um grupo a partir de seu nome.
     *
     * @param nome Nome do grupo.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    public Grupo(String nome) throws IllegalArgumentException, NullPointerException{
        Verificador.verificaNull(nome, "Nome não pode ser nulo.");
        Verificador.verificaVazia(nome, "Nome não pode ficar em branco.");

        this.nome = nome;
        this.alunos = new HashSet<>();
    }

    /**
     * Adiciona um aluno ao grupo.
     *
     * @param aluno Aluno à ser adicionado.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    public void adicionaAluno(Aluno aluno) throws NullPointerException{
        Verificador.verificaNull(aluno, "Nome não pode ser nulo.");
        this.alunos.add(aluno);
    }

    /**
     * Retorna a String que representa o grupo.
     * A representação apresentará os membros do grupo.
     *
     * @return A representação em String de grupo.
     */
    public String toString() {
        String lista = "\nAlunos do grupo " + this.nome + ":\n";
        for (Aluno aluno : this.alunos) {
            lista += "* " + aluno.toString() + "\n";
        }
        return lista;
    }
}
