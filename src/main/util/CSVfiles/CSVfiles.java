package main.util.CSVfiles;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Properties;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import main.student.studentdao.StudentDAO;
import main.util.assignmentutils.assignment.Assignment;
import main.util.filedetails.FileDetails;
import main.util.sshcommands.UsingJsch;

public class CSVfiles {

	static String marksContent ="";
	static String ip;
	static String username;
	static String password;
	static int port=22;

	public static ArrayList<Assignment> ReadMarksFile(String path) throws Exception{
		int x =0;
		String str[] = FileDetails.getFileList(path);
		ArrayList<Assignment> list = new ArrayList<Assignment>();
		ArrayList<CSVMarks> csvlist = new ArrayList<CSVMarks>();
		for(int i=0;i<str.length;i++)
		{
			if(str[i].equals("finalmarks.csv"))
				x=1;
			Assignment temp = new Assignment();
			if((str[i].contains(".java")||str[i].contains(".py")||str[i].contains(".c")||str[i].contains(".cpp")))
			{
				System.out.println("GETTING FILES LIST BEING ADDED");
				System.out.println(str[i]);
				temp.setName(str[i]);
				temp.setPath(path);
				String s[]=FileDetails.getStats(path,temp.getName());
				temp.setLastModified(s[1]);
				temp.setSize(s[0]);
				temp.setMarks(0);
				temp.setStatus("Not tested");
				list.add(temp);
			}
			//READING MARKS, ERROR FROM CSV FILE
		}	
		
		if(x==0)
		return list;
		
		Properties prop=new Properties();
		try {
			prop.load(CSVfiles.class.getResourceAsStream("/SSHinfo.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ip=prop.getProperty("ip");
		username=prop.getProperty("username");
		password=prop.getProperty("password");
		Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        try{
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
        }catch(Exception e){
        	e.printStackTrace();
        }
       InputStream stream=channelSftp.get(path+"finalmarks.csv");

//		BufferedReader reader1=new BufferedReader(new InputStreamReader(stream));
      //  stream=channelSftp.get(path_check);
	//	BufferedReader reader2=new BufferedReader(new InputStreamReader(stream));
//		BufferedReader br = null;
	//	FileReader fr = null;
		//fr = new FileReader(path+"\\finalmarks.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));	
		CsvReader read = new CsvReader(br);
		read.readHeaders();
		int count = 0;
	//System.out.println("TRYING TO REAAAsdss");
		while(read.readRecord()){
		//	System.out.println("HEREEEEEEEEE");
			String filename = read.get("FileName");
			String marks = read.get("Marks");
			String status = read.get("Status");
	//		System.out.println("TRYING TO READ");
		//	System.out.println("filename:"+filename+"  marks:"+marks+"   status:"+status);
			CSVMarks obj = new CSVMarks(filename,marks,status);		
			csvlist.add(obj);
			count++;
	}
		System.out.println("outside");
		read.close();
		for(int i=0;i<count;i++){
			for(int j=0;j<list.size();j++){
	//			System.out.println("L:"+list.get(i).getName()+"\nC:"+csvlist.get(j).getFilename());
				if((list.get(j).getName()).equals(csvlist.get(i).getFilename()))
						{
					list.get(j).setMarks(Integer.parseInt(csvlist.get(i).getMarks()));
					list.get(j).setStatus(csvlist.get(i).getStatus());
					break;
				}
			}
			System.out.println("I=="+i);
		}
		System.out.println("EXITTTTTTTTTTTTTTTTTTTTTTTTT");
		return list;
	}
	
	public static void WriteMarksFile(String path,String file,int marks,String status) throws Exception{
   System.out.println("@@@@@@@@@@@@@@@@@@@@@@@\n!!!!!!!!!!!!!!!!!!\n###################");
   System.out.println("PATH="+path+"\nFILENAME="+file+"\nMARKS="+marks+"\nSTATUS="+status);
	String outputFile = "finalmarks.csv";
   boolean alreadyExists = false;

		// before we open the file check to see if it already exists
   String c ;	
   try
		{
			c = UsingJsch.readingFile(path+outputFile);
		}catch(Exception e){
			c = null;
		}
		if(c!=null&&c!="")
			alreadyExists = true;			
		try {
			System.out.println(alreadyExists + "THIS IS WRITTEN:"+c);
			// use FileWriter constructor that specifies open for appending
			StringWriter s = new StringWriter();
			int x =-1;
			x = c.indexOf(file);
			System.out.println("\nVALUE OF X=="+x);
			String f = "";
			if(x!=-1){
			String r = c.substring(0, x);
			f = c.substring(x+1,c.length());
			f = f.substring(f.indexOf("\n")+1,f.length());
			System.out.println("R::::"+r+"\nF:::"+f);
			s.write(r);
			}
			else
				s.write(c);
			CsvWriter csvOutput = new CsvWriter(s, ',');
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				csvOutput.write("FileName");
				csvOutput.write("Marks");
				csvOutput.write("Status");
				csvOutput.endRecord();
			}
			// else assume that the file already has the correct header line
			//file =  file.substring(1,file.length()-1);
			System.out.println("RECOOOOOOOOOOORRDING\nFile"+file+"\nMArks: "+Integer.toString(marks)+"\nSTATUS:"+status);
			csvOutput.write(file);
			csvOutput.write(Integer.toString(marks));
			csvOutput.write(status);
			csvOutput.endRecord();
			// write out a few records
			if(x!=-1)
				s.write(f);
			System.out.println(s.toString());
			System.out.println("!!!!!!!!!!!!!!!\n@@@@@@@@@@@@@@@@\n################\n");
			UsingJsch.writingFile(path, s.toString(), "finalmarks.csv");
			csvOutput.flush();
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

	static String path1 ="/home/Btech15/kshitij.cs15/test/";
	static String file1 = "finalmarks.csv";
	public static void main(String[] args) throws Exception {
		
	/*	WriteMarksFile(path1, file1, 10, "Successful");
		ReadMarksFile(path1);
		System.out.println("\n\nSecond call");
		WriteMarksFile(path1, file1, 15, "Successful");
		ReadMarksFile(path1);
		System.out.println("\n\nThird call");
		WriteMarksFile(path1, file1, 0, "Compile time error");
		ReadMarksFile(path1);
*/
	}

}