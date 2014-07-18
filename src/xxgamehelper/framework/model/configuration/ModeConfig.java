package xxgamehelper.framework.model.configuration;

/***
 * The class to store helper mode configurations.
 * @author LongFangzhou
 */
public final class ModeConfig {
	
	public final boolean isDebugMode;
	public final boolean isBetaMode;
	
	public ModeConfig(boolean isDebugMode, boolean isBetaMode) {
		super();
		this.isDebugMode = isDebugMode;
		this.isBetaMode = isBetaMode;
	}
	
}
