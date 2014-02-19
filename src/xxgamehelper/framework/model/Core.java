package xxgamehelper.framework.model;

import xxgamehelper.framework.control.Messenger;

/***
 * The core class should provide a constructor initialize with web client.
 * @author LongFangzhou
 */
public abstract class Core extends CoreData implements CoreInterface{
	
	public static final int BUFFERSIZE = 8192;
	
	public Core(Messenger messenger) {
		super(messenger);
	}

}
