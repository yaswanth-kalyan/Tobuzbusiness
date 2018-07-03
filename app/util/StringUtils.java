package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.text.WordUtils;

public class StringUtils {
	
	public static boolean isNotEmpty(String message){ 
		return message != null && !message.trim().isEmpty();
	}
	
	public static boolean ignoreCase(String compareMessage,String actualMessage){
		return isNotEmpty(compareMessage) && isNotEmpty(actualMessage) && compareMessage.equalsIgnoreCase(actualMessage);
	}
	
	public static boolean inCase(String compareMessage,String actualMessage){
		return isNotEmpty(compareMessage) && isNotEmpty(actualMessage) && compareMessage.equals(actualMessage);
	}
	
	public static String capitalize(String data){
		if(isNotEmpty(data)){
			return WordUtils.capitalizeFully(data.toString().replaceAll("_", " "));
		}
		return "";
	}
	
	public static String delayCapitalize(String data){
		if(isNotEmpty(data)){
			String val = WordUtils.capitalizeFully(data.toString().replaceAll("_OR_", "_/_"));
			return capitalize(val);
		}
		return "";
	}
	
	public static boolean isEmpty(String message){
		return message == null || message.trim().isEmpty();
	}
	
	public static String getRandomAlphaNumericId(Integer number) {
		// TODO Auto-generated method stub
		return RandomStringUtils.randomAlphanumeric(number).toLowerCase();
	}
	
	public static String getRandomAlphabeticId(Integer number) {
		// TODO Auto-generated method stub
		return RandomStringUtils.randomAlphabetic(number).toLowerCase();
	}
	
	public static String getRandomNumericId(Integer number) {
		// TODO Auto-generated method stub
		return RandomStringUtils.randomNumeric(number);
	}
	
	public static Boolean validateEmail(String email) {
		//String regEx = "[a-zA-Z0-9_.+-]+@(?:(?:[a-zA-Z0-9-]+\.)?[a-zA-Z]+\\.)?(inspirage|oracle)\\.com";
		Pattern pattern = Pattern.compile("[a-zA-Z0-9_.+-]+@(?:(?:[a-zA-Z0-9-]+\\.)?[a-zA-Z]+\\.)?(inspirage|oracle)\\.com");
		if(isNotEmpty(email)) {
			Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {    
                return Boolean.TRUE;
            }
		}
		return Boolean.FALSE;
	}

}
