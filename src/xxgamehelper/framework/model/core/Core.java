package xxgamehelper.framework.model.core;

import xxgamehelper.framework.control.messenger.Messenger;
import xxgamehelper.framework.model.NetOperationInterface;

/***
 * The core class should provide a constructor initialize with web client.
 * @author LongFangzhou
 */
public abstract class Core extends CoreData implements CoreInterface, NetOperationInterface{
	
	public static final int BUFFERSIZE = 8192;
	
	public Core(Messenger messenger) {
		super(messenger);
	}

}
