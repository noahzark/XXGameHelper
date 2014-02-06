package xxgamehelper.framework.control;

import java.util.Date;

/***
 * All models should use this messenger to operate on views.
 * @author LongFangzhou
 * @version 0.1
 */
public abstract class Messenger extends MessengerData implements MessengerInterface{
	public final static int VERSION = 1;
	public final static String VERSIONSTRING = "1.0";

	public void println(String str){
		this.print(str + "\n");
	}
	
	public void print(String str){
		System.out.print(str);
	}
	
	public void showError(String errorCause){
		println("Error: " + errorCause
				+ ", please try again later or contact with the author");
	}
	
	public void showError(Exception e){
		if (this.isDebugMode()){
			println(new Date()+"");
			e.printStackTrace();
		}
		this.showError(e.toString());
	}

	public void showWarning(String content) {
		println("Warning: " + content
				+ ", please try again later or contact with the author");
	}
}
