package presentation;

import Domain.TransactionService;
import Domain.Model.Transaction;

public class SuaGiaoDichCommand extends TransactionCommand {
    private Transaction transactionRemote;

    public SuaGiaoDichCommand(TransactionService transactionServiceRemote,Transaction transactionRemote) {
        super(transactionServiceRemote);
        this.transactionRemote = transactionRemote;
    }

    @Override
    public void execute() {
        transactionServiceRemote.suaGiaoDich(transactionRemote);
    }
}
