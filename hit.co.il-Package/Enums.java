package hit.co.il;

import java.util.HashMap;
import java.util.Map;

public class Enums
{
	public enum eProductType
	{
		Food(1),
		Medical(2),
		Administration(3);

	    private int value;
	    private static Map map = new HashMap<>();

	    private eProductType(int value) {
	        this.value = value;
	    }

	    static {
	        for (eProductType pageType : eProductType.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static eProductType valueOf(int pageType) {
	        return (eProductType) map.get(pageType);
	    }

	    public int getValue() {
	        return value;
	    }
	}
}

