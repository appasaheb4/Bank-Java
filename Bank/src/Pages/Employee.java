package Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;

import CLASS.CalculatorGUI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Employee extends JFrame {

	private JPanel contentPane;
	public JDateChooser dateChooserEmp;
	private JTextField textFieldName;
	private JTextField textFieldAddress;
	private JTextField textFieldAge;
	private JTextField textFieldContactNo;
	private JTextField textFieldDays;
	private JTextField textFieldPaidAmount;
	private JTextField textFieldLeaves;
	private JTextField textFieldPayment;
	private JTable tableEmp;
	private JTextField textField_SrNo;
	private JTextField textField_SrNoMaster;

	public DefaultTableModel model1;
	public static double amountold = 0;
	public JRadioButton radioSavingAccount;

	public Connection conn;
	public PreparedStatement ps;
	public PreparedStatement ps1;
	public PreparedStatement ps2;
	public String val1;
	public Statement st;
	public ResultSet rs;
	public ResultSet rs1;
	public ResultSet rs2;
	public JDateChooser dateChooserloangpage;
	java.util.Date dt1, dt2;
	public JTextArea textAreaNote;
	public static int maxso;
	private JTextField textFieldAccountNo;
	public JButton btnSalaryPayment;
	public static String empname;
	public static double salary;
	public static double advancedleves;
	public static String leaves;
	public static String secounname;
	public static String name;
	public static String joindate;
	public static String acnoemp;
	public static String addemp;
	public static String ageemp;
	public static String contnoemp;
	public static String note;
	public static String salary55;
	public static String srnomaster;
	public JDateChooser dateChooserJoinDate;
	public JList<String> listName;
	public JScrollPane scrollPaneName;
	public String[] data = new String[1000];
	private JTextField textFieldInterst;

	public static String intert;

	public static String emp_name;
	public static String emp_salary;
	public static String emp_adpay;
	public static String emp_leaves;
	private JTextField textFieldTotalAmtB;
	public DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee frame = new Employee();
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
	public Employee() {
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
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {

			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1154, 67);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblEmployee = new JLabel("Employee");
		lblEmployee.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployee.setBounds(10, 0, 229, 45);
		panel.add(lblEmployee);

		Date date = new Date();
		dateChooserEmp = new JDateChooser();
		dateChooserEmp.setDateFormatString("yyyy-MM-dd");
		dateChooserEmp.setBounds(856, 36, 206, 20);
		dateChooserEmp.setDate(date);
		panel.add(dateChooserEmp);

		JLabel lblNewLabel = new JLabel("Date:");
		lblNewLabel.setBounds(800, 36, 46, 20);
		panel.add(lblNewLabel);

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

		textField_SrNo = new JTextField();
		textField_SrNo.setVisible(false);
		textField_SrNo.setEnabled(false);
		textField_SrNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_SrNo.setColumns(10);
		textField_SrNo.setBounds(185, 27, 209, 23);
		panel.add(textField_SrNo);

		textField_SrNoMaster = new JTextField();
		textField_SrNoMaster.setVisible(false);
		textField_SrNoMaster.setEnabled(false);
		textField_SrNoMaster.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_SrNoMaster.setColumns(10);
		textField_SrNoMaster.setBounds(428, 27, 209, 23);
		increment();
		panel.add(textField_SrNoMaster);

		JLabel lblAccountNumber = new JLabel("Account Number:");
		lblAccountNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAccountNumber.setBounds(10, 44, 108, 17);
		panel.add(lblAccountNumber);

		textFieldAccountNo = new JTextField();
		textFieldAccountNo.setEditable(false);
		textFieldAccountNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldAccountNo.setColumns(10);
		textFieldAccountNo.setBounds(115, 44, 209, 23);
		increment();
		panel.add(textFieldAccountNo);

		JButton button_7 = new JButton("");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculatorGUI cl = new CalculatorGUI("Bank System");
				cl.setSize(400, 350);
				cl.setFocusable(true);
				cl.setLocation(830, 0);
				cl.setVisible(true);
			}
		});
		button_7.setIcon(new ImageIcon(Employee.class.getResource("/ImageButtonIcon/clac.png")));
		button_7.setOpaque(false);
		button_7.setForeground(Color.RED);
		button_7.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button_7.setContentAreaFilled(false);
		button_7.setBorderPainted(false);
		button_7.setBounds(1073, 8, 64, 55);
		panel.add(button_7);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 73, 1154, 112);
		contentPane.add(panel_1);

		JLabel label = new JLabel("Name:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(10, 9, 81, 17);
		panel_1.add(label);

		textFieldName = new JTextField();
		textFieldName.addKeyListener(new KeyAdapter() {
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
					String query = "select * from banksystem.employees where Name like '" + textFieldName.getText()
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
		});
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldName.setColumns(10);
		textFieldName.setBounds(74, 9, 209, 23);
		panel_1.add(textFieldName);

		JLabel label_1 = new JLabel("Address:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(293, 9, 81, 17);
		panel_1.add(label_1);

		textFieldAddress = new JTextField();
		textFieldAddress.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				listName.setVisible(false);
				scrollPaneName.setVisible(false);
				textFieldContactNo.setVisible(true);
			}
		});
		textFieldAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(347, 9, 222, 23);
		panel_1.add(textFieldAddress);

		JLabel label_2 = new JLabel("Age:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(579, 12, 81, 17);
		panel_1.add(label_2);

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
		textFieldAge.setBounds(646, 6, 209, 23);
		panel_1.add(textFieldAge);

		JLabel label_3 = new JLabel("Contact No:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(10, 43, 106, 17);
		panel_1.add(label_3);

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
		textFieldContactNo.setBounds(74, 43, 209, 23);
		panel_1.add(textFieldContactNo);

		JLabel label_4 = new JLabel("Note:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(293, 40, 106, 17);
		panel_1.add(label_4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(347, 43, 508, 62);
		panel_1.add(scrollPane);

		textAreaNote = new JTextArea();
		textAreaNote.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == 9) {
					dateChooserJoinDate.requestFocus();
				}
			}
		});
		scrollPane.setViewportView(textAreaNote);

		scrollPaneName = new JScrollPane();
		scrollPaneName.setVisible(false);
		scrollPaneName.setBounds(74, 33, 206, 79);
		panel_1.add(scrollPaneName);

		listName = new JList<String>();
		listName.setVisible(false);
		listName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldName.setText(listName.getSelectedValue());
					String accountnotop = textFieldName.getText();

					listName.setVisible(false);
					scrollPaneName.setVisible(false);
					textFieldContactNo.setVisible(true);
					btnSalaryPayment.setEnabled(true);

					try {
						conn = DBConnection.getConnection();
						String query = "Select * from banksystem.employees where Name=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldName.getText());
						rs = ps.executeQuery();
						while (rs.next()) {

							String Srnomaster = rs.getString("SrNoMaster");
							textField_SrNoMaster.setText(Srnomaster);
							String UpdateAmtDate = rs.getString("Date");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date dd = sdf.parse(UpdateAmtDate);
							dateChooserEmp.setDate(dd);
							String AccountNumber = rs.getString("Name");
							textFieldName.setText(AccountNumber);
							String Name = rs.getString("AccountNO");
							textFieldAccountNo.setText(Name);
							String Address = rs.getString("Address");
							textFieldAddress.setText(Address);
							String Age = rs.getString("Age");
							textFieldAge.setText(Age);
							String ContactNo = rs.getString("ContactNo");
							textFieldContactNo.setText(ContactNo);

							String LoanAgainst = rs.getString("Days");
							textFieldDays.setText(LoanAgainst);

							String Duration = rs.getString("PaidAmount");
							textFieldPaidAmount.setText(Duration);

							String Days = rs.getString("Payment");
							textFieldPayment.setText(Days);

							String Amount = rs.getString("Leaves");
							textFieldLeaves.setText(Amount);

							String Interst = rs.getString("Notes");
							textAreaNote.setText(Interst);

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
		});
		listName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldName.setText(listName.getSelectedValue());
				String accountnotop = textFieldName.getText();

				listName.setVisible(false);
				scrollPaneName.setVisible(false);
				textFieldContactNo.setVisible(true);
				btnSalaryPayment.setEnabled(true);

				try {
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.employees where Name=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldName.getText());
					rs = ps.executeQuery();
					while (rs.next()) {

						String Srnomaster = rs.getString("SrNoMaster");
						textField_SrNoMaster.setText(Srnomaster);
						String UpdateAmtDate = rs.getString("Date");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date dd = sdf.parse(UpdateAmtDate);
						dateChooserEmp.setDate(dd);
						String AccountNumber = rs.getString("Name");
						textFieldName.setText(AccountNumber);
						String Name = rs.getString("AccountNO");
						textFieldAccountNo.setText(Name);
						String Address = rs.getString("Address");
						textFieldAddress.setText(Address);
						String Age = rs.getString("Age");
						textFieldAge.setText(Age);
						String ContactNo = rs.getString("ContactNo");
						textFieldContactNo.setText(ContactNo);

						String LoanAgainst = rs.getString("Days");
						textFieldDays.setText(LoanAgainst);

						String Duration = rs.getString("PaidAmount");
						textFieldPaidAmount.setText(Duration);

						String Days = rs.getString("Payment");
						textFieldPayment.setText(Days);

						String Amount = rs.getString("Leaves");
						textFieldLeaves.setText(Amount);

						String Interst = rs.getString("Notes");
						textAreaNote.setText(Interst);

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
		});
		scrollPaneName.setViewportView(listName);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(0, 196, 1154, 73);
		contentPane.add(panel_2);

		JLabel lblJoingDate = new JLabel("Joing Date:");
		lblJoingDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblJoingDate.setBounds(10, 9, 81, 17);
		panel_2.add(lblJoingDate);

		JLabel lblDays = new JLabel("Days:");
		lblDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDays.setBounds(293, 9, 81, 17);
		panel_2.add(lblDays);

		textFieldDays = new JTextField();
		textFieldDays.setEditable(false);
		textFieldDays.setText("0");
		textFieldDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldDays.setColumns(10);
		textFieldDays.setBounds(347, 9, 106, 23);
		panel_2.add(textFieldDays);

		JLabel lblPaidAmount = new JLabel("Advanced Pay Amount:");
		lblPaidAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPaidAmount.setBounds(792, 12, 133, 17);
		panel_2.add(lblPaidAmount);

		textFieldPaidAmount = new JTextField();
		textFieldPaidAmount.setText("0");
		textFieldPaidAmount.addKeyListener(new KeyAdapter() {
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
				if (textFieldPaidAmount.getText().equals("") || textFieldPaidAmount.getText().equals("0")) {
					textFieldPaidAmount.setText("1");
				}
			}
		});
		textFieldPaidAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldPaidAmount.setColumns(10);
		textFieldPaidAmount.setBounds(935, 9, 209, 23);
		panel_2.add(textFieldPaidAmount);

		JLabel lblLeave = new JLabel("Leave:");
		lblLeave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLeave.setBounds(10, 43, 106, 17);
		panel_2.add(lblLeave);

		textFieldLeaves = new JTextField();
		textFieldLeaves.setText("0");
		textFieldLeaves.addKeyListener(new KeyAdapter() {
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
		textFieldLeaves.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldLeaves.setColumns(10);
		textFieldLeaves.setBounds(74, 43, 209, 23);
		panel_2.add(textFieldLeaves);

		dateChooserJoinDate = new JDateChooser();
		dateChooserJoinDate.getCalendarButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldDays.setText("0");
			}
		});
		dateChooserJoinDate.getCalendarButton().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				textFieldDays.setText("0");
			}
		});
		dateChooserJoinDate.getCalendarButton().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				textFieldDays.setText("0");
			}
		});
		dateChooserJoinDate.addVetoableChangeListener(new VetoableChangeListener() {
			public void vetoableChange(PropertyChangeEvent evt) {

			}
		});
		dateChooserJoinDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldDays.setText("0");

			}
		});
		dateChooserJoinDate.setDateFormatString("yyyy-MM-dd");
		dateChooserJoinDate.setBounds(74, 12, 206, 20);
		dateChooserJoinDate.setDate(date);
		panel_2.add(dateChooserJoinDate);

		JLabel lblPayment = new JLabel("Salary:");
		lblPayment.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPayment.setBounds(530, 12, 81, 17);
		panel_2.add(lblPayment);

		textFieldPayment = new JTextField();
		textFieldPayment.setText("0");
		textFieldPayment.addKeyListener(new KeyAdapter() {
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
		textFieldPayment.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldPayment.setColumns(10);
		textFieldPayment.setBounds(597, 9, 185, 23);
		panel_2.add(textFieldPayment);

		radioSavingAccount = new JRadioButton("Transfer to saving account amount.");
		radioSavingAccount.setSelected(true);
		radioSavingAccount.setBounds(294, 40, 230, 23);
		panel_2.add(radioSavingAccount);

		JLabel lblNewLabel_1 = new JLabel("Saving A/C Interst %:");
		lblNewLabel_1.setBounds(530, 44, 142, 14);
		panel_2.add(lblNewLabel_1);

		textFieldInterst = new JTextField();
		textFieldInterst.setText("0");
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
		});
		textFieldInterst.setBounds(682, 40, 81, 22);
		panel_2.add(textFieldInterst);
		textFieldInterst.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(0, 351, 1154, 270);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 11, 1134, 248);
		panel_3.add(scrollPane_1);

		tableEmp = new JTable();
		tableEmp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					int i = tableEmp.getSelectedRow();
					model1 = (DefaultTableModel) tableEmp.getModel();
					textField_SrNoMaster.setText(model1.getValueAt(i, 1).toString());
					textField_SrNo.setText(model1.getValueAt(i, 2).toString());
					// dateChooserJoinDate.setDateFormatString(model1.getValueAt(i,
					// 3).toString());

					textFieldAccountNo.setText(model1.getValueAt(i, 5).toString());
					textFieldName.setText(model1.getValueAt(i, 6).toString());
					textFieldPayment.setText(model1.getValueAt(i, 7).toString());
					textFieldPaidAmount.setText(model1.getValueAt(i, 8).toString());
					textFieldDays.setText(model1.getValueAt(i, 10).toString());
					textFieldInterst.setText(model1.getValueAt(i, 11).toString());
					textFieldLeaves.setText(model1.getValueAt(i, 12).toString());
					textFieldAddress.setText(model1.getValueAt(i, 13).toString());
					textFieldAge.setText(model1.getValueAt(i, 14).toString());
					textFieldContactNo.setText(model1.getValueAt(i, 15).toString());
					textAreaNote.setText(model1.getValueAt(i, 16).toString());
					emptran();
				} catch (Exception es) {
					System.out.print(es.getMessage());
				}

				try {
					for (int i = 0; i < tableEmp.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableEmp.getValueAt(i, 0).toString());
						if (che) {

							empname = textFieldName.getText();
							conn = DBConnection.getConnection();
							String newname = textFieldName.getText();
							String secounname = null;
							double alltoal = 0;
							double oldamount = 0;
							String upsadata = "select * from banksystem.saving;";
							ps = conn.prepareStatement(upsadata);
							rs = ps.executeQuery();
							while (rs.next()) {
								secounname = rs.getString("Name");

								if (secounname.equals(newname)) {
									radioSavingAccount.setEnabled(false);
									break;
								} else {

									radioSavingAccount.setEnabled(true);

								}

							}

							btnSalaryPayment.setEnabled(true);
						}
					}
				} catch (Exception es) {
					System.out.println(es.getMessage());
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
					che();
				} catch (Exception ew) {

				}
				if(radioSavingAccount.isEnabled()==true)
				{

				try {

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserEmp.getDate();
					String date = (String) sdf.format(dateChooserEmp.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());

					int reply = JOptionPane.showConfirmDialog(null, "Create a new saving account", "Saving account",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						int maxxso44 = 0;
						conn = DBConnection.getConnection();
						String maxsr = "select max(SrnoMaster) from banksystem.saving;";
						ps = conn.prepareStatement(maxsr);
						rs = ps.executeQuery();
						while (rs.next()) {
							maxxso44 = rs.getInt(1) + 1;
						}

						double amt = Double.valueOf(textFieldPayment.getText())
								- Double.valueOf(textFieldPaidAmount.getText());
						double intest = Double.valueOf(textFieldInterst.getText());
						double interstamt = amt * intest / 100;

						String insa = "insert into banksystem.saving (SrnoMaster, Srno, Date,UpdateAmtDate, Name, AcountNumber, Address, Age, ContactNo,OpningAmount,Interist,Note) values(?,?,?,?,?,?,?,?,?,?,?,?);";
						ps2 = conn.prepareStatement(insa);
						ps2.setInt(1, maxxso44);
						ps2.setInt(2, maxxso44);
						ps2.setDate(3, (java.sql.Date) d);
						ps2.setDate(4, (java.sql.Date) d);
						ps2.setString(5, textFieldName.getText());
						ps2.setString(6, "S" + maxxso44);
						ps2.setString(7, textFieldAddress.getText());
						ps2.setDouble(8, Double.valueOf(textFieldAge.getText()));
						ps2.setString(9, textFieldContactNo.getText());
						ps2.setDouble(10, amt);
						ps2.setDouble(11, intest);
						ps2.setString(12, "This is Employee.");
						int is = ps2.executeUpdate();
						if (is > 0) {
							try {
								int issrno77 = 0;
								conn = (Connection) DBConnection.getConnection();
								String maxno = "select max(SrNoMaster) from banksystem.savingtranscation;";
								ps1 = conn.prepareStatement(maxno);
								rs1 = ps1.executeQuery();
								while (rs1.next()) {
									issrno77 = rs1.getInt(1) + 1;

								}

								String insertdata = "insert into banksystem.savingtranscation (SrNoMaster, SrNo, Date,Name, AccountNo, TransactionParticulars,Amount) values(?,?,?,?,?,?,?);";
								ps2 = conn.prepareStatement(insertdata);
								ps2.setInt(1, issrno77);
								ps2.setInt(2, 1);
								SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
								dt1 = dateChooserEmp.getDate();
								String date4 = (String) sdf4.format(dateChooserEmp.getDate());
								java.sql.Date d4 = new java.sql.Date(sdf.parse(date4).getTime());
								ps2.setDate(3, (java.sql.Date) d4);
								ps2.setString(4, textFieldName.getText());
								ps2.setString(5, "S" + issrno77);
								ps2.setString(6, "Employee opening account");
								ps2.setDouble(7, amt);
								ps2.executeUpdate();
								ps2.close();

								JOptionPane.showMessageDialog(null, "Thank you for creating saving account \n" + "Name="
										+ textFieldName.getText() + "\n" + "A/C No.=" + "S" + issrno77);

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
								} catch (Exception ew) {

								}

							}
						}
					}
				} catch (Exception ee) {
					System.out.println(ee.getMessage());
				}
				}

			}

			private void che() {
				boolean s = false;
				boolean s1 = true;
				String inc = null;
				int row = 0;
				int rows = tableEmp.getRowCount() - 1;

				for (int i = 0; i < tableEmp.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableEmp.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableEmp.getSelectedRow();
						int col = tableEmp.getSelectedColumn();
						tableEmp.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableEmp.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableEmp.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableEmp.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableEmp.setValueAt((Object) s, 0, 0);
							tableEmp.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableEmp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableEmp.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Select", "SrNoMaster", "SrNo", "JoinDate", "Paid Amt date", "A/C No", "Name", "Payment",
						"Adv. Paid Amt", "Paid Amt status", "Total Day", "Sa A/C Int%", "Leaves", "Address", "Age",
						"Contact No", "Notes" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableEmp.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableEmp.getColumnModel().getColumn(0).setMinWidth(50);
		tableEmp.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableEmp.getColumnModel().getColumn(1).setMinWidth(0);
		tableEmp.getColumnModel().getColumn(1).setMaxWidth(0);
		tableEmp.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableEmp.getColumnModel().getColumn(3).setMinWidth(80);
		tableEmp.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableEmp.getColumnModel().getColumn(4).setMinWidth(80);
		tableEmp.getColumnModel().getColumn(5).setPreferredWidth(80);
		tableEmp.getColumnModel().getColumn(5).setMinWidth(80);
		tableEmp.getColumnModel().getColumn(6).setPreferredWidth(150);
		tableEmp.getColumnModel().getColumn(6).setMinWidth(150);
		tableEmp.getColumnModel().getColumn(7).setPreferredWidth(80);
		tableEmp.getColumnModel().getColumn(7).setMinWidth(80);
		tableEmp.getColumnModel().getColumn(8).setPreferredWidth(80);
		tableEmp.getColumnModel().getColumn(8).setMinWidth(80);
		tableEmp.getColumnModel().getColumn(13).setPreferredWidth(150);
		tableEmp.getColumnModel().getColumn(13).setMinWidth(150);
		tableEmp.getColumnModel().getColumn(15).setPreferredWidth(80);
		tableEmp.getColumnModel().getColumn(15).setMinWidth(80);
		tableEmp.getColumnModel().getColumn(16).setPreferredWidth(150);
		tableEmp.getColumnModel().getColumn(16).setMinWidth(150);
		dayscount();
		showdata();

		scrollPane_1.setViewportView(tableEmp);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(0, 280, 1154, 60);
		contentPane.add(panel_4);

		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String in = "insert into banksystem.employees values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
					ps = conn.prepareStatement(in);
					ps.setInt(1, Integer.parseInt(textField_SrNoMaster.getText()));
					ps.setInt(2, Integer.parseInt(textField_SrNo.getText()));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserEmp.getDate();
					String date = (String) sdf.format(dateChooserEmp.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(3, (java.sql.Date) d);
					ps.setDate(4, (java.sql.Date) d);
					ps.setString(5, textFieldName.getText());
					ps.setString(6, textFieldAccountNo.getText());
					ps.setString(7, textFieldAddress.getText());
					ps.setDouble(8, Double.valueOf(textFieldAge.getText()));
					ps.setString(9, textFieldContactNo.getText());
					ps.setString(10, textFieldDays.getText());
					ps.setDouble(11, Double.valueOf(textFieldPaidAmount.getText()));
					ps.setDouble(12, Double.valueOf(textFieldPayment.getText()));
					ps.setDouble(13, Double.valueOf(textFieldLeaves.getText()));
					ps.setString(14, textAreaNote.getText());
					ps.setDouble(15, Double.valueOf(textFieldInterst.getText()));

					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are saved.");
						tabledataview();
						totalamount();

						if (radioSavingAccount.isSelected()) {

							int maxxso44 = 0;

							String maxsr = "select max(SrnoMaster) from banksystem.saving;";
							ps1 = conn.prepareStatement(maxsr);
							rs1 = ps1.executeQuery();
							while (rs1.next()) {
								maxxso44 = rs1.getInt(1) + 1;
							}
							rs1.close();
							ps1.close();

							double amt = Double.valueOf(textFieldPayment.getText())
									- Double.valueOf(textFieldPaidAmount.getText());
							double intest = Double.valueOf(textFieldInterst.getText());
							double interstamt = amt * intest / 100;

							String insa = "insert into banksystem.saving (SrnoMaster, Srno, Date,UpdateAmtDate, Name, AcountNumber, Address, Age, ContactNo,OpningAmount,Interist,Note) values(?,?,?,?,?,?,?,?,?,?,?,?);";
							ps2 = conn.prepareStatement(insa);
							ps2.setInt(1, maxxso44);
							ps2.setInt(2, maxxso44);
							ps2.setDate(3, (java.sql.Date) d);
							ps2.setDate(4, (java.sql.Date) d);
							ps2.setString(5, textFieldName.getText());
							ps2.setString(6, "S" + maxxso44);
							ps2.setString(7, textFieldAddress.getText());
							ps2.setDouble(8, Double.valueOf(textFieldAge.getText()));
							ps2.setString(9, textFieldContactNo.getText());
							ps2.setDouble(10, amt);
							ps2.setDouble(11, intest);
							ps2.setString(12, "This is Employee.");
							int is = ps2.executeUpdate();
							if (is > 0) {
								try {
									int issrno77 = 0;
									conn = (Connection) DBConnection.getConnection();
									String maxno = "select max(SrNoMaster) from banksystem.savingtranscation;";
									ps1 = conn.prepareStatement(maxno);
									rs1 = ps1.executeQuery();
									while (rs1.next()) {
										issrno77 = rs1.getInt(1) + 1;

									}
									rs1.close();
									ps1.close();
									String insertdata = "insert into banksystem.savingtranscation (SrNoMaster, SrNo, Date,Name, AccountNo, TransactionParticulars,Amount) values(?,?,?,?,?,?,?);";
									ps2 = conn.prepareStatement(insertdata);
									ps2.setInt(1, issrno77);
									ps2.setInt(2, 1);
									SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
									dt1 = dateChooserEmp.getDate();
									String date4 = (String) sdf4.format(dateChooserEmp.getDate());
									java.sql.Date d4 = new java.sql.Date(sdf.parse(date4).getTime());
									ps2.setDate(3, (java.sql.Date) d4);
									ps2.setString(4, textFieldName.getText());
									ps2.setString(5, "S" + issrno77);
									ps2.setString(6, "Employee opening account");
									ps2.setDouble(7, amt);
									ps2.executeUpdate();
									ps2.close();

									JOptionPane.showMessageDialog(null, "Thank you for creating saving account \n"
											+ "Name=" + textFieldName.getText() + "\n" + "A/C No.=" + "S" + issrno77);

								} catch (Exception ee) {
									System.out.println(ee.getMessage());
								}
							}

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
					conn = DBConnection.getConnection();
					String inserdata = "insert into banksystem.emptrancation (SrNoMaster, SrNo, Date, Name, AccountNo, Salary, AdvancePay, Leaves, TotalAmount, Mode) values(?,?,?,?,?,?,?,?,?,?);";
					ps = conn.prepareStatement(inserdata);
					ps.setInt(1, Integer.valueOf(textField_SrNoMaster.getText()));
					ps.setInt(2, 1);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserEmp.getDate();
					String date = (String) sdf.format(dateChooserEmp.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(3, (java.sql.Date) d);
					ps.setString(4, textFieldName.getText());
					ps.setString(5, textFieldAccountNo.getText());
					ps.setDouble(6, Double.valueOf(textFieldPayment.getText()));
					ps.setDouble(7, Double.valueOf(textFieldPaidAmount.getText()));
					ps.setString(8, textFieldLeaves.getText());
					double amount = Double.valueOf(textFieldPayment.getText())
							- Double.valueOf(textFieldPaidAmount.getText());
					ps.setDouble(9, amount);
					ps.setString(10, "");
					ps.executeUpdate();
					increment();
					reset();

				} catch (Exception es) {
					System.out.println(es.getMessage());
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
				dt1 = dateChooserJoinDate.getDate();
				String data2 = (String) sdf.format(dateChooserJoinDate.getDate());
				String data3 = (String) sdf.format(dateChooserJoinDate.getDate());
				String data4 = textFieldAccountNo.getText();
				String data5 = textFieldName.getText();
				String data6 = textFieldPayment.getText();
				String data7 = textFieldPaidAmount.getText();
				String data8 = null;
				if (data7.equals("") | data7.equals("0")) {
					data8 = "Y";
				} else {
					data8 = "N";
				}

				String data9 = textFieldDays.getText();
				String data10 = textFieldInterst.getText();
				String data11 = textFieldLeaves.getText();
				String data12 = textFieldAddress.getText();
				String data13 = textFieldAge.getText();
				String data14 = textFieldContactNo.getText();
				String data15 = textAreaNote.getText();

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12, data13, data14, data15 };
				model1 = (DefaultTableModel) tableEmp.getModel();
				model1.addRow(row);
			}

		});
		button.setIcon(new ImageIcon(Employee.class.getResource("/ImageButtonIcon/Save.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(125, 11, 115, 38);
		panel_4.add(button);

		JButton button_Update = new JButton("Update");
		button_Update.setVisible(false);
		button_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					conn = DBConnection.getConnection();
					String up = "UPDATE banksystem.employees  set Date=?, Name=?, Address=?, Age=?, ContactNo=?, Days=?, PaidAmount=?, Payment=?, Leaves=?, Notes=? where SrNoMaster=?";
					ps = conn.prepareStatement(up);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserEmp.getDate();
					String date = (String) sdf.format(dateChooserEmp.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(1, (java.sql.Date) d);
					ps.setString(2, textFieldName.getText());
					ps.setString(3, textFieldAddress.getText());
					ps.setDouble(4, Double.valueOf(textFieldAge.getText()));
					ps.setString(5, textFieldContactNo.getText());
					ps.setString(6, textFieldDays.getText());
					ps.setDouble(7, Double.valueOf(textFieldPaidAmount.getText()));
					ps.setDouble(8, Double.valueOf(textFieldPayment.getText()));
					ps.setString(9, textFieldLeaves.getText());
					ps.setString(10, textAreaNote.getText());
					ps.setInt(11, Integer.parseInt(textField_SrNoMaster.getText()));

					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are update.");
						reset();
						dispose();
						Employee emp = new Employee();
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
		button_Update.setIcon(new ImageIcon(Employee.class.getResource("/ImageButtonIcon/update.png")));
		button_Update.setToolTipText("");
		button_Update.setHorizontalAlignment(SwingConstants.LEADING);
		button_Update.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_Update.setBounds(10, 11, 105, 38);
		panel_4.add(button_Update);

		JButton button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String empd = "delete from banksystem.emptrancation where AccountNo='"
							+ textFieldAccountNo.getText() + "'";
					ps = conn.prepareStatement(empd);
					int ii = ps.executeUpdate();
					if (ii > 0) {

						String de = "delete from banksystem.employees where AccountNo='" + textFieldAccountNo.getText()
								+ "'";
						ps1 = conn.prepareStatement(de);
						int i = ps1.executeUpdate();
						if (i > 0) {
							JOptionPane.showMessageDialog(null, "Data are deleted.");
							reset();
							dispose();
							Employee fd = new Employee();
							fd.setUndecorated(true);
							fd.setVisible(true);
						}

					}
				} catch (Exception qe) {
					System.out.print(qe.getMessage());
				} finally {
					try {

						ps.close();
						ps1.close();
						conn.close();
					} catch (Exception ew) {
						System.out.println(ew.getMessage());
					}
				}
			}
		});
		button_2.setIcon(new ImageIcon(Employee.class.getResource("/ImageButtonIcon/delete.jpg")));
		button_2.setHorizontalAlignment(SwingConstants.LEADING);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_2.setBounds(386, 11, 115, 38);
		panel_4.add(button_2);

		JButton button_3 = new JButton("Reset");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		button_3.setIcon(new ImageIcon(Employee.class.getResource("/ImageButtonIcon/Reset5.jpg")));
		button_3.setHorizontalAlignment(SwingConstants.LEADING);
		button_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_3.setBounds(511, 11, 115, 38);
		panel_4.add(button_3);

		JButton button_4 = new JButton("Exit");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Welcome.frame.enable();
			}
		});
		button_4.setIcon(new ImageIcon(Employee.class.getResource("/ImageButtonIcon/Exit.png")));
		button_4.setHorizontalAlignment(SwingConstants.LEADING);
		button_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_4.setBounds(806, 11, 115, 38);
		panel_4.add(button_4);

		btnSalaryPayment = new JButton("Salary Payment");
		btnSalaryPayment.setEnabled(false);
		btnSalaryPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					for (int i = 0; i <= tableEmp.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableEmp.getValueAt(i, 0).toString());
						if (che) {
							acnoemp = textFieldAccountNo.getText();

							emp_adpay = tableEmp.getValueAt(i, 8).toString();
							emp_leaves = tableEmp.getValueAt(i, 12).toString();
							emp_name = tableEmp.getValueAt(i, 6).toString();
							emp_salary = tableEmp.getValueAt(i, 7).toString();
							intert = textFieldInterst.getText();
							srnomaster = tableEmp.getValueAt(i, 1).toString();
							EmpSalaryPopUp empp = new EmpSalaryPopUp();
							empp.setVisible(true);
						}
					}
				} catch (Exception ew) {
					System.out.println(ew.getMessage());
				}
				// try
				// {
				// if(radioSavingAccount.isEnabled()==true)
				// {
				// int reply = JOptionPane.showConfirmDialog(null, "Create new
				// saving account", "Saving Account",
				// JOptionPane.YES_NO_OPTION);
				// if (reply == JOptionPane.YES_OPTION)
				// {
				//
				//
				// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// dt1 = dateChooserEmp.getDate();
				// String date = (String) sdf.format(dateChooserEmp.getDate());
				// java.sql.Date d = new
				// java.sql.Date(sdf.parse(date).getTime());
				//
				// int maxxso44 = 0;
				//
				// conn=DBConnection.getConnection();
				// String maxsr = "select max(SrnoMaster) from
				// banksystem.saving;";
				// ps1 = conn.prepareStatement(maxsr);
				// rs1 = ps1.executeQuery();
				// while (rs1.next()) {
				// maxxso44 = rs1.getInt(1) + 1;
				// }
				//
				// rs1.close();
				// ps1.close();
				//
				//
				// String option = null;
				// if(textFieldInterst.getText().equals(""))
				// {
				// JTextField fileName = new JTextField();
				// Object[] message = {"Enter saving intesrt%:"};
				// option = JOptionPane.showInputDialog(null, message, "Add
				// New", JOptionPane.OK_CANCEL_OPTION);
				// }
				//
				//
				// double intest = Double.valueOf(option);
				//
				//
				// String insa = "insert into banksystem.saving (SrnoMaster,
				// Srno, Date,UpdateAmtDate, Name, AcountNumber, Address, Age,
				// ContactNo,Interist,Note) values(?,?,?,?,?,?,?,?,?,?,?);";
				// ps2 = conn.prepareStatement(insa);
				// ps2.setInt(1, maxxso44);
				// ps2.setInt(2, maxxso44);
				// ps2.setDate(3, (java.sql.Date) d);
				// ps2.setDate(4, (java.sql.Date) d);
				// ps2.setString(5, textFieldName.getText());
				// ps2.setString(6, "S-" + maxxso44);
				// ps2.setString(7, textFieldAddress.getText());
				// ps2.setDouble(8, Double.valueOf(textFieldAge.getText()));
				// ps2.setString(9, textFieldContactNo.getText());
				// ps2.setDouble(10, intest);
				//
				// ps2.setString(11, "This is Employee.");
				// int is = ps2.executeUpdate();
				// if (is > 0) {
				// try {
				// int issrno77 = 0;
				// conn = (Connection) DBConnection.getConnection();
				// String maxno = "select max(SrNoMaster) from
				// banksystem.savingtranscation;";
				// ps1 = conn.prepareStatement(maxno);
				// rs1 = ps1.executeQuery();
				// while (rs1.next()) {
				// issrno77 = rs1.getInt(1) + 1;
				//
				// }
				// JOptionPane.showMessageDialog(null, issrno77);
				// rs1.close();
				// ps1.close();
				// String insertdata = "insert into banksystem.savingtranscation
				// (SrNoMaster, SrNo, Date,Name, AccountNo,
				// TransactionParticulars) values(?,?,?,?,?,?);";
				// ps2 = conn.prepareStatement(insertdata);
				// ps2.setInt(1, issrno77);
				// ps2.setInt(2, 1);
				// SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
				// dt1 = dateChooserEmp.getDate();
				// String date4 = (String)
				// sdf4.format(dateChooserEmp.getDate());
				// java.sql.Date d4 = new
				// java.sql.Date(sdf.parse(date4).getTime());
				// ps2.setDate(3, (java.sql.Date) d4);
				// ps2.setString(4, textFieldName.getText());
				// ps2.setString(5, "S-" + issrno77);
				// ps2.setString(6, "Employee opening account");
				// ps2.executeUpdate();
				// ps2.close();
				//
				// JOptionPane.showMessageDialog(null, "Thank you for creating
				// saving account \n"
				// + "Name=" + textFieldName.getText() + "\n" + "A/C No.=" +
				// "S-" + issrno77);
				//
				// } catch (Exception ee) {
				// System.out.println(ee.getMessage());
				// }
				// }
				// }
				// }
				// }
				// catch(Exception ee)
				// {
				// System.out.println(ee.getMessage());
				// }
			}
		});
		btnSalaryPayment.setIcon(new ImageIcon(Employee.class.getResource("/ImageButtonIcon/salarylogo.jpg")));
		btnSalaryPayment.setHorizontalAlignment(SwingConstants.LEADING);
		btnSalaryPayment.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSalaryPayment.setBounds(636, 11, 160, 38);
		panel_4.add(btnSalaryPayment);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldDays.setEditable(true);
				btnEdit.setVisible(false);
				button_Update.setBounds(250, 11, 126, 38);
				button_Update.setVisible(true);

			}
		});
		btnEdit.setIcon(new ImageIcon(Employee.class.getResource("/ImageButtonIcon/edit.png")));
		btnEdit.setToolTipText("");
		btnEdit.setHorizontalAlignment(SwingConstants.LEADING);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(250, 11, 126, 38);
		panel_4.add(btnEdit);

		JButton button_5 = new JButton("Report");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					for (int i = 0; i <= tableEmp.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableEmp.getValueAt(i, 0).toString());
						if (che) {
							java.io.InputStream file = getClass().getResourceAsStream("/Reports/Employee.jrxml");
							String accno4 = String.valueOf(tableEmp.getValueAt(i, 5).toString());
							HashMap para = new HashMap();
							para.put("Acno", accno4);
							allinonereport r = new allinonereport("Agent Report", file, para);

						}

					}
				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		button_5.setIcon(new ImageIcon(Employee.class.getResource("/ImageButtonIcon/Report4d.png")));
		button_5.setHorizontalAlignment(SwingConstants.LEADING);
		button_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_5.setBounds(10, 655, 150, 38);
		contentPane.add(button_5);

		JButton button_6 = new JButton("View Transaction");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for (int i = 0; i <= tableEmp.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableEmp.getValueAt(i, 0).toString());
						if (che) {
							name = String.valueOf(tableEmp.getValueAt(i, 5).toString());

							TranEmployees2 tcomm = new TranEmployees2();
							tcomm.setUndecorated(true);
							tcomm.setVisible(true);
						}
					}
				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		button_6.setIcon(new ImageIcon(Employee.class.getResource("/ImageButtonIcon/TrancationView.png")));
		button_6.setHorizontalAlignment(SwingConstants.LEADING);
		button_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_6.setBounds(170, 655, 194, 38);
		contentPane.add(button_6);

		JLabel lblTotalPayment = new JLabel("Total Payment");
		lblTotalPayment.setBounds(884, 635, 109, 14);
		contentPane.add(lblTotalPayment);

		textFieldTotalAmtB = new JTextField();
		textFieldTotalAmtB.setForeground(Color.RED);
		textFieldTotalAmtB.setEditable(false);
		textFieldTotalAmtB.setColumns(10);
		textFieldTotalAmtB.setBounds(992, 632, 132, 20);
		contentPane.add(textFieldTotalAmtB);
	}

	public void reset() {
		increment();
		textFieldAddress.setText("");
		textFieldAge.setText("0");
		textFieldContactNo.setText("");
		textFieldDays.setText("");
		textFieldLeaves.setText("0");
		textFieldName.setText("");
		textFieldPaidAmount.setText("0");
		textFieldPayment.setText("0");
		textAreaNote.setText("");
		textFieldInterst.setText("1");
	}

	public void increment() {
		try {

			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("select max(SrNoMaster) from banksystem.employees;");
			rs = ps.executeQuery();
			while (rs.next()) {
				int val = (rs.getInt(1) + 1);
				val1 = String.valueOf(val);
			}
			textField_SrNoMaster.setText(val1);
			textField_SrNo.setText(val1);
			textFieldAccountNo.setText("Emp" + val1);

			SimpleDateFormat insdf = new SimpleDateFormat("yyyy-MM-dd");
			Date indate = new Date();
			String instringdate = insdf.format(indate);
			dateChooserEmp.setDate(insdf.parse(instringdate));
			dateChooserJoinDate.setDate(insdf.parse(instringdate));

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

	public void showdata() {
		try {
			conn = DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.employees order by Srnomaster;";
			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("SrNo");
				String data2 = rs.getString("Date");
				String data3 = rs.getString("UpdateDate");
				String data4 = rs.getString("AccountNO");
				String data5 = rs.getString("Name");
				String data6 = rs.getString("Payment");
				String data7 = rs.getString("PaidAmount");
				String data8 = null;
				if (data7.equals("") || data7.equals("0")) {
					data8 = "N";
				} else {
					data8 = "Y";
				}
				String data9 = rs.getString("Days");
				String data10 = rs.getString("Saacinterst");
				String data11 = rs.getString("Leaves");
				String data12 = rs.getString("Address");
				String data13 = rs.getString("Age");
				String data14 = rs.getString("ContactNo");
				String data15 = rs.getString("Notes");

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12, data13, data14, data15 };
				model1 = (DefaultTableModel) tableEmp.getModel();
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

	public void dayscount() {
		try {

			DateFormat dateFormat = new SimpleDateFormat("dd");
			Date date1 = new Date();

			String current = dateFormat.format(date1);
			conn = DBConnection.getConnection();
			String query = "select * from banksystem.employees;";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				String srno = rs.getString("SrNoMaster");
				String dbdate = rs.getString("Date");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date2 = formatter.parse(dbdate);

				long diff = date1.getTime() - date2.getTime();// in Milli
																// seconds
				int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
				String up = "UPDATE banksystem.employees set Days='" + numOfDays + "' where Srnomaster='" + srno + "'";
				ps = conn.prepareStatement(up);
				int i = ps.executeUpdate();

			}
		} catch (Exception ee) {
			System.err.println(ee.getMessage());
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

	public void emptran() {
		try {

			for (int i = 0; i < tableEmp.getRowCount(); i++) {

				Boolean che = Boolean.valueOf(tableEmp.getValueAt(i, 0).toString());
				if (che) {
					empname = tableEmp.getValueAt(i, 6).toString();
					leaves = tableEmp.getValueAt(i, 12).toString();
					joindate = tableEmp.getValueAt(i, 3).toString();
					acnoemp = tableEmp.getValueAt(i, 5).toString();
					addemp = tableEmp.getValueAt(i, 13).toString();
					ageemp = tableEmp.getValueAt(i, 14).toString();
					contnoemp = tableEmp.getValueAt(i, 15).toString();
					note = tableEmp.getValueAt(i, 16).toString();
					salary55 = tableEmp.getValueAt(i, 7).toString();
					srnomaster = tableEmp.getValueAt(i, 1).toString();

				}

			}
		}

		catch (Exception ee) {
			System.out.println(ee.getMessage());
		}

	}

	private void hiderd() {
		JOptionPane.showMessageDialog(null, "hie");
		btnSalaryPayment.setEnabled(false);

	}

	public void totalamount() {
		int rowsCount = tableEmp.getRowCount();
		double sum = 0;

		for (int i = 0; i < rowsCount; i++) {
			sum = sum + Double.valueOf(tableEmp.getValueAt(i, 8).toString());
		}
		textFieldTotalAmtB.setText(String.valueOf(df.format(sum)));

	}
}
