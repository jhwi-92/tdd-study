package main;

import java.time.LocalDate;

public class PayData{
    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private LocalDate paidDate;
    private Integer payAmount;
    public PayData(){

    }

    public static Builder builder() {
        return new Builder();
    }

    public PayData (LocalDate firstBillingDate, LocalDate billingDate, int payAmount){
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.payAmount = payAmount;
    }

    public int getPayAmount(){
        return this.payAmount;
    }
    public LocalDate getFirstBillingDate(){
        return this.firstBillingDate;
    }

    public LocalDate getBillingDate(){
        return this.billingDate;
    }

    public static class Builder {
        private PayData data = new PayData();

        public Builder firstBillingDate (LocalDate firstBillingDate){
            data.firstBillingDate = firstBillingDate;
            return this;
        }

        public Builder billingDate(LocalDate billingDate){
            data.billingDate = billingDate;
            return this;
        }
        public Builder payAmount(int payAMount){
            data.payAmount = payAMount;
            return this;
        }
        public PayData build(){
            return data;
        }
    }
}