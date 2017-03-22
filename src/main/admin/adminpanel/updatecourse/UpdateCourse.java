package main.admin.adminpanel.updatecourse;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import main.course.course.Course;
import main.course.coursedao.CourseDAO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateCourse extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private CourseDAO dao = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCourse frame = new UpdateCourse("CS201");
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
	public UpdateCourse(String course_id) {
		//System.out.println(course_id);
		try {
			dao=new CourseDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Course course=dao.getCourseById(course_id);
		setTitle("Update Course");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddCourseForm = new JLabel("Update Course Form");
		lblAddCourseForm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddCourseForm.setBounds(138, 29, 180, 14);
		contentPane.add(lblAddCourseForm);
		
		JLabel lblNewLabel = new JLabel("Course ID : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(44, 78, 130, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(course_id);//changed
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(184, 78, 204, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblCourseName = new JLabel("Course Name : ");
		lblCourseName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCourseName.setBounds(44, 128, 130, 14);
		contentPane.add(lblCourseName);
		
		textField_1 = new JTextField();
		textField_1.setText(course.getCourseName());
		textField_1.setBounds(184, 126, 204, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCourseInfo = new JLabel("Course Info :");
		lblCourseInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCourseInfo.setBounds(44, 175, 130, 14);
		contentPane.add(lblCourseInfo);
		 
		JTextArea textArea = new JTextArea();
		JScrollPane jsp=new JScrollPane(textArea);
		textArea.setLineWrap(true);
		textArea.setText(course.getCourseInfo());
		//textArea.setBounds(44, 212, 344, 189);
		jsp.setBounds(44,212,344,189);
		contentPane.add(jsp);
		//contentPane.add(textArea);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			Course course=new Course();
			int f=0;
			if(textField_1.getText().equals(""))
			{
				f=1;
				JOptionPane.showMessageDialog(UpdateCourse.this,"Course Name cannot be left empty","Alert : ",JOptionPane.WARNING_MESSAGE);
			}
			if(f==0)
			{	
				course.setCourseName(textField_1.getText());
				course.setCourseInfo(textArea.getText());
				dao.updateCourse(course);
				JOptionPane.showMessageDialog(UpdateCourse.this,"Course has been updated","Info : ",JOptionPane.INFORMATION_MESSAGE);
				// once the student has been added set the visibility to false and dispose off the JFrame
				UpdateCourse.this.setVisible(false);
				UpdateCourse.this.dispose();
			}
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSubmit.setBounds(116, 427, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancel.setBounds(227, 428, 89, 23);
		contentPane.add(btnCancel);
	}
}
