package eHealth;

import java.io.UnsupportedEncodingException;

public class main {

	public static void main(String[] args) {
		
		UserDB userTable = new UserDB();
		
		// AKTUELLE TABELLEN LÖSCHEN UND EINMAL AUSFUEHREN DAMIT DIE NEUE TABELLE ERSTELLT WIRD
		//userTable.createUserDBTable();

		
		userTable.displayListOfAllDBEntries();	
		
		LoginDialog lw = new LoginDialog();
        lw.createLoginDialog();
	}

}
