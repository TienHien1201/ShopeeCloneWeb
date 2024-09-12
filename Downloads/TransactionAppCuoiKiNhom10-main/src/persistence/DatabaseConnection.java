package TransactionApp.src.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/dbquanlinhadat";
    private static final String USER = "root";
    private static final String PASSWORD = "8000";
 
    private static Connection connection;

    public static Connection getConnection() throws ClassNotFoundException {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public static void main(String[] args) {
    try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("Kết nối thành công!");
            } else {
                System.out.println("Kết nối thất bại!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Không tìm thấy driver JDBC!");
        }    
    }
}
