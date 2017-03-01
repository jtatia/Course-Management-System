package main.admin.adminpanel.addAdmin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import main.admin.admin.Admin;
import main.admin.adminpaneldao.AdminPanelDAO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addAdmin extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField usernameTextField;
	private JTextField firstNameTextField;
	private JTextField middleNameTextField;
	private JTextField lastNameTextField;
	private JTextField ageTextField;
	private JPasswordField passwordField;
	private JTextField emailTextField;
	private JTextField seqTextField;
	private JPasswordField answerTextField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			addAdmin dialog = new addAdmin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public addAdmin() {
		setBounds(0, 0, 430, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		contentPanel.setDoubleBuffered(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			contentPanel.add(lblUsername, "6, 2, right, default");
		}
		{
			usernameTextField = new JTextField();
			contentPanel.add(usernameTextField, "8, 2, fill, default");
			usernameTextField.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			contentPanel.add(lblPassword, "6, 4, right, default");
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField, "8, 4, fill, default");
		}
		{
			JLabel lblFirstName = new JLabel("First Name");
			lblFirstName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			contentPanel.add(lblFirstName, "6, 6, right, default");
		}
		{
			firstNameTextField = new JTextField();
			contentPanel.add(firstNameTextField, "8, 6, fill, default");
			firstNameTextField.setColumns(10);
		}
		{
			JLabel lblMiddleName = new JLabel("Middle Name");
			lblMiddleName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			lblMiddleName.setForeground(new Color(0, 0, 0));
			contentPanel.add(lblMiddleName, "6, 8, right, default");
		}
		{
			middleNameTextField = new JTextField();
			contentPanel.add(middleNameTextField, "8, 8, fill, center");
			middleNameTextField.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Last Name");
			lblLastName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			lblLastName.setForeground(new Color(0, 0, 0));
			contentPanel.add(lblLastName, "6, 10, right, default");
		}
		{
			lastNameTextField = new JTextField();
			contentPanel.add(lastNameTextField, "8, 10, fill, default");
			lastNameTextField.setColumns(10);
		}
		{
			JLabel lblAge = new JLabel("Age");
			lblAge.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			contentPanel.add(lblAge, "6, 12, right, default");
		}
		{
			ageTextField = new JTextField();
			contentPanel.add(ageTextField, "8, 12, fill, default");
			ageTextField.setColumns(10);
		}
		{
			JLabel lblSex = new JLabel("Sex");
			lblSex.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			contentPanel.add(lblSex, "6, 14, right, default");
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "8, 14, fill, center");
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JRadioButton rdbtnM = new JRadioButton("M");
				rdbtnM.setActionCommand("M");
				rdbtnM.setFont(new Font("Tahoma", Font.BOLD, 14));
				buttonGroup.add(rdbtnM);
				panel.add(rdbtnM);
			}
			{
				JRadioButton rdbtnF = new JRadioButton("F");
				rdbtnF.setFont(new Font("Tahoma", Font.BOLD, 14));
				buttonGroup.add(rdbtnF);
				rdbtnF.setActionCommand("F");
				panel.add(rdbtnF);
			}
		}
		{
			JLabel lblEmail = new JLabel("Email");
			lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			contentPanel.add(lblEmail, "6, 16, right, default");
		}
		{
			emailTextField = new JTextField();
			contentPanel.add(emailTextField, "8, 16, fill, default");
			emailTextField.setColumns(10);
		}
		{
			JLabel lblSecurityQuestion = new JLabel("Security Question");
			lblSecurityQuestion.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			contentPanel.add(lblSecurityQuestion, "6, 18, right, default");
		}
		{
			seqTextField = new JTextField();
			contentPanel.add(seqTextField, "8, 18, fill, default");
			seqTextField.setColumns(10);
		}
		{
			JLabel lblAnswer = new JLabel("Answer");
			lblAnswer.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			lblAnswer.setForeground(new Color(0, 0, 0));
			contentPanel.add(lblAnswer, "6, 20, right, default");
		}
		{
			answerTextField = new JPasswordField();
			contentPanel.add(answerTextField, "8, 20, fill, default");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Admin admin = new Admin();
						admin.setUsername(usernameTextField.getText());
						char[] pass = passwordField.getPassword();
						String password = new String(pass);
						admin.setFirstname(firstNameTextField.getText());
						admin.setMiddlename(middleNameTextField.getText());
						admin.setLastname(lastNameTextField.getText());
						String age = ageTextField.getText();
						admin.setAge(Integer.parseInt(age));
						String sex = buttonGroup.getSelection().getActionCommand();
						admin.setSex(sex.charAt(0));
						admin.setEmail(emailTextField.getText());
						admin.setSecurityques(seqTextField.getText());
						char[] ans = answerTextField.getPassword();
						String answer = new String(ans);
						admin.setPassword(password);
						admin.setAnswer(answer);
						try{
							AdminPanelDAO adminPanel = new AdminPanelDAO();
							adminPanel.addAdmin(admin);
						}catch(Exception e){
							e.printStackTrace();
						}finally{
							setVisible(false);
							dispose();
						}
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
