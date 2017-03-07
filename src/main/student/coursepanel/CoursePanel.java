package main.student.coursepanel;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollBar;
import java.awt.FlowLayout;

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
		jtp.setForegroundAt(0, Color.WHITE);
		jtp.setBackgroundAt(0, Color.DARK_GRAY);
		jtp.add("Assignments", cap);
		jtp.setForegroundAt(1, Color.WHITE);
		jtp.setBackgroundAt(1, Color.DARK_GRAY);
		jtp.add("Upload Assignments", caup);
		jtp.setForegroundAt(2, Color.WHITE);
		jtp.setBackgroundAt(2, Color.DARK_GRAY);
		jtp.add("Discussion Forum", cdfp);
		jtp.setForegroundAt(3, Color.WHITE);
		jtp.setBackgroundAt(3, Color.DARK_GRAY);
		jtp.add("Info", cip);
		jtp.setForegroundAt(4, Color.WHITE);
		jtp.setBackgroundAt(4, Color.BLACK);
		cip.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(jtp);
	}

}
