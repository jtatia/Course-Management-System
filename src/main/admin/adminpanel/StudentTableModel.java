package main.admin.adminpanel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import main.student.student.Student;

public class StudentTableModel extends AbstractTableModel{
	
	private static final int ID=0;
	
	private static final int ROLL_NO=1;
	
	private static final int FIRST_NAME=2;
	
	private static final int MIDDLE_NAME=3;
	
	private static final int LAST_NAME=4;
	
	private static final int BATCH=5;
	
	private static final int EMAIL=6;
	
	private static final int SEX=7;
	
	private static final int AGE=8;
	
	private static final int SUBJECT1=9;
	
	private static final int SUBJECT2=10;
	
	private static final int SUBJECT3=11;
	
	private static final int SUBJECT4=12;
	
	private static final int SUBJECT5=13;
	
	private static final int SUBJECT6=14;
	
	private static final int SUBJECT7=15;
	
	private static final int SUBJECT8=16;
	
	private static final int SUBJECT9=17;
	
	private static final int SUBJECT10=18;
	
	
	private String[] columnNames={"ID", "Roll No", "First Name", "Last Name", "Batch", "Email", "Sex", "Age", "Subject 1", "Subject 2", "Subject 3", "Subject 4", "Subject 5", "Subject 6", "Subject 7", "Subject 8", "Subject 9", "Subject 10"};
	private List<Student> list;
	
	public StudentTableModel(List<Student> list)
	{
		this.list=list;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;    			//  returns number of columns
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();		   			// returns number of rows
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		Student stud=list.get(row);			// obtaining an instance of type Student
		
		switch(col)
		{
			case ID :
				return stud.getId();
			case ROLL_NO :
				return stud.getRollno();
			case FIRST_NAME :
				return stud.getFirstname();
			case MIDDLE_NAME :
				return stud.getMiddlename();
			case LAST_NAME :
				return stud.getLastname();
			case BATCH :
				return stud.getBatch();
			case SEX :
				return stud.getSex();
			case AGE :
				return stud.getAge();
			case EMAIL :
				return stud.getEmail();
			case SUBJECT1 :
				return stud.getSubject1();
			case SUBJECT2 :
				return stud.getSubject2();
			case SUBJECT3 :
				return stud.getSubject3();
			case SUBJECT4 :
				return stud.getSubject4();
			case SUBJECT5 :
				return stud.getSubject5();
			case SUBJECT6 :
				return stud.getSubject6();
			case SUBJECT7 :
				return stud.getSubject7();
			case SUBJECT8 :
				return stud.getSubject8();
			case SUBJECT9 :
				return stud.getSubject9();
			case SUBJECT10 :
				return stud.getSubject10();
			case -1:
				return stud;
			default:
				return stud.getRollno();
		}
	}
	public Class getColumnClass(int c)
	{
		return getValueAt(0,c).getClass();		// returns the column class 
	}
}
