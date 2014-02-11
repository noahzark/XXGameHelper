package xxgamehelper.framework.model;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

/***
 * The web client class should provide several methods for Internet communications.
 * @author LongFangzhou
 */
public interface WebClientInterface {
	
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
}
