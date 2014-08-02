package xxgamehelper.framework.model;

import java.util.Date;

import org.apache.log4j.Logger;

import xxgamehelper.framework.control.messenger.Messenger;
import xxgamehelper.framework.model.connection.Connection;
import xxgamehelper.framework.model.core.Core;
import xxgamehelper.framework.utils.FileTools;

/***
 * The helper launcher contains a static method to launch the helper.
 * @author LongFangzhou
 */
public class HelperLauncher {
	
	static Logger logger = Logger.getLogger(HelperLauncher.class);
	
	private static void launchCore(Messenger messenger) {
		Core core = messenger.getHelperFactory().buildCore(messenger);
		messenger.setHelperThread(new Thread(core));
		messenger.startHelper();
	}
	
	/***
	 * Launch the helper with a messenger and a helper factory.
	 * @param messenger The helper messenger
	 * @return The helper thread
	 */
	public static void launchWithoutCheck(Messenger messenger) {
		if (logger.isInfoEnabled())
		logger.info("Initialize the helper.");
		FileTools.createDirectory(messenger.getWorkPath());
		messenger.setHelperThread(null);
		Connection tscon = messenger.getHelperFactory().buildConnection(messenger);
		logger.info("Try to lanch a helper.");
		if (tscon.connect()) {
				HelperLauncher.launchCore(messenger);
				logger.info("Launch succeeded.");
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
		logger.info("Try to launch helper with a checker per " + checkInterval + " seconds.");
		messenger.releaseHelperThread();
		HelperLauncher.launchWithoutCheck(messenger);
		
		long lstLaunchTime = (new Date()).getTime();
		int restartCount = 0;
		int waitInterval = checkInterval;
		while (true){
			long verifyToken = 0L;
			try {
				do {
					messenger.println("The helper thread is alive.");
					verifyToken = messenger.getVerifyToken();// Remember the verify token
					Thread.sleep(waitInterval*1000);// Take a break to wait core thread update token
				} while(messenger.isHelperAlive() && verifyToken != messenger.getVerifyToken());
				long nowTime = (new Date()).getTime();
				messenger.println(nowTime + ": The helper thread is dead.");
				//If a verify token equals zero, it aborts normally and
				//don't need to dump work files.
				if (messenger.getVerifyToken()!=0L) {
					messenger.dumpWorkFiles();
					if (nowTime<=lstLaunchTime + 5*waitInterval*1000)
						restartCount++;
				}
			} catch (InterruptedException e) {
				messenger.showError(e);
			} finally {
				//TODO Do some clean jobs here
				messenger.releaseHelperThread();
			}
			if (restartCount<10) {
				messenger.println("Try to restart the helper core thread.");
				HelperLauncher.launchCore(messenger);
				waitInterval = checkInterval;
			} else {
				messenger.println("Try to restart the helper thread completely.");
				HelperLauncher.launchWithoutCheck(messenger);
				waitInterval = checkInterval * 5;
				restartCount = 0;
			}
			lstLaunchTime = (new Date()).getTime();
		}	
	}

}
