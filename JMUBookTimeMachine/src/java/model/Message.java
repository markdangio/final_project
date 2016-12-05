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
public class Message {
    
    private String content;
    private Date timeSent;
    
    public Message (String content, Date timeSent)
    {
        this.content = content;
        this.timeSent = timeSent;
    }

    public String getContent() {
        return content;
    }

    public Date getTimeSent() {
        return timeSent;
    }
    
}
