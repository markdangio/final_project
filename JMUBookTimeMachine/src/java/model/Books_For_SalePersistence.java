/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//import java.util.*;
import java.util.UUID;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Mitch
 */
public class Books_For_SalePersistence {

    /**
     * Add a book for sale record.
     *
     * @param books_For_Sale The books_For_Sale to be added
     * 
     * @return true if the database operation succeeded
     */
    public static boolean addBook_For_Sale(Books_For_Sale books_For_Sale) {//, Books books, User user) {
        DBHandler dbHandler = new DBHandler();

        try {

            String command = "INSERT INTO Books_for_Sale VALUES(";
            command += "'" + books_For_Sale.getSellerId() + "'";
            command += ", '" + books_For_Sale.getBookId() + "'";
            command += ", '" + books_For_Sale.getSaleId() + "'";
            command += ", '" + books_For_Sale.getPostedDate() + "'";
            command += ", " + books_For_Sale.getPrice();
            command += ", " + books_For_Sale.getSold();
            command += ", '" + books_For_Sale.getReserverId() + "')";

            int resultCount = dbHandler.doCommand(command);
            dbHandler.close();
            return (resultCount > 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Returns an ArrayList of all books for sale objects.
     *
     * @return an ArrayList of all books for sale objects
     */
    public static ArrayList<BookInfo> searchBook_For_Sale(ArrayList<Books> books, String userId) {
        ArrayList<BookInfo> result = new ArrayList<BookInfo>();

        for (Books tempBook : books) {

            String command = "SELECT * FROM Books_for_Sale WHERE bookId = ";
            command += "'" + tempBook.getBookId() + "'";
            command += " AND sold = 0";
            command += " AND sellerId != '" + userId + "'";

            // open a connection to the database and a Statement object
            try {
                DBHandler dbHandler = new DBHandler();
                ResultSet rs = dbHandler.doQuery(command);

                while (rs.next()) {
                    int i = 3; // 1st column
                    String bookIdB = tempBook.getBookId();
                    String titleB = tempBook.getTitle();
                    String authorB = tempBook.getAuthor();
                    int editionB = tempBook.getEdition();
                    String publisherB = tempBook.getPublisher();
                    String coverPhotoB = tempBook.getCoverPhoto();
                    String sellerId = rs.getString(1);
                    String saleId = rs.getString(i++);
                    String postedDate = rs.getString(i++);
                    double price = rs.getDouble(i++);
                    int sold = rs.getInt(i++);
                    String reserverId = rs.getString(i++);

                    BookInfo book = new BookInfo(bookIdB, titleB, authorB, editionB, publisherB, coverPhotoB, postedDate, price, sold, sellerId, saleId, reserverId);
                    result.add(book);
                }

                dbHandler.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        // return the result
        return result;
    }

    /**
     * Returns an ArrayList of all reserve book for sale objects.
     *
     * @return an ArrayList of all reserve book for sale objects
     */
    public static boolean reserveBook_For_Sale(String saleId, String reserverId) {
        DBHandler dbHandler = new DBHandler();
        try {

            String command = "UPDATE Books_for_Sale SET reserverId = ";
            command += "'" + reserverId + "'";
            command += " WHERE saleId = '" + saleId + "'";
            //command += " AND reserverId = NULL";

            int resultCount = dbHandler.doCommand(command);
            dbHandler.close();
            return (resultCount > 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Returns an ArrayList of all books for sale objects.
     *
     * @return an ArrayList of all  books for sale objects
     */
    public static boolean sellBooks_For_Sale(String saleId) {
        DBHandler dbHandler = new DBHandler();
        try {

            String command = "UPDATE Books_for_Sale SET sold = ";
            command += "'" + 1 + "'";
            command += " WHERE saleId = '" + saleId + "'";

            int resultCount = dbHandler.doCommand(command);
            dbHandler.close();
            return (resultCount > 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Returns an ArrayList of all search book for sale reserved objects.
     *
     * @return an ArrayList of all search book for sale reserved objects
     */
    public static ArrayList<BookInfo> searchBook_For_SaleReserved(String reserverId) {
        ArrayList<BookInfo> result = new ArrayList<BookInfo>();

        String command = "SELECT * FROM Books_for_Sale WHERE reserverId = ";
        command += "'" + reserverId + "'";

        // open a connection to the database and a Statement object
        try {
            DBHandler dbHandler = new DBHandler();
            ResultSet rs = dbHandler.doQuery(command);

            while (rs.next()) {
                int i = 3; // 1st column
                String bookIdB = rs.getString(2);

                Books book = BooksActions.getBook(bookIdB);

                String titleB = book.getTitle();
                String authorB = book.getAuthor();
                int editionB = book.getEdition();
                String publisherB = book.getPublisher();
                String coverPhotoB = book.getCoverPhoto();
                String sellerIdB = rs.getString(1);
                String saleId = rs.getString(i++);
                String postedDate = rs.getString(i++);
                double price = rs.getDouble(i++);
                int sold = rs.getInt(i++);
                String reserverIdB = rs.getString(i++);
                
                BookInfo bookInfo = new BookInfo(bookIdB, titleB, authorB, editionB, publisherB, coverPhotoB, postedDate, price, sold, sellerIdB, saleId, reserverIdB);
                result.add(bookInfo);
            }

            dbHandler.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // return the result
        return result;
    }

    /**
     * Returns an ArrayList of all search for books selling objects.
     *
     * @return an ArrayList of all search for books selling objects
     */
    public static ArrayList<BookInfo> searchBook_For_SaleSelling(String sellerId) {
        ArrayList<BookInfo> result = new ArrayList<BookInfo>();

        String command = "SELECT * FROM Books_for_Sale WHERE sellerId = ";
        command += "'" + sellerId + "'";

        // open a connection to the database and a Statement object
        try {
            DBHandler dbHandler = new DBHandler();
            ResultSet rs = dbHandler.doQuery(command);

            while (rs.next()) {
                int i = 3; // 1st column
                String bookIdB = rs.getString(2);

                Books book = BooksActions.getBook(bookIdB);

                String titleB = book.getTitle();
                String authorB = book.getAuthor();
                int editionB = book.getEdition();
                String publisherB = book.getPublisher();
                String coverPhotoB = book.getCoverPhoto();
                String sellerIdB = rs.getString(1);
                String saleId = rs.getString(i++);
                String postedDate = rs.getString(i++);
                double price = rs.getDouble(i++);
                int sold = rs.getInt(i++);
                String reserverId = rs.getString(i++);
                
                BookInfo bookInfo = new BookInfo(bookIdB, titleB, authorB, editionB, publisherB, coverPhotoB, postedDate, price, sold, sellerIdB, saleId, reserverId);
                result.add(bookInfo);
            }

            dbHandler.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // return the result
        return result;
    }
}
