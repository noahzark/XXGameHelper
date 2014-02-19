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
	
	/***
	 * The connection class should include a web client to transfer data.
	 */
	protected WebClient webclient;
	
}
