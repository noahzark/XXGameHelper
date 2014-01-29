package xxgamehelper.framework.model;

/***
 * The connection class attributes.
 * @author LongFangzhou
 */
public abstract class ConnectionData {
	/***
	 * The connection class should include a web client to transfer data.
	 */
	WebClient client;
	
	/***
	 * The method to get the web client generated.
	 * @return The generated web client
	 */
	public WebClient getClient(){
		return client;
	}
}
