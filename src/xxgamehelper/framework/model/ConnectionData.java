package xxgamehelper.framework.model;

import xxgamehelper.framework.control.Messenger;

/***
 * The connection class attributes.
 * @author LongFangzhou
 */
public abstract class ConnectionData {
	
	/***
	 * The messenger to output data.
	 */
	protected Messenger messenger;

	public void setMessenger(Messenger messenger) {
		this.messenger = messenger;
	}
	
	/***
	 * The connection class should include a web client to transfer data.
	 */
	WebClient webclient;
	
	/***
	 * The method to get the web client generated.
	 * @return The generated web client
	 */
	public WebClient getClient(){
		return webclient;
	}
}
