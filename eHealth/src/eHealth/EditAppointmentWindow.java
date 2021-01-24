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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditAppointmentWindow extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField dateField;
	private JTextField timeField;
	
	private String user;
	
	private AppointmentsDB appDB = new AppointmentsDB();
	private User userUsed;

	public EditAppointmentWindow(String user) {
		
		userUsed = new User(user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Appointment");
		lblNewLabel.setBounds(26, 12, 163, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(36, 39, 70, 15);
		contentPane.add(lblId);
		
		idField = new JTextField();
		idField.setBounds(162, 37, 114, 19);
		contentPane.add(idField);
		idField.setColumns(10);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(36, 139, 70, 15);
		contentPane.add(lblDate);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(36, 166, 70, 15);
		contentPane.add(lblTime);
		
		dateField = new JTextField();
		dateField.setBounds(162, 137, 114, 19);
		contentPane.add(dateField);
		dateField.setColumns(10);
		
		timeField = new JTextField();
		timeField.setBounds(162, 164, 114, 19);
		contentPane.add(timeField);
		timeField.setColumns(10);
		
		JButton btnEditThisAppointment = new JButton("Edit this Appointment");
		btnEditThisAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String idInput = idField.getText();
				dateField.setText(appDB.getStringColomnFromDB("AppointmentDate", "Appointments", "id = " + idInput));
				timeField.setText(appDB.getStringColomnFromDB("AppointmentTime", "Appointments", "id = " + idInput));
			}
		});
		btnEditThisAppointment.setBounds(159, 68, 211, 25);
		contentPane.add(btnEditThisAppointment);
		
		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(appDB.updateAppointmentInDB(idField.getText(), dateField.getText(), timeField.getText())) 
				{
					showMessageDialog(null, "User succsessfully updated", "Info", WARNING_MESSAGE);
					String queryWhere = " id = " + idField.getText(); 
					String appointmentDocFirstName = appDB.getStringColomnFromDB("docfirstname", "Appointments", queryWhere);
					String appointmentDocLastName = appDB.getStringColomnFromDB("doclastname", "Appointments", queryWhere);
					String appointmentDocAddress = appDB.getStringColomnFromDB("docaddress", "Appointments", queryWhere);
					String appointmentDate = appDB.getStringColomnFromDB("AppointmentDate", "Appointments", queryWhere);
					String appointmentTime = appDB.getStringColomnFromDB("AppointmentTime", "Appointments", queryWhere);
					
					String mailText = "Hello " + user + "\n\nYou just succsesfully modified a appointment.\n" +
							"Here is the modified data of the appointment:\n\n" +
							"Name of the Doctor:\t" + appointmentDocFirstName + " " + appointmentDocLastName +
							"\nAddress:\t" + appointmentDocAddress +
							"\nDate & Time:\t" + appointmentDate + " " + appointmentTime +
							"\n\nBest regards,\nYour eHealth Team";
					String subject ="[eHealth] Appointment modified";
					
					Mail.sendtext(userUsed.getEmail(), subject, mailText);
				}else{
					showMessageDialog(null, "Something went wrong", "Warning", WARNING_MESSAGE);
				}
			}
		});
		btnSaveChanges.setBounds(61, 206, 141, 25);
		contentPane.add(btnSaveChanges);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(248, 206, 117, 25);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(e -> this.dispose());
	}
	
	public void createEditAppointmentWindow(String id, String username) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditAppointmentWindow frame = new EditAppointmentWindow(username);
					frame.idField.setText(id);
					frame.user = username;
					frame.setVisible(true);
					frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
