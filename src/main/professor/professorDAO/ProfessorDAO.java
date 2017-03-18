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
	
	private Professor convertRowToProfessor(ResultSet rs) throws Exception{
		return new Professor(rs.getInt("s.no"), rs.getString("user_name"), rs.getString("first_name"), rs.getString("middle_name"),rs.getString("last_name"), rs.getString("email"), rs.getString("sex").charAt(0), rs.getString("password"), rs.getString("security_ques"), rs.getString("answer"), rs.getString("course_id1"), rs.getString("course_id2"), rs.getString("course_id3"), rs.getString("course_id4"), rs.getString("course_id5"));
	}

	public List<Professor> searchProfessor(String toSearch) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ArrayList<Professor> pr_list=new ArrayList<Professor>();
		try{
			pstmt=myCon.prepareStatement("select * from professor where (user_name like ?) or (first_name like ?) or (middle_name like ?) or (last_name like ?) or (email like ?) or (sex like ?) or (course_id1 like ?) or (course_id2 like ?) or (course_id3 like ?) or (course_id4 like ?) or (course_id5 like ?)");
			pstmt.setString(1, toSearch);
			pstmt.setString(2, toSearch);
			pstmt.setString(3, toSearch);
			pstmt.setString(4, toSearch);
			pstmt.setString(5, toSearch);
			pstmt.setString(6, toSearch);
			pstmt.setString(7, toSearch);
			pstmt.setString(8, toSearch);
			pstmt.setString(9, toSearch);
			pstmt.setString(10, toSearch);
			pstmt.setString(11, toSearch);
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
		PreparedStatement pstmt=null;
		try{
			
			pstmt=myCon.prepareStatement("update professor set first_name = ?, middle_name= ?, last_name = ?, sex = ?,email = ?,password = ? ,security_ques = ?, answer = ?, course_id1 = ?, course_id2= ?, course_id3= ?, course_id4= ?, course_id5= ? where user_name = ?");
			pstmt.setString(1, prof.getFirstname());
			pstmt.setString(2, prof.getMiddlename());
			pstmt.setString(3, prof.getLastname());
			pstmt.setString(4, ""+prof.getSex());
			pstmt.setString(5, prof.getEmail());
			pstmt.setString(6, EncryptPassword(prof.getPassword()));
		    pstmt.setString(7, prof.getSecurityques());
			pstmt.setString(8, prof.getAnswer());
			pstmt.setString(9, prof.getCourseid1());
			pstmt.setString(10, prof.getCourseid2());
			pstmt.setString(11,	prof.getCourseid3());
			pstmt.setString(12, prof.getCourseid4());
			pstmt.setString(13, prof.getCourseid5());
			pstmt.setString(14, prof.getUsername());
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
			if(myCon!=null){
				try{
					myCon.close();
				}catch(Exception exc){
					exc.printStackTrace();
				}
			}
		}
	}
}
