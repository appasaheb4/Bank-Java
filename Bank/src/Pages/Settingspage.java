package Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.WindowStateListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class Settingspage extends JFrame {

	private JPanel contentPane;

	// public String usertype=LoginPage.textFieldType.getText();
	// public String usertype1="User";

	public JButton btnAdminChangePassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settingspage frame = new Settingspage();
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
	public Settingspage() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int x=e.getXOnScreen();
				int y=e.getYOnScreen();
				Welcome.lblLocation.setText("X:"+x+" "+"Y:"+y );
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

		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblSettings.setBounds(10, 11, 161, 27);
		panel.add(lblSettings);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 45, 1099, 659);
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

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(271, 26, 237, 136);
		panel_1.add(panel_2);

		btnAdminChangePassword = new JButton(" ADMIN CHANGE PASSWORD");
		btnAdminChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminChangePassword acp = new AdminChangePassword();
				acp.setUndecorated(true);
				acp.setVisible(true);
			}
		});
		btnAdminChangePassword.setForeground(Color.WHITE);
		btnAdminChangePassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdminChangePassword.setBackground(new Color(255, 165, 0));
		btnAdminChangePassword.setBounds(0, 102, 237, 36);
		panel_2.add(btnAdminChangePassword);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Settingspage.class.getResource("/Image/imageall/change-password.png")));
		label_1.setBounds(22, 0, 215, 102);
		panel_2.add(label_1);

		JPanel panel_3 = new JPanel();
		panel_3.setVisible(false);
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(23, 192, 237, 136);
		panel_1.add(panel_3);

		JButton btnSetMinimumStock = new JButton("SET MINIMUM STOCK");
		btnSetMinimumStock.setVisible(false);

		btnSetMinimumStock.setForeground(Color.WHITE);
		btnSetMinimumStock.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSetMinimumStock.setBackground(new Color(184, 134, 11));
		btnSetMinimumStock.setBounds(0, 102, 237, 36);
		panel_3.add(btnSetMinimumStock);

		JLabel label_2 = new JLabel("");
		label_2.setVisible(false);
		label_2.setIcon(new ImageIcon(Settingspage.class.getResource("/Image/imageall/setminstock.png")));
		label_2.setBounds(22, 0, 215, 102);
		panel_3.add(label_2);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(527, 26, 237, 136);
		panel_1.add(panel_4);

		JButton btnBackUp = new JButton("Back Up");
		btnBackUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BackUp bak = new BackUp();
				bak.setUndecorated(true);
				bak.setVisible(true);
			}
		});
		btnBackUp.setForeground(Color.WHITE);
		btnBackUp.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBackUp.setBackground(Color.BLACK);
		btnBackUp.setBounds(0, 100, 237, 36);
		panel_4.add(btnBackUp);

		JLabel label_3 = new JLabel("");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setIcon(new ImageIcon(Settingspage.class.getResource("/ImageButtonIcon/BackUp1.jpg")));
		label_3.setBounds(22, 0, 215, 102);
		panel_4.add(label_3);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(24, 26, 237, 136);
		panel_1.add(panel_5);

		JButton btnCreateAdmin = new JButton("Create Admin");
		btnCreateAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPage ad = new AdminPage();
				ad.setUndecorated(true);
				ad.setVisible(true);
			}
		});
		btnCreateAdmin.setForeground(Color.WHITE);
		btnCreateAdmin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCreateAdmin.setBackground(Color.BLUE);
		btnCreateAdmin.setBounds(0, 102, 237, 36);
		panel_5.add(btnCreateAdmin);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Settingspage.class.getResource("/Image/imageall/1687-977791.png")));
		label.setBounds(22, 0, 215, 102);
		panel_5.add(label);
		
		JButton button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Welcome.frame.enable();
			}
		});
		button.setIcon(new ImageIcon(Settingspage.class.getResource("/ImageButtonIcon/Exit.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(10, 617, 131, 38);
		panel_1.add(button);
	}
}
