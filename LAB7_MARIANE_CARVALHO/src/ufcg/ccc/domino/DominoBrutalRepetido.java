package ufcg.ccc.domino;

import ufcg.ccc.domino.estrategia.*;


/**
 * Exemplo de como fazer um um main com uma disputa de muuuitos jogos entre duas
 * estratégias.
 * 
 */
public class DominoBrutalRepetido {

	public static void main(String[] args) throws EstrategiaInvalidaException, JogadaInvalidaException {
		int pontuacaoJ1 = 0, pontuacaoJ2 = 0, empates = 0, normal = 0, carroca = 0, laELo = 0, quadrada = 0;

		EstrategiaDeJogo estrategia1 = new JogaPrimeiraPossivel(), estrategia2 = new JogaPrimeiraPossivel();
		
		for (int i = 0; i < 10000; i++) {
			Jogo j = new Jogo("J1", estrategia1, "J2", estrategia2, 12);
			HistoricoDeJogo historico = j.jogaJogoCompleto();
			if (historico.isEmpate()) {
				empates++;
			} else {
				int pontuacao = historico.getPontuacao();

				switch (pontuacao) {
					case 1:
						normal ++;
						break;
					case 2:
						carroca ++;
						break;
					case 3:
						laELo ++;
						break;
					case 6:
						quadrada ++;
						break;
				}
			}

			if (historico.getVencedor() == "J1") {
				pontuacaoJ1+= historico.getPontuacao();
			} else if (historico.getVencedor() == "J2") {
				pontuacaoJ2+= historico.getPontuacao();
			}
		}

		System.out.println("Jogos:\t 10000" + "\nJ1:\t" + pontuacaoJ1 + " pontos\nJ2:\t"
				+ pontuacaoJ2 + " pontos\nEmpate:\t" + empates + "\n");
		System.out.println("Quantidade de vitórias por tipo\n" +
				"Vitórias normais:\t" + normal + "\nVitórias de carroça:\t" + carroca +
				"\nVitórias de lá e ló:\t" + laELo + "\nVitórias de quadrada:\t" + quadrada);
	}

}
