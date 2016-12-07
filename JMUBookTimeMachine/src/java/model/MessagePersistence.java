/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//import java.util.*;
import java.util.UUID;
import java.sql.*;
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
            command += ", '" + message.getTimeSent() + "'";
            
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