package xxgamehelper.framework.model;

import xxgamehelper.framework.control.Messenger;

/***
 * The helper launcher contains a static method to launch the helper.
 * @author LongFangzhou
 */
public class HelperLauncher {
	/***
	 * The interval time during the check break (seconds)
	 */
	public static int CHECK_INTERVALS = 60;
	
	/***
	 * Launch the helper with a messenger and a helper factory.
	 * @param messenger The helper messenger
	 * @param factory The helper factory
	 */
	public static void launch(Messenger messenger, HelperFactory factory) {
		Thread coreThread = null;
		while (true){
			Connection tscon = factory.buildConnection(messenger);
			if (tscon.connect())
				if (tscon.check()){
					Core core = factory.buildCore(tscon.getClient(), messenger);
					coreThread = new Thread(core);
					messenger.setGameThread(coreThread);
					coreThread.start();
				}
			try {
				Thread.sleep(CHECK_INTERVALS*1000); // Take a break to wait core thread to run
				while(coreThread!=null) {
					messenger.println("The thread is alive.");
					Thread.sleep(CHECK_INTERVALS*1000);
					if (!coreThread.isAlive())
						coreThread = null;
				}
				messenger.println("The thread is dead, try to restart.");
			} catch (InterruptedException e) {
				messenger.showError(e);
			}
		}
	}
}
