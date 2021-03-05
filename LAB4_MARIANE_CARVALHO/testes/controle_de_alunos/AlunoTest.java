package controle_de_alunos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    private Aluno aluno;

    @BeforeEach
    void criaAluno() {
        this.aluno = new Aluno("1234567", "João", "CC");
    }

    @Test
    void testContrutorSemExcecao() {
        assertDoesNotThrow(() -> {
            new Aluno("7654321", "Pedro", "CC");
        });
    }

    @Test
    void testContrutorMatriculaNull() {
        assertThrows(NullPointerException.class, () -> {
            new Aluno(null, "Pedro", "CC");
        });
    }

    @Test
    void testConstrutorNomeNull() {
        assertThrows(NullPointerException.class, () -> {
            new Aluno("7654321", null, "CC");
        });
    }

    @Test
    void testConstrutorCursoNull() {
        assertThrows(NullPointerException.class, () -> {
            new Aluno("7654321", "Pedro", null);
        });
    }

    @Test
    void testConstrutorMatriculaVazia() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno("", "Pedro", "CC");
        });
    }

    @Test
    void testConstrutorNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno("7654321", "", "CC");
        });
    }

    @Test
    void testConstrutorCursoVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno("7654321", "Pedro", "");
        });
    }

    @Test
    void testConstrutorMatriculaFormatoInapropriado() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno("12a345", "Pedro", "CC");
        });
    }

    @Test
    void testHashCodeIguais() {
        Aluno aluno2 = new Aluno("1234567", "Pedro", "CC");

        assertEquals(aluno2.hashCode(), this.aluno.hashCode());
    }

    @Test
    void testHashCodeDiferentes() {
        Aluno aluno2 = new Aluno("7654321", "Pedro", "CC");

        assertNotEquals(aluno2.hashCode(), this.aluno.hashCode());
    }

    @Test
    void testEqualsTrue() {
        Aluno aluno2 = new Aluno("1234567", "Pedro", "CC");

        assertEquals(aluno2, this.aluno);
    }

    @Test
    void testEqualsFalse() {
        Aluno aluno2 = new Aluno("7654321", "Pedro", "CC");

        assertNotEquals(aluno2, this.aluno);
    }

    @Test
    void testToString() {
        assertEquals("1234567 - João - CC", this.aluno.toString());
    }
}