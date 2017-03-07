package main.admin.admindao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import main.admin.admin.*;
import org.jasypt.util.password.StrongPasswordEncryptor;

import main.admin.admin.Admin;
import main.student.student.Student;

public class AdminDAO {
	
	private Connection myCon;
	
	private String dbname;
	
	private String password;
	
	private String user;
	
	public AdminDAO()throws Exception {
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
	
	public int passwordChecker(Admin admin){
		
		PreparedStatement pstmt=null;
		
		String encryptedPassword=null;
		
		StrongPasswordEncryptor encryptor=new StrongPasswordEncryptor();
		
		try{
			/*
			 * Roll No. set to be user Name and a primary Key
			 */
			pstmt=myCon.prepareStatement("select * from admin where username = ?");

			pstmt.setString(1, admin.getUsername());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				
				encryptedPassword=rs.getString("password");
				
				if(encryptor.checkPassword(admin.getPassword(), encryptedPassword)){
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
		return 0;
	}
	public Admin getAdminByUsername(String user_name){
		PreparedStatement pstmt=null;
		Admin ad=null;
		try{
			pstmt=myCon.prepareStatement("select * from admin where username=?");
			pstmt.setString(1, user_name);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				ad=new Admin(rs.getInt("s.no"),user_name,rs.getString("password"),rs.getString("first_name"),rs.getString("middle_name"),rs.getString("last_name"),rs.getInt("age"),rs.getString("sex").charAt(0),rs.getString("email"),rs.getString("security_ques"),rs.getString("answer"));
			}
			return ad;
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
		return ad;
	}

	public Admin getAdminByUserName(String username) {
		PreparedStatement pstmt=null;
		Admin admin=null;
		
		try {
			pstmt=myCon.prepareStatement("select * from admin where username = ?");

			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				admin=new Admin(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getInt(7), rs.getString(8).charAt(0), rs.getString(9), rs.getString(10), rs.getString(11));
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
		return admin;
	}
}
