package constants;

import com.avaje.ebean.annotation.EnumValue;

public enum TimePeroid {
	
	@EnumValue("ANNUAL")
	ANNUAL,
	
	@EnumValue("MONTH")
	MONTH,
	
	@EnumValue("WEEK")
	WEEK;
}
