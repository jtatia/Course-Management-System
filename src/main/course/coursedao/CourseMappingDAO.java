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
}
