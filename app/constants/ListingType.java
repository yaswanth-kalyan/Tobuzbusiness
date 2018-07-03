package constants;

import com.avaje.ebean.annotation.EnumValue;

public enum ListingType {
	
	@EnumValue("BUSINESS")
	BUSINESS,
	
	@EnumValue("FRANCHISE")
	FRANCHISE,
	
	@EnumValue("COMMERCIAL")
	COMMERCIAL;
	
	
}
