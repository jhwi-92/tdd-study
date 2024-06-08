package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = payData.getPayAmount() / 10_000;

        if (payData.getFirstBillingDate() != null) {

            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();

            if (dayOfFirstBilling != candidateExp.getDayOfMonth()) {
                final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();

                if (dayLenOfCandiMon < dayOfFirstBilling) {
                    return candidateExp.withDayOfMonth(dayLenOfCandiMon);
                }
                return candidateExp.withDayOfMonth(dayOfFirstBilling);
            }

//            if (payData.getFirstBillingDate().equals(LocalDate.of(2019, 1, 31))) {
//                return LocalDate.of(2019, 3, 31);
//            }
        }


        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
