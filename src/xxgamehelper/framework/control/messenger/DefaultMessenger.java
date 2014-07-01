package xxgamehelper.framework.control.messenger;

import java.util.Date;

import xxgamehelper.framework.model.HelperFactory;
import xxgamehelper.framework.utils.StringTools;

/***
 * A sample of Core's implement to provide basic/default functions.
 * @author LongFangzhou
 * @version 0.6
 */
public abstract class DefaultMessenger extends Messenger {
	
	public DefaultMessenger(String homeDir, HelperFactory helperFactory) {
		super(homeDir, helperFactory);
	}

	public void println(String str) {
		this.print(str + "\n");
	}
	
	public void print(String str) {
		System.out.print(str);
	}
	
	public void showError(String errorSummary) {
		println("Error: " + errorSummary
				+ ", please try again later or contact with the author.\n");
	}
	
	public void showError(String errorSummary, String errorCause) {
		println("Error: " + errorSummary + "\n"
				+ errorCause + ", please try again later or contact with the author.\n");
	}
	
	public void showError(Exception e) {
		if (this.isDebugMode()){
			println(new Date()+"");
			e.printStackTrace();
		}
		this.showError(e.toString(), e.getLocalizedMessage());
	}

	public void showWarning(String content) {
		println("Warning: " + content
				+ ", please try again later or contact with the author.\n");
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

	@Override
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
