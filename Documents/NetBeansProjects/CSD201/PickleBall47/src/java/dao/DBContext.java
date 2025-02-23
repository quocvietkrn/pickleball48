package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    // Phương thức để thiết lập kết nối đến cơ sở dữ liệu
    public Connection getConnection() throws SQLException {
        try {
            Connection con = null;
            // Đăng ký driver SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Chuỗi kết nối đến cơ sở dữ liệu PickleBall48
            String url = "jdbc:sqlserver://localhost:1433;databaseName=PickleBall48;user=sa;password=123";
            con = DriverManager.getConnection(url);
            return con;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Phương thức để đóng kết nối
    protected void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Phương thức main để kiểm tra kết nối
    public static void main(String[] args) throws SQLException {
        DBContext db = new DBContext();
        Connection conn = db.getConnection();
        if (conn != null) {
            System.out.println("Kết nối thành công đến cơ sở dữ liệu!");
            db.closeConnection(conn); // Đóng kết nối sau khi kiểm tra
        } else {
            System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
        }
    }
}