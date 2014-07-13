package xxgamehelper.framework.control.messenger;

import java.util.TreeMap;

/***
 * The class to store helper and game configurations.
 * @author LongFangzhou
 */
public class ConfigData {
	
	public ConfigData() {
		this.helperConfig = new TreeMap<String, String>();
		this.gameConfig = new TreeMap<String, String>();
	}
	
	/***
	 * The helper configurations.
	 */
	private TreeMap<String, String> helperConfig;
	
	/**
	 * @param helperConfig the helperConfig to add
	 */
	public void addHelperConfig(String key, String value) {
		this.helperConfig.put(key, value);
	}
	
	/***
	 * Get a helper configuration using a key.
	 * @param key The configuration key
	 * @return The configuration value
	 */
	public String getHelperConfig(String key) {
		return helperConfig.get(key);
	}

	/***
	 * The game configurations.
	 */
	private TreeMap<String, String> gameConfig;
	
	/**
	 * @param gameConfig the gameConfig to set
	 */
	public void addGameConfig(String key, String value) {
		this.gameConfig.put(key, value);
	}
	
	/***
	 * Get a game configuration using a key.
	 * @param key The configuration key
	 * @return The configuration value
	 */
	public String getGameConfig(String key) {
		return gameConfig.get(key);
	}

}
