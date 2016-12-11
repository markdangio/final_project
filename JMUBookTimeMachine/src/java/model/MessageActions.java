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

    public static boolean addMessage(String messageId, String toUserId, String fromUserId, String content, String timeSent) {

        Message newMessage = new Message(messageId, toUserId, fromUserId, content, timeSent);
        return MessagePersistence.addMessage(newMessage);
    }
    
    public static ArrayList<User> showAllUsersMessage(String userId) {
        return MessagePersistence.showAllUsersMessage(userId);
    }
    
    public static ArrayList<Message> getMessages(String toUserId, String fromUserId) {
        return MessagePersistence.getMessages(toUserId, fromUserId);
    }
    /*
    public static boolean checkMessage(String content, Date timeSent) {

        return MessagePersistence.checkMessage(content, timeSent);
    }*/

}
