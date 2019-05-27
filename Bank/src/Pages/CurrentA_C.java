package Pages;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.toedter.calendar.JDateChooser;

import CLASS.CalculatorGUI;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Cursor;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.Component;
import javax.swing.JList;
import java.awt.event.MouseMotionAdapter;

public class CurrentA_C extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldAddress;
	private JTextField textFieldAge;
	private JTextField textFieldContactNo;
	private JTable tableCurrentdatashow;
	private JTextField textFieldFiePath;

	public Connection conn;
	public PreparedStatement ps;
	public PreparedStatement ps1;
	public PreparedStatement ps2;
	public ResultSet rs;
	public ResultSet rs1;
	public String val1;
	
	public JButton button_update789;
	public File f;
	java.util.Date dt1, dt2;
	public DefaultTableModel model1;
	private JTextField textFielSrNo;
	private JTextField textFieldSrNoMaster;
	private JTextField textFieldAccountcategary;
	private JTextField textFieldOppingAmount;
	public JTextArea textAreaNotes;
	public JPanel panel_info2;
	private JTextField textFieldAccountNo;
	private JTextField textFieldBussinessName;
	private JTextField textFieldNomeny;
	private JTextField textField_NomenyOther;
	public JComboBox comboBoxNomeny;
	public static int issrno;
	public JDateChooser dateChooserCurrent;
	public JPanel panelJoinName;
	private JTextField textFieldJoName;
	private JTextField textFieldJoAge;
	private JTextField textFieldJoContactNo;
	private JTextField textFieldJoRelationOther;
	private JTextField textFieldOtherAmount;
	public JButton buttonbrowger;
	public JList<String> listName;
	public JScrollPane scrollPaneName;
	public String[] data = new String[1000];
	public JScrollPane scrollPane_1;
	public static String accno;
	private JTextField textFieldTotalAmtB;
	public DecimalFormat df = new DecimalFormat("#.##");
	private JTextField textFieldBalance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CurrentA_C frame = new CurrentA_C();
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
	public CurrentA_C() {
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

		JLabel lblCurrentAc = new JLabel("Current A/C");
		lblCurrentAc.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCurrentAc.setBounds(22, 11, 276, 42);
		panel.add(lblCurrentAc);

		JLabel label_1 = new JLabel("Date:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(789, 45, 81, 17);
		panel.add(label_1);

		Date date = new Date();
		dateChooserCurrent = new JDateChooser();
		dateChooserCurrent.setDateFormatString("yyyy-MM-dd");
		dateChooserCurrent.setBounds(885, 45, 196, 20);
		dateChooserCurrent.setDate(date);
		panel.add(dateChooserCurrent);

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

		textFielSrNo = new JTextField();
		textFielSrNo.setVisible(false);
		textFielSrNo.setEnabled(false);
		textFielSrNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFielSrNo.setColumns(10);
		textFielSrNo.setBounds(155, 25, 209, 23);
		panel.add(textFielSrNo);

		textFieldSrNoMaster = new JTextField();
		textFieldSrNoMaster.setVisible(false);
		textFieldSrNoMaster.setEnabled(false);
		textFieldSrNoMaster.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldSrNoMaster.setColumns(10);
		textFieldSrNoMaster.setBounds(387, 25, 209, 23);
		increment();
		panel.add(textFieldSrNoMaster);

		JLabel label_6 = new JLabel("Account No:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(22, 57, 81, 17);
		panel.add(label_6);

		textFieldAccountNo = new JTextField();
		textFieldAccountNo.setEditable(false);
		textFieldAccountNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldAccountNo.setColumns(10);
		textFieldAccountNo.setBounds(112, 53, 181, 23);
		increment();
		panel.add(textFieldAccountNo);

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
		button_1.setIcon(new ImageIcon(CurrentA_C.class.getResource("/ImageButtonIcon/clac.png")));
		button_1.setOpaque(false);
		button_1.setForeground(Color.RED);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		button_1.setBounds(1086, 7, 64, 55);
		panel.add(button_1);

		JPanel panel_info = new JPanel();
		panel_info.setLayout(null);
		panel_info.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_info.setBounds(0, 87, 1154, 166);
		contentPane.add(panel_info);

		JLabel label_2 = new JLabel("Name:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(10, 9, 81, 17);
		panel_info.add(label_2);

		textFieldName = new JTextField();

		textFieldName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {

					conn = (Connection) DBConnection.getConnection();
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
					listName.setListData(data);
					listName.setVisibleRowCount(100);
					scrollPaneName.setVisible(true);
					listName.setVisible(true);
					textFieldContactNo.setVisible(false);
					textFieldNomeny.setVisible(false);
					scrollPane_1.setVisible(false);

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
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

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == 40) {
					listName.requestFocus();
				}
			}
		});
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldName.setColumns(10);
		textFieldName.setBounds(109, 6, 209, 23);
		panel_info.add(textFieldName);

		JLabel label_3 = new JLabel("Address:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(617, 9, 56, 17);
		panel_info.add(label_3);

		textFieldAddress = new JTextField();
		textFieldAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(687, 6, 197, 23);
		panel_info.add(textFieldAddress);

		JLabel label_4 = new JLabel("Age:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(894, 9, 84, 17);
		panel_info.add(label_4);

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
		textFieldAge.setBounds(988, 3, 156, 23);
		panel_info.add(textFieldAge);

		JLabel label_5 = new JLabel("Contact No:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setBounds(10, 40, 74, 17);
		panel_info.add(label_5);

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
		textFieldContactNo.setBounds(109, 37, 209, 23);
		panel_info.add(textFieldContactNo);

		JLabel lblDocumentFileUpload = new JLabel("Document file upload:");
		lblDocumentFileUpload.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDocumentFileUpload.setBounds(624, 100, 183, 17);
		panel_info.add(lblDocumentFileUpload);

		JLabel lblNote = new JLabel("Note:");
		lblNote.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNote.setBounds(13, 100, 46, 17);
		panel_info.add(lblNote);

		JPanel panel_path = new JPanel();
		panel_path.setLayout(null);
		panel_path.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_path.setBounds(624, 115, 308, 44);
		panel_info.add(panel_path);

		textFieldFiePath = new JTextField();
		textFieldFiePath.setText("path");
		textFieldFiePath.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldFiePath.setEnabled(false);
		textFieldFiePath.setColumns(10);
		textFieldFiePath.setBounds(10, 13, 229, 23);
		panel_path.add(textFieldFiePath);

		buttonbrowger = new JButton("Browse");
		buttonbrowger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(contentPane);
				f = fc.getSelectedFile();
				String path = f.getAbsolutePath();
				textFieldFiePath.setText(path);
			}
		});
		buttonbrowger.setFont(new Font("Tahoma", Font.PLAIN, 9));
		buttonbrowger.setBounds(234, 13, 74, 23);
		panel_path.add(buttonbrowger);

		JLabel label = new JLabel("Account Catagery:");
		label.setVisible(false);
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(716, 40, 106, 17);
		panel_info.add(label);

		JComboBox comboBoxAccountCa = new JComboBox();
		comboBoxAccountCa.setVisible(false);
		comboBoxAccountCa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String st = comboBoxAccountCa.getSelectedItem().toString();
				daycount(st);
			}
		});
		comboBoxAccountCa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxAccountCa.setModel(new DefaultComboBoxModel(new String[] { "Select ", "A", "B", "C", "D", "E", "F" }));
		comboBoxAccountCa.setBounds(836, 37, 125, 23);
		panel_info.add(comboBoxAccountCa);

		JCheckBox checkBoxaccountcatagery = new JCheckBox("");
		checkBoxaccountcatagery.setVisible(false);
		checkBoxaccountcatagery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click();
			}

			private void click() {
				if (!checkBoxaccountcatagery.isSelected()) {
					comboBoxAccountCa.setEnabled(true);
					textFieldAccountcategary.setEnabled(false);
					textFieldAccountcategary.setText("");
				} else {
					comboBoxAccountCa.setEnabled(false);
					textFieldAccountcategary.setEnabled(true);

				}

			}
		});
		checkBoxaccountcatagery.setBounds(962, 37, 21, 23);
		panel_info.add(checkBoxaccountcatagery);

		textFieldAccountcategary = new JTextField();
		textFieldAccountcategary.setVisible(false);
		textFieldAccountcategary.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldAccountcategary.setText("");
			}
		});
		textFieldAccountcategary.setText("Other");
		textFieldAccountcategary.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldAccountcategary.setEnabled(false);
		textFieldAccountcategary.setColumns(10);
		textFieldAccountcategary.setBounds(1052, 37, 92, 23);
		panel_info.add(textFieldAccountcategary);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(110, 96, 503, 62);
		panel_info.add(scrollPane_1);

		textAreaNotes = new JTextArea();
		textAreaNotes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == 9) {
					buttonbrowger.requestFocus();
				}
			}
		});
		scrollPane_1.setViewportView(textAreaNotes);

		JLabel label_8 = new JLabel("Opning Amount:");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_8.setBounds(330, 38, 89, 17);
		panel_info.add(label_8);

		textFieldOppingAmount = new JTextField();
		textFieldOppingAmount.addKeyListener(new KeyAdapter() {
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
		textFieldOppingAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldOppingAmount.setColumns(10);
		textFieldOppingAmount.setBounds(426, 34, 118, 23);
		panel_info.add(textFieldOppingAmount);

		JLabel lblOther = new JLabel("Other:");
		lblOther.setVisible(false);
		lblOther.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOther.setBounds(992, 40, 46, 17);
		panel_info.add(lblOther);

		JLabel lblBussinessName = new JLabel("Bussiness Name:");
		lblBussinessName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBussinessName.setBounds(328, 9, 81, 17);
		panel_info.add(lblBussinessName);

		textFieldBussinessName = new JTextField();
		textFieldBussinessName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				scrollPaneName.setVisible(false);
				listName.setVisible(false);
				textFieldContactNo.setVisible(true);
				textFieldNomeny.setVisible(true);
				scrollPane_1.setVisible(true);
			}
		});
		textFieldBussinessName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldBussinessName.setColumns(10);
		textFieldBussinessName.setBounds(426, 6, 181, 23);
		panel_info.add(textFieldBussinessName);

		JLabel label_7 = new JLabel("Nomeny:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(10, 68, 106, 17);
		panel_info.add(label_7);

		textFieldNomeny = new JTextField();
		textFieldNomeny.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldNomeny.setColumns(10);
		textFieldNomeny.setBounds(108, 68, 209, 23);
		panel_info.add(textFieldNomeny);

		JLabel label_9 = new JLabel("Relation:");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_9.setBounds(326, 71, 74, 17);
		panel_info.add(label_9);

		comboBoxNomeny = new JComboBox();
		comboBoxNomeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = comboBoxNomeny.getSelectedItem().toString();
				textField_NomenyOther.setText("");
				textField_NomenyOther.setText(name);
			}
		});
		comboBoxNomeny.setModel(new DefaultComboBoxModel(new String[] {"   Select", "    Father", "    Mother", "   Paternal Grandfather", "    Maternal Grandfather ", "    Maternal Grandmother", "    Brother", "    Brother's Wife", "    Elder Brother", "    Younger Brother", "    Husband's sister", "    Sister", "    Sister's husband", "    Elder Sister", "    Younger Sister)", "    Husband's elder brother", "    Husband's younger brother", "    Elder brother's wife", "    Younger brother's wife", "    Wife's Sister", "    Wife's Brother", "    Wife's Brother's wife", "    Husband's Sister's Husband", "    Wife's sister's husband", "    Husband's elder brother's wife", "    Husband's younger brother's wife", "    Son", "    Son's wife (daughter-in-law) ", "    Daughter ", "    Daughter's husband (son-in-law) ", "    Grandson (son's son)", "    Granddaughter (son's daughter)", "    Grandson (daughter's son)", "    Granddaughter (daughter's daughter)", "    Husband", "    Wife", "    Husband's Mother (Mother-in-law)", "    Husband's Father (Father-in-law)", "    Fianc\u00E9 or Fianc\u00E9e", "    Father's younger brother (uncle)", "    Father's younger brother's wife (aunt)", "    Father's elder brother's (Uncle)", "    Father's elder brother's wife (Aunt) ", "    Father's sister (aunt)", "    Father's sister's husband", "    Mother's brother", "    Mother's brother's wife", "    Mother's sister", "    Mother's sister's husband", "    Brother's son (nephew)", "    Sister's son (nephew)", "    Brother's daughter (niece)", "    Sister's daughter (niece) ", "    Husband's elder brother's daughter", "    Husband's elder brother's son"}));
		comboBoxNomeny.setBounds(426, 68, 170, 23);
		panel_info.add(comboBoxNomeny);

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
		checkBoxNomeny.setBounds(597, 68, 27, 23);
		panel_info.add(checkBoxNomeny);

		JLabel label_10 = new JLabel("Other:");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_10.setBounds(625, 71, 74, 17);
		panel_info.add(label_10);

		textField_NomenyOther = new JTextField();
		textField_NomenyOther.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField_NomenyOther.setText("");
			}
		});
		textField_NomenyOther.setText("Other");
		textField_NomenyOther.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_NomenyOther.setEnabled(false);
		textField_NomenyOther.setColumns(10);
		textField_NomenyOther.setBounds(687, 68, 197, 23);
		panel_info.add(textField_NomenyOther);

		JLabel lblOtherAmount = new JLabel("Other Charge");
		lblOtherAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOtherAmount.setBounds(894, 71, 89, 17);
		panel_info.add(lblOtherAmount);

		textFieldOtherAmount = new JTextField();
		textFieldOtherAmount.setText("0");
		textFieldOtherAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

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
		textFieldOtherAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldOtherAmount.setColumns(10);
		textFieldOtherAmount.setBounds(988, 68, 156, 23);
		panel_info.add(textFieldOtherAmount);

		scrollPaneName = new JScrollPane();
		scrollPaneName.setVisible(false);
		scrollPaneName.setBounds(109, 29, 209, 130);
		panel_info.add(scrollPaneName);

		listName = new JList<String>();
		listName.setVisible(false);
		listName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldName.setText(listName.getSelectedValue());
					String accountnotop = textFieldName.getText();
					scrollPaneName.setVisible(false);
					listName.setVisible(false);
					textFieldContactNo.setVisible(true);
					textFieldNomeny.setVisible(true);
					scrollPane_1.setVisible(true);

					try {
						conn = (Connection) DBConnection.getConnection();
						String query = "Select * from banksystem.current where Name=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldName.getText());
						rs = ps.executeQuery();
						while (rs.next()) {

							String Srnomaster = rs.getString("SrNoMaster");
							textFieldSrNoMaster.setText(Srnomaster);
							String UpdateAmtDate = rs.getString("Date");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date dd = sdf.parse(UpdateAmtDate);
							dateChooserCurrent.setDate(dd);
							String AccountNumber = rs.getString("Name");
							textFieldName.setText(AccountNumber);
							String AccountNumber44 = rs.getString("AccountNumber");
							textFieldAccountNo.setText(AccountNumber44);
							String BussinessName = rs.getString("BussinessName");
							textFieldBussinessName.setText(BussinessName);
							String Age = rs.getString("Address");
							textFieldAddress.setText(Age);
							String ContactNo = rs.getString("Age");
							textFieldAge.setText(ContactNo);
							String LoanAgainst = rs.getString("Contactno");
							textFieldContactNo.setText(LoanAgainst);
							String Duration = rs.getString("AcountCat");
							textFieldAccountcategary.setText(Duration);

							String Days = rs.getString("OppingAmount");
							textFieldBalance.setText(Days);

							String Amount = rs.getString("Nomeny");
							textFieldNomeny.setText(Amount);

							String nomenyother = rs.getString("NomenyRelation");
							textField_NomenyOther.setText(nomenyother);

							String Interst = rs.getString("FilePath");
							textFieldFiePath.setText(Interst);

							String InstersAmt = rs.getString("Notes");
							textAreaNotes.setText(InstersAmt);

							String Total = rs.getString("JoinName");
							textFieldJoName.setText(Total);

							String ShareInterst = rs.getString("JoinAge");
							textFieldJoAge.setText(ShareInterst);

							String ShareInterstAmt = rs.getString("JoinContactNO");
							textFieldJoContactNo.setText(ShareInterstAmt);

							String FormFee = rs.getString("JoinRelation");
							textFieldJoRelationOther.setText(FormFee);

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
						} catch (Exception ew) {
							System.out.println(ew.getMessage());
						}
					}
					
					try {
						conn = (Connection) DBConnection.getConnection();
						String amount = "select Balance from banksystem.currenttranscation  where AccountNo='"
								+ textFieldAccountNo.getText() + "'";
						ps = conn.prepareStatement(amount);
						rs = ps.executeQuery();
						rs.first();
						double aoun = rs.getDouble("Balance");
						textFieldOppingAmount.setText(String.valueOf(df.format(aoun)));

						Component[] com = panel_info.getComponents();
						Component[] com1 = panel_path.getComponents();
						Component[] com2 = panel_info2.getComponents();
						textAreaNotes.setEnabled(false);
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
		listName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldName.setText(listName.getSelectedValue());
				String accountnotop = textFieldName.getText();
				scrollPaneName.setVisible(false);
				listName.setVisible(false);
				textFieldContactNo.setVisible(true);
				textFieldNomeny.setVisible(true);
				scrollPane_1.setVisible(true);

				try {
					conn = (Connection) DBConnection.getConnection();
					String query = "Select * from banksystem.current where Name=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldName.getText());
					rs = ps.executeQuery();
					while (rs.next()) {

						String Srnomaster = rs.getString("SrNoMaster");
						textFieldSrNoMaster.setText(Srnomaster);
						String UpdateAmtDate = rs.getString("Date");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date dd = sdf.parse(UpdateAmtDate);
						dateChooserCurrent.setDate(dd);
						String AccountNumber = rs.getString("Name");
						textFieldName.setText(AccountNumber);
						String AccountNumber44 = rs.getString("AccountNumber");
						textFieldAccountNo.setText(AccountNumber44);
						String BussinessName = rs.getString("BussinessName");
						textFieldBussinessName.setText(BussinessName);
						String Age = rs.getString("Address");
						textFieldAddress.setText(Age);
						String ContactNo = rs.getString("Age");
						textFieldAge.setText(ContactNo);
						String LoanAgainst = rs.getString("Contactno");
						textFieldContactNo.setText(LoanAgainst);
						String Duration = rs.getString("AcountCat");
						textFieldAccountcategary.setText(Duration);

						String Days = rs.getString("OppingAmount");
						textFieldBalance.setText(Days);

						String Amount = rs.getString("Nomeny");
						textFieldNomeny.setText(Amount);

						String nomenyother = rs.getString("NomenyRelation");
						textField_NomenyOther.setText(nomenyother);

						String Interst = rs.getString("FilePath");
						textFieldFiePath.setText(Interst);

						String InstersAmt = rs.getString("Notes");
						textAreaNotes.setText(InstersAmt);

						String Total = rs.getString("JoinName");
						textFieldJoName.setText(Total);

						String ShareInterst = rs.getString("JoinAge");
						textFieldJoAge.setText(ShareInterst);

						String ShareInterstAmt = rs.getString("JoinContactNO");
						textFieldJoContactNo.setText(ShareInterstAmt);

						String FormFee = rs.getString("JoinRelation");
						textFieldJoRelationOther.setText(FormFee);

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
					} catch (Exception ew) {
						System.out.println(ew.getMessage());
					}
				}
				
				try {
					conn = (Connection) DBConnection.getConnection();
					String amount = "select Balance from banksystem.currenttranscation  where AccountNo='"
							+ textFieldAccountNo.getText() + "'";
					ps = conn.prepareStatement(amount);
					rs = ps.executeQuery();
					rs.first();
					double aoun = rs.getDouble("Balance");
					textFieldOppingAmount.setText(String.valueOf(df.format(aoun)));

					Component[] com = panel_info.getComponents();
					Component[] com1 = panel_path.getComponents();
					Component[] com2 = panel_info2.getComponents();
					textAreaNotes.setEnabled(false);
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
		scrollPaneName.setViewportView(listName);
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBalance.setBounds(551, 37, 56, 17);
		panel_info.add(lblBalance);
		
		textFieldBalance = new JTextField();
		textFieldBalance.setEditable(false);
		textFieldBalance.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldBalance.setColumns(10);
		textFieldBalance.setBounds(597, 34, 118, 23);
		panel_info.add(textFieldBalance);
		panel_info.setFocusTraversalPolicy(new CLASS.FocusTraversalOnArray(
				new Component[] { textFieldName, textFieldBussinessName, textFieldAddress, textFieldAge,
						textFieldContactNo, comboBoxAccountCa, checkBoxaccountcatagery, textFieldAccountcategary,
						textFieldOppingAmount, textFieldNomeny, comboBoxNomeny, checkBoxNomeny, textField_NomenyOther,
						textFieldOtherAmount, panel_path, buttonbrowger, textFieldFiePath, textAreaNotes, label_2, label_3,
						label_4, label_5, lblDocumentFileUpload, lblNote, label, scrollPane_1, label_8, lblOther,
						lblBussinessName, label_7, label_9, label_10, lblOtherAmount }));

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(0, 323, 1154, 60);
		contentPane.add(panel_2);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = textFieldFiePath.getText();
				if (!textFieldName.getText().equals("") && !textFieldOppingAmount.getText().equals("")) {
					if (!path.equals("path")) {

						try {
							f = new File(textFieldFiePath.getText());
							FileInputStream fin = new FileInputStream(f);
							int len = (int) f.length();

							conn = (Connection) DBConnection.getConnection();
							String in = "insert into banksystem.current values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
							ps = conn.prepareStatement(in);
							ps.setInt(1, Integer.parseInt(textFieldSrNoMaster.getText()));
							ps.setInt(2, Integer.parseInt(textFielSrNo.getText()));
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooserCurrent.getDate();
							String date = (String) sdf.format(dateChooserCurrent.getDate());
							java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
							ps.setDate(3, (java.sql.Date) d);
							ps.setString(4, textFieldName.getText());
							ps.setString(5, textFieldAccountNo.getText());
							ps.setString(6, textFieldBussinessName.getText());
							ps.setString(7, textFieldAddress.getText());
							ps.setDouble(8, Double.valueOf(textFieldAge.getText()));
							ps.setString(9, textFieldContactNo.getText());
							ps.setString(10, textFieldAccountcategary.getText());
							ps.setDouble(11, Double.valueOf(textFieldOppingAmount.getText()));
							ps.setString(12, textFieldNomeny.getText());
							ps.setString(13, textField_NomenyOther.getText());
							ps.setBinaryStream(14, fin, len);
							ps.setString(15, textFieldFiePath.getText());
							ps.setString(16, textAreaNotes.getText());
							ps.setString(17, textFieldJoName.getText());
							ps.setString(18, textFieldJoAge.getText());
							ps.setString(19, textFieldJoContactNo.getText());
							ps.setString(20, textFieldJoRelationOther.getText());
							ps.setDouble(21, Double.valueOf(textFieldOtherAmount.getText()));
							int i = ps.executeUpdate();
							if (i > 0) {

								try {
									conn = (Connection) DBConnection.getConnection();
									String maxno = "select max(SrNoMaster) from banksystem.currenttranscation;";
									ps1 = conn.prepareStatement(maxno);
									rs1 = ps1.executeQuery();
									while (rs1.next()) {
										issrno = rs1.getInt(1) + 1;
									}

									String insertdata = "insert into banksystem.currenttranscation (SrNoMaster, SrNo, Date,Name,AccountNo, TransactionParticulars, Balance) values(?,?,?,?,?,?,?);";
									ps2 = conn.prepareStatement(insertdata);
									ps2.setInt(1, issrno);
									ps2.setInt(2, 1);
									SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
									dt1 = dateChooserCurrent.getDate();
									String date44 = (String) sdf4.format(dateChooserCurrent.getDate());
									java.sql.Date d1 = new java.sql.Date(sdf4.parse(date44).getTime());
									ps2.setDate(3, (java.sql.Date) d1);
									ps2.setString(4, textFieldName.getText());
									ps2.setString(5, textFieldAccountNo.getText());
									ps2.setString(6, "opening account");
									ps2.setDouble(7, Double.valueOf(textFieldOppingAmount.getText()));
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
								ps2.close();
								conn.close();
							} catch (Exception ew) {
								System.out.println(ew.getMessage());
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
								dt1 = dateChooserCurrent.getDate();
								String date = (String) sdf.format(dateChooserCurrent.getDate());
								java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
								ps1.setDate(2, (java.sql.Date) d);
								ps1.setString(3, textFieldName.getText());
								ps1.setString(4, textFieldAccountNo.getText());
								ps1.setDouble(5, Double.valueOf(textFieldOtherAmount.getText()));
								ps1.setString(6, textAreaNotes.getText());
								ps1.executeUpdate();

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

						try {
							JOptionPane.showMessageDialog(null, "Data are saved.");
							tabledataview();
							totalamount();
							increment();
							reset();

						}

						catch (Exception ee) {
							System.out.println(ee.getMessage());
						}
					} else {
						try {

							conn = (Connection) DBConnection.getConnection();
							String in = "insert into banksystem.current(SrNoMaster, SrNo, Date, Name, AccountNumber, BussinessName, Address, Age, Contactno, AcountCat, OppingAmount, Nomeny, NomenyRelation, FilePath, Notes, JoinName, JoinAge, JoinContactNO, JoinRelation, OtherAmount) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
							ps = conn.prepareStatement(in);
							ps.setInt(1, Integer.parseInt(textFieldSrNoMaster.getText()));
							ps.setInt(2, Integer.parseInt(textFielSrNo.getText()));
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooserCurrent.getDate();
							String date = (String) sdf.format(dateChooserCurrent.getDate());
							java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
							ps.setDate(3, (java.sql.Date) d);
							ps.setString(4, textFieldName.getText());
							ps.setString(5, textFieldAccountNo.getText());
							ps.setString(6, textFieldBussinessName.getText());
							ps.setString(7, textFieldAddress.getText());
							ps.setDouble(8, Double.valueOf(textFieldAge.getText()));
							ps.setString(9, textFieldContactNo.getText());
							ps.setString(10, textFieldAccountcategary.getText());
							ps.setDouble(11, Double.valueOf(textFieldOppingAmount.getText()));
							ps.setString(12, textFieldNomeny.getText());
							ps.setString(13, textField_NomenyOther.getText());
							ps.setString(14, textFieldFiePath.getText());
							ps.setString(15, textAreaNotes.getText());
							ps.setString(16, textFieldJoName.getText());
							ps.setString(17, textFieldJoAge.getText());
							ps.setString(18, textFieldJoContactNo.getText());
							ps.setString(19, textFieldJoRelationOther.getText());
							ps.setDouble(20, Double.valueOf(textFieldOtherAmount.getText()));
							int i = ps.executeUpdate();
							if (i > 0) {

								try {
									conn = (Connection) DBConnection.getConnection();
									String maxno = "select max(SrNoMaster) from banksystem.currenttranscation;";
									ps1 = conn.prepareStatement(maxno);
									rs1 = ps1.executeQuery();
									while (rs1.next()) {
										issrno = rs1.getInt(1) + 1;
									}

									String insertdata = "insert into banksystem.currenttranscation (SrNoMaster, SrNo, Date,Name,AccountNo, TransactionParticulars, Balance) values(?,?,?,?,?,?,?);";
									ps2 = conn.prepareStatement(insertdata);
									ps2.setInt(1, issrno);
									ps2.setInt(2, 1);
									SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
									dt1 = dateChooserCurrent.getDate();
									String date44 = (String) sdf4.format(dateChooserCurrent.getDate());
									java.sql.Date d1 = new java.sql.Date(sdf4.parse(date44).getTime());
									ps2.setDate(3, (java.sql.Date) d1);
									ps2.setString(4, textFieldName.getText());
									ps2.setString(5, textFieldAccountNo.getText());
									ps2.setString(6, "opening account");
									ps2.setDouble(7, Double.valueOf(textFieldOppingAmount.getText()));
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
								ps2.close();
								conn.close();
							} catch (Exception ew) {
								System.out.println(ew.getMessage());
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
								dt1 = dateChooserCurrent.getDate();
								String date = (String) sdf.format(dateChooserCurrent.getDate());
								java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
								ps1.setDate(2, (java.sql.Date) d);
								ps1.setString(3, textFieldName.getText());
								ps1.setString(4, textFieldAccountNo.getText());
								ps1.setDouble(5, Double.valueOf(textFieldOtherAmount.getText()));
								ps1.setString(6, textAreaNotes.getText());
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
							} catch (Exception ew) {
								System.out.println(ew.getMessage());
							}
						}

						try {
							JOptionPane.showMessageDialog(null, "Data are saved.");
							tabledataview();
							increment();
							reset();

						}

						catch (Exception ee) {
							System.out.println(ee.getMessage());
						}

					}
				}

			}

			private void tabledataview() {

				String data0 = textFieldSrNoMaster.getText();
				String data1 = textFielSrNo.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				dt1 = dateChooserCurrent.getDate();
				String data2 = (String) sdf.format(dateChooserCurrent.getDate());
				String data3 = textFieldAccountNo.getText();
				String data4 = textFieldName.getText();
				String data5 = textFieldBussinessName.getText();
				String data6 = textFieldOppingAmount.getText();
				String data7 = textFieldOtherAmount.getText();
				String data8 = textFieldNomeny.getText();
				String data9 = textField_NomenyOther.getText();
				String data10 = textFieldAccountcategary.getText();
				String data11 = textFieldAddress.getText();
				String data12 = textFieldAge.getText();
				String data13 = textFieldContactNo.getText();
				String data14 = textFieldFiePath.getText();
				String data15 = textAreaNotes.getText();
				String data16 = textFieldJoName.getText();
				String data17 = textFieldJoAge.getText();
				String data18 = textFieldJoContactNo.getText();
				String data19 = textFieldJoRelationOther.getText();
				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12, data13, data14, data15, data16, data17, data18, data19 };
				model1 = (DefaultTableModel) tableCurrentdatashow.getModel();
				model1.addRow(row);

			}
		});
		btnSave.setIcon(new ImageIcon(CurrentA_C.class.getResource("/ImageButtonIcon/Save.png")));
		btnSave.setHorizontalAlignment(SwingConstants.LEADING);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBounds(216, 11, 115, 38);
		panel_2.add(btnSave);

		 button_update789 = new JButton("Update");
		button_update789.setVisible(false);
		button_update789.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = textFieldFiePath.getText();
				if (!path.equals("path")) {

					try {

						FileInputStream fin = new FileInputStream(f);
						int len = (int) f.length();

						conn = (Connection) DBConnection.getConnection();
						String up = "UPDATE banksystem.current set Date=?, Name=?, AccountNumber=?, BussinessName=?, Address=?, Age=?, Contactno=?, AcountCat=?, OppingAmount=?,"
								+ " Nomeny=?, NomenyRelation=?, FileData=?, FilePath=?, Notes=?, JoinName=?, JoinAge=?, JoinContactNO=?, JoinRelation=?, OtherAmount=?  where SrNoMaster=?";
						ps = conn.prepareStatement(up);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						dt1 = dateChooserCurrent.getDate();
						String date = (String) sdf.format(dateChooserCurrent.getDate());
						java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
						ps.setDate(1, (java.sql.Date) d);
						ps.setString(2, textFieldName.getText());
						ps.setString(3, textFieldAccountNo.getText());
						ps.setString(4, textFieldBussinessName.getText());
						ps.setString(5, textFieldAddress.getText());
						ps.setDouble(6, Double.valueOf(textFieldAge.getText()));
						ps.setString(7, textFieldContactNo.getText());
						ps.setString(8, textFieldAccountcategary.getText());
						ps.setDouble(9, Double.valueOf(textFieldOppingAmount.getText()));
						ps.setString(10, textFieldNomeny.getText());
						ps.setString(11, textField_NomenyOther.getText());
						ps.setBinaryStream(12, fin, len);
						ps.setString(13, textFieldFiePath.getText());
						ps.setString(14, textAreaNotes.getText());
						ps.setString(15, textFieldJoName.getText());
						ps.setString(16, textFieldJoAge.getText());
						ps.setString(17, textFieldJoContactNo.getText());
						ps.setString(18, textFieldJoRelationOther.getText());
						ps.setDouble(19, Double.valueOf(textFieldOtherAmount.getText()));
						ps.setInt(20, Integer.parseInt(textFieldSrNoMaster.getText()));

						int i = ps.executeUpdate();
						if (i > 0) {
							JOptionPane.showMessageDialog(null, "Data are update.");
							dispose();
							CurrentA_C cur = new CurrentA_C();
							cur.setUndecorated(true);
							cur.setVisible(true);

						}

					} catch (Exception e2) {
						System.out.print(e2.getMessage());
					} finally {
						try {

							ps.close();
							conn.close();
						} catch (Exception ew) {
							System.out.println(ew.getMessage());
						}
					}
				} else {
					try {

						conn = (Connection) DBConnection.getConnection();
						String up = "UPDATE banksystem.current set Date=?, Name=?, AccountNumber=?, BussinessName=?, Address=?, Age=?, Contactno=?, AcountCat=?, OppingAmount=?,"
								+ " Nomeny=?, NomenyRelation=?, FilePath=?, Notes=?, JoinName=?, JoinAge=?, JoinContactNO=?, JoinRelation=?, OtherAmount=?  where SrNoMaster=?";
						ps = conn.prepareStatement(up);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						dt1 = dateChooserCurrent.getDate();
						String date = (String) sdf.format(dateChooserCurrent.getDate());
						java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
						ps.setDate(1, (java.sql.Date) d);
						ps.setString(2, textFieldName.getText());
						ps.setString(3, textFieldAccountNo.getText());
						ps.setString(4, textFieldBussinessName.getText());
						ps.setString(5, textFieldAddress.getText());
						ps.setDouble(6, Double.valueOf(textFieldAge.getText()));
						ps.setString(7, textFieldContactNo.getText());
						ps.setString(8, textFieldAccountcategary.getText());
						ps.setDouble(9, Double.valueOf(textFieldOppingAmount.getText()));
						ps.setString(10, textFieldNomeny.getText());
						ps.setString(11, textField_NomenyOther.getText());

						ps.setString(12, textFieldFiePath.getText());
						ps.setString(13, textAreaNotes.getText());
						ps.setString(14, textFieldJoName.getText());
						ps.setString(15, textFieldJoAge.getText());
						ps.setString(16, textFieldJoContactNo.getText());
						ps.setString(17, textFieldJoRelationOther.getText());
						ps.setDouble(18, Double.valueOf(textFieldOtherAmount.getText()));
						ps.setInt(19, Integer.parseInt(textFieldSrNoMaster.getText()));

						int i = ps.executeUpdate();
						if (i > 0) {
							JOptionPane.showMessageDialog(null, "Data are update.");
							dispose();
							CurrentA_C cur = new CurrentA_C();
							cur.setUndecorated(true);
							cur.setVisible(true);
							reset();
						}

					} catch (Exception e2) {
						System.out.print(e2.getMessage());
					} finally {
						try {

							ps.close();
							conn.close();
						} catch (Exception ew) {
							System.out.println(ew.getMessage());
						}
					}
				}

			}
		});
		button_update789.setIcon(new ImageIcon(CurrentA_C.class.getResource("/ImageButtonIcon/update.png")));
		button_update789.setToolTipText("");
		button_update789.setHorizontalAlignment(SwingConstants.LEADING);
		button_update789.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_update789.setBounds(60, 11, 126, 38);
		panel_2.add(button_update789);

		JButton button_4 = new JButton("Delete");
		button_4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					conn = (Connection) DBConnection.getConnection();
					String de = "delete from banksystem.currenttranscation where AccountNo=?";
					ps = conn.prepareStatement(de);
					ps.setString(1, textFieldAccountNo.getText());
					int i = ps.executeUpdate();
					if (i > 0) {
						try {
							conn = (Connection) DBConnection.getConnection();
							String deww = "delete from banksystem.current where AccountNumber=?";
							ps = conn.prepareStatement(deww);
							ps.setString(1, textFieldAccountNo.getText());
							int iw = ps.executeUpdate();
							if (iw > 0) {
								JOptionPane.showMessageDialog(null, "Data are deleted.");
								dispose();
								CurrentA_C cu = new CurrentA_C();
								cu.setUndecorated(true);
								cu.setVisible(true);

							}

						} catch (Exception qe) {
							System.out.print(qe.getMessage());
						}

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
		button_4.setIcon(new ImageIcon(CurrentA_C.class.getResource("/ImageButtonIcon/delete.jpg")));
		button_4.setHorizontalAlignment(SwingConstants.LEADING);
		button_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_4.setBounds(474, 11, 115, 38);
		panel_2.add(button_4);

		JButton button_5 = new JButton("Reset");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		button_5.setIcon(new ImageIcon(CurrentA_C.class.getResource("/ImageButtonIcon/Reset5.jpg")));
		button_5.setHorizontalAlignment(SwingConstants.LEADING);
		button_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_5.setBounds(599, 11, 115, 38);
		panel_2.add(button_5);

		JButton button_6 = new JButton("Exit");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Welcome.frame.enable();
			}
		});
		button_6.setIcon(new ImageIcon(CurrentA_C.class.getResource("/ImageButtonIcon/Exit.png")));
		button_6.setHorizontalAlignment(SwingConstants.LEADING);
		button_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_6.setBounds(724, 11, 115, 38);
		panel_2.add(button_6);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
				btnEdit.setVisible(false);
				button_update789.setVisible(true);
				button_update789.setBounds(338, 11, 126, 38);
				
				Component[] com = panel_info.getComponents();
				Component[] com1 = panel_path.getComponents();
				Component[] com2 = panel_info2.getComponents();
				textAreaNotes.setEnabled(true);
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
				catch(Exception ee)
				{
					System.out.println(ee.getMessage());
				}
				
			}
		});
		btnEdit.setIcon(new ImageIcon(CurrentA_C.class.getResource("/ImageButtonIcon/edit.png")));
		btnEdit.setToolTipText("");
		btnEdit.setHorizontalAlignment(SwingConstants.LEADING);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(338, 11, 126, 38);
		panel_2.add(btnEdit);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(0, 394, 1154, 250);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 1134, 202);
		panel_3.add(scrollPane);

		tableCurrentdatashow = new JTable();
		tableCurrentdatashow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					int i = tableCurrentdatashow.getSelectedRow();
					model1 = (DefaultTableModel) tableCurrentdatashow.getModel();

					textFieldSrNoMaster.setText(model1.getValueAt(i, 1).toString());
					textFielSrNo.setText(model1.getValueAt(i, 2).toString());
					// dateChooserCurrent.setDateFormatString(model1.getValueAt(i,
					// 3).toString());
					textFieldAccountNo.setText(model1.getValueAt(i, 4).toString());
					textFieldName.setText(model1.getValueAt(i, 5).toString());
					textFieldBussinessName.setText(model1.getValueAt(i, 6).toString());
					textFieldBalance.setText(model1.getValueAt(i, 7).toString());
					textFieldOtherAmount.setText(model1.getValueAt(i, 8).toString());
					textFieldNomeny.setText(model1.getValueAt(i, 9).toString());
					textField_NomenyOther.setText(model1.getValueAt(i, 10).toString());
					textFieldAccountcategary.setText(model1.getValueAt(i, 11).toString());
					textFieldAddress.setText(model1.getValueAt(i, 12).toString());
					textFieldAge.setText(model1.getValueAt(i, 13).toString());
					textFieldContactNo.setText(model1.getValueAt(i, 14).toString());
					textFieldFiePath.setText(model1.getValueAt(i, 15).toString());
					f = new File(model1.getValueAt(i, 15).toString());
					textAreaNotes.setText(model1.getValueAt(i, 16).toString());
					textFieldJoName.setText(model1.getValueAt(i, 17).toString());
					textFieldJoAge.setText(model1.getValueAt(i, 18).toString());
					textFieldJoContactNo.setText(model1.getValueAt(i, 19).toString());
					textFieldJoRelationOther.setText(model1.getValueAt(i, 20).toString());
				} catch (Exception ew) {
				}
				try {
					che();
				} catch (Exception ww) {
				}
				
				
				try {
					conn = (Connection) DBConnection.getConnection();
					String amount = "select Balance from banksystem.currenttranscation  where AccountNo='"
							+ textFieldAccountNo.getText() + "'";
					ps = conn.prepareStatement(amount);
					rs = ps.executeQuery();
					rs.first();
					double aoun = rs.getDouble("Balance");
					textFieldOppingAmount.setText(String.valueOf(df.format(aoun)));

					Component[] com = panel_info.getComponents();
					Component[] com1 = panel_path.getComponents();
					Component[] com2 = panel_info2.getComponents();
					textAreaNotes.setEnabled(false);
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
				int rows = tableCurrentdatashow.getRowCount() - 1;

				for (int i = 0; i < tableCurrentdatashow.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableCurrentdatashow.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableCurrentdatashow.getSelectedRow();
						int col = tableCurrentdatashow.getSelectedColumn();
						tableCurrentdatashow.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableCurrentdatashow.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableCurrentdatashow.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableCurrentdatashow.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableCurrentdatashow.setValueAt((Object) s, 0, 0);
							tableCurrentdatashow.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableCurrentdatashow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableCurrentdatashow.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableCurrentdatashow.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Select", "SrNoMaster", "SrNo", "Date", "A/C No", "Name ", "Bussiness Name", "Balance", "Other Charge", "Nomeny Name", "Nomeny Relation", "A/C Category", "Address", "Age", "Contact No", "File Path", "Note", "Join Name", "Age", "Contact No", "Relation"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableCurrentdatashow.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableCurrentdatashow.getColumnModel().getColumn(0).setMinWidth(50);
		tableCurrentdatashow.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableCurrentdatashow.getColumnModel().getColumn(1).setMinWidth(0);
		tableCurrentdatashow.getColumnModel().getColumn(1).setMaxWidth(0);
		tableCurrentdatashow.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableCurrentdatashow.getColumnModel().getColumn(2).setMinWidth(50);
		tableCurrentdatashow.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(3).setMinWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(4).setMinWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableCurrentdatashow.getColumnModel().getColumn(5).setMinWidth(150);
		tableCurrentdatashow.getColumnModel().getColumn(6).setPreferredWidth(150);
		tableCurrentdatashow.getColumnModel().getColumn(6).setMinWidth(150);
		tableCurrentdatashow.getColumnModel().getColumn(7).setPreferredWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(7).setMinWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(8).setPreferredWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(8).setMinWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(9).setPreferredWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(9).setMinWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(10).setPreferredWidth(150);
		tableCurrentdatashow.getColumnModel().getColumn(10).setMinWidth(150);
		tableCurrentdatashow.getColumnModel().getColumn(11).setPreferredWidth(0);
		tableCurrentdatashow.getColumnModel().getColumn(11).setMinWidth(0);
		tableCurrentdatashow.getColumnModel().getColumn(11).setMaxWidth(0);
		tableCurrentdatashow.getColumnModel().getColumn(12).setPreferredWidth(150);
		tableCurrentdatashow.getColumnModel().getColumn(12).setMinWidth(150);
		tableCurrentdatashow.getColumnModel().getColumn(13).setPreferredWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(13).setMinWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(14).setPreferredWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(14).setMinWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(15).setPreferredWidth(150);
		tableCurrentdatashow.getColumnModel().getColumn(15).setMinWidth(150);
		tableCurrentdatashow.getColumnModel().getColumn(16).setPreferredWidth(150);
		tableCurrentdatashow.getColumnModel().getColumn(16).setMinWidth(150);
		tableCurrentdatashow.getColumnModel().getColumn(17).setPreferredWidth(150);
		tableCurrentdatashow.getColumnModel().getColumn(17).setMinWidth(150);
		tableCurrentdatashow.getColumnModel().getColumn(18).setPreferredWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(18).setMinWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(19).setPreferredWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(19).setMinWidth(80);
		tableCurrentdatashow.getColumnModel().getColumn(20).setPreferredWidth(150);
		tableCurrentdatashow.getColumnModel().getColumn(20).setMinWidth(150);
		showdata();
		scrollPane.setViewportView(tableCurrentdatashow);

		JButton button_8 = new JButton("Report");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					for (int i = 0; i <= tableCurrentdatashow.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableCurrentdatashow.getValueAt(i, 0).toString());
						if (che) {
							String accno4 = String.valueOf(tableCurrentdatashow.getValueAt(i, 4).toString());

							java.io.InputStream file = getClass().getResourceAsStream("/Reports/Current.jrxml");
							accno = String.valueOf(tableCurrentdatashow.getValueAt(i, 4).toString());
							HashMap para = new HashMap();
							para.put("Acno", accno4);
							allinonereport r = new allinonereport("Current Report", file, para);
							break;
						}

					}
				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		button_8.setIcon(new ImageIcon(CurrentA_C.class.getResource("/ImageButtonIcon/Report4d.png")));
		button_8.setHorizontalAlignment(SwingConstants.LEADING);
		button_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_8.setBounds(10, 655, 153, 38);
		contentPane.add(button_8);

		 panel_info2 = new JPanel();
		 panel_info2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_info2.setBounds(0, 264, 1154, 48);
		contentPane.add(panel_info2);
		panel_info2.setLayout(null);

		JRadioButton rdbtnJoinAccount = new JRadioButton("Join Account");
		rdbtnJoinAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnJoinAccount.isSelected()) {
					panelJoinName.setVisible(true);
				} else {
					panelJoinName.setVisible(false);
				}
			}
		});
		rdbtnJoinAccount.setBounds(6, 7, 115, 23);
		panel_info2.add(rdbtnJoinAccount);

		panelJoinName = new JPanel();
		panelJoinName.setVisible(false);
		panelJoinName.setBounds(134, 7, 1010, 30);
		panel_info2.add(panelJoinName);
		panelJoinName.setLayout(null);

		JLabel label_11 = new JLabel("Name:");
		label_11.setBounds(0, 4, 46, 14);
		panelJoinName.add(label_11);

		textFieldJoName = new JTextField();
		textFieldJoName.setColumns(10);
		textFieldJoName.setBounds(36, 1, 162, 20);
		panelJoinName.add(textFieldJoName);

		JLabel label_12 = new JLabel("Age:");
		label_12.setBounds(205, 4, 46, 14);
		panelJoinName.add(label_12);

		textFieldJoAge = new JTextField();
		textFieldJoAge.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textFieldJoAge.setText("");
			}
		});
		textFieldJoAge.setText("0");
		textFieldJoAge.setColumns(10);
		textFieldJoAge.setBounds(232, 1, 61, 20);
		panelJoinName.add(textFieldJoAge);

		JLabel label_13 = new JLabel("Contact No:");
		label_13.setBounds(303, 4, 90, 14);
		panelJoinName.add(label_13);

		textFieldJoContactNo = new JTextField();
		textFieldJoContactNo.setColumns(10);
		textFieldJoContactNo.setBounds(378, 1, 136, 20);
		panelJoinName.add(textFieldJoContactNo);

		JLabel label_14 = new JLabel("Relation:");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_14.setBounds(524, 3, 74, 17);
		panelJoinName.add(label_14);

		JComboBox comboBoxJoRelation = new JComboBox();
		comboBoxJoRelation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = comboBoxJoRelation.getSelectedItem().toString();
				textFieldJoRelationOther.setText("");
				textFieldJoRelationOther.setText(name);
			}
		});
		comboBoxJoRelation.setModel(new DefaultComboBoxModel(new String[] {"   Select", "   Father", "   Mother", "   Paternal Grandfather", "    Maternal Grandfather ", "    Maternal Grandmother", "    Brother", "    Brother's Wife", "    Elder Brother", "    Younger Brother", "    Husband's sister", "    Sister", "    Sister's husband", "    Elder Sister", "    Younger Sister)", "    Husband's elder brother", "    Husband's younger brother", "    Elder brother's wife", "    Younger brother's wife", "    Wife's Sister", "    Wife's Brother", "    Wife's Brother's wife", "    Husband's Sister's Husband", "    Wife's sister's husband", "    Husband's elder brother's wife", "    Husband's younger brother's wife", "    Son", "    Son's wife (daughter-in-law) ", "    Daughter ", "    Daughter's husband (son-in-law) ", "    Grandson (son's son)", "    Granddaughter (son's daughter)", "    Grandson (daughter's son)", "    Granddaughter (daughter's daughter)", "    Husband", "    Wife", "    Husband's Mother (Mother-in-law)", "    Husband's Father (Father-in-law)", "    Fianc\u00E9 or Fianc\u00E9e", "    Father's younger brother (uncle)", "    Father's younger brother's wife (aunt)", "    Father's elder brother's (Uncle)", "    Father's elder brother's wife (Aunt) ", "    Father's sister (aunt)", "    Father's sister's husband", "    Mother's brother", "    Mother's brother's wife", "    Mother's sister", "    Mother's sister's husband", "    Brother's son (nephew)", "    Sister's son (nephew)", "    Brother's daughter (niece)", "    Sister's daughter (niece) ", "    Husband's elder brother's daughter", "    Husband's elder brother's son"}));
		comboBoxJoRelation.setBounds(576, 0, 133, 23);
		panelJoinName.add(comboBoxJoRelation);

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
		checkBoxJoRelation.setBounds(715, 0, 27, 23);
		panelJoinName.add(checkBoxJoRelation);

		JLabel label_15 = new JLabel("Other:");
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_15.setBounds(748, 3, 46, 17);
		panelJoinName.add(label_15);

		textFieldJoRelationOther = new JTextField();
		textFieldJoRelationOther.setText("Other");
		textFieldJoRelationOther.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldJoRelationOther.setEnabled(false);
		textFieldJoRelationOther.setColumns(10);
		textFieldJoRelationOther.setBounds(790, 0, 215, 23);
		panelJoinName.add(textFieldJoRelationOther);

		JButton button = new JButton("View Transaction");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for (int i = 0; i <= tableCurrentdatashow.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableCurrentdatashow.getValueAt(i, 0).toString());
						if (che) {
							accno = String.valueOf(tableCurrentdatashow.getValueAt(i, 4).toString());
							TranCurrent2 tr = new TranCurrent2();
							tr.setUndecorated(true);
							tr.setVisible(true);

						}
					}
				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(CurrentA_C.class.getResource("/ImageButtonIcon/TrancationView.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(173, 655, 194, 38);
		contentPane.add(button);

		JLabel lblTotalOppningAmt = new JLabel("Total Balance:");
		lblTotalOppningAmt.setBounds(853, 657, 124, 14);
		contentPane.add(lblTotalOppningAmt);

		textFieldTotalAmtB = new JTextField();
		textFieldTotalAmtB.setForeground(Color.RED);
		textFieldTotalAmtB.setEditable(false);
		textFieldTotalAmtB.setColumns(10);
		textFieldTotalAmtB.setBounds(987, 655, 132, 20);
		contentPane.add(textFieldTotalAmtB);
	}

	public void increment() {
		try {

			conn = (Connection) DBConnection.getConnection();
			ps1 = conn.prepareStatement("select max(SrNoMaster) from banksystem.current;");
			rs = ps1.executeQuery();
			while (rs.next()) {
				int val = (rs.getInt(1) + 1);
				val1 = String.valueOf(val);

			}
			textFieldSrNoMaster.setText(val1);
			textFielSrNo.setText(val1);
			textFieldAccountNo.setText("C" + val1);

			SimpleDateFormat insdf = new SimpleDateFormat("yyyy-MM-dd");
			Date indate = new Date();
			String instringdate = insdf.format(indate);
			dateChooserCurrent.setDate(insdf.parse(instringdate));

		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		} finally {
			try {
				rs.close();
				ps1.close();
				conn.close();
			} catch (Exception ew) {
				System.out.println(ew.getMessage());
			}
		}
	}

	public void reset() {
		increment();
		textFieldName.setText("");
		textFieldAddress.setText("");
		textFieldAge.setText("0");
		textFieldContactNo.setText("");
		textFieldAccountcategary.setText("");
		textFieldFiePath.setText("path");

		textFieldOppingAmount.setText("0");
		textAreaNotes.setText("");
		textFieldNomeny.getText();
		textFieldNomeny.setText("");
		textFieldBussinessName.setText("");
		textFieldOtherAmount.setText("0");
	}

	public void showdata() {
		try {
			conn = (Connection) DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.current  order by Srnomaster;";

			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("SrNo");
				String data2 = rs.getString("Date");
				String data3 = rs.getString("AccountNumber");
				String data4 = rs.getString("Name");
				String data5 = rs.getString("BussinessName");
				String data6 = rs.getString("OppingAmount");
				String data7 = rs.getString("OtherAmount");
				String data8 = rs.getString("Nomeny");
				String data9 = rs.getString("NomenyRelation");
				String data10 = rs.getString("AcountCat");
				String data11 = rs.getString("Address");
				String data12 = rs.getString("Age");
				String data13 = rs.getString("Contactno");
				String data14 = rs.getString("FilePath");
				String data15 = rs.getString("Notes");
				String data16 = rs.getString("JoinName");
				String data17 = rs.getString("JoinAge");
				String data18 = rs.getString("JoinContactNO");
				String data19 = rs.getString("JoinRelation");
				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12, data13, data14, data15, data16, data17, data18, data19 };
				model1 = (DefaultTableModel) tableCurrentdatashow.getModel();
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

	public void daycount(String st) {
		textFieldAccountcategary.setText(st);
	}

	public void totalamount() {
		int rowsCount = tableCurrentdatashow.getRowCount();
		double sum = 0;

		for (int i = 0; i < rowsCount; i++) {
			sum = sum + Double.valueOf(tableCurrentdatashow.getValueAt(i, 7).toString());
		}
		textFieldTotalAmtB.setText(String.valueOf(df.format(sum)));

	}
}
