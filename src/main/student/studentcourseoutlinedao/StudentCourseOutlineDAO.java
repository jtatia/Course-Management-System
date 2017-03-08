package main.student.studentcourseoutlinedao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;

import main.student.student.Student;

public class StudentCourseOutlineDAO {
	
	public Student student;
	
	public Connection myCon;
	
	public StudentCourseOutlineDAO(Student student)throws Exception{
		Properties properties = new Properties();
		properties.load(new FileInputStream("Files//details.properties"));
		String dbname=properties.getProperty("dbName");
		String user=properties.getProperty("user");
		String password=properties.getProperty("password");
		try{System.out.println("StudentCourseOutlineDAO");
		myCon=DriverManager.getConnection(dbname, user, password);
		/*
		 * For Debugging Purpose
		 */
		//System.out.println("Connection Established");
		}catch(SQLException exc){
			System.out.println("Connection Problem::: Message ::");
			exc.printStackTrace();
		}

		this.student = student;
		if(student == null){
			System.exit(0);
		}
	}
	
	public ArrayList<String> Coursedata(Student student){
		ArrayList<String> course = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			try{
			String sql = "Select * from student where roll_no= ? ";
			pstmt = (PreparedStatement) myCon.prepareStatement(sql);
			pstmt.setString(1, student.getRollno());
			rs = pstmt.executeQuery();
			while(rs.next()){
				for(int i=1;i<=9;i++)
				{
					String subject = "subject"+i;
					String temp = (rs.getString(subject));
				    System.out.println(temp);
				    if(!temp.equals("") && !temp.equals(null)){
					course.add(temp);
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				pstmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return course;
	}
	}


