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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class EditUserWindow extends JFrame {
	private JPanel contentPane;
	private JTextField userNameField;
	private JTextField firstNameField;
	private JTextField LastNameField;
	private JTextField streetNumberField;
	private JTextField zipCodeField;
	private JTextField cityField;
	private JTextField dateOfBirthField;
	private JTextField insuranceNameField;
	private JTextField streetField;
	
	private UserDB userTable = new UserDB();
	private JTextField emailField;


	public EditUserWindow() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Edit User Account");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(6, 6, 424, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Firstname:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setBounds(27, 178, 95, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblLastname.setBounds(27, 206, 95, 16);
		contentPane.add(lblLastname);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblStreet.setBounds(27, 260, 95, 16);
		contentPane.add(lblStreet);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNumber.setBounds(27, 288, 95, 16);
		contentPane.add(lblNumber);
		
		JLabel lblZipcode = new JLabel("Zip-Code:");
		lblZipcode.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblZipcode.setBounds(27, 316, 95, 16);
		contentPane.add(lblZipcode);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblCity.setBounds(27, 344, 95, 16);
		contentPane.add(lblCity);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth(YYYY-MM-DD):");
		lblDateOfBirth.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblDateOfBirth.setBounds(27, 372, 218, 16);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblInsurenceType = new JLabel("Insurence type:");
		lblInsurenceType.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblInsurenceType.setBounds(27, 400, 124, 16);
		contentPane.add(lblInsurenceType);
		
		JLabel lblInsuranceName = new JLabel("Insurance name:");
		lblInsuranceName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblInsuranceName.setBounds(27, 428, 124, 16);
		contentPane.add(lblInsuranceName);
		
		userNameField = new JTextField();
		userNameField.setBackground(Color.WHITE);
		userNameField.setHorizontalAlignment(SwingConstants.CENTER);
		userNameField.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		userNameField.setEnabled(false);
		userNameField.setColumns(10);
		userNameField.setBounds(244, 67, 300, 26);
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
		
		ButtonGroup group = new ButtonGroup();
		group.add(privateButton);
		group.add(publicButton);
		
		JButton confirmButton = new JButton("Save Changes");
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
            	if(streetField.getText().equals("") | streetNumberField.getText().equals("") | cityField.getText().equals("") | zipCodeField.getText().equals("") |
            			streetField.getText().length() > 60 | cityField.getText().length() > 40 |  zipCodeField.getText().length() > 5 | 
            			isInteger(zipCodeField.getText()) == false | isInteger(streetNumberField.getText()) == false) {
                	streetField.setBorder(new LineBorder(Color.RED, 1));
                	streetNumberField.setBorder(new LineBorder(Color.RED, 1));
                	cityField.setBorder(new LineBorder(Color.RED, 1));
                	zipCodeField.setBorder(new LineBorder(Color.RED, 1));
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
                    	zipCodeField.setBorder(new LineBorder(Color.GREEN, 1));
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
                else {
                	insuranceNameField.setBorder(new LineBorder(Color.GREEN, 1));
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
		confirmButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		confirmButton.setBounds(209, 472, 167, 29);
		contentPane.add(confirmButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(27, 472, 135, 29);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(e -> this.dispose());
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblUsername.setBounds(27, 72, 124, 16);
		contentPane.add(lblUsername);
		
		insuranceNameField = new JTextField();
		insuranceNameField.setBounds(244, 427, 300, 20);
		contentPane.add(insuranceNameField);
		insuranceNameField.setColumns(10);
		
		JButton selectUserButton = new JButton("Edit this User\r\n");
		selectUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usernameInput = userNameField.getText();
				if(userTable.checkIfUsernameExistsInDB(usernameInput)) {
					String queryWhere = "username = '" +usernameInput+"' ";
					userNameField.setText(userTable.getStringColomnFromDB("USERNAME", "USER", queryWhere));
					emailField.setText(userTable.getStringColomnFromDB("EMAIL", "USER",queryWhere));
					firstNameField.setText(userTable.getStringColomnFromDB("FIRSTNAME", "USER",queryWhere));
					LastNameField.setText(userTable.getStringColomnFromDB("LASTNAME", "USER",queryWhere));
					streetField.setText(userTable.getStringColomnFromDB("STREET", "USER",queryWhere));
					streetNumberField.setText(userTable.getStringColomnFromDB("STREETNO", "USER",queryWhere));
					zipCodeField.setText(userTable.getStringColomnFromDB("ZIPCODE", "USER",queryWhere));
					cityField.setText(userTable.getStringColomnFromDB("CITY", "USER",queryWhere));
					dateOfBirthField.setText(userTable.getStringColomnFromDB("DATEOFBIRTH", "USER",queryWhere));
					//healthInfoField.setText(userTable.getStringColomnFromDB("HEALTHINFO", usernameInput, "USER"));
					insuranceNameField.setText(userTable.getStringColomnFromDB("INSURANCENAME", "USER",queryWhere));
					
					if(userTable.getStringColomnFromDB("INSURANCETYPE", "USER",queryWhere).equals("public")) {
						publicButton.setSelected(true);
					}else {
						privateButton.setSelected(true);
					}
				}else {
					showMessageDialog(null, "User does not exist", "Warning", WARNING_MESSAGE);
				}

			}
		});
		selectUserButton.setBounds(285, 105, 209, 23);
		contentPane.add(selectUserButton);
		
		streetField = new JTextField();
		streetField.setBounds(244, 259, 300, 20);
		contentPane.add(streetField);
		streetField.setColumns(10);
		
		JButton doneButton = new JButton("Done\r\n");
		doneButton.setBounds(409, 471, 135, 27);
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
	
	
	public boolean isInteger(String input) {
	    try {
	        Integer.parseInt(input);
	        return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
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
