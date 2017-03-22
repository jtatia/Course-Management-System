package main.admin.adminpanel.coursetablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import main.course.course.Course;

public class CourseTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private static final int SNO=0;
	private static final int COURSE_ID=1;
	private static final int COURSE_NAME=2;
	
	private String[] columnNames = {"S.No","Course ID","Course Name"};
	private List<Course> list;
	public CourseTableModel(List<Course> list) {
		super();
		this.list = list;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	@Override
	public int getRowCount() {
		return list.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		Course temp = list.get(row);

		switch (col) {
		case SNO:
			return temp.getSerialNo();
		case COURSE_ID:
			return temp.getCourseId();
		case COURSE_NAME:
			return temp.getCourseName();
		case -1:
			return temp;
		default:
			return temp.getCourseId();
		}
	}
}
