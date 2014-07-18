package xxgamehelper.framework.model.configuration;

/***
 * The class to store helper configurations.
 * @author LongFangzhou
 */
public final class HelperConfig {
	/***
	 * The configurations is designed for which helper.
	 */
	public final String helperName;
	/***
	 * The name of this configuration.
	 */
	public final String configName;
	/***
	 * The author of this configuration.
	 */
	public final String author;
	
	/***
	 * Helper mode configurations.
	 */
	public ModeConfig mode;
	/***
	 * Game configurations.
	 */
	public GameConfig game = new GameConfig();
	
	public HelperConfig(String helperName, String configName, String author) {
		this.helperName = helperName;
		this.configName = configName;
		this.author = author;
	}
	
	public String toString(){
		return "A " + configName
				+ " configuration designed by " + author
				+ " for " + helperName
				+ ".";
	}
}
