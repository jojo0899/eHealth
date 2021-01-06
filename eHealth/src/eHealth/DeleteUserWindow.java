package eHealth;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class DeleteUserWindow extends JFrame {

	private JPanel contentPane;
	private JTextField userToDeleteTextField;
	private JButton deleteButton;
	private JButton doneButton;

	public DeleteUserWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Which user should be deleted?");
		lblNewLabel.setBounds(10, 113, 160, 14);
		contentPane.add(lblNewLabel);
		
		userToDeleteTextField = new JTextField();

		userToDeleteTextField.setBounds(205, 110, 130, 20);
		contentPane.add(userToDeleteTextField);
		userToDeleteTextField.setColumns(10);
		
		deleteButton = new JButton("Delete\r\n");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userToDelete = userToDeleteTextField.getText();
				try {
					DBController.deleteUserFromDB(userToDelete);
					String message = "User " + userToDelete + " succesfullyu deleted";
					showMessageDialog(null, message, "Message",WARNING_MESSAGE);
				} catch (SQLException e1) {
					showMessageDialog(null, "User not Found", "Warning", WARNING_MESSAGE);
				}
				
			}
		});
		deleteButton.setBounds(145, 170, 89, 23);
		contentPane.add(deleteButton);
		
		doneButton = new JButton("Done");
		doneButton.setBounds(259, 170, 89, 23);
		contentPane.add(doneButton);
		doneButton.addActionListener(e -> this.dispose());
	}
	
	
	public void createDeleteUserWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUserWindow duw = new DeleteUserWindow();
					duw.setVisible(true);
					duw.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
