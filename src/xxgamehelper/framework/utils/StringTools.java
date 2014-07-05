package xxgamehelper.framework.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/***
 * A scanner to check a string in a file or get a string in special pattern.
 * @author LongFangzhou
 * @author 1.5
 */
public class StringTools {
	
	/***
	 * Check if a key string is in a file(Default use UTF-8 char set to open it).
	 * @param key The key String
	 * @param workPath The directory path end with "/"
	 * @param fileName The name of target file
	 * @return Null if the key is not found,
	 * else return the line where the string first occurred
	 */
	public static String searchForString(String key, String workPath, String fileName){
		return StringTools.searchForString(key, workPath, fileName, "UTF-8");
	}
	
	/***
	 * Get all lines which contains the key string in a file(Default use UTF-8 char set to open it).
	 * @param key The key string
	 * @param workPath The directory path end with "/"
	 * @param fileName The name of target file
	 * @param charsetName  The char set used to open the file
	 * @return A string array
	 */
	public static String[] searchAllString(String key, String workPath, String fileName) {
		return StringTools.searchAllString(key, workPath, fileName, "UTF-8");
	}
	
	/***
	 * Check if a key string is in a file.
	 * @param key The key String
	 * @param workPath The directory path end with "/"
	 * @param fileName The name of target file
	 * @param charsetName The char set used to open the file
	 * @return Null if the key is not found,
	 * else return the line where the string first occurred
	 */
	public static String searchForString(String key, String workPath, String fileName, String charsetName) {
		try {
			InputStreamReader isr = new InputStreamReader(
					new FileInputStream(workPath+fileName), charsetName);
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
	 * Get all lines which contains the key string in a file.
	 * @param key The key string
	 * @param workPath The directory path end with "/"
	 * @param filePath The name of target file
	 * @param charsetName  The char set used to open the file
	 * @return A string array
	 */
	public static String[] searchAllString(String key, String workPath, String filePath, String charsetName) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			InputStreamReader isr = new InputStreamReader(
					new FileInputStream(workPath+filePath), charsetName);
			BufferedReader br = new BufferedReader(isr);
			String s;
			while ((s = br.readLine())!=null){
				if (s.contains(key)){
					list.add(s);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] strList = new String[list.size()];
		strList = list.toArray(strList);
		//You can also use
		//String[] strList = (String[])list.toArray(new String[0]);
		//but it will cost additional time and space to new String[0]
		return strList;
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
	
	/***
	 * Count the numbers of key contained in source string.
	 * @param key The key to search
	 * @param source The source string
	 * @return The number
	 */
	public static int countString(String key, String source) {
		int n = 0;
		while (source.contains(key)) {
			source = source.replaceFirst(key, "*");
			n++;
		}
		return n;
	}
	
	/***
	 * Split a fraction
	 * @param fractionStr The target String
	 * @return An int array with two numbers in it.
	 */
	public static int[] splitFraction(String fractionStr) {
		String[] infos = fractionStr.split("/");
		return new int[]{Integer.parseInt(infos[0]), Integer.parseInt(infos[1])};
	}
	
	/***
	 * Count a key appears how many times in a string.
	 * @param targetStr Target string
	 * @param keyStr The key string
	 * @return The amount of key string
	 */
	public static int countSubstring(String targetStr, String keyStr) {
		return targetStr.split(keyStr).length - 1 ;
	}
	
	/***
	 * Tells if a String is null or empty
	 * @param keyString The target string to analyze
	 * @return True if the string is null or empty
	 */
	public static boolean isNullOrEmpty(String keyString) {
		if (keyString!=null)
			if (!keyString.equals(""))
				return false;
		return true;
	}
	
	/***
	 * Force to parse a string to integer 
	 * @param numberStr The target string
	 * @return the integer or minimal value of integer.
	 */
	public static int parseIntWithoutCheck(String numberStr) {
		try {
			return Integer.parseInt(numberStr);
		} catch (NumberFormatException e) {
			return Integer.MIN_VALUE;
		}
	}
}
