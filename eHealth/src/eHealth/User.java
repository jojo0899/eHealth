package eHealth;

public class User {

	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String date_of_birth; 
	private String healthInfo;
	private String insuranceName;
	private String insuranceType;
	private String street;
	private int streetNo;
	private String zipCode;
	private String city;
	
	private String dbTableName = "USER";
	
	private UserDB userFromDB = new UserDB();
	
	public User(String usernameInput) {
		username = userFromDB.getStringColomnFromDB("username", usernameInput, dbTableName);
		password = userFromDB.getStringColomnFromDB("password", usernameInput, dbTableName);
		email = userFromDB.getStringColomnFromDB("email", usernameInput, dbTableName);
		firstName = userFromDB.getStringColomnFromDB("firstName", usernameInput, dbTableName);
		lastName = userFromDB.getStringColomnFromDB("lastName", usernameInput, dbTableName);
		date_of_birth = userFromDB.getStringColomnFromDB("dateOfBirth", usernameInput, dbTableName);
		healthInfo = userFromDB.getStringColomnFromDB("healthInfo", usernameInput, dbTableName);
		insuranceName = userFromDB.getStringColomnFromDB("insuranceName", usernameInput, dbTableName);
		insuranceType = userFromDB.getStringColomnFromDB("insuranceType", usernameInput, dbTableName);
		street = userFromDB.getStringColomnFromDB("street", usernameInput, dbTableName);
		streetNo = userFromDB.getIntColomnFromDB("streetNo", usernameInput, dbTableName);
		zipCode = userFromDB.getStringColomnFromDB("zipCode", usernameInput, dbTableName);
		city = userFromDB.getStringColomnFromDB("city", usernameInput, dbTableName);
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
	
	
}