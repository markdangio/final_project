/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.*;

/**
 *
 * @author hinesmj
 */
@WebServlet(name = "UserControl", urlPatterns = {"/userControl"})
public class UserControl extends HttpServlet {

    private final static Logger LOGGER
            = Logger.getLogger(UserControl.class.getCanonicalName());

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

        if (action.equals("signup")) {
            handleAdd(request, response);
        } else if (action.equals("checkUpdatePass")) {
            checkUserSecurity(request, response);
        } else if (action.equals("updatePass")) {
            changePass(request, response);
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
     * @Override protected void doGet(HttpServletRequest request,
     * HttpServletResponse response) throws ServletException, IOException {
     * processRequest(request, response);
    }
     */
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
     * @Override public String getServletInfo() { return "Short description";
     * }// </editor-fold>
     */
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private boolean checkFile(String fileName) {
        if (!fileName.endsWith(".jpg") || !fileName.endsWith(".jpeg")
                || !fileName.endsWith(".png") || !fileName.endsWith(".gif")) {
            //alert maybe?;
            return false;
        }
        else
        {
            return true;
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
        String userId = "";
        String username = request.getParameter("signUpUsername");
        String password = request.getParameter("signUpPassword");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String avatar = "";
        String birthday = request.getParameter("birthday");
        String securityAns = request.getParameter("signUpSecurityAnswer");

        InputStream filecontent = null;
        OutputStream out = null;
        //String tomcatBase = System.getProperty("catalina.home");

        //where the image will be saved
        final String path = "/Users/dangiomr/NetBeansProjects/File Uploader/web/images";
        //= tomcatBase + "/webapps/uploader/images";

        final Part filePart = request.getPart("avatar");
        final String fileName = getFileName(filePart);
        
        avatar = "/images/" + fileName; 
        if (checkFile(fileName)) {
            try {
            //out = new FileOutputStream(new File(path + File.separator
                //       + userName + "-" + fileName));
                filecontent = filePart.getInputStream();

                int read = 0;
                int size = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    size += read;
                    out.write(bytes, 0, read);
                }
                LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
                        new Object[]{fileName, path});

            } catch (FileNotFoundException fne) {

                LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                        new Object[]{fne.getMessage()});

            } finally {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
            }
        }

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
        } else {
            // execute add transaction
            userId = UUID.randomUUID().toString();
            userId = userId.replace("-", "");
            userId = userId.substring(0, 16);
            boolean addResult = UserActions.addUser(userId, password, firstname, lastname, email, avatar, birthday, username, securityAns);
            addMessage = addResult ? "New user added" : "User add failed" + userId + username + password + firstname + lastname + email + avatar + birthday + securityAns;
        }
        session.setAttribute("addmessage", addMessage);
        if (addMessage.equals("New user added")) {
            session.setAttribute("loggedIn", true);
            User userObj = UserActions.getUser(userId);
            session.setAttribute("userObj", userObj);
            forwardRequest(request, response, "/home.jsp");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out1 = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out1.println("<!DOCTYPE html>");
                out1.println("<html>");
                out1.println("<head>");
                out1.println("<title>Results</title>");
                out1.println("</head>");
                out1.println("<body>");
                out1.println("<h1>" + session.getAttribute("addmessage") + "</h1>");
                out1.println("</body>");
                out1.println("</html>");
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
        String username = request.getParameter("loginUsername");
        String securityAns = request.getParameter("recoverySQ");

        if (username == null || securityAns == null) {
            securityMessage = "Improper check user security request: " + username + securityAns;
        } else if (username.trim().length() == 0) {
            securityMessage = "Userame field must not be blank";
        } else if (securityAns.trim().length() == 0) {
            securityMessage = "Security Answer field must not be blank";
        } else {

            boolean checkSecurityResult = UserActions.checkUserSecurity(username, securityAns);
            securityMessage = checkSecurityResult ? "Reset Password" : "Cannot Reset Password" + username + securityAns;
        }
        session.setAttribute("securityMessage", securityMessage);
        if (securityMessage.equals("Reset Password")) {
            changePass(request, response);
        } else {
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

    private void changePass(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String changeMessage = null;
        HttpSession session = request.getSession(true);

        String username = request.getParameter("loginUsername");
        String password = request.getParameter("loginPassword");

        boolean checkResult = UserActions.changePass(username, password);
        changeMessage = checkResult ? "Password changed" : "Password not changed" + username + password;
        session.setAttribute("changeMessage", changeMessage);
        if (changeMessage.equals("Password changed")) {
            forwardRequest(request, response, "/login");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Results</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + session.getAttribute("changeMessage") + "</h1>");
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
