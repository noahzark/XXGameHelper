package xxgamehelper.framework.model;

import xxgamehelper.framework.control.messenger.Messenger;
import xxgamehelper.framework.model.connection.Connection;
import xxgamehelper.framework.model.core.Core;
import xxgamehelper.framework.utils.FileUtils;

/***
 * The helper launcher contains a static method to launch the helper.
 * @author LongFangzhou
 */
public class HelperLauncher {
	
	/***
	 * Launch the helper with a messenger and a helper factory.
	 * @param messenger The helper messenger
	 * @param factory The helper factory
	 * @return The helper thread
	 */
	public static void launch(Messenger messenger) {
		FileUtils.createDirectory(messenger.getWorkPath());
		messenger.setGameThread(null);
		Connection tscon = messenger.getHelperFactory().buildConnection(messenger);
		if (tscon.connect())
			if (tscon.check()){
				Core core = messenger.getHelperFactory().buildCore(messenger);
				messenger.setGameThread(new Thread(core));
				messenger.startGame();
			}
	}
	
	/***
	 * Launch the helper with a messenger, a helper factory,
	 * and a checker to ensure the helper thread keep running.
	 * @param messenger The helper messenger
	 * @param factory The helper factory
	 * @param checkInterval The interval time during the check break (seconds)
	 */
	public static void launchWithCheker(Messenger messenger, int checkInterval) {
		messenger.releaseGameThread();
		while (true){
			HelperLauncher.launch(messenger);
			long verifyToken = messenger.getVerifyToken();
			try {
				Thread.sleep(checkInterval*1000); // Take a break to wait core thread to run
				while(messenger.isGameAlive() && verifyToken != messenger.getVerifyToken()) {
					verifyToken = messenger.getVerifyToken();// Update the verify token
					messenger.println("The thread is alive, verify token:"+verifyToken);
					Thread.sleep(checkInterval*1000);						
				}
				messenger.println("The thread is dead, try to restart.");
				messenger.releaseGameThread();
			} catch (InterruptedException e) {
				messenger.showError(e);
			}
		}	
	}
}
