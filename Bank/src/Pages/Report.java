package Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class Report extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report frame = new Report();
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
	public Report() {
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
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 331, 153, 222);
		contentPane.add(panel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Report.class.getResource("/BankWelcomePageImage/shares.png")));
		label.setBounds(10, 0, 132, 169);
		panel.add(label);
		
		JButton btnFd = new JButton("Shares");
		btnFd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ReportShare rs=new ReportShare();
					rs.setUndecorated(true);
					rs.setVisible(true);
					
				} catch (Exception ew) {
					System.out.println(ew.getMessage());
				}
				
				
				
			}
		});
		btnFd.setForeground(Color.WHITE);
		btnFd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFd.setBackground(Color.ORANGE);
		btnFd.setBounds(0, 170, 153, 52);
		panel.add(btnFd);
		
		JButton button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Welcome.frame.enable();
			}
		});
		button.setIcon(new ImageIcon(Report.class.getResource("/ImageButtonIcon/Exit.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(10, 655, 115, 38);
		contentPane.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1154, 90);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Repots");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 27, 283, 52);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 98, 153, 222);
		contentPane.add(panel_2);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Report.class.getResource("/BankWelcomePageImage/loanreports.jpg")));
		label_1.setBounds(10, 0, 128, 169);
		panel_2.add(label_1);
		
		JButton btnLoan = new JButton("Loan");
		btnLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					ReportLoan rl=new ReportLoan();
					rl.setUndecorated(true);
					rl.setVisible(true);
					
				}
				catch(Exception ee)
				{
					System.out.print(ee.getMessage());
				}
				
			}
		});
		btnLoan.setForeground(Color.WHITE);
		btnLoan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLoan.setBackground(new Color(139, 0, 0));
		btnLoan.setBounds(0, 170, 153, 52);
		panel_2.add(btnLoan);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(173, 101, 153, 222);
		contentPane.add(panel_3);
		
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
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Report.class.getResource("/BankWelcomePageImage/saving5.png")));
		label_2.setBounds(10, 0, 128, 169);
		panel_3.add(label_2);
		
		JButton btnSaving = new JButton("Saving");
		btnSaving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReportSaving rs=new ReportSaving();
					rs.setUndecorated(true);
					rs.setVisible(true);
					
					
				} catch (Exception ew) {
					System.out.println(ew.getMessage());
				}
			}
		});
		btnSaving.setForeground(Color.WHITE);
		btnSaving.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSaving.setBackground(Color.GREEN);
		btnSaving.setBounds(0, 170, 153, 52);
		panel_3.add(btnSaving);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(336, 98, 153, 222);
		contentPane.add(panel_4);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(Report.class.getResource("/BankWelcomePageImage/currentr.png")));
		label_3.setBounds(10, 0, 128, 169);
		panel_4.add(label_3);
		
		JButton btnCurrent = new JButton("Current");
		btnCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReportCurrent rc=new ReportCurrent();
					rc.setUndecorated(true);
					rc.setVisible(true);
					
					
				} catch (Exception ew) {
					System.out.println(ew.getMessage());
				}
			}
		});
		btnCurrent.setForeground(Color.WHITE);
		btnCurrent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCurrent.setBackground(new Color(204, 0, 102));
		btnCurrent.setBounds(0, 170, 153, 52);
		panel_4.add(btnCurrent);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(499, 101, 153, 222);
		contentPane.add(panel_5);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(Report.class.getResource("/BankWelcomePageImage/recurringr.png")));
		label_4.setBounds(10, 0, 128, 169);
		panel_5.add(label_4);
		
		JButton btnRecurring = new JButton("Recurring");
		btnRecurring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReportRecurring rr=new ReportRecurring();
					rr.setUndecorated(true);
					rr.setVisible(true);
					
				} catch (Exception ew) {
					System.out.println(ew.getMessage());
				}
			}
		});
		btnRecurring.setForeground(Color.WHITE);
		btnRecurring.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRecurring.setBackground(new Color(204, 102, 51));
		btnRecurring.setBounds(0, 170, 153, 52);
		panel_5.add(btnRecurring);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBounds(662, 101, 153, 222);
		contentPane.add(panel_6);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(Report.class.getResource("/BankWelcomePageImage/Fdr.png")));
		label_5.setBounds(10, 0, 133, 169);
		panel_6.add(label_5);
		
		JButton btnFd_1 = new JButton("Fd");
		btnFd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReportFd rf=new ReportFd();
					rf.setUndecorated(true);
					rf.setVisible(true);
					
				} catch (Exception ew) {
					System.out.println(ew.getMessage());
				}

			}
		});
		btnFd_1.setForeground(Color.WHITE);
		btnFd_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFd_1.setBackground(Color.BLUE);
		btnFd_1.setBounds(0, 170, 153, 52);
		panel_6.add(btnFd_1);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBounds(173, 331, 153, 222);
		contentPane.add(panel_7);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(Report.class.getResource("/BankWelcomePageImage/schemer.png")));
		label_6.setBounds(10, 0, 133, 169);
		panel_7.add(label_6);
		
		JButton btnSchecme = new JButton("Scheme");
		btnSchecme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReportSchme rss=new ReportSchme();
					rss.setUndecorated(true);
					rss.setVisible(true);
					
					
					
				} catch (Exception ew) {
					System.out.println(ew.getMessage());
				}
			}
		});
		btnSchecme.setForeground(Color.WHITE);
		btnSchecme.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSchecme.setBackground(Color.RED);
		btnSchecme.setBounds(0, 170, 153, 52);
		panel_7.add(btnSchecme);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBounds(336, 331, 153, 222);
		contentPane.add(panel_8);
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon(Report.class.getResource("/BankWelcomePageImage/commison.png")));
		label_7.setBounds(10, 0, 128, 169);
		panel_8.add(label_7);
		
		JButton btnCommission = new JButton("Commission");
		btnCommission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReportCommition rc=new ReportCommition();
					rc.setUndecorated(true);
					rc.setVisible(true);
					
					
				} catch (Exception ew) {
					System.out.println(ew.getMessage());
				}
			}
		});
		btnCommission.setForeground(Color.WHITE);
		btnCommission.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCommission.setBackground(Color.GREEN);
		btnCommission.setBounds(0, 170, 153, 52);
		panel_8.add(btnCommission);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_9.setBounds(499, 331, 153, 222);
		contentPane.add(panel_9);
		
		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(Report.class.getResource("/BankWelcomePageImage/employeesr.jpg")));
		label_8.setBounds(10, 0, 128, 169);
		panel_9.add(label_8);
		
		JButton btnEmployees = new JButton("Employees");
		btnEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReportEmployess re=new ReportEmployess();
					re.setUndecorated(true);
					re.setVisible(true);
					
					
				} catch (Exception ew) {
					System.out.println(ew.getMessage());
				}
			}
		});
		btnEmployees.setForeground(Color.WHITE);
		btnEmployees.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEmployees.setBackground(Color.BLUE);
		btnEmployees.setBounds(0, 170, 153, 52);
		panel_9.add(btnEmployees);
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBounds(662, 331, 153, 222);
		contentPane.add(panel_10);
		
		JLabel label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon(Report.class.getResource("/BankWelcomePageImage/bankr.jpg")));
		label_9.setBounds(10, 0, 128, 169);
		panel_10.add(label_9);
		
		JButton btnBank = new JButton("Bank");
		btnBank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReportBank rb=new ReportBank();
					rb.setUndecorated(true);
					rb.setVisible(true);
				} catch (Exception ew) {
					System.out.println(ew.getMessage());
				}
			}
		});
		btnBank.setForeground(Color.WHITE);
		btnBank.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBank.setBackground(Color.LIGHT_GRAY);
		btnBank.setBounds(0, 170, 153, 52);
		panel_10.add(btnBank);
	}
}
