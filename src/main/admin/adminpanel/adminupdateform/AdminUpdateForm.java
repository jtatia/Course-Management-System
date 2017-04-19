package main.admin.adminpanel.adminupdateform;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.admin.admin.Admin;
import main.admin.admindao.AdminDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;

public class AdminUpdateForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField user_text;
	private JTextField firstname_text;
	private JTextField middlename_text;
	private JTextField lastname_text;
	private JTextField age_text;
	private JTextField sex_text;
	private JTextField email_text;
	private JTextField password_text;
	private JTextField answer_text;
	private AdminDAO ad_dao=null;
	private JComboBox comboBox;
	Admin ad=null;
	private String oldPassword="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdminUpdateForm dialog = new AdminUpdateForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdminUpdateForm(Admin ad) throws Exception{
		this();
		this.ad=ad;
		populateForm(ad);
	}
	public AdminUpdateForm() throws Exception{
		setTitle("Update Form");
		setBounds(400,50,530,460);
		AdminDAO ad_dao=new AdminDAO();
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
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
					lblLastName.setBounds(76, 64, 87, 14);
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
					lblLastName_1.setBounds(76, 92, 70, 14);
					panel.add(lblLastName_1);
				}
				{
					lastname_text = new JTextField();
					lastname_text.setBounds(173, 89, 242, 20);
					panel.add(lastname_text);
					lastname_text.setColumns(30);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Age :");
					lblNewLabel_1.setBounds(76, 123, 46, 14);
					panel.add(lblNewLabel_1);
				}
				{
					age_text = new JTextField();
					age_text.setBounds(173, 120, 242, 20);
					panel.add(age_text);
					age_text.setColumns(30);
				}
				
				JLabel lblNewLabel_2 = new JLabel("Sex :");
				lblNewLabel_2.setBounds(76, 155, 46, 14);
				panel.add(lblNewLabel_2);
				
				sex_text = new JTextField();
				sex_text.setBounds(173, 152, 242, 20);
				panel.add(sex_text);
				sex_text.setColumns(30);
				
				JLabel lblEmail = new JLabel("Email :");
				lblEmail.setBounds(76, 186, 46, 14);
				panel.add(lblEmail);
				
				email_text = new JTextField();
				email_text.setBounds(173, 183, 242, 20);
				panel.add(email_text);
				email_text.setColumns(10);
				
				password_text = new JTextField();
				password_text.setBounds(173, 214, 155, 20);
				panel.add(password_text);
				password_text.setColumns(10);
				
				JLabel lblPassword = new JLabel("Password :");
				lblPassword.setBounds(76, 217, 70, 14);
				panel.add(lblPassword);
				
				String ques[]={"Where do you live?","Which is your favourite book?","Which is your favourite movie?","Who is your role model?","What time of the day were you born?"};
				comboBox = new JComboBox(ques);
				comboBox.setBounds(173, 245, 242, 20);
				panel.add(comboBox);
				
				JLabel lblSecurityQuestion = new JLabel("Security Question:");
				lblSecurityQuestion.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblSecurityQuestion.setBounds(76, 248, 98, 14);
				panel.add(lblSecurityQuestion);
				
				answer_text = new JTextField();
				answer_text.setBounds(173, 276, 242, 20);
				panel.add(answer_text);
				answer_text.setColumns(30);
				
				JLabel lblAnswer = new JLabel("Answer :");
				lblAnswer.setBounds(76, 279, 57, 14);
				panel.add(lblAnswer);
				
				JButton btnNewButton = new JButton("enable");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String text_btn=btnNewButton.getText();
						if(text_btn.equals("enable")){
							password_text.setEnabled(true);
							btnNewButton.setText("disable");
						}else{
							password_text.setEnabled(false);
							btnNewButton.setText("enable");
						}
					}
				});
				btnNewButton.setBounds(338, 214, 77, 23);
				panel.add(btnNewButton);
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
						//Add button action
						String password2=password_text.getText();
						boolean encrypt=false;
						if(password2.equals(oldPassword))
							encrypt=false;
						else 
							encrypt=true;
						Admin modifiedAdmin=new Admin();
						modifiedAdmin.setUsername(user_text.getText());
						modifiedAdmin.setFirstname(firstname_text.getText());
						modifiedAdmin.setMiddlename(middlename_text.getText());
						modifiedAdmin.setLastname(lastname_text.getText());
						modifiedAdmin.setAge(Integer.parseInt(age_text.getText()));
						modifiedAdmin.setSex(sex_text.getText().charAt(0));
						modifiedAdmin.setEmail(email_text.getText());
						modifiedAdmin.setPassword(password2);
						modifiedAdmin.setSecurityques((String)comboBox.getSelectedItem());
						modifiedAdmin.setAnswer(answer_text.getText());
						ad_dao.modifyAdmin(modifiedAdmin,encrypt);
						System.out.println("Done");
						JOptionPane.showMessageDialog(contentPanel, "Your information has been updated");
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

	private void populateForm(Admin ad) {
		// TODO Auto-generated method stub
		String sex = ""+ad.getSex();
		user_text.setText(ad.getUsername());
		user_text.setEnabled(false);
		firstname_text.setText(ad.getFirstname());
		middlename_text.setText(ad.getMiddlename());
		lastname_text.setText(ad.getLastname());
		age_text.setText(new Integer(ad.getAge()).toString());
		sex_text.setText(sex);
		email_text.setText(ad.getEmail());
		oldPassword=ad.getPassword();
		password_text.setText(ad.getPassword());
		password_text.setEnabled(false);
		answer_text.setText(ad.getAnswer());
		comboBox.setSelectedItem(ad.getSecurityques());
	}
}
