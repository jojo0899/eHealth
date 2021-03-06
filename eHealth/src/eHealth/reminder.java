package eHealth;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * This class is used to send reminder e-mails. 
 * @author Pascal
 *
 */

public class reminder extends Thread {

	
	
	/**
     * <h4>Send reminder mails</h4>
     * this class is used to check every minute if a user need a reminder, and then send the user an e-mail,
     * with the appointment information stored in the database.     
     */
	
	public void run() {
		UserDB userTable = new UserDB();
		AppointmentsDB appDB = new AppointmentsDB();				
		java.util.Date dateapp = null;
		Date datenow = null;
		String timeString = null;
		
		ResultSet reminderList = null;
		
		while (true) {
			reminderList = appDB.reminderlist();
			datenow = new Date(System.currentTimeMillis());
			
			try {
				while (reminderList.next()) {
					timeString = reminderList.getString(1) + "/"+reminderList.getString(2);
					dateapp = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss").parse(timeString);
					long timediff ;
					if (dateapp.getTime()<datenow.getTime()) {
						timediff = -1;
						
					}else {
						timediff = Math.abs(dateapp.getTime()-datenow.getTime());
						timediff = TimeUnit.MINUTES.convert(timediff, TimeUnit.MILLISECONDS);
					}
					
					int n = reminderList.getInt(3);
					
					if(timediff< n) {
						
						String queryWhere = " id = " + reminderList.getString(5);
						String appointmentDocFirstName = appDB.getStringColumnFromDB("docfirstname", "Appointments", queryWhere);
						String appointmentDocLastName = appDB.getStringColumnFromDB("doclastname", "Appointments", queryWhere);
						String appointmentDocAddress = appDB.getStringColumnFromDB("docaddress", "Appointments", queryWhere);
						String appointmentDate = appDB.getStringColumnFromDB("AppointmentDate", "Appointments", queryWhere);
						String appointmentTime = appDB.getStringColumnFromDB("AppointmentTime", "Appointments", queryWhere);
												
						String mailText = "Hello " + reminderList.getString(4) + "\n\nThis is a reminder for your next appointment.\n"
								+ "In the following You can find the most important information regarding your appointment:\n\n"
								+ "Name of the Doctor:\t" + appointmentDocFirstName + " " + appointmentDocLastName
								+ "\nAddress:\t" + appointmentDocAddress + "\nDate & Time:\t" + appointmentDate + " "
								+ appointmentTime
								+ "\n\nYou can still edit or cancel the appointment through the eHealth Application."
								+ "\n\nBest regards,\nYour eHealth Team";
						String subject = "[eHealth] Appointment reminder";
						String usernameString = "username = '" + reminderList.getString(4) +"'";
						
						String mailString = userTable.getStringColumnFromDB("email", "user", usernameString );	
						
						Mail.sendtext(mailString, subject, mailText);
						appDB.updateReminder(reminderList.getString(5), "0");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				TimeUnit.SECONDS.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}	
}
