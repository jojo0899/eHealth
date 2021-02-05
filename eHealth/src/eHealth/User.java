package eHealth;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.core.util.FileUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * This class represents the user of the application.
 * @author marin
 *
 */
public class User {

	/**
	 * The username of the user
	 */
	private String username;
	
	/**
	 * The password of the user
	 */
	private String password;
	
	/**
	 * The email of the user
	 */
	private String email;
	
	/**
	 * The first name of the user
	 */
	private String firstName;
	
	/**
	 * The last name of the user
	 */
	private String lastName;
	
	/**
	 * The date of birth of the user
	 */
	private String date_of_birth; 
	
	/**
	 * The health information of the user
	 */
	private String healthInfo = "";
	
	/**
	 * The insurance name of the user
	 */
	private String insuranceName;
	
	/**
	 * The insurance type of the user
	 */
	private String insuranceType;
	
	/**
	 * The street of the user
	 */
	private String street;
	
	/**
	 * The street number of the user
	 */
	private int streetNo;
	
	/**
	 * The zip code of the user
	 */
	private String zipCode;
	
	/**
	 * The city of the user
	 */
	private String city;
	
	/**
	 * The latitude of the user
	 */
	private BigDecimal latitude;
	
	/**
	 * The longitude of the user
	 */
	private BigDecimal longitude;
	
	/**
	 * The health problem description of the user
	 */
	private String healthProblemDescription = "";

	/**
	 * The name of the user table from the database
	 */
	private String dbTableName = "USER";
	
	/**
	 * A instance of the user database allowing the user to acces the user table from the database
	 */
	private UserDB userFromDB = new UserDB();
	
	
	/**
	 * <h4>Constructing user from database</h4>
	 * This constructor uses he user table in our database to find the desired user and set the values of its attributes to the respective columns in the database.
	 * 
	 * @param usernameInput The username in the database table
	 */
	public User(String usernameInput) {
		String queryWhere = "username = '" +usernameInput+"' ";
		username = userFromDB.getStringColumnFromDB("username", dbTableName,queryWhere);
		password = userFromDB.getStringColumnFromDB("password", dbTableName,queryWhere);
		email = userFromDB.getStringColumnFromDB("email", dbTableName,queryWhere);
		firstName = userFromDB.getStringColumnFromDB("firstName", dbTableName,queryWhere);
		lastName = userFromDB.getStringColumnFromDB("lastName", dbTableName,queryWhere);
		date_of_birth = userFromDB.getStringColumnFromDB("dateOfBirth", dbTableName,queryWhere);
		healthInfo = userFromDB.getStringColumnFromDB("healthInfo", dbTableName,queryWhere);
		insuranceName = userFromDB.getStringColumnFromDB("insuranceName", dbTableName,queryWhere);
		insuranceType = userFromDB.getStringColumnFromDB("insuranceType", dbTableName,queryWhere);
		street = userFromDB.getStringColumnFromDB("street", dbTableName,queryWhere);
		streetNo = userFromDB.getIntColomnFromDB("streetNo", usernameInput, dbTableName);
		zipCode = userFromDB.getStringColumnFromDB("zipCode", dbTableName,queryWhere);
		city = userFromDB.getStringColumnFromDB("city", dbTableName,queryWhere);
		latitude = userFromDB.getBigDecimalColomnFromDB("latitude", dbTableName, queryWhere);
		longitude = userFromDB.getBigDecimalColomnFromDB("longitude", dbTableName, queryWhere);
		//System.out.println(latitude+" "+longitude);
	}

	/**
	 * <h4>Copy constructor for user</h4>
	 * This constructor creates a user, using a different user and copying all its attributes to the new user.
	 * 
	 * @param user The user to be copied from
	 */
	public User(User user) {
		this.username = user.username;
		this.password = user.password;
		this.email = user.email;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.date_of_birth = user.date_of_birth;
		this.healthInfo = user.healthInfo;
		this.insuranceName = user.insuranceName;
		this.insuranceType = user.insuranceType;
		this.street = user.street;
		this.streetNo = user.streetNo;
		this.zipCode = user.zipCode;
		this.city = user.city;
		this.latitude = user.latitude;
		this.longitude = user.longitude;
	}

	/**
	 * <h4>Writing a user into a TXT File</h4>
	 * All attributes of the user are formatted into a well readable TXT File.
	 * <p>
	 * The TXT File is saved on the users desktop.
	 * 
	 * @return true, if file was written successfully, else false
	 */
	public Boolean writeUserIntoTxt() {
		try {
			File outputFile = new File(System.getProperty("user.home") + "/Desktop/HealthInformation");
			//Files.setPosixFilePermisions(outputFile.toPath(), EnumSet.of(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, GROUP_READ, GROUP_EXECUTE));
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}
			FileWriter myWriter = new FileWriter(outputFile);

			String healthInfo = "This document contains all the information eHealth stores about you." +
								"\n\n\nPersonal information\n\nUsername: " + this.username + "\nName: " + this.firstName + " " + this.lastName + 
								"\nDate of birth: " + this.date_of_birth + "\nEmail: " + this.email + 
								"\nAddress: " + this.zipCode + " " + this.city + " , " + this.street + " " + this.streetNo + 
								"\n\n\nHealth information\n\nHealth problem:  " + this.healthInfo +
								"\n\nHealth problem description:\n" + healthProblemDescription +"\n\n\nInsurance" + 
								"\n\nInsurance type: " + this.insuranceType + "\nInsurance name: " + this.insuranceName;
			
			myWriter.write(healthInfo);
			myWriter.close();
			myWriter.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * <h4>Writing a user into a PDF File</h4>
	 * All attributes of the user are formatted into a well readable PDF File.
	 * <p>
	 * The PDF File is saved on the users desktop.
	 * 
	 * @return true, if file was written successfully, else false
	 */
	public Boolean writeUserIntoPdf() {
		Document document = new Document();
		String healthInfo = "This document contains all the information eHealth stores about you." +
				"\n\n\nPersonal information\n\nUsername: " + this.username + "\nName: " + this.firstName + " " + this.lastName + 
				"\nDate of birth: " + this.date_of_birth + "\nEmail: " + this.email + 
				"\nAddress: " + this.zipCode + " " + this.city + " , " + this.street + " " + this.streetNo + 
				"\n\n\nHealth information\n\nHealth problem:  " + this.healthInfo + 
				"\n\nHealth problem description:\n" + healthProblemDescription + "\n\n\nInsurance" + 
				"\n\nInsurance type: " + this.insuranceType + "\nInsurance name: " + this.insuranceName;
		try {
			PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.home") + "/Desktop/HealthInformation.pdf"));

			document.open();
			Paragraph para = new Paragraph (healthInfo); 
			document.add(para);
			document.close();
			return true;
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	//https://www.baeldung.com/java-pdf-creation

	/**
	 * <h4>Getting the health problem description</h4>
	 * @return The health problem description of the user
	 */
	public String getHealthProblemDescription() {
		return healthProblemDescription;
	}

	/**
	 * <h4>Setting the health problem description</h4>
	 * @param healthProblemDescription The health problem description to be set
	 */
	public void setHealthProblemDescription(String healthProblemDescription) {
		this.healthProblemDescription = healthProblemDescription;
	}

	/**
	 * <h4>Getting the username</h4>
	 * @return The username of the user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * <h4>Setting the username</h4>
	 * @param username The username to be set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * <h4>Getting the first name</h4>
	 * @return The first name of the user
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * <h4>Setting the first name</h4>
	 * @param firstName The first name to be set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * <h4>Getting the last name</h4>
	 * @return The last name of the user
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * <h4>Setting the last name</h4>
	 * @param lastName The last name to be set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * <h4>Getting the date of birth</h4>
	 * @return The date of birth of the user
	 */
	public String getDate_of_birth() {
		return date_of_birth;
	}


	/**
	 * <h4>Setting the date of birth</h4>
	 * @param date_of_birth The date of birth to be set
	 */
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}


	/**
	 * <h4>Getting the health information</h4>
	 * @return The password of the user
	 */
	public String getHealthInfo() {
		return healthInfo;
	}


	/**
	 * <h4>Setting the health information</h4>
	 * @param healthInfo The health information to be set
	 */
	public void setHealthInfo(String healthInfo) {
		this.healthInfo = healthInfo;
	}
	
	/**
	 * <h4>Adding health information</h4>
	 * A new health information is added to the already existing health information.
	 * 
	 * @param healthInfo The health information to be added
	 */
	public void addHealthInfo(String healthInfo) {
		if(this.healthInfo == null) {
			this.healthInfo = " ";
		}
		this.healthInfo = this.healthInfo + " ," + healthInfo;
	}


	/**
	 * <h4>Getting the insurance name</h4>
	 * @return The insurance name of the user
	 */
	public String getInsuranceName() {
		return insuranceName;
	}


	/**
	 * <h4>Setting the insurance name</h4>
	 * @param insuranceName The insurance name to be set
	 */
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}


	/**
	 * <h4>Getting the insurance type</h4>
	 * @return The insurance type of the user
	 */
	public String getInsuranceType() {
		return insuranceType;
	}


	/**
	 * <h4>Setting the insurance type</h4>
	 * @param insuranceType The insurance type to be set
	 */
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}


	/**
	 * <h4>Getting the street</h4>
	 * @return The street of the user
	 */
	public String getStreet() {
		return street;
	}


	/**
	 * <h4>Setting the street</h4>
	 * @param street The street to be set
	 */
	public void setStreet(String street) {
		this.street = street;
	}


	/**
	 * <h4>Getting the street number</h4>
	 * @return The street number of the user
	 */
	public int getStreetNo() {
		return streetNo;
	}


	/**
	 * <h4>Setting the street number</h4>
	 * @param streetNo The street number to be set
	 */
	public void setStreetNo(int streetNo) {
		this.streetNo = streetNo;
	}


	/**
	 * <h4>Getting the zip code</h4>
	 * @return The zip code of the user
	 */
	public String getZipCode() {
		return zipCode;
	}


	/**
	 * <h4>Setting the zip code</h4>
	 * @param zipCode The zip code to be set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	/**
	 * <h4>Getting the city</h4>
	 * @return The city of the user
	 */
	public String getCity() {
		return city;
	}


	/**
	 * <h4>Setting the city</h4>
	 * @param city The city to be set
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * <h4>Getting the email</h4>
	 * @return The email of the user
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * <h4>Setting the email</h4>
	 * @param email The email to be set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * <h4>Getting the latitude</h4>
	 * @return The latitude of the user
	 */
	public BigDecimal getLatitude() {
		return latitude;
	}


	/**
	 * <h4>Setting the latitude</h4>
	 * @param latitude The latitude to be set
	 */
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	/**
	 * <h4>Getting the longitude</h4>
	 * @return The longitude of the user
	 */
	public BigDecimal getLongitude() {
		return longitude;
	}

	/**
	 * <h4>Setting the longitude</h4>
	 * @param longitude The longitude to be set
	 */
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	
}
