package xxgamehelper.framework.model;

/***
 * The connection interface
 * @author LongFangzhou
 * Programs should use connection class to login to the 
 * server and generate the web client.
 */
public abstract interface ConnectionInterface {
	public boolean connect();
	
	public boolean check();
	
}
