package xxgamehelper.framework.model.client;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.params.CoreProtocolPNames;

import xxgamehelper.framework.control.messenger.Messenger;
import xxgamehelper.framework.utils.FileTools;

/***
 * The web client of game helper.
 * @author LongFangzhou
 * @version 0.3
 */
public class DefaultWebClient extends WebClient {
	
	public DefaultWebClient(Messenger messenger) {
		super(messenger);
	}
	
	public void injectCookiesToRequest(HttpRequestBase req) {
		CookieStore store = this.getCookieStore();
		List<Cookie> cookies = store.getCookies();
		System.out.println("Cookies:--------------------------------");
		StringBuffer sb = new StringBuffer();
		for (Cookie cookie : cookies) {
			System.out.println(cookie.getDomain()+ " - " + cookie.getName()+": "+cookie.getValue());
			if (!cookie.getName().contains("alcb")&&!cookie.getName().contains("secid"))
				sb.append(cookie.getName()+":"+cookie.getValue()+"; ");
		}
		req.addHeader("Cookie", sb.toString());
		System.out.println("Cookies finished.");
	}
	
	public HttpResponse sendRequest(HttpHost host, HttpRequestBase req) {
		if (this.messenger.isDebugMode())
			this.messenger.println((new Date()) + " Now loading:\n"
					+ req.getURI());
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

	public boolean saveRequestToFile(HttpHost host, HttpRequestBase req,
			String fileName) {
		HttpResponse rsp = this.sendRequest(host, req);
		if (rsp==null || rsp.getStatusLine().getStatusCode()>=400) {
			req.releaseConnection();
			return false;
		}
		this.lastRsp = rsp;
		try {
			String filePath = this.messenger.getWorkPath()	+ fileName;
			if (FileTools.saveRspToFile(rsp, filePath)) {
				if (this.messenger.isDebugMode())
					this.showLastResponseSummary();
				return true;
			}
		} catch (IOException e) {
			this.messenger.showError(e);
		}
		this.lastRsp = null;
		return false;
	}

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

	public void setUserAgent(String ua) {
		this.getParams().setParameter(
				CoreProtocolPNames.USER_AGENT, ua);
	}

}
