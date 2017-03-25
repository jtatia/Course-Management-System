package main.util.sshcommands;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.StreamGobbler;

public class SSHComm {
	private static Connection conn=null;
	private static ch.ethz.ssh2.Session sess=null;
	public SSHComm() throws IOException
	{
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream("Files//SSHinfo.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String serverIp=prop.getProperty("ip");
		String usernameString=prop.getProperty("username");
		String password=prop.getProperty("password");
		conn = new Connection(serverIp);
		try {
			conn.connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean isAuthenticated = conn.authenticateWithPassword(usernameString, password);
        if (isAuthenticated == false)
            throw new IOException("Authentication failed.");
        sess = conn.openSession();
	}
	public static void main(String args[])throws IOException, InterruptedException
	{
		SSHComm obj=new SSHComm();
//		obj.SSHClient("ls");
		obj.SSHClient("stat /home/stud/btech/cse/2015/kshitij.cs15/cms/MA201_ashish_prashant/assignments/HW1.pdf",obj);
//		obj.SSHClient("ls");
//		Thread.sleep(5000);
		System.out.println("#######################################");
	//	obj=new SSHComm("172.16.1.3","kshitij.cs15","7301997");
		obj.SSHClient("stat /home/stud/btech/cse/2015/kshitij.cs15/cms/MA201_ashish_prashant/assignments/HW1.pdf",obj);
		obj.close(); 
	}
	public static String SSHClient(String command,SSHComm obj) throws IOException{
        System.out.println("inside the ssh function");
        String res="";
        try
        {               
            sess.execCommand(command);  
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            System.out.println("the output of the command is");
           
            while (true)
            {
                String line = br.readLine();
                res=res+line+"\n";
                if (line == null)
                    break;
                System.out.println(line);
            }
            System.out.println("ExitCode: " + sess.getExitStatus());
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);

        }
        obj=new SSHComm();
        return res;
    }
	public static void close()
	{
		sess.close();
		conn.close();
	}
}
