package eHealth;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSeparator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;

/**
 * This class is used to generate the main window where you have options 
 * @author johannes
 * 
 */
public class MainWindow extends JFrame {

	/**
	 * creating the JPanel where every GUI element is placed on
	 */
	private JPanel contentPane;
	/**
	 * creating new JTextfield for entering the search radius
	 */
	private JTextField radius;
	/**
	 * creating new JTextfield for entering the wanted doctor ID
	 */
	private JTextField docIdField;
	/**
	 * creating new JTable to display all upcoming appointments of the logged in user
	 */
	private JTable appointmentsTable;
	/**
	 * creating new JTable to display the result of the doctor search
	 */
	private JTable searchResultTable;
	/**
	 * creating new JTextArea to enter a description of the health problem
	 */
	private JTextArea problemDescription;
	/**
	 * creating new JTextfield for entering the appointment ID
	 */
	private JTextField appointmentIdField;
	
	private String doctorType;
	private String appointmentTime;
	private String appointmentDate;

	private User userUsed;
	
	/**
	 * A instance of the user database allowing the system to access the user table from the database
	 */
	private UserDB userDB = new UserDB();
	/**
	 * A instance of the user database allowing the system to access the doctor table from the database
	 */
	private DoctorDB docDB = new DoctorDB();
	/**
	 * A instance of the user database allowing the system to access the appointment table from the database
	 */
	private AppointmentsDB appDB = new AppointmentsDB();


	private String appointmentDocFirstName;
	private String appointmentDocLastName;
	private String appointmentDocAddress;

	private int reminderInMin = -1;

	/**
	 * <h4>Defining properties for the main window GUI</h4>
	 * <p>
	 * This method is used set up the properties and actions for the main window.
	 * 
	 * @param username
	 */
	public MainWindow(String username) {

		userUsed = new User(username);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 775);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("eHealth");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 28));
		lblNewLabel.setBounds(6, 0, 155, 34);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Please select your health problem:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(6, 92, 335, 22);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Search radius:");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(6, 297, 136, 22);
		contentPane.add(lblNewLabel_1_1);

		radius = new JTextField();
		radius.setHorizontalAlignment(SwingConstants.RIGHT);
		radius.setColumns(10);
		radius.setBounds(205, 299, 107, 22);
		contentPane.add(radius);

		JLabel lblNewLabel_1_1_1 = new JLabel("km");
		lblNewLabel_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(324, 297, 36, 22);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Reminder:");
		lblNewLabel_1_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_2.setBounds(6, 399, 136, 22);
		contentPane.add(lblNewLabel_1_1_2);

		JComboBox<String> comboBoxReminder = new JComboBox<String>();

		comboBoxReminder.setModel(new DefaultComboBoxModel<String>(new String[] { "no reminder", "1 week before",
				"3 days before", "1 hour before", "10 minutes before" }));
		comboBoxReminder.setBounds(124, 396, 236, 34);
		contentPane.add(comboBoxReminder);

		JLabel lblUpcoming = new JLabel("Upcoming appointments:");
		lblUpcoming.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblUpcoming.setBounds(6, 519, 354, 34);
		contentPane.add(lblUpcoming);

		JLabel lblWelcome = new JLabel("Welcome, ");
		lblWelcome.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblWelcome.setBounds(6, 34, 169, 34);
		contentPane.add(lblWelcome);

		JLabel loggedInUser = new JLabel();
		loggedInUser.setText(userUsed.getUsername());
		loggedInUser.setForeground(new Color(0, 0, 0));
		loggedInUser.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		loggedInUser.setBounds(140, 34, 183, 34);
		contentPane.add(loggedInUser);

		JComboBox<String> comboBoxProblem = new JComboBox<String>();
		comboBoxProblem.setModel(new DefaultComboBoxModel<String>(
				new String[] { "---", "Eye pain", "Weak vision", "Watery eyes", "Cough", "Sniff", "Fever", "Headache",
						"Itchy skin", "Acne", "Toothache", "Gingivitis", "Jaw pain" }));
		comboBoxProblem.setBounds(6, 116, 354, 38);
		contentPane.add(comboBoxProblem);

		JComboBox<String> comboBoxHour = new JComboBox<String>();
		comboBoxHour.setModel(new DefaultComboBoxModel<String>(
				new String[] { "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17" }));
		comboBoxHour.setBounds(214, 368, 72, 27);
		contentPane.add(comboBoxHour);

		JComboBox<String> comboBoxMin = new JComboBox<String>();
		comboBoxMin.setModel(new DefaultComboBoxModel<String>(
				new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
		comboBoxMin.setBounds(288, 368, 72, 27);
		contentPane.add(comboBoxMin);
		
		JButton makeAppointmentBtn = new JButton("Make appointment");
		makeAppointmentBtn.setEnabled(false);

		Calendar now = Calendar.getInstance();

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(205, 327, 155, 29);
		contentPane.add(datePicker);
		// source: https://www.javaer101.com/de/article/1525991.html

		JButton searchButton = new JButton("Search for Doctor");
		/**
		 * action to search with the given data for a doctor
		 */
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String radiusString = radius.getText();
				int radiusInt = 0;
				appointmentDate = datePicker.getJFormattedTextField().getText();
				appointmentTime = (comboBoxHour.getSelectedItem() + ":" + comboBoxMin.getSelectedItem());

				makeAppointmentBtn.setEnabled(false);
				
				switch (comboBoxProblem.getSelectedIndex()) {
				case 0:
					// Warning
					showMessageDialog(null, "Please select your health problem!", "Warning", WARNING_MESSAGE);
					return;
				case 1:
				case 2:
				case 3:
					// Augenarzt
					System.out.println("AU");
					doctorType = "Oculist";
					break;
				case 4:
				case 5:
				case 6:
				case 7:
					// Hausarzt
					System.out.println("HA");
					doctorType = "FamilyDoctor";
					break;
				case 8:
				case 9:
					// Hautarzt
					System.out.println("HAT");
					doctorType = "Dermatologist";
					break;
				case 10:
				case 11:
				case 12:
					// Zahnarzt
					System.out.println("ZA");
					doctorType = "Dentist";
					break;

				default:
					showMessageDialog(null, "Please select a Health Problem!", "Warning", WARNING_MESSAGE);
					return;
				}
				String healthProblemInput = "";
				switch (comboBoxProblem.getSelectedIndex()) {
				case 0:
					break;
				case 1:
					healthProblemInput = "Eye pain";
					break;
				case 2:
					healthProblemInput = "Weak vision";
					break;
				case 3:
					healthProblemInput = "Watery eyes";
					break;
				case 4:
					healthProblemInput = "Cough";
					break;
				case 5:
					healthProblemInput = "Sniff";
					break;
				case 6:
					healthProblemInput = "Fever";
					break;
				case 7:
					healthProblemInput = "Headache";
					break;
				case 8:
					healthProblemInput = "Itchy skin";
					break;
				case 9:
					healthProblemInput = "Acne";
					break;
				case 10:
					healthProblemInput = "Toothache";
					break;
				case 11:
					healthProblemInput = "gingivitis";
					break;
				case 12:
					healthProblemInput = "Jaw pain";
					break;
				default:
					break;
				}

				switch (comboBoxReminder.getSelectedIndex()) {
				case 0:
					break;
				case 1:
					reminderInMin = 10080;
					break;
				case 2:
					reminderInMin = 4320;
					break;
				case 3:
					reminderInMin = 60;
					break;
				case 4:
					reminderInMin = 10;
					break;
				default:
					break;
				}

				if (healthProblemInput != "") {
					userUsed.addHealthInfo(healthProblemInput);
					userDB.updateHealthInfoInDB(healthProblemInput, username);
				}

				if (radiusString.equals("") | isInteger(radiusString) == false) {
					radius.setBorder(new LineBorder(Color.RED, 1));
					radius.setBorder(new LineBorder(Color.RED, 1));
					showMessageDialog(null, "Please enter an integer value!", "Warning", WARNING_MESSAGE);
					return;
				} else {
					radius.setBorder(new LineBorder(Color.GREEN, 1));
					radius.setBorder(new LineBorder(Color.GREEN, 1));

					try {
						radiusInt = Integer.parseInt(radiusString);
					} catch (NumberFormatException exc) {
						showMessageDialog(null, "ERROR", "Warning", WARNING_MESSAGE);
						return;
					}
				}
				if ((appointmentDate.equals(""))) {
					showMessageDialog(null, "Please enter a valid date!", "Warning", WARNING_MESSAGE);
					return;
				}

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date inputdate = sdf.parse(appointmentDate);
					Date actualdate = sdf.parse(((now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1) + "-"
							+ (now.get(Calendar.DAY_OF_MONTH))));
					//System.out.println("Input: " + inputdate);
					//System.out.println("Date today: " + actualdate);
					if (inputdate.compareTo(actualdate) < 0) {
						showMessageDialog(null, "You can't make an appointment in the past!", "Warning", WARNING_MESSAGE);
						return;
					}
					else if (inputdate.compareTo(actualdate) == 0) {
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
				// source:
				// https://www.codeflow.site/de/article/java__how-to-compare-dates-in-java				
				
				makeAppointmentBtn.setEnabled(true);
				
				try {
					docDB.resultSetToTableModel(searchResultTable, doctorType, " ", userUsed, radiusInt);
					searchResultTable.getColumnModel().getColumn(3).setPreferredWidth(300);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(problemDescription.getText().equals(null)) {
					userUsed.setHealthProblemDescription("-");
				}else {
					userUsed.setHealthProblemDescription(problemDescription.getText());	
				}

			}

		});
		searchButton.setBounds(6, 451, 354, 29);
		contentPane.add(searchButton);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Date:");
		lblNewLabel_1_1_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_2_1.setBounds(6, 331, 88, 22);
		contentPane.add(lblNewLabel_1_1_2_1);

		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Time:");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_2_1_1.setBounds(6, 365, 187, 22);
		contentPane.add(lblNewLabel_1_1_2_1_1);

		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setForeground(Color.RED);
		logoutBtn.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		/**
		 * action to log out from eHealth and go back to the login dialog
		 */
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to log out from eHealth?\nUnsaved changes won't be saved!", "Warning",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				if (confirm == 0) {
					dispose();
					LoginDialog lw = new LoginDialog();
					lw.createLoginDialog();
				} else
					return;
			}
		});

		logoutBtn.setBounds(1155, 763, 117, 29);
		logoutBtn.setBounds(853, 10, 117, 29);
		contentPane.add(logoutBtn);

		JButton quitBtn = new JButton("Quit");
		quitBtn.setForeground(Color.RED);
		quitBtn.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		/**
		 * action to quit the application
		 */
		quitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to quit eHealth?\nUnsaved changes won't be saved!", "Warning",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				if (confirm == 0) {
					System.exit(0);
				} else
					return;

			}
		});
		quitBtn.setBounds(1155, 826, 117, 29);
		quitBtn.setBounds(960, 10, 117, 29);
		contentPane.add(quitBtn);

		JScrollPane scrollPaneSearchResult = new JScrollPane();
		scrollPaneSearchResult.setBounds(405, 92, 663, 280);
		contentPane.add(scrollPaneSearchResult);

		searchResultTable = new JTable();
		scrollPaneSearchResult.setViewportView(searchResultTable);

		JLabel lblSearchResults = new JLabel("Search results:");
		lblSearchResults.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblSearchResults.setBounds(405, 36, 263, 30);
		contentPane.add(lblSearchResults);

		/**
		 * action to make an appointment at the selected doctor
		 */
		makeAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String docIdString = docIdField.getText();
				int docId = 0;
				try {
					docId = Integer.parseInt(docIdString);
				} catch (NumberFormatException exc) {
					showMessageDialog(null, "Please enter a valid id!", "Warning", WARNING_MESSAGE);
					docIdField.setBorder(new LineBorder(Color.RED, 1));
					return;
				}
				String queryWhere = " id = " + docId;
				appointmentDocFirstName = docDB.getStringColumnFromDB("firstname", doctorType, queryWhere);
				appointmentDocLastName = docDB.getStringColumnFromDB("lastname", doctorType, queryWhere);
				appointmentDocAddress = docDB.getStringColumnFromDB("address", doctorType, queryWhere);
				
				

				String mailText = "Hello " + username + "\n\nYou just succsesfully created a new appointment.\n"
						+ "In the following You can find the most important information regarding your appointment:\n\n"
						+ "Name of the Doctor:\t" + appointmentDocFirstName + " " + appointmentDocLastName
						+ "\nAddress:\t" + appointmentDocAddress + "\nDate & Time:\t" + appointmentDate + " "
						+ appointmentTime
						+ "\n\nYou can still edit or cancel the appointment through the eHealth Application."
						+ "\n\nBest regards,\nYour eHealth Team";
				String subject = "[eHealth] New Appointment Created";
				Mail.sendtext(userUsed.getEmail(), subject, mailText);

				try {
					appDB.insertIntoAppointmentsDBTable(username, appointmentDocFirstName, appointmentDocLastName,
							appointmentDocAddress, appointmentDate, appointmentTime, reminderInMin);
					docIdField.setBorder(new LineBorder(Color.GREEN, 1));
					showMessageDialog(null, "Appointment succesfully created");
				} catch (SQLException e1) {
					docIdField.setBorder(new LineBorder(Color.RED, 1));
					showMessageDialog(null,
							"Appointment could not be created\nPlease make sure you entered a valid id\nAlso note that you can not create duplicate Appointments",
							"Warning", WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		makeAppointmentBtn.setBounds(819, 396, 258, 25);
		contentPane.add(makeAppointmentBtn);

		docIdField = new JTextField();
		docIdField.setHorizontalAlignment(SwingConstants.CENTER);
		docIdField.setBounds(729, 396, 78, 19);
		contentPane.add(docIdField);
		docIdField.setColumns(10);

		JLabel lblEnterTheId = new JLabel("Enter the ID of the wanted Doctor:");
		lblEnterTheId.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblEnterTheId.setBounds(405, 397, 337, 15);
		contentPane.add(lblEnterTheId);

		JScrollPane scrollPanAppointments = new JScrollPane();
		scrollPanAppointments.setBounds(6, 557, 1065, 137);
		contentPane.add(scrollPanAppointments);

		appointmentsTable = new JTable();
		scrollPanAppointments.setViewportView(appointmentsTable);

		JButton refreshAppointmentTableBtn = new JButton("Refresh appointments");
		/**
		 * action to refresh the displayed table of the appointment database
		 */
		refreshAppointmentTableBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String queryWhere = "WHERE username = '" + username + "'";
				try {
					appDB.resultSetToTableModel(appointmentsTable, "Appointments", queryWhere, 7);
					appointmentsTable.getColumnModel().getColumn(0).setPreferredWidth(30);
					appointmentsTable.getColumnModel().getColumn(4).setPreferredWidth(280);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refreshAppointmentTableBtn.setBounds(819, 520, 258, 25);
		contentPane.add(refreshAppointmentTableBtn);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 557, 1043, 2);
		contentPane.add(separator_1);

		JLabel lblSelectTheAppointment = new JLabel("Enter the Id of the Apointment you wish to edit or delete:");
		lblSelectTheAppointment.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblSelectTheAppointment.setBounds(6, 710, 516, 23);
		contentPane.add(lblSelectTheAppointment);

		appointmentIdField = new JTextField();
		appointmentIdField.setHorizontalAlignment(SwingConstants.CENTER);
		appointmentIdField.setBounds(534, 713, 72, 19);
		contentPane.add(appointmentIdField);
		appointmentIdField.setColumns(10);

		JButton editAppointmentBtn = new JButton("Edit");
		editAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditAppointmentWindow eaw = new EditAppointmentWindow(username);
				eaw.createEditAppointmentWindow(appointmentIdField.getText(), username);
			}
		});
		editAppointmentBtn.setBounds(618, 712, 232, 25);
		contentPane.add(editAppointmentBtn);

		/**
		 * action to delete a selected appointment
		 */
		JButton deleteAppointmentBtn = new JButton("Delete");
		deleteAppointmentBtn.setForeground(Color.RED);
		deleteAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int appId = Integer.parseInt(appointmentIdField.getText());
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this appointment?",
						"Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				if (confirm == 0) {
					String queryWhere = " id = " + appId;
					appointmentDocFirstName = appDB.getStringColumnFromDB("docfirstname", "Appointments", queryWhere);
					appointmentDocLastName = appDB.getStringColumnFromDB("doclastname", "Appointments", queryWhere);
					appointmentDocAddress = appDB.getStringColumnFromDB("docaddress", "Appointments", queryWhere);
					appointmentDate = appDB.getStringColumnFromDB("AppointmentDate", "Appointments", queryWhere);
					appointmentTime = appDB.getStringColumnFromDB("AppointmentTime", "Appointments", queryWhere);

					String mailText = "Hello " + username + "\n\nYou just succsesfully deleted a appointment.\n"
							+ "Here is the data of the deleted appointment:\n\n" + "Name of the Doctor:\t"
							+ appointmentDocFirstName + " " + appointmentDocLastName + "\nAddress:\t"
							+ appointmentDocAddress + "\nDate & Time:\t" + appointmentDate + " " + appointmentTime
							+ "\n\nYou can create a new appointment anytime using the eHealth Application."
							+ "\n\nBest regards,\nYour eHealth Team";
					String subject = "[eHealth] Appointment deleted";

					if (appDB.deleteAppointmentFromDB(appId)) {
						showMessageDialog(null, "Appointment succesfully deleted");
						appointmentIdField.setText("");
						Mail.sendtext(userUsed.getEmail(), subject, mailText);
					} else {
						showMessageDialog(null, "Appointment could not be deleted");
					}
				} else
					return;
			}
		});
		deleteAppointmentBtn.setBounds(845, 712, 232, 25);
		contentPane.add(deleteAppointmentBtn);

		JLabel lblNewLabel_2 = new JLabel(":");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(283, 371, 18, 16);
		contentPane.add(lblNewLabel_2);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 492, 1083, 15);
		contentPane.add(separator);

		JButton btnExportHealthInfo = new JButton("Export to TXT");
		/**
		 * action to export your health information as a txt file
		 */
		btnExportHealthInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userUsed.writeUserIntoTxt()) {
					showMessageDialog(null,
							"Your health information file has succesfully been exportet to Your Desktop");
				} else {
					showMessageDialog(null, "Something went wrong.");
				}

			}
		});
		btnExportHealthInfo.setBounds(698, 453, 183, 25);
		contentPane.add(btnExportHealthInfo);
		
		JLabel lblNewLabel_1_2 = new JLabel("Describe your health problem:");
		lblNewLabel_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(6, 157, 335, 22);
		contentPane.add(lblNewLabel_1_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(6, 193, 354, 92);
		contentPane.add(scrollPane);
		
		problemDescription = new JTextArea();
		scrollPane.setViewportView(problemDescription);
		problemDescription.setLineWrap(true);
		problemDescription.setWrapStyleWord(true);
		
		JLabel lblYouCanExport = new JLabel("Export your health information: ");
		lblYouCanExport.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblYouCanExport.setBounds(405, 450, 281, 25);
		contentPane.add(lblYouCanExport);
		
		JButton btnExportToPdf = new JButton("Export to PDF");
		/**
		 * action to export your health information as a pdf file
		 */
		btnExportToPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userUsed.writeUserIntoPdf()) {
					showMessageDialog(null,
							"Your health information file has succesfully been exportet to Your Desktop");
				} else {
					showMessageDialog(null, "Something went wrong.");
				}

			}
			
		});
		btnExportToPdf.setBounds(894, 453, 183, 25);
		contentPane.add(btnExportToPdf);

	}

	/**
	 * <h4>Integer check</h4>
	 * Method to check if a number is an integer value.
	 * <p>
	 * @param input
	 * @return true, if input is integer, else false
	 */
	public boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * <h4>Create main window</h4>
	 * Method to create the main window with the logged in user
	 * <p>
	 * @param usernameInput
	 */
	public static void createMainWindow(String usernameInput) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow mw = new MainWindow(usernameInput);
					mw.setVisible(true);
					mw.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}