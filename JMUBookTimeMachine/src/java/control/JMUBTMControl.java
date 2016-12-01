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
import servlet.LoginServlet;

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
        else if (action.equals("signup")){
            handleAdd(request, response);
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

    public void handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws IOException,
            ServletException {
        forwardRequest(request, response, "/home.jsp");
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet JMUBTMControl</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet JMUBTMControl at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }*/

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
        if (request.getParameter("adduser") != null) {
            handleAdd(request, response);
        }
    }
    
    /*
     * Add a user to the table.
     */
    private void handleAdd(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String addMessage = null;
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String avatar = request.getParameter("avatar");
        String birthday = request.getParameter("birthday");

        if (username == null || password == null || firstname == null || lastname == null
                || email == null || birthday == null) {
            addMessage = "Improper add user request";
        } else if (username.trim().length() == 0) {
            addMessage = "Userame field must not be blank";
        } else if (password.trim().length() == 0) {
            addMessage = "Password field must not be blank";
        } else if (firstname.trim().length() == 0) {
            addMessage = "First name field must not be blank";
        } else if (lastname.trim().length() == 0) {
            addMessage = "Last name field must not be blank";
        } else if (email.trim().length() == 0) {
            addMessage = "Email field must not be blank";
        } else if (birthday.trim().length() == 0) {
            addMessage = "Birthday field must not be blank";
        } else {
            // execute add transaction
            boolean addResult = UserActions.addUser(username, password, firstname, lastname, email, avatar, birthday);
            addMessage = addResult ? "New user added" : "User add failed";
        }
        session.setAttribute("addmessage", addMessage);
        if(addMessage.equals("New user added")){
            session.setAttribute("loggedIn", true);
            session.setAttribute("username", username);
            forwardRequest(request, response, "/home.jsp");
        }
        else{
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Results</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + session.getAttribute("addmessage") + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }

        }
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
