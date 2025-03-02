/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminController;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ProfileStaff", urlPatterns = {"/ProfileStaff"})
public class ProfileStaff extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse reqs)
            throws ServletException, IOException {
        try {
            String url = "";
            HttpSession session = req.getSession(false);
            // Get session ra neu khong co session account tuc la chua login thi response ve trang login.
            if (session != null && session.getAttribute("account") != null) {
                User user = (User) session.getAttribute("account");
                // set thong tin cua user vao bien requestScope user
                req.setAttribute("user", user);
                url = "profileStaff_VA.jsp";
            } else {
                // trang login
                url = "sign-in.jsp";
            }
            req.getRequestDispatcher(url).forward(req, reqs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse reps) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String action = req.getParameter("action") == null ? "" : req.getParameter("action");
        if (session != null && session.getAttribute("account") != null) {
            switch (action) {
                case "updateProfile":
                    updateProfile(req, reps);
                    break;
            }
            reps.sendRedirect("profile2");
        } else {
            reps.sendRedirect("sign-in.jsp");
        }
    }

    private void updateProfile(HttpServletRequest req, HttpServletResponse resp) {
        String url = "profile";
        try {
            // Get Paramater by input [Name]
            String name = req.getParameter("name");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            String genderS = req.getParameter("gender");
            String Dob = req.getParameter("Dob");
            String linkFace = req.getParameter("linkFacebook");
            String bankName = req.getParameter("bankName");
            String bankCode = req.getParameter("bankCode");
            String id = req.getParameter("accountId");

            HttpSession session = req.getSession(false);
            AccountDAO accountDAO = new AccountDAO();
            Account account = new Account();
//            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(Dob);
            // get gender neu nhu la nam thi Male co value thi do la Nam va nguoc lai.
            int gender = 0;
            if (genderS.contains("gender-male")) {
                gender = 1;
            }
            int accountId = Integer.parseInt(id);

            account.setIDAccount(accountId);
            account.setName(name);
            account.setPhoneNumber(phone);
            account.setGender(gender);
//            user.setDob(date);
            account.setIDEmail(email);
            account.setIDFacebook(linkFace);
            account.setBank(bankName);
            account.setBankNumber(bankCode);

            boolean result = accountDAO.updateProfile(account, Dob);
            // neu update user thanh cong thi tra ve userObject
            if (result) {
                account = accountDAO.getAccountById(accountId);
                session.setAttribute("account", account);
                // Nguoc lai update failed
            } else {
                String message = "Update user information error";
                req.setAttribute("updateError", message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
