package main.util.codetester;

import java.io.IOException;

import main.util.sshcommands.SSHCommands;

public class PythonCompiler extends Languages {
	public String errormessage="";
	//public String s="";
	public PythonCompiler(){
		super("");
	}
	public int execute(String path,String filename,String inputFile)throws Exception{
		SSHCommands sh=new SSHCommands();
		path.trim();
		filename.trim();
		inputFile.trim();
		String command1="cd "+path;
		String command2="python "+filename+" <"+inputFile+">"+" out.txt";//If you need to change name of output file please change from here
		String cmd[]={command1,command2};
		ExecuteProgram ep=new ExecuteProgram(sh,cmd,this);
		Thread.sleep(10000);
		
		if(!ep.t.getState().equals(Thread.State.TERMINATED))
		{
			ep.t.stop();
			return 2;
		}
		if(s.startsWith("error")){
			errormessage=s.substring(6);
			return 1;
		}
		return 0;
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		PythonCompiler obj = new PythonCompiler();
		
		String path = "/home/stud/btech/cse/2015/kshitij.cs15/test/";
		String filename = "testing.py";
		String inputfile = "input2.txt";
		int res = obj.execute(path,filename,inputfile);
		
		System.out.println("Inside Main now :: \n\n ");
		
		/*if(res == 1)
		{
			System.out.println("Compile Time Error\n\n" + obj.errormessage);
			System.exit(1);
		}*/
		
		if(res == 1)
		{
			System.out.println("Run Time Error\n\n" + obj.errormessage);
			System.exit(1);
		}
		if (res == 2)
		{
			obj.errormessage="Timed Out";
			System.out.println("TimeOut Error\n\n" + obj.errormessage);
			System.exit(1);
		}
		System.out.println("Successful");
	}
	
}
