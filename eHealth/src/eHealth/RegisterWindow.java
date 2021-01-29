package eHealth;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.color.*;
import javax.swing.border.LineBorder;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.JSeparator;

/**
 * This class is used to generate the register window if you want to create a new account. 
 */
public class RegisterWindow extends JFrame {

	private JPanel contentPane;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField email;
	private JTextField street;
	private JTextField number;
	private JTextField zipcode;
	private JTextField city;
	private JTextField dateofbirth;
	private JTextField insurancename;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField repeatPasswordField;
	
	private UserDB userTable = new UserDB();

	/**
	 * Method to set properties and actions for the register window.
	 */
	public RegisterWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 575, 610);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Create your new Account:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(6, 6, 424, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Firstname:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setBounds(27, 80, 95, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblLastname.setBounds(27, 108, 95, 16);
		contentPane.add(lblLastname);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblStreet.setBounds(27, 164, 95, 16);
		contentPane.add(lblStreet);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNumber.setBounds(27, 192, 95, 16);
		contentPane.add(lblNumber);
		
		JLabel lblZipcode = new JLabel("Zip-Code:");
		lblZipcode.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblZipcode.setBounds(27, 220, 95, 16);
		contentPane.add(lblZipcode);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblCity.setBounds(27, 248, 95, 16);
		contentPane.add(lblCity);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblDateOfBirth.setBounds(27, 276, 107, 16);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblInsurenceType = new JLabel("Insurence type:");
		lblInsurenceType.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblInsurenceType.setBounds(27, 314, 124, 16);
		contentPane.add(lblInsurenceType);
		
		JLabel lblInsuranceName = new JLabel("Insurance name:");
		lblInsuranceName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblInsuranceName.setBounds(27, 348, 124, 16);
		contentPane.add(lblInsuranceName);
		
		firstname = new JTextField();
		firstname.setBounds(244, 76, 300, 26);
		contentPane.add(firstname);
		firstname.setColumns(10);
		
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(244, 104, 300, 26);
		contentPane.add(lastname);
		
		street = new JTextField();
		street.setColumns(10);
		street.setBounds(244, 161, 300, 26);
		contentPane.add(street);
		
		number = new JTextField();
		number.setColumns(10);
		number.setBounds(244, 189, 300, 26);
		contentPane.add(number);
		
		zipcode = new JTextField();
		zipcode.setColumns(10);
		zipcode.setBounds(244, 217, 300, 26);
		contentPane.add(zipcode);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(244, 245, 300, 26);
		contentPane.add(city);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(244, 273, 300, 29);
		contentPane.add(datePicker);
		
		insurancename = new JTextField();
		insurancename.setColumns(10);
		insurancename.setBounds(244, 345, 300, 26);
		contentPane.add(insurancename);
		
		JRadioButton publicButton = new JRadioButton("public");
		publicButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		publicButton.setBounds(244, 311, 95, 23);
		contentPane.add(publicButton);
		
		JRadioButton privateButton = new JRadioButton("private");
		privateButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		privateButton.setBounds(351, 311, 95, 23);
		contentPane.add(privateButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(privateButton);
		group.add(publicButton);
		
		JButton ConfirmBtn = new JButton("Confirm and Register");
		ConfirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Read in text fields
                String firstName = firstname.getText();
                String lastName = lastname.getText();
                String emailAddress = email.getText();
                String addressStreet = street.getText();
                String addressStreetNo = number.getText();
                String adressZipCode = zipcode.getText();
                String addressCity = city.getText();
                String dateOfBirth = datePicker.getJFormattedTextField().getText();
                String healthInformation = null;
                String insuranceType;
                String insuranceName = insurancename.getText();
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                char[] repeatedPassword = repeatPasswordField.getPassword();
                
                if (firstName.equals("") | firstName.length() > 25) {
                	firstname.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null,"Please enter a valid firstname!", "Warning", WARNING_MESSAGE);                	
                	return;
                }
                else {
                	firstname.setBorder(new LineBorder(Color.GREEN, 1));
                }
                if (lastName.equals("") | lastName.length() > 25 ) {
                	lastname.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null,"Please enter a valid lastname!", "Warning", WARNING_MESSAGE);
                	return;
                }
                else {
                	lastname.setBorder(new LineBorder(Color.GREEN, 1));
                }
                                               
                if (emailAddress.equals("") | isValidEmailAddress(emailAddress) == false | emailAddress.length() > 50) {
                	email.setBorder(new LineBorder(Color.RED, 1));
					showMessageDialog(null,"Please enter a valid email address!", "Warning", WARNING_MESSAGE);
		        	return;
				}
                else {
                	email.setBorder(new LineBorder(Color.GREEN, 1));
                }
                
            	if(addressStreet.equals("") | addressStreetNo.equals("") | addressCity.equals("") | adressZipCode.equals("") | 
            			addressStreet.length() > 60 | addressCity.length() > 40 |  adressZipCode.length() > 5 | 
            			isInteger(adressZipCode) == false | isInteger(addressStreetNo) == false) {
                	street.setBorder(new LineBorder(Color.RED, 1));
                	number.setBorder(new LineBorder(Color.RED, 1));
                	city.setBorder(new LineBorder(Color.RED, 1));
                	zipcode.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null,"Please enter valid address data!", "Warning", WARNING_MESSAGE);
                	return;
                }
            	else {
            		String address;
                	address = addressCity+ " " + addressStreet+ " " + addressStreetNo;
                	Map<String, Double> coords;
                	coords = OpenStreetMapUtils.getInstance().getCoordinates(address);
                	BigDecimal lat = new BigDecimal(coords.get("lat"));
                	BigDecimal lon = new BigDecimal(coords.get("lon"));
                	
                	if(lat != null & lon != null) {
                    	street.setBorder(new LineBorder(Color.GREEN, 1));
                    	number.setBorder(new LineBorder(Color.GREEN, 1));
                    	city.setBorder(new LineBorder(Color.GREEN, 1));
                    	zipcode.setBorder(new LineBorder(Color.GREEN, 1));
                    }
                    
                    else if(coords == null) {
                    	street.setBorder(new LineBorder(Color.RED, 1));
                    	number.setBorder(new LineBorder(Color.RED, 1));
                    	city.setBorder(new LineBorder(Color.RED, 1));
                    	zipcode.setBorder(new LineBorder(Color.RED, 1));
                    	showMessageDialog(null,"Please enter valid address data!", "Warning", WARNING_MESSAGE);
                    	return;
                    }
            	}
            	
            	//street number validation 
            	
                int adressStreetNoAsInt = 0;
                try {
                	adressStreetNoAsInt = Integer.parseInt(addressStreetNo);
                }catch(NumberFormatException exc) {
                	showMessageDialog(null, "Enter a valid street no!", "Warning", WARNING_MESSAGE);
                	return;
                } 
   
                                           
                if (dateOfBirth.equals("")) {           
                	showMessageDialog(null,"Please enter your date of birth!", "Warning", WARNING_MESSAGE);
		        	return;
				}              
            
                if(publicButton.isSelected()) {
                	insuranceType = "public";
                }else if(privateButton.isSelected()){
                	insuranceType = "private";
                }else {
                	insuranceType = "";
                	showMessageDialog(null, "Insurance Type must be selected!", "Warning", WARNING_MESSAGE);
                	return;
                }
                if (insuranceName.equals("") | insuranceName.length() > 30) {
                	insurancename.setBorder(new LineBorder(Color.RED, 1));
					showMessageDialog(null,"Please enter a valid insurance name!", "Warning", WARNING_MESSAGE);
		        	return;
				}
                else {
                	insurancename.setBorder(new LineBorder(Color.GREEN, 1));
                }
                if (username.equals("") | username.length() > 15) {
                	usernameField.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null,"Please enter a username!\nMust be smaller or equal 15 characters!", "Warning", WARNING_MESSAGE);
                	return;
                }
                else if (userTable.checkIfUsernameExistsInDB(username)) {
                	usernameField.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null,"This username is not available!\nPlease choose another username!", "Warning", WARNING_MESSAGE);
                	return;
                }    
                else {
                	usernameField.setBorder(new LineBorder(Color.GREEN, 1));
                }
                
                String pwd = new String(password);
                if (pwd.equals("")) {
                	passwordField.setBorder(new LineBorder(Color.RED, 1));
                	repeatPasswordField.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null, "Please enter a password!", "Warning", WARNING_MESSAGE);
                	return;
                }
                else if(!Arrays.equals(password, repeatedPassword)) {
                	passwordField.setBorder(new LineBorder(Color.RED, 1));
                	repeatPasswordField.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null, "Passwords do no match", "Warning", WARNING_MESSAGE);
                	passwordField.setText("");
                	repeatPasswordField.setText("");
                	return;
                }
                else {
                	passwordField.setBorder(new LineBorder(Color.GREEN, 1));
                	repeatPasswordField.setBorder(new LineBorder(Color.GREEN, 1));
                }
                
                                
                //password encryption
                String encryptedPassword = "";
                try {
					encryptedPassword = Password.createhash(password, username);
				} catch (UnsupportedEncodingException e1) {
					showMessageDialog(null, "Password Error", "Warning", WARNING_MESSAGE);
					return;
				}
              
                //insert data into database
                if(userTable.insertUserIntoDB(username, encryptedPassword, emailAddress, firstName, lastName, dateOfBirth,
                									healthInformation, insuranceName, insuranceType, addressStreet,
                									adressStreetNoAsInt, adressZipCode, addressCity)) 
                {
                	dispose();
                	showMessageDialog(null,"Registration finished\nYou can login now");
                }else {
                	showMessageDialog(null,"Registration failed, try again");
                }
			}
		});
		
		ConfirmBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		ConfirmBtn.setBounds(263, 530, 167, 29);
		contentPane.add(ConfirmBtn);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> this.dispose());
		
		cancelButton.setBounds(125, 531, 117, 29);
		contentPane.add(cancelButton);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblUsername.setBounds(27, 410, 124, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPassword.setBounds(27, 437, 124, 16);
		contentPane.add(lblPassword);
		
		JLabel lblRepeatPassword = new JLabel("repeat password:");
		lblRepeatPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblRepeatPassword.setBounds(27, 464, 124, 16);
		contentPane.add(lblRepeatPassword);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(244, 407, 300, 26);
		contentPane.add(usernameField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(244, 434, 300, 26);
		contentPane.add(passwordField);
		
		repeatPasswordField = new JPasswordField();
		repeatPasswordField.setBounds(244, 461, 300, 26);
		contentPane.add(repeatPasswordField);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(27, 136, 85, 16);
		contentPane.add(lblNewLabel_2);
		
		email = new JTextField();
		email.setBounds(244, 133, 300, 26);
		contentPane.add(email);
		email.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(27, 387, 517, 2);
		contentPane.add(separator);
	}
	/**
	 * Method to check if an email pattern is valid.
	 * @param email
	 * @return true/false
	 */
	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
   	   	try {
   	   		InternetAddress emailAddr = new InternetAddress(email);
   	   		emailAddr.validate();
   	   	} 	catch (AddressException ex) {
   	   		result = false;
   	   	}
   	   	return result;
	}
	// https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
	
	/**
	 * Method to check whether a number is an integer or not.
	 * @param input
	 * @return true/false
	 */
	public boolean isInteger(String input) {
	    try {
	        Integer.parseInt(input);
	        return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
	}
	
	/**
	* This is the method used to create the register window.
	*/	
	public void createRegisterWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWindow rw = new RegisterWindow();
					rw.setVisible(true);
					rw.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
