package autodebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static autodebit.CardValidity.VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AutoDebitRegister_Fake_Test {
    private AutoDebitRegister register;
    private StubCardNumberValidator cardNumberValidator;
    private MemoryAutoDebitInfoRepository repository;

    @BeforeEach
    void setUp() {
        cardNumberValidator = new StubCardNumberValidator();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(cardNumberValidator, repository);
    }

    @DisplayName("이미 자동이체 정보가 등록되어 있을 때 기존 정보가 올바르게 바뀌는지 검사")
    @Test
    void alreadyRegistered_InfoUpdated() {
        repository.save(
                new AutoDebitInfo("user1", "111222333444", LocalDateTime.now()));

        AutoDebitReq req = new AutoDebitReq("user1", "123456789012");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("123456789012", saved.getCardNumber());
    }

    @DisplayName("자동이체 정보가 등록되어 지 않을 때 새로운 정보가 올바르게 등록되는지 검사")
    @Test
    void notYetRegistered_newInfoRegistered() {
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("1234123412341234", saved.getCardNumber());
    }
}
