package presentation;

import javax.swing.border.EmptyBorder;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Domain.TransactionService;
import Domain.Model.HouseTransaction;
import Domain.Model.LandTransaction;
import Domain.Model.Transaction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class TransactionUI extends JFrame implements Subscriber  {
    private JPanel paneRemote;
    private JTextField txtNgayGiaoDichRemote;
    private JTextField txtDonGiaRemote;
    private JTextField txtDiaChiRemote;
    private JTextField txtDienTichRemote;
    private JTextField textField, textField_1, textField_2;
    private JTable tableRemote;
    private JComboBox<String> CBloaiGDRemote, CBloaiNhaRemote, CBloaiDatRemote;
    private JMenuBar bdsMenuBarRemote;
    private JMenu chucNangMenuRemote, troGiupMenuRemote;
TransactionController transactionControllerRemote;
TransactionService transactionServiceRemote;
    public TransactionUI() {
        setTitle("Transaction App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1109, 770);
        setLocationRelativeTo(null);
        
        bdsMenuBarRemote = new JMenuBar();
        bdsMenuBarRemote.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setJMenuBar(bdsMenuBarRemote);
        
        chucNangMenuRemote = new JMenu("Chức Năng");
        chucNangMenuRemote.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        bdsMenuBarRemote.add(chucNangMenuRemote);
        
        JMenuItem gdDatMenuItemRemote = new JMenuItem("Xem Giao Dịch Đất");
        gdDatMenuItemRemote.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chucNangMenuRemote.add(gdDatMenuItemRemote);
        
        JMenuItem gdNhaMenuItemRemote = new JMenuItem("Xem Giao Dịch Nhà");
        gdNhaMenuItemRemote.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chucNangMenuRemote.add(gdNhaMenuItemRemote);
        
        troGiupMenuRemote = new JMenu("Trợ Giúp");
        troGiupMenuRemote.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        bdsMenuBarRemote.add(troGiupMenuRemote);
        
        JMenuItem troGiupMenuItemRemote = new JMenuItem("Liên Hệ Admin");
        troGiupMenuItemRemote.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        troGiupMenuRemote.add(troGiupMenuItemRemote);
        
        paneRemote = new JPanel();
        paneRemote.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(paneRemote);
        paneRemote.setLayout(null);
        
        JPanel mainPanelRemote = new JPanel();
        //mainPanelRemote.setBackground(new Color(46, 139, 87));
        mainPanelRemote.setBounds(0, 0, 1106, 770);
        paneRemote.add(mainPanelRemote);
        mainPanelRemote.setLayout(null);
        
        JPanel inputPanelRemote = new JPanel();
        inputPanelRemote.setLayout(null);
        inputPanelRemote.setBounds(93, 10, 395, 320);
        mainPanelRemote.add(inputPanelRemote);
        
        JLabel dateLabelRemote = new JLabel("Ngày Giao Dịch");
        dateLabelRemote.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        dateLabelRemote.setBounds(10, 55, 108, 24);
        inputPanelRemote.add(dateLabelRemote);
        
        txtNgayGiaoDichRemote = new JTextField();
        txtNgayGiaoDichRemote.setColumns(10);
        txtNgayGiaoDichRemote.setBounds(133, 55, 145, 24);
        inputPanelRemote.add(txtNgayGiaoDichRemote);
        
        txtDonGiaRemote = new JTextField();
        txtDonGiaRemote.setColumns(10);
        txtDonGiaRemote.setBounds(133, 99, 145, 24);
        inputPanelRemote.add(txtDonGiaRemote);
        
        JLabel donGiaLabel_1_1 = new JLabel("Đơn Giá");
        donGiaLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        donGiaLabel_1_1.setBounds(10, 99, 108, 24);
        inputPanelRemote.add(donGiaLabel_1_1);
        
        JLabel loaiDatLabel_1 = new JLabel("Loại Đất");
        loaiDatLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        loaiDatLabel_1.setBounds(10, 147, 108, 24);
        inputPanelRemote.add(loaiDatLabel_1);
        
        txtDiaChiRemote = new JTextField();
        txtDiaChiRemote.setColumns(10);
        txtDiaChiRemote.setBounds(133, 276, 145, 24);
        inputPanelRemote.add(txtDiaChiRemote);
        
        JLabel dienTichLabel_1 = new JLabel("Diện Tích");
        dienTichLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        dienTichLabel_1.setBounds(10, 276, 108, 24);
        inputPanelRemote.add(dienTichLabel_1);
        
        CBloaiDatRemote = new JComboBox<>();
        CBloaiDatRemote.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        CBloaiDatRemote.setBounds(133, 146, 145, 24);
        inputPanelRemote.add(CBloaiDatRemote);
        
        JLabel loaiGDLabel_1 = new JLabel("Loại Giao Dịch");
        loaiGDLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        loaiGDLabel_1.setBounds(10, 10, 108, 24);
        inputPanelRemote.add(loaiGDLabel_1);
        
        CBloaiGDRemote = new JComboBox<>();
        CBloaiGDRemote.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        CBloaiGDRemote.setBounds(133, 9, 145, 24);
        inputPanelRemote.add(CBloaiGDRemote);
        
        JLabel loaiNhaLabel_1 = new JLabel("Loại Nhà");
        loaiNhaLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        loaiNhaLabel_1.setBounds(10, 194, 108, 24);
        inputPanelRemote.add(loaiNhaLabel_1);
        
        CBloaiNhaRemote = new JComboBox<>();
        CBloaiNhaRemote.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        CBloaiNhaRemote.setBounds(133, 194, 145, 24);
        inputPanelRemote.add(CBloaiNhaRemote);
        
        JLabel diaChiLabel_1 = new JLabel("Địa Chỉ");
        diaChiLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        diaChiLabel_1.setBounds(10, 235, 108, 24);
        inputPanelRemote.add(diaChiLabel_1);
        
        txtDienTichRemote = new JTextField();
        txtDienTichRemote.setColumns(10);
        txtDienTichRemote.setBounds(133, 235, 145, 24);
        inputPanelRemote.add(txtDienTichRemote);
        
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(10, 340, 1086, 264);
        mainPanelRemote.add(tablePanel);
        
        // Column names
        String[] columnNames = { "Mã Giao Dịch", "Ngày giao dịch", "Đơn giá", "Loại đất", "Loại nhà", "Địa chỉ", "Diện tích", "Thành tiền" };
        
        // Initialize table model with column names and no initial rows
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        // Initialize table and set model
        tableRemote = new JTable();
        tableRemote.setModel(model);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(new JScrollPane(tableRemote), BorderLayout.CENTER); // Add JScrollPane for better view
        
        // Adjust column widths to fit table width
        tableRemote.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        // Adding new buttons and text fields
        JButton btnAdd = new JButton("Thêm");
        btnAdd.setBounds(302, 640, 85, 63);
        mainPanelRemote.add(btnAdd);
        
        JButton btnDelete = new JButton("Xóa");
        btnDelete.setBounds(492, 640, 85, 63);
        mainPanelRemote.add(btnDelete);
        
        JButton btnEdit = new JButton("Sửa");
        btnEdit.setBounds(397, 640, 85, 63);
        mainPanelRemote.add(btnEdit);
        
        JButton btnSearch = new JButton("Tìm Kiếm");
        btnSearch.setBounds(705, 640, 109, 63);
        mainPanelRemote.add(btnSearch);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               find();
            }
        });
        
        JButton btnExport = new JButton("Xuất");
        btnExport.setBounds(587, 640, 103, 63);
        mainPanelRemote.add(btnExport);
        
        JButton btnTotal = new JButton("Tính tổng");
        btnTotal.setBounds(845, 227, 109, 53);
        mainPanelRemote.add(btnTotal);
        
        JButton btnAverage = new JButton("Tính trung bình");
        btnAverage.setBounds(845, 154, 109, 52);
        mainPanelRemote.add(btnAverage);
        
        //Text của Tìm Kieem
        // textField = new JTextField();
        // textField.setBounds(559, 79, 239, 52);
        // mainPanelRemote.add(textField);
        // textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(559, 155, 239, 51);
        mainPanelRemote.add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(559, 227, 239, 52);
        mainPanelRemote.add(textField_2);
        btnAdd.addActionListener(transactionControllerRemote);
        btnDelete.addActionListener(transactionControllerRemote);
        btnEdit.addActionListener(transactionControllerRemote);
        btnSearch.addActionListener(transactionControllerRemote);
        btnExport.addActionListener(transactionControllerRemote);
        btnTotal.addActionListener(transactionControllerRemote);
        btnAverage.addActionListener(transactionControllerRemote);
        }
        public void exportTransaction() {
        
            transactionServiceRemote.xuatGiaoDichTheoThang(textField_1.getText());
            JOptionPane.showMessageDialog(this, "Xuất giao dịch thành công.");
        }
        
private Date parseDate(String dateStr) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    try {
        return (Date) formatter.parse(dateStr);
    } catch (ParseException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Định dạng ngày tháng không hợp lệ. Vui lòng nhập lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }
}
public void displayAverage(double average) {
    JOptionPane.showMessageDialog(this, "Giá trị trung bình của các giao dịch là: " + average);
}

public void displayTotal(double total) {
    JOptionPane.showMessageDialog(this, "Tổng giá trị giao dịch: " + total);
}
public  Transaction getNewTransaction() {
    Date ngayGiaoDich = parseDate(txtNgayGiaoDichRemote.getText());
    float donGia = Float.parseFloat(txtDonGiaRemote.getText());
    float dienTich = Float.parseFloat(txtDienTichRemote.getText());

    String loaiGiaoDich = (String) CBloaiGDRemote.getSelectedItem();
    if (loaiGiaoDich.equals("House")) {
        String loaiNha = (String) CBloaiNhaRemote.getSelectedItem();
        String diaChi = txtDiaChiRemote.getText();
        return new HouseTransaction(-1, ngayGiaoDich, donGia, dienTich, loaiNha, diaChi);  // Mã giao dịch tạm thời là -1
    } else if (loaiGiaoDich.equals("Land")) {
        String loaiDat = (String) CBloaiDatRemote.getSelectedItem();
        return new LandTransaction(-1, ngayGiaoDich, donGia, dienTich, loaiDat);  // Mã giao dịch tạm thời là -1
    }
    
    // Nếu loại giao dịch không hợp lệ
    return null;
}
public int getTransactionIdToDelete() {
    String idStr = JOptionPane.showInputDialog(this, "Nhập mã giao dịch để xóa:", "Xóa Giao Dịch", JOptionPane.PLAIN_MESSAGE);
    return Integer.parseInt(idStr);
}
public Transaction getUpdatedTransaction() {
    int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập mã giao dịch để cập nhật:", "Cập Nhật Giao Dịch", JOptionPane.PLAIN_MESSAGE));
    Date ngayGiaoDich = parseDate(txtNgayGiaoDichRemote.getText());
    float donGia = Float.parseFloat(txtDonGiaRemote.getText());
    float dienTich = Float.parseFloat(txtDienTichRemote.getText());

    String loaiGiaoDich = (String) CBloaiGDRemote.getSelectedItem();
    if (loaiGiaoDich.equals("House")) {
        String loaiNha = (String) CBloaiNhaRemote.getSelectedItem();
        String diaChi = txtDiaChiRemote.getText();
        return new HouseTransaction(id, ngayGiaoDich, donGia, dienTich, loaiNha, diaChi);
    } else if (loaiGiaoDich.equals("Land")) {
        String loaiDat = (String) CBloaiDatRemote.getSelectedItem();
        return new LandTransaction(id, ngayGiaoDich, donGia, dienTich, loaiDat);
    }

    // Nếu loại giao dịch không hợp lệ
    return null;
}
public String getSearchKeyword() {
    return textField.getText().trim();
}
public int getSearchTransactionId() {
    String idStr = JOptionPane.showInputDialog(this, "Nhập mã giao dịch để tìm kiếm:", "Tìm Kiếm Giao Dịch", JOptionPane.PLAIN_MESSAGE);
    return Integer.parseInt(idStr);
}

public String getMonthForAverage() {
    return JOptionPane.showInputDialog(this, "Nhập tháng để tính trung bình giao dịch (định dạng MM/yyyy):", "Tính Trung Bình Giao Dịch", JOptionPane.PLAIN_MESSAGE);
}


    private void find() {
        String idStr = JOptionPane.showInputDialog(this, "Enter the Transaction ID to find:");
      //  if (idStr != null && !idStr.isEmpty()) {
         //   try {
                int id = Integer.parseInt(idStr);
               // Student student = studentService.findStudent(id);
            //     if (student != null) {
            //         populateInputFields(student);
            //     } else {
            //         JOptionPane.showMessageDialog(this, "Student not found with ID: " + id);
            //     }
            // } catch (NumberFormatException ex) {
            //     JOptionPane.showMessageDialog(this, "Invalid input for ID. Please enter a valid number.");
            // }
       // }
    
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TransactionUI frame = new TransactionUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public void onSubscribe(Subscription subscription) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onSubscribe'");
    }
    @Override
    public void onNext(Object item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onNext'");
    }
    @Override
    public void onError(Throwable throwable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onError'");
    }
    @Override
    public void onComplete() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onComplete'");
    }

    public int getSelectedRow() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSelectedRow'");
    }
}
