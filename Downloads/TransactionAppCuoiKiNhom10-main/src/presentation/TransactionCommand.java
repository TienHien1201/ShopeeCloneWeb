package presentation;

import Domain.TransactionService;
import Domain.Model.Transaction;

public abstract class TransactionCommand {
    protected TransactionService transactionServiceRemote;

    public TransactionCommand(TransactionService transactionServiceRemote) {
        this.transactionServiceRemote = transactionServiceRemote;
    }

    

    public abstract void execute();
}