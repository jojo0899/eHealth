package eHealth;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class AdminWindow extends JFrame {

	private JPanel contentPane;

	
	public AdminWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Admin Account Management");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(6, 6, 424, 42);
		contentPane.add(lblNewLabel_1);
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
