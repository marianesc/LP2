package ufcg.ccc.domino.estrategia;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Peca;

import java.util.List;

/**
 * Dá prioridade a jogar primeiro uma carroça que se encaixa.
 * Caso não tenha, joga a ultima peça que encaixa. Tenta primeiro no lado direito
 * e depois esquerdo, se encaixar em ambas.
 *
 * @author Mariane Carvalho
 */
public class JogaPrimeiroCarrocoes implements EstrategiaDeJogo {

    @Override
    public Jogada decideJogada(VisaoDaMesa mesa, List<Peca> mao) {
        Jogada jogada = null;

        for (Peca peca : mao) {
            if (mesa.getNumPecas() == 0 || peca.encaixa(mesa.getNumNaDireita())) {
                jogada =  new Jogada(peca, Jogada.TipoJogada.NA_DIREITA);

                if (peca.getNumEsquerdo() == peca.getNumDireito()) break;
            }
            else if (peca.encaixa(mesa.getNumNaEsquerda())) {
                jogada =  new Jogada(peca, Jogada.TipoJogada.NA_ESQUERDA);

                if (peca.getNumEsquerdo() == peca.getNumDireito()) break;
            }
        }

        if  (jogada == null) return new Jogada();

        return jogada;
    }
}
