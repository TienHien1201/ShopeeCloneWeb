package presentation;

import Domain.TransactionService;

public class XoaGiaoDichCommand extends TransactionCommand {
    private int maGiaoDich;

    public XoaGiaoDichCommand(TransactionService transactionServiceRemote, int maGiaoDich) {
        super(transactionServiceRemote);
        this.maGiaoDich = maGiaoDich;
    }

    @Override
    public void execute() {
        transactionServiceRemote.xoaGiaoDich(maGiaoDich);
    }
}