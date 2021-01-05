package eHealth;

import org.apache.commons.codec.binary.Hex;


import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;



public class password {
	
	//method to create a hashed password
	public static String createhash(String password, String salt)throws UnsupportedEncodingException {
		
	    int iterations = 10000;
	    
	    //länge
	    int keyLength = 512;
	    char[] passwordChars = password.toCharArray();
	    byte[] saltBytes = salt.getBytes();
	
	    byte[] hashedBytes = hashPassword(passwordChars, saltBytes, iterations, keyLength);
	    String hashedString = Hex.encodeHexString(hashedBytes);
	    
	    // return hashed password
    	return hashedString;
	}
	
	//method to check if password is correct
	public static String testpassword(String password, String hashedpassword,String salt) {
		
		
	    int iterations = 10000;
	    int keyLength = 512;
	    char[] passwordChars = password.toCharArray();
	    byte[] saltBytes = salt.getBytes();
	
	    byte[] hashedBytes = hashPassword(passwordChars, saltBytes, iterations, keyLength);
	    String hashedString = Hex.encodeHexString(hashedBytes);
		
	    
	    //return true if equal else false
		if(hashedpassword.equals(hashedString)) {
			return "true";
		}else {
			return "false";
		}
		
		
	}
	
	
	

	
	
	public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
	
	    try {
	        SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
	        PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
	        SecretKey key = skf.generateSecret( spec );
	        byte[] res = key.getEncoded( );
	        return res;
	    } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
	        throw new RuntimeException( e );
	    }
	}
}

	
	

