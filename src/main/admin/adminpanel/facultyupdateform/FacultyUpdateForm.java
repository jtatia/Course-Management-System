package main.admin.adminpanel.facultyupdateform;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import main.professor.professor.Professor;
import main.professor.professorDAO.ProfessorDAO;

import java.awt.Color;

public class FacultyUpdateForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField user_text;
	private JTextField firstname_text;
	private JTextField middlename_text;
	private JTextField lastname_text;
	private JTextField sex_text;
	private JTextField email_text;
	private JTextField password_text;
	private JTextField answer_text;
	private JTextField course_text;
	private Professor pr=null;
	private ProfessorDAO prdao=null;
	private JComboBox comboBox;
	private JList clist;
	private DefaultListModel<String> model=null;
	private String old_name="";
	private String foldername="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FacultyUpdateForm dialog = new FacultyUpdateForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FacultyUpdateForm(Professor pr)throws Exception{
		this();
		this.pr=pr;
		populateForm(pr);
	}

	public FacultyUpdateForm() throws Exception{
		setTitle("Update Form");
		setBounds(400,50,530,550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		prdao=new ProfessorDAO();
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				JPanel panel = new JPanel();
				scrollPane.setViewportView(panel);
				panel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Username : ");
				lblNewLabel.setBounds(76, 11, 70, 14);
				panel.add(lblNewLabel);
				
				user_text = new JTextField();
				user_text.setBounds(173, 8, 242, 20);
				panel.add(user_text);
				user_text.setColumns(30);
				
				JLabel lblFirstName = new JLabel("First Name:");
				lblFirstName.setBounds(76, 39, 70, 14);
				panel.add(lblFirstName);
				
				firstname_text = new JTextField();
				firstname_text.setBounds(173, 33, 242, 20);
				panel.add(firstname_text);
				firstname_text.setColumns(30);
				{
					JLabel lblLastName = new JLabel("Middle Name :");
					lblLastName.setBounds(76, 64, 70, 14);
					panel.add(lblLastName);
				}
				{
					middlename_text = new JTextField();
					middlename_text.setBounds(173, 61, 242, 20);
					panel.add(middlename_text);
					middlename_text.setColumns(30);
				}
				{
					JLabel lblLastName_1 = new JLabel("Last Name :");
					lblLastName_1.setBounds(76, 92, 58, 14);
					panel.add(lblLastName_1);
				}
				{
					lastname_text = new JTextField();
					lastname_text.setBounds(173, 89, 242, 20);
					panel.add(lastname_text);
					lastname_text.setColumns(30);
				}
				
				JLabel lblNewLabel_2 = new JLabel("Sex :");
				lblNewLabel_2.setBounds(76, 155, 46, 14);
				panel.add(lblNewLabel_2);
				
				sex_text = new JTextField();
				sex_text.setBounds(173, 152, 242, 20);
				panel.add(sex_text);
				sex_text.setColumns(30);
				
				JLabel lblEmail = new JLabel("Email :");
				lblEmail.setBounds(76, 123, 46, 14);
				panel.add(lblEmail);
				
				email_text = new JTextField();
				email_text.setBounds(173, 120, 242, 20);
				panel.add(email_text);
				email_text.setColumns(10);
				
				password_text = new JTextField();
				password_text.setBounds(173, 183, 150, 20);
				panel.add(password_text);
				password_text.setColumns(10);
				
				JLabel lblPassword = new JLabel("Password :");
				lblPassword.setBounds(76, 186, 58, 14);
				panel.add(lblPassword);
				
				String ques[]={"Where do you live?","Which is your favourite book?","Which is your favourite movie?","Who is your role model?","What time of the day were you born?"};
				comboBox = new JComboBox(ques);
				comboBox.setBounds(173, 214, 242, 20);
				panel.add(comboBox);
				
				JLabel lblSecurityQuestion = new JLabel("Security Question:");
				lblSecurityQuestion.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblSecurityQuestion.setBounds(76, 217, 98, 14);
				panel.add(lblSecurityQuestion);
				
				answer_text = new JTextField();
				answer_text.setBounds(173, 245, 242, 20);
				panel.add(answer_text);
				answer_text.setColumns(30);
				
				JLabel lblAnswer = new JLabel("Answer :");
				lblAnswer.setBounds(76, 248, 46, 14);
				panel.add(lblAnswer);
				
				JLabel lblCoursesTaken = new JLabel("Courses Taken :");
				lblCoursesTaken.setBounds(76, 284, 89, 14);
				panel.add(lblCoursesTaken);
				
				clist = new JList();
				model=new DefaultListModel<String>();
				clist.setForeground(SystemColor.text);
				clist.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				clist.setBackground(SystemColor.activeCaption);
				clist.setBounds(173, 303, 150, 123);
				panel.add(clist);
				
				JButton addbtn = new JButton("Add");
				addbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					String cid=course_text.getText();
					model.addElement(cid);
					course_text.setText("");
					}
				});
				addbtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
				addbtn.setBounds(326, 280, 89, 72);
				panel.add(addbtn);
				
				JButton delbtn = new JButton("Delete");
				delbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						model.remove(clist.getSelectedIndex());
					}
				});
				delbtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
				delbtn.setBounds(326, 354, 89, 72);
				panel.add(delbtn);
				
				course_text = new JTextField();
				course_text.setBounds(173, 281, 150, 20);
				panel.add(course_text);
				course_text.setColumns(10);
				
				JButton btnEnable = new JButton("enable");
				btnEnable.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String btnstr=btnEnable.getText();
						if(btnstr.equals("enable")){
							password_text.setEnabled(true);
							btnEnable.setText("disable");
						}else{
							password_text.setEnabled(false);
							btnEnable.setText("enable");
						}
					}
				});
				btnEnable.setBounds(333, 182, 82, 23);
				panel.add(btnEnable);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String course_id[]=new String[model.getSize()];
						String unscore="";
						Professor modifiedprof=new Professor();
						modifiedprof.setAnswer(answer_text.getText());
						modifiedprof.setEmail(email_text.getText());
						modifiedprof.setFirstname(firstname_text.getText());
						modifiedprof.setLastname(lastname_text.getText());
						modifiedprof.setMiddlename(middlename_text.getText());
						modifiedprof.setPassword(password_text.getText());
						modifiedprof.setSecurityques((String)comboBox.getSelectedItem());
						modifiedprof.setSex(sex_text.getText().charAt(0));
						modifiedprof.setUsername(user_text.getText());
						for(int i=0;i<model.getSize();i++){
							course_id[i]=model.get(i);
							if(i==model.getSize()-1)
								unscore+=course_id[i];
							unscore+=course_id[i]+"_";
						}
						modifiedprof.setCourseids(course_id);
						modifiedprof.setCourseString(unscore);
						prdao.modifyProfessor(modifiedprof);
						JOptionPane.showMessageDialog(contentPanel, "Information has been updated Succesfully");
						dispose();
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
	
	private void populateForm(Professor prof) {
		// TODO Auto-generated method stub
		String sex = ""+prof.getSex();
		user_text.setText(prof.getUsername());
		user_text.setEnabled(false);
		firstname_text.setText(prof.getFirstname());
		middlename_text.setText(prof.getMiddlename());
		lastname_text.setText(prof.getLastname());
		sex_text.setText(sex);
		email_text.setText(prof.getEmail());
		password_text.setText(prof.getPassword());
		password_text.setEnabled(false);
		answer_text.setText(prof.getAnswer());
		comboBox.setSelectedItem(prof.getSecurityques());
		String crs[]=prof.getCourseids();
		int size = crs.length;
		for(int i=0; i<size; i++){
			//System.out.println(crs[i]);
	    	model.addElement(crs[i]);
		}
		clist.setModel(model);
	}
}
