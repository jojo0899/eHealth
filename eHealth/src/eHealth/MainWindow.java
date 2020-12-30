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

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	
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
		
		JLabel lblNewLabel_1 = new JLabel("Please enter your health problem:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(6, 46, 299, 22);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(6, 80, 299, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Search radius:");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(6, 130, 136, 22);
		contentPane.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(141, 126, 164, 34);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("km");
		lblNewLabel_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(307, 131, 136, 22);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Reminder:");
		lblNewLabel_1_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_2.setBounds(6, 164, 136, 22);
		contentPane.add(lblNewLabel_1_1_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"no reminder", "1 week before", "3 days before", "1 hour before", "10 minutes before"}));
		comboBox.setBounds(141, 161, 164, 34);
		contentPane.add(comboBox);
		
		JLabel lblUpcoming = new JLabel("Upcoming appointments:");
		lblUpcoming.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblUpcoming.setBounds(390, 6, 354, 34);
		contentPane.add(lblUpcoming);
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
