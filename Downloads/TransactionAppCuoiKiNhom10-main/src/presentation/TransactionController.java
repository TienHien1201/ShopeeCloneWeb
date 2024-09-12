package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import Domain.TransactionService;
import Domain.Model.Transaction;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class TransactionController implements ActionListener {

    private static TransactionController instance;
    private TransactionUI transactionUIRemote;
    private TransactionService transactionServiceRemote;
     TransactionController(TransactionUI transactionUIRemote) {
        this.transactionUIRemote = transactionUIRemote;
    }
    public static TransactionController getInstance(TransactionUI transactionUIRemote) {
        if (instance == null) {
            instance = new TransactionController(transactionUIRemote);
        }
        return instance;
    }
    

    public static TransactionController makeTransactionController() {
        return instance;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
           
            case "Thêm":
                // Gọi phương thức để thêm giao dịch mới
                this.addTransaction();
                break;
            case "Xóa":
                // Gọi phương thức để xóa giao dịch
                this.deleteTransaction();
                break;
            case "Sửa":
                // Gọi phương thức để sửa giao dịch
                this.editTransaction();
                break;
            case "Tìm Kiếm":
                // Gọi phương thức để tìm kiếm giao dịch
                this.searchTransaction();
                break;
            case "Xuất":
                // Gọi phương thức để xuất giao dịch
                this.exportTransactions();
                break;
            case "Tính tổng":
                // Gọi phương thức để tính tổng
                this.calculateTotal();
                break;
            case "Tính trung bình":
                // Gọi phương thức để tính trung bình
                this.calculateAverage();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Lệnh không hợp lệ: " + command);
                break;
        }
    }
    public void execute(TransactionCommand command) {
        command.execute();
    }
    

    public void addTransaction() {
        Transaction transaction = transactionUIRemote.getNewTransaction();
        transactionServiceRemote.themGiaoDich(transaction);
        //transactionUIRemote.updateTable(transactionServiceRemote.xuatGiaoDichTheoThang());
    }

    public void deleteTransaction() {
        int selectedRow = transactionUIRemote.getSelectedRow();
        if (selectedRow != -1) {
            transactionServiceRemote.xoaGiaoDich(selectedRow);
           // transactionUIRemote.updateTable(transactionServiceRemote.getAllTransactions());
        } else {
            JOptionPane.showMessageDialog(transactionUIRemote, "Vui lòng chọn giao dịch cần xóa.");
        }
    }

    public void editTransaction() {
        int selectedRow = transactionUIRemote.getSelectedRow();
        if (selectedRow != -1) {
            Transaction updatedTransaction = transactionUIRemote.getUpdatedTransaction();
            transactionServiceRemote.suaGiaoDich(updatedTransaction);
           // transactionUIRemote.updateTable(transactionServiceRemote.getAllTransactions());
        } else {
            JOptionPane.showMessageDialog(transactionUIRemote, "Vui lòng chọn giao dịch cần sửa.");
        }
    }
   
    public void searchTransaction() {
        String keyword = transactionUIRemote.getSearchKeyword();
        List<Transaction> result = transactionServiceRemote.timKiemGiaoDich(Integer.parseInt(keyword));
       // transactionUIRemote.updateTable(result);
    }

    public void exportTransactions() {
        transactionUIRemote.exportTransaction();
        // transactionServiceRemote.xuatGiaoDichTheoThang(textField_1.text());
        // JOptionPane.showMessageDialog(transactionUIRemote, "Xuất giao dịch thành công.");
    }

    public void calculateTotal() {
        double total = transactionServiceRemote.tinhTongSLTungLoai();
        transactionUIRemote.displayTotal(total);
    }

    public void calculateAverage() {
        double average = transactionServiceRemote.tinhTBTTGDD();
        transactionUIRemote.displayAverage(average);
    }
}

