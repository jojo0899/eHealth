package eHealth;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField radius;
	private JTextField dateField;
	private JTextField docIdField;
	private JTable appointmentsTable;
	private JTable searchResultTable;
	
	private String doctorType;
	private String appointmentTime;
	private String appointmentDate;
	
	private User userUsed;

	private DoctorDB docDB = new DoctorDB();
	private AppointmentsDB appDB = new AppointmentsDB();
	private JTextField appointmentIdField;

	private String appointmentDocFirstName; 
	private String appointmentDocLastName;
	private String appointmentDocAddress;
	
	
	public MainWindow(String username) {
		
		userUsed = new User(username);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1284, 904);
		setBounds(100, 100, 1083, 670);
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
		lblNewLabel_1.setBounds(6, 92, 299, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Search radius:");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(6, 176, 136, 22);
		contentPane.add(lblNewLabel_1_1);
		
		radius = new JTextField();
		radius.setHorizontalAlignment(SwingConstants.RIGHT);
		radius.setColumns(10);
		radius.setBounds(205, 178, 88, 22);
		contentPane.add(radius);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("km");
		lblNewLabel_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(305, 176, 36, 22);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Reminder:");
		lblNewLabel_1_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_2.setBounds(6, 278, 136, 22);
		contentPane.add(lblNewLabel_1_1_2);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"no reminder", "1 week before", "3 days before", "1 hour before", "10 minutes before"}));
		comboBox.setBounds(105, 275, 236, 34);
		contentPane.add(comboBox);
		
		JLabel lblUpcoming = new JLabel("Upcoming appointments:");
		lblUpcoming.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblUpcoming.setBounds(6, 410, 354, 34);
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
		comboBoxProblem.setModel(new DefaultComboBoxModel<String>(new String[] {"---", "eye pain", "weak vision", "watery eyes", "cough", "sniff", "fever", "headache", "itchy skin", "acne", "tootache", "gingivitis", "jaw pain"}));
		comboBoxProblem.setBounds(6, 126, 335, 38);
		contentPane.add(comboBoxProblem);
		
		JComboBox<String> comboBoxHour = new JComboBox<String>();
		comboBoxHour.setModel(new DefaultComboBoxModel<String>(new String[] {"7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"}));
		comboBoxHour.setBounds(195, 244, 72, 27);
		contentPane.add(comboBoxHour);
		
		JComboBox<String> comboBoxMin = new JComboBox<String>();
		comboBoxMin.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"}));
		comboBoxMin.setBounds(269, 244, 72, 27);
		contentPane.add(comboBoxMin);
		
		dateField = new JTextField();
		dateField.setBounds(205, 210, 136, 26);
		contentPane.add(dateField);
		dateField.setColumns(10);
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		JButton searchButton = new JButton("Search for Doctor");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String radiusString = radius.getText();
				int radiusInt = 0;
				appointmentDate = dateField.getText();
				appointmentTime = (comboBoxHour.getSelectedItem() + ":" + comboBoxMin.getSelectedItem());
				
				switch(comboBoxProblem.getSelectedIndex()) {
				case 0:
					//Warning
					showMessageDialog(null,"Please select your health problem!", "Warning", WARNING_MESSAGE);
					return;
				case 1:
				case 2:
				case 3:
					//Augenarzt
					System.out.println("AU");
					doctorType = "Oculist";
					break;
				case 4:
				case 5: 
				case 6:
				case 7:
					//Hausarzt
					System.out.println("HA");
					doctorType = "FamilyDoctor";
					break;
				case 8:
				case 9:
					//Hautarzt
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
					showMessageDialog(null,"Please select a Health Problem!", "Warning", WARNING_MESSAGE);
					return;
				}

				if (radiusString.equals("") | isInteger(radiusString) == false) {
					radius.setBorder(new LineBorder(Color.RED, 1));
                	radius.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null, "Please enter an integer value!", "Warning", WARNING_MESSAGE);
                	return;
				}
				else {
					radius.setBorder(new LineBorder(Color.GREEN, 1));
                	radius.setBorder(new LineBorder(Color.GREEN, 1));
                	
                    try {
                    	radiusInt = Integer.parseInt(radiusString);
                    }catch(NumberFormatException exc) {
                    	showMessageDialog(null, "ERROR", "Warning", WARNING_MESSAGE);
                    	return;
                    } 
				}
				
				if (appointmentDate.equals("")) {
                	dateField.setBorder(new LineBorder(Color.RED, 1));
                	showMessageDialog(null,"Please enter a date!", "Warning", WARNING_MESSAGE);
		        	return;
				}
                else {
                	try {
                		Date checkDate = isoFormat.parse(appointmentDate);
                	} catch (ParseException e1) {
                		e1.printStackTrace();
                		dateField.setBorder(new LineBorder(Color.RED, 1));
                		showMessageDialog(null,"Please enter a valid date in the given format!", "Warning", WARNING_MESSAGE);
                		return;
                	}
                	dateField.setBorder(new LineBorder(Color.GREEN, 1));
                }
				// source: https://sopra.cs.tu-dortmund.de/wiki/infos/tutorials/java/datum
				
				// distance calc
				String queryWhereCondition = "WHERE ";
				try {
					docDB.resultSetToTableModel(searchResultTable, doctorType, " ", userUsed, radiusInt);
					searchResultTable.getColumnModel().getColumn(3).setPreferredWidth(300);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			
		});
		searchButton.setBounds(6, 330, 335, 29);
		contentPane.add(searchButton);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Date (YYYY-MM-DD):");
		lblNewLabel_1_1_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_2_1.setBounds(6, 210, 193, 22);
		contentPane.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Time:");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1_1_2_1_1.setBounds(6, 244, 187, 22);
		contentPane.add(lblNewLabel_1_1_2_1_1);
				
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setForeground(Color.RED);
		logoutBtn.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out from eHealth?\nUnsaved changes won't be saved!", "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				if (confirm == 0) {
					dispose();
					LoginDialog lw = new LoginDialog();
			        lw.createLoginDialog();
				}
				else return;
			}
		});

		logoutBtn.setBounds(1155, 763, 117, 29);
		logoutBtn.setBounds(853, 10, 117, 29);
		contentPane.add(logoutBtn);
		
		JButton quitBtn = new JButton("Quit");
		quitBtn.setForeground(Color.RED);
		quitBtn.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		quitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit eHealth?\nUnsaved changes won't be saved!", "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				if (confirm == 0) {
					System.exit(0);
				}
				else return;
				
				
			}
		});
		quitBtn.setBounds(1155, 826, 117, 29);
		quitBtn.setBounds(960, 10, 117, 29);
		contentPane.add(quitBtn);
		
		JScrollPane scrollPaneSearchResult = new JScrollPane();
		scrollPaneSearchResult.setBounds(431, 65, 624, 207);
		scrollPaneSearchResult.setBounds(405, 92, 663, 211);
		contentPane.add(scrollPaneSearchResult);
		
		searchResultTable = new JTable();
		scrollPaneSearchResult.setViewportView(searchResultTable);
		
		JLabel lblSearchResults = new JLabel("Search results:");
		lblSearchResults.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblSearchResults.setBounds(405, 36, 263, 30);
		contentPane.add(lblSearchResults);
		
		JButton makeAppointmentBtn = new JButton("Make appointment");
		makeAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String docIdString = docIdField.getText();
				int docId = 0;
                try {
                	docId = Integer.parseInt(docIdString);
                }catch(NumberFormatException exc) {
                	showMessageDialog(null, "Please enter a valid id!", "Warning", WARNING_MESSAGE);
                	docIdField.setBorder(new LineBorder(Color.RED, 1));
                	return;
                } 
				String queryWhere = " id = " + docId; 
				appointmentDocFirstName = docDB.getStringColomnFromDB("firstname", doctorType, queryWhere);
				appointmentDocLastName = docDB.getStringColomnFromDB("lastname", doctorType, queryWhere);
				appointmentDocAddress = docDB.getStringColomnFromDB("address", doctorType, queryWhere);
				
				String mailText = "Hello " + username + "\n\nYou just succsesfully created a new appointment.\n" +
									"In the following You can find the most important information regarding your appointment:\n\n" +
									"Name of the Doctor:\t" + appointmentDocFirstName + " " + appointmentDocLastName +
									"\nAddress:\t" + appointmentDocAddress +
									"\nDate & Time:\t" + appointmentDate + " " + appointmentTime +
									"\n\nYou can still edit or cancel the appointment through the eHealth Application." +
									"\n\nBest regards,\nYour eHealth Team";
				String subject ="[eHealth] New Appointment Created";
				Mail.sendtext(userUsed.getEmail(), subject, mailText);
				
				try {
					appDB.insertIntoAppointmentsDBTable(username, appointmentDocFirstName, appointmentDocLastName, appointmentDocAddress, appointmentDate, appointmentTime);
					docIdField.setBorder(new LineBorder(Color.GREEN, 1));
					showMessageDialog(null, "Appointment succesfully created");
				} catch (SQLException e1) {
					docIdField.setBorder(new LineBorder(Color.RED, 1));
					showMessageDialog(null,"Appointment could not be created\nPlease make sure you entered a valid id\nAlso note that you can not create duplicate Appointments","Warning",WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		makeAppointmentBtn.setBounds(819, 332, 258, 25);
		contentPane.add(makeAppointmentBtn);
		
		docIdField = new JTextField();
		docIdField.setHorizontalAlignment(SwingConstants.CENTER);
		docIdField.setBounds(729, 333, 78, 19);
		contentPane.add(docIdField);
		docIdField.setColumns(10);
		
		JLabel lblEnterTheId = new JLabel("Enter the ID of the wanted Doctor:");
		lblEnterTheId.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblEnterTheId.setBounds(405, 336, 337, 15);
		contentPane.add(lblEnterTheId);
		
		JScrollPane scrollPanAppointments = new JScrollPane();
		scrollPanAppointments.setBounds(52, 576, 1003, 164);
		scrollPanAppointments.setBounds(6, 456, 1065, 137);
		contentPane.add(scrollPanAppointments);
		
		appointmentsTable = new JTable();
		scrollPanAppointments.setViewportView(appointmentsTable);
		
		JButton refreshAppointmentTableBtn = new JButton("Refresh appointments");
		refreshAppointmentTableBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String queryWhere = "WHERE username = '" + username+"'";
				try {
					appDB.resultSetToTableModel(appointmentsTable, "Appointments", queryWhere);
					appointmentsTable.getColumnModel().getColumn(0).setPreferredWidth(30);
					appointmentsTable.getColumnModel().getColumn(4).setPreferredWidth(280);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refreshAppointmentTableBtn.setBounds(938, 534, 117, 25);
		refreshAppointmentTableBtn.setBounds(819, 421, 258, 25);
		contentPane.add(refreshAppointmentTableBtn);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 448, 1043, 2);
		contentPane.add(separator_1);
		
		JLabel lblSelectTheAppointment = new JLabel("Enter the Id of the Apointment you wish to edit or delete:");
		lblSelectTheAppointment.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblSelectTheAppointment.setBounds(6, 605, 440, 15);
		contentPane.add(lblSelectTheAppointment);
		
		appointmentIdField = new JTextField();
		appointmentIdField.setHorizontalAlignment(SwingConstants.CENTER);
		appointmentIdField.setBounds(429, 603, 72, 19);
		contentPane.add(appointmentIdField);
		appointmentIdField.setColumns(10);
		
		JButton editAppointmentBtn = new JButton("Edit");
		editAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditAppointmentWindow eaw = new EditAppointmentWindow(username);
				eaw.createEditAppointmentWindow(appointmentIdField.getText(), username);
			}
		});
		editAppointmentBtn.setBounds(513, 602, 155, 25);
		contentPane.add(editAppointmentBtn);
		
		JButton deleteAppointmentBtn = new JButton("Delete");
		deleteAppointmentBtn.setForeground(Color.RED);
		deleteAppointmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int appId = Integer.parseInt(appointmentIdField.getText());
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this appointment?", "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				if (confirm == 0) {
					String queryWhere = " id = " + appId; 
					appointmentDocFirstName = appDB.getStringColomnFromDB("docfirstname", "Appointments", queryWhere);
					appointmentDocLastName = appDB.getStringColomnFromDB("doclastname", "Appointments", queryWhere);
					appointmentDocAddress = appDB.getStringColomnFromDB("docaddress", "Appointments", queryWhere);
					appointmentDate = appDB.getStringColomnFromDB("AppointmentDate", "Appointments", queryWhere);
					appointmentTime = appDB.getStringColomnFromDB("AppointmentTime", "Appointments", queryWhere);
					
					String mailText = "Hello " + username + "\n\nYou just succsesfully deleted a appointment.\n" +
							"Here is the data of the deleted appointment:\n\n" +
							"Name of the Doctor:\t" + appointmentDocFirstName + " " + appointmentDocLastName +
							"\nAddress:\t" + appointmentDocAddress +
							"\nDate & Time:\t" + appointmentDate + " " + appointmentTime +
							"\n\nYou can create a new appointment anytime using the eHealth Application." +
							"\n\nBest regards,\nYour eHealth Team";
					String subject ="[eHealth] Appointment deleted";
				
				
					if(appDB.deleteAppointmentFromDB(appId)) {
						showMessageDialog(null, "Appointment succesfully deleted");
						appointmentIdField.setText("");
						Mail.sendtext(userUsed.getEmail(), subject, mailText);
					}else {
						showMessageDialog(null, "Appointment could not be deleted");
					}
				}
				else return;
			}
		});
		deleteAppointmentBtn.setBounds(680, 602, 162, 25);
		contentPane.add(deleteAppointmentBtn);
		
		JLabel lblNewLabel_2 = new JLabel(":");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(264, 247, 18, 16);
		contentPane.add(lblNewLabel_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 383, 1083, 15);
		contentPane.add(separator);
		
		
	}
	
	public boolean isInteger(String input) {
	    try {
	        Integer.parseInt(input);
	        return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
	}
	
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