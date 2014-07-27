package xxgamehelper.framework.model.client;

import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpRequestBase;

/***
 * The web client class should provide several methods for Internet communications.
 * @author LongFangzhou
 */
public interface WebClientInterface {
	
	/***
	 * Inject all cookies in the web client to a request.
	 * @param req Target request
	 */
	public void injectCookiesToRequest(HttpRequestBase req);
	
	/***
	 * Inject some request headers to a request.
	 * @param request The target request
	 * @param headers The headers map
	 */
	public void injectHeaders(HttpRequestBase request,
			Map<String, String> headers);
	
	/***
	 * Convert a map to a name value pair list.
	 * @param paramsMap The parameters map
	 * @return Name value pair list
	 */
	public List<NameValuePair> generateFormParams(Map<String, String> paramsMap);
	
	/***
	 * Send a request to the server and get response.
	 * @param host The target host
	 * @param req A HttpGet/Post request
	 * @return The HttpResponse
	 */
	public HttpResponse sendRequest(HttpHost host, HttpRequestBase req);

	/***
	 * A method to use a web client to send requests 
	 * and save content to a file.
	 * @param host The host
	 * @param req The request
	 * @param filePath The directory where to save the file
	 * @param fileName The name of file which saves contents
	 */
	public boolean saveRequestToFile(HttpHost host, HttpRequestBase req,
			String filePath, String fileName);
	
	/***
	 * To show status line and headers of the last response.
	 */
	public void showLastResponseSummary();
	
	/***
	 * Set the client's user agent string.
	 * @param ua User agent string
	 */
	public void setUserAgent(String ua);
	
	/***
	 * User proxy server to net connections.
	 * @param address The proxy server address
	 * @param port The proxy server port
	 */
	public void useProxy(String address, int port);
	
	/***
	 * Use GET method to obtain web content with some request headers.
	 * @param host The server
	 * @param actionName Remote action
	 * @param headers The request headers map
	 * @param fileName A file to save those content
	 * @return If the operation succeed, return true. Otherwise false
	 */
	public boolean doGet(HttpHost host, String actionName,
			Map<String, String> headers,
			String filePath, String fileName);
	
	/***
	 * Use POST method to obtain web content with some request headers.
	 * @param host The server
	 * @param actionName Remote action
	 * @param paramsMap Request parameters map
	 * @param headers The request headers map
	 * @param fileName A file to save those content
	 * @return If the operation succeed, return true. Otherwise false
	 */
	public boolean doPost(HttpHost host, String actionName,
			Map<String, String> paramsMap,
			Map<String, String> headers,
			String filePath, String fileName);
	
	/***
	 * Use POST method to obtain web content with some request headers.
	 * @param host The server
	 * @param actionName Remote action
	 * @param paramsMap Request parameters map
	 * @param headers The request headers map
	 * @param fileName A file to save those content
	 * @return If the operation succeed, return true. Otherwise false
	 */
	@Deprecated
	public boolean doPost(HttpHost host, String actionName,
			List<NameValuePair> formParams,
			Map<String, String> headers,
			String fileName);
}
