package xxgamehelper.framework.model;

import java.util.Map;

/***
 * Some methods to communicate with server.
 * @author LongFangzhou
 */
public interface NetOperationInterface {
	
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
	boolean doPost(String remoteAddress, Map<String, String> formParams,
			String fileName);
	
	/***
	 * Use POST method to obtain web content with some request headers.
	 * @param remoteAddress Remote net address
	 * @param formParams To save the parameters
	 * @param headers The request headers
	 * @param fileName A file to save those content
	 * @return If the operation succeed, return true. Otherwise false.
	 */
	boolean doPost(String remoteAddress, Map<String, String> formParams,
			Map<String, String> headers, String fileName);

}
