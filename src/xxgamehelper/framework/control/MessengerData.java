package xxgamehelper.framework.control;

/***
 * The messenger class attributes.
 * @author LongFangzhou
 */
public abstract class MessengerData {
	/***
	 * The program work path.
	 */
	public String workPath = "work";
	
	/***
	 * For beta tests or premium uses.
	 */
	private boolean betaMode = false;
	
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
	private boolean debugMode = false;

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
}
