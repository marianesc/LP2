package fornecedor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import produto.Produto;

import static org.junit.jupiter.api.Assertions.*;

class FornecedorTest {

    private Fornecedor fornecedor;

    @BeforeEach
    void init() {
        this.fornecedor = new Fornecedor("Dona Alba", "donaAlba@gmail.com", "83986554239");
        this.fornecedor.adicionaProduto("X-burguer", "Sanduiche completo", 5);
        this.fornecedor.adicionaProduto("Suco", "Suco de maracuja", 3);
        this.fornecedor.adicionaProduto("Refrigerante", "Refrigerante de limao", 3);
        this.fornecedor.adicionaCombo("X-burguer + Refrigerante", "X-burguer com refri", 8, 0.20);
    }

    @Test
    void consultaProdutoExistente() {
        Produto produto = new Produto("X-burguer", "Sanduiche completo", 5);
        assertEquals(produto, this.fornecedor.consultaProduto("X-burguer", "Sanduiche completo"));
    }

    @Test
    void consultaProdutoInexistente() {
        assertNull(this.fornecedor.consultaProduto("X-frango", "Sanduiche completo"));
    }

    @Test
    void adicionaComboInexistente() {
        this.fornecedor.adicionaCombo("X-burguer + Suco", "X-burguer com suco", 8, 0.20);
        String representacaoCombo = this.fornecedor.exibeProduto("X-burguer + Suco", "X-burguer com suco");
        assertEquals("X-burguer + Suco - X-burguer com suco - R$6,40", representacaoCombo);
    }

    @Test
    void adicionaComboExistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.adicionaCombo("X-burguer + Refrigerante", "X-burguer com refri", 8, 0.20);
        });
    }

    @Test
    void editaCombo() {
        this.fornecedor.editaCombo("X-burguer + Refrigerante", "X-burguer com refri", 0.25);
        String representacaoCombo = this.fornecedor.exibeProduto("X-burguer + Refrigerante", "X-burguer com refri");
        assertEquals("X-burguer + Refrigerante - X-burguer com refri - R$6,00", representacaoCombo);
    }

    @Test
    void editaComboProdutoInexistente() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.editaCombo("X-burguer + Suco", "X-burguer com suco", 0.25);
        });
    }
}