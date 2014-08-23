package xxgamehelper.framework.model.client;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

import xxgamehelper.framework.control.messenger.Messenger;

/***
 * The web client class attributes.
 * @author LongFangzhou
 */
public class WebClientData extends DefaultHttpClient {
	
	Messenger messenger;
	HttpResponse lastRsp;
	
	WebClientData() {
		super(new ThreadSafeClientConnManager());
	}
	
	/**
	 * @return the lastRsp
	 */
	public HttpResponse getLastRsp() {
		return lastRsp;
	}
	
}
