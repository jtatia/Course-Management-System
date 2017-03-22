package main.student.coursepanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;

import main.util.assignmentutils.assignment.Assignment;
import main.util.assignmentutils.assignmenttablemodel.AssignmentTableModel;
import main.util.download.Download;
import main.util.filechooser.FileChooser;
import main.util.filedetails.FileDetails;

public class CourseAssignmentPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private List<Assignment> list;
	JTable table;
	
	public CourseAssignmentPanel()
	{
		
		
	}
	
	/*Assume I have a path in my argument*/
	public CourseAssignmentPanel(String path)throws Exception {
		this.setPreferredSize(new Dimension(1300,600));
		//this.setBorder(BorderFactory.createLineBorder(Color.RED));
		path+="assignments/";
		System.out.println("I have entered");
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1320, 38);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblAssignmentsUploaded = new JLabel("Assignments Uploaded :");
		lblAssignmentsUploaded.setBounds(0, 11, 184, 14);
		panel.add(lblAssignmentsUploaded);
		table=new JTable();
		
		list = new ArrayList<Assignment>();
		
		String str[] = FileDetails.getFileList(path);
		for(int i=0;i<str.length;i++)
		{
			Assignment temp = new Assignment();
			temp.setName("\""+str[i]+"\"");
			temp.setPath(path);
			String s[]=FileDetails.getStats(path,temp.getName() );
			temp.setLastModified(s[1]);
			temp.setSize(s[0]);
			list.add(temp);
		}
		
		AssignmentTableModel atm = new AssignmentTableModel(list);
		System.out.println("########   "+list);
		table.setModel(atm);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 510, 1320, 42);
		add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton downloadbtn = new JButton("Download");
		downloadbtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileChooser fc = new FileChooser();
				String path = fc.getDirectoryPath();
				if(table.getSelectedRowCount()>0){
					int[] row = table.getSelectedRows();
					for(int r : row){
						Assignment a = list.get(r);
						String file = a.getPath()+a.getName();
						Download dwn = new Download();
						dwn.downloadFile(file, path);	
					}
				}
				
			}
		});
		
		
		JToggleButton tglbtnSelectMultiple = new JToggleButton("Select Multiple");
		tglbtnSelectMultiple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tglbtnSelectMultiple.isSelected()){
					table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
					
				}
				else
				{
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
									}
			}
		});
	//	tglbtnSelectMultiple.setBounds(10, 530, 200, 20);
		tglbtnSelectMultiple.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(tglbtnSelectMultiple);
		panel_2.add(downloadbtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 1320, 450);
		add(scrollPane);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	}
}
