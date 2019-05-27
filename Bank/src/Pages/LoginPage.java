package Pages;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JButton;


import com.mysql.jdbc.Statement;

import AppPackage.AnimationClass;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JProgressBar;


public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldusername;
	public Connection conn = null;
	public ResultSet rs;
	public Statement st;
	public ResultSet rs1;
	public Statement st1;
	private JPasswordField textFieldPassword;
	public static JTextField textFieldType;
	public static JTextField textFieldUserName;
	public JLabel lblBackGourad1;
	public JLabel lblBackGourad2;
	public JProgressBar progressBar;
	public static int MY_MAXIMUM=100;

	AnimationClass Ac = new AnimationClass();

	/**
	 * Launch the application.
	 * 
	 */
public void Slideshow() {
		
	
		new Thread()
		{
			int count11;
			@Override
			public void run()
			{
				try
				{
					while(true)
					{
						switch (count11) {
						case 0: 
							ImageIcon I=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (61).jpg")));
							lblBackGourad1.setIcon(I);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 1;
							break;
						case 1:
							ImageIcon I1=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (55).jpg")));
							lblBackGourad2.setIcon(I1);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 2;
							break;
						case 2:
							ImageIcon I2=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (56).jpg")));
							lblBackGourad1.setIcon(I2);
							
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 3;
							
						case 3:
							ImageIcon I3=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (57).jpg")));
							lblBackGourad2.setIcon(I3);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 4;
							break;
						case 4:
							ImageIcon I4=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (58).jpg")));
							lblBackGourad1.setIcon(I4);
							
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 5;
							break;
						case 5:
							ImageIcon I5=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (59).jpg")));
							lblBackGourad2.setIcon(I5);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 6;
							break;
						case 6: 
							ImageIcon I6=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (60).jpg")));
							lblBackGourad1.setIcon(I6);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 7;
							break;
						case 7:
							ImageIcon I7=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (61).jpg")));
							lblBackGourad2.setIcon(I7);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 8;
							break;
						case 8: 
							ImageIcon I8=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (62).jpg")));
							lblBackGourad1.setIcon(I8);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 9;
							break;
						case 9:
							ImageIcon I9=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (63).jpg")));
							lblBackGourad2.setIcon(I9);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 10;
							break;
						case 10: 
							ImageIcon I10=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (64).jpg")));
							lblBackGourad1.setIcon(I10);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 11;
							break;
						case 11:
							ImageIcon I11=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (65).jpg")));
							lblBackGourad2.setIcon(I11);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 12;
							break;
						case 12: 
							ImageIcon I12=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (66).jpg")));
							lblBackGourad1.setIcon(I12);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 13;
							break;
						case 13:
							ImageIcon I13=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (67).jpg")));
							lblBackGourad2.setIcon(I13);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 14;
							break;
						case 14: 
							ImageIcon I14=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (68).jpg")));
							lblBackGourad1.setIcon(I14);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 15;
							break;
						case 15:
							ImageIcon I15=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (69).jpg")));
							lblBackGourad2.setIcon(I15);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 16;
							break;
						case 16: 
							ImageIcon I16=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (70).jpg")));
							lblBackGourad1.setIcon(I16);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 17;
							break;
						case 17:
							ImageIcon I17=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (71).jpg")));
							lblBackGourad2.setIcon(I17);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 18;
							break;
						case 18: 
							ImageIcon I18=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (72).jpg")));
							lblBackGourad1.setIcon(I18);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 19;
							break;
						case 19:
							ImageIcon I19=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (73).jpg")));
							lblBackGourad2.setIcon(I19);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 20;
							break;
						case 20: 
							ImageIcon I20=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (74).jpg")));
							lblBackGourad1.setIcon(I20);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 21;
							break;
						case 21:
							ImageIcon I21=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (75).jpg")));
							lblBackGourad2.setIcon(I21);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 22;
							break;
						case 22: 
							ImageIcon I22=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (76).jpg")));
							lblBackGourad1.setIcon(I22);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 23;
							break;
						case 23:
							ImageIcon I123=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (77).jpg")));
							lblBackGourad2.setIcon(I123);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 24;
							break;
						case 24: 
							ImageIcon I24=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (78).jpg")));
							lblBackGourad1.setIcon(I24);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 25;
							break;
						case 25:
							ImageIcon I25=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (79).jpg")));
							lblBackGourad2.setIcon(I25);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 26;
							break;
						case 26: 
							ImageIcon I26=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (80).jpg")));
							lblBackGourad1.setIcon(I26);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 27;
							break;
						case 27:
							ImageIcon I27=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (81).jpg")));
							lblBackGourad2.setIcon(I27);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 28;
							break;
						case 28: 
							ImageIcon I28=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (82).jpg")));
							lblBackGourad1.setIcon(I28);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 29;
							break;
						case 29:
							ImageIcon I29=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (83).jpg")));
							lblBackGourad2.setIcon(I29);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 30;
							break;
						case 30: 
							ImageIcon I30=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (84).jpg")));
							lblBackGourad1.setIcon(I30);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 31;
							break;
						case 31:
							ImageIcon I31=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (85).jpg")));
							lblBackGourad2.setIcon(I31);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 32;
							break;
						case 32: 
							ImageIcon I32=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (86).jpg")));
							lblBackGourad1.setIcon(I32);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 33;
							break;
						case 33:
							ImageIcon I33=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (87).jpg")));
							lblBackGourad2.setIcon(I33);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 34;
							break;
						case 34: 
							ImageIcon I34=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (88).jpg")));
							lblBackGourad1.setIcon(I34);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 35;
							break;
						case 35:
							ImageIcon I35=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (89).jpg")));
							lblBackGourad2.setIcon(I35);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 36;
							break;
						case 36: 
							ImageIcon I36=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (90).jpg")));
							lblBackGourad1.setIcon(I36);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 37;
							break;
						case 37:
							ImageIcon I37=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (91).jpg")));
							lblBackGourad2.setIcon(I37);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 38;
							break;
						case 38: 
							ImageIcon I38=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (92).jpg")));
							lblBackGourad1.setIcon(I38);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 39;
							break;
						case 39:
							ImageIcon I39=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (93).jpg")));
							lblBackGourad2.setIcon(I39);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 40;
							break;
						case 40: 
							ImageIcon I40=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (94).jpg")));
							lblBackGourad1.setIcon(I40);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 41;
							break;
						case 41:
							ImageIcon I41=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (95).jpg")));
							lblBackGourad2.setIcon(I41);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 42;
							break;
						case 42: 
							ImageIcon I42=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (96).jpg")));
							lblBackGourad1.setIcon(I42);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 43;
							break;
						case 43:
							ImageIcon I43=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (97).jpg")));
							lblBackGourad2.setIcon(I43);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 44;
							break;
						case 44: 
							ImageIcon I44=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (98).jpg")));
							lblBackGourad1.setIcon(I44);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 45;
							break;
						case 45:
							ImageIcon I45=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (99).jpg")));
							lblBackGourad2.setIcon(I45);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 46;
							break;
						case 46: 
							ImageIcon I46=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (100).jpg")));
							lblBackGourad1.setIcon(I46);
							Thread.sleep(3000);
							Ac.jLabelXLeft(0, -840, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(840, 0, 20, 7, lblBackGourad2);
							count11 = 47;
							break;
						case 47:
							ImageIcon I47=new ImageIcon(ImageIO.read(getClass().getResource("/LoginPageImage/1 (101).jpg")));
							lblBackGourad2.setIcon(I47);
							Thread.sleep(3000);
							Ac.jLabelXRight(-840, 0, 20, 7, lblBackGourad1);
							Ac.jLabelXLeft(0, 840, 20, 7, lblBackGourad2);
							count11 = 0;
							break;
						
						}
					}
				}
				catch(Exception ee)
				{
					
				}
				
			}
		
		}.start();
	
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		
			public void run() {
				try {
					//Thread.sleep(4500);
					LoginPage frame = new LoginPage();
					
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
	public LoginPage() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/ImageButtonIcon/Login1.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 471);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Slideshow();

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 830, 436);

		panel_1.setOpaque(false);

		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(368, 72, 77, 25);
		lblNewLabel_1.setPreferredSize(new Dimension(200,24));
		panel_1.add(lblNewLabel_1);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.BLUE);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsername.setBounds(199, 134, 125, 25);
		panel_1.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(199, 182, 125, 25);
		panel_1.add(lblPassword);

		textFieldusername = new JTextField();
		textFieldusername.setBackground(Color.WHITE);
		textFieldusername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldusername.setBounds(326, 134, 197, 26);
		textFieldusername.requestFocus();
		panel_1.add(textFieldusername);
		textFieldusername.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.setHorizontalAlignment(SwingConstants.LEADING);
		btnLogin.setIcon(new ImageIcon(LoginPage.class.getResource("/ImageButtonIcon/Login.png")));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					conn = DBConnection.getConnection();
					st = (Statement) conn.createStatement();
					
					String username = textFieldusername.getText().toString();
					String password = textFieldPassword.getText().toString();

					rs = st.executeQuery("select * from banksystem.adminloginpage where UserName='"
							+ username + "' and Password='" + password + "';");
					
					
					if (rs.next()) {
						try
						{
							progressBar.setEnabled(true);
							progressBar.setVisible(true);
						new Thread(){
							public void run(){
				            for (int i = 0;i<=MY_MAXIMUM; i+=10) {
				              try {
								setvalue(i);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				               try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				            }
				        }

							private void setvalue(int i) throws InterruptedException {
								progressBar.setValue(i);
								
								if(i==100)
								{
									Welcome wel=new Welcome();
									wel.call();
									dispose();
								}
								
							}
				 }.start();
						}
						catch(Exception es)
						{
							System.out.println(es.getMessage());
						}
					
						

					}  else {
						JOptionPane.showMessageDialog(null,
								"plz enter username and passwrod carect..");
					}
					

				} catch (Exception ee) {
					System.out.println(ee.getMessage());
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(236, 225, 115, 33);
		panel_1.add(btnLogin);

		JButton btnExit = new JButton("Exit");
		btnExit.setHorizontalAlignment(SwingConstants.LEADING);
		btnExit.setIcon(new ImageIcon(LoginPage.class.getResource("/ImageButtonIcon/Exit.png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(365, 225, 115, 33);
		btnExit.setOpaque(false);
		panel_1.add(btnExit);

		textFieldPassword = new JPasswordField();
		textFieldPassword.setBackground(Color.WHITE);
		textFieldPassword.setBounds(326, 182, 197, 25);
		panel_1.add(textFieldPassword);

		JLabel lblNewLabel_5 = new JLabel("Forgot password");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ForgotPassword fp = new ForgotPassword();
				fp.setVisible(true);

			}
		});
		lblNewLabel_5.setForeground(new Color(0, 0, 255));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(293, 269, 108, 14);
		panel_1.add(lblNewLabel_5);
		
				JLabel label_1 = new JLabel("Developed By:\r\n");
				label_1.setForeground(Color.BLUE);
				label_1.setBounds(155, 299, 117, 20);
				panel_1.add(label_1);
				label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				
						JLabel label_2 = new JLabel("HIRAY SOFTTECH PVT.LTD.\r\n");
						label_2.setForeground(Color.BLUE);
						label_2.setBounds(281, 292, 340, 31);
						panel_1.add(label_2);
						label_2.setFont(new Font("Tahoma", Font.BOLD, 22));
						
								JLabel label_3 = new JLabel("Website: www.hsofttech.com\r\n");
								label_3.setForeground(Color.BLUE);
								label_3.setBounds(173, 326, 181, 17);
								panel_1.add(label_3);
								label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
								
										JLabel label_4 = new JLabel("Emil-ID:info@hsoftech.com\r\n");
										label_4.setForeground(Color.BLUE);
										label_4.setBounds(362, 326, 167, 17);
										panel_1.add(label_4);
										label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
										
												JLabel label_5 = new JLabel("Ph:0241-2424000  Cell:9021550005\r\n");
												label_5.setForeground(Color.BLUE);
												label_5.setBounds(245, 345, 227, 17);
												panel_1.add(label_5);
												label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
												
												 progressBar = new JProgressBar();
												 progressBar.setStringPainted(true);
												 progressBar.setVisible(false);
												 progressBar.setEnabled(false);
												progressBar.setForeground(Color.BLUE);
												progressBar.setBounds(10, 368, 740, 25);
												panel_1.add(progressBar);

		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(578, 376, 124, 26);
		contentPane.add(textFieldUserName);
		textFieldUserName.setEnabled(false);
		textFieldUserName.setColumns(10);

		textFieldType = new JTextField();
		textFieldType.setBounds(10, 376, 124, 26);
		contentPane.add(textFieldType);
		textFieldType.setEnabled(false);
		textFieldType.setVisible(false);
		textFieldType.setColumns(10);
								
										lblBackGourad2 = new JLabel("");
										lblBackGourad2.setVerticalAlignment(SwingConstants.TOP);
										lblBackGourad2.setHorizontalAlignment(SwingConstants.LEFT);
										lblBackGourad2.setBounds(0, 0, 840, 472);
										contentPane.add(lblBackGourad2);
						
								lblBackGourad1 = new JLabel("");
								lblBackGourad1.setBounds(0, 0, 840, 472);
								contentPane.add(lblBackGourad1);
								lblBackGourad1.setHorizontalAlignment(SwingConstants.CENTER);
								
								JLabel labelMainLable = new JLabel("");
								labelMainLable.setIcon(new ImageIcon(LoginPage.class.getResource("/LoginPageImage/MainImage.jpg")));
								labelMainLable.setVerticalAlignment(SwingConstants.TOP);
								labelMainLable.setHorizontalAlignment(SwingConstants.RIGHT);
								labelMainLable.setBounds(0, 0, 830, 436);
								contentPane.add(labelMainLable);
		textFieldUserName.setVisible(false);
	}
}
