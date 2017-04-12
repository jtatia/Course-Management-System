package test.course;

import static org.junit.Assert.*;

import org.junit.Test;

import main.course.coursedao.CourseMappingDAO;

public class CourseMappingDAOTest {

	CourseMappingDAO dao = null;
	@Test
	public void test_getDirectoryPathForProf() throws Exception {
		dao = new CourseMappingDAO();
		String prof = "jimson";
		String course = "CS225";
		assertNotNull(dao.getDirectoryPathForProf(course, prof));
		assertEquals("/home/Btech15/kshitij.cs15/cms/CS225_jimson/",dao.getDirectoryPathForProf(course, prof));
		assertEquals("/home/Btech15/kshitij.cs15/cms/",dao.getDirectoryPathForProf("CS229", "samrat"));
	}
	
	@Test
	public void test_getDirectoryPath()throws Exception {
		dao = new CourseMappingDAO();
		String course = "CS226";
		String batch = "cse15b";
		assertNotNull(dao.getDirectoryPath(course, batch));
		assertEquals("/home/Btech15/kshitij.cs15/cms/CS226_jimson/",dao.getDirectoryPath(course, batch));
		assertEquals("/home/Btech15/kshitij.cs15/cms/",dao.getDirectoryPath("CS231", "cse15b"));
	}

}
