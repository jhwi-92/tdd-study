package test.java.chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    @Test
    void name (){

    }

    private void assertStrength(String password, PasswordStrength expStr){
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }

    @Test
    @DisplayName("첫번째 테스트 (강함)")
    void meetsAllCriteria_Then_Strong(){

        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("두번째 테스트 (보통) : 길이만 8글자 미만")
    void meetsOtherCriteria_except_for_Length_Then_Normal(){
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("세번째 테스트 (보통) : 숫자를 포함하지 않음")
    void meetsOtherCriteria_except_for_number_Then_Normal(){
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("네번째 테스트 (유효하지 않음) : 값이 없음")
    void nullInput_Then_Invalid(){
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("다섯번째 테스트 (보통) : 대문자를 포함하지 않음")
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal(){
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("여섯번째 테스트 (약함) : 길이가 8글자 이상 조건만 충족")
    void meetsOnlyLengthCriteria_Then_Weak(){
        assertStrength("abcdefghi", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("일곱번째 테스트 (약함) : 숫자 포함 조건만 충족")
    void meetsOnlyNumberCriteria_Then_Weak(){
        assertStrength("abcdefghi", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("여덞번째 테스트 (약함) : 대문자 포함 조건만 충족")
    void meetsOnlyUppercaseCriteria_Then_Weak(){
        assertStrength("ABCDE", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("아홉번째 테스트 (약함) : 아무 조건 충족하지 않음")
    void meetsNoCriteria_Then_Weak(){
        assertStrength("abc", PasswordStrength.WEAK);
    }
}
