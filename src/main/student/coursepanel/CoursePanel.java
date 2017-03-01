package main.student.coursepanel;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollBar;

public class CoursePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CoursePanel() {
		JTabbedPane jtp = new JTabbedPane(JTabbedPane.RIGHT);
		CourseAssignmentPanel cap=new CourseAssignmentPanel();
		
		CourseMaterialPanel cmp=new CourseMaterialPanel();
		
		CourseAssignmentUploadPanel caup=new CourseAssignmentUploadPanel();
		
		CourseDiscussionForumPanel cdfp=new CourseDiscussionForumPanel();
		
		CourseInfoPanel cip=new CourseInfoPanel();
		
		jtp.add("Material", cmp);
		jtp.add("Assignments", cap);
		jtp.add("Upload Assignments", caup);
		jtp.add("Discussion Forum", cdfp);
		jtp.add("Info", cip);
		add(jtp);
	}

}
