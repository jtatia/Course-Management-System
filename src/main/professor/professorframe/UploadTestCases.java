package main.professor.professorframe;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jcraft.jsch.SftpException;

import main.util.login.LoginPortal;
import main.util.sshcommands.SSHComm;
import main.util.sshcommands.SSHCommands;
import main.util.sshcommands.UsingJsch;
import main.util.upload.Upload;
import net.neoremind.sshxcute.exception.TaskExecFailException;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;

public class UploadTestCases extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private SSHCommands sshc;
	private UsingJsch usingJsch=null;
	private boolean flag=true;
	private JList list;
	private DefaultListModel<String> model;
	private String currentFile="";
	File f;
	public static String marksContent="";
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UploadTestCases frame = new UploadTestCases("CS225_jimson","HW1");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UploadTestCases(String mainDir,String assignmentDir) {
		setTitle("Upload Test Cases ");
		setVisible(true);
		@SuppressWarnings("deprecation")
		ArrayList<String> []ar=(ArrayList<String>[])new ArrayList<?>[3];
		for(int i=0;i<3;i++)
		{
			ar[i]=new ArrayList<String>();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		String currentDir = System.getProperty("user.dir");
		sshc = new SSHCommands();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		JLabel lblTestCasesUpload = new JLabel("TEST CASES  UPLOAD PANEL");
		panel.add(lblTestCasesUpload);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnSubmitTestCases = new JButton("SUBMIT TEST CASES");
		btnSubmitTestCases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f=new File("marks.txt");
				FileWriter fw=null;
				BufferedWriter bw=null;
				try  {
					fw=new FileWriter(f);
					bw= new BufferedWriter(fw);
					bw.write(marksContent);
					System.out.println("Done");
				} catch (IOException e) {
					e.printStackTrace();
				}
				finally
				{
					try {
						if (bw != null)
							bw.close();
						if (fw != null)
							fw.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				File dir = new File(currentDir);
				  File[] directoryListing = dir.listFiles();
				  if (directoryListing != null) {
				    for (File child : directoryListing) {
				    	if(child.getName().startsWith("input")||child.getName().startsWith("marks"))
				    	{
				    		System.out.println(child.getName());
				    		System.out.println(child.toPath());
				    		Upload up=new Upload();
					        up.uploadFile(currentDir+"/"+child.getName(),"/home/stud/btech/cse/2015/kshitij.cs15/cms/"+mainDir+"/uploads/"+assignmentDir+"/inputFile/"+child.getName(),child.getName());
				    	}	
				    } 
				  } else {
				    // Handle the case where dir is not really a directory.
				    // Checking dir.isDirectory() above would not be sufficient
				    // to avoid race conditions with another process that deletes
				    // directories.
				  }
			}
		});
		panel_1.add(btnSubmitTestCases);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 42, 647, 214);
	//	panel_2.add(textArea);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(10, 42, 647, 214);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    panel_2.add(scroll);
	    
		JLabel lblTestCase = new JLabel("Test Case");
		lblTestCase.setBounds(127, 17, 98, 14);
		panel_2.add(lblTestCase);
		
		JLabel lblMarks = new JLabel("Marks");
		lblMarks.setBounds(695, 17, 46, 14);
		panel_2.add(lblMarks);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Specify File name ");
		textField_1.setBounds(676, 168, 86, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(676, 42, 86, 77);
		panel_2.add(textField);
		textField.setColumns(10);
		
		String filenames="";
		model = new DefaultListModel<>();
		try {
			filenames=sshc.runSingleCommand("ls /home/stud/btech/cse/2015/kshitij.cs15/cms/"+mainDir+"/uploads/"+assignmentDir+"/inputFile/");
		} catch (TaskExecFailException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String names[]=filenames.split("\n");
		for(int i=0;i<names.length;i++)
		{
			model.addElement(names[i]);
		}
		list = new JList(model);
		list.setBounds(10, 314, 647, 155);
		JScrollPane listScroll = new JScrollPane(list);
		panel_2.add(listScroll);
		listScroll.setBounds(10, 314, 647, 155);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String text=textArea.getText();
			
			if(text!=null && !text.trim().equals(""))
			{
				FileWriter fw=null;
				BufferedWriter bw=null;
				currentFile=(textField_1.getText()).trim();
				if(!currentFile.equals(""))
				{
					if(!currentFile.endsWith(".txt"))
						currentFile=currentFile+".txt";
					File f=new File(currentFile);
					
					try  {
						fw=new FileWriter(f);
						bw= new BufferedWriter(fw);
						bw.write(text);
						System.out.println("Done");
					} catch (IOException e) {
						e.printStackTrace();
					}
					finally
					{
						try {
							if (bw != null)
								bw.close();
							if (fw != null)
								fw.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
					textArea.setText("");
					setMarksFromName(currentFile,textField.getText());
					
					textField.setText("");
					textField_1.setText("");
					if(!model.contains(currentFile))
					{	
						ar[0].add(text);
						ar[1].add(currentFile);
						ar[2].add(getMarksFromName(currentFile));
						model.addElement(currentFile);
						list.revalidate();
						list.repaint();
					}
					else
					{
						int index=ar[1].indexOf(currentFile);
						ar[0].add(index, text);
						ar[1].add(index,currentFile);
						ar[2].add(index,getMarksFromName(currentFile));
					}	
				}
				else
				{
					JOptionPane.showMessageDialog(UploadTestCases.this,"Please enter a valid file name","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			}
		});
		button.setBounds(676, 378, 86, 23);
		panel_2.add(button);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String file=(String) list.getSelectedValue();
				String fileContent="";
				String marks="";
				System.out.println(file);
				textField.setText(getMarksFromName(file));
				if(ar[1].contains(file))
				{
					int index=ar[1].indexOf(file);
					fileContent=ar[0].get(index);
					marks=ar[2].get(index);
				}	
				else
				{	
				usingJsch=new UsingJsch();		
				fileContent=usingJsch.readingFile("/home/stud/btech/cse/2015/kshitij.cs15/cms/"+mainDir+"/uploads/"+assignmentDir+"/inputFile/"+file);
				marksContent=usingJsch.readingFile("/home/stud/btech/cse/2015/kshitij.cs15/cms/"+mainDir+"/uploads/"+assignmentDir+"/inputFile/marks.txt");
				marks=getMarksFromName(marksContent);
				usingJsch.close();
				}
				System.out.println(fileContent);	
				textField_1.setText(file);
				textField.setText(marks);
				textArea.setText(fileContent);
			}
		});
		btnEdit.setBounds(676, 412, 86, 23);
		panel_2.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String file=(String) list.getSelectedValue();
					deleteMarksFromName(file);
					model.removeElement(file);
					list.revalidate();
					list.repaint();
					sshc.runSingleCommand("rm /home/stud/btech/cse/2015/kshitij.cs15/cms/"+mainDir+"/uploads/"+assignmentDir+"/inputFile/"+file);
				} catch (TaskExecFailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(676, 446, 86, 23);
		panel_2.add(btnDelete);
		
		JLabel lblUploadedInputs = new JLabel("UPLOADED INPUTS");
		lblUploadedInputs.setBounds(10, 279, 161, 14);
		panel_2.add(lblUploadedInputs);
		
		JLabel lblFileName = new JLabel("File Name ");
		lblFileName.setBounds(695, 143, 86, 14);
		panel_2.add(lblFileName);
		
		
		
		/*IMPORTANT NOTE FOR FUTURE : In this program the objective is to delete all the inputs files created on the local machine of 
		 * the end user. This must be implemented at the time of the window closing. The problem i was facing in deleting the files at 
		 * the time of window closing was an error message that the file is already being used by some process inspite of the fact that
		 * all the reader and writers had been closed. Then I tried the same thing using the window closed method. Still the same 
		 * problem. Finally resolved it using the window open event. This is because when the window is opened these files are not 
		 * being used in any process. Thus we have an advantage as well -- the inputs uploaded will remain at the local machine until 
		 * the window is opened again to upload new inputs. 
		 * 
		 * Below is the code which iterates through the files in the present working directory and deletes all the files which start 
		 * with "input" e.g. input1.txt, input2.txt*/
		
		this.addWindowListener(new WindowAdapter(){			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				File dir = new File(currentDir);
				  File[] directoryListing = dir.listFiles();
				  if (directoryListing != null) {
				    for (File child : directoryListing) {
				    	if(child.getName().startsWith("input") || child.getName().startsWith("marks"))
				    	{
				    		System.out.println(child.getName());
				    		System.out.println(child.toPath());
				    		try {
							    Files.delete(child.toPath());
							} catch (NoSuchFileException x) {
							    System.err.format("%s: no such" + " file or directory%n", currentDir+"\\"+child.getName());
							} catch (DirectoryNotEmptyException x) {
							    System.err.format("%s not empty%n", currentDir+"\\"+child.getName());
							} catch (IOException x) {
							    // File permission problems are caught here.
							    System.err.println(x);
							}
				    	}	
				    }
				    
				    File marksLocal=new File("marks.txt");
				    usingJsch=new UsingJsch();
					String fileContent="";
					fileContent=usingJsch.readingFile("/home/stud/btech/cse/2015/kshitij.cs15/cms/"+mainDir+"/uploads/"+assignmentDir+"/inputFile/marks.txt");
					usingJsch.close();
					System.out.println(fileContent);
					if(!fileContent.equals(""))
						marksContent=fileContent;
					else
					{
						marksContent="";
					}
				    FileWriter fw=null;
				    BufferedWriter bw=null;
				    try  {
						fw=new FileWriter(marksLocal);
						bw= new BufferedWriter(fw);
						bw.write(marksContent);
						System.out.println("Done");
					} catch (IOException e) {
						e.printStackTrace();
					}
					finally
					{
						try {
							if (bw != null)
								bw.close();
							if (fw != null)
								fw.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
					}
				   else {
				    // Handle the case where dir is not really a directory.
				    // Checking dir.isDirectory() above would not be sufficient
				    // to avoid race conditions with another process that deletes
				    // directories.
				  }
			}
        });	
}

private static void getMarksContent()
{
	BufferedReader br = null;
	FileReader fr = null;
	String currentDir=System.getProperty("user.dir");;
	try {

		fr = new FileReader(currentDir+"\\marks.txt");
		br = new BufferedReader(fr);
		String sCurrentLine;
		br = new BufferedReader(fr);

		while ((sCurrentLine = br.readLine()) != null) {
			marksContent=marksContent+sCurrentLine;
		}

	} catch (IOException e) {

		e.printStackTrace();

	} finally {

		try {

			if (br != null)
				br.close();

			if (fr != null)
				fr.close();

		} catch (IOException ex) {

			ex.printStackTrace();

		}

	}
}

private static String getMarksFromName(String file)
{
	String marks="";
	System.out.println(marksContent);
	int index=marksContent.indexOf(file);
	marks=marksContent.substring(index+file.length()+2);
	marks=marks.substring(0, marks.indexOf("_"));
	return marks;
}

private static void setMarksFromName(String file,String m)
{
	String marks="";
	System.out.println(file+"aks");
	int index=marksContent.indexOf(file);
	if(index!=-1)
	{	
		marks=marksContent.substring(index+file.length()+2);
		marks=marks.substring(marks.indexOf("_"));
		System.out.println("aks "+marksContent.substring(0,index+file.length()+2)+" "+m+" "+marks);
		marksContent=marksContent.substring(0,index+file.length()+2)+m+marks;
	}
	else
	{
		marksContent=marksContent+file+"=="+m+"_";
	}	

	System.out.println("After set Marks call\n"+marksContent);
}

private static void deleteMarksFromName(String file)
{
	String marks="";
	int index=marksContent.indexOf(file);
	if(index!=-1)
	{	
		marks=marksContent.substring(index);
		marks=marks.substring(marks.indexOf("_")+1);
		marksContent=marksContent.substring(0, index)+marks;
	}
	System.out.println("After Delete Marks call\n"+marksContent);
}
}