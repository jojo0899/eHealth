package eHealth;

import java.sql.*;

public class DBController {

    private static Connection connectToDB(){

        Connection conn = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/eda","test", "test");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void createUserDBTable(){
        Connection conn =  connectToDB();
        try {
            Statement st = conn.createStatement();
            st.execute("CREATE TABLE user( " +
                    "username	varchar(20) PRIMARY KEY, " +
                    "user_password	varchar(30), " +
                    "fname			varchar(15), " +
                    "lname			varchar(15), " +
                    "address			varchar(35), " +
                    "date_of_birth	date, " +
                    "health_info		varchar(25), " +
                    "insurance_type	varchar(8));"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    public static Boolean insertUserIntoDB(String username,
            String user_password,
            String fname,
            String lname,
            String address,
            String date_of_birth,
            String health_info,
            String insurance_type)
	{
		Connection conn =  connectToDB();
		try {
		Statement st = conn.createStatement();
		st.execute("INSERT INTO user VALUES ('"+ username + "','"
		                            + user_password + "','"
		                            +fname + "','"
		                            +lname + "','"
		                            +address + "','"
		                            +date_of_birth+"','"
		                            +health_info + "','"
		                            +insurance_type + "')");
		return true;
		} catch (SQLException e) {
		e.printStackTrace();
		return false;
		}
	}
    
    public static void displayListOfAllDBEntries(){
        Connection conn =  connectToDB();
        try {
            Statement st = conn.createStatement();
            ResultSet results = st.executeQuery("SELECT * FROM user");
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
            ResultSet result =  st.executeQuery("SELECT username,user_password FROM USER WHERE username ='" + username + "' AND user_password ='" + password + "'");
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
