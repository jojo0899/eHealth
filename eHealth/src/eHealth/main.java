package eHealth;

public class main {

	public static void main(String[] args) {
		
		DBController.displayListOfAllDBEntries();
		
		
		LoginDialog lw = new LoginDialog();
        lw.createLoginDialog();
	}

}
