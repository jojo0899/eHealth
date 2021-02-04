package eHealth;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * This class is used to create the appointments table, and provide operations for querying the appointments table. 
 * @author marin
 *
 */
public class AppointmentsDB extends DBController{

	/**
	 * <h4>Creating the appointments table</h4>
	 * A query is executed, which creates the appointment table with all its attributes in the database.
	 */
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
	
    /**
     * <h4>Inserting a row into the appointments table</h4>
     * A row is inserted into the appointments table, with the attributes specified by the caller of this method.
     * 
     * @param username The username of the referenced user
     * @param docfirstname The first name of the referenced doctor
     * @param doclastname The last name of the referenced doctor
     * @param docaddress The address of the referenced doctor
     * @param AppointmentDate The specified date for the appointment
     * @param AppointmentTime The specified time for the appointment
     * @param reminder The reminder for the appointment (in min)
     * @throws SQLException If row can not be inserted
     */
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
    
    /**
     * <h4>Deleting an appointment from the appointment table</h4>
     * The appointment with the id specified by the caller of the method is deleted from the appointment table.
     * @param id The Id which should be deleted
     * @return true if deleted successfully, false if deletion failed
     */
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
