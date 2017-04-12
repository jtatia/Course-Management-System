package main.util.sshcommands;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.IOptionName;
import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;
import net.neoremind.sshxcute.task.impl.ExecShellScript;

public class SSHCommands {
	
	private static ConnBean conn=null;
	private static SSHExec ssh=null;
	
	public SSHCommands()
	{
		// Steps To Do
		// 1. create instance of ConnBean using ---> ConnBean conn=new ConnBean(ip,username,password) 
		// 2. Put the conn object to the static method getInstance() ---> SSHExec ssh=SSHExec.getInstance(conn)
		// 3. Connect to ssh server using ---> ssh.connect()
		// 4. Execute commands
		// 5. ssh.disconnect()
		
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream("Files//SSHinfo.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ip=prop.getProperty("ip");
		String user=prop.getProperty("username");
		String password=prop.getProperty("password");
		conn = new ConnBean(ip,user,password);
		ssh=SSHExec.getInstance(conn);
		ssh.connect();
		SSHExec.setOption(IOptionName.INTEVAL_TIME_BETWEEN_TASKS, 0l);
	}
	
	public static void main(String args[]) throws Exception
	{
		SSHCommands obj=new SSHCommands();
		String cmnds[]={"ls","cd testdir","ls"};
		obj.runMultipleCommand(cmnds);
		obj.close();
	}
	
	/*Will return "error" if error occurs*/
	public static String runMultipleCommand(String cmd[]) throws TaskExecFailException
	{	
		CustomTask task = new ExecCommand(cmd);
		Result res = ssh.exec(task);
		if (res.isSuccess) 
		{
			System.out.println("Return code: " + res.rc);
			System.out.println("sysout: " + res.sysout);
		}
		else
		{
			System.out.println("Return code: " + res.rc);
			System.out.println("error message: " + res.error_msg);
			return "error";
		}
		return res.sysout;
	}
	
	/*Will return "error" if error occurs*/
	public static String runSingleCommand(String cmd) throws TaskExecFailException
	{	
		CustomTask task = new ExecCommand(cmd);
		Result res = ssh.exec(task);
		if (res.isSuccess) 
		{
			System.out.println("Return code: " + res.rc);
			System.out.println("sysout: " + res.sysout);
		}
		else
		{
			System.out.println("Return code: " + res.rc);
			System.out.println("error message: " + res.error_msg);
			return "error";
		}
		return res.sysout;
	}
	
	public void close()
	{
		ssh.disconnect();
	}
}