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
public class ClassesPersistence {

    /**
     * Add a class record.
     *
     * @param classes The class to be added
     * @return true iff the database operation succeeded
     */
    public static boolean addClass(Classes classes) {
        DBHandler dbHandler = new DBHandler();
        
        String command = "INSERT INTO Classes VALUES(";
        command += "'" + classes.getClassId() + "'";
        command += ", '" + classes.getName() + "'";
        command += ", '" + classes.getSubject() + "'";
        command += ", '" + classes.getNumber() + "'";
        command += ", '" + classes.getSection() + "'";
        command += ", '" + classes.getProfessor() + "'";
        
        // empty dates must be entered as null rather than ''
        command += classes.getDescription().equals("") 
                ? ", NULL" 
                : ", '" + classes.getDescription() + "'";
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
     * Add a pet record.
     *
     * @param username The user to be added
     * @param password The user's password
     * @return true iff the database operation succeeded
     */
    public static boolean checkClass(String name, String subject, int number, int section, String professor) {
        DBHandler dbHandler = new DBHandler();
                
        String command = "SELECT * FROM Classes WHERE name = ";
        command += "'" + name + "'";
        command += " AND subject = '" + subject + "'";
        command += " AND number = " + number;
        command += " AND section = " + section;
        command += " AND professor = '" + professor + "'";
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
    public static String getClassId(String name, String subject, int number, int section, String professor) {
        String result = "";
        
        String command = "SELECT classId FROM Classes WHERE name = ";
        command += "'" + name + "'";
        command += " AND subject = '" + subject + "'";
        command += " AND number = " + number;
        command += " AND section = " + section;
        command += " AND professor = '" + professor + "'";

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