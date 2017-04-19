package main.util.home;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import javax.swing.*;

import de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;

public class SplashScreen extends JWindow {

    static boolean isRegistered;
    private static JProgressBar progressBar = new JProgressBar();
    private static SplashScreen execute;
    private static int count;
    private static Timer timer1;

    public SplashScreen() {
    	execute =this;
        Container container = getContentPane();
        container.setLayout(null);
        container.setBackground(new Color(0,51,102));
        JPanel panel = new JPanel();
        panel.setBorder(new javax.swing.border.EtchedBorder());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(10, 10, 640, 400);
        panel.setLayout(null);
        panel.setBackground(new Color(156,213,242));
       // container.add(panel);
        
        JLabel label = new JLabel("",new ImageIcon("splash.jpg"),SwingConstants.CENTER);
        label.setFont(new Font("Comic Sans MS",Font.BOLD,19));
        
        label.setBounds(0, 0, 640, 400);
        panel.add(label);
        container.add(label);
        progressBar.setMaximum(50);
        progressBar.setBounds(0, 400, 640, 15);
        progressBar.setForeground(new Color(24,112,11));
        container.add(progressBar);
        loadProgressBar();
        setSize(640, 415);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadProgressBar() {
        ActionListener al = new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                count++;

                progressBar.setValue(count);

              //  System.out.println(count);

                if (count == 60) {

                    createFrame();

                    execute.setVisible(false);//swapped this around with timer1.stop()

                    timer1.stop();
                }

            }

            private void createFrame() throws HeadlessException {
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
        };
        timer1 = new Timer(50, al);
        timer1.start();
    }

    public static void main(String[] args) {
        execute = new SplashScreen();
    }
};