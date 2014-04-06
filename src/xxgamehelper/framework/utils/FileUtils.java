package xxgamehelper.framework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import xxgamehelper.framework.model.core.Core;

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
	
	/***
	 * The method to save a response to the file
	 * @param rsp The http response
	 * @param fileName The target file
	 * @throws IOException 
	 */
	public static boolean saveRspToFile(HttpResponse rsp, String fileName) throws IOException{
		if (rsp.getStatusLine().getStatusCode() == 302) {
			Header[] header = rsp.getHeaders("Location");
			if (header.length>0) {
				FileOutputStream fos = new FileOutputStream(new File(fileName));
				fos.write(header[0].getValue().getBytes());
				HttpEntity entity = rsp.getEntity();
				EntityUtils.consume(entity);
			}
		} else {
			HttpEntity entity = rsp.getEntity();
			if (entity != null) {
				Header[] coding = rsp.getHeaders("Content-Encoding");
				InputStream is = null;
				if (coding.length>0)
					is = new GZIPInputStream(entity.getContent());
				else
					is = entity.getContent();
				FileOutputStream fos = new FileOutputStream(new File(fileName));
				byte[] b = new byte[Core.BUFFERSIZE];
				int len = 0;
				while((len=is.read(b))!=-1)
					fos.write(b,0,len);
				fos.close();
			}
		}
		return true;
	}
	
	/***
	 * Check if a path is exists, if not will create it.
	 * @param path The target directory path
	 * @return True if the directory created succeed, false if the directory is already existed.
	 */
	public static boolean createDirectory(String path) {
		File dir = new File(path);
		if (dir.exists())
			return false;
		dir.mkdirs();
		return true;
	}

}
