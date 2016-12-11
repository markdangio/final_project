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
    
    /**
     * Handle an HTTP POST transaction for showing messages.
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
     */
    private void showMessages(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String fromUserId = (String)session.getAttribute("userId");
        String toUserId = request.getParameter("toUserId");
        
        ArrayList<Message> messages = MessageActions.getMessages(toUserId, fromUserId);
        session.setAttribute("messages", messages);
        
        forwardRequest(request, response, "/message.jsp");
    }
    
    /**
     * Handle an HTTP POST transaction for showing all user messages.
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
     */
    private void showAllUsersMessage(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String userId = (String)session.getAttribute("userId");
        ArrayList<User> messagesUsers = MessageActions.showAllUsersMessage(userId);
        session.setAttribute("messagesUsers", messagesUsers);
        
        forwardRequest(request, response, "/messages.jsp");
    }
    
    /**
     * Handle an HTTP POST transaction for adding a message.
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
     */
    private void handleAdd(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String addMessageMessage = null;
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String fromUserId = (String)session.getAttribute("userId");
        String toUserId = (String)session.getAttribute("toUserId");
        String content = request.getParameter("content");
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeSent = sdf.format(today);

        if (content == null) {
            addMessageMessage = "Improper add message request: " + content;
        } else {
            // execute add transaction
            String messageId = UUID.randomUUID().toString();
            messageId = messageId.replace("-","");
            messageId = messageId.substring(0, 16);
            
            boolean addResult = MessageActions.addMessage(messageId, toUserId, fromUserId, content, timeSent);
            addMessageMessage = addResult ? "New message added" : "Message add failed" + messageId + toUserId + fromUserId + content + timeSent;
        }
        
        if(addMessageMessage.equals("New message added")){
            session.setAttribute("addMessageMessage", null);
            forwardRequest(request, response, "/messages.jsp");
        }
        else{
            session.setAttribute("addMessageMessage", addMessageMessage);
            forwardRequest(request, response, "/message.jsp?action=show?toUserId=" + toUserId);

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
