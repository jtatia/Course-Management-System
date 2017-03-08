package main.admin.adminpanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import main.admin.admin.*;
import main.admin.settings.AdminSettingsFrame;
import main.util.login.LoginPortal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.CardLayout;

public class MainAdminPanel extends JFrame {
	CardLayout card=new CardLayout(0, 0);
	private JPanel contentPane;
	private JPanel panelMiddle;
	private JButton btnFacultyPanel;
	private JButton btnStudent;
	private JButton btnAdminPanel;
	private JButton btnCourseSide;
	JButton btnCoursePanel;
	private JLabel lblNameField;
	private JLabel lblUserfield;
	private JLabel lblEmailfield;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAdminPanel frame = new MainAdminPanel();
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
	Admin ad;
	public MainAdminPanel(Admin ad)throws Exception{
		this();
		this.ad=ad;
		lblNameField.setText(ad.getFirstname()+ad.getMiddlename()+ad.getLastname());
		lblUserfield.setText(ad.getUsername());
		lblEmailfield.setText(ad.getEmail());
	}
	public MainAdminPanel()throws Exception {
		setTitle("Admin Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, 1366, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 22));
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 22));
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 22));
		
		lblNameField = new JLabel("New label");
		lblNameField.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 22));
		
		lblUserfield = new JLabel("New label");
		lblUserfield.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 22));
		
		lblEmailfield = new JLabel("New label");
		lblEmailfield.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 22));
		GroupLayout gl_panelTop = new GroupLayout(panelTop);
		gl_panelTop.setHorizontalGroup(
			gl_panelTop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTop.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNameField)
					.addGap(318)
					.addComponent(lblUsername)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUserfield)
					.addGap(264)
					.addComponent(lblEmail)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEmailfield)
					.addContainerGap(248, Short.MAX_VALUE))
		);
		gl_panelTop.setVerticalGroup(
			gl_panelTop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTop.createSequentialGroup()
					.addGroup(gl_panelTop.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(lblUsername)
						.addComponent(lblEmail)
						.addComponent(lblNameField)
						.addComponent(lblUserfield)
						.addComponent(lblEmailfield))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelTop.setLayout(gl_panelTop);
		
		JPanel panelFooter = new JPanel();
		contentPane.add(panelFooter, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Settings");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try
			{
				new AdminSettingsFrame(ad.getUsername());
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(MainAdminPanel.this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			}
		});
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response=JOptionPane.showConfirmDialog(panelMiddle, "Are you Sure you want to logout");
				if(response==JOptionPane.YES_OPTION){
					LoginPortal login=new LoginPortal("Admin");
					login.setVisible(true);
					dispose();
				}
			}
		});
		
		btnAdminPanel = new JButton("Admin Panel");
		btnAdminPanel.setEnabled(false);
		btnAdminPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panelMiddle, "Admin Search Panel");
				btnFacultyPanel.setEnabled(true);
				btnStudent.setEnabled(true);
				btnAdminPanel.setEnabled(true);
				btnCoursePanel.setEnabled(true);
			}
		});
		
		btnStudent = new JButton("Student Panel");
		btnStudent.setEnabled(false);
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panelMiddle, "Student Search Panel");
				btnFacultyPanel.setEnabled(true);
				btnStudent.setEnabled(true);
				btnAdminPanel.setEnabled(true);
				btnCoursePanel.setEnabled(true);
			}
		});
		
		btnFacultyPanel = new JButton("Faculty Panel");
		btnFacultyPanel.setEnabled(false);
		btnFacultyPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panelMiddle, "Professor Search Panel");
				btnFacultyPanel.setEnabled(true);
				btnStudent.setEnabled(true);
				btnAdminPanel.setEnabled(true);
				btnCoursePanel.setEnabled(true);
			}
		});
		
		JButton btnHomePage = new JButton("Home Page");
		btnHomePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panelMiddle,"Selection Panel");
				btnFacultyPanel.setEnabled(false);
				btnStudent.setEnabled(false);
				btnAdminPanel.setEnabled(false);
				btnCoursePanel.setEnabled(false);
			}
		});
		
		btnCoursePanel = new JButton("Course Panel");
		btnCoursePanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panelMiddle, "Course Search Panel");
				btnFacultyPanel.setEnabled(true);
				btnStudent.setEnabled(true);
				btnAdminPanel.setEnabled(true);
				btnCoursePanel.setEnabled(true);
			}
		});
		btnCoursePanel.setEnabled(false);
		GroupLayout gl_panelFooter = new GroupLayout(panelFooter);
		gl_panelFooter.setHorizontalGroup(
			gl_panelFooter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFooter.createSequentialGroup()
					.addContainerGap(740, Short.MAX_VALUE)
					.addComponent(btnHomePage)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnFacultyPanel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnStudent)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAdminPanel)
					.addGap(10)
					.addComponent(btnCoursePanel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addGap(14)
					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
		);
		gl_panelFooter.setVerticalGroup(
			gl_panelFooter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFooter.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelFooter.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogout)
						.addComponent(btnNewButton)
						.addComponent(btnAdminPanel)
						.addComponent(btnStudent)
						.addComponent(btnFacultyPanel)
						.addComponent(btnHomePage)
						.addComponent(btnCoursePanel)))
		);
		panelFooter.setLayout(gl_panelFooter);
		
		panelMiddle = new JPanel();
		contentPane.add(panelMiddle, BorderLayout.CENTER);
		panelMiddle.setLayout(card);
		
		JPanel panel_select = new JPanel();
		adminSearchPanel asp=new adminSearchPanel();
		StudentSearchPanel ssp=new StudentSearchPanel();
		CourseSearchPanel csp = new CourseSearchPanel();
		ProfessorSearchPanel psp=new ProfessorSearchPanel();
		panelMiddle.add(panel_select, "Selection Panel");
		panelMiddle.add(asp, "Admin Search Panel");
		panelMiddle.add(ssp,"Student Search Panel");
		panelMiddle.add(csp,"Course Search Panel");
		panelMiddle.add(psp,"Professor Search Panel");
		JButton btnStudentSide = new JButton("STUDENT SIDE");
		btnStudentSide.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		btnStudentSide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panelMiddle, "Student Search Panel");
				btnFacultyPanel.setEnabled(true);
				btnStudent.setEnabled(true);
				btnAdminPanel.setEnabled(true);
				btnCoursePanel.setEnabled(true);
			}
		});
		
		JButton btnFacultySide = new JButton("FACULTY SIDE");
		btnFacultySide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panelMiddle, "Professor Search Panel");
				btnFacultyPanel.setEnabled(true);
				btnStudent.setEnabled(true);
				btnAdminPanel.setEnabled(true);
				btnCoursePanel.setEnabled(true);
			}
		});
		btnFacultySide.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		
		JButton btnAdminSide = new JButton("ADMIN SIDE");
		btnAdminSide.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		btnAdminSide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panelMiddle, "Admin Search Panel");
				btnFacultyPanel.setEnabled(true);
				btnStudent.setEnabled(true);
				btnAdminPanel.setEnabled(true);
				btnCoursePanel.setEnabled(true);
			}
		});
		
		btnCourseSide = new JButton("COURSE SIDE");
		btnCourseSide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panelMiddle, "Course Search Panel");
				btnFacultyPanel.setEnabled(true);
				btnStudent.setEnabled(true);
				btnAdminPanel.setEnabled(true);
				btnCoursePanel.setEnabled(true);
			}
		});
		btnCourseSide.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		GroupLayout gl_panel_select = new GroupLayout(panel_select);
		gl_panel_select.setHorizontalGroup(
			gl_panel_select.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_select.createSequentialGroup()
					.addGap(260)
					.addGroup(gl_panel_select.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnAdminSide, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnFacultySide, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
					.addGroup(gl_panel_select.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnCourseSide, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnStudentSide, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
					.addGap(289))
		);
		gl_panel_select.setVerticalGroup(
			gl_panel_select.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_select.createSequentialGroup()
					.addGap(84)
					.addGroup(gl_panel_select.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStudentSide, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdminSide, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(gl_panel_select.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCourseSide, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFacultySide, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
					.addContainerGap(105, Short.MAX_VALUE))
		);
		panel_select.setLayout(gl_panel_select);
	}
}
