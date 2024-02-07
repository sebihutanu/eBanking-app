package org.poo.cb;
public class Cont{
    public final String currency;
    public double amount;

    Cont(String currency) {
        this.currency = currency;
    }

    public void addAmount(double amount) {
        this.amount += amount;
    }
    public void substract(double amount) {
        this.amount -= amount;
    }

    public String toString() {
        String formattedAmount = String.format("%.2f", amount);
        return "{\"currencyName\":" + "\"" + currency + "\"" + ",\"amount\":" + "\"" + formattedAmount + "\"" + "}";
    }

}