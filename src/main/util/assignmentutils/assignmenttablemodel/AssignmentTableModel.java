package main.util.assignmentutils.assignmenttablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import main.util.assignmentutils.assignment.Assignment;

public class AssignmentTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int NAME = 0;
	private static final int SIZE = 1;
	private static final int LAST_MODIFIED = 2;
	
	private String[] columnNames = {"Name", "Size", "Last Modified"};
	private List<Assignment> list;
	
	public AssignmentTableModel(List<Assignment> list) {
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
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		Assignment temp = list.get(row);

		switch (col) {
		case NAME:
			return temp.getName();
		case SIZE:
			return temp.getSize();
		case LAST_MODIFIED:
			return temp.getLastModified();
		case -1:
			return temp;
		default:
			return temp.getName();
		}
	}

	

}
