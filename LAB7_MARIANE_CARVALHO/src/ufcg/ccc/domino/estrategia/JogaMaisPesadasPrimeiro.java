package ufcg.ccc.domino.estrategia;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Peca;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Joga sempre a pesa de maior somatório que encaixa. Tenta primeiro no lado direito
 * e depois esquerdo, se encaixar em ambas.
 *
 * @author Mariane Carvalho
 */
public class JogaMaisPesadasPrimeiro implements EstrategiaDeJogo {

    @Override
    public Jogada decideJogada(VisaoDaMesa mesa, List<Peca> mao) {
        Peca[] maoCopia = new Peca[mao.size()];
        mao.toArray(maoCopia);
        Arrays.sort(maoCopia, new PecaComparator());

        if (mesa.getNumPecas() == 0) {
            return new Jogada(maoCopia[0], Jogada.TipoJogada.NA_DIREITA);
        }

        for (Peca peca : maoCopia) {
            if (peca.encaixa(mesa.getNumNaDireita())) {
                return new Jogada(peca, Jogada.TipoJogada.NA_DIREITA);
            }
            if (peca.encaixa(mesa.getNumNaEsquerda())) {
                return new Jogada(peca, Jogada.TipoJogada.NA_ESQUERDA);
            }
        }

        return new Jogada();
    }

    private class PecaComparator implements Comparator<Peca> {

        public int compare(Peca peca1, Peca peca2) {
            Integer somatorio1 = peca1.getNumDireito() + peca1.getNumEsquerdo();
            Integer somatorio2 = peca2.getNumDireito() + peca2.getNumEsquerdo();

            return somatorio2.compareTo(somatorio1);
        }

    }
}
