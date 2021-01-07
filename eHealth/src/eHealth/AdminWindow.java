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
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class AdminWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField userSelectionTextField;

	
	public AdminWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 600);
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
		viewUsersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DBController.resultSetToTableModel(table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		viewUsersButton.setBounds(34, 454, 169, 23);
		contentPane.add(viewUsersButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 60, 1015, 382);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton editUserButton = new JButton("Edit this User\r\n");
		editUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditUserWindow edtu = new EditUserWindow();
				edtu.createEditUserWindow();
			}
		});
		editUserButton.setBounds(749, 501, 173, 23);
		contentPane.add(editUserButton);
		
		JButton deleteButton = new JButton("Delete this User");
		deleteButton.setForeground(Color.RED);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userToDelete = userSelectionTextField.getText();
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this User?", "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				if (confirm == 0) {
					try {
						DBController.deleteUserFromDB(userToDelete);
						String message = "User " + userToDelete + " succesfully deleted";
						showMessageDialog(null, message, "Message",WARNING_MESSAGE);
						userSelectionTextField.setText("");
					} catch (SQLException e1) {
						showMessageDialog(null, "User not Found", "Warning", WARNING_MESSAGE);
					}
					//DeleteUserWindow  duw = new DeleteUserWindow();
					//duw.createDeleteUserWindow();
				}
				else return;
			}
		});
		deleteButton.setBounds(925, 501, 169, 23);
		contentPane.add(deleteButton);
		
		JButton refreshButton = new JButton("Refresh\r\n");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DBController.resultSetToTableModel(table);
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
		lblNewLabel.setBounds(6, 489, 583, 42);
		contentPane.add(lblNewLabel);
		
		userSelectionTextField = new JTextField();
		userSelectionTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		userSelectionTextField.setBounds(516, 498, 221, 26);
		contentPane.add(userSelectionTextField);
		userSelectionTextField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 477, 1100, 12);
		contentPane.add(separator);
	}

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
