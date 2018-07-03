package constants;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import com.avaje.ebean.annotation.EnumValue;

public enum TobuzPackageType {
	
	@EnumValue("FREE")
	FREE,
	
	@EnumValue("SILVER")
	SILVER,
	
	@EnumValue("GOLD")
	GOLD,
	
	@EnumValue("BRONZE")
	BRONZE;
	
	public static List<TobuzPackageType> getBuyerPackages(){
		List<TobuzPackageType> packages = new LinkedList<TobuzPackageType>();
		for(TobuzPackageType packageType : TobuzPackageType.values()) {
			if(!packageType.equals(TobuzPackageType.BRONZE)) {
				packages.add(packageType);
			}
		}
		return packages;
	}

	public String capitalize(){
		return WordUtils.capitalizeFully(toString().replaceAll("_", " "));
	}
}
