package eHealth;

public class main {

	public static void main(String[] args) {
		
		
		// AKTUELLE TABELLEN LÖSCHEN UND EINMAL AUSFUEHREN DAMIT DIE NEUE TABELLE ERSTELLT WIRD
		DBController.createUserDBTable();
		
		DBController.displayListOfAllDBEntries();
		
		
		LoginDialog lw = new LoginDialog();
        lw.createLoginDialog();
	}

}
