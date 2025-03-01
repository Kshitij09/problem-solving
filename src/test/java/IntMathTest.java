import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntMathTest {

    @Test
    void sqrt() {
        assertEquals(7, IntMath.sqrt(49));
        assertEquals(27, IntMath.sqrt(27 * 27));
    }

    @Test
    void ceilDiv() {
        assertEquals(3, IntMath.ceilDiv(5, 2));
    }

    @Test
    void pow() {
        assertEquals(64, IntMath.pow(2,6));
    }
}