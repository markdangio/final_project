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
    
    public Message (String messageId, String toUserId, String fromUserId, String content, String timeSent)
    {
        this.messageId = messageId;
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
        this.content = content;
        this.timeSent = timeSent;
    }
    
    public String getMessageId() {
        return messageId;
    }
    
    public String getToUserId() {
        return toUserId;
    }
    
    public String getFromUserId() {
        return fromUserId;
    }
    
    public String getContent() {
        return content;
    }

    public String getTimeSent() {
        return timeSent;
    }
    
}
