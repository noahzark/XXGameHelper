package xxgamehelper.framework.model.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;

import xxgamehelper.framework.control.messenger.Messenger;
import xxgamehelper.framework.utils.CoreEntityUtils;
import xxgamehelper.framework.utils.FileTools;
import xxgamehelper.framework.utils.StringTools;

/***
 * The web client of game helper.
 * @author LongFangzhou
 * @version 0.6
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
	
	public void injectHeaders(HttpRequestBase request, Map<String, String> headers) {
		if (headers != null) {
			Set<Entry<String, String>> entries = headers.entrySet();
			for (Entry<String, String> entry : entries) {
				request.addHeader(entry.getKey(), entry.getValue());
			}
		}
	}
	
	@Override
	public List<NameValuePair> generateFormParams(Map<String, String> paramsMap) {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		if (paramsMap!=null) {
			Set<Entry<String, String>> set = paramsMap.entrySet();
			for (Entry<String, String> entry : set) {
				formParams.add(new BasicNameValuePair(
						entry.getKey(), entry.getValue()));
			}
		}
		return formParams;
	}

	public HttpResponse sendRequest(HttpHost host, HttpRequestBase req) {
		if (this.messenger.isDebugMode())
			this.messenger.println((new Date()) + " Now loading:\n"
					+ req.getURI());
		HttpResponse rsp = null;
		try {
			rsp = this.execute(host, req);
			this.lastRsp = rsp;
		} catch (IllegalStateException e) {
			this.lastRsp = null;
			this.messenger.showError(e);
		} catch (IOException e) {
			this.lastRsp = null;
			this.messenger.showError(e);
		} finally {
//			req.releaseConnection();
		}
		return rsp;
	}

	public boolean saveRequestToFile(HttpHost host, HttpRequestBase req,
			String filePath, String fileName) {
		if (!fileName.contains(".")){
			fileName += ".html";
		}
		HttpResponse rsp = this.sendRequest(host, req);
		if (rsp==null || rsp.getStatusLine().getStatusCode()>=400) {
			req.releaseConnection();
			return false;
		}
		this.lastRsp = rsp;
		try {
			String fileAddress = filePath + fileName;
			if (this.messenger.isDebugMode())
				this.showLastResponseSummary();
			if (FileTools.saveRspToFile(messenger, rsp, fileAddress, messenger.isShowDownloadMode())) 
				return true;
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

	@Override
	public boolean doGet(HttpHost host, String actionName,
			Map<String, String> headers,
			String filePath, String fileName) {
		HttpGet req = new HttpGet(actionName);
		this.injectHeaders(req, headers);
		return saveRequestToFile(host, req, filePath, fileName);
	}
	
	@Override
	public boolean doPost(HttpHost host, String actionName,
			Map<String, String> paramsMap, Map<String, String> headers,
			String filePath, String fileName) {
		HttpPost req = new HttpPost(actionName);
		this.injectHeaders(req, headers);
		req.setEntity(CoreEntityUtils.generateEntity(
				this.generateFormParams(paramsMap)));
		return saveRequestToFile(host, req, filePath, fileName);
	}
	
	@Override
	@Deprecated
	public boolean doPost(HttpHost host, String actionName,
			List<NameValuePair> formParams, Map<String, String> headers,
			String fileName) {
		HttpPost req = new HttpPost(actionName);
		this.injectHeaders(req, headers);
		req.setEntity(CoreEntityUtils.generateEntity(
				formParams));
		return saveRequestToFile(host, req, this.messenger.getWorkPath(), fileName);
	}
	
	public String downloadFile(String src, String filePath) {
		String saveName = src.substring(src.lastIndexOf('/')+1);
		return this.downloadFile(src, filePath, saveName);
	}

	@Override
	public String downloadFile(String src, String filePath, String fileName) {
		HttpHost picHost = new HttpHost(StringTools.sortString(src, "//", '/'));
		if (this.doGet(picHost, src, null, filePath, fileName))
			return fileName;
		return null;
	}

}
