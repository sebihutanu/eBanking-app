package org.poo.cb;

public interface AddFriendConditionVerifier {
    boolean verify(AddFriendCommand command);
    String getErrorMessage(AddFriendCommand command);
}
