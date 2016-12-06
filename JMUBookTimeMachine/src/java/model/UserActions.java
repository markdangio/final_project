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
     */
    public static boolean addUser(String userId, String username, String password, String firstname, String lastname, String email,
            String avatar, String birthday) {

        User newUser = new User(userId, username, password, firstname, lastname, email, avatar, birthday);
        return UserPersistence.addUser(newUser);
    }
    
    public static boolean checkUser(String username, String password) {

        return UserPersistence.checkUser(username, password);
    }

} // end class