package eHealth;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditUserWindow extends JFrame {
	private JPanel contentPane;
	private JTextField userNameField;
	private JTextField firstNameField;
	private JTextField LastNameField;
	private JTextField streetNumberField;
	private JTextField zipCodeField;
	private JTextField cityField;
	private JTextField dateOfBirthField;
	private JTextField healthInfoField;
	private JPasswordField passwordField;
	private JTextField insuranceNameField;
	private JTextField streetField;
	
	private UserDB userTable = new UserDB();
	private JTextField emailField;


	public EditUserWindow() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 622, 715);
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
		lblNewLabel.setBounds(27, 197, 95, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblLastname.setBounds(27, 224, 95, 16);
		contentPane.add(lblLastname);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblStreet.setBounds(27, 314, 95, 16);
		contentPane.add(lblStreet);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNumber.setBounds(27, 354, 95, 16);
		contentPane.add(lblNumber);
		
		JLabel lblZipcode = new JLabel("Zip-Code:");
		lblZipcode.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblZipcode.setBounds(27, 408, 95, 16);
		contentPane.add(lblZipcode);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblCity.setBounds(27, 435, 95, 16);
		contentPane.add(lblCity);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth(YYYY-MM-DD):");
		lblDateOfBirth.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDateOfBirth.setBounds(27, 462, 207, 16);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblHealthInformation = new JLabel("Health information:");
		lblHealthInformation.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblHealthInformation.setBounds(27, 489, 140, 16);
		contentPane.add(lblHealthInformation);
		
		JLabel lblInsurenceType = new JLabel("Insurence type:");
		lblInsurenceType.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblInsurenceType.setBounds(27, 381, 124, 16);
		contentPane.add(lblInsurenceType);
		
		JLabel lblInsuranceName = new JLabel("Insurance name:");
		lblInsuranceName.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblInsuranceName.setBounds(27, 516, 124, 16);
		contentPane.add(lblInsuranceName);
		
		userNameField = new JTextField();
		userNameField.setColumns(10);
		userNameField.setBounds(220, 102, 300, 26);
		contentPane.add(userNameField);
		
		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		firstNameField.setBounds(244, 194, 300, 26);
		contentPane.add(firstNameField);
		
		LastNameField = new JTextField();
		LastNameField.setColumns(10);
		LastNameField.setBounds(244, 224, 300, 26);
		contentPane.add(LastNameField);
		
		streetNumberField = new JTextField();
		streetNumberField.setColumns(10);
		streetNumberField.setBounds(244, 344, 300, 26);
		contentPane.add(streetNumberField);
		
		zipCodeField = new JTextField();
		zipCodeField.setColumns(10);
		zipCodeField.setBounds(244, 398, 300, 26);
		contentPane.add(zipCodeField);
		
		cityField = new JTextField();
		cityField.setColumns(10);
		cityField.setBounds(244, 425, 300, 26);
		contentPane.add(cityField);
		
		dateOfBirthField = new JTextField();
		dateOfBirthField.setColumns(10);
		dateOfBirthField.setBounds(244, 459, 300, 26);
		contentPane.add(dateOfBirthField);
		
		JRadioButton publicButton = new JRadioButton("public");
		publicButton.setBounds(259, 374, 95, 23);
		contentPane.add(publicButton);
		
		JRadioButton privateButton = new JRadioButton("private");
		privateButton.setBounds(368, 374, 95, 23);
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
				if(userTable.updateUserInDB(userNameField.getText(),  firstNameField.getText(), emailField.getText(), LastNameField.getText(), 
						dateOfBirthField.getText(), healthInfoField.getText(), insuranceNameField.getText(), 
						insuranceType,streetField.getText(),  streetNo,zipCodeField.getText(), cityField.getText())) 
				{
					showMessageDialog(null, "User succsessfully updated", "Info", WARNING_MESSAGE);
				}else{
					showMessageDialog(null, "Something went wrong", "Warning", WARNING_MESSAGE);
				}
			}
		});
		confirmButton.setFont(new Font("Dialog", Font.PLAIN, 13));
		confirmButton.setBounds(209, 636, 167, 29);
		contentPane.add(confirmButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(47, 637, 117, 29);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(e -> this.dispose());
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblUsername.setBounds(27, 105, 124, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPassword.setBounds(27, 543, 124, 16);
		contentPane.add(lblPassword);
		
		healthInfoField = new JTextField();
		healthInfoField.setColumns(10);
		healthInfoField.setBounds(244, 486, 300, 26);
		contentPane.add(healthInfoField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(244, 543, 300, 26);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Select the User Account to edit");
		lblNewLabel_2.setBounds(27, 70, 326, 14);
		contentPane.add(lblNewLabel_2);
		
		insuranceNameField = new JTextField();
		insuranceNameField.setBounds(244, 516, 300, 20);
		contentPane.add(insuranceNameField);
		insuranceNameField.setColumns(10);
		
		JButton selectUserButton = new JButton("Select User\r\n");
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
					healthInfoField.setText(userTable.getStringColomnFromDB("HEALTHINFO", usernameInput, "USER"));
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
		selectUserButton.setBounds(324, 139, 89, 23);
		contentPane.add(selectUserButton);
		
		streetField = new JTextField();
		streetField.setBounds(244, 314, 300, 20);
		contentPane.add(streetField);
		streetField.setColumns(10);
		
		JButton doneButton = new JButton("Done\r\n");
		doneButton.setBounds(437, 636, 109, 27);
		contentPane.add(doneButton);
		
		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setBounds(35, 278, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		emailField = new JTextField();
		emailField.setBounds(244, 275, 300, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
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
