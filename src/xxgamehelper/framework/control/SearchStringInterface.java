package xxgamehelper.framework.control;

/***
 * Two methods for searching a string in a file 
 * @author LongFangzhou
 */
public interface SearchStringInterface {
	
	/***
	 * Search for a key in a file
	 * @param key Target key
	 * @param fileName Target file
	 * @return The whole line contains the key
	 */
	public String findString(String key, String fileName);
	
	/***
	 * Search for a key in a file
	 * @param key Target key
	 * @param fileName Target file
	 * @return All lines contain the key
	 */
	public String[] findAllString(String key, String fileName);
	
}
