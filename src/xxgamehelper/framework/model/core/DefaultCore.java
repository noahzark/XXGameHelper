package xxgamehelper.framework.model.core;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.log4j.Logger;

import xxgamehelper.framework.control.SearchStringInterface;
import xxgamehelper.framework.control.messenger.Messenger;
import xxgamehelper.framework.utils.CoreEntityUtils;

/***
 * A sample of Core's implement to provide basic/default functions.
 * @author LongFangzhou
 * @version 0.8
 */
public abstract class DefaultCore extends Core implements SearchStringInterface {

	static Logger logger = Logger.getLogger(DefaultCore.class);
	
	/***
	 * The constructor to initialize with a web client and a messenger.
	 * @param webclient The client generated by connection class
	 * @param messenger The messenger to output messages
	 */
	public DefaultCore(Messenger messenger) {
		super(messenger);
		randomer = new Random();
	}
	
	public String findString(String key, String fileName){
		return this.messenger.findString(key, fileName);
	}
	
	public String[] findAllString(String key, String fileName) {
		return this.messenger.findAllString(key, fileName);
	}

	public boolean preRequest(String remoteAddress, String fileName) {
		if (this.server == null){
			out.showError("Server is null");
			return false;
		}
		if (this.webclient == null) {
			out.showError("Webclient is null", "Maybe developer forgot to save connection");
			return false;
		}
		return true;
	}
	
	public void injectHeaders(HttpRequestBase request, Map<String, String> headers) {
		if (headers != null) {
			Set<Entry<String, String>> entries = headers.entrySet();
			for (Entry<String, String> entry : entries) {
				request.addHeader(entry.getKey(), entry.getValue());
			}
		}
	}
	
	public boolean getPage(String remoteAddress, String fileName) {
		return this.getPage(remoteAddress, null, fileName);
	}
	
	public boolean getPage(String remoteAddress, Map<String, String> headers, String fileName) {
		if (!fileName.contains(".")){
			fileName += ".html";
		}
		if (this.preRequest(remoteAddress, fileName)){
			HttpGet req = new HttpGet(remoteAddress);
			this.injectHeaders(req, headers);
			return webclient.saveRequestToFile(server, req, fileName);
		}
		return false;
	}
	
	public boolean postPage(String remoteAddress, List<NameValuePair> formParams,
			String fileName){
		return this.postPage(remoteAddress, formParams, null, fileName);
	}
	
	public boolean postPage(String remoteAddress, List<NameValuePair> formParams,
			Map<String, String> headers, String fileName){
		if (!fileName.contains(".")){
			fileName += ".html";
		}
		if (this.preRequest(remoteAddress, fileName)){
			HttpPost req = new HttpPost(remoteAddress);
			this.injectHeaders(req, headers);
			req.setEntity(CoreEntityUtils.generateEntity(formParams));
			return webclient.saveRequestToFile(server, req, fileName);
		}
		return false;
	}
	
	public void updateToken() {
		Long token = 0L;
		while (token==0L)
			token = this.randomer.nextLong();
		this.messenger.setVerifyToken(token);
	}
	
	private long generateRestTime(int basicRestTime, int extraRestTime){
		if (extraRestTime<=0) {
			out.showWarning("extra rest time is illegal, use default");
			extraRestTime = 30;
		}
		basicRestTime += randomer.nextInt(extraRestTime);
		return basicRestTime;
	}
	
	public void rest() {
		try {
			long t = 0;
			t = this.generateRestTime(this.basicRestTime, this.extraRestTime);
			this.messenger.pauseHelper(t);
		} catch (InterruptedException e) {
			this.messenger.println("The rest is interrupted");
		}
	}
	
	@Override
	public void run() {
		try {
			this.initGame();
			while (!this.isExitFlag()){
				this.updateToken();
				try {
					this.runGame();
					if (!this.isExitFlag())
						this.rest();
					this.cleanFiles();
				} catch (Exception e) {
					StackTraceElement[] stack = e.getStackTrace();
					int pos = 0;
					for (;pos<stack.length-1;pos++)
						if (stack[pos].getClassName().contains("xxgamehelper"))
							break;
					logger.error((new Date()).getTime() + "Found a unhandled error:" + e + "\n"
							+ e.getStackTrace()[pos]);
					out.showError(e);
					break;
				}
			}
			this.postGame();
		} catch (Exception e) {
			out.showError(e);
		}
	}
	
	@Override
	public void postGame() {
		this.messenger.setVerifyToken(0);
	}
}

