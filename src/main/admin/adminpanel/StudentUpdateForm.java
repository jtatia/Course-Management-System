package main.admin.adminpanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import main.admin.studentdbhandler.StudentDbHandler;
import main.student.student.Student;
import main.student.studentdao.StudentDAO;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class StudentUpdateForm extends JDialog {

	private final JPanel form_panel = new JPanel();
	private JTextField id_text;
	private JTextField batch_text;
	private JTextField roll_text;
	private JTextField first_text;
	private JTextField middle_text;
	private JTextField last_text;
	private JTextField sex_text;
	private JTextField age_text;
	private JTextField email_text;
	private JTextField password_text;
	private JComboBox comboBox;
	private JTextField ans_text;
	private JTextField s1_text;
	private JTextField s2_text;
	private JTextField s3_text;
	private JTextField s5_text;
	private JTextField s7_text;
	private JTextField s9_text;
	private JTextField s4_text;
	private JTextField s6_text;
	private JTextField s8_text;
	private JTextField s10_text;
	private Student tempStu=null;
	private String prev;
	private int flag=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			StudentUpdateForm dialog = new StudentUpdateForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public StudentUpdateForm(Student student) throws Exception{
		this();
		StudentDAO dao=new StudentDAO();
		tempStu=dao.getStudentByRollno(student.getRollno());
		prev=tempStu.getPassword();
		populateForm();
	}
	public StudentUpdateForm() {
		setBounds(400,50,600, 640);
		getContentPane().setLayout(new BorderLayout());
		form_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(form_panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Indetification Number :");
		
		id_text = new JTextField();
		id_text.setColumns(30);
		
		JLabel lblNewLabel_1 = new JLabel("Batch :");
		
		batch_text = new JTextField();
		batch_text.setColumns(30);
		
		JLabel lblRollNo = new JLabel("Roll No :");
		
		roll_text = new JTextField();
		roll_text.setColumns(30);
		
		JLabel lblNewLabel_2 = new JLabel("First Name :");
		
		first_text = new JTextField();
		first_text.setColumns(30);
		
		JLabel lblNewLabel_3 = new JLabel("Middle Name :");
		
		middle_text = new JTextField();
		middle_text.setColumns(30);
		
		JLabel lblLastName = new JLabel("Last Name :");
		
		last_text = new JTextField();
		last_text.setColumns(30);
		
		JLabel lblNewLabel_4 = new JLabel("Sex :");
		
		JLabel lblNewLabel_5 = new JLabel("Age :");
		
		JLabel lblNewLabel_6 = new JLabel("Email :");
		
		sex_text = new JTextField();
		sex_text.setColumns(30);
		
		age_text = new JTextField();
		age_text.setColumns(30);
		
		email_text = new JTextField();
		email_text.setColumns(30);
		
		JLabel lblPassword = new JLabel("Password:");
		
		password_text = new JTextField();
		password_text.setColumns(30);
		
		JLabel lblNewLabel_7 = new JLabel("Security Question :");
		
		JLabel lblAnswer = new JLabel("Answer:");
		
		ans_text = new JTextField();
		ans_text.setColumns(30);
		
		JLabel lblSubject = new JLabel("Subject 1 :");
		
		s1_text = new JTextField();
		s1_text.setColumns(10);
		
		JLabel lblSubject_1 = new JLabel("Subject 2 :");
		
		s2_text = new JTextField();
		s2_text.setColumns(10);
		
		JLabel lblSubject_2 = new JLabel("Subject 3 :");
		
		s3_text = new JTextField();
		s3_text.setColumns(10);
		
		JLabel lblSubject_3 = new JLabel("Subject 5 :");
		
		s5_text = new JTextField();
		s5_text.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Subject 7 :");
		
		s7_text = new JTextField();
		s7_text.setColumns(10);
		
		JLabel lblSubject_4 = new JLabel("Subject 9 :");
		
		s9_text = new JTextField();
		s9_text.setColumns(10);
		
		JLabel lblSubject_5 = new JLabel("Subject 4 :");
		
		JLabel lblSubject_6 = new JLabel("Subject 6 :");
		
		JLabel lblSubject_7 = new JLabel("Subject 8 :");
		
		JLabel lblSubject_8 = new JLabel("Subject 10:");
		
		s4_text = new JTextField();
		s4_text.setColumns(10);
		
		s6_text = new JTextField();
		s6_text.setColumns(10);
		
		s8_text = new JTextField();
		s8_text.setColumns(10);
		
		s10_text = new JTextField();
		s10_text.setColumns(10);
		
		String ques[]={"Where do you live?","Which is your favourite book?","Which is your favourite movie?","Who is your role model?","What time of the day were you born?"};
		comboBox = new JComboBox(ques);
		GroupLayout gl_form_panel = new GroupLayout(form_panel);
		gl_form_panel.setHorizontalGroup(
			gl_form_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_form_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_form_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSubject_4)
						.addComponent(lblAnswer)
						.addComponent(lblNewLabel_7)
						.addComponent(lblPassword)
						.addComponent(lblNewLabel_6)
						.addComponent(lblNewLabel_4)
						.addComponent(lblLastName)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel)
						.addComponent(lblRollNo)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_5)
						.addComponent(lblSubject)
						.addComponent(lblSubject_3)
						.addComponent(lblSubject_2)
						.addComponent(lblNewLabel_8))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(id_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(batch_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(roll_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(first_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(middle_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(last_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(sex_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(age_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(email_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ans_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_form_panel.createSequentialGroup()
							.addComponent(s5_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblSubject_6)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(s6_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_form_panel.createSequentialGroup()
							.addComponent(s7_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblSubject_7)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(s8_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_form_panel.createSequentialGroup()
							.addComponent(s9_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addComponent(lblSubject_8)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(s10_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_form_panel.createSequentialGroup()
							.addGroup(gl_form_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_form_panel.createSequentialGroup()
									.addComponent(s3_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(16)
									.addComponent(lblSubject_5))
								.addGroup(gl_form_panel.createSequentialGroup()
									.addComponent(s1_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblSubject_1)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_form_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(s2_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(s4_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_form_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(password_text, Alignment.LEADING)))
					.addContainerGap(205, Short.MAX_VALUE))
		);
		gl_form_panel.setVerticalGroup(
			gl_form_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_form_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_form_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(id_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(batch_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(roll_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRollNo))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(first_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(middle_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLastName)
						.addComponent(last_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4)
						.addGroup(gl_form_panel.createSequentialGroup()
							.addComponent(sex_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_form_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(age_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_6)
						.addComponent(email_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPassword)
						.addComponent(password_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_7)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAnswer)
						.addComponent(ans_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(s1_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSubject)
						.addComponent(lblSubject_1)
						.addComponent(s2_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(s3_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSubject_2)
						.addComponent(lblSubject_5)
						.addComponent(s4_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(s5_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSubject_3)
						.addComponent(lblSubject_6)
						.addComponent(s6_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(s7_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_8)
						.addComponent(lblSubject_7)
						.addComponent(s8_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_form_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSubject_4)
						.addGroup(gl_form_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(s9_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(s10_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSubject_8)))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		form_panel.setLayout(gl_form_panel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new LineBorder(new Color(0, 0, 0)));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try{
							/*
							 * Have to find a way around This stuff of modifying password by ADMIN(ROADBLOCK)
							 */
							/*if(!prev.equals(password_text.getText())){
									flag=1;
									System.out.println("Changed");
							}
							else flag=0;*/
							StudentDbHandler stdb=new StudentDbHandler();
							stdb.modifyStudent(roll_text.getText(),first_text.getText(),
									middle_text.getText(),last_text.getText(),sex_text.getText(),new Integer(age_text.getText()),
									email_text.getText(),batch_text.getText(),password_text.getText(),(String)comboBox.getSelectedItem(),ans_text.getText(),
									s1_text.getText(),s2_text.getText(),s3_text.getText(),s4_text.getText(),s5_text.getText(),s6_text.getText(),
									s7_text.getText(),s8_text.getText(),s9_text.getText(),s10_text.getText());
							dispose();
						}catch(Exception exc){
							exc.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void populateForm(){
		id_text.setText((new Integer(tempStu.getId()).toString()));
		roll_text.setText(tempStu.getRollno());;
		first_text.setText(tempStu.getFirstname());;
		middle_text.setText(tempStu.getMiddlename());
		last_text.setText(tempStu.getLastname());
		sex_text.setText(""+tempStu.getSex());
		age_text.setText(new Integer(tempStu.getAge()).toString());;
		email_text.setText(tempStu.getEmail());;
		password_text.setText(tempStu.getPassword());;
		//security_text.setText(tempStu.getSecurityques());;
		comboBox.setSelectedItem(tempStu.getSecurityques());
		ans_text.setText(tempStu.getAnswer());
		batch_text.setText(tempStu.getBatch());
		s1_text.setText(tempStu.getSubject1());
		s2_text.setText(tempStu.getSubject2());
		s3_text.setText(tempStu.getSubject3());
		s4_text.setText(tempStu.getSubject5());
		s5_text.setText(tempStu.getSubject5());
		s7_text.setText(tempStu.getSubject7());
		s9_text.setText(tempStu.getSubject9());
		s6_text.setText(tempStu.getSubject6());
		s8_text.setText(tempStu.getSubject8());
		s10_text.setText(tempStu.getSubject10());
	}
}
