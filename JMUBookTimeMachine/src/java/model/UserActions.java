/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;

/**
 *
 * @author Mitch
 */

public class UserActions {

    /**
     * Add a user record.
     *
     * @param userId The user's id
     * @param username The user's name
     * @param password The user's password
     * @param firstname The user's first name
     * @param lastname The user's last name
     * @param email The user's email
     * @param avatar The user's profile picture
     * @param birthday The user's birth data in the format YYY-MM-DD
     * @param securityAns The users answer to the security question
     */
    public static boolean addUser(String userId, String firstname, String lastname, String email,
            String avatar, String birthday, String username, String securityAns, String password) {

        User newUser = new User(userId, firstname, lastname, email, avatar, birthday, username, securityAns, password);
        return UserPersistence.addUser(newUser);
    }
    
    /**
     * Checks to see if a user record exists.
     *
     * @param username The user's username
     * @param password The user's password
     * @return boolean that says if the user exists
     */
    public static boolean checkUser(String username, String password) {

        return UserPersistence.checkUser(username, password);
    }
    
    /**
     * Checks to see if a user knows their security answer.
     *
     * @param username The user's name
     * @param securityAns The users answer to the security question
     * @return boolean that says if the user can reset their password
     */
    public static boolean checkUserSecurity(String username, String securityAns) {

        return UserPersistence.checkUserSecurity(username, securityAns);
    }
    
    /**
     * Changes a Users password
     *
     * @param username The user's name
     * @param password The user's password
     * @return boolean that says if the user successfully reset their password
     */
    public static boolean changePass(String username, String password) {

        return UserPersistence.changePass(username, password);
    }
    
    /**
     * Gets the id of a user
     *
     * @param username The user's name
     * @return string of the users id
     */
    public static String getUserId(String username) {

        return UserPersistence.getUserId(username);
    }
    
    /**
     * Gets the User object associated with the users id
     *
     * @param userId The user's id
     * @return User object with users info
     */
    public static User getUser(String userId) {

        return UserPersistence.getUser(userId);
    }

} // end class