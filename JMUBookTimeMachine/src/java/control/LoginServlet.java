/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.RequestDispatcher;
//import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserActions;

/**
 *
 * @author Mitch
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        
        String username = request.getParameter("loginUsername");
        String password = request.getParameter("loginPassword");
        password = hashPassword(password);

        if (UserActions.checkUser(username, password)) {
            session.setAttribute("checkLoginMessage", null);
            loginUser(request, response, username, password);
        }
        else {
            session.setAttribute("checkLoginMessage", "Cannot successfully log in: " + username);
            forwardRequest(request, response, "/index.jsp");
        }
    }
    
    /**
     * Handle login user.
     * 
     * @param request The HTTP request object
     * @param response The HTTP resonse object
     * @param username  String
     * @param password  String
     */
    public void loginUser(HttpServletRequest request, HttpServletResponse response, String username, String password)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedIn", true);
        session.setAttribute("username", username);
        session.setAttribute("userId", UserActions.getUserId(username));
        forwardRequest(request, response, "/home.jsp");
    }
    
    /*
     * Forward this request to another component. 
     */
    private void forwardRequest(HttpServletRequest request,
            HttpServletResponse response, String forwardUrl)
            throws IOException, ServletException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                forwardUrl);
        dispatcher.forward(request, response);
    }
    /**
     * Hash password
     *
     * @param password String
     */
    private static String hashPassword(String password) {

        String digest;
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.reset();
            byte[] bytes = md.digest(password.getBytes());
            digest = new BigInteger(1, bytes).toString(16);
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
            digest = null;
        }
        return digest;
    }

}
