package eHealth;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 * This class is used to establish a connection to the eHealth database.
 * <p>
 * The provided methods can be used to retrieve data from the database.
 * @author marin
 *
 */
abstract class DBController {

	/**
	 * <h4>Connecting to the eHealth database</h4>
	 * Creates a connection (session) to the eHealth embedded database.
	 * <p>
	 * The returned connection object can be used to communicate with the database (execute queries)
	 * 
	 * @return	the connection object 
	 */
    protected static Connection connectToDB(){

        Connection conn = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/eda;create=true","test", "test");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
	/**
	 * <h4>Retrieving a String column from the database</h4>
	 * The caller of this method must provide the name of the table, the name of the column in that table and a where condition in order to retrieve the desired information.
	 * <p>
	 * This method then uses this information to create a query which is then executed against the eHealth database.
	 * <p>
	 * As a result, the caller of this method gets the String located in the desired column and at the desired row.
	 * If the query could not find any data, using the provided parameters, the method returns an empty String.
	 * 
	 * @param column The desired column
	 * @param table The desired table
	 * @param whereCondition The condition to specify which row to retrieve
	 * @return The desired data as a String
	 */
    protected String getStringColumnFromDB(String column, String table, String whereCondition) {
    	Connection conn = connectToDB();
    	try{
    		String query  = "SELECT " + column +" FROM " + table + " WHERE " + whereCondition;
            Statement st = conn.createStatement();
            ResultSet result =  st.executeQuery(query);
            if(result.next()) {
            	String output = result.getString(column);
            	return output;
            }else {
            	throw new Exception("No data available");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    	
    }
    
    /**
     * <h4>Retrieving a Integer column from the database</h4>
	 * The caller of this method must provide the name of the table, the name of the column in that table and a where condition in order to retrieve the desired information.
	 * <p>
	 * This method then uses this information to create a query which is then executed against the eHealth database.
	 * <p>
	 * As a result, the caller of this method gets the Integer located in the desired column and at the desired row.
	 * If the query could not find any data, using the provided parameters, the method returns a 0.
     * @param column The desired column
     * @param username The user for whom the caller of this method wants to retrieve data
     * @param table The desired table
     * @return The desired data as a Integer
     */
    protected int getIntColomnFromDB(String column, String username, String table) {
    	Connection conn = connectToDB();
    	try{
    		String query  = "SELECT " + column +" FROM " + table + " WHERE username ='" + username + "'";
            Statement st = conn.createStatement();
            ResultSet result =  st.executeQuery(query);
            result.next();
            return result.getInt(column);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * <h4>Retrieving a BigDecimal column from the database</h4>
	 * The caller of this method must provide the name of the table, the name of the column in that table and a where condition in order to retrieve the desired information.
	 * <p>
	 * This method then uses this information to create a query which is then executed against the eHealth database.
	 * <p>
	 * As a result, the caller of this method gets the BigDecimal located in the desired column and at the desired row.
	 * If the query could not find any data, using the provided parameters, the method returns null.
	 * 
     * @param column The desired column
     * @param table The desired table
     * @param whereCondition The condition to specify which row to retrieve
     * @return The desired data as a BigDecimal
     * @see BigDecimal
     */
    protected BigDecimal getBigDecimalColomnFromDB(String column,String table, String whereCondition) {
    	Connection conn = connectToDB();
    	try{
    		String query  = "SELECT " + column +" FROM " + table + " WHERE "+whereCondition;
            Statement st = conn.createStatement();
            ResultSet result =  st.executeQuery(query);
            result.next();
            return result.getBigDecimal(column);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * <h4>Populating a table with data retrieved from the database</h4>
     * This method populates a empty table with data retrieved from the eHealth database.
     * <p>
     * The parameters are used to specify which data from a database table should be presented in the UI-Table.
     * 
     * @param table The table which must be populated with data
     * @param tableName The name of the table in the database
     * @param whereCondition The condition to specify which row to retrieve
     * @param columnCount The number of columns in the database table
     * @throws SQLException If the query cannot be executed or no data is available
     */
    protected void resultSetToTableModel(JTable table, String tableName, String whereCondition, int columnCount) throws SQLException{
    	Connection conn = connectToDB();
    	Statement st = conn.createStatement();
    	ResultSet rs =  st.executeQuery("SELECT * FROM " + tableName + " " + whereCondition);
        DefaultTableModel tableModel = new DefaultTableModel();
        ResultSetMetaData metaData = rs.getMetaData();
        
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        }

        Object[] row = new Object[columnCount];

        while (rs.next()){
            for (int i = 0; i < columnCount; i++){
                row[i] = rs.getObject(i+1);
            }
            tableModel.addRow(row);
        }

        table.setModel(tableModel);
    }
    
    /**
     * <h4>Displaying all entries of a database table on the console</h4>
     * This method uses the provided table name to display all its data on the console.
     * @param tableName The name of the desired table
     */
    protected abstract void displayListOfAllDBEntries(String tableName);

    

    



    
}
