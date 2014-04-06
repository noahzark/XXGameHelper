package xxgamehelper.framework.model;

import java.util.Random;

import org.apache.http.HttpHost;

import xxgamehelper.framework.control.Messenger;

/***
 * The core class attributes.
 * @author LongFangzhou
 */
public abstract class CoreData {
	
	public CoreData(Messenger messenger) {
		this.messenger = messenger;
		this.webclient = messenger.getWebClient();
		this.exitFlag = false;
		this.basicRestTime = 20;
		this.extraRestTime = 20;
	}
	
	/***
	 * The messenger to output data.
	 */
	protected Messenger messenger;

	/***
	 * The web client to transfer data.
	 */
	protected WebClient webclient;
	
	/***
	 * The host target.
	 */
	protected HttpHost host;

	public void setHost(HttpHost host) {
		this.host = host;
	}
	
	/**
	 * Get whether if the game thread is about to exit.
	 * @return the exitFlag
	 */
	public boolean isExitFlag() {
		return exitFlag;
	}

	/**
	 * @param exitFlag The exitFlag to set
	 */
	public void setExitFlag(boolean exitFlag) {
		this.exitFlag = exitFlag;
	}

	/***
	 * Tell whether the thread should exit
	 */
	private boolean exitFlag;
	
	/***
	 * A randomizer to generate random numbers
	 */
	protected Random randomer;
	
	/***
	 * Rest time = Basic rest time + 1 ~ Extra rest time
	 */
	protected int basicRestTime, extraRestTime;
	
}
