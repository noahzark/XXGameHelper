package xxgamehelper.framework.control;

/***
 * Several methods for operating the game helper.
 * @author LongFangzhou
 *
 */
public interface HelperOperationInterface {
	
	/***
	 * Start the helper thread.
	 */
	void startHelper();
	
	/***
	 * Pause the helper thread for several seconds.
	 * @param millis
	 * @throws InterruptedException
	 */
	void pauseHelper(long t) throws InterruptedException;
	
	/***
	 * Continue the helper thread.
	 */
	void continueHelper();
	
	/***
	 * The method to judge whether the helper thread is alive or not.
	 * @return True if the game thread is alive, otherwise false.
	 */
	boolean isHelperAlive();
	
	/***
	 * The method to release helper thread and clean.
	 */
	public void releaseHelperThread();
	
}
