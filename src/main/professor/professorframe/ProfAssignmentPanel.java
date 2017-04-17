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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;

import main.student.coursepanel.CourseAssignmentUploadPanel;
import main.util.assignmentutils.assignment.Assignment;
import main.util.assignmentutils.assignmenttablemodel.AssignmentTableModel;
import main.util.download.Download;
import main.util.filechooser.FileChooser;
import main.util.filedetails.FileDetails;
import main.util.upload.Upload;

public class ProfAssignmentPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private List<Assignment> list;
	JTable table;
	private AssignmentTableModel atm;
	private String p;
	private String filepath="";
	
	public ProfAssignmentPanel()
	{
		
		
	}
	
	/*Assume I have a path in my argument*/
	
	public ProfAssignmentPanel(String path)throws Exception {
		this.setPreferredSize(new Dimension(1300,600));
		//this.setBorder(BorderFactory.createLineBorder(Color.RED));
		path+="assignments/";
		p=path;
		System.out.println("I have entered @ : : : "+p);
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
		System.out.println("########   "+list);
		table.setModel(atm);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 510, 1320, 42);
		add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
		
		JButton downloadbtn = new JButton("Download");
		downloadbtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileChooser fc = new FileChooser();
				String path = fc.getDirectoryPath();
				if(table.getSelectedRowCount()>0){
					int[] row = table.getSelectedRows();
					 new Thread(){
						
						public void run(){
							for(int r : row){
								Assignment a = list.get(r);
								String file = a.getPath()+a.getName();
								Download dwn = new Download();
								dwn.downloadFile(file, path,a);	
							}
						}
					}.start();
					
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
		
		
	
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			// add functionality here
				filepath="";
				try{
						FileChooser chooser = new FileChooser();
						filepath=chooser.getFilePath();
					
					if(!filepath.equals(""))
					{
					System.out.println(filepath+" ********************* "+p);
					new Thread(){
					public void run(){					
					Upload upload = new Upload();
					upload.professorUploadFile(filepath, p, "");
					JOptionPane.showMessageDialog(ProfAssignmentPanel.this,"Upload done successfully.","Info",JOptionPane.INFORMATION_MESSAGE);
						}}.start();
					}
						/*new Thread(){
							public void run(){					
<<<<<<< HEAD
								Upload upload = new Upload();
								upload.professorUploadFile(textField.getText(),p,"assignments");
								textField.setText("");
							}
						}.start();
					}
				}catch(Exception e1){
=======
						Upload upload = new Upload();
						upload.professorUploadFile(textField.getText(),p,"");
						textField.setText("");
						JOptionPane.showMessageDialog(ProfAssignmentPanel.this,"Upload done successfully.","Info",JOptionPane.INFORMATION_MESSAGE);
							}}.start();*/
					
					}catch(Exception e1){
						JOptionPane.showMessageDialog(ProfAssignmentPanel.this,"Upload failed.","Erro",JOptionPane.ERROR_MESSAGE);
>>>>>>> 00f016908b10b8e4d356ad9f47bb57b9a112ecd5
						e1.printStackTrace();
				}
			}
		});
		panel_2.add(refresh);
		panel_2.add(btnUpload);

	
	//	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 57, 1310, 450);
		add(scrollPane);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
	}
}
