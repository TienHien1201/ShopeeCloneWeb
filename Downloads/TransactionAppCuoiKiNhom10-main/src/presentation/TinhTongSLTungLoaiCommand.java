package presentation;

import Domain.TransactionService;
import Domain.Model.Transaction;

public class TinhTongSLTungLoaiCommand extends TransactionCommand {

    public TinhTongSLTungLoaiCommand(TransactionService transactionServiceRemote) {
        super(transactionServiceRemote);
    }

    @Override
    public void execute() {
        transactionServiceRemote.tinhTongSLTungLoai();
    }
}