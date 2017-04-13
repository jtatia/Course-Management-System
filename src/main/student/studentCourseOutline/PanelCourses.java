package main.student.studentCourseOutline;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import main.student.studentcourseoutlinedao.StudentCourseOutlineDAO;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import main.course.coursedao.CourseMappingDAO;

import main.student.coursepanel.CoursePanel;
import main.student.student.*;
public class PanelCourses extends JPanel {

	/**
	 * Create the panel.
	 */
	private CourseTableModel model;
	private JTable table;
	private CourseMappingDAO cmdao;
	  CourseOutline co=null;
	  JLabel jb,jb_c;
	public PanelCourses(Student student,CourseOutline courseoutline) throws Exception{
		this.co=courseoutline;
		setLayout(new BorderLayout(0, 0));
		cmdao=new CourseMappingDAO();
		/*JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);*/
		
		JLabel lblCoursesTaken = new JLabel("Courses Taken");
		//scrollPane.setColumnHeaderView(lblCoursesTaken);
		
		StudentCourseOutlineDAO sctodao = new StudentCourseOutlineDAO(student);
		ArrayList<String> course = sctodao.Coursedata(student);
       /* DefaultListModel<String> model = new DefaultListModel<>();
		int size = course.size();
		for(int i=0; i<size; i++){
	    	model.addElement(course.get(i));
		}
		JList list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String course_name=(String)list.getSelectedValue();
				//System.out.println("Course id is::"+course_name);
				//System.out.println("Batch is ::"+student.getBatch());
				String path=cmdao.getDirectoryPath(course_name, student.getBatch());
				
				//System.out.println("Path is::"+path);
				//System.out.println(path);
				co.c_panel.curr_subject=course_name;
				try {
					co.c_panel.resetPanes(student, path);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 jb=(JLabel)co.panel.getComponent(7);
				 jb_c=(JLabel)co.panel.getComponent(6);
				jb.setVisible(true);
				jb_c.setVisible(true);
				jb.setText(course_name);
				CardLayout card=(CardLayout)co.panel_1.getLayout();
				co.panel_2.getComponent(0).setEnabled(true);
				card.show(co.panel_1, "CoursePanel");
			}
		});
		scrollPane.setViewportView(list);*/
		
		TableCellRenderer buttonRenderer = new ButtonRenderer();
		model=new CourseTableModel(course,this,student);
		table = new JTable();
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultRenderer(JButton.class,buttonRenderer);
		table.addMouseListener(new JTableButtonMouseListener(table));
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//resizeColumnWidth(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(800);    
		tcm.getColumn(1).setPreferredWidth(200);    
		
		scrollPane.setBounds(10, 57, 1320, 450);
		scrollPane.setColumnHeaderView(lblCoursesTaken);
		add(scrollPane, BorderLayout.CENTER);
		
		
	}
}
