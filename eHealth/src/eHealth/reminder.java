package eHealth;


import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class reminder extends Thread {

	
	
	
	
	public void run() {
		UserDB userTable = new UserDB();
		AppointmentsDB appDB = new AppointmentsDB();				
		java.util.Date dateapp = null;
		Date datenow = null;
		String timeString = null;
		
		ResultSet reminderList = null;
		
		while (true) {
			reminderList =appDB.reminderlist();
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
					System.out.println(timediff);
					int n = reminderList.getInt(3);
					
					if(timediff< n) {
						
						String queryWhere = " id = " + reminderList.getString(5);
						String appointmentDocFirstName = appDB.getStringColomnFromDB("docfirstname", "Appointments", queryWhere);
						String appointmentDocLastName = appDB.getStringColomnFromDB("doclastname", "Appointments", queryWhere);
						String appointmentDocAddress = appDB.getStringColomnFromDB("docaddress", "Appointments", queryWhere);
						String appointmentDate = appDB.getStringColomnFromDB("AppointmentDate", "Appointments", queryWhere);
						String appointmentTime = appDB.getStringColomnFromDB("AppointmentTime", "Appointments", queryWhere);
												
						String mailText = "Hello " + reminderList.getString(4) + "\n\nThis is areminder for your next appointment.\n"
								+ "In the following You can find the most important information regarding your appointment:\n\n"
								+ "Name of the Doctor:\t" + appointmentDocFirstName + " " + appointmentDocLastName
								+ "\nAddress:\t" + appointmentDocAddress + "\nDate & Time:\t" + appointmentDate + " "
								+ appointmentTime
								+ "\n\nYou can still edit or cancel the appointment through the eHealth Application."
								+ "\n\nBest regards,\nYour eHealth Team";
						String subject = "[eHealth] Appointment reminder";
						String usernameString = "username = '" + reminderList.getString(4) +"'";
						
						String mailString = userTable.getStringColomnFromDB("email", "user", usernameString );	
						
						System.out.println(mailString + subject + mailText);
						
						//Mail.sendtext(mailString, subject, mailText);
						//appDB.updateReminder(reminderList.getString(5), "0");
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
