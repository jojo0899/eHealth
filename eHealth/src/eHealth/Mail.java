package eHealth;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.util.Properties;

/**
 * This class is used to send e-mails. 
 * @author Pascal
 *
 */

public class Mail {

	
	 /**
     * <h4>Sending an  e-mail</h4>
     * An email is send send to a receiver with a the given message.
     * @param mailreceiver The receiver e-mail address
     * @param mailsubject The subject of the e-mail
     * @param mailmessage The message of the e-mail
     */
		public static void sendtext(String mailreceiver, String mailsubject, String mailmessage) {
			final String username = "healthtesting01@gmail.com";
	        final String password = "3He4lth!";

	        Properties prop = new Properties();
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS
	        
	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("healthtesting01@gmail.com"));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse(mailreceiver)
	            );
	            message.setSubject(mailsubject);
	            message.setText(mailmessage);
	            
	            BodyPart messageBodyPart = new MimeBodyPart();
	            
	            Multipart multipart = new MimeMultipart();
	            multipart.addBodyPart(messageBodyPart);
	            
	            messageBodyPart.setText("This is message body");	          

	            Transport.send(message);

	            System.out.println("Done");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
		}
		
		/**
	     * <h4>Sending an  e-mail</h4>
	     * An email is send with an attached file to a receiver with a the given message.
	     * @param mailreceiver The receiver e-mail address
	     * @param mailsubject The subject of the e-mail
	     * @param mailmessage The message of the e-mail
	     * @param filename the directory of the file to be send
	     */
		
		public static void sendattchement(String mailreceiver, String mailsubject, String mailmessage, String filename) {
			final String username = "healthtesting01@gmail.com";
	        final String password = "3He4lth!";

	        Properties prop = new Properties();
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS
	        
	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });	 

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(username));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(mailreceiver));

	         // Set Subject: header field
	         message.setSubject(mailsubject);

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText(mailmessage);

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");
	  
	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	   }
		
		
		
		
		
		
		
}
			
	
	
	




