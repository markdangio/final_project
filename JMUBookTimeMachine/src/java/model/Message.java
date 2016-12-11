/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author massarmh
 */
public class Message {
    
    private String messageId, toUserId, fromUserId, content, timeSent;
    
    /**
     * Initialize a Message object.
     *
     * @param messageId The message's id
     * @param toUserId The user's id that the message is to
     * @param fromUserId The user's id that the message is from
     * @param content The content of the message
     * @param timeSent The time that the message was sent
     */
    public Message (String messageId, String toUserId, String fromUserId, String content, String timeSent)
    {
        this.messageId = messageId;
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
        this.content = content;
        this.timeSent = timeSent;
    }
    
    /**
     * Get the message's id.
     *
     * @return The user's id
     */
    public String getMessageId() {
        return messageId;
    }
    
    /**
     * Get the user's id that the message is to.
     *
     * @return The user's id
     */
    public String getToUserId() {
        return toUserId;
    }
    
    /**
     * Get the user's id that the message is from.
     *
     * @return The user's id
     */
    public String getFromUserId() {
        return fromUserId;
    }
    
    /**
     * Get the message's content
     *
     * @return The message's content
     */
    public String getContent() {
        return content;
    }

    /**
     * Get the time that the message sent
     *
     * @return The the time that the message sent
     */
    public String getTimeSent() {
        return timeSent;
    }
    
}
