package main.student.coursepanel.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import main.course.course.Course;
import main.student.coursepanel.objects.UploadInfo;


public class CourseAssignmentUploadTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private static final int SNO=0;
	private static final int FILE_NAME=1;
	private static final int DATE_MODIFIED=2;
	private static final int FILE_SIZE=3;
	
	private String[] columnNames = {"S.No","File Name","Date Modified", "File Size"};
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
		case SNO:
//			return temp.getSerialNo();
		case FILE_NAME:
			return temp.getName();
		case DATE_MODIFIED:
			return temp.getLastModified();
		case FILE_SIZE:
			return temp.getSize();
		case -1:
			return temp;
		default:
			return temp.getName();
		}
	}
}
