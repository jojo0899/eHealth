package eHealth;

public class main {

	public static void main(String[] args) {
		
		
		// AKTUELLE TABELLEN LÖSCHEN UND EINMAL AUSFUEHREN DAMIT DIE NEUE TABELLE ERSTELLT WIRD
		//DBController.createUserDBTable();
		
		UserDB userTable = new UserDB();
		userTable.displayListOfAllDBEntries();
		
		
		LoginDialog lw = new LoginDialog();
        lw.createLoginDialog();
	}

}
