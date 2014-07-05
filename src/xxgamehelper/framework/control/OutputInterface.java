package xxgamehelper.framework.control;

/***
 * The messenger class should provide information and error output methods.
 * @author LongFangzhou
 */
public interface OutputInterface {
	
	/***
	 * Output a whole line.
	 * @param str The content
	 */
	void println(String str);
	
	/***
	 * Output a string.
	 * @param str The content
	 */
	void print(String str);
	
	/***
	 * Output a warning message.
	 * @param str The content
	 */
	void showWarning(String content);
	
	/***
	 * Output an error message.
	 * @param errorSummary The summary of error
	 */
	void showError(String errorSummary);
	
	/***
	 * Output an error message.
	 * @param errorSummary The summary of error
	 * @param errorCause The cause of error
	 */
	void showError(String errorSummary, String errorCause);
	
	/***
	 * Output an error exception.
	 * @param e The exception
	 */
	void showError(Exception e);
	
}
