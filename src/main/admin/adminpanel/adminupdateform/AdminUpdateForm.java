package main.admin.adminpanel.adminupdateform;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AdminUpdateForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
	public AdminUpdateForm() {
		setTitle("Update Form");
		setBounds(100, 100, 450, 300);
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
				lblNewLabel.setBounds(64, 11, 58, 14);
				panel.add(lblNewLabel);
				
				textField = new JTextField();
				textField.setBounds(122, 8, 242, 20);
				panel.add(textField);
				textField.setColumns(30);
				
				JLabel lblFirstName = new JLabel("First Name:");
				lblFirstName.setBounds(64, 36, 70, 14);
				panel.add(lblFirstName);
				
				textField_1 = new JTextField();
				textField_1.setBounds(122, 33, 242, 20);
				panel.add(textField_1);
				textField_1.setColumns(30);
				{
					JLabel lblLastName = new JLabel("Middle Name :");
					lblLastName.setBounds(52, 64, 70, 14);
					panel.add(lblLastName);
				}
				{
					textField_2 = new JTextField();
					textField_2.setBounds(122, 61, 242, 20);
					panel.add(textField_2);
					textField_2.setColumns(30);
				}
				{
					JLabel lblLastName_1 = new JLabel("Last Name :");
					lblLastName_1.setBounds(64, 92, 58, 14);
					panel.add(lblLastName_1);
				}
				{
					textField_3 = new JTextField();
					textField_3.setBounds(122, 89, 242, 20);
					panel.add(textField_3);
					textField_3.setColumns(30);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Age :");
					lblNewLabel_1.setBounds(96, 123, 26, 14);
					panel.add(lblNewLabel_1);
				}
				{
					textField_4 = new JTextField();
					textField_4.setBounds(122, 120, 242, 20);
					panel.add(textField_4);
					textField_4.setColumns(30);
				}
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
						//Add button action
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
