package xxgamehelper.framework.model;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import xxgamehelper.framework.control.Messenger;

/***
 * A sample of Connection's implement to provide basic/default functions.
 * @author LongFangzhou
 * @version 0.2
 */
public abstract class DefaultConnection extends Connection {
	
	/***
	 * The constructor to initialize with a messenger and a WebClient.
	 * @param messenger The messenger to output messages
	 */
	public DefaultConnection(Messenger messenger, WebClient webclient){
		this.messenger = messenger;
		this.webclient = webclient;
	}
	
	/***
	 * Use POST method to obtain web content.
	 * @param host Target host
	 * @param req The request
	 * @param entity The request entity
	 * @param fileName A file to save those content.
	 * @return If the operation succeed, return true. Otherwise false.
	 */
	public boolean doPost(HttpHost host, HttpPost req,
			HttpEntity entity, String fileName) {
		req.setEntity(entity);
		return this.webclient.saveRequestToFile(host, req, fileName);
	}
	
	/***
	 * Use GET method to obtain web content.
	 * @param host Target host
	 * @param req The request
	 * @param fileName A file to save those content.
	 * @return If the operation succeed, return true. Otherwise false.
	 */
	public boolean doGet(HttpHost host, HttpGet req, String fileName) {
		return this.webclient.saveRequestToFile(host, req, fileName);
	}
	
}