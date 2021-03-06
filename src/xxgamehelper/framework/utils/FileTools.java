package xxgamehelper.framework.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.Timer;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import xxgamehelper.framework.control.OutputInterface;
import xxgamehelper.framework.model.core.Core;

/***
 * A class to analyze download process.
 * @author LongFangzhou
 */
class DownloadCore {
	
	OutputInterface out;
	HttpResponse rsp;
	String fileAddress;
	
	private long currentProgress = 0;
	private long lastSecondProgress = 0;
	private long fileLength = 0;
	private long startTime = 0;
	
	DownloadCore(OutputInterface out, HttpResponse rsp, String fileAddress) {
		this.out = out;
		this.rsp = rsp;
		this.fileAddress = fileAddress;
	}
	
	/***
	 * Execute the download progress.
	 * @param quiet Don't show download process statics
	 * @return Downloaded file length
	 * @throws IOException 
	 */
	long executeDownload(boolean showProgress) throws IOException {
		startTime = TimeTools.getCurrentTime();
		HttpEntity entity = rsp.getEntity();
		fileLength = entity.getContentLength();
		
		Timer t = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				long timeCost = TimeTools.getCurrentTime() - startTime;
				long lastSecondSpeed = currentProgress-lastSecondProgress;
				lastSecondProgress = currentProgress;
				if (fileLength > 0) {
					out.print((currentProgress*100/fileLength)+"% - ");
					out.print("Avg." + (currentProgress*1000/timeCost/1024) + " KB/S - ");
					out.print("Lst." + (lastSecondSpeed/1024) + " KB/S - ");
					if (lastSecondSpeed==0)
						lastSecondSpeed = 1;
					out.println((fileLength-currentProgress)/lastSecondSpeed + "S left.");
				}
			}
		});
		if (showProgress)
			t.start();
		
		try {
			if (entity != null) {
				Header[] coding = rsp.getHeaders("Content-Encoding");
				InputStream is = null;
				if (coding.length>0)
					is = new GZIPInputStream(entity.getContent());
				else
					is = entity.getContent();
				FileOutputStream fos = new FileOutputStream(new File(fileAddress));
				byte[] b = new byte[Core.BUFFERSIZE];
				int len = 0;
				while((len=is.read(b))!=-1) {
					fos.write(b,0,len);
					currentProgress += len;
				}
				fos.close();
			}
		} catch (IOException e) {
			out.showError(e);
			return -1;
		} finally {
			if (showProgress) {
				t.stop();
				out.print("100% - ");
				out.println(currentProgress*1000/(TimeTools.getCurrentTime()-startTime)/1024 + " KB/S");
			}
			EntityUtils.consume(entity);
		}
		if (currentProgress != fileLength && fileLength > 0)
			out.showWarning("File length is different: Recv."+ currentProgress+" Expc:"+fileLength);
		return currentProgress;
	}
}

/***
 * Some tools to operate on files.
 * @author LongFangzhou
 * @version 0.3
 */
public class FileTools {
	
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
	public static boolean saveRspToFile(OutputInterface out, HttpResponse rsp,
			String fileName, boolean showProgress) throws IOException{
		if (rsp.getStatusLine().getStatusCode() == 302) {
			Header[] header = rsp.getHeaders("Location");
			if (header.length>0) {
				FileOutputStream fos = new FileOutputStream(new File(fileName));
				fos.write(header[0].getValue().getBytes());
				HttpEntity entity = rsp.getEntity();
				EntityUtils.consume(entity);
				fos.close();
			}
		} else {
			DownloadCore core = new DownloadCore(out, rsp, fileName);
			core.executeDownload(showProgress);
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
	
	/***
	 * Move a file or a directory to a new place.
	 * @param from The original address
	 * @param to The target address
	 * @param deleteOld Delete the old files or not
	 */
	public static void moveFile(String from, String to, boolean deleteOld){ 
		File dir = new File(from);
		if (dir.isFile()) {
			dir.renameTo(new File(to));
			if (deleteOld) {
				dir.delete();
			}
			return;
		}
		File[] files = dir.listFiles(); //List all files
		if (files == null)
			return; 
		File moveDir = new File(to); //Target
		if (!moveDir.exists()) { 
			moveDir.mkdirs(); 
		}
		//Move files
		for (int i = 0; i < files.length; i++) { 
			if (files[i].isDirectory()) { 
				moveFile(files[i].getPath(), to + "/" + files[i].getName(), deleteOld); 
				if (deleteOld)
					files[i].delete();
			} 
			File moveFile = new File(moveDir.getPath() + "/" + files[i].getName());
			//If there is a file with same name, delete it.
			if (moveFile.exists()&&moveFile.isFile()) {
				moveFile.delete(); 
			}
			files[i].renameTo(moveFile);
		}
		if (deleteOld)
			dir.delete();
	}
	
	/***
	 * Delete a directory.
	 * @param dir The target directory
	 * @return True if all delete operations succeeded.
	 */
	public static boolean deleteFile(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //Delete son directories
            for (int i=0; i<children.length; i++) {
                boolean success = deleteFile(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        //Delete the empty directory
        return dir.delete();
    }
	
	/***
	 * Unzip to specific path
	 * @param zipPath Zip file path
	 * @param descDir Target file path
	 * @throws IOException
	 */
	public static void unZipFiles(String zipPath,String descDir)throws IOException {
		unZipFiles(new File(zipPath), descDir);
	}
	
	/***
	 * Unzip to specific path
	 * @param zipFile Zip file path
	 * @param descDir Target file path
	 * @throws IOException
	 */
	public static void unZipFiles(File zipFile,String descDir) throws IOException{
		File pathFile = new File(descDir);
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		for(Enumeration<?> entries = zip.entries();
				entries.hasMoreElements();){
			ZipEntry entry = (ZipEntry)entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir+zipEntryName).replaceAll("\\*", "/");;
			//Create if the file path is not exist
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if(!file.exists()){
				file.mkdirs();
			}
			//Don't unzip if it's a directory
			if(new File(outPath).isDirectory()){
				continue;
			}
			
			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[8192];
			int len;
			while((len=in.read(buf1))>0){
				out.write(buf1,0,len);
			}
			in.close();
			out.close();
		}
		zip.close();
	}
	
	/***
	 * Unpack an rar file
	 * @param rarFilePath
	 * @param exportPath
	 * @param rarFileName
	 * @param password
	 * @return 0 If succeed
	 */
	public static int unRarFiles(String rarFilePath, String exportPath,
			String rarFileName,String password) {   
		try {   
			Runtime run = Runtime.getRuntime();
			String cmd = "unrar x " + "-p"+ password
					+ " \"" + rarFilePath + "/" + rarFileName + "\""
					+ " \"" + exportPath + "\"";
			System.out.println(cmd);
			Process p = run.exec(cmd);
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String lineStr = null;
			while ((lineStr = inBr.readLine()) != null) {
				System.out.println(lineStr);
				if (lineStr.contains("没有可提取的文件"))
					return 1;
			}
			//Check execution
			if (p.waitFor() != 0) {
				// p.exitValue()==0 exit normally
				if (p.exitValue() == 1)
					return 1;
				}
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;//success
	}

}
