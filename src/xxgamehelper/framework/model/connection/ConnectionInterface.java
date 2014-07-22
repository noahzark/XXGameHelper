package xxgamehelper.framework.model.connection;

/***
 * The connection class should contains connect and check connection methods.
 * @author LongFangzhou
 */
public abstract interface ConnectionInterface {
	
	public void useProxy(String address, int port);
	
	/***
	 * Do something befroe login to the server.
	 * @return True while connect operation is done. If errors occurs, return false.
	 */
	public boolean preConnect();
	
	/***
	 * Login to the server
	 * @return True while connect operation is done. If errors occurs, return false.
	 */
	public boolean login();
	
	/***
	 * Server connection operations.
	 * @return True while connect operation is done. If errors occurs, return false.
	 */
	public boolean connect();
	
	/***
	 * Check and save the connection
	 * @return True while connection is right, otherwise false.
	 */
	public boolean check();
	
	/***
	 * Save the connection to messenger.
	 */
	public void saveConnection();
}
