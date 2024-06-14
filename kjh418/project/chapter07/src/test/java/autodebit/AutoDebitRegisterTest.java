package autodebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static autodebit.CardValidity.THEFT;
import static autodebit.CardValidity.VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegisterTest {
    private AutoDebitRegister register;

    @BeforeEach
    void setUp() {
        CardNumberValidator validator = new CardNumberValidator();
        AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(validator, repository);
    }

    /*
        외부 업체에서 해당 로직을 삭제하면 테스트는 통과하지 못하고 실패하게 된다.
        ->> 대역을 써서 테스트를 진행할 수 있다.
     */
    @Test
    void validCard() {
        // 업체에서 받은 테스트용 유효한 카드 번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);
        assertEquals(VALID, result.getValidity());
    }

    @Test
    void theftCard() {
        // 업체에서 받은 도난 테스트용 카드 번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = this.register.register(req);
        assertEquals(THEFT, result.getValidity());
    }
}
