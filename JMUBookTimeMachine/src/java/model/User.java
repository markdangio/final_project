/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Mitch
 */

public class User {

    private String userId, username, password, firstname, lastname, email, avatar, birthday;

    /**
     * Initialize a User object.
     *
     * @param userId The user's id
     * @param username The user's username
     * @param password The user's password
     * @param firstname The user's first name
     * @param lastname The user's last name
     * @param email The user's email
     * @param avatar The user's profile picture
     * @param birthday The user's birth date in the format YYYY-MM-DD
     */
    public User(String userId, String username, String password, String firstname, String lastname, String email, String avatar, String birthday) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.avatar = avatar;
        this.birthday = birthday;
    }
    
    public String getUserId() {
        return userId;
    }

    /**
     * Get the user's username.
     *
     * @return The user's username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Get the user's password.
     *
     * @return The user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the user's first name.
     *
     * @return The name of the user's first name
     */
    public String getFirstName() {
        return firstname;
    }

    /**
     * Get the user's species.
     *
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastname;
    }

    /**
     * Get the user's email.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Get the user's avatar.
     *
     * @return The profile picture of the user
     */
    public String getAvatar() {
        return avatar;
    }
    
    /**
     * Get the user's birth date.
     *
     * @return The birth date of the user, in the format YYYY-MM-DD
     */
    public String getBirthday() {
        return birthday;
    }
}
