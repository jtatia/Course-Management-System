// Use of migLayout in the top and central pane
package main.student.studentCourseOutline;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import main.student.coursepanel.CoursePanel;
import main.student.settings.SettingsFrame;
import main.student.student.Student;
import main.student.studentcourseoutlinedao.StudentCourseOutlineDAO;
import net.miginfocom.swing.MigLayout;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentCourseOutline extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentCourseOutline frame = new StudentCourseOutline(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public StudentCourseOutline(Student student) throws Exception {
		setTitle("Student Course Outline");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(800,500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel settingsPanel = new JPanel();
		contentPane.add(settingsPanel, BorderLayout.SOUTH);
		
		JButton settingsButton = new JButton("Settings");
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try
			{
			new SettingsFrame(student.getRollno());
			}
			catch(Exception err)
			{
				JOptionPane.showMessageDialog(StudentCourseOutline.this,err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			}
		});
		settingsPanel.add(settingsButton);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			int response = JOptionPane.showConfirmDialog(StudentCourseOutline.this, "Are U Sure U Want to Logout?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
		settingsPanel.add(logoutButton);
		
		JPanel studentInfoPanel = new JPanel();
		contentPane.add(studentInfoPanel, BorderLayout.NORTH);
		studentInfoPanel.setLayout(new MigLayout("", "[][][]", "[][][][]"));
		
		JLabel lblStudentProfileInformation = new JLabel("Student Profile Information  ");
		studentInfoPanel.add(lblStudentProfileInformation, "cell 0 0");
		
		JLabel lblName = new JLabel("Name :");
		studentInfoPanel.add(lblName, "cell 0 1");
		
		JLabel nameLabel = new JLabel("New label");
		studentInfoPanel.add(nameLabel, "cell 1 1");
		nameLabel.setText(student.toString());
		
		JLabel lblRollNo = new JLabel("Roll no : ");
		studentInfoPanel.add(lblRollNo, "cell 0 2");
		
		JLabel rollLabel = new JLabel("New label");
		studentInfoPanel.add(rollLabel, "cell 1 2");
		rollLabel.setText(student.getRollno());
		
		JLabel lblBatchCode = new JLabel("Batch Code : ");
		studentInfoPanel.add(lblBatchCode, "cell 0 3");
		
		JLabel batchLabel = new JLabel("New label");
		studentInfoPanel.add(batchLabel, "cell 1 3");
		batchLabel.setText(student.getBatch());
		
		JPanel coursePanel = new JPanel();
		contentPane.add(coursePanel, BorderLayout.CENTER);
		coursePanel.setLayout(new MigLayout("", "[71px,grow]", "[][14px,grow]"));
		
		JLabel courseLabel = new JLabel("Courses Taken");
		coursePanel.add(courseLabel, "cell 0 0,alignx left,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		coursePanel.add(scrollPane, "cell 0 1,grow");
		
		StudentCourseOutlineDAO sctodao = new StudentCourseOutlineDAO(student);
		ArrayList<String> course = sctodao.Coursedata(student);
        DefaultListModel<String> model = new DefaultListModel<>();
		int size = course.size();
		for(int i=0; i<size; i++){
	    	model.addElement(course.get(i));
		}
		JList<String> list = new JList<String>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				coursePanel.setVisible(false);
				CoursePanel cp=new CoursePanel();
				contentPane.add(cp,BorderLayout.CENTER);
				//cp.setVisible(true);
			}
		});

		scrollPane.setViewportView(list);
	}

}
