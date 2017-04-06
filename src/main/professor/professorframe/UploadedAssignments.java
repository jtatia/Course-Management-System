package main.professor.professorframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.util.assignmentutils.assignment.Assignment;
import main.util.assignmentutils.assignmenttablemodel.AssignmentTableModel;
import main.util.download.Download;
import main.util.filechooser.FileChooser;
import main.util.filedetails.FileDetails;

import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class UploadedAssignments extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private List<Assignment> list;
	private AssignmentTableModel atm;
	private String path="";
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UploadedAssignments frame = new UploadedAssignments("","");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 */
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public UploadedAssignments(String p,String assignmentFolderName) throws Exception {
		setTitle("UPLOADS -  <dynamic>");
		setVisible(true);
		p=p+assignmentFolderName.substring(1,assignmentFolderName.length()-1)+"/";
		path=p;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Select Multiple");
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tglbtnNewToggleButton.isSelected()){
					table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
					
				}
				else
				{
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
									}
			}
		});
		bottomPanel.add(tglbtnNewToggleButton);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
								dwn.downloadFile(file,path,a);	
							}
						}
					}.start();
				}
			}
		});
		bottomPanel.add(btnDownload);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.clearSelection();
				new Thread(){				
					public void run(){
					try{
					String str[] = FileDetails.getFileList(path);
					list = new ArrayList<Assignment>();
					for(int i=0;i<str.length;i++)
					{
						Assignment temp = new Assignment();
						temp.setName("\""+str[i]+"\"");
						temp.setPath(path);
						String s[]=FileDetails.getStats(path,temp.getName());
						temp.setLastModified(s[1]);
						temp.setSize(s[0]);
						list.add(temp);
					}
					atm = new AssignmentTableModel(list);
					table.setModel(atm);
					}catch(Exception ex){
						ex.printStackTrace();
					}
					}}.start();
				}
		});
		bottomPanel.add(btnNewButton);
		
		JButton btnTestFiles = new JButton("Test Files");
		bottomPanel.add(btnTestFiles);
		btnTestFiles.setEnabled(false);
		
		JButton btnLogFiles = new JButton("Log Files");
		bottomPanel.add(btnLogFiles);
		btnLogFiles.setEnabled(false);
		
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel(" ASSIGNMENT NAME : "+assignmentFolderName);
		topPanel.add(lblNewLabel);
		
		JButton btnUploadOutputFile = new JButton("Upload Output File");
		JButton btnUploadOutputProgram = new JButton("Upload Output Program");
		JButton btnUploadTestCases = new JButton("Upload Test Cases");
		JCheckBox chckbxEnableCompiler = new JCheckBox("Enable Compiler");
		topPanel.add(chckbxEnableCompiler);
		chckbxEnableCompiler.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(chckbxEnableCompiler.isSelected())
				{
					btnLogFiles.setEnabled(true);
					btnTestFiles.setEnabled(true);
					btnUploadTestCases.setEnabled(true);
					btnUploadOutputProgram.setEnabled(true);
					btnUploadOutputFile.setEnabled(true);
				}
				else
				{
					btnLogFiles.setEnabled(false);
					btnTestFiles.setEnabled(false);
					btnUploadTestCases.setEnabled(false);
					btnUploadOutputProgram.setEnabled(false);
					btnUploadOutputFile.setEnabled(false);
				}	
			}
			
		});
		
		btnUploadTestCases.setToolTipText("Click to upload test Cases");
		topPanel.add(btnUploadTestCases);
		btnUploadTestCases.setEnabled(false);
		
		btnUploadOutputProgram.setToolTipText("To upload the desired output program. You may also use the \"Upload Output File\" button to upload the .txt Output file. ");
		topPanel.add(btnUploadOutputProgram);
		btnUploadOutputProgram.setEnabled(false);
		
		
		btnUploadOutputFile.setToolTipText("To upload the desired .txt output file. You may also use the \"Upload Output Program\" button to upload the Output Program. ");
		topPanel.add(btnUploadOutputFile);
		btnUploadOutputFile.setEnabled(false);
		
		JLabel lblNewLabel_1 = new JLabel("");
		topPanel.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		list = new ArrayList<Assignment>();
	/*	String str[] = FileDetails.getFileList(path);
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
		table.setModel(atm);
		table.setRowHeight(30);
	}
}
