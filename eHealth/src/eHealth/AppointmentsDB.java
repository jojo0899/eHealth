package eHealth;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppointmentsDB extends DBController{

    public void createAppointmentsDBTable(){
        Connection conn =  connectToDB();
        try {
            Statement st = conn.createStatement();
            st.execute(
            		"Create TABLE IF NOT EXISTS Appointments ("+
            				"id    			 int AUTO_INCREMENT NOT NULL," +
            			    "username	     varchar(15)," +
            			    "docfirstname	 varchar(30)," +
            			    "doclastname	 varchar(30)," +
            			    "docaddress   	 varchar(200)," +
            			    "AppointmentDate DATE," +
            			    "AppointmentTime TIME," + 
            			    "Reminder		 int," +
            			    "PRIMARY KEY(username, docfirstname, doclastname, docaddress, AppointmentDate, AppointmentTime));"
            		);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    public void insertIntoAppointmentsDBTable(String username, String docfirstname, String doclastname, String docaddress, String AppointmentDate, String AppointmentTime, int reminder) throws SQLException {
    	Connection conn =  connectToDB();
		Statement st = conn.createStatement();
		
		st.execute("INSERT INTO Appointments(USERNAME,DOCFIRSTNAME, DOCLASTNAME, DOCADDRESS, AppointmentDate, AppointmentTime, Reminder) VALUES('" 
													+ username + "','"
													+ docfirstname + "','"
													+ doclastname + "','"
													+ docaddress + "','"
													+ AppointmentDate + "','"
													+ AppointmentTime + "','"
													+ reminder + "');");
    }
    
    public Boolean updateAppointmentInDB(String id, String date, String time) {
    	Connection conn =  connectToDB();
		try {
			Statement st = conn.createStatement();
			String query = "UPDATE Appointments SET AppointmentDate = '" + date + "', AppointmentTime = '" + time +"' WHERE id = " + id;
			st.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public Boolean deleteAppointmentFromDB(int id) {
    	Connection conn = connectToDB();
        Statement st;
        try {
			st = conn.createStatement();
			st.execute("DELETE FROM Appointments WHERE id= "+id);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }

	
	@Override
	protected void displayListOfAllDBEntries(String tableName) {
		// TODO Auto-generated method stub
		
	}
	
	public ResultSet reminderlist() {
		ResultSet results = null;
		Connection conn =  connectToDB();
        try {
            Statement st = conn.createStatement();
            results = st.executeQuery("SELECT AppointmentDate, AppointmentTime, reminder, username, id FROM Appointments Where Reminder > 0;");
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return results;		
	}
	
	public Boolean updateReminder(String id, String reminder) {
    	Connection conn =  connectToDB();
		try {
			Statement st = conn.createStatement();
			String query = "UPDATE Appointments SET reminder = '" + reminder + "' WHERE id = " + id;
			st.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    }
	

}
