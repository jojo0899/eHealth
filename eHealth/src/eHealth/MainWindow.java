package eHealth;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;

	
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("eHealth");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 28));
		lblNewLabel.setBounds(6, 0, 155, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Please select your health problem:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(6, 92, 299, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Search radius:");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(6, 176, 136, 22);
		contentPane.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setColumns(10);
		textField_1.setBounds(195, 172, 62, 34);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("km");
		lblNewLabel_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(269, 176, 36, 22);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Reminder:");
		lblNewLabel_1_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_2.setBounds(6, 278, 136, 22);
		contentPane.add(lblNewLabel_1_1_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"no reminder", "1 week before", "3 days before", "1 hour before", "10 minutes before"}));
		comboBox.setBounds(105, 275, 205, 34);
		contentPane.add(comboBox);
		
		JLabel lblUpcoming = new JLabel("Upcoming appointments:");
		lblUpcoming.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblUpcoming.setBounds(390, 6, 354, 34);
		contentPane.add(lblUpcoming);
		
		JLabel lblWelcome = new JLabel("Welcome, ");
		lblWelcome.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblWelcome.setBounds(6, 34, 123, 34);
		contentPane.add(lblWelcome);
		
		JLabel lblLoggedInUser = new JLabel("<<name>>");
		lblLoggedInUser.setForeground(Color.RED);
		lblLoggedInUser.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblLoggedInUser.setBounds(128, 34, 182, 34);
		contentPane.add(lblLoggedInUser);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(6, 126, 304, 38);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(6, 358, 299, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Date:");
		lblNewLabel_1_1_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_2_1.setBounds(6, 210, 62, 22);
		contentPane.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Time:");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_2_1_1.setBounds(6, 244, 62, 22);
		contentPane.add(lblNewLabel_1_1_2_1_1);
		
		textField = new JTextField();
		textField.setBounds(80, 210, 225, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(80, 244, 225, 26);
		contentPane.add(textField_2);
	}
	
	public void createMainWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow mw = new MainWindow();
					mw.setVisible(true);
					mw.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
