package xxgamehelper.framework.model.connection;

import org.apache.http.HttpHost;

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
	
	/***
	 * The server which connects to.
	 */
	protected HttpHost server;
	
	/***
	 * Set the server which will login to.
	 * @param protocol HTTPS/HTTP
	 * @param address Server address
	 * @param port Server port
	 */
	public void setServer(String protocol, String address, int port) {
		this.server = new HttpHost(address, port, protocol);
	}
	
}
