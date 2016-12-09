/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;

/**
 *
 * @author hinesmj
 */
@WebServlet(name = "UserControl", urlPatterns = {"/userControl"})
public class UserControl extends HttpServlet {

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
        
        if (action.equals("signup")){
            handleAdd(request, response);
        }
        else if (action.equals("checkUpdatePass")){
            checkUserSecurity(request, response);
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
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     *
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>*/

    /*
     * Add a user to the table.
     */
    private void handleAdd(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String addMessage = null;
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String userId = "";
        String username = request.getParameter("signUpUsername");
        String password = request.getParameter("signUpPassword");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String avatar = request.getParameter("avatar");
        String birthday = request.getParameter("birthday");
        String securityAns = request.getParameter("signUpSecurityAnswer");
        

        if (username == null || password == null || firstname == null || lastname == null
                || email == null || birthday == null || securityAns == null) {
            addMessage = "Improper add user request: " + username + password + firstname + lastname + email + birthday + securityAns;
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
        } else if (securityAns.trim().length() == 0) {
            addMessage = "Security Answer field must not be blank";
        }else {
            // execute add transaction
            userId = UUID.randomUUID().toString();
            userId = userId.replace("-","");
            userId = userId.substring(0, 16);
            
            boolean addResult = UserActions.addUser(userId, password, firstname, lastname, email, avatar, birthday, username, securityAns);
            addMessage = addResult ? "New user added" : "User add failed" + userId + username + password + firstname + lastname + email + avatar + birthday + securityAns;
        }
        session.setAttribute("addmessage", addMessage);
        if(addMessage.equals("New user added")){
            session.setAttribute("loggedIn", true);
            User userObj = UserActions.getUser(userId);
            session.setAttribute("userObj", userObj);
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
     * Add a user to the table.
     */
    private void checkUserSecurity(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String securityMessage = null;
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String username = request.getParameter("recoveryUserName");
        String securityAns = request.getParameter("recoverySQ");
        

        if (username == null || securityAns == null) {
            securityMessage = "Improper check user security request: " + username + securityAns;
        } else if (username.trim().length() == 0) {
            securityMessage = "Userame field must not be blank";
        } else if (securityAns.trim().length() == 0) {
            securityMessage = "Security Answer field must not be blank";
        }else {
            
            boolean checkSecurityResult = UserActions.checkUserSecurity(username, securityAns);
            securityMessage = checkSecurityResult ? "Reset Password" : "Cannot Reset Password" + username+ securityAns;
        }
        session.setAttribute("securityMessage", securityMessage);
        if(securityMessage.equals("Reset Password")){
            forwardRequest(request, response, "/changePass.jsp");
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
                out.println("<h1>" + session.getAttribute("securityMessage") + "</h1>");
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
