/*
 * ADD PROFESSOR METHOD HAS NOT YET BEEN DEFINED. ASSIGNED PERSON PLEASE ADD.
 */
package main.professor.professorDAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
				String courses=rs.getString("course_ids");
				String course[]=courses.split("_");
				prof=new Professor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7).charAt(0),rs.getString(8),rs.getString(9),rs.getString(10),course);
				System.out.println(rs.getString(2));		
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
	
	public List<Professor> getAllProfessor(){
		PreparedStatement pstmt=null;
		ArrayList<Professor> pr_list=new ArrayList<Professor>();
		try{
			/*if(myCon==null)
				System.out.println("null");*/
			pstmt=myCon.prepareStatement("select * from professor");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Professor temp_pr=convertRowToProfessor(rs);
				pr_list.add(temp_pr);
			}
			return pr_list;
		}catch(Exception exc){
			exc.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(Exception exc){}
			}
		}
		return null;
	}
	
	public List<String> getAllUsernames(){
		PreparedStatement pstmt=null;
		ArrayList<String> pr_list=new ArrayList<String>();
		try{
			pstmt=myCon.prepareStatement("select * from professor");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				String pr_str=convertRowToProfessor(rs).getUsername();
				pr_list.add(pr_str);
			}
			return pr_list;
		}catch(Exception exc){
			exc.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(Exception exc){}
			}
		}
		return null;
	}
	
	private Professor convertRowToProfessor(ResultSet rs) throws Exception{
		String courses=rs.getString("course_ids");
		String course[]=courses.split("_");
		return new Professor(rs.getInt("s.no"), rs.getString("user_name"), rs.getString("first_name"), rs.getString("middle_name"),rs.getString("last_name"), rs.getString("email"), rs.getString("sex").charAt(0), rs.getString("password"), rs.getString("security_ques"), rs.getString("answer"), course);
	}

	public List<Professor> searchProfessor(String toSearch) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ArrayList<Professor> pr_list=new ArrayList<Professor>();
		try{
			pstmt=myCon.prepareStatement("select * from professor where (user_name like ?) or (first_name like ?) or (middle_name like ?) or (last_name like ?) or (email like ?) or (sex like ?) or (course_ids like ?)");
			pstmt.setString(1, "%"+toSearch+"%");
			pstmt.setString(2, "%"+toSearch+"%");
			pstmt.setString(3, "%"+toSearch+"%");
			pstmt.setString(4, "%"+toSearch+"%");
			pstmt.setString(5, "%"+toSearch+"%");
			pstmt.setString(6, "%"+toSearch+"%");
			pstmt.setString(7, "%"+toSearch+"%");
			//pstmt.setString(12, toSearch);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Professor pr_temp=convertRowToProfessor(rs);
				pr_list.add(pr_temp);
			}
			return pr_list;
		}catch(Exception exc){
			exc.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(Exception exc){}
			}
		}
		return null;
	}
	private String EncryptPassword(String password){
		StrongPasswordEncryptor encryptor=new StrongPasswordEncryptor();
		String encryptedPassword=encryptor.encryptPassword(password);
		return encryptedPassword;
	}
	public void modifyProfessor(Professor prof)
	{
		System.out.println("inside method");
		PreparedStatement pstmt=null;
		try{
			System.out.println(prof.getMiddlename()+" "+prof.getUsername());
			pstmt=myCon.prepareStatement("update professor set user_name=?,first_name = ?, middle_name= ?, last_name = ?, sex = ?,email = ?,password = ? ,security_ques = ?, answer = ?, course_ids = ? where user_name = ?");
			pstmt.setString(1, prof.getUsername());
			pstmt.setString(2, prof.getFirstname());
			pstmt.setString(3, prof.getMiddlename());
			pstmt.setString(4, prof.getLastname());
			pstmt.setString(5, ""+prof.getSex());
			pstmt.setString(6, prof.getEmail());
			pstmt.setString(7, EncryptPassword(prof.getPassword()));
		    pstmt.setString(8, prof.getSecurityques());
			pstmt.setString(9, prof.getAnswer());
			//##############################################################################
			String courseIds[]=prof.getCourseids();
			String courses="";
			for(int i=0;i<courseIds.length-1;i++)
			{
				courses=courses+courseIds[i]+"_";
			}
			courses=courses+courseIds[courseIds.length-1];
			pstmt.setString(10, courses);
			pstmt.setString(11, prof.getUsername());
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

	public void deleteProf(String user) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		 try{
			 //System.out.println("Course"+id);
			 pstmt=myCon.prepareStatement("delete from professor where user_name = ?");
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
		 }
	 }
	
	public List<String> getAllCourses(String user){
		ArrayList<String> course_taken = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try{
			pstmt=myCon.prepareStatement("select * from professor where user_name=?");
			pstmt.setString(1, user);
			rs=pstmt.executeQuery();
			while(rs.next()){
					 String c_name = rs.getString("course_ids");
					 String str[]=c_name.split("_");
					 for(int i=0;i<str.length;i++)
						 course_taken.add(str[i]);
				 }
			}
		catch(Exception exc){
			exc.printStackTrace();
		}finally{
			if(pstmt!=null){
				try{
					pstmt.close();
				}catch(Exception exc){}
			}
			if(rs!=null){
				try{
					pstmt.close();
				}catch(Exception exc){}
			}
		}
		System.out.println(course_taken.size());
		return course_taken;
	}
}
