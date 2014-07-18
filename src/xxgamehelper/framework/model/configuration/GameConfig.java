package xxgamehelper.framework.model.configuration;

/***
 * The class to store several game configurations.
 * @author LongFangzhou
 */
public class GameConfig {
	
	/***
	 * The string configurations of game.
	 */
	public final GameConfigDetail<String> strConfig
		= new GameConfigDetail<String>();
	
	/***
	 * The integer configurations of game.
	 */
	public final GameConfigDetail<Integer> numConfig
		= new GameConfigDetail<Integer>();
	
	/***
	 * The float configurations of game.
	 */
	public final GameConfigDetail<Float> floatConfig
		= new GameConfigDetail<Float>();

}
