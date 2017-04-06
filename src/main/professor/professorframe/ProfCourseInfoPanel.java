package main.professor.professorframe;

import javax.swing.JPanel;

import main.student.coursepanel.CourseInfoPanel;

public class ProfCourseInfoPanel extends  CourseInfoPanel{
	/**
	 * Create the panel.
	 */
	public ProfCourseInfoPanel()throws Exception {
		super();
	}
	public ProfCourseInfoPanel(String course_id)throws Exception {
		super(course_id);
	}
}
