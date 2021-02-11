package eHealth;

import org.apache.commons.codec.binary.Hex;


import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * This class is used to generate a hashed password. 
 * @author Pascal
 *
 */

public class Password {
	
	 /**
     * <h4>Generates a hashed password</h4>
     * A hashed password is generated for the given password and salt
     * @param password The password to be hashed be hashed
     * @param salt The Salt added to the password
     * @return returns the hashed password
     
     */
	public static String createhash(char[] password, String salt)throws UnsupportedEncodingException {
		
	    int iterations = 10000;
	    
	   
	    int keyLength = 512;
	    char[] passwordChars = password;
	    byte[] saltBytes = salt.getBytes();
	
	    byte[] hashedBytes = hashPassword(passwordChars, saltBytes, iterations, keyLength);
	    String hashedString = Hex.encodeHexString(hashedBytes);
	    
	    
    	return hashedString;
	}
	
	/**
     * <h4>checks if the password is correct</h4>
     * check if the given password is the same with the hashed password.
     * @param password The password to be hashed be hashed
     * @param hashedpassword
     * @param salt The Salt added to the password
     * @return returns true if the password is correct, returns falls if the password is false.
     
     */
	
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

	
	

