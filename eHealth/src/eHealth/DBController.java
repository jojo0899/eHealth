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

    protected String getColomnFromDB(String column, String username, String Table) {
    	Connection conn = connectToDB();
    	try{
    		String query  = "SELECT " + column +" FROM " + Table + " WHERE username ='" + username + "'";
            Statement st = conn.createStatement();
            ResultSet result =  st.executeQuery(query);
            result.next();
            return result.getString(column);
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
    
    protected abstract void displayListOfAllDBEntries();
    

    



    
}
