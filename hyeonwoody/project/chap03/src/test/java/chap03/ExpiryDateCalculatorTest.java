package test.java.chap03;

import main.ExpiryDateCalculator;
import main.PayData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    @Test
    @DisplayName("만원 납부하면 한달 뒤가 만료일")
    void paidTenThousandThenExpiredOneMonthLater (){

        int payAmount = 10_000;
        PayData payData = PayData.builder()
                .billingDate(LocalDate.of(2019,4,1))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2019,5,1));
        PayData payData2 = PayData.builder()
                .billingDate(LocalDate.of(2019,5,1))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2019,6, 1));
    }

    @Test
    @DisplayName("납부일과 한달 뒤 일자가 같지 않음")
    void TheExpiredDateisNotMathedWithOneMonthLater (){

        int payAmount = 10_000;
        PayData payData = PayData.builder()
                .billingDate(LocalDate.of(2019,1, 31))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2019,2,28));
    }

    @Test
    @DisplayName("첫 납부일과 만료일 일자가 다를 때 만원 납부")
    void differentBillingDateAndExpiredDate (){

        int payAmount = 10_000;
        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,30))
                .billingDate(LocalDate.of(2019,2, 28))
                .payAmount(10_000)
                .build();
        //assertExpiryDate(payData2, LocalDate.of(2019,3,30));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,5,31))
                .billingDate(LocalDate.of(2019,6, 30))
                .payAmount(10_000)
                .build();
        //assertExpiryDate(payData3, LocalDate.of(2019,7,31));

        PayData payData4 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,31))
                .billingDate(LocalDate.of(2019,2, 28))
                .payAmount(20_000)
                .build();
        assertExpiryDate(payData4, LocalDate.of(2019,4,30));
    }

    @Test
    @DisplayName("이만원 이상 납부하면 비례해서 만료일 계산")
    void uponPayingMoreThanTwoThousands (){

        int payAmount = 10_000;
        PayData payData2 = PayData.builder()
                //.firstBillingDate(LocalDate.of(2019,3,1))
                .billingDate(LocalDate.of(2019,3, 1))
                .payAmount(20_000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2019,5,1));
    }

    @Test
    @DisplayName("10개월 요금 납부 시 1년 서비스 제공")
    void provide1YearPayingMoreThan10Month (){

        int payAmount = 10_000;
        PayData payData2 = PayData.builder()
                //.firstBillingDate(LocalDate.of(2019,3,1))
                .billingDate(LocalDate.of(2019,1, 28))
                .payAmount(100_000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2020,1,28));
    }

    private void assertExpiryDate (PayData payData, LocalDate expectedExpiryDate){
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
