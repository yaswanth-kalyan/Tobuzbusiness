package constants;

import com.avaje.ebean.annotation.EnumValue;

public enum ListingSalePriceType {
	
	@EnumValue("SALE_PRICE")
	SALE_PRICE,
	
	@EnumValue("AVAILABLE_ON_APPROACH")
	AVAILABLE_ON_APPROACH;

}
