package org.poo.cb;

public class UserExistenceVerifier implements AddFriendConditionVerifier{
    public boolean verify(AddFriendCommand command) {
        return command.users.containsKey(command.email) && command.users.containsKey(command.friendEmail);
    }

    public String getErrorMessage(AddFriendCommand command) {
        return (!command.users.containsKey(command.email)) ? "User with " + command.email + " does not exist" : "User with " + command.friendEmail + " does not exist";
    }
}
