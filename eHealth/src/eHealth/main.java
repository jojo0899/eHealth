package eHealth;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;


import javax.mail.search.AddressStringTerm;







public class main {

	public static void main(String[] args) {
		
		UserDB userTable = new UserDB();
		DoctorDB docTable = new DoctorDB();
		
		
		
//		userTable.displayListOfAllDBEntries();	
//	
//		docTable.displayListOfAllDBEntries("dentist");
//		docTable.displayListOfAllDBEntries("familydoc"); 
		
		//testing
//		AppointmentsDB ap = new AppointmentsDB();
//		ap.createAppointmentsDBTable();
//		ap.insertIntoAppointmentsDBTable("marin", "harbert", "Dr. hensemaier", "An der straser strase 3, frankfurt", "2021-01-01", "15:00");
		
		Thread reminder = new reminder();
		reminder.start();
			
		
		
		 
		
		
		
		
//		
		LoginDialog lw = new LoginDialog();
        lw.createLoginDialog();
	}
	
	

}
