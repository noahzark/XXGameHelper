package xxgamehelper.framework.control;

import xxgamehelper.framework.model.HelperFactory;

/***
 * All models should use this messenger to operate on views.
 * @author LongFangzhou
 */
public abstract class Messenger extends MessengerData implements MessengerInterface{

	public Messenger(HelperFactory helperFactory) {
		super(helperFactory);
	}

}
