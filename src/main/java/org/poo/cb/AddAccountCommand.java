package org.poo.cb;

import java.util.HashMap;

public class AddAccountCommand implements Command{
    private final String email;
    private final String currency;
    private final HashMap<String, User> users;

    AddAccountCommand(String email, String currency, HashMap<String, User> users) {
        this.email = email;
        this.currency = currency;
        this.users = users;
    }

    public void execute() {
        if(users.get(email).portfolio.accounts.containsKey(currency)) {
            System.out.println("Account with " + currency + " already exists");
            return;
        }
        Cont cont = new Cont(currency);
        users.get(email).portfolio.accounts.put(currency, cont);
    }
}
