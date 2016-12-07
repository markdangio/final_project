/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedIn", false);
        session.setAttribute("userId", null);
        session.setAttribute("username", null);
        response.sendRedirect("index.jsp");
    }


}
