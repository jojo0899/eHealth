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

public class User {

	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String date_of_birth; 
	private String healthInfo = "";
	private String insuranceName;
	private String insuranceType;
	private String street;
	private int streetNo;
	private String zipCode;
	private String city;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private String healthProblemDescription = "";

	private String dbTableName = "USER";
	
	private UserDB userFromDB = new UserDB();
	
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

	public String getHealthProblemDescription() {
		return healthProblemDescription;
	}

	public void setHealthProblemDescription(String healthProblemDescription) {
		this.healthProblemDescription = healthProblemDescription;
	}

	// Getters & Setters
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getDate_of_birth() {
		return date_of_birth;
	}


	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}


	public String getHealthInfo() {
		return healthInfo;
	}


	public void setHealthInfo(String healthInfo) {
		this.healthInfo = healthInfo;
	}
	
	public void addHealthInfo(String healthInfo) {
		if(this.healthInfo == null) {
			this.healthInfo = " ";
		}
		this.healthInfo = this.healthInfo + " ," + healthInfo;
	}


	public String getInsuranceName() {
		return insuranceName;
	}


	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}


	public String getInsuranceType() {
		return insuranceType;
	}


	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public int getStreetNo() {
		return streetNo;
	}


	public void setStreetNo(int streetNo) {
		this.streetNo = streetNo;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	
}
