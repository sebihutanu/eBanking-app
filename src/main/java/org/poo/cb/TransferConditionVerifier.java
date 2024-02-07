package org.poo.cb;

public interface TransferConditionVerifier {
    boolean verify(TransferMoneyCommand command);
    String getErrorMessage(TransferMoneyCommand command);
}
