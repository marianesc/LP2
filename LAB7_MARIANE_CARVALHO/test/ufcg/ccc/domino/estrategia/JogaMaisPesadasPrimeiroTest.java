package ufcg.ccc.domino.estrategia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Mesa;
import ufcg.ccc.domino.Peca;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JogaMaisPesadasPrimeiroTest {

    private Mesa mesa;

    @BeforeEach
    void setUp() throws Exception {
        mesa = new Mesa();
        mesa.jogaNaDireita(new Peca(1, 2));
        mesa.jogaNaEsquerda(new Peca(1, 1));
    }

    @Test
    void testPassa() {
        JogaMaisPesadasPrimeiro estrategia = new JogaMaisPesadasPrimeiro();

        Jogada j1 = estrategia.decideJogada(mesa, List.of(new Peca(3, 3), new Peca(0, 3)));

        assertEquals(Jogada.TipoJogada.PASSA, j1.getTipo());
    }

    @Test
    void testJogaMaisPesada() {
        JogaMaisPesadasPrimeiro estrategia = new JogaMaisPesadasPrimeiro();

        Jogada j1 = estrategia.decideJogada(mesa, List.of(new Peca(0, 2), new Peca(3, 2)));

        assertEquals(Jogada.TipoJogada.NA_DIREITA, j1.getTipo());
        assertEquals(3, j1.getPeca().getNumEsquerdo());
        assertEquals(2, j1.getPeca().getNumDireito());
    }

    @Test
    void testPrefereDireita() {
        JogaMaisPesadasPrimeiro estrategia = new JogaMaisPesadasPrimeiro();

        Jogada j1 = estrategia.decideJogada(mesa, List.of(new Peca(3, 3), new Peca(2, 1), new Peca(2, 6)));

        assertEquals(Jogada.TipoJogada.NA_DIREITA, j1.getTipo());
        assertEquals(2, j1.getPeca().getNumEsquerdo());
        assertEquals(6, j1.getPeca().getNumDireito());
    }

    @Test
    void testJogaNaEsquerda() {
        JogaMaisPesadasPrimeiro estrategia = new JogaMaisPesadasPrimeiro();

        Jogada j1 = estrategia.decideJogada(mesa, List.of(new Peca(3, 3), new Peca(1, 6), new Peca(1, 4)));

        assertEquals(Jogada.TipoJogada.NA_ESQUERDA, j1.getTipo());
        assertEquals(1, j1.getPeca().getNumEsquerdo());
        assertEquals(6, j1.getPeca().getNumDireito());
    }
}