package eHealth;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserDB extends DBController {
	
    public void createUserDBTable(){
        Connection conn =  connectToDB();
        try {
            Statement st = conn.createStatement();
            st.execute(
            		"Create TABLE USER("+
            			    "username    	varchar(15) PRIMARY KEY," +
            			    "password    	char(128)," +
            			    "email	    	varchar(30)," +
            			    "firstName   	varchar(15)," +
            			    "lastname    	varchar(15)," +
            			    "dateOfBirth 	DATE," +
            			    "healthInfo  	varchar(30)," +
            			    "insuranceName   varchar(30)," +
            			    "insuranceType   varchar(8)," +
            			    "street          varchar(20)," +
            			    "streetNo        int," +
            			    "zipCode         varchar(10)," +
            			    "city            varchar(20));"
            		);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Boolean insertUserIntoDB(
    		String username,
            String password,
            String email,
            String firstName,
            String lastName,
            String date_of_birth, 
            String healthInfo,
            String insuranceName,
            String insuranceType,
            String street,
            int streetNo,
            String zipCode,
            String city)
	{
		Connection conn =  connectToDB();
		try {
		Statement st = conn.createStatement();
		
		st.execute("INSERT INTO USER VALUES('" + username + "','"
													+ password + "','"
													+ email + "','"
													+ firstName + "','"
													+ lastName + "','"
													+ date_of_birth + "','"
													+ healthInfo + "','"
													+ insuranceName + "','"
													+ insuranceType + "','"
													+ street + "',"
													+ streetNo + ",'"
													+ zipCode + "','"
													+ city + "');");
		
		return true;
		} catch (SQLException e) {
		e.printStackTrace();
		return false;
		}
	}
    
    
    
    public Boolean validLoginData(String username, String password){
        Connection conn = connectToDB();
        try{
            Statement st = conn.createStatement();
            ResultSet result =  st.executeQuery("SELECT username,password FROM USER WHERE username ='" + username + "' AND password ='" + password + "'");
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void deleteUserFromDB(String username){
        Connection conn = connectToDB();
        Statement st;
		try {
			st = conn.createStatement();
			st.execute("DELETE FROM USER WHERE username='"+username+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    public Boolean updateUserInDB(
    		String username,
            /*String password,*/
    		String email,
            String firstName,
            String lastName,
            String date_of_birth, 
            String healthInfo,
            String insuranceName,
            String insuranceType,
            String street,
            int streetNo,
            String zipCode,
            String city)
	{
		Connection conn =  connectToDB();
		try {
		Statement st = conn.createStatement();
		
		st.execute("UPDATE USER SET " + "username = '" + username + "'," +
				/*"password = '"+ password + "'," +*/
				"email = '"+ email + "'," +
				"firstname = '"+ firstName + "'," +
				"lastname = '"+ lastName + "'," +
				"dateofbirth = '"+ date_of_birth + "'," +
				"healthinfo = '"+ healthInfo + "'," +
				"insurancename = '"	+ insuranceName + "'," +
				"insurancetype = '"	+ insuranceType + "'," +
				"street = '"+ street + "'," +
				"streetno = "	+ streetNo + "," +
				"zipcode = '"+ zipCode + "'," +
				"city = '"+ city 
				+ "' WHERE username = '" + username + "';");
		
		return true;
		} catch (SQLException e) {
		e.printStackTrace();
		return false;
		}
	}
    
    public Boolean checkIfUsernameExistsInDB(String username) {
        Connection conn = connectToDB();
        try{
            Statement st = conn.createStatement();
            ResultSet result =  st.executeQuery("SELECT username FROM USER WHERE username ='" + username + "'");
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public void resultSetToTableModel(JTable table) throws SQLException{
    	Connection conn = connectToDB();
    	Statement st = conn.createStatement();
    	ResultSet rs =  st.executeQuery("SELECT * FROM USER");
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

	@Override
	public void displayListOfAllDBEntries() {
        Connection conn =  connectToDB();
        try {
            Statement st = conn.createStatement();
            ResultSet results = st.executeQuery("SELECT * FROM USER");
            while (results.next()) {
                System.out.print(results.getRow()+" : ");
                for(int i=1;i<=12;i++){  // for all columns in user
                    String data = results.getString(i);
                    System.out.print(data + " | ");
                }
                System.out.println("\n");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}


    
}