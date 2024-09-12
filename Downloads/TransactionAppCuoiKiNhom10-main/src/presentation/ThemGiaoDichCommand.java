package presentation;

import Domain.TransactionService;
import Domain.Model.Transaction;

public class ThemGiaoDichCommand extends TransactionCommand {
    private Transaction transactionRemote;

    public ThemGiaoDichCommand(TransactionService transactionServiceRemote,Transaction transactionRemote) {
        super(transactionServiceRemote);
        this.transactionRemote = transactionRemote;
    }

    @Override
    public void execute() {
        transactionServiceRemote.themGiaoDich(transactionRemote);
    }
}