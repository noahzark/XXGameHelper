package xxgamehelper.framework.model.configuration;

import java.util.HashMap;

/***
 * The class to store game configurations of specific type.
 * @author LongFangzhou
 * @param <ValueType> Game configuration value type
 */
public final class GameConfigType<ValueType> {
	
	private final HashMap<String, ValueType> map
	= new HashMap<String, ValueType>();
	
	public ValueType getConfig(String key) {
		return map.get(key);
	}
	
	public boolean setConfig(String key, ValueType value) {
		if (map.containsKey(key))
			return false;
		map.put(key, value);
		return true;
	}

}
