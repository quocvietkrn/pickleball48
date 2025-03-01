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
        String sql = "SELECT IDPickleBallField, Name, TypeofPickleBallField, Price, Image, Status "
                   + "FROM PickleBallField WHERE Status IS NULL OR Status = 0";
        try (PreparedStatement st = getConnection().prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                PickleBallField pbf = new PickleBallField(
                    rs.getInt("IDPickleBallField"),
                    rs.getString("Name"),
                    rs.getInt("TypeofPickleBallField"),
                    rs.getDouble("Price"),
                    rs.getString("Image"),
                    rs.getInt("Status")
                );
                list.add(pbf);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PickleBallFieldDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Lấy thông tin sân PickleBall bằng ID
    public PickleBallField getPickleBallFieldByID(int id) {
        String sql = "SELECT IDPickleBallField, Name, TypeofPickleBallField, Price, Image, Status "
                   + "FROM PickleBallField WHERE IDPickleBallField = ?";
        try (PreparedStatement st = getConnection().prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new PickleBallField(
                        rs.getInt("IDPickleBallField"),
                        rs.getString("Name"),
                        rs.getInt("TypeofPickleBallField"),
                        rs.getDouble("Price"),
                        rs.getString("Image"),
                        rs.getInt("Status")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PickleBallFieldDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Lấy ID của sân PickleBall cuối cùng trong danh sách
    public int getPickleBallFieldWithLastIndex() {
        String sql = "SELECT TOP 1 IDPickleBallField FROM PickleBallField ORDER BY IDPickleBallField DESC";
        try (PreparedStatement st = getConnection().prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("IDPickleBallField");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PickleBallFieldDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    // Cập nhật thông tin sân PickleBall
    public void updatePickleBallField(PickleBallField pbf) {
        String sql = "UPDATE PickleBallField SET Name = ?, TypeofPickleBallField = ?, Price = ?, Image = ? "
                   + "WHERE IDPickleBallField = ?";
        try (PreparedStatement st = getConnection().prepareStatement(sql)) {
            st.setString(1, pbf.getName());
            st.setInt(2, pbf.getTypeofPickleBallField());
            st.setDouble(3, pbf.getPrice());
            st.setString(4, pbf.getImage());
            st.setInt(5, pbf.getIDPickleBallField());
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(PickleBallFieldDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Thêm sân PickleBall mới
    public void insertPickleBallField(PickleBallField pbf) {
        String sql = "INSERT INTO PickleBallField (Name, TypeofPickleBallField, Price, Image, Status) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = getConnection().prepareStatement(sql)) {
            st.setString(1, pbf.getName());
            st.setInt(2, pbf.getTypeofPickleBallField());
            st.setDouble(3, pbf.getPrice());
            st.setString(4, pbf.getImage());
            st.setInt(5, 0); // Mặc định trạng thái là 0 (hoạt động)
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(PickleBallFieldDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Xóa sân PickleBall (cập nhật trạng thái thành 1 - không hoạt động)
    public void deletePickleBallField(int id) {
        String sql = "UPDATE PickleBallField SET Status = 1 WHERE IDPickleBallField = ?";
        try (PreparedStatement st = getConnection().prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(PickleBallFieldDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Phương thức main để kiểm tra
    public static void main(String[] args) {
        PickleBallFieldDAO pbfDao = new PickleBallFieldDAO();
        String sql = "SELECT * FROM PickleBallField WHERE Status = 0";

        // Lấy danh sách sân PickleBall
        List<PickleBallField> list = pbfDao.getPickleBallFields();
        list.forEach(pbf -> System.out.println(pbf));

        // Lấy thông tin sân bằng ID
        PickleBallField pbf = pbfDao.getPickleBallFieldByID(1);
        if (pbf != null) {
            System.out.println("Sân tìm được: " + pbf.getName());
        }

        // Thêm sân mới
        PickleBallField newPbf = new PickleBallField();
        newPbf.setName("Sân PickleBall mới");
        newPbf.setTypeofPickleBallField(1);
        newPbf.setPrice(200000);
        newPbf.setImage("image_url.jpg");
        pbfDao.insertPickleBallField(newPbf);

        // Cập nhật thông tin sân
        PickleBallField updatePbf = new PickleBallField();
        updatePbf.setIDPickleBallField(1);
        updatePbf.setName("Sân PickleBall cập nhật");
        updatePbf.setTypeofPickleBallField(2);
        updatePbf.setPrice(250000);
        updatePbf.setImage("new_image_url.jpg");
        pbfDao.updatePickleBallField(updatePbf);

        // Xóa sân
        pbfDao.deletePickleBallField(1);
    }
}
