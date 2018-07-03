package constants;

import com.avaje.ebean.annotation.EnumValue;

public enum ListingFor {
	
	@EnumValue("SALE")
	SALE,
	
	@EnumValue("RENT_OR_LEASE")
	RENT_OR_LEASE,
	
	@EnumValue("OTHERS")
	OTHERS;

}
