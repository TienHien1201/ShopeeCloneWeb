package TransactionApp.src.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import TransactionApp.src.domain.model.HouseTransaction;
import TransactionApp.src.domain.model.LandTransaction;
import TransactionApp.src.domain.model.Transaction;

public class TransactionPersistenceServiceImpl implements TransactionPersistenceService {

    private Connection connection;

    public TransactionPersistenceServiceImpl() throws ClassNotFoundException {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void themGiaoDich(Transaction transaction) {
        String sql = "INSERT INTO GiaoDich (NgayGiaoDich, DonGia, LoaiDat, LoaiNha, DiaChi, DienTich, ThanhTien) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(transaction.getNgay().getTime()));
            pstmt.setDouble(2, transaction.getDonGia());
            if (transaction instanceof LandTransaction) {
                pstmt.setString(3, ((LandTransaction) transaction).getLoaiDat());
                pstmt.setNull(4, Types.VARCHAR);
                pstmt.setNull(5, Types.VARCHAR);
            } else if (transaction instanceof HouseTransaction) {
                pstmt.setNull(3, Types.VARCHAR);
                pstmt.setString(4, ((HouseTransaction) transaction).getLoaiNha());
                pstmt.setString(5, ((HouseTransaction) transaction).getDiaChi());
            }
            pstmt.setDouble(6, transaction.getDienTich());
            pstmt.setDouble(7, transaction.thanhTien());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void xoaGiaoDich(int maGD) {
        String sql = "DELETE FROM GiaoDich WHERE MaGD = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, maGD);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void suaGiaoDich(Transaction transaction) {
        String sql = "UPDATE GiaoDich SET NgayGiaoDich = ?, DonGia = ?, LoaiDat = ?, LoaiNha = ?, DiaChi = ?, DienTich = ?, ThanhTien = ? WHERE MaGD = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(transaction.getNgay().getTime()));
            pstmt.setDouble(2, transaction.getDonGia());
            if (transaction instanceof LandTransaction) {
                pstmt.setString(3, ((LandTransaction) transaction).getLoaiDat());
                pstmt.setNull(4, Types.VARCHAR);
                pstmt.setNull(5, Types.VARCHAR);
            } else if (transaction instanceof HouseTransaction) {
                pstmt.setNull(3, Types.VARCHAR);
                pstmt.setString(4, ((HouseTransaction) transaction).getLoaiNha());
                pstmt.setString(5, ((HouseTransaction) transaction).getDiaChi());
            }
            pstmt.setDouble(6, transaction.getDienTich());
            pstmt.setDouble(7, transaction.thanhTien());
            pstmt.setInt(8, transaction.getMaGiaoDich());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transaction timKiemGiaoDich(int maGD) {
        String sql = "SELECT * FROM GiaoDich WHERE MaGD = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, maGD);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Date ngay = rs.getDate("NgayGiaoDich");
                double donGia = rs.getDouble("DonGia");
                double dienTich = rs.getDouble("DienTich");
                if (rs.getString("LoaiDat") != null) {
                    LandTransaction transaction = new LandTransaction(maGD, ngay, donGia, dienTich, rs.getString("LoaiDat"));
                    return transaction;
                } else {
                    HouseTransaction transaction = new HouseTransaction(maGD, ngay, donGia, dienTich, rs.getString("LoaiNha"), rs.getString("DiaChi"));
                    return transaction;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public double tinhTBTGDD() {
        String sql = "SELECT AVG(ThanhTien) FROM GiaoDich WHERE LoaiDat IS NOT NULL";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int tinhTongSLTungLoai() {
        String sql = "SELECT COUNT(*) FROM GiaoDich";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Transaction> xuatGiaoDichTheoThang(int month) {
        String sql = "SELECT * FROM GiaoDich WHERE MONTH(NgayGiaoDich) = ?";
        List<Transaction> transactions = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, month);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int maGiaoDich = rs.getInt("MaGD");
                Date ngay = rs.getDate("NgayGiaoDich");
                double donGia = rs.getDouble("DonGia");
                double dienTich = rs.getDouble("DienTich");
                if (rs.getString("LoaiDat") != null) {
                    LandTransaction transaction = new LandTransaction(maGiaoDich, ngay, donGia, dienTich, rs.getString("LoaiDat"));
                    transactions.add(transaction);
                } else {
                    HouseTransaction transaction = new HouseTransaction(maGiaoDich, ngay, donGia, dienTich, rs.getString("LoaiNha"), rs.getString("DiaChi"));
                    transactions.add(transaction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
