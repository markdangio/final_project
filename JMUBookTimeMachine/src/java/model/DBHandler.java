package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.*;

/**
 * An abstract parent class for database handlers. This class loads database
 * access parameters from web.xml and loads the database driver class.
 *
 * @author R.Grove
 * @version 2014-10-25
 */
public class DBHandler {
    /** Database access credentials */
    protected String driverName = null, url = null, userId = null, password = null;
    /** Database connection */
    private Connection con;
    /** SQL Statement */
    protected Statement stmt;
    /** Connection open status */
    protected boolean isOpen = false;

    /**
     * Get parameters required to open DBMS connection.
     */
    public DBHandler() {
        // Get DB access credentials from web.xml
        try {
            Context envCtx = (Context) (new InitialContext()).lookup("java:comp/env");
            driverName = (String) envCtx.lookup("DriverClassName");
            url = (String) envCtx.lookup("grove.cs.jmu.edu");
            userId = (String) envCtx.lookup("team13");
            password = (String) envCtx.lookup("e@gle6");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open the DB connection
     *
     * @throws SQLException if the DB connection cannot be established
     */
    public void open() throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        con = DriverManager.getConnection(url, userId, password);
        stmt = con.createStatement();
        isOpen = true;
    }

    /**
     * Close the DB connection.
     *
     * @throws SQLException if the DB connection close fails
     */
    public void close() throws SQLException {
        stmt.close();
        con.close();
        isOpen = false;
    }
    
    /**
     * Execute a command and return a result count.
     *
     * @param command An SQL command to be executed.
     * @return The count of rows affected by the command
     * @throws SQLException if there is a database error during command
     * execution
     */
    public int doCommand(String command) throws SQLException {
        if (!isOpen) {
            open();
        }
        int resultCount = stmt.executeUpdate(command);
        return resultCount;
    }
    
    /**
     * Execute a query and return a ResultSet.
     *
     * @param query The SQL query to be executed
     * @return A ResultSet of the query results
     * @throws SQLException if the database query action fails
     */
    public ResultSet doQuery(String query) throws SQLException {
        if (!isOpen) {
            open();
        }
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
}