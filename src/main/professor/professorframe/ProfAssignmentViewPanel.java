package main.professor.professorframe;
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
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.professor.professorframe.UploadedAssignments;
import main.util.assignmentutils.assignment.Assignment;
import main.util.assignmentutils.assignmenttablemodel.AssignmentTableModel;
import main.util.download.Download;
import main.util.filechooser.FileChooser;
import main.util.filedetails.FileDetails;
import main.util.upload.Upload;

public class ProfAssignmentViewPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private List<Assignment> list;
	private JTable table;
	private AssignmentTableModel atm;
	private String p;
	
	public ProfAssignmentViewPanel()
	{
		
		
	}
	
	/*Assume I have a path in my argument*/
	
	public ProfAssignmentViewPanel(String path)throws Exception {
		this.setPreferredSize(new Dimension(1300,600));
		//this.setBorder(BorderFactory.createLineBorder(Color.RED));
		path+="uploads/";
		p=path;
		System.out.println("I have entered @ : : : "+p);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1320, 38);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblAssignmentsUploaded = new JLabel("Assignments Submitted :");
		lblAssignmentsUploaded.setBounds(0, 11, 184, 14);
		panel.add(lblAssignmentsUploaded);
		
		table=new JTable();

		list = new ArrayList<Assignment>();
		/*
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
		*/
		atm = new AssignmentTableModel(list);
		table.getSelectionModel().setValueIsAdjusting(true);
		System.out.println("########   "+list);
		table.setModel(atm);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 510, 1320, 42);
		add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
	//	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 57, 1320, 450);
		add(scrollPane);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		ListSelectionModel lsm=table.getSelectionModel();
		lsm.setValueIsAdjusting(true);
		lsm.addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if(event.getValueIsAdjusting()){
	        		
	        	}
	        	else{
		            int row=table.getSelectedRow();
		            String assignmentFolderName=(String) table.getValueAt(row, 0);
		       //   System.out.println("Assignment Folder Name = "+assignmentFolderName);
		            System.out.println(p+"Hello");
		            try {
		            	System.out.println(p+"Hello In Try");
						new UploadedAssignments(p,assignmentFolderName);
						event.getValueIsAdjusting();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        }
	    });
		
		JButton refresh = new JButton("Refresh");
		refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				new Thread(){				
				public void run(){
				try{
				String str[] = FileDetails.getFileList(p);
				list = new ArrayList<Assignment>();
				for(int i=0;i<str.length;i++)
				{
					Assignment temp = new Assignment();
					temp.setName(str[i]);
					temp.setPath(p);
					String s[]=FileDetails.getStats(p,temp.getName() );
					temp.setLastModified(s[1]);
					temp.setSize(s[0]);
					list.add(temp);
					atm = new AssignmentTableModel(list);
					table.setModel(atm);
				}
				
				}catch(Exception ex){
					ex.printStackTrace();
				}
				}}.start();
			}
		});
		panel_2.add(refresh);
	}
}
