package test.student;

import static org.junit.Assert.*;
import org.junit.Test;
import main.student.student.Student;
import main.student.studentcourseoutlinedao.StudentCourseOutlineDAO;

public class StudentCourseOutlineTest {

	@Test
	public void CoursedataTest()throws Exception {
		Student student = new Student();
		student.setRollno("1501CS23");
		StudentCourseOutlineDAO scdao = new StudentCourseOutlineDAO(student);
		assertNotNull(scdao);
		assertNotNull(scdao.Coursedata(student));
		assertEquals(scdao.Coursedata(student).get(0),"Math");
		student.setRollno("1501CS22");
		scdao = new StudentCourseOutlineDAO(student);
		assertTrue(scdao.Coursedata(student).isEmpty());
	}

}
