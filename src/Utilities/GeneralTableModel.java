package Utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class GeneralTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData resultSetMetaData;
    private int numberOfRows;
    // keep track of database connection status
    private boolean connectedToDatabase = false;

    // constructor initializes resultSet and obtains its meta data object;
    // determines number of rows
    public GeneralTableModel(Connection connection, String query) {
        
        // create Statement to query database
        this.connection = connection;

        try {
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            // update database connection status
            connectedToDatabase = true;

            // set query and execute it
            setQuery(query);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),
                    "Error in creating statements and calling setQuery",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Class getColumnClass(int column) throws IllegalStateException {

        // ensure database connection is available
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        return Object.class; // if problems occur above, assume type Object
    } // end method getColumnClass

    // get number of columns in ResultSet
    @Override
    public int getColumnCount() throws IllegalStateException {
        // ensure database connection is available
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        // determine number of columns
        try {
            return resultSetMetaData.getColumnCount();
        } // end try
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString(),
                    "Error in returning column number",
                    JOptionPane.ERROR_MESSAGE);
        } // end catch

        return 0; // if problems occur above, return 0 for number of columns
    } // end method getColumnCount

    // get name of a particular column in ResultSet
    @Override
    public String getColumnName(int column) throws IllegalStateException {
        // ensure database connection is available
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        // determine column name
        try {
            StringBuilder sb = new StringBuilder(
                    resultSetMetaData.getColumnName(column + 1));
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            return sb.toString();
        } // end try
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString(),
                    "Error in getting column name", 
                    JOptionPane.ERROR_MESSAGE);
        } // end catch

        return ""; // if problems, return empty string for column name
    } // end method getColumnName

    // return number of rows in ResultSet
    @Override
    public int getRowCount() throws IllegalStateException {
        // ensure database connection is available
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        return numberOfRows;
    } // end method getRowCount

    // obtain value in particular row and column
    @Override
    public Object getValueAt(int row, int column) 
            throws IllegalStateException {
        // ensure database connection is available
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        // obtain a value at specified ResultSet row and column
        try {
            resultSet.absolute(row + 1);
            return resultSet.getObject(column + 1);

        } // end try
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString(),
                    "Error in getting absolute and object",
                    JOptionPane.ERROR_MESSAGE);
        } // end catch

        return ""; // if problems, return empty string object
    } // end method getValueAt

    // set new database query string
    public void setQuery(String query) throws SQLException,
            IllegalStateException {
        // ensure database connection is available
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        // specify query and execute it
        resultSet = statement.executeQuery(query);

        // obtain meta data for ResultSet
        resultSetMetaData = resultSet.getMetaData();

        // determine number of rows in ResultSet
        resultSet.last(); // move to last row
        numberOfRows = resultSet.getRow(); // get row number

        fireTableStructureChanged();

    } // end method setQuery

    // close Statement and Connection
    public void disconnectFromDatabase() {
        if (connectedToDatabase) {
            // close Statement and Connection
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } // end try
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString(),
                        "Error in closing resultSet, "
                        + "statement and connection",
                        JOptionPane.ERROR_MESSAGE);
            } // end catch
            finally // update database connection status
            {
                connectedToDatabase = false;
            } // end finally
        } // end if
    } // end method disconnectFromDatabase
} // end constructor ResultSetTableModel