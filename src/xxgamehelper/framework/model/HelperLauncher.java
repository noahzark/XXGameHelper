package xxgamehelper.framework.model;

import xxgamehelper.framework.control.Messenger;

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
	public static Thread launch(Messenger messenger, HelperFactory factory) {
		Thread coreThread = null;
		Connection tscon = factory.buildConnection(messenger);
		if (tscon.connect())
			if (tscon.check()){
				Core core = factory.buildCore(tscon.getClient(), messenger);
				coreThread = new Thread(core);
				messenger.setGameThread(coreThread);
				coreThread.start();
			}
		return coreThread;
	}
	
	/***
	 * Launch the helper with a messenger, a helper factory,
	 * and a checker to ensure the helper thread keep running.
	 * @param messenger The helper messenger
	 * @param factory The helper factory
	 * @param checkInterval The interval time during the check break (seconds)
	 */
	public static void launchWithCheker(Messenger messenger, HelperFactory factory,
			int checkInterval) {
		Thread coreThread = null;
		while (true){
			coreThread = HelperLauncher.launch(messenger, factory);
			try {
				Thread.sleep(checkInterval*1000); // Take a break to wait core thread to run
				while(coreThread!=null) {
					messenger.println("The thread is alive.");
					Thread.sleep(checkInterval*1000);
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
