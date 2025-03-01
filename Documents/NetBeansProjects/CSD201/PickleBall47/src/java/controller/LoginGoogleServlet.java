/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import commons.GooglePojo;
import commons.GoogleUtils;
import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Minh Trung
 */
@WebServlet(name = "LoginGoogleServlet", urlPatterns = {"/login-google"})
public class LoginGoogleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginGoogleServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {

            RequestDispatcher dis = request.getRequestDispatcher("sign-in.jsp");
            dis.forward(request, response); 
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
            AccountDAO accountdal = new AccountDAO();
            Account accountNew = new Account();
            if (accountdal.checkEmail(googlePojo.getEmail()) == false) {

                accountNew.setIDAccount(accountdal.getLengthAccount() + 1);
                accountNew.setName(googlePojo.getName());
                accountNew.setIDEmail(googlePojo.getEmail());
                accountNew.setRole(1);
                accountdal.insertAccountByGoogle(accountNew);

                session.setAttribute("account", accountNew);
            } else {
                accountNew = accountdal.getAccountByEmail(googlePojo.getEmail());
                session.setAttribute("account", accountNew);
            }
            RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
            dis.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
