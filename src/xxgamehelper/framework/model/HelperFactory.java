package xxgamehelper.framework.model;

import xxgamehelper.framework.control.Messenger;

/***
 * The factory to build connections and cores.
 * @author LongFangzhou
 */
public interface HelperFactory {
	
	/***
	 * Help launcher to build a connection with the messenger.
	 * @param messenger
	 * @return
	 */
	Connection buildConnection(Messenger messenger);
	
	/***
	 * Help launcher to build a core with webclient and messenger.
	 * @param client
	 * @param messenger
	 * @return
	 */
	Core buildCore(WebClient client, Messenger messenger);
	
}
