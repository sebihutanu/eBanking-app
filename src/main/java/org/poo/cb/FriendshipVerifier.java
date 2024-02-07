package org.poo.cb;

public class FriendshipVerifier implements TransferConditionVerifier {
        public boolean verify(TransferMoneyCommand command) {
            return command.users.get(command.email).friends.contains(command.users.get(command.friendEmail));
        }
        public String getErrorMessage(TransferMoneyCommand command) {
            return "You are not allowed to transfer money to " + command.friendEmail;
        }
}
