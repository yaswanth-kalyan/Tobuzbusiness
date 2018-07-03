package constants;

import com.avaje.ebean.annotation.EnumValue;

public enum AlertType {
	
	@EnumValue("NONE")
	NONE,
	
	@EnumValue("SUCCESS")
	SUCCESS,
	
	@EnumValue("FAILURE")
	FAILURE,
	
	@EnumValue("WARNING")
	WARNING;

}
