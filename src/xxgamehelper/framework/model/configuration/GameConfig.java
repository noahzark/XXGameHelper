package xxgamehelper.framework.model.configuration;

/***
 * The class to store several game configurations.
 * @author LongFangzhou
 */
public class GameConfig {
	
	/***
	 * The string configurations of game.
	 */
	public final GameConfigType<String> strConfig
		= new GameConfigType<String>();
	
	/***
	 * The integer configurations of game.
	 */
	public final GameConfigType<Integer> numConfig
		= new GameConfigType<Integer>();
	
	/***
	 * The float configurations of game.
	 */
	public final GameConfigType<Float> floatConfig
		= new GameConfigType<Float>();

}
