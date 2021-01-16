package eHealth;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class DoctorDB extends DBController{


	public void displayListOfAllDBEntries(String tableName) {
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
	
    public void createDoctorDBTable(String docType){
        Connection conn =  connectToDB();
        try {
            Statement st = conn.createStatement();
            st.execute(
            		"Create TABLE IF NOT EXISTS " + docType + "("+
            			    "id    			 int AUTO_INCREMENT NOT NULL," +
            			    "firstname	     varchar(12)," +
            			    "lastname	     varchar(12)," +
            			    "address   		 varchar(50)," +
            			    "latitude        Decimal(80,70)," +
            			    "longitude       Decimal(80,70),"
            			    + "PRIMARY KEY(id));"

            		);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void insertIntoDoctorDBTable(String tableName, String firstname, String lastname, String address) {
    	Connection conn =  connectToDB();
    	
    	Map<String, Double> coords;
    	coords = OpenStreetMapUtils.getInstance().getCoordinates(address);
    	BigDecimal lat = new BigDecimal(coords.get("lat"));
    	BigDecimal lon = new BigDecimal(coords.get("lon"));
    	
		try {
		Statement st = conn.createStatement();
		
		st.execute("INSERT INTO "+tableName+"(FIRSTNAME,LASTNAME, ADDRESS, LATITUDE, LONGITUDE) VALUES('" 
													+ firstname + "','"
													+ lastname + "','"
													+ address + "','"
													+ lat + "','"
													+ lon + "');");
		} catch (SQLException e) {
		e.printStackTrace();
		}
    }

	@Override
	protected void displayListOfAllDBEntries() {
		// TODO Auto-generated method stub
		
	}

}
