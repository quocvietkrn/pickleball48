package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.PickleBallFieldSchedule;
import model.RegisteredPickleBallField;

public class RegisteredPickleBallFieldDAO extends DBContext {

    // Lấy danh sách đăng ký sân PickleBall theo trạng thái
    public List<RegisteredPickleBallField> getRegisteredPickleBallFieldWithStatus(int status) {
        List<RegisteredPickleBallField> list = new ArrayList<>();
        AccountDAO accountDao = new AccountDAO();
        PickleBallFieldScheduleDAO pbfsDao = new PickleBallFieldScheduleDAO();
        String sql = "SELECT [IDRegisteredPickleBallField]\n"
                + "      ,[IDAccount1]\n"
                + "      ,[IDAccount2]\n"
                + "      ,[IDPickleBallFieldSchedule]\n"
                + "      ,[Date]\n"
                + "      ,[Name]\n"
                + "      ,[PhoneNumber]\n"
                + "      ,[Deposit]\n"
                + "      ,[Status]\n"
                + "      ,[Note]\n"
                + "  FROM [dbo].[RegisteredPickleBallField] WHERE Status = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, status);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int IDRegisteredPickleBallField = rs.getInt("IDRegisteredPickleBallField");
                int IDAccount1 = rs.getInt("IDAccount1");
                Account account1 = accountDao.getAccountByID(IDAccount1);
                int IDAccount2 = rs.getInt("IDAccount2");
                Account account2 = accountDao.getAccountByID(IDAccount2);
                int IDPickleBallFieldSchedule = rs.getInt("IDPickleBallFieldSchedule");
                PickleBallFieldSchedule pbfs = pbfsDao.getPickleBallFieldScheduleByID(IDPickleBallFieldSchedule);
                Date date = rs.getDate("Date");
                String name = rs.getString("Name");
                String phoneNumber = rs.getString("PhoneNumber");
                double deposit = rs.getDouble("Deposit");
                int Status = rs.getInt("Status");
                String note = rs.getString("Note");
                RegisteredPickleBallField rpbf = new RegisteredPickleBallField(
                    IDRegisteredPickleBallField, account1, account2, pbfs, date, name, phoneNumber, deposit, Status, note
                );
                list.add(rpbf);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredPickleBallFieldDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Lấy danh sách đăng ký sân PickleBall theo ID người dùng
    public List<RegisteredPickleBallField> getRegisteredPickleBallFieldByIDAccount1(int id) {
        List<RegisteredPickleBallField> list = new ArrayList<>();
        AccountDAO accountDao = new AccountDAO();
        PickleBallFieldScheduleDAO pbfsDao = new PickleBallFieldScheduleDAO();
        String sql = "SELECT [IDRegisteredPickleBallField]\n"
                + "      ,[IDAccount1]\n"
                + "      ,[IDAccount2]\n"
                + "      ,[IDPickleBallFieldSchedule]\n"
                + "      ,[Date]\n"
                + "      ,[Name]\n"
                + "      ,[PhoneNumber]\n"
                + "      ,[Deposit]\n"
                + "      ,[Status]\n"
                + "      ,[Note]\n"
                + "  FROM [dbo].[RegisteredPickleBallField] WHERE IDAccount1 = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int IDRegisteredPickleBallField = rs.getInt("IDRegisteredPickleBallField");
                int IDAccount1 = rs.getInt("IDAccount1");
                Account account1 = accountDao.getAccountByID(IDAccount1);
                int IDAccount2 = rs.getInt("IDAccount2");
                Account account2 = accountDao.getAccountByID(IDAccount2);
                int IDPickleBallFieldSchedule = rs.getInt("IDPickleBallFieldSchedule");
                PickleBallFieldSchedule pbfs = pbfsDao.getPickleBallFieldScheduleByID(IDPickleBallFieldSchedule);
                Date date = rs.getDate("Date");
                String name = rs.getString("Name");
                String phoneNumber = rs.getString("PhoneNumber");
                double deposit = rs.getDouble("Deposit");
                int Status = rs.getInt("Status");
                String note = rs.getString("Note");
                RegisteredPickleBallField rpbf = new RegisteredPickleBallField(
                    IDRegisteredPickleBallField, account1, account2, pbfs, date, name, phoneNumber, deposit, Status, note
                );
                list.add(rpbf);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredPickleBallFieldDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Lấy thông tin đăng ký sân PickleBall theo ID
    public RegisteredPickleBallField getRegisteredPickleBallFieldByID(int id) {
        AccountDAO accountDao = new AccountDAO();
        PickleBallFieldScheduleDAO pbfsDao = new PickleBallFieldScheduleDAO();
        String sql = "SELECT [IDRegisteredPickleBallField]\n"
                + "      ,[IDAccount1]\n"
                + "      ,[IDAccount2]\n"
                + "      ,[IDPickleBallFieldSchedule]\n"
                + "      ,[Date]\n"
                + "      ,[Name]\n"
                + "      ,[PhoneNumber]\n"
                + "      ,[Deposit]\n"
                + "      ,[Status]\n"
                + "      ,[Note]\n"
                + "  FROM [dbo].[RegisteredPickleBallField] WHERE IDRegisteredPickleBallField = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int IDRegisteredPickleBallField = rs.getInt("IDRegisteredPickleBallField");
                int IDAccount1 = rs.getInt("IDAccount1");
                Account account1 = accountDao.getAccountByID(IDAccount1);
                int IDAccount2 = rs.getInt("IDAccount2");
                Account account2 = accountDao.getAccountByID(IDAccount2);
                int IDPickleBallFieldSchedule = rs.getInt("IDPickleBallFieldSchedule");
                PickleBallFieldSchedule pbfs = pbfsDao.getPickleBallFieldScheduleByID(IDPickleBallFieldSchedule);
                Date date = rs.getDate("Date");
                String name = rs.getString("Name");
                String phoneNumber = rs.getString("PhoneNumber");
                double deposit = rs.getDouble("Deposit");
                int Status = rs.getInt("Status");
                String note = rs.getString("Note");
                return new RegisteredPickleBallField(
                    IDRegisteredPickleBallField, account1, account2, pbfs, date, name, phoneNumber, deposit, Status, note
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredPickleBallFieldDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Thêm đăng ký sân PickleBall mới
    public void insertRegisteredPickleBallField(RegisteredPickleBallField rpbf) {
        String sql = "INSERT INTO [dbo].[RegisteredPickleBallField]\n"
                + "           ([IDAccount1]\n"
                + "           ,[IDPickleBallFieldSchedule]\n"
                + "           ,[Date]\n"
                + "           ,[Name]\n"
                + "           ,[PhoneNumber]\n"
                + "           ,[Deposit]\n"
                + "           ,[Status]\n"
                + "           ,[Note])\n"
                + "     VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, rpbf.getAccount1().getIDAccount());
            st.setInt(2, rpbf.getPickleBallFieldSchedule().getIDPickleBallFieldSchedule());
            st.setDate(3, (Date) rpbf.getDate());
            st.setString(4, rpbf.getName());
            st.setString(5, rpbf.getPhoneNumber());
            st.setDouble(6, rpbf.getDeposit());
            st.setInt(7, rpbf.getStatus());
            st.setString(8, rpbf.getNote());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Cập nhật trạng thái đăng ký sân PickleBall
    public void changeStatusWithIDRegisteredPickleBallField(int status, int id) {
        String sql = "UPDATE [dbo].[RegisteredPickleBallField]\n"
                + "   SET [Status] = ?\n"
                + " WHERE IDRegisteredPickleBallField = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, status);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Xóa đăng ký sân PickleBall theo ID
    public void deleteByIDRegisteredPickleBallField(int id) {
        String sql = "DELETE FROM [dbo].[RegisteredPickleBallField]\n"
                + "      WHERE IDRegisteredPickleBallField = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Cập nhật thông tin đăng ký sân PickleBall
    public void updateRegisteredPickleBallField(String name, String phone, String note, int id) {
        String sql = "UPDATE [dbo].[RegisteredPickleBallField]\n"
                + "   SET [Name] = ?,\n"
                + "       [PhoneNumber] = ?,\n"
                + "       [Note] = ?\n"
                + " WHERE IDRegisteredPickleBallField = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, phone);
            st.setString(3, note);
            st.setInt(4, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Lấy ID đăng ký sân PickleBall cuối cùng
    public int getRegisteredPickleBallFieldWithLastIndex() {
        int id = -1;
        String sql = "SELECT TOP 1 IDRegisteredPickleBallField\n"
                + "FROM [dbo].[RegisteredPickleBallField]\n"
                + "ORDER BY IDRegisteredPickleBallField DESC;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt("IDRegisteredPickleBallField");
                return id;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredPickleBallFieldDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    // Phương thức main để kiểm tra
    public static void main(String[] args) {
        RegisteredPickleBallFieldDAO rpbfDao = new RegisteredPickleBallFieldDAO();
        List<RegisteredPickleBallField> list = rpbfDao.getRegisteredPickleBallFieldWithStatus(0);
        for (RegisteredPickleBallField rpbf : list) {
            System.out.println(rpbf.getName());
        }
    }
}