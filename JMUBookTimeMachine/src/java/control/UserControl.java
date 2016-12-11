/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.*;

@MultipartConfig
/**
 *
 * @author hinesmj
 */
@WebServlet(name = "UserControl", urlPatterns = {"/userControl"})
public class UserControl extends HttpServlet {

    private final static Logger LOGGER
            = Logger.getLogger(UserControl.class.getCanonicalName());

    /**
     * Handle an HTTP POST transaction for signing up, checking security question, and changing password.
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

    /**
     * Returns true if the file has the correct extension
     *
     * @return a boolean if the file has the right extension
     *
     */
    private boolean checkFile(String fileName) {
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("gif")) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Adds a User to the database
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
     */
    private void handleAdd(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String addUserMessage = "";
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String userId = "";
        String username = request.getParameter("signUpUsername");
        String password = request.getParameter("signUpPassword");
        password = hashPassword(password);
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String avatar = "";
        String birthday = request.getParameter("birthday");
        String securityAns = request.getParameter("signUpSecurityAnswer");

        InputStream filecontent = null;
        OutputStream out = null;

        String tomcatBase = System.getProperty("catalina.home");
        //where the image will be saved
        String path = tomcatBase + "/webapps/uploader/images";
        //= "/Users/dangiomr/NetBeansProjects/final_project/JMUBookTimeMachine/web/images";

        Part filePart = request.getPart("file");
        String fileName = getFileName(filePart);

        if (fileName == null || fileName.equals("") || filePart == null) {
            avatar = "";
        } 
        else if (checkFile(fileName)) {
            {
                avatar = "images/" + username + "-" + fileName;

                try {
                    out = new FileOutputStream(new File(path + "/" + username + "-" +fileName));
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
        }
        else
        {
            avatar = "";
        }
        if (username == null || password == null || firstname == null || lastname == null
                || email == null || birthday == null || securityAns == null) {
            addUserMessage = "Improper add user request: " + username + password + firstname + lastname + email + birthday + securityAns;
        } else if (username.trim().length() == 0) {
            addUserMessage = "Userame field must not be blank";
        } else if (password.trim().length() == 0) {
            addUserMessage = "Password field must not be blank";
        } else if (firstname.trim().length() == 0) {
            addUserMessage = "First name field must not be blank";
        } else if (lastname.trim().length() == 0) {
            addUserMessage = "Last name field must not be blank";
        } else if (email.trim().length() == 0) {
            addUserMessage = "Email field must not be blank";
        } else if (birthday.trim().length() == 0) {
            addUserMessage = "Birthday field must not be blank";
        } else if (securityAns.trim().length() == 0) {
            addUserMessage = "Security Answer field must not be blank";
        } else {
            // execute add transaction
            userId = UUID.randomUUID().toString();
            userId = userId.replace("-", "");
            userId = userId.substring(0, 16);
            boolean addResult = UserActions.addUser(userId, firstname, lastname, email, avatar, birthday, username, securityAns, password);
            addUserMessage = addResult ? "New user added" : "User add failed" + userId + username + password + firstname + lastname + email + avatar + birthday + securityAns;
        }
        
        if (addUserMessage.equals("New user added")) {
            session.setAttribute("addUserMessage", null);
            session.setAttribute("loggedIn", true);
            session.setAttribute("userId", userId);
            forwardRequest(request, response, "/home.jsp");
        } else {
            session.setAttribute("addUserMessage", addUserMessage);
            forwardRequest(request, response, "/index.jsp");
        }
    }

    /**
     * Checks to see if the users security answer matches the database
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
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
        
        if (securityMessage.equals("Reset Password")) {
            session.setAttribute("securityMessage", null);
            changePass(request, response);
        } else {
            session.setAttribute("securityMessage", securityMessage);
            forwardRequest(request, response, "/recovery.jsp");
        }
    }
    
    /**
     * Changes the password of the user
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
     */
    private void changePass(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String changeMessage = null;
        HttpSession session = request.getSession(true);

        String username = request.getParameter("loginUsername");
        String password = request.getParameter("loginPassword");
        password = hashPassword(password);

        boolean checkResult = UserActions.changePass(username, password);
        changeMessage = checkResult ? "Password changed" : "Password not changed" + username + password;
        
        if (changeMessage.equals("Password changed")) {
            session.setAttribute("changeMessage", null);
            forwardRequest(request, response, "/login");
        } else {
            session.setAttribute("changeMessage", changeMessage);
            forwardRequest(request, response, "/recovery.jsp");
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

    /**
     * Hashes the password
     *
     * @param password the password inputted
     * @return String of hashed password
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