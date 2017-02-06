package main.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.awt.Color;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Home() throws Exception {
		setTitle("Course Management System");
		setIconImage(ImageIO.read(new File("iitp.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(25);
		flowLayout.setVgap(105);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JButton btnStudent = new JButton("Student");
		btnStudent.setForeground(Color.BLACK);
		btnStudent.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
		panel.add(btnStudent);
		
		JButton btnProfesssor = new JButton("Professsor");
		btnProfesssor.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
		panel.add(btnProfesssor);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
		panel.add(btnAdmin);
	}

}
