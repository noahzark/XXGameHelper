package xxgamehelper.framework.control.messenger;

import xxgamehelper.framework.control.SearchStringInterface;
import xxgamehelper.framework.control.HelperOperationInterface;
import xxgamehelper.framework.model.HelperFactory;

/***
 * All models should use this messenger to operate on views.
 * @author LongFangzhou
 */
public abstract class Messenger extends MessengerData implements OutputInterface, HelperOperationInterface, SearchStringInterface{

	public Messenger(HelperFactory helperFactory) {
		super(helperFactory);
	}

}
