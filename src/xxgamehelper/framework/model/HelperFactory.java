package xxgamehelper.framework.model;

import xxgamehelper.framework.control.messenger.Messenger;
import xxgamehelper.framework.model.connection.Connection;
import xxgamehelper.framework.model.core.Core;

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
	Core buildCore(Messenger messenger);
	
}
