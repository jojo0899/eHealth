package eHealth;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.h2.engine.User;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;

/**
 * This class is used to generate the admin window, so it provides the basic functions to view, edit and delete users. 
 * @author johannes
 *
 */
public class AdminWindow extends JFrame {

	/**
	 * creating the JPanel where every GUI element is placed on
	 */
	private JPanel contentPane;
	/**
	 * creating new JTable to display all users with their personal information
	 */
	private JTable table;
	/**
	 * creating new JTextfield for entering the user id which is wanted to edit or delete
	 */
	private JTextField userSelectionTextField;
	/**
	 * A instance of the user database allowing the system to access the user table from the database
	 */
	private UserDB userTable = new UserDB();

	/**
	 * <h4>Defining properties for the admin window GUI</h4>
	 * <p>
	 * This method is used set up the properties and actions for the admin window.
	 */
	public AdminWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1333, 570);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Admin Account Management");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(6, 6, 424, 42);
		contentPane.add(lblNewLabel_1);
		
		JButton viewUsersButton = new JButton("View Users");
		/**
		 * action to view all users out of the user database
		 */
		viewUsersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					userTable.resultSetToTableModel(table, "user", "", 13);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		viewUsersButton.setBounds(34, 454, 169, 23);
		contentPane.add(viewUsersButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 60, 1291, 382);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton editUserButton = new JButton("Edit this User\r\n");
		/**
		 * action to edit selected user
		 */
		editUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userTable.checkIfUsernameExistsInDB(userSelectionTextField.getText())) {
					EditUserWindow edtu = new EditUserWindow();
					edtu.createEditUserWindow(userSelectionTextField.getText());
				}
				else {
					showMessageDialog(null, "User not found", "Message",WARNING_MESSAGE);
					userSelectionTextField.setText("");
				}
				
			}
		});
		editUserButton.setBounds(954, 501, 173, 23);
		contentPane.add(editUserButton);
		
		JButton deleteButton = new JButton("Delete this User");
		deleteButton.setForeground(Color.RED);
		/**
		 * action to delete selected user
		 */
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userToDelete = userSelectionTextField.getText();
				
				if(userTable.checkIfUsernameExistsInDB(userToDelete)) {
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this User?", "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
					if (confirm == 0) {
							userTable.deleteUserFromDB(userToDelete);
							String message = "User " + userToDelete + " succesfully deleted";
							showMessageDialog(null, message, "Message",WARNING_MESSAGE);
							userSelectionTextField.setText("");
					}
					else return;
				}
				else {
					showMessageDialog(null, "User not found", "Message",WARNING_MESSAGE);
					userSelectionTextField.setText("");
				}
			}
		});
		deleteButton.setBounds(1138, 501, 169, 23);
		contentPane.add(deleteButton);
		
		JButton refreshButton = new JButton("Refresh\r\n");
		/**
		 * action to refresh the displayed table of the user database
		 */
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					userTable.resultSetToTableModel(table, "user", "", 13);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refreshButton.setBounds(215, 454, 169, 23);
		contentPane.add(refreshButton);
		
		JLabel lblNewLabel = new JLabel("Please enter the username of the user you like to edit or delete:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(149, 488, 564, 42);
		contentPane.add(lblNewLabel);
		
		userSelectionTextField = new JTextField();
		userSelectionTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		userSelectionTextField.setBounds(723, 498, 221, 26);
		contentPane.add(userSelectionTextField);
		userSelectionTextField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 477, 1307, 12);
		contentPane.add(separator);
		
		JButton btnNewButton = new JButton("Leave Admin View");
		/**
		 * action to leave admin view and go back to the login dialog
		 */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave Admin view?\nAny unsaved Changes won't be saved!", "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				if (confirm == 0) {
					dispose();
					LoginDialog lw = new LoginDialog();
			        lw.createLoginDialog();
				}
				else return;
			}
		});
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		btnNewButton.setBounds(1109, 6, 198, 29);
		contentPane.add(btnNewButton);
	}

	/**
	 * <h4>Create admin window</h4>
	 * Method to create the admin window
	 */
	public void createAdminWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow aw = new AdminWindow();
					aw.setVisible(true);
					aw.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}