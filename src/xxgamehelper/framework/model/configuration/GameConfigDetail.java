package xxgamehelper.framework.model.configuration;

import java.util.HashMap;
import java.util.Set;

/***
 * The class to store game configurations of specific type.
 * @author LongFangzhou
 * @param <ValueType> Game configuration value type
 */
public final class GameConfigDetail<ValueType> {
	
	private final HashMap<String, ValueType> configMap
	= new HashMap<String, ValueType>();
	
	public ValueType getConfig(String key) {
		return configMap.get(key);
	}
	
	public boolean setConfig(String key, ValueType value) {
		if (configMap.containsKey(key))
			return false;
		configMap.put(key, value);
		return true;
	}
	
	public String showConfig() {
		StringBuffer sb = new StringBuffer();
		Set<String> keySet = configMap.keySet();
		for (String key : keySet)
			sb.append("Key:"+key+"\tValue:"+configMap.get(key)+"\n");
		return sb.toString();
	}

}
