package saga;

/**
 * Classe que verifica as possíveis exceções do sistema.
 *
 * @author Mariane Carvalho
 */
public class Verificador {

    /**
     * Verifica se a String é nula ou vazia para lançar uma exceção.
     *
     * @param string String a ser verificada.
     * @param mensagem Mensagem que deve ser lançada.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public static void verificaStringNullVazia(String string, String mensagem) throws IllegalArgumentException {
        if(string == null || string.isBlank()) throw new IllegalArgumentException(mensagem);
    }

    /**
     * Verifica se o objeto é nulo para lançar uma exceção.
     *
     * @param o Objeto a ser verificado.
     * @param mensagem Mensagem que deve ser lançada.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public static void verificaNull(Object o, String mensagem) throws IllegalArgumentException {
        if(o == null) throw new IllegalArgumentException(mensagem);
    }

    /**
     * Verifica se o formato do cpf não é válido para lançar uma exceção.
     *
     * @param string cpf a ser verificado.
     * @param mensagem Mensagem que deve ser lançada.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public static void verificaValidadeCpfException(String string, String mensagem) throws IllegalArgumentException{
        if(!string.matches("\\d+") || string.length() != 11 ) throw new IllegalArgumentException(mensagem);
    }

    /**
     * Verifica se o valor é negativo para lançar uma exceção.
     *
     * @param valor Valor a ser verificado.
     * @param mensagem Mensagem que deve ser lançada.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public static void verificaNumeroNegativo(double valor, String mensagem) throws IllegalArgumentException{
        if(valor < 0) throw new IllegalArgumentException(mensagem);
    }

    /**
     * Verifica se o formato da data não é valido para lançar uma exceção.
     *
     * @param data Data a ser verificada.
     * @param mensagem Mensagem que deve ser lançada.
     * @throws IllegalArgumentException Caso receba alguma informação inapropriada.
     */
    public static void verificaData(String data, String mensagem) throws IllegalArgumentException{
        String[] partes = data.split("/");
        if(!partes[0].matches("\\d+") || partes[0].length() > 2) throw new IllegalArgumentException(mensagem);
        else if(!partes[1].matches("\\d+") || partes[1].length() > 2) throw new IllegalArgumentException(mensagem);
        else if(!partes[2].matches("\\d+") || partes[2].length() > 4) throw new IllegalArgumentException(mensagem);
    }
}
