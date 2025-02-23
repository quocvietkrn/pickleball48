package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.DasboarDTO;

public class AdminDAO extends DBContext {

    // Lấy tổng số người dùng (không bao gồm admin)
    public int getTotalUser() throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM dbo.[Account] WHERE Role != 3";
        try {
            st = getConnection().prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                st.close();
            }
        }
        return 0;
    }

    // Lấy danh sách người dùng với phân trang
    public List<Account> getUsers(int index) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Account> listUsers = new ArrayList<>();
        String sql = "SELECT [IDAccount]\n"
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
                + "      ,[Name]\n"
                + "  FROM [dbo].[Account] WHERE Role != 3 ORDER BY Gender DESC OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
        try {
            st = getConnection().prepareStatement(sql);
            st.setInt(1, (index - 1) * 10);
            rs = st.executeQuery();
            while (rs.next()) {
                Account user = new Account();
                user.setIDAccount(rs.getInt("IDAccount"));
                user.setName(rs.getString("Name"));
                user.setUserName(rs.getString("UserName"));
                user.setPassWord(rs.getString("PassWord"));
                user.setGender(rs.getInt("Gender"));
                user.setPhoneNumber(rs.getString("PhoneNumber"));
                user.setIDEmail(rs.getString("IDEmail"));
                user.setIDFacebook(rs.getString("IDFacebook"));
                user.setBank(rs.getString("Bank"));
                user.setBankNumber(rs.getString("BankNumber"));
                user.setRole(rs.getInt("Role"));
                user.setStatus(rs.getInt("Status"));

                listUsers.add(user);
            }
            return listUsers;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                st.close();
            }
        }
        return null;
    }

    // Vô hiệu hóa người dùng
    public int disableUser(int accId) throws SQLException {
        PreparedStatement st = null;
        String sql = "UPDATE dbo.Account SET Status = 1 WHERE IDAccount = ?";
        try {
            st = getConnection().prepareStatement(sql);
            st.setInt(1, accId);
            int affectedRow = st.executeUpdate();
            if (affectedRow > 0) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                st.close();
            }
        }
        return 0;
    }

    // Kích hoạt lại người dùng
    public int enableUser(int accId) throws SQLException {
        PreparedStatement st = null;
        String sql = "UPDATE dbo.Account SET Status = 0 WHERE IDAccount = ?";
        try {
            st = getConnection().prepareStatement(sql);
            st.setInt(1, accId);
            int affectedRow = st.executeUpdate();
            if (affectedRow > 0) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                st.close();
            }
        }
        return 0;
    }

    // Cập nhật vai trò của người dùng
    public int setRoleUser(int accountId, int roleId) throws SQLException {
        PreparedStatement st = null;
        String sql = "UPDATE dbo.Account SET Role = ? WHERE IDAccount = ?";
        try {
            st = getConnection().prepareStatement(sql);
            st.setInt(1, roleId);
            st.setInt(2, accountId);
            int affectedRow = st.executeUpdate();
            if (affectedRow > 0) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                st.close();
            }
        }
        return 0;
    }

    // Lấy thống kê doanh thu theo năm
    public List<DasboarDTO> getStatistic(String year) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<DasboarDTO> listStatistic = new ArrayList<>();
        String sql = "WITH Months AS (\n"
                + "    SELECT \n"
                + "        YearMonth = DATEADD(MONTH, n.Number, DATEFROMPARTS(?, 1, 1))\n"
                + "    FROM \n"
                + "        (SELECT TOP (12) ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1 AS Number \n"
                + "         FROM master.dbo.spt_values) n\n"
                + "    CROSS JOIN \n"
                + "        (SELECT DISTINCT YEAR(PaymentDate) AS Year FROM [dbo].[Bill] WHERE PaymentDate IS NOT NULL) y\n"
                + "    WHERE \n"
                + "        DATEADD(MONTH, n.Number, DATEFROMPARTS(y.Year, 1, 1)) <= DATEFROMPARTS(y.Year, 12, 31)\n"
                + ")\n"
                + "SELECT \n"
                + "    YEAR(m.YearMonth) AS Year,\n"
                + "    MONTH(m.YearMonth) AS Month,\n"
                + "    ISNULL(SUM(b.TotalPrice), 0) AS TotalRevenue\n"
                + "FROM \n"
                + "    Months m\n"
                + "LEFT JOIN \n"
                + "    [dbo].[Bill] b\n"
                + "    ON YEAR(b.PaymentDate) = YEAR(m.YearMonth) \n"
                + "    AND MONTH(b.PaymentDate) = MONTH(m.YearMonth)\n"
                + "GROUP BY \n"
                + "    YEAR(m.YearMonth), MONTH(m.YearMonth)\n"
                + "ORDER BY \n"
                + "    Year, Month;";
        try {
            st = getConnection().prepareStatement(sql);
            st.setString(1, year);
            rs = st.executeQuery();
            while (rs.next()) {
                DasboarDTO dto = new DasboarDTO();
                dto.setYear(rs.getString("Year"));
                dto.setMonth(rs.getString("Month"));
                dto.setRevenue(rs.getString("TotalRevenue"));
                listStatistic.add(dto);
            }
            return listStatistic;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                st.close();
            }
        }
        return null;
    }
}