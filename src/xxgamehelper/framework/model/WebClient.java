package xxgamehelper.framework.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import xxgamehelper.framework.control.Messenger;

/***
 * The web client of game helper.
 * @author LongFangzhou
 * @version 0.2
 */
public abstract class WebClient extends DefaultHttpClient {
	private Messenger messenger;
	private HttpResponse lastRsp;
	
	/***
	 * Constructor with a messenger.
	 * @param messenger
	 */
	public WebClient(Messenger messenger) {
		this.messenger = messenger;
	}
	
	/***
	 * Send a request to the server and get response.
	 * @param host The target host
	 * @param req A HttpGet/Post request
	 * @return The HttpResponse
	 */
	public HttpResponse sendRequest(HttpHost host, HttpRequestBase req) {
		HttpResponse rsp = null;
		try {
			rsp = this.execute(host, req);
			this.lastRsp = rsp;
		} catch (IllegalStateException | IOException e) {
			this.lastRsp = null;
			this.messenger.showError(e);
		}
		return rsp;
	}

	/***
	 * A method to use a web client to send requests 
	 * and save content to a file.
	 * @param host The host
	 * @param req The request
	 * @param fileName The name of file which saves contents
	 */
	public boolean saveRequestToFile(HttpHost host, HttpRequestBase req,
			String fileName) {
		HttpResponse rsp = this.sendRequest(host, req);
		this.lastRsp = rsp;
		try {
			if (this.saveRspToFile(rsp, fileName))
				return true;
		} catch (IOException e) {
			this.lastRsp = null;
			this.messenger.showError(e);
		}
		return false;
	}
	
	/***
	 * The method to save a response to the file
	 * @param entity
	 * @param rsp
	 * @param fileName
	 * @throws IOException 
	 */
	private boolean saveRspToFile(HttpResponse rsp, String fileName) throws IOException{
		HttpEntity entity = rsp.getEntity();
		if (entity != null) {
			Header[] coding = rsp.getHeaders("Content-Encoding");
			InputStream is = null;
			try {
				if (coding.length>0)
					is = new GZIPInputStream(entity.getContent());
				else
					is = entity.getContent();
				FileOutputStream fos = new FileOutputStream(new File(fileName));
				byte[] b = new byte[Core.BUFFERSIZE];
				int len = 0;
				while((len=is.read(b))!=-1)
					fos.write(b,0,len);
				fos.close();
			} catch (IllegalStateException | IOException e) {
				this.lastRsp = null;
				this.messenger.showError(e);
				return false;
			} finally {
				EntityUtils.consume(entity);
			}
			if (this.messenger.isDebugMode())
				this.showLastResponseSummary();
		}
		if (rsp.getStatusLine().getStatusCode()<400)
			return true;
		else
			return false;
	}
	
	/**
	 * @return the lastRsp
	 */
	public HttpResponse getLastRsp() {
		return lastRsp;
	}

	/***
	 * To show status line and headers of the last response.
	 */
	public void showLastResponseSummary() {
		if (this.lastRsp == null){
			this.messenger.showWarning("Last response didn't receive or fail.");
			return;
		}
		this.messenger.println("Status: " + this.lastRsp.getStatusLine().toString());
		Header[] headers = this.lastRsp.getAllHeaders();
		for (Header header : headers)
			this.messenger.println(header.toString());
		this.messenger.println("");
	}
	
	/***
	 * Use a easy cookie policy.
	 */
	public void useEasyCookiePolicy() {
		CookieSpecFactory csf = new CookieSpecFactory(){
			public CookieSpec  newInstance(HttpParams params){
	        	return new BrowserCompatSpec(){
	            	@Override
	            	public void validate(Cookie cookie, CookieOrigin origin)
	            			throws MalformedCookieException{
	            				//Oh, I am easy
	            	}
	            };
	        }};
	    this.getCookieSpecs().register("easy", csf);
	    this.getParams().setParameter(ClientPNames.COOKIE_POLICY, "easy");
	}
	
	/***
	 * Set the client's user agent string.
	 * @param ua User agent string
	 */
	public void setUserAgent(String ua) {
		this.getParams().setParameter(
				CoreProtocolPNames.USER_AGENT, ua);
	}
}
