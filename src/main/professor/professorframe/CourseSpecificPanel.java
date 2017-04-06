package main.professor.professorframe;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import main.professor.professor.Professor;
import main.student.coursepanel.CourseAssignmentPanel;
import main.student.coursepanel.CourseAssignmentUploadPanel;
import main.student.coursepanel.CourseDiscussionForumPanel;
import main.student.coursepanel.CourseInfoPanel;
import main.student.coursepanel.CourseMaterialPanel;
import main.student.student.Student;

public class CourseSpecificPanel extends JPanel {

	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	private static JTabbedPane jtp;
	private static ProfDiscussionForumPanel pdf;
	private static ProfCourseMaterialPanel pcm;
	private static ProfCourseInfoPanel pci;
	private static ProfAssignmentViewPanel pav;
	private static ProfAssignmentPanel pa;
	public static String curr_subject;
	public void resetPanes(Professor prof, String path) throws Exception
	{	
		jtp.removeAll();
		System.out.println("Before Initialisation");
		pdf=new ProfDiscussionForumPanel();
		System.out.println("Initialisation Done");
		pcm=new ProfCourseMaterialPanel(path);
		
		pci=new ProfCourseInfoPanel();
		
		pav=new ProfAssignmentViewPanel(path);
		
		pa=new ProfAssignmentPanel(path);
		
		jtp.add("Material", pcm);
		jtp.setForegroundAt(0, Color.WHITE);
		jtp.setBackgroundAt(0, Color.DARK_GRAY);
		jtp.add("Assignments", pa);
		jtp.setForegroundAt(1, Color.WHITE);
		jtp.setBackgroundAt(1, Color.DARK_GRAY);
		jtp.add("Upload Assignments", pav);
		jtp.setForegroundAt(2, Color.WHITE);
		jtp.setBackgroundAt(2, Color.DARK_GRAY);
		jtp.add("Discussion Forum", pdf);
		jtp.setForegroundAt(3, Color.WHITE);
		jtp.setBackgroundAt(3, Color.DARK_GRAY);
		jtp.add("Info", pci);
		jtp.setForegroundAt(4, Color.WHITE);
		jtp.setBackgroundAt(4, Color.DARK_GRAY);
		pci.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
	
	public CourseSpecificPanel() throws Exception {
		jtp = new JTabbedPane(JTabbedPane.TOP);
		
		pdf=new ProfDiscussionForumPanel();
		
		pcm=new ProfCourseMaterialPanel();
		
		pci=new ProfCourseInfoPanel();
		
		pav=new ProfAssignmentViewPanel();
		
		pa=new ProfAssignmentPanel();
		
		jtp.add("Material", pcm);
		jtp.setForegroundAt(0, Color.WHITE);
		jtp.setBackgroundAt(0, Color.DARK_GRAY);
		jtp.add("Assignments", pa);
		jtp.setForegroundAt(1, Color.WHITE);
		jtp.setBackgroundAt(1, Color.DARK_GRAY);
		jtp.add("Upload Assignments", pav);
		jtp.setForegroundAt(2, Color.WHITE);
		jtp.setBackgroundAt(2, Color.DARK_GRAY);
		jtp.add("Discussion Forum", pdf);
		jtp.setForegroundAt(3, Color.WHITE);
		jtp.setBackgroundAt(3, Color.DARK_GRAY);
		jtp.add("Info", pci);
		jtp.setForegroundAt(4, Color.WHITE);
		jtp.setBackgroundAt(4, Color.DARK_GRAY);
		pci.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(jtp);

	}

}
