package autodebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static autodebit.CardValidity.INVALID;
import static autodebit.CardValidity.VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegister_Stub_Test {
    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private StubAutoDebitInfoRepository stubRepository;

    @BeforeEach
    void setUp() {
        stubValidator = new StubCardNumberValidator();
        stubRepository = new StubAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, stubRepository);
    }

        @DisplayName("실제 객체 대신 stubCardNumberValidator 사용하여 카드번호 유효성 검사")
        @Test
        void invalidCard() {
            stubValidator.setInvalidNo("111122223333");

            AutoDebitReq req = new AutoDebitReq("user1", "111122223333");
            RegisterResult result = this.register.register(req);

            assertEquals(INVALID, result.getValidity());
        }

        @DisplayName("도난 카드번호에 대한 자동이체 기능 테스트")
        @Test
        void theftCard() {
            stubValidator.setTheftNo("1234567890123456");

            AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
            RegisterResult result = this.register.register(req);

            assertEquals(CardValidity.THEFT, result.getValidity());
    }

    @Test
    void validCard() {
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);
        assertEquals(VALID, result.getValidity());
    }
}
