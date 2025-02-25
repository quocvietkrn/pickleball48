/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminController;

import dao.PickleBallFieldDAO;
import dao.PickleBallFieldScheduleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.PickleBallField;
import model.PickleBallFieldSchedule;

/**
 *
 * @author ASUS
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet(name = "UpdateField", urlPatterns = {"/admin/UpdateField"})
public class UpdateField extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdatesanbongServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatesanbongServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id_raw = request.getParameter("PickleBallField");
        int id = Integer.parseInt(id_raw);
        PickleBallFieldDAO ffD = new PickleBallFieldDAO();
        PickleBallField ff = ffD.getPickleBallFieldByID(id);
        request.setAttribute("PickleBallField", ff);
        request.getRequestDispatcher("UpdateField.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        int IDPickleBallField = Integer.parseInt(request.getParameter("fieldID"));
        String Name = request.getParameter("fieldName");
        int TypeofFootballField = Integer.parseInt(request.getParameter("fieldType"));
        int Price = Integer.parseInt(request.getParameter("fieldPrice"));
        Part file = request.getPart("fieldImage");
        String imageFileName = file.getSubmittedFileName();
        String uploadPath = "D:/1_SWD392/SWD192/FBK74/web/img_nhat/" + imageFileName;
        try {
            FileOutputStream fos = new FileOutputStream(uploadPath);
            InputStream is = file.getInputStream();
            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();

            PickleBallFieldDAO ffD = new PickleBallFieldDAO();
            if (data != null) {
                PickleBallField ff = new PickleBallField(IDPickleBallField, Name, TypeofFootballField, Price, Name, Price);

                ffD.updatePickleBallField(ff);
            } else {
                PickleBallField ff = new PickleBallField(IDPickleBallField, Name, TypeofFootballField, Price, ffD.getPickleBallFieldByID(IDPickleBallField).getImage(), 0);
                ffD.updatePickleBallField(ff);
            }
        } catch (Exception e) {
        }

        //Xử lý chèn lịch sân
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String[] selectedTimes = request.getParameterValues("selectedTimes");
        if (selectedTimes != null) {
            List<String> selectedTimesList = new ArrayList<>(Arrays.asList(selectedTimes));
            PickleBallFieldScheduleDAO ffsD = new PickleBallFieldScheduleDAO();
            ffsD.changeStatusWithIDPickleBallField(1, IDPickleBallField);
            //ListFFS1 chứa các giờ trong db status = 1
            List<PickleBallFieldSchedule> listFFS1 = ffsD.getPickleBallFieldScheduleByIDPickleBallFieldWithStatus1(IDPickleBallField);
            //xử lý xóa các đối tượng trùng time đã chọn
            //Xử lý lấy các time không còn được chọn và xóa chúng
            Iterator<String> timeIterator = selectedTimesList.iterator();
            while (timeIterator.hasNext()) {
                String timeSelected = timeIterator.next();
                String[] timesplit = timeSelected.split("-");
                timesplit[0] = timesplit[0].trim();
                Date parsedTime;
                try {
                    parsedTime = timeFormat.parse(timesplit[0]);
                    Time selectedTime = new Time(parsedTime.getTime());
                    for (PickleBallFieldSchedule pickleBallFieldSchedule : listFFS1) {
                        if (selectedTime.equals(pickleBallFieldSchedule.getStartTime())) {
                            timeIterator.remove();
                            ffsD.changeStatusWithIDPickleBallFieldSchedule(0, pickleBallFieldSchedule.getIDPickleBallFieldSchedule());
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(UpdateField.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for (String string : selectedTimesList) {
                String[] timesplit = string.split("-");
                timesplit[0] = timesplit[0].trim();
                timesplit[1] = timesplit[1].trim();
                ffsD.insertPickleBallFieldSchedule(timesplit[0], timesplit[1], IDPickleBallField, 0);
            }
        }
        response.sendRedirect("PickleBallFieldList");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
