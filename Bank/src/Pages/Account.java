package Pages;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;
import java.util.Date;

import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;

import CLASS.CalculatorGUI;
import CLASS.FocusTraversalOnArray;


import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseMotionAdapter;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Account extends JFrame {

	private JPanel contentPane;
	private JTable tableAccount;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public DefaultTableModel model1;

	public Connection conn;
	public PreparedStatement ps;
	public PreparedStatement ps1;
	public PreparedStatement ps2;
	public PreparedStatement ps3;
	public String val1;
	public Statement st;
	public JDateChooser dateChooserloangpage;
	java.util.Date dt1, dt2;
	public ResultSet rs;
	public ResultSet rs1;
	private JTextField textFieldAccountNumber;
	private JTextField textFieldName;
	private JTextField textFieldBal;
	private JTextField textFieldAmount;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	public JRadioButton rdbtnbycash;

	public JRadioButton rdbtnByCheque;
	private JTextField textFieldChequeNumber;
	public JPanel panel_bycheqyedetails;
	public JPanel panel_Button;
	public JPanel panel_Amount;
	public JScrollPane scrollPane_1;
	public JList<String> list;
	public String[] data = new String[1000];
	public String[] data11 = new String[1000];
	public String[] data1 = new String[1000];

	public JPanel panel_Listno;

	public JPanel panel_name;
	private JScrollPane scrollPane_name;
	private JList<String> list_name;
	private JTextField textFieldBy;
	public static int isrno;
	public static int isrno1;
	public static int isrno2;
	public JButton btnDeposite;
	public DecimalFormat df = new DecimalFormat("#.##");
	private JTextField textFieldTransactionParticulars;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Account().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Account() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int x=e.getXOnScreen();
				int y=e.getYOnScreen();
				Welcome.lblLocation.setText("X:"+x+" "+"Y:"+y );
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				textFieldAccountNumber.requestFocus();
			}
		});

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();

		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				try {
					String accountnotop = textFieldAccountNumber.getText();
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.loan where AccountNumber=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldAccountNumber.getText());
					rs = ps.executeQuery();
					while (rs.next()) {

						String accountno = rs.getString("AccountNumber");
						if (accountnotop.equals(accountno)) {
							btnDeposite.setEnabled(false);
						}

					}

				} catch (Exception ee) {
					System.out.println(ee.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}
			}
		});

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1154, 98);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblAccount = new JLabel("Account");
		lblAccount.setBounds(10, 23, 276, 42);
		lblAccount.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblAccount);

		Date date = new Date();
		JDateChooser dateChooserAccount = new JDateChooser();
		dateChooserAccount.setBounds(948, 71, 196, 20);
		dateChooserAccount.setDateFormatString("yyyy-MM-dd");
		dateChooserAccount.setDate(date);
		panel.add(dateChooserAccount);

		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculatorGUI cl = new CalculatorGUI("Bank System");
				cl.setSize(400, 350);
				cl.setFocusable(true);
				cl.setLocation(830, 0);
				cl.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(Account.class.getResource("/ImageButtonIcon/clac.png")));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});
		button.setOpaque(false);
		button.setForeground(Color.RED);
		button.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBounds(1071, 8, 64, 55);
		panel.add(button);
		
		JLabel lblsavingcurrentloan = new JLabel("(Saving,Current,Loan)");
		lblsavingcurrentloan.setForeground(Color.BLACK);
		lblsavingcurrentloan.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblsavingcurrentloan.setBounds(20, 56, 276, 42);
		panel.add(lblsavingcurrentloan);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 109, 1144, 38);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Account Number:");
		lblNewLabel.setBounds(10, 11, 114, 14);
		panel_1.add(lblNewLabel);

		textFieldAccountNumber = new JTextField();
		textFieldAccountNumber.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				datashow();
			}
		});

		textFieldAccountNumber.addKeyListener(new KeyAdapter() {
			@Override
			
			public void keyReleased(KeyEvent e) {
				
				try {

					conn = DBConnection.getConnection();
					String query = "select * from banksystem.loan where AccountNumber like '%"
							+ textFieldAccountNumber.getText() + "%'";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					int i = 1;
					while (rs.next()) {
						String name = rs.getString("AccountNumber");
						data[i] = name;
						i++;

					}
					list.setListData(data);
					list.setVisibleRowCount(100);
					panel_Amount.setVisible(false);
					panel_Button.setVisible(false);
					panel_Listno.setVisible(true);
					scrollPane_1.setVisible(true);

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}
				try {

					conn = DBConnection.getConnection();
					String query = "select * from banksystem.saving where AcountNumber like '"
							+ textFieldAccountNumber.getText() + "%'";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					int i = 0;
					while (rs.next()) {
						String name = rs.getString("AcountNumber");

						data[i] = name;
						i++;

					}
					list.setListData(data);
					list.setVisibleRowCount(100);
					panel_Amount.setVisible(false);
					panel_Button.setVisible(false);
					panel_Listno.setVisible(true);
					scrollPane_1.setVisible(true);

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}
				try {

					conn = DBConnection.getConnection();
					String query = "select * from banksystem.current where AccountNumber like '"
							+ textFieldAccountNumber.getText() + "%'";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					int i = 0;
					while (rs.next()) {
						String name = rs.getString("AccountNumber");

						data[i] = name;
						i++;

					}
					list.setListData(data);
					list.setVisibleRowCount(100);
					panel_Amount.setVisible(false);
					panel_Button.setVisible(false);
					panel_Listno.setVisible(true);
					scrollPane_1.setVisible(true);

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == 40) {
					list.requestFocus();
				}
			}
		});

		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"Cancel"); //$NON-NLS-1$
		getRootPane().getActionMap().put("Cancel", new AbstractAction() { //$NON-NLS-1$
			public void actionPerformed(ActionEvent e) {
				close();
			}

			private void close() {
				dispose();

			}
		});

		textFieldAccountNumber.setBounds(113, 8, 172, 20);
		panel_1.add(textFieldAccountNumber);
		textFieldAccountNumber.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(293, 11, 42, 14);
		panel_1.add(lblName);

		textFieldName = new JTextField();
		textFieldName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				listshow();

			}
		});
		textFieldName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {

					conn = DBConnection.getConnection();
					String query = "select * from banksystem.loan where Name like '" + textFieldName.getText() + "%'";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					int i = 0;
					while (rs.next()) {
						String name = rs.getString("Name");

						data[i] = name;
						i++;

					}
					list_name.setListData(data);
					list_name.setVisibleRowCount(100);
					panel_Amount.setVisible(false);
					panel_Button.setVisible(false);
					panel_name.setVisible(true);
					scrollPane_name.setVisible(true);

				}

				catch (Exception e1) {
					System.out.println(e1.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}
				try {

					conn = DBConnection.getConnection();
					String query = "select * from banksystem.saving where Name like '" + textFieldName.getText() + "%'";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					int i = 0;
					while (rs.next()) {
						String name = rs.getString("Name");

						data[i] = name;
						i++;

					}
					list_name.setListData(data);
					list_name.setVisibleRowCount(100);
					panel_Amount.setVisible(false);
					panel_Button.setVisible(false);
					panel_name.setVisible(true);
					scrollPane_name.setVisible(true);

				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}
				try {

					conn = DBConnection.getConnection();
					String query = "select * from banksystem.current where Name like '" + textFieldName.getText()
							+ "%'";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					int i = 0;
					while (rs.next()) {
						String name = rs.getString("Name");

						data[i] = name;
						i++;
				}
					list_name.setListData(data);
					list_name.setVisibleRowCount(100);
					panel_Amount.setVisible(false);
					panel_Button.setVisible(false);
					panel_name.setVisible(true);
					scrollPane_name.setVisible(true);

				} catch (Exception e3) {
					System.out.println(e3.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == 40) {
					list_name.requestFocus();
				}
			}
		});
		textFieldName.setColumns(10);
		textFieldName.setBounds(333, 8, 229, 20);
		panel_1.add(textFieldName);

		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(572, 11, 57, 14);
		panel_1.add(lblBalance);

		textFieldBal = new JTextField();
		textFieldBal.setForeground(Color.RED);
		textFieldBal.setEditable(false);
		textFieldBal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldBal.setColumns(10);
		textFieldBal.setBounds(639, 8, 191, 20);
		panel_1.add(textFieldBal);
		panel_1.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { textFieldAccountNumber,
				textFieldName, textFieldBal, lblNewLabel, lblName, lblBalance }));

		Date date1 = new Date();

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(20, 316, 1134, 330);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 1114, 308);
		panel_2.add(scrollPane);

		tableAccount = new JTable();
		tableAccount.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableAccount.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Sr No", "Tran Date",
				"Tran Particulars", "Tran Mode", "Cheque No", "Amount", "Withdrawal", "Deposit", "Balance" }));
		tableAccount.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableAccount.getColumnModel().getColumn(0).setMinWidth(50);
		tableAccount.getColumnModel().getColumn(1).setPreferredWidth(80);
		tableAccount.getColumnModel().getColumn(1).setMinWidth(80);
		tableAccount.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableAccount.getColumnModel().getColumn(2).setMinWidth(150);
		tableAccount.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableAccount.getColumnModel().getColumn(3).setMinWidth(80);
		tableAccount.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableAccount.getColumnModel().getColumn(4).setMinWidth(80);
		tableAccount.getColumnModel().getColumn(5).setPreferredWidth(15);
		tableAccount.getColumnModel().getColumn(5).setMinWidth(0);
		tableAccount.getColumnModel().getColumn(5).setMaxWidth(0);
		tableAccount.getColumnModel().getColumn(6).setPreferredWidth(80);
		tableAccount.getColumnModel().getColumn(6).setMinWidth(80);
		tableAccount.getColumnModel().getColumn(7).setPreferredWidth(80);
		tableAccount.getColumnModel().getColumn(7).setMinWidth(80);
		tableAccount.getColumnModel().getColumn(8).setPreferredWidth(80);
		tableAccount.getColumnModel().getColumn(8).setMinWidth(80);

		scrollPane.setViewportView(tableAccount);

		panel_Amount = new JPanel();
		panel_Amount.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Amount.setBounds(10, 158, 1144, 74);
		contentPane.add(panel_Amount);
		panel_Amount.setLayout(null);

		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(420, 34, 49, 14);
		panel_Amount.add(lblAmount);

		textFieldAmount = new JTextField();
		textFieldAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}

		});
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(479, 31, 128, 20);
		panel_Amount.add(textFieldAmount);

		rdbtnbycash = new JRadioButton("by cash");
		rdbtnbycash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldBy.setText("");
				textFieldBy.setText("By Cash");
				Select();
			}
		});
		rdbtnbycash.setSelected(true);
		buttonGroup_1.add(rdbtnbycash);
		rdbtnbycash.setBounds(637, 30, 71, 23);
		panel_Amount.add(rdbtnbycash);

		rdbtnByCheque = new JRadioButton("by cheque");
		rdbtnByCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldBy.setText("");
				textFieldBy.setText("By Cheque");
				Select();
			}
		});
		buttonGroup_1.add(rdbtnByCheque);
		rdbtnByCheque.setBounds(710, 30, 96, 23);
		panel_Amount.add(rdbtnByCheque);

		panel_bycheqyedetails = new JPanel();
		panel_bycheqyedetails.setVisible(false);
		panel_bycheqyedetails.setBounds(815, 11, 319, 57);
		panel_Amount.add(panel_bycheqyedetails);
		panel_bycheqyedetails.setLayout(null);

		JLabel lblChequeName = new JLabel("Cheque Numebr:");
		lblChequeName.setBounds(10, 25, 96, 14);
		panel_bycheqyedetails.add(lblChequeName);

		textFieldChequeNumber = new JTextField();
		textFieldChequeNumber.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldChequeNumber.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Plz enter cheque number.");
					textFieldChequeNumber.requestFocus();
				}
			}
		});
		textFieldChequeNumber.setColumns(10);
		textFieldChequeNumber.setBounds(108, 22, 206, 20);
		panel_bycheqyedetails.add(textFieldChequeNumber);

		textFieldBy = new JTextField();
		textFieldBy.setVisible(false);
		textFieldBy.setText("By Cash");
		textFieldBy.setEnabled(false);
		textFieldBy.setBounds(325, 48, 86, 20);
		panel_Amount.add(textFieldBy);
		textFieldBy.setColumns(10);

		JLabel lblTransactionParticulars = new JLabel("Transaction Particulars:");
		lblTransactionParticulars.setBounds(10, 31, 150, 14);
		panel_Amount.add(lblTransactionParticulars);

		textFieldTransactionParticulars = new JTextField();
		textFieldTransactionParticulars.setColumns(10);
		textFieldTransactionParticulars.setBounds(170, 28, 228, 20);
		panel_Amount.add(textFieldTransactionParticulars);
		panel_Amount.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] {
				textFieldTransactionParticulars, textFieldAmount, rdbtnbycash, rdbtnByCheque, panel_bycheqyedetails,
				textFieldChequeNumber, lblAmount, lblChequeName, textFieldBy, lblTransactionParticulars }));

		panel_Button = new JPanel();
		panel_Button.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Button.setBounds(10, 243, 1144, 62);
		contentPane.add(panel_Button);
		panel_Button.setLayout(null);

		JButton btnPayDue = new JButton("Withdral");
		btnPayDue.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (rdbtnByCheque.isSelected()) {
					if (textFieldChequeNumber.getText().equals("")) {
						//JOptionPane.showMessageDialog(null, "Plz enter cheque number");
					}
				}
			}
		});

		btnPayDue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnByCheque.isSelected()) {

					if (textFieldChequeNumber.getText().equals("")) {
						//JOptionPane.showMessageDialog(null, "Plz enter cheque number");
					}
				}

				String acno = textFieldAccountNumber.getText();
				double curamt = Double.valueOf(textFieldAmount.getText());

				try {
					conn = DBConnection.getConnection();
					String in = "Select * from banksystem.loan where AccountNumber='" + acno + "'";
					ps = conn.prepareStatement(in);
					rs = ps.executeQuery();
					String name = null;
					while (rs.next()) {
						String accountno = rs.getString("AccountNumber");
						double amount = rs.getDouble("Amount");
						double interst = rs.getDouble("Interst");
						name = rs.getString("Name");
						double amtmin = amount - curamt;
						double intcur = amtmin * interst / 100;

						double sumamt = amtmin + intcur;
						if (accountno.equals(acno)) {

							int srnomaster = 0;
							String maxno = "select max(SrNo),SrNoMaster from banksystem.loantranscation where AccountNo='"
									+ accountno + "';";
							ps1 = conn.prepareStatement(maxno);
							rs1 = ps1.executeQuery();
							while (rs1.next()) {
								isrno = rs1.getInt(1) + 1;
								srnomaster = rs1.getInt("SrNoMaster");

							}

							String insertdata = "insert into banksystem.loantranscation (SrNoMaster, SrNo, Date, Name, AccountNo, TransactionParticulars, TranBy, chequeno, Amount, Deposite, Balance) values(?,?,?,?,?,?,?,?,?,?,?);";
							ps2 = conn.prepareStatement(insertdata);
							ps2.setInt(1, srnomaster);
							ps2.setInt(2, isrno);
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooserAccount.getDate();
							String date = (String) sdf.format(dateChooserAccount.getDate());
							java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
							ps2.setDate(3, (java.sql.Date) d);
							ps2.setString(4, name);
							ps2.setString(5, textFieldAccountNumber.getText());
							ps2.setString(6, textFieldTransactionParticulars.getText());
							ps2.setString(7, textFieldBy.getText());
							ps2.setString(8, textFieldChequeNumber.getText());
							ps2.setDouble(9, Double.valueOf(textFieldAmount.getText()));
							ps2.setDouble(10, Double.valueOf(textFieldAmount.getText()));
							ps2.setDouble(11,
									Double.valueOf(textFieldBal.getText()) - Double.valueOf(textFieldAmount.getText()));
							ps2.executeUpdate();

						}

						double claculation = Double.valueOf(textFieldBal.getText())
								- Double.valueOf(textFieldAmount.getText());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						dt1 = dateChooserAccount.getDate();
						String date = (String) sdf.format(dateChooserAccount.getDate());
						java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
						String up = "UPDATE banksystem.loan  set AmtPaidDate='" + d + "',Amount='" + amtmin
								+ "',InstersAmt='" + intcur + "',Total='" + claculation + "'  where AccountNumber='"
								+ acno + "'";
						ps3 = conn.prepareStatement(up);
						int ii = ps3.executeUpdate();
						if (ii > 0) {
							JOptionPane.showMessageDialog(null, "Thanks....");
							datashow();
							reset();
						}

					}

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				} finally {
					try {
						rs.close();
						rs1.close();
						ps.close();
						ps1.close();
						ps2.close();
						ps3.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}

				try {

					double balanceamt = Double.valueOf(textFieldBal.getText());
					double amountpaid = Double.valueOf(textFieldAmount.getText());

					if (amountpaid <=balanceamt) {

						conn = DBConnection.getConnection();
						String in = "Select * from banksystem.saving where AcountNumber='" + acno + "'";
						ps = conn.prepareStatement(in);
						rs = ps.executeQuery();
						while (rs.next()) {
							String accountno = rs.getString("AcountNumber");
							double amount = rs.getDouble("OpningAmount");
							double interst = rs.getDouble("Interist");

							double amtmin = amount - curamt;
							double intcur = amtmin * interst / 100;
							double sumamt = amtmin + intcur;
							String name = null;
							if (accountno.equals(acno)) {
								int srnomaster1 = 0;
								String maxno = "select max(SrNo),SrNoMaster,name from banksystem.savingtranscation where AccountNo='"
										+ accountno + "';";
								ps1 = conn.prepareStatement(maxno);
								rs1 = ps1.executeQuery();
								while (rs1.next()) {
									isrno1 = rs1.getInt(1) + 1;
									srnomaster1 = rs1.getInt("SrNoMaster");
									name = rs1.getString("Name");
								}

								String insertdata = "insert into banksystem.savingtranscation (SrNoMaster, SrNo,Date, Name,  AccountNo, TransactionParticulars, tranby, chequeno, Amount, Widtral ,Balance) values(?,?,?,?,?,?,?,?,?,?,?);";
								ps2 = conn.prepareStatement(insertdata);
								ps2.setInt(1, srnomaster1);
								ps2.setInt(2, isrno1);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								dt1 = dateChooserAccount.getDate();
								String date = (String) sdf.format(dateChooserAccount.getDate());
								java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
								ps2.setDate(3, (java.sql.Date) d);
								ps2.setString(4, name);
								ps2.setString(5, textFieldAccountNumber.getText());
								ps2.setString(6, textFieldTransactionParticulars.getText());
								ps2.setString(7, textFieldBy.getText());
								ps2.setString(8, textFieldChequeNumber.getText());
								ps2.setDouble(9, Double.valueOf(textFieldAmount.getText()));
								ps2.setDouble(10, Double.valueOf(textFieldAmount.getText()));
								ps2.setDouble(11, Double.valueOf(textFieldBal.getText())
										- Double.valueOf(textFieldAmount.getText()));
								ps2.executeUpdate();

							}

							String up = "UPDATE banksystem.saving  set OpningAmount='" + amtmin + "',InterestPer='"
									+ intcur + "',Total='" + sumamt + "'  where AcountNumber='" + acno + "'";
							ps3 = conn.prepareStatement(up);
							int ii = ps3.executeUpdate();
							if (ii > 0) {
								JOptionPane.showMessageDialog(null, "Thanks....");
								datashow();
								reset();
							}
						}
					}

					else {
						JOptionPane.showMessageDialog(null, "Enter amount is greater than balance amount.");
					}

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				} finally {
					try {
						rs.close();
						rs1.close();
						ps.close();
						ps1.close();
						ps3.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}
				try {
					double balanceamt = Double.valueOf(textFieldBal.getText());
					double amountpaid = Double.valueOf(textFieldAmount.getText());

					if (amountpaid <=balanceamt) {

						conn = DBConnection.getConnection();
						String in = "Select * from banksystem.current where AccountNumber='" + acno + "'";
						ps = conn.prepareStatement(in);
						String name = null;
						rs = ps.executeQuery();
						while (rs.next()) {
							String accountno = rs.getString("AccountNumber");
							double amount = rs.getDouble("OppingAmount");
							double amtmin = amount - curamt;
							name = rs.getString("Name");

							if (accountno.equals(acno)) {
								int srnomaster3 = 0;
								String maxno = "select max(SrNo),SrNoMaster from banksystem.currenttranscation where AccountNo='"
										+ accountno + "';";
								ps1 = conn.prepareStatement(maxno);
								rs1 = ps1.executeQuery();
								while (rs1.next()) {
									isrno2 = rs1.getInt(1) + 1;
									srnomaster3 = rs1.getInt("SrNoMaster");

								}

								String insertdata = "insert into banksystem.currenttranscation (SrNoMaster, SrNo, Date, Name, AccountNo, TransactionParticulars, tranby, chequeno, Amount, Withral, Balance) values(?,?,?,?,?,?,?,?,?,?,?);";
								ps2 = conn.prepareStatement(insertdata);
								ps2.setInt(1, srnomaster3);
								ps2.setInt(2, isrno2);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								dt1 = dateChooserAccount.getDate();
								String date = (String) sdf.format(dateChooserAccount.getDate());
								java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
								ps2.setDate(3, (java.sql.Date) d);
								ps2.setString(4, name);
								ps2.setString(5, textFieldAccountNumber.getText());
								ps2.setString(6, textFieldTransactionParticulars.getText());
								ps2.setString(7, textFieldBy.getText());
								ps2.setString(8, textFieldChequeNumber.getText());
								ps2.setDouble(9, Double.valueOf(textFieldAmount.getText()));
								ps2.setDouble(10, Double.valueOf(textFieldAmount.getText()));
								ps2.setDouble(11, Double.valueOf(textFieldBal.getText())
										- Double.valueOf(textFieldAmount.getText()));
								ps2.executeUpdate();

							}

							String up = "UPDATE banksystem.current  set OppingAmount='" + amtmin
									+ "'  where AccountNumber='" + acno + "'";
							ps3 = conn.prepareStatement(up);
							int ii = ps3.executeUpdate();
							if (ii > 0) {
								JOptionPane.showMessageDialog(null, "Thanks....");
								datashow();
								reset();
							}

						}
					}

					else {
						JOptionPane.showMessageDialog(null, "Enter amount is greater than balance amount.");
					}

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				} finally {
					try {
						rs.close();
						rs1.close();

						ps.close();
						ps1.close();
						ps3.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}

			}
		});
		btnPayDue.setIcon(new ImageIcon(Account.class.getResource("/ImageButtonIcon/widrawal1(1).png")));
		btnPayDue.setHorizontalAlignment(SwingConstants.LEADING);
		btnPayDue.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPayDue.setBounds(360, 13, 165, 38);
		panel_Button.add(btnPayDue);

		btnDeposite = new JButton("Deposite");
		btnDeposite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnByCheque.isSelected()) {

					if (textFieldChequeNumber.getText().equals("")) {
						//JOptionPane.showMessageDialog(null, "Plz enter cheque number");
					}
				}

				String acno = textFieldAccountNumber.getText();
				double curamt = Double.valueOf(textFieldAmount.getText());

				try {
					conn = DBConnection.getConnection();
					String in = "Select * from banksystem.saving where AcountNumber='" + acno + "'";
					ps = conn.prepareStatement(in);
					rs = ps.executeQuery();
					String name = null;
					while (rs.next()) {
						String accountno = rs.getString("AcountNumber");
						double amount = rs.getDouble("OpningAmount");
						double interst = rs.getDouble("Interist");
						name = rs.getString("Name");

						double amtmin = amount + curamt;
						double intcur = amtmin * interst / 100;
						double sumamt = amtmin + intcur;

						if (accountno.equals(acno)) {

							int srnomaster4 = 0;
							String maxno = "select max(SrNo),SrNOMaster from banksystem.savingtranscation where AccountNo='"
									+ accountno + "';";
							ps1 = conn.prepareStatement(maxno);
							rs1 = ps1.executeQuery();
							while (rs1.next()) {
								isrno1 = rs1.getInt(1) + 1;
								srnomaster4 = rs1.getInt("SrNOMaster");

							}

							String insertdata = "insert into banksystem.savingtranscation (SrNoMaster, SrNo,Date, Name, AccountNo, TransactionParticulars, tranby, chequeno, Amount, Depotie, Balance) values(?,?,?,?,?,?,?,?,?,?,?);";
							ps2 = conn.prepareStatement(insertdata);
							ps2.setInt(1, srnomaster4);
							ps2.setInt(2, isrno1);
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooserAccount.getDate();
							String date = (String) sdf.format(dateChooserAccount.getDate());
							java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
							ps2.setDate(3, (java.sql.Date) d);
							ps2.setString(4, name);
							ps2.setString(5, textFieldAccountNumber.getText());
							ps2.setString(6, textFieldTransactionParticulars.getText());
							ps2.setString(7, textFieldBy.getText());
							ps2.setString(8, textFieldChequeNumber.getText());
							ps2.setDouble(9, Double.valueOf(textFieldAmount.getText()));
							ps2.setDouble(10, Double.valueOf(textFieldAmount.getText()));
							ps2.setDouble(11,
									Double.valueOf(textFieldAmount.getText()) + Double.valueOf(textFieldBal.getText()));
							ps2.executeUpdate();

						}

						String up = "UPDATE banksystem.saving  set OpningAmount='" + amtmin + "',InterestPer='" + intcur
								+ "',Total='" + sumamt + "'  where AcountNumber='" + acno + "'";
						ps3 = conn.prepareStatement(up);
						int ii = ps3.executeUpdate();
						if (ii > 0) {
							JOptionPane.showMessageDialog(null, "Thanks....");
							datashow();
							reset();
						}

					}

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				} finally {
					try {
						rs.close();
						rs1.close();
						ps.close();
						ps1.close();
						ps2.close();
						ps3.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}
				try {
					conn = DBConnection.getConnection();
					String in = "Select * from banksystem.current where AccountNumber='" + acno + "'";
					ps = conn.prepareStatement(in);
					rs = ps.executeQuery();
					String name = null;
					while (rs.next()) {
						String accountno = rs.getString("AccountNumber");
						double amount = rs.getDouble("OppingAmount");
						double amtmin = amount + curamt;
						name = rs.getString("Name");
						if (accountno.equals(acno)) {
							int srnomaster5 = 0;
							String maxno = "select max(SrNo),SrNoMaster from banksystem.currenttranscation where AccountNo='"
									+ accountno + "';";
							ps1 = conn.prepareStatement(maxno);
							rs1 = ps1.executeQuery();
							while (rs1.next()) {
								isrno2 = rs1.getInt(1) + 1;
								srnomaster5 = rs1.getInt("SrNoMaster");
							}

							String insertdata = "insert into banksystem.currenttranscation(SrNoMaster, SrNo, Date, Name, AccountNo, TransactionParticulars, tranby, chequeno, Amount,Deposite, Balance) values(?,?,?,?,?,?,?,?,?,?,?);";
							ps2 = conn.prepareStatement(insertdata);
							ps2.setInt(1, srnomaster5);
							ps2.setInt(2, isrno2);
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooserAccount.getDate();
							String date = (String) sdf.format(dateChooserAccount.getDate());
							java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
							ps2.setDate(3, (java.sql.Date) d);
							ps2.setString(4, name);
							ps2.setString(5, textFieldAccountNumber.getText());
							ps2.setString(6, textFieldTransactionParticulars.getText());
							ps2.setString(7, textFieldBy.getText());
							ps2.setString(8, textFieldChequeNumber.getText());
							ps2.setDouble(9, Double.valueOf(textFieldAmount.getText()));
							ps2.setDouble(10, Double.valueOf(textFieldAmount.getText()));
							ps2.setDouble(11,
									Double.valueOf(textFieldBal.getText()) + Double.valueOf(textFieldAmount.getText()));
							ps2.executeUpdate();

						}

						String up = "UPDATE banksystem.current  set OppingAmount='" + amtmin
								+ "'  where AccountNumber='" + acno + "'";
						ps3 = conn.prepareStatement(up);
						int ii = ps3.executeUpdate();
						if (ii > 0) {
							JOptionPane.showMessageDialog(null, "Thanks....");
							datashow();
							reset();
						}

					}

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				} finally {
					try {
						rs.close();
						rs1.close();
						ps.close();
						ps1.close();
						ps2.close();
						ps3.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}

			}
		});
		btnDeposite.setIcon(new ImageIcon(Account.class.getResource("/ImageButtonIcon/add.png")));
		btnDeposite.setHorizontalAlignment(SwingConstants.LEADING);
		btnDeposite.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDeposite.setBounds(535, 11, 152, 40);
		panel_Button.add(btnDeposite);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Welcome.frame.enable();

			}
		});
		btnExit.setIcon(new ImageIcon(Account.class.getResource("/ImageButtonIcon/Exit.png")));
		btnExit.setHorizontalAlignment(SwingConstants.LEADING);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(697, 11, 131, 38);
		panel_Button.add(btnExit);

		panel_Listno = new JPanel();
		panel_Listno.setVisible(false);
		panel_Listno.setBounds(125, 140, 188, 163);
		contentPane.add(panel_Listno);
		panel_Listno.setLayout(null);

		list = new JList<String>();
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					textFieldAccountNumber.setText(list.getSelectedValue());
					String accountnotop = textFieldAccountNumber.getText();

					try {
						conn = DBConnection.getConnection();
						String query = "Select * from banksystem.loan where AccountNumber=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldAccountNumber.getText());
						rs = ps.executeQuery();
						while (rs.next()) {
							String name = rs.getString("Name");
							textFieldName.setText(name);
							Double bal = rs.getDouble("Amount");
							textFieldBal.setText(String.valueOf(df.format(bal)));
							String accountno = rs.getString("AccountNumber");
							if (accountnotop.equals(accountno)) {
								btnDeposite.setEnabled(false);
								btnPayDue.setText("Pay Due");
							}

						}
						datashow();
						listshow();

					} catch (Exception ee) {
						System.out.println(ee.getMessage());
					} finally {
						try {
							rs.close();
							ps.close();
							conn.close();
						} catch (Exception ew) {
							ew.getMessage();
						}
					}
					try {
						conn = DBConnection.getConnection();
						String query = "Select * from banksystem.saving where AcountNumber=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldAccountNumber.getText());
						rs = ps.executeQuery();
						while (rs.next()) {
							String name = rs.getString("Name");
							textFieldName.setText(name);
							Double bal = rs.getDouble("OpningAmount");
							textFieldBal.setText(String.valueOf(df.format(bal)));
							String accountno = rs.getString("AcountNumber");
							if (accountnotop.equals(accountno)) {
								btnDeposite.setEnabled(true);
								btnPayDue.setText("Withdral");
							}
						}

					} catch (Exception ee) {
						System.out.println(ee.getMessage());
					} finally {
						try {
							rs.close();
							ps.close();
							conn.close();
						} catch (Exception ew) {
							ew.getMessage();
						}
					}
					try {
						conn = DBConnection.getConnection();
						String query = "Select * from banksystem.current where AccountNumber=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldAccountNumber.getText());
						rs = ps.executeQuery();
						while (rs.next()) {
							String name = rs.getString("Name");
							textFieldName.setText(name);
							Double bal = rs.getDouble("OppingAmount");
							textFieldBal.setText(String.valueOf(df.format(bal)));
							String accountno = rs.getString("AccountNumber");
							if (accountnotop.equals(accountno)) {
								btnDeposite.setEnabled(true);
								btnPayDue.setText("Withdral");
							}
						}

						panel_Listno.setVisible(false);
						listshow();

					} catch (Exception ee) {
						System.out.println(ee.getMessage());
					} finally {
						try {
							rs.close();
							ps.close();
							conn.close();
						} catch (Exception ew) {
							ew.getMessage();
						}
					}

				}

			}
		});

		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				textFieldAccountNumber.setText(list.getSelectedValue());
				String accountnotop = textFieldAccountNumber.getText();

				try {
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.loan where AccountNumber=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldAccountNumber.getText());
					rs = ps.executeQuery();
					while (rs.next()) {
						String name = rs.getString("Name");
						textFieldName.setText(name);
						Double bal = rs.getDouble("Amount");
						textFieldBal.setText(String.valueOf(df.format(bal)));
						String accountno = rs.getString("AccountNumber");
						if (accountnotop.equals(accountno)) {
							btnDeposite.setEnabled(false);
							btnPayDue.setText("Pay Due");
						}

					}
					datashow();
					listshow();

				} catch (Exception ee) {
					System.out.println(ee.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}
				try {
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.saving where AcountNumber=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldAccountNumber.getText());
					rs = ps.executeQuery();
					while (rs.next()) {
						String name = rs.getString("Name");
						textFieldName.setText(name);
						Double bal = rs.getDouble("OpningAmount");
						textFieldBal.setText(String.valueOf(df.format(bal)));
						String accountno = rs.getString("AcountNumber");
						if (accountnotop.equals(accountno)) {
							btnDeposite.setEnabled(true);
							btnPayDue.setText("Withdral");
						}
					}

				} catch (Exception ee) {
					System.out.println(ee.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}
				try {
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.current where AccountNumber=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldAccountNumber.getText());
					rs = ps.executeQuery();
					while (rs.next()) {
						String name = rs.getString("Name");
						textFieldName.setText(name);
						Double bal = rs.getDouble("OppingAmount");
						textFieldBal.setText(String.valueOf(df.format(bal)));
						String accountno = rs.getString("AccountNumber");
						if (accountnotop.equals(accountno)) {
							btnDeposite.setEnabled(true);
							btnPayDue.setText("Withdral");
						}
					}

					panel_Listno.setVisible(false);
					listshow();

				} catch (Exception ee) {
					System.out.println(ee.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}

			}

		});
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 11, 166, 136);
		panel_Listno.add(scrollPane_1);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setVisible(false);
		scrollPane_1.setViewportView(list);

		panel_name = new JPanel();
		panel_name.setVisible(false);
		panel_name.setBounds(341, 140, 235, 165);
		contentPane.add(panel_name);
		panel_name.setLayout(null);

		list_name = new JList<String>();
		list_name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					panel_name.setVisible(false);
					textFieldName.setText(list_name.getSelectedValue());
					String accountnotop = textFieldName.getText();

					try {
						conn = DBConnection.getConnection();
						String query = "Select * from banksystem.loan where Name=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldName.getText());
						rs = ps.executeQuery();
						while (rs.next()) {
							String name = rs.getString("AccountNumber");
							textFieldAccountNumber.setText(name);
							Double bal = rs.getDouble("Amount");
							textFieldBal.setText(String.valueOf(df.format(bal)));
							String namename = rs.getString("Name");
							if (namename.equals(accountnotop)) {
								btnDeposite.setEnabled(false);
								btnPayDue.setText("Pay Due");
							}

						}
						btnDeposite.setEnabled(false);

					} catch (Exception ee) {
						System.out.println(ee.getMessage());
					} finally {
						try {
							rs.close();
							ps.close();
							conn.close();
						} catch (Exception ew) {
							ew.getMessage();
						}
					}
					try {
						conn = DBConnection.getConnection();
						String query = "Select * from banksystem.saving where Name=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldName.getText());
						rs = ps.executeQuery();
						while (rs.next()) {
							String name = rs.getString("AcountNumber");
							textFieldAccountNumber.setText(name);
							Double bal = rs.getDouble("OpningAmount");
							textFieldBal.setText(String.valueOf(df.format(bal)));
							String namename = rs.getString("Name");
							if (namename.equals(accountnotop)) {
								btnDeposite.setEnabled(true);
								btnPayDue.setText("Withdraw");
							}

						}

					} catch (Exception ee1) {
						System.out.println(ee1.getMessage());
					} finally {
						try {
							rs.close();
							ps.close();
							conn.close();
						} catch (Exception ew) {
							ew.getMessage();
						}
					}
					try {
						conn = DBConnection.getConnection();
						String query = "Select * from banksystem.current where Name=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldName.getText());
						rs = ps.executeQuery();
						while (rs.next()) {
							String name = rs.getString("AccountNumber");
							textFieldAccountNumber.setText(name);
							Double bal = rs.getDouble("OppingAmount");
							textFieldBal.setText(String.valueOf(df.format(bal)));

							String namename = rs.getString("Name");
							if (namename.equals(accountnotop)) {
								btnDeposite.setEnabled(true);
								btnPayDue.setText("Withdraw");
							}

						}

						datashow();

						scrollPane_name.setVisible(false);
						listshow();

					} catch (Exception ee2) {
						System.out.println(ee2.getMessage());
					} finally {
						try {
							rs.close();
							ps.close();
							conn.close();
						} catch (Exception ew) {
							ew.getMessage();
						}
					}

				}
			}
		});
		list_name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				panel_name.setVisible(false);
				textFieldName.setText(list_name.getSelectedValue());
				String accountnotop = textFieldName.getText();

				try {
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.loan where Name=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldName.getText());
					rs = ps.executeQuery();
					while (rs.next()) {
						String name = rs.getString("AccountNumber");
						textFieldAccountNumber.setText(name);
						Double bal = rs.getDouble("Amount");
						textFieldBal.setText(String.valueOf(df.format(bal)));
						String namename = rs.getString("Name");
						if (namename.equals(accountnotop)) {
							btnDeposite.setEnabled(false);
							btnPayDue.setText("Pay Due");
						}

					}
					btnDeposite.setEnabled(false);

				} catch (Exception ee) {
					System.out.println(ee.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}
				try {
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.saving where Name=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldName.getText());
					rs = ps.executeQuery();
					while (rs.next()) {
						String name = rs.getString("AcountNumber");
						textFieldAccountNumber.setText(name);
						Double bal = rs.getDouble("OpningAmount");
						textFieldBal.setText(String.valueOf(df.format(bal)));
						String namename = rs.getString("Name");
						if (namename.equals(accountnotop)) {
							btnDeposite.setEnabled(true);
							btnPayDue.setText("Withdraw");
						}

					}

				} catch (Exception ee1) {
					System.out.println(ee1.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}
				try {
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.current where Name=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldName.getText());
					rs = ps.executeQuery();
					while (rs.next()) {
						String name = rs.getString("AccountNumber");
						textFieldAccountNumber.setText(name);
						Double bal = rs.getDouble("OppingAmount");
						textFieldBal.setText(String.valueOf(df.format(bal)));

						String namename = rs.getString("Name");
						if (namename.equals(accountnotop)) {
							btnDeposite.setEnabled(true);
							btnPayDue.setText("Withdraw");
						}

					}

					datashow();

					scrollPane_name.setVisible(false);
					listshow();

				} catch (Exception ee2) {
					System.out.println(ee2.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ew) {
						ew.getMessage();
					}
				}

			}
		});
		scrollPane_name = new JScrollPane();
		scrollPane_name.setBounds(0, 11, 235, 143);
		panel_name.add(scrollPane_name);
		scrollPane_name.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_name.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_name.setVisible(false);
		scrollPane_name.setViewportView(list_name);

	}

	public void filter(String quey) {
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model1);
		tableAccount.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(quey));
	}

	public void Select() {

		if (rdbtnbycash.isSelected()) {
			panel_bycheqyedetails.setVisible(false);

		} else {
			panel_bycheqyedetails.setVisible(true);
		}
	}

	public void datashow() {

		String accno = textFieldAccountNumber.getText();
		model1 = (DefaultTableModel) tableAccount.getModel();
		model1.setRowCount(0);
		try {
			conn = DBConnection.getConnection();
			String show = "select * from banksystem.loantranscation where AccountNo='" + accno + "'";
			ps = conn.prepareStatement(show);
			rs = ps.executeQuery();

			while (rs.next()) {

				String data0 = rs.getString("SrNo");
				String data1 = rs.getString("Date");
				String data2 = rs.getString("TransactionParticulars");
				String data3 = rs.getString("TranBy");
				String data4 = rs.getString("chequeno");
				String data5 = rs.getString("Amount");
				String data7 = rs.getString("Deposite");
				String data6 = "";
				String data8 = rs.getString("Balance");

				Object[] row = { data0, data1, data2, data3, data4, data5, data6, data7, data8 };
				model1 = (DefaultTableModel) tableAccount.getModel();
				model1.addRow(row);

			}

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (Exception ew) {
				ew.getMessage();
			}
		}
		try {
			conn = DBConnection.getConnection();
			String show = "select * from banksystem.savingtranscation where AccountNo='" + accno + "'";
			ps = conn.prepareStatement(show);
			rs = ps.executeQuery();

			while (rs.next()) {

				String data0 = rs.getString("SrNo");
				String data1 = rs.getString("Date");
				String data2 = rs.getString("TransactionParticulars");
				String data3 = rs.getString("tranby");
				String data4 = rs.getString("chequeno");
				String data5 = rs.getString("Amount");
				String data6 = rs.getString("Widtral");
				String data7 = rs.getString("Depotie");
				String data8 = rs.getString("Balance");

				Object[] row = { data0, data1, data2, data3, data4, data5, data6, data7, data8 };
				model1 = (DefaultTableModel) tableAccount.getModel();
				model1.addRow(row);

			}

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (Exception ew) {
				ew.getMessage();
			}
		}

		try {
			conn = DBConnection.getConnection();
			String show = "select * from banksystem.currenttranscation where AccountNo='" + accno + "'";
			ps = conn.prepareStatement(show);
			rs = ps.executeQuery();

			while (rs.next()) {

				String data0 = rs.getString("SrNo");
				String data1 = rs.getString("Date");
				String data2 = rs.getString("TransactionParticulars");
				String data3 = rs.getString("tranby");
				String data4 = rs.getString("chequeno");
				String data5 = rs.getString("Amount");
				String data6 = rs.getString("Withral");
				String data7 = rs.getString("Deposite");
				String data8 = rs.getString("Balance");

				Object[] row = { data0, data1, data2, data3, data4, data5, data6, data7, data8 };
				model1 = (DefaultTableModel) tableAccount.getModel();
				model1.addRow(row);

			}

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (Exception ew) {
				ew.getMessage();
			}
		}

	}

	public void reset() {
		textFieldAccountNumber.setText("");

		textFieldAmount.setText("");
		textFieldBal.setText("");
		textFieldChequeNumber.setText("");
		textFieldChequeNumber.setText("");
		textFieldName.setText("");
		textFieldTransactionParticulars.setText("");
	}

	public void listshow() {
		Timer t = new Timer(600, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel_Amount.setVisible(true);
				panel_Button.setVisible(true);
			}
		});
		t.setRepeats(false);
		t.start();

	}
}
