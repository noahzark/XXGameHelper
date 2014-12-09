package xxgamehelper.framework.control.messenger;

import java.io.File;
import xxgamehelper.framework.model.HelperFactory;
import xxgamehelper.framework.model.client.WebClient;
import xxgamehelper.framework.model.configuration.ConfigManager;
import xxgamehelper.framework.model.configuration.HelperConfig;

/***
 * The messenger class attributes.
 * @author LongFangzhou
 */
public abstract class MessengerData extends ConfigData{
	
	/***
	 * Default messenger data constructor with some initial values.
	 */
	public MessengerData(HelperFactory helperFactory) {
		super();
		if (this.requireConfig && this.loadConfig(this.getAPPName()+".xml"))
			;
		else {
			this.homePath = "./";
			this.betaMode = false;
			this.debugMode = false;
		}
		
		this.dataPath = homePath + "XXGameHelper/";
		this.workPath = "work";
		this.errorDumpPath = "error";
		
		File workDir = new File(dataPath);
		if (!workDir.exists())
			workDir.mkdirs();
		else
			if (!workDir.isDirectory())
				System.out.println("Work Directory error.");

		
		this.showDownloadMode = false;
		this.helperFactory = helperFactory;
		this.requireConfig = false;
	}
	
	private WebClient webClient;
	
	/**
	 * @return the webClient
	 */
	public WebClient getWebClient() {
		return webClient;
	}

	/**
	 * @param webClient the webClient to set
	 */
	public void setWebClient(WebClient webClient) {
		this.webClient = webClient;
	}
	
	/***
	 * The method to get the program's name.
	 * @return Program name string
	 */
	public abstract String getAPPName();
	
	private String homePath;
	
	/***
	 * The directory which contains the application data.
	 */
	private String dataPath;
	
	/**
	 * @return the directory which contains the application data.
	 */
	public String getDataPath() {
		return this.dataPath + this.getAPPName() + "/";
	}

	/***
	 * The application work path.
	 */
	private String workPath;
	
	/***
	 * @return the application work path.
	 */
	public String getWorkPath() {
		return this.getDataPath() + workPath + "/";
	}
	
	/***
	 * The place to save error dump files.
	 */
	private String errorDumpPath;

	/***
	 * 
	 * @return The place to save error dump files.
	 */
	public String getErrorDumpPath() {
		return this.getDataPath() + errorDumpPath + "/";
	}

	/**
	 * @param workPath the workPath to set
	 */
	public void setWorkPath(String workPath) {
		this.workPath = workPath;
	}
	
	/***
	 * Whether the helper is requiring a manual configurations.
	 */
	private boolean requireConfig;
	
	/***
	 * Tells whether the helper is requiring a configuration.
	 * @return True if it's required
	 */
	public boolean isRequireConfig() {
		return requireConfig;
	}

	public void setRequireConfig(boolean requireConfig) {
		this.requireConfig = requireConfig;
	}
	
	public HelperConfig helperConfig;
	
	public boolean loadConfig(String configFileName) {
		helperConfig = ConfigManager.loadConfig(
				configFileName, this.getAPPName());
		if (helperConfig!=null) {
			this.homePath = "./";
			setDebugMode(helperConfig.mode.isDebugMode);
			setBetaMode(helperConfig.mode.isBetaMode);
			return true;
		}
		return false;
	}
	
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

	/***
	 * Show download progress.
	 */
	private boolean showDownloadMode;
	
	/***
	 * Get wheter the client needs to show download progress.
	 * @return The switch
	 */
	public boolean isShowDownloadMode() {
		return showDownloadMode;
	}

	public void setShowDownloadMode(boolean showDownloadMode) {
		this.showDownloadMode = showDownloadMode;
	}
	
	protected Thread helperThread;
	
	/**
	 * @return the gameThread
	 */
	public Thread getHelperThread() {
		return helperThread;
	}

	/**
	 * @param helperThread the gameThread to set
	 */
	public void setHelperThread(Thread helperThread) {
		this.helperThread = helperThread;
	}
	
	/***
	 * A verification token for the cheker to check
	 * whether the thread is running normally or not.
	 */
	private long verifyToken = Long.MIN_VALUE;
	
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
	 * The factory to build connections and cores.
	 */
	private HelperFactory helperFactory;
	
	/**
	 * @return the helperFactory
	 */
	public HelperFactory getHelperFactory() {
		return helperFactory;
	}
	
}
