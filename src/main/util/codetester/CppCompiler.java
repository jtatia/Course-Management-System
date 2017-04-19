package main.util.codetester;

import main.util.sshcommands.SSHCommands;
import net.neoremind.sshxcute.exception.TaskExecFailException;
public class CppCompiler extends Languages{
		
		public String errormessage="";
		//public String s="";
		public CppCompiler(){
			super("");
		}
		/* Provide the path upto the directory in which the assignment is*/
		/* Provide the complete filename */
		public int compile(String path, String filename)throws Exception
		{
			SSHCommands sh=new SSHCommands();
			filename.trim();
			String command1 = "cd "+path;
			String command2 = "g++ -std=c++11 "+filename;
			String com[]={command1,command2};
			s = sh.runMultipleCommand(com);
			sh.close();
			if(s.startsWith("error")){
				errormessage=s.substring(6);
				return 1;
			}
			return 0;
		}
		
		/* Provide the path upto the directory in which the assignment is*/
		/* Provide the complete filename */
		/* Provide the name of the input file */
		public int execute(String path, String filename, String inputfile)throws Exception
		{
			SSHCommands sh=new SSHCommands();
			filename.trim();
			inputfile.trim();
			String command1 = "cd "+path;
			String command2 = "./a.out <inputFiles/"+inputfile+">"+"out.txt";
			String com[]={command1,command2};
			ExecuteProgram ep = new ExecuteProgram(sh,com,this);
			Thread.sleep(2000);
			sh.close();
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
		
		public int execute1(String path, String filename, String inputfile)throws Exception
		{
			SSHCommands sh=new SSHCommands();
			filename.trim();
			inputfile.trim();
			String command1 = "cd "+path;
			String command2 = "./a.out <inputFiles/"+inputfile+">"+" outputFiles/"+inputfile;
			String com[]={command1,command2};
			ExecuteProgram ep = new ExecuteProgram(sh,com,this);
			Thread.sleep(2000);
			
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
		
		public static void main(String arg[])throws Exception
		{
			CppCompiler obj = new CppCompiler();
			
			String path = "/home/stud/btech/cse/2015/kshitij.cs15/test/";
			String filename = "NumberSum.cpp";
			String inputfile = "input2.txt";
			int res = obj.compile(path,filename);
			
			System.out.println("Inside Main now :: \n\n ");
			
			if(res == 1)
			{
				System.out.println("Compile Time Error\n\n" + obj.errormessage);
				System.exit(1);
			}
			
			res = obj.execute(path,filename,inputfile);
			
			System.out.println("Inside Main now :: \n\n ");
			
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
