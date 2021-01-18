package eHealth;

import java.io.UnsupportedEncodingException;

public class main {

	public static void main(String[] args) {
		
		UserDB userTable = new UserDB();
		DoctorDB docTable = new DoctorDB();
		
		
//		userTable.displayListOfAllDBEntries();	
//	
//		docTable.displayListOfAllDBEntries("dentist");
//		docTable.displayListOfAllDBEntries("familydoc"); 
		
		//testing
		AppointmentsDB ap = new AppointmentsDB();
		ap.createAppointmentsDBTable();
		ap.insertIntoAppointmentsDBTable("marin", "harbert", "Dr. hensemaier", "An der straser strase 3, frankfurt", "2021-01-01", "15:00");
		
		LoginDialog lw = new LoginDialog();
        lw.createLoginDialog();
	}

}
