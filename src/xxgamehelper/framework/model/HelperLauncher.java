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
	
	/***
	 * Launch the helper with a messenger and a helper factory.
	 * @param messenger The helper messenger
	 * @param factory The helper factory
	 * @return The helper thread
	 */
	public static void launch(Messenger messenger) {
		if (logger.isInfoEnabled())
		logger.info("Initialize the helper.");
		FileTools.createDirectory(messenger.getWorkPath());
		messenger.setHelperThread(null);
		Connection tscon = messenger.getHelperFactory().buildConnection(messenger);
		logger.info("Try to lanch a helper.");
		if (tscon.connect())
			if (tscon.check()){
				Core core = messenger.getHelperFactory().buildCore(messenger);
				messenger.setHelperThread(new Thread(core));
				messenger.startHelper();
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
		while (true){
			HelperLauncher.launch(messenger);
			long verifyToken = 0L;
			try {
				do {
					messenger.println("The helper thread is alive.");
					verifyToken = messenger.getVerifyToken();// Remember the verify token
					Thread.sleep(checkInterval*1000);// Take a break to wait core thread update token
				} while(messenger.isHelperAlive() && verifyToken != messenger.getVerifyToken());
				messenger.println((new Date()) + ": The helper thread is dead.");
				messenger.println("Dump work files.");
				logger.info("Start dumping error files.");
				//Check if the error is duplicated
				FileTools.fileMove(
						messenger.getWorkPath(),
						messenger.getDataPath()+"error/"+(new Date()).getTime(),
						true);
				logger.info("Done");
				messenger.println("Try to restart the helper thread");
			} catch (InterruptedException e) {
				messenger.showError(e);
			} finally {
				//TODO Do some clean jobs here
				messenger.releaseHelperThread();
			}
		}	
	}

}
