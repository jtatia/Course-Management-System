package main.admin.adminpaneldao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.jasypt.util.password.StrongPasswordEncryptor;
import java.sql.PreparedStatement;
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
			stmt.setLong(7, admin.getSex());
			stmt.setString(8, admin.getEmail());
			stmt.setString(9, admin.getSecurityques());
			stmt.setString(10, admin.getAnswer());
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
}
