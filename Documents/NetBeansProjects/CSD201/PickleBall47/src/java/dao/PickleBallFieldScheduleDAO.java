package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PickleBallField;
import model.PickleBallFieldSchedule;

public class PickleBallFieldScheduleDAO extends DBContext {

    // Lấy danh sách lịch trình sân PickleBall theo thời gian bắt đầu và trạng thái
    public List<PickleBallFieldSchedule> getPickleBallFieldScheduleByStartTimeAndStatus0AndStatusFF0(Time startTime) {
        List<PickleBallFieldSchedule> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    [PFS].[IDPickleBallFieldSchedule],\n"
                + "    [PFS].[StartTime],\n"
                + "    [PFS].[Endtime],\n"
                + "    [PFS].[IDPickleBallField],\n"
                + "    [PFS].[Status]\n"
                + "FROM \n"
                + "    [PickleBallFieldSchedule] AS [PFS]\n"
                + "JOIN \n"
                + "    [PickleBallField] AS [PF]\n"
                + "ON \n"
                + "    [PFS].[IDPickleBallField] = [PF].[IDPickleBallField]\n"
                + "WHERE CONVERT(time, PFS.[StartTime]) = CONVERT(time, ?) AND ([PFS].[Status] = 0 OR [PFS].[Status] IS NULL)\n"
                + "    AND ([PF].[Status] = 0 OR [PF].[Status] IS NULL);";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setTime(1, startTime);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int IDPickleBallFieldSchedule = rs.getInt("IDPickleBallFieldSchedule");
                Time StartTime = rs.getTime("StartTime");
                Time Endtime = rs.getTime("Endtime");
                int IDPickleBallField = rs.getInt("IDPickleBallField");
                PickleBallFieldDAO pbfDao = new PickleBallFieldDAO();
                PickleBallField pbf = pbfDao.getPickleBallFieldByID(IDPickleBallField);
                int Status = rs.getInt("Status");
                PickleBallFieldSchedule pbfs = new PickleBallFieldSchedule(IDPickleBallFieldSchedule, StartTime, Endtime, pbf, Status);
                list.add(pbfs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PickleBallFieldScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Lấy danh sách lịch trình sân PickleBall theo thời gian bắt đầu, trạng thái và loại sân
    public List<PickleBallFieldSchedule> getPickleBallFieldScheduleByStartTimeAndStatus0AndStatusFF0AndTypeFF(Time startTime, int typeFF) {
        List<PickleBallFieldSchedule> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    [PFS].[IDPickleBallFieldSchedule],\n"
                + "    [PFS].[StartTime],\n"
                + "    [PFS].[Endtime],\n"
                + "    [PFS].[IDPickleBallField],\n"
                + "    [PFS].[Status]\n"
                + "FROM \n"
                + "    [PickleBallFieldSchedule] AS [PFS]\n"
                + "JOIN \n"
                + "    [PickleBallField] AS [PF]\n"
                + "ON \n"
                + "    [PFS].[IDPickleBallField] = [PF].[IDPickleBallField]\n"
                + "WHERE CONVERT(time, PFS.[StartTime]) = CONVERT(time, ?) AND ([PFS].[Status] = 0 OR [PFS].[Status] IS NULL)\n"
                + "    AND ([PF].[Status] = 0 OR [PF].[Status] IS NULL) AND ([PF].[TypeofFootballField] = ?);";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setTime(1, startTime);
            st.setInt(2, typeFF);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int IDPickleBallFieldSchedule = rs.getInt("IDPickleBallFieldSchedule");
                Time StartTime = rs.getTime("StartTime");
                Time Endtime = rs.getTime("Endtime");
                int IDPickleBallField = rs.getInt("IDPickleBallField");
                PickleBallFieldDAO pbfDao = new PickleBallFieldDAO();
                PickleBallField pbf = pbfDao.getPickleBallFieldByID(IDPickleBallField);
                int Status = rs.getInt("Status");
                PickleBallFieldSchedule pbfs = new PickleBallFieldSchedule(IDPickleBallFieldSchedule, StartTime, Endtime, pbf, Status);
                list.add(pbfs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PickleBallFieldScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Lấy danh sách lịch trình sân PickleBall theo ID sân
    public List<PickleBallFieldSchedule> getPickleBallFieldScheduleByIDPickleBallField(int id) {
        List<PickleBallFieldSchedule> list = new ArrayList<>();
        String sql = "SELECT [IDPickleBallFieldSchedule]\n"
                + "      ,[StartTime]\n"
                + "      ,[Endtime]\n"
                + "      ,[IDPickleBallField]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[PickleBallFieldSchedule] WHERE IDPickleBallField=?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int IDPickleBallFieldSchedule = rs.getInt("IDPickleBallFieldSchedule");
                Time StartTime = rs.getTime("StartTime");
                Time Endtime = rs.getTime("Endtime");
                int IDPickleBallField = rs.getInt("IDPickleBallField");
                PickleBallFieldDAO pbfDao = new PickleBallFieldDAO();
                PickleBallField pbf = pbfDao.getPickleBallFieldByID(IDPickleBallField);
                int Status = rs.getInt("Status");
                PickleBallFieldSchedule pbfs = new PickleBallFieldSchedule(IDPickleBallFieldSchedule, StartTime, Endtime, pbf, Status);
                list.add(pbfs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PickleBallFieldScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Lấy danh sách lịch trình sân PickleBall theo ID sân và trạng thái
    public List<PickleBallFieldSchedule> getPickleBallFieldScheduleByIDPickleBallFieldWithStatus(int id) {
        List<PickleBallFieldSchedule> list = new ArrayList<>();
        String sql = "SELECT [IDPickleBallFieldSchedule]\n"
                + "      ,[StartTime]\n"
                + "      ,[Endtime]\n"
                + "      ,[IDPickleBallField]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[PickleBallFieldSchedule] WHERE IDPickleBallField=? AND (Status IS NULL OR Status = 0)";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int IDPickleBallFieldSchedule = rs.getInt("IDPickleBallFieldSchedule");
                Time StartTime = rs.getTime("StartTime");
                Time Endtime = rs.getTime("Endtime");
                int IDPickleBallField = rs.getInt("IDPickleBallField");
                PickleBallFieldDAO pbfDao = new PickleBallFieldDAO();
                PickleBallField pbf = pbfDao.getPickleBallFieldByID(IDPickleBallField);
                int Status = rs.getInt("Status");
                PickleBallFieldSchedule pbfs = new PickleBallFieldSchedule(IDPickleBallFieldSchedule, StartTime, Endtime, pbf, Status);
                list.add(pbfs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PickleBallFieldScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Lấy danh sách tất cả lịch trình sân PickleBall với trạng thái
    public List<PickleBallFieldSchedule> getAllPickleBallFieldScheduleByIDPickleBallFieldWithStatus() {
        List<PickleBallFieldSchedule> list = new ArrayList<>();
        String sql = "SELECT [IDPickleBallFieldSchedule]\n"
                + "      ,[StartTime]\n"
                + "      ,[Endtime]\n"
                + "      ,[IDPickleBallField]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[PickleBallFieldSchedule] WHERE (Status IS NULL OR Status = 0)";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int IDPickleBallFieldSchedule = rs.getInt("IDPickleBallFieldSchedule");
                Time StartTime = rs.getTime("StartTime");
                Time Endtime = rs.getTime("Endtime");
                int IDPickleBallField = rs.getInt("IDPickleBallField");
                PickleBallFieldDAO pbfDao = new PickleBallFieldDAO();
                PickleBallField pbf = pbfDao.getPickleBallFieldByID(IDPickleBallField);
                int Status = rs.getInt("Status");
                PickleBallFieldSchedule pbfs = new PickleBallFieldSchedule(IDPickleBallFieldSchedule, StartTime, Endtime, pbf, Status);
                list.add(pbfs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PickleBallFieldScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Lấy danh sách lịch trình sân PickleBall theo ID sân và trạng thái 1
    public List<PickleBallFieldSchedule> getPickleBallFieldScheduleByIDPickleBallFieldWithStatus1(int id) {
        List<PickleBallFieldSchedule> list = new ArrayList<>();
        String sql = "SELECT [IDPickleBallFieldSchedule]\n"
                + "      ,[StartTime]\n"
                + "      ,[Endtime]\n"
                + "      ,[IDPickleBallField]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[PickleBallFieldSchedule] WHERE IDPickleBallField=? AND (Status = 1)";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int IDPickleBallFieldSchedule = rs.getInt("IDPickleBallFieldSchedule");
                Time StartTime = rs.getTime("StartTime");
                Time Endtime = rs.getTime("Endtime");
                int IDPickleBallField = rs.getInt("IDPickleBallField");
                PickleBallFieldDAO pbfDao = new PickleBallFieldDAO();
                PickleBallField pbf = pbfDao.getPickleBallFieldByID(IDPickleBallField);
                int Status = rs.getInt("Status");
                PickleBallFieldSchedule pbfs = new PickleBallFieldSchedule(IDPickleBallFieldSchedule, StartTime, Endtime, pbf, Status);
                list.add(pbfs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PickleBallFieldScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Lấy lịch trình sân PickleBall theo ID
    public PickleBallFieldSchedule getPickleBallFieldScheduleByID(int id) {
        PickleBallFieldDAO pbfDao = new PickleBallFieldDAO();
        String sql = "SELECT [IDPickleBallFieldSchedule]\n"
                + "      ,[StartTime]\n"
                + "      ,[Endtime]\n"
                + "      ,[IDPickleBallField]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[PickleBallFieldSchedule] WHERE IDPickleBallFieldSchedule = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int IDPickleBallFieldSchedule = rs.getInt("IDPickleBallFieldSchedule");
                Time StartTime = rs.getTime("StartTime");
                Time Endtime = rs.getTime("Endtime");
                int IDPickleBallField = rs.getInt("IDPickleBallField");
                PickleBallField pbf = pbfDao.getPickleBallFieldByID(IDPickleBallField);
                int Status = rs.getInt("Status");
                PickleBallFieldSchedule pbfs = new PickleBallFieldSchedule(IDPickleBallFieldSchedule, StartTime, Endtime, pbf, Status);
                return pbfs;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PickleBallFieldScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Cập nhật trạng thái lịch trình sân PickleBall theo ID
    public void changeStatusWithIDPickleBallFieldSchedule(int status, int id) {
        String sql = "UPDATE [dbo].[PickleBallFieldSchedule]\n"
                + "   SET [Status] = ?\n"
                + " WHERE IDPickleBallFieldSchedule = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, status);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Cập nhật trạng thái lịch trình sân PickleBall theo ID sân
    public void changeStatusWithIDPickleBallField(int status, int id) {
        String sql = "UPDATE [dbo].[PickleBallFieldSchedule]\n"
                + "   SET [Status] = ?\n"
                + " WHERE IDPickleBallField = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, status);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Thêm lịch trình sân PickleBall mới
    public void insertPickleBallFieldSchedule(String startTime, String endTime, int idff, int status) {
        String sql = "INSERT INTO [dbo].[PickleBallFieldSchedule]\n"
                + "           ([StartTime]\n"
                + "           ,[Endtime]\n"
                + "           ,[IDPickleBallField]\n"
                + "           ,[Status])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?)";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, startTime);
            st.setString(2, endTime);
            st.setInt(3, idff);
            st.setInt(4, status);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Phương thức main để kiểm tra
    public static void main(String[] args) {
        PickleBallFieldScheduleDAO pbfsDao = new PickleBallFieldScheduleDAO();
        List<PickleBallFieldSchedule> list = new ArrayList<>();
        Time sqlTime = Time.valueOf("09:30:00");

        // Lấy danh sách lịch trình sân PickleBall theo thời gian bắt đầu
        list = pbfsDao.getPickleBallFieldScheduleByStartTimeAndStatus0AndStatusFF0(sqlTime);
        for (PickleBallFieldSchedule pbfs : list) {
            System.out.println(pbfs.getStartTime());
        }
    }
}