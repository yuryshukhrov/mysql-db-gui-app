package Utilities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableInformation {

    private Connection connection;
    private ArrayList<String> tableNames;

    public TableInformation() {
        connection = null;
        tableNames = new ArrayList<>();
    }

    public TableInformation(Connection connection) {
        this.connection = connection;
        tableNames = new ArrayList<>();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<String> getTableNames()
            throws ClassNotFoundException, SQLException {

        String TABLE_NAME = "TABLE_NAME";
        String[] TABLE_TYPES = {"TABLE"};
        DatabaseMetaData dbmd = connection.getMetaData();

        ResultSet tables = dbmd.getTables(null, null, null, TABLE_TYPES);
    
        while (tables.next()) {    
            StringBuilder sb = new StringBuilder(tables.getString(TABLE_NAME)); 
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));   
            tableNames.add(sb.toString());
        }

        return tableNames;
    }
}
