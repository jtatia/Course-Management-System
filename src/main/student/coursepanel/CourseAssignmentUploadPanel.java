package main.student.coursepanel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import main.admin.adminpanel.addcourse.AddCourse;
import org.jdesktop.swingx.JXTextField;
import main.admin.adminpanel.coursetablemodel.CourseTableModel;
import main.admin.adminpanel.updatecourse.UpdateCourse;
import main.course.course.Course;
import main.course.coursedao.CourseDAO;
import main.course.coursedao.CourseMappingDAO;
import main.student.coursepanel.model.CourseAssignmentUploadTableModel;
import main.student.coursepanel.objects.UploadInfo;
import main.student.student.Student;
import main.util.assignmentutils.assignment.Assignment;
import main.util.filechooser.FileChooser;
import main.util.filedetails.FileDetails;
import main.util.sshcommands.SSHComm;
import main.util.upload.Upload;

import javax.swing.JToggleButton;

public class CourseAssignmentUploadPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JXTextField textField;
//	private JXTextField assignField;
	private JComboBox assignField;
	private JTable table;
    private CourseAssignmentUploadTableModel model;
    private List<UploadInfo> uploadInfo;
    private SSHComm sshc;
	/**
	 * Create the panel.
	 */
	public CourseAssignmentUploadPanel(Student student, String path)throws Exception {
		System.out.println("CourseAssignmentUploadPanel : "+path);
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1320, 38);
		add(panel);
		panel.setLayout(null);
		sshc=new SSHComm();
		table=new JTable();
		textField = new JXTextField();
		textField.setBounds(10, 11, 800, 20);
		textField.setPrompt("Enter path of file to upload");
		panel.add(textField);
		textField.setColumns(9);
	/*	assignField = new JXTextField();
		assignField.setBounds(850,11,100,20);
		assignField.setColumns(2);
		assignField.setPrompt("Enter assign no. or name");
		panel.add(assignField);*/
		System.out.println("ls "+path+"uploads/");
		String str=sshc.SSHClient("ls "+path+"uploads/", sshc);
		str=str.substring(0,str.indexOf("null"));
		sshc.close();
		System.out.println("###"+str+"###");
		String assign[]=str.split("\n");
		assignField=new JComboBox(assign);
		assignField.setBounds(850,11,150,20);
		assignField.setToolTipText("Enter assign no. or name");
		panel.add(assignField);
		assignField.setSelectedItem(null);
		/* adding files into list which then is added into model which is added to table*/
		
        System.out.println(path);
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				String toUpload = textField.getText();
				if(toUpload.equals("")){
					FileChooser chooser = new FileChooser();
					textField.setText(chooser.getFilePath());
				}

					new Thread(){
					
						public void run(){					
					Upload upload = new Upload();
					upload.studentUploadAssignment(textField.getText(), path, (String)assignField.getSelectedItem(), student.getRollno());
					textField.setText("");
					assignField.setSelectedItem(null);
				}}.start();
				}catch(Exception e1){
					e1.printStackTrace();
				
			}}
		});
		btnUpload.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpload.setBounds(1040, 10, 89, 23);
		panel.add(btnUpload);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(){
					public void run(){
				uploadInfo = new ArrayList<UploadInfo>();
				textField.setText("");
				assignField.setSelectedItem(null);
				try {
					String str[] = FileDetails.getFileList(path+"uploads");
					
					JOptionPane optionPane = new JOptionPane("Please Wait...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

					 JDialog dialog = new JDialog();
					dialog.setTitle("Message");
					dialog.setModal(true);
					dialog.setContentPane(optionPane);
					dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					dialog.pack();
					//System.out.println("what about here:::::::");
					dialog.setBounds(500, 300, 350, 150);;
					
					new Thread(){
					public void run()
					{
						try{
						for(int i=0;i<str.length;i++)
						{
							System.out.println(str[i]);
							String str1[] = FileDetails.getFileList(path+"uploads/"+str[i]);			
							for(int j=0;j<str1.length;j++){
								System.out.println(str1[j]);
								if(str1[j].contains(student.getRollno())){
							UploadInfo temp = new UploadInfo();
							temp.setName(str1[j]);
							String s[]=FileDetails.getStats(path+"uploads/"+str[i]+"/", temp.getName());
							temp.setLastModified(s[1]);
							temp.setSize(s[0]);
							uploadInfo.add(temp);
							model = new CourseAssignmentUploadTableModel(uploadInfo);
							table.setModel(model);
			
						}
	
						}
						}
						dialog.dispose();}catch(Exception e){e.printStackTrace();}
					}
					}.start();
					dialog.setVisible(true);
} catch (Exception e2) {
					e2.printStackTrace();
				}
					}}.start();
			}		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRefresh.setBounds(1170, 10, 89, 23);
		panel.add(btnRefresh);
		
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 1320, 550);
		add(scrollPane);
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
	}
}
