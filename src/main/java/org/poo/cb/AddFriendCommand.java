package org.poo.cb;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class AddFriendCommand implements Command {
    public final String email;
    public final String friendEmail;
    public final HashMap<String, User> users;
    private final List<AddFriendConditionVerifier> verifiers = new ArrayList<>();

    AddFriendCommand(String email, String friendEmail, HashMap<String, User> users) {
        this.email = email;
        this.friendEmail = friendEmail;
        this.users = users;
        verifiers.add(new UserExistenceVerifier());
        verifiers.add(new FriendshipExistenceVerifier());
    }
    public void execute() {
        for (AddFriendConditionVerifier verifier : verifiers) {
            if (!verifier.verify(this)) {
                System.out.println(verifier.getErrorMessage(this));
                return;
            }
        }

        User user = users.get(email);
        User friendUser = users.get(friendEmail);
        user.adaugaPrieten(friendUser);
        friendUser.adaugaPrieten(user);
    }
}
