package main.course.coursedao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import main.course.course.Course;

public class CourseDAO {
	
	private Connection myCon;
	
	private String dbname;
	
	private String password;
	
	private String user;

	public CourseDAO()throws Exception {
		Properties prop=new Properties();
		/*
		 * Change File Location in the Properties File. This is only a test/temp.location
		 */
		prop.load(new FileInputStream("Files//details.properties"));
		dbname=prop.getProperty("dbName");
		user=prop.getProperty("user");
		password=prop.getProperty("password");
		try{
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
	
	public Course getCourseById(String course_id) {
		
		PreparedStatement pstmt=null;
		Course course=null;
		
		try {
			pstmt=myCon.prepareStatement("select * from course where course_id = ?");

			pstmt.setString(1, course_id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				course=new Course(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
			}
			
		}catch(Exception exc){
			exc.printStackTrace();
		}finally{
			try{
				pstmt.close();
			}catch(Exception exc){
				exc.printStackTrace();
			}
		}
		return course;
	}
	
	public void updateCourse(Course course)
	{
		PreparedStatement pstmt=null;
		try
		{
			pstmt=myCon.prepareStatement("update course set course_name=?, course_info=?");
			pstmt.setString(1,course.getCourseName());
			pstmt.setString(2,course.getCourseInfo());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(Exception exc){
					exc.printStackTrace();
				}
		}
			if(myCon!=null){
				try{
					myCon.close();
				}catch(Exception exc){
					exc.printStackTrace();
				}
			}
		}
	}
}
