package main.professor.professorframe;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class CourseSpecificPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CourseSpecificPanel() {
		JTabbedPane jtp = new JTabbedPane(JTabbedPane.RIGHT);
		
		ProfDiscussionForumPanel pdf=new ProfDiscussionForumPanel();
		
		ProfCourseMaterialPanel pcm=new ProfCourseMaterialPanel();
		
		ProfCourseInfoPanel pci=new ProfCourseInfoPanel();
		
		ProfAssignmentViewPanel pav=new ProfAssignmentViewPanel();
		
		ProfAssignmentPanel pa=new ProfAssignmentPanel();
		
		jtp.add("Material", pcm);
		jtp.add("Assignments", pa);
		jtp.add("Uploaded Assignments", pav);
		jtp.add("Discussion Forum", pdf);
		jtp.add("Info", pci);
		add(jtp);

	}

}
