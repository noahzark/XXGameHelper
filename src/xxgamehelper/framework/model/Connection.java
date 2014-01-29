package xxgamehelper.framework.model;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;

/***
 * Programs should use connection class to login to the server
 * and generate the web client.
 * @author LongFangzhou
 */
public abstract class Connection extends ConnectionData implements ConnectionInterface{
	
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
		try {
			this.webclient = new WebClient();
			WebClient.sendPost(webclient, host, req, entity, fileName);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return false;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
