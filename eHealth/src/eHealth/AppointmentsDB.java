package eHealth;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AppointmentsDB extends DBController{

    public void createAppointmentsDBTable(){
        Connection conn =  connectToDB();
        try {
            Statement st = conn.createStatement();
            st.execute(
            		"Create TABLE IF NOT EXISTS Appointments ("+
            			    "username	     varchar(15)," +
            			    "docfirstname	 varchar(30)," +
            			    "doclastname	 varchar(30)," +
            			    "docaddress   	 varchar(200)," +
            			    "AppointmentDate DATE," +
            			    "AppointmentTime TIME," + 
            			    "PRIMARY KEY(username, docfirstname, doclastname, docaddress, AppointmentDate, AppointmentTime));"
            		);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    public void insertIntoAppointmentsDBTable(String username, String docfirstname, String doclastname, String docaddress, String AppointmentDate, String AppointmentTime) {
    	Connection conn =  connectToDB();
		try {
		Statement st = conn.createStatement();
		
		st.execute("INSERT INTO Appointments(USERNAME,DOCFIRSTNAME, DOCLASTNAME, DOCADDRESS, AppointmentDate, AppointmentTime) VALUES('" 
													+ username + "','"
													+ docfirstname + "','"
													+ doclastname + "','"
													+ docaddress + "','"
													+ AppointmentDate + "','"
													+ AppointmentTime + "');");
		} catch (SQLException e) {
		e.printStackTrace();
		}
    }
	
	
	
	@Override
	protected void displayListOfAllDBEntries(String tableName) {
		// TODO Auto-generated method stub
		
	}

}
