package xxgamehelper.framework.model;

import org.apache.http.HttpHost;

import xxgamehelper.framework.control.Messenger;

/***
 * The core class attributes.
 * @author LongFangzhou
 */
public abstract class CoreData {
	
	/***
	 * The messenger to output data.
	 */
	protected Messenger messenger;

	public void setMessenger(Messenger messenger) {
		this.messenger = messenger;
	}

	/***
	 * The web client to transfer data.
	 */
	protected WebClient webclient;
	
	public void setWebclient(WebClient webclient){
		this.webclient = webclient;
	}
	
	/***
	 * The host target.
	 */
	protected HttpHost host;

	public void setHost(HttpHost host) {
		this.host = host;
	}
	
}
