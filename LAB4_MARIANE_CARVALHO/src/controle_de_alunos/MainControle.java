package controle_de_alunos;

import java.util.Scanner;

/**
 * Interface com menus texto para manipular um controle de alunos.
 *
 * @author Mariane Carvalho
 */
public class MainControle {

    public static void main(String[] args) {
        Controlador controlador = new Controlador();

        Scanner sc = new Scanner(System.in);
        String escolha = "";
        while (true) {
            try {
                escolha = menu(sc);
                comando(escolha, controlador, sc);
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
                fechar();
            }
        }
    }

    /**
     * Exibe o menu e captura a escolha do/a usuário/a.
     *
     * @param sc Scanner para captura da opção do usuário.
     * @return O comando escolhido.
     * @throws IllegalArgumentException Caso receba uma entrada vazia.
     */
    private static String menu(Scanner sc) throws IllegalArgumentException{
        System.out.print(
                "\n---\nMENU\n" +
                        "(C)adastrar Aluno\n" +
                        "(E)xibir Aluno\n" +
                        "(N)ovo Grupo\n" +
                        "(A)locar Aluno no Grupo e Imprimir Grupos\n" +
                        "(R)egistrar Aluno que Respondeu\n" +
                        "(I)mprimir Alunos que Responderam\n" +
                        "(O)ra, vamos fechar o programa!\n" +
                        "\n" +
                        "Opção> ");
        String opcao =  sc.nextLine().trim().toUpperCase();

        Verificador.verificaVazia(opcao, "Opção inválida(nula ou vazia)");

        return opcao;
    }

    /**
     * Interpreta a opção escolhida por quem está usando o sistema.
     *
     * @param opcao Opção digitada.
     * @param controlador Instância do controlador do sistema.
     * @param sc Scanner para o caso do comando precisar de mais input.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    private static void comando(String opcao, Controlador controlador, Scanner sc) throws IllegalArgumentException, NullPointerException{
        switch (opcao) {
            case "C":
                cadastraAluno(controlador, sc);
                break;
            case "E":
                exibeAluno(controlador, sc);
                break;
            case "N":
                novoGrupo(controlador,sc);
                break;
            case "A":
                alocarImprimirGrupo(controlador, sc);
                break;
            case "R":
                registraAlunoRespondeu(controlador, sc);
                break;
            case "I":
                imprimeAlunosResponderam(controlador);
                break;
            case "O":
                fechar();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    /**
     * Cadastra um aluno no controlador, caso ele já não exista.
     *
     * @param controlador Instância do controlador do sistema.
     * @param sc Scanner para o caso do comando precisar de mais input.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    private static void cadastraAluno(Controlador controlador, Scanner sc) throws IllegalArgumentException, NullPointerException{
        System.out.print("\nMatrícula: ");
        String matricula = sc.nextLine().trim();

        System.out.print("Nome: ");
        String nome = sc.nextLine().trim();

        System.out.print("Curso: ");
        String curso = sc.nextLine().trim();

        if(controlador.cadastraAluno(matricula, nome, curso)) System.out.println("CADASTRO REALIZADO!");
        else System.out.println("MATRÍCULA JÁ CADASTRADA!");
    }

    /**
     * Sai da aplicação.
     */
    private static void fechar() {
        System.out.println("\nEncerrando aplicação.");
        System.exit(0);
    }

    /**
     * Exibe o aluno solicitado caso ele exista.
     *
     * @param controlador Instância do controlador do sistema.
     * @param sc Scanner para o caso do comando precisar de mais input.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    private static void exibeAluno(Controlador controlador, Scanner sc) throws IllegalArgumentException, NullPointerException{
        System.out.print("\nMatrícula: ");
        String matricula = sc.nextLine().trim();

        String representacaoAluno = controlador.consultaAluno(matricula);
        if(representacaoAluno != null) System.out.println(representacaoAluno);
        else System.out.println("Aluno não cadastrado.");
    }

    /**
     * Cria um novo grupo, caso ele já não exista.
     *
     * @param controlador Instância do controlador do sistema.
     * @param sc Scanner para o caso do comando precisar de mais input.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    private static void novoGrupo(Controlador controlador, Scanner sc) throws IllegalArgumentException, NullPointerException{
        System.out.print("\nGrupo: ");
        String grupo = sc.nextLine().trim();

        if(controlador.criaGrupo(grupo)) System.out.println("CADASTRO REALIZADO!");
        else System.out.println("GRUPO JÁ CADASTRADO!");
    }

    /**
     * Invoca a função adequada de acordo com a opção escolhida por quem está usando o sistema.
     *
     * @param controlador Instância do controlador do sistema.
     * @param sc Scanner para o caso do comando precisar de mais input.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    private static void alocarImprimirGrupo(Controlador controlador, Scanner sc) throws IllegalArgumentException, NullPointerException{
        System.out.print("\n(A)locar Aluno ou (I)mprimir Grupo? ");
        String opcao = sc.nextLine().trim().toUpperCase();

        if(opcao.equals("A")) alocarAluno(controlador, sc);
        else if(opcao.equals("I")) imprimirGrupo(controlador, sc);
        else System.out.println("Opção inválida!");
    }

    /**
     * Aloca um aluno em um grupo, caso ambos existam.
     *
     * @param controlador Instância do controlador do sistema.
     * @param sc Scanner para o caso do comando precisar de mais input.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    private static void alocarAluno(Controlador controlador, Scanner sc) throws IllegalArgumentException, NullPointerException{
        System.out.print("\nMatricula: ");
        String matricula = sc.nextLine().trim();

        System.out.print("Grupo: ");
        String nomeGrupo = sc.nextLine().trim();

        int alocacao = controlador.alocaAlunoGrupo(matricula, nomeGrupo);
        if(alocacao == -1) System.out.println("Aluno não cadastrado.");
        else if(alocacao == -2) System.out.println("Grupo não cadastrado.");
        else System.out.println("ALUNO ALOCADO");
    }

    /**
     * Imprime um grupo existente.
     *
     * @param controlador Instância do controlador do sistema.
     * @param sc Scanner para o caso do comando precisar de mais input.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    private static void  imprimirGrupo(Controlador controlador, Scanner sc) throws IllegalArgumentException, NullPointerException {
        System.out.print("\nGrupo: ");
        String nomeGrupo = sc.nextLine().trim();

        String representacaoGrupo = controlador.getRepresentacaoGrupo(nomeGrupo);
        if(representacaoGrupo != null) System.out.println(representacaoGrupo);
        else System.out.println("Grupo não cadastrado.");
    }

    /**
     * Registra aluno que respondeu no quadro.
     *
     * @param controlador Instância do controlador do sistema.
     * @param sc Scanner para o caso do comando precisar de mais input.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    private static void registraAlunoRespondeu(Controlador controlador, Scanner sc) throws IllegalArgumentException, NullPointerException{
        System.out.print("\nMatricula: ");
        String matricula = sc.nextLine().trim();

        if(controlador.registraAlunoQuadro(matricula)) System.out.println("ALUNO REGISTRADO!");
        else System.out.println("Aluno não cadastrado.");
    }

    /**
     * Imprime os alunos que responderam no quadro.
     *
     * @param controlador Instância do controlador do sistema.
     */
    private static void imprimeAlunosResponderam(Controlador controlador) {
        System.out.println(controlador.getAlunosQuadro());
    }
}
