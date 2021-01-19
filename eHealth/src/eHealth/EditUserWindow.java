package eHealth;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

public class EditUserWindow extends JFrame {
	private JPanel contentPane;
	private JTextField userNameField;
	private JTextField firstNameField;
	private JTextField LastNameField;
	private JTextField streetNumberField;
	private JTextField zipCodeField;
	private JTextField cityField;
	private JTextField dateOfBirthField;
	private JPasswordField passwordField;
	private JTextField insuranceNameField;
	private JTextField streetField;
	
	private UserDB userTable = new UserDB();
	private JTextField emailField;


	public EditUserWindow() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Edit User Account");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(6, 6, 424, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Firstname:");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel.setBounds(27, 178, 95, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblLastname.setBounds(27, 206, 95, 16);
		contentPane.add(lblLastname);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblStreet.setBounds(27, 260, 95, 16);
		contentPane.add(lblStreet);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNumber.setBounds(27, 288, 95, 16);
		contentPane.add(lblNumber);
		
		JLabel lblZipcode = new JLabel("Zip-Code:");
		lblZipcode.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblZipcode.setBounds(27, 316, 95, 16);
		contentPane.add(lblZipcode);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblCity.setBounds(27, 344, 95, 16);
		contentPane.add(lblCity);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth(YYYY-MM-DD):");
		lblDateOfBirth.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDateOfBirth.setBounds(27, 372, 218, 16);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblInsurenceType = new JLabel("Insurence type:");
		lblInsurenceType.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblInsurenceType.setBounds(27, 400, 124, 16);
		contentPane.add(lblInsurenceType);
		
		JLabel lblInsuranceName = new JLabel("Insurance name:");
		lblInsuranceName.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblInsuranceName.setBounds(27, 428, 124, 16);
		contentPane.add(lblInsuranceName);
		
		userNameField = new JTextField();
		userNameField.setEnabled(false);
		userNameField.setColumns(10);
		userNameField.setBounds(230, 68, 300, 26);
		contentPane.add(userNameField);
		
		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		firstNameField.setBounds(244, 174, 300, 26);
		contentPane.add(firstNameField);
		
		LastNameField = new JTextField();
		LastNameField.setColumns(10);
		LastNameField.setBounds(244, 202, 300, 26);
		contentPane.add(LastNameField);
		
		streetNumberField = new JTextField();
		streetNumberField.setColumns(10);
		streetNumberField.setBounds(244, 284, 300, 26);
		contentPane.add(streetNumberField);
		
		zipCodeField = new JTextField();
		zipCodeField.setColumns(10);
		zipCodeField.setBounds(244, 312, 300, 26);
		contentPane.add(zipCodeField);
		
		cityField = new JTextField();
		cityField.setColumns(10);
		cityField.setBounds(244, 340, 300, 26);
		contentPane.add(cityField);
		
		dateOfBirthField = new JTextField();
		dateOfBirthField.setColumns(10);
		dateOfBirthField.setBounds(244, 368, 300, 26);
		contentPane.add(dateOfBirthField);
		
		JRadioButton publicButton = new JRadioButton("public");
		publicButton.setBounds(244, 393, 95, 23);
		contentPane.add(publicButton);
		
		JRadioButton privateButton = new JRadioButton("private");
		privateButton.setBounds(353, 393, 95, 23);
		contentPane.add(privateButton);
		
		JButton confirmButton = new JButton("Confirm ");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int streetNo = Integer.parseInt(streetNumberField.getText());
				
				String insuranceType;
                
                if(publicButton.isSelected()) {
                	insuranceType = "public";
                }else{
                	insuranceType = "private";
                }
                // cant change username & password yet
                if (firstNameField.getText().equals("") | firstNameField.getText().length() > 25) {
                	firstNameField.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null,"Please enter a valid firstname!", "Warning", WARNING_MESSAGE);                	
                	return;
                }
                else {
                	firstNameField.setBorder(new LineBorder(Color.GREEN, 1));
                }
                if (LastNameField.getText().equals("") | LastNameField.getText().length() > 25 ) {
                	LastNameField.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null,"Please enter a valid lastname!", "Warning", WARNING_MESSAGE);
                	return;
                }
                else {
                	LastNameField.setBorder(new LineBorder(Color.GREEN, 1));
                }
                                               
                if (emailField.getText().equals("") /*| isValidEmailAddress(emailAddress) == false */| emailField.getText().length() > 50) {
                	emailField.setBorder(new LineBorder(Color.RED, 1));
					showMessageDialog(null,"Please enter a valid email address!", "Warning", WARNING_MESSAGE);
		        	return;
				}
                else {
                	emailField.setBorder(new LineBorder(Color.GREEN, 1));
                }
                
                // address validation not completely working yet
            	if(streetField.getText().equals("") | streetNumberField.getText().equals("") | cityField.getText().equals("") | streetField.getText().length() > 60 | cityField.getText().length() > 40) {
                	streetField.setBorder(new LineBorder(Color.RED, 1));
                	streetNumberField.setBorder(new LineBorder(Color.RED, 1));
                	cityField.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null,"Please enter valid address data!", "Warning", WARNING_MESSAGE);
                	return;
                }
            	else {
            		String address;
                	address = cityField.getText()+ " " + streetField.getText()+ " " + streetNumberField.getText();
                	Map<String, Double> coords;
                	coords = OpenStreetMapUtils.getInstance().getCoordinates(address);
                	BigDecimal lat = new BigDecimal(coords.get("lat"));
                	BigDecimal lon = new BigDecimal(coords.get("lon"));
                	
                	if(lat != null & lon != null) {
                    	streetField.setBorder(new LineBorder(Color.GREEN, 1));
                    	streetNumberField.setBorder(new LineBorder(Color.GREEN, 1));
                    	cityField.setBorder(new LineBorder(Color.GREEN, 1));
                    }
                    
                    else if(coords == null) {
                    	streetField.setBorder(new LineBorder(Color.RED, 1));
                    	streetNumberField.setBorder(new LineBorder(Color.RED, 1));
                    	cityField.setBorder(new LineBorder(Color.RED, 1));
                    	showMessageDialog(null,"Please enter valid address data!", "Warning", WARNING_MESSAGE);
                    	return;
                    }
            	}
            	
          
                              
                if (dateOfBirthField.getText().equals("")) {
                	dateOfBirthField.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null,"Please enter a valid date of birth!", "Warning", WARNING_MESSAGE);
		        	return;
				}
                else {
                	dateOfBirthField.setBorder(new LineBorder(Color.GREEN, 1));
                }
          /*      if (healthInformation.equals("") | healthInformation.length() > 30) {
                	healthinfo.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null,"Please enter your health information!", "Warning", WARNING_MESSAGE);
		        	return;
				}
                else {
                	healthinfo.setBorder(new LineBorder(Color.GREEN, 1));
                }
          */    if(publicButton.isSelected()) {
                	insuranceType = "public";
                }else if(privateButton.isSelected()){
                	insuranceType = "private";
                }else {
                	insuranceType = "";
                	showMessageDialog(null, "Insurance Type must be selected!", "Warning", WARNING_MESSAGE);
                	return;
                }
                if (insuranceNameField.getText().equals("") | insuranceNameField.getText().length() > 30) {
                	insuranceNameField.setBorder(new LineBorder(Color.RED, 1));
					showMessageDialog(null,"Please enter a valid insurance name!", "Warning", WARNING_MESSAGE);
		        	return;
				}
                
                if(userTable.updateUserInDB(userNameField.getText(), emailField.getText() , firstNameField.getText(), LastNameField.getText(), 
						dateOfBirthField.getText(), null, insuranceNameField.getText(), 
						insuranceType,streetField.getText(),  streetNo,zipCodeField.getText(), cityField.getText())) 
				{
					showMessageDialog(null, "User succsessfully updated", "Info", WARNING_MESSAGE);
				}else{
					showMessageDialog(null, "Something went wrong", "Warning", WARNING_MESSAGE);
				}
			}
		});
		confirmButton.setFont(new Font("Dialog", Font.PLAIN, 13));
		confirmButton.setBounds(209, 513, 167, 29);
		contentPane.add(confirmButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(27, 513, 135, 29);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(e -> this.dispose());
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblUsername.setBounds(27, 72, 124, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPassword.setBounds(27, 456, 124, 16);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(244, 452, 300, 26);
		contentPane.add(passwordField);
		
		insuranceNameField = new JTextField();
		insuranceNameField.setBounds(244, 427, 300, 20);
		contentPane.add(insuranceNameField);
		insuranceNameField.setColumns(10);
		
		JButton selectUserButton = new JButton("Edit this User\r\n");
		selectUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usernameInput = userNameField.getText();
				if(userTable.checkIfUsernameExistsInDB(usernameInput)) {
					userNameField.setText(userTable.getStringColomnFromDB("USERNAME", usernameInput, "USER"));
					emailField.setText(userTable.getStringColomnFromDB("EMAIL", usernameInput, "USER"));
					firstNameField.setText(userTable.getStringColomnFromDB("FIRSTNAME", usernameInput, "USER"));
					LastNameField.setText(userTable.getStringColomnFromDB("LASTNAME", usernameInput, "USER"));
					streetField.setText(userTable.getStringColomnFromDB("STREET", usernameInput, "USER"));
					streetNumberField.setText(userTable.getStringColomnFromDB("STREETNO", usernameInput, "USER"));
					zipCodeField.setText(userTable.getStringColomnFromDB("ZIPCODE", usernameInput, "USER"));
					cityField.setText(userTable.getStringColomnFromDB("CITY", usernameInput, "USER"));
					dateOfBirthField.setText(userTable.getStringColomnFromDB("DATEOFBIRTH", usernameInput, "USER"));
					//healthInfoField.setText(userTable.getStringColomnFromDB("HEALTHINFO", usernameInput, "USER"));
					insuranceNameField.setText(userTable.getStringColomnFromDB("INSURANCENAME", usernameInput, "USER"));
					
					if(userTable.getStringColomnFromDB("INSURANCETYPE", usernameInput, "USER").equals("public")) {
						publicButton.setSelected(true);
					}else {
						privateButton.setSelected(true);
					}
				}else {
					showMessageDialog(null, "User does not exist", "Warning", WARNING_MESSAGE);
				}

			}
		});
		selectUserButton.setBounds(269, 106, 209, 23);
		contentPane.add(selectUserButton);
		
		streetField = new JTextField();
		streetField.setBounds(244, 259, 300, 20);
		contentPane.add(streetField);
		streetField.setColumns(10);
		
		JButton doneButton = new JButton("Done\r\n");
		doneButton.setBounds(409, 512, 135, 27);
		contentPane.add(doneButton);
		
		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(27, 234, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		emailField = new JTextField();
		emailField.setBounds(244, 232, 300, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 150, 570, 12);
		contentPane.add(separator);
		doneButton.addActionListener(e -> this.dispose());
	}
	
	
	public void createEditUserWindow(String usernameInput) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditUserWindow euw = new EditUserWindow();
					euw.userNameField.setText(usernameInput);
					euw.setVisible(true);
					euw.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
