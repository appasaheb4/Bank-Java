package Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.print.attribute.standard.JobKOctetsProcessed;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.mysql.jdbc.Statement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;

public class AdminChangePassword extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsername;

	public Connection conn=null;
	public PreparedStatement pre;
	public PreparedStatement update;
	public ResultSet rs;
	public Statement st;
	private JPasswordField passwordFieldOldPass;
	private JPasswordField passwordFieldNewPass;
	private JPasswordField passwordFieldConPass;
	private JPasswordField passwordFieldSysOldPass;
	private JPasswordField passwordFieldSysNewPass;
	private JPasswordField passwordFieldSysConPass;
	public JPanel panel_2;
	public JRadioButton rdbtnYes;

	public ButtonGroup bg = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminChangePassword frame = new AdminChangePassword();
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
	public AdminChangePassword() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				textFieldUsername.requestFocus();
			}
		});
		
		
		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1184, 45);
		contentPane.add(panel);

		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAdmin.setBounds(10, 11, 195, 27);
		panel.add(lblAdmin);

		JButton button = new JButton("X");
		button.setForeground(Color.RED);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setOpaque(false);
		button.setFont(new Font("Tahoma", Font.PLAIN, 30));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBounds(990, -1, 69, 45);
		panel.add(button);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 51, 748, 671);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); //$NON-NLS-1$
	        getRootPane().getActionMap().put("Cancel", new AbstractAction(){ //$NON-NLS-1$
	            public void actionPerformed(ActionEvent e)
	            {
	            	close();
	            }

				private void close() {
					dispose();
					
				}
	        });

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(10, 38, 125, 17);
		panel_1.add(lblUsername);

		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(158, 27, 267, 28);
		//String loginname = LoginPage.textFieldUserName.getText();
		//textFieldUsername.setText(loginname);
		panel_1.add(textFieldUsername);

		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"System Access Pwd", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 413, 417, 196);
		panel_2.setVisible(false);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel label = new JLabel("Old Password:\r\n");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(10, 54, 125, 17);
		panel_2.add(label);

		JLabel label_1 = new JLabel("New Password:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(10, 93, 125, 17);
		panel_2.add(label_1);

		JLabel label_2 = new JLabel("Confirm Password:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(10, 132, 138, 17);
		panel_2.add(label_2);

		passwordFieldSysOldPass = new JPasswordField();
		passwordFieldSysOldPass.setBounds(164, 54, 243, 29);
		panel_2.add(passwordFieldSysOldPass);

		passwordFieldSysNewPass = new JPasswordField();
		passwordFieldSysNewPass.setBounds(164, 93, 243, 29);
		panel_2.add(passwordFieldSysNewPass);

		passwordFieldSysConPass = new JPasswordField();
		passwordFieldSysConPass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String oldpass=passwordFieldSysNewPass.getText().toString();
				String conpass=passwordFieldSysConPass.getText().toString();
				if(oldpass.equals(conpass))
				{
				
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Plz enter new and confirm password are same.");
				}
			}
		});
		passwordFieldSysConPass.setBounds(164, 132, 243, 29);
		panel_2.add(passwordFieldSysConPass);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Password", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_3.setBounds(10, 73, 417, 228);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblOldPassword = new JLabel("Old Password:\r\n");
		lblOldPassword.setBounds(10, 54, 125, 17);
		panel_3.add(lblOldPassword);
		lblOldPassword.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setBounds(10, 97, 125, 17);
		panel_3.add(lblNewPassword);
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblCofirmPassword = new JLabel("Confirm Password:");
		lblCofirmPassword.setBounds(10, 144, 138, 17);
		panel_3.add(lblCofirmPassword);
		lblCofirmPassword.setFont(new Font("Tahoma", Font.BOLD, 14));

		passwordFieldOldPass = new JPasswordField();
		passwordFieldOldPass.setBounds(164, 54, 243, 29);
		panel_3.add(passwordFieldOldPass);

		passwordFieldNewPass = new JPasswordField();
		passwordFieldNewPass.setBounds(164, 93, 243, 29);
		panel_3.add(passwordFieldNewPass);

		passwordFieldConPass = new JPasswordField();
		passwordFieldConPass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String oldpass=passwordFieldNewPass.getText().toString();
				String conpass=passwordFieldConPass.getText().toString();
				if(oldpass.equals(conpass))
				{
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Plz enter new and confirm password are same.");
					
				}
			}
		});
		passwordFieldConPass.setBounds(164, 132, 243, 29);
		panel_3.add(passwordFieldConPass);

		JLabel lblNewLabel = new JLabel("System Access Password change?");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 326, 234, 17);
		panel_1.add(lblNewLabel);

		JRadioButton rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(true);

			}
		});
		rdbtnYes.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnYes.setBounds(37, 356, 109, 23);
		bg.add(rdbtnYes);

		panel_1.add(rdbtnYes);

		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setSelected(true);
		rdbtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
			}
		});
		rdbtnNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnNo.setBounds(180, 356, 109, 23);
		bg.add(rdbtnNo);
		panel_1.add(rdbtnNo);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(437, 36, 286, 343);
		panel_1.add(panel_4);

		JButton btnChangePassword1 = new JButton("Change Password");
		btnChangePassword1.setHorizontalAlignment(SwingConstants.LEADING);
		btnChangePassword1.setIcon(new ImageIcon(AdminChangePassword.class.getResource("/ImageButtonIcon/changepassword.jpg")));
		btnChangePassword1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass1=passwordFieldNewPass.getText().toString();
				String pass2=passwordFieldConPass.getText().toString();
				if(pass1.equals(pass2))
				{
					try {

						conn =DBConnection.getConnection();
						st = (Statement) conn.createStatement();
						String username = textFieldUsername.getText().toString();
						String password = passwordFieldOldPass.getText().toString();
						// String type=

						rs = st.executeQuery("select Username,Password from banksystem.adminloginpage where Username='"
								+ username + "' and Password='" + password + "';");
						if (rs.next()) {
							String query = "update banksystem.adminloginpage set Password= ? where Username=?";
							update = conn.prepareStatement(query);
							update.setString(1, passwordFieldConPass.getText()
									.toString());
							update.setString(2, textFieldUsername.getText());
							

							int ii = update.executeUpdate();
							if (ii > 0) {
								JOptionPane.showMessageDialog(null,
										"Password are changed.");
								reset();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Password are not changed");
						}

					} catch (Exception ee) {
						System.out.println(ee.getMessage());
					}
					finally
					{
						try
						{
							
							rs.close();
							update.close();
							st.close();
							conn.close();
						}
						catch(Exception ew)
						{
							System.out.println(ew.getMessage());
						}
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Plz new password and confirm password corect.");
				}

			

			}
		});
		btnChangePassword1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnChangePassword1.setBounds(10, 44, 249, 59);
		panel_4.add(btnChangePassword1);

		JButton button_2 = new JButton("Change System Access Pwd");
		button_2.setIcon(new ImageIcon(AdminChangePassword.class.getResource("/ImageButtonIcon/changepassword1.jpg")));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass2=passwordFieldSysNewPass.getText().toString();
				String pass3=passwordFieldSysConPass.getText().toString();
				if (rdbtnYes.isSelected())
				{
					if(pass2.equals(pass3))
							{
					try {
						
						conn = DBConnection.getConnection();
						st = (Statement) conn.createStatement();
						String username = textFieldUsername.getText()
								.toString();
						String password1 = passwordFieldSysOldPass.getText()
								.toString();
						// String type=

						rs = st.executeQuery("select Username,Systempassword from banksystem.adminloginpage where Username='"
								+ username
								+ "' and Systempassword='"
								+ password1
								+ "';");
						System.out.println(username);
						System.out.println(password1);
						if (rs.next()) {
							String query = "update banksystem.adminloginpage set Systempassword=? where Username=?;";
							update = conn.prepareStatement(query);
							update.setString(1, passwordFieldSysConPass.getText().toString());
							update.setString(2, textFieldUsername.getText());
							int ii = update.executeUpdate();
							if (ii > 0) {
								JOptionPane.showMessageDialog(null, "System Access Password are Changed");
								reset();
								
							}
						} else {
							JOptionPane.showMessageDialog(null, "System Access Password are not Changed");
						}

					} catch (Exception ee) {
						System.out.println(ee.getMessage());
					}
					finally
					{
						try
						{
							
							rs.close();
							update.close();
							st.close();
							conn.close();
						}
						catch(Exception ew)
						{
							System.out.println(ew.getMessage());
						}
					}
							}
					else
					{
						JOptionPane.showMessageDialog(null, "Plz new password and confirm password corect.");
					}
				}
			}
		});
		button_2.setHorizontalAlignment(SwingConstants.LEFT);
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(10, 136, 249, 70);
		panel_4.add(button_2);

		JButton btnExit_1 = new JButton("Exit");
		btnExit_1.setHorizontalAlignment(SwingConstants.LEADING);
		btnExit_1.setIcon(new ImageIcon(AdminChangePassword.class.getResource("/ImageButtonIcon/Exit1.png")));
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		btnExit_1.setToolTipText("Plz enter mobile number.");
		btnExit_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit_1.setBounds(10, 240, 249, 59);
		panel_4.add(btnExit_1);
	}
	public void reset()
	{
		try
		{
		textFieldUsername.setText("");
		passwordFieldOldPass.setText("");
		passwordFieldNewPass.setText("");
		passwordFieldConPass.setText("");
		passwordFieldSysOldPass.setText("");
		passwordFieldSysNewPass.setText("");
		passwordFieldSysConPass.setText("");
		}
		catch(Exception ee){}
		
		
	}
}
