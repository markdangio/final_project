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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mitch
 */
public class MessagePersistence {

    /**
     * Add a message record.
     *
     * @param message The message to be added
     * @return true iff the database operation succeeded
     */
    public static boolean addMessage(Message message) {//, User toUser, User fromUser) {
        DBHandler dbHandler = new DBHandler();
        
        try {
            String messageid = UUID.randomUUID().toString();
            messageid = messageid.replace("-","");
            messageid = messageid.substring(0, 16);

            String command = "INSERT INTO Message VALUES(";
            command += "'" + messageid + "'";
            command += ", '" + message.getToUserId() + "'";
            command += ", '" + message.getFromUserId() + "'";
            command += ", '" + message.getContent() + "'";
            command += ", '" + message.getTimeSent() + "')";
            
            int resultCount = dbHandler.doCommand(command);
            dbHandler.close();
            return (resultCount > 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Returns an ArrayList of all Pet objects.
     *
     * @return an ArrayList of all Pet objects
     */
    public static ArrayList<User> showAllUsersMessage(String toUserId, String fromUserId) {
        ArrayList<User> result = new ArrayList<User>();

        String myUserId = fromUserId;
        
        String command = "SELECT * FROM Message WHERE (";
        command += "toUserId = '" + toUserId + "'";
        command += " AND fromUserId = '" + fromUserId + "') OR";
        command += "(toUserId = '" + fromUserId + "'";
        command += " AND fromUserId = '" + toUserId + "')";

        // open a connection to the database and a Statement object
        try {
            DBHandler dbHandler = new DBHandler();
            ResultSet rs = dbHandler.doQuery(command);

            while (rs.next()) {
                int i = 1;
                String messageId = rs.getString(i++);
                String toUserIdM = rs.getString(i++);
                String fromUserIdM = rs.getString(i++);
                String content = rs.getString(i++);
                String timeSent = rs.getString(i++);
                
                User user;
                if(toUserIdM.equals(myUserId)){
                    user = UserActions.getUser(fromUserIdM);
                }
                else{
                    user = UserActions.getUser(toUserIdM);
                }
                
                result.add(user);
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
    public static ArrayList<Message> getMessages(String toUserId, String fromUserId) {
        ArrayList<Message> result = new ArrayList<Message>();
        
        String command = "SELECT * FROM Message WHERE (";
        command += "toUserId = '" + toUserId + "'";
        command += " AND fromUserId = '" + fromUserId + "') OR";
        command += "(toUserId = '" + fromUserId + "'";
        command += " AND fromUserId = '" + toUserId + "')";

        // open a connection to the database and a Statement object
        try {
            DBHandler dbHandler = new DBHandler();
            ResultSet rs = dbHandler.doQuery(command);

            while (rs.next()) {
                int i = 1;
                String messageId = rs.getString(i++);
                String toUserIdM = rs.getString(i++);
                String fromUserIdM = rs.getString(i++);
                String content = rs.getString(i++);
                String timeSent = rs.getString(i++);
                
                Message message = new Message(messageId, toUserIdM, fromUserIdM, content, timeSent);
                
                result.add(message);
            }

            dbHandler.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // return the result
        return result;
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
     *
    public static ArrayList<Pet> getAllPets() {
        String query = "select * from pet";
        ArrayList<Pet> result = new ArrayList<Pet>();

        // open a connection to the database and a Statement object
        try {
            DBQueryHandler dbQueryHandler = new DBQueryHandler();
            ResultSet rs = dbQueryHandler.doQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                int i = 1; // 1st column
                String name = rs.getString(i++);
                String owner = rs.getString(i++);
                String species = rs.getString(i++);
                String sex = rs.getString(i++);
                String birth = rs.getString(i++);
                Pet pet = new Pet(name, owner, species, sex, birth);
                result.add(pet);
            }

            dbQueryHandler.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // return the result
        return result;
    }*/
}