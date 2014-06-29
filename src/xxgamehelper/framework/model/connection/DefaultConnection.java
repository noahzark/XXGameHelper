package xxgamehelper.framework.model.connection;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;

import xxgamehelper.framework.control.messenger.Messenger;
import xxgamehelper.framework.model.client.WebClient;
import xxgamehelper.framework.utils.CoreEntityUtils;

/***
 * A sample of Connection's implement to provide basic/default functions.
 * @author LongFangzhou
 * @version 0.2
 */
public abstract class DefaultConnection extends Connection {
	
	public void useProxy(String address, int port) {
		HttpHost proxy = new HttpHost(address, port);
		this.webclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	}
	
	/***
	 * The constructor to initialize with a messenger and a WebClient.
	 * @param messenger The messenger to output messages
	 */
	public DefaultConnection(Messenger messenger, WebClient webclient){
		this.messenger = messenger;
		this.webclient = webclient;
	}
	
	public boolean check() {
		this.saveConnection();
		return true;
	}
	
	public void saveConnection() {
		this.messenger.setWebClient(this.webclient);
	}
	
	/***
	 * Set the server with default http protocol and 80 port.
	 * @param address
	 */
	public void setServer(String address) {
		this.server = new HttpHost(address, 80, "http");
	}
	
	/***
	 * Use POST method to obtain web content.
	 * @param host Target host
	 * @param req The request
	 * @param formParams The request entity
	 * @param fileName A file to save those content.
	 * @return If the operation succeed, return true. Otherwise false.
	 */
	public boolean doPost(HttpHost host, HttpPost req,
			List<NameValuePair> formParams, String fileName) {
		HttpEntity entity = CoreEntityUtils.generateEntity(formParams);
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
