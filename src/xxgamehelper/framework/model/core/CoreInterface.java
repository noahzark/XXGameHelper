package xxgamehelper.framework.model.core;

/***
 * The core class should be runnable to improve stability and achieve multi-thread.
 * @author LongFangzhou
 */
public interface CoreInterface extends Runnable {
	
	/***
	 * The method to clean temporary work files.
	 * You can use utils.FileUtils.deleteFiles() for help.
	 */
	void cleanFiles();

	/***
	 * The method to initialize the game runtime environment.
	 */
	void initGame();
	
	/***
	 * The method to run the game.
	 */
	void runGame();
	
	/***
	 * Do some operations when the game is finish.
	 */
	void postGame();
	
	
	/***
	 * Update the verify token in messenger to show the thread is alive.
	 */
	abstract void updateToken();
	
	/***
	 * Take a rest after operations.
	 */
	abstract void rest();
}
