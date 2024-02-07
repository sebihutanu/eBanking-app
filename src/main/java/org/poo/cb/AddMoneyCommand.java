package org.poo.cb;

import java.util.HashMap;

public class AddMoneyCommand implements Command{
    private final String email;
    private final String currency;
    private final double amount;
    private final HashMap<String, User> users;

    AddMoneyCommand(String email, String currency, double amount, HashMap<String, User> users) {
        this.email = email;
        this.currency = currency;
        this.amount = amount;
        this.users = users;
    }

    public void execute() {
        users.get(email).portfolio.accounts.get(currency).addAmount(amount);
    }
}
