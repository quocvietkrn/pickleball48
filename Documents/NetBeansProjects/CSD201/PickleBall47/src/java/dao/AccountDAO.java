package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

public class AccountDAO extends DBContext {

    // Lấy thông tin tài khoản bằng ID
    public Account getAccountByID(int accountId) {
        String sql = "SELECT [IDAccount]\n"
                + "      ,[Name]\n"
                + "      ,[UserName]\n"
                + "      ,[PassWord]\n"
                + "      ,[Gender]\n"
                + "      ,[PhoneNumber]\n"
                + "      ,[IDEmail]\n"
                + "      ,[IDFacebook]\n"
                + "      ,[Bank]\n"
                + "      ,[BankNumber]\n"
                + "      ,[Role]\n"
                + "      ,[Status]\n"
                + "      ,[Dob]\n"
                + "  FROM [dbo].[Account] WHERE [IDAccount] = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, accountId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("IDAccount");
                String name = rs.getString("Name");
                String userName = rs.getString("UserName");
                String password = rs.getString("PassWord");
                int gender = rs.getInt("Gender");
                String phoneNumber = rs.getString("PhoneNumber");
                String idEmail = rs.getString("IDEmail");
                String idFacebook = rs.getString("IDFacebook");
                String bank = rs.getString("Bank");
                String bankNumber = rs.getString("BankNumber");
                int role = rs.getInt("Role");
                int status = rs.getInt("Status");
                Date dob = rs.getDate("Dob");
                Account account = new Account(id, name, userName, password, gender, phoneNumber, idEmail, idFacebook, bank, bankNumber, role, status, dob);
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Lấy thông tin tài khoản bằng UserName
    public Account getAccountByUserName(String userName) {
        String sql = "SELECT [IDAccount]\n"
                + "      ,[Name]\n"
                + "      ,[UserName]\n"
                + "      ,[PassWord]\n"
                + "      ,[Gender]\n"
                + "      ,[PhoneNumber]\n"
                + "      ,[IDEmail]\n"
                + "      ,[IDFacebook]\n"
                + "      ,[Bank]\n"
                + "      ,[BankNumber]\n"
                + "      ,[Role]\n"
                + "      ,[Status]\n"
                + "      ,[Dob]\n"
                + "  FROM [dbo].[Account] WHERE [UserName] = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("IDAccount");
                String name = rs.getString("Name");
                String user = rs.getString("UserName");
                String password = rs.getString("PassWord");
                int gender = rs.getInt("Gender");
                String phoneNumber = rs.getString("PhoneNumber");
                String idEmail = rs.getString("IDEmail");
                String idFacebook = rs.getString("IDFacebook");
                String bank = rs.getString("Bank");
                String bankNumber = rs.getString("BankNumber");
                int role = rs.getInt("Role");
                int status = rs.getInt("Status");
                Date dob = rs.getDate("Dob");
                Account account = new Account(id, name, user, password, gender, phoneNumber, idEmail, idFacebook, bank, bankNumber, role, status, dob);
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Lấy thông tin tài khoản bằng Email
    public Account getAccountByEmail(String email) {
        String sql = "SELECT [IDAccount]\n"
                + "      ,[Name]\n"
                + "      ,[UserName]\n"
                + "      ,[PassWord]\n"
                + "      ,[Gender]\n"
                + "      ,[PhoneNumber]\n"
                + "      ,[IDEmail]\n"
                + "      ,[IDFacebook]\n"
                + "      ,[Bank]\n"
                + "      ,[BankNumber]\n"
                + "      ,[Role]\n"
                + "      ,[Status]\n"
                + "      ,[Dob]\n"
                + "  FROM [dbo].[Account] WHERE [IDEmail] = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("IDAccount");
                String name = rs.getString("Name");
                String userName = rs.getString("UserName");
                String password = rs.getString("PassWord");
                int gender = rs.getInt("Gender");
                String phoneNumber = rs.getString("PhoneNumber");
                String idEmail = rs.getString("IDEmail");
                String idFacebook = rs.getString("IDFacebook");
                String bank = rs.getString("Bank");
                String bankNumber = rs.getString("BankNumber");
                int role = rs.getInt("Role");
                int status = rs.getInt("Status");
                Date dob = rs.getDate("Dob");
                Account account = new Account(id, name, userName, password, gender, phoneNumber, idEmail, idFacebook, bank, bankNumber, role, status, dob);
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Lấy danh sách tất cả tài khoản
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT [IDAccount]\n"
                + "      ,[Name]\n"
                + "      ,[UserName]\n"
                + "      ,[PassWord]\n"
                + "      ,[Gender]\n"
                + "      ,[PhoneNumber]\n"
                + "      ,[IDEmail]\n"
                + "      ,[IDFacebook]\n"
                + "      ,[Bank]\n"
                + "      ,[BankNumber]\n"
                + "      ,[Role]\n"
                + "      ,[Status]\n"
                + "      ,[Dob]\n"
                + "  FROM [dbo].[Account]";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("IDAccount");
                String name = rs.getString("Name");
                String userName = rs.getString("UserName");
                String password = rs.getString("PassWord");
                int gender = rs.getInt("Gender");
                String phoneNumber = rs.getString("PhoneNumber");
                String idEmail = rs.getString("IDEmail");
                String idFacebook = rs.getString("IDFacebook");
                String bank = rs.getString("Bank");
                String bankNumber = rs.getString("BankNumber");
                int role = rs.getInt("Role");
                int status = rs.getInt("Status");
                Date dob = rs.getDate("Dob");
                Account account = new Account(id, name, userName, password, gender, phoneNumber, idEmail, idFacebook, bank, bankNumber, role, status, dob);
                accounts.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    // Thêm tài khoản mới
    public boolean addUser(Account account) {
        try ( Connection connection = getConnection()) {
            String query = "INSERT INTO Account (UserName, [PassWord], [Name], IDEmail, Role, Status, Gender) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, account.getUserName());
                preparedStatement.setString(2, account.getPassWord());
                preparedStatement.setString(3, account.getName());
                preparedStatement.setString(4, account.getIDEmail());
                // set default for user register have role = 1 [Customer] va status = 0;
                preparedStatement.setInt(5, 1);
                preparedStatement.setInt(6, 0);
                preparedStatement.setInt(7, account.getGender());
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0; // Returns true if at least one row was affected
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging purposes
            System.out.println("Add user unsuccessfully!");
            return false;
        }
    }

    // Cập nhật thông tin tài khoản
    public boolean updateProfile(Account user, String Date) {
        String sql = "UPDATE dbo.[Account] SET [Name] = ?, [PhoneNumber] = ?, [IDEmail] = ?, "
                + "[Gender] = ?, [Dob] = ?, [IDFacebook] = ?, [Bank] = ? , [BankNumber] = ? "
                + "WHERE [IDAccount] = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, user.getName());
            st.setString(2, user.getPhoneNumber());
            st.setString(3, user.getIDEmail());
            st.setInt(4, user.getGender());
            st.setString(5, Date);
            st.setString(6, user.getIDFacebook());
            st.setString(7, user.getBank());
            st.setString(8, user.getBankNumber());
            st.setInt(9, user.getIDAccount());
            int affectedRow = st.executeUpdate();
            if (affectedRow > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    // Kiểm tra email đã tồn tại chưa
    public boolean checkEmailExists(String email) {
        String sql = "SELECT 1 FROM [dbo].[Account] WHERE [IDEmail] = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Đổi mật khẩu
    public boolean changePassword(int accountId, String newPassword) {
        String sql = "UPDATE [dbo].[Account] SET [PassWord] = ? WHERE [IDAccount] = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, newPassword);
            st.setInt(2, accountId);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
   public void insertAccountByGoogle(Account user) {
    String sql = "INSERT INTO [dbo].[Account] ([Name], [UserName], [IDEmail], [Role]) VALUES (?, ?, ?, ?)";
    try {
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setString(1, user.getName());
        st.setString(2, user.getIDEmail()); // Dùng email làm UserName nếu cần
        st.setString(3, user.getIDEmail());
        st.setInt(4, user.getRole());
        st.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi ra console
    }
}


    public boolean checkEmail(String email) {
        String sql = "SELECT TOP (1000) [IDAccount]\n"
                + "      ,[Name]\n"
                + "      ,[UserName]\n"
                + "      ,[PassWord]\n"
                + "      ,[Gender]\n"
                + "      ,[PhoneNumber]\n"
                + "      ,[IDEmail]\n"
                + "      ,[IDFacebook]\n"
                + "      ,[Bank]\n"
                + "      ,[BankNumber]\n"
                + "      ,[Role]\n"
                + "  FROM [dbo].[Account] where IDEmail = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public int getLengthAccount() {
        int i = 0;
        String sql = "SELECT [IDAccount]\n"
                + "      ,[Name]\n"
                + "      ,[UserName]\n"
                + "      ,[PassWord]\n"
                + "      ,[Gender]\n"
                + "      ,[PhoneNumber]\n"
                + "      ,[IDEmail]\n"
                + "      ,[IDFacebook]\n"
                + "      ,[Bank]\n"
                + "      ,[BankNumber]\n"
                + "      ,[Role]\n"
                + "  FROM [dbo].[Account]";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                i++;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return i;
    }
     public static void main(String[] args) {
    // Thông tin test
    String testUsername = "abc";
    String testPassword = "testPassword";
    String testName = "Test User";
    String email = "ac@gmail.com";

    // Tạo AccountDAO
    AccountDAO userDAO = new AccountDAO();

    // Kiểm tra xem email đã tồn tại chưa
    if (!userDAO.checkEmail(email)) {
        // Nếu chưa tồn tại, thêm tài khoản mới
        Account user = new Account();
        user.setName(testName);
        user.setUserName(testUsername);
        user.setIDEmail(email);
        user.setPassWord(testPassword);
        user.setRole(1); // Set role mặc định

        userDAO.insertAccountByGoogle(user);
        System.out.println("Tài khoản mới đã được thêm vào database.");
    } else {
        System.out.println("Email đã tồn tại trong hệ thống.");
    }

    // Kiểm tra lại dữ liệu trong database
    Account checkUser = userDAO.getAccountByUserName(testUsername);
    if (checkUser != null) {
        System.out.println("Dữ liệu trong database: " + checkUser);
    } else {
        System.out.println("Không tìm thấy tài khoản với username: " + testUsername);
    }
}


    }
