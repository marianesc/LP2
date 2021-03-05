package agenda;
import java.util.Arrays;

/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100 contatos. 
 * 
 * @author nazareno
 * @author Mariane Carvalho
 */
public class Agenda {

	/**
	 * Tamanho da agenda.
	 */
	private static final int TAMANHO_AGENDA = 100;
	/**
	 * Array de contatos.
	 */
	private Contato[] contatos;

	/**
	 * Cria uma agenda.
	 */
	public Agenda() {
		this.contatos = new Contato[TAMANHO_AGENDA];
	}
	
	/**
	 * Acessa a lista de contatos mantida.
	 *
	 * @return O array de contatos.
	 */
	public Contato[] getContatos() {
		return this.contatos.clone();
	}

	/**
	 * Acessa os dados de um contato específico.
	 *
	 * @param posicao Posição do contato na agenda.
	 * @return Dados do contato. Null se não há contato na posição.
	 */
	public Contato getContato(int posicao) {
		return contatos[posicao - 1];
	}

	/**
	 * Cadastra um contato em uma posição.
	 * Um cadastro em uma posição já ocupada, o contato existente é substituído pelo novo.
	 *
	 * @param posicao Posição do contato na agenda.
	 * @param contato Contato a ser cadastrado na agenda.
	 */
	public void cadastraContato(int posicao, Contato contato) {
		contatos[posicao - 1] = contato;
	}

	/**
	 * Confere se uma agenda é igual a outra, comparando se ambas tem os mesmo contatos nas mesmas posições.
	 *
	 * @param o Objeto a ser comparado.
	 * @return Booleano se os objetos são iguais ou não.
	 */
	public boolean equals(Object o) {
		if (o == null) return false;
		if (this.getClass() != o.getClass()) return false;
		Agenda agenda = (Agenda) o;
		return Arrays.toString(this.contatos).equals(Arrays.toString(agenda.contatos));
	}

	/**
	 * Obtém o hashCode da representação em String do array contatos.
	 *
	 * @return Retorna o hashCode do array contatos.
	 */
	public int hashCode() {
		return Arrays.toString(this.contatos).hashCode();
	}

}
