package main.util.forgotpassword;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.admin.admin.Admin;
import main.admin.admindao.AdminDAO;
import main.admin.adminpanel.MainAdminPanel;
import main.admin.studentdbhandler.StudentDbHandler;
import main.professor.professor.Professor;
import main.professor.professorDAO.ProfessorDAO;
import main.professor.professorframe.ProfessorFrame;
import main.student.student.Student;
import main.student.studentCourseOutline.CourseOutline;
import main.student.studentCourseOutline.StudentCourseOutline;
import main.student.studentdao.StudentDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ForgotPasswordDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	
	private JTextField userIdTextField;
	
	private JLabel questionLabel;
	
	private JButton submitButton2;
	
	private JButton cancelButton ;
	
	private Student student;
	
	private Admin admin;
	
	private Professor prof;
	
	private StudentDAO studentdao;
	
	private StudentDbHandler sdbh;
	
	private AdminDAO admindao;
	
	private ProfessorDAO professordao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ForgotPasswordDialog dialog = new ForgotPasswordDialog("Student");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ForgotPasswordDialog(String user) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel userIdPanel = new JPanel();
		userIdPanel.setBounds(10, 11, 414, 40);
		contentPanel.add(userIdPanel);
		userIdPanel.setLayout(null);
		
		JLabel lblNewLabel=new JLabel("Enter User Id : ");;
		if(user.equals("Student"))
		lblNewLabel = new JLabel("Enter Roll No : ");
		else if(user.equals("Admin"))
		lblNewLabel = new JLabel("Enter User Name : ");	
		
		lblNewLabel.setBounds(10, 11, 115, 14);
		userIdPanel.add(lblNewLabel);
		
		userIdTextField = new JTextField();
		userIdTextField.setBounds(131, 8, 172, 20);
		userIdPanel.add(userIdTextField);
		userIdTextField.setColumns(10);
		
		JButton submitButton1 = new JButton("Submit");
		submitButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try
			{
				if(user.equals("Student"))
				{
					studentdao=new StudentDAO();							// getting instance of StudentDAO to access database	
					String rollNo=userIdTextField.getText();				// getting the user id from the user id field
					student=studentdao.getStudentByRollno(rollNo.trim());  	// obtaining the reference to the student using the roll no 
					if(student==null)
					{
						// if no such roll no exists 
						JOptionPane.showMessageDialog(ForgotPasswordDialog.this,"Invalid User Id","Error",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						questionLabel.setVisible(true);			// to display the security question
						cancelButton.setEnabled(true);			// enable the buttons so that the user can submit the answer or cancel
						submitButton2.setEnabled(true);				
						questionLabel.setText(student.getSecurityques());	
					}	
				}
				else if(user.equals("Admin"))
				{
					admindao=new AdminDAO();								// getting instance of StudentDAO to access database	
					String username=userIdTextField.getText();				// getting the user id from the user id field
					admin=admindao.getAdminByUserName(username.trim());  	// obtaining the reference to the student using the roll no 
					if(admin==null)
					{
						// if no such roll no exists 
						JOptionPane.showMessageDialog(ForgotPasswordDialog.this,"Invalid User Name","Error",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						questionLabel.setVisible(true);			// to display the security question
						cancelButton.setEnabled(true);			// enable the buttons so that the user can submit the answer or cancel
						submitButton2.setEnabled(true);				
						questionLabel.setText(admin.getSecurityques());	
					}
				}
				
				else if(user.equals("Professor"))
				{
					professordao=new ProfessorDAO();
					String username=userIdTextField.getText();
					prof=professordao.getProfByUsername(username.trim());
					
					if(prof==null)
					{
						// if no such roll no exists 
						JOptionPane.showMessageDialog(ForgotPasswordDialog.this,"Invalid User Name","Error",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						questionLabel.setVisible(true);			// to display the security question
						cancelButton.setEnabled(true);			// enable the buttons so that the user can submit the answer or cancel
						submitButton2.setEnabled(true);				
						questionLabel.setText(prof.getSecurityques());	
					}
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(ForgotPasswordDialog.this,"Error: "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}	
			}
		});
		submitButton1.setBounds(313, 7, 89, 23);
		userIdPanel.add(submitButton1);
		
		JPanel securityPanel = new JPanel();
		securityPanel.setBounds(10, 62, 414, 129);
		contentPanel.add(securityPanel);
		securityPanel.setLayout(null);
		
		JLabel securityQuestionLabel = new JLabel("Security Question : ");
		securityQuestionLabel.setBounds(10, 11, 131, 14);
		securityPanel.add(securityQuestionLabel);
		
		questionLabel = new JLabel("New label");
		questionLabel.setVisible(false);
		questionLabel.setBounds(10, 36, 394, 14);
		securityPanel.add(questionLabel);
		
		JTextArea answerTextArea = new JTextArea();
		answerTextArea.setBounds(10, 61, 394, 57);
		securityPanel.add(answerTextArea);
	
			submitButton2 = new JButton("Submit");
			submitButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				String answer=(answerTextArea.getText()).trim();      // obtaining the user answer
				if(user.equals("Student"))
				{	
					if(answer.equalsIgnoreCase(student.getAnswer())){
						String str=student.getFirstname()+(int)(Math.random()*100);    // generating a new password
						student.setPassword(str);		// setting the new password
						try {
							sdbh=new StudentDbHandler();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sdbh.modifyStudent(student.getRollno(), student.getFirstname(),student.getMiddlename(), student.getLastname(), ""+student.getSex(), student.getAge(),
								student.getEmail(), student.getBatch(),student.getPassword(), student.getSecurityques(), student.getAnswer(), student.getSubject1(), student.getSubject2(),
								student.getSubject3(),student.getSubject4(), student.getSubject5(), student.getSubject6(), student.getSubject7(), student.getSubject8(),
								student.getSubject9(), student.getSubject10(),true);
						JOptionPane.showMessageDialog(ForgotPasswordDialog.this,"Message : Your Password has been reset to - "+str+". Go to your account settings to change your password" ,"Alert :"  ,JOptionPane.INFORMATION_MESSAGE );
						try
						{
							// entering the student course outline using the security answer  
							CourseOutline courseOutline=new CourseOutline(student);
							courseOutline.setVisible(true);
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(ForgotPasswordDialog.this,"Error : "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						// case of invalid security answer
						JOptionPane.showMessageDialog(ForgotPasswordDialog.this,"Message : Security answer did not match !! Access denied." ,"Information"  ,JOptionPane.INFORMATION_MESSAGE );
					}	
				}
				else if(user.equals("Admin"))
				{
					if(answer.equalsIgnoreCase(admin.getAnswer())){
						String str=admin.getFirstname()+(int)(Math.random()*100);    // generating a new password
						admin.setPassword(str);		// setting the new password
						admindao.modifyAdmin(admin);
						JOptionPane.showMessageDialog(ForgotPasswordDialog.this,"Message : Your Password has been reset to - "+str+". Go to your account settings to change your password" ,"Alert :"  ,JOptionPane.INFORMATION_MESSAGE );
						try
						{
							// entering the student course outline using the security answer  
							MainAdminPanel mainAdminPanel=new MainAdminPanel(admin);
							mainAdminPanel.setVisible(true);
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(ForgotPasswordDialog.this,"Error : "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						// case of invalid security answer
						JOptionPane.showMessageDialog(ForgotPasswordDialog.this,"Message : Security answer did not match !! Access denied." ,"Information"  ,JOptionPane.INFORMATION_MESSAGE );
					}
				}	
				
				else if(user.equals("Professor"))
				{
					if(answer.equalsIgnoreCase(prof.getAnswer())){
						String str=prof.getFirstname()+(int)(Math.random()*100);    // generating a new password
						prof.setPassword(str);		// setting the new password
						professordao.modifyProfessor(prof,true);
						JOptionPane.showMessageDialog(ForgotPasswordDialog.this,"Message : Your Password has been reset to - "+str+". Go to your account settings to change your password" ,"Alert :"  ,JOptionPane.INFORMATION_MESSAGE );
						try
						{
							// entering the student course outline using the security answer  
							ProfessorFrame profframe=new ProfessorFrame(prof);
							profframe.setVisible(true);
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(ForgotPasswordDialog.this,"Error : "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						// case of invalid security answer
						JOptionPane.showMessageDialog(ForgotPasswordDialog.this,"Message : Security answer did not match !! Access denied." ,"Information"  ,JOptionPane.INFORMATION_MESSAGE );
					}
				}
				}
			});
			submitButton2.setBounds(215, 202, 78, 23);
			contentPanel.add(submitButton2);
			submitButton2.setActionCommand("OK");
			submitButton2.setEnabled(false);
			getRootPane().setDefaultButton(submitButton2);
		
		
			cancelButton = new JButton("Cancel");
			cancelButton.setEnabled(false);
			cancelButton.setBounds(318, 202, 78, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				dispose();					// if cancel button is pressed dispose of the JDialog 
				setVisible(false);
				}
			});
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
	}
}
