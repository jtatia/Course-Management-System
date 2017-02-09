package main.student.studentdao;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.jasypt.util.password.StrongPasswordEncryptor;
import main.student.student.*;
public class StudentDAO{
	private Connection myCon;
	
	private String dbname;
	
	private String password;
	
	private String user;
	
	public StudentDAO()throws Exception {
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
	
	public int passwordChecker(Student student){
		
		PreparedStatement pstmt=null;
		
		String encryptedPassword=null;
		
		StrongPasswordEncryptor encryptor=new StrongPasswordEncryptor();
		
		try{
			/*
			 * Roll No. set to be user Name and a primary Key
			 */
			pstmt=myCon.prepareStatement("select * from student where roll_no = ?");

			pstmt.setString(1, student.getRollno());
			ResultSet rs = pstmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					encryptedPassword=rs.getString("password");
				}
				if(encryptor.checkPassword(student.getPassword(), encryptedPassword)){
					return 2;
				}
				else return 1;
			}
			else{
				/*
				 * Display error message in JOptionPane in LoginPortal Parent Interface 
				 * 0-UserName Wrong
				 * 1-Password Wrong
				 * 2-Both Correct
				 */
				return 0;
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
		/*
		 * Error Shown If this is not added
		 */
		return 0;
	}
}