/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminController;
import dao.PickleBallFieldDAO;
import dao.PickleBallFieldScheduleDAO;
import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import model.PickleBallField;


/**
 *
 * @author ASUS
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet(name = "AddField", urlPatterns = {"/admin/addfield"})
public class AddField extends HttpServlet {

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        PickleBallFieldDAO ffDAO = new PickleBallFieldDAO();

        String nameFF = request.getParameter("fieldName");
        String typeFFStr = request.getParameter("fieldType");
        String priceStr = request.getParameter("fieldPrice");
        Part file = request.getPart("fieldImage");

        // Validate input
        if (nameFF == null || nameFF.trim().isEmpty() || 
            typeFFStr == null || priceStr == null || file == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data.");
            return;
        }

        try {
            int typeFF = Integer.parseInt(typeFFStr);
            int price = Integer.parseInt(priceStr);

            String imageFileName = file.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("/img_nhat/") + imageFileName;

            // Ensure upload directory exists
            File uploadDir = new File(getServletContext().getRealPath("/img_nhat/"));
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Save file to the server
            try (InputStream is = file.getInputStream();
                 FileOutputStream fos = new FileOutputStream(uploadPath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }

            // Insert football field
            PickleBallField ff = new PickleBallField();
            ff.setName(nameFF);
            ff.setTypeofPickleBallField(typeFF);
            ff.setPrice(price);
            ff.setImage(imageFileName);
            ff.setStatus(0);
            ffDAO.insertPickleBallField(ff);

            int idFootballField = ffDAO.getPickleBallFieldWithLastIndex();

            // Insert schedules
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String[] selectedTimes = request.getParameterValues("selectedTimes");
            if (selectedTimes != null) {
                PickleBallFieldScheduleDAO ffsD = new PickleBallFieldScheduleDAO();
                for (String timeRange : selectedTimes) {
                    String[] timesplit = timeRange.split("-");
                    if (timesplit.length == 2) {
                        String startTime = timesplit[0].trim();
                        String endTime = timesplit[1].trim();
                        ffsD.insertPickleBallFieldSchedule(startTime, endTime, idFootballField, 0);
                    }
                }
            }

            response.sendRedirect("danhsachsanbong");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid numeric input.");
            e.printStackTrace();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing.");
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addsanbong_nhat.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles adding new football fields.";
    }
}
