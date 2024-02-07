package org.poo.cb;

public class CommissionStrategy implements ExchangeMoneyStrategy{
    public double calculateAmount(double amount) {
        return amount * 1.01;
    }
}
