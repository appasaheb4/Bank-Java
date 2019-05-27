package Pages;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.toedter.calendar.JDateChooser;

import CLASS.CalculatorGUI;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JList;
import java.awt.event.MouseMotionAdapter;

public class SavingA_C extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldAddress;
	private JTextField textFieldAge;
	private JTable tablesavingdatashow;
	private JTextField textFieldFilePath;
	private JTextField textFieldAccountCategray;
	private JTextField textFieldSrno;
	private JTextField textFieldSrNoMaster;
	public Connection conn;
	public PreparedStatement ps;
	public PreparedStatement ps1;
	public PreparedStatement ps2;
	public ResultSet rs;
	public ResultSet rs1;
	public String val1;
	public File f;
	java.util.Date dt1, dt2;
	public DefaultTableModel model1;
	public JComboBox comboBoxAcountCatagry;
	private JTextField textFieldAmout;
	private JTextField textFieldIntrestPer;
	private JTextField textFieldIntrestAmt;
	private JTextField textFieldTotal;
	public JTextArea textFieldNote;
	private JTextField textFieldContactNo;
	private JTextField textFieldAcountNO;
	private JTextField textFieldNomeny;
	private JTextField textFieldNomenyOther;
	public JComboBox comboBoxNomeny;
	public static int issrno;
	public JPanel panel_JoinAccount;
	private JTextField textFieldJoName;
	private JTextField textFieldJoAge;
	private JTextField textFieldJoContactNo;
	private JTextField textFieldJoRelationOther;
	private JTextField textFieldOtherAmount;
	public JDateChooser dateChooserSavingDate;
	public JButton buttonBrowger;
	public JList<String> listname;
	public String[] data = new String[1000];
	public JScrollPane scrollPaneName;
	public DecimalFormat dff = new DecimalFormat("#.##");
	public static String accno;

	public JScrollPane scrollPane_1;
	private JTextField textFieldTotalAmtB;
	private JTextField textFieldBalance;
	public JPanel panel_info2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SavingA_C frame = new SavingA_C();
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
	public SavingA_C() {
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

		JLabel lblSavingAc = new JLabel("Saving A/C");
		lblSavingAc.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSavingAc.setBounds(32, 11, 276, 42);
		panel.add(lblSavingAc);

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
		dateChooserSavingDate = new JDateChooser();
		dateChooserSavingDate.setDateFormatString("yyyy-MM-dd");
		dateChooserSavingDate.setBounds(854, 45, 196, 20);
		dateChooserSavingDate.setDate(date);
		panel.add(dateChooserSavingDate);

		textFieldSrno = new JTextField();
		textFieldSrno.setEnabled(false);
		textFieldSrno.setVisible(false);
		textFieldSrno.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldSrno.setColumns(10);
		textFieldSrno.setBounds(187, 30, 209, 23);
		panel.add(textFieldSrno);

		textFieldSrNoMaster = new JTextField();
		textFieldSrNoMaster.setEnabled(false);
		textFieldSrNoMaster.setVisible(false);
		textFieldSrNoMaster.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldSrNoMaster.setColumns(10);
		textFieldSrNoMaster.setBounds(426, 25, 209, 23);
		increment();
		panel.add(textFieldSrNoMaster);

		JLabel label_9 = new JLabel("Acount Number:");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_9.setBounds(32, 57, 90, 14);
		panel.add(label_9);

		textFieldAcountNO = new JTextField();
		textFieldAcountNO.setEditable(false);
		textFieldAcountNO.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldAcountNO.setColumns(10);
		textFieldAcountNO.setBounds(124, 53, 184, 23);
		increment();
		DurationIncrement();
		panel.add(textFieldAcountNO);

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
		button_1.setIcon(new ImageIcon(SavingA_C.class.getResource("/ImageButtonIcon/clac.png")));
		button_1.setOpaque(false);
		button_1.setForeground(Color.RED);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		button_1.setBounds(1079, 8, 64, 55);
		panel.add(button_1);

		JPanel panel_info = new JPanel();
		panel_info.setLayout(null);
		panel_info.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_info.setBounds(0, 87, 1154, 197);
		contentPane.add(panel_info);

		JLabel label = new JLabel("Name:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(10, 9, 81, 17);
		panel_info.add(label);

		textFieldName = new JTextField();

		textFieldName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {

					conn = (Connection) DBConnection.getConnection();
					String query = "select * from banksystem.saving where Name like '" + textFieldName.getText() + "%'";
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
					comboBoxAcountCatagry.setVisible(false);
					textFieldAmout.setVisible(false);
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

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == 40) {
					listname.requestFocus();
				}
			}
		});
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldName.setColumns(10);
		textFieldName.setBounds(108, 6, 209, 23);
		panel_info.add(textFieldName);

		JLabel label_2 = new JLabel("Address:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(326, 9, 81, 17);
		panel_info.add(label_2);

		textFieldAddress = new JTextField();
		textFieldAddress.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				scrollPaneName.setVisible(false);
				listname.setVisible(false);
				// comboBoxAcountCatagry.setVisible(true);
				textFieldAmout.setVisible(true);
				textFieldNomeny.setVisible(true);
				scrollPane_1.setVisible(true);
			}
		});
		textFieldAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(401, 6, 209, 23);
		panel_info.add(textFieldAddress);

		JLabel label_3 = new JLabel("Age:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(620, 9, 81, 17);
		panel_info.add(label_3);

		textFieldAge = new JTextField();
		textFieldAge.setText("0");
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
		textFieldAge.setBounds(681, 9, 181, 23);
		panel_info.add(textFieldAge);

		JLabel lblLoanCatogeryInterrst = new JLabel("Account Catagery:");
		lblLoanCatogeryInterrst.setVisible(false);
		lblLoanCatogeryInterrst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLoanCatogeryInterrst.setBounds(8, 163, 106, 17);
		panel_info.add(lblLoanCatogeryInterrst);

		JLabel lblDocumentFileUpload = new JLabel("Document file upload:");
		lblDocumentFileUpload.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDocumentFileUpload.setBounds(618, 105, 111, 17);
		panel_info.add(lblDocumentFileUpload);

		JLabel lblNote = new JLabel("Note:");
		lblNote.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNote.setBounds(8, 105, 43, 17);
		panel_info.add(lblNote);

		JPanel panel_path = new JPanel();
		panel_path.setLayout(null);
		panel_path.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_path.setBounds(677, 121, 307, 44);
		panel_info.add(panel_path);

		textFieldFilePath = new JTextField();
		textFieldFilePath.setText("path");
		textFieldFilePath.setEnabled(false);
		textFieldFilePath.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldFilePath.setColumns(10);
		textFieldFilePath.setBounds(10, 13, 229, 23);
		panel_path.add(textFieldFilePath);

		buttonBrowger = new JButton("Browse");
		buttonBrowger.setFont(new Font("Tahoma", Font.PLAIN, 9));
		buttonBrowger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(contentPane);
				f = fc.getSelectedFile();
				String path = f.getAbsolutePath();
				textFieldFilePath.setText(path);
			}
		});
		buttonBrowger.setBounds(233, 13, 74, 23);
		panel_path.add(buttonBrowger);

		textFieldAccountCategray = new JTextField();
		textFieldAccountCategray.setVisible(false);
		textFieldAccountCategray.setEnabled(false);
		textFieldAccountCategray.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldAccountCategray.setText("");
			}
		});

		textFieldAccountCategray.setText("Other");
		textFieldAccountCategray.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAccountCategray.setColumns(10);
		textFieldAccountCategray.setBounds(399, 162, 209, 23);
		panel_info.add(textFieldAccountCategray);

		comboBoxAcountCatagry = new JComboBox();
		comboBoxAcountCatagry.setVisible(false);
		comboBoxAcountCatagry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String st = comboBoxAcountCatagry.getSelectedItem().toString();
				daycount(st);
			}
		});
		comboBoxAcountCatagry.setModel(new DefaultComboBoxModel(new String[] { "Select", "A", "B", "C", "D", "E" }));
		comboBoxAcountCatagry.setBounds(106, 163, 209, 23);
		panel_info.add(comboBoxAcountCatagry);

		JCheckBox checkBoxaccountcatagery = new JCheckBox("");
		checkBoxaccountcatagery.setVisible(false);
		checkBoxaccountcatagery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click();
			}

			private void click() {
				if (!checkBoxaccountcatagery.isSelected()) {
					comboBoxAcountCatagry.setEnabled(true);
					textFieldAccountCategray.setEnabled(false);
					textFieldAccountCategray.setText("");
				} else {
					comboBoxAcountCatagry.setEnabled(false);
					textFieldAccountCategray.setEnabled(true);
					String me = "";
					comboBoxAcountCatagry.setSelectedIndex(0);
				}

			}
		});
		checkBoxaccountcatagery.setBounds(325, 162, 27, 23);
		panel_info.add(checkBoxaccountcatagery);

		JLabel lblOpningAmount = new JLabel("Total Amount:");
		lblOpningAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOpningAmount.setBounds(8, 47, 106, 17);
		panel_info.add(lblOpningAmount);

		textFieldAmout = new JTextField();
		textFieldAmout.addKeyListener(new KeyAdapter() {
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
		textFieldAmout.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAmout.setColumns(10);
		textFieldAmout.setBounds(106, 44, 209, 23);
		panel_info.add(textFieldAmout);

		JLabel lblInterst = new JLabel("Interst %:");
		lblInterst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblInterst.setBounds(324, 44, 64, 17);
		panel_info.add(lblInterst);

		textFieldIntrestPer = new JTextField();
		textFieldIntrestPer.addKeyListener(new KeyAdapter() {
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
		textFieldIntrestPer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldIntrestPer.setColumns(10);
		textFieldIntrestPer.setBounds(399, 41, 106, 23);
		panel_info.add(textFieldIntrestPer);

		textFieldIntrestAmt = new JTextField();
		textFieldIntrestAmt.setVisible(false);
		textFieldIntrestAmt.setForeground(Color.RED);
		textFieldIntrestAmt.setEditable(false);
		textFieldIntrestAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldIntrestAmt.setColumns(10);
		textFieldIntrestAmt.setBounds(724, 42, 43, 23);
		panel_info.add(textFieldIntrestAmt);

		JLabel lblAnnualTotalAmount = new JLabel("Annual Total Amount:");
		lblAnnualTotalAmount.setVisible(false);
		lblAnnualTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAnnualTotalAmount.setBounds(777, 46, 43, 14);
		panel_info.add(lblAnnualTotalAmount);

		textFieldTotal = new JTextField();
		textFieldTotal.setVisible(false);
		textFieldTotal.setForeground(Color.RED);
		textFieldTotal.setEditable(false);
		textFieldTotal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldTotal.setColumns(10);
		textFieldTotal.setBounds(825, 38, 35, 23);
		panel_info.add(textFieldTotal);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(105, 103, 461, 62);
		panel_info.add(scrollPane_1);

		textFieldNote = new JTextArea();
		textFieldNote.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == 9) {
					buttonBrowger.requestFocus();
				}
			}
		});
		textFieldNote.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane_1.setViewportView(textFieldNote);

		JLabel label_4 = new JLabel("Contact No:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(872, 12, 81, 17);
		panel_info.add(label_4);

		textFieldContactNo = new JTextField();
		textFieldContactNo.addKeyListener(new KeyAdapter() {
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
		textFieldContactNo.setBounds(951, 9, 193, 23);
		panel_info.add(textFieldContactNo);

		JLabel label_6 = new JLabel("Nomeny:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(8, 75, 106, 17);
		panel_info.add(label_6);

		textFieldNomeny = new JTextField();
		textFieldNomeny.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldNomeny.setColumns(10);
		textFieldNomeny.setBounds(106, 75, 209, 23);
		panel_info.add(textFieldNomeny);

		JLabel label_10 = new JLabel("Relation:");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_10.setBounds(324, 78, 74, 17);
		panel_info.add(label_10);

		comboBoxNomeny = new JComboBox();
		comboBoxNomeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String name = comboBoxNomeny.getSelectedItem().toString();
				textFieldNomenyOther.setText("");
				textFieldNomenyOther.setText(name);
				}
				catch(Exception ww)
				{
					System.out.println(ww.getMessage());
				}
			}
		});
		comboBoxNomeny.setModel(new DefaultComboBoxModel(new String[] {"   Select", "    Father", "    Mother", "   Paternal Grandfather", "    Maternal Grandfather ", "    Maternal Grandmother", "    Brother", "    Brother's Wife", "    Elder Brother", "    Younger Brother", "    Husband's sister", "    Sister", "    Sister's husband", "    Elder Sister", "    Younger Sister)", "    Husband's elder brother", "    Husband's younger brother", "    Elder brother's wife", "    Younger brother's wife", "    Wife's Sister", "    Wife's Brother", "    Wife's Brother's wife", "    Husband's Sister's Husband", "    Wife's sister's husband", "    Husband's elder brother's wife", "    Husband's younger brother's wife", "    Son", "    Son's wife (daughter-in-law) ", "    Daughter ", "    Daughter's husband (son-in-law) ", "    Grandson (son's son)", "    Granddaughter (son's daughter)", "    Grandson (daughter's son)", "    Granddaughter (daughter's daughter)", "    Husband", "    Wife", "    Husband's Mother (Mother-in-law)", "    Husband's Father (Father-in-law)", "    Fianc\u00E9 or Fianc\u00E9e", "    Father's younger brother (uncle)", "    Father's younger brother's wife (aunt)", "    Father's elder brother's (Uncle)", "    Father's elder brother's wife (Aunt) ", "    Father's sister (aunt)", "    Father's sister's husband", "    Mother's brother", "    Mother's brother's wife", "    Mother's sister", "    Mother's sister's husband", "    Brother's son (nephew)", "    Sister's son (nephew)", "    Brother's daughter (niece)", "    Sister's daughter (niece) ", "    Husband's elder brother's daughter", "    Husband's elder brother's son"}));
		comboBoxNomeny.setBounds(399, 75, 176, 23);
		panel_info.add(comboBoxNomeny);

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
		checkBoxNomeny.setBounds(581, 75, 27, 23);
		panel_info.add(checkBoxNomeny);

		JLabel label_11 = new JLabel("Other:");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_11.setBounds(616, 78, 81, 17);
		panel_info.add(label_11);

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
		textFieldNomenyOther.setBounds(679, 75, 181, 23);
		panel_info.add(textFieldNomenyOther);

		JLabel lblOtherAmount = new JLabel("Other Amount:");
		lblOtherAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOtherAmount.setBounds(870, 42, 111, 14);
		panel_info.add(lblOtherAmount);

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
		textFieldOtherAmount.setBounds(949, 38, 193, 23);
		panel_info.add(textFieldOtherAmount);

		scrollPaneName = new JScrollPane();
		scrollPaneName.setVisible(false);
		scrollPaneName.setBounds(108, 30, 209, 94);
		panel_info.add(scrollPaneName);

		listname = new JList<String>();
		listname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldName.setText(listname.getSelectedValue());
					String accountnotop = textFieldName.getText();

					scrollPaneName.setVisible(false);
					listname.setVisible(false);
					// comboBoxAcountCatagry.setVisible(true);
					textFieldAmout.setVisible(true);
					textFieldNomeny.setVisible(true);
					scrollPane_1.setVisible(true);

					try {
						conn = (Connection) DBConnection.getConnection();
						String query = "Select * from banksystem.saving where Name=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldName.getText());
						rs = ps.executeQuery();
						while (rs.next()) {

							String Srnomaster = rs.getString("SrnoMaster");
							textFieldSrNoMaster.setText(Srnomaster);
							String UpdateAmtDate = rs.getString("UpdateAmtDate");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date dd = sdf.parse(UpdateAmtDate);
							dateChooserSavingDate.setDate(dd);

							String Name = rs.getString("Name");
							textFieldName.setText(Name);
							String AcountNumber = rs.getString("AcountNumber");
							textFieldAcountNO.setText(AcountNumber);
							String Address = rs.getString("Address");
							textFieldAddress.setText(Address);
							String Age = rs.getString("Age");
							textFieldAge.setText(Age);
							String ContactNo = rs.getString("ContactNo");
							textFieldContactNo.setText(ContactNo);
							String LoanAgainst = rs.getString("AccountCatogery");
							textFieldAccountCategray.setText(LoanAgainst);

							String Duration = rs.getString("OpningAmount");
							textFieldBalance.setText(Duration);
							String Days = rs.getString("Interist");
							textFieldIntrestPer.setText(Days);
							String Amount = rs.getString("InterestPer");
							textFieldIntrestAmt.setText(Amount);
							String Interst = rs.getString("Total");
							textFieldTotal.setText(Interst);
							String InstersAmt = rs.getString("Nomeny");
							textFieldNomeny.setText(InstersAmt);
							String Total = rs.getString("NomenyRelation");
							textFieldNomenyOther.setText(Total);
							String ShareInterst = rs.getString("FilePath");
							textFieldFilePath.setText(ShareInterst);
							String ShareInterstAmt = rs.getString("Note");
							textFieldNote.setText(ShareInterstAmt);
							String FormFee = rs.getString("JoinName");
							textFieldJoName.setText(FormFee);
							String amount = rs.getString("OtherAmount");
							textFieldOtherAmount.setText(amount);

							String TotalRemaningFee = rs.getString("JoinContactNo");
							textFieldJoContactNo.setText(TotalRemaningFee);

							String Gaurentr1 = rs.getString("JoinRelation");
							textFieldJoRelationOther.setText(Gaurentr1);

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
						conn = (Connection) DBConnection.getConnection();
						String amount = "select Balance from banksystem.savingtranscation  where AccountNo='"
								+ textFieldAcountNO.getText() + "'";
						ps = conn.prepareStatement(amount);
						rs = ps.executeQuery();
						rs.first();
						double aoun = rs.getDouble("Balance");
						textFieldAmout.setText(String.valueOf(dff.format(aoun)));

						Component[] com = panel_info.getComponents();
						Component[] com1 = panel_path.getComponents();
						Component[] com2 = panel_info2.getComponents();
						textFieldNote.setEnabled(false);
						for (int a = 0; a < com.length; a++) {
							com[a].setEnabled(false);

						}
						for (int a = 0; a < com1.length; a++) {
							com1[a].setEnabled(false);
						}
						for (int a = 0; a < com2.length; a++) {
							com2[a].setEnabled(false);
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
		listname.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				textFieldName.setText(listname.getSelectedValue());
				String accountnotop = textFieldName.getText();

				scrollPaneName.setVisible(false);
				listname.setVisible(false);
				// comboBoxAcountCatagry.setVisible(true);
				textFieldAmout.setVisible(true);
				textFieldNomeny.setVisible(true);
				scrollPane_1.setVisible(true);

				try {
					conn = (Connection) DBConnection.getConnection();
					String query = "Select * from banksystem.saving where Name=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldName.getText());
					rs = ps.executeQuery();
					while (rs.next()) {

						String Srnomaster = rs.getString("SrnoMaster");
						textFieldSrNoMaster.setText(Srnomaster);
						String UpdateAmtDate = rs.getString("UpdateAmtDate");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date dd = sdf.parse(UpdateAmtDate);
						dateChooserSavingDate.setDate(dd);
						String Name = rs.getString("Name");
						textFieldName.setText(Name);

						String AcountNumber = rs.getString("AcountNumber");
						textFieldAcountNO.setText(AcountNumber);
						String Address = rs.getString("Address");
						textFieldAddress.setText(Address);
						String Age = rs.getString("Age");
						textFieldAge.setText(Age);
						String ContactNo = rs.getString("ContactNo");
						textFieldContactNo.setText(ContactNo);
						String LoanAgainst = rs.getString("AccountCatogery");
						textFieldAccountCategray.setText(LoanAgainst);

						String Duration = rs.getString("OpningAmount");
						textFieldBalance.setText(Duration);
						String Days = rs.getString("Interist");
						textFieldIntrestPer.setText(Days);
						String Amount = rs.getString("InterestPer");
						textFieldIntrestAmt.setText(Amount);
						String Interst = rs.getString("Total");
						textFieldTotal.setText(Interst);
						String InstersAmt = rs.getString("Nomeny");
						textFieldNomeny.setText(InstersAmt);
						String Total = rs.getString("NomenyRelation");
						textFieldNomenyOther.setText(Total);
						String ShareInterst = rs.getString("FilePath");
						textFieldFilePath.setText(ShareInterst);
						String ShareInterstAmt = rs.getString("Note");
						textFieldNote.setText(ShareInterstAmt);
						String FormFee = rs.getString("JoinName");
						textFieldJoName.setText(FormFee);
						String otheramout = rs.getString("OtherAmount");
						textFieldOtherAmount.setText(otheramout);
						String TotalRemaningFee = rs.getString("JoinContactNo");
						textFieldJoContactNo.setText(TotalRemaningFee);
						String Gaurentr1 = rs.getString("JoinRelation");
						textFieldJoRelationOther.setText(Gaurentr1);

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
					conn = (Connection) DBConnection.getConnection();
					String amount = "select Balance from banksystem.savingtranscation  where AccountNo='"
							+ textFieldAcountNO.getText() + "'";
					ps = conn.prepareStatement(amount);
					rs = ps.executeQuery();
					rs.first();
					double aoun = rs.getDouble("Balance");
					textFieldAmout.setText(String.valueOf(dff.format(aoun)));

					Component[] com = panel_info.getComponents();
					Component[] com1 = panel_path.getComponents();
					Component[] com2 = panel_info2.getComponents();
					textFieldNote.setEnabled(false);
					for (int a = 0; a < com.length; a++) {
						com[a].setEnabled(false);

					}
					for (int a = 0; a < com1.length; a++) {
						com1[a].setEnabled(false);
					}
					for (int a = 0; a < com2.length; a++) {
						com2[a].setEnabled(false);
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
		listname.setVisible(false);
		scrollPaneName.setViewportView(listname);
		
		JLabel lblBalanceAmount = new JLabel("Balance Amount:");
		lblBalanceAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBalanceAmount.setBounds(515, 43, 94, 17);
		panel_info.add(lblBalanceAmount);
		
		textFieldBalance = new JTextField();
		textFieldBalance.setEditable(false);
		textFieldBalance.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldBalance.setColumns(10);
		textFieldBalance.setBounds(607, 40, 106, 23);
		panel_info.add(textFieldBalance);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(0, 345, 1154, 60);
		contentPane.add(panel_2);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = textFieldFilePath.getText();

				if (!textFieldName.getText().equals("") && !textFieldAmout.getText().equals("")) {
					if (!path.equals("path")) {
						try {
							FileInputStream fin = new FileInputStream(f);
							int len = (int) f.length();
							conn = (Connection) DBConnection.getConnection();
							String in = "insert into banksystem.saving  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
							ps = conn.prepareStatement(in);
							ps.setInt(1, Integer.parseInt(textFieldSrNoMaster.getText()));
							ps.setInt(2, Integer.parseInt(textFieldSrno.getText()));
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooserSavingDate.getDate();
							String date = (String) sdf.format(dateChooserSavingDate.getDate());
							java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
							ps.setDate(3, (java.sql.Date) d);
							ps.setDate(4, (java.sql.Date) d);
							ps.setString(5, textFieldName.getText());
							ps.setString(6, textFieldAcountNO.getText());
							ps.setString(7, textFieldAddress.getText());
							ps.setDouble(8, Double.valueOf(textFieldAge.getText()));
							ps.setString(9, textFieldContactNo.getText());
							ps.setString(10, textFieldAccountCategray.getText());
							ps.setDouble(11, Double.valueOf(textFieldAmout.getText()));
							ps.setDouble(12, Double.valueOf(textFieldIntrestPer.getText()));
							ps.setDouble(13, Double.valueOf(textFieldIntrestAmt.getText()));
							ps.setDouble(14, Double.valueOf(textFieldTotal.getText()));
							ps.setString(15, textFieldNomeny.getText());
							ps.setString(16, textFieldNomenyOther.getText());
							ps.setBinaryStream(17, fin, len);
							ps.setString(18, textFieldFilePath.getText());
							ps.setString(19, textFieldNote.getText().toString());
							ps.setString(20, textFieldJoName.getText());
							ps.setString(21, textFieldJoAge.getText());
							ps.setString(22, textFieldJoContactNo.getText());
							ps.setString(23, textFieldJoRelationOther.getText());
							ps.setDouble(24, Double.valueOf(textFieldOtherAmount.getText()));
							int ii = ps.executeUpdate();
							if (ii > 0) {
								JOptionPane.showMessageDialog(null, "Data are saved.");
								tabledataview();
								try {
									conn = (Connection) DBConnection.getConnection();
									String maxno = "select max(SrNoMaster) from banksystem.savingtranscation;";
									ps1 = conn.prepareStatement(maxno);
									rs1 = ps1.executeQuery();
									while (rs1.next()) {
										issrno = rs1.getInt(1) + 1;
									}

									String insertdata = "insert into banksystem.savingtranscation (SrNoMaster, SrNo, Date,Name, AccountNo, TransactionParticulars, Balance) values(?,?,?,?,?,?,?);";
									ps2 = conn.prepareStatement(insertdata);
									ps2.setInt(1, issrno);
									ps2.setInt(2, 1);
									SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
									dt1 = dateChooserSavingDate.getDate();
									String date4 = (String) sdf4.format(dateChooserSavingDate.getDate());
									java.sql.Date d4 = new java.sql.Date(sdf.parse(date4).getTime());
									ps2.setDate(3, (java.sql.Date) d4);
									ps2.setString(4, textFieldName.getText());
									ps2.setString(5, textFieldAcountNO.getText());
									ps2.setString(6, "opening account");
									ps2.setDouble(7, Double.valueOf(textFieldAmout.getText()));
									ps2.executeUpdate();

								} catch (Exception ee) {
									System.out.println(ee.getMessage());
								}

							}

						} catch (Exception ee) {
							System.out.print(ee.getMessage());
						} finally {
							try {
								rs.close();
								rs1.close();
								ps.close();
								ps1.close();
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
								dt1 = dateChooserSavingDate.getDate();

								String date = (String) sdf.format(dateChooserSavingDate.getDate());
								java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
								ps1.setDate(2, (java.sql.Date) d);
								ps1.setString(3, textFieldName.getText());
								ps1.setString(4, textFieldAcountNO.getText());
								ps1.setDouble(5, Double.valueOf(textFieldOtherAmount.getText()));
								ps1.setString(6, textFieldNote.getText());
								ps1.executeUpdate();

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
						try {

							reset();
						} catch (Exception ees) {
							System.out.println(ees.getMessage());
						}
					}

					else {
						try {

							conn = (Connection) DBConnection.getConnection();
							String in = "insert into banksystem.saving(SrnoMaster, Srno, Date, UpdateAmtDate, Name, AcountNumber, Address, Age, ContactNo, AccountCatogery, OpningAmount, Interist, InterestPer, Total, Nomeny, NomenyRelation, FilePath, Note, JoinName, JoinDate, JoinContactNo, JoinRelation, OtherAmount)  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
							ps = conn.prepareStatement(in);
							ps.setInt(1, Integer.parseInt(textFieldSrNoMaster.getText()));
							ps.setInt(2, Integer.parseInt(textFieldSrno.getText()));
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooserSavingDate.getDate();
							String date = (String) sdf.format(dateChooserSavingDate.getDate());
							java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
							ps.setDate(3, (java.sql.Date) d);
							ps.setDate(4, (java.sql.Date) d);
							ps.setString(5, textFieldName.getText());
							ps.setString(6, textFieldAcountNO.getText());
							ps.setString(7, textFieldAddress.getText());
							ps.setDouble(8, Double.valueOf(textFieldAge.getText()));
							ps.setString(9, textFieldContactNo.getText());
							ps.setString(10, textFieldAccountCategray.getText());
							ps.setDouble(11, Double.valueOf(textFieldAmout.getText()));
							ps.setDouble(12, Double.valueOf(textFieldIntrestPer.getText()));
							ps.setDouble(13, Double.valueOf(textFieldIntrestAmt.getText()));
							ps.setDouble(14, Double.valueOf(textFieldTotal.getText()));
							ps.setString(15, textFieldNomeny.getText());
							ps.setString(16, textFieldNomenyOther.getText());
							ps.setString(17, textFieldFilePath.getText());
							ps.setString(18, textFieldNote.getText().toString());
							ps.setString(19, textFieldJoName.getText());
							ps.setString(20, textFieldJoAge.getText());
							ps.setString(21, textFieldJoContactNo.getText());
							ps.setString(22, textFieldJoRelationOther.getText());
							ps.setDouble(23, Double.valueOf(textFieldOtherAmount.getText()));
							int ii = ps.executeUpdate();
							if (ii > 0) {
								try {
									conn = (Connection) DBConnection.getConnection();
									String maxno = "select max(SrNoMaster) from banksystem.savingtranscation;";
									ps1 = conn.prepareStatement(maxno);
									rs1 = ps1.executeQuery();
									while (rs1.next()) {
										issrno = rs1.getInt(1) + 1;
									}

									String insertdata = "insert into banksystem.savingtranscation (SrNoMaster, SrNo, Date,Name, AccountNo, TransactionParticulars, Balance) values(?,?,?,?,?,?,?);";
									ps2 = conn.prepareStatement(insertdata);
									ps2.setInt(1, issrno);
									ps2.setInt(2, 1);
									SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
									dt1 = dateChooserSavingDate.getDate();
									String date4 = (String) sdf4.format(dateChooserSavingDate.getDate());
									java.sql.Date d4 = new java.sql.Date(sdf.parse(date4).getTime());
									ps2.setDate(3, (java.sql.Date) d4);
									ps2.setString(4, textFieldName.getText());
									ps2.setString(5, textFieldAcountNO.getText());
									ps2.setString(6, "opening account");
									ps2.setDouble(7, Double.valueOf(textFieldAmout.getText()));
									ps2.executeUpdate();

								} catch (Exception ee) {
									System.out.println(ee.getMessage());
								} finally {
									try {
										rs.close();
										rs1.close();
										ps.close();
										ps1.close();
										ps2.close();
										conn.close();
									} catch (Exception ewww) {
										System.out.println(ewww.getMessage());
									}
								}

							}

						} catch (Exception ee) {
							System.out.print(ee.getMessage());
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
								dt1 = dateChooserSavingDate.getDate();

								String date = (String) sdf.format(dateChooserSavingDate.getDate());
								java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
								ps1.setDate(2, (java.sql.Date) d);
								ps1.setString(3, textFieldName.getText());
								ps1.setString(4, textFieldAcountNO.getText());
								ps1.setDouble(5, Double.valueOf(textFieldOtherAmount.getText()));
								ps1.setString(6, textFieldNote.getText());
								ps1.executeUpdate();

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
						try {
							JOptionPane.showMessageDialog(null, "Data are saved.");
							tabledataview();
							totalamount();
							reset();
						} catch (Exception ees) {
							System.out.println(ees.getMessage());
						}

					}
				}

			}

			private void tabledataview() {

				String data0 = textFieldSrNoMaster.getText();
				String data1 = textFieldSrno.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				dt1 = dateChooserSavingDate.getDate();
				String data2 = (String) sdf.format(dateChooserSavingDate.getDate());
				String data3 = (String) sdf.format(dateChooserSavingDate.getDate());
				String data4 = textFieldAcountNO.getText();
				String data5 = textFieldName.getText();
				String data6 = textFieldIntrestPer.getText();
				String data7 = textFieldAmout.getText();
				String data8 = textFieldIntrestAmt.getText();
				String data9 = textFieldTotal.getText();
				String data10 = textFieldOtherAmount.getText();
				String data11 = textFieldNomeny.getText();
				String data12 = textFieldNomenyOther.getText();
				String data13 = textFieldNote.getText().toString();
				String data14 = textFieldFilePath.getText();
				String data15 = textFieldAccountCategray.getText();
				String data16 = textFieldAddress.getText();
				String data17 = textFieldAge.getText();
				String data18 = textFieldContactNo.getText();
				String data19 = textFieldJoName.getText();
				String data20 = textFieldJoAge.getText();
				String data21 = textFieldJoContactNo.getText();
				String data22 = textFieldJoRelationOther.getText();

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12, data13, data14, data15, data16, data17, data18, data19, data20, data21,
						data22 };
				model1 = (DefaultTableModel) tablesavingdatashow.getModel();
				model1.addRow(row);
				increment();
				reset();

			}
		});
		btnSave.setIcon(new ImageIcon(SavingA_C.class.getResource("/ImageButtonIcon/Save.png")));
		btnSave.setHorizontalAlignment(SwingConstants.LEADING);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBounds(216, 11, 115, 38);
		panel_2.add(btnSave);

		JButton button_update = new JButton("Update");
		button_update.setVisible(false);
		button_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = textFieldFilePath.getText();
				if (!path.equals("path")) {
					try {
						FileInputStream fin = new FileInputStream(f);
						int len = (int) f.length();
						conn = (Connection) DBConnection.getConnection();
						String up = "UPDATE banksystem.saving set UpdateAmtDate=?, Name=?, AcountNumber=?, Address=?, Age=?, ContactNo=?, AccountCatogery=?,"
								+ " OpningAmount=?, Interist=?, InterestPer=?, Total=?, Nomeny=?, NomenyRelation=?, FileData=?, FilePath=?, Note=?, JoinName=?,"
								+ " JoinDate=?, JoinContactNo=?, JoinRelation=?, OtherAmount=? where SrnoMaster=?";
						ps = conn.prepareStatement(up);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						dt1 = dateChooserSavingDate.getDate();
						String date = (String) sdf.format(dateChooserSavingDate.getDate());
						java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
						ps.setDate(1, (java.sql.Date) d);
						ps.setString(2, textFieldName.getText());
						ps.setString(3, textFieldAcountNO.getText());
						ps.setString(4, textFieldAddress.getText());
						ps.setDouble(5, Double.valueOf(textFieldAge.getText()));
						ps.setString(6, textFieldContactNo.getText());
						ps.setString(7, textFieldAccountCategray.getText());
						ps.setDouble(8, Double.valueOf(textFieldAmout.getText()));
						ps.setDouble(9, Double.valueOf(textFieldIntrestPer.getText()));
						ps.setDouble(10, Double.valueOf(textFieldIntrestAmt.getText()));
						ps.setDouble(11, Double.valueOf(textFieldTotal.getText()));
						ps.setString(12, textFieldNomeny.getText());
						ps.setString(13, textFieldNomenyOther.getText());
						ps.setBinaryStream(14, fin, len);
						ps.setString(15, textFieldFilePath.getText());
						ps.setString(16, textFieldNote.getText().toString());
						ps.setString(17, textFieldJoName.getText());
						ps.setString(18, textFieldJoAge.getText());
						ps.setString(19, textFieldJoContactNo.getText());
						ps.setString(20, textFieldJoRelationOther.getText());
						ps.setDouble(21, Double.valueOf(textFieldOtherAmount.getText()));
						ps.setInt(22, Integer.parseInt(textFieldSrNoMaster.getText()));

						int i = ps.executeUpdate();
						if (i > 0) {
							JOptionPane.showMessageDialog(null, "Data are update.");
							increment();
							dispose();
							SavingA_C sa = new SavingA_C();
							sa.setUndecorated(true);
							sa.setVisible(true);
							reset();
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

						conn = (Connection) DBConnection.getConnection();
						String up = "UPDATE banksystem.saving set UpdateAmtDate=?, Name=?, AcountNumber=?, Address=?, Age=?, ContactNo=?, AccountCatogery=?,"
								+ " OpningAmount=?, Interist=?, InterestPer=?, Total=?, Nomeny=?, NomenyRelation=?, FilePath=?, Note=?, JoinName=?,"
								+ " JoinDate=?, JoinContactNo=?, JoinRelation=?, OtherAmount=? where SrnoMaster=?";
						ps = conn.prepareStatement(up);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						dt1 = dateChooserSavingDate.getDate();
						String date = (String) sdf.format(dateChooserSavingDate.getDate());
						java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
						ps.setDate(1, (java.sql.Date) d);
						ps.setString(2, textFieldName.getText());
						ps.setString(3, textFieldAcountNO.getText());
						ps.setString(4, textFieldAddress.getText());
						ps.setDouble(5, Double.valueOf(textFieldAge.getText()));
						ps.setString(6, textFieldContactNo.getText());
						ps.setString(7, textFieldAccountCategray.getText());
						ps.setDouble(8, Double.valueOf(textFieldAmout.getText()));
						ps.setDouble(9, Double.valueOf(textFieldIntrestPer.getText()));
						ps.setDouble(10, Double.valueOf(textFieldIntrestAmt.getText()));
						ps.setDouble(11, Double.valueOf(textFieldTotal.getText()));
						ps.setString(12, textFieldNomeny.getText());
						ps.setString(13, textFieldNomenyOther.getText());
						ps.setString(14, textFieldFilePath.getText());
						ps.setString(15, textFieldNote.getText().toString());
						ps.setString(16, textFieldJoName.getText());
						ps.setString(17, textFieldJoAge.getText());
						ps.setString(18, textFieldJoContactNo.getText());
						ps.setString(19, textFieldJoRelationOther.getText());
						ps.setDouble(20, Double.valueOf(textFieldOtherAmount.getText()));
						ps.setInt(21, Integer.parseInt(textFieldSrNoMaster.getText()));

						int i = ps.executeUpdate();
						if (i > 0) {
							JOptionPane.showMessageDialog(null, "Data are update.");
							increment();
							dispose();
							SavingA_C sa = new SavingA_C();
							sa.setUndecorated(true);
							sa.setVisible(true);
							reset();
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
		button_update.setIcon(new ImageIcon(SavingA_C.class.getResource("/ImageButtonIcon/update.png")));
		button_update.setToolTipText("");
		button_update.setHorizontalAlignment(SwingConstants.LEADING);
		button_update.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_update.setBounds(60, 11, 126, 38);
		panel_2.add(button_update);

		JButton button_4 = new JButton("Delete");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					conn = (Connection) DBConnection.getConnection();
					String de = "delete from banksystem.savingtranscation where AccountNo=?";
					ps = conn.prepareStatement(de);
					ps.setString(1, textFieldAcountNO.getText());
					int i = ps.executeUpdate();
					if (i > 0) {

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

				try {
					conn = (Connection) DBConnection.getConnection();
					String de = "delete from banksystem.saving where SrnoMaster=?";
					ps = conn.prepareStatement(de);
					ps.setInt(1, Integer.parseInt(textFieldSrNoMaster.getText()));
					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are deleted.");
						dispose();
						SavingA_C sa = new SavingA_C();
						sa.setUndecorated(true);
						sa.setVisible(true);

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
		button_4.setIcon(new ImageIcon(SavingA_C.class.getResource("/ImageButtonIcon/delete.jpg")));
		button_4.setHorizontalAlignment(SwingConstants.LEADING);
		button_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_4.setBounds(477, 11, 115, 38);
		panel_2.add(button_4);

		JButton button_5 = new JButton("Reset");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		button_5.setIcon(new ImageIcon(SavingA_C.class.getResource("/ImageButtonIcon/Reset5.jpg")));
		button_5.setHorizontalAlignment(SwingConstants.LEADING);
		button_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_5.setBounds(602, 11, 115, 38);
		panel_2.add(button_5);

		JButton button_6 = new JButton("Exit");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Welcome.frame.enable();
			}
		});
		button_6.setIcon(new ImageIcon(SavingA_C.class.getResource("/ImageButtonIcon/Exit.png")));
		button_6.setHorizontalAlignment(SwingConstants.LEADING);
		button_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_6.setBounds(727, 11, 115, 38);
		panel_2.add(button_6);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldIntrestAmt.setEditable(true);
				textFieldTotal.setEditable(true);
				btnEdit.setVisible(false);
				button_update.setBounds(341, 11, 126, 38);
				button_update.setVisible(true);
				
				Component[] com = panel_info.getComponents();
				Component[] com1 = panel_path.getComponents();
				Component[] com2 = panel_info2.getComponents();
				textFieldNote.setEnabled(true);
				for (int a = 0; a < com.length; a++) {
					com[a].setEnabled(true);

				}
				for (int a = 0; a < com1.length; a++) {
					com1[a].setEnabled(true);
				}
				for (int a = 0; a < com2.length; a++) {
					com2[a].setEnabled(true);
				}
				
			}
		});
		btnEdit.setIcon(new ImageIcon(SavingA_C.class.getResource("/ImageButtonIcon/edit.png")));
		btnEdit.setToolTipText("");
		btnEdit.setHorizontalAlignment(SwingConstants.LEADING);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(341, 11, 126, 38);
		panel_2.add(btnEdit);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(0, 416, 1154, 239);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 1134, 217);
		panel_3.add(scrollPane);

		tablesavingdatashow = new JTable();
		tablesavingdatashow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablesavingdatashow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					int i = tablesavingdatashow.getSelectedRow();
					model1 = (DefaultTableModel) tablesavingdatashow.getModel();
					textFieldSrNoMaster.setText(model1.getValueAt(i, 1).toString());
					textFieldSrno.setText(model1.getValueAt(i, 2).toString());
					// dateChooserSavingDate.setDateFormatString(model1.getValueAt(i,
					// 3).toString());
					textFieldAcountNO.setText(model1.getValueAt(i, 5).toString());
					textFieldName.setText(model1.getValueAt(i, 6).toString());
					textFieldIntrestPer.setText(model1.getValueAt(i, 7).toString());
					textFieldBalance.setText(model1.getValueAt(i, 8).toString());
					textFieldIntrestAmt.setText(model1.getValueAt(i, 9).toString());
					textFieldTotal.setText(model1.getValueAt(i, 10).toString());
					textFieldOtherAmount.setText(model1.getValueAt(i, 11).toString());
					textFieldNomeny.setText(model1.getValueAt(i, 12).toString());
					textFieldNomenyOther.setText(model1.getValueAt(i, 13).toString());
					textFieldNote.setText(model1.getValueAt(i, 14).toString());
					textFieldFilePath.setText(model1.getValueAt(i, 15).toString());
					f = new File(model1.getValueAt(i, 15).toString());
					textFieldAccountCategray.setText(model1.getValueAt(i, 16).toString());
					textFieldAddress.setText(model1.getValueAt(i, 17).toString());
					textFieldAge.setText(model1.getValueAt(i, 18).toString());
					textFieldContactNo.setText(model1.getValueAt(i, 19).toString());
					textFieldJoName.setText(model1.getValueAt(i, 20).toString());
					textFieldJoAge.setText(model1.getValueAt(i, 21).toString());
					textFieldJoContactNo.setText(model1.getValueAt(i, 22).toString());
					textFieldJoRelationOther.setText(model1.getValueAt(i, 23).toString());
				} catch (Exception ee) {

				}

				try {
					che();
				} catch (Exception ew) {
				}
				
				
				
				try {
					conn = (Connection) DBConnection.getConnection();
					String amount = "select Balance from banksystem.savingtranscation  where AccountNo='"
							+ textFieldAcountNO.getText() + "'";
					ps = conn.prepareStatement(amount);
					rs = ps.executeQuery();
					rs.first();
					double aoun = rs.getDouble("Balance");
					textFieldAmout.setText(String.valueOf(dff.format(aoun)));

					Component[] com = panel_info.getComponents();
					Component[] com1 = panel_path.getComponents();
					Component[] com2 = panel_info2.getComponents();
					textFieldNote.setEnabled(false);
					for (int a = 0; a < com.length; a++) {
						com[a].setEnabled(false);

					}
					for (int a = 0; a < com1.length; a++) {
						com1[a].setEnabled(false);
					}
					for (int a = 0; a < com2.length; a++) {
						com2[a].setEnabled(false);
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
				int rows = tablesavingdatashow.getRowCount() - 1;

				for (int i = 0; i < tablesavingdatashow.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tablesavingdatashow.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tablesavingdatashow.getSelectedRow();
						int col = tablesavingdatashow.getSelectedColumn();
						tablesavingdatashow.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tablesavingdatashow.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tablesavingdatashow.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tablesavingdatashow.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tablesavingdatashow.setValueAt((Object) s, 0, 0);
							tablesavingdatashow.setValueAt((Object) s1, row, 0);

						}
					}

					else {
					}
				}

			}
		});
		tablesavingdatashow.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tablesavingdatashow.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Select", "SrnoMaster", "SrNo", "Date", "Update Date", "A/C No", "Name", "Int %", "Balance", "Interest Amt", "Total", "Other Char.", "Nomeny Name", "Nomeny Re", "Note", "File Path", "Account Act", "Address", "Age", "Contact No", "Join Name", "Age", "Contact No", "Relation"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablesavingdatashow.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablesavingdatashow.getColumnModel().getColumn(0).setMinWidth(50);
		tablesavingdatashow.getColumnModel().getColumn(1).setPreferredWidth(0);
		tablesavingdatashow.getColumnModel().getColumn(1).setMinWidth(0);
		tablesavingdatashow.getColumnModel().getColumn(1).setMaxWidth(0);
		tablesavingdatashow.getColumnModel().getColumn(2).setPreferredWidth(50);
		tablesavingdatashow.getColumnModel().getColumn(2).setMinWidth(50);
		tablesavingdatashow.getColumnModel().getColumn(3).setPreferredWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(3).setMinWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(4).setPreferredWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(4).setMinWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(5).setPreferredWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(5).setMinWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(6).setPreferredWidth(150);
		tablesavingdatashow.getColumnModel().getColumn(6).setMinWidth(150);
		tablesavingdatashow.getColumnModel().getColumn(7).setPreferredWidth(50);
		tablesavingdatashow.getColumnModel().getColumn(7).setMinWidth(50);
		tablesavingdatashow.getColumnModel().getColumn(8).setPreferredWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(8).setMinWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(9).setPreferredWidth(0);
		tablesavingdatashow.getColumnModel().getColumn(9).setMinWidth(0);
		tablesavingdatashow.getColumnModel().getColumn(9).setMaxWidth(0);
		tablesavingdatashow.getColumnModel().getColumn(10).setPreferredWidth(0);
		tablesavingdatashow.getColumnModel().getColumn(10).setMinWidth(0);
		tablesavingdatashow.getColumnModel().getColumn(10).setMaxWidth(0);
		tablesavingdatashow.getColumnModel().getColumn(12).setPreferredWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(12).setMinWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(13).setPreferredWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(13).setMinWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(15).setPreferredWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(15).setMinWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(16).setPreferredWidth(15);
		tablesavingdatashow.getColumnModel().getColumn(16).setMinWidth(0);
		tablesavingdatashow.getColumnModel().getColumn(16).setMaxWidth(0);
		tablesavingdatashow.getColumnModel().getColumn(17).setPreferredWidth(150);
		tablesavingdatashow.getColumnModel().getColumn(17).setMinWidth(150);
		tablesavingdatashow.getColumnModel().getColumn(19).setPreferredWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(19).setMinWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(20).setPreferredWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(20).setMinWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(22).setPreferredWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(22).setMinWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(23).setPreferredWidth(80);
		tablesavingdatashow.getColumnModel().getColumn(23).setMinWidth(80);
		showdata();
		scrollPane.setViewportView(tablesavingdatashow);

		JButton button_8 = new JButton("Report");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for (int i = 0; i < tablesavingdatashow.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tablesavingdatashow.getValueAt(i, 0).toString());
						if (che) {
							String accno4 = String.valueOf(tablesavingdatashow.getValueAt(i, 5).toString());

							java.io.InputStream file = getClass().getResourceAsStream("/Reports/Saving.jrxml");
							accno = String.valueOf(tablesavingdatashow.getValueAt(i, 5).toString());
							HashMap para = new HashMap();
							para.put("Acno", accno4);
							allinonereport r = new allinonereport("Saving Report", file, para);

							break;
						}

					}
				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		button_8.setIcon(new ImageIcon(SavingA_C.class.getResource("/ImageButtonIcon/Report4d.png")));
		button_8.setHorizontalAlignment(SwingConstants.LEADING);
		button_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_8.setBounds(10, 666, 140, 38);
		contentPane.add(button_8);

		 panel_info2 = new JPanel();
		panel_info2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_info2.setBounds(0, 289, 1154, 45);
		contentPane.add(panel_info2);
		panel_info2.setLayout(null);

		JRadioButton rdbtnJoinAccount = new JRadioButton("Join Account");
		rdbtnJoinAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnJoinAccount.isSelected()) {
					panel_JoinAccount.setVisible(true);
				} else {
					panel_JoinAccount.setVisible(false);
				}
			}
		});
		rdbtnJoinAccount.setBounds(6, 7, 107, 23);
		panel_info2.add(rdbtnJoinAccount);

		panel_JoinAccount = new JPanel();
		panel_JoinAccount.setVisible(false);
		panel_JoinAccount.setBounds(119, 7, 1025, 27);
		panel_info2.add(panel_JoinAccount);
		panel_JoinAccount.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(10, 4, 46, 14);
		panel_JoinAccount.add(lblNewLabel);

		textFieldJoName = new JTextField();
		textFieldJoName.setBounds(46, 1, 162, 20);
		panel_JoinAccount.add(textFieldJoName);
		textFieldJoName.setColumns(10);

		textFieldJoAge = new JTextField();
		textFieldJoAge.setText("0");
		textFieldJoAge.setColumns(10);
		textFieldJoAge.setBounds(242, 1, 61, 20);
		panel_JoinAccount.add(textFieldJoAge);

		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(215, 4, 46, 14);
		panel_JoinAccount.add(lblAge);

		textFieldJoContactNo = new JTextField();
		textFieldJoContactNo.setColumns(10);
		textFieldJoContactNo.setBounds(388, 1, 136, 20);
		panel_JoinAccount.add(textFieldJoContactNo);

		JLabel lblContactNo = new JLabel("Contact No:");
		lblContactNo.setBounds(313, 4, 90, 14);
		panel_JoinAccount.add(lblContactNo);

		JLabel label_5 = new JLabel("Relation:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setBounds(534, 3, 74, 17);
		panel_JoinAccount.add(label_5);

		JComboBox comboBoxJoRelation = new JComboBox();
		comboBoxJoRelation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = comboBoxJoRelation.getSelectedItem().toString();
				textFieldJoRelationOther.setText("");
				textFieldJoRelationOther.setText(name);
			}
		});
		comboBoxJoRelation.setModel(new DefaultComboBoxModel(new String[] {"   Select", "   Father", "   Mother", "   Paternal Grandfather", "    Maternal Grandfather ", "    Maternal Grandmother", "    Brother", "    Brother's Wife", "    Elder Brother", "    Younger Brother", "    Husband's sister", "    Sister", "    Sister's husband", "    Elder Sister", "    Younger Sister)", "    Husband's elder brother", "    Husband's younger brother", "    Elder brother's wife", "    Younger brother's wife", "    Wife's Sister", "    Wife's Brother", "    Wife's Brother's wife", "    Husband's Sister's Husband", "    Wife's sister's husband", "    Husband's elder brother's wife", "    Husband's younger brother's wife", "    Son", "    Son's wife (daughter-in-law) ", "    Daughter ", "    Daughter's husband (son-in-law) ", "    Grandson (son's son)", "    Granddaughter (son's daughter)", "    Grandson (daughter's son)", "    Granddaughter (daughter's daughter)", "    Husband", "    Wife", "    Husband's Mother (Mother-in-law)", "    Husband's Father (Father-in-law)", "    Fianc\u00E9 or Fianc\u00E9e", "    Father's younger brother (uncle)", "    Father's younger brother's wife (aunt)", "    Father's elder brother's (Uncle)", "    Father's elder brother's wife (Aunt) ", "    Father's sister (aunt)", "    Father's sister's husband", "    Mother's brother", "    Mother's brother's wife", "    Mother's sister", "    Mother's sister's husband", "    Brother's son (nephew)", "    Sister's son (nephew)", "    Brother's daughter (niece)", "    Sister's daughter (niece) ", "    Husband's elder brother's daughter", "    Husband's elder brother's son"}));
		comboBoxJoRelation.setBounds(586, 0, 133, 23);
		panel_JoinAccount.add(comboBoxJoRelation);

		JCheckBox checkBoxJoRelation = new JCheckBox("");
		checkBoxJoRelation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!checkBoxJoRelation.isSelected()) {
					comboBoxJoRelation.setEnabled(true);
					textFieldJoRelationOther.setEnabled(false);
					textFieldJoRelationOther.setText("");
				} else {
					comboBoxJoRelation.setEnabled(false);
					textFieldJoRelationOther.setEnabled(true);
					comboBoxJoRelation.setSelectedIndex(0);
				}

			}
		});
		checkBoxJoRelation.setBounds(725, 0, 27, 23);
		panel_JoinAccount.add(checkBoxJoRelation);

		JLabel label_7 = new JLabel("Other:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(758, 3, 46, 17);
		panel_JoinAccount.add(label_7);

		textFieldJoRelationOther = new JTextField();
		textFieldJoRelationOther.setText("Other");
		textFieldJoRelationOther.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldJoRelationOther.setEnabled(false);
		textFieldJoRelationOther.setColumns(10);
		textFieldJoRelationOther.setBounds(800, 0, 215, 23);
		panel_JoinAccount.add(textFieldJoRelationOther);

		JButton button = new JButton("View Transaction");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for (int i = 0; i <= tablesavingdatashow.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tablesavingdatashow.getValueAt(i, 0).toString());
						if (che) {
							accno = String.valueOf(tablesavingdatashow.getValueAt(i, 5).toString());
							TranSaving2 tr = new TranSaving2();
							tr.setUndecorated(true);
							tr.setVisible(true);

						}
					}
				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(SavingA_C.class.getResource("/ImageButtonIcon/TrancationView.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(160, 666, 194, 38);
		contentPane.add(button);

		JLabel lblTotalOppningAmount = new JLabel("Total Balance:");
		lblTotalOppningAmount.setBounds(859, 668, 127, 14);
		contentPane.add(lblTotalOppningAmount);

		textFieldTotalAmtB = new JTextField();
		textFieldTotalAmtB.setForeground(Color.RED);
		textFieldTotalAmtB.setEditable(false);
		textFieldTotalAmtB.setColumns(10);
		textFieldTotalAmtB.setBounds(996, 666, 132, 20);
		contentPane.add(textFieldTotalAmtB);
	}

	public void increment() {
		try {

			conn = (Connection) DBConnection.getConnection();
			PreparedStatement ps1 = conn.prepareStatement("select max(SrnoMaster) from banksystem.saving;");
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				int val = (rs.getInt(1) + 1);
				val1 = String.valueOf(val);

			}
			textFieldSrNoMaster.setText(val1);
			textFieldSrno.setText(val1);
			textFieldAcountNO.setText("S" + val1);

			SimpleDateFormat insdf = new SimpleDateFormat("yyyy-MM-dd");
			Date indate = new Date();
			String instringdate = insdf.format(indate);
			dateChooserSavingDate.setDate(insdf.parse(instringdate));

			rs.close();
			ps.close();
			conn.close();
		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		}
	}

	public void reset() {
		increment();

		textFieldAddress.setText("");
		textFieldAge.setText("0");
		textFieldAmout.setText("0");
		textFieldContactNo.setText("");
		textFieldFilePath.setText("path");
		textFieldIntrestAmt.setText("0");
		textFieldIntrestPer.setText("0");
		textFieldName.setText("");
		textFieldTotal.setText("0");
		textFieldNote.setText("");
		textFieldNomeny.setText("");
		textFieldNomenyOther.setText("Other");
		comboBoxNomeny.setSelectedIndex(0);
		textFieldJoName.setText("");
		textFieldJoAge.setText("0");
		textFieldJoContactNo.setText("");
		textFieldJoRelationOther.setText("");
		textFieldOtherAmount.setText("0");

	}

	public void showdata() {
		try {
			conn = (Connection) DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.saving order by Srnomaster;";

			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data0 = rs.getString("SrnoMaster");
				String data1 = rs.getString("Srno");
				String data2 = rs.getString("Date");
				String data3 = rs.getString("UpdateAmtDate");
				String data4 = rs.getString("AcountNumber");
				String data5 = rs.getString("Name");
				String data6 = rs.getString("Interist");
				String data7 = rs.getString("OpningAmount");
				String data8 = rs.getString("InterestPer");
				String data9 = rs.getString("Total");
				String data10 = rs.getString("OtherAmount");
				String data11 = rs.getString("Nomeny");
				String data12 = rs.getString("NomenyRelation");
				String data13 = rs.getString("Note");
				String data14 = rs.getString("FilePath");
				String data15 = rs.getString("AccountCatogery");
				String data16 = rs.getString("Address");
				String data17 = rs.getString("Age");
				String data18 = rs.getString("ContactNo");
				String data19 = rs.getString("JoinName");
				String data20 = rs.getString("JoinDate");
				String data21 = rs.getString("JoinContactNo");
				String data22 = rs.getString("JoinRelation");

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12, data13, data14, data15, data16, data17, data18, data19, data20, data21,
						data22 };
				model1 = (DefaultTableModel) tablesavingdatashow.getModel();
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

	public void daycount(String st) {
		textFieldAccountCategray.setText(st);
	}

	public void Calculation() {
		String ins = textFieldIntrestPer.getText();
		if (ins.equals("0")) {
			textFieldTotal.setText(textFieldAmout.getText());

		} else {

			double amount = Double.valueOf(textFieldAmout.getText());
			double interst = Double.valueOf(textFieldIntrestPer.getText());
			double totla = amount * interst / 100.0;
			textFieldIntrestAmt.setText(String.valueOf(totla));
			textFieldTotal.setText(String.valueOf(amount + totla));
		}
	}

	public void DurationIncrement() {
		try {

			conn = (Connection) DBConnection.getConnection();
			String show = "select * from banksystem.saving order by SrnoMaster;";
			ps = conn.prepareStatement(show);
			rs = ps.executeQuery();
			while (rs.next()) {
				String date1 = rs.getString("UpdateAmtDate");
				String acno = rs.getString("AcountNumber");
				double interst = rs.getDouble("Interist");
				double maturityamt = rs.getDouble("OpningAmount");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date datecur = new Date();
				String date2 = (String) sdf.format(datecur);
				Date date11 = sdf.parse(date1);
				Date date12 = sdf.parse(date2);
				long diff = date12.getTime() - date11.getTime();
				long dayscount = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

				if (dayscount >= 90) {
					SimpleDateFormat sdf44 = new SimpleDateFormat("yyyy-MM-dd");
					Date datecur44 = new Date();
					String sysdate44 = (String) sdf44.format(datecur);
					java.sql.Date sqlDate = new java.sql.Date(datecur44.getTime());

					String accnoo = rs.getString("AcountNumber");
					double matamt = rs.getDouble("OpningAmount");
					double interstnew = rs.getDouble("Interist");
					double interscount = matamt * interstnew / 100;
					double aginmatuamtsum1 = matamt + interscount;
					double sssinteramount = aginmatuamtsum1 * interstnew / 100;
					double allsum1 = aginmatuamtsum1 + sssinteramount;

					String one = dff.format(aginmatuamtsum1);
					double aginmatuamtsum = Double.valueOf(one);

					String two = dff.format(allsum1);
					double allsum = Double.valueOf(two);

					String upsavin = "UPDATE banksystem.saving set  UpdateAmtDate='" + (java.sql.Date) sqlDate
							+ "', OpningAmount='" + aginmatuamtsum + "', InterestPer='" + sssinteramount + "', Total='"
							+ allsum + "' where AcountNumber='" + accnoo + "'";
					ps1 = conn.prepareStatement(upsavin);
					int ii = ps1.executeUpdate();
					if (ii > 0) {

						int isrno1 = 0;
						int srnomaster1 = 0;
						String name = null;
						String maxno = "select max(SrNo),SrNoMaster,name from banksystem.savingtranscation where AccountNo='"
								+ acno + "';";
						ps1 = conn.prepareStatement(maxno);
						rs1 = ps1.executeQuery();
						while (rs1.next()) {
							isrno1 = rs1.getInt(1) + 1;
							srnomaster1 = rs1.getInt("SrNoMaster");
							name = rs1.getString("Name");

						}
						rs1.close();
						ps1.close();
						String insertdata = "insert into banksystem.savingtranscation (SrNoMaster, SrNo, Date,Name,AccountNo, TransactionParticulars,Amount, Balance) values(?,?,?,?,?,?,?,?);";
						ps2 = conn.prepareStatement(insertdata);
						ps2.setInt(1, srnomaster1);
						ps2.setInt(2, isrno1);
						ps2.setDate(3, (java.sql.Date) sqlDate);
						ps2.setString(4, name);
						ps2.setString(5, acno);
						ps2.setString(6, "Instert% added=" + interstnew);
						ps2.setDouble(7, aginmatuamtsum);
						ps2.setDouble(8, allsum);
						ps2.executeUpdate();

					}

				}

			}

		} catch (Exception es) {
			System.out.println(es.getMessage());
		} finally {
			try {
				rs.close();
				rs1.close();
				ps.close();
				ps1.close();
				ps2.close();
				conn.close();
			} catch (Exception ewww) {
				System.out.println(ewww.getMessage());
			}
		}

	}

	public void totalamount() {
		int rowsCount = tablesavingdatashow.getRowCount();
		double sum = 0;

		for (int i = 0; i < rowsCount; i++) {
			sum = sum + Double.valueOf(tablesavingdatashow.getValueAt(i, 8).toString());
		}
		textFieldTotalAmtB.setText(String.valueOf(dff.format(sum)));

	}
}
