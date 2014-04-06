package xxgamehelper.framework.model.connection;

import xxgamehelper.framework.control.messenger.Messenger;
import xxgamehelper.framework.model.client.WebClient;

/***
 * The connection class attributes.
 * @author LongFangzhou
 */
public abstract class ConnectionData {
	
	/***
	 * The messenger to output data.
	 */
	protected Messenger messenger;
	
	/***
	 * The connection class should include a web client to transfer data.
	 */
	protected WebClient webclient;
	
}
