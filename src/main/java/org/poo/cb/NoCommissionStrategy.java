package org.poo.cb;

public class NoCommissionStrategy implements ExchangeMoneyStrategy{
    public double calculateAmount(double amount) {
        return amount;
    }
}
