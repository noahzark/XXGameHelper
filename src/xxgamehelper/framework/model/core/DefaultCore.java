package xxgamehelper.framework.model.core;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.http.NameValuePair;
import org.apache.log4j.Logger;

import xxgamehelper.framework.control.SearchStringInterface;
import xxgamehelper.framework.control.messenger.Messenger;

/***
 * A sample of Core's implement to provide basic/default functions.
 * @author LongFangzhou
 * @version 0.9
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
	
	public boolean doGet(String remoteAddress, String fileName) {
		return this.doGet(remoteAddress, null, fileName);
	}
	
	public boolean doGet(String remoteAddress, Map<String, String> headers, String fileName) {
		if (this.preRequest(remoteAddress, fileName)){
			return webclient.doGet(server, remoteAddress, headers, fileName);
		}
		return false;
	}
	
	public boolean doPost(String remoteAddress, List<NameValuePair> formParams,
			String fileName){
		return this.doPost(remoteAddress, formParams, null, fileName);
	}
	
	public boolean doPost(String remoteAddress, List<NameValuePair> formParams,
			Map<String, String> headers, String fileName){
		if (this.preRequest(remoteAddress, fileName)){
			return webclient.doPost(server, remoteAddress, formParams, headers, fileName);
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

