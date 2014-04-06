package xxgamehelper.framework.control;

public interface GameOperationInterface {
	
	/***
	 * Start the game thread.
	 */
	void startGame();
	
	/***
	 * Pause the game thread for several seconds.
	 * @param millis
	 * @throws InterruptedException
	 */
	void pauseGame(long t) throws InterruptedException;
	
	/***
	 * Continue the game thread.
	 */
	void continueGame();
	
	/***
	 * The method to judge whether the game thread is alive or not.
	 * @return True if the game thread is alive, otherwise false.
	 */
	boolean isGameAlive();
	
	/***
	 * The method to release game thread and clean.
	 */
	public void releaseGameThread();
	
}
