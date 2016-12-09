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
     * Add a pet record.
     *
     * @param books_For_Sale The books_For_Sale to be added
     * @param book The book to be used to get the id
     * @return true iff the database operation succeeded
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
     * Add a pet record.
     *
     * @param username The user to be added
     * @param password The user's password
     * @return true iff the database operation succeeded
     *
    public static boolean checkUser(String username, String password) {
        DBHandler dbHandler = new DBHandler();
                
        String command = "SELECT * FROM User WHERE username = ";
        command += "'" + username + "'";
        command += " AND password = '" + password + "'";
        try {
            ResultSet resultCount = dbHandler.doQuery(command);
            
            System.out.println(resultCount);
            int i = 0;
            while(resultCount.next()) {
                i++;
            }
            dbHandler.close();
            return (i > 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }*/

    /**
     * Delete a pet from the Pet table.
     * 
     * @param pet The Pet to be deleted, identified by the name field only.
     * @return true iff the database operation succeeded
     *
    public static boolean deletePet(Pet pet) {
        DBCommandHandler dbCommandHandler = new DBCommandHandler();
        try {
            String command = "delete from pet where name = '" + pet.getName() + "'";
            int result = dbCommandHandler.doCommand(command);
            dbCommandHandler.close();
            return (result > 0);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }*/
    
    /**
     * Returns an ArrayList of all Pet objects.
     *
     * @return an ArrayList of all Pet objects
     */
    public static ArrayList<BookInfo> searchBook_For_Sale(ArrayList<Books> books) {
        ArrayList<BookInfo> result = new ArrayList<BookInfo>();
        
        for (Books tempBook : books) {

            String command = "SELECT * FROM Books_for_Sale WHERE bookId = ";
            command += "'" + tempBook.getBookId() + "'";
            command += " AND sold = 0";

            // open a connection to the database and a Statement object
            try {
                DBHandler dbHandler = new DBHandler();
                ResultSet rs = dbHandler.doQuery(command);

                while (rs.next()) {
                    int i = 4; // 1st column
                    String bookIdB = tempBook.getBookId();
                    String titleB = tempBook.getTitle();
                    String authorB = tempBook.getAuthor();
                    int editionB = tempBook.getEdition();
                    String publisherB = tempBook.getPublisher();
                    String coverPhotoB = tempBook.getCoverPhoto();
                    String sellerId = rs.getString(1);
                    String postedDate = rs.getString(i++);
                    double price = rs.getDouble(i++);
                    int sold = rs.getInt(i++);
                    
                    BookInfo book = new BookInfo(bookIdB, titleB, authorB, editionB, publisherB, coverPhotoB, postedDate, price, sold, sellerId);
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
     * Returns an ArrayList of all Pet objects.
     *
     * @return an ArrayList of all Pet objects
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
                int i = 4; // 1st column
                String bookIdB = rs.getString(2);
                
                Books book = BooksActions.getBook(bookIdB);
                
                String titleB = book.getTitle();
                String authorB = book.getAuthor();
                int editionB = book.getEdition();
                String publisherB = book.getPublisher();
                String coverPhotoB = book.getCoverPhoto();
                String sellerIdB = rs.getString(1);
                String postedDate = rs.getString(i++);
                double price = rs.getDouble(i++);
                int sold = rs.getInt(i++);
                BookInfo bookInfo = new BookInfo(bookIdB, titleB, authorB, editionB, publisherB, coverPhotoB, postedDate, price, sold, sellerIdB);
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
     * Returns an ArrayList of all Pet objects.
     *
     * @return an ArrayList of all Pet objects
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
                int i = 4; // 1st column
                String bookIdB = rs.getString(2);
                
                Books book = BooksActions.getBook(bookIdB);
                
                String titleB = book.getTitle();
                String authorB = book.getAuthor();
                int editionB = book.getEdition();
                String publisherB = book.getPublisher();
                String coverPhotoB = book.getCoverPhoto();
                String sellerIdB = rs.getString(1);
                String postedDate = rs.getString(i++);
                double price = rs.getDouble(i++);
                int sold = rs.getInt(i++);
                BookInfo bookInfo = new BookInfo(bookIdB, titleB, authorB, editionB, publisherB, coverPhotoB, postedDate, price, sold, sellerIdB);
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