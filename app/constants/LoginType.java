package constants;

import com.avaje.ebean.annotation.EnumValue;

public enum LoginType {
	
	@EnumValue("TOBUZ")
	TOBUZ,
	
	@EnumValue("FACEBOOK")
	FACEBOOK,
	
	@EnumValue("GOOGLE")
	GOOGLE,
	
	@EnumValue("LINKED_IN")
	LINKED_IN,
	
	@EnumValue("TWITTER")
	TWITTER;

}
