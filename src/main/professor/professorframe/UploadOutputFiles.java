package main.professor.professorframe;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jcraft.jsch.SftpException;

import main.util.sshcommands.SSHCommands;
import main.util.sshcommands.UsingJsch;
import net.neoremind.sshxcute.exception.TaskExecFailException;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class UploadOutputFiles extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String assignmentFolder;
	private String dir;
	private JPanel contentPane;
	private SSHCommands sshc;
	private JList list;
	private DefaultListModel<String> model;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					UploadOutputFiles frame = new UploadOutputFiles("/home/Btech15/kshitij.cs15/cms/CS225_jimson/uploads/HW1/");

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
	public UploadOutputFiles(String path) {

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

		setTitle("Upload Output Files ");
		setVisible(true);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		sshc = new SSHCommands();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		JLabel lblTestCasesUpload = new JLabel("OUTPUT FILES UPLOAD PANEL");
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
	    
		JLabel lblTestCase = new JLabel("CURRENT OUTPUT FILE");
		lblTestCase.setBounds(127, 17, 169, 14);
		panel_2.add(lblTestCase);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Enter the corresponding input file name");
		textField_1.setBounds(676, 235, 86, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		String filenames="";
		model = new DefaultListModel<>();
		try {
			filenames=sshc.runSingleCommand("ls "+path+"outputFiles/");
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
						UsingJsch.writingFile(dir+"outputFiles/", textArea.getText(), file);
					} catch (SftpException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					textArea.setText("");
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
					JOptionPane.showMessageDialog(UploadOutputFiles.this,"Please enter a valid file name","Error",JOptionPane.ERROR_MESSAGE);
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
				System.out.println("File to be editted >>>>>>>>>>>>>> "+file);					
				fileContent=UsingJsch.readingFile(path+"outputFiles/"+file);
				System.out.println(file);
				textField_1.setText(file);
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
					model.removeElement(file);
					list.revalidate();
					list.repaint();
					sshc.runSingleCommand("rm "+path+"outputFiles/"+file);
				} catch (TaskExecFailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(676, 446, 86, 23);
		panel_2.add(btnDelete);
		
		JLabel lblUploadedInputs = new JLabel("UPLOADED OUTPUTS");
		lblUploadedInputs.setBounds(10, 279, 161, 14);
		panel_2.add(lblUploadedInputs);
		
		JLabel lblFileName = new JLabel("INPUT FILE NAME ");
		lblFileName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFileName.setBounds(667, 204, 111, 30);
		panel_2.add(lblFileName);	
}
}
