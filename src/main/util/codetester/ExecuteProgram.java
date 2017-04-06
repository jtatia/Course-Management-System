package main.util.codetester;

import main.util.sshcommands.SSHCommands;
import net.neoremind.sshxcute.exception.TaskExecFailException;

public class ExecuteProgram implements Runnable{
	SSHCommands sh;
	String cmd[];
	Thread t;
	Languages ln;
	public ExecuteProgram(SSHCommands sh, String cmd[],Languages lang){
		this.sh=sh;
		this.cmd=cmd;
		ln=lang;
		 t = new Thread(this,"Execute Program");
		t.start();
	}
	
	public void run(){
		try {
		ln.s=sh.runMultipleCommand(cmd);
		} catch (TaskExecFailException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally{
			sh.close();
		}
	}
}
