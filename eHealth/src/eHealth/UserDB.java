package eHealth;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 * This class is used to create the user table, and provide operations for querying the user table. 
 * @author marin
 *
 */
public class UserDB extends DBController {
	
	/**
	 * <h4>Creating the user table</h4>
	 * Creates the user table in the database if it does not exist already.
	 */
    public void createUserDBTable(){
        Connection conn =  connectToDB();
        try {
            Statement st = conn.createStatement();
            st.execute(
            		"Create TABLE IF NOT EXISTS USER("+
            			    "username    	varchar(15) PRIMARY KEY," +
            			    "password    	char(128)," +
            			    "email	    	varchar(50)," +
            			    "firstName   	varchar(25)," +
            			    "lastname    	varchar(25)," +
            			    "dateOfBirth 	DATE," +
            			    "healthInfo  	varchar(30)," +
            			    "insuranceName   varchar(30)," +
            			    "insuranceType   varchar(8)," +
            			    "street          varchar(60)," +
            			    "streetNo        int," +
            			    "zipCode         varchar(10)," +
            			    "city        	 varchar(40)," +
            			    "latitude        Decimal(80,70)," +
            			    "longitude       Decimal(80,70));"

            		);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * <h4>Inserting a user into the user table</h4>
     * A user is inserted into the user table, using the attributes specified by the caller of the method.
     * <p>
     * The latitude and longitude is calculated within this method, using OpenStreetMapUtils.
     * 
     * @param username
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param date_of_birth
     * @param healthInfo
     * @param insuranceName
     * @param insuranceType
     * @param street
     * @param streetNo
     * @param zipCode
     * @param city
     * @return true if user was successfully inserted into the database, else false
     */
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
    	
    	String address;
    	address = city+ " " + street+ " " + streetNo;
    	Map<String, Double> coords;
    	coords = OpenStreetMapUtils.getInstance().getCoordinates(address);
    	BigDecimal lat = new BigDecimal(coords.get("lat"));
    	BigDecimal lon = new BigDecimal(coords.get("lon"));
        
        
    	
    	
    	
    	
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
													+ street + "','"
													+ streetNo + "','"
													+ zipCode + "','"
													+ city + "','"
													+ lat + "','"
													+ lon + "');");
		
		return true;
		} catch (SQLException e) {
		e.printStackTrace();
		return false;
		}
	}
    
    
    /**
     * <h4>Validating login data</h4>
     * Checks if the username and the encrypted password match in the database.
     * 
     * @param username
     * @param password
     * @return true if matched, else false
     */
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
    
    /**
     * <h4>Deleting a user from the user table</h4>
     * Deletes the user with the given username from the user table.
     * @param username
     */
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
    
    /**
     * <h4>Updating a row in the user table</h4>
     * Updates a row in the user table with the given parameters.
     * 
     * @param username
     * @param email
     * @param firstName
     * @param lastName
     * @param date_of_birth
     * @param healthInfo
     * @param insuranceName
     * @param insuranceType
     * @param street
     * @param streetNo
     * @param zipCode
     * @param city
     * @return true if updated successfully, else false
     */
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
    
    /**
     * <h4>Updating the health information of a user in the database</h4>
     * Changes the health information of the given user to the given String.
     * 
     * @param healthInfo New health info to be inserted
     * @param username User which should have its health information changed 
     */
    public void updateHealthInfoInDB(String healthInfo, String username) {
    	Connection conn = connectToDB();
    	Statement st;
		try {
			st = conn.createStatement();
	    	st.execute("UPDATE USER SET healthinfo = '"+ healthInfo + "' WHERE username = '" + username + "';" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * <h4>Checking if a user exists in the user table</h4>
     * Checks if the user with the given username exists in the user table.
     * 
     * @param username The user to be checked
     * @return true if user exists in db, else false
     */
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
    

	@Override
	public void displayListOfAllDBEntries(String tableName) {
        Connection conn =  connectToDB();
        try {
            Statement st = conn.createStatement();
            ResultSet results = st.executeQuery("SELECT * FROM " + tableName);
            while (results.next()) {
                System.out.print(results.getRow()+" : ");
                for(int i=1;i<=15;i++){  // for all columns in user
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
