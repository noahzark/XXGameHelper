package xxgamehelper.framework.model.configuration;

/***
 * The class to store helper mode configurations.
 * @author LongFangzhou
 */
public final class WorkConfig {
	
	public final String workFolder;
	public final boolean isDebugMode;
	public final boolean isBetaMode;
	
	public WorkConfig(String workFolder, boolean isDebugMode, boolean isBetaMode) {
		super();
		this.workFolder = workFolder;
		this.isDebugMode = isDebugMode;
		this.isBetaMode = isBetaMode;
	}
	
	public String showConfig() {
		return "Work directory: " + workFolder
				+ "Debug Mode: " + isDebugMode
				+ "\tBeta Mode: " + isBetaMode;
	}
	
}
