package eHealth;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

abstract class DBController {

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

    protected String getStringColomnFromDB(String column, String Table, String whereCondition) {
    	Connection conn = connectToDB();
    	try{
    		String query  = "SELECT " + column +" FROM " + Table + " WHERE " + whereCondition;
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
    
    protected int getIntColomnFromDB(String column, String username, String Table) {
    	Connection conn = connectToDB();
    	try{
    		String query  = "SELECT " + column +" FROM " + Table + " WHERE username ='" + username + "'";
            Statement st = conn.createStatement();
            ResultSet result =  st.executeQuery(query);
            result.next();
            return result.getInt(column);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    protected BigDecimal getBigDecimalColomnFromDB(String column,String Table, String whereCondition) {
    	Connection conn = connectToDB();
    	try{
    		String query  = "SELECT " + column +" FROM " + Table + " WHERE "+whereCondition;
            Statement st = conn.createStatement();
            ResultSet result =  st.executeQuery(query);
            result.next();
            return result.getBigDecimal(column);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    protected void resultSetToTableModel(JTable table, String tableName, String whereCondition) throws SQLException{
    	Connection conn = connectToDB();
    	Statement st = conn.createStatement();
    	ResultSet rs =  st.executeQuery("SELECT * FROM " + tableName + " " + whereCondition);
        DefaultTableModel tableModel = new DefaultTableModel();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

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
    
    protected abstract void displayListOfAllDBEntries(String tableName);

    

    



    
}
