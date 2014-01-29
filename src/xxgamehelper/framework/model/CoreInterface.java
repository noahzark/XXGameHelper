package xxgamehelper.framework.model;

import org.apache.http.HttpEntity;

/***
 * The core class should be runnable to improve stability and achieve multi-thread.
 * @author LongFangzhou
 * @version 0.1
 */
public abstract interface CoreInterface extends Runnable {

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
	abstract boolean postPage(String remoteAddress, HttpEntity httpEntity, String fileName);
	
	/***
	 * The method to clean tempoary files.
	 */
	abstract void clearFiles();

}
