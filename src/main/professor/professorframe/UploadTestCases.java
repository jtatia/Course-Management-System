package main.professor.professorframe;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jcraft.jsch.ChannelSftp;
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

	private String assignmentFolder;
	private String dir;
	private JPanel contentPane;
	private JTextField textField;
	private SSHCommands sshc;
	
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

					UploadTestCases frame = new UploadTestCases("/home/Btech15/kshitij.cs15/cms/CS225_jimson/uploads/HW1/");

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
	public UploadTestCases(String path) {

		/*
		 * dir stores the directory name 
		 * For eg. if path=/home/Btech15/kshitij.cs15/cms/CS225_jimson/uploads/HW1/
		 * 		   then dir=cms/CS225_jimson/uploads/HW1/
		 * 			and assignmentFolder=HW1
		 * */
		assignmentFolder=path.substring(0,path.length()-1);
		assignmentFolder=assignmentFolder.substring(assignmentFolder.lastIndexOf("/")+1);
		dir=path.substring(path.indexOf("cms"));
		dir=dir.substring(0,dir.lastIndexOf("/"))+"/";
		System.out.println("#########"+dir);
		System.out.println("#########"+assignmentFolder);

		setTitle("Upload Test Cases ");
		setVisible(true);

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
			filenames=sshc.runSingleCommand("ls "+path+"inputFiles/");
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
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String text=textArea.getText();
			
			if(text!=null && !text.trim().equals(""))
			{
				String file=(textField_1.getText()).trim();
				if(!file.equals(""))
				{
					if(!file.endsWith(".txt"))
						file=file+".txt";
					try {
						UsingJsch.writingFile(dir+"inputFiles/", textArea.getText(), file);
						setMarksFromName(file,textField.getText());
						UsingJsch.writingFile(dir, marksContent, "marks.txt");
					} catch (SftpException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					textArea.setText("");
					
					textField.setText("");
					textField_1.setText("");
					if(!model.contains(file))
					{	
						model.addElement(file);
						list.revalidate();
						list.repaint();
					}	
				}
				else
				{
					JOptionPane.showMessageDialog(UploadTestCases.this,"Please enter a valid file name","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			}
		});
		btnSubmit.setBounds(676, 378, 86, 23);
		panel_2.add(btnSubmit);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String file=(String) list.getSelectedValue();
				String fileContent="";
				String marks="";
				System.out.println("File to be editted >>>>>>>>>>>>>> "+file);
				textField.setText(getMarksFromName(file));					
				fileContent=UsingJsch.readingFile(path+"inputFiles/"+file);
				marks=getMarksFromName(file);
				System.out.println(file);
				textField.setText(getMarksFromName(file));
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
					sshc.runSingleCommand("rm "+path+"inputFiles/"+file);
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
		
		
		
		
		this.addWindowListener(new WindowAdapter(){			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
					
					String fileContent="";
					System.out.println("window open "+path);
					fileContent=UsingJsch.readingFile(path+"marks.txt");
					UsingJsch.close();
					System.out.println("window open "+fileContent);
					marksContent=fileContent;	
					

			}
        });	
}

public static void getMarksContent()
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

public static String getMarksFromName(String file)
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
