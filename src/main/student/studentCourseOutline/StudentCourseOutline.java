// Use of migLayout in the top and central pane
package main.student.studentCourseOutline;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import main.student.coursepanel.CoursePanel;
import main.student.student.Student;
import net.miginfocom.swing.MigLayout;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

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
	 */
	public StudentCourseOutline(Student student) {
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
		settingsPanel.add(settingsButton);
		
		JButton logoutButton = new JButton("Logout");
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
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				coursePanel.setVisible(false);
				CoursePanel cp=new CoursePanel();
				contentPane.add(cp,BorderLayout.CENTER);
				//cp.setVisible(true);
			}
		});
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Demo Course"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
	}

}
