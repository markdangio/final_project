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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
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
@WebServlet(name = "BBC", urlPatterns = {"/bbc"})
public class BBC extends HttpServlet {
    
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
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        
        if (action.equals("check")){
            handleClassCheck(request, response);
        } else if (action.equals("search")){
            handleBookSearch(request, response);
        } else if (action.equals("reserve")){
            handleBookReserve(request, response);
        } else if (action.equals("sold")){
            session.setAttribute("saleId", request.getParameter("saleId"));
            handleBook_For_SaleSell(request, response);
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
     * Handle file checking.
     *
     * @param fileName string
     */
    private boolean checkFile(String fileName) {
        if (!fileName.endsWith(".jpg") || !fileName.endsWith(".jpeg")
                || !fileName.endsWith(".png") || !fileName.endsWith(".gif")) {
            //alert maybe?;
            return false;
        } else {
            return true;
        }
    }

    /*
     * Add a user to the table.
     */
    private void handleClassAdd(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String addClassMessage = null;
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String classId = UUID.randomUUID().toString();
        classId = classId.replace("-","");
        classId = classId.substring(0, 16);
        String name = request.getParameter("sellClassName");
        String subject = request.getParameter("sellClassSubject");
        int number = 0;
        int section = 0;
        try{
            number = Integer.parseInt(request.getParameter("sellClassNumber"));
            section = Integer.parseInt(request.getParameter("sellClassSection"));
        }
        catch(NumberFormatException e){}
        
        String professor = request.getParameter("sellClassProfessor");
        String description = request.getParameter("sellClassDescription");

        if (name == null || subject == null || professor == null) {
            addClassMessage = "No Class Field can be left empty";
        } else if (name.trim().length() == 0) {
            addClassMessage = "Class Name field must not be blank";
        } else if (subject.trim().length() == 0) {
            addClassMessage = "Class Subject field must not be blank";
        } else if (number <= 0) {
            addClassMessage = "Class Number must be greater than zero";
        } else if (section <= 0) {
            addClassMessage = "Class Section must be greater than zero";
        } else if (professor.trim().length() == 0) {
            addClassMessage = "Class Professor field must not be blank";
        } else {
            // execute add transaction
            boolean addResult = ClassesActions.addClass(classId, name, subject, number, section, professor, description);
            addClassMessage = addResult ? "New class added" : "Class was not added successfully";
        }
        
        if(addClassMessage.equals("New class added")){
            session.setAttribute("addClassMessage", null);
            session.setAttribute("classId", classId);
            handleBookCheck(request, response);
        }
        else{
            session.setAttribute("addClassMessage", addClassMessage);
            forwardRequest(request, response, "/home.jsp");

        }
    }
    
    /**
     * Handle a class check.
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
     */
    private void handleClassCheck(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String checkMessage = null;
        HttpSession session = request.getSession(true);

        String name = request.getParameter("sellClassName");
        String subject = request.getParameter("sellClassSubject");
        int number = 0;
        int section = 0;
        try{
            number = Integer.parseInt(request.getParameter("sellClassNumber"));
            section = Integer.parseInt(request.getParameter("sellClassSection"));
        }
        catch(NumberFormatException e){}
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
    
    /**
     * Handle add a book.
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
     */
    private void handleBookAdd(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String addBookMessage = null;
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String bookId = UUID.randomUUID().toString();
        bookId = bookId.replace("-","");
        bookId = bookId.substring(0, 16);
        String title = request.getParameter("sellTitle");
        String author = request.getParameter("sellAuthor");
        int edition = 0;
        try{
            edition = Integer.parseInt(request.getParameter("sellEdition"));
        }
        catch(NumberFormatException e){}
        
        String publisher = request.getParameter("sellPublisher");
        String coverPhoto = request.getParameter("sellCoverPhoto");
        
        
        InputStream filecontent = null;
        OutputStream out = null;
        //String tomcatBase = System.getProperty("catalina.home");

        //where the image will be saved
        final String path = "/Users/dangiomr/NetBeansProjects/final_project/web/images";
        //= tomcatBase + "/webapps/uploader/images";

        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);

        coverPhoto = "/images/" + fileName;
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

        if (title == null || author == null || edition <= 0 || publisher == null) {
            addBookMessage = "No Book field can be left empty";
        } else if (title.trim().length() == 0) {
            addBookMessage = "Book Title field must not be blank";
        } else if (author.trim().length() == 0) {
            addBookMessage = "Book Author field must not be blank";
        } else if (edition <= 0) {
            addBookMessage = "Book Edition field must not be less than one";
        } else {
            // execute add transaction
            String classId = (String)session.getAttribute("classId");
            boolean addResult = BooksActions.addBook(bookId, title, author, edition, publisher, coverPhoto, classId);
            addBookMessage = addResult ? "New book added" : "Book was not added successfully";
        }
        
        if(addBookMessage.equals("New book added")){
            session.setAttribute("addBookMessage", null);
            session.setAttribute("bookId", bookId);
            handleBook_For_SaleAdd(request, response);
        }
        else{
            session.setAttribute("addBookMessage", addBookMessage);
            forwardRequest(request, response, "/home.jsp");
        }
    }

    /**
     * Handle when a book is added.
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
     */
    private void handleBookCheck(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String checkMessage = null;
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String title = request.getParameter("sellTitle");
        String author = request.getParameter("sellAuthor");
        int edition = 0;
        try{
            edition = Integer.parseInt(request.getParameter("sellEdition"));
        }
        catch(NumberFormatException e){}
        
        String publisher = request.getParameter("sellPublisher");
        String coverPhoto = request.getParameter("sellCoverPhoto");

        if (title == null || author == null || publisher == null) {
            checkMessage = "Improper check book request: " + title + author + edition + publisher;
        } else if (title.trim().length() == 0) {
            checkMessage = "Book Title field must not be blank";
        } else if (author.trim().length() == 0) {
            checkMessage = "Book Author field must not be blank";
        } else if (edition <= 0) {
            checkMessage = "Book Edition field must not be less than one";
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
    
    /**
     * Handle a Book search.
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
     */
    private void handleBookSearch(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String searchmessage = null;
        HttpSession session = request.getSession(true);
        String userId = (String)session.getAttribute("userId");

        // get add-user request parameters
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int edition = 0;
        try{
            edition = Integer.parseInt(request.getParameter("edition"));
        }
        catch(NumberFormatException e){}
        
        String publisher = request.getParameter("publisher");
        /*
        String className = request.getParameter("className");
        String classSubject = request.getParameter("classSubject");
        int classNumber = 0;
        int classSection = 0;
        try{
            classNumber = Integer.parseInt(request.getParameter("classNumber"));
            classSection = Integer.parseInt(request.getParameter("classSection"));
        }
        catch(NumberFormatException e) {}
        
        String classProfessor = request.getParameter("classclassProfessor");
        */
        if (title == null || author == null || publisher == null) { // || className == null || classSubject == null || classProfessor == null) {
            searchmessage = "Improper check book request: " + title + author + edition + publisher;
        } else if (title.trim().length() == 0) {
            searchmessage = "Book Title field must not be blank";
        } else if (author.trim().length() == 0) {
            searchmessage = "Book Author field must not be blank";
        } else if (edition <= 0) {
            searchmessage = "Book Edition field must not be less than one";
        } else if (publisher.trim().length() == 0) {
            searchmessage = "Book Publisher field must not be blank";
        }/*else if (className.trim().length() == 0) {
            searchmessage = "Class Name field must not be blank";
        } else if (classSubject.trim().length() == 0) {
            searchmessage = "Class Subject field must not be blank";
        }else if (classNumber <= 0) {
            searchmessage = "Class Number field must not be less than one";
        }else if (classSection <= 0) {
            searchmessage = "Class Section field must not be less than one";
        }else if (classProfessor.trim().length() == 0) {
            searchmessage = "Class Professir field must not be blank";
        }*/else {
            // execute add transaction
            ArrayList<Books> bookResults = BooksActions.searchBook(title, author, edition, publisher);
            ArrayList<BookInfo> bookSaleResults = Books_For_SaleActions.searchBook_For_Sale(bookResults, userId);
            session.setAttribute("bookResults", bookSaleResults);
            
            searchmessage = "Book search complete";
        }
        
        if(searchmessage.equals("Book search complete")){
            session.setAttribute("searchmessage", null);
            forwardRequest(request, response, "/results.jsp");
        }
        else{
            session.setAttribute("searchmessage", searchmessage);
            forwardRequest(request, response, "/home.jsp");
        }
    }
    
    /**
     * Handle a book being reserved.
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
     */
    private void handleBookReserve(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String reserveMessage = null;
        HttpSession session = request.getSession(true);
        
        String saleId = request.getParameter("saleId");
        String reserverId = request.getParameter("reserverId");
        
        boolean checkResult = Books_For_SaleActions.reserveBook_For_Sale(saleId, reserverId);
        reserveMessage = checkResult ? "Book reserved" : "Book not reserved" + saleId + reserverId;
        session.setAttribute("reserveMessage", reserveMessage);
        if(reserveMessage.equals("Book reserved")){
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
                out.println("<h1>" + session.getAttribute("reserveMessage") + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
    
    /**
     * Handle adding a new book.
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
     */
    private void handleBook_For_SaleAdd(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String addBFSMessage = null;
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String saleId = UUID.randomUUID().toString();
        saleId = saleId.replace("-","");
        saleId = saleId.substring(0, 16);
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String postedDate = sdf.format(today);
        int edition = 0;
        try{
            edition = Integer.parseInt(request.getParameter("sellEdition"));
        }
        catch(NumberFormatException e){}
        
        double price = Double.parseDouble(request.getParameter("sellPrice"));
        int sold = 0;
        String reserverId = null;

        if (price <= 0) {
            addBFSMessage = "Book Price cannot be below zero";
        } else {
            // execute add transaction
            
            String sellerId = (String)session.getAttribute("userId");
            String bookId = (String)session.getAttribute("bookId");
            
            boolean addResult = Books_For_SaleActions.addBooks_For_Sale(sellerId, bookId, saleId, postedDate, price, sold, reserverId);
            addBFSMessage = addResult ? "New books_for_sale added" : "Book was not posted for sale successfully";
        }
        
        if(addBFSMessage.equals("New books_for_sale added")){
            session.setAttribute("addBFSMessage", null);
            forwardRequest(request, response, "/profile.jsp");
        }
        else{
            session.setAttribute("addBFSMessage", addBFSMessage);
            forwardRequest(request, response, "/home.jsp");
        }
    }
    
    /**
     * Handles whether a book has sold or not.
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
     */
    private void handleBook_For_SaleSell(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String sellBFSMessage = null;
        HttpSession session = request.getSession(true);

        // get add-user request parameters
        String saleId = (String)session.getAttribute("saleId");
            
        boolean addResult = Books_For_SaleActions.sellBooks_For_Sale(saleId);
        sellBFSMessage = addResult ? "books_for_sale sold" : "Book was not sold successfully";
        
        if(sellBFSMessage.equals("books_for_sale sold")){
            session.setAttribute("sellBFSMessage", null);
        }
        else{
            session.setAttribute("sellBFSMessage", sellBFSMessage);
        }
        
        forwardRequest(request, response, "/profile.jsp");
    }
    
    /*
     * Forward this request to another component. 
     *
     * @param request The HTTP request object
     * @param response The HTTP resonse object
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
