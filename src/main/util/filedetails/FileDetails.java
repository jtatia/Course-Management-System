package main.util.filedetails;

import main.util.sshcommands.SSHCommands;

public class FileDetails {

	/* 
	 * Returns a String[2] array with first element as size and 
	 * second element as last modified date 
	 */
	public static String[] getStats(String path, String filename)throws Exception
	{
		String ans[]=new String[2];
		SSHCommands sh = new SSHCommands();
		String result = sh.runSingleCommand("stat "+path+filename);
		
		int size_index=result.indexOf("Size: ");
		size_index+=6;
		String size =  result.substring(size_index, result.indexOf(' ', size_index));
		double s=0;
		size.trim();
		try{
			s = Double.parseDouble(size);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		s=s/1024;
		ans[0]=((Double)s).toString()+ " KB";
		
		int mod_index=result.indexOf("Change: ");
		mod_index+=8;
		System.out.println("asdfadf"+result.lastIndexOf(':', mod_index));
		System.out.println("cxvascxx"+mod_index);
		String mod= result.substring(mod_index, result.lastIndexOf(':'));
		
		mod.trim();
		ans[1]=mod;
		sh.close();		
		return ans;
	}
	
	/* Will return an array of files (and folders0 in path directory
	 * Be sure to pass valid path and not path upto a filename
	 *  */
	public static String[] getFileList(String path)throws Exception
	{
		SSHCommands sh = new SSHCommands();
		String result = sh.runSingleCommand("ls "+path);
		System.out.println("################# Result "+result);
		String ans[] = result.split("\n");
		System.out.println("################Length "+ans.length);
		System.out.println("ans : :" + ans[0]+"lauda");
		sh.close();
		return ans;
	}
}
