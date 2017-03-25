package main.professor.professorframe.teachTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import main.professor.professorDAO.ProfessorDAO;

public class tableModelTeach extends AbstractTableModel{
	private static final int COURSE_NAME=0;
	private static final int COURSE_BUTTON=1;
	private List<String> list;
	private String columnNames[]={"Course Name","Redirection Buttons"};
	
	public tableModelTeach(List<String> list){
		System.out.println("So Far");
		this.list = list;
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
								jbtn.addActionListener(new ActionListener(){
									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO Auto-generated method stub
										System.out.println("Hello World");
									}
								});
								return jbtn;
		}
		return null;
	}

}
