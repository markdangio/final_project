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
    public static boolean addUser(String userId, String password, String firstname, String lastname, String email,
            String avatar, String birthday, String username, String securityAns) {

        User newUser = new User(userId, password, firstname, lastname, email, avatar, birthday, username, securityAns);
        return UserPersistence.addUser(newUser);
    }
    
    public static boolean checkUser(String username, String password) {

        return UserPersistence.checkUser(username, password);
    }
    
    public static boolean checkUserSecurity(String username, String securityAns) {

        return UserPersistence.checkUserSecurity(username, securityAns);
    }
    
    public static boolean changePass(String username, String password) {

        return UserPersistence.changePass(username, password);
    }
    
    public static String getUserId(String username) {

        return UserPersistence.getUserId(username);
    }
    
    public static User getUser(String userId) {

        return UserPersistence.getUser(userId);
    }

} // end class