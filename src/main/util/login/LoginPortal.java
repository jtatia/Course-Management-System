/*This class makes the GUI of the login portal of the course Management System*/
/*import the required packages*/
package main.util.login;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import main.util.home.*;
public class LoginPortal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private JTextField textField;
	
	private JPasswordField passwordField;
	
	private JFrame currentObject;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPortal frame = new LoginPortal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public LoginPortal() {
		currentObject=this;
		setTitle("Login Portal");
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
		});
		btnLogin.setBounds(172, 160, 74, 23);
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
		btnCancel.setBounds(266, 160, 74, 23);
		contentPane.add(btnCancel);
	}
}
