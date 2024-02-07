package org.poo.cb;

public class FundsAvailableVerifier implements TransferConditionVerifier{

    public boolean verify(TransferMoneyCommand command) {
        return command.users.get(command.email).portfolio.accounts.get(command.currency).amount >= command.amount;
    }
    public String getErrorMessage(TransferMoneyCommand command) {
        return "Insufficient amount in account " + command.currency + " for transfer";
    }
}
