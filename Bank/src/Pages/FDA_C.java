package Pages;

import java.awt.BorderLayout;


import java.awt.Checkbox;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import com.mysql.jdbc.Statement;

import com.toedter.calendar.JDateChooser;

import CLASS.CalculatorGUI;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Cursor;
import javax.swing.JList;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseMotionAdapter;

public class FDA_C extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNameFd;
	private JTextField textFieldAddressFd;
	private JTextField textFieldAgeFd;
	private JTextField textFieldContactNoFd;
	private JTable tableFDA_C;
	private JTextField textFieldDourationFd;
	private JTextField textFieldAmountFD;
	private JTextField textFieldInteresFD;
	private JTextField textFieldTotalFD;
	public JComboBox comboBoxDurationFd;
	public DefaultTableModel model1;
	public JDateChooser dateChooserfdac;

	private JTextField textFieldSrNo;
	public JTextField textFieldMasterSrno;
	public Connection conn;
	public PreparedStatement ps;
	public PreparedStatement ps1;
	public PreparedStatement ps2;
	public PreparedStatement ps3;
	public ResultSet rs1;
	public String val1;
	public Statement st;
	public JDateChooser dateChooserloangpage;
	java.util.Date dt1, dt2;

	public ResultSet rs;
	private JTextField textFieldSrNoFD;
	private JTextField textFieldSrNoMasterFD;
	private JTextField textFieldInteresAmtFD;
	public JTextField textFieldLoanPerstantege;
	private JTextField textFieldLoanAmt;
	private JTextField textFieldInterst;
	public JPanel panel_6;
	public static String Srno;
	private JTextField textFieldDays;
	public static double countday;
	public static double daycount;
	public static int srnomaster;
	public static double intrestper;
	public JTextArea textFieldNotesFD;
	public static double amount;
	public static double totalamount;
	public static double intersamt;
	private JTextField textField_Nomeny;
	private JTextField textField_NomenyOther;
	public JComboBox comboBoxNomeny;
	private JTextField textFieldInterstAmt;
	private JTextField textFieldTotal;
	private JTextField textFieldAccountNo;
	public JRadioButton rdbtnFdAgainstLoan;
	public int incrementval;
	public static String srnofd;
	private JTextField textFieldOtherAmount;
	public static String acno;
	public DecimalFormat df = new DecimalFormat("#.##");
	public DecimalFormat dff = new DecimalFormat("#.##");
	public JList<String> listname;
	public JScrollPane scrollPaneName;
	public String[] data = new String[1000];
	private boolean DEBUG = false;
	public static String accno;
	private JTextField textFieldTotalAmtB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FDA_C frame = new FDA_C();
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
	public FDA_C() {
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
				textFieldNameFd.requestFocus();
				totalamount();
			}
		});

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == KeyEvent.VK_A) {
					dispose();
					Account ac = new Account();
					ac.setUndecorated(true);
					ac.setVisible(true);

				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == KeyEvent.VK_A) {
					dispose();
					Account ac = new Account();
					ac.setUndecorated(true);
					ac.setVisible(true);

				}
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1154, 76);
		contentPane.add(panel);

		JLabel lblFdAc = new JLabel("FD A/C");
		lblFdAc.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFdAc.setBounds(22, 11, 276, 42);
		panel.add(lblFdAc);

		JLabel label_1 = new JLabel("Date:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(777, 45, 81, 17);
		panel.add(label_1);

		Date date = new Date();
		dateChooserfdac = new JDateChooser();
		dateChooserfdac.setDateFormatString("dd-MM-yyyy");
		dateChooserfdac.setBounds(841, 45, 196, 20);
		dateChooserfdac.setDate(date);
		panel.add(dateChooserfdac);

		textFieldSrNoFD = new JTextField();
		textFieldSrNoFD.setVisible(false);
		textFieldSrNoFD.setEnabled(false);
		textFieldSrNoFD.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldSrNoFD.setColumns(10);
		textFieldSrNoFD.setBounds(142, 25, 209, 23);
		panel.add(textFieldSrNoFD);

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

		textFieldSrNoMasterFD = new JTextField();
		textFieldSrNoMasterFD.setVisible(false);
		textFieldSrNoMasterFD.setEnabled(false);
		textFieldSrNoMasterFD.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldSrNoMasterFD.setColumns(10);
		textFieldSrNoMasterFD.setBounds(383, 25, 209, 23);
		increment();
		panel.add(textFieldSrNoMasterFD);

		JLabel lblAccountNumber = new JLabel("Account Number:");
		lblAccountNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAccountNumber.setBounds(22, 53, 110, 17);
		panel.add(lblAccountNumber);

		textFieldAccountNo = new JTextField();
		textFieldAccountNo.setEditable(false);
		textFieldAccountNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAccountNo.setColumns(10);
		textFieldAccountNo.setBounds(142, 50, 209, 23);
		increment();
		// DurationIncrement() ;
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
		button_5.setIcon(new ImageIcon(FDA_C.class.getResource("/ImageButtonIcon/clac.png")));
		button_5.setOpaque(false);
		button_5.setForeground(Color.RED);
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button_5.setContentAreaFilled(false);
		button_5.setBorderPainted(false);
		button_5.setBounds(1073, 12, 64, 55);
		panel.add(button_5);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 83, 1154, 112);
		contentPane.add(panel_1);

		JLabel label_2 = new JLabel("Name:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(10, 9, 81, 17);
		panel_1.add(label_2);

		textFieldNameFd = new JTextField();
		textFieldNameFd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {

					conn = DBConnection.getConnection();
					String query = "select * from banksystem.fd where Name like '" + textFieldNameFd.getText() + "%'";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					int i = 0;
					while (rs.next()) {
						String name = rs.getString("Name");

						data[i] = name;
						i++;

					}
					listname.setListData(data);
					listname.setVisibleRowCount(100);

					scrollPaneName.setVisible(true);
					listname.setVisible(true);
					textFieldContactNoFd.setVisible(false);

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ess) {
						System.out.println(ess.getMessage());
					}
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == 40) {
					listname.requestFocus();
				}
			}
		});
		textFieldNameFd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldNameFd.setColumns(10);
		textFieldNameFd.setBounds(74, 9, 209, 23);
		panel_1.add(textFieldNameFd);

		JLabel label_3 = new JLabel("Address:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(293, 9, 81, 17);
		panel_1.add(label_3);

		textFieldAddressFd = new JTextField();
		textFieldAddressFd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				scrollPaneName.setVisible(false);
				listname.setVisible(false);
				textFieldContactNoFd.setVisible(true);
			}
		});
		textFieldAddressFd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAddressFd.setColumns(10);
		textFieldAddressFd.setBounds(347, 9, 222, 23);
		panel_1.add(textFieldAddressFd);

		JLabel label_4 = new JLabel("Age:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(579, 12, 81, 17);
		panel_1.add(label_4);

		textFieldAgeFd = new JTextField();
		textFieldAgeFd.setText("0");
		textFieldAgeFd.addKeyListener(new KeyAdapter() {
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
		textFieldAgeFd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAgeFd.setColumns(10);
		textFieldAgeFd.setBounds(612, 6, 100, 23);
		panel_1.add(textFieldAgeFd);

		JLabel label_5 = new JLabel("Contact No:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setBounds(10, 43, 106, 17);
		panel_1.add(label_5);

		textFieldContactNoFd = new JTextField();
		textFieldContactNoFd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '+') || (c == '-') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldContactNoFd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldContactNoFd.setColumns(10);
		textFieldContactNoFd.setBounds(74, 43, 209, 23);
		panel_1.add(textFieldContactNoFd);

		JLabel lblNote = new JLabel("Note:");
		lblNote.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNote.setBounds(293, 40, 106, 17);
		panel_1.add(lblNote);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(347, 43, 364, 62);
		panel_1.add(scrollPane_1);

		textFieldNotesFD = new JTextArea();
		textFieldNotesFD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == 9) {
					comboBoxDurationFd.requestFocus();
				}
			}
		});
		scrollPane_1.setViewportView(textFieldNotesFD);

		scrollPaneName = new JScrollPane();
		scrollPaneName.setVisible(false);
		scrollPaneName.setBounds(74, 37, 209, 75);
		panel_1.add(scrollPaneName);

		listname = new JList<String>();
		listname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					textFieldNameFd.setText(listname.getSelectedValue());
					String accountnotop = textFieldNameFd.getText();

					scrollPaneName.setVisible(false);
					listname.setVisible(false);
					textFieldContactNoFd.setVisible(true);

					try {
						conn = DBConnection.getConnection();
						String query = "Select * from banksystem.fd where Name=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldNameFd.getText());
						rs = ps.executeQuery();
						while (rs.next()) {

							String Srnomaster = rs.getString("SrNoMaster");
							textFieldSrNoMasterFD.setText(Srnomaster);
							String UpdateAmtDate = rs.getString("UpdateDate");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date dd = sdf.parse(UpdateAmtDate);
							dateChooserfdac.setDate(dd);
							String AccountNumber = rs.getString("Name");
							textFieldNameFd.setText(AccountNumber);
							String Name = rs.getString("AccountNumber");
							textFieldAccountNo.setText(Name);
							String Address = rs.getString("Address");
							textFieldAddressFd.setText(Address);
							String Age = rs.getString("Age");
							textFieldAgeFd.setText(Age);
							String ContactNo = rs.getString("ContactNo");
							textFieldContactNoFd.setText(ContactNo);
							String LoanAgainst = rs.getString("Duration");
							textFieldDourationFd.setText(LoanAgainst);

							String Duration = rs.getString("Days");
							textFieldDays.setText(Duration);
							String Days = rs.getString("Amount");
							textFieldAmountFD.setText(Days);
							String Amount = rs.getString("Interes");
							textFieldInteresFD.setText(Amount);
							String Interst = rs.getString("IntsetAmt");
							textFieldInteresAmtFD.setText(Interst);
							String InstersAmt = rs.getString("Total");
							textFieldTotalFD.setText(InstersAmt);
							String Total = rs.getString("LoanPer");
							textFieldLoanPerstantege.setText(Total);
							String ShareInterst = rs.getString("LoanAmount");
							textFieldLoanAmt.setText(ShareInterst);
							String Interst4 = rs.getString("Interst");
							textFieldInterst.setText(Interst4);
							String FormFee = rs.getString("InterstAmt44");
							textFieldInterstAmt.setText(FormFee);

							String StationaryAmt = rs.getString("TotalLoanAgamt");
							textFieldTotal.setText(StationaryAmt);

							String TotalRemaningFee = rs.getString("Nomeny");
							textField_Nomeny.setText(TotalRemaningFee);
							String Gaurentr1 = rs.getString("NomenyRelation");
							textField_NomenyOther.setText(Gaurentr1);

							String Relation1 = rs.getString("Notes");
							textFieldNotesFD.setText(Relation1);
							String Gaurentr2 = rs.getString("OtherAmount");
							textFieldOtherAmount.setText(Gaurentr2);

						}

					} catch (Exception ee) {
						System.out.println(ee.getMessage());
					} finally {
						try {
							rs.close();
							ps.close();
							conn.close();
						} catch (Exception ess) {
							System.out.println(ess.getMessage());
						}
					}

				}
			}
		});
		listname.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldNameFd.setText(listname.getSelectedValue());
				String accountnotop = textFieldNameFd.getText();

				scrollPaneName.setVisible(false);
				listname.setVisible(false);
				textFieldContactNoFd.setVisible(true);

				try {
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.fd where Name=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldNameFd.getText());
					rs = ps.executeQuery();
					while (rs.next()) {

						String Srnomaster = rs.getString("SrNoMaster");
						textFieldSrNoMasterFD.setText(Srnomaster);
						String UpdateAmtDate = rs.getString("UpdateDate");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date dd = sdf.parse(UpdateAmtDate);
						dateChooserfdac.setDate(dd);
						String AccountNumber = rs.getString("Name");
						textFieldNameFd.setText(AccountNumber);
						String Name = rs.getString("AccountNumber");
						textFieldAccountNo.setText(Name);
						String Address = rs.getString("Address");
						textFieldAddressFd.setText(Address);
						String Age = rs.getString("Age");
						textFieldAgeFd.setText(Age);
						String ContactNo = rs.getString("ContactNo");
						textFieldContactNoFd.setText(ContactNo);
						String LoanAgainst = rs.getString("Duration");
						textFieldDourationFd.setText(LoanAgainst);

						String Duration = rs.getString("Days");
						textFieldDays.setText(Duration);
						String Days = rs.getString("Amount");
						textFieldAmountFD.setText(Days);
						String Amount = rs.getString("Interes");
						textFieldInteresFD.setText(Amount);
						String Interst = rs.getString("IntsetAmt");
						textFieldInteresAmtFD.setText(Interst);
						String InstersAmt = rs.getString("Total");
						textFieldTotalFD.setText(InstersAmt);
						String Total = rs.getString("LoanPer");
						textFieldLoanPerstantege.setText(Total);
						String ShareInterst = rs.getString("LoanAmount");
						textFieldLoanAmt.setText(ShareInterst);
						String Interst4 = rs.getString("Interst");
						textFieldInterst.setText(Interst4);
						String FormFee = rs.getString("InterstAmt44");
						textFieldInterstAmt.setText(FormFee);

						String StationaryAmt = rs.getString("TotalLoanAgamt");
						textFieldTotal.setText(StationaryAmt);

						String TotalRemaningFee = rs.getString("Nomeny");
						textField_Nomeny.setText(TotalRemaningFee);
						String Gaurentr1 = rs.getString("NomenyRelation");
						textField_NomenyOther.setText(Gaurentr1);

						String Relation1 = rs.getString("Notes");
						textFieldNotesFD.setText(Relation1);
						String Gaurentr2 = rs.getString("OtherAmount");
						textFieldOtherAmount.setText(Gaurentr2);

					}

				} catch (Exception ee) {
					System.out.println(ee.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ess) {
						System.out.println(ess.getMessage());
					}
				}

			}
		});
		listname.setVisible(false);
		scrollPaneName.setViewportView(listname);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(0, 387, 1154, 60);
		contentPane.add(panel_2);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					conn = DBConnection.getConnection();
					String in = "insert into banksystem.fd values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
					ps = conn.prepareStatement(in);
					ps.setInt(1, Integer.parseInt(textFieldSrNoFD.getText()));
					ps.setInt(2, Integer.parseInt(textFieldSrNoFD.getText()));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserfdac.getDate();
					String date = (String) sdf.format(dateChooserfdac.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(3, (java.sql.Date) d);
					ps.setDate(4, (java.sql.Date) d);
					ps.setString(5, textFieldNameFd.getText());
					ps.setString(6, textFieldAccountNo.getText());
					ps.setString(7, textFieldAddressFd.getText());
					ps.setDouble(8, Double.valueOf(textFieldAgeFd.getText()));
					ps.setString(9, textFieldContactNoFd.getText());
					ps.setString(10, textFieldDourationFd.getText());
					ps.setString(11, textFieldDays.getText());
					ps.setDouble(12, Double.valueOf(textFieldAmountFD.getText()));
					ps.setDouble(13, Double.valueOf(textFieldInteresFD.getText()));
					ps.setDouble(14, Double.valueOf(textFieldInteresAmtFD.getText()));
					ps.setDouble(15, Double.valueOf(textFieldTotalFD.getText()));
					ps.setDouble(16, Double.valueOf(textFieldLoanPerstantege.getText()));
					ps.setDouble(17, Double.valueOf(textFieldLoanAmt.getText()));
					ps.setDouble(18, Double.valueOf(textFieldInterst.getText()));
					ps.setDouble(19, Double.valueOf(textFieldInterstAmt.getText()));
					ps.setDouble(20, Double.valueOf(textFieldTotal.getText()));
					ps.setString(21, textField_Nomeny.getText());
					ps.setString(22, textField_NomenyOther.getText());
					ps.setString(23, textFieldNotesFD.getText());
					ps.setDouble(24, Double.valueOf(textFieldOtherAmount.getText()));
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data are saved.");
					tabledataview();
					totalamount();

				} catch (Exception ee) {
					System.out.print(ee.getMessage());
				} finally {
					try {

						ps.close();
						conn.close();
					} catch (Exception ess) {
						System.out.println(ess.getMessage());
					}
				}
				try {
					if (rdbtnFdAgainstLoan.isSelected()) {
						conn = DBConnection.getConnection();
						String acno = null;
						ps1 = conn.prepareStatement("select max(SrnoMaster) from banksystem.loan;");
						rs = ps1.executeQuery();
						while (rs.next()) {
							incrementval = (rs.getInt(1) + 1);

						}

						conn = DBConnection.getConnection();
						String in = "insert into banksystem.loan(Srnomaster,Srno,date,UpdateAmtDate,AmtPaidDate,AccountNumber,Name,Address,Age,ContactNo,Duration,Days,Amount,Interst,InstersAmt,Total,Nomeny,NomeyRelation,Note,Code,LastDate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
						ps = conn.prepareStatement(in);
						ps.setInt(1, incrementval);
						ps.setInt(2, incrementval);
						SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
						dt1 = dateChooserfdac.getDate();
						String date2 = (String) sdf2.format(dateChooserfdac.getDate());
						java.sql.Date d2 = new java.sql.Date(sdf2.parse(date2).getTime());
						ps.setDate(3, (java.sql.Date) d2);
						ps.setDate(4, (java.sql.Date) d2);
						ps.setDate(5, (java.sql.Date) d2);

						ps.setString(6, "L" + incrementval);
						ps.setString(7, textFieldNameFd.getText());
						ps.setString(8, textFieldAddressFd.getText());
						ps.setDouble(9, Double.valueOf(textFieldAgeFd.getText()));
						ps.setString(10, textFieldContactNoFd.getText());
						ps.setString(11, textFieldDourationFd.getText());
						ps.setString(12, textFieldDays.getText());
						ps.setDouble(13, Double.valueOf(textFieldLoanAmt.getText()));
						ps.setDouble(14, Double.valueOf(textFieldInterst.getText()));
						ps.setDouble(15, Double.valueOf(textFieldInterstAmt.getText()));
						ps.setDouble(16, Double.valueOf(textFieldTotal.getText()));
						ps.setString(17, textField_Nomeny.getText());
						ps.setString(18, textField_NomenyOther.getText());
						ps.setString(19, textFieldNotesFD.getText());
						ps.setInt(20, 1);
						Calendar c = Calendar.getInstance();
						int year = c.get(Calendar.YEAR);
						int month = c.get(Calendar.MONTH);
						int day = 1;
						c.set(year, month, day);
						int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);

						c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);
						String indate = sdf2.format(c.getTime());
						Date parsed = (Date) sdf2.parse(indate);
						java.sql.Date sql7777 = new java.sql.Date(parsed.getTime());

						ps.setDate(21, (java.sql.Date) sql7777);
						int i = ps.executeUpdate();
						if (i > 0) {

							try {
								int issrno = 0;
								conn = DBConnection.getConnection();
								String maxno = "select max(SrNoMaster) from banksystem.loantranscation;";
								ps2 = conn.prepareStatement(maxno);
								rs1 = ps2.executeQuery();
								while (rs1.next()) {
									issrno = rs1.getInt(1) + 1;
								}

								String insertdata = "insert into banksystem.loantranscation (SrNoMaster, SrNo,Date,Name, AccountNo, TransactionParticulars,Balance) values(?,?,?,?,?,?,?);";
								ps3 = conn.prepareStatement(insertdata);
								ps3.setInt(1, issrno);

								ps2.setInt(2, 1);
								SimpleDateFormat sdf22 = new SimpleDateFormat("yyyy-MM-dd");
								dt1 = dateChooserfdac.getDate();
								String date22 = (String) sdf22.format(dateChooserfdac.getDate());
								java.sql.Date d22 = new java.sql.Date(sdf22.parse(date22).getTime());
								ps3.setDate(3, (java.sql.Date) d22);
								ps3.setString(4, textFieldNameFd.getText());
								ps3.setString(5, "L" + incrementval);
								ps3.setString(6, "Loan of");
								ps3.setDouble(7, Double.valueOf(textFieldInterstAmt.getText()));
								ps3.executeUpdate();

							} catch (Exception ee) {
								System.out.println(ee.getMessage());
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
									System.out.println(ew.getMessage());
								}
							}

						}

					}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
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
						SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
						dt1 = dateChooserfdac.getDate();
						String date2 = (String) sdf2.format(dateChooserfdac.getDate());
						java.sql.Date d2 = new java.sql.Date(sdf2.parse(date2).getTime());
						ps1.setDate(2, (java.sql.Date) d2);
						ps1.setString(3, textFieldNameFd.getText());
						ps1.setString(4, textFieldAccountNo.getText());
						ps1.setDouble(5, Double.valueOf(textFieldOtherAmount.getText()));
						ps1.setString(6, textFieldNotesFD.getText());
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
					} catch (Exception ew) {
						System.out.println(ew.getMessage());
					}
				}

			}

			private void tabledataview() {

				String data0 = textFieldSrNoMasterFD.getText();
				String data1 = textFieldSrNoFD.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				dt1 = dateChooserfdac.getDate();
				String data2 = (String) sdf.format(dateChooserfdac.getDate());
				String data3 = (String) sdf.format(dateChooserfdac.getDate());
				String data4 = textFieldAccountNo.getText();
				String data5 = textFieldNameFd.getText();
				String data6 = textFieldInteresFD.getText();
				String data7 = textFieldAmountFD.getText();
				String data8 = textFieldDourationFd.getText();
				String data9 = textFieldDays.getText();
				String data10 = textFieldInteresAmtFD.getText();
				String data11 = textFieldTotalFD.getText();
				String data12 = textFieldLoanPerstantege.getText();
				String data13 = textFieldLoanAmt.getText();
				String data14 = textFieldInterst.getText();
				String data15 = textFieldInterstAmt.getText();
				String data16 = textFieldTotal.getText();
				String data17 = textField_Nomeny.getText();
				String data18 = textField_NomenyOther.getText();
				String data19 = textFieldAddressFd.getText();
				String data20 = textFieldAgeFd.getText();
				String data21 = textFieldContactNoFd.getText();
				String data22 = textFieldNotesFD.getText();
				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12, data13, data14, data15, data16, data17, data18, data19, data20, data21,
						data22 };
				model1 = (DefaultTableModel) tableFDA_C.getModel();
				model1.addRow(row);

			}
		});
		btnSave.setIcon(new ImageIcon(FDA_C.class.getResource("/ImageButtonIcon/Save.png")));
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
					String up = "UPDATE banksystem.fd  set Date=?, UpdateDate=?, Name=?, AccountNumber=?, Address=?, Age=?, ContactNo=?, Duration=?, Days=?, Amount=?, Interes=?, IntsetAmt=?, Total=?, LoanPer=?, LoanAmount=?, Interst=?, InterstAmt44=?, TotalLoanAgamt=?, Nomeny=?, NomenyRelation=?, Notes=?,OtherAmount=?   where SrNoMaster=?";
					ps = conn.prepareStatement(up);
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserfdac.getDate();
					String date2 = (String) sdf2.format(dateChooserfdac.getDate());
					java.sql.Date d2 = new java.sql.Date(sdf2.parse(date2).getTime());
					ps.setDate(1, (java.sql.Date) d2);
					ps.setDate(2, (java.sql.Date) d2);
					ps.setString(3, textFieldNameFd.getText());
					ps.setString(4, textFieldAccountNo.getText());
					ps.setString(5, textFieldAddressFd.getText());
					ps.setDouble(6, Double.valueOf(textFieldAgeFd.getText()));
					ps.setString(7, textFieldContactNoFd.getText());
					ps.setString(8, textFieldDourationFd.getText());
					ps.setString(9, textFieldDays.getText());
					ps.setDouble(10, Double.valueOf(textFieldAmountFD.getText()));
					ps.setDouble(11, Double.valueOf(textFieldInteresFD.getText()));
					ps.setDouble(12, Double.valueOf(textFieldInteresAmtFD.getText()));
					ps.setDouble(13, Double.valueOf(textFieldTotalFD.getText()));
					ps.setDouble(14, Double.valueOf(textFieldLoanPerstantege.getText()));
					ps.setDouble(15, Double.valueOf(textFieldLoanAmt.getText()));
					ps.setDouble(16, Double.valueOf(textFieldInterst.getText()));
					ps.setDouble(17, Double.valueOf(textFieldLoanAmt.getText()));
					ps.setDouble(18, Double.valueOf(textFieldTotal.getText()));
					ps.setString(19, textField_Nomeny.getText());
					ps.setString(20, textField_NomenyOther.getText());
					ps.setString(21, textFieldNotesFD.getText());
					ps.setDouble(22, Double.valueOf(textFieldOtherAmount.getText()));
					ps.setInt(23, Integer.parseInt(textFieldSrNoMasterFD.getText()));

					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are update.");
						dispose();
						FDA_C fd = new FDA_C();
						fd.setUndecorated(true);
						fd.setVisible(true);
					}

				} catch (Exception e2) {
					System.out.print(e2.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ew) {
						System.out.println(ew.getMessage());
					}
				}

			}
		});
		button_Update.setIcon(new ImageIcon(FDA_C.class.getResource("/ImageButtonIcon/update.png")));
		button_Update.setToolTipText("");
		button_Update.setHorizontalAlignment(SwingConstants.LEADING);
		button_Update.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_Update.setBounds(77, 11, 126, 38);
		panel_2.add(button_Update);

		JButton button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String de = "delete from banksystem.fd where SrNomaster=?";
					ps = conn.prepareStatement(de);
					ps.setInt(1, Integer.parseInt(textFieldSrNoMasterFD.getText()));
					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are deleted.");
						dispose();
						FDA_C fd = new FDA_C();
						fd.setUndecorated(true);
						fd.setVisible(true);

					}

				} catch (Exception qe) {
					System.out.print(qe.getMessage());
				} finally {
					try {

						ps.close();
						conn.close();
					} catch (Exception ew) {
						System.out.println(ew.getMessage());
					}
				}
			}
		});
		button_2.setIcon(new ImageIcon(FDA_C.class.getResource("/ImageButtonIcon/delete.jpg")));
		button_2.setHorizontalAlignment(SwingConstants.LEADING);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_2.setBounds(477, 11, 115, 38);
		panel_2.add(button_2);

		JButton button_3 = new JButton("Reset");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		button_3.setIcon(new ImageIcon(FDA_C.class.getResource("/ImageButtonIcon/Reset5.jpg")));
		button_3.setHorizontalAlignment(SwingConstants.LEADING);
		button_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_3.setBounds(602, 11, 115, 38);
		panel_2.add(button_3);

		JButton button_4 = new JButton("Exit");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Welcome.frame.enable();
			}
		});
		button_4.setIcon(new ImageIcon(FDA_C.class.getResource("/ImageButtonIcon/Exit.png")));
		button_4.setHorizontalAlignment(SwingConstants.LEADING);
		button_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_4.setBounds(727, 11, 115, 38);
		panel_2.add(button_4);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldInteresAmtFD.setEditable(true);
				textFieldAccountNo.setEditable(true);
				textFieldTotalFD.setEditable(true);
				textFieldTotal.setEditable(true);
				textFieldLoanAmt.setEditable(true);
				textFieldInterstAmt.setEditable(true);
				btnEdit.setVisible(false);
				button_Update.setBounds(341, 12, 126, 38);
				button_Update.setVisible(true);

			}
		});
		btnEdit.setIcon(new ImageIcon(FDA_C.class.getResource("/ImageButtonIcon/edit.png")));
		btnEdit.setToolTipText("");
		btnEdit.setHorizontalAlignment(SwingConstants.LEADING);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(341, 12, 126, 38);
		panel_2.add(btnEdit);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(0, 458, 1154, 186);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 1134, 168);
		panel_3.add(scrollPane);

		tableFDA_C = new JTable(model1);

		tableFDA_C.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableFDA_C.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					int i = tableFDA_C.getSelectedRow();
					model1 = (DefaultTableModel) tableFDA_C.getModel();
					textFieldSrNoMasterFD.setText(model1.getValueAt(i, 1).toString());
					textFieldSrNoFD.setText(model1.getValueAt(i, 2).toString());
					//dateChooserfdac.setDateFormatString(model1.getValueAt(i, 3).toString());

					textFieldAccountNo.setText(model1.getValueAt(i, 5).toString());
					textFieldNameFd.setText(model1.getValueAt(i, 6).toString());
					textFieldInteresFD.setText(model1.getValueAt(i, 7).toString());
					textFieldAmountFD.setText(model1.getValueAt(i, 8).toString());
					textFieldDourationFd.setText(model1.getValueAt(i, 9).toString());
					textFieldDays.setText(model1.getValueAt(i, 10).toString());
					
					textFieldInteresAmtFD.setText(model1.getValueAt(i, 11).toString());
					textFieldTotalFD.setText(model1.getValueAt(i, 12).toString());
					textFieldLoanPerstantege.setText(model1.getValueAt(i, 13).toString());
					textFieldLoanAmt.setText(model1.getValueAt(i, 14).toString());
					textFieldInterst.setText(model1.getValueAt(i, 15).toString());
					textFieldInterstAmt.setText(model1.getValueAt(i, 16).toString());
					textFieldTotal.setText(model1.getValueAt(i, 17).toString());
					textField_Nomeny.setText(model1.getValueAt(i, 18).toString());
					textField_NomenyOther.setText(model1.getValueAt(i, 19).toString());
					textFieldAddressFd.setText(model1.getValueAt(i, 20).toString());
					textFieldAgeFd.setText(model1.getValueAt(i, 21).toString());
					textFieldContactNoFd.setText(model1.getValueAt(i, 22).toString());
					textFieldNotesFD.setText(model1.getValueAt(i, 23).toString());
				} catch (Exception es) {
					System.out.println(es.getMessage());
				}
				try {

					che();

				} catch (Exception ee) {
					System.out.println(ee.getMessage());
				}

			}

			private void che() {
				boolean s = false;
				boolean s1 = true;
				String inc = null;
				int row = 0;
				int rows = tableFDA_C.getRowCount() - 1;

				for (int i = 0; i < tableFDA_C.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableFDA_C.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableFDA_C.getSelectedRow();
						int col = tableFDA_C.getSelectedColumn();
						tableFDA_C.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableFDA_C.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableFDA_C.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableFDA_C.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableFDA_C.setValueAt((Object) s, 0, 0);
							tableFDA_C.setValueAt((Object) s1, row, 0);

						}
					}

					else {
					}
				}

			}

		});
		tableFDA_C.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableFDA_C.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Select", "SrNoMaster", "SrNo", "Date", "Update Date", "A/C No", "Name ", "Int %", "Amount", "Duration", "Days", "Interes Amt", "Maturity Amt", "Loan Per", "Loan Amount", "Loan Int%", "Loan Int Amt", "Total L Amt", "Nomeny", "Nomeny Relation", "Address", "Age", "Contact No", "Nots"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableFDA_C.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableFDA_C.getColumnModel().getColumn(0).setMinWidth(50);
		tableFDA_C.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableFDA_C.getColumnModel().getColumn(1).setMinWidth(0);
		tableFDA_C.getColumnModel().getColumn(1).setMaxWidth(0);
		tableFDA_C.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableFDA_C.getColumnModel().getColumn(2).setMinWidth(50);
		tableFDA_C.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableFDA_C.getColumnModel().getColumn(3).setMinWidth(80);
		tableFDA_C.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableFDA_C.getColumnModel().getColumn(4).setMinWidth(80);
		tableFDA_C.getColumnModel().getColumn(4).setMaxWidth(80);
		tableFDA_C.getColumnModel().getColumn(5).setPreferredWidth(80);
		tableFDA_C.getColumnModel().getColumn(5).setMinWidth(80);
		tableFDA_C.getColumnModel().getColumn(6).setPreferredWidth(150);
		tableFDA_C.getColumnModel().getColumn(6).setMinWidth(150);
		tableFDA_C.getColumnModel().getColumn(7).setPreferredWidth(50);
		tableFDA_C.getColumnModel().getColumn(7).setMinWidth(50);
		tableFDA_C.getColumnModel().getColumn(8).setPreferredWidth(80);
		tableFDA_C.getColumnModel().getColumn(8).setMinWidth(80);
		tableFDA_C.getColumnModel().getColumn(9).setPreferredWidth(80);
		tableFDA_C.getColumnModel().getColumn(9).setMinWidth(80);
		tableFDA_C.getColumnModel().getColumn(10).setPreferredWidth(50);
		tableFDA_C.getColumnModel().getColumn(10).setMinWidth(0);
		tableFDA_C.getColumnModel().getColumn(10).setMaxWidth(0);
		tableFDA_C.getColumnModel().getColumn(11).setPreferredWidth(15);
		tableFDA_C.getColumnModel().getColumn(11).setMinWidth(0);
		tableFDA_C.getColumnModel().getColumn(11).setMaxWidth(0);
		tableFDA_C.getColumnModel().getColumn(12).setPreferredWidth(15);
		tableFDA_C.getColumnModel().getColumn(12).setMinWidth(80);
		tableFDA_C.getColumnModel().getColumn(15).setPreferredWidth(50);
		tableFDA_C.getColumnModel().getColumn(15).setMinWidth(50);
		tableFDA_C.getColumnModel().getColumn(16).setPreferredWidth(80);
		tableFDA_C.getColumnModel().getColumn(16).setMinWidth(80);
		tableFDA_C.getColumnModel().getColumn(17).setPreferredWidth(80);
		tableFDA_C.getColumnModel().getColumn(17).setMinWidth(80);
		tableFDA_C.getColumnModel().getColumn(18).setPreferredWidth(150);
		tableFDA_C.getColumnModel().getColumn(18).setMinWidth(150);
		tableFDA_C.getColumnModel().getColumn(19).setPreferredWidth(150);
		tableFDA_C.getColumnModel().getColumn(19).setMinWidth(150);
		tableFDA_C.getColumnModel().getColumn(20).setPreferredWidth(150);
		tableFDA_C.getColumnModel().getColumn(20).setMinWidth(150);
		tableFDA_C.getColumnModel().getColumn(21).setPreferredWidth(50);
		tableFDA_C.getColumnModel().getColumn(21).setMinWidth(50);
		tableFDA_C.getColumnModel().getColumn(22).setPreferredWidth(80);
		tableFDA_C.getColumnModel().getColumn(22).setMinWidth(80);
		tableFDA_C.getColumnModel().getColumn(23).setPreferredWidth(150);
		tableFDA_C.getColumnModel().getColumn(23).setMinWidth(150);
		showdata();

		scrollPane.setViewportView(tableFDA_C);

		JButton btnWidraw = new JButton("Withdral");
		btnWidraw.setIcon(new ImageIcon(FDA_C.class.getResource("/ImageButtonIcon/widrawal1(1).png")));
		btnWidraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double amtaa = Double.valueOf(textFieldAmountFD.getText());
				if (amtaa <= 0) {
					JOptionPane.showMessageDialog(null, "account is suspend mode because balance total withdrawal.");
				} else {
					try {

						for (int i = 0; i <= tableFDA_C.getRowCount(); i++) {
							Boolean che = Boolean.valueOf(tableFDA_C.getValueAt(i, 0).toString());
							if (che) {
								String Srno = tableFDA_C.getValueAt(i, 1).toString();
								srnofd = Srno;
								acno = textFieldAccountNo.getText();
								String datadate = tableFDA_C.getValueAt(i, 4).toString();
								
								countday = Double.valueOf(tableFDA_C.getValueAt(i, 10).toString());
								
								srnomaster = Integer.parseInt(tableFDA_C.getValueAt(i, 1).toString());
								intrestper = Double.valueOf(tableFDA_C.getValueAt(i, 7).toString());
								
								
								amount = Double.valueOf(tableFDA_C.getValueAt(i, 8).toString());
							
								totalamount = Double.valueOf(tableFDA_C.getValueAt(i, 12).toString());
								
								intersamt = Double.valueOf(tableFDA_C.getValueAt(i, 11).toString());
								

								DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								Date date1 = new Date();

								DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
								Date date2 = sdf1.parse(datadate);

								long diff = date1.getTime() - date2.getTime();
								double day = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

								daycount = day;

								if (countday >= daycount) {
									Fd_Premature fdp = new Fd_Premature();
									fdp.setVisible(true);

								} else if (countday < daycount) {
									FdMature fdm = new FdMature();
									fdm.setVisible(true);

								} else {
									JOptionPane.showMessageDialog(null,
											"account is suspend mode because balance total withdrawal");
								}
							}
						}
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ee) {
						System.out.println(ee.getMessage());
					}
				}

			}

		});
		btnWidraw.setHorizontalAlignment(SwingConstants.LEADING);
		btnWidraw.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnWidraw.setBounds(26, 655, 169, 38);
		contentPane.add(btnWidraw);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(0, 199, 1154, 112);
		contentPane.add(panel_4);

		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDuration.setBounds(10, 11, 54, 17);
		panel_4.add(lblDuration);

		JLabel lblOther = new JLabel("Other:");
		lblOther.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOther.setBounds(319, 11, 81, 17);
		panel_4.add(lblOther);

		textFieldDourationFd = new JTextField();
		textFieldDourationFd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String st = textFieldDourationFd.getText();
				daycount(st);

			}
		});
		textFieldDourationFd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldDourationFd.setText("");
			}
		});
		textFieldDourationFd.setText("other e.g 3 Month,3 Year");
		textFieldDourationFd.setEnabled(false);
		textFieldDourationFd.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldDourationFd.setColumns(10);
		textFieldDourationFd.setBounds(360, 9, 209, 23);
		panel_4.add(textFieldDourationFd);

		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAmount.setBounds(10, 42, 54, 17);
		panel_4.add(lblAmount);

		textFieldAmountFD = new JTextField();
		textFieldAmountFD.addKeyListener(new KeyAdapter() {
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
		textFieldAmountFD.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAmountFD.setColumns(10);
		textFieldAmountFD.setBounds(73, 42, 209, 23);
		panel_4.add(textFieldAmountFD);

		JLabel lblInteres = new JLabel("Interes %:");
		lblInteres.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblInteres.setBounds(292, 42, 106, 17);
		panel_4.add(lblInteres);

		textFieldInteresFD = new JTextField();
		textFieldInteresFD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Calculation();
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == '%') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldInteresFD.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldInteresFD.setColumns(10);
		textFieldInteresFD.setBounds(360, 44, 63, 23);
		panel_4.add(textFieldInteresFD);

		comboBoxDurationFd = new JComboBox();
		comboBoxDurationFd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxDurationFd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String st = comboBoxDurationFd.getSelectedItem().toString();
				daycount(st);
				}
				catch(Exception we)
				{
					we.printStackTrace();
				}
			}
		});
		comboBoxDurationFd.setModel(new DefaultComboBoxModel(new String[] { "Select", "1 Month", "2 Month", "3 Month\t",
				"6 Month\t", "9 Month", "1 Year", "2 Year\t", "3 Year", "4 Year", "5 Year" }));
		comboBoxDurationFd.setBounds(73, 13, 209, 23);
		panel_4.add(comboBoxDurationFd);

		JCheckBox checkBoxDuration = new JCheckBox("");
		checkBoxDuration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click1();
			}

			private void click1() {
				if (!checkBoxDuration.isSelected()) {
					comboBoxDurationFd.setEnabled(true);
					textFieldDourationFd.setEnabled(false);
					textFieldDourationFd.setText("");

				} else {
					comboBoxDurationFd.setEnabled(false);
					textFieldDourationFd.setEnabled(true);
					String em = "";
					comboBoxDurationFd.setSelectedIndex(0);
				}

			}
		});
		checkBoxDuration.setBounds(292, 11, 21, 23);
		panel_4.add(checkBoxDuration);

		JLabel lblTotal = new JLabel("Maturity Amount:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotal.setBounds(579, 39, 109, 17);
		panel_4.add(lblTotal);

		textFieldTotalFD = new JTextField();
		textFieldTotalFD.setForeground(Color.RED);
		textFieldTotalFD.setEditable(false);
		textFieldTotalFD.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldTotalFD.setColumns(10);
		textFieldTotalFD.setBounds(671, 37, 176, 23);
		panel_4.add(textFieldTotalFD);

		textFieldInteresAmtFD = new JTextField();
		textFieldInteresAmtFD.setForeground(Color.RED);
		textFieldInteresAmtFD.setEditable(false);
		textFieldInteresAmtFD.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldInteresAmtFD.setColumns(10);
		textFieldInteresAmtFD.setBounds(435, 44, 134, 23);
		panel_4.add(textFieldInteresAmtFD);

		textFieldDays = new JTextField();
		textFieldDays.setVisible(false);
		textFieldDays.setEnabled(false);
		textFieldDays.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldDays.setColumns(10);
		textFieldDays.setBounds(579, 11, 63, 23);
		panel_4.add(textFieldDays);

		JLabel label_6 = new JLabel("Nomeny:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(10, 82, 63, 17);
		panel_4.add(label_6);

		textField_Nomeny = new JTextField();
		textField_Nomeny.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_Nomeny.setColumns(10);
		textField_Nomeny.setBounds(73, 79, 209, 23);
		panel_4.add(textField_Nomeny);

		JLabel label_7 = new JLabel("Relation:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(292, 82, 74, 17);
		panel_4.add(label_7);

		comboBoxNomeny = new JComboBox();
		comboBoxNomeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = comboBoxNomeny.getSelectedItem().toString();
				textField_NomenyOther.setText("");
				textField_NomenyOther.setText(name);
			}
		});
		comboBoxNomeny.setModel(new DefaultComboBoxModel(new String[] {"   Select", "   Father", "   Mother", "   Paternal Grandfather", "    Maternal Grandfather ", "    Maternal Grandmother", "    Brother", "    Brother's Wife", "    Elder Brother", "    Younger Brother", "    Husband's sister", "    Sister", "    Sister's husband", "    Elder Sister", "    Younger Sister)", "    Husband's elder brother", "    Husband's younger brother", "    Elder brother's wife", "    Younger brother's wife", "    Wife's Sister", "    Wife's Brother", "    Wife's Brother's wife", "    Husband's Sister's Husband", "    Wife's sister's husband", "    Husband's elder brother's wife", "    Husband's younger brother's wife", "    Son", "    Son's wife (daughter-in-law) ", "    Daughter ", "    Daughter's husband (son-in-law) ", "    Grandson (son's son)", "    Granddaughter (son's daughter)", "    Grandson (daughter's son)", "    Granddaughter (daughter's daughter)", "    Husband", "    Wife", "    Husband's Mother (Mother-in-law)", "    Husband's Father (Father-in-law)", "    Fianc\u00E9 or Fianc\u00E9e", "    Father's younger brother (uncle)", "    Father's younger brother's wife (aunt)", "    Father's elder brother's (Uncle)", "    Father's elder brother's wife (Aunt) ", "    Father's sister (aunt)", "    Father's sister's husband", "    Mother's brother", "    Mother's brother's wife", "    Mother's sister", "    Mother's sister's husband", "    Brother's son (nephew)", "    Sister's son (nephew)", "    Brother's daughter (niece)", "    Sister's daughter (niece) ", "    Husband's elder brother's daughter", "    Husband's elder brother's son"}));
		comboBoxNomeny.setBounds(360, 79, 176, 23);
		panel_4.add(comboBoxNomeny);

		JCheckBox checkBoxNomeny = new JCheckBox("");
		checkBoxNomeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkBoxNomeny.isSelected()) {
					comboBoxNomeny.setEnabled(true);
					textField_NomenyOther.setEnabled(false);
					textField_NomenyOther.setText("");
				} else {
					comboBoxNomeny.setEnabled(false);
					textField_NomenyOther.setEnabled(true);
					comboBoxNomeny.setSelectedIndex(0);
				}
			}
		});
		checkBoxNomeny.setBounds(542, 76, 27, 23);
		panel_4.add(checkBoxNomeny);

		JLabel label_8 = new JLabel("Other:");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_8.setBounds(579, 77, 81, 17);
		panel_4.add(label_8);

		textField_NomenyOther = new JTextField();
		textField_NomenyOther.setText("Other");
		textField_NomenyOther.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_NomenyOther.setEnabled(false);
		textField_NomenyOther.setColumns(10);
		textField_NomenyOther.setBounds(671, 77, 176, 23);
		panel_4.add(textField_NomenyOther);

		JLabel lblOtherAmount = new JLabel("Other Charge:");
		lblOtherAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOtherAmount.setBounds(857, 42, 86, 17);
		panel_4.add(lblOtherAmount);

		textFieldOtherAmount = new JTextField();
		textFieldOtherAmount.setText("0");
		textFieldOtherAmount.addKeyListener(new KeyAdapter() {
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
		textFieldOtherAmount.setForeground(Color.BLACK);
		textFieldOtherAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldOtherAmount.setColumns(10);
		textFieldOtherAmount.setBounds(955, 39, 166, 23);
		panel_4.add(textFieldOtherAmount);
		
		JLabel label = new JLabel("e.g. 1 Month, 1M ,1 Year,1 Y ... etc");
		label.setForeground(Color.RED);
		label.setBounds(366, 29, 194, 14);
		panel_4.add(label);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(0, 322, 1154, 54);
		contentPane.add(panel_5);

		rdbtnFdAgainstLoan = new JRadioButton("FD Against Loan");
		rdbtnFdAgainstLoan.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnFdAgainstLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnFdAgainstLoan.isSelected()) {
					panel_6.setVisible(true);
				} else {
					panel_6.setVisible(false);

				}

			}
		});
		rdbtnFdAgainstLoan.setBounds(6, 7, 143, 23);
		panel_5.add(rdbtnFdAgainstLoan);

		panel_6 = new JPanel();
		panel_6.setBounds(135, 7, 1009, 44);
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		panel_6.setVisible(false);

		JLabel lblAmount_1 = new JLabel("Loan %:");
		lblAmount_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAmount_1.setBounds(23, 14, 63, 17);
		panel_6.add(lblAmount_1);

		textFieldLoanPerstantege = new JTextField();
		textFieldLoanPerstantege.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textFieldLoanPerstantege.setText("");
			}
		});
		textFieldLoanPerstantege.setText("0");
		textFieldLoanPerstantege.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				claculation();

			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == '%') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldLoanPerstantege.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldLoanPerstantege.setColumns(10);
		textFieldLoanPerstantege.setBounds(96, 11, 74, 23);
		panel_6.add(textFieldLoanPerstantege);

		JLabel lblAmount_2 = new JLabel("Amount:");
		lblAmount_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAmount_2.setBounds(191, 14, 56, 17);
		panel_6.add(lblAmount_2);

		textFieldLoanAmt = new JTextField();
		textFieldLoanAmt.setEditable(false);
		textFieldLoanAmt.setForeground(Color.RED);
		textFieldLoanAmt.setText("0");
		textFieldLoanAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldLoanAmt.setColumns(10);
		textFieldLoanAmt.setBounds(257, 11, 115, 23);
		panel_6.add(textFieldLoanAmt);

		JLabel lblTotal_1 = new JLabel("Interst %:");
		lblTotal_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotal_1.setBounds(373, 14, 83, 17);
		panel_6.add(lblTotal_1);

		textFieldInterst = new JTextField();
		textFieldInterst.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textFieldInterst.setText("");
			}
		});
		textFieldInterst.setText("0");
		textFieldInterst.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				claculation();
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == '%') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldInterst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldInterst.setColumns(10);
		textFieldInterst.setBounds(429, 11, 98, 23);
		panel_6.add(textFieldInterst);

		JLabel lblInterstAmt = new JLabel("Interst Amt:");
		lblInterstAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblInterstAmt.setBounds(537, 14, 74, 17);
		panel_6.add(lblInterstAmt);

		textFieldInterstAmt = new JTextField();
		textFieldInterstAmt.setEditable(false);
		textFieldInterstAmt.setForeground(Color.RED);
		textFieldInterstAmt.setText("0");
		textFieldInterstAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldInterstAmt.setColumns(10);
		textFieldInterstAmt.setBounds(621, 11, 117, 23);
		panel_6.add(textFieldInterstAmt);

		JLabel lblTotal_2 = new JLabel("Total:");
		lblTotal_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotal_2.setBounds(748, 14, 56, 17);
		panel_6.add(lblTotal_2);

		textFieldTotal = new JTextField();
		textFieldTotal.setEditable(false);
		textFieldTotal.setForeground(Color.RED);
		textFieldTotal.setText("0");
		textFieldTotal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldTotal.setColumns(10);
		textFieldTotal.setBounds(794, 11, 129, 23);
		panel_6.add(textFieldTotal);

		JButton button = new JButton("Report");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for (int i = 0; i <= tableFDA_C.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableFDA_C.getValueAt(i, 0).toString());
						if (che) {
							String accno4 = String.valueOf(tableFDA_C.getValueAt(i, 5).toString());

							accno = String.valueOf(tableFDA_C.getValueAt(i, 5).toString());

							java.io.InputStream file = getClass().getResourceAsStream("/Reports/Fd.jrxml");
							HashMap para = new HashMap();
							para.put("Acno", accno4);
							allinonereport r = new allinonereport("Fd Report", file, para);

						}

					}
				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(FDA_C.class.getResource("/ImageButtonIcon/Report4d.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(203, 655, 150, 38);
		contentPane.add(button);

		JButton btnReceipt = new JButton("Receipt");
		btnReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					java.io.InputStream file = getClass().getResourceAsStream("/Reports/ReceptFd.jrxml");
					allinonereport r = new allinonereport("Recipt", file);

				} catch (Exception es) {
					System.out.println(es.getMessage());
				}
			}
		});
		btnReceipt.setIcon(new ImageIcon(FDA_C.class.getResource("/ImageButtonIcon/receipt4.jpg")));
		btnReceipt.setHorizontalAlignment(SwingConstants.LEADING);
		btnReceipt.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReceipt.setBounds(367, 655, 150, 38);
		contentPane.add(btnReceipt);

		JLabel lblTotalAmount = new JLabel("Total Amount:");
		lblTotalAmount.setBounds(844, 661, 109, 14);
		contentPane.add(lblTotalAmount);

		textFieldTotalAmtB = new JTextField();
		textFieldTotalAmtB.setForeground(Color.RED);
		textFieldTotalAmtB.setEditable(false);
		textFieldTotalAmtB.setColumns(10);
		textFieldTotalAmtB.setBounds(952, 658, 132, 20);
		contentPane.add(textFieldTotalAmtB);

	}

	public void reset() {
		increment();
		textFieldNameFd.setText("");
		textFieldOtherAmount.setText("");
		textFieldAddressFd.setText("");
		textFieldAgeFd.setText("");
		textFieldContactNoFd.setText("");
		comboBoxDurationFd.setSelectedIndex(1);
		textFieldDourationFd.setText("");
		textFieldAmountFD.setText("");
		textFieldInteresFD.setText("");
		textFieldTotalFD.setText("");
		textFieldInteresAmtFD.setText("");
		textFieldDays.setText("");
		textFieldNotesFD.setText("");
		textField_Nomeny.setText("");
		textField_NomenyOther.setText("Other");
		textFieldLoanPerstantege.setText("0");
		textFieldLoanAmt.setText("0");
		textFieldInterst.setText("0");
		textFieldInterstAmt.setText("0");
		textFieldTotal.setText("0");
		comboBoxNomeny.setSelectedIndex(0);

	}

	public void increment() {
		try {

			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("select max(SrNoMaster) from banksystem.fd;");
			rs = ps.executeQuery();
			while (rs.next()) {
				int val = (rs.getInt(1) + 1);
				val1 = String.valueOf(val);
			}
			textFieldSrNoMasterFD.setText(val1);
			textFieldSrNoFD.setText(val1);
			textFieldAccountNo.setText("F" + val1);

			SimpleDateFormat insdf = new SimpleDateFormat("yyyy-MM-dd");
			Date indate = new Date();
			String instringdate = insdf.format(indate);
			dateChooserfdac.setDate(insdf.parse(instringdate));

		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (Exception ew) {
				System.out.println(ew.getMessage());
			}
		}
	}

	public void Calculation() {
		String ins = textFieldInteresFD.getText();
		if (ins.equals("0")) {
			textFieldTotalFD.setText(textFieldAmountFD.getText());

		} else {

			double amount = Double.valueOf(textFieldAmountFD.getText());
			double interst = Double.valueOf(textFieldInteresFD.getText());
			double totla = amount * interst / 100.0;
			textFieldInteresAmtFD.setText(String.valueOf(totla));
			double sumtotal = totla + amount;
			textFieldTotalFD.setText(String.valueOf(sumtotal));
		}
	}

	public void showdata() {
		try {
			conn = DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.fd order by Srnomaster;";
			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();

			while (rs.next()) {
				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("SrNo");
				String data2 = rs.getString("Date");
				String data3 = rs.getString("UpdateDate");
				String data4 = rs.getString("AccountNumber");
				String data5 = rs.getString("Name");
				String data6 = rs.getString("Interes");
				String data7 = rs.getString("Amount");
				String data8 = rs.getString("Duration");
				String data9 = rs.getString("Days");
				

				double aii = rs.getDouble("IntsetAmt");
				String data10 = String.valueOf(df.format(aii));

				double tototo = rs.getDouble("Total");
				String data11 = String.valueOf(df.format(tototo));

				String data12 = rs.getString("LoanPer");
				String data13 = rs.getString("LoanAmount");
				String data14 = rs.getString("Interst");

				double interstmat = rs.getDouble("InterstAmt44");
				String data15 = String.valueOf(df.format(interstmat));
				double totalamt = rs.getDouble("TotalLoanAgamt");

				String data16 = String.valueOf(df.format(totalamt));
				String data17 = rs.getString("Nomeny");
				String data18 = rs.getString("NomenyRelation");
				
				String data19 = rs.getString("Address");
				String data20 = rs.getString("Age");
				String data21 = rs.getString("ContactNo");
				String data22 = rs.getString("Notes");

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12, data13, data14, data15, data16, data17, data18, data19, data20, data21,
						data22 };
				model1 = (DefaultTableModel) tableFDA_C.getModel();
				model1.addRow(row);

			}

		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (Exception ew) {
				System.out.println(ew.getMessage());
			}
		}
	}

	public void daycount(String str) {
		try
		{
		String number = "";
		String letter = "";
		String symbol = "";
		String firstLetter;
		textFieldDourationFd.setText(str);
		for (int i = 0; i < str.length(); i++) {
			char a = str.charAt(i);
			if (Character.isDigit(a)) {
				number = number + a;

			} else {
				letter = letter + a;
			}
		}
		firstLetter = Character.toString(letter.charAt(1));
		System.out.println(firstLetter);

		if (firstLetter.equals("Y") || firstLetter.equals("y")) {
			double let = Double.valueOf(number);
			double day = let * 365;
			System.out.println(day);
			textFieldDays.setText(String.valueOf(day));

		} else if (firstLetter.equals("M") || firstLetter.equals("m")) {
			double let = Double.valueOf(number);
			double day = let * 30;
			System.out.println(day);
			textFieldDays.setText(String.valueOf(day));

		} else {
			double let = Double.valueOf(number);
			double day = let * 30;
			System.out.println(day);
			textFieldDays.setText(String.valueOf(day));
		}
		}
		catch(Exception we)
		{
			we.printStackTrace();
		}
	}

	public void claculation() {

		double amount = Double.valueOf(textFieldAmountFD.getText());
		double amountper = Double.valueOf(textFieldLoanPerstantege.getText());
		double loanamt = (amountper / 100) * amount;
		textFieldLoanAmt.setText(String.valueOf(loanamt));

		double amount1 = Double.valueOf(textFieldLoanAmt.getText());
		double intest2 = Double.valueOf(textFieldInterst.getText());
		double totla = amount1 * intest2 / 100.0;
		textFieldInterstAmt.setText(String.valueOf(totla));
		textFieldTotal.setText(String.valueOf(amount1 + totla));

	}

	public void DurationIncrement() {
		try {

			conn = (Connection) DBConnection.getConnection();
			String show = "select * from banksystem.fd order by SrNoMaster;";
			ps = conn.prepareStatement(show);
			rs = ps.executeQuery();
			while (rs.next()) {
				String date1 = rs.getString("UpdateDate");
				String acno = rs.getString("AccountNumber");
				double amount = rs.getDouble("Amount");
				double interst = rs.getDouble("Interes");
				double maturityamt = rs.getDouble("Total");
				double day = rs.getDouble("Days");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date datecur = new Date();
				String date2 = (String) sdf.format(datecur);
				Date date11 = sdf.parse(date1);
				Date date12 = sdf.parse(date2);
				long diff = date12.getTime() - date11.getTime();
				long dayscount = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

				if (dayscount > day) {
					SimpleDateFormat sdf44 = new SimpleDateFormat("yyyy-MM-dd");
					Date datecur44 = new Date();
					String sysdate44 = (String) sdf44.format(datecur);
					java.sql.Date sqlDate = new java.sql.Date(datecur44.getTime());

					double interscount = maturityamt * interst / 100;
					double aginmatuamtsum1 = maturityamt + interscount;

					double amountagin = aginmatuamtsum1 * interst / 100;
					double sumaamount = aginmatuamtsum1 + amountagin;

					String one = dff.format(aginmatuamtsum1);
					double aginmatuamtsum = Double.valueOf(one);

					String two = dff.format(sumaamount);
					double allsumamount = Double.valueOf(two);

					String upsavin = "UPDATE banksystem.fd set  UpdateDate='" + (java.sql.Date) sqlDate + "', Amount='"
							+ aginmatuamtsum + "', Interes='" + interst + "',IntsetAmt='" + amountagin + "', Total='"
							+ allsumamount + "' where AccountNumber='" + acno + "'";
					ps1 = conn.prepareStatement(upsavin);
					ps1.executeUpdate();

				}

			}

		} catch (Exception es) {
			System.out.println(es.getMessage());
		} finally {
			try {
				rs.close();
				ps.close();
				ps1.close();
				conn.close();
			} catch (Exception ew) {
				System.out.println(ew.getMessage());
			}
		}

	}

	public void totalamount() {
		int rowsCount = tableFDA_C.getRowCount();
		double sum = 0;

		for (int i = 0; i < rowsCount; i++) {
			sum = sum + Double.valueOf(tableFDA_C.getValueAt(i, 8).toString());
		}
		textFieldTotalAmtB.setText(String.valueOf(dff.format(sum)));

	}
}
