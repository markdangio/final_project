/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.Date;

/**
 *
 * @author massarmh
 */
public class MessageActions {

    public static boolean addMessage(String content, Date timeSent) {

        Message newMessage = new Message(content, timeSent);
        return MessagePersistence.addMessage(newMessage);
    }

    public static boolean checkMessage(String content, Date timeSent) {

        return MessagePersistence.checkMessage(content, timeSent);
    }

}