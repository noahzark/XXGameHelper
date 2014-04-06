package xxgamehelper.framework.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import xxgamehelper.framework.control.messenger.Messenger;
import xxgamehelper.framework.view.LoginFrame;

/***
 * The login frame action listener super class.
 * @author LongFangzhou
 */
public abstract class LoginFrameListener implements ActionListener{
	protected Messenger messenger;
	protected LoginFrame inst;
	
	public LoginFrameListener(Messenger messenger, LoginFrame inst) {
		this.messenger = messenger;
		this.inst = inst;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == inst.getButtonConfirm())
			this.doConfirmAction(inst.getJTextFieldA().getText(),inst.getJTextFieldB().getText());
		else
			this.doCancelAction();
	}

	/***
	 * The action after cancel button was pressed.
	 */
	public abstract void doCancelAction();
	
	/***
	 * The action after confirm button was pressed.
	 * @param username The string in username box
	 * @param password The string in password box
	 */
	public abstract void doConfirmAction(String username, String password);

}
