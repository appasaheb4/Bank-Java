package Pages;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;

import CLASS.CalculatorGUI;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JCheckBox;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Cursor;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseMotionAdapter;

public class SharesA_C extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNameSh;
	private JTextField textFieldAddressSh;
	private JTextField textFieldAgeSh;
	private JTextField textFieldContactNoSh;
	private JTable tableSharesAC;
	private JTextField textFieldAmountSh;
	private JTextField textFieldShareperSh;
	private JTextField textFieldTotalQtySh;
	private JTextField textFieldTotalSh;
	public DefaultTableModel model1;
	public JDateChooser dateChooserfdacSharesac;
	public static String accno;

	private JTextField textFieldSrNo;
	public JTextField textFieldMasterSrno;
	public Connection conn;
	public PreparedStatement ps;
	public PreparedStatement ps1;
	public PreparedStatement ps2;
	public String val1;
	public Statement st;
	public JDateChooser dateChooserloangpage;
	java.util.Date dt1, dt2;

	public ResultSet rs;
	private JTextField textFieldSrNoSh;
	private JTextField textFieldSrNoMasterSh;
	public JTextArea textFieldNotesSh;
	private JTextField textFieldAccountNo;
	private JTextField textFieldNomeny;
	private JTextField textFieldNomenyOther;
	public static String srnofd;
	public static double countday;
	public static double daycount;
	public static int srnomaster;
	public static double intrestper;
	public static double amount;
	public static double totalamount;
	public static double intersamt;
	private JTextField textFieldOtherAmount;
	private JTextField textFielSchPerAmt;
	public static String acno;
	public JList<String> listName;
	public String[] data = new String[1000];
	public JScrollPane scrollPaneName;
	public JScrollPane scrollPane_1;
	private JTextField textFieldTotalAmtB;
	public DecimalFormat dff = new DecimalFormat("#.##");
	private JTextField textFieldMatirotuAmt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SharesA_C frame = new SharesA_C();
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
	public SharesA_C() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				textFieldNameSh.requestFocus();
				totalamount();
			}
		});

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1154, 76);
		contentPane.add(panel);

		JLabel lblFdAc = new JLabel("Shares A/C");
		lblFdAc.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFdAc.setBounds(22, 11, 276, 42);
		panel.add(lblFdAc);

		JLabel label_1 = new JLabel("Date:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(777, 45, 81, 17);
		panel.add(label_1);

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

		Date date = new Date();
		dateChooserfdacSharesac = new JDateChooser();
		dateChooserfdacSharesac.setDateFormatString("yyyy-MM-dd");
		dateChooserfdacSharesac.setBounds(840, 45, 196, 20);
		dateChooserfdacSharesac.setDate(date);
		panel.add(dateChooserfdacSharesac);

		textFieldSrNoSh = new JTextField();
		textFieldSrNoSh.setVisible(false);
		textFieldSrNoSh.setEnabled(false);
		textFieldSrNoSh.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldSrNoSh.setColumns(10);
		textFieldSrNoSh.setBounds(189, 25, 209, 23);
		panel.add(textFieldSrNoSh);

		textFieldSrNoMasterSh = new JTextField();
		textFieldSrNoMasterSh.setVisible(false);
		textFieldSrNoMasterSh.setEnabled(false);
		textFieldSrNoMasterSh.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldSrNoMasterSh.setColumns(10);
		textFieldSrNoMasterSh.setBounds(421, 25, 209, 23);
		increment();
		panel.add(textFieldSrNoMasterSh);

		JLabel lblAccountNumber = new JLabel("Account Number:");
		lblAccountNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAccountNumber.setBounds(10, 56, 117, 17);
		panel.add(lblAccountNumber);

		textFieldAccountNo = new JTextField();
		textFieldAccountNo.setEditable(false);
		textFieldAccountNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldAccountNo.setColumns(10);
		textFieldAccountNo.setBounds(137, 53, 209, 23);
		increment();
		panel.add(textFieldAccountNo);

		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculatorGUI cl = new CalculatorGUI("Bank System");
				cl.setSize(400, 350);
				cl.setFocusable(true);
				cl.setLocation(830, 0);
				cl.setVisible(true);
			}
		});
		button_5.setIcon(new ImageIcon(SharesA_C.class.getResource("/ImageButtonIcon/clac.png")));
		button_5.setOpaque(false);
		button_5.setForeground(Color.RED);
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button_5.setContentAreaFilled(false);
		button_5.setBorderPainted(false);
		button_5.setBounds(1080, 11, 64, 55);
		panel.add(button_5);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 85, 1154, 150);
		contentPane.add(panel_1);

		JLabel label_2 = new JLabel("Name:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(10, 9, 81, 17);
		panel_1.add(label_2);

		textFieldNameSh = new JTextField();
		textFieldNameSh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == 40) {
					listName.requestFocus();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				try {

					conn = DBConnection.getConnection();
					String query = "select * from banksystem.shares where Name like '" + textFieldNameSh.getText()
							+ "%'";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					int i = 0;
					while (rs.next()) {
						String name = rs.getString("Name");

						data[i] = name;
						i++;

					}
					listName.setListData(data);
					listName.setVisibleRowCount(100);

					scrollPaneName.setVisible(true);
					listName.setVisible(true);

					scrollPane_1.setVisible(false);
					textFieldNomeny.setVisible(false);

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
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
		});
		textFieldNameSh.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldNameSh.setColumns(10);
		textFieldNameSh.setBounds(83, 9, 209, 23);
		panel_1.add(textFieldNameSh);

		JLabel label_3 = new JLabel("Address:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(299, 12, 81, 17);
		panel_1.add(label_3);

		textFieldAddressSh = new JTextField();
		textFieldAddressSh.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				scrollPaneName.setVisible(false);
				listName.setVisible(false);

				scrollPane_1.setVisible(true);
				textFieldNomeny.setVisible(true);
			}
		});
		textFieldAddressSh.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAddressSh.setColumns(10);
		textFieldAddressSh.setBounds(349, 11, 209, 23);
		panel_1.add(textFieldAddressSh);

		JLabel label_4 = new JLabel("Age:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(568, 12, 81, 17);
		panel_1.add(label_4);

		textFieldAgeSh = new JTextField();
		textFieldAgeSh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					arg0.consume();
				}
			}

		});
		textFieldAgeSh.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAgeSh.setColumns(10);
		textFieldAgeSh.setBounds(645, 6, 191, 23);
		panel_1.add(textFieldAgeSh);

		JLabel label_5 = new JLabel("Contact No:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setBounds(568, 62, 67, 17);
		panel_1.add(label_5);

		textFieldContactNoSh = new JTextField();
		textFieldContactNoSh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '+') || (c == '-') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		textFieldContactNoSh.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldContactNoSh.setColumns(10);
		textFieldContactNoSh.setBounds(645, 59, 191, 23);
		panel_1.add(textFieldContactNoSh);

		JLabel lblNote = new JLabel("Note:");
		lblNote.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNote.setBounds(10, 50, 106, 17);
		panel_1.add(lblNote);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(83, 45, 475, 62);
		panel_1.add(scrollPane_1);

		textFieldNotesSh = new JTextArea();
		textFieldNotesSh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == 9) {
					textFieldContactNoSh.requestFocus();
				}
			}
		});
		scrollPane_1.setViewportView(textFieldNotesSh);

		JLabel label = new JLabel("Nomeny:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(10, 118, 106, 17);
		panel_1.add(label);

		textFieldNomeny = new JTextField();
		textFieldNomeny.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldNomeny.setColumns(10);
		textFieldNomeny.setBounds(83, 118, 209, 23);
		panel_1.add(textFieldNomeny);

		JLabel label_6 = new JLabel("Relation:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(299, 121, 59, 17);
		panel_1.add(label_6);

		JComboBox comboBoxNomeny = new JComboBox();
		comboBoxNomeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = comboBoxNomeny.getSelectedItem().toString();
				textFieldNomenyOther.setText("");
				textFieldNomenyOther.setText(name);
			}
		});
		comboBoxNomeny.setModel(new DefaultComboBoxModel(new String[] { "   Select", "   Paternal Grandfather",
				"    Maternal Grandfather ", "    Maternal Grandmother", "    Brother", "    Brother's Wife",
				"    Elder Brother", "    Younger Brother", "    Husband's sister", "    Sister",
				"    Sister's husband", "    Elder Sister", "    Younger Sister)", "    Husband's elder brother",
				"    Husband's younger brother", "    Elder brother's wife", "    Younger brother's wife",
				"    Wife's Sister", "    Wife's Brother", "    Wife's Brother's wife",
				"    Husband's Sister's Husband", "    Wife's sister's husband", "    Husband's elder brother's wife",
				"    Husband's younger brother's wife", "    Son", "    Son's wife (daughter-in-law) ", "    Daughter ",
				"    Daughter's husband (son-in-law) ", "    Grandson (son's son)",
				"    Granddaughter (son's daughter)", "    Grandson (daughter's son)",
				"    Granddaughter (daughter's daughter)", "    Husband", "    Wife",
				"    Husband's Mother (Mother-in-law)", "    Husband's Father (Father-in-law)",
				"    Fianc\u00E9 or Fianc\u00E9e", "    Father's younger brother (uncle)",
				"    Father's younger brother's wife (aunt)", "    Father's elder brother's (Uncle)",
				"    Father's elder brother's wife (Aunt) ", "    Father's sister (aunt)",
				"    Father's sister's husband", "    Mother's brother", "    Mother's brother's wife",
				"    Mother's sister", "    Mother's sister's husband", "    Brother's son (nephew)",
				"    Sister's son (nephew)", "    Brother's daughter (niece)", "    Sister's daughter (niece) ",
				"    Husband's elder brother's daughter", "    Husband's elder brother's son" }));
		comboBoxNomeny.setBounds(349, 118, 228, 23);
		panel_1.add(comboBoxNomeny);

		JCheckBox checkBoxNomeny = new JCheckBox("");
		checkBoxNomeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkBoxNomeny.isSelected()) {
					comboBoxNomeny.setEnabled(true);
					textFieldNomenyOther.setEnabled(false);
					textFieldNomenyOther.setText("");
				} else {
					comboBoxNomeny.setEnabled(false);
					textFieldNomenyOther.setEnabled(true);
					comboBoxNomeny.setSelectedIndex(0);
				}
			}
		});
		checkBoxNomeny.setBounds(583, 118, 27, 23);
		panel_1.add(checkBoxNomeny);

		JLabel label_7 = new JLabel("Other:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(618, 121, 81, 17);
		panel_1.add(label_7);

		textFieldNomenyOther = new JTextField();
		textFieldNomenyOther.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldNomenyOther.setText("");
			}
		});
		textFieldNomenyOther.setText("Other");
		textFieldNomenyOther.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldNomenyOther.setEnabled(false);
		textFieldNomenyOther.setColumns(10);
		textFieldNomenyOther.setBounds(681, 118, 155, 23);
		panel_1.add(textFieldNomenyOther);

		scrollPaneName = new JScrollPane();
		scrollPaneName.setVisible(false);
		scrollPaneName.setBounds(83, 34, 209, 116);
		panel_1.add(scrollPaneName);

		listName = new JList<String>();
		listName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldNameSh.setText(listName.getSelectedValue());
					String accountnotop = textFieldNameSh.getText();

					scrollPaneName.setVisible(false);
					listName.setVisible(false);

					scrollPane_1.setVisible(true);
					textFieldNomeny.setVisible(true);

					try {
						conn = DBConnection.getConnection();
						String query = "Select * from banksystem.shares where Name=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldNameSh.getText());
						rs = ps.executeQuery();
						while (rs.next()) {

							String Srnomaster = rs.getString("SrNoMaster");
							textFieldSrNoMasterSh.setText(Srnomaster);

							String UpdateAmtDate = rs.getString("Date");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date dd = sdf.parse(UpdateAmtDate);
							dateChooserfdacSharesac.setDate(dd);
							String AccountNumber = rs.getString("Name");
							textFieldNameSh.setText(AccountNumber);
							String Name = rs.getString("AccountNo");
							textFieldAccountNo.setText(Name);
							String Address = rs.getString("Address");
							textFieldAddressSh.setText(Address);
							String Age = rs.getString("Age");
							textFieldAgeSh.setText(Age);
							String ContactNo = rs.getString("ContactNo");
							textFieldContactNoSh.setText(ContactNo);
							String LoanAgainst = rs.getString("Amount");
							textFieldAmountSh.setText(LoanAgainst);
							String Duration = rs.getString("SchPer");
							textFieldShareperSh.setText(Duration);
							String Days = rs.getString("TotalQtyl");
							textFieldTotalQtySh.setText(Days);
							String Amount = rs.getString("Total");
							textFieldTotalSh.setText(Amount);
							String Interst = rs.getString("Notess");
							textFieldNotesSh.setText(Interst);
							String InstersAmt = rs.getString("NomenyName");
							textFieldNomeny.setText(InstersAmt);
							String Total = rs.getString("NomenyRelation");
							textFieldNomenyOther.setText(Total);
							String ShareInterst = rs.getString("OtherAmount");
							textFieldOtherAmount.setText(ShareInterst);

						}

					} catch (Exception ee) {
						System.out.println(ee.getMessage());
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
			}
		});
		listName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldNameSh.setText(listName.getSelectedValue());
				String accountnotop = textFieldNameSh.getText();

				scrollPaneName.setVisible(false);
				listName.setVisible(false);

				scrollPane_1.setVisible(true);
				textFieldNomeny.setVisible(true);

				try {
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.shares where Name=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldNameSh.getText());
					rs = ps.executeQuery();
					while (rs.next()) {

						String Srnomaster = rs.getString("SrNoMaster");
						textFieldSrNoMasterSh.setText(Srnomaster);

						String UpdateAmtDate = rs.getString("Date");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date dd = sdf.parse(UpdateAmtDate);
						dateChooserfdacSharesac.setDate(dd);
						String AccountNumber = rs.getString("Name");
						textFieldNameSh.setText(AccountNumber);
						String Name = rs.getString("AccountNo");
						textFieldAccountNo.setText(Name);
						String Address = rs.getString("Address");
						textFieldAddressSh.setText(Address);
						String Age = rs.getString("Age");
						textFieldAgeSh.setText(Age);
						String ContactNo = rs.getString("ContactNo");
						textFieldContactNoSh.setText(ContactNo);
						String LoanAgainst = rs.getString("Amount");
						textFieldAmountSh.setText(LoanAgainst);
						String Duration = rs.getString("SchPer");
						textFieldShareperSh.setText(Duration);
						String Days = rs.getString("TotalQtyl");
						textFieldTotalQtySh.setText(Days);
						String Amount = rs.getString("Total");
						textFieldTotalSh.setText(Amount);
						String Interst = rs.getString("Notess");
						textFieldNotesSh.setText(Interst);
						String InstersAmt = rs.getString("NomenyName");
						textFieldNomeny.setText(InstersAmt);
						String Total = rs.getString("NomenyRelation");
						textFieldNomenyOther.setText(Total);
						String ShareInterst = rs.getString("OtherAmount");
						textFieldOtherAmount.setText(ShareInterst);

					}

				} catch (Exception ee) {
					System.out.println(ee.getMessage());
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
		});
		listName.setVisible(false);
		scrollPaneName.setViewportView(listName);
		panel_1.setFocusTraversalPolicy(new CLASS.FocusTraversalOnArray(
				new Component[] { textFieldNameSh, textFieldAddressSh, textFieldAgeSh, textFieldContactNoSh,
						textFieldNotesSh, textFieldNomeny, comboBoxNomeny, checkBoxNomeny, label_5, label_2, label_3,
						label_4, lblNote, scrollPane_1, label, label_6, label_7, textFieldNomenyOther }));

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(0, 295, 1154, 60);
		contentPane.add(panel_2);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					conn = DBConnection.getConnection();
					String in = "insert into banksystem.shares values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
					ps = conn.prepareStatement(in);
					ps.setInt(1, Integer.parseInt(textFieldSrNoMasterSh.getText()));
					ps.setInt(2, Integer.parseInt(textFieldSrNoSh.getText()));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserfdacSharesac.getDate();
					String date = (String) sdf.format(dateChooserfdacSharesac.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(3, (java.sql.Date) d);
					ps.setString(4, textFieldNameSh.getText());
					ps.setString(5, textFieldAccountNo.getText());
					ps.setString(6, textFieldAddressSh.getText());
					ps.setDouble(7, Double.valueOf(textFieldAgeSh.getText()));
					ps.setString(8, textFieldContactNoSh.getText());
					ps.setDouble(9, Double.valueOf(textFieldAmountSh.getText()));
					ps.setDouble(10, Double.valueOf(textFieldShareperSh.getText()));
					ps.setDouble(11, Double.valueOf(textFielSchPerAmt.getText()));
					ps.setDouble(12, Double.valueOf(textFieldTotalQtySh.getText()));
					ps.setDouble(13, Double.valueOf(textFieldTotalSh.getText()));
					ps.setDouble(14, Double.valueOf(textFieldMatirotuAmt.getText()));
					ps.setString(15, textFieldNotesSh.getText());
					ps.setString(16, textFieldNomeny.getText());
					ps.setString(17, textFieldNomenyOther.getText());
					ps.setDouble(18, Double.valueOf(textFieldOtherAmount.getText()));

					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are saved.");
						tabledataview();
						totalamount();

					}

				} catch (Exception ee) {
					System.out.print(ee.getMessage());
				} finally {
					try {

						ps.close();
						conn.close();
					} catch (Exception ewww) {
						System.out.println(ewww.getMessage());
					}
				}
				try {
					double otheramt = Double.valueOf(textFieldOtherAmount.getText());
					if (otheramt > 0) {
						int maxso = 0;
						conn = (Connection) DBConnection.getConnection();
						String maxsoq = "select max(SrNoMaster) from banksystem.expenditureincome;";
						ps = conn.prepareStatement(maxsoq);
						rs = ps.executeQuery();
						while (rs.next()) {
							maxso = rs.getInt(1) + 1;

						}
						String inser = "insert into banksystem.expenditureincome(SrNoMaster, Date, Name,AccountNo, Amount, Note) values(?,?,?,?,?,?)";
						ps1 = conn.prepareStatement(inser);

						ps1.setInt(1, maxso);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						dt1 = dateChooserfdacSharesac.getDate();
						String date = (String) sdf.format(dateChooserfdacSharesac.getDate());
						java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
						ps1.setDate(2, (java.sql.Date) d);
						ps1.setString(3, textFieldNameSh.getText());
						ps1.setString(4, textFieldAccountNo.getText());
						ps1.setDouble(5, Double.valueOf(textFieldOtherAmount.getText()));
						ps1.setString(6, textFieldNotesSh.getText());
						ps1.executeUpdate();
						increment();
						reset();

					}

				} catch (Exception ee) {
					System.out.println(ee.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						ps1.close();
						conn.close();
					} catch (Exception ewww) {
						System.out.println(ewww.getMessage());
					}
				}

			}

			private void tabledataview() {

				String data0 = textFieldSrNoMasterSh.getText();
				String data1 = textFieldSrNoSh.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				dt1 = dateChooserfdacSharesac.getDate();
				String data2 = (String) sdf.format(dateChooserfdacSharesac.getDate());
				String data3 = textFieldAccountNo.getText();
				String data4 = textFieldNameSh.getText();
				String data5 = textFieldAmountSh.getText();
				String data6 = textFieldShareperSh.getText();
				String data7 = textFieldTotalQtySh.getText();
				String data8 = textFielSchPerAmt.getText();
				String data9 = textFieldTotalSh.getText();
				String data10=textFieldMatirotuAmt.getText();
				String data11 = textFieldOtherAmount.getText();
				String data12 = textFieldAddressSh.getText();
				String data13 = textFieldAgeSh.getText();
				String data14 = textFieldContactNoSh.getText();
				String data15 = textFieldNotesSh.getText();
				String data16 = textFieldNomeny.getText();
				String data17= textFieldNomenyOther.getText();

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12, data13, data14, data15, data16,data17 };
				model1 = (DefaultTableModel) tableSharesAC.getModel();
				model1.addRow(row);

			}

		});
		btnSave.setIcon(new ImageIcon(SharesA_C.class.getResource("/ImageButtonIcon/Save.png")));
		btnSave.setHorizontalAlignment(SwingConstants.LEADING);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBounds(216, 11, 115, 38);
		panel_2.add(btnSave);

		JButton button_Update = new JButton("Update");
		button_Update.setVisible(false);
		button_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String up = "UPDATE banksystem.shares set  Date=?, Name=?,AccountNo=?, Address=?, Age=?, ContactNo=?, Amount=?, SchPer=?, TotalQtyl=?, Total=?,MaturityAmt=?, Notess=?,NomenyName=?,NomenyRelation=?,OtherAmount=? where SrNoMaster=?";
					ps = conn.prepareStatement(up);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserfdacSharesac.getDate();
					String date = (String) sdf.format(dateChooserfdacSharesac.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(1, (java.sql.Date) d);
					ps.setString(2, textFieldNameSh.getText());
					ps.setString(3, textFieldAccountNo.getText());
					ps.setString(4, textFieldAddressSh.getText());
					ps.setDouble(5, Double.valueOf(textFieldAgeSh.getText()));
					ps.setString(6, textFieldContactNoSh.getText());
					ps.setDouble(7, Double.valueOf(textFieldAmountSh.getText()));
					ps.setDouble(8, Double.valueOf(textFieldShareperSh.getText()));
					ps.setDouble(9, Double.valueOf(textFieldTotalQtySh.getText()));
					ps.setDouble(10, Double.valueOf(textFieldTotalSh.getText()));
					ps.setDouble(11, Double.valueOf(textFieldMatirotuAmt.getText()));
					ps.setString(12, textFieldNotesSh.getText());
					ps.setString(13, textFieldNomeny.getText());
					ps.setString(14, textFieldNomenyOther.getText());
					ps.setDouble(15, Double.valueOf(textFieldOtherAmount.getText()));
					ps.setInt(16, Integer.parseInt(textFieldSrNoMasterSh.getText()));
					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are update.");
						dispose();
						SharesA_C sh = new SharesA_C();
						sh.setUndecorated(true);
						sh.setVisible(true);
					}

				} catch (Exception e2) {
					System.out.print(e2.getMessage());
				} finally {
					try {

						ps.close();
						conn.close();
					} catch (Exception ewww) {
						System.out.println(ewww.getMessage());
					}
				}

			}
		});
		button_Update.setIcon(new ImageIcon(SharesA_C.class.getResource("/ImageButtonIcon/update.png")));
		button_Update.setToolTipText("");
		button_Update.setHorizontalAlignment(SwingConstants.LEADING);
		button_Update.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_Update.setBounds(80, 11, 126, 38);
		panel_2.add(button_Update);

		JButton button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String de = "delete from banksystem.shares where SrNomaster=?";
					ps = conn.prepareStatement(de);
					ps.setInt(1, Integer.parseInt(textFieldSrNoMasterSh.getText()));
					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are deleted.");
						dispose();
						SharesA_C sh = new SharesA_C();
						sh.setUndecorated(true);
						sh.setVisible(true);

					}

				} catch (Exception qe) {
					System.out.print(qe.getMessage());
				} finally {
					try {

						ps.close();
						conn.close();
					} catch (Exception ewww) {
						System.out.println(ewww.getMessage());
					}
				}
			}
		});
		button_2.setIcon(new ImageIcon(SharesA_C.class.getResource("/ImageButtonIcon/delete.jpg")));
		button_2.setHorizontalAlignment(SwingConstants.LEADING);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_2.setBounds(475, 11, 115, 38);
		panel_2.add(button_2);

		JButton button_3 = new JButton("Reset");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		button_3.setIcon(new ImageIcon(SharesA_C.class.getResource("/ImageButtonIcon/Reset5.jpg")));
		button_3.setHorizontalAlignment(SwingConstants.LEADING);
		button_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_3.setBounds(600, 11, 115, 38);
		panel_2.add(button_3);

		JButton button_4 = new JButton("Exit");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Welcome.frame.enable();

			}
		});
		button_4.setIcon(new ImageIcon(SharesA_C.class.getResource("/ImageButtonIcon/Exit.png")));
		button_4.setHorizontalAlignment(SwingConstants.LEADING);
		button_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_4.setBounds(725, 11, 115, 38);
		panel_2.add(button_4);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFielSchPerAmt.setEditable(true);
				textFieldTotalSh.setEditable(true);
				textFieldAccountNo.setEditable(true);
				textFieldMatirotuAmt.setEditable(true);
				btnEdit.setVisible(false);
				button_Update.setBounds(339, 11, 126, 38);
				button_Update.setVisible(true);
			}
		});
		btnEdit.setIcon(new ImageIcon(SharesA_C.class.getResource("/ImageButtonIcon/edit.png")));
		btnEdit.setToolTipText("");
		btnEdit.setHorizontalAlignment(SwingConstants.LEADING);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(339, 11, 126, 38);
		panel_2.add(btnEdit);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(0, 366, 1154, 289);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 1134, 267);
		panel_3.add(scrollPane);

		tableSharesAC = new JTable();
		tableSharesAC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSharesAC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableSharesAC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					int i = tableSharesAC.getSelectedRow();
					model1 = (DefaultTableModel) tableSharesAC.getModel();
					textFieldSrNoMasterSh.setText(model1.getValueAt(i, 1).toString());
					textFieldSrNoSh.setText(model1.getValueAt(i, 2).toString());
					//dateChooserfdacSharesac.setDateFormatString(model1.getValueAt(i, 3).toString());
					textFieldAccountNo.setText(model1.getValueAt(i, 4).toString());
					textFieldNameSh.setText(model1.getValueAt(i, 5).toString());
					
					textFieldAmountSh.setText(model1.getValueAt(i, 6).toString());
					textFieldShareperSh.setText(model1.getValueAt(i, 7).toString());
					textFieldTotalQtySh.setText(model1.getValueAt(i, 8).toString());
					textFielSchPerAmt.setText(model1.getValueAt(i, 9).toString());
					textFieldTotalSh.setText(model1.getValueAt(i, 10).toString());
					textFieldMatirotuAmt.setText(model1.getValueAt(i, 11).toString());
					textFieldOtherAmount.setText(model1.getValueAt(i, 12).toString());
					textFieldAddressSh.setText(model1.getValueAt(i, 13).toString());
					textFieldAgeSh.setText(model1.getValueAt(i, 14).toString());
					textFieldContactNoSh.setText(model1.getValueAt(i, 15).toString());
					textFieldNotesSh.setText(model1.getValueAt(i, 16).toString());
					textFieldNomeny.setText(model1.getValueAt(i, 17).toString());
					textFieldNomenyOther.setText(model1.getValueAt(i, 18).toString());
				} catch (Exception ee) {
				}
				try {
					che();
				} catch (Exception ew) {
				}

			}

			private void che() {
				boolean s = false;
				boolean s1 = true;
				String inc = null;
				int row = 0;
				int rows = tableSharesAC.getRowCount() - 1;

				for (int i = 0; i < tableSharesAC.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableSharesAC.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableSharesAC.getSelectedRow();
						int col = tableSharesAC.getSelectedColumn();
						tableSharesAC.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableSharesAC.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableSharesAC.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableSharesAC.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableSharesAC.setValueAt((Object) s, 0, 0);
							tableSharesAC.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableSharesAC.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableSharesAC.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Select", "SrNoMaster", "SrNo", "Date", "A/C No", "Name ", "Amount (Per Share)", "% on share", "Qty ", "Sch Amt", "Total", "Maturity Amt", "Other Char.", "Address", "Age", "Contact No", "Notes", "Nomeny Name", "Nomeny Re"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableSharesAC.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableSharesAC.getColumnModel().getColumn(0).setMinWidth(50);
		tableSharesAC.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableSharesAC.getColumnModel().getColumn(1).setMinWidth(0);
		tableSharesAC.getColumnModel().getColumn(1).setMaxWidth(0);
		tableSharesAC.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableSharesAC.getColumnModel().getColumn(2).setMinWidth(50);
		tableSharesAC.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableSharesAC.getColumnModel().getColumn(3).setMinWidth(80);
		tableSharesAC.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableSharesAC.getColumnModel().getColumn(4).setMinWidth(80);
		tableSharesAC.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableSharesAC.getColumnModel().getColumn(5).setMinWidth(150);
		tableSharesAC.getColumnModel().getColumn(6).setPreferredWidth(80);
		tableSharesAC.getColumnModel().getColumn(6).setMinWidth(80);
		tableSharesAC.getColumnModel().getColumn(7).setPreferredWidth(80);
		tableSharesAC.getColumnModel().getColumn(7).setMinWidth(80);
		tableSharesAC.getColumnModel().getColumn(9).setPreferredWidth(15);
		tableSharesAC.getColumnModel().getColumn(9).setMinWidth(0);
		tableSharesAC.getColumnModel().getColumn(9).setMaxWidth(0);
		tableSharesAC.getColumnModel().getColumn(13).setPreferredWidth(150);
		tableSharesAC.getColumnModel().getColumn(13).setMinWidth(150);
		tableSharesAC.getColumnModel().getColumn(14).setPreferredWidth(80);
		tableSharesAC.getColumnModel().getColumn(14).setMinWidth(80);
		tableSharesAC.getColumnModel().getColumn(15).setPreferredWidth(80);
		tableSharesAC.getColumnModel().getColumn(15).setMinWidth(80);
		tableSharesAC.getColumnModel().getColumn(16).setPreferredWidth(150);
		tableSharesAC.getColumnModel().getColumn(16).setMinWidth(150);
		tableSharesAC.getColumnModel().getColumn(17).setPreferredWidth(80);
		tableSharesAC.getColumnModel().getColumn(17).setMinWidth(80);
		tableSharesAC.getColumnModel().getColumn(18).setPreferredWidth(80);
		tableSharesAC.getColumnModel().getColumn(18).setMinWidth(80);
		showdata();

		scrollPane.setViewportView(tableSharesAC);

		JButton button_6 = new JButton("Report");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for (int i = 0; i <= tableSharesAC.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableSharesAC.getValueAt(i, 0).toString());
						if (che) {
							String accno4 = String.valueOf(tableSharesAC.getValueAt(i, 4).toString());

							java.io.InputStream file = getClass().getResourceAsStream("/Reports/Share.jrxml");
							accno = String.valueOf(tableSharesAC.getValueAt(i, 4).toString());
							HashMap para = new HashMap();
							para.put("Acno", accno4);
							allinonereport r = new allinonereport("Share Report", file, para);
							break;
						}

					}
				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		button_6.setIcon(new ImageIcon(SharesA_C.class.getResource("/ImageButtonIcon/Report4d.png")));
		button_6.setHorizontalAlignment(SwingConstants.LEADING);
		button_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_6.setBounds(201, 666, 143, 38);
		contentPane.add(button_6);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(0, 246, 1154, 38);
		contentPane.add(panel_4);

		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAmount.setBounds(10, 9, 81, 17);
		panel_4.add(lblAmount);

		textFieldAmountSh = new JTextField();
		textFieldAmountSh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Calculation();
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		textFieldAmountSh.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAmountSh.setColumns(10);
		textFieldAmountSh.setBounds(83, 6, 111, 23);
		panel_4.add(textFieldAmountSh);

		JLabel lblOnShare = new JLabel("% on share (option):");
		lblOnShare.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOnShare.setBounds(200, 9, 111, 17);
		panel_4.add(lblOnShare);

		textFieldShareperSh = new JTextField();
		textFieldShareperSh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Calculation();
			}

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
		textFieldShareperSh.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldShareperSh.setColumns(10);
		textFieldShareperSh.setBounds(309, 6, 68, 23);
		panel_4.add(textFieldShareperSh);

		JLabel lblTotalQty = new JLabel("Total Qty:");
		lblTotalQty.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotalQty.setBounds(551, 9, 81, 17);
		panel_4.add(lblTotalQty);

		textFieldTotalQtySh = new JTextField();
		textFieldTotalQtySh.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
			}
		});
		textFieldTotalQtySh.setText("1");
		textFieldTotalQtySh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Calculation();
			}

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
		textFieldTotalQtySh.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldTotalQtySh.setColumns(10);
		textFieldTotalQtySh.setBounds(608, 6, 38, 23);
		panel_4.add(textFieldTotalQtySh);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotal.setBounds(654, 8, 57, 17);
		panel_4.add(lblTotal);

		textFieldTotalSh = new JTextField();
		textFieldTotalSh.setForeground(Color.RED);
		textFieldTotalSh.setEditable(false);
		textFieldTotalSh.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldTotalSh.setColumns(10);
		textFieldTotalSh.setBounds(688, 5, 95, 23);
		panel_4.add(textFieldTotalSh);

		JLabel lblOtherAmount = new JLabel("Other Charge:");
		lblOtherAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOtherAmount.setBounds(966, 9, 81, 17);
		panel_4.add(lblOtherAmount);

		textFieldOtherAmount = new JTextField();
		textFieldOtherAmount.setText("0");
		textFieldOtherAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldOtherAmount.setColumns(10);
		textFieldOtherAmount.setBounds(1057, 6, 81, 23);
		panel_4.add(textFieldOtherAmount);

		JLabel lblOnShare_1 = new JLabel("% on share amt:");
		lblOnShare_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOnShare_1.setBounds(382, 10, 95, 17);
		panel_4.add(lblOnShare_1);

		textFielSchPerAmt = new JTextField();
		textFielSchPerAmt.setForeground(Color.RED);
		textFielSchPerAmt.setEditable(false);
		textFielSchPerAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFielSchPerAmt.setColumns(10);
		textFielSchPerAmt.setBounds(467, 6, 75, 23);
		panel_4.add(textFielSchPerAmt);
		
		JLabel lblMaturityAmt = new JLabel("Maturity Amt:");
		lblMaturityAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMaturityAmt.setBounds(787, 9, 95, 17);
		panel_4.add(lblMaturityAmt);
		
		textFieldMatirotuAmt = new JTextField();
		textFieldMatirotuAmt.setForeground(Color.RED);
		textFieldMatirotuAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMatirotuAmt.setEditable(false);
		textFieldMatirotuAmt.setColumns(10);
		textFieldMatirotuAmt.setBounds(873, 6, 86, 23);
		panel_4.add(textFieldMatirotuAmt);

		JButton button = new JButton("Withdral");
		button.setIcon(new ImageIcon(SharesA_C.class.getResource("/ImageButtonIcon/widrawal1(1).png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double amtaa = Double.valueOf(textFieldAmountSh.getText());
				if (amtaa <= 0) {
					JOptionPane.showMessageDialog(null, "account is suspend mode because balance total withdrawal.");
				} else {

					try {

						for (int i = 0; i <= tableSharesAC.getRowCount(); i++) {
							Boolean che = Boolean.valueOf(tableSharesAC.getValueAt(i, 0).toString());
							if (che) {
								String Srno = tableSharesAC.getValueAt(i, 1).toString();
								srnofd = Srno;
								acno = tableSharesAC.getValueAt(i, 4).toString();
								ShareWithralAmt sw = new ShareWithralAmt();
								sw.setVisible(true);

							}
						}

					} catch (Exception ee) {
						System.out.println(ee.getMessage());
					}
				}
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(31, 666, 160, 38);
		contentPane.add(button);

		JLabel lblTotalAmount = new JLabel("Total (Amt*Qty):");
		lblTotalAmount.setBounds(900, 669, 109, 14);
		contentPane.add(lblTotalAmount);

		textFieldTotalAmtB = new JTextField();
		textFieldTotalAmtB.setForeground(Color.RED);
		textFieldTotalAmtB.setEditable(false);
		textFieldTotalAmtB.setColumns(10);
		textFieldTotalAmtB.setBounds(1008, 666, 132, 20);
		contentPane.add(textFieldTotalAmtB);
		contentPane.setFocusTraversalPolicy(new CLASS.FocusTraversalOnArray(new Component[] { panel_1, panel, lblFdAc,
				label_1, dateChooserfdacSharesac, dateChooserfdacSharesac.getCalendarButton(), textFieldSrNoSh,
				textFieldSrNoMasterSh, lblAccountNumber, textFieldAccountNo, label_2, textFieldNameSh, label_3,
				textFieldAddressSh, label_4, textFieldAgeSh, label_5, textFieldContactNoSh, lblNote, scrollPane_1,
				textFieldNotesSh, label, textFieldNomeny, label_6, comboBoxNomeny, checkBoxNomeny, label_7,
				textFieldNomenyOther, panel_2, btnSave, button_Update, button_2, button_3, button_4, panel_3, scrollPane,
				tableSharesAC, button_6, panel_4, lblAmount, textFieldAmountSh, lblOnShare, textFieldShareperSh,
				lblTotalQty, textFieldTotalQtySh, lblTotal, textFieldTotalSh, lblOtherAmount, textFieldOtherAmount,
				lblOnShare_1, textFielSchPerAmt, button }));
	}

	public void increment() {
		try {

			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("select max(SrNoMaster) from banksystem.shares;");
			rs = ps.executeQuery();
			while (rs.next()) {
				int val = (rs.getInt(1) + 1);
				val1 = String.valueOf(val);

			}
			textFieldSrNoMasterSh.setText(val1);
			textFieldSrNoSh.setText(val1);
			textFieldAccountNo.setText("Sh" + val1);

			SimpleDateFormat insdf = new SimpleDateFormat("yyyy-MM-dd");
			Date indate = new Date();
			String instringdate = insdf.format(indate);
			dateChooserfdacSharesac.setDate(insdf.parse(instringdate));

		} catch (Exception ee) {
			System.out.println(ee.getMessage());
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

	public void reset() {
		increment();
		textFieldNameSh.setText("");
		textFieldAddressSh.setText("");
		textFieldAgeSh.setText("0");
		textFieldContactNoSh.setText("");
		textFieldAmountSh.setText("0");
		textFieldShareperSh.setText("0");
		textFieldTotalQtySh.setText("10");
		textFieldTotalSh.setText("0");
		textFieldNotesSh.setText("");
		textFieldNomeny.setText("");
		textFieldNomenyOther.setText("");
		textFieldOtherAmount.setText("0");
		textFielSchPerAmt.setText("0");
		textFieldMatirotuAmt.setText("0");
		
	}

	public void Calculation() {
		String ins = textFieldShareperSh.getText();
		if (ins.equals("0")) {
			double one = Double.valueOf(textFieldAmountSh.getText());
			double two = Double.valueOf(textFieldTotalQtySh.getText());
			double three = one * two;
			textFieldTotalSh.setText(String.valueOf(three));

		} else {

			double amount = Double.valueOf(textFieldAmountSh.getText());
			double interst = Double.valueOf(textFieldShareperSh.getText());
			double totalqty = Double.valueOf(textFieldTotalQtySh.getText());
			double totla = amount * interst / 100.0;
			double mul = amount * totalqty;
			textFielSchPerAmt.setText(String.valueOf(totla));
			
			double alltotal1 = totla * totalqty + mul;
			textFieldTotalSh.setText(String.valueOf(mul));
			textFieldMatirotuAmt.setText(String.valueOf(alltotal1));

		}
	}

	public void showdata() {
		try {
			conn = DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.shares order by SrNomaster;";
			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("SrNoMaster");
				String data2 = rs.getString("Date");
				String data3 = rs.getString("AccountNo");
				String data4 = rs.getString("Name");
				String data5 = rs.getString("Amount");
				String data6 = rs.getString("SchPer");
				String data7 = rs.getString("TotalQtyl");
				String data8 = rs.getString("SchAmt");
				String data9 = rs.getString("Total");
				String data10=rs.getString("MaturityAmt");
				String data11 = rs.getString("OtherAmount");
				String data12 = rs.getString("Address");
				String data13 = rs.getString("Age");
				String data14 = rs.getString("ContactNo");
				String data15 = rs.getString("Notess");
				String data16 = rs.getString("NomenyName");
				String data17 = rs.getString("NomenyRelation");

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12, data13, data14, data15, data16,data17 };
				model1 = (DefaultTableModel) tableSharesAC.getModel();
				model1.addRow(row);

			}

		} catch (Exception ee) {
			System.out.println(ee.getMessage());
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

	public void totalamount() {
		int rowsCount = tableSharesAC.getRowCount();
		double sum = 0;

		for (int i = 0; i < rowsCount; i++) {
			sum = sum + Double.valueOf(tableSharesAC.getValueAt(i, 10).toString());
		}
		textFieldTotalAmtB.setText(String.valueOf(dff.format(sum)));

	}
}
