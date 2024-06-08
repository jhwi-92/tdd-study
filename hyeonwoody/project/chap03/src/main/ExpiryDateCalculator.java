package main;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {



    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonth = payData.getPayAmount() == 100_000 ? 12 : payData.getPayAmount() / 10_000;
        if (payData.getFirstBillingDate() != null){
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonth);
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            if ( dayOfFirstBilling != candidateExp.getDayOfMonth()){
                final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
                if (dayLenOfCandiMon <
                        payData.getFirstBillingDate().getDayOfMonth()){
                    return candidateExp.withDayOfMonth(dayLenOfCandiMon);
                }
                return candidateExp.withDayOfMonth(dayOfFirstBilling);
            }
            else {
                return candidateExp;
            }
        }

        return payData.getBillingDate().plusMonths(addedMonth);

    }
}
