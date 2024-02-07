package org.poo.cb;
import java.util.HashMap;

public class ListPortfolioCommand implements Command{
    private final String email;
    private final HashMap<String, User> users;

    ListPortfolioCommand(String email, HashMap<String, User> users) {
        this.email = email;
        this.users = users;
    }

    public void execute() {
        if(!users.containsKey(email)) {
            System.out.println("User with " + email + " doesn't exist");
            return;
        }
        User userListPortfolio = users.get(email);
        String toPrintPortfolio = userListPortfolio.portfolio.toString();
        System.out.println(toPrintPortfolio);
    }
}
