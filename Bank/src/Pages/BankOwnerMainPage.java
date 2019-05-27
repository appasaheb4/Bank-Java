package Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class BankOwnerMainPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankOwnerMainPage frame = new BankOwnerMainPage();
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
	public BankOwnerMainPage() {
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
		panel.setBounds(0, 0, 1154, 83);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bank Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(10, 11, 285, 49);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(24, 108, 397, 243);
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
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(BankOwnerMainPage.class.getResource("/BankWelcomePageImage/BankLogo.jpg")));
		lblNewLabel_1.setBounds(10, 22, 190, 221);
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("New Transaction");
		btnNewButton.setIcon(new ImageIcon(BankOwnerMainPage.class.getResource("/BankWelcomePageImage/trannew.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BankOwner ban;
				try {
					ban = new BankOwner();
					ban.setUndecorated(true);
					ban.setVisible(true);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		});
		btnNewButton.setBounds(24, 377, 177, 45);
		contentPane.add(btnNewButton);
		
		JButton btnMiniStatement = new JButton("Mini Statement");
		btnMiniStatement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MiniStatement rb=new MiniStatement();
				rb.setUndecorated(true);
				rb.setVisible(true);
			}
		});
		btnMiniStatement.setIcon(new ImageIcon(BankOwnerMainPage.class.getResource("/BankWelcomePageImage/satement.png")));
		btnMiniStatement.setBounds(211, 377, 201, 45);
		contentPane.add(btnMiniStatement);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Welcome.frame.enable();
			}
		});
		btnExit.setHorizontalAlignment(SwingConstants.LEADING);
		btnExit.setIcon(new ImageIcon(BankOwnerMainPage.class.getResource("/ImageButtonIcon/Exit.png")));
		btnExit.setBounds(422, 377, 107, 45);
		contentPane.add(btnExit);
	}
}
