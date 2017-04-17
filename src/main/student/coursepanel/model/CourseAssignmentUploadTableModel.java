package main.student.coursepanel.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import main.course.course.Course;
import main.student.coursepanel.objects.UploadInfo;


public class CourseAssignmentUploadTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final int FILE_NAME=0;
	private static final int DATE_MODIFIED=1;
	private static final int FILE_SIZE=2;
	private static final int ASSIGNMENT_NAME=3;
	
	private String[] columnNames = {"File Name","Date Modified", "File Size","Assignment Name"};
	private List<UploadInfo> list;
	public CourseAssignmentUploadTableModel(List<UploadInfo> uploadInfo) {
		super();
		this.list = uploadInfo;
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
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		UploadInfo temp = list.get(row);

		switch (col) {
		case FILE_NAME:
			return temp.getName();
		case DATE_MODIFIED:
			return temp.getLastModified();
		case FILE_SIZE:
			return temp.getSize();
		case ASSIGNMENT_NAME:
			return temp.getParentDir();
		case -1:
			return temp;
		default:
			return temp.getName();
		}
	}
}
