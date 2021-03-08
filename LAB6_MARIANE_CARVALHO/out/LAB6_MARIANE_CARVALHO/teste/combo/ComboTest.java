package combo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComboTest {

    private Combo combo;

    @BeforeEach
    void init() {
        this.combo = new Combo("X-burguer + suco", "X-burguer com suco de maracuja", 8, 0.20);
    }

    @Test
    void getPreco() {
        assertEquals(6.4, this.combo.getPreco());
    }
}