package main.util.filedetails;

import main.util.sshcommands.SSHComm;
import main.util.sshcommands.SSHCommands;

public class FileDetails {

	/* 
	 * Returns a String[2] array with first element as size and 
	 * second element as last modified date 
	 */
	
	private static SSHComm sshc=null;
	
	synchronized public static String[] getStats(String path, String filename)throws Exception
	{
		String ans[]=new String[2];
		sshc=new SSHComm(); 
		/*
		 * The comments below can be used to revert back to the previous code. 
		 * SSHCommands sh = new SSHCommands();
		 * String result = sh.runSingleCommand("stat "+path+filename);
		*/
		String result = sshc.SSHClient("stat "+path+filename, sshc); 
		int size_index=result.indexOf("Size: ");
		size_index+=6;
		System.out.println(path+filename);
		System.out.println(result);
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
		sshc.close();		
		return ans;
	}
	
	/* Will return an array of files (and folders0 in path directory
	 * Be sure to pass valid path and not path upto a filename
	 *  */
	synchronized public static String[] getFileList(String path)throws Exception
	{	
		/*
		 * The comments below can be used to revert back to the previous code.
		SSHCommands sh = new SSHCommands();
		String result = sh.runSingleCommand("ls "+path);
		*/
		sshc=new SSHComm();
		String result=sshc.SSHClient("ls "+path, sshc);
		System.out.println("################# Result "+result);
		String arr[] = result.split("\n");
		String ans[]=new String[arr.length-1];
		for(int i=0;i<arr.length-1;i++)
			ans[i]=arr[i];
		System.out.println("################Length "+ans.length);
		//System.out.println("ans : :" + ans[0]+"lauda");
		sshc.close();
		return ans;
	}
}
