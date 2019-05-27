package Pages;

import java.awt.BorderLayout;
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

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;


import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Cursor;
import javax.swing.JList;
import java.awt.event.MouseMotionAdapter;

public class SchemeA_C extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldAddress;
	private JTextField textFieldAge;
	private JTextField textFieldContactNo;
	private JTextField textFieldAmt;
	private JTable tableSchemA_C;
	private JTextField textFieldSchmePer;
	private JTextField textFieldSchemeAmt;
	private JTextField textFieldTotalAmt;
	private JTextField textFieldSchemeTypeOther;
	private JTextField textFieldDuration;
	private JTextField textFieldDays;
	public ResultSet rs;
	public static String accno;

	public DefaultTableModel model1;

	public JTextArea textAreaNotes;

	public Connection conn;
	public PreparedStatement ps;
	public PreparedStatement ps1;
	public PreparedStatement ps2;
	public String val1;
	public Statement st;
	public JDateChooser dateChooserloangpage;
	java.util.Date dt1, dt2;
	private JTextField textFieldSrNo;
	private JTextField textFieldSrNoMaster;
	private JTextField textFieldNomeny;
	private JTextField textFieldNomenyOther;

	public JComboBox comboBoxSchType;

	public static double countday;
	public static double daycount;
	public static int srnomaster;
	public static double intrestper;
	public static double amount;
	public static double totalamount;
	public static double intersamt;
	public static String srnofd;
	public DecimalFormat df = new DecimalFormat("#.##");
	private JTextField textFieldOtherAmount;
	private JTextField textFieldAccountNO;
	public static String acno;
	public JDateChooser dateChooserSchemeA_C;

	public JCheckBox checkBoxDuration;
	public JButton btnSave;
	public JScrollPane scrollPane_1;
	public JList<String> listname;
	public JScrollPane scrollPaneName;
	public String[] data = new String[1000];

	public JComboBox comboBoxDuration;
	private JTextField textFieldTotalAmtB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SchemeA_C frame = new SchemeA_C();
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
	public SchemeA_C() {
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
			public void windowOpened(WindowEvent arg0) {
				textFieldName.requestFocus();
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

		JLabel lblSchemeAc = new JLabel("Scheme A/C");
		lblSchemeAc.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSchemeAc.setBounds(22, 11, 276, 42);
		panel.add(lblSchemeAc);

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
		dateChooserSchemeA_C = new JDateChooser();
		dateChooserSchemeA_C.setDateFormatString("yyyy-MM-dd");
		dateChooserSchemeA_C.setBounds(822, 42, 214, 20);
		dateChooserSchemeA_C.setDate(date);

		panel.add(dateChooserSchemeA_C);
		textFieldSrNo = new JTextField();
		textFieldSrNo.setVisible(false);
		textFieldSrNo.setEnabled(false);
		textFieldSrNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldSrNo.setColumns(10);
		textFieldSrNo.setBounds(191, 25, 199, 23);
		panel.add(textFieldSrNo);

		textFieldSrNoMaster = new JTextField();
		textFieldSrNoMaster.setVisible(false);
		textFieldSrNoMaster.setEnabled(false);
		textFieldSrNoMaster.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldSrNoMaster.setColumns(10);
		textFieldSrNoMaster.setBounds(415, 25, 199, 23);
		increment();
		panel.add(textFieldSrNoMaster);

		JLabel lblAccountNumber = new JLabel("Account Number:");
		lblAccountNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAccountNumber.setBounds(10, 53, 97, 17);
		panel.add(lblAccountNumber);

		textFieldAccountNO = new JTextField();
		textFieldAccountNO.setEditable(false);
		textFieldAccountNO.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldAccountNO.setColumns(10);
		textFieldAccountNO.setBounds(129, 50, 199, 23);
		increment();

		panel.add(textFieldAccountNO);

		JButton button_5 = new JButton("");
		button_5.setIcon(new ImageIcon(SchemeA_C.class.getResource("/ImageButtonIcon/clac.png")));
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculatorGUI cl = new CalculatorGUI("Bank System");
				cl.setSize(400, 350);
				cl.setFocusable(true);
				cl.setLocation(830, 0);
				cl.setVisible(true);
			}
		});
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
		panel_1.setBounds(0, 86, 1154, 201);
		contentPane.add(panel_1);

		JLabel label = new JLabel("Name:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(10, 9, 50, 17);
		panel_1.add(label);

		textFieldName = new JTextField();
		textFieldName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == 40) {
					listname.requestFocus();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				try {

					conn = DBConnection.getConnection();
					String query = "select * from banksystem.schemea_c where Name like '" + textFieldName.getText()
							+ "%'";
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

					comboBoxDuration.setVisible(false);
					checkBoxDuration.setVisible(false);
					comboBoxSchType.setVisible(false);
					textFieldNomeny.setVisible(false);
					scrollPane_1.setVisible(false);

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
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldName.setColumns(10);
		textFieldName.setBounds(91, 9, 199, 23);
		panel_1.add(textFieldName);

		JLabel label_2 = new JLabel("Address:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(295, 12, 81, 17);
		panel_1.add(label_2);

		textFieldAddress = new JTextField();
		textFieldAddress.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				scrollPaneName.setVisible(false);
				listname.setVisible(false);

				comboBoxDuration.setVisible(true);
				checkBoxDuration.setVisible(true);
				comboBoxSchType.setVisible(true);
				textFieldNomeny.setVisible(true);
				scrollPane_1.setVisible(true);
			}
		});
		textFieldAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(359, 9, 209, 23);
		panel_1.add(textFieldAddress);

		JLabel label_3 = new JLabel("Age:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(580, 12, 35, 17);
		panel_1.add(label_3);

		textFieldAge = new JTextField();
		textFieldAge.addKeyListener(new KeyAdapter() {
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
		textFieldAge.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(643, 9, 199, 23);
		panel_1.add(textFieldAge);

		JLabel label_4 = new JLabel("Contact No:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(852, 9, 71, 17);
		panel_1.add(label_4);

		textFieldContactNo = new JTextField();
		textFieldContactNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}

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
		textFieldContactNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldContactNo.setColumns(10);
		textFieldContactNo.setBounds(914, 9, 216, 23);
		panel_1.add(textFieldContactNo);

		JLabel label_5 = new JLabel("Note:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setBounds(10, 133, 65, 17);
		panel_1.add(label_5);

		JLabel lblDoubleAmount = new JLabel("Amount :");
		lblDoubleAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDoubleAmount.setBounds(580, 43, 55, 17);
		panel_1.add(lblDoubleAmount);

		textFieldAmt = new JTextField();
		textFieldAmt.addKeyListener(new KeyAdapter() {
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
		textFieldAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAmt.setColumns(10);
		textFieldAmt.setBounds(643, 40, 199, 23);
		panel_1.add(textFieldAmt);

		JLabel lblShemeType = new JLabel("Scheme Type:");
		lblShemeType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblShemeType.setBounds(10, 77, 71, 17);
		panel_1.add(lblShemeType);

		JLabel lblDurat = new JLabel("Duration:\r\n");
		lblDurat.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDurat.setBounds(10, 44, 91, 17);
		panel_1.add(lblDurat);

		JLabel lblSchemeAmt = new JLabel("Scheme %:");
		lblSchemeAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSchemeAmt.setBounds(852, 43, 55, 17);
		panel_1.add(lblSchemeAmt);

		textFieldSchmePer = new JTextField();
		textFieldSchmePer.addKeyListener(new KeyAdapter() {
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
		textFieldSchmePer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldSchmePer.setColumns(10);
		textFieldSchmePer.setBounds(914, 40, 216, 23);
		panel_1.add(textFieldSchmePer);

		JLabel label_6 = new JLabel("Scheme Amt:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(580, 74, 71, 17);
		panel_1.add(label_6);

		textFieldSchemeAmt = new JTextField();
		textFieldSchemeAmt.setForeground(Color.RED);
		textFieldSchemeAmt.setEditable(false);
		textFieldSchemeAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldSchemeAmt.setColumns(10);
		textFieldSchemeAmt.setBounds(643, 71, 199, 23);
		panel_1.add(textFieldSchemeAmt);

		JLabel lblTotal = new JLabel("Total:\r\n");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotal.setBounds(852, 68, 106, 17);
		panel_1.add(lblTotal);

		textFieldTotalAmt = new JTextField();
		textFieldTotalAmt.setForeground(Color.RED);
		textFieldTotalAmt.setEditable(false);
		textFieldTotalAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldTotalAmt.setColumns(10);
		textFieldTotalAmt.setBounds(914, 71, 216, 23);
		panel_1.add(textFieldTotalAmt);

		comboBoxSchType = new JComboBox();
		comboBoxSchType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String st = comboBoxSchType.getSelectedItem().toString();
				daycount1(st);
			}

			private void daycount1(String st) {
				textFieldSchemeTypeOther.setText(st);

			}
		});
		comboBoxSchType.setModel(new DefaultComboBoxModel(new String[] { "Select", "A", "B", "C", "D", "E", "F" }));
		comboBoxSchType.setBounds(91, 75, 199, 23);
		panel_1.add(comboBoxSchType);

		JCheckBox checkBoxSchType = new JCheckBox("");
		checkBoxSchType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click1();
			}

			private void click1() {
				if (!checkBoxSchType.isSelected()) {
					comboBoxSchType.setEnabled(true);
					textFieldSchemeTypeOther.setEnabled(false);

				} else {
					comboBoxSchType.setEnabled(false);
					textFieldSchemeTypeOther.setEnabled(true);

				}

			}
		});
		checkBoxSchType.setBounds(295, 77, 21, 23);
		panel_1.add(checkBoxSchType);

		JLabel label_7 = new JLabel("Other:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(322, 77, 44, 17);
		panel_1.add(label_7);

		textFieldSchemeTypeOther = new JTextField();
		textFieldSchemeTypeOther.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldSchemeTypeOther.setText("");
			}
		});
		textFieldSchemeTypeOther.setText("Other");
		textFieldSchemeTypeOther.setEnabled(false);
		textFieldSchemeTypeOther.setBounds(359, 75, 209, 20);
		panel_1.add(textFieldSchemeTypeOther);
		textFieldSchemeTypeOther.setColumns(10);

		comboBoxDuration = new JComboBox();
		comboBoxDuration.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					String st = comboBoxDuration.getSelectedItem().toString();
					daycount(st);
				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		comboBoxDuration.setModel(new DefaultComboBoxModel(new String[] { "Select", "1 Month", "3 Month\t", "6 Month\t",
				"9 Month", "1 Year", "2 Year\t", "3 Year", "4 Year", "5 Year" }));
		comboBoxDuration.setBounds(91, 42, 162, 23);
		panel_1.add(comboBoxDuration);

		checkBoxDuration = new JCheckBox("");
		checkBoxDuration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click();
			}

			private void click() {
				if (!checkBoxDuration.isSelected()) {
					comboBoxDuration.setEnabled(true);
					textFieldDuration.setEnabled(false);

				} else {
					comboBoxDuration.setEnabled(false);
					textFieldDuration.setEnabled(true);

				}

			}
		});
		checkBoxDuration.setBounds(259, 42, 21, 23);
		panel_1.add(checkBoxDuration);

		JLabel label_8 = new JLabel("Other:");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_8.setBounds(295, 48, 44, 17);
		panel_1.add(label_8);

		textFieldDuration = new JTextField();
		textFieldDuration.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String st = textFieldDuration.getText();
				daycount(st);
			}
		});
		textFieldDuration.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldDuration.setText("");
			}
		});
		textFieldDuration.setText("other e.g 3 Month,3 Year");
		textFieldDuration.setEnabled(false);
		textFieldDuration.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldDuration.setColumns(10);
		textFieldDuration.setBounds(359, 42, 209, 23);
		panel_1.add(textFieldDuration);

		textFieldDays = new JTextField();
		textFieldDays.setVisible(false);
		textFieldDays.setEnabled(false);
		textFieldDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldDays.setColumns(10);
		textFieldDays.setBounds(1055, 75, 75, 23);
		panel_1.add(textFieldDays);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(91, 133, 477, 63);
		panel_1.add(scrollPane_1);

		textAreaNotes = new JTextArea();
		textAreaNotes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == 9) {
					btnSave.requestFocus();
				}
			}
		});
		scrollPane_1.setViewportView(textAreaNotes);

		JLabel label_9 = new JLabel("Nomeny:");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_9.setBounds(10, 105, 106, 17);
		panel_1.add(label_9);

		textFieldNomeny = new JTextField();
		textFieldNomeny.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldNomeny.setColumns(10);
		textFieldNomeny.setBounds(91, 105, 199, 23);
		panel_1.add(textFieldNomeny);

		JLabel label_10 = new JLabel("Relation:");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_10.setBounds(295, 105, 74, 17);
		panel_1.add(label_10);

		JComboBox comboBoxNomeny = new JComboBox();
		comboBoxNomeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = comboBoxNomeny.getSelectedItem().toString();
				textFieldNomenyOther.setText("");
				textFieldNomenyOther.setText(name);
			}
		});
		comboBoxNomeny.setModel(new DefaultComboBoxModel(new String[] { "   Select", "   Father", "   Mother",
				"   Paternal Grandfather", "    Maternal Grandfather ", "    Maternal Grandmother", "    Brother",
				"    Brother's Wife", "    Elder Brother", "    Younger Brother", "    Husband's sister", "    Sister",
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
		comboBoxNomeny.setBounds(359, 105, 191, 23);
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
		checkBoxNomeny.setBounds(556, 105, 27, 23);
		panel_1.add(checkBoxNomeny);

		JLabel label_11 = new JLabel("Other:");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_11.setBounds(590, 111, 53, 17);
		panel_1.add(label_11);

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
		textFieldNomenyOther.setBounds(643, 105, 199, 23);
		panel_1.add(textFieldNomenyOther);

		JLabel lblOtherAmount = new JLabel("Other Amount :");
		lblOtherAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOtherAmount.setBounds(850, 108, 91, 17);
		panel_1.add(lblOtherAmount);

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
		textFieldOtherAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldOtherAmount.setColumns(10);
		textFieldOtherAmount.setBounds(931, 105, 199, 23);
		panel_1.add(textFieldOtherAmount);

		scrollPaneName = new JScrollPane();
		scrollPaneName.setVisible(false);
		scrollPaneName.setBounds(91, 30, 199, 166);
		panel_1.add(scrollPaneName);

		listname = new JList<String>();
		listname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldName.setText(listname.getSelectedValue());
					String accountnotop = textFieldName.getText();

					scrollPaneName.setVisible(false);
					listname.setVisible(false);

					comboBoxDuration.setVisible(true);
					checkBoxDuration.setVisible(true);
					comboBoxSchType.setVisible(true);
					textFieldNomeny.setVisible(true);
					scrollPane_1.setVisible(true);

					try {
						conn = DBConnection.getConnection();
						String query = "Select * from banksystem.schemea_c where Name=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldName.getText());
						rs = ps.executeQuery();
						while (rs.next()) {

							String Srnomaster = rs.getString("SrNoMaster");
							textFieldSrNoMaster.setText(Srnomaster);

							String UpdateAmtDate = rs.getString("Date");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date dd = sdf.parse(UpdateAmtDate);
							dateChooserSchemeA_C.setDate(dd);
							String AccountNumber = rs.getString("Name");
							textFieldName.setText(AccountNumber);
							String Name = rs.getString("Address");
							textFieldAddress.setText(Name);
							String Address = rs.getString("Age");
							textFieldAge.setText(Address);
							String Age = rs.getString("ContactNo");
							textFieldContactNo.setText(Age);
							String ContactNo = rs.getString("Amount");
							textFieldAmt.setText(ContactNo);
							String LoanAgainst = rs.getString("SchPer");
							textFieldSchmePer.setText(LoanAgainst);
							String Duration = rs.getString("SchAmt");
							textFieldSchemeAmt.setText(Duration);
							String Days = rs.getString("Total");
							textFieldTotalAmt.setText(Days);

							String Amount = rs.getString("SchType");
							textFieldSchemeTypeOther.setText(Amount);
							String Interst = rs.getString("Duration");
							textFieldDuration.setText(Interst);

							String InstersAmt = rs.getString("Days");
							textFieldDays.setText(InstersAmt);

							String Total = rs.getString("Note");
							textAreaNotes.setText(Total);
							String ShareInterst = rs.getString("NomenyName");
							textFieldNomeny.setText(ShareInterst);

							String ShareInterstAmt = rs.getString("NomenyRelation");
							textFieldNomenyOther.setText(ShareInterstAmt);

							String FormFee = rs.getString("AccountNo");
							textFieldAccountNO.setText(FormFee);

							String StationaryAmt = rs.getString("OtherAmount");
							textFieldOtherAmount.setText(StationaryAmt);

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
		listname.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldName.setText(listname.getSelectedValue());
				String accountnotop = textFieldName.getText();

				scrollPaneName.setVisible(false);
				listname.setVisible(false);

				comboBoxDuration.setVisible(true);
				checkBoxDuration.setVisible(true);
				comboBoxSchType.setVisible(true);
				textFieldNomeny.setVisible(true);
				scrollPane_1.setVisible(true);

				try {
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.schemea_c where Name=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldName.getText());
					rs = ps.executeQuery();
					while (rs.next()) {

						String Srnomaster = rs.getString("SrNoMaster");
						textFieldSrNoMaster.setText(Srnomaster);

						String UpdateAmtDate = rs.getString("Date");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date dd = sdf.parse(UpdateAmtDate);
						dateChooserSchemeA_C.setDate(dd);
						String AccountNumber = rs.getString("Name");
						textFieldName.setText(AccountNumber);
						String Name = rs.getString("Address");
						textFieldAddress.setText(Name);
						String Address = rs.getString("Age");
						textFieldAge.setText(Address);
						String Age = rs.getString("ContactNo");
						textFieldContactNo.setText(Age);
						String ContactNo = rs.getString("Amount");
						textFieldAmt.setText(ContactNo);
						String LoanAgainst = rs.getString("SchPer");
						textFieldSchmePer.setText(LoanAgainst);
						String Duration = rs.getString("SchAmt");
						textFieldSchemeAmt.setText(Duration);
						String Days = rs.getString("Total");
						textFieldTotalAmt.setText(Days);

						String Amount = rs.getString("SchType");
						textFieldSchemeTypeOther.setText(Amount);
						String Interst = rs.getString("Duration");
						textFieldDuration.setText(Interst);

						String InstersAmt = rs.getString("Days");
						textFieldDays.setText(InstersAmt);

						String Total = rs.getString("Note");
						textAreaNotes.setText(Total);
						String ShareInterst = rs.getString("NomenyName");
						textFieldNomeny.setText(ShareInterst);

						String ShareInterstAmt = rs.getString("NomenyRelation");
						textFieldNomenyOther.setText(ShareInterstAmt);

						String FormFee = rs.getString("AccountNo");
						textFieldAccountNO.setText(FormFee);

						String StationaryAmt = rs.getString("OtherAmount");
						textFieldOtherAmount.setText(StationaryAmt);

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
		listname.setVisible(false);
		scrollPaneName.setViewportView(listname);
		
		JLabel lblEgMonth = new JLabel("e.g. 1 Month, 1M ,1 Year,1 Y ... etc");
		lblEgMonth.setForeground(Color.RED);
		lblEgMonth.setBounds(374, 62, 194, 14);
		panel_1.add(lblEgMonth);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(0, 298, 1154, 60);
		contentPane.add(panel_2);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String in = "insert into banksystem.schemea_c values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
					ps = conn.prepareStatement(in);
					ps.setInt(1, Integer.parseInt(textFieldSrNoMaster.getText()));
					ps.setInt(2, Integer.parseInt(textFieldSrNoMaster.getText()));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserSchemeA_C.getDate();
					String date = (String) sdf.format(dateChooserSchemeA_C.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(3, (java.sql.Date) d);
					ps.setString(4, textFieldName.getText());
					ps.setString(5, textFieldAddress.getText());
					ps.setDouble(6, Double.valueOf(textFieldAge.getText()));
					ps.setString(7, textFieldContactNo.getText());
					ps.setDouble(8, Double.valueOf(textFieldAmt.getText()));
					ps.setDouble(9, Double.valueOf(textFieldSchmePer.getText()));
					ps.setDouble(10, Double.valueOf(textFieldSchemeAmt.getText()));
					ps.setDouble(11, Double.valueOf(textFieldTotalAmt.getText()));
					ps.setString(12, textFieldSchemeTypeOther.getText());
					ps.setString(13, textFieldDuration.getText());
					ps.setString(14, textFieldDays.getText());
					ps.setString(15, textAreaNotes.getText());
					ps.setString(16, textFieldNomeny.getText());
					ps.setString(17, textFieldNomenyOther.getText());
					ps.setString(18, textFieldAccountNO.getText());
					ps.setDouble(19, Double.valueOf(textFieldOtherAmount.getText()));
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
						dt1 = dateChooserSchemeA_C.getDate();
						String date = (String) sdf.format(dateChooserSchemeA_C.getDate());
						java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
						ps1.setDate(2, (java.sql.Date) d);
						ps1.setString(3, textFieldName.getText());
						ps1.setString(4, textFieldAccountNO.getText());
						ps1.setDouble(5, Double.valueOf(textFieldOtherAmount.getText()));
						ps1.setString(6, textAreaNotes.getText());
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

				String data0 = textFieldSrNoMaster.getText();
				String data1 = textFieldSrNo.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				dt1 = dateChooserSchemeA_C.getDate();
				String data2 = (String) sdf.format(dateChooserSchemeA_C.getDate());
				String data3 = textFieldName.getText();
				String data4 = textFieldAccountNO.getText();
				String data5 = textFieldAmt.getText();
				String data6 = textFieldSchmePer.getText();
				String data7 = textFieldSchemeAmt.getText();
				String data8 = textFieldTotalAmt.getText();
				String data9 = textFieldOtherAmount.getText();
				String data10 = textFieldDuration.getText();
				String data11 = textFieldDays.getText();

				String data12 = textFieldAddress.getText();
				String data13 = textFieldAge.getText();
				String data14 = textFieldContactNo.getText();

				String data15 = textFieldSchemeTypeOther.getText();

				String data16 = textAreaNotes.getText().toString();
				String data17 = textFieldNomeny.getText();
				String data18 = textFieldNomenyOther.getText();

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12, data13, data14, data15, data16, data17, data18 };
				model1 = (DefaultTableModel) tableSchemA_C.getModel();
				model1.addRow(row);

			}
		});
		btnSave.setIcon(new ImageIcon(SchemeA_C.class.getResource("/ImageButtonIcon/Save.png")));
		btnSave.setHorizontalAlignment(SwingConstants.LEADING);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBounds(216, 11, 115, 38);
		panel_2.add(btnSave);

		JButton button_1 = new JButton("Update");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String up = "UPDATE banksystem.schemea_c  set Date=?,Name=?, Address=?, Age=?, ContactNo=?, Amount=?, SchPer=?, SchAmt=?, Total=?, SchType=?, Duration=?,Days=?,Note=?,NomenyName=?,NomenyRelation=?,AccountNo=?,OtherAmount=?  where SrNoMaster=?";
					ps = conn.prepareStatement(up);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserSchemeA_C.getDate();
					String date = (String) sdf.format(dateChooserSchemeA_C.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(1, (java.sql.Date) d);
					ps.setString(2, textFieldName.getText());
					ps.setString(3, textFieldAddress.getText());
					ps.setDouble(4, Double.valueOf(textFieldAge.getText()));
					ps.setString(5, textFieldContactNo.getText());
					ps.setDouble(6, Double.valueOf(textFieldAmt.getText()));
					ps.setDouble(7, Double.valueOf(textFieldSchmePer.getText()));
					ps.setDouble(8, Double.valueOf(textFieldSchemeAmt.getText()));
					ps.setDouble(9, Double.valueOf(textFieldTotalAmt.getText()));
					ps.setString(10, textFieldSchemeTypeOther.getText());
					ps.setString(11, textFieldDuration.getText());
					ps.setString(12, textFieldDays.getText());
					ps.setString(13, textAreaNotes.getText());
					ps.setString(14, textFieldNomeny.getText());
					ps.setString(15, textFieldNomenyOther.getText());
					ps.setString(16, textFieldAccountNO.getText());
					ps.setDouble(17, Double.valueOf(textFieldOtherAmount.getText()));
					ps.setInt(18, Integer.parseInt(textFieldSrNoMaster.getText()));

					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are update.");
						reset();
						dispose();
						SchemeA_C sc = new SchemeA_C();
						sc.setUndecorated(true);
						sc.setVisible(true);
						increment();
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
		button_1.setIcon(new ImageIcon(SchemeA_C.class.getResource("/ImageButtonIcon/update.png")));
		button_1.setToolTipText("");
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBounds(473, 11, 126, 38);
		panel_2.add(button_1);

		JButton button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String de = "delete from banksystem.schemea_c where SrNoMaster=?";
					ps = conn.prepareStatement(de);
					ps.setInt(1, Integer.parseInt(textFieldSrNoMaster.getText()));
					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are deleted.");
						reset();
						dispose();
						SchemeA_C fd = new SchemeA_C();
						fd.setUndecorated(true);
						fd.setVisible(true);
						increment();
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
		button_2.setIcon(new ImageIcon(SchemeA_C.class.getResource("/ImageButtonIcon/delete.jpg")));
		button_2.setHorizontalAlignment(SwingConstants.LEADING);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_2.setBounds(606, 11, 115, 38);
		panel_2.add(button_2);

		JButton button_3 = new JButton("Reset");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		button_3.setIcon(new ImageIcon(SchemeA_C.class.getResource("/ImageButtonIcon/Reset5.jpg")));
		button_3.setHorizontalAlignment(SwingConstants.LEADING);
		button_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_3.setBounds(731, 11, 115, 38);
		panel_2.add(button_3);

		JButton button_4 = new JButton("Exit");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Welcome.frame.enable();
			}
		});
		button_4.setIcon(new ImageIcon(SchemeA_C.class.getResource("/ImageButtonIcon/Exit.png")));
		button_4.setHorizontalAlignment(SwingConstants.LEADING);
		button_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_4.setBounds(856, 11, 115, 38);
		panel_2.add(button_4);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldSchemeAmt.setEditable(true);
				textFieldTotalAmt.setEditable(true);
			}
		});
		btnEdit.setIcon(new ImageIcon(SchemeA_C.class.getResource("/ImageButtonIcon/edit.png")));
		btnEdit.setToolTipText("");
		btnEdit.setHorizontalAlignment(SwingConstants.LEADING);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(341, 11, 126, 38);
		panel_2.add(btnEdit);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(0, 369, 1154, 275);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 1134, 253);
		panel_3.add(scrollPane);

		tableSchemA_C = new JTable();
		tableSchemA_C.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableSchemA_C.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int i = tableSchemA_C.getSelectedRow();
					model1 = (DefaultTableModel) tableSchemA_C.getModel();
					textFieldSrNoMaster.setText(model1.getValueAt(i, 1).toString());
					textFieldSrNo.setText(model1.getValueAt(i, 2).toString());
					dateChooserSchemeA_C.setDateFormatString(model1.getValueAt(i, 3).toString());
					textFieldName.setText(model1.getValueAt(i, 4).toString());
					textFieldAccountNO.setText(model1.getValueAt(i, 5).toString());
					textFieldAmt.setText(model1.getValueAt(i, 6).toString());
					textFieldSchmePer.setText(model1.getValueAt(i, 7).toString());
					textFieldSchemeAmt.setText(model1.getValueAt(i, 8).toString());
					textFieldTotalAmt.setText(model1.getValueAt(i, 9).toString());
					textFieldOtherAmount.setText(model1.getValueAt(i, 10).toString());
					textFieldDuration.setText(model1.getValueAt(i, 11).toString());

					textFieldDays.setText(model1.getValueAt(i, 12).toString());
					textFieldAddress.setText(model1.getValueAt(i, 13).toString());
					textFieldAge.setText(model1.getValueAt(i, 14).toString());
					textFieldContactNo.setText(model1.getValueAt(i, 15).toString());

					textFieldSchemeTypeOther.setText(model1.getValueAt(i, 16).toString());

					textAreaNotes.setText(model1.getValueAt(i, 17).toString());
					textFieldNomeny.setText(model1.getValueAt(i, 18).toString());
					textFieldNomenyOther.setText(model1.getValueAt(i, 19).toString());
				} catch (Exception ess) {
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
				int rows = tableSchemA_C.getRowCount() - 1;

				for (int i = 0; i < tableSchemA_C.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableSchemA_C.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableSchemA_C.getSelectedRow();
						int col = tableSchemA_C.getSelectedColumn();
						tableSchemA_C.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableSchemA_C.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableSchemA_C.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableSchemA_C.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableSchemA_C.setValueAt((Object) s, 0, 0);
							tableSchemA_C.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableSchemA_C.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableSchemA_C.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Select", "SrNoMaster", "SrNo", "Date", "Name", "Account No", "Amount", "Scheme %",
						"Scheme Amt", "Total", "Other Char.", "Duration", "Days", "Address", "Age", "Contact No",
						"Scheme Type", "Notes", "Nomeny Name", "Nomeny Relation" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableSchemA_C.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableSchemA_C.getColumnModel().getColumn(0).setMinWidth(50);
		tableSchemA_C.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableSchemA_C.getColumnModel().getColumn(1).setMinWidth(0);
		tableSchemA_C.getColumnModel().getColumn(1).setMaxWidth(0);
		tableSchemA_C.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableSchemA_C.getColumnModel().getColumn(2).setMinWidth(50);
		tableSchemA_C.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableSchemA_C.getColumnModel().getColumn(3).setMinWidth(80);
		tableSchemA_C.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableSchemA_C.getColumnModel().getColumn(4).setMinWidth(150);
		tableSchemA_C.getColumnModel().getColumn(6).setPreferredWidth(80);
		tableSchemA_C.getColumnModel().getColumn(6).setMinWidth(80);
		tableSchemA_C.getColumnModel().getColumn(7).setPreferredWidth(80);
		tableSchemA_C.getColumnModel().getColumn(7).setMinWidth(80);
		tableSchemA_C.getColumnModel().getColumn(8).setPreferredWidth(80);
		tableSchemA_C.getColumnModel().getColumn(8).setMinWidth(80);
		tableSchemA_C.getColumnModel().getColumn(9).setPreferredWidth(80);
		tableSchemA_C.getColumnModel().getColumn(9).setMinWidth(80);
		tableSchemA_C.getColumnModel().getColumn(11).setPreferredWidth(80);
		tableSchemA_C.getColumnModel().getColumn(11).setMinWidth(80);
		tableSchemA_C.getColumnModel().getColumn(12).setPreferredWidth(80);
		tableSchemA_C.getColumnModel().getColumn(12).setMinWidth(80);
		tableSchemA_C.getColumnModel().getColumn(13).setPreferredWidth(150);
		tableSchemA_C.getColumnModel().getColumn(13).setMinWidth(150);
		tableSchemA_C.getColumnModel().getColumn(15).setPreferredWidth(80);
		tableSchemA_C.getColumnModel().getColumn(15).setMinWidth(80);
		tableSchemA_C.getColumnModel().getColumn(16).setPreferredWidth(80);
		tableSchemA_C.getColumnModel().getColumn(16).setMinWidth(80);
		tableSchemA_C.getColumnModel().getColumn(17).setPreferredWidth(150);
		tableSchemA_C.getColumnModel().getColumn(17).setMinWidth(150);
		tableSchemA_C.getColumnModel().getColumn(18).setPreferredWidth(80);
		tableSchemA_C.getColumnModel().getColumn(18).setMinWidth(80);
		tableSchemA_C.getColumnModel().getColumn(19).setPreferredWidth(80);
		tableSchemA_C.getColumnModel().getColumn(19).setMinWidth(80);
		showdata();
		scrollPane.setViewportView(tableSchemA_C);

		JButton button_6 = new JButton("Report");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for (int i = 0; i <= tableSchemA_C.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableSchemA_C.getValueAt(i, 0).toString());
						if (che) {
							String accno4 = String.valueOf(tableSchemA_C.getValueAt(i, 5).toString());

							java.io.InputStream file = getClass().getResourceAsStream("/Reports/Scheme.jrxml");
							accno = String.valueOf(tableSchemA_C.getValueAt(i, 5).toString());
							HashMap para = new HashMap();
							para.put("Acno", accno4);
							allinonereport r = new allinonereport("Schme Report", file, para);
							break;
						}

					}
				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		button_6.setIcon(new ImageIcon(SchemeA_C.class.getResource("/ImageButtonIcon/Report4d.png")));
		button_6.setHorizontalAlignment(SwingConstants.LEADING);
		button_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_6.setBounds(152, 655, 139, 38);
		contentPane.add(button_6);

		JButton button = new JButton("Withdral");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double amount = Double.valueOf(textFieldSchemeAmt.getText());
				if (amount <= 0) {
					JOptionPane.showMessageDialog(null, "account is suspend mode because balance total withdrawal.");

				} else {

					try {

						for (int i = 0; i <= tableSchemA_C.getRowCount(); i++) {
							Boolean che = Boolean.valueOf(tableSchemA_C.getValueAt(i, 0).toString());
							if (che) {
								String Srno = tableSchemA_C.getValueAt(i, 1).toString();
								srnofd = Srno;
								acno = tableSchemA_C.getValueAt(i, 5).toString();
								String datadate = tableSchemA_C.getValueAt(i, 3).toString();
								countday = Double.valueOf(tableSchemA_C.getValueAt(i, 11).toString());

								DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								Date date1 = new Date();

								DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
								Date date2 = sdf1.parse(datadate);

								long diff = date1.getTime() - date2.getTime();
								double day = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
								daycount = day;

								if (countday >= daycount) {

									SchPremature shp = new SchPremature();
									shp.setVisible(true);

								} else if (countday < daycount) {

									SchMature sm = new SchMature();
									sm.setVisible(true);

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
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(10, 655, 132, 38);
		contentPane.add(button);

		JLabel label_12 = new JLabel("Total Amount:");
		label_12.setBounds(882, 656, 109, 14);
		contentPane.add(label_12);

		textFieldTotalAmtB = new JTextField();
		textFieldTotalAmtB.setForeground(Color.RED);
		textFieldTotalAmtB.setEditable(false);
		textFieldTotalAmtB.setColumns(10);
		textFieldTotalAmtB.setBounds(990, 653, 132, 20);
		contentPane.add(textFieldTotalAmtB);
	}

	public void daycount(String str) {
		try
		{
		textFieldDuration.setText(str);
		String number = "";
		String letter = "";
		String symbol = "";
		String firstLetter;

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
		catch(Exception w)
		{
			w.printStackTrace();
		}
	}

	public void increment() {
		try {

			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("select max(SrNoMaster) from banksystem.schemea_c;");
			rs = ps.executeQuery();
			while (rs.next()) {
				int val = (rs.getInt(1) + 1);
				val1 = String.valueOf(val);
			}
			textFieldSrNoMaster.setText(val1);
			textFieldSrNo.setText(val1);
			textFieldAccountNO.setText("Sc" + val1);

			SimpleDateFormat insdf = new SimpleDateFormat("yyyy-MM-dd");
			Date indate = new Date();
			String instringdate = insdf.format(indate);
			dateChooserSchemeA_C.setDate(insdf.parse(instringdate));

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
		textAreaNotes.setText("");
		textFieldAddress.setText("");
		textFieldAge.setText("0");
		textFieldAmt.setText("0");
		textFieldContactNo.setText("");
		textFieldDays.setText("");
		textFieldDuration.setText("");
		textFieldName.setText("");
		textFieldSchemeAmt.setText("");
		textFieldSchemeTypeOther.setText("");
		textFieldSchmePer.setText("0");
		textFieldTotalAmt.setText("0");
		textFieldNomeny.setText("");
		textFieldNomenyOther.setText("");
		textFieldOtherAmount.setText("0");

	}

	public void showdata() {
		try {
			conn = DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.schemea_c order by SrNoMaster;";
			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("SrNo");
				String data2 = rs.getString("Date");
				String data3 = rs.getString("Name");
				String data4 = rs.getString("AccountNo");
				double amout = rs.getDouble("Amount");
				String data5 = df.format(amout);
				double schper = rs.getDouble("SchPer");
				String data6 = df.format(schper);
				double schamt = rs.getDouble("SchAmt");
				String data7 = df.format(schamt);
				double toatalamt = rs.getDouble("Total");
				String data8 = df.format(toatalamt);

				double OtherAmount = rs.getDouble("OtherAmount");
				String data9 = df.format(OtherAmount);

				String data10 = rs.getString("Duration");
				String data11 = rs.getString("Days");

				String data12 = rs.getString("Address");
				String data13 = rs.getString("Age");
				String data14 = rs.getString("ContactNo");

				String data15 = rs.getString("SchType");

				String data16 = rs.getString("Note");
				String data17 = rs.getString("NomenyName");
				String data18 = rs.getString("NomenyRelation");

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12, data13, data14, data15, data16, data17, data18 };
				model1 = (DefaultTableModel) tableSchemA_C.getModel();
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

	public void Calculation() {
		String ins = textFieldSchmePer.getText();
		if (ins.equals("0")) {
			textFieldTotalAmt.setText(textFieldAmt.getText());

		} else {
			// double oldbal=Double.valueOf(tex)
			double amount = Double.valueOf(textFieldAmt.getText());
			double interst = Double.valueOf(textFieldSchmePer.getText());
			double totla = amount * interst / 100.0;
			textFieldSchemeAmt.setText(String.valueOf(totla));
			double sumtotal = totla + amount;
			textFieldTotalAmt.setText(String.valueOf(sumtotal));
		}
	}

	public void DurationIncrement() {
		try {
			conn = (Connection) DBConnection.getConnection();
			String show = "select * from banksystem.schemea_c order by SrNoMaster;";
			ps = conn.prepareStatement(show);
			rs = ps.executeQuery();
			while (rs.next()) {

				String date1 = rs.getString("Date");
				String acno = rs.getString("AccountNo");
				double interst = rs.getDouble("SchPer");
				double maturityamt = rs.getDouble("Total");
				String days = rs.getString("Days");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date datecur = new Date();
				String date2 = (String) sdf.format(datecur);
				Date date11 = sdf.parse(date1);
				Date date12 = sdf.parse(date2);
				long diff = date12.getTime() - date11.getTime();
				long dayscount = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				double daycont = Double.valueOf(days);
				if (dayscount > daycont) {

					SimpleDateFormat sdf44 = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserSchemeA_C.getDate();
					String sysdate44 = (String) sdf44.format(dateChooserSchemeA_C.getDate());
					double interscount = maturityamt * interst / 100;
					double aginmatuamtsum = maturityamt + interscount;
					double interagincount = aginmatuamtsum * interst / 100;
					double allsumsum = aginmatuamtsum + interagincount;
					String upsavin = "UPDATE banksystem.schemea_c set  Date='" + sysdate44 + "', Amount='"
							+ aginmatuamtsum + "', SchPer='" + interst + "',SchAmt='" + interscount + "',Total='"
							+ allsumsum + "' where AccountNo='" + acno + "'";
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
			} catch (Exception ewww) {
				System.out.println(ewww.getMessage());
			}
		}

	}

	public void totalamount() {
		int rowsCount = tableSchemA_C.getRowCount();
		double sum = 0;

		for (int i = 0; i < rowsCount; i++) {
			sum = sum + Double.valueOf(tableSchemA_C.getValueAt(i, 6).toString());
		}
		textFieldTotalAmtB.setText(String.valueOf(df.format(sum)));

	}
}
