package main.util.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Download {
	
	String ip;
	String username;
	String password;
	int port=22;
	
	public Download()
	{
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream("Files//SSHinfo.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ip=prop.getProperty("ip");
		username=prop.getProperty("username");
		password=prop.getProperty("password");
	}
	
	
	/*
	 * This is a generic function to download file from "from " on ssh to 
	 * "to" on the local system.
	 * Make sure that you specify complete path upto the filename in both the 
	 * strings.
	 * ALSO MAKE SURE THAT PATH IS SPECIFIED USING '/' AS SEPARATOR 
	 */
	public void downloadFile(String from, String to)
	{
		Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        
        String directory=from.substring(0, from.lastIndexOf('/'));
        String filename=from.substring(from.lastIndexOf('/'+1));
        
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, ip, port);
            session.setPassword(password);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            
            channelSftp.cd(directory);
            byte[] buffer = new byte[1024];
            BufferedInputStream bis = new BufferedInputStream(channelSftp.get(filename));
            File newFile = new File(to);
            OutputStream os = new FileOutputStream(newFile);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            int readCount;
            while ((readCount = bis.read(buffer)) > 0) {
                System.out.println("Writing: ");
                bos.write(buffer, 0, readCount);
            }
            bis.close();
            bos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

	}

}
