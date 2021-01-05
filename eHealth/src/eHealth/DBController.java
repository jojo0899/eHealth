package eHealth;

import java.sql.*;

public class DBController {

    private static Connection connectToDB(){

        Connection conn = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/eda;create=true","test", "test");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void createUserDBTable(){
        Connection conn =  connectToDB();
        try {
            Statement st = conn.createStatement();
            st.execute(
            		"Create TABLE User_v2("+
            			    "username    	varchar(15) PRIMARY KEY," +
            			    "password    	varchar(20)," +
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
    
    
    public static Boolean insertUserIntoDB(
    		String username,
            String password,
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
		
		st.execute("INSERT INTO USER_V2 VALUES('" + username + "','"
													+ password + "','"
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
		//e.printStackTrace();
		return false;
		}
	}
    
    
    public static void displayListOfAllDBEntries(){
        Connection conn =  connectToDB();
        try {
            Statement st = conn.createStatement();
            ResultSet results = st.executeQuery("SELECT * FROM USER_V2");
            while (results.next()) {
                System.out.print(results.getRow()+" : ");
                for(int i=1;i<=8;i++){  // for all columns in user
                    String data = results.getString(i);
                    System.out.print(data + " | ");
                }
                System.out.println("\n");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Boolean validLoginData(String username, String password){
        Connection conn = connectToDB();
        try{
            Statement st = conn.createStatement();
            ResultSet result =  st.executeQuery("SELECT username,password FROM USER_V2 WHERE username ='" + username + "' AND password ='" + password + "'");
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void deleteUserFromDB(String username){
        Connection conn = connectToDB();
        try{
            Statement st = conn.createStatement();
            st.execute("DELETE FROM user WHERE username='"+username+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
