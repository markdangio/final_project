/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Books;
import model.Books_For_SaleActions;
import model.BooksActions;
import model.ClassesActions;
import model.UserActions;

/**
 *
 * @author hinesmj
 */
@WebServlet(name = "BBC", urlPatterns = {"/bbc"})
public class BBC extends HttpServlet {

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
        
        if (action.equals("check")){
            handleClassCheck(request, response);
        } else if (action.equals("search")){
            handleBookSearch(request, response);
        }
    }

    /*
     * Add a user to the table.
     */
    private void handleClassAdd(HttpServletRequest request,
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
                || professor == null) {
            addMessage = "Improper add class request";
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
            handleBookCheck(request, response);
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
    private void handleClassCheck(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String checkMessage = null;
        HttpSession session = request.getSession(true);

        String name = request.getParameter("sellClassName");
        String subject = request.getParameter("sellClassSubject");
        int number = Integer.parseInt(request.getParameter("sellClassNumber"));
        int section = Integer.parseInt(request.getParameter("sellClassSection"));
        String professor = request.getParameter("sellClassProfessor");
        String description = request.getParameter("sellClassDescription");

        if (name == null || subject == null || number <= 0 || section <= 0
                || professor == null) {
            checkMessage = "Improper add class request: " + name + subject + number + section + professor + description;
        } else if (name.trim().length() == 0) {
            checkMessage = "Name field must not be blank";
        } else if (subject.trim().length() == 0) {
            checkMessage = "Subject field must not be blank";
        } else if (number <= 0) {
            checkMessage = "Number must be greater than zero";
        } else if (number <= 0) {
            checkMessage = "Section must be greater than zero";
        } else if (professor.trim().length() == 0) {
            checkMessage = "Professor field must not be blank";
        } else {
            // execute add transaction
            boolean checkResult = ClassesActions.checkClass(name, subject, number, section, professor);
            checkMessage = checkResult ? "Class found" : "Class not found";
        }
        session.setAttribute("checkMessage", checkMessage);
        if(checkMessage.equals("Class found")){
            String classId = ClassesActions.getClassId(name, subject, number, section, professor);
            session.setAttribute("classId", classId);
            handleBookCheck(request, response);
        }
        else{
            handleClassAdd(request, response);
        }
    }
    
    /*
     * Add a user to the table.
     */
    private void handleBookAdd(HttpServletRequest request,
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
            addMessage = addResult ? "New book added" : "Book add failed" + bookId + title + author + edition + publisher + coverPhoto + classId;
        }
        session.setAttribute("addmessage", addMessage);
        if(addMessage.equals("New book added")){
            session.setAttribute("bookId", bookId);
            handleBook_For_SaleAdd(request, response);
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
    private void handleBookCheck(HttpServletRequest request,
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
            handleBook_For_SaleAdd(request, response);
        }
        else{
            handleBookAdd(request, response);
        }
    }
    
    private void handleBookSearch(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String searchMessage = null;
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int edition = Integer.parseInt(request.getParameter("edition"));
        String publisher = request.getParameter("publisher");

        ArrayList<Books> checkResult = BooksActions.searchBook(title, author, edition, publisher);
        session.setAttribute("bookResults", checkResult);
        forwardRequest(request, response, "/results.jsp");
    }
    
    /*
     * Add a user to the table.
     */
    private void handleBook_For_SaleAdd(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String addMessage = null;
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String saleId = UUID.randomUUID().toString();
        saleId = saleId.replace("-","");
        saleId = saleId.substring(0, 16);
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String postedDate = sdf.format(today);
        double price = Double.parseDouble(request.getParameter("sellPrice"));
        int sold = 0;

        if (price <= 0) {
            addMessage = "Improper add books_for_sale request: " + price;
        } else {
            // execute add transaction
            
            String sellerId = (String)session.getAttribute("userId");
            String bookId = (String)session.getAttribute("bookId");
            
            boolean addResult = Books_For_SaleActions.addBooks_For_Sale(sellerId, bookId, saleId, postedDate, price, sold);
            addMessage = addResult ? "New books_for_sale added" : "Books_for_sale add failed " + sellerId + bookId + saleId + postedDate + price + sold;
        }
        session.setAttribute("addmessage", addMessage);
        if(addMessage.equals("New books_for_sale added")){
            forwardRequest(request, response, "/profile.jsp");
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
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

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
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
