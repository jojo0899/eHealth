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
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterWindow extends JFrame {

	private JPanel contentPane;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField street;
	private JTextField number;
	private JTextField zipcode;
	private JTextField city;
	private JTextField dateofbirth;
	private JTextField healthinfo;
	private JTextField insurancename;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField repeatPasswordField;
	
	private UserDB userTable = new UserDB();

	
	public RegisterWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 560, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Create your new Account:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(6, 6, 424, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Firstname:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setBounds(27, 79, 95, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblLastname.setBounds(27, 107, 95, 16);
		contentPane.add(lblLastname);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblStreet.setBounds(27, 135, 95, 16);
		contentPane.add(lblStreet);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNumber.setBounds(27, 163, 95, 16);
		contentPane.add(lblNumber);
		
		JLabel lblZipcode = new JLabel("Zip-Code:");
		lblZipcode.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblZipcode.setBounds(27, 191, 95, 16);
		contentPane.add(lblZipcode);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblCity.setBounds(27, 219, 95, 16);
		contentPane.add(lblCity);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth(YYYY-MM-DD):");
		lblDateOfBirth.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblDateOfBirth.setBounds(27, 248, 207, 16);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblHealthInformation = new JLabel("Health information:");
		lblHealthInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblHealthInformation.setBounds(27, 275, 140, 16);
		contentPane.add(lblHealthInformation);
		
		JLabel lblInsurenceType = new JLabel("Insurence type:");
		lblInsurenceType.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblInsurenceType.setBounds(27, 303, 124, 16);
		contentPane.add(lblInsurenceType);
		
		JLabel lblInsuranceName = new JLabel("Insurance name:");
		lblInsuranceName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblInsuranceName.setBounds(27, 331, 124, 16);
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
		street.setBounds(244, 132, 300, 26);
		contentPane.add(street);
		
		number = new JTextField();
		number.setColumns(10);
		number.setBounds(244, 160, 300, 26);
		contentPane.add(number);
		
		zipcode = new JTextField();
		zipcode.setColumns(10);
		zipcode.setBounds(244, 188, 300, 26);
		contentPane.add(zipcode);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(244, 216, 300, 26);
		contentPane.add(city);
		
		dateofbirth = new JTextField();
		dateofbirth.setColumns(10);
		dateofbirth.setBounds(244, 245, 300, 26);
		contentPane.add(dateofbirth);
		
		healthinfo = new JTextField();
		healthinfo.setColumns(10);
		healthinfo.setBounds(244, 272, 300, 26);
		contentPane.add(healthinfo);
		
		insurancename = new JTextField();
		insurancename.setColumns(10);
		insurancename.setBounds(244, 328, 300, 26);
		contentPane.add(insurancename);
		
		JRadioButton publicButton = new JRadioButton("public");
		publicButton.setBounds(263, 302, 95, 23);
		contentPane.add(publicButton);
		
		JRadioButton privateButton = new JRadioButton("private");
		privateButton.setBounds(367, 302, 95, 23);
		contentPane.add(privateButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(privateButton);
		group.add(publicButton);
		
		// Add exception Handling for falsely formatted inputs
		// Add pw check
		JButton ConfirmBtn = new JButton("Confirm and Register");
		ConfirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                char[] repeatedPassword = repeatPasswordField.getPassword();
                if(!Arrays.equals(password, repeatedPassword)) {
                	showMessageDialog(null, "Passwords do no match", "Warning", WARNING_MESSAGE);
                	passwordField.setText("");
                	repeatPasswordField.setText("");
                	return;
                }
                String encryptedPassword = "";
                try {
					encryptedPassword = Password.createhash(password, username);
				} catch (UnsupportedEncodingException e1) {
					showMessageDialog(null, "Password Error", "Warning", WARNING_MESSAGE);
				}
                String firstName = firstname.getText();
                String lastName = lastname.getText();
                String dateOfBirth = dateofbirth.getText();
                String healthInformation = healthinfo.getText();
                String insuranceName = insurancename.getText();
                String insuranceType;
                
                if(publicButton.isSelected()) {
                	insuranceType = "public";
                }else if(privateButton.isSelected()){
                	insuranceType = "private";
                }else {
                	insuranceType = "";
                	showMessageDialog(null, "Insurance Type must be selected!", "Warning", WARNING_MESSAGE);
                	return;
                }
                String addressStreet = street.getText();
                String addressStreetNo = number.getText();
                int adressStreetNoAsInt = 0;
                try {
                	adressStreetNoAsInt = Integer.parseInt(addressStreetNo);
                }catch(NumberFormatException exc) {
                	showMessageDialog(null, "Enter a valid street no!", "Warning", WARNING_MESSAGE);
                }
   
                
                String adressZipCode = zipcode.getText();
                String addressCity = city.getText();
                
                if(userTable.insertUserIntoDB(username, encryptedPassword, firstName, lastName, dateOfBirth,
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
		ConfirmBtn.setBounds(263, 467, 167, 29);
		contentPane.add(ConfirmBtn);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> this.dispose());
		
		cancelButton.setBounds(129, 467, 117, 29);
		contentPane.add(cancelButton);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblUsername.setBounds(27, 359, 124, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPassword.setBounds(27, 387, 124, 16);
		contentPane.add(lblPassword);
		
		JLabel lblRepeatPassword = new JLabel("repeat password:");
		lblRepeatPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblRepeatPassword.setBounds(27, 415, 124, 16);
		contentPane.add(lblRepeatPassword);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(244, 356, 300, 26);
		contentPane.add(usernameField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(244, 384, 300, 26);
		contentPane.add(passwordField);
		
		repeatPasswordField = new JPasswordField();
		repeatPasswordField.setBounds(244, 412, 300, 26);
		contentPane.add(repeatPasswordField);
	}
	
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
