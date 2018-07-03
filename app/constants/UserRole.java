package constants;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

import com.avaje.ebean.annotation.EnumValue;

import util.StringUtils;

public enum UserRole {
	
	@EnumValue("BROKER")
	BROKER,
	
	@EnumValue("BUYER")
	BUYER,
	
	@EnumValue("BUSINESS_SERVICE")
	BUSINESS_SERVICE,
	
	@EnumValue("FRANCHISOR")
	FRANCHISOR,
	
	@EnumValue("SELLER")
	SELLER,
	
	@EnumValue("TOBUZ_ADMIN")
	TOBUZ_ADMIN;
	
	public String capitalize(){
		return WordUtils.capitalizeFully(toString().replaceAll("_", " "));
	}
	
	public static Map<String, String> options() {
		final LinkedHashMap<String, String> vals = new LinkedHashMap<String, String>();
		for (final UserRole val : UserRole.values()) {
			vals.put(val.toString(), StringUtils.capitalize(val.toString()));
		}

		return vals;
	}

}
