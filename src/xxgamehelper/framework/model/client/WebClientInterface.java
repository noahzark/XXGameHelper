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
	 * @param fileName The name of file which saves contents
	 */
	public boolean saveRequestToFile(HttpHost host, HttpRequestBase req,
			String fileName);
	
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
	 * Use GET method to obtain web content.
	 * @param host The server
	 * @param actionName Remote action
	 * @param fileName A file to save those content
	 * @return If the operation succeed, return true. Otherwise false
	 */
	public boolean doGet(HttpHost host, String actionName,
			String fileName);
	
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
			String fileName);
	
	/***
	 * Use POST method to obtain web content.
	 * @param host The server
	 * @param actionName Remote action
	 * @param formParams Request parameters
	 * @param fileName A file to save those content
	 * @return If the operation succeed, return true. Otherwise false
	 */
	public boolean doPost(HttpHost host, String actionName,
			List<NameValuePair> formParams, String fileName);
	
	/***
	 * Use POST method to obtain web content with some request headers.
	 * @param host The server
	 * @param actionName Remote action
	 * @param formParams Request parameters
	 * @param headers The request headers map
	 * @param fileName A file to save those content
	 * @return If the operation succeed, return true. Otherwise false
	 */
	public boolean doPost(HttpHost host, String actionName,
			List<NameValuePair> formParams,
			Map<String, String> headers,
			String fileName);
}
