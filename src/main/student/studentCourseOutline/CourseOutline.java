package main.student.studentCourseOutline;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import main.student.student.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import main.student.coursepanel.*;
import main.student.settings.SettingsFrame;
public class CourseOutline extends JFrame {

	private JPanel contentPane;
	CardLayout card;
	JPanel panel;
	JPanel panel_1;
	JPanel panel_2;
	CoursePanel c_panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseOutline frame = new CourseOutline(null);
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
	public CourseOutline(Student student) throws Exception{
		setTitle("Course Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblStudentProfileInformation = new JLabel("Student Profile Information");
		
		JLabel lblNewLabel = new JLabel("Name:");
		
		JLabel nameLabel = new JLabel("New label");
		nameLabel.setText(student.toString());
		
		JLabel lblNewLabel_2 = new JLabel("Roll No.:");
		
		JLabel rollLabel = new JLabel("New label");
		rollLabel.setText(student.getRollno());
		
		JLabel lblNewLabel_4 = new JLabel("Batch Code:");
		
		JLabel batchLabel = new JLabel("New label");
		batchLabel.setText(student.getBatch());
		
		JLabel lblCourseName = new JLabel("Course Name : ");
		
		JLabel lblNil = new JLabel("NIL");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStudentProfileInformation)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_2))
							.addGap(12)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(nameLabel)
								.addComponent(rollLabel))
							.addGap(131)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_4)
								.addComponent(lblCourseName))
							.addGap(3)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNil)
								.addComponent(batchLabel))))
					.addContainerGap(593, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblStudentProfileInformation)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(nameLabel)
						.addComponent(lblNewLabel_4)
						.addComponent(batchLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(rollLabel)
						.addComponent(lblCourseName)
						.addComponent(lblNil)))
		);
		panel.setLayout(gl_panel);
		lblNil.setVisible(false);
		lblCourseName.setVisible(false);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		card=new CardLayout(0,0);
		panel_1.setLayout(card);
		c_panel=new CoursePanel();
		PanelCourses panel_course=new PanelCourses(student,this);
		
		panel_1.add(panel_course,"PanelCourses");
		panel_1.add(c_panel,"CoursePanel");
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				new SettingsFrame(student.getRollno());
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(CourseOutline.this,err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_1, "PanelCourses");
				JLabel jb=(JLabel)panel.getComponent(7);
				JLabel jb_c=(JLabel)panel.getComponent(6);
				jb.setVisible(false);
				jb_c.setVisible(false);
				btnBack.setEnabled(false);
			}
		});
		btnBack.setEnabled(false);
		panel_2.add(btnBack);
		panel_2.add(btnSettings);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(CourseOutline.this, "Are U Sure U Want to Logout?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
		panel_2.add(btnLogout);
	}
}
