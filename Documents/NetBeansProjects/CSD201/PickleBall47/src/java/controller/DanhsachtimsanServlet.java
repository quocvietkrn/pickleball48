/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.PickleBallFieldScheduleDAO;
import dao.RegisteredPickleBallFieldDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PickleBallFieldSchedule;
import model.RegisteredPickleBallField;

/**
 *
 * @author Minh Trung
 */
@WebServlet(name = "Danhsachtimsan", urlPatterns = {"/danhsachtimsan"})
public class DanhsachtimsanServlet extends HttpServlet {

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
            out.println("<title>Servlet DanhsachtimsanServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DanhsachtimsanServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson json = new Gson();
        String ngay_raw = request.getParameter("ngay");
        String gio_raw = request.getParameter("gio");
        String loaisan_raw = request.getParameter("loaisan");
        HttpSession session = request.getSession();

//        thêm
        if (ngay_raw != null) {
            String[] ngayParts = ngay_raw.split("-");
            if (ngayParts.length == 3) {
                // Ngày đúng định dạng "yyyy-mm-dd"
                ngay_raw = ngayParts[2] + "/" + ngayParts[1] + "/" + ngayParts[0];
            }
        }

//      lấy ngày
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = "";
        Date currentDate1;
        if (ngay_raw == null) {
            // Sử dụng java.util.Calendar để lấy thời gian hiện tại
            Calendar calendar = Calendar.getInstance();

            // Lấy thời gian hiện tại dưới dạng java.util.Date
            Date date = calendar.getTime();
            currentDate = df.format(date);
            currentDate1 = date;
        } else {
            try {
                currentDate1 = df.parse(ngay_raw);
                currentDate = df.format(currentDate1);
            } catch (ParseException ex) {
                Logger.getLogger(DanhsachtimsanServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        PickleBallFieldScheduleDAO ffsd = new  PickleBallFieldScheduleDAO();
        List<List< PickleBallFieldSchedule>> list = new ArrayList<>();
//        Lấy time
        int startTime1;
        LocalTime startTime;
        if (gio_raw == null) {
            startTime = LocalTime.now();
        } else {
            String[] gio_test = gio_raw.split(":");
            if (gio_test[0].length() == 1) {
                gio_raw = "0" + gio_raw;
            }
            startTime = LocalTime.parse(gio_raw);
        }
        int minute = startTime.getMinute();
        if (minute < 30) {
            startTime1 = startTime.getHour() - 1;
        } else {
            startTime1 = startTime.getHour();
        }
        if (startTime1 != 0 && startTime1 > 0) {
            startTime = LocalTime.of(startTime1, 30);
        } else {
            startTime = LocalTime.of(23, 30);
        }
        //Lấy loại sân
        int loaisan = 0;
        if (loaisan_raw != null) {
            loaisan = Integer.parseInt(loaisan_raw);
        }

        LocalTime endTime = LocalTime.of(0, 30);
        List< PickleBallFieldSchedule> listSchedule = new ArrayList<>();
        // B?t d?u vòng l?p

        if (loaisan == 0) {
            for (LocalTime currentTime = startTime; !currentTime.equals(endTime); currentTime = currentTime.plusHours(1)) {
                // Chuy?n d?i LocalTime sang java.sql.Time (n?u c?n)
                Time sqlTime = Time.valueOf(currentTime);
                listSchedule = ffsd.getPickleBallFieldScheduleByStartTimeAndStatus0AndStatusFF0(sqlTime);
                if (listSchedule.size() != 0) {
                    list.add(listSchedule);
                }
            }
        } else {
            for (LocalTime currentTime = startTime; !currentTime.equals(endTime); currentTime = currentTime.plusHours(1)) {
                // Chuy?n d?i LocalTime sang java.sql.Time (n?u c?n)
                Time sqlTime = Time.valueOf(currentTime);
                listSchedule = ffsd.getPickleBallFieldScheduleByStartTimeAndStatus0AndStatusFF0AndTypeFF(sqlTime, loaisan);
                if (listSchedule.size() != 0) {
                    list.add(listSchedule);
                }
            }
        }

        if (!list.isEmpty()) {

            int maxSize = 0;

            for (List< PickleBallFieldSchedule> subList : list) {
                if (subList.size() > maxSize) {
                    maxSize = subList.size();
                }
            }
            int i = 10 / maxSize;
            session.setAttribute("size", i);
        }

        String[] dateSplit = currentDate.split("/");
        String currentDateChange = dateSplit[2] + dateSplit[1] + dateSplit[0];
        List<Integer> listIDFFS = new ArrayList<>();
        //lấy ra id với status = 1
        List<RegisteredPickleBallField> listRFF = new ArrayList<>();
        RegisteredPickleBallFieldDAO rFFD = new RegisteredPickleBallFieldDAO();
        listRFF = rFFD.getRegisteredPickleBallFielWithStatusAndDate(1, currentDateChange);
        for (RegisteredPickleBallField registeredPickleBallField : listRFF) {
            listIDFFS.add(registeredPickleBallField.getPickleBallFieldSchedule().getIDPickleBallFieldSchedule());
        }
        //lấy ra id với status = 2
        listRFF = rFFD.getRegisteredPickleBallFielWithStatusAndDate(2, currentDateChange);
        for (RegisteredPickleBallField registeredPickleBallField : listRFF) {
            listIDFFS.add(registeredPickleBallField.getPickleBallFieldSchedule().getIDPickleBallFieldSchedule());
        }
        //lấy id RFO
        if (request.getParameter("idRFO") != null) {
            String idRFO_raw = request.getParameter("idRFO");
            session.setAttribute("idRFO", Integer.parseInt(idRFO_raw));
        }
        session.setAttribute("currentDate", currentDate);
        session.setAttribute("currentTime", startTime);
        session.setAttribute("currentHour", startTime1);
        session.setAttribute("listIDFFS", listIDFFS);
        session.setAttribute("listffs", list);
        request.getRequestDispatcher("pagination").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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