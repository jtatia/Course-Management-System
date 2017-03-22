package main.admin.adminpaneldao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.mysql.jdbc.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.admin.admin.Admin;

public class AdminPanelDAO {

	private Connection myCon;
	
	private String dbname;
	
	private String password;
	
	private String user;
	
	public AdminPanelDAO()throws Exception {
		Properties prop=new Properties();
		/*
		 * Change File Location in the Properties File. This is only a test/temp.location
		 */
		prop.load(new FileInputStream("Files//details.properties"));
		dbname=prop.getProperty("dbName");
		user=prop.getProperty("user");
		password=prop.getProperty("password");
		try{System.out.println("AdminPanelDAO");
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
	public void addAdmin(Admin admin)throws Exception
	{
	PreparedStatement stmt = null;
		try {
			StrongPasswordEncryptor encryptor=new StrongPasswordEncryptor();
			String sql = "insert into admin"+
		" (username, password, first_name, middle_name, last_name, age, sex, email, security_ques, answer )"+
					" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = myCon.prepareStatement(sql);
			stmt.setString(1, admin.getUsername());
			stmt.setString(2, encryptor.encryptPassword(admin.getPassword()));
			stmt.setString(3, admin.getFirstname());
			stmt.setString(4, admin.getMiddlename());
			stmt.setString(5, admin.getLastname());
			stmt.setInt(6, admin.getAge());
			stmt.setString(7, Character.toString(admin.getSex()));
			stmt.setString(8, admin.getEmail());
			stmt.setString(9, admin.getSecurityques());
			stmt.setString(10,admin.getAnswer());
			stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public List<Admin> getAllAdmin()throws Exception {
		List<Admin> admin = new ArrayList<>();
		ResultSet rs = null;
		Statement stmt = null;
		try{
			String sql ="select * from admin";
			stmt=(Statement) myCon.createStatement();
			rs= stmt.executeQuery(sql);
			while(rs.next()){
				Admin temp = convertRowToAdmin(rs);
			    admin.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(stmt!=null)
		stmt.close();
			if(rs!=null)
		rs.close();
		}
		return admin;
	}
	
	public List<Admin> searchAdmin(String toSearch)throws Exception{
		List<Admin> admin = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			String sql = "select * from admin where username like ? or first_name like ? or middle_name like ? or last_name like ? or email like ?";
			stmt = myCon.prepareStatement(sql);
			stmt.setString(1, toSearch);
			stmt.setString(2, toSearch);
			stmt.setString(3, toSearch);
			stmt.setString(4, toSearch);
			stmt.setString(5, toSearch);
			rs = stmt.executeQuery();
			while(rs.next()){
				Admin temp = convertRowToAdmin(rs);
				admin.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(stmt!=null)
		stmt.close();
			if(rs!=null)
		rs.close();
		}
		return admin;
	}
	
	private Admin convertRowToAdmin(ResultSet rs) {
		Admin admin = null;
		try{
			String c =rs.getString("sex");
			char sex = c.charAt(0);
			admin = new Admin(rs.getInt("s.no"),rs.getString("username"),rs.getString("password"),rs.getString("first_name"),rs.getString("middle_name"),rs.getString("last_name"),rs.getInt("age"),sex,rs.getString("email"),rs.getString("security_ques"),rs.getString("answer"));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return admin;
	}
	public void deleteAdmin(String user) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		 ResultSet rs=null;
		 try{
			 //System.out.println("Course"+id);
			 pstmt=myCon.prepareStatement("delete from admin where username = ?");
			 pstmt.setString(1, user);
			 pstmt.executeUpdate();
		 }catch(Exception exc){
			 exc.printStackTrace();
		 }finally{
			 if(pstmt!=null){
				 try{
					 pstmt.close();
				 }catch(Exception exc){}
			 }
			 if(rs!=null){
				 try{
					 rs.close();
				 }catch(Exception exc){}
			 }
		 }
	 }
}
