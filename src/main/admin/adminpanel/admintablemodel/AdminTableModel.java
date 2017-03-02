package main.admin.adminpanel.admintablemodel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import main.admin.admin.Admin;

public class AdminTableModel extends AbstractTableModel{
	
	private static final int SNO=0;
	private static final int USERNAME=1;
	private static final int FIRST_NAME=2;
	private static final int MIDDLE_NAME=3;
	private static final int LAST_NAME=4;
	private static final int AGE=5;
	private static final int SEX=6;
	private static final int EMAIL=7;
	
	
	private String[] columnNames = {"S.No.", "Username", "First Name", "Middle Name", "Last Name", "Age", "Sex", "Email"};
	private ArrayList<Admin> list;
	
	public AdminTableModel(ArrayList<Admin> list)
	{
		this.list=list;
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
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		Admin temp = list.get(row);

		switch (col) {
		case SNO:
			return temp.getSno();
		case USERNAME:
			return temp.getUsername();
		case FIRST_NAME:
			return temp.getFirstname();
		case MIDDLE_NAME:
			return temp.getMiddlename();
		case LAST_NAME:
			return temp.getLastname();
		case AGE:
			return temp.getAge();
		case SEX:
			return temp.getSex();
		case EMAIL:
			return temp.getEmail();
		case -1:
			return temp;
		default:
			return temp.getUsername();
		}
	}

}
