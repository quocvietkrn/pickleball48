/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminController;

import dao.RegisteredPickleBallFieldDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.RegisteredPickleBallField;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "CheckRequest", urlPatterns = {"/CheckRequest"})
public class CheckRequest extends HttpServlet {

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
            out.println("<title>Servlet XulyyeucauServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet XulyyeucauServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson json = new Gson();       
        BillDAO billDAO = new BillDAO();
        RegisteredPickleBallFieldDAO rffDAO = new RegisteredPickleBallFieldDAO();
        String idBill_raw = request.getParameter("idBill");
        int idBill = Integer.parseInt(idBill_raw);
        int status = Integer.parseInt(request.getParameter("status"));
        String requestStatus = request.getParameter("requestStatus");
        if(requestStatus.equals("duyet")){
            if(status==5) {
                RegisteredPickleBallField rff = billDAO.getBillByID(idBill).getRegisteredPickleBallField();
                String test = json.toJson(rff);
                out.print(test);
            }
            else{
                String test = json.toJson("Xác nhận đơn duyệt");
                out.print(test);
            }
        }else{
            if(status==5){
                RegisteredPickleBallField rff = billDAO.getBillByID(idBill).getRegisteredPickleBallField();
                String test = json.toJson(rff);
                out.print(test);
            } else{
                String test = json.toJson("Từ chối đơn duyệt");
                out.print(test);
            }
        }
        out.flush();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson json = new Gson();       
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String idBill_raw = request.getParameter("idBill");
        String status_raw = request.getParameter("status");
        int idBill = Integer.parseInt(idBill_raw);
        int status = Integer.parseInt(status_raw);
        BillDAO billD = new BillDAO();
        RegisteredPickleBallFieldDAO rFFD = new RegisteredPickleBallFieldDAO();
        
        RegisteredPickleBallField rFF = billD.getBillByID(idBill).getRegisteredPickleBallField();
        int idrFF = rFF.getIDRegisteredPickleBallField();
        rFFD.changeStatusWithIDRegisteredPickleBallField(status, idrFF);
        
        billD.updateIDAccount2(idBill,account.getIDAccount());
        String test = json.toJson("thanh cong");
        out.print(test);
        out.flush();
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
