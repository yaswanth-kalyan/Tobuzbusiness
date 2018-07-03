package util;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

import models.AdminData;

public class EmailService {
	
	public static AdminData adminData = AdminData.getAdminData();
	
 	static final String FROM = adminData.getEmailFromId();   // Replace with your "From" address. This address must be verified.
    
    // Supply your SMTP credentials below. Note that your SMTP credentials are different from your AWS credentials.
 	
    static final String SMTP_USERNAME = adminData.getEmailSmtpUserName();  // Replace with your SMTP username.
    static final String SMTP_PASSWORD = adminData.getEmailSmtpPassword();  // Replace with your SMTP password.
    
    // Amazon SES SMTP host name. 
    static final String HOST = adminData.getEmailHost();    
    
    // The port you will connect to on the Amazon SES SMTP endpoint. 
    // STARTTLS to encrypt the connection.
    static final int PORT = adminData.getEmailPort();

    public static Boolean sendSampleMail(String to,String subject,String body) {
    	Boolean status = false;
    	try{
    		System.out.println(subject+"   "+body);
	    	 Properties props = System.getProperties();
	         props.put("mail.smtps.host", HOST);
	         props.put("mail.smtps.auth", "true");

	         Session session = Session.getInstance(props, null);
	         Message msg = new MimeMessage(session);
	         msg.setFrom(new InternetAddress(FROM));
	         InternetAddress[] addrs = InternetAddress.parse(to, false);
	       //  InternetAddress[] addrs = InternetAddress.parse("bar@example.com", false));
	         msg.setRecipients(Message.RecipientType.TO, addrs);
	         msg.setSubject(subject);
	         msg.setContent(body,"text/html");
	         msg.setSentDate(new Date());

	         SMTPTransport t =
	             (SMTPTransport) session.getTransport("smtps");
	         t.connect("smtp.mailgun.com", "postmaster@alerts.womenchangemakers.in", "168d0b5a4ed81288adb6c68cc97c218d");
	         t.sendMessage(msg, msg.getAllRecipients());

	         System.out.println("Response: " + t.getLastServerResponse());

	         t.close();
	    	}catch(Exception ex){
	    		ex.printStackTrace();
	    	}
    	
        return status;
    }
	/**
	 * Action to send asynchronous email
	 */
    public static CompletableFuture<String> asynchronousMail(String to,String subject,String body) {
		Supplier<Boolean> supplier = () -> sendSampleMail(to, subject, body);
		final CompletableFuture<Boolean> promise = CompletableFuture.supplyAsync(supplier);
		return promise.thenApplyAsync((final Boolean status) -> "The Mail Sending Status : " + status+ " Promise" + promise);
	}
    
   
    
    public static void sendSampleEmail1(){
    	
    	try{
    	 Properties props = System.getProperties();
         props.put("mail.smtps.host", HOST);
         props.put("mail.smtps.auth", "true");

         Session session = Session.getInstance(props, null);
         Message msg = new MimeMessage(session);
         msg.setFrom(new InternetAddress(FROM));
         InternetAddress[] addrs = InternetAddress.parse("vlaxmi.b3@gmail.com", false);
       //  InternetAddress[] addrs = InternetAddress.parse("bar@example.com", false));
         msg.setRecipients(Message.RecipientType.TO, addrs);

         msg.setSubject("Hello");
         msg.setText("Testing some Mailgun awesomness");
         msg.setSentDate(new Date());

         SMTPTransport t =
             (SMTPTransport) session.getTransport("smtps");
         t.connect("smtp.mailgun.com", "postmaster@alerts.womenchangemakers.in", "168d0b5a4ed81288adb6c68cc97c218d");
         t.sendMessage(msg, msg.getAllRecipients());

         System.out.println("Response: " + t.getLastServerResponse());

         t.close();
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }

}
