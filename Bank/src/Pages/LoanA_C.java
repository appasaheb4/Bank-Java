package Pages;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Statement;

import com.toedter.calendar.JDateChooser;

import CLASS.CalculatorGUI;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ScrollPaneConstants;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.border.BevelBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextArea;
import java.awt.Cursor;
import javax.swing.JList;
import java.awt.event.MouseMotionAdapter;

public class LoanA_C extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldAddress;
	private JTextField textFieldAge;
	private JTextField textFieldLoanAgaisntInterrst;
	private JTextField textFieldGarenter1;
	private JTextField textFieldGarenterOther1;
	public JTextField textFieldFilePath;
	private JTable tableloanshowdata;
	public JButton btnNewButtonBrowse;
	private JTextField textFieldAmount;
	private JTextField textFieldInterst;
	private JTextField textFieldTotal;
	private JTextField textFieldDuration;
	public JComboBox comboBoxDuration;
	public JComboBox comboBoxRelatiton1;
	public DefaultTableModel model1;
	public JCheckBox checkBoxDuration;
	public JCheckBox chckboxGarenter1;
	public JList<String> listloanname;
	public String[] data = new String[1000];
	public JScrollPane scrollPane_name;
	public JButton buttonUpdate;
	public JPanel panel_Info;

	public File f;
	public String path = null;

	private JTextField textFieldSrNo;
	public JTextField textFieldMasterSrno;
	public static Connection conn;
	public static PreparedStatement ps;
	public static PreparedStatement ps1;
	public static PreparedStatement ps2;
	public static PreparedStatement ps3;
	public static PreparedStatement ps4;
	public static PreparedStatement ps5;
	public static PreparedStatement ps6;
	public static ResultSet rs4;
	public static ResultSet rs5;
	public String val1;
	public Statement st;
	public static JDateChooser dateChooserloangpage;
	static java.util.Date dt1;
	java.util.Date dt2;

	public static ResultSet rs;
	public static ResultSet rs1;
	private JTextField textFieldDays;
	private JTextField textFieldInterstAmt;
	public JTextArea textFieldNote;
	private JTextField textFieldContatNo;

	public static int crack;
	private JTextField textFieldFormFee;
	private JTextField textFieldAccountNumber;
	private JTextField textFieldGarenter2;
	private JTextField textFieldGarenterOther2;
	private JTextField textField_Nomeny;
	private JTextField textField_Nomeny_1;
	public JComboBox comboBox_NomenyRelation;
	public JComboBox comboBoxRelatiton2;
	public static int issrno;
	public static int isrnoan;
	private JTextField textFieldShareInterst;
	private JTextField textFieldShareIntestAmt;
	private JTextField textFieldTotalRemaningAmt;
	private JTextField textFieldStationnaryAmt;
	public SimpleDateFormat sdff = new SimpleDateFormat("dd");
	private JTextField textFieldMonthlyInst;
	public static String accno;
	private JTextField textFieldTotalAmountB;
	public DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanA_C frame = new LoanA_C();
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
	public LoanA_C() {
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
				amountupdate();
				totalamount();
			}
		});

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1154, 76);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Loan A/C");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 276, 42);
		panel.add(lblNewLabel);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDate.setBounds(769, 48, 81, 17);
		panel.add(lblDate);

		Date date = new Date();
		dateChooserloangpage = new JDateChooser();
		dateChooserloangpage.setDateFormatString("yyyy-MM-dd");
		dateChooserloangpage.setBounds(860, 45, 196, 20);
		dateChooserloangpage.setDate(date);
		panel.add(dateChooserloangpage);

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

		textFieldSrNo = new JTextField();
		textFieldSrNo.setVisible(false);
		textFieldSrNo.setEnabled(false);
		// textFieldSrNo.setText(textFieldMasterSrno.getText());
		textFieldSrNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldSrNo.setColumns(10);
		textFieldSrNo.setBounds(150, 25, 209, 23);
		panel.add(textFieldSrNo);

		textFieldMasterSrno = new JTextField();
		textFieldMasterSrno.setVisible(false);
		textFieldMasterSrno.setEnabled(false);
		textFieldMasterSrno.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldMasterSrno.setColumns(10);
		textFieldMasterSrno.setBounds(397, 25, 209, 23);
		increment();

		panel.add(textFieldMasterSrno);

		JLabel lblAcountNumber = new JLabel("Acount Number:");
		lblAcountNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAcountNumber.setBounds(10, 51, 90, 14);
		panel.add(lblAcountNumber);

		textFieldAccountNumber = new JTextField();
		textFieldAccountNumber.setEditable(false);
		textFieldAccountNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldAccountNumber.setColumns(10);
		textFieldAccountNumber.setBounds(102, 47, 184, 23);
		increment();

		panel.add(textFieldAccountNumber);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculatorGUI cl = new CalculatorGUI("Bank System");
				cl.setSize(400, 350);
				cl.setFocusable(true);
				cl.setLocation(830, 0);
				cl.setVisible(true);
			}
		});
		button_1.setIcon(new ImageIcon(LoanA_C.class.getResource("/ImageButtonIcon/clac.png")));
		button_1.setOpaque(false);
		button_1.setForeground(Color.RED);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		button_1.setBounds(1084, 11, 64, 55);
		panel.add(button_1);

		panel_Info = new JPanel();
		panel_Info.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Info.setBounds(0, 82, 1154, 293);
		contentPane.add(panel_Info);
		panel_Info.setLayout(null);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblName.setBounds(10, 9, 81, 17);
		panel_Info.add(lblName);

		textFieldName = new JTextField();

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
					listloanname.setListData(data);
					listloanname.setVisibleRowCount(100);

					scrollPane_name.setVisible(true);
					listloanname.setVisible(true);
					textFieldLoanAgaisntInterrst.setVisible(false);
					textFieldShareInterst.setVisible(false);
					textFieldGarenter1.setVisible(false);
					textFieldGarenter2.setVisible(false);
					textField_Nomeny.setVisible(false);

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

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == 40) {
					listloanname.requestFocus();
				}
			}
		});
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldName.setColumns(10);
		textFieldName.setBounds(95, 6, 191, 23);
		panel_Info.add(textFieldName);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAddress.setBounds(296, 9, 81, 17);
		panel_Info.add(lblAddress);

		textFieldAddress = new JTextField();
		textFieldAddress.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				scrollPane_name.setVisible(false);
				listloanname.setVisible(false);
				textFieldLoanAgaisntInterrst.setVisible(true);
				textFieldShareInterst.setVisible(true);
				textFieldGarenter1.setVisible(true);
				textFieldGarenter2.setVisible(true);
				textField_Nomeny.setVisible(true);
			}
		});
		textFieldAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(351, 6, 209, 23);
		panel_Info.add(textFieldAddress);

		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAge.setBounds(570, 12, 81, 17);
		panel_Info.add(lblAge);

		textFieldAge = new JTextField();
		textFieldAge.addKeyListener(new KeyAdapter() {
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
		textFieldAge.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(620, 9, 213, 23);
		panel_Info.add(textFieldAge);

		JLabel lblLoanAgainst = new JLabel("Loan Against:");
		lblLoanAgainst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLoanAgainst.setBounds(10, 44, 81, 17);
		panel_Info.add(lblLoanAgainst);

		textFieldLoanAgaisntInterrst = new JTextField();
		textFieldLoanAgaisntInterrst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldLoanAgaisntInterrst.setColumns(10);
		textFieldLoanAgaisntInterrst.setBounds(95, 41, 191, 23);
		panel_Info.add(textFieldLoanAgaisntInterrst);

		JLabel lblGaurecta = new JLabel("Guaranted 1:");
		lblGaurecta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGaurecta.setBounds(12, 108, 106, 17);
		panel_Info.add(lblGaurecta);

		textFieldGarenter1 = new JTextField();
		textFieldGarenter1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldGarenter1.setColumns(10);
		textFieldGarenter1.setBounds(97, 108, 189, 23);
		panel_Info.add(textFieldGarenter1);

		JLabel lblRelastion = new JLabel("Relation 1:");
		lblRelastion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRelastion.setBounds(296, 111, 106, 17);
		panel_Info.add(lblRelastion);

		textFieldGarenterOther1 = new JTextField();
		textFieldGarenterOther1.setForeground(Color.RED);
		textFieldGarenterOther1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldGarenterOther1.setText("");
			}
		});
		textFieldGarenterOther1.setText("Other");
		textFieldGarenterOther1.setEnabled(false);
		textFieldGarenterOther1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldGarenterOther1.setColumns(10);
		textFieldGarenterOther1.setBounds(624, 109, 209, 23);
		panel_Info.add(textFieldGarenterOther1);

		JLabel lblOther = new JLabel("Other:");
		lblOther.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOther.setBounds(574, 112, 81, 17);
		panel_Info.add(lblOther);

		comboBoxRelatiton1 = new JComboBox();
		comboBoxRelatiton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = comboBoxRelatiton1.getSelectedItem().toString();
				textFieldGarenterOther1.setText("");
				textFieldGarenterOther1.setText(name);
			}
		});
		comboBoxRelatiton1.setModel(new DefaultComboBoxModel(new String[] {"   Select", "    Father", "    Mother", "   Paternal Grandfather", "    Maternal Grandfather ", "    Maternal Grandmother", "    Brother", "    Brother's Wife", "    Elder Brother", "    Younger Brother", "    Husband's sister", "    Sister", "    Sister's husband", "    Elder Sister", "    Younger Sister)", "    Husband's elder brother", "    Husband's younger brother", "    Elder brother's wife", "    Younger brother's wife", "    Wife's Sister", "    Wife's Brother", "    Wife's Brother's wife", "    Husband's Sister's Husband", "    Wife's sister's husband", "    Husband's elder brother's wife", "    Husband's younger brother's wife", "    Son", "    Son's wife (daughter-in-law) ", "    Daughter ", "    Daughter's husband (son-in-law) ", "    Grandson (son's son)", "    Granddaughter (son's daughter)", "    Grandson (daughter's son)", "    Granddaughter (daughter's daughter)", "    Husband", "    Wife", "    Husband's Mother (Mother-in-law)", "    Husband's Father (Father-in-law)", "    Fianc\u00E9 or Fianc\u00E9e", "    Father's younger brother (uncle)", "    Father's younger brother's wife (aunt)", "    Father's elder brother's (Uncle)", "    Father's elder brother's wife (Aunt) ", "    Father's sister (aunt)", "    Father's sister's husband", "    Mother's brother", "    Mother's brother's wife", "    Mother's sister", "    Mother's sister's husband", "    Brother's son (nephew)", "    Sister's son (nephew)", "    Brother's daughter (niece)", "    Sister's daughter (niece) ", "    Husband's elder brother's daughter", "    Husband's elder brother's son"}));
		comboBoxRelatiton1.setBounds(351, 108, 176, 23);
		panel_Info.add(comboBoxRelatiton1);

		JLabel lblFileBrowse = new JLabel("File Browse:");
		lblFileBrowse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFileBrowse.setBounds(583, 195, 106, 17);
		panel_Info.add(lblFileBrowse);

		chckboxGarenter1 = new JCheckBox("");
		chckboxGarenter1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click();
			}

			private void click() {
				if (!chckboxGarenter1.isSelected()) {
					comboBoxRelatiton1.setEnabled(true);
					textFieldGarenterOther1.setEnabled(false);
					textFieldGarenterOther1.setText("");
				} else {
					comboBoxRelatiton1.setEnabled(false);
					textFieldGarenterOther1.setEnabled(true);
					String me = "";
					comboBoxRelatiton1.setSelectedIndex(0);
				}

			}
		});
		chckboxGarenter1.setBounds(539, 109, 27, 23);
		panel_Info.add(chckboxGarenter1);

		JLabel lblNote = new JLabel("Note:");
		lblNote.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNote.setBounds(10, 209, 50, 17);
		panel_Info.add(lblNote);

		JPanel panel_Path = new JPanel();
		panel_Path.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_Path.setBounds(586, 223, 277, 44);
		panel_Info.add(panel_Path);
		panel_Path.setLayout(null);

		textFieldFilePath = new JTextField();
		textFieldFilePath.setEditable(false);
		textFieldFilePath.setText("path");
		textFieldFilePath.setBounds(10, 13, 196, 23);
		panel_Path.add(textFieldFilePath);
		textFieldFilePath.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldFilePath.setColumns(10);

		btnNewButtonBrowse = new JButton("Browse");
		btnNewButtonBrowse.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButtonBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser fc = new JFileChooser();
					fc.showOpenDialog(contentPane);
					f = fc.getSelectedFile();
					String path = f.getAbsolutePath();

					textFieldFilePath.setText(path);

					File files = new File("D:\\BankSystem\\Document");
					String path2 = files.getAbsolutePath();
					if (!files.exists()) {

						if (files.mkdirs()) {

						}

						else {

						}

					}
				} catch (Exception es) {
					System.out.println(es.getMessage());
				}

			}
		});
		btnNewButtonBrowse.setBounds(205, 13, 72, 23);
		panel_Path.add(btnNewButtonBrowse);

		JLabel lblAmount = new JLabel("Total Loan Amt:");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAmount.setBounds(570, 38, 106, 17);
		panel_Info.add(lblAmount);

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

			@Override
			public void keyReleased(KeyEvent e) {
				Calculation();
			}
		});
		textFieldAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(657, 36, 176, 23);
		panel_Info.add(textFieldAmount);

		JLabel lblInterst = new JLabel("Interst %:");
		lblInterst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblInterst.setBounds(842, 40, 81, 17);
		panel_Info.add(lblInterst);

		textFieldInterst = new JTextField();
		textFieldInterst.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == '%') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				Calculation();
			}
		});
		textFieldInterst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldInterst.setColumns(10);
		textFieldInterst.setBounds(924, 38, 166, 23);
		panel_Info.add(textFieldInterst);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setVisible(false);
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotal.setBounds(1009, 170, 81, 17);
		panel_Info.add(lblTotal);

		textFieldTotal = new JTextField();
		textFieldTotal.setForeground(Color.RED);
		textFieldTotal.setVisible(false);
		textFieldTotal.setEnabled(false);
		textFieldTotal.setFont(new Font("Tahoma", Font.BOLD, 18));
		textFieldTotal.setColumns(10);
		textFieldTotal.setBounds(935, 226, 209, 23);
		panel_Info.add(textFieldTotal);

		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDuration.setBounds(296, 41, 81, 17);
		panel_Info.add(lblDuration);

		comboBoxDuration = new JComboBox();
		comboBoxDuration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String st = comboBoxDuration.getSelectedItem().toString();
				daycount(st);
				}
			catch(Exception ww)
				{
				System.out.println(ww.getMessage());
				}
			}
		});
		comboBoxDuration.setModel(new DefaultComboBoxModel(new String[] { "Select", "1 Month", "3 Month\t", "6 Month\t",
				"9 Month", "1 Year", "2 Year\t", "3 Year", "4 Year", "5 Year" }));
		comboBoxDuration.setBounds(351, 37, 73, 23);
		panel_Info.add(comboBoxDuration);

		checkBoxDuration = new JCheckBox("");
		checkBoxDuration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click1();
			}

			private void click1() {
				if (!checkBoxDuration.isSelected()) {
					comboBoxDuration.setEnabled(true);
					textFieldDuration.setEnabled(false);
					textFieldDuration.setText("");

				} else {
					comboBoxDuration.setEnabled(false);
					textFieldDuration.setEnabled(true);
					String em = "";
					comboBoxDuration.setSelectedIndex(0);
				}

			}
		});
		checkBoxDuration.setBounds(430, 37, 21, 23);
		panel_Info.add(checkBoxDuration);

		textFieldDuration = new JTextField();
		textFieldDuration.setEnabled(false);
		textFieldDuration.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldDuration.setText("");
			}
		});
		textFieldDuration.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String st = textFieldDuration.getText();
				daycount(st);
			}
		});
		textFieldDuration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textFieldDuration.setText("other e.g. 3 Month,3 Year");
		textFieldDuration.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textFieldDuration.setColumns(10);
		textFieldDuration.setBounds(454, 36, 106, 23);
		panel_Info.add(textFieldDuration);

		textFieldDays = new JTextField();
		textFieldDays.setVisible(false);
		textFieldDays.setEnabled(false);
		textFieldDays.setBounds(929, 194, 45, 17);
		panel_Info.add(textFieldDays);
		textFieldDays.setColumns(10);

		textFieldInterstAmt = new JTextField();
		textFieldInterstAmt.setForeground(Color.RED);
		textFieldInterstAmt.setVisible(false);
		textFieldInterstAmt.setEnabled(false);
		textFieldInterstAmt.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldInterstAmt.setColumns(10);
		textFieldInterstAmt.setBounds(996, 195, 94, 23);
		panel_Info.add(textFieldInterstAmt);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(96, 204, 478, 62);
		panel_Info.add(scrollPane_1);

		textFieldNote = new JTextArea();
		textFieldNote.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == 9) {
					btnNewButtonBrowse.requestFocus();

				}
			}
		});
		scrollPane_1.setViewportView(textFieldNote);

		JLabel label_1 = new JLabel("Contact No:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(842, 12, 106, 17);
		panel_Info.add(label_1);

		textFieldContatNo = new JTextField();
		textFieldContatNo.addKeyListener(new KeyAdapter() {
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
		textFieldContatNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldContatNo.setColumns(10);
		textFieldContatNo.setBounds(924, 9, 166, 23);
		panel_Info.add(textFieldContatNo);

		JLabel lblFormFeeprocessingFee = new JLabel("Form Fee/Processing Fee:");
		lblFormFeeprocessingFee.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFormFeeprocessingFee.setBounds(567, 74, 143, 17);
		panel_Info.add(lblFormFeeprocessingFee);

		textFieldFormFee = new JTextField();
		textFieldFormFee.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFormFee.setText("");
			}
		});
		textFieldFormFee.setText("0");
		textFieldFormFee.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					arg0.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				Calculation();
			}
		});
		textFieldFormFee.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldFormFee.setColumns(10);
		textFieldFormFee.setBounds(694, 71, 65, 23);
		panel_Info.add(textFieldFormFee);

		JLabel lblGuaranteed = new JLabel("Guaranted 2:");
		lblGuaranteed.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGuaranteed.setBounds(10, 142, 106, 17);
		panel_Info.add(lblGuaranteed);

		textFieldGarenter2 = new JTextField();
		textFieldGarenter2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldGarenter2.setColumns(10);
		textFieldGarenter2.setBounds(95, 142, 191, 23);
		panel_Info.add(textFieldGarenter2);

		JLabel lblRelation = new JLabel("Relation 2:");
		lblRelation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRelation.setBounds(294, 145, 106, 17);
		panel_Info.add(lblRelation);

		comboBoxRelatiton2 = new JComboBox();
		comboBoxRelatiton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = comboBoxRelatiton2.getSelectedItem().toString();
				textFieldGarenterOther2.setText("");
				textFieldGarenterOther2.setText(name);
			}
		});
		comboBoxRelatiton2.setModel(new DefaultComboBoxModel(new String[] {"   Select", "    Father", "    Mother", "    Paternal Grandfather", "    Maternal Grandfather ", "    Maternal Grandmother", "    Brother", "    Brother's Wife", "    Elder Brother", "    Younger Brother", "    Husband's sister", "    Sister", "    Sister's husband", "    Elder Sister", "    Younger Sister)", "    Husband's elder brother", "    Husband's younger brother", "    Elder brother's wife", "    Younger brother's wife", "    Wife's Sister", "    Wife's Brother", "    Wife's Brother's wife", "    Husband's Sister's Husband", "    Wife's sister's husband", "    Husband's elder brother's wife", "    Husband's younger brother's wife", "    Son", "    Son's wife (daughter-in-law) ", "    Daughter ", "    Daughter's husband (son-in-law) ", "    Grandson (son's son)", "    Granddaughter (son's daughter)", "    Grandson (daughter's son)", "    Granddaughter (daughter's daughter)", "    Husband", "    Wife", "    Husband's Mother (Mother-in-law)", "    Husband's Father (Father-in-law)", "    Fianc\u00E9 or Fianc\u00E9e", "    Father's younger brother (uncle)", "    Father's younger brother's wife (aunt)", "    Father's elder brother's (Uncle)", "    Father's elder brother's wife (Aunt) ", "    Father's sister (aunt)", "    Father's sister's husband", "    Mother's brother", "    Mother's brother's wife", "    Mother's sister", "    Mother's sister's husband", "    Brother's son (nephew)", "    Sister's son (nephew)", "    Brother's daughter (niece)", "    Sister's daughter (niece) ", "    Husband's elder brother's daughter", "    Husband's elder brother's son"}));
		comboBoxRelatiton2.setBounds(349, 142, 176, 23);
		panel_Info.add(comboBoxRelatiton2);

		JCheckBox chckboxGarenter2 = new JCheckBox("");
		chckboxGarenter2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!chckboxGarenter2.isSelected()) {
					comboBoxRelatiton2.setEnabled(true);
					textFieldGarenterOther2.setEnabled(false);
					textFieldGarenterOther2.setText("");
				} else {
					comboBoxRelatiton2.setEnabled(false);
					textFieldGarenterOther2.setEnabled(true);

					comboBoxRelatiton2.setSelectedIndex(0);
				}
			}
		});
		chckboxGarenter2.setBounds(537, 143, 27, 23);
		panel_Info.add(chckboxGarenter2);

		JLabel label_4 = new JLabel("Other:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(572, 146, 81, 17);
		panel_Info.add(label_4);

		textFieldGarenterOther2 = new JTextField();
		textFieldGarenterOther2.setForeground(Color.RED);
		textFieldGarenterOther2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textFieldGarenterOther2.setText("");
			}
		});
		textFieldGarenterOther2.setText("Other");
		textFieldGarenterOther2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldGarenterOther2.setEnabled(false);
		textFieldGarenterOther2.setColumns(10);
		textFieldGarenterOther2.setBounds(622, 143, 209, 23);
		panel_Info.add(textFieldGarenterOther2);

		JLabel lblNomeny = new JLabel("Nomeny:");
		lblNomeny.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNomeny.setBounds(10, 170, 106, 17);
		panel_Info.add(lblNomeny);

		textField_Nomeny = new JTextField();
		textField_Nomeny.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_Nomeny.setColumns(10);
		textField_Nomeny.setBounds(95, 170, 191, 23);
		panel_Info.add(textField_Nomeny);

		JLabel label_6 = new JLabel("Relation:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(294, 173, 106, 17);
		panel_Info.add(label_6);

		comboBox_NomenyRelation = new JComboBox();
		comboBox_NomenyRelation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = comboBox_NomenyRelation.getSelectedItem().toString();
				textField_Nomeny_1.setText("");
				textField_Nomeny_1.setText(name);

			}
		});
		comboBox_NomenyRelation.setModel(new DefaultComboBoxModel(new String[] {"   Select", "    Father", "    Mother", "   Paternal Grandfather", "    Maternal Grandfather ", "    Maternal Grandmother", "    Brother", "    Brother's Wife", "    Elder Brother", "    Younger Brother", "    Husband's sister", "    Sister", "    Sister's husband", "    Elder Sister", "    Younger Sister)", "    Husband's elder brother", "    Husband's younger brother", "    Elder brother's wife", "    Younger brother's wife", "    Wife's Sister", "    Wife's Brother", "    Wife's Brother's wife", "    Husband's Sister's Husband", "    Wife's sister's husband", "    Husband's elder brother's wife", "    Husband's younger brother's wife", "    Son", "    Son's wife (daughter-in-law) ", "    Daughter ", "    Daughter's husband (son-in-law) ", "    Grandson (son's son)", "    Granddaughter (son's daughter)", "    Grandson (daughter's son)", "    Granddaughter (daughter's daughter)", "    Husband", "    Wife", "    Husband's Mother (Mother-in-law)", "    Husband's Father (Father-in-law)", "    Fianc\u00E9 or Fianc\u00E9e", "    Father's younger brother (uncle)", "    Father's younger brother's wife (aunt)", "    Father's elder brother's (Uncle)", "    Father's elder brother's wife (Aunt) ", "    Father's sister (aunt)", "    Father's sister's husband", "    Mother's brother", "    Mother's brother's wife", "    Mother's sister", "    Mother's sister's husband", "    Brother's son (nephew)", "    Sister's son (nephew)", "    Brother's daughter (niece)", "    Sister's daughter (niece) ", "    Husband's elder brother's daughter", "    Husband's elder brother's son"}));
		comboBox_NomenyRelation.setBounds(349, 170, 176, 23);
		panel_Info.add(comboBox_NomenyRelation);

		JCheckBox checkBox_Nomeny = new JCheckBox("");
		checkBox_Nomeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkBox_Nomeny.isSelected()) {
					comboBox_NomenyRelation.setEnabled(true);
					textField_Nomeny_1.setEnabled(false);
					textField_Nomeny_1.setText("");
				} else {
					comboBox_NomenyRelation.setEnabled(false);
					textField_Nomeny_1.setEnabled(true);
					comboBox_NomenyRelation.setSelectedIndex(0);
				}
			}
		});
		checkBox_Nomeny.setBounds(537, 171, 27, 23);
		panel_Info.add(checkBox_Nomeny);

		JLabel label_7 = new JLabel("Other:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(572, 174, 81, 17);
		panel_Info.add(label_7);

		textField_Nomeny_1 = new JTextField();
		textField_Nomeny_1.setForeground(Color.RED);
		textField_Nomeny_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField_Nomeny_1.setText("");
			}
		});
		textField_Nomeny_1.setText("Other");
		textField_Nomeny_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_Nomeny_1.setEnabled(false);
		textField_Nomeny_1.setColumns(10);
		textField_Nomeny_1.setBounds(622, 171, 209, 23);
		panel_Info.add(textField_Nomeny_1);

		JLabel lblShareInterst = new JLabel("Share Interst  %:");
		lblShareInterst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblShareInterst.setBounds(10, 76, 95, 17);
		panel_Info.add(lblShareInterst);

		textFieldShareInterst = new JTextField();
		textFieldShareInterst.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Calculation();
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == '%') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		textFieldShareInterst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldShareInterst.setColumns(10);
		textFieldShareInterst.setBounds(95, 72, 191, 23);
		panel_Info.add(textFieldShareInterst);

		textFieldShareIntestAmt = new JTextField();
		textFieldShareIntestAmt.setForeground(Color.RED);
		textFieldShareIntestAmt.setEditable(false);
		textFieldShareIntestAmt.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldShareIntestAmt.setColumns(10);
		textFieldShareIntestAmt.setBounds(401, 72, 161, 23);
		panel_Info.add(textFieldShareIntestAmt);

		JLabel lblShareInterstAmt = new JLabel("Share Interst Amt:");
		lblShareInterstAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblShareInterstAmt.setBounds(296, 76, 95, 17);
		panel_Info.add(lblShareInterstAmt);

		JLabel lblTotalRemaningAmtount = new JLabel("Total Pay amt:");
		lblTotalRemaningAmtount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotalRemaningAmtount.setBounds(842, 111, 143, 17);
		panel_Info.add(lblTotalRemaningAmtount);

		textFieldTotalRemaningAmt = new JTextField();
		textFieldTotalRemaningAmt.setForeground(Color.RED);
		textFieldTotalRemaningAmt.setEditable(false);
		textFieldTotalRemaningAmt.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldTotalRemaningAmt.setColumns(10);
		textFieldTotalRemaningAmt.setBounds(934, 106, 122, 23);
		panel_Info.add(textFieldTotalRemaningAmt);

		JLabel lblStationaryAmt = new JLabel("Stationary Amt:");
		lblStationaryAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblStationaryAmt.setBounds(766, 74, 95, 17);
		panel_Info.add(lblStationaryAmt);

		textFieldStationnaryAmt = new JTextField();
		textFieldStationnaryAmt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldStationnaryAmt.setText("");
			}
		});
		textFieldStationnaryAmt.setText("0");
		textFieldStationnaryAmt.addKeyListener(new KeyAdapter() {
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
		textFieldStationnaryAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldStationnaryAmt.setColumns(10);
		textFieldStationnaryAmt.setBounds(851, 71, 73, 23);
		panel_Info.add(textFieldStationnaryAmt);

		scrollPane_name = new JScrollPane();
		scrollPane_name.setVisible(false);
		scrollPane_name.setBounds(95, 30, 191, 163);
		panel_Info.add(scrollPane_name);

		listloanname = new JList<String>();
		listloanname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldName.setText(listloanname.getSelectedValue());
					String accountnotop = textFieldName.getText();
					try {
						conn = DBConnection.getConnection();
						String query = "Select * from banksystem.loan where Name=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldName.getText());
						rs = ps.executeQuery();
						while (rs.next()) {

							String Srnomaster = rs.getString("Srnomaster");
							textFieldMasterSrno.setText(Srnomaster);
							String UpdateAmtDate = rs.getString("UpdateAmtDate");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date dd = sdf.parse(UpdateAmtDate);
							dateChooserloangpage.setDate(dd);
							String AccountNumber = rs.getString("AccountNumber");
							textFieldAccountNumber.setText(AccountNumber);
							String Name = rs.getString("Name");
							textFieldName.setText(Name);
							String Address = rs.getString("Address");
							textFieldAddress.setText(Address);
							String Age = rs.getString("Age");
							textFieldAge.setText(Age);
							String ContactNo = rs.getString("ContactNo");
							textFieldContatNo.setText(ContactNo);
							String LoanAgainst = rs.getString("LoanAgainst");
							textFieldLoanAgaisntInterrst.setText(LoanAgainst);
							String Duration = rs.getString("Duration");
							textFieldDuration.setText(Duration);
							String Days = rs.getString("Days");
							textFieldDays.setText(Days);
							String Amount = rs.getString("Amount");
							// textFieldAmount.setText(Amount);
							String Interst = rs.getString("Interst");
							textFieldInterst.setText(Interst);
							String InstersAmt = rs.getString("InstersAmt");
							textFieldInterstAmt.setText(InstersAmt);
							String Total = rs.getString("Total");
							textFieldTotal.setText(Total);
							String ShareInterst = rs.getString("ShareInterst");
							textFieldShareInterst.setText(ShareInterst);
							String ShareInterstAmt = rs.getString("ShareInterstAmt");
							textFieldShareIntestAmt.setText(ShareInterstAmt);
							String FormFee = rs.getString("FormFee");
							textFieldFormFee.setText(FormFee);
							String StationaryAmt = rs.getString("StationaryAmt");
							textFieldStationnaryAmt.setText(StationaryAmt);
							String TotalRemaningFee = rs.getString("TotalRemaningFee");
							textFieldTotalRemaningAmt.setText(TotalRemaningFee);
							String Gaurentr1 = rs.getString("Gaurentr1");
							textFieldGarenter1.setText(Gaurentr1);
							String Relation1 = rs.getString("Relation1");
							textFieldGarenterOther1.setText(Relation1);
							String Gaurentr2 = rs.getString("Gaurentr2");
							textFieldGarenter2.setText(Gaurentr2);
							String Relation2 = rs.getString("Relation2");
							textFieldGarenterOther2.setText(Relation2);
							String Nomeny = rs.getString("Nomeny");
							textField_Nomeny.setText(Nomeny);
							String NomeyRelation = rs.getString("NomeyRelation");
							textField_Nomeny_1.setText(NomeyRelation);
							String FilePath = rs.getString("FilePath");
							textFieldFilePath.setText(FilePath);
							String Note = rs.getString("Note");
							textFieldNote.setText(Note);

							scrollPane_name.setVisible(false);
							listloanname.setVisible(false);
							textFieldLoanAgaisntInterrst.setVisible(true);
							textFieldShareInterst.setVisible(true);
							textFieldGarenter1.setVisible(true);
							textFieldGarenter2.setVisible(true);
							textField_Nomeny.setVisible(true);

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
					try {
						conn = DBConnection.getConnection();
						String amount = "select Balance from banksystem.loantranscation  where AccountNo='"
								+ textFieldAccountNumber.getText() + "'";
						ps = conn.prepareStatement(amount);
						rs = ps.executeQuery();
						rs.first();
						double aoun = rs.getDouble("Balance");
						textFieldAmount.setText(String.valueOf(df.format(aoun)));

						Component[] com = panel_Info.getComponents();
						Component[] com1 = panel_Path.getComponents();
						textFieldNote.setEnabled(false);
						for (int a = 0; a < com.length; a++) {
							com[a].setEnabled(false);

						}
						for (int a = 0; a < com1.length; a++) {
							com1[a].setEnabled(false);
						}

					} catch (Exception es) {
						System.out.println(es.getMessage());
					} finally {
						try {
							rs.close();
							ps.close();
							conn.close();
						} catch (Exception ee) {
						}
					}

				}

			}
		});
		listloanname.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {

				textFieldName.setText(listloanname.getSelectedValue());
				String accountnotop = textFieldName.getText();

				try {
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.loan where Name=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldName.getText());
					rs = ps.executeQuery();
					while (rs.next()) {

						String Srnomaster = rs.getString("Srnomaster");
						textFieldMasterSrno.setText(Srnomaster);
						String UpdateAmtDate = rs.getString("UpdateAmtDate");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date dd = sdf.parse(UpdateAmtDate);
						dateChooserloangpage.setDate(dd);
						String AccountNumber = rs.getString("AccountNumber");
						textFieldAccountNumber.setText(AccountNumber);
						String Name = rs.getString("Name");
						textFieldName.setText(Name);
						String Address = rs.getString("Address");
						textFieldAddress.setText(Address);
						String Age = rs.getString("Age");
						textFieldAge.setText(Age);
						String ContactNo = rs.getString("ContactNo");
						textFieldContatNo.setText(ContactNo);
						String LoanAgainst = rs.getString("LoanAgainst");
						textFieldLoanAgaisntInterrst.setText(LoanAgainst);
						String Duration = rs.getString("Duration");
						textFieldDuration.setText(Duration);
						String Days = rs.getString("Days");
						textFieldDays.setText(Days);
						String Amount = rs.getString("Amount");
						// textFieldAmount.setText(Amount);
						String Interst = rs.getString("Interst");
						textFieldInterst.setText(Interst);
						String InstersAmt = rs.getString("InstersAmt");
						textFieldInterstAmt.setText(InstersAmt);
						String Total = rs.getString("Total");
						textFieldTotal.setText(Total);
						String ShareInterst = rs.getString("ShareInterst");
						textFieldShareInterst.setText(ShareInterst);
						String ShareInterstAmt = rs.getString("ShareInterstAmt");
						textFieldShareIntestAmt.setText(ShareInterstAmt);
						String FormFee = rs.getString("FormFee");
						textFieldFormFee.setText(FormFee);
						String StationaryAmt = rs.getString("StationaryAmt");
						textFieldStationnaryAmt.setText(StationaryAmt);
						String TotalRemaningFee = rs.getString("TotalRemaningFee");
						textFieldTotalRemaningAmt.setText(TotalRemaningFee);
						String Gaurentr1 = rs.getString("Gaurentr1");
						textFieldGarenter1.setText(Gaurentr1);
						String Relation1 = rs.getString("Relation1");
						textFieldGarenterOther1.setText(Relation1);
						String Gaurentr2 = rs.getString("Gaurentr2");
						textFieldGarenter2.setText(Gaurentr2);
						String Relation2 = rs.getString("Relation2");
						textFieldGarenterOther2.setText(Relation2);
						String Nomeny = rs.getString("Nomeny");
						textField_Nomeny.setText(Nomeny);
						String NomeyRelation = rs.getString("NomeyRelation");
						textField_Nomeny_1.setText(NomeyRelation);
						String FilePath = rs.getString("FilePath");
						textFieldFilePath.setText(FilePath);
						String Note = rs.getString("Note");
						textFieldNote.setText(Note);

						scrollPane_name.setVisible(false);
						listloanname.setVisible(false);
						textFieldLoanAgaisntInterrst.setVisible(true);
						textFieldShareInterst.setVisible(true);
						textFieldGarenter1.setVisible(true);
						textFieldGarenter2.setVisible(true);
						textField_Nomeny.setVisible(true);

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
				try {
					conn = DBConnection.getConnection();
					String amount = "select Balance from banksystem.loantranscation  where AccountNo='"
							+ textFieldAccountNumber.getText() + "'";
					ps = conn.prepareStatement(amount);
					rs = ps.executeQuery();
					rs.first();
					double aoun = rs.getDouble("Balance");
					textFieldAmount.setText(String.valueOf(df.format(aoun)));

					Component[] com = panel_Info.getComponents();
					Component[] com1 = panel_Path.getComponents();
					textFieldNote.setEnabled(false);
					for (int a = 0; a < com.length; a++) {
						com[a].setEnabled(false);

					}
					for (int a = 0; a < com1.length; a++) {
						com1[a].setEnabled(false);
					}

				} catch (Exception es) {
					System.out.println(es.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ee) {
					}
				}

			}
		});
		scrollPane_name.setViewportView(listloanname);

		JLabel lblMontthl = new JLabel("Monthly Installment:");
		lblMontthl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMontthl.setBounds(929, 75, 119, 17);
		panel_Info.add(lblMontthl);

		textFieldMonthlyInst = new JTextField();
		textFieldMonthlyInst.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldMonthlyInst.setText("");
			}
		});
		textFieldMonthlyInst.addKeyListener(new KeyAdapter() {
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
		textFieldMonthlyInst.setText("0");
		textFieldMonthlyInst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMonthlyInst.setColumns(10);
		textFieldMonthlyInst.setBounds(1033, 72, 73, 23);
		panel_Info.add(textFieldMonthlyInst);
		
		JLabel label = new JLabel("e.g. 1 Month, 1M ,1 Year,1 Y ... etc");
		label.setForeground(Color.RED);
		label.setBounds(367, 57, 194, 14);
		panel_Info.add(label);
		listloanname.setVisible(false);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(0, 386, 1154, 60);
		contentPane.add(panel_3);

		JButton buttonAdd = new JButton("Save");

		buttonAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String path = textFieldFilePath.getText();

				if (!textFieldName.getText().equals("") && !textFieldAmount.getText().equals("")) {

					if (!path.equals("path")) {
						try {

							FileInputStream fin = new FileInputStream(f);
							int len = (int) f.length();

							conn = DBConnection.getConnection();
							String in = "insert into banksystem.loan (Srnomaster, Srno, date, UpdateAmtDate,AmtPaidDate, AccountNumber, Name, Address, Age, ContactNo, LoanAgainst, Duration, Days, Amount, Interst, InstersAmt, Total, ShareInterst, ShareInterstAmt, FormFee, StationaryAmt, TotalRemaningFee, Gaurentr1, Relation1, Gaurentr2, Relation2, Nomeny, NomeyRelation, FileData, FilePath, Note,MonthlyInst, Code,LastDate)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
							ps = conn.prepareStatement(in, Statement.RETURN_GENERATED_KEYS);
							ps.setInt(1, Integer.parseInt(textFieldMasterSrno.getText()));
							ps.setInt(2, Integer.parseInt(textFieldSrNo.getText()));
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooserloangpage.getDate();
							String date = (String) sdf.format(dateChooserloangpage.getDate());
							java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
							ps.setDate(3, (java.sql.Date) d);
							ps.setDate(4, (java.sql.Date) d);
							ps.setDate(5, (java.sql.Date) d);
							ps.setString(6, textFieldAccountNumber.getText());
							ps.setString(7, textFieldName.getText());
							ps.setString(8, textFieldAddress.getText());
							ps.setDouble(9, Double.valueOf(textFieldAge.getText()));

							ps.setString(10, textFieldContatNo.getText());
							ps.setString(11, textFieldLoanAgaisntInterrst.getText());
							ps.setString(12, textFieldDuration.getText());
							ps.setDouble(13, Double.valueOf(textFieldDays.getText()));
							ps.setDouble(14, Double.valueOf(textFieldAmount.getText()));
							ps.setDouble(15, Double.valueOf(textFieldInterst.getText()));
							ps.setDouble(16, Double.valueOf(textFieldInterstAmt.getText()));
							ps.setDouble(17, Double.valueOf(textFieldTotal.getText()));
							ps.setDouble(18, Double.valueOf(textFieldShareInterst.getText()));
							ps.setDouble(19, Double.valueOf(textFieldShareIntestAmt.getText()));
							ps.setDouble(20, Double.valueOf(textFieldFormFee.getText()));
							ps.setDouble(21, Double.valueOf(textFieldStationnaryAmt.getText()));
							ps.setDouble(22, Double.valueOf(textFieldTotalRemaningAmt.getText()));
							ps.setString(23, textFieldGarenter1.getText());
							ps.setString(24, textFieldGarenterOther1.getText());
							ps.setString(25, textFieldGarenter2.getText());
							ps.setString(26, textFieldGarenterOther2.getText());
							ps.setString(27, textField_Nomeny.getText());
							ps.setString(28, textField_Nomeny_1.getText());
							ps.setBinaryStream(29, fin, len);
							ps.setString(30, textFieldFilePath.getText());
							ps.setString(31, textFieldNote.getText());
							ps.setDouble(32, Double.valueOf(textFieldMonthlyInst.getText()));
							ps.setInt(33, 1);

							Calendar c = Calendar.getInstance();
							int year = c.get(Calendar.YEAR);
							int month = c.get(Calendar.MONTH);
							int day = 1;
							c.set(year, month, day);
							int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);

							c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);
							String indate = sdf.format(c.getTime());
							Date parsed = (Date) sdf.parse(indate);
							java.sql.Date sql7777 = new java.sql.Date(parsed.getTime());
							ps.setDate(34, (java.sql.Date) sql7777);
							int i = ps.executeUpdate();
							if (i > 0) {

								try {
									conn = DBConnection.getConnection();
									String maxno = "select max(SrNoMaster) from banksystem.shares;";
									ps = conn.prepareStatement(maxno);
									rs = ps.executeQuery();

									while (rs.next()) {
										isrnoan = rs.getInt(1) + 1;
									}
									String insert = "insert into banksystem.shares (SrNoMaster, Date, Name, AccountNo, Address, Age, ContactNo, Amount, SchPer,Total,Notess)values(?,?,?,?,?,?,?,?,?,?,?)";
									ps1 = conn.prepareStatement(insert);

									ps1.setInt(1, isrnoan);
									SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
									dt1 = dateChooserloangpage.getDate();
									String date1 = (String) sdf1.format(dateChooserloangpage.getDate());
									java.sql.Date d1 = new java.sql.Date(sdf.parse(date1).getTime());
									ps1.setDate(2, (java.sql.Date) d1);
									ps1.setString(3, textFieldName.getText());
									ps1.setString(4, textFieldAccountNumber.getText());
									ps1.setString(5, textFieldAddress.getText());
									ps1.setDouble(6, Double.valueOf(textFieldAge.getText()));
									ps1.setString(7, textFieldContatNo.getText());
									ps1.setDouble(8, Double.valueOf(textFieldShareIntestAmt.getText()));
									ps1.setDouble(9, Double.valueOf(textFieldShareInterst.getText()));
									ps1.setDouble(10, Double.valueOf(textFieldShareIntestAmt.getText()));
									ps1.setString(11, textFieldNote.getText());
									ps1.executeUpdate();

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
						} catch (Exception es) {
							System.out.println(es.getMessage());
						}

						try {
							conn = DBConnection.getConnection();
							String maxno = "select max(SrNoMaster) from banksystem.loantranscation;";
							ps1 = conn.prepareStatement(maxno);
							rs1 = ps1.executeQuery();
							while (rs1.next()) {
								issrno = rs1.getInt(1) + 1;
							}

							String insertdata = "insert into banksystem.loantranscation (SrNoMaster, SrNo,Date,Name, AccountNo, TransactionParticulars,Balance) values(?,?,?,?,?,?,?);";
							ps2 = conn.prepareStatement(insertdata);
							ps2.setInt(1, issrno);
							ps2.setInt(2, 1);
							SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooserloangpage.getDate();
							String date2 = (String) sdf2.format(dateChooserloangpage.getDate());
							java.sql.Date d2 = new java.sql.Date(sdf2.parse(date2).getTime());
							ps2.setDate(3, (java.sql.Date) d2);
							ps2.setString(4, textFieldName.getText());
							ps2.setString(5, textFieldAccountNumber.getText());
							ps2.setString(6, "Loan of");
							ps2.setDouble(7, Double.valueOf(textFieldAmount.getText()));
							int iii = ps2.executeUpdate();
							if (iii > 0) {
								JOptionPane.showMessageDialog(null, "Data are saved.");
								tabledataview();
								totalamount();

							}

						} catch (Exception ee) {
							System.out.println(ee.getMessage());
						} finally {
							try {
								rs1.close();
								ps1.close();
								ps2.close();
								conn.close();
							} catch (Exception ewww) {
								System.out.println(ewww.getMessage());
							}
						}

						try {
							int maxso = 0;
							conn = DBConnection.getConnection();
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
							dt1 = dateChooserloangpage.getDate();
							String date = (String) sdf.format(dateChooserloangpage.getDate());
							java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
							ps1.setDate(2, (java.sql.Date) d);
							ps1.setString(3, textFieldName.getText());
							ps1.setString(4, textFieldAccountNumber.getText());
							double value = Double.valueOf(textFieldFormFee.getText())
									+ Double.valueOf(textFieldStationnaryAmt.getText());
							ps1.setDouble(5, value);
							ps1.setString(6, textFieldNote.getText());
							ps1.executeUpdate();

							increment();
							reset();

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

					else {
						try {

							conn = DBConnection.getConnection();
							String in = "insert into banksystem.loan (Srnomaster, Srno, date, UpdateAmtDate,AmtPaidDate, AccountNumber, Name, Address, Age, ContactNo, LoanAgainst, Duration, Days, Amount, Interst, InstersAmt, Total, ShareInterst, ShareInterstAmt, FormFee, StationaryAmt, TotalRemaningFee, Gaurentr1, Relation1, Gaurentr2, Relation2, Nomeny, NomeyRelation, FilePath, Note,MonthlyInst, Code,LastDate)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
							ps = conn.prepareStatement(in, Statement.RETURN_GENERATED_KEYS);
							ps.setInt(1, Integer.parseInt(textFieldMasterSrno.getText()));
							ps.setInt(2, Integer.parseInt(textFieldSrNo.getText()));
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooserloangpage.getDate();
							String date = (String) sdf.format(dateChooserloangpage.getDate());
							java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
							ps.setDate(3, (java.sql.Date) d);
							ps.setDate(4, (java.sql.Date) d);
							ps.setDate(5, (java.sql.Date) d);
							ps.setString(6, textFieldAccountNumber.getText());
							ps.setString(7, textFieldName.getText());
							ps.setString(8, textFieldAddress.getText());
							ps.setDouble(9, Double.valueOf(textFieldAge.getText()));

							ps.setString(10, textFieldContatNo.getText());
							ps.setString(11, textFieldLoanAgaisntInterrst.getText());
							ps.setString(12, textFieldDuration.getText());
							ps.setDouble(13, Double.valueOf(textFieldDays.getText()));
							ps.setDouble(14, Double.valueOf(textFieldAmount.getText()));
							ps.setDouble(15, Double.valueOf(textFieldInterst.getText()));
							ps.setDouble(16, Double.valueOf(textFieldInterstAmt.getText()));
							ps.setDouble(17, Double.valueOf(textFieldTotal.getText()));
							ps.setDouble(18, Double.valueOf(textFieldShareInterst.getText()));
							ps.setDouble(19, Double.valueOf(textFieldShareIntestAmt.getText()));
							ps.setDouble(20, Double.valueOf(textFieldFormFee.getText()));
							ps.setDouble(21, Double.valueOf(textFieldStationnaryAmt.getText()));
							ps.setDouble(22, Double.valueOf(textFieldTotalRemaningAmt.getText()));
							ps.setString(23, textFieldGarenter1.getText());
							ps.setString(24, textFieldGarenterOther1.getText());
							ps.setString(25, textFieldGarenter2.getText());
							ps.setString(26, textFieldGarenterOther2.getText());
							ps.setString(27, textField_Nomeny.getText());
							ps.setString(28, textField_Nomeny_1.getText());
							ps.setString(29, textFieldFilePath.getText());
							ps.setString(30, textFieldNote.getText());
							ps.setDouble(31, Double.valueOf(textFieldMonthlyInst.getText()));
							ps.setInt(32, 1);

							Calendar c = Calendar.getInstance();
							int year = c.get(Calendar.YEAR);
							int month = c.get(Calendar.MONTH);
							int day = 1;
							c.set(year, month, day);
							int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);

							c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);
							String indate = sdf.format(c.getTime());
							Date parsed = (Date) sdf.parse(indate);
							java.sql.Date sql7777 = new java.sql.Date(parsed.getTime());
							ps.setDate(33, (java.sql.Date) sql7777);
							int i = ps.executeUpdate();
							if (i > 0) {

								try {
									conn = DBConnection.getConnection();
									String maxno = "select max(SrNoMaster) from banksystem.shares;";
									ps1 = conn.prepareStatement(maxno);
									rs = ps1.executeQuery();

									while (rs.next()) {
										isrnoan = rs.getInt(1) + 1;
									}
									String insert = "insert into banksystem.shares (SrNoMaster, Date, Name, AccountNo, Address, Age, ContactNo, Amount, SchPer,Total,Notess)values(?,?,?,?,?,?,?,?,?,?,?)";
									ps2 = conn.prepareStatement(insert);

									ps2.setInt(1, isrnoan);
									SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
									dt1 = dateChooserloangpage.getDate();
									String date1 = (String) sdf1.format(dateChooserloangpage.getDate());
									java.sql.Date d1 = new java.sql.Date(sdf.parse(date1).getTime());
									ps2.setDate(2, (java.sql.Date) d1);
									ps2.setString(3, textFieldName.getText());
									ps2.setString(4, textFieldAccountNumber.getText());
									ps2.setString(5, textFieldAddress.getText());
									ps2.setDouble(6, Double.valueOf(textFieldAge.getText()));
									ps2.setString(7, textFieldContatNo.getText());
									ps2.setDouble(8, Double.valueOf(textFieldShareIntestAmt.getText()));
									ps2.setDouble(9, Double.valueOf(textFieldShareInterst.getText()));
									ps2.setDouble(10, Double.valueOf(textFieldShareIntestAmt.getText()));
									ps2.setString(11, textFieldNote.getText());
									ps2.executeUpdate();

								} catch (Exception ee) {
									System.out.println(ee.getMessage());
								} finally {
									try {
										rs.close();
										ps.close();
										ps1.close();
										ps2.close();
										conn.close();
									} catch (Exception ewww) {
										System.out.println(ewww.getMessage());
									}
								}
							}
						} catch (Exception es) {
							System.out.println(es.getMessage());
						}

						try {
							conn = DBConnection.getConnection();
							String maxno = "select max(SrNoMaster) from banksystem.loantranscation;";
							ps1 = conn.prepareStatement(maxno);
							rs1 = ps1.executeQuery();
							while (rs1.next()) {
								issrno = rs1.getInt(1) + 1;
							}

							String insertdata = "insert into banksystem.loantranscation (SrNoMaster, SrNo,Date,Name, AccountNo, TransactionParticulars,Balance) values(?,?,?,?,?,?,?);";
							ps2 = conn.prepareStatement(insertdata);
							ps2.setInt(1, issrno);
							ps2.setInt(2, 1);
							SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooserloangpage.getDate();
							String date2 = (String) sdf2.format(dateChooserloangpage.getDate());
							java.sql.Date d2 = new java.sql.Date(sdf2.parse(date2).getTime());
							ps2.setDate(3, (java.sql.Date) d2);
							ps2.setString(4, textFieldName.getText());
							ps2.setString(5, textFieldAccountNumber.getText());
							ps2.setString(6, "Loan of");
							ps2.setDouble(7, Double.valueOf(textFieldAmount.getText()));
							int iii = ps2.executeUpdate();
							if (iii > 0) {
								JOptionPane.showMessageDialog(null, "Data are saved.");
								tabledataview();

							}

						} catch (Exception ee) {
							System.out.println(ee.getMessage());
						} finally {
							try {
								rs1.close();
								ps1.close();
								ps2.close();
								conn.close();
							} catch (Exception ewww) {
								System.out.println(ewww.getMessage());
							}
						}

						try {
							int maxso = 0;
							conn = DBConnection.getConnection();
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
							dt1 = dateChooserloangpage.getDate();
							String date = (String) sdf.format(dateChooserloangpage.getDate());
							java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
							ps1.setDate(2, (java.sql.Date) d);
							ps1.setString(3, textFieldName.getText());
							ps1.setString(4, textFieldAccountNumber.getText());
							double value = Double.valueOf(textFieldFormFee.getText())
									+ Double.valueOf(textFieldStationnaryAmt.getText());
							ps1.setDouble(5, value);
							ps1.setString(6, textFieldNote.getText());
							ps1.executeUpdate();

							increment();
							reset();

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
				} else {
					JOptionPane.showMessageDialog(null, "Insert new recoard.");
				}

			}

			private void tabledataview() {

				String data0 = textFieldMasterSrno.getText();
				String data1 = textFieldSrNo.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				dt1 = dateChooserloangpage.getDate();
				String data2 = (String) sdf.format(dateChooserloangpage.getDate());
				String data3 = (String) sdf.format(dateChooserloangpage.getDate());
				String data4 = textFieldAccountNumber.getText();
				String data5 = textFieldName.getText();
				String data6 = textFieldInterst.getText();
				String data7 = textFieldAmount.getText();
				String data8 = textFieldInterstAmt.getText();
				String data9 = textFieldTotal.getText();
				String data10 = textFieldShareInterst.getText();
				String data11 = textFieldShareIntestAmt.getText();
				String data12 = textFieldFormFee.getText();
				String data13 = textFieldStationnaryAmt.getText();
				String data14 = textFieldTotalRemaningAmt.getText();
				String data15 = textFieldMonthlyInst.getText();
				String data16 = textFieldDuration.getText();
				String data17 = textFieldDays.getText();
				String data18 = textFieldLoanAgaisntInterrst.getText();
				String data19 = textFieldGarenter1.getText();
				String data20 = textFieldGarenterOther1.getText();
				String data21 = textFieldGarenter2.getText();
				String data22 = textFieldGarenterOther2.getText();
				String data23 = textField_Nomeny.getText();
				String data24 = textField_Nomeny_1.getText();
				String data25 = textFieldAddress.getText();
				String data26 = textFieldAge.getText();
				String data27 = textFieldContatNo.getText();
				String data28 = textFieldFilePath.getText();
				String data29 = textFieldNote.getText();
				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12, data13, data14, data15, data16, data17, data18, data19, data20, data21,
						data22, data23, data24, data25, data26, data27, data28, data29 };
				model1 = (DefaultTableModel) tableloanshowdata.getModel();
				model1.addRow(row);

			}
		});
		buttonAdd.setIcon(new ImageIcon(LoanA_C.class.getResource("/ImageButtonIcon/Save.png")));
		buttonAdd.setHorizontalAlignment(SwingConstants.LEADING);
		buttonAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonAdd.setBounds(216, 11, 115, 38);
		panel_3.add(buttonAdd);

		buttonUpdate = new JButton("Update");
		buttonUpdate.setVisible(false);
		buttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = textFieldFilePath.getText();
				if (!path.equals("path")) {

					try {
						FileInputStream fin = new FileInputStream(f);
						int len = (int) f.length();
						conn = DBConnection.getConnection();
						String up = "UPDATE banksystem.loan set  UpdateAmtDate=?,AccountNumber=?, Name=?, Address=?, Age=?, "
								+ "ContactNo=?, LoanAgainst=?, Duration=?, Days=?, Amount=?, Interst=?, InstersAmt=?, Total=?, ShareInterst=?,"
								+ " ShareInterstAmt=?, FormFee=?, StationaryAmt=?, TotalRemaningFee=?, Gaurentr1=?, Relation1=?, Gaurentr2=?, "
								+ "Relation2=?, Nomeny=?, NomeyRelation=?, FileData=?, FilePath=?, Note=?, MonthlyInst=? where Srnomaster=?";
						ps = conn.prepareStatement(up);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						dt1 = dateChooserloangpage.getDate();
						String date = (String) sdf.format(dateChooserloangpage.getDate());
						java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
						ps.setDate(1, (java.sql.Date) d);
						ps.setString(2, textFieldAccountNumber.getText());
						ps.setString(3, textFieldName.getText());
						ps.setString(4, textFieldAddress.getText());
						ps.setDouble(5, Double.valueOf(textFieldAge.getText()));

						ps.setString(6, textFieldContatNo.getText());
						ps.setString(7, textFieldLoanAgaisntInterrst.getText());
						ps.setString(8, textFieldDuration.getText());
						ps.setDouble(9, Double.valueOf(textFieldDays.getText()));
						ps.setDouble(10, Double.valueOf(textFieldAmount.getText()));
						ps.setDouble(11, Double.valueOf(textFieldInterst.getText()));
						ps.setDouble(12, Double.valueOf(textFieldInterstAmt.getText()));
						ps.setDouble(13, Double.valueOf(textFieldTotal.getText()));
						ps.setDouble(14, Double.valueOf(textFieldShareInterst.getText()));
						ps.setDouble(15, Double.valueOf(textFieldShareIntestAmt.getText()));
						ps.setDouble(16, Double.valueOf(textFieldFormFee.getText()));
						ps.setDouble(17, Double.valueOf(textFieldStationnaryAmt.getText()));
						ps.setDouble(18, Double.valueOf(textFieldTotalRemaningAmt.getText()));
						ps.setString(19, textFieldGarenter1.getText());
						ps.setString(20, textFieldGarenterOther1.getText());
						ps.setString(21, textFieldGarenter2.getText());
						ps.setString(22, textFieldGarenterOther2.getText());
						ps.setString(23, textField_Nomeny.getText());
						ps.setString(24, textField_Nomeny_1.getText());
						ps.setBinaryStream(25, fin, len);
						ps.setString(26, textFieldFilePath.getText());
						ps.setString(27, textFieldNote.getText());
						ps.setDouble(28, Double.valueOf(textFieldMonthlyInst.getText()));
						ps.setInt(29, Integer.parseInt(textFieldMasterSrno.getText()));

						int i = ps.executeUpdate();
						if (i > 0) {
							JOptionPane.showMessageDialog(null, "Data are update.");
							dispose();
							LoanA_C lo = new LoanA_C();
							lo.setUndecorated(true);
							lo.setVisible(true);

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
				} else {
					try {

						conn = DBConnection.getConnection();
						String up = "UPDATE banksystem.loan set  UpdateAmtDate=?,AccountNumber=?, Name=?, Address=?, Age=?, "
								+ "ContactNo=?, LoanAgainst=?, Duration=?, Days=?, Amount=?, Interst=?, InstersAmt=?, Total=?, ShareInterst=?,"
								+ " ShareInterstAmt=?, FormFee=?, StationaryAmt=?, TotalRemaningFee=?, Gaurentr1=?, Relation1=?, Gaurentr2=?, "
								+ "Relation2=?, Nomeny=?, NomeyRelation=?, FilePath=?, Note=?, MonthlyInst=? where Srnomaster=?";
						ps = conn.prepareStatement(up);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						dt1 = dateChooserloangpage.getDate();
						String date = (String) sdf.format(dateChooserloangpage.getDate());
						java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
						ps.setDate(1, (java.sql.Date) d);
						ps.setString(2, textFieldAccountNumber.getText());
						ps.setString(3, textFieldName.getText());
						ps.setString(4, textFieldAddress.getText());
						ps.setDouble(5, Double.valueOf(textFieldAge.getText()));

						ps.setString(6, textFieldContatNo.getText());
						ps.setString(7, textFieldLoanAgaisntInterrst.getText());
						ps.setString(8, textFieldDuration.getText());
						ps.setDouble(9, Double.valueOf(textFieldDays.getText()));
						ps.setDouble(10, Double.valueOf(textFieldAmount.getText()));
						ps.setDouble(11, Double.valueOf(textFieldInterst.getText()));
						ps.setDouble(12, Double.valueOf(textFieldInterstAmt.getText()));
						ps.setDouble(13, Double.valueOf(textFieldTotal.getText()));
						ps.setDouble(14, Double.valueOf(textFieldShareInterst.getText()));
						ps.setDouble(15, Double.valueOf(textFieldShareIntestAmt.getText()));
						ps.setDouble(16, Double.valueOf(textFieldFormFee.getText()));
						ps.setDouble(17, Double.valueOf(textFieldStationnaryAmt.getText()));
						ps.setDouble(18, Double.valueOf(textFieldTotalRemaningAmt.getText()));
						ps.setString(19, textFieldGarenter1.getText());
						ps.setString(20, textFieldGarenterOther1.getText());
						ps.setString(21, textFieldGarenter2.getText());
						ps.setString(22, textFieldGarenterOther2.getText());
						ps.setString(23, textField_Nomeny.getText());
						ps.setString(24, textField_Nomeny_1.getText());

						ps.setString(25, textFieldFilePath.getText());
						ps.setString(26, textFieldNote.getText());
						ps.setDouble(27, Double.valueOf(textFieldMonthlyInst.getText()));
						ps.setInt(28, Integer.parseInt(textFieldMasterSrno.getText()));

						int i = ps.executeUpdate();
						if (i > 0) {
							JOptionPane.showMessageDialog(null, "Data are update.");
							dispose();
							LoanA_C lo = new LoanA_C();
							lo.setUndecorated(true);
							lo.setVisible(true);

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
			}
		});
		buttonUpdate.setIcon(new ImageIcon(LoanA_C.class.getResource("/ImageButtonIcon/update.png")));
		buttonUpdate.setToolTipText("");
		buttonUpdate.setHorizontalAlignment(SwingConstants.LEADING);
		buttonUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonUpdate.setBounds(71, 11, 126, 38);
		panel_3.add(buttonUpdate);

		JButton buttonDelete = new JButton("Delete");
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String de = "delete from banksystem.loantranscation where AccountNo=?";
					ps = conn.prepareStatement(de);
					ps.setString(1, textFieldAccountNumber.getText());
					int is = ps.executeUpdate();
					if (is > 0) {
						try {
							conn = DBConnection.getConnection();
							String de4 = "delete from banksystem.loan where Srnomaster=?";
							ps = conn.prepareStatement(de4);
							ps.setInt(1, Integer.parseInt(textFieldMasterSrno.getText()));
							int is4 = Integer.parseInt(textFieldMasterSrno.getText()) - 1;
							ps.executeUpdate();

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
						try {
							conn = DBConnection.getConnection();
							String de2 = "delete from banksystem.expenditureincome where AccountNo=?";
							ps = conn.prepareStatement(de2);
							ps.setString(1, textFieldAccountNumber.getText());
							ps.executeUpdate();

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
						try {
							conn = DBConnection.getConnection();
							String de2 = "delete from banksystem.shares where AccountNo=?";
							ps = conn.prepareStatement(de2);
							ps.setString(1, textFieldAccountNumber.getText());
							ps.executeUpdate();
							JOptionPane.showMessageDialog(null, "Data are deleted.");
							dispose();
							LoanA_C lo = new LoanA_C();
							lo.setUndecorated(true);
							lo.setVisible(true);

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

				} catch (Exception qe) {
					System.out.print(qe.getMessage());
				}

			}
		});
		buttonDelete.setIcon(new ImageIcon(LoanA_C.class.getResource("/ImageButtonIcon/delete.jpg")));
		buttonDelete.setHorizontalAlignment(SwingConstants.LEADING);
		buttonDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonDelete.setBounds(477, 11, 115, 38);
		panel_3.add(buttonDelete);

		JButton buttonReset = new JButton("Reset");
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();

			}
		});
		buttonReset.setIcon(new ImageIcon(LoanA_C.class.getResource("/ImageButtonIcon/Reset5.jpg")));
		buttonReset.setHorizontalAlignment(SwingConstants.LEADING);
		buttonReset.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonReset.setBounds(602, 11, 115, 38);
		panel_3.add(buttonReset);

		JButton buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Welcome.frame.enable();
			}
		});
		buttonExit.setIcon(new ImageIcon(LoanA_C.class.getResource("/ImageButtonIcon/Exit.png")));
		buttonExit.setHorizontalAlignment(SwingConstants.LEADING);
		buttonExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonExit.setBounds(727, 11, 115, 38);
		panel_3.add(buttonExit);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try
				{

				btnEdit.setVisible(false);
				buttonUpdate.setVisible(true);
				buttonUpdate.setBounds(341, 11, 126, 38);
				
				Component[] com = panel_Info.getComponents();
				Component[] com1 = panel_Path.getComponents();
				textFieldNote.setEnabled(true);
				for (int a = 0; a < com.length; a++) {
					com[a].setEnabled(true);

				}
				for (int a = 0; a < com1.length; a++) {
					com1[a].setEnabled(true);
				}
				}
				catch(Exception ew)
				{
					
				}

			}
		});
		btnEdit.setIcon(new ImageIcon(LoanA_C.class.getResource("/ImageButtonIcon/edit.png")));
		btnEdit.setToolTipText("");
		btnEdit.setHorizontalAlignment(SwingConstants.LEADING);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(341, 11, 126, 38);
		panel_3.add(btnEdit);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(0, 457, 1154, 187);
		contentPane.add(panel_4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4
				.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup().addContainerGap()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1132, Short.MAX_VALUE)
								.addContainerGap()));
		gl_panel_4
				.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup().addContainerGap()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
								.addContainerGap()));

		tableloanshowdata = new JTable();
		tableloanshowdata.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		tableloanshowdata.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int i = tableloanshowdata.getSelectedRow();
					model1 = (DefaultTableModel) tableloanshowdata.getModel();
					textFieldMasterSrno.setText(model1.getValueAt(i, 1).toString());
					textFieldSrNo.setText(model1.getValueAt(i, 2).toString());
					// dateChooserloangpage.setDateFormatString(model1.getValueAt(i,
					// 3).toString());
					textFieldAccountNumber.setText(model1.getValueAt(i, 5).toString());
					textFieldName.setText(model1.getValueAt(i, 6).toString());
					textFieldInterst.setText(model1.getValueAt(i, 7).toString());
					// textFieldAmount.setText(model1.getValueAt(i,
					// 8).toString());
					textFieldInterstAmt.setText(model1.getValueAt(i, 9).toString());
					textFieldTotal.setText(model1.getValueAt(i, 10).toString());
					textFieldShareInterst.setText(model1.getValueAt(i, 11).toString());
					textFieldShareIntestAmt.setText(model1.getValueAt(i, 12).toString());
					textFieldFormFee.setText(model1.getValueAt(i, 13).toString());
					textFieldStationnaryAmt.setText(model1.getValueAt(i, 14).toString());
					textFieldTotalRemaningAmt.setText(model1.getValueAt(i, 15).toString());
					textFieldMonthlyInst.setText(model1.getValueAt(i, 16).toString());
					textFieldDuration.setText(model1.getValueAt(i, 17).toString());
					textFieldDays.setText(model1.getValueAt(i, 18).toString());
					textFieldLoanAgaisntInterrst.setText(model1.getValueAt(i, 19).toString());
					textFieldGarenter1.setText(model1.getValueAt(i, 20).toString());
					textFieldGarenterOther1.setText(model1.getValueAt(i, 21).toString());
					textFieldGarenter2.setText(model1.getValueAt(i, 22).toString());
					textFieldGarenterOther2.setText(model1.getValueAt(i, 23).toString());
					textField_Nomeny.setText(model1.getValueAt(i, 24).toString());
					textField_Nomeny_1.setText(model1.getValueAt(i, 25).toString());
					textFieldAddress.setText(model1.getValueAt(i, 26).toString());
					textFieldAge.setText(model1.getValueAt(i, 27).toString());
					textFieldContatNo.setText(model1.getValueAt(i, 28).toString());
					textFieldFilePath.setText(model1.getValueAt(i, 29).toString());
					f = new File(model1.getValueAt(i, 29).toString());
					textFieldNote.setText(model1.getValueAt(i, 30).toString());
				} catch (Exception es) {
				}
				try {
					che();
				} catch (Exception es) {
				}

				try {
					conn = DBConnection.getConnection();
					String amount = "select Balance from banksystem.loantranscation  where AccountNo='"
							+ textFieldAccountNumber.getText() + "'";
					ps = conn.prepareStatement(amount);
					rs = ps.executeQuery();
					rs.first();
					double aoun = rs.getDouble("Balance");
					textFieldAmount.setText(String.valueOf(df.format(aoun)));

					Component[] com = panel_Info.getComponents();
					Component[] com1 = panel_Path.getComponents();
					textFieldNote.setEnabled(false);
					for (int a = 0; a < com.length; a++) {
						com[a].setEnabled(false);

					}
					for (int a = 0; a < com1.length; a++) {
						com1[a].setEnabled(false);
					}

				} catch (Exception es) {
					System.out.println(es.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ee) {
					}
				}

			}

			private void che() {
				boolean s = false;
				boolean s1 = true;
				String inc = null;
				int row = 0;
				int rows = tableloanshowdata.getRowCount() - 1;

				for (int i = 0; i < tableloanshowdata.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableloanshowdata.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableloanshowdata.getSelectedRow();
						int col = tableloanshowdata.getSelectedColumn();
						tableloanshowdata.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableloanshowdata.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableloanshowdata.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableloanshowdata.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableloanshowdata.setValueAt((Object) s, 0, 0);
							tableloanshowdata.setValueAt((Object) s1, row, 0);

						}
					}

					else {
					}
				}

			}
		});
		tableloanshowdata.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableloanshowdata.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Select", "SrnoMaster", "Srno", "Date", "Update Date", "A/C No", "Name", "Int %",
						"Balance", "Interst Amt", "Total", "Share Interst", "Share Int Amt", "FormFee", "Statio Amt",
						"Total Rem Amt", "Monthly Pay Amt", "Duration", "Days", "Loan Against", "Guaranteed1",
						"Relation1", "Guaranteed1", "Relation2", "Nomeny", "Nomeny Rel", "Address", "Age", "Contact No",
						"File Path", "Notes" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class, Object.class, Object.class, Byte.class, Object.class,
					Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class, Byte.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableloanshowdata.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableloanshowdata.getColumnModel().getColumn(0).setMinWidth(50);
		tableloanshowdata.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableloanshowdata.getColumnModel().getColumn(1).setMinWidth(0);
		tableloanshowdata.getColumnModel().getColumn(1).setMaxWidth(0);
		tableloanshowdata.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableloanshowdata.getColumnModel().getColumn(2).setMinWidth(50);
		tableloanshowdata.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(3).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(3).setMaxWidth(80);
		tableloanshowdata.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(4).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(5).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(5).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(6).setPreferredWidth(150);
		tableloanshowdata.getColumnModel().getColumn(6).setMinWidth(150);
		tableloanshowdata.getColumnModel().getColumn(7).setPreferredWidth(50);
		tableloanshowdata.getColumnModel().getColumn(7).setMinWidth(50);
		tableloanshowdata.getColumnModel().getColumn(7).setMaxWidth(100);
		tableloanshowdata.getColumnModel().getColumn(8).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(8).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(8).setMaxWidth(100);
		tableloanshowdata.getColumnModel().getColumn(9).setPreferredWidth(0);
		tableloanshowdata.getColumnModel().getColumn(9).setMinWidth(0);
		tableloanshowdata.getColumnModel().getColumn(9).setMaxWidth(0);
		tableloanshowdata.getColumnModel().getColumn(10).setPreferredWidth(0);
		tableloanshowdata.getColumnModel().getColumn(10).setMinWidth(0);
		tableloanshowdata.getColumnModel().getColumn(10).setMaxWidth(0);
		tableloanshowdata.getColumnModel().getColumn(11).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(11).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(12).setPreferredWidth(0);
		tableloanshowdata.getColumnModel().getColumn(12).setMinWidth(0);
		tableloanshowdata.getColumnModel().getColumn(12).setMaxWidth(0);
		tableloanshowdata.getColumnModel().getColumn(13).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(13).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(14).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(14).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(15).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(15).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(17).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(17).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(19).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(19).setMinWidth(8);
		tableloanshowdata.getColumnModel().getColumn(20).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(20).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(21).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(21).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(22).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(22).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(23).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(23).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(24).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(24).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(25).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(25).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(26).setPreferredWidth(150);
		tableloanshowdata.getColumnModel().getColumn(26).setMinWidth(150);
		tableloanshowdata.getColumnModel().getColumn(28).setPreferredWidth(80);
		tableloanshowdata.getColumnModel().getColumn(28).setMinWidth(80);
		tableloanshowdata.getColumnModel().getColumn(29).setPreferredWidth(150);
		tableloanshowdata.getColumnModel().getColumn(29).setMinWidth(150);
		tableloanshowdata.getColumnModel().getColumn(30).setPreferredWidth(150);
		tableloanshowdata.getColumnModel().getColumn(30).setMinWidth(150);
		scrollPane.setViewportView(tableloanshowdata);
		showdata();

		panel_4.setLayout(gl_panel_4);

		JButton btnReport = new JButton("Report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					for (int i = 0; i <= tableloanshowdata.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableloanshowdata.getValueAt(i, 0).toString());
						if (che) {
							String accno4 = String.valueOf(tableloanshowdata.getValueAt(i, 5).toString());

							java.io.InputStream file = getClass().getResourceAsStream("/Reports/Loan.jrxml");
							accno = String.valueOf(tableloanshowdata.getValueAt(i, 5).toString());
							HashMap para = new HashMap();
							para.put("AccountNo", accno4);
							allinonereport r = new allinonereport("Loan Report", file, para);

						}

					}
				} catch (Exception ew) {
					ew.printStackTrace();
				}

			}
		});
		btnReport.setIcon(new ImageIcon(LoanA_C.class.getResource("/ImageButtonIcon/Report4d.png")));
		btnReport.setHorizontalAlignment(SwingConstants.LEADING);
		btnReport.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReport.setBounds(10, 655, 150, 38);
		contentPane.add(btnReport);

		JButton button = new JButton("View Transaction");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					for (int i = 0; i <= tableloanshowdata.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableloanshowdata.getValueAt(i, 0).toString());
						if (che) {
							accno = String.valueOf(tableloanshowdata.getValueAt(i, 5).toString());
							TranLoan2 tr = new TranLoan2();
							tr.setUndecorated(true);
							tr.setVisible(true);
							break;

						}
					}
				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(LoanA_C.class.getResource("/ImageButtonIcon/TrancationView.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(170, 655, 194, 38);
		contentPane.add(button);

		JLabel lblNewLabel_1 = new JLabel("Total Balance:");
		lblNewLabel_1.setBounds(904, 658, 86, 14);
		contentPane.add(lblNewLabel_1);

		textFieldTotalAmountB = new JTextField();
		textFieldTotalAmountB.setForeground(Color.RED);
		textFieldTotalAmountB.setEditable(false);
		textFieldTotalAmountB.setBounds(983, 655, 132, 20);
		contentPane.add(textFieldTotalAmountB);
		textFieldTotalAmountB.setColumns(10);

	}

	public void reset() {
		try {
			increment();
			SimpleDateFormat insdf = new SimpleDateFormat("yyyy-MM-dd");
			Date indate = new Date();
			String instringdate = insdf.format(indate);

			dateChooserloangpage.setDate(insdf.parse(instringdate));

			textField_Nomeny_1.setText("Other");
			textFieldGarenterOther1.setText("Other");
			textFieldGarenterOther2.setText("Other");
			textFieldShareInterst.setText("");
			textFieldShareIntestAmt.setText("");
			textFieldFormFee.setText("");
			textFieldStationnaryAmt.setText("");
			textFieldTotalRemaningAmt.setText("");
			textFieldNote.setText("");
			textFieldDays.setText("");
			textField_Nomeny.setText("");
			textFieldGarenter2.setText("");
			textFieldName.setText("");
			textFieldAddress.setText("");
			textFieldAge.setText("0");
			textFieldContatNo.setText("");
			textFieldLoanAgaisntInterrst.setText("");
			textFieldDuration.setText("");
			textFieldMonthlyInst.setText("0");

			textFieldAmount.setText("0");
			textFieldInterst.setText("0");
			textFieldTotal.setText("0");
			textFieldGarenter1.setText("");
			textFieldFilePath.setText("path");

			comboBox_NomenyRelation.setSelectedIndex(0);
			comboBoxRelatiton1.setSelectedIndex(0);
			comboBoxDuration.setSelectedIndex(0);
			comboBoxRelatiton2.setSelectedIndex(0);
			textFieldShareInterst.setText("0");
			textFieldShareIntestAmt.setText("0");
			textFieldStationnaryAmt.setText("0");
			textFieldTotalRemaningAmt.setText("0");
		} catch (Exception es) {
			System.out.println(es.getMessage());
		}

	}

	public void increment() {
		try {

			conn = DBConnection.getConnection();
			ps1 = conn.prepareStatement("select max(SrnoMaster) from banksystem.loan;");
			rs = ps1.executeQuery();
			while (rs.next()) {
				int val = (rs.getInt(1) + 1);
				val1 = String.valueOf(val);

			}
			textFieldMasterSrno.setText(val1);
			textFieldSrNo.setText(val1);
			textFieldAccountNumber.setText("L" + val1);

		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		} finally {
			try {
				rs.close();
				ps1.close();
				conn.close();
			} catch (Exception ewww) {
				System.out.println(ewww.getMessage());
			}
		}
	}

	public void Calculation() {
		String ins = textFieldInterst.getText();
		if (ins.equals("0")) {
			textFieldTotal.setText(textFieldAmount.getText());

		} else {

			double amount = Double.valueOf(textFieldAmount.getText());
			double interst = Double.valueOf(textFieldInterst.getText());
			double totla = amount * interst / 100;
			textFieldInterstAmt.setText(String.valueOf(totla));
			textFieldTotal.setText(String.valueOf(amount + totla));

			double shareinterst = Double.valueOf(textFieldShareInterst.getText());

			double shareinterstamt = amount * shareinterst / 100;
			textFieldShareIntestAmt.setText(String.valueOf(shareinterstamt));

			double formfee = Double.valueOf(textFieldFormFee.getText());
			double stattionaryamt = Double.valueOf(textFieldStationnaryAmt.getText());
			double subotheramt = shareinterstamt + formfee + stattionaryamt;
			double remaningamt = amount - subotheramt;
			textFieldTotalRemaningAmt.setText(String.valueOf(remaningamt));
		}
	}

	public void showdata() {
		try {

			conn = DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.loan order by Srnomaster;";
			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data0 = rs.getString("Srnomaster");
				String data1 = rs.getString("Srno");
				String data2 = rs.getString("date");
				String data3 = rs.getString("UpdateAmtDate");
				String data4 = rs.getString("AccountNumber");
				String data5 = rs.getString("Name");
				String data6 = rs.getString("Interst");
				String dataamt = rs.getString("Amount");
				double amout = Double.valueOf(dataamt);
				DecimalFormat twoDForm = new DecimalFormat("#.##");
				double datashow = Double.valueOf(twoDForm.format(amout));
				String data7 = String.valueOf(datashow);
				String data8 = rs.getString("InstersAmt");
				String data9 = rs.getString("Total");
				String data10 = rs.getString("ShareInterst");
				String data11 = rs.getString("ShareInterstAmt");
				String data12 = rs.getString("FormFee");
				String data13 = rs.getString("StationaryAmt");
				String data14 = rs.getString("TotalRemaningFee");
				String data15 = rs.getString("MonthlyInst");
				String data16 = rs.getString("Duration");
				String data17 = rs.getString("Days");
				String data18 = rs.getString("LoanAgainst");
				String data19 = rs.getString("Gaurentr1");
				String data20 = rs.getString("Relation1");
				String data21 = rs.getString("Gaurentr2");
				String data22 = rs.getString("Relation2");
				String data23 = rs.getString("Nomeny");
				String data24 = rs.getString("NomeyRelation");
				String data25 = rs.getString("Address");
				String data26 = rs.getString("Age");
				String data27 = rs.getString("ContactNo");
				String data28 = rs.getString("FilePath");
				String data29 = rs.getString("Note");
				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12, data13, data14, data15, data16, data17, data18, data19, data20, data21,
						data22, data23, data24, data25, data26, data27, data28, data29 };
				model1 = (DefaultTableModel) tableloanshowdata.getModel();
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

	public void daycount(String str) {
		try
		{
		String number = "";
		String letter = "";
		String symbol = "";
		String firstLetter;
		textFieldDuration.setText(str);
		for (int i = 0; i < str.length(); i++) {
			char a = str.charAt(i);
			if (Character.isDigit(a)) {
				number = number + a;

			} else {
				letter = letter + a;
			}
		}
		firstLetter = Character.toString(letter.charAt(1));

		if (firstLetter.equals("Y") || firstLetter.equals("y")) {
			double let = Double.valueOf(number);
			double day = let * 365;

			textFieldDays.setText(String.valueOf(day));

		} else if (firstLetter.equals("M") || firstLetter.equals("m")) {
			double let = Double.valueOf(number);
			double day = let * 30;

			textFieldDays.setText(String.valueOf(day));

		} else {
			double let = Double.valueOf(number);
			double day = let * 30;

			textFieldDays.setText(String.valueOf(day));
		}
		}
		catch(Exception ees)
		{
			System.out.println(ees.getMessage());
		}
	}

	public static void amountupdate() {

		try {
			DecimalFormat dff = new DecimalFormat("#.##");
			conn = DBConnection.getConnection();
			String amount = "select * from banksystem.loan order by Srnomaster;";
			ps = conn.prepareStatement(amount);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data44 = rs.getString("UpdateAmtDate");
				String newName = data44.substring(8, 10);

				String srnomasteraa = rs.getString("Srnomaster");
				String custname = rs.getString("Name");
				String acno = rs.getString("AccountNumber");
				double amountloan = rs.getDouble("Amount");
				double maramt = rs.getDouble("Total");
				double intesrtlo = rs.getDouble("Interst");
				double intestamtlo = rs.getDouble("InstersAmt");
				int code = rs.getInt("Code");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date datecur = new Date();
				String sysdate = (String) sdf.format(datecur);

				Calendar c = Calendar.getInstance();
				int lastDate = c.getActualMaximum(Calendar.DATE);
				String countdateinlastmont = String.valueOf(lastDate);

				Date curdatedate = sdf.parse(sysdate);
				java.sql.Date sqlDate = new java.sql.Date(curdatedate.getTime());

				String curantdate = sdf.format(datecur);
				String shortcurdate = curantdate.substring(8, 10);

				int twodedate = Integer.parseInt(shortcurdate);

				if (lastDate == 28 && twodedate == 28 || lastDate == 29 && twodedate == 29
						|| lastDate == 30 && twodedate == 30 || lastDate == 31 && twodedate == 31) {

					Date curdatedate44 = new Date();
					Date dbdate = sdf.parse(data44);
					if (!data44.equals(sysdate)) {
						Calendar cq = Calendar.getInstance();
						int year = cq.get(Calendar.YEAR);
						int month = cq.get(Calendar.MONTH);
						int day = 1;
						cq.set(year, month, day);
						int numOfDaysInMonth = cq.getActualMaximum(Calendar.DAY_OF_MONTH);
						SimpleDateFormat sdfq = new SimpleDateFormat("yyyy-MM-dd");
						cq.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);
						String indate = sdfq.format(cq.getTime());
						Date parsed = (Date) sdfq.parse(indate);
						java.sql.Date sql7777 = new java.sql.Date(parsed.getTime());

						long diff = curdatedate44.getTime() - dbdate.getTime();
						long totalday44 = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

						Double countdayinner = (double) totalday44;
						double amountis = amountloan * intesrtlo / 100;

						double onedayamount = (amountloan + amountis);
						double yearoneday = onedayamount / 365;

						double totaldayamt = yearoneday * countdayinner;
						double sumaountouter = totaldayamt + amountloan;
						double interstcontin = sumaountouter * intesrtlo / 100;
						double sumsumamt = sumaountouter + interstcontin;

						String up12 = "UPDATE banksystem.loan set UpdateAmtDate='" + (java.sql.Date) sqlDate
								+ "',Amount='" + dff.format(sumaountouter) + "',InstersAmt='"
								+ dff.format(interstcontin) + "',Total='" + dff.format(sumsumamt) + "',LastDate='"
								+ (java.sql.Date) sql7777 + "',Code=" + 5 + " where Srnomaster=" + srnomasteraa + "";
						ps6 = conn.prepareStatement(up12);
						int ii = ps6.executeUpdate();

						if (ii > 0) {
							try {
								int isrno = 0;
								int srnomaster = 0;
								String maxno = "select max(SrNo),SrNoMaster from banksystem.loantranscation where AccountNo='"
										+ acno + "';";
								ps1 = conn.prepareStatement(maxno);
								rs1 = ps1.executeQuery();
								while (rs1.next()) {
									isrno = rs1.getInt(1) + 1;
									srnomaster = rs1.getInt("SrNoMaster");

								}

								String insertdata = "insert into banksystem.loantranscation (SrNoMaster,SrNo,Date,Name,AccountNo, TransactionParticulars, Amount, Balance) values(?,?,?,?,?,?,?,?);";
								ps2 = conn.prepareStatement(insertdata);
								ps2.setInt(1, srnomaster);
								ps2.setInt(2, isrno);
								ps2.setDate(3, (java.sql.Date) sqlDate);
								ps2.setString(4, custname);
								ps2.setString(5, acno);
								ps2.setString(6, "Monthly Intsert=" + intesrtlo + "%");
								ps2.setDouble(7, sumaountouter);
								ps2.setDouble(8, sumsumamt);
								ps2.executeUpdate();
								ps2.close();

							} catch (Exception es) {
								System.out.print(es.getMessage());
							} finally {
								try {
									rs.close();
									rs1.close();
									ps.close();
									ps1.close();
									ps2.close();
									ps6.close();
									conn.close();
								} catch (Exception ewww) {
									System.out.println(ewww.getMessage());
								}
							}
						}
					}
				}
			}

		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		}

		try {
			DecimalFormat dff = new DecimalFormat("#.##");
			conn = DBConnection.getConnection();
			String amount = "select * from banksystem.loan order by Srnomaster;";
			ps = conn.prepareStatement(amount);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data44 = rs.getString("UpdateAmtDate");
				String newName = data44.substring(8, 10);

				String srnomasteraa = rs.getString("Srnomaster");
				String custname = rs.getString("Name");
				String acno = rs.getString("AccountNumber");
				double amountloan = rs.getDouble("Amount");
				double maramt = rs.getDouble("Total");
				double intesrtlo = rs.getDouble("Interst");
				double intestamtlo = rs.getDouble("InstersAmt");
				int code = rs.getInt("Code");

				String lastdateloan = rs.getString("LastDate");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date datecur = new Date();
				String sysdate = (String) sdf.format(datecur);

				Calendar c = Calendar.getInstance();
				int lastDate = c.getActualMaximum(Calendar.DATE);
				String countdateinlastmont = String.valueOf(lastDate);

				Date curdatedate = sdf.parse(sysdate);
				java.sql.Date sqlDate = new java.sql.Date(curdatedate.getTime());

				String curantdate = sdf.format(datecur);
				String shortcurdate = curantdate.substring(8, 10);

				int twodedate = Integer.parseInt(shortcurdate);

				//// -----------------month
				//// lastdate---------------------------////
				Calendar cq = Calendar.getInstance();
				int year = cq.get(Calendar.YEAR);
				int month = cq.get(Calendar.MONTH);
				int day = 1;
				cq.set(year, month, day);
				int numOfDaysInMonth = cq.getActualMaximum(Calendar.DAY_OF_MONTH);
				SimpleDateFormat sdfq = new SimpleDateFormat("yyyy-MM-dd");
				cq.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);
				String indate = sdfq.format(cq.getTime());
				Date parsed = (Date) sdfq.parse(indate);
				java.sql.Date sql7777 = new java.sql.Date(parsed.getTime());

				/// -----------------------------monthlastdate-------------------////

				//// -----------------------before month last
				//// date------------------/////
				Calendar aCalendar = Calendar.getInstance();
				// add -1 month to current month
				aCalendar.add(Calendar.MONTH, -1);
				// set DATE to 1, so first date of previous month
				aCalendar.set(Calendar.DATE, 1);

				Date firstDateOfPreviousMonth = aCalendar.getTime();

				// set actual maximum date of previous month
				aCalendar.set(Calendar.DATE, aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				// read it
				Date lastDateOfPreviousMonth = aCalendar.getTime();
				String beforelastdate = sdf.format(lastDateOfPreviousMonth);

				//// -----------------------before month last
				//// date-----------------/////

				if (!lastdateloan.equals(indate)) {

					if (!beforelastdate.equals(lastdateloan)) {
						if (!beforelastdate.equals(data44) && code > 1) {

							Date curdatedate44 = new Date();
							Date dbdate = sdf.parse(data44);
							long diff = curdatedate44.getTime() - dbdate.getTime();
							long totalday44 = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

							Double countdayinner = (double) totalday44;
							double amountis = amountloan * intesrtlo / 100;

							double onedayamount = (amountloan + amountis);
							double yearoneday = onedayamount / 365;

							double totaldayamt = yearoneday * countdayinner;
							double sumaountouter = totaldayamt + amountloan;
							double interstcontin = sumaountouter * intesrtlo / 100;
							double sumsumamt = sumaountouter + interstcontin;

							String up12 = "UPDATE banksystem.loan set UpdateAmtDate='" + (java.sql.Date) sqlDate
									+ "',Amount='" + dff.format(sumaountouter) + "',InstersAmt='"
									+ dff.format(interstcontin) + "',Total='" + dff.format(sumsumamt) + "',LastDate='"
									+ (java.sql.Date) sql7777 + "' where Srnomaster=" + srnomasteraa + "";
							ps6 = conn.prepareStatement(up12);
							int ii = ps6.executeUpdate();

							if (ii > 0) {

								try {
									int isrno = 0;
									int srnomaster = 0;
									String maxno = "select max(SrNo),SrNoMaster from banksystem.loantranscation where AccountNo='"
											+ acno + "';";
									ps1 = conn.prepareStatement(maxno);
									rs1 = ps1.executeQuery();
									while (rs1.next()) {
										isrno = rs1.getInt(1) + 1;
										srnomaster = rs1.getInt("SrNoMaster");

									}

									String insertdata = "insert into banksystem.loantranscation (SrNoMaster,SrNo,Date,Name,AccountNo, TransactionParticulars, Amount, Balance) values(?,?,?,?,?,?,?,?);";
									ps2 = conn.prepareStatement(insertdata);
									ps2.setInt(1, srnomaster);
									ps2.setInt(2, isrno);
									ps2.setDate(3, (java.sql.Date) sqlDate);
									ps2.setString(4, custname);
									ps2.setString(5, acno);
									ps2.setString(6, "Monthly Intsert=" + intesrtlo + "%");
									ps2.setDouble(7, sumaountouter);
									ps2.setDouble(8, sumsumamt);
									ps2.executeUpdate();
									ps2.close();

								} catch (Exception es) {
									System.out.print(es.getMessage());
								}

								finally {
									try {
										rs.close();
										rs1.close();
										ps.close();
										ps1.close();
										ps2.close();
										ps3.close();
										conn.close();
									} catch (Exception ewww) {
										System.out.println(ewww.getMessage());
									}
								}
							}
						}
					}
				}
			}

		} catch (Exception esw) {
			System.out.println(esw.getMessage());
		}

	}

	public void totalamount() {
		int rowsCount = tableloanshowdata.getRowCount();
		double sum = 0;
		double amttotal = 0;
		for (int i = 0; i < rowsCount; i++) {
			sum = sum + Double.valueOf(tableloanshowdata.getValueAt(i, 8).toString());

		}
		textFieldTotalAmountB.setText(String.valueOf(df.format(sum)));

	}
}
