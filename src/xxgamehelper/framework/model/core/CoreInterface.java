package xxgamehelper.framework.model.core;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

/***
 * The core class should be runnable to improve stability and achieve multi-thread.
 * @author LongFangzhou
 */
public interface CoreInterface extends Runnable {

	/***
	 * Do some check or other operations before sending a request.
	 * @param remoteAddress
	 * @return True if checks passed, otherwise false
	 */
	boolean preRequest(String remoteAddress, String fileName);
	
	/***
	 * Use GET method to obtain web content.
	 * @param remoteAddress Remote net address
	 * @param fileName A file to save those content
	 * @return If the operation succeed, return true. Otherwise false.
	 */
	boolean doGet(String remoteAddress, String fileName);
	
	/***
	 * Use GET method to obtain web content with some request headers.
	 * @param remoteAddress Remote net address
	 * @param headers The request headers
	 * @param fileName A file to save those content
	 * @return If the operation succeed, return true. Otherwise false.
	 */
	boolean doGet(String remoteAddress, Map<String, String> headers
			, String fileName);
	
	/***
	 * Use POST method to obtain web content.
	 * @param remoteAddress Remote net address
	 * @param formParams To save the parameters
	 * @param fileName A file to save those content
	 * @return If the operation succeed, return true. Otherwise false.
	 */
	boolean doPost(String remoteAddress, List<NameValuePair> formParams,
			String fileName);
	
	/***
	 * Use POST method to obtain web content with some request headers.
	 * @param remoteAddress Remote net address
	 * @param formParams To save the parameters
	 * @param headers The request headers
	 * @param fileName A file to save those content
	 * @return If the operation succeed, return true. Otherwise false.
	 */
	boolean doPost(String remoteAddress, List<NameValuePair> formParams,
			Map<String, String> headers, String fileName);
	
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
