package presentation;

import Domain.TransactionService;
import Domain.Model.Transaction;

public class TimKiemGiaoDichCommand extends TransactionCommand {
    private int maGiaoDich;

    public TimKiemGiaoDichCommand(TransactionService transactionServiceRemote, int maGiaoDich) {
        super(transactionServiceRemote);
        this.maGiaoDich = maGiaoDich;
    }

    @Override
    public void execute() {
        transactionServiceRemote.timKiemGiaoDich(maGiaoDich);
    }
}
