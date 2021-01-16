package eHealth;

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

    protected String getStringColomnFromDB(String column, String username, String Table) {
    	Connection conn = connectToDB();
    	try{
    		String query  = "SELECT " + column +" FROM " + Table + " WHERE username ='" + username + "'";
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
    
    protected abstract void displayListOfAllDBEntries();

    

    



    
}
