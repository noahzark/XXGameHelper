package xxgamehelper.framework.model;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.params.CoreProtocolPNames;
import xxgamehelper.framework.control.Messenger;
import xxgamehelper.framework.utils.FileUtils;

/***
 * The web client of game helper.
 * @author LongFangzhou
 * @version 0.2
 */
public class DefaultWebClient extends WebClient {
	
	public DefaultWebClient(Messenger messenger) {
		super(messenger);
	}
	
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

	public boolean saveRequestToFile(HttpHost host, HttpRequestBase req,
			String fileName) {
		HttpResponse rsp = this.sendRequest(host, req);
		if (rsp.getStatusLine().getStatusCode()>400)
			return false;
		this.lastRsp = rsp;
		try {
			if (FileUtils.saveRspToFile(rsp, fileName)) {
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
