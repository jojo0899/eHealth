package eHealth;

public class main {

	public static void main(String[] args) {
		
		
		//DBController.insertUserIntoDB("test1", "passwd", "person", "andaname", "1999-11-11", "sick", "someins", "public", "somestreet", 34, "234233", "frankfurt");
		
		DBController.displayListOfAllDBEntries();
		
		
		LoginDialog lw = new LoginDialog();
        lw.createLoginDialog();
	}

}
