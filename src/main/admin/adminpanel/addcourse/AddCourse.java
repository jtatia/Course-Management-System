package main.admin.adminpanel.addcourse;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import main.admin.adminpanel.AddStudentForm;
import main.admin.adminpanel.addfaculty.AddFacultyForm;
import main.course.course.Course;
import main.course.coursedao.CourseDAO;
import main.course.coursedao.CourseMappingDAO;
import main.professor.professorDAO.ProfessorDAO;
import main.student.student.Student;
import main.util.foldermaker.FolderMaker;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class AddCourse extends JDialog {
	static int z=0,i=0;
	private JPanel contentPane;
	private JTextField IdText;
	private JTextField courseText;
	private CourseDAO dao = null;
	private CourseMappingDAO cmdao=null;
	private ProfessorDAO pfdao = null;
	private JList list;
	private DefaultListModel<String> model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddCourse dialog = new AddCourse();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public AddCourse() {
		try {
			dao=new CourseDAO();
			cmdao=new CourseMappingDAO();
			pfdao=new ProfessorDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setTitle("Add Course");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 713, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAddCourseForm = new JLabel("Add Course Form");
		lblAddCourseForm.setBounds(121, 16, 126, 14);
		lblAddCourseForm.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNewLabel = new JLabel("Course ID : ");
		lblNewLabel.setBounds(44, 50, 89, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		IdText = new JTextField();
		IdText.setBounds(157, 48, 231, 20);
		IdText.setColumns(10);
		
		JLabel lblCourseName = new JLabel("Course Name : ");
		lblCourseName.setBounds(44, 88, 103, 14);
		lblCourseName.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		courseText = new JTextField();
		courseText.setBounds(157, 86, 231, 20);
		courseText.setColumns(10);
		
		JLabel lblCourseInfo = new JLabel("Course Info :");
		lblCourseInfo.setBounds(44, 334, 130, 14);
		lblCourseInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		JScrollPane jsp=new JScrollPane(textArea);
		jsp.setBounds(44, 359, 344, 189);
		JComboBox comboBox = new JComboBox();
		JCheckBox chckbxCse = new JCheckBox("Computer Science(cse) ");
		JCheckBox chckbxMe = new JCheckBox("Mechanical(me)");
		JCheckBox chckbxEe = new JCheckBox("Electrical(ee)");
		JCheckBox chckbxCe = new JCheckBox("Civil Engineering(ce)");
		JCheckBox chckbxCh = new JCheckBox("Chemical Engineering(che)");
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(121, 559, 89, 23);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			Course course=new Course();
			int f=0;
			String folderName="";
			String course_id=IdText.getText();
			if(course_id.equals(""))
			{
				f=1;	
				JOptionPane.showMessageDialog(AddCourse.this,"Enter the Course Id","Alert : ",JOptionPane.WARNING_MESSAGE);
			}
			if(courseText.getText().equals(""))
			{
				f=1;
				JOptionPane.showMessageDialog(AddCourse.this,"Enter the Course Name","Alert : ",JOptionPane.WARNING_MESSAGE);
			}
			Course c=dao.getCourseById(course_id);
			
			if(c==null)
			{
				if(f==0)
				{	
					course.setCourseId(IdText.getText());
					folderName+=IdText.getText()+"_";
					course.setCourseName(courseText.getText());
					course.setCourseInfo(textArea.getText());
					dao.addCourse(course);
					// once the student has been added set the visibility to false and dispose off the JFrame
					ListModel lm=list.getModel();
					int size=lm.getSize();
					String professors[]=new String [size];
					for(int k=0;k<size;k++)
					{
						pfdao.updateCourseIds((String)model.get(k),course_id);
						professors[k]=(String) lm.getElementAt(k);
						folderName+=(k==size-1)?professors[k]:professors[k]+"_";
					}	
					String batches[]=new String [5];
					String b=(String) comboBox.getSelectedItem();
					char bh=b.charAt(0);
					if(chckbxCse.isSelected())
						batches[0]="cse"+b.substring(b.length()-2)+bh;
					else
						batches[0]="";
					if(chckbxEe.isSelected())
						batches[1]="ee"+b.substring(b.length()-2)+bh;
					else
						batches[1]="";
					if(chckbxMe.isSelected())
						batches[2]="me"+b.substring(b.length()-2)+bh;
					else
						batches[2]="";
					if(chckbxCh.isSelected())
						batches[3]="ch"+b.substring(b.length()-2)+bh;
					else
						batches[3]="";
					if(chckbxCe.isSelected())
						batches[4]="ce"+b.substring(b.length()-2)+bh;
					else
						batches[4]="";
					FolderMaker fm=new FolderMaker();
					try {
						fm.createDirectoryStructure(folderName);
					} catch (Exception e) {
						e.printStackTrace();
					}
					cmdao.addEntry(course.getCourseId(), professors, batches);
					JOptionPane.showMessageDialog(AddCourse.this,"New Course has been added","Info : ",JOptionPane.INFORMATION_MESSAGE);AddCourse.this.setVisible(false);
					AddCourse.this.dispose();
				}
			}
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(241, 559, 89, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNameOfProfessor = new JLabel("Professor(s) :");
		lblNameOfProfessor.setBounds(44, 134, 103, 16);
		lblNameOfProfessor.setFont(new Font("Tahoma", Font.BOLD, 13));

		List<String> pr_user=pfdao.getAllUsernames();
		JComboBox profCombo = new JComboBox(pr_user.toArray());
		profCombo.setBounds(157, 133, 111, 20);
		contentPane.add(profCombo);
		JButton btnNewButton = new JButton("+");
		btnNewButton.setBounds(278, 134, 52, 20);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.addElement((String)profCombo.getSelectedItem());
				profCombo.setSelectedItem(null);
				list.revalidate();
				list.repaint();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.setLayout(null);
		
		model = new DefaultListModel<>();
		list = new JList(model);
		list.setForeground(new Color(255, 255, 255));
		list.setBackground(new Color(221, 160, 221));
		list.setBounds(44, 189, 344, 134);
		contentPane.add(list);
		contentPane.add(lblAddCourseForm);
		contentPane.add(lblNewLabel);
		contentPane.add(IdText);
		contentPane.add(lblCourseName);
		contentPane.add(courseText);
		contentPane.add(lblNameOfProfessor);
		contentPane.add(btnNewButton);
		contentPane.add(lblCourseInfo);
		contentPane.add(jsp);
		contentPane.add(btnSubmit);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_1 = new JLabel("List of Professors for this Course");
		lblNewLabel_1.setBounds(44, 164, 241, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("-");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.remove(list.getSelectedIndex());
				list.revalidate();
				list.repaint();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(336, 134, 52, 19);
		contentPane.add(btnNewButton_1);
		
		JLabel lblBatch = new JLabel("Batch :");
		lblBatch.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBatch.setBounds(425, 51, 52, 14);
		contentPane.add(lblBatch);
		
		
		comboBox.setBackground(Color.WHITE);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"btech16", "btech15", "btech14", "btech13","mtech13","mtech14","mtech15","mtech16","mtech17"}));
		comboBox.setBounds(481, 48, 176, 20);
		contentPane.add(comboBox);
		
		
		chckbxCse.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxCse.setBounds(425, 210, 253, 23);
		contentPane.add(chckbxCse);
		
		
		chckbxEe.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxEe.setBounds(425, 236, 155, 23);
		contentPane.add(chckbxEe);
		
		
		chckbxMe.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxMe.setBounds(425, 261, 176, 23);
		contentPane.add(chckbxMe);
		
		
		chckbxCe.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxCe.setBounds(425, 158, 232, 23);
		contentPane.add(chckbxCe);
		
		
		chckbxCh.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxCh.setBounds(425, 184, 220, 23);
		contentPane.add(chckbxCh);
		
		JLabel lblNewLabel_2 = new JLabel("BRANCH/STREAM");
		lblNewLabel_2.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 18));
		lblNewLabel_2.setBounds(425, 107, 232, 20);
		contentPane.add(lblNewLabel_2);
	}
}
