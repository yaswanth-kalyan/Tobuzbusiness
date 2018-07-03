package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import models.AdminData;
import play.Configuration;
import play.Play;

public class SMSService {
	
	//MSIg authentication key
	private static String AUTH_KEY = AdminData.getAdminData().getSmsAuthKey();
	//Sender ID,While using route4 sender id should be 6 characters long.
	private static String SENDER_ID = AdminData.getAdminData().getSmsSenderId();
	public final static String enableSMS = Configuration.root().getString("mobile.service");
	
	
	public static void sendOTPSMS(String message,String mobile,String otp){
		
	//	https://control.msg91.com/api/sendotp.php?authkey=YourAuthKey&mobile=919999999990&message=Your%20otp%20is%202786&sender=senderid&otp=2786
//		 https://control.msg91.com/api/sendotp.php?authkey=YourAuthKey&mobile=919999999990&message=Your%20otp%20is%202786&sender=senderid&otp=2786&email=YourEmailID
		URLConnection myURLConnection=null;
		URL myURL=null;
		BufferedReader reader=null;

		//encoding message 
		String encoded_message=URLEncoder.encode(message);

		//Send SMS API
		String mainUrl="https://control.msg91.com/api/sendotp.php?";

		//Prepare parameter string 
		StringBuilder sbPostData= new StringBuilder(mainUrl);
		sbPostData.append("authkey="+AUTH_KEY); 
		sbPostData.append("&mobile="+mobile);
		sbPostData.append("&message="+encoded_message);
		sbPostData.append("&sender="+SENDER_ID);
		sbPostData.append("&otp="+otp);
		sbPostData.append("&route=4");
		sbPostData.append("&country=0");
		sbPostData.append("&email=lakshmi@thrymr.net");
		//route=4&country=0

		//final string
		mainUrl = sbPostData.toString();
		try
		{
			if(enableSMS.equalsIgnoreCase("true") && Play.isProd()) {
		    //prepare connection
		    myURL = new URL(mainUrl);
		    myURLConnection = myURL.openConnection();
		    myURLConnection.connect();
		    reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		    //reading response 
		    String response;
		    while ((response = reader.readLine()) != null) 
		    //print response 
		    System.out.println(response);
		    
		    //finally close connection
		    reader.close();
			}
		} 
		catch (IOException e) 
		{ 
			e.printStackTrace();
		} 
	}
	
	
	public static void sendSMS(String toMobileNumber, String message) {
		
		String route="default";
		//Prepare Url
		URLConnection myURLConnection=null;
		URL myURL=null;
		BufferedReader reader=null;

		//encoding message 
		String encoded_message=URLEncoder.encode(message);

		//Send SMS API
		String mainUrl="https://control.msg91.com/api/sendhttp.php?";

		//Prepare parameter string 
		StringBuilder sbPostData= new StringBuilder(mainUrl);
		sbPostData.append("authkey="+AUTH_KEY); 
		sbPostData.append("&mobiles="+toMobileNumber);
		sbPostData.append("&message="+encoded_message);
		sbPostData.append("&route="+route);
		sbPostData.append("&sender="+SENDER_ID);

		//final string
		mainUrl = sbPostData.toString();
		try
		{
		    //prepare connection
		    myURL = new URL(mainUrl);
		    myURLConnection = myURL.openConnection();
		    myURLConnection.connect();
		    reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		    //reading response 
		    String response;
		    while ((response = reader.readLine()) != null) 
		    //print response 
		    System.out.println(response);
		    
		    //finally close connection
		    reader.close();
		} 
		catch (IOException e) 
		{ 
			e.printStackTrace();
		} 

	}

}
