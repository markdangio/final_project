/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//import java.util.*;
import java.sql.*;

/**
 *
 * @author Mitch
 */
public class UserPersistence {

    /**
     * Add a user record.
     *
     * @param user The user to be added
     * @return true iff the database operation succeeded
     */
    public static boolean addUser(User user) {
        DBHandler dbHandler = new DBHandler();
        
        String command = "INSERT INTO User VALUES(";
        command += "'" + user.getUserId() + "'";
        
        command += ", '" + user.getFirstName() + "'";
        command += ", '" + user.getLastName() + "'";
        command += ", '" + user.getEmail() + "'";
        
        // empty dates must be entered as null rather than ''
        command += user.getAvatar().equals("") 
                ? ", NULL" 
                : ", '" + user.getAvatar() + "'";
        command += ", '" + user.getBirthday() + "'";
        command += ", '" + user.getUsername() + "'";
        command += ", '" + user.getSecurityAnswer() + "'";
        command += ", '" + user.getPassword() + "'";
        command += ")";

        try {
            int resultCount = dbHandler.doCommand(command);
            dbHandler.close();
            return (resultCount > 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Checks to see if a user exists in the database
     *
     * @param username The user to be added
     * @param password The user's password
     * @return true iff the database operation succeeded
     */
    public static boolean checkUser(String username, String password) {
        DBHandler dbHandler = new DBHandler();
                
        String command = "SELECT * FROM User WHERE username = ";
        command += "'" + username + "'";
        command += " AND password = '" + password + "'";
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
     * Checks to see if the user knows their security password
     *
     * @param username The user to be added
     * @param securityAns The user's security password
     * @return true iff the database operation succeeded
     */
    
    public static boolean checkUserSecurity(String username, String securityAns) {
        DBHandler dbHandler = new DBHandler();
                
        String command = "SELECT * FROM User WHERE username = ";
        command += "'" + username + "'";
        command += " AND securityAns = '" + securityAns + "'";
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
     * Changes the users password
     *
     * @return true iff the database operation succeeded
     */
    public static boolean changePass(String username, String password) {
        DBHandler dbHandler = new DBHandler();
        try {

            String command = "UPDATE User SET password = ";
            command += "'" + password + "'";
            command += " WHERE username = '" + username + "'";
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
     * Returns an the id of the User
     *
     * @param username the username of the user
     * @return String of users id
     */
    public static String getUserId(String username) {
        String query = "SELECT userId FROM User WHERE username = '" + username + "'";
        String result = "";

        // open a connection to the database and a Statement object
        try {
            DBHandler dbHandler = new DBHandler();
            ResultSet rs = dbHandler.doQuery(query);

            rs.next();
            result = rs.getString(1);

            dbHandler.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // return the result
        return result;
    }
    
    /**
     * Returns aUser object
     *
     * @param userId id of the user
     * @return User object with user info
     */
    public static User getUser(String userId) {
        String query = "SELECT * FROM User WHERE userId = '" + userId + "'";
        User result = null;
        // open a connection to the database and a Statement object
        try {
            DBHandler dbHandler = new DBHandler();
            ResultSet rs = dbHandler.doQuery(query);

            while (rs.next()) {
                int i = 1; // 1st column
                String userIdU = rs.getString(i++);
                
                String firstName = rs.getString(i++);
                String lastName = rs.getString(i++);
                String email = rs.getString(i++);
                String avatar = rs.getString(i++);
                String birthday =rs.getString(i++);
                String username = rs.getString(i++);
                String securityAns = "";
                i++;
                String password = "";
                i++;
                result = new User(userIdU, firstName, lastName, email, avatar, birthday, username, securityAns, password);
            }

            dbHandler.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // return the result
        return result;
    }
}