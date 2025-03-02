/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminController;

import dao.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 *
 * @author ASUS
 */
@WebServlet(name = "ChangePasswordStaffServlet", urlPatterns = {"/admin/changePassword"})
public class ChangePasswordStaffServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Goi vao day dung roi");
        request.getRequestDispatcher("changePasswordStaff_VA.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String errorMessage = "";
        // Kiểm tra dữ liệu đầu vào
        if (email.isEmpty() || password.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            errorMessage = "Vui lòng điền đầy đủ thông tin!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("changePasswordStaff_VA.jsp").forward(request, response);
        }

        if (!(newPassword.equals(confirmPassword))) {
            errorMessage = "Mật khẩu mới không khớp!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("changePasswordStaff_VA.jsp").forward(request, response);
        } else {
            AccountDAO = new AccountDAO();
            if (ud.changePasswordWithOldPassword(newPassword, email, password)) {
                String successMessage = "Thay đổi mật khẩu thành công";
                System.out.println(successMessage);
                request.setAttribute("successMessage", successMessage);
                HttpSession session = request.getSession();
                session.removeAttribute("account");
                request.getRequestDispatcher("index1.jsp").forward(request, response);
            } else {
                errorMessage = "Không đúng mật khẩu cũ !";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("changePasswordStaff_VA.jsp").forward(request, response);
            }
        }

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
        processRequest(request, response);
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
    }

}
