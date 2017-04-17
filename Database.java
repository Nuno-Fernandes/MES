/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

import java.sql.*;

/**
 *
 * @author Utilizador
 */
public class Database {
    
    private static String jdbcDriver;  
    private static String URL;
    private static String username;
    private static String password;
    private boolean status;
    private Connection databaseConnection;
    private Statement databaseStatement;
    private ResultSet dataSet;
    
    /**
     * Gets the username
     * @return 
     */
    public String getUsername()
    {
        return username;
    }
    
     /**
     * Gets the password
     * @return 
     */
    public String getPassword()
    {
        return password;
    }
    
    
    /**
     * Gets database status
     * @return 
     */
    public boolean isReady()
    {
        return status;
    }
    
    /**
     * Gets database URL
     * @return 
     */
    public String getURL()
    {
        return URL;
    }
    
    /**
     * Gets database driver
     * @return 
     */
    public String getDriver()
    {
        return jdbcDriver;
    }
    
  /**
   * Initializes database with given parameters
   * @param driver
   * @param url
   * @return
   * true - database initialized with success
   * false - database not initialized
   */
    public boolean initDatabase(String databaseDriver, String databaseURL)
    {
        // if driver is empty
        if ("".equals(databaseDriver))
        {
            System.out.println("No driver.");
            return false;
        }
        // if the url is empty
        else if ("".equals(databaseURL))
        {
            System.out.println("No url.");
            return false;
        }
        // if all parameters were given
        else
        {
            jdbcDriver = databaseDriver;  
            URL = databaseURL;
            return true;
        }     
    }
    
    /**
     * Sets the database credentials 
     * @param username
     * @param password
     * @return 
     * true 
     * false
     */
    public boolean setCredentials(String databaseUsername, String databasePassword)
    {
        // if the username is empty
        if ("".equals(databaseUsername))
        {
            System.out.println("No username.");
            return false;
        }
        // if the username is empty
        else if ("".equals(databasePassword))
        {
            System.out.println("No password.");
            return false;
        }
        // if all credentials were given
        else
        {
            username = databaseUsername;
            password = databasePassword;
            return true;
        }     
    }
    
    /**
     * Opens a database connection
     * @return 
     * true
     * false
     */
    public boolean openConnection()
    {
      databaseConnection = null;
        
      System.out.println("Connecting to database...");
     
      try
      {
        databaseConnection = DriverManager.getConnection(getURL(),
              getUsername(), getPassword());
      } catch (SQLException e) 
        {
            // if there was some exception
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return false;
        }
      
      // if a database connection was created
      if (databaseConnection != null) 
      {
          System.out.println("You made it, take control your database now!");
          status = true;
          return true;
      } 
      
      // if a database connection was not created
      else 
      {
          System.out.println("Failed to make connection!");
          status = false;
          return false;
      }
    }
    
    /**
     * Closes the connection
     * @return
     * true
     * false
     */
    public boolean closeConnection()
    {
      //TO DO
      return true;
    }
    
    /**
     * Tests a ready database connection with a test query
     * @return 
     * true
     * false
     */
    private boolean testDatabase()
    {  
        // if database is ready, tests
        if (status)
        {
            // if response to test query is received
            if(true)
                return true;
        }
        // if database is not ready
        else
        {
            System.out.println("Only test when ready.");
            return false;
        }
        
        return false;
    }
   
    /**
     * 
     */
    public void registerDriver()
    {
        System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

        try 
        {

            Class.forName(getDriver());

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                                + "Include in your library path!");
            e.printStackTrace();
            return;

        }
        
        System.out.println("PostgreSQL JDBC Driver Registered!");
    }
    
    /**
     * Executes a given query
     * @param query
     * @throws SQLException 
     */
    public void executeQuery(String query) throws SQLException
    {
      System.out.println("Creating statement...");
      databaseStatement = databaseConnection.createStatement();
      ResultSet dataSet = databaseStatement.executeQuery(query);
    }
}

