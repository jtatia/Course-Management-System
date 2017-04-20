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

import main.student.studentdao.StudentDAO;


public class UsingJsch {

	public static ChannelSftp sftpChannel;
    public static Session session;
	public static Channel channel;
	/*public static void main(String[] args) {
        // TODO Auto-generated method stub
        JSch jsch = new JSch();
        session = null;
        try {
            session = jsch.getSession("kshitij.cs15", "172.16.26.9", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("kshitij#456");
            session.connect();
            System.out.println("Connected to session successfully");
            channel = session.openChannel("sftp");
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
    	/*	try {
				writingFile("cms/CS225_jimson/","hello this is demo input to be written","demo.txt");
			} catch (SftpException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
         //   sftpChannel.exit();
           // session.disconnect();
       // } catch (JSchException e) {
        //    e.printStackTrace();  
      //  }
    //}*/
	public static void writingFile(String dir,String text,String file,int mode) throws SftpException, IOException
	{
		//this function has not been used but can be used to specify write mode like ChannelSftp.APPEND,ChannelSftp.RESUME,ChannelSftp.OVERWRITE
		JSch jsch = new JSch();
        session = null;
        Properties prop=new Properties();
		try {
			prop.load(UsingJsch.class.getResourceAsStream("/SSHinfo.properties"));
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
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("Connected to Channel successfully");
            sftpChannel = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();  
        }
	  System.out.println("Within writingFiles >>>>>>>>dir,text,file"+dir+",,,,,"+text+",,,,,"+file);
	  sftpChannel.cd(dir);
      InputStream obj_InputStream = new ByteArrayInputStream(text.getBytes());
      sftpChannel.put(obj_InputStream,file,mode);
      System.out.println(text);
      System.out.println(dir+file);
      System.out.println(sftpChannel.pwd());
      sftpChannel.exit();
      obj_InputStream.close();
      channel.disconnect();
      session.disconnect();
	}

	public static void writingFile(String dir,String text,String file) throws SftpException, IOException
	{
		/*
		InputStream is=new ByteArrayInputStream(text.getBytes());
		String currentDirectory=sftpChannel.pwd();
		System.out.println(currentDirectory);
		sftpChannel.cd("");
		currentDirectory=sftpChannel.pwd();
		System.out.println(currentDirectory);
	//	sftpChannel.mkdir("newDir");
	//	sftpChannel.cd("newDir");
		sftpChannel.put(is,"inputqwertyomomom.txt");*/
		JSch jsch = new JSch();
        session = null;
        Properties prop=new Properties();
		try {
			prop.load(UsingJsch.class.getResourceAsStream("/SSHinfo.properties"));
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
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("Connected to Channel successfully");
            sftpChannel = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();  
        }
	  System.out.println("Within writingFiles >>>>>>>>dir,text,file"+dir+",,,,,"+text+",,,,,"+file);
	  System.out.println(sftpChannel.pwd());
	  sftpChannel.cd(dir);
      InputStream obj_InputStream = new ByteArrayInputStream(text.getBytes());
      sftpChannel.put(obj_InputStream,file);
      System.out.println(text);
      System.out.println(dir+file);
      System.out.println(sftpChannel.pwd());
      sftpChannel.exit();
      obj_InputStream.close();
      channel.disconnect();
      session.disconnect();
	}

	public static String readingFile(String remoteFile)
	{
		JSch jsch = new JSch();
        session = null;
        Properties prop=new Properties();
		try {
			prop.load(UsingJsch.class.getResourceAsStream("/SSHinfo.properties"));
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
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("Connected to Channel successfully");
            sftpChannel = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();  
        }
		String str="";
		    try
		    {
		    InputStream out= null;
		    out= sftpChannel.get(remoteFile);
		    BufferedReader br = new BufferedReader(new InputStreamReader(out));
		    String line;
		    while ((line = br.readLine()) != null) 
		    {
		        	str=str+line+"\n";
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
	public static void close()
	{
		sftpChannel.exit();
        session.disconnect();
	}
}
