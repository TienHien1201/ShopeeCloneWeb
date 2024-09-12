package presentation;

import Domain.TransactionService;

public class TrungBinhThanhTienGDDatCommand extends TransactionCommand {

    public TrungBinhThanhTienGDDatCommand(TransactionService transactionServiceRemote) {
        super(transactionServiceRemote);
    }

    @Override
    public void execute() {
        transactionServiceRemote.tinhTBTTGDD();
    }
}
