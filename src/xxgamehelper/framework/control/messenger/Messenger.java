package xxgamehelper.framework.control.messenger;

import xxgamehelper.framework.control.FileOperationInterface;
import xxgamehelper.framework.control.GameOperationInterface;
import xxgamehelper.framework.model.HelperFactory;

/***
 * All models should use this messenger to operate on views.
 * @author LongFangzhou
 */
public abstract class Messenger extends MessengerData implements MessengerInterface, GameOperationInterface, FileOperationInterface{

	public Messenger(HelperFactory helperFactory) {
		super(helperFactory);
	}

}
