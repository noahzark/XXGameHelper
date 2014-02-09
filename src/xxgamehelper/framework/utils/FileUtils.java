package xxgamehelper.framework.utils;

import java.io.File;
import java.io.FilenameFilter;

/***
 * Some tools to operate on files.
 * @author LongFangzhou
 * @version 0.1
 */
public class FileUtils {
	
	/***
	 * A method to delete specific types of files in a directory.
	 * @param dirPath The directory path
	 * @param fileTypes The file types to be deleted
	 * @return True if the operation is succeeded, otherwise false.
	 */
	public static boolean deleteFiles(String dirPath,String[] fileTypes) {
		File f = new File(dirPath);
		if (!f.isDirectory() || fileTypes.length<=0)
			return false;
		try {
			for (String fileType : fileTypes){
				final String tempStr = fileType;
				FilenameFilter ff = new FilenameFilter(){
					public boolean accept(File arg0, String filename) {
						if (filename.toLowerCase().endsWith(tempStr))
							return true;
						else
							return false;        
					}
				};
				File[] files = f.listFiles(ff);
				for (int i=0;i<files.length;i++)
					files[i].delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
