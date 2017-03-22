package main.student.coursepanel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
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
import main.util.upload.Upload;

import javax.swing.JToggleButton;

public class CourseAssignmentUploadPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JXTextField textField;
	private JXTextField assignField;
	private JTable table;
    private CourseAssignmentUploadTableModel model;
    private CourseMappingDAO dao = null;
    private List<UploadInfo> uploadInfo;
	/**
	 * Create the panel.
	 */
	public CourseAssignmentUploadPanel(Student student, String path)throws Exception {
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1320, 38);
		add(panel);
		panel.setLayout(null);
		table=new JTable();
		textField = new JXTextField();
		textField.setBounds(10, 11, 850, 20);
		textField.setPrompt("Enter path of file to upload");
		panel.add(textField);
		textField.setColumns(9);
		assignField = new JXTextField();
		assignField.setBounds(820,11,100,20);
		assignField.setPrompt("Enter assign no. or name");
		panel.add(assignField);
		/* adding files into list which then is added into model which is added to table*/
		
        uploadInfo = new ArrayList<>();
        
		String str[] = FileDetails.getFileList(path);
		for(int i=0;i<str.length;i++)
		{
			Assignment temp = new Assignment();
			temp.setName(str[i]);
			temp.setPath(path);
			String s[]=FileDetails.getStats(path, "\""+str[i]+"\"");
			temp.setLastModified(s[1]);
			temp.setSize(s[0]);
		//	list.add(temp);
		}
		
		
		
		model = new CourseAssignmentUploadTableModel(uploadInfo);
		table.setModel(model);
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				String toUpload = textField.getText();
				if(toUpload.equals("")){
					FileChooser chooser = new FileChooser();
					textField.setText(chooser.getFilePath());
				}
				else
				{
					Upload upload = new Upload();
					upload.studentUploadAssignment(textField.getText(), path, assignField.getText(), student.getRollno());
				}
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		btnUpload.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpload.setBounds(1120, 10, 89, 23);
		panel.add(btnUpload);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				assignField.setText("");
				try {
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRefresh.setBounds(1230, 10, 89, 23);
		panel.add(btnRefresh);
		
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 1320, 550);
		add(scrollPane);
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

	}
}
