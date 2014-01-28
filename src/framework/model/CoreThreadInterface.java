package framework.model;

import org.apache.http.HttpEntity;

/***
 * The interface of core class
 * @author LongFangzhou
 * @version 0.1
 * The core class should be a thread to improve stability.
 */
public abstract interface CoreThreadInterface extends Runnable {

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
