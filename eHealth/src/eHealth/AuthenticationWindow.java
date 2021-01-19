package eHealth;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;

public class AuthenticationWindow extends JFrame {

	private JPanel contentPane;
	private JTextField authenticationNumberInputField;

	private User userUsed;

	private String authenticationNumber;

	public AuthenticationWindow(String username) {

		userUsed = new User(username);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 127, 80));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Enter 6-digit number:");
		lblNewLabel.setBounds(156, 168, 138, 14);

		authenticationNumberInputField = new JTextField();
		authenticationNumberInputField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (authenticationNumberInputField.getText().equals(authenticationNumber)) {
					if (username.equals("admin")) {
						dispose();
						AdminWindow aw = new AdminWindow();
						aw.createAdminWindow();
					} else {
						dispose();
						MainWindow.createMainWindow(username);
					}
				} else {
					showMessageDialog(null, "Wrong Authentication code", "Warning", WARNING_MESSAGE);
				}
			}
		});
		authenticationNumberInputField.setHorizontalAlignment(SwingConstants.CENTER);
		authenticationNumberInputField.setBounds(114, 194, 221, 26);
		authenticationNumberInputField.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		authenticationNumberInputField.setColumns(10);

		JButton sendButton = new JButton("Send");
		sendButton.setBounds(156, 115, 138, 23);
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				authenticationNumber = getRandom6DigitNumberString();
				System.out.println(authenticationNumber);
				String mailText = "This is your 6-digit Authentication number: " + authenticationNumber
						+ "\nUse it to login into eHealth now!";
				Mail.sendtext(userUsed.getEmail(), "ehealth login with 2FA", mailText);
			}
		});

		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBounds(131, 231, 187, 23);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (authenticationNumberInputField.getText().equals(authenticationNumber)) {
					if (username.equals("admin")) {
						dispose();
						AdminWindow aw = new AdminWindow();
						aw.createAdminWindow();
					} else {
						dispose();
						MainWindow.createMainWindow(username);
					}
				} else {
					showMessageDialog(null, "Wrong Authentication code", "Warning", WARNING_MESSAGE);
				}
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("You are using 2-Factor-Authentication.");
		lblNewLabel_1.setBounds(47, 16, 356, 31);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		
		JLabel lblNewLabel_2 = new JLabel("Click the button below to receive an email");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(71, 59, 308, 16);
		
		JLabel lblNewLabel_3 = new JLabel(" with the 6-digit authentication number.");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(79, 80, 292, 23);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Button.darkShadow"));
		separator.setBounds(0, 144, 455, 12);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel_2);
		contentPane.add(lblNewLabel_3);
		contentPane.add(sendButton);
		contentPane.add(separator);
		contentPane.add(lblNewLabel);
		contentPane.add(authenticationNumberInputField);
		contentPane.add(confirmButton);

	}

	public static String getRandom6DigitNumberString() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		return String.format("%06d", number);
	}

	public static void createAuthenticationWindow(String user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthenticationWindow frame = new AuthenticationWindow(user);
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
