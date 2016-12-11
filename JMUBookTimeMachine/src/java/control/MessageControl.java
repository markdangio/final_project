/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;
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
@WebServlet(name = "MessageControl", urlPatterns = {"/messageControl"})
public class MessageControl extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        
        if (action.equals("create")){
            handleAdd(request, response);
        }
        else if (action.equals("show")){
            session.setAttribute("toUserId", request.getParameter("toUserId"));
            showMessages(request, response);
        }
        else if (action.equals("showMessages")){
            showAllUsersMessage(request, response);
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
        if (request.getParameter("adduser") != null) {
            handleAdd(request, response);
        }
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
    private void showMessages(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String addMessage = null;
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String fromUserId = (String)session.getAttribute("userId");
        String toUserId = request.getParameter("toUserId");
        
        ArrayList<Message> messages = MessageActions.getMessages(toUserId, fromUserId);
        session.setAttribute("messages", messages);
        
        forwardRequest(request, response, "/message.jsp");
    }
    
    /*
     * Add a user to the table.
     */
    private void showAllUsersMessage(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String fromUserId = (String)session.getAttribute("userId");
        String toUserId = request.getParameter("toUserId");
        ArrayList<User> messagesUsers = MessageActions.showAllUsersMessage(toUserId, fromUserId);
        session.setAttribute("messagesUsers", messagesUsers);
        
        forwardRequest(request, response, "/messages.jsp");
    }
    
    /*
     * Add a user to the table.
     */
    private void handleAdd(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String addMessage = null;
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String fromUserId = (String)session.getAttribute("userId");
        String toUserId = (String)session.getAttribute("toUserId");
        String content = request.getParameter("content");
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeSent = sdf.format(today);

        if (content == null) {
            addMessage = "Improper add message request: " + content;
        } else {
            // execute add transaction
            String messageId = UUID.randomUUID().toString();
            messageId = messageId.replace("-","");
            messageId = messageId.substring(0, 16);
            
            boolean addResult = MessageActions.addMessage(messageId, toUserId, fromUserId, content, timeSent);
            addMessage = addResult ? "New message added" : "Message add failed" + messageId + toUserId + fromUserId + content + timeSent;
        }
        session.setAttribute("addmessage", addMessage);
        if(addMessage.equals("New message added")){
            forwardRequest(request, response, "/messages.jsp");
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
