package controle_de_alunos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControladorTest {

    private Controlador controlador;
    private Aluno aluno;
    private Grupo grupo;

    @BeforeEach
    void init() {
        this.controlador = new Controlador();

        this.aluno = new Aluno("123", "Pedro", "CC");
        this.controlador.cadastraAluno("123", "Pedro", "CC");

        this.controlador.criaGrupo("FMCC");
    }

    @Test
    void testCadastraAlunoInexistente() {
        assertTrue(this.controlador.cadastraAluno("12345", "João", "CC"));
        String representacaoAluno = "\nAluno: 12345 - João - CC";
        assertEquals(representacaoAluno, this.controlador.consultaAluno("12345"));
    }

    @Test
    void testCadastraAlunoExistente() {
        assertFalse(this.controlador.cadastraAluno("123", "João", "CC"));
    }

    @Test
    void testCadastraAlunoMatriculaNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controlador.cadastraAluno(null, "João", "CC");
        });
    }

    @Test
    void testCadastraAlunoMatriculaVazia() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlador.cadastraAluno("", "João", "CC");
        });
    }

    @Test
    void testCadastraAlunoMatriculaFormatoInapropriado() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlador.cadastraAluno("123a", "João", "CC");
        });
    }

    @Test
    void testCadastraAlunoNomeNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controlador.cadastraAluno("12345", null, "CC");
        });
    }

    @Test
    void testCadastraAlunoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlador.cadastraAluno("12345", "", "CC");
        });
    }

    @Test
    void testCadastraAlunoCursoNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controlador.cadastraAluno("12345", "João", null);
        });
    }

    @Test
    void testCadastraAlunoCursoVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlador.cadastraAluno("12345", "João", "");
        });
    }

    @Test
    void testConsultaAlunoExistente() {
        String representacaoEsperada = "\nAluno: " + this.aluno.toString();
        assertEquals(representacaoEsperada, this.controlador.consultaAluno("123"));
    }

    @Test
    void testConsultaAlunoInexistente() {
        assertNull(this.controlador.consultaAluno("321"));
    }

    @Test
    void testConsultaAlunoMatriculaNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controlador.consultaAluno(null);
        });
    }

    @Test
    void testConsultaAlunoMatriculaVazia() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlador.consultaAluno("");
        });
    }

    @Test
    void testConsultaAlunoMatriculaFormatoInapropriado() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlador.consultaAluno("123a");
        });
    }

    @Test
    void testCriaGrupoInexistente() {
        assertTrue(this.controlador.criaGrupo("Grafos"));
        String representacaoGrupo = "\nAlunos do grupo Grafos:\n";
        assertEquals(representacaoGrupo, this.controlador.getRepresentacaoGrupo("Grafos"));
    }

    @Test
    void testCriaGrupoExistente() {
        assertFalse(this.controlador.criaGrupo("Fmcc"));
    }

    @Test
    void testCriaGrupoNomeNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controlador.criaGrupo(null);
        });
    }

    @Test
    void testCriaGrupoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlador.criaGrupo("");
        });
    }

    @Test
    void testAlocaAlunoGrupo() {
        assertEquals(0, this.controlador.alocaAlunoGrupo("123", "fmcc"));
        String representacaoAluno = this.aluno.toString();
        String representacaoGrupo = this.controlador.getRepresentacaoGrupo("fmcc");
        assertTrue(representacaoGrupo.contains(representacaoAluno));
    }

    @Test
    void testAlocaAlunoGrupoMatriculaInexistente() {
        assertEquals(-1, this.controlador.alocaAlunoGrupo("321", "fmcc"));
    }

    @Test
    void testAlocaAlunoGrupoComGrupoInexistente() {
        assertEquals(-2, this.controlador.alocaAlunoGrupo("123", "p2"));
    }

    @Test
    void testAlocaAlunoGrupoMatriculaNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controlador.alocaAlunoGrupo(null, "FMCC");
        });
    }

    @Test
    void testAlocaAlunoMatriculaVazia() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlador.alocaAlunoGrupo("", "FMCC");
        });
    }

    @Test
    void testAlocaAlunoMatriculaFormatoInapropriado() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlador.alocaAlunoGrupo("123a", "FMCC");
        });
    }

    @Test
    void testAlocaAlunoNomeNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controlador.alocaAlunoGrupo("123", null);
        });
    }

    @Test
    void testAlocaAlunoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlador.alocaAlunoGrupo("123", "");
        });
    }

    @Test
    void testGetRepresentacaoGrupoExistente() {
        this.controlador.alocaAlunoGrupo("123", "FMCC");
        String representacaoGrupo =  "\nAlunos do grupo FMCC:\n* " + this.aluno.toString() + "\n";
        assertEquals(representacaoGrupo, this.controlador.getRepresentacaoGrupo("fmcc"));
    }

    @Test
    void testGetRepresentacaoGrupoInexistente() {
        assertNull(this.controlador.getRepresentacaoGrupo("p2"));
    }

    @Test
    void testGetRepresentacaoGrupoNomeNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controlador.getRepresentacaoGrupo(null);
        });
    }

    @Test
    void testGetRepresentacaoGrupoNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlador.getRepresentacaoGrupo("");
        });
    }

    @Test
    void testRegistraAlunoQuadroAlunoExistente() {
        assertTrue(this.controlador.registraAlunoQuadro("123"));
        String representacaoQuadro = "\nAlunos:\n1. " + this.aluno.toString() + "\n";
        assertEquals(representacaoQuadro, this.controlador.getAlunosQuadro());
    }

    @Test
    void testRegistraAlunoQuadroAlunoInexistente() {
        assertFalse(this.controlador.registraAlunoQuadro("321"));
    }

    @Test
    void testRegistraAlunoQuadroMatriculaNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controlador.registraAlunoQuadro(null);
        });
    }

    @Test
    void testRegistraAlunoQuadroMatriculaVazia() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlador.registraAlunoQuadro("");
        });
    }

    @Test
    void testRegistraAlunoQuadroMatriculaFormatoInapropriado() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlador.registraAlunoQuadro("123a");
        });
    }

    @Test
    void testGetAlunosQuadro() {
        this.controlador.registraAlunoQuadro("123");
        String representacaoQuadro = "\nAlunos:\n1. " + this.aluno.toString() + "\n";
        assertEquals(representacaoQuadro, this.controlador.getAlunosQuadro());
    }
}