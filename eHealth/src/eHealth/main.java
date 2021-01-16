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
		
		
		LoginDialog lw = new LoginDialog();
        lw.createLoginDialog();
	}

}
