package main.student.studentCourseOutline;

import java.awt.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

import main.course.coursedao.CourseMappingDAO;
import main.professor.professorDAO.ProfessorDAO;
import main.professor.professorframe.ProfessorFrame;
import main.student.student.Student;

public class CourseTableModel extends AbstractTableModel{
	private static final int COURSE_NAME=0;
	private static final int COURSE_BUTTON=1;
	private List<String> list;
	private String user;
	private ProfessorFrame pf=null;
	private String columnNames[]={"Course Name","Redirection Buttons"};
	private CourseMappingDAO cmdao=null;
	private Student student=null; 
	private PanelCourses pc=null;
	
	public CourseTableModel(List<String> list, PanelCourses pc, Student student){
		this.pc=pc;
		this.student=student;
		this.list = list;
		this.user=user;
		
		try {
			cmdao=new CourseMappingDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String getColumnName(int col)
	{
		return columnNames[col];
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	
	@Override
	public Class<?> getColumnClass(int c)
	{
		return getValueAt(0,c).getClass();// returns the column class
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		switch(col){
			case COURSE_NAME:return list.get(row);
			case COURSE_BUTTON:final JButton jbtn = new JButton("Go to Course Page");
								jbtn.setActionCommand(list.get(row));
								jbtn.addActionListener(new ActionListener(){
									@Override
									public void actionPerformed(ActionEvent arg0) {
								
										String course_name=jbtn.getActionCommand();
										System.out.println(course_name+"   on button press");
										//System.out.println("   on button press");
										String path=cmdao.getDirectoryPath(course_name, student.getBatch());
										pc.co.c_panel.curr_subject=course_name;
			                    		System.out.println(path);
			                    		try {
			            					pc.co.c_panel.resetPanes(student, path);
			            				} catch (Exception e) {
			            					// TODO Auto-generated catch block
			            					e.printStackTrace();
			            				}
			                    		
			                    		pc.jb=(JLabel)pc.co.panel.getComponent(7);
			           				    pc.jb_c=(JLabel)pc.co.panel.getComponent(6);
			           				 pc.jb.setVisible(true);
			         				pc.jb_c.setVisible(true);
			         				pc.jb.setText(course_name);
			         				CardLayout card=(CardLayout)pc.co.panel_1.getLayout();
			        				pc.co.panel_2.getComponent(0).setEnabled(true);
			        				card.show(pc.co.panel_1, "CoursePanel");
									}
								});
								return jbtn;
		}
		return null;
	}

}
