package main.admin.adminpanel;

import javax.swing.JPanel;

import java.awt.Component;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.JXTextField;

import javax.swing.JToggleButton;

public class StudentSearchPanel extends JPanel {
	
	/**
	 * 
	 */

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
		
	/*	JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 1320, 450);
		add(scrollPane);*/
		table = new JTable(){
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		       public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		           Component component = super.prepareRenderer(renderer, row, column);
		           int rendererWidth = component.getPreferredSize().width;
		           TableColumn tableColumn = getColumnModel().getColumn(column);
		           tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
		           return component;
		        }
		    };
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	//	scrollPane.setViewportView(table);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(Object.class, centerRenderer);
		table.setDefaultRenderer(Integer.class, centerRenderer);
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//resizeColumnWidth(table);
		scrollPane.setBounds(10, 57, 1320, 450);
		add(scrollPane);
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
				Student tempStud=(Student)table.getValueAt(row,-1);
				try{
				StudentUpdateForm form=new StudentUpdateForm(tempStud);
				form.setVisible(true);
				}catch(Exception exc){
					exc.printStackTrace();
				}
		}});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				if(row<0){
					JOptionPane.showMessageDialog(StudentSearchPanel.this, "Select a Student","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				Student tempStud=(Student)table.getValueAt(row,-1);
				try {
					sdbh = new StudentDbHandler();
					sdbh.removeStudent(tempStud.getRollno());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(btnDelete);

	}
}
