package main.course.coursedao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import main.course.course.Course;
import main.student.student.Student;
import main.student.studentdao.StudentDAO;

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
		prop.load(CourseDAO.class.getResourceAsStream("/details.properties"));
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
	
	public Course convertRowToCourse(ResultSet rs)throws Exception{
		Course course=new Course();
		course.setCourseId(rs.getString("course_id"));
		course.setSerialNo(rs.getInt("s.no"));
		course.setCourseName(rs.getString("course_name"));
		course.setCourseInfo(rs.getString("course_info"));
		return course;
	}
	
	public List<Course> getAllCourses()
	{
		Statement st=null;
		ArrayList<Course> list= new ArrayList<Course>();
		ResultSet rs=null;
		try
		{
			st=myCon.createStatement();
			rs=st.executeQuery("select * from course");
			while(rs.next())
			{
				Course course=convertRowToCourse(rs);
				list.add(course);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally{
			if(st!=null){
				try{
					st.close();
				}catch(Exception exc){
					exc.printStackTrace();
				}
			}
			if(rs!=null){
				try{
					rs.close();
				}catch(Exception exc){
					exc.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public List<Course> searchCourse(String field)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Course> list=new ArrayList<Course>();
		try
		{
			String text="%"+field+"%";
			pstmt=myCon.prepareStatement("select * from course where (course_id like ?) or (course_name like ?)");
			pstmt.setString(1,"%"+text+"%");
			pstmt.setString(2,"%"+text+"%");
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				Course course=convertRowToCourse(rs);
				list.add(course);
			}
		}	
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pstmt!=null)
			{	
				try
				{
					pstmt.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try{
					rs.close();
				}catch(Exception exc){
					exc.printStackTrace();
				}
			}
		}
		return list;
	}
	public Course getCourseById(String course_id) {
		
		PreparedStatement pstmt=null;
		Course course=null;
		
		try {
			//System.out.println("hello0");
			pstmt=myCon.prepareStatement("select * from course where course_id = ?");
			//System.out.println("hello");
			
			pstmt.setString(1, course_id);
			//System.out.println("hello1");
			ResultSet rs = pstmt.executeQuery();
			//System.out.println(rs.next());
			while(rs.next())
			{
				course=new Course(rs.getInt("s.no"),rs.getString("course_id"),rs.getString("course_name"),rs.getString("course_info"));
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
		}
	}
	 public void addCourse(Course course)
	 {
		 PreparedStatement pstmt=null;
			try{
				pstmt=myCon.prepareStatement("INSERT INTO course(`course_id`,`course_name`,`course_info`)VALUES(?,?,?)");
				pstmt.setString(1, course.getCourseId());
				pstmt.setString(2, course.getCourseName());
				pstmt.setString(3, course.getCourseInfo());
				pstmt.executeUpdate();
			}catch(Exception exc){
				exc.printStackTrace();
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
	 public void deleteCourse(String id){
		 PreparedStatement pstmt=null;
		 try{
			 //System.out.println("Course"+id);
			 pstmt=myCon.prepareStatement("delete from course where course_id = ?");
			 pstmt.setString(1, id);
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