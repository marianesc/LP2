package controle_de_alunos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerificadorTest {

    @Test
    void testVerificaNullTrue() {
        NullPointerException error = assertThrows(NullPointerException.class, () -> {
            Verificador.verificaNull(null, "Mensagem de erro");
        });

        assertEquals("Mensagem de erro", error.getMessage());
    }

    @Test
    void testVerificaNullFalse() {
        assertDoesNotThrow(() -> {
            Verificador.verificaNull("Objeto não nulo", "Mensagem de erro");
        });
    }

    @Test
    void testVerificaVaziaTrue() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> {
            Verificador.verificaVazia("", "Mensagem de erro");
        });

        assertEquals("Mensagem de erro", error.getMessage());
    }

    @Test
    void testVerificaVaziaFalse() {
        assertDoesNotThrow(() -> {
            Verificador.verificaVazia("String não vazia", "Mensagem de erro");
        });
    }

    @Test
    void testVerificaSomenteDigitosTrue() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> {
            Verificador.verificaSomenteDigitos("semDigit0s", "Mensagem de erro");
        });

        assertEquals("Mensagem de erro", error.getMessage());
    }

    @Test
    void testVerificaSomenteDigitosFalse() {
        assertDoesNotThrow(() -> {
            Verificador.verificaSomenteDigitos("123456789", "Mensagem de erro");
        });
    }
}