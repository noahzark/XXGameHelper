package xxgamehelper.framework.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * A scanner to check a string in a file or get a string in special pattern.
 * @author LongFangzhou
 * @author 1.1
 */
public class StringScanner {
	
	/***
	 * Check if a key string is in a file(Default use UTF-8 char set to open it).
	 * @param key The key String
	 * @param fileName The name of target file
	 * @return Null if the key is not found,
	 * else return the line where the string first occurred
	 */
	public static String findString(String key, String fileName){
		return StringScanner.findString(key, fileName, "UTF-8");
	}
	
	/***
	 * Check if a key string is in a file.
	 * @param key The key String
	 * @param fileName The name of target file
	 * @param charsetName The charset used to open the file
	 * @return Null if the key is not found,
	 * else return the line where the string first occurred
	 */
	public static String findString(String key, String fileName, String charsetName) {
		try {
			InputStreamReader isr = new InputStreamReader(
					new FileInputStream(fileName), charsetName);
			BufferedReader br = new BufferedReader(isr);
			String s;
			while ((s = br.readLine())!=null){
				if (s.contains(key)){
					br.close();
					return s;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/***
	 * To get a target string with specific pattern.
	 * @param s The string to search
	 * @param front The start string
	 * @param back The end char
	 * @return The target string
	 */
	public static String sortString(String s,String front,char back){
		StringBuffer sb = new StringBuffer();
		int i;
		if (s == null)
			return null;
		for (i=0;i<s.length();i++){
			sb.append(s.charAt(i));
			if (sb.toString().contains(front))
				break;
		}
		sb = new StringBuffer();
		for (i++;i<s.length();i++){
			if (s.charAt(i)==back)
				break;
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}
}
