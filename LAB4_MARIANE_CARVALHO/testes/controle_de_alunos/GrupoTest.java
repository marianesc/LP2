package controle_de_alunos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrupoTest {

    private Grupo grupo;

    @BeforeEach
    void criaGrupo() {
        this.grupo = new Grupo("FMCC");
    }

    @Test
    void testContrutorSemExcecao() {
        assertDoesNotThrow(() -> {
            new Grupo("Grafos");
        });
    }

    @Test
    void testConstrutorNomeNull() {
        assertThrows(NullPointerException.class, () -> {
            new Grupo(null);
        });
    }

    @Test
    void testConstrutorNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Grupo("");
        });
    }

    @Test
    void testAdicionaAlunoInexistente() {
        Aluno aluno = new Aluno("1234567", "Pedro", "CC");
        this.grupo.adicionaAluno(aluno);

        String representacaoAluno = aluno.toString();
        String representacaoGrupo = this.grupo.toString();
        assertTrue(representacaoGrupo.contains(representacaoAluno));
    }

    @Test
    void testAdicionaAlunoExistente() {
        Aluno aluno = new Aluno("1234567", "Pedro", "CC");
        this.grupo.adicionaAluno(aluno);
        Aluno aluno2 = new Aluno("1234567", "Rian", "CC");
        this.grupo.adicionaAluno(aluno2);

        String representacaoAluno = aluno.toString();
        String representacaoGrupo = this.grupo.toString();
        assertTrue(representacaoGrupo.contains(representacaoAluno));
    }

    @Test
    void testAdicionaAlunoNull() {
        assertThrows(NullPointerException.class, () -> {
            this.grupo.adicionaAluno(null);
        });
    }

    @Test
    void testToString() {
        Aluno aluno = new Aluno("1234567", "Pedro", "CC");
        this.grupo.adicionaAluno(aluno);

        String representacaoAluno = aluno.toString();
        String representacaoGrupo = this.grupo.toString();
        assertEquals("\nAlunos do grupo FMCC:\n* 1234567 - Pedro - CC\n", representacaoGrupo);
    }
}