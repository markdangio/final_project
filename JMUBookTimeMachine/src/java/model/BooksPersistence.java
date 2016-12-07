/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.*;
import java.sql.*;

/**
 *
 * @author Mitch
 */
public class BooksPersistence {

    /**
     * Add a pet record.
     *
     * @param book The book to be added
     * @param classes The class to be used to get the id
     * @return true iff the database operation succeeded
     */
    public static boolean addBook(Books book) {//, Classes classes) {
        DBHandler dbHandler = new DBHandler();
        
        try {
            String command = "INSERT INTO Books VALUES(";
            command += "'" + book.getBookId() + "'";
            command += ", '" + book.getTitle() + "'";
            command += ", '" + book.getAuthor() + "'";
            command += ", " + book.getEdition();
            command += ", '" + book.getPublisher() + "'";

            command += book.getCoverPhoto().equals("") 
                    ? ", NULL" 
                    : ", '" + book.getCoverPhoto() + "'";

            command += ", '" + book.getClassId() + "'";
            
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
     */
    public static boolean checkBook(String title, String author, int edition, String publisher) {
        DBHandler dbHandler = new DBHandler();
                
        String command = "SELECT * FROM Books WHERE title = ";
        command += "'" + title + "'";
        command += " AND author = '" + author + "'";
        command += " AND edition = " + edition;
        command += " AND publisher = '" + publisher + "'";
        try {
            ResultSet resultCount = dbHandler.doQuery(command);
            
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
    }

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
    public static String getBookId(String title, String author, int edition, String publisher) {
        String result = "";
        
        String command = "SELECT bookId FROM Books WHERE title = ";
        command += "'" + title + "'";
        command += " AND author = '" + author + "'";
        command += " AND edition = " + edition;
        command += " AND publisher = '" + publisher + "'";

        // open a connection to the database and a Statement object
        try {
            DBHandler dbHandler = new DBHandler();
            ResultSet rs = dbHandler.doQuery(command);

            rs.next();
            result = rs.getString(1);

            dbHandler.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // return the result
        return result;
    }
}