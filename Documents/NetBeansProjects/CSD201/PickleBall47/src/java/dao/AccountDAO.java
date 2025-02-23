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
    public boolean addAccount(Account account) {
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([Name]\n"
                + "           ,[UserName]\n"
                + "           ,[PassWord]\n"
                + "           ,[Gender]\n"
                + "           ,[PhoneNumber]\n"
                + "           ,[IDEmail]\n"
                + "           ,[IDFacebook]\n"
                + "           ,[Bank]\n"
                + "           ,[BankNumber]\n"
                + "           ,[Role]\n"
                + "           ,[Status]\n"
                + "           ,[Dob])\n"
                + "     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, account.getName());
            st.setString(2, account.getUserName());
            st.setString(3, account.getPassWord());
            st.setInt(4, account.getGender());
            st.setString(5, account.getPhoneNumber());
            st.setString(6, account.getIDEmail());
            st.setString(7, account.getIDFacebook());
            st.setString(8, account.getBank());
            st.setString(9, account.getBankNumber());
            st.setInt(10, account.getRole());
            st.setInt(11, account.getStatus());
            st.setDate(12, new java.sql.Date(account.getDob().getTime()));
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Cập nhật thông tin tài khoản
    public boolean updateAccount(Account account) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [Name] = ?\n"
                + "      ,[UserName] = ?\n"
                + "      ,[PassWord] = ?\n"
                + "      ,[Gender] = ?\n"
                + "      ,[PhoneNumber] = ?\n"
                + "      ,[IDEmail] = ?\n"
                + "      ,[IDFacebook] = ?\n"
                + "      ,[Bank] = ?\n"
                + "      ,[BankNumber] = ?\n"
                + "      ,[Role] = ?\n"
                + "      ,[Status] = ?\n"
                + "      ,[Dob] = ?\n"
                + " WHERE [IDAccount] = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, account.getName());
            st.setString(2, account.getUserName());
            st.setString(3, account.getPassWord());
            st.setInt(4, account.getGender());
            st.setString(5, account.getPhoneNumber());
            st.setString(6, account.getIDEmail());
            st.setString(7, account.getIDFacebook());
            st.setString(8, account.getBank());
            st.setString(9, account.getBankNumber());
            st.setInt(10, account.getRole());
            st.setInt(11, account.getStatus());
            st.setDate(12, new java.sql.Date(account.getDob().getTime()));
            st.setInt(13, account.getIDAccount());
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    }
