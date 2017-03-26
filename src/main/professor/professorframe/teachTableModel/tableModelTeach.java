package main.professor.professorframe.teachTableModel;

import java.awt.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import main.course.coursedao.CourseMappingDAO;
import main.professor.professorDAO.ProfessorDAO;
import main.professor.professorframe.ProfessorFrame;

public class tableModelTeach extends AbstractTableModel{
	private static final int COURSE_NAME=0;
	private static final int COURSE_BUTTON=1;
	private List<String> list;
	private String user;
	private ProfessorFrame pf=null;
	private String columnNames[]={"Course Name","Redirection Buttons"};
	private CourseMappingDAO cmdao=null;
	private ProfessorDAO pdao=null; 
	
	public tableModelTeach(List<String> list,String user,ProfessorFrame pf){
		System.out.println("So Far");
		this.list = list;
		this.user=user;
		this.pf=pf;
		try {
			cmdao=new CourseMappingDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pdao=new ProfessorDAO();
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
								
										String course=jbtn.getActionCommand();
										System.out.println(course+"   on button press");
										System.out.println("   on button press");
										String path=cmdao.getDirectoryPathForProf(course, user);
			                    		System.out.println(path);
										try {
											pf.csp.resetPanes(pdao.getProfByUsername(user), path);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
			                    	
			                    		CardLayout card=(CardLayout)pf.panel.getLayout();
			            			//	pf.settingsPanel.getComponent(0).setEnabled(true);
			            				pf.csp.curr_subject=course;
			            				card.show(pf.panel, "CourseSpecificPanel");
									}
								});
								return jbtn;
		}
		return null;
	}

}
