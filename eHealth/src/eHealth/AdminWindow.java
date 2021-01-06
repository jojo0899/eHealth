package eHealth;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class AdminWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;

	
	public AdminWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1283, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
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
		viewUsersButton.setBounds(10, 90, 169, 23);
		contentPane.add(viewUsersButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(189, 90, 1015, 382);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
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
