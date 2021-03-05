package agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContatoTest {

    private Contato contato;
    private Contato contato1;
    private Contato contato2;
    private Contato contato3;
    private Contato contato4;

    @BeforeEach
    public void criaContato() {
        this.contato = new Contato("Alan", "Turing", "(83) 99999-0000", "(83) 2101-2201",
                "", 1, 0);

        this.contato1 = new Contato("Alan", "Turing", "(81) 98123-3232", "",
                "(83) 98301-9238", 0, 1);

        this.contato2 = new Contato("Ada", "Lovelace", "(81) 98176-9490", "",
                "(83) 98634-3789", 1, 2);

        this.contato3 = new Contato("Isaac", "Newton", "(83) 98156-7349", "(83) 98649-0249",
                "(83) 98164-4695", 1, 1);

        this.contato4 = new Contato("Ada", "Lovelace", "(81) 98176-9490", "",
                "(83) 98634-3789", 2, 1);
    }

    @Test
    void testEqualsTrue() {
        assertTrue(this.contato.equals(this.contato1));
    }

    @Test
    void testEqualsFalse() {
        assertFalse(this.contato.equals(this.contato2));
    }

    @Test
    void testGetNomeCompleto() {
        assertEquals("Alan Turing", this.contato.getNomeCompleto());
    }

    @Test
    void testToStringComZap() {
        assertEquals("Alan Turing\n(81) 98123-3232 (zap)\n(83) 98301-9238\n", this.contato1.toString());
    }

    @Test
    void testToStringComPrioritario() {
        assertEquals("Alan Turing\n(83) 99999-0000 (prioritário)\n(83) 2101-2201\n", this.contato.toString());
    }

    @Test
    void testToStringComAmbos() {
        assertEquals("Isaac Newton\n(83) 98156-7349 (prioritário) (zap)\n(83) 98649-0249\n(83) 98164-4695\n", this.contato3.toString());
    }

    @Test
    void testGetPrioritarioExistente() {
        assertEquals("(81) 98176-9490", this.contato2.getPrioritario());
    }

    @Test
    void testGetPrioritarioNaoDefiniu() {
        assertEquals("Não tem", this.contato1.getPrioritario());
    }

    @Test
    void testGetPrioritarioTelefoneVazio() {
        assertEquals("Não tem", this.contato4.getPrioritario());
    }

    @Test
    void testGetWhatsappExistente() {
        assertEquals("(81) 98123-3232", this.contato1.getWhatsapp());
    }

    @Test
    void testGetWhatsappNaoDefiniu() {
        assertEquals("Não tem", this.contato.getWhatsapp());
    }

    @Test
    void testGetWhatsappTelefoneVazio() {
        assertEquals("Não tem", this.contato2.getWhatsapp());
    }

    @Test
    void testHashCodeIguais() {
        assertEquals(this.contato.hashCode(), this.contato1.hashCode());
    }

    @Test
    void testHashCodeDiferentes() {
        assertNotEquals(this.contato.hashCode(), this.contato2.hashCode());
    }
}