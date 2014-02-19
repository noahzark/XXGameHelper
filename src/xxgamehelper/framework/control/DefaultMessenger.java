package xxgamehelper.framework.control;

import java.util.Date;

import xxgamehelper.framework.model.HelperFactory;

/***
 * A sample of Core's implement to provide basic/default functions.
 * @author LongFangzhou
 * @version 0.3
 */
public class DefaultMessenger extends Messenger {
	
	public DefaultMessenger(HelperFactory helperFactory) {
		super(helperFactory);
		// TODO Auto-generated constructor stub
	}

	public final static int VERSION = 1;
	public final static String VERSIONSTRING = "1.0";

	public void println(String str) {
		this.print(str + "\n");
	}
	
	public void print(String str) {
		System.out.print(str);
	}
	
	public void showError(String errorCause) {
		println("Error: " + errorCause
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
	}
	
	public void pauseGame(long t) throws InterruptedException{
		this.println("The game was paused for "+t+" second(s).\n");
		Thread.sleep(t*1000L);
	}
	
	public void continueGame() {
		this.gameThread.interrupt();
	}
	
	public boolean isGameAlive() {
		if (this.gameThread!=null)
			if (this.gameThread.isAlive())
				return true;
		return false;
	}
	
	public void releaseGameThread() {
		if (this.gameThread!=null) {
			this.gameThread.interrupt();
			this.gameThread.interrupt();
			this.gameThread = null;
		}
	}
}
