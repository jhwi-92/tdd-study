package test.java.chap02;

import main.java.Calculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculateTest {
    @Test
    void plus(){
        int result = Calculator.plus(1,2);
        assertEquals(3, result);
        assertEquals(5, Calculator.plus(4,1));
    }
}
