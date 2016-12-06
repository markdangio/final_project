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
import model.ClassesActions;

/**
 *
 * @author hinesmj
 */
@WebServlet(name = "ClassesControl", urlPatterns = {"/classesControl"})
public class ClassesControl extends HttpServlet {

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
        
        if (action.equals("create")){
            handleAdd(request, response);
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
        String classId = UUID.randomUUID().toString();
        classId = classId.replace("-","");
        classId = classId.substring(0, 16);
        String name = request.getParameter("sellClassName");
        String subject = request.getParameter("sellClassSubject");
        int number = Integer.parseInt(request.getParameter("sellClassNumber"));
        int section = Integer.parseInt(request.getParameter("sellClassSection"));
        String professor = request.getParameter("sellClassProfessor");
        String description = request.getParameter("sellClassDescription");

        if (name == null || subject == null || number <= 0 || section <= 0
                || professor == null || description == null) {
            addMessage = "Improper add class request: " + name + subject + number + section + professor + description;
        } else if (name.trim().length() == 0) {
            addMessage = "Name field must not be blank";
        } else if (subject.trim().length() == 0) {
            addMessage = "Subject field must not be blank";
        } else if (number <= 0) {
            addMessage = "Number must be greater than zero";
        } else if (number <= 0) {
            addMessage = "Section must be greater than zero";
        } else if (professor.trim().length() == 0) {
            addMessage = "Professor field must not be blank";
        } else {
            // execute add transaction
            boolean addResult = ClassesActions.addClass(classId, name, subject, number, section, professor, description);
            addMessage = addResult ? "New class added" : "Class add failed";
        }
        session.setAttribute("addmessage", addMessage);
        if(addMessage.equals("New class added")){
            session.setAttribute("classId", classId);
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
