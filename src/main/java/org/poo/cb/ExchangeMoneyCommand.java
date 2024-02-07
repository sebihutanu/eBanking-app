package org.poo.cb;

import java.util.HashMap;

public class ExchangeMoneyCommand implements Command{
    private final String email;
    private final String sourceCurrency;
    private final String destinationCurrency;
    private final double amount;
    private final double[][] exchangeRatesMatrix;
    private ExchangeMoneyStrategy exchangeMoneyStrategy;
    private final HashMap<String, User> users;

    ExchangeMoneyCommand(String email, String sourceCurrency, String destinationCurrency, double amount, HashMap<String, User> users, double[][] exchangeRatesMatrix) {
        this.email = email;
        this.sourceCurrency = sourceCurrency;
        this.destinationCurrency = destinationCurrency;
        this.amount = amount;
        this.users = users;
        this.exchangeRatesMatrix = exchangeRatesMatrix;
    }
    public void execute() {
        double exchangeSold = exchangeFunction(amount, exchangeRatesMatrix, mapCurrency(destinationCurrency), mapCurrency(sourceCurrency));

        exchangeMoneyStrategy = (1.0/2.0)* users.get(email).portfolio.accounts.get(sourceCurrency).amount >= exchangeSold
                || users.get(email).premium ? new NoCommissionStrategy() : new CommissionStrategy();
        double transferedAmount = exchangeMoneyStrategy.calculateAmount(exchangeSold);
        if(users.get(email).portfolio.accounts.get(sourceCurrency).amount < transferedAmount) {
            System.out.println("Insufficient amount in account " + sourceCurrency + " for exchange");
            return;
        }
        users.get(email).portfolio.accounts.get(sourceCurrency).substract(transferedAmount);
        users.get(email).portfolio.accounts.get(destinationCurrency).addAmount(amount);
    }
    public static double exchangeFunction(double amount, double[][] exchangeRatesMatrix, int sourceCurrency, int destinationCurrency) {
        return amount * exchangeRatesMatrix[sourceCurrency - 1][destinationCurrency - 1];
    }
    public static int mapCurrency(String currency) {
        return switch (currency) {
            case "EUR" -> 1;
            case "GBP" -> 2;
            case "JPY" -> 3;
            case "CAD" -> 4;
            case "USD" -> 5;
            default -> 0;
        };
    }
}
