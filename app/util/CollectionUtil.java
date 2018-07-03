package util;

import java.util.List;

/**
 * 
 * @author vamshi
 *
 */
public class CollectionUtil {

	public static <T> Boolean isListNotEmpty(List<T> object) {
		return object != null && object.size() > 0;

	}
	
	public static int convertDoubleToInteger(Double val){
		return val.intValue();
	}
}
