/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserActions;
import java.util.UUID;

/**
 *
 * @author Mitch
 */
@WebServlet(name = "JMUBTMControl", urlPatterns = {"/control"})
public class JMUBTMControl extends HttpServlet {

    /**
     * Handle an HTTP POST transaction for a drop or add.
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
     */
    
    @Override
    public void service(HttpServletRequest request,
            HttpServletResponse response) throws IOException,
            ServletException {

        String action = request.getParameter("action");
        
        if (action.equals("login")){
            forwardRequest(request, response, "/login");
        }
        else if (action.equals("logout")){
            forwardRequest(request, response, "/logout");
        }
        else{
            HttpSession session = request.getSession(true);
            if ((Boolean) session.getAttribute("loggedIn")){
                handleRequest(request, response);
            }
            else{
                response.sendRedirect("index.jsp");
            }
        }
    }
    /**
     * Handle an HTTP POST transaction.
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
     */
    public void handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws IOException,
            ServletException {
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

}
