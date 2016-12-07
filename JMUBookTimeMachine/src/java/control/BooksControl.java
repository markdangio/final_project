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
import model.BooksActions;

/**
 *
 * @author hinesmj
 */
@WebServlet(name = "BooksControl", urlPatterns = {"/booksControl"})
public class BooksControl extends HttpServlet {

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
        else if (action.equals("check")) {
            handleCheck(request, response);
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
        String bookId = UUID.randomUUID().toString();
        bookId = bookId.replace("-","");
        bookId = bookId.substring(0, 16);
        String title = request.getParameter("sellTitle");
        String author = request.getParameter("sellAuthor");
        int edition = Integer.parseInt(request.getParameter("sellEdition"));
        String publisher = request.getParameter("sellPublisher");
        String coverPhoto = request.getParameter("sellCoverPhoto");

        if (title == null || author == null || edition <= 0 || publisher == null) {
            addMessage = "Improper add user request: " + title + author + edition + publisher;
        } else if (title.trim().length() == 0) {
            addMessage = "Userame field must not be blank";
        } else if (author.trim().length() == 0) {
            addMessage = "Password field must not be blank";
        } else if (edition <= 0) {
            addMessage = "Edition field must not be less than one";
        } else {
            // execute add transaction
            String classId = (String)session.getAttribute("classId");
            boolean addResult = BooksActions.addBook(bookId, title, author, edition, publisher, coverPhoto, classId);
            addMessage = addResult ? "New book added" : "Book add failed";
        }
        session.setAttribute("addmessage", addMessage);
        if(addMessage.equals("New book added")){
            session.setAttribute("bookId", bookId);
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
    private void handleCheck(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String checkMessage = null;
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String title = request.getParameter("sellTitle");
        String author = request.getParameter("sellAuthor");
        int edition = Integer.parseInt(request.getParameter("sellEdition"));
        String publisher = request.getParameter("sellPublisher");
        String coverPhoto = request.getParameter("sellCoverPhoto");

        if (title == null || author == null || edition <= 0 || publisher == null) {
            checkMessage = "Improper add user request: " + title + author + edition + publisher;
        } else if (title.trim().length() == 0) {
            checkMessage = "Userame field must not be blank";
        } else if (author.trim().length() == 0) {
            checkMessage = "Password field must not be blank";
        } else if (edition <= 0) {
            checkMessage = "Edition field must not be less than one";
        } else {
            // execute add transaction
            boolean checkResult = BooksActions.checkBook(title, author, edition, publisher);
            checkMessage = checkResult ? "Book found" : "Book not found";
        }
        session.setAttribute("checkMessage", checkMessage);
        if(checkMessage.equals("Book found")){
            String bookId = BooksActions.getBookId(title, author, edition, publisher);
            session.setAttribute("bookId", bookId);
        }
        else{
            handleAdd(request, response);
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
