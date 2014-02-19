package xxgamehelper.framework.control;

/***
 * The messenger class should provide information and error output methods.
 * @author LongFangzhou
 */
public interface MessengerInterface {
	
	/***
	 * Output a whole line.
	 * @param str The content
	 */
	void println(String str);
	
	/***
	 * Output a string.
	 * @param str The content
	 */
	void print(String str);
	
	/***
	 * Output a warning message.
	 * @param str The content
	 */
	void showWarning(String content);
	
	/***
	 * Output an error message.
	 * @param errorCause The cause of error
	 */
	void showError(String errorCause);
	
	/***
	 * Output an error exception.
	 * @param e The exception
	 */
	void showError(Exception e);
	
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
