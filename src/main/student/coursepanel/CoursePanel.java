package main.student.coursepanel;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.JScrollBar;
import java.awt.FlowLayout;
import javax.swing.border.MatteBorder;

import main.student.student.Student;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

public class CoursePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private static JTabbedPane jtp;
	private static CourseAssignmentPanel cap;
	private static CourseMaterialPanel cmp;
	private static CourseAssignmentUploadPanel caup;
	private static CourseDiscussionForumPanel cdfp;
	private static CourseInfoPanel cip;
	
	public void resetPanes(Student student, String path) throws Exception
	{
		
		jtp.removeAll();
		System.out.println("Before Initialisation");
		cap=new CourseAssignmentPanel(path);
		System.out.println("Initialisation Done");
		cmp=new CourseMaterialPanel(path);
		
		caup=new CourseAssignmentUploadPanel(student,path);
		
		cdfp=new CourseDiscussionForumPanel(path);
		
		cip=new CourseInfoPanel(path);
		
		jtp.add("Material", cmp);
		jtp.setForegroundAt(0, Color.WHITE);
		jtp.setBackgroundAt(0, Color.DARK_GRAY);
		jtp.add("Assignments", cap);
		//jtp.removeTabAt(1);
		//jtp.addTab("Assignments", cap);
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
		//jtp.revalidate();
		//jtp.repaint();
		//cap.revalidate();
		//cap.repaint();
		//System.out.println("New things");
	}
	
	public CoursePanel() throws Exception
	{
//		this();
			//jtp.setBounds(100, 100, 300, 200);
			//jtp.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			//int ft_size=jtp.getFont().getSize();
			setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			jtp = new JTabbedPane(JTabbedPane.TOP);
			
			cap=new CourseAssignmentPanel();
			
			cmp=new CourseMaterialPanel();
			
			caup=new CourseAssignmentUploadPanel(null, "");
			
			cdfp=new CourseDiscussionForumPanel();
			
			cip=new CourseInfoPanel();
			
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
	/*
	public CoursePanel(String path)throws Exception {
	//	this();
		//jtp.setBounds(100, 100, 300, 200);
		//jtp.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		//int ft_size=jtp.getFont().getSize();
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jtp = new JTabbedPane(JTabbedPane.RIGHT);
		
		cap=new CourseAssignmentPanel(path);
		
		cmp=new CourseMaterialPanel(path);
		
		caup=new CourseAssignmentUploadPanel();
		
		cdfp=new CourseDiscussionForumPanel(path);
		
		cip=new CourseInfoPanel(path);
		
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
*/
}
