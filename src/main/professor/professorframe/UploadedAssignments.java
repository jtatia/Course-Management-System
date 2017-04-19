package main.professor.professorframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import main.util.CSVfiles.CSVfiles;
import main.util.assignmentutils.assignment.Assignment;
import main.util.assignmentutils.assignmenttablemodel.AssignmentTableModel;
import main.util.assignmentutils.assignmenttablemodel.AssignmentTableModelC;
import main.util.download.Download;
import main.util.filechooser.FileChooser;
import main.util.filedetails.FileDetails;
import main.util.sshcommands.SSHComm;
import main.util.sshcommands.SSHCommands;
import main.util.sshcommands.UsingJsch;
import main.util.upload.Upload;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import main.util.codetester.*;

import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

public class UploadedAssignments extends JFrame {

	private Upload upload=null;
	private JPanel contentPane;
	private JTable table;
	private List<Assignment> list;
	ArrayList<Assignment> list1;
	private SSHCommands sshc;
	private AssignmentTableModel atm;
	private AssignmentTableModelC atmc;
	private String path="";
//	private JavaCompiler jc = new JavaCompiler();
    private JavaCompiler jc = new JavaCompiler();
    private PythonCompiler pc =new PythonCompiler();
    private CCompiler cc = new CCompiler();
    private CppCompiler cp = new CppCompiler();
	public int model_mode = 0;
	public static String Logpath;
	/**
	 * sLaunch the application.
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
		setTitle("UPLOADS - "+assignmentFolderName);
		setVisible(true);
		String call = p;
		System.out.println("PART 1:-"+p+"Assignment folder name:- "+assignmentFolderName);
		p=p+assignmentFolderName+"/";
		System.out.println("PART 2:-"+p);
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
				System.out.println("HERRRRRRE");
				table.clearSelection();
				new Thread(){				
					public void run(){
					try{
						System.out.println("PATH"+path);
					String str[] = FileDetails.getFileList(path);
					list = new ArrayList<Assignment>();
					for(int i=0;i<str.length;i++)
					{
						Assignment temp = new Assignment();
						System.out.println("Problem happens after this");
						System.out.println("modelmode= "+model_mode);
						if((model_mode ==1 && (str[i].contains(".java")||str[i].contains(".py")||str[i].contains(".c")||str[i].contains(".cpp")))||(model_mode ==0 && str[i].contains(".")))
						{
						temp.setName(str[i]);
						temp.setPath(path);
						String s[]=FileDetails.getStats(path,temp.getName());
						temp.setLastModified(s[1]);
						temp.setSize(s[0]);
						temp.setStatus("Not tested");
						temp.setMarks(0);
						list.add(temp);
						}
						if(model_mode == 1)
						{
						list1 = CSVfiles.ReadMarksFile(path) ;
						atmc = new AssignmentTableModelC(list1);
						table.setModel(atmc);
						}
						else
						{
							atm = new AssignmentTableModel(list);
							table.setModel(atm);
						}
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
//				System.out.println("here");
			//	if(table.getSelectedRow() > 0){
	//				System.out.println("inside");
					int[] rows = table.getSelectedRows();
					new Thread() {
						public void run(){
							try {
								System.out.println("!!!!!!!!!!!!!!!!!!!!!!\n"+path+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
								String str[] = FileDetails.getFileList(path);
								int c=0;
								for(int i=0;i<str.length;i++){
								if(!str[i].equals("logFiles1"))
								c++;
								}
								if(c==str.length){
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}			
							
							//added
							
							JOptionPane optionPane = new JOptionPane("Please Wait...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

							 JDialog dialog = new JDialog();
							dialog.setTitle("Message");
							dialog.setModal(true);
							dialog.setContentPane(optionPane);
							dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
							dialog.pack();
							//System.out.println("what about here:::::::");
							dialog.setBounds(500, 300, 350, 150);;
							
							//added
							
							new Thread(){
								public void run()
								{try{
							for(int r : rows){
								Assignment a = list.get(r);
								try {System.out.println("calling");
									test(a.getPath(),a.getName());
								} catch (Exception e) {
									e.printStackTrace();
								}
						//		atmc = new AssignmentTableModelC(list);
							//	table.setModel(atmc);
							}
							
							dialog.dispose();
								}catch(Exception e)
								{
									e.printStackTrace();
								}
								}
							}.start();
							dialog.setVisible(true);
							//System.out.println("I am hereeeeee:::::");
							
							
						}
					}.start();
				
			}
		});
		bottomPanel.add(btnTestFiles);
		btnTestFiles.setEnabled(false);
		
		JButton btnLogFiles = new JButton("Log Files");
		btnLogFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					if(table.getSelectedRowCount() < 1)
						JOptionPane.showMessageDialog(UploadedAssignments.this,"Please Select atleast Single person","Error",JOptionPane.ERROR_MESSAGE);
				//	SavePathForFiles obj = new SavePathForFiles(call,assignmentFolderName);
				//	obj.setVisible(true);
					else {
					int[] rows = table.getSelectedRows();
					FileChooser fc = new FileChooser();
					try{
					new Thread() {
									
									public void run(){
						           for(int r : rows){
										Assignment a = list.get(r);
										String x =a.getName();
										String file = a.getPath()+"logFiles/"+x.substring(0,x.lastIndexOf('.'))+".txt";
										/*try {
											String list = sshc.runSingleCommand("ls"+path+"logFiles/");
											if(list.equals(""))
							JOptionPane.showMessageDialog(UploadedAssignments.this,"No files exist","Error",JOptionPane.ERROR_MESSAGE);
											if(!list.contains(a.getName()))
													continue;
										} catch (TaskExecFailException e) {
											e.printStackTrace();
										}
									*/	Download dwn = new Download();
										dwn.downloadFile(file, fc.getDirectoryPath(),a);								
									}}
								}.start();
					}catch(Exception e){
						e.printStackTrace();
					}
			}}
		});
		bottomPanel.add(btnLogFiles);
		btnLogFiles.setEnabled(false);
		
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel(" ASSIGNMENT NAME : "+assignmentFolderName);
		topPanel.add(lblNewLabel);
		
		JButton btnUploadOutputFile = new JButton("Upload Output Files");
		btnUploadOutputFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UploadOutputFiles(path);
			}
		});
		JButton btnUploadOutputProgram = new JButton("Upload Output Program");
		btnUploadOutputProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				upload=new Upload();
				FileDialog fd=new FileDialog(UploadedAssignments.this,"Upload Output File",FileDialog.LOAD);
				fd.setVisible(true);
				String filename=fd.getFile();
				String directory=fd.getDirectory();
				if(filename!=null && directory!=null)
				{
					String file_type=filename.substring(filename.lastIndexOf(".")+1);
					if(file_type.equals("java")||file_type.equals("py")||file_type.equals("c")||file_type.equals("cpp")){
						upload.uploadFile(directory+filename, path+filename, filename);
						String inp[] = null;
						try {
							inp = FileDetails.getFileList(path+"inputFiles/");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        int length = inp.length;
				    	String error = "Not tested";
				        if(length ==0){
				        	error = "No input Files present";
				        }						
						int status;
						try{
						if(file_type.equals("java"))

						status = jc.compile(path,filename,false);            

						else if(file_type.equals("py"))
							status = 0; //needs to be replaced for python           
						else if(file_type.equals("cpp"))
							status = cp.compile(path,filename);
						else
							status = cc.compile(path,filename);            
						for(int i =0; i<length; i++)
						{
						  if(status == 0){						
						  int x = 1;
						  if(file_type.equals("java"))
								x = jc.execute1(path,filename,inp[i]);               
						  else if(file_type.equals("py"))
								x = pc.execute1(path,filename,inp[i]);               
						  else if(file_type.equals("cpp"))
								x = cp.execute1(path,filename,inp[i]);               
						  else
								x = cc.execute1(path,filename,inp[i]);
						  if(x == 0){
							  error = "Successful";
						  }
						  else if (x == 1)
							  error = "Runtime Error";
						  else
							  error = "Timed out";
						  }
						  else{
							  error = "Compile time error";
							  break;
						  }
						}
						SSHCommands.runSingleCommand("rm "+filename);
						if(file_type.contains("java"))
							SSHCommands.runSingleCommand("rm "+filename.substring(0, filename.lastIndexOf("."))+"class");
						JOptionPane.showMessageDialog(UploadedAssignments.this, error);
					}catch(Exception e){
						error = "Exception occurred";
					}
						}
					else{
						JOptionPane.showMessageDialog(UploadedAssignments.this, "Please upload only .java,.py,.cpp,.c files");
					}
				}
			}
		});
		JButton btnUploadTestCases = new JButton("Upload Test Cases");
		btnUploadTestCases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UploadTestCases utc = new UploadTestCases(path);
				utc.setVisible(true);
			}
		});
		JCheckBox chckbxEnableCompiler = new JCheckBox("Enable Compiler");
		topPanel.add(chckbxEnableCompiler);
		chckbxEnableCompiler.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(chckbxEnableCompiler.isSelected())
				{
					model_mode = 1;
					btnLogFiles.setEnabled(true);
					btnTestFiles.setEnabled(true);
					btnUploadTestCases.setEnabled(true);
					btnUploadOutputProgram.setEnabled(true);
					btnUploadOutputFile.setEnabled(true);
				}
				else
				{
					model_mode = 0;
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
		
	//	list = new ArrayList<Assignment>();
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
	//	atm = new AssignmentTableModel(list);
	//	table.setModel(atm);
		table.setRowHeight(30);
	}
	
	/**FUnction takes path and name of file. path is till 'assigname/'. it first generates an array containing all the marks
	 * It then sends file for compiling. if it compiles then it sends for execution. if executes it sends for checking outputs
	 * and then deletes all extra files and finally has 2 parameters error and marks */
	
	private void test(String path, String name) throws Exception {
		//System.out.println("WWWWWWWWWWWWWWWWWWWWWRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");        
		System.out.println("PATH="+path+"\n"+"NAME="+name);
		String inp[] = FileDetails.getFileList(path+"inputFiles/");
        int length = inp.length;
        if(length ==0){
        	//error log
  //      	System.out.println("ERRRORR");
        }
        UploadTestCases utc = new UploadTestCases(path);
        utc.getMarksContent(path);
        //String content = utc.marksContent;
        ArrayList<Integer> marks = new ArrayList<Integer>();
		for(int i=0;i<length;i++){
			String contain = utc.getMarksFromName(inp[i]);
			marks.add(Integer.parseInt(contain));
//			System.out.println("MMMMMMAAAAAAAAAARRRRRRRRRRRRRRRKKKKKKKKKSSSSSSSS="+marks);
		}
		
        Assignment assign = new Assignment();
		assign.setName(name);
		assign.setPath(path);
		String s[]=FileDetails.getStats(path,name);
		assign.setLastModified(s[1]);
		assign.setSize(s[0]);
		String log = "";
	    int marksOfOutput = 0;
		String error = "Successful";
		String file_type=name.substring(name.lastIndexOf(".")+1);
		int status;
		System.out.println("file type"+file_type);
		for(int i =0; i<length; i++)
			{
			if(file_type.equals("java"))
				status = jc.compile(path,name);            
				else if(file_type.equals("py"))
				status = 0; //needs to be replaced for python           
				else if(file_type.equals("cpp"))
				status = cp.compile(path,name);
				else
				status = cc.compile(path,name);            
				
			  if(status == 0){
				  int x = 1;
					if(file_type.equals("java"))
					x = jc.execute(path,name,inp[i]);               
					else if(file_type.equals("py"))
					x = pc.execute(path,name,inp[i]);               
					else if(file_type.equals("cpp"))
					x = cp.execute(path,name,inp[i]);               
					else
					x = cc.execute(path,name,inp[i]);               
			  if(x == 0){
			  String outputFile = path +"out.txt";
			  System.out.println("$$$$$$$$$$$outputPAth=="+outputFile);
			  String output = path + "outputFiles/"+inp[i]; //all output files submitted by prof stored in outputFiles dir with naming:- out_i.txt
			  System.out.println("OUTPUT!@!@@##$$:: "+output);
			  System.out.println("MARKS SENT:"+marks.get(i));
			  FileOutputMatcher fom = new FileOutputMatcher(outputFile,output,marks.get(i));
			  marksOfOutput  += fom.CheckOutputs();
			  fom.DeleteFiles();
			  System.out.println("current stud marks="+marksOfOutput);
			  }
			  else if (x == 1)
				  error = "Runtime Error";
			  else
				  error = "Timed out";
			  }
			  else{
				  error = "Compile time error";
					if(file_type.equals("java"))
						log += jc.errormessage;
					else if(file_type.equals("py"))
					log += pc.errormessage;               
					else if(file_type.equals("cpp"))
					log += cp.errormessage;               
					else
					log += cc.errormessage;               
			  break;
			  }
				if(file_type.equals("java"))
					log += jc.errormessage;
				else if(file_type.equals("py"))
				log += pc.errormessage;               
				else if(file_type.equals("cpp"))
				log += cp.errormessage;               
				else
				log += cc.errormessage;               
		  }
//		assign.setMarks(marksOfOutput);
//		assign.setStatus(error);
		System.out.println("Final\n"+marksOfOutput+"\n"+error);
		//System.out.println("SHouldWORKSSSJSJKJSDKSKADKSJDJLKSADLKSAD");
		UsingJsch.writingFile(path+"logFiles/", log, name.substring(0,name.lastIndexOf('.'))+".txt");
		CSVfiles.WriteMarksFile(path,name, marksOfOutput, error);
		//writing marks and status to new file
		//writeMarksAnderror(path,name,marksOfOutput,error);
	}
}
