package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PickleBallField;

public class PickleBallFieldDAO extends DBContext {

    // Lấy danh sách tất cả sân PickleBall (có trạng thái null hoặc 0)
    public List<PickleBallField> getPickleBallFields() {
        List<PickleBallField> list = new ArrayList<>();
        String sql = "SELECT [IDPickleBallField]\n"
                + "      ,[Name]\n"
                + "      ,[TypeofFootballField]\n"
                + "      ,[Price]\n"
                + "      ,[Image]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[PickleBallField] WHERE Status IS NULL OR Status = 0;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int IDPickleBallField = rs.getInt("IDPickleBallField");
                String Name = rs.getString("Name");
                int TypeofFootballField = rs.getInt("TypeofFootballField");
                double Price = rs.getDouble("Price");
                String Image = rs.getString("Image");
                int Status = rs.getInt("Status");
                PickleBallField pbf = new PickleBallField(IDPickleBallField, Name, TypeofFootballField, Price, Image, Status);
                list.add(pbf);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PickleBallFieldDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Lấy thông tin sân PickleBall bằng ID
    public PickleBallField getPickleBallFieldByID(int id) {
        String sql = "SELECT [IDPickleBallField]\n"
                + "      ,[Name]\n"
                + "      ,[TypeofFootballField]\n"
                + "      ,[Price]\n"
                + "      ,[Image]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[PickleBallField] WHERE IDPickleBallField = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int IDPickleBallField = rs.getInt("IDPickleBallField");
                String Name = rs.getString("Name");
                int TypeofFootballField = rs.getInt("TypeofFootballField");
                double Price = rs.getDouble("Price");
                String Image = rs.getString("Image");
                int Status = rs.getInt("Status");
                PickleBallField pbf = new PickleBallField(IDPickleBallField, Name, TypeofFootballField, Price, Image, Status);
                return pbf;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PickleBallFieldDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Lấy ID của sân PickleBall cuối cùng trong danh sách
    public int getPickleBallFieldWithLastIndex() {
        int id = -1;
        String sql = "SELECT TOP 1 IDPickleBallField\n"
                + "FROM [dbo].[PickleBallField]\n"
                + "ORDER BY IDPickleBallField DESC;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt("IDPickleBallField");
                return id;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PickleBallFieldDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    // Cập nhật thông tin sân PickleBall
    public void updatePickleBallField(PickleBallField pbf) {
        String sql = "UPDATE [dbo].[PickleBallField]\n"
                + "   SET [Name] = ?\n"
                + "      ,[TypeofFootballField] = ?\n"
                + "      ,[Price] = ?\n"
                + "      ,[Image] = ?\n"
                + " WHERE IDPickleBallField = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, pbf.getName());
            st.setInt(2, pbf.getTypeofFootballField());
            st.setDouble(3, pbf.getPrice());
            st.setString(4, pbf.getImage());
            st.setInt(5, pbf.getIDPickleBallField());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Thêm sân PickleBall mới
    public void insertPickleBallField(PickleBallField pbf) {
        String sql = "INSERT INTO [dbo].[PickleBallField]\n"
                + "           ([Name]\n"
                + "           ,[TypeofFootballField]\n"
                + "           ,[Price]\n"
                + "           ,[Image]\n"
                + "           ,[Status])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, pbf.getName());
            st.setInt(2, pbf.getTypeofFootballField());
            st.setDouble(3, pbf.getPrice());
            st.setString(4, pbf.getImage());
            st.setInt(5, 0); // Mặc định trạng thái là 0 (hoạt động)
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Xóa sân PickleBall (cập nhật trạng thái thành 1 - không hoạt động)
    public void deletePickleBallField(int id) {
        String sql = "UPDATE [dbo].[PickleBallField]\n"
                + "   SET [Status] = ?\n"
                + " WHERE IDPickleBallField = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, 1); // Trạng thái 1: không hoạt động
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Phương thức main để kiểm tra
    public static void main(String[] args) {
        PickleBallFieldDAO pbfDao = new PickleBallFieldDAO();

        // Lấy danh sách sân PickleBall
        List<PickleBallField> list = pbfDao.getPickleBallFields();
        for (PickleBallField pbf : list) {
            System.out.println(pbf.toString());
        }

        // Lấy thông tin sân bằng ID
        PickleBallField pbf = pbfDao.getPickleBallFieldByID(1);
        if (pbf != null) {
            System.out.println("Sân tìm được: " + pbf.getName());
        }

        // Thêm sân mới
        PickleBallField newPbf = new PickleBallField();
        newPbf.setName("Sân PickleBall mới");
        newPbf.setTypeofFootballField(1);
        newPbf.setPrice(200000);
        newPbf.setImage("image_url.jpg");
        pbfDao.insertPickleBallField(newPbf);

        // Cập nhật thông tin sân
        PickleBallField updatePbf = new PickleBallField();
        updatePbf.setIDPickleBallField(1);
        updatePbf.setName("Sân PickleBall cập nhật");
        updatePbf.setTypeofFootballField(2);
        updatePbf.setPrice(250000);
        updatePbf.setImage("new_image_url.jpg");
        pbfDao.updatePickleBallField(updatePbf);

        // Xóa sân
        pbfDao.deletePickleBallField(1);
    }
}