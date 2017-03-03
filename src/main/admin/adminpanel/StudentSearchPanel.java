package main.admin.adminpanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
import javax.swing.JToggleButton;

public class StudentSearchPanel extends JPanel {
	private JTextField textField;
	
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
		panel.setBounds(10, 11, 1320, 40);
		//panel.setBounds(10, 11, 830, 400);
		add(panel);
		panel.setLayout(null);
		table=new JTable();
		list=new ArrayList<Student>();
		sdbh=new StudentDbHandler();
		list=sdbh.getAllStudent();
		StudentTableModel model=new StudentTableModel(list);
		table.setModel(model);
		
		textField = new JTextField();
		textField.setBounds(0, 11, 1080, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String text=textField.getText();
			list=new ArrayList<Student>();
			try
			{
				sdbh=new StudentDbHandler();
				if(text!=null && text.trim().length()>0)
				{
					list=sdbh.searchStudent(text);
				}	
				else
				{
					list=sdbh.getAllStudent();
				}
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
		btnSearch.setBounds(1100, 9, 104, 23);
		panel.add(btnSearch);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try
			{	
			textField.setText("");
			list=new ArrayList<Student>();
			sdbh=new StudentDbHandler();
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
		btnNewButton.setBounds(1220, 10, 97, 23);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 1320, 450);
		add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10,530, 1280,40);
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
			new StudentUpdateForm();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(btnDelete);

	}
}
