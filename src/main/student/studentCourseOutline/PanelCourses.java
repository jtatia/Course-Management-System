package main.student.studentCourseOutline;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.student.studentcourseoutlinedao.StudentCourseOutlineDAO;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;

import main.student.coursepanel.CoursePanel;
import main.student.student.*;
public class PanelCourses extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelCourses(Student student,CourseOutline co) throws Exception{
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		JLabel lblCoursesTaken = new JLabel("Courses Taken");
		scrollPane.setColumnHeaderView(lblCoursesTaken);
		
		StudentCourseOutlineDAO sctodao = new StudentCourseOutlineDAO(student);
		ArrayList<String> course = sctodao.Coursedata(student);
        DefaultListModel<String> model = new DefaultListModel<>();
		int size = course.size();
		for(int i=0; i<size; i++){
	    	model.addElement(course.get(i));
		}
		JList list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				CoursePanel cp=new CoursePanel();
				JLabel jb=(JLabel)co.panel.getComponent(7);
				JLabel jb_c=(JLabel)co.panel.getComponent(6);
				String course_name=(String)list.getSelectedValue();
				jb.setVisible(true);
				jb_c.setVisible(true);
				jb.setText(course_name);
				CardLayout card=(CardLayout)co.panel_1.getLayout();
				co.panel_2.getComponent(0).setEnabled(true);
				card.show(co.panel_1, "CoursePanel");
			}
		});
		scrollPane.setViewportView(list);
	}
}
