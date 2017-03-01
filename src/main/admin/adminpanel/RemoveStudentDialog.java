package main.admin.adminpanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.admin.studentdbhandler.StudentDbHandler;
import main.student.student.Student;
import main.student.studentdao.StudentDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RemoveStudentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RemoveStudentDialog dialog = new RemoveStudentDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RemoveStudentDialog() {
		setVisible(true);
		setTitle("Remove Student");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEnterTheRoll = new JLabel("Enter the Roll Number of the student to be removed :");
		lblEnterTheRoll.setBounds(21, 66, 360, 14);
		contentPanel.add(lblEnterTheRoll);
		
		textField = new JTextField();
		textField.setBounds(21, 93, 297, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Submit");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					try
					{
						// obtaining the roll number of the student to be removed
						String rollno=textField.getText();
						StudentDAO dao=new StudentDAO();
						Student stud=dao.getStudentByRollno(rollno);
						// checking if the roll number is a valid roll number that is whether it exists in the database or not
						if(stud!=null)
						{
							StudentDbHandler sdbh=new StudentDbHandler();
							sdbh.removeStudent(rollno);
							// calling the method of the StudentDbHandler to remove the student using the roll number
							JOptionPane.showMessageDialog(RemoveStudentDialog.this,"Successfully removed the student from the database","Info : ",JOptionPane.INFORMATION_MESSAGE);
							// on removal of the student display suitable message of success in removing the student
							// hide the dialog box and dispose it when the student has been successfully removed 
							RemoveStudentDialog.this.setVisible(false);
							RemoveStudentDialog.this.dispose();
						}	
						else
						{
							JOptionPane.showMessageDialog(RemoveStudentDialog.this,"No such Rollno exists in the database","Alert : ",JOptionPane.WARNING_MESSAGE);
						}	
					}
					catch(Exception e)
					{
						e.printStackTrace();
						JOptionPane.showMessageDialog(RemoveStudentDialog.this,e.getMessage(),"Error : ",JOptionPane.ERROR_MESSAGE);
					}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
