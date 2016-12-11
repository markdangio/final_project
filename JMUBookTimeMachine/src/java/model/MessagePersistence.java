/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//import java.util.*;
import java.util.UUID;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mitch
 */
public class MessagePersistence {

    /**
     * Add a message record.
     *
     * @param message The message to be added
     * @return true iff the database operation succeeded
     */
    public static boolean addMessage(Message message) {
        DBHandler dbHandler = new DBHandler();

        try {
            String messageid = UUID.randomUUID().toString();
            messageid = messageid.replace("-", "");
            messageid = messageid.substring(0, 16);

            String command = "INSERT INTO Message VALUES(";
            command += "'" + messageid + "'";
            command += ", '" + message.getToUserId() + "'";
            command += ", '" + message.getFromUserId() + "'";
            command += ", '" + message.getContent() + "'";
            command += ", '" + message.getTimeSent() + "')";

            int resultCount = dbHandler.doCommand(command);
            dbHandler.close();
            return (resultCount > 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Returns an ArrayList of User objects.
     *
     * @param userId user's id
     * @return an ArrayList of User objects
     */
    public static ArrayList<User> showAllUsersMessage(String userId) {
        ArrayList<User> result = new ArrayList<User>();
        ArrayList<String> users = new ArrayList<String>();

        String command = "SELECT * FROM Message WHERE ";
        command += "toUserId = '" + userId + "'";
        command += " OR fromUserId = '" + userId + "'";

        // open a connection to the database and a Statement object
        try {
            DBHandler dbHandler = new DBHandler();
            ResultSet rs = dbHandler.doQuery(command);

            while (rs.next()) {
                int i = 1;
                String messageId = rs.getString(i++);
                String toUserIdM = rs.getString(i++);
                String fromUserIdM = rs.getString(i++);
                String content = rs.getString(i++);
                String timeSent = rs.getString(i++);

                User user;
                String id;
                if (toUserIdM.equals(userId)) {
                    user = UserActions.getUser(fromUserIdM);
                    id = toUserIdM;
                } else {
                    user = UserActions.getUser(toUserIdM);
                    id = fromUserIdM;
                }

                if (!users.contains(id)) {
                    users.add(id);
                    result.add(user);
                }
            }

            dbHandler.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // return the result
        return result;
    }
    
    /**
     * Returns an ArrayList of all Message objects between two users
     *
     * @param toUserId user id that the message is to
     * @param fromUserId user id that the message is from
     * @return an ArrayList of all Message objects between two users
     */
    public static ArrayList<Message> getMessages(String toUserId, String fromUserId) {
        ArrayList<Message> result = new ArrayList<Message>();
        
        String command = "SELECT * FROM Message WHERE (";
        command += "toUserId = '" + toUserId + "'";
        command += " AND fromUserId = '" + fromUserId + "') OR ";
        command += "(toUserId = '" + fromUserId + "'";
        command += " AND fromUserId = '" + toUserId + "')";

        // open a connection to the database and a Statement object
        try {
            DBHandler dbHandler = new DBHandler();
            ResultSet rs = dbHandler.doQuery(command);

            while (rs.next()) {
                int i = 1;
                String messageId = rs.getString(i++);
                String toUserIdM = rs.getString(i++);
                String fromUserIdM = rs.getString(i++);
                String content = rs.getString(i++);
                String timeSent = rs.getString(i++);
                
                Message message = new Message(messageId, toUserIdM, fromUserIdM, content, timeSent);
                
                result.add(message);
            }

            dbHandler.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // return the result
        
        Collections.sort(result, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o1.getTimeSent().compareTo(o2.getTimeSent());
            }
        });
        return result;
    }
}
