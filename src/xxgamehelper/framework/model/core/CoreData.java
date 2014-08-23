package xxgamehelper.framework.model.core;

import java.util.Random;

import org.apache.http.HttpHost;

import xxgamehelper.framework.control.OutputInterface;
import xxgamehelper.framework.control.messenger.Messenger;
import xxgamehelper.framework.model.client.WebClient;

/***
 * The core class attributes.
 * @author LongFangzhou
 */
public abstract class CoreData {
	
	public CoreData(Messenger messenger) {
		this.messenger = messenger;
		this.out = messenger;
		this.webclient = messenger.getWebClient();
		this.exitFlag = false;
		this.basicRestTime = 20;
		this.extraRestTime = 20;
	}
	
	/***
	 * The messenger to carry data.
	 */
	protected Messenger messenger;
	
	/***
	 * The output interface to output data.
	 */
	protected OutputInterface out;

	/***
	 * The web client to transfer data.
	 */
	protected WebClient webclient;
	
	/***
	 * The host target.
	 */
	protected HttpHost server;
	
	/***
	 * Set the server with default http protocol and 80 port.
	 * @param address
	 */
	public void setServer(String address) {
		this.server = new HttpHost(address);
	}

	/***
	 * Set the server which will login to.
	 * @param protocol HTTPS/HTTP
	 * @param address Server address
	 * @param port Server port
	 */
	public void setServer(String protocol, String address, int port) {
		this.server = new HttpHost(address, port, protocol);
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
