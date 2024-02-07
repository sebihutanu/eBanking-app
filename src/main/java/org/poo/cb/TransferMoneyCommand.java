package org.poo.cb;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
public class TransferMoneyCommand implements Command{
    public final String email;
    public final String friendEmail;
    public final String currency;
    public final double amount;
    public final HashMap<String, User> users;
    private final List<TransferConditionVerifier> verifiers = new ArrayList<>();
    TransferMoneyCommand(String email, String friendEmail, String currency, double amount, HashMap<String, User> users) {
        this.email = email;
        this.friendEmail = friendEmail;
        this.currency = currency;
        this.amount = amount;
        this.users = users;
        verifiers.add(new FundsAvailableVerifier());
        verifiers.add(new FriendshipVerifier());
    }
    public void execute() {
        for (TransferConditionVerifier verifier : verifiers) {
            if (!verifier.verify(this)) {
                System.out.println(verifier.getErrorMessage(this));
                return;
            }
        }

        users.get(email).portfolio.accounts.get(currency).substract(amount);
        users.get(friendEmail).portfolio.accounts.get(currency).addAmount(amount);
    }
}
