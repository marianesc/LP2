package ufcg.ccc.domino;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ufcg.ccc.domino.Jogada.TipoJogada;
import ufcg.ccc.domino.estrategia.EstrategiaDeJogo;
import ufcg.ccc.domino.estrategia.EstrategiaInvalidaException;

/**
 * Um jogo de dominó envolvendo 2 jogadores.
 *
 */
public class Jogo {

	private Jogador jogador1;
	private Jogador jogador2;

	private Mesa mesa;
	private int rodadasJogadas;

	private boolean finalizado;
	private String vencedor;

	private int pontuacao;

	/**
	 * Fatora código comum de inicialização.
	 */
	private Jogo() {
		this.rodadasJogadas = 0;
		this.finalizado = false;
		this.vencedor = null;
		this.mesa = new Mesa();
		this.pontuacao = 0;
	}

	/**
	 * Para uso em testes apenas: cria, embaralha e distribui peças entre dois
	 * jogadores de maneira reprodutível. Sempre que o mesmo objeto Random for
	 * passado, as peças com cada jogador acabarão sendo as mesmas.
	 * 
	 * @param nomeJogador1            Id do jogador 1.
	 * @param estrategia1             Estratégia para o jogador 1.
	 * @param nomeJogador2            Id do jogador 2.
	 * @param estrategia2             Estratégia para o jogador 2.
	 * @param numPecasInicial         Número de peças a dar para cada jogador no
	 *                                início.
	 * @param geradorDeNumsAleatorios Objeto que determina as peças que cada um
	 *                                receberá após embaralhamento.
	 */
	protected Jogo(String nomeJogador1, EstrategiaDeJogo estrategia1, String nomeJogador2, EstrategiaDeJogo estrategia2,
			int numPecasInicial, Random geradorDeNumsAleatorios) {
		this();
		List<Peca> pecas = criaPecas();
		Collections.shuffle(pecas, geradorDeNumsAleatorios);

		List<Peca> maoJ1 = sorteiaMao(pecas, numPecasInicial);
		List<Peca> maoJ2 = sorteiaMao(pecas, numPecasInicial);

		this.jogador1 = new Jogador(nomeJogador1, estrategia1, maoJ1);
		this.jogador2 = new Jogador(nomeJogador2, estrategia2, maoJ2);
	}

	/**
	 * Cria, embaralha e distribui peças entre dois jogadores.
	 * 
	 * @param nomeJogador1    Id do jogador 1.
	 * @param estrategia1     Estratégia para o jogador 1.
	 * @param nomeJogador2    Id do jogador 2.
	 * @param estrategia2     Estratégia para o jogador 2.
	 * @param numPecasInicial Número de peças a dar para cada jogador no início.
	 */
	public Jogo(String nomeJogador1, EstrategiaDeJogo estrategia1, String nomeJogador2, EstrategiaDeJogo estrategia2,
			int numPecasInicial) {
		this(nomeJogador1, estrategia1, nomeJogador2, estrategia2, numPecasInicial, new Random());
	}

	/**
	 * 
	 * Para uso em testes: cria um jogo com peças escolhidas para a mão dos
	 * jogadores. Isso permite criar situações para testes de unidade mais
	 * facilmente.
	 * 
	 * @param nomeJogador1 Id do jogador 1.
	 * @param estrategia1  Estratégia para o jogador 1.
	 * @param nomeJogador2 Id do jogador 2.
	 * @param estrategia2  Estratégia para o jogador 2.
	 * @param maoJogador1  Mão do jogador 1.
	 * @param maoJogador2  Mão do jogador 2
	 */
	public Jogo(String nomeJogador1, EstrategiaDeJogo estrategia1, String nomeJogador2, EstrategiaDeJogo estrategia2,
			List<Peca> maoJogador1, List<Peca> maoJogador2) {
		this();
		this.jogador1 = new Jogador(nomeJogador1, estrategia1, new LinkedList<Peca>(maoJogador1));
		this.jogador2 = new Jogador(nomeJogador2, estrategia2, new LinkedList<Peca>(maoJogador2));
	}

	/**
	 * Sorteia peças para um jogador.
	 * 
	 * @param pecas           conjunto de peças total (será alterado)
	 * @param numPecasInicial Número de peças a retirar
	 * @return Peças retiradas.
	 */
	private List<Peca> sorteiaMao(List<Peca> pecas, int numPecasInicial) {
		List<Peca> mao = new LinkedList<Peca>();
		for (int i = 0; i < numPecasInicial; i++) {
			mao.add(pecas.remove(0));
		}
		return mao;
	}

	/**
	 * Cria o dominó.
	 * 
	 * @return Conjunto de 28 peças de 0:0 a 6:6
	 */
	private List<Peca> criaPecas() {
		List<Peca> pecas = new LinkedList<Peca>();

		for (int i = 0; i <= 6; i++) {
			for (int j = i; j <= 6; j++) {
				pecas.add(new Peca(i, j));
			}
		}

		return pecas;
	}

	/**
	 * @return Número de peças na mão do jogador 1.
	 */
	public int getNumPecasJ1() {
		return this.jogador1.getNumPecas();
	}

	/**
	 * @return Número de peças na mão do jogador 2.
	 */
	public int getNumPecasJ2() {
		return this.jogador2.getNumPecas();
	}

	/**
	 * Joga uma rodada do jogo. Ambos os jogadores fazem 1 jogada, iniciando pelo
	 * jogador 1. As exceções abaixo são necessárias para proteger o jogo de
	 * estratégias com bugs.
	 * 
	 * @throws EstrategiaInvalidaException Se a estratégia de um dos jogadores
	 *                                     decidir jogar uma peça que ele não
	 *                                     possui ou se ele tentar passar tendo
	 *                                     uma peça que encaixa.
	 * @throws JogadaInvalidaException     Se a peça escolhida por algum dos
	 *                                     jogadores não encaixar na mesa.
	 */
	public void rodada() throws EstrategiaInvalidaException, JogadaInvalidaException {
		rodadasJogadas += 1;

		Jogada jogadaJ1 = jogador1.decideJogada(mesa);
		jogaJogada(jogador1, jogadaJ1);

		if (jogador1.getNumPecas() == 0) {
			// J1 venceu
			this.finalizado = true;
			this.vencedor = this.jogador1.getNome();
			this.pontuacao = this.calculaPontuacao(this.jogador1);
			return;
		}

		Jogada jogadaJ2 = jogador2.decideJogada(mesa);
		jogaJogada(jogador2, jogadaJ2);

		if (jogador2.getNumPecas() == 0) {
			// J2 venceu
			this.finalizado = true;
			this.vencedor = this.jogador2.getNome();
			this.pontuacao = this.calculaPontuacao(this.jogador2);
			return;
		}

		// confere se há um vencedor na contagem, se não, é empate
		if (jogadaJ1.getTipo() == TipoJogada.PASSA && jogadaJ2.getTipo() == TipoJogada.PASSA) {
			this.finalizado = true;

			this.vencedor = this.confereDesempate();
		}

	}

	/**
	 * Confere se há um vencedor com o desempate através da quantidade de peças ou
	 * somatório no número das peças da mão.
	 *
	 * @return Nome do vencedor.
	 */
	private String confereDesempate() {
		if (this.jogador1.getNumPecas() < this.jogador2.getNumPecas()) {
			this.pontuacao = 1;
			return this.jogador1.getNome();
		}

		if (this.jogador2.getNumPecas() < this.jogador1.getNumPecas()) {
			this.pontuacao = 1;
			return this.jogador2.getNome();
		}

		if (this.jogador1.somatorioMao() < this.jogador2.somatorioMao()) {
			this.pontuacao = 1;
			return this.jogador1.getNome();
		}

		if (this.jogador2.somatorioMao() < this.jogador1.somatorioMao()) {
			this.pontuacao = 1;
			return this.jogador2.getNome();
		}

		return null;
	}

	/**
	 * Joga o jogo do ponto atual até o seu fim.
	 * @return Histórico de jogo.
	 * 
	 * @throws EstrategiaInvalidaException Se a estratégia de um dos jogadores
	 *                                     decidir jogar uma peça que ele não
	 *                                     possui.
	 * @throws JogadaInvalidaException     Se a peça escolhida por algum dos
	 *                                     jogadores não encaixar na mesa.
	 */
	public HistoricoDeJogo jogaJogoCompleto() throws EstrategiaInvalidaException, JogadaInvalidaException {
		HistoricoDeJogo jogado = new HistoricoDeJogo(jogador1, jogador2);
		while (!this.isFinalizado()) {
			this.rodada();
			jogado.addRodada(jogador1.getUltimaJogada(), jogador2.getUltimaJogada(), mesa);
		}
		
		if(this.isResultadoEmpate()) {
			jogado.setResultadoEmpate();
		} else {
			jogado.setVencedor(getVencedor());
			jogado.setPontuacao(this.pontuacao);
		}
		
		return jogado;
	}

	/**
	 * Faz a jogada decidida por um dos jogadores ter efeito. Quem realiza de fato
	 * as jogadas é essa classe (Jogo), e não o Jogador ou a estratégia, para evitar
	 * que estratégias com erro modifiquem indevidamente a mesa ou dados do jogador.
	 * 
	 * @param jogador Jogador jogando
	 * @param jogada  A jogada a colocar em prática
	 * @throws JogadaInvalidaException Caso ela não possa ser jogada na mesa atual
	 * @throws EstrategiaInvalidaException Caso o jogador passe tendo uma peça que encaixa
	 */
	private void jogaJogada(Jogador jogador, Jogada jogada) throws JogadaInvalidaException, EstrategiaInvalidaException {
		if (jogada.getTipo() == TipoJogada.PASSA) {
			for (Peca peca : jogador.getMao()) {
				if (peca.encaixa(this.mesa.getNumNaDireita()) || peca.encaixa(this.mesa.getNumNaEsquerda())){
					throw new EstrategiaInvalidaException("Tentou passar tendo peça que encaixa.");
				}
			}
			return;
		}

		switch (jogada.getTipo()) {
		case NA_ESQUERDA: {
			this.mesa.jogaNaEsquerda(jogada.getPeca());
			break;
		}
		case NA_DIREITA: {
			this.mesa.jogaNaDireita(jogada.getPeca());
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + jogada.getTipo());
		}

		jogador.removeDaMao(jogada.getPeca());
	}

	@Override
	public String toString() {
		String o = jogador1.toString() + "\n" + jogador2.toString() + "\nMesa: " + mesa.toString();
		return o;
	}

	/**
	 * @return Número de jogadas já jogadas.
	 */
	public int getNumRodadas() {
		return rodadasJogadas;
	}

	/**
	 * O jogo está finalizado quando um dos jogadores não tem mais peças ou não é
	 * mais possível jogar para ambos.
	 * 
	 * @return Se o jogo está encerrado
	 */
	public boolean isFinalizado() {
		return this.finalizado;
	}

	/**
	 * Informa se o jogo terminou e foi empate. Retorna false se o jogo ainda não
	 * acabou.
	 * 
	 * @return true se o jogo acabou com empate.
	 */
	public boolean isResultadoEmpate() {
		return this.isFinalizado() && this.vencedor == null;
	}

	/**
	 * @return Id do vencedor, ou null caso o jogo não esteja finalizado.
	 */
	public String getVencedor() {
		return vencedor;
	}

	/**
	 * Calcula a pontuação da vitória do jogador de acordo com a peça que ele bateu.
	 * Batida normal ou contagem pós trancamento = 1
	 * Batida de carroça = 2
	 * Batida de lá e ló = 3
	 * Batida de quadrada = 6
	 *
	 * @param jogador Jogador que venceu.
	 * @return A pontuação.
	 */
	private int calculaPontuacao(Jogador jogador) {
		Jogada ultimaJogada = jogador.getUltimaJogada();
		Peca ultimaPeca = ultimaJogada.getPeca();

		if (ultimaPeca.encaixa(this.mesa.getNumNaDireita()) && ultimaPeca.encaixa(this.mesa.getNumNaEsquerda())) {

			if (ultimaPeca.getNumDireito() == ultimaPeca.getNumEsquerdo()) return 6;

			return 3;
		}

		if (ultimaPeca.getNumDireito() == ultimaPeca.getNumEsquerdo()) return 2;

		return 1;
	}
}
