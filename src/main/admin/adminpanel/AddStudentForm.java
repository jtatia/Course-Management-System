package main.admin.adminpanel;

import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.admin.studentdbhandler.StudentDbHandler;
import main.student.student.Student;
import main.student.studentdao.StudentDAO;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AddStudentForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField rollNoTextField;
	private JTextField firstNameTextField;
	private JTextField middleNameTextField;
	private JTextField lastNameTextField;
	private JTextField ageTextField;
	private JTextField emailTextField;
	private JTextField batchTextField;
	private JTextField passwordTextField;
	private JComboBox securityComboBox;
	private JTextField securityAnswerTextField;
	private JTextField sub1TextField;
	private JTextField sub2TextField;
	private JTextField sub3TextField;
	private JTextField sub4TextField;
	private JTextField sub5TextField;
	private JTextField sub6TextField;
	private JTextField sub7TextField;
	private JTextField sub8TextField;
	private JTextField sub9TextField;
	private JTextField sub10TextField;
	private JTextField sexTextField;
	private JScrollPane jsp=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentForm frame = new AddStudentForm();
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
	public AddStudentForm() {
		setTitle("Add Student");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 100, 450, 300);
		setSize(800,570);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JButton submit = new JButton("Submit");
		contentPane.add(submit, BorderLayout.SOUTH);
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
	
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				try
				{
					int f=0;
					/*
					 * f=0 means that every text Field has been correctly filled. 
					 * f=1 means that some required text Fields are still empty indicating that student cannot be added.
					 * */
					StudentDbHandler studentDbHandler=new StudentDbHandler();
					StudentDAO studentDAO=new StudentDAO();
					
					String rollno=rollNoTextField.getText();
					if(rollno.equals(""))
					{
						// roll no is a required field. 
						f=1;
						JOptionPane.showMessageDialog(AddStudentForm.this,"Enter the Student Rollno","Alert : ",JOptionPane.WARNING_MESSAGE);
					}
					
					String firstName=firstNameTextField.getText();
					if(firstName.equals(""))
					{
						// first name of student is a must field
						f=1;
						JOptionPane.showMessageDialog(AddStudentForm.this,"Enter First Name of Student","Alert : ",JOptionPane.WARNING_MESSAGE);
					}
					
					// last name and middle names are optional fields 
					String middleName=middleNameTextField.getText();
					String lastName=lastNameTextField.getText();
					
					int age=0;
					// making sure that age is not left blank, if left blank age=0
					if(!(ageTextField.getText()).equals(""))
					age=Integer.parseInt(ageTextField.getText());
					
					char sex=' ';
					// making sure that sex is not left blank, if blank sex=' '
					if((sexTextField.getText()).length()>0)
					sex=(sexTextField.getText()).charAt(0);
					
					// email is not a required field
					String email=emailTextField.getText();
					
					// batch is a required field
					String batch=batchTextField.getText();
					if(batch.equals(""))
					{
						f=1;
						JOptionPane.showMessageDialog(AddStudentForm.this,"Enter the batch of Student","Alert : ",JOptionPane.WARNING_MESSAGE);
					}
					
					// password is a required field
					String password=passwordTextField.getText();
					if(password.equals(""))
					{
						f=1;
						JOptionPane.showMessageDialog(AddStudentForm.this,"Please set a default password","Alert : ",JOptionPane.WARNING_MESSAGE);
					}
					
					// security ques and ans are required fields
					String securityQuestion=(String)securityComboBox.getSelectedItem();
					if(securityQuestion.equals(""))
					{
						f=1;
						JOptionPane.showMessageDialog(AddStudentForm.this,"Please set a default security question","Alert : ",JOptionPane.WARNING_MESSAGE);
					}
					
					String securityAnswer=securityAnswerTextField.getText();
					if(securityAnswer.equals(""))
					{
						f=1;
						JOptionPane.showMessageDialog(AddStudentForm.this,"Please set a security answer","Alert : ",JOptionPane.WARNING_MESSAGE);
					}
					
					// subjects are not required fields
					String sub1=sub1TextField.getText();
					String sub2=sub2TextField.getText();
					String sub3=sub3TextField.getText();
					String sub4=sub4TextField.getText();
					String sub5=sub5TextField.getText();
					String sub6=sub6TextField.getText();
					String sub7=sub7TextField.getText();
					String sub8=sub8TextField.getText();
					String sub9=sub9TextField.getText();
					String sub10=sub10TextField.getText();
					
					// making sure that there is no student with same roll number already in the database
					// display suitable message if there exists a student with same roll number
					Student stud=studentDAO.getStudentByRollno(rollno);
					if(stud==null)
					{	
						// checking for suitable value of sex character
						if((sex=='M' || sex=='F' || sex=='f' || sex=='m'))
						{	
							// f=0 means all required fields have been filled correctly so the student can be added to the database 
							if(f==0)
							{
								studentDbHandler.addStudent(rollno, firstName, middleName, lastName, sex, age, email, batch, password, securityQuestion, securityAnswer, sub1, sub2, sub3, sub4, sub5, sub6, sub7, sub8, sub9, sub10);
								// show message dialog that the student has been successfully added
								JOptionPane.showMessageDialog(AddStudentForm.this,"New Student has been added","Info : ",JOptionPane.INFORMATION_MESSAGE);
								// once the student has been added set the visibility to false and dispose off the JFrame
								AddStudentForm.this.setVisible(false);
								AddStudentForm.this.dispose();
							}	
						}
						else
						{
							// display suitable message if invalid sex character is given
							JOptionPane.showMessageDialog(AddStudentForm.this,"Invalid Sex Character","Alert : ",JOptionPane.ERROR_MESSAGE);
						}	
					}
					else
					{
						// displaying alert message if there exists a student with same roll number
						JOptionPane.showMessageDialog(AddStudentForm.this,"Roll number "+rollno+" already exists. Cannot add another student with same roll no.","Error : ",JOptionPane.ERROR_MESSAGE);
					}	
				}
				catch(Exception e)
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(AddStudentForm.this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		JPanel panel = new JPanel();
		
		// using MigLayout
		scrollPane.setViewportView(panel);
		panel.setLayout(new MigLayout("", "[][grow][][][][][][][][][]", "[][][][][][][][][][][][][][][][][][][][][][][grow][][][][][][][][]"));
		
		JLabel lblRollNumber = new JLabel("Roll Number :");
		panel.add(lblRollNumber, "cell 0 0,alignx trailing");
		
		rollNoTextField = new JTextField();
		panel.add(rollNoTextField, "cell 1 0,growx");
		rollNoTextField.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name :");
		panel.add(lblFirstName, "cell 0 2,alignx trailing");
		
		firstNameTextField = new JTextField();
		panel.add(firstNameTextField, "cell 1 2,growx");
		firstNameTextField.setColumns(10);
		
		JLabel lblMiddleName = new JLabel("Middle Name :");
		panel.add(lblMiddleName, "cell 0 4,alignx trailing");
		
		middleNameTextField = new JTextField();
		panel.add(middleNameTextField, "cell 1 4,growx");
		middleNameTextField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name :");
		panel.add(lblLastName, "cell 0 6,alignx trailing");
		
		lastNameTextField = new JTextField();
		panel.add(lastNameTextField, "cell 1 6,growx");
		lastNameTextField.setColumns(10);
		
		JLabel lblSex = new JLabel("Sex :");
		panel.add(lblSex, "cell 0 8,alignx trailing");
		
		sexTextField = new JTextField();
		panel.add(sexTextField, "cell 1 8,growx");
		sexTextField.setColumns(10);
		
		JLabel lblAge = new JLabel(" Age :");
		panel.add(lblAge, "cell 0 10,alignx trailing");
		
		ageTextField = new JTextField();
		panel.add(ageTextField, "cell 1 10,growx");
		ageTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Email :");
		panel.add(lblNewLabel, "cell 0 12,alignx trailing");
		
		emailTextField = new JTextField();
		panel.add(emailTextField, "cell 1 12,growx");
		emailTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Batch :");
		panel.add(lblNewLabel_1, "cell 0 14,alignx trailing");
		
		batchTextField = new JTextField();
		panel.add(batchTextField, "cell 1 14,growx");
		batchTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password : ");
		panel.add(lblPassword, "cell 0 16,alignx trailing");
		
		passwordTextField = new JTextField();
		panel.add(passwordTextField, "cell 1 16,growx");
		passwordTextField.setColumns(10);
		
		JLabel lblSecurityQuestion = new JLabel("Security Question :");
		panel.add(lblSecurityQuestion, "cell 0 18,alignx trailing");
		
		/*securityQuestionTextField = new JTextField();*/
		String ques[]={"Where do you live?","Which is your favourite book?","Which is your favourite movie?","Who is your role model?","What time of the day were you born?"};
		securityComboBox=new JComboBox(ques);
		securityComboBox.setSelectedItem(null);
		panel.add(securityComboBox, "cell 1 18,growx");
		/*securityQuestionTextField.setColumns(10);*/
		
		JLabel lblSecurityAnswer = new JLabel("Security Answer :");
		panel.add(lblSecurityAnswer, "cell 0 20,alignx trailing");
		
		securityAnswerTextField = new JTextField();
		panel.add(securityAnswerTextField, "cell 1 20,growx");
		securityAnswerTextField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 1 21 2 50,grow");
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("List of Subjects");
		lblNewLabel_2.setBounds(10, 11, 111, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblSubject = new JLabel("Subject 1 :");
		lblSubject.setBounds(10, 36, 69, 14);
		panel_1.add(lblSubject);
		
		JLabel lblSubject_1 = new JLabel("Subject 2 :");
		lblSubject_1.setBounds(10, 61, 69, 14);
		panel_1.add(lblSubject_1);
		
		JLabel lblSubject_2 = new JLabel("Subject 3 :");
		lblSubject_2.setBounds(10, 86, 69, 14);
		panel_1.add(lblSubject_2);
		
		JLabel lblSubject_3 = new JLabel("Subject 4 :");
		lblSubject_3.setBounds(10, 111, 69, 14);
		panel_1.add(lblSubject_3);
		
		JLabel lblSubject_4 = new JLabel("Subject 5 :");
		lblSubject_4.setBounds(10, 136, 69, 14);
		panel_1.add(lblSubject_4);
		
		JLabel lblNewLabel_3 = new JLabel("Subject 6 :");
		lblNewLabel_3.setBounds(228, 36, 69, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Subject 7 :");
		lblNewLabel_4.setBounds(228, 61, 69, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Subject 8 :");
		lblNewLabel_5.setBounds(228, 86, 69, 14);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Subject 9 :");
		lblNewLabel_6.setBounds(228, 111, 69, 14);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Subject 10 :");
		lblNewLabel_7.setBounds(228, 136, 69, 14);
		panel_1.add(lblNewLabel_7);
		
		sub1TextField = new JTextField();
		sub1TextField.setBounds(87, 33, 125, 20);
		panel_1.add(sub1TextField);
		sub1TextField.setColumns(10);
		
		sub2TextField = new JTextField();
		sub2TextField.setBounds(87, 58, 125, 20);
		panel_1.add(sub2TextField);
		sub2TextField.setColumns(10);
		
		sub3TextField = new JTextField();
		sub3TextField.setBounds(89, 83, 123, 20);
		panel_1.add(sub3TextField);
		sub3TextField.setColumns(10);
		
		sub4TextField = new JTextField();
		sub4TextField.setBounds(89, 108, 123, 20);
		panel_1.add(sub4TextField);
		sub4TextField.setColumns(10);
		
		sub5TextField = new JTextField();
		sub5TextField.setBounds(89, 133, 125, 20);
		panel_1.add(sub5TextField);
		sub5TextField.setColumns(10);
		
		sub6TextField = new JTextField();
		sub6TextField.setBounds(298, 33, 125, 20);
		panel_1.add(sub6TextField);
		sub6TextField.setColumns(10);
		
		sub7TextField = new JTextField();
		sub7TextField.setBounds(298, 58, 125, 20);
		panel_1.add(sub7TextField);
		sub7TextField.setColumns(10);
		
		sub8TextField = new JTextField();
		sub8TextField.setBounds(298, 83, 125, 20);
		panel_1.add(sub8TextField);
		sub8TextField.setColumns(10);
		
		sub9TextField = new JTextField();
		sub9TextField.setBounds(298, 108, 125, 20);
		panel_1.add(sub9TextField);
		sub9TextField.setColumns(10);
		
		sub10TextField = new JTextField();
		sub10TextField.setBounds(298, 133, 125, 20);
		panel_1.add(sub10TextField);
		sub10TextField.setColumns(10);
	}
}