package utilidades;

/**
 * Classe que testa as possíveis exceções do sistema.
 *
 * @author Aluno de período anterior
 * @author Mariane Carvalho
 */
public class Util {
	/**
	 * Testa se o objeto é nulo para lançar uma exceção.
	 *
	 * @param o Objeto à ser testado.
	 * @param mensagem Mensagem que deve ser lançada.
	 */
	public static void testaNull(Object o, String mensagem) {
		if (o == null) {
			throw new NullPointerException(mensagem);
		}
	}

	/**
	 * Testa se a String é vazia para lançar uma exceção.
	 *
	 * @param s String à ser testada.
	 * @param mensagem Mensagem que deve ser lançada.
	 */
	public static void testaVazio(String s, String mensagem) {
		if (s.equals("")) {
			throw new IllegalArgumentException(mensagem);
		}
	}
}
