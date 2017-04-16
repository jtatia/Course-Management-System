package main.util.foldermaker;

import main.util.sshcommands.SSHCommands;

public class FolderMaker {
	private String []folder_array={"assignments","materials","uploads"};
	public FolderMaker(){}
	public void createDirectoryStructure(String folder_name) throws Exception{
		SSHCommands ssh=new SSHCommands();
		String cmd[]={"cd cms/","mkdir "+folder_name,"cd "+folder_name,"mkdir assignments","mkdir uploads","mkdir materials"};
		ssh.runMultipleCommand(cmd);
		ssh.close();
	}
	
	//Need to update this	
	/*public void updateDirectoryStructure(String old_folder_name,String folder_name) throws Exception{
		SSHCommands ssh=new SSHCommands();
		ssh.runSingleCommand("cd cms/");
		ssh.runSingleCommand("mv "+old_folder_name+" "+folder_name);
		ssh.close();
	}*/
}
