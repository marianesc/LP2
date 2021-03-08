package fornecedor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControlerFornecedorTest {

    private ControlerFornecedor controller;

    @BeforeEach
    void init() {
        this.controller = new ControlerFornecedor();
        this.controller.adicionaFornecedor("Dona Alba", "donaAlba@gmail.com", "83986554239");
        this.controller.adicionaProduto("Dona Alba", "X-burguer", "Sanduiche completo", 5);
        this.controller.adicionaProduto("Dona Alba", "Suco", "Suco de maracuja", 3);
        this.controller.adicionaProduto("Dona Alba", "Refrigerante", "Refrigerante de limao", 3);
        this.controller.adicionaCombo("Dona Alba", "X-burguer + Suco", "X-burguer com suco de maracuja", 0.20, "X-burguer - Sanduiche completo, Suco - Suco de maracuja");
    }

    @Test
    void adicionaComboFornecedorNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controller.adicionaCombo(null, "X-burguer + refrigerante",  "X-burguer com refri", 0.20, "X-burguer - Sanduiche completo, Refrigerante - Refrigerante de limao");
        });
    }

    @Test
    void adicionaComboFornecedorVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCombo("", "X-burguer + refrigerante",  "X-burguer com refri", 0.20, "X-burguer - Sanduiche completo, Refrigerante - Refrigerante de limao");
        });
    }

    @Test
    void adicionaComboNomeNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controller.adicionaCombo("Dona Alba", null,  "X-burguer com refri", 0.20, "X-burguer - Sanduiche completo, Refrigerante - Refrigerante de limao");
        });
    }

    @Test
    void adicionaComboNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCombo("Dona Alba", "",  "X-burguer com refri", 0.20, "X-burguer - Sanduiche completo, Refrigerante - Refrigerante de limao");
        });
    }

    @Test
    void adicionaComboDescricaoNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controller.adicionaCombo("Dona Alba", "X-burguer + refrigerante",  null, 0.20, "X-burguer - Sanduiche completo, Refrigerante - Refrigerante de limao");
        });
    }

    @Test
    void adicionaComboDescricaoVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCombo("Dona Alba", "X-burguer + refrigerante",  "", 0.20, "X-burguer - Sanduiche completo, Refrigerante - Refrigerante de limao");
        });
    }

    @Test
    void adicionaComboProdutosNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controller.adicionaCombo("Dona Alba", "X-burguer + refrigerante",  "X-burguer com refri", 0.20, null);
        });
    }

    @Test
    void adicionaComboProdutosVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCombo("Dona Alba", "X-burguer + refrigerante",  "X-burguer com refri", 0.20, "");
        });
    }

    @Test
    void adicionaComboFatorMaiorInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCombo("Dona Alba", "X-burguer + refrigerante",  "X-burguer com refri", 1.20, "X-burguer - Sanduiche completo, Refrigerante - Refrigerante de limao");
        });
    }

    @Test
    void adicionaComboFatorMenorInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCombo("Dona Alba", "X-burguer + refrigerante",  "X-burguer com refri", -0.20, "X-burguer - Sanduiche completo, Refrigerante - Refrigerante de limao");
        });
    }

    @Test
    void adicionaComboFatorZeroInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCombo("Dona Alba", "X-burguer + refrigerante",  "X-burguer com refri", 0, "X-burguer - Sanduiche completo, Refrigerante - Refrigerante de limao");
        });
    }

    @Test
    void adicionaComboFatorUmInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCombo("Dona Alba", "X-burguer + refrigerante",  "X-burguer com refri", 1, "X-burguer - Sanduiche completo, Refrigerante - Refrigerante de limao");
        });
    }

    @Test
    void adicionaComboFornecedorInexistente() {
        assertThrows(NullPointerException.class, () -> {
            this.controller.adicionaCombo("Joao", "X-burguer + refrigerante",  "X-burguer com refri", 0.20, "X-burguer - Sanduiche completo, Refrigerante - Refrigerante de limao");
        });
    }

    @Test
    void adicionaComboProdutoInexistente() {
        assertThrows(NullPointerException.class, () -> {
            this.controller.adicionaCombo("Dona Alba", "X-frango + refrigerante",  "X-frango com refri", 0.20, "X-frago - Sanduiche completo de frango, Refrigerante - Refrigerante de limao");
        });
    }

    @Test
    void adicionaComboComCombo() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.adicionaCombo("Dona Alba", "Refrigerante e X-burguer + Suco",  "Refri e X-burguer com suco", 0.20, "Refrigerante - Refrigerante de limao, X-burguer + Suco - X-burguer com suco de maracuja");
        });
    }

    @Test
    void adicionaCombo() {
        this.controller.adicionaCombo("Dona Alba", "X-burguer + Refrigerante", "X-burguer com refri", 0.20, "Refrigerante - Refrigerante de limao, X-burguer - Sanduiche completo");
        String representacaoProduto = this.controller.exibeProduto("X-burguer + Refrigerante", "X-burguer com refri", "Dona Alba");
        assertEquals("X-burguer + Refrigerante - X-burguer com refri - R$6,40", representacaoProduto);
    }

    @Test
    void editaComboNomeNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controller.editaCombo(null, "X-burguer com suco de maracuja", "Dona Alba", 0.25);
        });
    }

    @Test
    void editaComboNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCombo("", "X-burguer com suco de maracuja", "Dona Alba", 0.25);
        });
    }

    @Test
    void editaComboDescricaoNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controller.editaCombo("X-burguer + Suco", null, "Dona Alba", 0.25);
        });
    }

    @Test
    void editaComboDescricaoVazia() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCombo("X-burguer + Suco", "", "Dona Alba", 0.25);
        });
    }

    @Test
    void editaComboFornecedorNull() {
        assertThrows(NullPointerException.class, () -> {
            this.controller.editaCombo("X-burguer + Suco", "X-burguer com suco de maracuja", null, 0.25);
        });
    }

    @Test
    void editaComboFornecedorVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCombo("X-burguer + Suco", "X-burguer com suco de maracuja", "", 0.25);
        });
    }

    @Test
    void editaComboFornecedorInexistente() {
        assertThrows(NullPointerException.class, () -> {
            this.controller.editaCombo("X-burguer + Suco", "X-burguer com suco de maracuja", "Marcos", 0.25);
        });
    }

    @Test
    void editaComboFatorMaiorInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCombo("X-burguer + Suco", "X-burguer com suco de maracuja", "Dona Alba", 1.25);
        });
    }

    @Test
    void editaComboFatorMenorInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCombo("X-burguer + Suco", "X-burguer com suco de maracuja", "Dona Alba", -0.25);
        });
    }

    @Test
    void editaComboFatorZeroInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCombo("X-burguer + Suco", "X-burguer com suco de maracuja", "Dona Alba", 0);
        });
    }

    @Test
    void editaComboFatorUmInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controller.editaCombo("X-burguer + Suco", "X-burguer com suco de maracuja", "Dona Alba", 1);
        });
    }

    @Test
    void editaCombo() {
        this.controller.editaCombo("X-burguer + Suco", "X-burguer com suco de maracuja", "Dona Alba", 0.25);
        String representacaoProduto = this.controller.exibeProduto("X-burguer + Suco", "X-burguer com suco de maracuja", "Dona Alba");
        assertEquals("X-burguer + Suco - X-burguer com suco de maracuja - R$6,00", representacaoProduto);
    }
}