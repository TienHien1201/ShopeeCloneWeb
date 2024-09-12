package TransactionApp.src.persistence;

import java.sql.Date;
import java.util.List;

import MauCuoiKi.src.domain.model.Student;
import TransactionApp.src.domain.model.Transaction;



public interface TransactionPersistenceService  {
     void themGiaoDich(Transaction transaction);
    void xoaGiaoDich(int maGD);
    void suaGiaoDich(Transaction transaction);
    Transaction timKiemGiaoDich(int maGD);
    double tinhTBTGDD();
    int tinhTongSLTungLoai();
    List<Transaction> xuatGiaoDichTheoThang(Date date);
}
