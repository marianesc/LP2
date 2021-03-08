package saga.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Cliente cliente1;
    private Cliente cliente2;
    private Cliente cliente3;

    @BeforeEach
    void init() {
        this.cliente1 = new Cliente("00023827490", "Victor Emanuel", "vitao@ccc.ufcg.edu.br", "Labarc");
        this.cliente2 = new Cliente("00023827490", "Victor Emanuel", "emanuel@ccc.ufcg.edu.br", "SPLAB");
        this.cliente3 = new Cliente("64269141198", "Ana Amari", "ana_amari@ccc.ufcg.edu.br", "SPG");
    }

    @Test
    void testHashCodeIgual() {
        assertEquals(this.cliente1.hashCode(), this.cliente2.hashCode());
    }

    @Test
    void testHashCodeDiferente() {
        assertNotEquals(this.cliente1, this.cliente3);
    }

    @Test
    void testEqualsTrue() {
        assertEquals(this.cliente1, this.cliente2);
    }

    @Test
    void testEqualsFalse() {
        assertNotEquals(this.cliente1, this.cliente3);
    }

    @Test
    void testToString() {
        String representacao = "Victor Emanuel - Labarc - vitao@ccc.ufcg.edu.br";
        assertEquals(representacao, this.cliente1.toString());
    }

    @Test
    void testContemAtributoTrue() {
        assertTrue(Cliente.contemAtributo("nome"));
    }

    @Test
    void testContemAtributoFalse() {
        assertFalse(Cliente.contemAtributo("descricao"));
    }

    @Test
    void testCompareToMaior() {
        int comparacao = this.cliente1.compareTo(this.cliente3);
        assertTrue(comparacao > 0);
    }

    @Test
    void testCompareToMenor() {
        int comparacao = this.cliente3.compareTo(this.cliente1);
        assertTrue(comparacao < 0);
    }

    @Test
    void testCompareToIgual() {
        int comparacao = this.cliente1.compareTo(this.cliente2);
        assertEquals(0, comparacao);
    }

    @Test
    void testAdicionaNaConta() {
        Compra compra1 = new Compra("Rubacao", "Feijao com arroz e queijo coalho", 17.00, "25/08/2019", "Victor Emanuel", "Dona Alba");
        this.cliente1.adicionaNaConta("Dona Alba", compra1);
        Conta conta = new Conta("Dona Alba");
        assertEquals(conta, this.cliente1.consultaConta("Dona Alba"));

        conta.adicionaCompra(compra1);
        String representacao = "Dona Alba | Rubacao - 25-08-2019";
        assertEquals(representacao, this.cliente1.consultaConta("Dona Alba").toString());
    }

    @Test
    void testConsultaConta() {
        Compra compra1 = new Compra("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00, "23/03/2019", "Victor Emanuel", "Seu Olavo");
        this.cliente1.adicionaNaConta("Seu Olavo", compra1);
        Conta conta = new Conta("Seu Olavo");
        assertEquals(conta, this.cliente1.consultaConta("Seu Olavo"));
    }

    @Test
    void testConsultaContaInexistente() {
        assertNull(this.cliente1.consultaConta("Seu Olavo"));
    }

    @Test
    void testConsultaContas() {
        Compra compra1 = new Compra("X-frango", "Hamburguer de frango com queijo e calabresa", 5.00, "23/03/2019", "Victor Emanuel", "Seu Olavo");
        Compra compra2 = new Compra("X-burguer", "Hamburguer de carne com queijo e calabresa", 4.50, "24/03/2019", "Victor Emanuel", "Marcos");
        this.cliente1.adicionaNaConta("Seu Olavo", compra1);
        this.cliente1.adicionaNaConta("Marcos", compra2);

        Conta conta1 = new Conta("Seu Olavo");
        Conta conta2 = new Conta("Marcos");
        Map<String, Conta> contas = new TreeMap<>();
        contas.put("Seu Olavo", conta1);
        contas.put("Marcos", conta2);
        List<Conta> contas1 = new ArrayList<>(contas.values());

        assertEquals(contas1, this.cliente1.consultaContas());
    }
}