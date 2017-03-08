package main.admin.adminpanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.admin.studentdbhandler.StudentDbHandler;
import main.student.student.Student;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXTextField;

import javax.swing.JToggleButton;

public class StudentSearchPanel extends JPanel {
	
	private JXTextField textField;
	
	private JTable table;
	
	private List<Student> list=null;
	
	private StudentDbHandler sdbh=null;
	/**
	 * Create the panel.
	 */
	public StudentSearchPanel()throws Exception {
		setLayout(null);
		setVisible(true);
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1320, 38);
		//panel.setBounds(10, 11, 830, 400);
		add(panel);
		panel.setLayout(null);
		table=new JTable();
		sdbh=new StudentDbHandler();
		list=sdbh.getAllStudent();
		StudentTableModel model=new StudentTableModel(list);
		table.setModel(model);

		
		textField = new JXTextField();
		textField.setBounds(10, 11, 1090, 20);
		textField.setPrompt("Student Search panel.Please enter a String to search.");
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String text=textField.getText();
			try
			{
				//sdbh=new StudentDbHandler();
				list=sdbh.searchStudent(text);
				if(text.trim().equals(""))
				list=new ArrayList<Student>();		
				StudentTableModel model=new StudentTableModel(list);
				table.setModel(model);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(StudentSearchPanel.this,e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
			}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSearch.setBounds(1120, 10, 89, 23);
		panel.add(btnSearch);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try
			{	
			textField.setText("");
			//sdbh=new StudentDbHandler();
			list=sdbh.getAllStudent();
			StudentTableModel model=new StudentTableModel(list);
			table.setModel(model);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(StudentSearchPanel.this,e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
			}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(1230, 10, 89, 23);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 1320, 450);
		add(scrollPane);
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 530, 1320, 42);
		add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton btnAdd = new JButton("Add ");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			new AddStudentForm();
			}
		});
		JButton btnUpdate = new JButton("Update");
		JToggleButton tglbtnSelectMultiple = new JToggleButton("Select Multiple");
		tglbtnSelectMultiple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tglbtnSelectMultiple.isSelected())
				{
					btnUpdate.setEnabled(false);
					table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				}
				else
				{	
					btnUpdate.setEnabled(true);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				}
			}
		});
		tglbtnSelectMultiple.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(tglbtnSelectMultiple);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(btnAdd);
		
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				if(row<0){
					JOptionPane.showMessageDialog(StudentSearchPanel.this, "Select a Student","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				Student tempEmp=(Student)table.getValueAt(row,-1);
				try{
				StudentUpdateForm dialog=new StudentUpdateForm(tempEmp);
				dialog.setVisible(true);
				}catch(Exception exc){
					exc.printStackTrace();
				}
		}});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(btnDelete);

	}
}
