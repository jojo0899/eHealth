package eHealth;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class EditAppointmentWindow extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField dateField;
	
	private String user;
	
	private AppointmentsDB appDB = new AppointmentsDB();
	private User userUsed;

	public EditAppointmentWindow(String user) {
		
		userUsed = new User(user);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 335, 290);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Appointment");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(6, 6, 203, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblId.setBounds(6, 45, 70, 15);
		contentPane.add(lblId);
		
		idField = new JTextField();
		idField.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		idField.setHorizontalAlignment(SwingConstants.CENTER);
		idField.setBounds(97, 45, 141, 19);
		contentPane.add(idField);
		idField.setColumns(10);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblDate.setBounds(6, 139, 70, 15);
		contentPane.add(lblDate);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblTime.setBounds(6, 181, 70, 15);
		contentPane.add(lblTime);
		
		Calendar now = Calendar.getInstance();
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJFormattedTextField().setBounds(0, 0, 273, 173);
		datePicker.setBounds(88, 139, 176, 29);
		contentPane.add(datePicker);
		
		JComboBox comboBoxHour = new JComboBox();
		comboBoxHour.setModel(new DefaultComboBoxModel(new String[] {"7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"}));
		comboBoxHour.setBounds(98, 177, 70, 27);
		contentPane.add(comboBoxHour);
		
		JComboBox comboBoxMin = new JComboBox();
		comboBoxMin.setModel(new DefaultComboBoxModel(new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"}));
		comboBoxMin.setBounds(168, 177, 70, 27);
		contentPane.add(comboBoxMin);
		
		JButton btnEditThisAppointment = new JButton("Edit this Appointment");
		btnEditThisAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String idInput = idField.getText();
				datePicker.getJFormattedTextField().setText(appDB.getStringColumnFromDB("AppointmentDate", "Appointments", "id = " + idInput));
				//timeField.setText(appDB.getStringColomnFromDB("AppointmentTime", "Appointments", "id = " + idInput));
			}
		});
		btnEditThisAppointment.setBounds(62, 76, 211, 25);
		contentPane.add(btnEditThisAppointment);
		
		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String timeInput = comboBoxHour.getSelectedItem() + ":" + comboBoxMin.getSelectedItem();
				String appointmentDate = datePicker.getJFormattedTextField().getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date date1 = sdf.parse(appointmentDate);
					Date date2 = sdf.parse(((now.get(Calendar.YEAR)) + "-" + now.get(Calendar.MONTH) + 1) + "-"
							+ now.get(Calendar.DAY_OF_MONTH));
					if (date1.compareTo(date2) < 0) {
						showMessageDialog(null, "You can't make an appointment in the past!", "Warning",
								WARNING_MESSAGE);
						return;
					}
					else if (date1.compareTo(date2) == 0) {
						if ((comboBoxHour.getSelectedIndex() + 7)< now.get(Calendar.HOUR_OF_DAY)) {					
							showMessageDialog(null, "You can't make an appointment in the past!\nPlease check your time!", "Warning", WARNING_MESSAGE);
							return;
						}
						else if ((comboBoxHour.getSelectedIndex() + 7)<= now.get(Calendar.HOUR_OF_DAY) & (comboBoxMin.getSelectedIndex() * 5) < now.get(Calendar.MINUTE)) {
							showMessageDialog(null, "You can't make an appointment in the past!\nPlease check your time!", "Warning", WARNING_MESSAGE);
							return;
						}
					}
					else { }
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				
				if(appDB.updateAppointmentInDB(idField.getText(), appointmentDate, timeInput)) 
				{
					dispose();
					showMessageDialog(null, "Appointment succsessfully updated", "Info", WARNING_MESSAGE);
					String queryWhere = " id = " + idField.getText(); 
					String appointmentDocFirstName = appDB.getStringColumnFromDB("docfirstname", "Appointments", queryWhere);
					String appointmentDocLastName = appDB.getStringColumnFromDB("doclastname", "Appointments", queryWhere);
					String appointmentDocAddress = appDB.getStringColumnFromDB("docaddress", "Appointments", queryWhere);
					String appointmentDateInput = appDB.getStringColumnFromDB("AppointmentDate", "Appointments", queryWhere);
					String appointmentTime = appDB.getStringColumnFromDB("AppointmentTime", "Appointments", queryWhere);
					
					String mailText = "Hello " + user + "\n\nYou just succsesfully modified a appointment.\n" +
							"Here is the modified data of the appointment:\n\n" +
							"Name of the Doctor:\t" + appointmentDocFirstName + " " + appointmentDocLastName +
							"\nAddress:\t" + appointmentDocAddress +
							"\nDate & Time:\t" + appointmentDate + " " + appointmentTime +
							"\n\nBest regards,\nYour eHealth Team";
					String subject ="[eHealth] Appointment modified";
					Mail.sendtext(userUsed.getEmail(), subject, mailText);
				}else{
					showMessageDialog(null, "Something went wrong\nPlease select a valid Id", "Warning", WARNING_MESSAGE);
				}
			}
		});
		btnSaveChanges.setBounds(168, 216, 161, 25);
		contentPane.add(btnSaveChanges);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(6, 216, 161, 25);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_1 = new JLabel(":");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(165, 172, 14, 16);
		contentPane.add(lblNewLabel_1);
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
