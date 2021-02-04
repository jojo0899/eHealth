package eHealth;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * This class is used to create the doctor table, and provide operations for querying the doctor table. 
 * @author marin
 *
 */
public class DoctorDB extends DBController{

	/**
	 * <h4>Creating the doctor table</h4>
	 * The doctor table for the specified docType is created in the database if it does not exist already
	 * @param docType The sort of doctor
	 */
    public void createDoctorDBTable(String docType){
        Connection conn =  connectToDB();
        try {
            Statement st = conn.createStatement();
            st.execute(
            		"Create TABLE IF NOT EXISTS " + docType + "("+
            			    "id    			 int AUTO_INCREMENT NOT NULL," +
            			    "firstname	     varchar(30)," +
            			    "lastname	     varchar(30)," +
            			    "address   		 varchar(200)," +
            			    "latitude        Decimal(80,70)," +
            			    "longitude       Decimal(80,70),"
            			    + "PRIMARY KEY(id));"

            		);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * <h4>Getting the latitude of a doctor</h4>
     * This method queries the doctor table, specified by the caller of the method, for the latitude of a specific doctor.
     * 
     * @param doctype The sort of doctor
     * @param whereCondition The where condition for the query
     * @return The latitude of the desired doctor
     * @throws SQLException If desired doctor was not found in the given table
     */
    public BigDecimal getDocLat(String doctype, String whereCondition) throws SQLException {
    	String query = "SELECT latitude FROM " + doctype + "  " + whereCondition;
    	Connection conn = connectToDB();
    	Statement st = conn.createStatement();
    	ResultSet result = st.executeQuery(query);
    	result.next();
    	return result.getBigDecimal("latitude");
    }
    
    /**
	 * <h4>Getting the longitude of a doctor</h4>
     * This method queries the doctor table, specified by the caller of the method, for the longitude of a specific doctor.
     * 
     * @param doctype The sort of doctor
     * @param whereCondition The where condition for the query
     * @return The longitude of the desired doctor
     * @throws SQLException If desired doctor was not found in the given table
     */
    public BigDecimal getDocLon(String doctype, String whereCondition) throws SQLException {
    	String query = "SELECT longitude FROM " + doctype + "  " + whereCondition;
    	Connection conn = connectToDB();
    	Statement st = conn.createStatement();
    	ResultSet result = st.executeQuery(query);
    	result.next();
    	return result.getBigDecimal("longitude");
    }
    
    /**
     * <h4>Inserting a doctor into a doctor table</h4>
     * A doctor is inserted into a doctor table, using the attributes specified by the caller of the method.
     * <p>
     * The latitude and longitude is calculated within this method, using OpenStreetMapUtils
     * 
     * @param tableName The name of the table to insert into
     * @param firstname The first name of the doctor
     * @param lastname The last name of the doctor
     * @param address The address of the doctor 
     */
    public void insertIntoDoctorDBTable(String tableName, String firstname, String lastname, String address) {
    	Connection conn =  connectToDB();
    	
    	Map<String, Double> coords;
    	coords = OpenStreetMapUtils.getInstance().getCoordinates(address);
    	BigDecimal lat = new BigDecimal(coords.get("lat"));
    	BigDecimal lon = new BigDecimal(coords.get("lon"));
    	
		try {
		Statement st = conn.createStatement();
		
		st.execute("INSERT INTO "+tableName+"(FIRSTNAME, LASTNAME, ADDRESS, LATITUDE, LONGITUDE) VALUES('" 
													+ firstname + "','"
													+ lastname + "','"
													+ address + "','"
													+ lat + "','"
													+ lon + "');");
		} catch (SQLException e) {
		e.printStackTrace();
		}
    }
    
    /**
     * <h4>Populating the doctor tables with data</h4>
     * The doctors available in eHealth are hard coded into the database using this method.
     * <p>
     * The methods "createDoctorDBTable" and "insertIntoDoctorDBTable", also part of this class, are used to populate the tables.
     */
    public void createPopulatedDoctorDB(){
    	createDoctorDBTable("Dentist");
    	createDoctorDBTable("Oculist");
    	createDoctorDBTable("FamilyDoctor");
    	createDoctorDBTable("Dermatologist");
    	
    	insertIntoDoctorDBTable("Dentist", "Ralph", "Dr. Röser", "Friedberger Landstraße 406, 60389 Frankfurt am Main");
    	insertIntoDoctorDBTable("Dentist", "Axel", "Dr. Strohecker", "Rhönstraße 72, 63071 Offenbach am Main");
    	insertIntoDoctorDBTable("Dentist", "Robert", "Dr. Wollnik", "Krämerstraße 22A, 63450 Hanau");
    	insertIntoDoctorDBTable("Dentist", "Gerd", "Dr. Heine", "Zuckerstraße 39, 64807 Dieburg");
    	
    	insertIntoDoctorDBTable("Oculist", "Julia", "Dr. Seiler", "Staufenstraße 46, 60323 Frankfurt am Main");
    	insertIntoDoctorDBTable("Oculist", "Winfried", "Dr. Weiler", "Kaiserstraße 29, 63065 Offenbach am Main");
    	insertIntoDoctorDBTable("Oculist", "Gabriele", "Dr. Goldman", "Krämerstraße 7, 63450 Hanau");
    	insertIntoDoctorDBTable("Oculist", "Klaus-Paul ", "Dr. Müller", "Zentturmstraße 16, 64807 Dieburg");
    	
    	insertIntoDoctorDBTable("FamilyDoctor", "Thomas", "Dr. Heddäus", "Staufenstraße 46, 60323 Frankfurt am Main");
    	insertIntoDoctorDBTable("FamilyDoctor", "Seibel Hendrik ", "Dr. Oliver", "Kaiserstraße 84, 63065 Offenbach am Main");
    	insertIntoDoctorDBTable("FamilyDoctor", "Uwe", "Dr. Andreas", "Nürnberger Str. 39, 63450 Hanau");
    	insertIntoDoctorDBTable("FamilyDoctor", "Norbert", "Dr. Neumann", "Zuckerstraße 9, 64807 Dieburg");
    	
    	insertIntoDoctorDBTable("Dermatologist", " Axel ", "Dr. Diez", "Krämerstraße 22A, 63450 Hanau");
    	insertIntoDoctorDBTable("Dermatologist", "Wolfgang", "Dr. Gerber", "Berliner Str. 79, 63065 Offenbach am Main");
    	insertIntoDoctorDBTable("Dermatologist", "Kay", "Dr. Dirting", "Nürnberger Str. 20, 63450 Hanau");
    	insertIntoDoctorDBTable("Dermatologist", "Kerstin", "Dr. Friedrich", "Zentturmstraße 6, 64807 Dieburg");
    }

    /**
     * <h4>Populating a table(UI) with data retrieved from the doctor table</h4>
     * A table from the UI is populated with the data of a doctor table from the database.
     * <p>
     * Not all rows from the doctor table are also represented in the UI, only the rows which match the where condition and the max distance condition specified by the caller of this method.
     * <p>
     * The distance between doctor and user is calculated within the method, using OpenStreetMapUtils.
     * @param table The table to be populated
     * @param tableName The name of the table to extract data from
     * @param whereCondition The where condition for the query
     * @param user The user used, for determining the distance
     * @param maxDistance The maximum distance between doctor and user
     * @throws SQLException If no data was found
     */
    public void resultSetToTableModel(JTable table, String tableName, String whereCondition, User user, double maxDistance) throws SQLException{
    	Connection conn = connectToDB();
    	Statement st = conn.createStatement();
    	ResultSet rs =  st.executeQuery("SELECT * FROM " + tableName + " " + whereCondition);
        DefaultTableModel tableModel = new DefaultTableModel();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        BigDecimal userLat = user.getLatitude();
        BigDecimal userLon = user.getLongitude();
 
        double userToDocDistance = 0.0;
        
        for (int columnIndex = 1; columnIndex <= columnCount-2; columnIndex++){

            	
                tableModel.addColumn(metaData.getColumnLabel(columnIndex));

        }
        
        Object[] row = new Object[columnCount];


        
        while (rs.next()){
            for (int i = 0; i < columnCount; i++){
            	
                row[i] = rs.getObject(i+1);
            }
            String queryWhere = "WHERE id = " + rs.getRow();
            BigDecimal docLat = getDocLat(tableName, queryWhere);
            BigDecimal docLon = getDocLon(tableName, queryWhere);
            
            userToDocDistance = OpenStreetMapUtils.distance(userLat, userLon, docLat, docLon);
            if(userToDocDistance <= maxDistance) {
                tableModel.addRow(row);
            }

        }

        table.setModel(tableModel);
    }

	@Override
	protected void displayListOfAllDBEntries(String tableName) {
        Connection conn =  connectToDB();
        try {
            Statement st = conn.createStatement();
            ResultSet results = st.executeQuery("SELECT * FROM " + tableName);
            while (results.next()) {
                System.out.print(results.getRow()+" : ");
                for(int i=1;i<=6;i++){  // for all columns in user
                    String data = results.getString(i);
                    System.out.print(data + " | ");
                }
                System.out.println("\n");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		
	}

}
