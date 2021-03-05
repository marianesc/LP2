package controle_de_alunos;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe controladora do sistema.
 * Contém todas as operações necessárias para controlar as coleções e entidades.
 *
 * @author Mariane Carvalho
 */
public class Controlador {

    /**
     * Mapa de alunos, onde cada aluno é identificado pela sua matrícula.
     */
    private HashMap<String, Aluno> alunos;
    /**
     * Mapa de grupos, onde cada grupo é identificados pelo seu nome em letras minúsculas.
     */
    private HashMap<String, Grupo> grupos;
    /**
     * Lista de alunos que responderam no quadro.
     */
    private ArrayList<Aluno> alunoRepondeQuadro;

    /**
     * Contrutor que inicializa os atributos.
     */
    public Controlador() {
        this.alunos = new HashMap<>();
        this.grupos = new HashMap<>();
        this.alunoRepondeQuadro = new ArrayList<>();
    }

    /**
     * Cadastra um aluno, caso ele já não exista.
     *
     * @param matricula Matrícula do aluno.
     * @param nome Nome do aluno.
     * @param curso Curso do aluno.
     * @return Booleano de se foi possível cadastrar.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    public boolean cadastraAluno(String matricula, String nome, String curso) throws IllegalArgumentException, NullPointerException{
        Verificador.verificaNull(matricula, "Matrícula não pode ser nula.");
        Verificador.verificaNull(nome, "Nome não pode ser nulo.");
        Verificador.verificaNull(curso, "Curso não pode ser nulo.");
        Verificador.verificaVazia(matricula, "Matrícula não pode ficar em branco.");
        Verificador.verificaVazia(nome, "Nome não pode ficar em branco.");
        Verificador.verificaVazia(curso, "Curso não pode ficar em branco.");
        Verificador.verificaSomenteDigitos(matricula, "Matrícula só pode conter números.");

        if(this.alunos.containsKey(matricula)) return false;
        Aluno aluno = new Aluno(matricula, nome, curso);
        this.alunos.put(matricula, aluno);
        return true;
    }

    /**
     * Consulta um aluno, caso ele exista.
     *
     * @param matricula Matrícula do aluno.
     * @return A representação do aluno, caso ele exista, se não, null.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    public String consultaAluno(String matricula) throws IllegalArgumentException, NullPointerException{
        Verificador.verificaNull(matricula, "Matrícula não pode ser nula.");
        Verificador.verificaVazia(matricula, "Matrícula não pode ficar em branco.");
        Verificador.verificaSomenteDigitos(matricula, "Matrícula só pode conter números.");

        if(!this.alunos.containsKey(matricula)) return null;
        String representacao = "\nAluno: " + this.alunos.get(matricula).toString();
        return representacao;
    }

    /**
     * Cria um novo grupo, caso ele já não exista.
     *
     * @param nome Nome do grupo.
     * @return Booleano de se foi possível criar.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    public boolean criaGrupo(String nome) throws IllegalArgumentException, NullPointerException{
        Verificador.verificaNull(nome, "Nome não pode ser nulo.");
        Verificador.verificaVazia(nome, "Nome não pode ficar em branco.");

        String key = nome.toLowerCase();
        if(this.grupos.containsKey(key)) return false;
        Grupo grupo = new Grupo(nome);
        this.grupos.put(key, grupo);
        return true;
    }

    /**
     * Aloca um aluno em um grupo, caso ambos existam.
     *
     * @param matricula Matrícula do aluno.
     * @param nomeGrupo Nome do grupo.
     * @return Um inteiro que representa se foi possível alocar ou não.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    public int alocaAlunoGrupo(String matricula, String nomeGrupo) throws IllegalArgumentException, NullPointerException{
        Verificador.verificaNull(matricula, "Matrícula não pode ser nula.");
        Verificador.verificaNull(nomeGrupo, "Nome não pode ser nulo.");
        Verificador.verificaVazia(matricula, "Matrícula não pode ficar em branco.");
        Verificador.verificaSomenteDigitos(matricula, "Matrícula só pode conter números.");
        Verificador.verificaVazia(nomeGrupo, "Nome não pode ficar em branco.");

        if(!this.alunos.containsKey(matricula)) return -1;
        if(!this.grupos.containsKey(nomeGrupo.toLowerCase())) return -2;
        Aluno aluno = this.alunos.get(matricula);
        Grupo grupo = this.grupos.get(nomeGrupo.toLowerCase());
        grupo.adicionaAluno(aluno);
        return 0;
    }

    /**
     * Obtém a representação de um grupo existente.
     *
     * @param nomeGrupo Nome do grupo.
     * @return Os componentes do grupo, caso ele exista, se não, null.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    public String getRepresentacaoGrupo(String nomeGrupo) throws IllegalArgumentException, NullPointerException{
        Verificador.verificaNull(nomeGrupo, "Nome não pode ser nulo.");
        Verificador.verificaVazia(nomeGrupo, "Nome não pode ficar em branco.");

        String key = nomeGrupo.toLowerCase();
        if(!this.grupos.containsKey(key)) return null;
        String representacaoGrupo = this.grupos.get(key).toString();
        return representacaoGrupo;
    }

    /**
     * Registra um aluno que respondeu no quadro, caso ele exista.
     *
     * @param matricula Matrícula do aluno.
     * @return Booleano de se foi possível registrar.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    public boolean registraAlunoQuadro(String matricula) throws IllegalArgumentException, NullPointerException{
        Verificador.verificaNull(matricula, "Matrícula não pode ser nula.");
        Verificador.verificaVazia(matricula, "Matrícula não pode ficar em branco.");
        Verificador.verificaSomenteDigitos(matricula, "Matrícula só pode conter números.");

        if(!this.alunos.containsKey(matricula)) return false;
        Aluno aluno = this.alunos.get(matricula);
        alunoRepondeQuadro.add(aluno);
        return true;
    }

    /**
     * Obtém a representação da lista de alunos que responderam no quadro.
     *
     * @return Sequência ordenada de alunos que responderam no quadro.
     */
    public String getAlunosQuadro() {
        String lista = "\nAlunos:\n";
        for (int i = 0; i < this.alunoRepondeQuadro.size(); i++) {
            Aluno aluno = this.alunoRepondeQuadro.get(i);
            lista += (i + 1) + ". " + aluno.toString() + "\n";
        }
        return lista;
    }
}
