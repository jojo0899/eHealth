package eHealth;



import java.io.IOException;
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

public class OpenStreetMapUtils {
	
	

  public final static Logger log = Logger.getLogger("OpenStreeMapUtils");

  private static OpenStreetMapUtils instance = null;
  private JSONParser jsonParser;

  private static OkHttpClient client = getUnsafeOkHttpClient();

  public OpenStreetMapUtils() {
    jsonParser = new JSONParser();
  }

  public static OpenStreetMapUtils getInstance() {
    if (instance == null) {
      instance = new OpenStreetMapUtils();
    }
    return instance;
  }

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

  protected OkHttpClient getClient() {
    return client;
  }

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
}

//https://medium.com/@muasir_33255/geo-data-leveraging-openstreetmap-11a5fe2cf9dc