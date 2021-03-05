package controle_de_alunos;

/**
 * Classe que verifica as possíveis exceções do sistema.
 *
 * @author Mariane Carvalho
 */
public class Verificador {

    /**
     * Verifica se o objeto é nulo para lançar uma exceção.
     *
     * @param o Objeto a ser verificado.
     * @param mensagem Mensagem que deve ser lançada.
     * @throws NullPointerException Caso receba alguma informação nula.
     */
    public static void verificaNull(Object o, String mensagem) throws  NullPointerException{
        if (o == null) throw new NullPointerException(mensagem);
    }

    /**
     * Verifica se a String é vazia ou só contém espaços em branco para lançar uma exceção.
     *
     * @param string String a ser verificada.
     * @param mensagem Mensagem que deve ser lançada.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public static void verificaVazia(String string, String mensagem) throws IllegalArgumentException{
        if(string.isBlank()) throw new IllegalArgumentException(mensagem);
    }

    /**
     * Verifica se a String não contém só dígitos para lançar uma exceção.
     *
     * @param string String a ser verificada.
     * @param mensagem Mensagem que deve ser lançada.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public static void verificaSomenteDigitos(String string, String mensagem) throws IllegalArgumentException{
        if(!string.matches("\\d+")) throw new IllegalArgumentException(mensagem);
    }
}
