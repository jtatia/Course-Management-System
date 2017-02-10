package test.student.studentdaotest;

import static org.junit.Assert.*;
import main.student.student.Student;
import main.student.studentdao.*;
import org.junit.Test;

public class test {

	@Test
	public void studentdao_passcheck()throws Exception {
			StudentDAO studentdao = new StudentDAO();
			Student student = new Student();
			student.setRollno("1501CS56");
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
		student.setRollno("1501CS23");
		student.setPassword("satakshi");
		assertNotNull(student);
		assertEquals(studentdao.passwordChecker(student),2);
	}
}
