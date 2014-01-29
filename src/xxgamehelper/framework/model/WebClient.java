package xxgamehelper.framework.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/***
 * The web client of game helper.
 * @author LongFangzhou
 * @version 0.1
 */
public class WebClient extends DefaultHttpClient {

	/***
	 * A static method to use a web client to send GET requests 
	 * and save content to a file.
	 * @param webclient The web client
	 * @param host The host
	 * @param req The GET request
	 * @param entity The response entity
	 * @param fileName The name of file which saves contents
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void sendGet(WebClient webclient, HttpHost host,
			HttpGet req, HttpEntity entity, String fileName)
			throws ClientProtocolException, IOException {
		HttpResponse rsp = webclient.execute(host, req);
		
		entity = rsp.getEntity();
		if (entity != null) {
			Header[] headers = rsp.getHeaders("Content-Encoding");
			InputStream is = null;
			if (headers.length>0)
				is = new GZIPInputStream(entity.getContent());
			else
				is = entity.getContent();
			FileOutputStream fos = new FileOutputStream(new File(fileName));
			byte[] b = new byte[Core.BUFFERSIZE];
			int len = 0;
			while((len=is.read(b))!=-1)
				fos.write(b,0,len);
			fos.close();
		}
	}
	
	/***
	 * A static method to use a web client to send POST requests 
	 * and save content to a file.
	 * @param webclient The web client
	 * @param host The host
	 * @param req The POST request
	 * @param entity The request entity
	 * @param fileName The name of file which saves contents
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void sendPost(WebClient webclient, HttpHost host,
			HttpPost req, HttpEntity entity, String fileName)
			throws ClientProtocolException, IOException {
		req.setEntity(entity);
		HttpResponse rsp = webclient.execute(host, req);
		
		entity = rsp.getEntity();
		if (entity != null) {
			Header[] headers = rsp.getHeaders("Content-Encoding");
			InputStream is = null;
			if (headers.length>0)
				is = new GZIPInputStream(entity.getContent());
			else
				is = entity.getContent();
			FileOutputStream fos = new FileOutputStream(new File(fileName));
			byte[] b = new byte[Core.BUFFERSIZE];
			int len = 0;
			while((len=is.read(b))!=-1)
				fos.write(b,0,len);
			fos.close();
		}
	}
}
