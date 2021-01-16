package eHealth;

import java.io.UnsupportedEncodingException;

public class main {

	public static void main(String[] args) {
		
		UserDB userTable = new UserDB();
		DoctorDB docTable = new DoctorDB();
		
		// change main to only create tables if they do not exist yet
		
		// AKTUELLE TABELLEN LÖSCHEN UND EINMAL AUSFUEHREN DAMIT DIE NEUE TABELLE ERSTELLT WIRD
		userTable.createUserDBTable();

		docTable.createDoctorDBTable("dentist");
		docTable.createDoctorDBTable("familydoc");
		
		/*		---TESTING---
		
		docTable.insertIntoDoctorDBTable("dentist", "martin", "jochen", "Festplatzstraße 2, 61118 Bad Vilbel");
		docTable.insertIntoDoctorDBTable("dentist", "mazar", "abdulah", "Wilhelmstraße 22, 61118 Bad Vilbel");
		docTable.insertIntoDoctorDBTable("dentist", "herbert", "schweizer", "Tucholskystraße 33, 61118 Bad Vilbel");
		
		docTable.insertIntoDoctorDBTable("familydoc", "karl", "jarvis", "Auf dem Niederberg 43, 61118 Bad Vilbel");
		docTable.insertIntoDoctorDBTable("familydoc", "boris", "schmeichel", "Auf dem Niederberg 1A, 61118 Bad Vilbel");
		docTable.insertIntoDoctorDBTable("familydoc", "schaad", "martin", "Auf dem Niederberg 19, 61118 Bad Vilbel");
		
		userTable.displayListOfAllDBEntries();	
	
		docTable.displayListOfAllDBEntries("dentist");
		docTable.displayListOfAllDBEntries("familydoc"); */
		
		
		LoginDialog lw = new LoginDialog();
        lw.createLoginDialog();
	}

}
