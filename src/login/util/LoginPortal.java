/*This class makes the GUI of the login portal of the course Management System*/
/*import the required packages*/
package login.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginPortal extends JFrame {

	// local variables
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

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
		btnLogin.setBounds(172, 160, 74, 23);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(266, 160, 74, 23);
		contentPane.add(btnCancel);
	}
}
