package main.admin.adminpanel.addfaculty;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import main.admin.admin.Admin;
import main.admin.adminpaneldao.AdminPanelDAO;
import main.professor.professor.Professor;
import main.professor.professorDAO.ProfessorDAO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

public class AddFacultyForm extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField usernameTextField;
	private JTextField firstNameTextField;
	private JTextField middleNameTextField;
	private JTextField lastNameTextField;
	private JTextField emailTextField;
	private JComboBox securityComboBox;
	private JPasswordField answerTextField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField passwordField;
	private ProfessorDAO profDAO;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddFacultyForm dialog = new AddFacultyForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AddFacultyForm()throws Exception {
		setTitle("Add Faculty");
		setBounds(400, 100, 500, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		contentPanel.setDoubleBuffered(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		profDAO=new ProfessorDAO();
		contentPanel.setLayout(null);
		{
			JLabel lblUsername = new JLabel("Username :");
			lblUsername.setBounds(76, 9, 84, 21);
			lblUsername.setFont(new Font("Century Gothic", Font.BOLD, 14));
			contentPanel.add(lblUsername);
		}
		{
			usernameTextField = new JTextField();
			usernameTextField.setBounds(190, 11, 265, 20);
			contentPanel.add(usernameTextField);
			usernameTextField.setColumns(10);
		}
		{
			JLabel lblFirstName = new JLabel("First Name :");
			lblFirstName.setBounds(76, 40, 84, 21);
			lblFirstName.setFont(new Font("Century Gothic", Font.BOLD, 14));
			contentPanel.add(lblFirstName);
		}
		{
			firstNameTextField = new JTextField();
			firstNameTextField.setBounds(190, 42, 265, 20);
			contentPanel.add(firstNameTextField);
			firstNameTextField.setColumns(10);
		}
		{
			JLabel lblMiddleName = new JLabel("Middle Name :");
			lblMiddleName.setBounds(76, 71, 100, 21);
			lblMiddleName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			lblMiddleName.setForeground(new Color(0, 0, 0));
			contentPanel.add(lblMiddleName);
		}
		{
			middleNameTextField = new JTextField();
			middleNameTextField.setBounds(190, 73, 265, 20);
			contentPanel.add(middleNameTextField);
			middleNameTextField.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Last Name :");
			lblLastName.setBounds(76, 103, 84, 21);
			lblLastName.setFont(new Font("Century Gothic", Font.BOLD, 14));
			lblLastName.setForeground(new Color(0, 0, 0));
			contentPanel.add(lblLastName);
		}
		{
			lastNameTextField = new JTextField();
			lastNameTextField.setBounds(190, 104, 265, 20);
			contentPanel.add(lastNameTextField);
			lastNameTextField.setColumns(10);
		}
		{
			JLabel lblEmail = new JLabel("Email :");
			lblEmail.setBounds(76, 133, 56, 21);
			lblEmail.setFont(new Font("Century Gothic", Font.BOLD, 14));
			contentPanel.add(lblEmail);
		}
		{
			emailTextField = new JTextField();
			emailTextField.setBounds(190, 135, 265, 20);
			contentPanel.add(emailTextField);
			emailTextField.setColumns(10);
		}
		{
			JLabel lblSex = new JLabel("Sex : ");
			lblSex.setBounds(76, 173, 56, 21);
			lblSex.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			contentPanel.add(lblSex);
		}
		{
			JPanel panel_gender = new JPanel();
			panel_gender.setBounds(190, 173, 265, 35);
			contentPanel.add(panel_gender);
			panel_gender.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JRadioButton rdbtnM = new JRadioButton("M");
				rdbtnM.setActionCommand("M");
				rdbtnM.setFont(new Font("Tahoma", Font.BOLD, 14));
				buttonGroup.add(rdbtnM);
				panel_gender.add(rdbtnM);
			}
			{
				JRadioButton rdbtnF = new JRadioButton("F");
				rdbtnF.setFont(new Font("Tahoma", Font.BOLD, 14));
				buttonGroup.add(rdbtnF);
				rdbtnF.setActionCommand("F");
				panel_gender.add(rdbtnF);
			}
		}
		{
			JLabel lblEmail = new JLabel("Password :");
			lblEmail.setBounds(76, 217, 84, 21);
			lblEmail.setFont(new Font("Century Gothic", Font.BOLD, 14));
			contentPanel.add(lblEmail);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(190, 219, 265, 20);
			passwordField.setColumns(30);
			contentPanel.add(passwordField);
		}
		{
			JLabel lblSecurityQuestion = new JLabel("Security Question :");
			lblSecurityQuestion.setBounds(76, 248, 123, 21);
			lblSecurityQuestion.setFont(new Font("Century Gothic", Font.BOLD, 12));
			contentPanel.add(lblSecurityQuestion);
		}
		{
			String ques[]={"Where do you live?","Which is your favourite book?","Which is your favourite movie?","Who is your role model?","What time of the day were you born?"};
			securityComboBox=new JComboBox(ques);
			securityComboBox.setBounds(190, 250, 265, 20);
			securityComboBox.setSelectedItem(null);
			contentPanel.add(securityComboBox);
		}
		{
			JLabel lblAnswer = new JLabel("Answer :");
			lblAnswer.setBounds(76, 279, 84, 21);
			lblAnswer.setFont(new Font("Century Gothic", Font.BOLD, 14));
			lblAnswer.setForeground(new Color(0, 0, 0));
			contentPanel.add(lblAnswer);
		}
		{
			answerTextField = new JPasswordField();
			answerTextField.setBounds(190, 281, 265, 20);
			contentPanel.add(answerTextField);
		}
		{
			JLabel lblNewLabel = new JLabel("Newly added professors must register their courses on the course panel by");
			lblNewLabel.setFont(new Font("Arial Unicode MS", Font.ITALIC, 12));
			lblNewLabel.setBounds(20, 336, 443, 21);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblProvidingTheRelevent = new JLabel(" providing the relevent details.");
			lblProvidingTheRelevent.setFont(new Font("Arial Unicode MS", Font.ITALIC, 12));
			lblProvidingTheRelevent.setBounds(20, 368, 293, 14);
			contentPanel.add(lblProvidingTheRelevent);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//Add code here
						Professor prf=new Professor();
						prf.setAnswer(answerTextField.getText());
						prf.setEmail(emailTextField.getText());
						prf.setFirstname(firstNameTextField.getText());
						prf.setMiddlename(middleNameTextField.getText());
						prf.setLastname(lastNameTextField.getText());
						String button_name=buttonGroup.getSelection().getActionCommand();
						System.out.println("button = "+button_name);
						prf.setSex(button_name.charAt(0));
						prf.setPassword(new String(passwordField.getPassword()));
						prf.setSecurityques((String)securityComboBox.getSelectedItem());
						prf.setUsername(usernameTextField.getText());
						profDAO.addProfessor(prf);
						JOptionPane.showMessageDialog(contentPanel, "New Faculty Added Successfully!!!");
						setVisible(false);
						dispose();
					}
				});
				okButton.setForeground(SystemColor.desktop);
				okButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
