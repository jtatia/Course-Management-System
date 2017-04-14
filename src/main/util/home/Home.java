package main.util.home;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
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
import de.javasoft.plaf.synthetica.SyntheticaLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					try { 
						
						//UIManager.setLookAndFeel(new BernsteinLookAndFeel ());
						//UIManager.setLookAndFeel(new AluminiumLookAndFeel ());
						//UIManager.setLookAndFeel(new TextureLookAndFeel ());
						UIManager.setLookAndFeel(new NoireLookAndFeel ());
						//UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());
						//UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel()); 
						//UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
						//UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
						//UIManager.setLookAndFeel(new LiquidLookAndFeel());
					} catch (Exception e) {
					    e.printStackTrace();
					}
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
		setBounds(500, 200, 450, 300);
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
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginPortal loginPortal = new LoginPortal("Student");
				loginPortal.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		btnStudent.setForeground(Color.BLACK);
		btnStudent.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
		panel.add(btnStudent);
		
		JButton btnProfesssor = new JButton("Professor");
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
		/*try{
			main.util.lookandfeel.LookAndFeel.set();
			}catch(Exception exc){
				exc.printStackTrace();
		}*/
	}
}
