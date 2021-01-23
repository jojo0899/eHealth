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

public class DoctorDB extends DBController{

	
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
    
    public BigDecimal getDocLat(String doctype, String whereCondition) throws SQLException {
    	String query = "SELECT latitude FROM " + doctype + "  " + whereCondition;
    	Connection conn = connectToDB();
    	Statement st = conn.createStatement();
    	ResultSet result = st.executeQuery(query);
    	result.next();
    	return result.getBigDecimal("latitude");
    }
    
    public BigDecimal getDocLon(String doctype, String whereCondition) throws SQLException {
    	String query = "SELECT longitude FROM " + doctype + "  " + whereCondition;
    	Connection conn = connectToDB();
    	Statement st = conn.createStatement();
    	ResultSet result = st.executeQuery(query);
    	result.next();
    	return result.getBigDecimal("longitude");
    }
    
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

    
    public void resultSetToTableModel(JTable table, String tableName, String whereCondition, User user, double maxDistance) throws SQLException{
    	Connection conn = connectToDB();
    	Statement st = conn.createStatement();
    	ResultSet rs =  st.executeQuery("SELECT * FROM " + tableName + " " + whereCondition);
        DefaultTableModel tableModel = new DefaultTableModel();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        BigDecimal userLat = user.getLatitude();
        BigDecimal userLon = user.getLongitude();
        
//        BigDecimal docLat = null;
//        BigDecimal docLon = null;
 
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
