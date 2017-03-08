package main.professor.professorDAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.jasypt.util.password.StrongPasswordEncryptor;

import main.professor.professor.Professor;

public class ProfessorDAO {
	
	private Connection myCon;
	
	private String dbname;
	
	private String password;
	
	private String user;
	
	public ProfessorDAO()throws Exception{
		
		Properties prop=new Properties();
		prop.load(new FileInputStream("Files//details.properties"));
		dbname=prop.getProperty("dbName");
		user=prop.getProperty("user");
		password=prop.getProperty("password");
		try{System.out.println("ProfessorDAO");
			myCon=DriverManager.getConnection(dbname, user, password);
		}catch(SQLException exc){
			System.out.println("Connection Problem::: Message ::");
			exc.printStackTrace();
		}
	}
	
	public int passwordChecker(Professor prof)
	{
		PreparedStatement pstmt=null;
		
		String encryptedPassword=null;
		
		StrongPasswordEncryptor encryptor=new StrongPasswordEncryptor();
		
		try{
			
			pstmt=myCon.prepareStatement("select * from professor where user_name=?");

			pstmt.setString(1, prof.getUsername());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				
				encryptedPassword=rs.getString("password");
				
				if(encryptor.checkPassword(prof.getPassword(), encryptedPassword)){
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
	
	public Professor getProfByUsername(String username)
	{
		PreparedStatement pstmt=null;
		Professor prof=null;
		
		try{
			pstmt=myCon.prepareStatement("select * from professor where user_name=?");
			
			pstmt.setString(1, username);
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				prof=new Professor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7).charAt(0),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
						rs.getString(13),rs.getString(14),rs.getString(15));
						
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
		return prof;
	}

}
