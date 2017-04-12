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
import main.util.codetester.*;

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
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class UploadedAssignments extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private List<Assignment> list;
	private AssignmentTableModel atm;
	private String path="";
	private JavaCompiler jc = new JavaCompiler();
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
						atm = new AssignmentTableModel(list);
						table.setModel(atm);
					}
					
					}catch(Exception ex){
						ex.printStackTrace();
					}
					}}.start();
				}
		});
		bottomPanel.add(btnNewButton);
		
		JButton btnTestFiles = new JButton("Test Files");
		btnTestFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("here");
			//	if(table.getSelectedRow() > 0){
					System.out.println("inside");
					int[] rows = table.getSelectedRows();
					new Thread() {
						
						public void run(){
							for(int r : rows){
								Assignment a = list.get(r);
								try {System.out.println("calling");
									list.add(test(a.getPath(),a.getName()));
								} catch (Exception e) {
									e.printStackTrace();
								}
								atm = new AssignmentTableModel(list);
								table.setModel(atm);
							}
						}
					}.start();
				
			}
		});
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
	
	private Assignment test(String path, String name) throws Exception {
		String inp[] = FileDetails.getFileList(path+"inputFiles/");
        int length = inp.length;
        if(length ==0){
        	//error log
        	System.out.println("ERRRORR");
        }
        ArrayList<Integer> marks = new ArrayList<Integer>();
		Assignment assign = new Assignment();
		assign.setName(name);
		assign.setPath(path);
		String s[]=FileDetails.getStats(path,name);
		assign.setLastModified(s[1]);
		assign.setSize(s[0]);
		String marks_file = path + "marks.txt";  //all marks per question stored in a txt file in same directory
		System.out.println("INSIDE          TEST               METHOD");
		System.out.println("Marks path="+marks_file);
		BufferedReader br = new BufferedReader(new FileReader(marks_file));
		String mark = br.readLine();
		System.out.println("MARKS");
		while(mark!=null){                         //Storing marks in an array -> transfer method to global
			int conversion = Integer.parseInt(mark);
			System.out.println(conversion);
			marks.add(conversion);
			mark=br.readLine();
		}
		br.close();
		int marksOfOutput = 0;
		String error = "Successful";
		for(int i =0; i<length; i++){
			System.out.println("Compiling\n"+path+"===="+name);
			int status = jc.compile(path,name);            //compiling
			  if(status == 0){                        //-->if compiles
			  int x = jc.execute(path,name,inp[length]);                             //  -->execute
			  System.out.println("EXecute"+inp[length]);
			  if(x == 0){
			  String outputFile = path +"output.txt";
			  System.out.println("outputPAth=="+outputFile);
			  String output = path + "outputFiles/out_"+(length+1)+".txt"; //all output files submitted by prof stored in outputFiles dir with naming:- out_i.txt
			  System.out.println("output"+output);
			  FileOutputMatcher fom = new FileOutputMatcher(outputFile,output,marks.get(length));
			  marksOfOutput  += fom.CheckOutputs();
			  System.out.println("current stud marks"+marksOfOutput);
			  }
			  else
				  error = jc.getErrormessage();
			  }
			  else
				  error = jc.getErrormessage();
		}
		assign.setMarks(marksOfOutput);
		assign.setStatus(error);
		System.out.println("Final\n"+marksOfOutput+"\n"+error);
		return assign;
	}
}
