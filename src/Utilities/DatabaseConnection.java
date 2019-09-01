package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    private final static String driver = "com.mysql.cj.jdbc.Driver";
    
    private String hostName;
    private String dataBase;
    private String portNumber;
    private String userName;      
    private String passWord; 
    private String databaseURL;
    private Connection connection;
    
    public DatabaseConnection () {}

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return passWord;
    }

    public void setPassword(char[] tempPWD) {
        
        if(tempPWD.length != 0) {
        passWord = new String(tempPWD);
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
     public String getDatabaseURL() {
         databaseURL = "jdbc:mysql://" + hostName + ":" + 
                portNumber + "/" + dataBase + "";   
        return databaseURL;
    }

    public void setConnection() throws SQLException, 
            ClassNotFoundException {
        Class.forName(driver);
        connection = DriverManager.getConnection
                (getDatabaseURL(), userName, passWord);     
    }
    
    @Override
    public String toString() {
        return 
             "\nHost Name: " + hostName + "\n" +
             "Database: " + dataBase + "\n" +
             "Port Number: " + portNumber + "\n" +
             "User Name: " + userName + "\n" +
             "Connection: " + connection + "\n";
    }    
}
