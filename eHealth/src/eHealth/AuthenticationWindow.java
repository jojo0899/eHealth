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

public class AuthenticationWindow extends JFrame {

	private JPanel contentPane;
	private JTextField authenticationNumberInputField;

	private User userUsed;

	private String authenticationNumber;

	public AuthenticationWindow(String username) {

		userUsed = new User(username);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter 6-digit number:");
		lblNewLabel.setBounds(27, 162, 109, 14);
		contentPane.add(lblNewLabel);

		JTextPane txtpnYouAreUsing = new JTextPane();
		txtpnYouAreUsing.setText(
				"You are using 2-Factor-Authentication. Click the button below to receive an email with the 6-digit authentication number.");
		txtpnYouAreUsing.setBounds(27, 11, 376, 68);
		contentPane.add(txtpnYouAreUsing);

		authenticationNumberInputField = new JTextField();
		authenticationNumberInputField.setBounds(156, 159, 221, 20);
		contentPane.add(authenticationNumberInputField);
		authenticationNumberInputField.setColumns(10);

		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				authenticationNumber = getRandom6DigitNumberString();
				String mailText = "This is your 6-digit Authentication number: " + authenticationNumber
						+ "\nUse it to login into eHealth now!";
				Mail.sendtext(userUsed.getEmail(), "ehealth login with 2FA", mailText);
			}
		});
		sendButton.setBounds(153, 89, 89, 23);
		contentPane.add(sendButton);

		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (authenticationNumberInputField.getText().equals(authenticationNumber)) {
					if (username.equals("admin")) {
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
		confirmButton.setBounds(202, 206, 89, 23);
		contentPane.add(confirmButton);

	}

	public void authentication() {

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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
