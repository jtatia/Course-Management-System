package main.professor.professorframe;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import main.professor.professorDAO.ProfessorDAO;
import main.professor.professorframe.teachTableModel.ButtonRenderer;
import main.professor.professorframe.teachTableModel.JTableButtonMouseListener;
import main.professor.professorframe.teachTableModel.tableModelTeach;
public class CourseListPanel extends JPanel {
	private JTable table;
	private tableModelTeach model=null;
	ProfessorDAO dao;
	/**
	 * Create the panel.
	 */
	public CourseListPanel(String user,ProfessorFrame pf) throws Exception{
		setLayout(new BorderLayout(0, 0));
        TableCellRenderer buttonRenderer = new ButtonRenderer();
		dao = new ProfessorDAO();
		model=new tableModelTeach(dao.getAllCourses(user),user,pf);
		table = new JTable();
		table.setRowHeight(30);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultRenderer(JButton.class,buttonRenderer);
		table.addMouseListener(new JTableButtonMouseListener(table));
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//resizeColumnWidth(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(800);    
		tcm.getColumn(1).setPreferredWidth(200);    
		
		scrollPane.setBounds(10, 57, 1320, 450);
		add(scrollPane, BorderLayout.CENTER);	
	}
}
