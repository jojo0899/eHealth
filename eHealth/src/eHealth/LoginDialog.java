package eHealth;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import static javax.swing.JOptionPane.*;
import java.awt.Color;
import java.awt.SystemColor;

public class LoginDialog extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	
	private UserDB userTable = new UserDB();
	private DoctorDB docTables = new DoctorDB();
	private AppointmentsDB appTable = new AppointmentsDB();

	
	public LoginDialog() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("eHealth Login");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 35));
		lblNewLabel_1.setBounds(113, 36, 256, 52);
		contentPane.add(lblNewLabel_1);
		
		JLabel usernamelbl = new JLabel("username:");
		usernamelbl.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		usernamelbl.setBounds(95, 120, 87, 16);
		contentPane.add(usernamelbl);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPassword.setBounds(95, 153, 87, 16);
		contentPane.add(lblPassword);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		usernameField.setBounds(194, 115, 175, 26);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		passwordField.setBounds(194, 148, 175, 26);
		contentPane.add(passwordField);
		
		JButton loginButton = new JButton("login");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login();
				
				//Mail.sendtext("@gmail.com", "ehealth project test", "test1");
			}
		});
		loginButton.setBounds(377, 149, 117, 29);
		contentPane.add(loginButton);
		
		JButton CreateAccButton = new JButton("Create new Account");
		CreateAccButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterWindow rw = new RegisterWindow();
                rw.createRegisterWindow();
			}
		});
		CreateAccButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		CreateAccButton.setBounds(194, 186, 175, 29);
		contentPane.add(CreateAccButton);
		
		JButton btnNewButton = new JButton("@");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String createDBInfoText = "If you are using the Application for the first time click YES (the Database will then be created).\nAs a next step you have to create a admin account.";
				int confirm = JOptionPane.showConfirmDialog(null, createDBInfoText, "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (confirm == 0) {
					userTable.createUserDBTable();
					docTables.createPopulatedDoctorDB();
					appTable.createAppointmentsDBTable();
				}else {
					return;
				}
				
			}
		});
		btnNewButton.setBounds(10, 11, 43, 23);
		contentPane.add(btnNewButton);
	}
	
	private void login() {
		String user;
		user = usernameField.getText();
	
		char[] password = passwordField.getPassword();
	    String encryptedPassword = "";
	    try {
			encryptedPassword = Password.createhash(password, user);
		} catch (UnsupportedEncodingException e1) {
			showMessageDialog(null, "Password Error", "Warning", WARNING_MESSAGE);
		}
		
		if(userTable.validLoginData(user, encryptedPassword)) {
			dispose();
			AuthenticationWindow.createAuthenticationWindow(user);
		}
		else {
			showMessageDialog(null, "Wrong Username or Password!\nPlease try again!", "Warning", WARNING_MESSAGE);
			usernameField.setText("");
			passwordField.setText("");
			
		}
	}
	
	


	
	public void createLoginDialog() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginDialog lw = new LoginDialog();
					lw.setVisible(true);
					lw.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
