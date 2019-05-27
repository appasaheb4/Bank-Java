package Pages;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import CLASS.CalculatorGUI;
import CLASS.Clock;


import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JDesktopPane;

public class Welcome {
	static JFrame frame;
	public JLabel lblSaving;
	public JLabel lblLoan;
	public Connection conn;
	public PreparedStatement ps;
	public PreparedStatement ps1;
	public PreparedStatement ps2;
	public ResultSet rs;
	public ResultSet rs1;
	public String path = null;
	public String path1 = null;
	public static JPanel panel_Dashboard;
	public static JPanel panel_Titleshow;
	public JPanel panel_insideall;
	public JPanel panel_allpages;
	public static boolean color;
	public static JLabel lblLocation;

	

	/**
	 * Launch the application.ss
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					Welcome window = new Welcome();
					window.frame.setVisible(true);
					panel_Dashboard.requestDefaultFocus();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws InterruptedException
	 */
	public Welcome() throws InterruptedException {
		initialize();
	}

	public static void call() {
		try {
			Welcome window = new Welcome();
			window.frame.setVisible(true);
		} catch (Exception ca) {
			System.out.println(ca.getMessage());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws InterruptedException
	 */
	private void initialize() throws InterruptedException {
		frame = new JFrame();
		frame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int x=e.getXOnScreen();
				int y=e.getYOnScreen();
				lblLocation.setText("X:"+x+" "+"Y:"+y );
				
			}
		});
		
		

		frame.setBounds(100, 100, 1378, 740);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);

		JPanel panel_clock = new JPanel();
		panel_clock.setBorder(new LineBorder(new Color(0, 128, 0), 4));
		panel_clock.setBackground(Color.LIGHT_GRAY);
		panel_clock.setBounds(0, 0, 184, 207);
		loandurationend();
		loanupcoming();
		Clock c_clock = new Clock();
		c_clock.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.RED, Color.RED, Color.RED, Color.RED));
		c_clock.setForeground(Color.RED);
		c_clock.setBackground(Color.RED);
		c_clock.setBounds(10, 11, 164, 186);
		c_clock.setPreferredSize(new Dimension(100, 100));
		panel_clock.add(c_clock);
		c_clock.start();
		frame.getContentPane().add(panel_clock);
		panel_clock.setLayout(null);

		panel_Dashboard = new JPanel();
		panel_Dashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				LoanA_C.amountupdate();

			}
		});
		panel_Dashboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_Dashboard.setBackground(Color.BLACK);
		panel_Dashboard.setBounds(0, 206, 184, 608);
		frame.getContentPane().add(panel_Dashboard);
		panel_Dashboard.setLayout(null);

		JButton btnNewButton_1 = new JButton("LOAN A/C");
		btnNewButton_1.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/loan1.png")));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_1.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setForeground(Color.WHITE);
				btnNewButton_1.setBorderPainted(false);
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoanA_C lo = new LoanA_C();
				lo.getContentPane().add(panel_allpages);
				lo.setUndecorated(true);
				lo.setVisible(true);
				// frame.disable();
			}
		});
		
		
		
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(0, 95, 184, 25);
		btnNewButton_1.setMnemonic(KeyEvent.VK_L);

		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		panel_Dashboard.add(btnNewButton_1);

		JButton btnSavingAc = new JButton("SAVING A/C");
		btnSavingAc.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/saving.png")));
		btnSavingAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SavingA_C sv = new SavingA_C();
				sv.getContentPane().add(panel_allpages);
				sv.setUndecorated(true);
				sv.setVisible(true);
				// frame.disable();
			}
		});
		btnSavingAc.setHorizontalAlignment(SwingConstants.LEADING);
		btnSavingAc.setVerticalAlignment(SwingConstants.TOP);
		btnSavingAc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSavingAc.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSavingAc.setForeground(Color.WHITE);
				btnSavingAc.setBorderPainted(false);
			}
		});
		btnSavingAc.setForeground(Color.WHITE);
		btnSavingAc.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSavingAc.setBounds(0, 125, 184, 25);
		btnSavingAc.setMnemonic(KeyEvent.VK_S);
		btnSavingAc.setOpaque(false);
		btnSavingAc.setContentAreaFilled(false);
		btnSavingAc.setBorderPainted(false);
		panel_Dashboard.add(btnSavingAc);

		JButton btnCurrentAc = new JButton("CURRENT A/C");
		btnCurrentAc.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/current.png")));
		btnCurrentAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CurrentA_C curr = new CurrentA_C();
				curr.getContentPane().add(panel_allpages);
				curr.setUndecorated(true);
				curr.setVisible(true);
				// frame.disable();
			}
		});
		btnCurrentAc.setHorizontalAlignment(SwingConstants.LEADING);
		btnCurrentAc.setVerticalAlignment(SwingConstants.TOP);
		btnCurrentAc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCurrentAc.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCurrentAc.setForeground(Color.WHITE);
				btnCurrentAc.setBorderPainted(false);
			}
		});
		btnCurrentAc.setForeground(Color.WHITE);
		btnCurrentAc.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCurrentAc.setBounds(0, 155, 184, 25);
		btnCurrentAc.setMnemonic(KeyEvent.VK_C);
		btnCurrentAc.setOpaque(false);
		btnCurrentAc.setContentAreaFilled(false);
		btnCurrentAc.setBorderPainted(false);
		panel_Dashboard.add(btnCurrentAc);

		JButton btnFd = new JButton("FD");
		btnFd.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/fd.png")));
		btnFd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FDA_C fd = new FDA_C();
				fd.getContentPane().add(panel_allpages);
				fd.setUndecorated(true);
				fd.setVisible(true);
				// frame.disable();
			}
		});
		btnFd.setHorizontalAlignment(SwingConstants.LEADING);
		btnFd.setVerticalAlignment(SwingConstants.TOP);
		btnFd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnFd.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnFd.setForeground(Color.WHITE);
				btnFd.setBorderPainted(false);
			}
		});
		btnFd.setForeground(Color.WHITE);
		btnFd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFd.setBounds(0, 215, 184, 25);
		btnFd.setMnemonic(KeyEvent.VK_F);
		btnFd.setOpaque(false);
		btnFd.setContentAreaFilled(false);
		btnFd.setBorderPainted(false);
		panel_Dashboard.add(btnFd);

		JButton btnShares = new JButton("SHARES");
		btnShares.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/share.png")));
		btnShares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SharesA_C sh = new SharesA_C();
				sh.getContentPane().add(panel_allpages);
				sh.setUndecorated(true);
				sh.setVisible(true);
				// frame.disable();

			}
		});
		btnShares.setHorizontalAlignment(SwingConstants.LEADING);
		btnShares.setVerticalAlignment(SwingConstants.TOP);
		btnShares.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnShares.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnShares.setForeground(Color.WHITE);
				btnShares.setBorderPainted(false);
			}
		});
		btnShares.setForeground(Color.WHITE);
		btnShares.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnShares.setBounds(0, 280, 184, 25);
		btnShares.setMnemonic(KeyEvent.VK_H);
		btnShares.setOpaque(false);
		btnShares.setContentAreaFilled(false);
		btnShares.setBorderPainted(false);
		panel_Dashboard.add(btnShares);

		JButton btnSchecmeAc = new JButton("SCHECME A/C");
		btnSchecmeAc.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/scheme.png")));
		btnSchecmeAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SchemeA_C sch = new SchemeA_C();
				sch.getContentPane().add(panel_allpages);
				sch.setUndecorated(true);
				sch.setVisible(true);
				// frame.disable();
			}
		});
		btnSchecmeAc.setHorizontalAlignment(SwingConstants.LEADING);
		btnSchecmeAc.setVerticalAlignment(SwingConstants.TOP);
		btnSchecmeAc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSchecmeAc.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSchecmeAc.setForeground(Color.WHITE);
				btnSchecmeAc.setBorderPainted(false);
			}
		});
		btnSchecmeAc.setForeground(Color.WHITE);
		btnSchecmeAc.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSchecmeAc.setBounds(0, 341, 184, 25);
		btnSchecmeAc.setMnemonic(KeyEvent.VK_M);
		btnSchecmeAc.setOpaque(false);
		btnSchecmeAc.setContentAreaFilled(false);
		btnSchecmeAc.setBorderPainted(false);
		panel_Dashboard.add(btnSchecmeAc);

		JButton btnCommissionAc = new JButton("COMMISSION A/C");
		btnCommissionAc.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/agent.png")));
		btnCommissionAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommissionA_C comm = new CommissionA_C();
				comm.getContentPane().add(panel_allpages);
				comm.setUndecorated(true);
				comm.setVisible(true);
				// frame.disable();
			}
		});
		btnCommissionAc.setHorizontalAlignment(SwingConstants.LEADING);
		btnCommissionAc.setVerticalAlignment(SwingConstants.TOP);
		btnCommissionAc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCommissionAc.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCommissionAc.setForeground(Color.WHITE);
				btnCommissionAc.setBorderPainted(false);
			}
		});
		btnCommissionAc.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCommissionAc.setForeground(Color.WHITE);
		btnCommissionAc.setBounds(0, 371, 184, 25);
		btnCommissionAc.setMnemonic(KeyEvent.VK_O);

		btnCommissionAc.setOpaque(false);
		btnCommissionAc.setContentAreaFilled(false);
		btnCommissionAc.setBorderPainted(false);
		panel_Dashboard.add(btnCommissionAc);

		JButton btnExit = new JButton("EXIT");
		btnExit.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/exit44.png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DayBook.showoppningamt();
				copy();
				System.exit(0);
			}
		});
		btnExit.setHorizontalAlignment(SwingConstants.LEADING);
		btnExit.setVerticalAlignment(SwingConstants.TOP);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setForeground(Color.WHITE);
				btnExit.setBorderPainted(false);
			}
		});
		btnExit.setOpaque(false);
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setContentAreaFilled(false);
		btnExit.setBorderPainted(false);
		btnExit.setBounds(0, 521, 184, 36);
		btnExit.setMnemonic(KeyEvent.VK_X);
		panel_Dashboard.add(btnExit);

		JButton btnReport = new JButton("REPORT");
		btnReport.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/report44.png")));
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Report re = new Report();
				re.getContentPane().add(panel_allpages);
				re.setUndecorated(true);
				re.setVisible(true);
				// frame.disable();
			}
		});
		btnReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReport.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnReport.setForeground(Color.WHITE);
			}
		});
		btnReport.setVerticalAlignment(SwingConstants.TOP);
		btnReport.setOpaque(false);
		btnReport.setHorizontalAlignment(SwingConstants.LEADING);
		btnReport.setForeground(Color.WHITE);
		btnReport.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReport.setContentAreaFilled(false);
		btnReport.setBorderPainted(false);
		btnReport.setBounds(0, 461, 184, 25);
		btnReport.setMnemonic(KeyEvent.VK_P);

		panel_Dashboard.add(btnReport);

		JButton btnDashboard = new JButton("DASHBOARD");
		btnDashboard.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/dashboard.png")));
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDashboard.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnDashboard.setForeground(Color.WHITE);
				btnDashboard.setBorderPainted(false);
			}
		});
		btnDashboard.setVerticalAlignment(SwingConstants.TOP);
		btnDashboard.setOpaque(false);
		btnDashboard.setHorizontalAlignment(SwingConstants.LEADING);
		btnDashboard.setForeground(Color.WHITE);
		btnDashboard.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDashboard.setMnemonic(KeyEvent.VK_D);
		btnDashboard.setContentAreaFilled(false);
		btnDashboard.setBorderPainted(false);
		btnDashboard.setBounds(0, 5, 184, 25);
		panel_Dashboard.add(btnDashboard);

		JMenuBar menuBar = new JMenuBar();
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuBar.setBorderPainted(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuBar.setForeground(Color.green);
				menuBar.setBorderPainted(false);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuBar.setForeground(Color.WHITE);
				menuBar.setBorderPainted(false);
			}
		});
		menuBar.setFont(new Font("Tahoma", Font.BOLD, 18));
		menuBar.setBackground(Color.BLACK);
		menuBar.setForeground(Color.WHITE);
		menuBar.setBorderPainted(false);
		menuBar.setBounds(12, 245, 200, 35);
		panel_Dashboard.add(menuBar);

		JMenu mnNewMenu = new JMenu("INTEREST A/C");
		mnNewMenu.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/interst.png")));
		mnNewMenu.setLocation(0, 0);
		mnNewMenu.setMnemonic(KeyEvent.VK_I);
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mnNewMenu.setForeground(Color.green);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				mnNewMenu.setForeground(Color.WHITE);
				mnNewMenu.setBorderPainted(false);
			}
		});
		mnNewMenu.setFont(new Font("Tahoma", Font.BOLD, 14));
		mnNewMenu.setBackground(Color.BLACK);
		mnNewMenu.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu);

		JMenu mnNewMenu_1 = new JMenu("Income");
		mnNewMenu_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		mnNewMenu.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("Loan");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IncomeLoan inl = new IncomeLoan();
				inl.getContentPane().add(panel_allpages);
				inl.setUndecorated(true);
				inl.setVisible(true);
			}
		});
		mntmNewMenuItem.setFont(new Font("Tahoma", Font.BOLD, 12));
		mnNewMenu_1.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Commission");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IncomeCommisstion comm = new IncomeCommisstion();
				comm.setUndecorated(true);
				comm.setVisible(true);

			}
		});
		mntmNewMenuItem_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenu mnNewMenu_2 = new JMenu("Outgoing");
		mnNewMenu_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		mnNewMenu.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Saving");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OutgoingSaving outs = new OutgoingSaving();
				outs.getContentPane().add(panel_allpages);
				outs.setUndecorated(true);
				outs.setVisible(true);
			}
		});
		mntmNewMenuItem_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		mnNewMenu_2.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("FD");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OutgoingFd outFd = new OutgoingFd();
				outFd.getContentPane().add(panel_allpages);
				outFd.setUndecorated(true);
				outFd.setVisible(true);

			}
		});
		mntmNewMenuItem_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		mnNewMenu_2.add(mntmNewMenuItem_6);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Schemes(Doubling Amount)");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OutgoingShemes outsc = new OutgoingShemes();
				outsc.getContentPane().add(panel_allpages);
				outsc.setUndecorated(true);
				outsc.setVisible(true);
			}
		});
		mntmNewMenuItem_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		mnNewMenu_2.add(mntmNewMenuItem_7);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Recurring");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OutgoingRecurring outre = new OutgoingRecurring();
				outre.getContentPane().add(panel_allpages);
				outre.setUndecorated(true);
				outre.setVisible(true);
			}
		});
		mntmNewMenuItem_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		mnNewMenu_2.add(mntmNewMenuItem_8);

		JButton btnAccount = new JButton("ACCOUNT");
		btnAccount.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/Account.png")));
		btnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAccount.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAccount.setForeground(Color.WHITE);
				btnAccount.setBorderPainted(false);
			}
		});
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account o = new Account();
				o.setUndecorated(true);
				o.setVisible(true);

				// jAccount o=new jAccount();
				// panel_allpages.add(o);
				// o.show();

			}
		});
		btnAccount.setVerticalAlignment(SwingConstants.TOP);
		btnAccount.setOpaque(false);
		btnAccount.setHorizontalAlignment(SwingConstants.LEADING);
		btnAccount.setForeground(Color.WHITE);
		btnAccount.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAccount.setContentAreaFilled(false);
		btnAccount.setBorderPainted(false);
		btnAccount.setBounds(0, 35, 184, 25);
		btnAccount.setMnemonic(KeyEvent.VK_A);
		panel_Dashboard.add(btnAccount);

		JButton btnE = new JButton("EMPLOYEES");
		btnE.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/emp.png")));
		btnE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnE.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnE.setForeground(Color.WHITE);
				btnE.setBorderPainted(false);
			}
		});
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employee emp = new Employee();
				emp.getContentPane().add(panel_allpages);
				emp.setUndecorated(true);
				emp.setVisible(true);
				// frame.disable();
			}
		});
		btnE.setVerticalAlignment(SwingConstants.TOP);
		btnE.setOpaque(false);
		btnE.setHorizontalAlignment(SwingConstants.LEADING);
		btnE.setForeground(Color.WHITE);
		btnE.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnE.setContentAreaFilled(false);
		btnE.setBorderPainted(false);
		btnE.setBounds(0, 401, 184, 25);
		btnE.setMnemonic(KeyEvent.VK_Y);
		panel_Dashboard.add(btnE);

		JButton btnBankOwner = new JButton("BANK OWNER");
		btnBankOwner.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/Bank.png")));
		btnBankOwner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankOwnerMainPage bak = new BankOwnerMainPage();
				bak.getContentPane().add(panel_allpages);
				bak.setUndecorated(true);
				bak.setVisible(true);
				// frame.disable();
			}
		});
		btnBankOwner.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBankOwner.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnBankOwner.setForeground(Color.WHITE);
				btnBankOwner.setBorderPainted(false);
			}
		});
		btnBankOwner.setVerticalAlignment(SwingConstants.TOP);
		btnBankOwner.setOpaque(false);
		btnBankOwner.setHorizontalAlignment(SwingConstants.LEADING);
		btnBankOwner.setForeground(Color.WHITE);
		btnBankOwner.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBankOwner.setContentAreaFilled(false);
		btnBankOwner.setBorderPainted(false);
		btnBankOwner.setBounds(0, 431, 184, 25);
		btnBankOwner.setMnemonic(KeyEvent.VK_N);
		panel_Dashboard.add(btnBankOwner);

		JButton btnRecurring = new JButton("Recurring");
		btnRecurring.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/recurring.png")));
		btnRecurring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Recurring rec = new Recurring();
				rec.getContentPane().add(panel_allpages);
				rec.setUndecorated(true);
				rec.setVisible(true);
				// frame.disable();
			}
		});
		btnRecurring.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnRecurring.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRecurring.setForeground(Color.WHITE);
				btnRecurring.setBorderPainted(false);

			}
		});
		btnRecurring.setVerticalAlignment(SwingConstants.TOP);
		btnRecurring.setOpaque(false);
		btnRecurring.setHorizontalAlignment(SwingConstants.LEADING);
		btnRecurring.setForeground(Color.WHITE);
		btnRecurring.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRecurring.setContentAreaFilled(false);
		btnRecurring.setBorderPainted(false);
		btnRecurring.setBounds(0, 185, 184, 25);
		btnRecurring.setMnemonic(KeyEvent.VK_R);
		panel_Dashboard.add(btnRecurring);

		JButton btnDayBook = new JButton("DAY BOOK");
		btnDayBook.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/Day Book.png")));
		btnDayBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DayBook db = new DayBook();
				db.setUndecorated(true);
				db.setVisible(true);
			}
		});
		btnDayBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnDayBook.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnDayBook.setForeground(Color.WHITE);
				btnDayBook.setBorderPainted(false);
			}
		});
		btnDayBook.setVerticalAlignment(SwingConstants.TOP);
		btnDayBook.setOpaque(false);
		btnDayBook.setHorizontalAlignment(SwingConstants.LEADING);
		btnDayBook.setForeground(Color.WHITE);
		btnDayBook.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDayBook.setContentAreaFilled(false);
		btnDayBook.setBorderPainted(false);
		btnDayBook.setBounds(0, 65, 184, 25);
		btnDayBook.setMnemonic(KeyEvent.VK_B);
		panel_Dashboard.add(btnDayBook);

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		menuBar_1.setBorderPainted(false);
		menuBar_1.setForeground(Color.WHITE);
		menuBar_1.setBackground(Color.BLACK);
		menuBar_1.setBounds(11, 315, 400, 21);

		panel_Dashboard.add(menuBar_1);

		JMenu mnNewMenu_3 = new JMenu("EXPENDITURE");
		mnNewMenu_3.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/exp.png")));
		mnNewMenu_3.setMnemonic(KeyEvent.VK_E);
		mnNewMenu_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				mnNewMenu_3.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mnNewMenu_3.setForeground(Color.WHITE);
				mnNewMenu_3.setBorderPainted(false);

			}
		});
		mnNewMenu_3.setLocation(0, 0);
		mnNewMenu_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		mnNewMenu_3.setForeground(Color.WHITE);
		mnNewMenu_3.setBackground(Color.BLACK);
		menuBar_1.add(mnNewMenu_3);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Expenditure Income");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExpenditureIncomde exi = new ExpenditureIncomde();
				exi.getContentPane().add(panel_allpages);
				exi.setUndecorated(true);
				exi.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Expenditure Outgoing");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Expenditure ex = new Expenditure();
				ex.getContentPane().add(panel_allpages);
				ex.setUndecorated(true);
				ex.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_3);

		JButton btnSettin = new JButton("SETTING");
		btnSettin.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/setting77.png")));
		btnSettin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Settingspage set = new Settingspage();
				set.getContentPane().add(panel_allpages);
				set.setUndecorated(true);
				set.setVisible(true);
				// frame.disable();
			}
		});
		btnSettin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnSettin.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnSettin.setForeground(Color.WHITE);
				btnSettin.setBorderPainted(false);
			}
		});
		btnSettin.setVerticalAlignment(SwingConstants.TOP);
		btnSettin.setOpaque(false);
		btnSettin.setHorizontalAlignment(SwingConstants.LEADING);
		btnSettin.setForeground(Color.WHITE);
		btnSettin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSettin.setContentAreaFilled(false);
		btnSettin.setBorderPainted(false);
		btnSettin.setBounds(0, 491, 184, 25);
		btnSettin.setMnemonic(KeyEvent.VK_T);

		panel_Dashboard.add(btnSettin);

		panel_Titleshow = new JPanel();
		panel_Titleshow.setBounds(182, 0, 1186, 59);
		frame.getContentPane().add(panel_Titleshow);
		panel_Titleshow.setLayout(null);

		JButton btnNewButton = new JButton("X");
		btnNewButton.setForeground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copy();
				System.exit(0);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewButton.setBounds(1112, 0, 64, 55);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		panel_Titleshow.add(btnNewButton);

		lblLoan = new JLabel("Up Coming Due");
		lblLoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LoanDurationUpComing len = new LoanDurationUpComing();
				len.getContentPane().add(panel_allpages);
				len.setUndecorated(true);
				len.setVisible(true);

			}
		});
		lblLoan.setEnabled(false);
		lblLoan.setVerticalTextPosition(SwingConstants.TOP);
		lblLoan.setForeground(Color.BLUE);
		lblLoan.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblLoan.setBounds(229, 13, 251, 44);

		loandurationend();
		loanupcoming();
		panel_Titleshow.add(lblLoan);

		lblSaving = new JLabel("Due Over");
		lblSaving.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoanDurationEnd loe = new LoanDurationEnd();
				loe.setUndecorated(true);
				loe.setVisible(true);
			}
		});
		lblSaving.setEnabled(false);
		lblSaving.setForeground(Color.RED);
		lblSaving.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSaving.setBounds(547, 13, 136, 39);

		panel_Titleshow.add(lblSaving);

		JButton button_2 = new JButton("");
		button_2.setVisible(false);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CalculatorGUI cl = new CalculatorGUI("Bank System");

				cl.setSize(400, 350);
				cl.setFocusable(true);
				cl.setLocation(830, 0);
				cl.setVisible(true);

			}
		});
		button_2.setIcon(new ImageIcon(Welcome.class.getResource("/ImageButtonIcon/clac.png")));
		button_2.setOpaque(false);
		button_2.setForeground(Color.RED);
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button_2.setContentAreaFilled(false);
		button_2.setBorderPainted(false);
		button_2.setBounds(1040, 0, 64, 55);
		panel_Titleshow.add(button_2);
		
		 lblLocation = new JLabel("");
		lblLocation.setBounds(10, 0, 154, 14);
		panel_Titleshow.add(lblLocation);

		panel_insideall = new JPanel();
		panel_insideall.setLayout(null);
		panel_insideall.setBounds(194, 61, 1174, 668);
		frame.getContentPane().add(panel_insideall);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(220, 11, 142, 222);
		panel_insideall.add(panel_4);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Welcome.class.getResource("/BankWelcomePageImage/DAYBOOK.jpg")));
		label_1.setBounds(10, 0, 122, 169);
		panel_4.add(label_1);

		JButton btnLoanAc = new JButton("DAY BOOK");
		btnLoanAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DayBook db = new DayBook();
				db.getContentPane().add(panel_allpages);
				db.setUndecorated(true);
				db.setVisible(true);
				// frame.disable();
			}
		});
		btnLoanAc.setForeground(Color.WHITE);
		btnLoanAc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLoanAc.setBackground(new Color(210, 105, 30));
		btnLoanAc.setBounds(0, 170, 142, 52);

		panel_4.add(btnLoanAc);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(429, 11, 142, 222);
		panel_insideall.add(panel_5);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Welcome.class.getResource("/BankWelcomePageImage/loan2.png")));
		label_2.setBounds(10, 0, 122, 169);
		panel_5.add(label_2);

		JButton btnSavingAc_1 = new JButton("LOAN A/C");
		btnSavingAc_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoanA_C sa = new LoanA_C();
				sa.getContentPane().add(panel_allpages);
				sa.setUndecorated(true);
				sa.setVisible(true);

				// frame.disable();

			}
		});
		btnSavingAc_1.setForeground(Color.WHITE);
		btnSavingAc_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSavingAc_1.setBackground(Color.RED);
		btnSavingAc_1.setBounds(0, 170, 142, 52);
		btnSavingAc_1.setMnemonic(KeyEvent.VK_L);
		panel_5.add(btnSavingAc_1);

		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBounds(838, 11, 142, 222);
		panel_insideall.add(panel_6);

		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(Welcome.class.getResource("/BankWelcomePageImage/currentac.png")));
		label_3.setBounds(10, 0, 122, 169);
		panel_6.add(label_3);

		JButton btnFd_1 = new JButton("CURRENT A/C");
		btnFd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CurrentA_C fd = new CurrentA_C();
				fd.getContentPane().add(panel_allpages);
				fd.setUndecorated(true);
				fd.setVisible(true);
				// frame.disable();
			}
		});
		btnFd_1.setForeground(Color.WHITE);
		btnFd_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFd_1.setBackground(new Color(32, 178, 170));
		btnFd_1.setBounds(0, 170, 142, 52);

		panel_6.add(btnFd_1);

		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_9.setBounds(638, 11, 142, 222);
		panel_insideall.add(panel_9);

		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(Welcome.class.getResource("/BankWelcomePageImage/saving3.png")));
		label_6.setBounds(10, 0, 122, 169);
		panel_9.add(label_6);

		JButton btnCurrentAc_1 = new JButton("SAVING A/C");
		btnCurrentAc_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SavingA_C cur = new SavingA_C();
				cur.getContentPane().add(panel_allpages);
				cur.setUndecorated(true);
				cur.setVisible(true);
				// frame.disable();
			}
		});
		btnCurrentAc_1.setForeground(Color.WHITE);
		btnCurrentAc_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCurrentAc_1.setBackground(new Color(127, 255, 0));
		btnCurrentAc_1.setBounds(0, 170, 142, 52);

		panel_9.add(btnCurrentAc_1);

		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBounds(429, 248, 142, 222);
		panel_insideall.add(panel_10);

		JLabel label_7 = new JLabel("");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setIcon(new ImageIcon(Welcome.class.getResource("/BankWelcomePageImage/schema.png")));
		label_7.setBounds(10, 0, 122, 169);
		panel_10.add(label_7);

		JButton btnInterestAc_1 = new JButton("SCHEME A/C");
		btnInterestAc_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SchemeA_C ints = new SchemeA_C();
				ints.getContentPane().add(panel_allpages);
				ints.setUndecorated(true);
				ints.setVisible(true);
				// frame.disable();
			}
		});
		btnInterestAc_1.setForeground(Color.WHITE);
		btnInterestAc_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInterestAc_1.setBackground(new Color(0, 153, 51));
		btnInterestAc_1.setBounds(0, 170, 142, 52);
		btnInterestAc_1.setMnemonic(KeyEvent.VK_M);
		panel_10.add(btnInterestAc_1);

		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.setBounds(638, 248, 142, 222);
		panel_insideall.add(panel_11);

		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(Welcome.class.getResource("/BankWelcomePageImage/commison1.png")));
		label_8.setBounds(10, 0, 122, 169);
		panel_11.add(label_8);

		JButton btnSchecmeAc_1 = new JButton("COMMITION A/C");
		btnSchecmeAc_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommissionA_C sc = new CommissionA_C();
				sc.getContentPane().add(panel_allpages);
				sc.setUndecorated(true);
				sc.setVisible(true);
				// frame.disable();
			}
		});
		btnSchecmeAc_1.setForeground(Color.WHITE);
		btnSchecmeAc_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSchecmeAc_1.setBackground(new Color(255, 204, 102));
		btnSchecmeAc_1.setBounds(0, 170, 142, 52);

		panel_11.add(btnSchecmeAc_1);

		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBounds(1023, 11, 142, 222);
		panel_insideall.add(panel_7);

		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(Welcome.class.getResource("/BankWelcomePageImage/Reccringhome.png")));
		label_4.setBounds(10, 0, 122, 169);
		panel_7.add(label_4);

		JButton btnR = new JButton("RECRRING A/C");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Recurring sh = new Recurring();
				sh.getContentPane().add(panel_allpages);
				sh.setUndecorated(true);
				sh.setVisible(true);
				// frame.disable();

			}
		});
		btnR.setForeground(Color.WHITE);
		btnR.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnR.setBackground(new Color(255, 102, 0));
		btnR.setBounds(0, 170, 142, 52);
		panel_7.add(btnR);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBounds(220, 244, 142, 222);
		panel_insideall.add(panel_8);

		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(Welcome.class.getResource("/BankWelcomePageImage/shares.png")));
		label_5.setBounds(10, 0, 122, 169);
		panel_8.add(label_5);

		JButton btnSharesAc = new JButton("SHARES A/C");
		btnSharesAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SharesA_C exp = new SharesA_C();
				exp.getContentPane().add(panel_allpages);
				exp.setUndecorated(true);
				exp.setVisible(true);
				// frame.disable();
			}
		});
		btnSharesAc.setForeground(Color.WHITE);
		btnSharesAc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSharesAc.setBackground(new Color(153, 51, 102));
		btnSharesAc.setBounds(0, 170, 142, 52);

		panel_8.add(btnSharesAc);

		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		panel_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_12.setBounds(838, 248, 142, 222);
		panel_insideall.add(panel_12);

		JLabel label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon(Welcome.class.getResource("/BankWelcomePageImage/EMPLOYEE.png")));
		label_9.setBounds(10, 0, 122, 169);
		panel_12.add(label_9);

		JButton btnCommissionAc_1 = new JButton("EMPLOYEES A/C");
		btnCommissionAc_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employee comm = new Employee();
				comm.getContentPane().add(panel_allpages);
				comm.setUndecorated(true);
				comm.setVisible(true);
				// frame.disable();
			}
		});
		btnCommissionAc_1.setForeground(Color.WHITE);
		btnCommissionAc_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCommissionAc_1.setBackground(Color.BLUE);
		btnCommissionAc_1.setBounds(0, 170, 142, 52);
		panel_12.add(btnCommissionAc_1);

		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_13.setBounds(1023, 248, 142, 222);
		panel_insideall.add(panel_13);

		JLabel label_10 = new JLabel("");
		label_10.setIcon(new ImageIcon(Welcome.class.getResource("/BankWelcomePageImage/bankhome.jpg")));
		label_10.setBounds(10, 0, 122, 169);
		panel_13.add(label_10);

		JButton btnReport_1 = new JButton("BANK ");
		btnReport_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					BankOwner rse;
					rse = new BankOwner();
					rse.getContentPane().add(panel_allpages);
					rse.setUndecorated(true);
					rse.setVisible(true);
					// frame.disable();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnReport_1.setForeground(Color.WHITE);
		btnReport_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReport_1.setBackground(Color.GREEN);
		btnReport_1.setBounds(0, 170, 142, 52);
		panel_13.add(btnReport_1);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(17, 11, 142, 222);
		panel_insideall.add(panel);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Welcome.class.getResource("/BankWelcomePageImage/myaccount.jpg")));
		label.setBounds(10, 0, 122, 169);
		panel.add(label);

		JButton btnAccount_1 = new JButton("ACCOUNT");
		btnAccount_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Account acc = new Account();
				acc.getContentPane().add(panel_allpages);
				acc.setUndecorated(true);
				acc.setVisible(true);
				// frame.disable();
			}
		});
		btnAccount_1.setForeground(Color.WHITE);
		btnAccount_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAccount_1.setBackground(Color.BLUE);
		btnAccount_1.setBounds(0, 170, 142, 52);
		panel.add(btnAccount_1);

		JPanel panel_14 = new JPanel();
		panel_14.setLayout(null);
		panel_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_14.setBounds(17, 248, 142, 222);
		panel_insideall.add(panel_14);

		JLabel label_11 = new JLabel("");
		label_11.setIcon(new ImageIcon(Welcome.class.getResource("/BankWelcomePageImage/FD.png")));
		label_11.setBounds(10, 0, 122, 169);
		panel_14.add(label_11);

		JButton btnFd_2 = new JButton("FD A/C");
		btnFd_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FDA_C fd = new FDA_C();
				fd.getContentPane().add(panel_allpages);
				fd.setUndecorated(true);
				fd.setVisible(true);
				// frame.disable();
			}
		});
		btnFd_2.setForeground(Color.WHITE);
		btnFd_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFd_2.setBackground(new Color(153, 51, 51));
		btnFd_2.setBounds(0, 170, 142, 52);
		panel_14.add(btnFd_2);

		JPanel panel_15 = new JPanel();
		panel_15.setLayout(null);
		panel_15.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_15.setBounds(17, 484, 142, 173);
		panel_insideall.add(panel_15);

		JLabel label_12 = new JLabel("");
		label_12.setIcon(new ImageIcon(Welcome.class.getResource("/Image/imageall/sett.png")));
		label_12.setBounds(10, 24, 122, 114);
		panel_15.add(label_12);

		JButton btnSetting = new JButton("SETTING");
		btnSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settingspage se = new Settingspage();
				se.getContentPane().add(panel_allpages);
				se.setUndecorated(true);
				se.setVisible(true);
				// frame.disable();
			}
		});
		btnSetting.setForeground(Color.WHITE);
		btnSetting.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSetting.setBackground(new Color(0, 0, 0));
		btnSetting.setBounds(0, 142, 142, 31);
		panel_15.add(btnSetting);

		JPanel panel_16 = new JPanel();
		panel_16.setLayout(null);
		panel_16.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_16.setBounds(220, 484, 142, 173);
		panel_insideall.add(panel_16);

		JLabel label_13 = new JLabel("");
		label_13.setIcon(new ImageIcon(Welcome.class.getResource("/Image/imageall/reports_icon.png")));
		label_13.setBounds(10, 24, 122, 114);
		panel_16.add(label_13);

		JButton btnReport_2 = new JButton("REPORT");
		btnReport_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Report ree = new Report();
				ree.getContentPane().add(panel_allpages);
				ree.setUndecorated(true);
				ree.setVisible(true);
				// frame.disable();
			}
		});
		btnReport_2.setForeground(Color.WHITE);
		btnReport_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReport_2.setBackground(new Color(0, 153, 0));
		btnReport_2.setBounds(0, 142, 142, 31);
		panel_16.add(btnReport_2);

		panel_allpages = new JPanel();

		panel_allpages.setBounds(17, 11, 1148, 646);
		panel_insideall.add(panel_allpages);

		JDesktopPane desktopPane = new JDesktopPane();
		panel_allpages.add(desktopPane);

	}

	public void loanupcoming() {
		try {
			conn = (Connection) DBConnection.getConnection();
			String loanre = "select * from banksystem.loan order by Srnomaster;";
			ps = (PreparedStatement) conn.prepareStatement(loanre);
			rs = ps.executeQuery();
			while (rs.next()) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String updatedate = rs.getString("UpdateAmtDate");


				Date currentsysdate = new Date();
				String datedate = (String) sdf.format(currentsysdate);
				String shortdate = datedate.substring(8, 10);
				
				if (shortdate.equals("25") || shortdate.equals("26") || shortdate.equals("27") || shortdate.equals("28")
						|| shortdate.equals("29") || shortdate.equals("30") || shortdate.equals("31")) {
					paydue();

				}


			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (Exception ewww) {
				System.out.println(ewww.getMessage());
			}
		}

	}

	
	public void loandurationend() {
		try {
			conn = (Connection) DBConnection.getConnection();
			String loanre = "select * from banksystem.loan order by Srnomaster;";
			ps = (PreparedStatement) conn.prepareStatement(loanre);
			rs = ps.executeQuery();
			while (rs.next()) {
				Double day = rs.getDouble("Days");
				

				String date = rs.getString("AmtPaidDate");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date datecur = new Date();
				String sysdate = (String) sdf.format(datecur);

				SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
				String inputString1 = date;
				String inputString2 = sysdate;
				Date date1 = myFormat.parse(inputString1);
				Date date2 = myFormat.parse(inputString2);
				long diff = date2.getTime() - date1.getTime();
				long totalday44 = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

				double totalday = Double.valueOf(totalday44);
				

				
				if (totalday > day) {
					
					afterpaydue();

				}

			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (Exception ewww) {
				System.out.println(ewww.getMessage());
			}
		}

	}
	
	public void paydue()  {
		try
		{
		lblLoan.setEnabled(true);

		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean value = (((int) Math.round(Math.random() * 1))) == 0 ? false : true;
				if (value == true)
					lblLoan.setForeground(Color.red);
				else
					lblLoan.setForeground(Color.blue);

			}
		});
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		timer.start();
		}
		catch(Exception ew){
			System.out.println(ew.getMessage());
		}
	}

	public void afterpaydue() {
		try
		{
		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblSaving.setEnabled(true);
				boolean value = (((int) Math.round(Math.random() * 1))) == 0 ? false : true;
				if (value == true)
				{
					lblSaving.setForeground(Color.red);
				
				}
				else
				{
					lblSaving.setForeground(Color.blue);
					
				}
			}
		});
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		timer.start();
		}
		catch(Exception ew)
		{
			System.out.println(ew.getMessage());
		}

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {

				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public void copy() {
		File srcFolder = new File("J:\\Project\\Eclipse\\Bank");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		File destFolder = new File("C:\\Project\\Project2016\\Bank_" + sdf.format(date) + "");
		try {
			if (!destFolder.exists()) {
				destFolder.mkdirs();
				copyFolder(srcFolder, destFolder);
				if (destFolder.exists()) {
					destFolder.renameTo(destFolder);
					try {
						Process p = null;
						path = destFolder.getPath();
						path = path.replace('\\', '/');

						path = path + "Bank" + "_" + sdf.format(date) + ".sql";

						Runtime runtime = Runtime.getRuntime();
						p = runtime
								.exec("C:/Program Files (x86)/MySQL/MySQL Server 5.5/bin/mysqldump.exe -uroot -papple4 --add-drop-database -B banksystem -r"
										+ path);
						int processComplete = p.waitFor();
						if (processComplete == 0) {
							JOptionPane.showMessageDialog(null, "BackUp Creaated Succuss");
						} else {
							JOptionPane.showMessageDialog(null, "BackUp not created");

						}

					} catch (Exception ess) {
						System.out.println(ess.getMessage());
					}

				}
			} else {
				Process p = null;
				path = destFolder.getPath();
				path = path.replace('\\', '/');

				path = path + "Bank" + "_" + sdf.format(date) + ".sql";

				Runtime runtime = Runtime.getRuntime();
				p = runtime
						.exec("C:/Program Files (x86)/MySQL/MySQL Server 5.5/bin/mysqldump.exe -uroot -papple4 --add-drop-database -B banksystem -r"
								+ path);
				int processComplete = p.waitFor();
				if (processComplete == 0) {
					JOptionPane.showMessageDialog(null, "BackUp Creaated Succuss");
				} else {
					JOptionPane.showMessageDialog(null, "BackUp not created");

				}

			}
		} catch (Exception es) {
			System.out.println(es.getMessage());
		}

	}

	private static void copyFolder(File src, File dest) throws IOException {

		if (src.isDirectory()) {
			if (!dest.exists()) {
				dest.mkdir();

			}
			String files[] = src.list();
			for (String file : files) {

				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// copyFolder(srcFile, destFile);
				// //===========Imp=============================
			}

		} else {

			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;

			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();

		}
	}
}
