/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author massarmh
 */
public class MessageActions {

    
    /**
     * Add a message record.
     *
     * @param messageId The message's id
     * @param toUserId The user's id that the message is to
     * @param fromUserId The user's id that the message is from
     * @param content The content of the message
     * @param timeSent The time that the message was sent
     * @return true if message was created successfully
     */
    public static boolean addMessage(String messageId, String toUserId, String fromUserId, String content, String timeSent) {

        Message newMessage = new Message(messageId, toUserId, fromUserId, content, timeSent);
        return MessagePersistence.addMessage(newMessage);
    }
    
    /**
     * Gets a list of users that the user has a message with
     *
     * @param userId The user's username
     * @return ArrayList of User objects
     */
    public static ArrayList<User> showAllUsersMessage(String userId) {
        return MessagePersistence.showAllUsersMessage(userId);
    }
    
    /**
     * Gets a list of messages the user has with another user
     *
     * @param toUserId The user's username
     * @param fromUserId The user's username
     * @return ArrayList of Message objects
     */
    public static ArrayList<Message> getMessages(String toUserId, String fromUserId) {
        return MessagePersistence.getMessages(toUserId, fromUserId);
    }
}
