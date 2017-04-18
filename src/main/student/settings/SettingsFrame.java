package main.student.settings;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.admin.studentdbhandler.StudentDbHandler;
import main.student.student.Student;
import main.student.studentdao.StudentDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SettingsFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JTextField textField;
	
	private JLabel lblMiddleName;
	
	private JTextField textField_1;
	
	private JLabel lblNewLabel;
	
	private JTextField textField_2;
	
	private JLabel lblSex;
	
	private JTextField textField_3;
	
	private JTextField textField_4;
	
	private JPasswordField passwordField;
	
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
		
			public void run() {
				try {
					SettingsFrame frame = new SettingsFrame("1501CS23");   // default roll number to open constructor  
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
	public SettingsFrame(String rollno)throws Exception {
		
		StudentDAO sdao=new StudentDAO(); 
		Student stud=sdao.getStudentByRollno(rollno);
		setTitle("Student Settings");
		setVisible(true);
		
		// default close operation is dispose on close so that it gets back to the original window
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPersonalProfile = new JLabel("Personal Profile :");
		lblPersonalProfile.setBounds(20, 11, 123, 14);
		contentPane.add(lblPersonalProfile);
		
		JLabel lblFirstName = new JLabel("First Name :");
		lblFirstName.setBounds(30, 36, 113, 14);
		contentPane.add(lblFirstName);
		
		textField = new JTextField();
		textField.setBounds(164, 33, 210, 20);
		textField.setText(stud.getFirstname());
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblMiddleName = new JLabel("Middle Name :");
		lblMiddleName.setBounds(28, 64, 115, 14);
		contentPane.add(lblMiddleName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(164, 61, 210, 20);
		textField_1.setText(stud.getMiddlename());
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel = new JLabel("Last Name : ");
		lblNewLabel.setBounds(30, 92, 113, 14);
		contentPane.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(164, 89, 210, 20);
		contentPane.add(textField_2);
		textField_2.setText(stud.getLastname());
		textField_2.setColumns(10);
		
		lblSex = new JLabel("Sex :");
		lblSex.setBounds(30, 117, 113, 14);
		contentPane.add(lblSex);
		
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Male");
		rdbtnNewRadioButton.setBounds(164, 116, 63, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(229, 116, 70, 23);
		contentPane.add(rdbtnFemale);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(rdbtnFemale);
		bg.add(rdbtnNewRadioButton);
		
		if(stud.getSex()=='M' || stud.getSex()=='m')
			rdbtnNewRadioButton.setSelected(true);
		if(stud.getSex()=='F' || stud.getSex()=='f')
			rdbtnFemale.setSelected(true);
		
		JLabel lblAge = new JLabel("Age :");
		lblAge.setBounds(30, 142, 113, 14);
		contentPane.add(lblAge);
		
		
		textField_3 = new JTextField();
		textField_3.setBounds(164, 139, 210, 20);
		textField_3.setText(""+stud.getAge());
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(30, 167, 113, 14);
		contentPane.add(lblEmail);
		
		textField_4 = new JTextField();
		textField_4.setBounds(164, 164, 210, 20);
		textField_4.setText(stud.getEmail());
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblPasswordSettings = new JLabel("Password Settings :");
		lblPasswordSettings.setBounds(20, 210, 123, 14);
		contentPane.add(lblPasswordSettings);
		
		// Security questions are added to the comboBox
		
		String ques[]={"Where do you live?","Which is your favourite book?","Which is your favourite movie?","Who is your role model?","What time of the day were you born?"};
		JComboBox comboBox=new JComboBox(ques);
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try
			{
			
				// student Database handler is used to update the student database 
		
			StudentDbHandler sdbh=new StudentDbHandler();
			
			String sex="";
			if(rdbtnNewRadioButton.isSelected())
				sex="M";
			if(rdbtnFemale.isSelected())
				sex="F";
			
			sdbh.modifyStudent(rollno, textField.getText(), textField_1.getText(), textField_2.getText(), sex, Integer.parseInt(textField_3.getText()), textField_4.getText(), stud.getBatch(), new String(passwordField.getPassword()), (String)comboBox.getSelectedItem(), textField_5.getText(), stud.getSubject1(), stud.getSubject2(), stud.getSubject3(), stud.getSubject4(), stud.getSubject5(), stud.getSubject6(), stud.getSubject7(), stud.getSubject8(), stud.getSubject9(), stud.getSubject10(),true);
			
			// showing a message of success in case of successful submission 
			
			JOptionPane.showMessageDialog(SettingsFrame.this,"Successfully updated records","Error",JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			dispose();			
			}
			catch(Exception err)
			{
				JOptionPane.showMessageDialog(SettingsFrame.this,err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSubmit.setBounds(106, 427, 107, 23);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancel.setBounds(219, 427, 99, 23);
		contentPane.add(btnCancel);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setBounds(30, 245, 113, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(164, 242, 210, 20);
		contentPane.add(passwordField);
		
		JLabel lblSecurityQuestion = new JLabel("Security Question :");
		lblSecurityQuestion.setBounds(30, 280, 113, 14);
		contentPane.add(lblSecurityQuestion);
		
		// comboBox has been set in such a manner so that apart from the drop down list , text can also be typed into it
		
		comboBox.setEditable(true);
		comboBox.setBounds(164, 277, 234, 20);
		contentPane.add(comboBox);
		
		JLabel lblSecurityAnswer = new JLabel("Security Answer :");
		lblSecurityAnswer.setBounds(30, 322, 113, 14);
		contentPane.add(lblSecurityAnswer);
		
		textField_5 = new JTextField();
		textField_5.setBounds(164, 319, 134, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		// labels added just for the sake of information
		
		JLabel lblNewLabel_1 = new JLabel("Choose security question and answer very carefully.");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(30, 361, 268, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblMayBeNeeded = new JLabel("May be needed at a later time.");
		lblMayBeNeeded.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		lblMayBeNeeded.setBounds(30, 377, 268, 14);
		contentPane.add(lblMayBeNeeded);
	}
}
