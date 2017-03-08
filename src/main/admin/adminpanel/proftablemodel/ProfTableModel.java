package main.admin.adminpanel.proftablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import main.professor.professor.*;

public class ProfTableModel extends AbstractTableModel{
	private static final int OBJECT_COL=-1;
	
	private static final int ID=0;
	
	private static final int USER_NAME=1;
	
	private static final int FIRST_NAME=2;
	
	private static final int MIDDLE_NAME=3;
	
	private static final int LAST_NAME=4;
	
	private static final int EMAIL=5;
	
	private static final int SEX=6;
	
	private static final int COURSE_ID1=7;
	
	private static final int COURSE_ID2=8;
	
	private static final int COURSE_ID3=9;
	
	private static final int COURSE_ID4=10;
	
	private static final int COURSE_ID5=11;
	
	private String column_names[]={"S.No.","Username","First Name","Middle Name","Last Name","Email","Sex","Course ID1","Course ID2","Course ID3","Course ID4","Course ID5"};
	
	private List<Professor> pr_list;
	
	public ProfTableModel(List<Professor> list){
		pr_list=list;
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return column_names.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return pr_list.size();
	}
	
	@Override
	public Class<?> getColumnClass(int col){
		return getValueAt(0,col).getClass();
	}
	
	@Override
	public String getColumnName(int row){
		return column_names[row];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		Professor prof=pr_list.get(row);
		switch(col){
			case OBJECT_COL:return prof;
			case ID:return prof.getSno();
			case FIRST_NAME:return prof.getFirstname();
			case MIDDLE_NAME:return prof.getMiddlename();
			case LAST_NAME:return prof.getLastname();
			case EMAIL:return prof.getEmail();
			case SEX:return prof.getSex();
			case COURSE_ID1:return prof.getCourseid1();
			case COURSE_ID2:return prof.getCourseid2();
			case COURSE_ID3:return prof.getCourseid3();
			case COURSE_ID4:return prof.getCourseid4();
			case COURSE_ID5:return prof.getCourseid5();
			default:return prof.getFirstname();
		}
	}
}
