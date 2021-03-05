package agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {

    private Agenda agenda;
    private Contato contato;

    @BeforeEach
    public void criaAgenda() {
        this.agenda = new Agenda();
        this.contato = new Contato("Alan", "Turing", "(83) 99999-0000", "(83) 2101-2201",
                "", 1, 0);
        this.agenda.cadastraContato(1, this.contato);
    }

    @Test
    void testGetContato() {
        assertEquals(this.contato, this.agenda.getContato(1));
    }

    @Test
    void testCadastraContatoPosicaoVazia() {
        assertEquals(this.contato, this.agenda.getContato(1));
    }

    @Test
    void testCadastraContatoPosicaoOcupada() {
        Contato contato1 = new Contato("Isaac", "Newton", "(83) 98156-7349", "(83) 98649-0249",
                "(83) 98164-4695", 1, 1);
        this.agenda.cadastraContato(1, contato1);
        assertEquals(contato1, this.agenda.getContato(1));
    }

    @Test
    void testEqualsTrue() {
        Agenda agenda1 = new Agenda();
        agenda1.cadastraContato(1, this.contato);
        assertTrue(this.agenda.equals(agenda1));
    }

    @Test
    void testEqualsFalse() {
        Agenda agenda1 = new Agenda();
        agenda1.cadastraContato(1, this.contato);
        Contato contato1 = new Contato("Isaac", "Newton", "(83) 98156-7349", "(83) 98649-0249",
                "(83) 98164-4695", 1, 1);
        agenda1.cadastraContato(2, contato1);
        assertFalse(this.agenda.equals(agenda1));
    }

    @Test
    void testHashCodeIguais() {
        Agenda agenda1 = new Agenda();
        agenda1.cadastraContato(1, this.contato);
        assertEquals(this.agenda.hashCode(), agenda1.hashCode());
    }

    @Test
    void testHashCodeDiferentes() {
        Agenda agenda1 = new Agenda();
        agenda1.cadastraContato(1, this.contato);
        Contato contato1 = new Contato("Isaac", "Newton", "(83) 98156-7349", "(83) 98649-0249",
                "(83) 98164-4695", 1, 1);
        agenda1.cadastraContato(2, contato1);
        assertNotEquals(this.agenda.hashCode(), agenda1.hashCode());
    }
}