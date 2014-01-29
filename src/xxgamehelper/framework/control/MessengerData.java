package xxgamehelper.framework.control;

/***
 * The messenger class attributes.
 * @author LongFangzhou
 */
public abstract class MessengerData {
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
}
