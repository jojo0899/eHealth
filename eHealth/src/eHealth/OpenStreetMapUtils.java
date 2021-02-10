package eHealth;



import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

/**
 * This class is used to get the coordinates from an address and to calculate the distance between to points.
 * @author Pascal
 *
 */

public class OpenStreetMapUtils {
	
	

  public final static Logger log = Logger.getLogger("OpenStreeMapUtils");

  private static OpenStreetMapUtils instance = null;
  private JSONParser jsonParser;

  private static OkHttpClient client = getUnsafeOkHttpClient();
  
  /**
   * <h4>create jsonParser</h4>
   * Creating a new jsonParser
   *   
   */

  public OpenStreetMapUtils() {
    jsonParser = new JSONParser();
  }
  /**
   * <h4>OpenStreetMap instance</h4>
   * Creating a new OpenStreetMapUtils instance
   *   
   */
  public static OpenStreetMapUtils getInstance() {
    if (instance == null) {
      instance = new OpenStreetMapUtils();
    }
    return instance;
  }

  
  /**
   * <h4>Sending an URL</h4>
   * Build the complete URL request then sends it. the received data is transformed to jsonData and the returned
   *  
   *   @param completeURL The URL 
   *   @return the jsonData with the response
   */
  protected String getRequest(String completeUrl)
      throws IOException {
    Response response = null;
    try {
      Request request = new Request.Builder()
          .url(completeUrl)
          .header("Accept", "application/json")
          .build();

      response = getClient().newCall(request).execute();
      String jsonData = response.body().string();

      jsonData = jsonData.replace("[]", "{}");
      return jsonData;
    } finally {
      if (response != null && response.body() != null) {
        response.body().close();
      }
    }
  }

  /**
   * <h4>Returns the OkHttpClient</h4>
   * Returns the OkHttpClient
   *   
   *   @return the OkHttpClient
   */
  protected OkHttpClient getClient() {
    return client;
  }

  /**
   * <h4>Get coordinates</h4>
   * builds a OpenStreetMap URL for the specified address, then sends this URL with the OkHttpClient 
   * to receive the coordinates from OpenStreetMap and return them. 
   * 
   * @param address the address
   * @param return returns the coordinates
   *   
   */
  public Map<String, Double> getCoordinates(String address) {
	  
  	org.apache.log4j.BasicConfigurator.configure();

    Map<String, Double> res;
    StringBuffer query;
    String[] split = address.split(" ");
    String queryResult = null;

    query = new StringBuffer();
    res = new HashMap<String, Double>();

    query.append("http://nominatim.openstreetmap.org/search?q=");

    if (split.length == 0) {
      return null;
    }

    for (int i = 0; i < split.length; i++) {
      query.append(split[i]);
      if (i < (split.length - 1)) {
        query.append("+");
      }
    }
    query.append("&format=json&addressdetails=1");

    

    try {
      queryResult = getRequest(query.toString());
    } catch (Exception e) {
      log.error("Error when trying to get data with the following query " + query);
    }

    if (queryResult == null) {
      return null;
    }

    Object obj = JSONValue.parse(queryResult);
   

    if (obj instanceof JSONArray) {
      JSONArray array = (JSONArray) obj;
      if (array.size() > 0) {
        JSONObject jsonObject = (JSONObject) array.get(0);

        String lon = (String) jsonObject.get("lon");
        String lat = (String) jsonObject.get("lat");
        
        res.put("lon", Double.parseDouble(lon));
        res.put("lat", Double.parseDouble(lat));

      }
    }

    return res;
  }

  /**
   * <h4>Builds the OkHttpClient</h4>
   * building the OkHttpClient
   * 
   * 
   * @return the new OkHttpClient
   */
  
  private static OkHttpClient getUnsafeOkHttpClient() {
    try {
      final TrustManager[] trustAllCerts = new TrustManager[]{
          new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                String authType) {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
                String authType) {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
              return new java.security.cert.X509Certificate[]{};
            }
          }
      };

      final SSLContext sslContext = SSLContext.getInstance("SSL");
      sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
      final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

      OkHttpClient.Builder builder = new OkHttpClient.Builder();
      builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
      builder.hostnameVerifier((hostname, session) -> true);

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  /**
   * <h4>Calculate the distance between coordinates</h4>
   * The distance between with the input defined points is calculate and returned
   * 
   * @param lat1 the latitude of the fist point
   * @param long1 the longitude of the first point
   * @param lat2 the latitude of the second point
   * @param long2 the longitude of the second point
   * @return returns the distance
   */
  public static double distance(BigDecimal lat1, BigDecimal long1, BigDecimal lat2, BigDecimal long2) {
	  	double lat1AsDouble = lat1.doubleValue();
	  	double lon1AsDouble = long1.doubleValue();
	  	double lat2AsDouble = lat2.doubleValue();
	  	double lon2AsDouble = long2.doubleValue();
	  	
		double lat = (lat1AsDouble+lat2AsDouble)/ 2 * 0.01745;
		double dx = 111.3* Math.cos(lat) * (lon1AsDouble-lon2AsDouble);
		double dy = 111.3*(lat1AsDouble-lat2AsDouble);
		double dist= Math.sqrt(dx*dx+dy*dy);		
		return dist;
	}
  
  
}

//https://medium.com/@muasir_33255/geo-data-leveraging-openstreetmap-11a5fe2cf9dc