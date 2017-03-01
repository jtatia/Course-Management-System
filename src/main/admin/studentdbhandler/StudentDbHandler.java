package main.admin.studentdbhandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class StudentDbHandler {
	private String dbName;
	
	private String user;
	
	private String password;
	
	private Connection myCon;
	
	private String sex="";
	
	public StudentDbHandler() throws IOException {
		Properties prop=new Properties();
		prop.load(new FileInputStream("Files//details.properties"));
		dbName=prop.getProperty("dbName");
		user=prop.getProperty("user");
		password=prop.getProperty("password");
		try{
		myCon=DriverManager.getConnection(dbName,user,password);
		/*
		 * System.out.println("Connection Established");
		 */
		}catch(Exception exc){
			System.out.println("Connection Could not be Established::");
			exc.printStackTrace();
		}
	}
	
	private String EncryptPassword(){
		StrongPasswordEncryptor encryptor=new StrongPasswordEncryptor();
		String encryptedPassword=encryptor.encryptPassword(this.password);
		return encryptedPassword;
	}
	
	/*
	 * Method to add Student
	 */
	public void addStudent(String rollno, String firstname, String middlename, String lastname, char sex, int age,
			String email, String batch,String password, String securityques, String answer, String subject1, String subject2,
			String subject3, String subject4, String subject5, String subject6, String subject7, String subject8,
			String subject9, String subject10){
			PreparedStatement pstmt=null;
			try{
				this.sex=""+sex;
				pstmt=myCon.prepareStatement("INSERT INTO student(`roll_no`,`first_name`,`middle_name`,`last_name`,`sex`,`age`,`email`,`batch_code`,`password`,`security_ques`,`answer`,`subject1`,`subject2`,`subject3`,`subject4`,`subject5`,`subject6`,`subject7`,`subject8`,`subject9`,`subject10`)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pstmt.setString(1, rollno);
				pstmt.setString(2, firstname);
				pstmt.setString(3, middlename);
				pstmt.setString(4, lastname);
				pstmt.setString(5, this.sex);
				pstmt.setInt(6, age);
				pstmt.setString(7, email);
				pstmt.setString(8,batch);
				pstmt.setString(9,EncryptPassword());
				pstmt.setString(10, securityques);
				pstmt.setString(11, answer);
				pstmt.setString(12, subject1);
				pstmt.setString(13, subject2);
				pstmt.setString(14, subject3);
				pstmt.setString(15, subject4);
				pstmt.setString(16, subject5);
				pstmt.setString(17, subject6);
				pstmt.setString(18, subject7);
				pstmt.setString(19, subject8);
				pstmt.setString(20, subject9);
				pstmt.setString(21, subject10);
				pstmt.executeUpdate();
			}catch(Exception exc){
				exc.printStackTrace();
			}
	}
	public void removeStudent(String id){
		PreparedStatement pstmt=null;
		try{
			pstmt=myCon.prepareStatement("delete from student where roll_no = ?;");
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
	/*
	 * ROLL NO. CANNOT BE UPDATED AS THE ENTIRE SQL DEPENEDS ON IT
	 */
	public void modifyStudent(String rollno, String firstname, String middlename, String lastname, char sex, int age,
			String email, String batch,String password, String securityques, String answer, String subject1, String subject2,
			String subject3, String subject4, String subject5, String subject6, String subject7, String subject8,
			String subject9, String subject10){
			PreparedStatement pstmt=null;
			try{
				this.sex=""+sex;
				pstmt=myCon.prepareStatement("update student set first_name = ?,middle_name=?,last_name =?,sex = ?,age = ?,email = ?,batch_code = ?,password = ? ,security_ques = ?,answer = ?,subject1 = ?,subject2 = ?,subject3 = ?,subject4 = ?,subject5 = ?,subject6 = ?,subject7 = ?,subject8 = ?,subject9 = ?,subject10 = ? where roll_no = ?");
				pstmt.setString(1, firstname);
				pstmt.setString(2, middlename);
				pstmt.setString(3, lastname);
				pstmt.setString(4, this.sex);
				pstmt.setInt(5, age);
				pstmt.setString(6, email);
				pstmt.setString(7,batch);
				pstmt.setString(8,EncryptPassword());
				pstmt.setString(9, securityques);
				pstmt.setString(10, answer);
				pstmt.setString(11, subject1);
				pstmt.setString(12, subject2);
				pstmt.setString(13, subject3);
				pstmt.setString(14, subject4);
				pstmt.setString(15, subject5);
				pstmt.setString(16, subject6);
				pstmt.setString(17, subject7);
				pstmt.setString(18, subject8);
				pstmt.setString(19, subject9);
				pstmt.setString(20, subject10);
				pstmt.setString(21, rollno);
				pstmt.executeUpdate();
			}catch(Exception exc){
				exc.printStackTrace();
			}
	}
}
