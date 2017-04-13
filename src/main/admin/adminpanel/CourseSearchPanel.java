package main.admin.adminpanel;

import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import main.admin.adminpanel.addcourse.AddCourse;

import org.jdesktop.swingx.JXTextField;


import main.admin.adminpanel.coursetablemodel.CourseTableModel;
import main.admin.adminpanel.updatecourse.UpdateCourse;
import main.course.course.Course;
import main.course.coursedao.CourseDAO;
import main.course.coursedao.CourseMappingDAO;

import javax.swing.JToggleButton;

public class CourseSearchPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JXTextField textField;
	private JTable table;
    private CourseTableModel courseTableModel;
    private CourseDAO dao = null;
    private CourseMappingDAO map_dao=null;
    List<Course> course = null;
	/**
	 * Create the panel.
	 */
	public CourseSearchPanel()throws Exception {
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1320, 38);
		add(panel);
		panel.setLayout(null);
		table=new JTable();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		textField = new JXTextField();
		textField.setBounds(10, 11, 1090, 20);
		textField.setPrompt("Course search panel. Please enter the string to search");
		panel.add(textField);
		textField.setColumns(10);
		dao = new CourseDAO();
		map_dao=new CourseMappingDAO();
		course=dao.getAllCourses();
		courseTableModel = new CourseTableModel(course);
		table.setModel(courseTableModel);
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				String toSearch = textField.getText();
				course=dao.searchCourse(toSearch);
				courseTableModel = new CourseTableModel(course);
				table.setModel(courseTableModel);
				table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
				table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
				}catch(Exception e1){
					e1.printStackTrace();
				}
				}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSearch.setBounds(1120, 10, 89, 23);
		panel.add(btnSearch);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				try {
					course=dao.getAllCourses();
					courseTableModel = new CourseTableModel(course);
					table.setModel(courseTableModel);
					table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
					table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRefresh.setBounds(1230, 10, 89, 23);
		panel.add(btnRefresh);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 530, 1320, 42);
		add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddCourse();
			}
		});
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();
				if(row<0){
					JOptionPane.showMessageDialog(CourseSearchPanel.this, "Select a Course","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				Course tempCourse=(Course)table.getValueAt(row,-1);
				try{//System.out.println(tempCourse.getCourseId());
					UpdateCourse form=new UpdateCourse(tempCourse.getCourseId());
					form.setVisible(true);
					}catch(Exception exc){
						exc.printStackTrace();
					}
			}
			
		});
		JToggleButton tglbtnSelectMultiple = new JToggleButton("Select Multiple");
		tglbtnSelectMultiple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tglbtnSelectMultiple.isSelected()){
					table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
					btnUpdate.setEnabled(false);
				}
				else
				{
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					btnUpdate.setEnabled(true);
				}
			}
		});
		tglbtnSelectMultiple.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(tglbtnSelectMultiple);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(btnAdd);
		
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int []index=table.getSelectedRows();
				for(int i=0;i<index.length;i++){
					Course crs=(Course)table.getValueAt(index[i], -1);
					dao.deleteCourse(crs.getCourseId());
					map_dao.deleteEntry(crs.getCourseId());
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 1320, 450);
		add(scrollPane);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		table.setRowHeight(30);
	}
}
