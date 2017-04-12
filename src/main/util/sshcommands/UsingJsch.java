package main.util.sshcommands;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UserInfo;


public class UsingJsch {

	private static ChannelSftp sftpChannel;
    private static Session session;
	
	public static void main(String[] args) {
        // TODO Auto-generated method stub
        JSch jsch = new JSch();
        session = null;
        try {
            session = jsch.getSession("kshitij.cs15", "172.16.1.3", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("7301997");
            session.connect();
            System.out.println("Connected to session successfully");
            Channel channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("Connected to Channel successfully");
            sftpChannel = (ChannelSftp) channel;

            String currentDirectory="";
			try {
				currentDirectory = sftpChannel.pwd();
			} catch (SftpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		System.out.println(currentDirectory);
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();  
        }
    }
	public void writingFile(String text) throws SftpException
	{
		InputStream is=new ByteArrayInputStream(text.getBytes());
		String currentDirectory=sftpChannel.pwd();
		System.out.println(currentDirectory);
		sftpChannel.cd("testdir");
		currentDirectory=sftpChannel.pwd();
		System.out.println(currentDirectory);
	//	sftpChannel.mkdir("newDir");
	//	sftpChannel.cd("newDir");
		sftpChannel.put(is,"inputqwertyomomom.txt");
	}

	public String readingFile(String remoteFile)
	{
		String str="";
		    try
		    {
		    InputStream out= null;
		    out= sftpChannel.get(remoteFile);
		    BufferedReader br = new BufferedReader(new InputStreamReader(out));
		    String line;
		    while ((line = br.readLine()) != null) 
		    {
		        	str=str+line;
		    }
		    br.close();
		        sftpChannel.disconnect();
		        session.disconnect();
		    }
		    catch(SftpException | IOException e)
		    {
		    System.out.println(e);
		    }
		    return str;
		}	
	
	
	public UsingJsch()
	{
		JSch jsch = new JSch();
        session = null;
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
        try {
            session = jsch.getSession(user, ip, 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();
            System.out.println("Connected to session successfully");
            Channel channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("Connected to Channel successfully");
            sftpChannel = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();  
        }
	}
	public void close()
	{
		sftpChannel.exit();
        session.disconnect();
	}
}
