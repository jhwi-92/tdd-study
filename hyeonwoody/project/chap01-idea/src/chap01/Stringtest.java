package chap01;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class Stringtest {
    @Test
    void subString() {
        String str = "abcde";
        assertEquals("cd", str.substring(2,4));
    }
}
