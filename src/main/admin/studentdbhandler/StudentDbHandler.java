package main.admin.studentdbhandler;

import java.io.FileInputStream;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import main.student.student.*;
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
		try{System.out.println("StudentDBHandler");
		myCon=DriverManager.getConnection(dbName,user,password);
		/*
		 * System.out.println("Connection Established");
		 */
		}catch(Exception exc){
			System.out.println("Connection Could not be Established::");
			exc.printStackTrace();
		}
	}
	
	private String EncryptPassword(String password){
		StrongPasswordEncryptor encryptor=new StrongPasswordEncryptor();
		String encryptedPassword=encryptor.encryptPassword(password);
		return encryptedPassword;
	}
	public List<Student> getAllStudent()throws Exception{
		Statement stmt=null;
		ArrayList<Student> list_stud=new ArrayList<Student>();
		ResultSet rs=null;
		try{
			stmt=myCon.createStatement();
			rs=stmt.executeQuery("select * from student");
			while(rs.next()){
				Student st=convertRowToStudent(rs);
				list_stud.add(st);
			}
		}catch(Exception exc){
			exc.printStackTrace();
		}finally{
			if(stmt!=null){
				try{
					stmt.close();
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
		return list_stud;
	}
	
	/*
	 * Method to add Student
	 */
	
	public List<Student> searchStudent(String field){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Student> list_search=new ArrayList<Student>();
		try{
			field="%"+field+"%";
			pstmt=myCon.prepareStatement("select * from student where (roll_no like ?) or (first_name like ?) or (middle_name like ?) or (last_name like ?) or (batch_code like ?) or (email like ?) or (subject1 like ?) or (subject2 like ?) or (subject3 like ?) or (subject4 like ?) or (subject5 like ?) or (subject6 like ?) or (subject7 like ?) or (subject8 like ?) or (subject9 like ?) or (subject10 like ?)");
			pstmt.setString(1, field);
			pstmt.setString(2, field);
			pstmt.setString(3, field);
			pstmt.setString(4, field);
			pstmt.setString(5, field);
			pstmt.setString(6, field);
			pstmt.setString(7, field);
			pstmt.setString(8, field);
			pstmt.setString(9, field);
			pstmt.setString(10, field);
			pstmt.setString(11, field);
			pstmt.setString(12, field);
			pstmt.setString(13, field);
			pstmt.setString(14, field);
			pstmt.setString(15, field);
			pstmt.setString(16, field);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Student st=convertRowToStudent(rs);
				list_search.add(st);
			}
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
			if(rs!=null){
				try{
					rs.close();
				}catch(Exception exc){
					exc.printStackTrace();
				}
			}
			/*if(myCon!=null){
				try{
				myCon.close();
				}catch(Exception exc){
					exc.printStackTrace();
				}
			}*/
		}
		return list_search;
	}
	public Student convertRowToStudent(ResultSet rs)throws Exception{
		Student stud=new Student();
		stud.setAge(rs.getInt("age"));
		stud.setId(rs.getInt("s.no"));
		stud.setFirstname(rs.getString("first_name"));
		stud.setMiddlename(rs.getString("last_name"));
		stud.setLastname(rs.getString("last_name"));
		stud.setSex(rs.getString("sex").charAt(0));
		stud.setRollno(rs.getString("roll_no"));
		stud.setEmail(rs.getString("email"));
		stud.setBatch(rs.getString("batch_code"));
		stud.setSubject1(rs.getString("subject1"));
		stud.setSubject2(rs.getString("subject2"));
		stud.setSubject3(rs.getString("subject3"));
		stud.setSubject4(rs.getString("subject4"));
		stud.setSubject5(rs.getString("subject5"));
		stud.setSubject6(rs.getString("subject6"));
		stud.setSubject7(rs.getString("subject7"));
		stud.setSubject8(rs.getString("subject8"));
		stud.setSubject9(rs.getString("subject9"));
		stud.setSubject10(rs.getString("subject10"));
		return stud;
	}
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
				pstmt.setString(9,EncryptPassword(password));
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
	public void modifyStudent(String rollno, String firstname, String middlename, String lastname, String sex, int age,
			String email, String batch,String password, String securityques, String answer, String subject1, String subject2,
			String subject3, String subject4, String subject5, String subject6, String subject7, String subject8,
			String subject9, String subject10,boolean encrypt){
			PreparedStatement pstmt=null;
			try{
				pstmt=myCon.prepareStatement("update student set first_name = ?,middle_name=?,last_name =?,sex = ?,age = ?,email = ?,batch_code = ?,password = ? ,security_ques = ?,answer = ?,subject1 = ?,subject2 = ?,subject3 = ?,subject4 = ?,subject5 = ?,subject6 = ?,subject7 = ?,subject8 = ?,subject9 = ?,subject10 = ? where roll_no = ?");
				pstmt.setString(1, firstname);
				pstmt.setString(2, middlename);
				pstmt.setString(3, lastname);
				pstmt.setString(4, sex);
				pstmt.setInt(5, age);
				pstmt.setString(6, email);
				pstmt.setString(7,batch);
				if(encrypt)
			    pstmt.setString(8, EncryptPassword(password));
				else
				pstmt.setString(8, password);	
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
	public void deleteStudent(String rn){
		 PreparedStatement pstmt=null;
		 ResultSet rs=null;
		 try{
			 //System.out.println("Course"+id);
			 pstmt=myCon.prepareStatement("delete from student where roll_no = ?");
			 pstmt.setString(1, rn);
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
