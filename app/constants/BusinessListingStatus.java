package constants;

import com.avaje.ebean.annotation.EnumValue;

public enum BusinessListingStatus {
	
	@EnumValue("PUBLISHED")
	PUBLISHED,
	
	@EnumValue("UNDER_REVIEW")
	UNDER_REVIEW,
	
	@EnumValue("APPROVED")
	APPROVED,
	
	@EnumValue("REJECTED")
	REJECTED,
	
	@EnumValue("SOLD")
	SOLD,
	
	@EnumValue("COMPLETED")
	COMPLETED,
	
	@EnumValue("DRAFTED")
	DRAFTED;

}
