package xxgamehelper.framework.model;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import xxgamehelper.framework.control.Messenger;

/***
 * Programs should use connection class to login to the server
 * and generate the web client.
 * @author LongFangzhou
 */
public abstract class Connection extends ConnectionData implements ConnectionInterface{
	
	/***
	 * The constructor to initialize with a messenger and a WebClient.
	 * @param messenger The messenger to output messages
	 */
	public Connection(Messenger messenger, WebClient webclient){
		this.setMessenger(messenger);
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
		return this.webclient.sendPost(host, req, entity, fileName);
	}
	
	public boolean doGet(HttpHost host, HttpGet req, String fileName) {
		return this.webclient.sendGet(host, req, fileName);
	}
}
