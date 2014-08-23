package xxgamehelper.framework.control.messenger;

import java.io.File;
import java.util.Date;

import org.apache.log4j.Logger;

import xxgamehelper.framework.model.HelperFactory;
import xxgamehelper.framework.utils.FileTools;
import xxgamehelper.framework.utils.StringTools;

/***
 * A sample of Core's implement to provide basic/default functions.
 * @author LongFangzhou
 * @version 0.7
 */
public abstract class DefaultMessenger extends Messenger {
	
	static Logger logger = Logger.getLogger(DefaultMessenger.class);
	
	public DefaultMessenger(HelperFactory helperFactory) {
		super(helperFactory);
		if (logger.isInfoEnabled())
			logger.info("Default messenger initialized.");
	}

	public void println(String str) {
		this.print(str + "\n");
	}
	
	public void print(String str) {
		System.out.print(str);
	}
	
	public void showError(String errorSummary) {
		this.showError(errorSummary, null);
	}
	
	public void showError(String errorSummary, String errorCause) {
		println("Error: " + errorSummary
				+ ((errorCause != null)? " - " + errorCause : "")
				+ ", please try again later or contact with the author.\n");
	}
	
	public void showError(Exception e) {
		if (this.isDebugMode()){
			println(new Date()+"");
			e.printStackTrace();
		}
		this.showError(e.toString());
	}

	public void showWarning(String content) {
		println("Warning: " + content
				+ ", please try again later or contact with the author.\n");
		logger.warn(content);
	}
	
	public void startHelper() {
		this.helperThread.start();
	}
	
	public void pauseHelper(long t) throws InterruptedException{
		this.println("The game was paused for "+t+" second(s).\n");
		Thread.sleep(t*1000L);
	}
	
	public void continueHelper() {
		this.helperThread.interrupt();
	}
	
	public boolean isHelperAlive() {
		if (this.helperThread!=null)
			if (this.helperThread.isAlive())
				return true;
		return false;
	}
	
	public void releaseHelperThread() {
		if (this.helperThread!=null) {
			this.helperThread.interrupt();
			this.helperThread.interrupt();
			this.helperThread = null;
		}
	}
	
	public void dumpWorkFiles() {
		println("Dump work files.");
		logger.info("Start dumping error files.");
		FileTools.moveFile(
				getWorkPath(),
				getErrorDumpPath()+(new Date()).getTime(),
				true);
		File dumpPath = new File(getErrorDumpPath());
		File[] errorFiles = dumpPath.listFiles();
		if (errorFiles.length>10) {
			logger.warn("There is too much error dump files," +
					" try to delete the earliest.");
			long earlies = Long.MAX_VALUE;
			File earliesFile = null;
			for (int i=0;i<errorFiles.length;i++) {
				long errorTime = Long.parseLong(errorFiles[i].getName());
				if (errorTime<earlies) {
					earlies = errorTime;
					earliesFile = errorFiles[i];
				}
			}
			FileTools.deleteFile(earliesFile);
		}
		logger.info("Done");
	}

	public String findString(String key, String fileName) {
		if (!fileName.contains(".")){
			fileName += ".html";
		}
		return StringTools.searchForString(key, this.getWorkPath(), fileName);
	}

	@Override
	public String[] findAllString(String key, String fileName) {
		if (!fileName.contains(".")){
			fileName += ".html";
		}
		return StringTools.searchAllString(key, this.getWorkPath(), fileName);
	}
}
