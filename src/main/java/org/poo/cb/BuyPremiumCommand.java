package org.poo.cb;
import java.util.HashMap;

public class BuyPremiumCommand implements Command {
    private final String email;
    private final HashMap<String, User> users;

    BuyPremiumCommand(String email, HashMap<String, User> users) {
        this.email = email;
        this.users = users;
    }

    public void execute() {
        users.get(email).premium = true;
    }
}
