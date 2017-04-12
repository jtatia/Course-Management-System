package main.course.coursedao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import main.student.student.Student;

public class CourseMappingDAO {

	private Connection myCon;
	
	private String dbname;
	
	private String password;
	
	private String user;
	
	public CourseMappingDAO()throws Exception {
		Properties prop=new Properties();
		/*
		 * Change File Location in the Properties File. This is only a test/temp.location
		 */
		prop.load(new FileInputStream("Files//details.properties"));
		dbname=prop.getProperty("dbName");
		user=prop.getProperty("user");
		password=prop.getProperty("password");
		try{System.out.println("CourseMappingDAO");
		myCon=DriverManager.getConnection(dbname, user, password);
		/*
		 * For Debugging Purpose
		 */
		//System.out.println("Connection Established");
		}catch(SQLException exc){
			System.out.println("Connection Problem::: Message ::");
			exc.printStackTrace();
		}
	}
	
	public void addEntry(String courseId,String professors[],String batches[])
	{
		PreparedStatement pstmt=null;
		String prof="";
		for(int i=0;i<professors.length-1;i++)
		{
			System.out.println("pr="+professors[i]);
			prof=prof+professors[i]+"_";
		}
		prof=prof+professors[professors.length-1];
		try
		{
			pstmt=myCon.prepareStatement("INSERT INTO course_mapping(`course_id`,`professor`,`batch_id1`,`batch_id2`,`batch_id3`,`batch_id4`,`batch_id5`)VALUES(?,?,?,?,?,?,?)");
			pstmt.setString(1, courseId);
			pstmt.setString(2, prof);
			pstmt.setString(3, batches[0]);
			pstmt.setString(4, batches[1]);
			pstmt.setString(5, batches[2]);
			pstmt.setString(6, batches[3]);
			pstmt.setString(7, batches[4]);
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(Exception exc){
					exc.printStackTrace();
				}
			}
		}
	}
	
	public void updateEntry(String courseId,String professors[],String batches[])
	{
		PreparedStatement pstmt=null;
		String prof="";
		for(int i=0;i<professors.length-1;i++)
		{
			prof=prof+professors[i]+"_";
		}
		prof=prof+professors[professors.length-1];
		try
		{
			pstmt=myCon.prepareStatement("update course_mapping set course_id=?, professor=?,batch_id1 =?,batch_id2 = ?,batch_id3 = ?,batch_id4 = ?,batch_id5 = ? where course_id = ? and professor = ?");
			pstmt.setString(1, courseId);
			pstmt.setString(2, prof);
			pstmt.setString(3, batches[0]);
			pstmt.setString(4, batches[1]);
			pstmt.setString(5, batches[2]);
			pstmt.setString(6, batches[3]);
			pstmt.setString(7, batches[4]);
			pstmt.setString(8, courseId);
			pstmt.setString(9, prof);
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(Exception exc){
					exc.printStackTrace();
				}
			}
		}
	}
	public String getDirectoryPathForProf(String courseId,String profUsername)
	{
		String path="/home/stud/btech/cse/2015/kshitij.cs15/cms/";
		PreparedStatement pstmt=null;
	//	System.out.println(courseId);
		try {
			
			pstmt=myCon.prepareStatement("select * from course_mapping where course_id = ? and professor like ?");
			pstmt.setString(1, courseId);
			pstmt.setString(2, "%"+profUsername+"%");
			ResultSet rs = pstmt.executeQuery();
			String dir_name="";
			while(rs.next())
			{
				String professor=rs.getString("professor");
				dir_name=dir_name+courseId+"_"+professor;	
			}
			path=path+dir_name+"/";
		}catch(Exception exc){
			exc.printStackTrace();
		}finally{
			try{
				pstmt.close();
			}catch(Exception exc){
				exc.printStackTrace();
			}
		}
		return path;
	}
	public String getDirectoryPath(String courseId,String batch)
	{
		String path="/home/stud/btech/cse/2015/kshitij.cs15/cms/";
		PreparedStatement pstmt=null;
		try {
			pstmt=myCon.prepareStatement("select * from course_mapping where course_id = ?");
			pstmt.setString(1, courseId);
			ResultSet rs = pstmt.executeQuery();
			String dir_name="";
			while(rs.next())
			{
				String professor=rs.getString("professor");
				String batch1=rs.getString("batch_id1");
				String batch2=rs.getString("batch_id2");
				String batch3=rs.getString("batch_id3");
				String batch4=rs.getString("batch_id4");
				String batch5=rs.getString("batch_id5");
				if(batch1.equals("") && batch2.equals("") && batch3.equals("") && batch4.equals("") && batch5.equals(""))
				{
					dir_name=courseId+"_"+professor;
					break;
				}
				else
				{
					if(batch1.equals(batch) || batch2.equals(batch) && batch3.equals(batch) && batch4.equals(batch) && batch5.equals(batch))
					{
						dir_name=courseId+"_"+professor;
						break;
					}
				}	
			}
			path=path+dir_name+"/";
		}catch(Exception exc){
			exc.printStackTrace();
		}finally{
			try{
				pstmt.close();
			}catch(Exception exc){
				exc.printStackTrace();
			}
		}
		return path;
	}

	public void deleteEntry(String courseId) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		try{
			 //System.out.println("Course"+id);
			 pstmt=myCon.prepareStatement("delete from course_mapping where course_id = ?");
			 pstmt.setString(1, courseId);
			 pstmt.executeUpdate();
		 }catch(Exception exc){
			 exc.printStackTrace();
		 }finally{
			 if(pstmt!=null){
				 try{
					 pstmt.close();
				 }catch(Exception exc){}
			 }
		 }
	}
}
