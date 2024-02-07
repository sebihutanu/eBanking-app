package org.poo.cb;

public class FriendshipExistenceVerifier implements AddFriendConditionVerifier {
    public boolean verify(AddFriendCommand command) {
        return !command.users.get(command.email).friends.contains(command.users.get(command.friendEmail));
    }

    public String getErrorMessage(AddFriendCommand command) {
        return "User with " + command.friendEmail + " is already a friend";
    }
}
