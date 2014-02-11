package xxgamehelper.framework.model;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;

import xxgamehelper.framework.control.Messenger;

/***
 * The web client class attributes.
 * @author LongFangzhou
 */
public class WebClientData extends DefaultHttpClient {
	
	Messenger messenger;
	HttpResponse lastRsp;
	
	/**
	 * @return the lastRsp
	 */
	public HttpResponse getLastRsp() {
		return lastRsp;
	}
	
}
