package main.util.home;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.alee.laf.WebLookAndFeel;
import com.birosoft.liquid.*;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import com.jtattoo.plaf.noire.NoireLookAndFeel;
import com.jtattoo.plaf.texture.TextureLookAndFeel;
import com.seaglasslookandfeel.*;


import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;
//import de.javasoft.plaf.synthetica.SyntheticaLookAndFeel;
//import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import main.util.login.*;


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
		
		JWindow window = new JWindow();
		window.getContentPane().setBackground(Color.gray);
		JLabel lb=new JLabel("COURSE MANAGEMENT SYSTEM", SwingConstants.CENTER);
		lb.setFont(new Font("Comic Sans MS",Font.BOLD,19));
		
		window.getContentPane().add(lb);
		window.setBounds(550, 270, 400, 200);
		window.setVisible(true);
		try {
		    Thread.sleep(3000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		window.setVisible(false);
		
		window.dispose();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					try { 	
						//UIManager.setLookAndFeel(new BernsteinLookAndFeel ());
						//UIManager.setLookAndFeel(new AluminiumLookAndFeel ());
						//UIManager.setLookAndFeel(new TextureLookAndFeel ());
						//UIManager.setLookAndFeel(new NoireLookAndFeel ());
						//UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());
						UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel()); 
						//UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
						//UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
						//UIManager.setLookAndFeel(new LiquidLookAndFeel());
					} catch (Exception e) {
					    e.printStackTrace();
					}
					Home frame = new Home();
					frame.setVisible(true);
					frame.setResizable(false);
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
		setBounds(500, 200, 530, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JButton btnStudent = new JButton("Student");
		btnStudent.setBounds(32, 105, 124, 70);
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginPortal loginPortal = new LoginPortal("Student");
				loginPortal.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		panel.setLayout(null);
		btnStudent.setForeground(UIManager.getColor("Button.highlight"));
		btnStudent.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
		panel.add(btnStudent);
		
		JButton btnProfesssor = new JButton("Professor");
		btnProfesssor.setBounds(187, 105, 146, 70);
		btnProfesssor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginPortal loginPortal = new LoginPortal("Professor");
				loginPortal.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnProfesssor.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
		panel.add(btnProfesssor);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setBounds(364, 105, 115, 70);
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginPortal loginPortal = new LoginPortal("Admin");
				loginPortal.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnAdmin.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
		panel.add(btnAdmin);
		
		JButton btnBlueLight = new JButton("Blue Light");
		btnBlueLight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel());
					updateUI();
				} catch (UnsupportedLookAndFeelException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBlueLight.setBounds(14, 273, 89, 44);
		panel.add(btnBlueLight);
		
		JButton btnNewButton = new JButton("Noire");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					UIManager.setLookAndFeel(new NoireLookAndFeel ());
					updateUI();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(113, 273, 89, 44);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Bernstein");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(new BernsteinLookAndFeel ());
					updateUI();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(212, 273, 89, 44);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Black Eye");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
					updateUI();
				} catch (UnsupportedLookAndFeelException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(311, 273, 89, 44);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Aluminium");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(new AluminiumLookAndFeel ());
					updateUI();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(410, 273, 89, 44);
		panel.add(btnNewButton_3);
		/*try{
			main.util.lookandfeel.LookAndFeel.set();
			}catch(Exception exc){
				exc.printStackTrace();
		}*/
	}
	
	public void updateUI()
	{
		SwingUtilities.updateComponentTreeUI(this);
	}
}
