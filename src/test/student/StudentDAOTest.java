package test.student;

import static org.junit.Assert.*;
import main.student.student.Student;
import main.student.studentdao.*;
import org.junit.Test;

public class StudentDAOTest {

	@Test
	public void studentdao_passcheck()throws Exception {
			StudentDAO studentdao = new StudentDAO();
			Student student = new Student();
			student.setRollno("1501CS23");
			student.setPassword("fight");
	        assertNotNull(student);
			assertEquals(studentdao.passwordChecker(student),1);
		
	}
	@Test
	public void studentdao_usernamecheck()throws Exception {
		StudentDAO studentdao = new StudentDAO();
		Student student = new Student();
		student.setRollno("1501CS22");
		student.setPassword("fight");
		assertNotNull(student);
		assertEquals(studentdao.passwordChecker(student),0);
	}
	
	@Test
	public void studentdao_correctcheck()throws Exception {
		StudentDAO studentdao = new StudentDAO();
		Student student = new Student();
		student.setRollno("1501CS26");
		student.setPassword("satakshi");
		assertNotNull(student);
		assertEquals(studentdao.passwordChecker(student),2);
	}
	
	@Test
	public void student_byroll()throws Exception {
		StudentDAO studentdao = new StudentDAO();
		assertNotNull(studentdao.getStudentByRollno("1501CS23"));
		assertNull(studentdao.getStudentByRollno("1501EE252"));
	}
}

