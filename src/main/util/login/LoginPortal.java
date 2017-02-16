/*This class makes the GUI of the login portal of the course Management System*/
/*import the required packages*/
package main.util.login;

import main.admin.admin.Admin;
import main.admin.admindao.AdminDAO;
import main.admin.adminpanel.AdminPanel;
import main.student.student.*;
import main.student.studentCourseOutline.StudentCourseOutline;
import main.student.studentdao.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import main.util.forgotpassword.ForgotPasswordDialog;
import main.util.home.*;
public class LoginPortal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private JTextField textField;
	
	private JPasswordField passwordField;
	
	private JFrame currentObject;

	
	public LoginPortal(String str) {
		currentObject=this;
		setTitle(str+" Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name  : ");
		lblUserName.setBounds(71, 63, 91, 14);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password  :");
		lblPassword.setBounds(71, 108, 91, 14);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(172, 60, 168, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(172, 105, 168, 20);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(str.equals("Student"))
					
				{	
					
					try
					{
						//creating a default student object
						Student student = new Student();
						// obtaining the roll number 
						String rollno=textField.getText();
						// obtaining the password
						char[] pass=passwordField.getPassword();
						String password = new String(pass);
						student.setRollno(rollno);
						student.setPassword(password);
						// creating studentDAO instance
						StudentDAO dao=new StudentDAO();
						// checking password
						int value=dao.passwordChecker(student);
						switch(value)
						{
							case 0:
								// invalid user name
								JOptionPane.showMessageDialog(LoginPortal.this,"Invalid User Name","Error",JOptionPane.ERROR_MESSAGE);
								break;
							case 1:
								// invalid password
								JOptionPane.showMessageDialog(LoginPortal.this,"Invalid Password","Error",JOptionPane.ERROR_MESSAGE);
								break;
							case 2:
								// everything is fine. Login successful
								currentObject.setVisible(false);
								currentObject.dispose();
								// obtaining student by roll no
								Student stud=dao.getStudentByRollno(student.getRollno());
								StudentCourseOutline studentCourseOutline=new StudentCourseOutline(stud);
								studentCourseOutline.setVisible(true);
								// Student GUI to be added here 
						}	
					}
					catch(Exception ex)
					{
						// In case of Execptions
						JOptionPane.showMessageDialog(LoginPortal.this,"Error : "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				
				else if(str.equals("Admin"))
				{
					
					try
					{
						//creating a default admin object
						Admin admin = new Admin();
						// obtaining the username 
						String username=textField.getText();
						// obtaining the password
						char[] pass=passwordField.getPassword();
						String password = new String(pass);
						admin.setUsername(username);
						admin.setPassword(password);
						// creating AdminDAO instance
						AdminDAO dao=new AdminDAO();
						// checking password
						int value=dao.passwordChecker(admin);
						switch(value)
						{
							case 0:
								// invalid user name
								JOptionPane.showMessageDialog(LoginPortal.this,"Invalid User Name","Error",JOptionPane.ERROR_MESSAGE);
								break;
							case 1:
								// invalid password
								JOptionPane.showMessageDialog(LoginPortal.this,"Invalid Password","Error",JOptionPane.ERROR_MESSAGE);
								break;
							
							/*
							 * Add case 2 according to needs and also create the getAdminByUsername() method in 
							 * AdminDAO if needed
							 * */
							 case 2:
								// everything is fine. Login successful
								currentObject.setVisible(false);
								currentObject.dispose();
								// obtaining student by roll no
								Admin ad=dao.getAdminByUsername(username);
								AdminPanel adminpanel=new AdminPanel(ad);
								adminpanel.setVisible(true);
								// Student GUI to be added here
								   
								 
						}	
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
						// In case of Execptions
						JOptionPane.showMessageDialog(LoginPortal.this,"Error : "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnLogin.setBounds(216, 160, 74, 23);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// if cancel button is pressed
				currentObject.setVisible(false);
				currentObject.dispose();
				Home home;
				try {
					home = new Home();
					home.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCancel.setBounds(300, 160, 74, 23);
		contentPane.add(btnCancel);
		
		JButton btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ForgotPasswordDialog dialog=new ForgotPasswordDialog();	
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			}
		});
		btnForgotPassword.setBounds(71, 160, 135, 23);
		contentPane.add(btnForgotPassword);
	}
}
