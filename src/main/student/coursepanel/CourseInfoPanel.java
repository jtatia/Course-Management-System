package main.student.coursepanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

import org.jdesktop.swingx.JXLabel;

import main.course.course.Course;
import main.course.coursedao.CourseDAO;
import javax.swing.JScrollPane;

public class CourseInfoPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private Course co=null;
	private CourseDAO coDao;
	public CourseInfoPanel(){ }
	public CourseInfoPanel(String course_id) throws Exception{
		setBackground(new Color(0, 0, 0));
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(1300,600));
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(1300,50));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(139, 0, 0));
		add(panel, BorderLayout.NORTH);
		
		JLabel lblCourseSummary = new JLabel("COURSE SUMMARY");
		lblCourseSummary.setFont(new Font("Verdana", Font.BOLD, 25));
		lblCourseSummary.setForeground(new Color(255, 255, 255));
		panel.add(lblCourseSummary);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);//If you want a different background uncomment this
		//panel_1.setPreferredSize(new Dimension(1300,400));
		coDao = new CourseDAO();
		co=coDao.getCourseById(course_id);
		System.out.println("Course Selected or not:::" + co.getCourseInfo());
		panel_1.setPreferredSize(new Dimension(1290,500));
		JXLabel jl =new JXLabel("");
		try{
			System.out.println("Inside the try catch block");
			if(co.getCourseInfo()!=null)
				jl.setText("\n\n"+co.getCourseInfo());
		}catch(Exception e){
			e.printStackTrace();
		}
		jl.setLineWrap(true);
		jl.setFont(new Font("Geneva", Font.BOLD, 16));
		panel_1.add(jl);
		add(panel_1,BorderLayout.CENTER);
	}
}