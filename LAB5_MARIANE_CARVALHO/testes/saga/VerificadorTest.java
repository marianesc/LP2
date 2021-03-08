package saga;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerificadorTest {

    @Test
    void testVerificaStringNullTrue() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> {
            Verificador.verificaStringNullVazia(null, "Mensagem de erro");
        });

        assertEquals("Mensagem de erro", error.getMessage());
    }

    @Test
    void testVerificaStringVaziaTrue() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> {
            Verificador.verificaStringNullVazia("", "Mensagem de erro");
        });

        assertEquals("Mensagem de erro", error.getMessage());
    }

    @Test
    void testVerificaStringNullVaziaFalse() {
        assertDoesNotThrow(() -> {
            Verificador.verificaStringNullVazia("Objeto não nulo", "Mensagem de erro");
        });
    }

    @Test
    void testVerificaNullTrue() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> {
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
    void testVerificaValidadeCpfExceptionTrueTamanho() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> {
            Verificador.verificaValidadeCpfException("123456789", "Mensagem de erro");
        });

        assertEquals("Mensagem de erro", error.getMessage());
    }

    @Test
    void testVerificaValidadeCpfExceptionTrueLetra() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> {
            Verificador.verificaValidadeCpfException("123456789a1", "Mensagem de erro");
        });

        assertEquals("Mensagem de erro", error.getMessage());
    }

    @Test
    void testVerificaValidadeCpfExceptionFalse() {
        assertDoesNotThrow(() -> {
            Verificador.verificaValidadeCpfException("12345678912", "Mensagem de erro");
        });
    }

    @Test
    void testVerificaNumeroNegativoTrue() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> {
            Verificador.verificaNumeroNegativo( -1, "Mensagem de erro");
        });

        assertEquals("Mensagem de erro", error.getMessage());
    }

    @Test
    void testVerificaNumeroNegativoFalse() {
        assertDoesNotThrow(() -> {
            Verificador.verificaNumeroNegativo(1, "Mensagem de erro");
        });
    }

    @Test
    void testVerificaDataFalse() {
        assertDoesNotThrow(() -> {
            Verificador.verificaData("12/08/2019", "Mensagem de erro");
        });
    }

    @Test
    void testVerificaDataTrueDia() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> {
            Verificador.verificaData( "1a/08/2019", "Mensagem de erro");
        });

        assertEquals("Mensagem de erro", error.getMessage());
    }

    @Test
    void testVerificaDataTrueMes() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> {
            Verificador.verificaData( "12/a8/2019", "Mensagem de erro");
        });

        assertEquals("Mensagem de erro", error.getMessage());
    }

    @Test
    void testVerificaDataTrueAno() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> {
            Verificador.verificaData( "12/08/2a19", "Mensagem de erro");
        });

        assertEquals("Mensagem de erro", error.getMessage());
    }
}