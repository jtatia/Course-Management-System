package main.admin.adminpanel.addcourse;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.admin.adminpanel.AddStudentForm;
import main.course.course.Course;
import main.course.coursedao.CourseDAO;
import main.student.student.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCourse extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private CourseDAO dao = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourse frame = new AddCourse();
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
	public AddCourse() {
		try {
			dao=new CourseDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setTitle("Add Course");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddCourseForm = new JLabel("Add Course Form");
		lblAddCourseForm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddCourseForm.setBounds(138, 29, 180, 14);
		contentPane.add(lblAddCourseForm);
		
		JLabel lblNewLabel = new JLabel("Course ID : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(44, 80, 130, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(184, 78, 204, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCourseName = new JLabel("Course Name : ");
		lblCourseName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCourseName.setBounds(44, 128, 130, 14);
		contentPane.add(lblCourseName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(184, 126, 204, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCourseInfo = new JLabel("Course Info :");
		lblCourseInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCourseInfo.setBounds(44, 175, 130, 14);
		contentPane.add(lblCourseInfo);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(44, 212, 344, 189);
		contentPane.add(textArea);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			Course course=new Course();
			int f=0;
			String course_id=textField.getText();
			if(course_id.equals(""))
			{
				f=1;	
				JOptionPane.showMessageDialog(AddCourse.this,"Enter the Course Id","Alert : ",JOptionPane.WARNING_MESSAGE);
			}
			if(textField_1.getText().equals(""))
			{
				f=1;
				JOptionPane.showMessageDialog(AddCourse.this,"Enter the Course Name","Alert : ",JOptionPane.WARNING_MESSAGE);
			}
			Course c=dao.getCourseById(course_id);
			if(c==null)
			{
				if(f==0)
				{	
					course.setCourseId(textField.getText());
					course.setCourseName(textField_1.getText());
					course.setCourseInfo(textArea.getText());
					dao.addCourse(course);
					JOptionPane.showMessageDialog(AddCourse.this,"New Course has been added","Info : ",JOptionPane.INFORMATION_MESSAGE);
					// once the student has been added set the visibility to false and dispose off the JFrame
					AddCourse.this.setVisible(false);
					AddCourse.this.dispose();
				}
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
