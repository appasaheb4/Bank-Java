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
import javax.swing.JRadioButton;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionAdapter;

public class BankOwner extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField textFieldCheqNo;
	private JTextField textFieldCashName;
	private JTable tablebankowner;

	public JPanel panel_CashandChequeD;
	public JPanel panel_ChequeNoandName;
	public JRadioButton rdbtncash;
	public JRadioButton rdbtnCheque;
	private JTextField textFieldType;
	private JTextField textFieldPaymentMode;

	public DefaultTableModel model1;

	public Connection conn;
	public PreparedStatement ps;
	public String val1;
	public Statement st;
	public ResultSet rs;
	public JDateChooser dateChooserloangpage;
	java.util.Date dt1, dt2;
	private JTextField textField_SrNo;
	private JTextField textField_SrNoMaster;
	private JTextField textFieldBankName;
	public JRadioButton rdbtnBankAdd;
	public JButton btnBankAdd;
	public JComboBox comboBoxTanctionType;
	private JTextField textFieldAmoun;
	public JTextArea textAreaTransactionDetials;
	private JTextField textFieldBankName1;
	public JComboBox comboBoxBankName;
	public JLabel lblnotebankname;
	public JButton btnDeleteBank;

	public JDateChooser dateChooserBackOwner;
	private JTextField textFieldAccountNo;
	private JButton button_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankOwner frame = new BankOwner();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public BankOwner() throws ParseException {
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
				dateChooserBackOwner.requestDefaultFocus();
			}
		});

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1154, 96);
		contentPane.add(panel);
		panel.setLayout(null);

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

		JLabel lblNewLabel = new JLabel("Bank Transaction Details");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(10, 0, 318, 64);
		panel.add(lblNewLabel);

		Date date = new Date();

		textField_SrNo = new JTextField();
		textField_SrNo.setVisible(false);
		textField_SrNo.setEnabled(false);
		textField_SrNo.setColumns(10);
		textField_SrNo.setBounds(178, 37, 194, 20);
		panel.add(textField_SrNo);

		textField_SrNoMaster = new JTextField();
		textField_SrNoMaster.setVisible(false);
		textField_SrNoMaster.setEnabled(false);
		textField_SrNoMaster.setColumns(10);
		textField_SrNoMaster.setBounds(449, 37, 194, 20);
		increment();
		panel.add(textField_SrNoMaster);

		JLabel lblNewLabel_1 = new JLabel("Account No:");
		lblNewLabel_1.setBounds(20, 71, 125, 14);
		panel.add(lblNewLabel_1);

		textFieldAccountNo = new JTextField();
		textFieldAccountNo.setEditable(false);
		textFieldAccountNo.setBounds(169, 68, 86, 20);
		increment();
		panel.add(textFieldAccountNo);
		textFieldAccountNo.setColumns(10);

		button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculatorGUI cl = new CalculatorGUI("Bank System");
				cl.setSize(400, 350);
				cl.setFocusable(true);
				cl.setLocation(830, 0);
				cl.setVisible(true);
			}
		});
		button_5.setIcon(new ImageIcon(BankOwner.class.getResource("/ImageButtonIcon/clac.png")));
		button_5.setOpaque(false);
		button_5.setForeground(Color.RED);
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button_5.setContentAreaFilled(false);
		button_5.setBorderPainted(false);
		button_5.setBounds(1080, 9, 64, 55);
		panel.add(button_5);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 107, 1154, 320);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Bank Name:");
		lblNewLabel_2.setBounds(403, 174, 69, 14);
		panel_1.add(lblNewLabel_2);

		panel_CashandChequeD = new JPanel();
		panel_CashandChequeD.setVisible(false);
		panel_CashandChequeD.setBounds(520, 213, 508, 59);
		panel_1.add(panel_CashandChequeD);
		panel_CashandChequeD.setLayout(null);

		rdbtncash = new JRadioButton("Cash");
		rdbtncash.setSelected(true);
		rdbtncash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bankmanagement();
			}
		});
		buttonGroup_1.add(rdbtncash);
		rdbtncash.setBounds(138, 7, 64, 23);
		panel_CashandChequeD.add(rdbtncash);

		rdbtnCheque = new JRadioButton("Cheque");
		rdbtnCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bankmanagement();
			}
		});
		buttonGroup_1.add(rdbtnCheque);
		rdbtnCheque.setBounds(224, 7, 82, 23);
		panel_CashandChequeD.add(rdbtnCheque);

		panel_ChequeNoandName = new JPanel();
		panel_ChequeNoandName.setVisible(false);
		panel_ChequeNoandName.setBounds(10, 33, 498, 23);
		panel_CashandChequeD.add(panel_ChequeNoandName);
		panel_ChequeNoandName.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Cheque No:");
		lblNewLabel_3.setBounds(21, 4, 67, 14);
		panel_ChequeNoandName.add(lblNewLabel_3);

		textFieldCheqNo = new JTextField();
		textFieldCheqNo.setBounds(98, 0, 140, 23);
		panel_ChequeNoandName.add(textFieldCheqNo);
		textFieldCheqNo.setColumns(10);

		JLabel lblChequeName = new JLabel("Cheque Name:");
		lblChequeName.setVisible(false);
		lblChequeName.setBounds(248, 4, 89, 14);
		panel_ChequeNoandName.add(lblChequeName);

		textFieldCashName = new JTextField();
		textFieldCashName.setVisible(false);
		textFieldCashName.setBounds(347, 0, 141, 23);
		panel_ChequeNoandName.add(textFieldCashName);
		textFieldCashName.setColumns(10);

		textFieldType = new JTextField();
		textFieldType.setVisible(false);
		textFieldType.setText("Deposite");
		textFieldType.setColumns(10);
		textFieldType.setBounds(212, 63, 131, 23);
		panel_1.add(textFieldType);

		textFieldPaymentMode = new JTextField();
		textFieldPaymentMode.setVisible(false);
		textFieldPaymentMode.setText("Cash");
		textFieldPaymentMode.setColumns(10);
		textFieldPaymentMode.setBounds(212, 110, 131, 23);
		panel_1.add(textFieldPaymentMode);

		JLabel lblTransactionDate = new JLabel("Transaction Date:");
		lblTransactionDate.setBounds(395, 30, 107, 14);
		panel_1.add(lblTransactionDate);
		dateChooserBackOwner = new JDateChooser();
		dateChooserBackOwner.setBounds(520, 27, 184, 20);
		panel_1.add(dateChooserBackOwner);
		dateChooserBackOwner.setDateFormatString("yyyy-MM-dd");
		dateChooserBackOwner.setDate(date);

		JLabel lblTransactionType = new JLabel("Transaction Type:");
		lblTransactionType.setBounds(395, 64, 107, 14);
		panel_1.add(lblTransactionType);

		comboBoxTanctionType = new JComboBox();
		comboBoxTanctionType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tranctionType();
			}
		});
		comboBoxTanctionType.setModel(new DefaultComboBoxModel(new String[] { "Select", "Deposite", "Widraw" }));
		comboBoxTanctionType.setBounds(520, 61, 184, 20);
		panel_1.add(comboBoxTanctionType);

		JLabel lblTransactionDetails = new JLabel("Transaction Details:");
		lblTransactionDetails.setBounds(395, 89, 115, 14);
		panel_1.add(lblTransactionDetails);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(520, 92, 333, 73);
		panel_1.add(scrollPane_1);

		textAreaTransactionDetials = new JTextArea();
		textAreaTransactionDetials.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == 9) {

					comboBoxBankName.requestFocus();
				}
			}
		});
		scrollPane_1.setViewportView(textAreaTransactionDetials);

		comboBoxBankName = new JComboBox();
		comboBoxBankName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldBankName1.setText(comboBoxBankName.getSelectedItem().toString());
			}
		});
		comboBoxBankName.setModel(new DefaultComboBoxModel(new String[] { "Select" }));
		comboBoxBankName.setBounds(520, 171, 184, 20);
		banknameshow();
		panel_1.add(comboBoxBankName);

		JLabel lblModeOfPayment = new JLabel("Mode of Payment:");
		lblModeOfPayment.setBounds(403, 212, 107, 14);
		panel_1.add(lblModeOfPayment);

		rdbtnBankAdd = new JRadioButton("Bank Add");
		rdbtnBankAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bankname();
			}
		});
		rdbtnBankAdd.setBounds(710, 170, 82, 23);
		panel_1.add(rdbtnBankAdd);

		textFieldBankName = new JTextField();
		textFieldBankName.setVisible(false);
		textFieldBankName.setColumns(10);
		textFieldBankName.setBounds(797, 171, 231, 23);
		panel_1.add(textFieldBankName);

		btnBankAdd = new JButton("Add");
		btnBankAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String in = "insert into banksystem.bankname(SrNo,BankName)values(?,?);";
					ps = conn.prepareStatement(in);
					ps.setInt(1, Integer.parseInt(textField_SrNoMaster.getText()));
					ps.setString(2, textFieldBankName.getText());
					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Bank Name are added.");
						comboBoxBankName.addItem(textFieldBankName.getText().toString());

					}

				} catch (Exception ee) {
					System.err.println(ee.getMessage());
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
		btnBankAdd.setVisible(false);
		btnBankAdd.setBounds(1037, 170, 89, 23);
		panel_1.add(btnBankAdd);

		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(403, 293, 82, 14);
		panel_1.add(lblAmount);

		textFieldAmoun = new JTextField();
		textFieldAmoun.setBounds(520, 290, 184, 20);
		panel_1.add(textFieldAmoun);
		textFieldAmoun.setColumns(10);

		textFieldBankName1 = new JTextField();
		textFieldBankName1.setVisible(false);
		textFieldBankName1.setText("Bank");
		textFieldBankName1.setColumns(10);
		textFieldBankName1.setBounds(212, 154, 131, 23);
		panel_1.add(textFieldBankName1);

		lblnotebankname = new JLabel("Note: At time one bank added.");
		lblnotebankname.setVisible(false);
		lblnotebankname.setForeground(Color.RED);
		lblnotebankname.setBounds(807, 194, 221, 14);
		panel_1.add(lblnotebankname);

		btnDeleteBank = new JButton("Delete");
		btnDeleteBank.setVisible(false);
		btnDeleteBank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					conn = DBConnection.getConnection();
					String de = "delete from banksystem.bankname where BankName=?";
					ps = conn.prepareStatement(de);
					ps.setString(1, textFieldBankName.getText());
					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Bank Delete.");
						dispose();
						BankOwner fd = new BankOwner();
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
		btnDeleteBank.setBounds(1038, 208, 89, 23);
		panel_1.add(btnDeleteBank);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(0, 498, 1154, 206);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 11, 1134, 184);
		panel_4.add(scrollPane);

		tablebankowner = new JTable();
		tablebankowner.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablebankowner.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int i = tablebankowner.getSelectedRow();
					model1 = (DefaultTableModel) tablebankowner.getModel();
					textField_SrNoMaster.setText(model1.getValueAt(i, 1).toString());
					textField_SrNo.setText(model1.getValueAt(i, 2).toString());
					dateChooserBackOwner.setDateFormatString(model1.getValueAt(i, 3).toString());
					comboBoxBankName.setSelectedItem(model1.getValueAt(i, 4).toString());
					textFieldAmoun.setText(model1.getValueAt(i, 5).toString());
					comboBoxTanctionType.setSelectedItem(model1.getValueAt(i, 6).toString());
					textFieldPaymentMode.setText(model1.getValueAt(i, 7).toString());
					textFieldCheqNo.setText(model1.getValueAt(i, 8).toString());
					textFieldCashName.setText(model1.getValueAt(i, 9).toString());
					textAreaTransactionDetials.setText(model1.getValueAt(i, 10).toString());
				} catch (Exception es) {
				}
				try {
					che();
				} catch (Exception ss) {
				}

			}

			private void che() {
				boolean s = false;
				boolean s1 = true;
				String inc = null;
				int row = 0;
				int rows = tablebankowner.getRowCount() - 1;

				for (int i = 0; i < tablebankowner.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tablebankowner.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tablebankowner.getSelectedRow();
						int col = tablebankowner.getSelectedColumn();
						tablebankowner.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tablebankowner.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tablebankowner.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tablebankowner.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tablebankowner.setValueAt((Object) s, 0, 0);
							tablebankowner.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tablebankowner.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Select", "SrNoMaster", "SrNo", "Tran Date", "Bank Name", "Amount", "Tran Type",
						"Mode of Payment", "Cheque No", "Cheque Name", "Trans Details" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class, Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablebankowner.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablebankowner.getColumnModel().getColumn(0).setMinWidth(50);
		tablebankowner.getColumnModel().getColumn(1).setPreferredWidth(0);
		tablebankowner.getColumnModel().getColumn(1).setMinWidth(0);
		tablebankowner.getColumnModel().getColumn(1).setMaxWidth(0);
		tablebankowner.getColumnModel().getColumn(2).setPreferredWidth(50);
		tablebankowner.getColumnModel().getColumn(2).setMinWidth(50);
		tablebankowner.getColumnModel().getColumn(3).setPreferredWidth(80);
		tablebankowner.getColumnModel().getColumn(3).setMinWidth(80);
		tablebankowner.getColumnModel().getColumn(4).setPreferredWidth(150);
		tablebankowner.getColumnModel().getColumn(4).setMinWidth(150);
		tablebankowner.getColumnModel().getColumn(5).setPreferredWidth(80);
		tablebankowner.getColumnModel().getColumn(5).setMinWidth(80);
		tablebankowner.getColumnModel().getColumn(6).setPreferredWidth(80);
		tablebankowner.getColumnModel().getColumn(6).setMinWidth(80);
		tablebankowner.getColumnModel().getColumn(7).setPreferredWidth(80);
		tablebankowner.getColumnModel().getColumn(7).setMinWidth(8);
		tablebankowner.getColumnModel().getColumn(8).setPreferredWidth(80);
		tablebankowner.getColumnModel().getColumn(8).setMinWidth(80);
		tablebankowner.getColumnModel().getColumn(9).setPreferredWidth(80);
		tablebankowner.getColumnModel().getColumn(9).setMinWidth(80);
		tablebankowner.getColumnModel().getColumn(10).setPreferredWidth(80);
		tablebankowner.getColumnModel().getColumn(10).setMinWidth(80);
		showdata();
		scrollPane.setViewportView(tablebankowner);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(0, 438, 1154, 60);
		contentPane.add(panel_5);

		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String in = "insert into banksystem.ownerbank values(?,?,?,?,?,?,?,?,?,?,?);";
					ps = conn.prepareStatement(in);
					ps.setInt(1, Integer.parseInt(textField_SrNoMaster.getText()));
					ps.setInt(2, Integer.parseInt(textField_SrNo.getText()));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserBackOwner.getDate();
					String date = (String) sdf.format(dateChooserBackOwner.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(3, (java.sql.Date) d);
					ps.setString(4, textFieldAccountNo.getText());
					ps.setString(5, textFieldType.getText());
					ps.setString(6, String.valueOf(textAreaTransactionDetials.getText()));
					ps.setString(7, textFieldBankName1.getText());
					ps.setString(8, textFieldPaymentMode.getText());
					ps.setString(9, textFieldCheqNo.getText());
					ps.setString(10, textFieldCashName.getText());
					ps.setDouble(11, Double.valueOf(textFieldAmoun.getText()));
					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are saved.");
						tabledataview();
						increment();
						reset();
					}

				} catch (Exception ee) {
					System.out.print(ee.getMessage());
				} finally {
					try {

						ps.close();
						conn.close();
					} catch (Exception ew) {
						System.out.println(ew.getMessage());
					}
				}
			}

			private void tabledataview() {

				String data0 = textField_SrNoMaster.getText();
				String data1 = textField_SrNo.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				dt1 = dateChooserBackOwner.getDate();
				String data2 = (String) sdf.format(dateChooserBackOwner.getDate());
				String data3 = textFieldBankName1.getText();
				String data4 = textFieldAmoun.getText();
				String data5 = textFieldType.getText();
				String data6 = textFieldPaymentMode.getText();
				String data7 = textFieldCheqNo.getText();
				String data8 = textFieldCashName.getText();
				String data9 = textAreaTransactionDetials.getText().toString();

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9 };
				model1 = (DefaultTableModel) tablebankowner.getModel();
				model1.addRow(row);
			}

		});
		button.setIcon(new ImageIcon(BankOwner.class.getResource("/ImageButtonIcon/Save.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(216, 11, 115, 38);
		panel_5.add(button);

		JButton button_1 = new JButton("Update");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					conn = DBConnection.getConnection();
					String up = "UPDATE banksystem.ownerbank  set TranDate=?, TranType=?, TranDetils=?, BankName=?, PaymentMode=?, ChequeNo=?,ChequeName=?,Amunt=? where SrNoMaster=?";
					ps = conn.prepareStatement(up);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserBackOwner.getDate();
					String date = (String) sdf.format(dateChooserBackOwner.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(1, (java.sql.Date) d);
					ps.setString(2, textFieldType.getText());
					ps.setString(3, textAreaTransactionDetials.getText());
					ps.setString(4, textFieldBankName1.getText());
					ps.setString(5, textFieldPaymentMode.getText());
					ps.setString(6, textFieldCheqNo.getText());
					ps.setString(7, textFieldCashName.getText());
					ps.setDouble(8, Double.valueOf(textFieldAmoun.getText()));
					ps.setInt(9, Integer.parseInt(textField_SrNoMaster.getText()));

					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are update.");
						reset();
						dispose();
						BankOwner emp = new BankOwner();
						emp.setUndecorated(true);
						emp.setVisible(true);
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
		});
		button_1.setIcon(new ImageIcon(BankOwner.class.getResource("/ImageButtonIcon/update.png")));
		button_1.setToolTipText("");
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBounds(341, 11, 126, 38);
		panel_5.add(button_1);

		JButton button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					conn = DBConnection.getConnection();
					String de = "delete from banksystem.ownerbank where SrNomaster=?";
					ps = conn.prepareStatement(de);
					ps.setInt(1, Integer.parseInt(textField_SrNoMaster.getText()));
					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are deleted.");
						reset();
						dispose();
						BankOwner fd = new BankOwner();
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
		button_2.setIcon(new ImageIcon(BankOwner.class.getResource("/ImageButtonIcon/delete.jpg")));
		button_2.setHorizontalAlignment(SwingConstants.LEADING);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_2.setBounds(474, 11, 115, 38);
		panel_5.add(button_2);

		JButton button_3 = new JButton("Reset");
		button_3.setIcon(new ImageIcon(BankOwner.class.getResource("/ImageButtonIcon/Reset5.jpg")));
		button_3.setHorizontalAlignment(SwingConstants.LEADING);
		button_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_3.setBounds(599, 11, 115, 38);
		panel_5.add(button_3);

		JButton button_4 = new JButton("Exit");
		button_4.setIcon(new ImageIcon(BankOwner.class.getResource("/ImageButtonIcon/Exit.png")));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Welcome.frame.enable();
			}
		});
		button_4.setHorizontalAlignment(SwingConstants.LEADING);
		button_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_4.setBounds(724, 11, 115, 38);
		panel_5.add(button_4);
	}

	public void bankmanagement() {
		if (rdbtnCheque.isSelected()) {
			panel_ChequeNoandName.setVisible(true);
			textFieldPaymentMode.setText("Cheque");
		} else {
			panel_ChequeNoandName.setVisible(false);
			textFieldPaymentMode.setText("Cash");
		}

	}

	public void increment() {
		try {

			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("select max(SrNoMaster) from banksystem.ownerbank;");
			rs = ps.executeQuery();
			while (rs.next()) {
				int val = (rs.getInt(1) + 1);
				val1 = String.valueOf(val);
			}
			textField_SrNoMaster.setText(val1);
			textField_SrNo.setText(val1);
			textFieldAccountNo.setText("B-" + val1);

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

	public void reset() {
		comboBoxTanctionType.setSelectedIndex(0);
		textAreaTransactionDetials.setText("");
		comboBoxBankName.setSelectedIndex(0);
		textFieldCheqNo.setText("");
		textFieldCashName.setText("");
		textFieldAmoun.setText("");

	}

	public void showdata() {
		try {
			conn = DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.ownerbank order by Srnomaster;";

			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("SrNo");
				String data2 = rs.getString("TranDate");
				String data3 = rs.getString("BankName");
				String data4 = rs.getString("Amunt");
				String data5 = rs.getString("TranType");
				String data6 = rs.getString("PaymentMode");
				String data7 = rs.getString("ChequeNo");
				String data8 = rs.getString("ChequeName");
				String data9 = rs.getString("TranDetils");

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9 };
				model1 = (DefaultTableModel) tablebankowner.getModel();
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

	public void bankname() {
		if (rdbtnBankAdd.isSelected()) {
			lblnotebankname.setVisible(true);
			textFieldBankName.setVisible(true);
			btnBankAdd.setVisible(true);
			btnDeleteBank.setVisible(true);

		} else {
			lblnotebankname.setVisible(false);
			textFieldBankName.setVisible(false);
			btnBankAdd.setVisible(false);
			btnDeleteBank.setVisible(false);
		}
	}

	public void tranctionType() {

		if (comboBoxTanctionType.getSelectedItem() == "Deposite"
				|| comboBoxTanctionType.getSelectedItem() == "Widraw") {
			panel_CashandChequeD.setVisible(true);
			textFieldType.setText(comboBoxTanctionType.getSelectedItem().toString());

		} else {
			panel_CashandChequeD.setVisible(false);
			textFieldType.setText(comboBoxTanctionType.getSelectedItem().toString());
		}
	}

	public void banknameshow() {

		try {
			conn = DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.bankname order by SrNo;";

			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String BankName = rs.getString("BankName");
				comboBoxBankName.addItem(BankName);

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
}
