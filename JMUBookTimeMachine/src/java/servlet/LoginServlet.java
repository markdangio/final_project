/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
//import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ((username.equals("massarmh") || username.equals("dangiomr") || username.equals("hinesmj")) && password.equals("websie")) {
            HttpSession session = request.getSession(true);
            session.setAttribute("loggedIn", true);
            session.setAttribute("username", username);
            //response.sendRedirect("index.jsp");
            String nextPage = "/home.jsp";
            RequestDispatcher dispatcher = 
                          getServletContext().getRequestDispatcher(nextPage);
            dispatcher.forward(request, response);
        }
        else {
            //response.sendRedirect("login.jsp");
            String nextPage = "/index.html";
            RequestDispatcher dispatcher = 
                          getServletContext().getRequestDispatcher(nextPage);
            dispatcher.forward(request, response);
        }
    }

}
