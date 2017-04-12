package test.course;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import main.course.course.Course;
import main.course.coursedao.CourseDAO;

public class CourseDAOTest {

	@Test
	public void test_searchCourse() throws Exception {
		CourseDAO dao = new CourseDAO();
		
		ArrayList<Course> course = new ArrayList<Course>();
		Course c1 = new Course();
		Course c2 = new Course();
		Course c3 = new Course();
		
		c1.setCourseId("CS201");
		c1.setCourseName("Algorithms");
		course.add(c1);
		c2.setCourseId("CS225");
		c2.setCourseName("Switching Theory");
		course.add(c2);
		c3.setCourseId("CS226");
		c3.setCourseName("Switching THeory Lab");
		course.add(c3);
		assertEquals(course,dao.searchCourse("CS"));		
	}

}


