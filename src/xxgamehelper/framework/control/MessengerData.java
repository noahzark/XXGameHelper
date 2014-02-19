package xxgamehelper.framework.control;

import java.util.TreeMap;

/***
 * The messenger class attributes.
 * @author LongFangzhou
 */
public abstract class MessengerData {
	
	/***
	 * Default messenger data constructor with some initial values.
	 */
	public MessengerData() {
		this.workPath = "work";
		this.betaMode = false;
		this.debugMode = false;
		this.helperConfig = new TreeMap<String, String>();
		this.gameConfig = new TreeMap<String, String>();
	}
	
	/***
	 * The program work path.
	 */
	public String workPath;
	
	/***
	 * For beta tests or premium uses.
	 */
	private boolean betaMode;
	
	/***
	 * Get if the helper is running under test or premium mode.
	 * @return
	 */
	public boolean isBetaMode() {
		return betaMode;
	}

	public void setBetaMode(boolean betaMode) {
		this.betaMode = betaMode;
	}
	
	/***
	 * For debug uses.
	 */
	private boolean debugMode;

	/***
	 * Get if the helper is running under debug mode.
	 * @return The statement
	 */
	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
	
	protected Thread gameThread;
	
	/**
	 * @return the gameThread
	 */
	public Thread getGameThread() {
		return gameThread;
	}

	/**
	 * @param gameThread the gameThread to set
	 */
	public void setGameThread(Thread gameThread) {
		this.gameThread = gameThread;
	}
	
	/***
	 * A verification token for the cheker to check
	 * whether the thread is running normally or not.
	 */
	private long verifyToken = 0L;
	
	/**
	 * @return the verifyToken
	 */
	public long getVerifyToken() {
		return verifyToken;
	}

	/**
	 * @param verifyToken the verifyToken to set
	 */
	public void setVerifyToken(long verifyToken) {
		this.verifyToken = verifyToken;
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
