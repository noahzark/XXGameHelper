package xxgamehelper.framework.model;

import org.apache.http.HttpEntity;

/***
 * The core class should be runnable to improve stability and achieve multi-thread.
 * @author LongFangzhou
 * @version 0.1
 */
public abstract interface CoreInterface extends Runnable {

	/***
	 * Do some check or other operations before sending a request.
	 * @param remoteAddress
	 * @return True if checks passed, otherwise false
	 */
	abstract boolean preRequest(String remoteAddress);
	
	/***
	 * Use GET method to obtain web content.
	 * @param remoteAddress Remote net address.
	 * @param fileName A file to save those content.
	 * @return If the operation succeed, return true. Otherwise false.
	 */
	abstract boolean getPage(String remoteAddress, String fileName);
	
	/***
	 * Use POST method to obtain web content.
	 * @param remoteAddress Remote net address.
	 * @param httpEntity To save the parameters.
	 * @param fileName A file to save those content.
	 * @return If the operation succeed, return true. Otherwise false.
	 */
	abstract boolean postPage(String remoteAddress, HttpEntity entity, String fileName);
	
	/***
	 * The method to clean temporary files.
	 * @param workPath The work directory
	 * @param fileTypes The types of file which needed to be cleaned
	 */
	abstract void clearFiles(String workPath,String[] fileTypes);

	/***
	 * The method to initialize the game runtime environment.
	 */
	abstract void initGame();
	
	/***
	 * The method to run the game.
	 */
	abstract void runGame();
	
	/***
	 * Update the verify token in messenger to show the thread is alive.
	 */
	abstract void updateToken();
}
