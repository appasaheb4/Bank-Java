package Pages;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;

import CLASS.CalculatorGUI;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import java.awt.Cursor;

import java.awt.Component;
import java.awt.event.MouseMotionAdapter;

public class Recurring extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textField_Address;
	private JTextField textField_Age;
	private JTextField textField_ContactNo;
	private JTextField textField_Amount;
	private JTextField textField_Interest;
	private JTextField textField_MaturedAmount;
	private JTextField textFDuration;
	private JTextField textField_Days;
	private JTable tableRecurring;
	private JTextField textFieldSrNo;
	private JTextField textField_SrNoMaster;
	public static String acno;
	public JComboBox comboBoxNomeny;
	public static Connection conn;
	public static PreparedStatement ps;
	public PreparedStatement ps1;
	public PreparedStatement ps2;
	public PreparedStatement ps3;
	public String val1;
	public Statement st;
	public DefaultTableModel model1;
	public ResultSet rs;
	public ResultSet rs1;
	public ResultSet rs2;
	static java.util.Date dt1;
	java.util.Date dt2;
	private JTextField textField_InterestAmt;
	public JList<String> listCostName;
	public String[] data = new String[1000];
	public String[] data1 = new String[1000];
	public JPanel panel_ConsName;
	public JScrollPane scrollPane_CoustName;
	public JPanel panel_info2;
	public JPanel panel_Button;
	public JScrollPane scrollPanenote;
	public JButton buttonSave;
	public JButton btnDailyCollection;
	public JDateChooser dateChooserRecurring;
	public JTextArea textAreaNotes;
	public static int inc = 0;
	public static String SrNoMaster;
	public JComboBox comboBoxDouration;
	public double premamt = 0;
	
	public static double total = 0;
	private JTextField textFieldNameAgaint;
	private JTextField textFieldAccountNo;
	public JPanel panel_NameofAgest;
	private JScrollPane scrollPaneNameofAgest44;
	private JList listNameofAgest44;
	public JPanel panel_Info;
	private JTextField textFieldNomeny;
	private JTextField textFieldNomenyOther;
	private JLabel lblOtherAmount;
	private JTextField textFieldOtherAmount;

	public static String cusname;
	public static String agname;
	public DecimalFormat dff = new DecimalFormat("#.##");
	private JButton button;
	public static String accno;
	private JButton button_5;
	private JLabel lblTotalAmount;
	private JTextField textFieldTotalAmtB;
	private JButton btnEdit;
	private JLabel label_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Recurring frame = new Recurring();
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
	public Recurring() {
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
				textFieldNameAgaint.requestFocus();
				totalamount();

				String name = comboBoxDouration.getSelectedItem().toString();
				textFDuration.setText(name);
				daycount(name);

			}

		});

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1154, 86);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblRecurring = new JLabel("Recurring(Daily Collection)");
		lblRecurring.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblRecurring.setBounds(10, 11, 378, 64);
		panel.add(lblRecurring);

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
		textFieldSrNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldSrNo.setColumns(10);
		textFieldSrNo.setBounds(160, 24, 187, 23);
		panel.add(textFieldSrNo);

		textField_SrNoMaster = new JTextField();
		textField_SrNoMaster.setVisible(false);
		textField_SrNoMaster.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_SrNoMaster.setColumns(10);
		textField_SrNoMaster.setBounds(398, 25, 104, 23);
		increment();
		panel.add(textField_SrNoMaster);

		Date date = new Date();
		dateChooserRecurring = new JDateChooser();
		dateChooserRecurring.setDateFormatString("yyyy-MM-dd");
		dateChooserRecurring.setBounds(870, 44, 196, 20);
		dateChooserRecurring.setDate(date);
		panel.add(dateChooserRecurring);

		JLabel label_5 = new JLabel("Date:");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_5.setBounds(779, 47, 81, 17);
		panel.add(label_5);

		JLabel lblAccountNumber = new JLabel("Account Number:");
		lblAccountNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAccountNumber.setBounds(10, 62, 94, 17);
		panel.add(lblAccountNumber);

		textFieldAccountNo = new JTextField();
		textFieldAccountNo.setEditable(false);
		textFieldAccountNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldAccountNo.setColumns(10);
		textFieldAccountNo.setBounds(114, 59, 123, 23);
		increment();
		// DurationIncrement();
		panel.add(textFieldAccountNo);

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
		button_5.setIcon(new ImageIcon(Recurring.class.getResource("/ImageButtonIcon/clac.png")));
		button_5.setOpaque(false);
		button_5.setForeground(Color.RED);
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button_5.setContentAreaFilled(false);
		button_5.setBorderPainted(false);
		button_5.setBounds(1079, 13, 64, 55);
		panel.add(button_5);

		panel_Info = new JPanel();
		panel_Info.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Info.setBounds(0, 86, 1154, 138);
		contentPane.add(panel_Info);
		panel_Info.setLayout(null);

		JLabel lblCostomerName = new JLabel("Customer Name");
		lblCostomerName.setBounds(300, 14, 85, 17);
		lblCostomerName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_Info.add(lblCostomerName);

		textFieldName = new JTextField();
		textFieldName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {

			}

			@Override
			public void focusGained(FocusEvent e) {
				panel_NameofAgest.setVisible(false);
				textField_ContactNo.setVisible(true);
			}
		});
		textFieldName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {

					conn = DBConnection.getConnection();
					String query = "select * from banksystem.recurring where CustomerName like '"
							+ textFieldName.getText() + "%'";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					int i = 0;
					while (rs.next()) {

						String name = rs.getString("CustomerName");
						data1[i] = name;
						i++;

					}
					listCostName.setListData(data1);
					listCostName.setVisibleRowCount(100);
					panel_info2.setVisible(false);
					panel_Button.setVisible(false);
					scrollPanenote.setVisible(false);
					panel_ConsName.setVisible(true);

					scrollPane_CoustName.setVisible(true);

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
					listCostName.requestFocus();
				}
			}
		});
		textFieldName.setBounds(389, 11, 213, 23);
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldName.setColumns(10);
		panel_Info.add(textFieldName);

		JLabel label_1 = new JLabel("Address:");
		label_1.setBounds(612, 14, 81, 17);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_Info.add(label_1);

		textField_Address = new JTextField();
		textField_Address.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				panel_ConsName.setVisible(false);
				panel_info2.setVisible(true);
				panel_Button.setVisible(true);
				scrollPanenote.setVisible(true);
				panel_NameofAgest.setVisible(false);
				textField_ContactNo.setVisible(true);
			}
		});
		textField_Address.setBounds(670, 11, 209, 23);
		textField_Address.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_Address.setColumns(10);
		panel_Info.add(textField_Address);

		JLabel label_2 = new JLabel("Age:");
		label_2.setBounds(889, 14, 36, 17);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_Info.add(label_2);

		textField_Age = new JTextField();
		textField_Age.setText("0");
		textField_Age.addKeyListener(new KeyAdapter() {
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
		textField_Age.setBounds(918, 11, 92, 23);
		textField_Age.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_Age.setColumns(10);
		panel_Info.add(textField_Age);

		JLabel label_3 = new JLabel("Contact No:");
		label_3.setBounds(10, 40, 59, 17);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_Info.add(label_3);

		textField_ContactNo = new JTextField();
		textField_ContactNo.addKeyListener(new KeyAdapter() {
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
		textField_ContactNo.setBounds(87, 37, 201, 23);
		textField_ContactNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_ContactNo.setColumns(10);
		panel_Info.add(textField_ContactNo);

		JLabel label_4 = new JLabel("Note:");
		label_4.setBounds(300, 37, 59, 17);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_Info.add(label_4);

		scrollPanenote = new JScrollPane();
		scrollPanenote.setBounds(388, 37, 491, 51);
		scrollPanenote.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanenote.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_Info.add(scrollPanenote);

		textAreaNotes = new JTextArea();
		textAreaNotes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == 9) {
					textFieldNomeny.requestFocus();
				}

			}
		});
		scrollPanenote.setViewportView(textAreaNotes);

		panel_ConsName = new JPanel();
		panel_ConsName.setVisible(false);
		panel_ConsName.setBounds(389, 37, 216, 90);
		panel_Info.add(panel_ConsName);
		panel_ConsName.setLayout(null);

		listCostName = new JList<String>();
		listCostName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					try {

						panel_ConsName.setVisible(false);
						textFieldName.setText(listCostName.getSelectedValue().toString());
						conn = DBConnection.getConnection();
						String query = "Select * from banksystem.recurring where CustomerName=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldName.getText());
						rs = ps.executeQuery();
						rs.last();
						String srno = rs.getString("SrNoMaster");
						textField_SrNoMaster.setText(srno);
						String sr = rs.getString("SrNo");
						textFieldSrNo.setText(sr);
						// String date1 = rs.getString("Date");
						// DateFormat format = new
						// SimpleDateFormat("yyyy-MM-dd");
						// Date date = format.parse(date1);
						// dateChooserRecurring.setDate(date);
						String name = rs.getString("CustomerName");
						textFieldName.setText(name);
						String address = rs.getString("Address");
						textField_Address.setText(address);
						String age = rs.getString("Age");
						textField_Age.setText(age);
						String contno = rs.getString("ContactNo");
						textField_ContactNo.setText(contno);

						String acnot = rs.getString("AccountNo");
						textFieldAccountNo.setText(acnot);

						String interse = rs.getString("Intrest");
						textField_Interest.setText(interse);

						String duration = rs.getString("Duration");
						textFDuration.setText(duration);
						String days = rs.getString("Days");
						textField_Days.setText(days);
						String note = rs.getString("Notes");
						textAreaNotes.setText(note);

						scrollPane_CoustName.setVisible(false);
						panel_info2.setVisible(true);
						panel_Button.setVisible(true);
						scrollPanenote.setVisible(true);
						buttonSave.setVisible(false);
						btnDailyCollection.setVisible(true);
						comboBoxDouration.setEnabled(false);
						textFDuration.setEnabled(false);
						datashow();

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
						try
						{
							
							Component[] com = panel_Info.getComponents();
							Component[] com1 = panel_info2.getComponents();
							textAreaNotes.setEnabled(false);
							for (int a = 0; a < com.length; a++) {
								com[a].setEnabled(false);

							}
							for (int a = 0; a < com1.length; a++) {
								com1[a].setEnabled(false);
							}
							textField_Amount.setEnabled(true);
							
							
							
							
						}
						catch(Exception ew)
						{
						}
					}

				}

			}
		});
		listCostName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				try {

					panel_ConsName.setVisible(false);
					textFieldName.setText(listCostName.getSelectedValue().toString());
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.recurring where CustomerName=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldName.getText());
					rs = ps.executeQuery();
					rs.last();
					String srno = rs.getString("SrNoMaster");
					textField_SrNoMaster.setText(srno);
					String sr = rs.getString("SrNo");
					textFieldSrNo.setText(sr);
					// String date1 = rs.getString("Date");
					// DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					// Date date = format.parse(date1);
					// dateChooserRecurring.setDate(date);
					String name = rs.getString("CustomerName");
					textFieldName.setText(name);
					String address = rs.getString("Address");
					textField_Address.setText(address);
					String age = rs.getString("Age");
					textField_Age.setText(age);
					String contno = rs.getString("ContactNo");
					textField_ContactNo.setText(contno);

					String acnot = rs.getString("AccountNo");
					textFieldAccountNo.setText(acnot);

					String interse = rs.getString("Intrest");
					textField_Interest.setText(interse);

					String duration = rs.getString("Duration");
					textFDuration.setText(duration);
					String days = rs.getString("Days");
					textField_Days.setText(days);
					String note = rs.getString("Notes");
					textAreaNotes.setText(note);

					scrollPane_CoustName.setVisible(false);
					panel_info2.setVisible(true);
					panel_Button.setVisible(true);
					scrollPanenote.setVisible(true);
					buttonSave.setVisible(false);
					btnDailyCollection.setVisible(true);
					comboBoxDouration.setEnabled(false);
					textFDuration.setEnabled(false);
					datashow();

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
					try
					{
						
						Component[] com = panel_Info.getComponents();
						Component[] com1 = panel_info2.getComponents();
						textAreaNotes.setEnabled(false);
						for (int a = 0; a < com.length; a++) {
							com[a].setEnabled(false);

						}
						for (int a = 0; a < com1.length; a++) {
							com1[a].setEnabled(false);
						}
						textField_Amount.setEnabled(true);
						
						
						
						
					}
					catch(Exception ew)
					{
					}
				}

			}

		});

		scrollPane_CoustName = new JScrollPane();
		scrollPane_CoustName.setBounds(0, 0, 216, 90);
		panel_ConsName.add(scrollPane_CoustName);
		scrollPane_CoustName.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_CoustName.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_CoustName.setVisible(false);
		scrollPane_CoustName.setViewportView(listCostName);

		panel_NameofAgest = new JPanel();
		panel_NameofAgest.setBounds(87, 31, 214, 96);
		panel_Info.add(panel_NameofAgest);
		panel_NameofAgest.setVisible(false);
		panel_NameofAgest.setLayout(null);

		listNameofAgest44 = new JList<String>();
		listNameofAgest44.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldNameAgaint.setText(listNameofAgest44.getSelectedValue().toString());
					panel_NameofAgest.setVisible(false);
					textField_ContactNo.setVisible(true);
					
				}

			}
		});
		listNameofAgest44.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					textFieldNameAgaint.setText(listNameofAgest44.getSelectedValue().toString());
					panel_NameofAgest.setVisible(false);
					textField_ContactNo.setVisible(true);
				} catch (Exception es) {
					System.out.println(es.getMessage());
					
				}

			}
		});

		scrollPaneNameofAgest44 = new JScrollPane();
		scrollPaneNameofAgest44.setBounds(0, 0, 214, 96);
		scrollPaneNameofAgest44.setViewportView(listNameofAgest44);
		panel_NameofAgest.add(scrollPaneNameofAgest44);

		textFieldNameAgaint = new JTextField();
		textFieldNameAgaint.setBounds(87, 11, 201, 23);
		panel_Info.add(textFieldNameAgaint);
		textFieldNameAgaint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {

					conn = DBConnection.getConnection();
					String query = "select * from banksystem.commissiona_c where Name like '"
							+ textFieldNameAgaint.getText() + "%'";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					int i = 0;
					while (rs.next()) {

						String name = rs.getString("Name");
						data[i] = name;
						i++;

					}
					listNameofAgest44.setListData(data);
					listNameofAgest44.setVisibleRowCount(100);
					textField_ContactNo.setVisible(false);
					panel_NameofAgest.setVisible(true);
					scrollPaneNameofAgest44.setVisible(true);
					listNameofAgest44.setVisible(true);

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
					listNameofAgest44.requestFocus();
				}
			}
		});
		textFieldNameAgaint.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldNameAgaint.setColumns(10);

		JLabel label = new JLabel("Name of Agent:");
		label.setBounds(10, 17, 85, 17);
		panel_Info.add(label);
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel label_info = new JLabel("Nomeny:");
		label_info.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_info.setBounds(10, 107, 106, 17);
		panel_Info.add(label_info);

		textFieldNomeny = new JTextField();
		textFieldNomeny.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldNomeny.setColumns(10);
		textFieldNomeny.setBounds(87, 107, 201, 23);
		panel_Info.add(textFieldNomeny);

		JLabel label_7 = new JLabel("Relation:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(326, 110, 74, 17);
		panel_Info.add(label_7);

		comboBoxNomeny = new JComboBox();
		comboBoxNomeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = comboBoxNomeny.getSelectedItem().toString();
				textFieldNomenyOther.setText("");
				textFieldNomenyOther.setText(name);
			}
		});
		comboBoxNomeny.setModel(new DefaultComboBoxModel(new String[] {"   Select", "    Father", "    Mother", "   Paternal Grandfather", "    Maternal Grandfather ", "    Maternal Grandmother", "    Brother", "    Brother's Wife", "    Elder Brother", "    Younger Brother", "    Husband's sister", "    Sister", "    Sister's husband", "    Elder Sister", "    Younger Sister)", "    Husband's elder brother", "    Husband's younger brother", "    Elder brother's wife", "    Younger brother's wife", "    Wife's Sister", "    Wife's Brother", "    Wife's Brother's wife", "    Husband's Sister's Husband", "    Wife's sister's husband", "    Husband's elder brother's wife", "    Husband's younger brother's wife", "    Son", "    Son's wife (daughter-in-law) ", "    Daughter ", "    Daughter's husband (son-in-law) ", "    Grandson (son's son)", "    Granddaughter (son's daughter)", "    Grandson (daughter's son)", "    Granddaughter (daughter's daughter)", "    Husband", "    Wife", "    Husband's Mother (Mother-in-law)", "    Husband's Father (Father-in-law)", "    Fianc\u00E9 or Fianc\u00E9e", "    Father's younger brother (uncle)", "    Father's younger brother's wife (aunt)", "    Father's elder brother's (Uncle)", "    Father's elder brother's wife (Aunt) ", "    Father's sister (aunt)", "    Father's sister's husband", "    Mother's brother", "    Mother's brother's wife", "    Mother's sister", "    Mother's sister's husband", "    Brother's son (nephew)", "    Sister's son (nephew)", "    Brother's daughter (niece)", "    Sister's daughter (niece) ", "    Husband's elder brother's daughter", "    Husband's elder brother's son"}));
		comboBoxNomeny.setBounds(398, 107, 179, 23);
		panel_Info.add(comboBoxNomeny);

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
		checkBoxNomeny.setBounds(583, 107, 27, 23);
		panel_Info.add(checkBoxNomeny);

		JLabel label_8 = new JLabel("Other:");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_8.setBounds(618, 110, 81, 17);
		panel_Info.add(label_8);

		textFieldNomenyOther = new JTextField();
		textFieldNomenyOther.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textFieldNomenyOther.setText("");
			}
		});
		textFieldNomenyOther.setText("Other");
		textFieldNomenyOther.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldNomenyOther.setEnabled(false);
		textFieldNomenyOther.setColumns(10);
		textFieldNomenyOther.setBounds(681, 107, 181, 23);
		panel_Info.add(textFieldNomenyOther);
		panel_Info.setFocusTraversalPolicy(
				new CLASS.FocusTraversalOnArray(new Component[] { textFieldNameAgaint, lblCostomerName, textFieldName,
						label_1, textField_Address, label_2, textField_Age, label_3, textField_ContactNo,
						panel_ConsName, label_4, scrollPanenote, textAreaNotes, scrollPane_CoustName, listCostName,
						panel_NameofAgest, scrollPaneNameofAgest44, listNameofAgest44, label, label_info, textFieldNomeny,
						label_7, comboBoxNomeny, checkBoxNomeny, label_8, textFieldNomenyOther }));

		panel_info2 = new JPanel();
		panel_info2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_info2.setBounds(0, 235, 1154, 53);
		contentPane.add(panel_info2);
		panel_info2.setLayout(null);

		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(269, 11, 73, 17);
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_info2.add(lblAmount);

		textField_Amount = new JTextField();
		textField_Amount.setText("0");
		textField_Amount.setBounds(319, 8, 199, 23);
		textField_Amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
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
		textField_Amount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_Amount.setColumns(10);
		panel_info2.add(textField_Amount);

		textField_Interest = new JTextField();
		textField_Interest.setForeground(Color.RED);
		textField_Interest.setBounds(581, 8, 85, 23);
		textField_Interest.addKeyListener(new KeyAdapter() {
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
		textField_Interest.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_Interest.setColumns(10);
		panel_info2.add(textField_Interest);

		JLabel lblMatiared = new JLabel("Matured Amount:");
		lblMatiared.setBounds(759, 11, 97, 17);
		lblMatiared.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_info2.add(lblMatiared);

		textField_MaturedAmount = new JTextField();
		textField_MaturedAmount.setText("1");
		textField_MaturedAmount.setForeground(Color.RED);
		textField_MaturedAmount.setEditable(false);
		textField_MaturedAmount.setBounds(852, 6, 97, 23);
		textField_MaturedAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_MaturedAmount.setColumns(10);
		panel_info2.add(textField_MaturedAmount);

		JLabel lblInterst = new JLabel("Interst %:");
		lblInterst.setBounds(528, 11, 54, 17);
		lblInterst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_info2.add(lblInterst);

		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setBounds(10, 11, 54, 17);
		lblDuration.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_info2.add(lblDuration);

		comboBoxDouration = new JComboBox();
		comboBoxDouration.setBounds(58, 8, 73, 23);
		comboBoxDouration.setModel(new DefaultComboBoxModel(new String[] { "Select", "1 Month", "2 Month", "3 Month\t",
				"6 Month\t", "9 Month", "1 Year", "2 Year\t", "3 Year", "4 Year", "5 Year" }));
		comboBoxDouration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
				String st = comboBoxDouration.getSelectedItem().toString();
				daycount(st);
				}
				catch(Exception ww)
				{
					ww.printStackTrace();
				}
			}
		});
		panel_info2.add(comboBoxDouration);

		JCheckBox checkBoxDuration = new JCheckBox("");
		checkBoxDuration.setBounds(137, 11, 21, 23);
		checkBoxDuration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click1();
			}

			private void click1() {
				if (!checkBoxDuration.isSelected()) {
					comboBoxDouration.setEnabled(true);
					textFDuration.setEnabled(false);
					textFDuration.setText("");

				} else {
					comboBoxDouration.setEnabled(false);
					textFDuration.setEnabled(true);
					String em = "";
					comboBoxDouration.setSelectedIndex(0);
				}

			}
		});
		panel_info2.add(checkBoxDuration);

		textFDuration = new JTextField();
		textFDuration.setForeground(Color.BLACK);
		textFDuration.setEditable(false);
		textFDuration.setBounds(164, 8, 95, 23);
		textFDuration.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				
			}
		});
		textFDuration.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String st = textFDuration.getText();
				daycount(st);
			}
		});
		textFDuration.setText("other e.g. 3 Month..");
		textFDuration.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textFDuration.setColumns(10);
		panel_info2.add(textFDuration);

		textField_InterestAmt = new JTextField();
		textField_InterestAmt.setText("1");
		textField_InterestAmt.setForeground(Color.RED);
		textField_InterestAmt.setEditable(false);
		textField_InterestAmt.setBounds(676, 8, 73, 23);
		textField_InterestAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_InterestAmt.setColumns(10);
		panel_info2.add(textField_InterestAmt);

		lblOtherAmount = new JLabel("Other Charge:");
		lblOtherAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOtherAmount.setBounds(959, 11, 97, 17);
		panel_info2.add(lblOtherAmount);

		textFieldOtherAmount = new JTextField();
		textFieldOtherAmount.setText("0");
		textFieldOtherAmount.setForeground(Color.BLACK);
		textFieldOtherAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldOtherAmount.setColumns(10);
		textFieldOtherAmount.setBounds(1051, 6, 93, 23);
		panel_info2.add(textFieldOtherAmount);
		
		label_6 = new JLabel("e.g. 1 Month, 1M ,1 Year,1 Y ... etc");
		label_6.setForeground(Color.RED);
		label_6.setBounds(115, 35, 194, 14);
		panel_info2.add(label_6);

		panel_Button = new JPanel();
		panel_Button.setLayout(null);
		panel_Button.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Button.setBounds(0, 299, 1154, 60);
		contentPane.add(panel_Button);

		buttonSave = new JButton("Save");
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String in = "insert into banksystem.recurring values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
					ps = conn.prepareStatement(in);
					ps.setInt(1, Integer.parseInt(textField_SrNoMaster.getText()));
					ps.setInt(2, Integer.parseInt(textFieldSrNo.getText()));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserRecurring.getDate();
					String date = (String) sdf.format(dateChooserRecurring.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(3, (java.sql.Date) d);
					ps.setDate(4, (java.sql.Date) d);
					ps.setString(5, textFieldName.getText());
					ps.setString(6, textFieldAccountNo.getText());
					ps.setString(7, textField_Address.getText());
					ps.setDouble(8, Double.valueOf(textField_Age.getText()));
					ps.setString(9, textField_ContactNo.getText());
					ps.setDouble(10, Double.valueOf(textField_Amount.getText()));
					ps.setDouble(11, Double.valueOf(textField_Interest.getText()));
					ps.setDouble(12, Double.valueOf(textField_InterestAmt.getText()));
					ps.setDouble(13, Double.valueOf(textField_MaturedAmount.getText()));
					ps.setString(14, textFDuration.getText());
					ps.setString(15, textField_Days.getText());
					ps.setString(16, textFieldNameAgaint.getText());
					ps.setString(17, textAreaNotes.getText());
					ps.setString(18, textFieldNomeny.getText());
					ps.setString(19, textFieldNomenyOther.getText());
					ps.setDouble(20, Double.valueOf(textFieldOtherAmount.getText()));
					int i = ps.executeUpdate();

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
					conn = DBConnection.getConnection();
					String in1 = "insert into banksystem.dailyrecurring(SrNOMaster,SrNo,Date, CustomerName,AccountNo,AgentName,Amount, Interst, InterestAmt, MaturedAmt, Duration, Days) values(?,1,?,?,?,?,?,?,?,?,?,?);";
					ps = conn.prepareStatement(in1);
					ps.setInt(1, Integer.parseInt(textField_SrNoMaster.getText()));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserRecurring.getDate();
					String date = (String) sdf.format(dateChooserRecurring.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(2, (java.sql.Date) d);
					ps.setString(3, textFieldName.getText());
					ps.setString(4, textFieldAccountNo.getText());
					ps.setString(5, textFieldNameAgaint.getText());
					ps.setDouble(6, Double.valueOf(textField_Amount.getText()));
					ps.setDouble(7, Double.valueOf(textField_Interest.getText()));
					ps.setDouble(8, Double.valueOf(textField_InterestAmt.getText()));
					ps.setDouble(9, Double.valueOf(textField_MaturedAmount.getText()));
					ps.setString(10, textFDuration.getText());
					ps.setString(11, textField_Days.getText());

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
						dt1 = dateChooserRecurring.getDate();
						String date = (String) sdf.format(dateChooserRecurring.getDate());
						java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
						ps1.setDate(2, (java.sql.Date) d);
						ps1.setString(3, textFieldName.getText());
						ps1.setString(4, textFieldAccountNo.getText());
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

				String data0 = textField_SrNoMaster.getText();
				String data1 = textFieldSrNo.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				dt1 = dateChooserRecurring.getDate();
				String data2 = (String) sdf.format(dateChooserRecurring.getDate());
				String data3 = (String) sdf.format(dateChooserRecurring.getDate());
				String data4 = textFieldAccountNo.getText();
				String data5 = textFieldName.getText();
				String data6 = textField_Interest.getText();
				String data7 = textField_Amount.getText();
				String data8 = textFDuration.getText();
				String data9 = textField_Days.getText();
				String data10 = textField_InterestAmt.getText();
				String data11 = textField_MaturedAmount.getText();
				String data12 = textFieldNameAgaint.getText();
				String data13 = textField_Address.getText();
				String data14 = textField_Age.getText();
				String data15 = textField_ContactNo.getText();
				String data16 = textAreaNotes.getText();
				String data17 = textFieldNomeny.getText();
				String data18 = textFieldNomenyOther.getText();

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12, data13, data14, data15, data16, data17, data18 };
				model1 = (DefaultTableModel) tableRecurring.getModel();
				model1.addRow(row);

			}

		});
		buttonSave.setIcon(new ImageIcon(Recurring.class.getResource("/ImageButtonIcon/Save.png")));
		buttonSave.setHorizontalAlignment(SwingConstants.LEADING);
		buttonSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonSave.setBounds(216, 11, 115, 38);
		panel_Button.add(buttonSave);

		JButton button_Update = new JButton("Update");
		button_Update.setVisible(false);
		button_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					conn = DBConnection.getConnection();
					String up = "UPDATE banksystem.recurring set Date=?, CustomerName=?,AccountNo=?,Address=?, Age=?, ContactNo=?, Intrest=?, IntrestAmt=?, MaturedAmount=?, Duration=?, Days=?,Nameofagent=?, Notes=?,NomenyName=?,NomenyRelation=?,OtherAmount=? where Srnomaster="
							+ textField_SrNoMaster.getText() + "";
					ps = conn.prepareStatement(up);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserRecurring.getDate();
					String date = (String) sdf.format(dateChooserRecurring.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(1, (java.sql.Date) d);
					ps.setString(2, textFieldName.getText());
					ps.setString(3, textFieldAccountNo.getText());
					ps.setString(4, textField_Address.getText());
					ps.setDouble(5, Double.valueOf(textField_Age.getText()));
					ps.setString(6, textField_ContactNo.getText());
					
					ps.setDouble(7, Double.valueOf(textField_Interest.getText()));
					ps.setDouble(8, Double.valueOf(textField_InterestAmt.getText()));
					ps.setDouble(9, Double.valueOf(textField_MaturedAmount.getText()));
					ps.setString(10, textFDuration.getText());
					ps.setString(11, textField_Days.getText());
					ps.setString(12, textFieldNameAgaint.getText());
					ps.setString(13, textAreaNotes.getText());
					ps.setString(14, textFieldNomeny.getText());
					ps.setString(15, textFieldNomenyOther.getText());
					ps.setDouble(16, Double.valueOf(textFieldOtherAmount.getText()));

					int is = ps.executeUpdate();
					if (is > 0) {
						JOptionPane.showMessageDialog(null, "Data are update.");
						dispose();
						Recurring re=new Recurring();
						re.setUndecorated(true);
						re.setVisible(true);
 

					}

				} catch (Exception e2) {
					System.out.print(e2.getMessage());
				}
				finally {
					try {

						ps.close();
						ps1.close();
						conn.close();
					} catch (Exception ewww) {
						System.out.println(ewww.getMessage());
					}
				}

			}
		});
		button_Update.setIcon(new ImageIcon(Recurring.class.getResource("/ImageButtonIcon/update.png")));
		button_Update.setToolTipText("");
		button_Update.setHorizontalAlignment(SwingConstants.LEADING);
		button_Update.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_Update.setBounds(977, 11, 126, 38);
		panel_Button.add(button_Update);

		JButton button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					conn = DBConnection.getConnection();
					String de = "delete from banksystem.dailyrecurring where AccountNo=?";
					ps = conn.prepareStatement(de);
					ps.setString(1, textFieldAccountNo.getText());
					int i = ps.executeUpdate();
					if (i > 0) {
						try {
							conn = DBConnection.getConnection();
							String dei = "delete from banksystem.recurring where AccountNo=?";
							ps1 = conn.prepareStatement(dei);
							ps1.setString(1, textFieldAccountNo.getText());
							int i1 = ps1.executeUpdate();
							if (i1 > 0) {
								JOptionPane.showMessageDialog(null, "Data are deleted.");
								dispose();
								Recurring lo = new Recurring();
								lo.setUndecorated(true);
								lo.setVisible(true);
							}

						} catch (Exception qe) {
							System.out.print(qe.getMessage());
						} finally {
							try {

								ps.close();
								ps1.close();
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
		button_2.setIcon(new ImageIcon(Recurring.class.getResource("/ImageButtonIcon/delete.jpg")));
		button_2.setHorizontalAlignment(SwingConstants.LEADING);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_2.setBounds(474, 11, 115, 38);
		panel_Button.add(button_2);

		JButton button_3 = new JButton("Reset");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		button_3.setIcon(new ImageIcon(Recurring.class.getResource("/ImageButtonIcon/Reset5.jpg")));
		button_3.setHorizontalAlignment(SwingConstants.LEADING);
		button_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_3.setBounds(599, 11, 115, 38);
		panel_Button.add(button_3);

		JButton button_4 = new JButton("Exit");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Welcome.frame.enable();
			}
		});
		button_4.setIcon(new ImageIcon(Recurring.class.getResource("/ImageButtonIcon/Exit.png")));
		button_4.setHorizontalAlignment(SwingConstants.LEADING);
		button_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_4.setBounds(724, 11, 115, 38);
		panel_Button.add(button_4);

		textField_Days = new JTextField();
		textField_Days.setVisible(false);
		textField_Days.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textField_Days.setEnabled(false);
		textField_Days.setColumns(10);
		textField_Days.setBounds(1038, 11, 95, 23);
		panel_Button.add(textField_Days);

		btnDailyCollection = new JButton("Add Collection");
		btnDailyCollection.setIcon(new ImageIcon(Recurring.class.getResource("/ImageButtonIcon/add.png")));
		btnDailyCollection.setVisible(false);
		btnDailyCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double interstzerno = Double.valueOf(textField_Interest.getText());
				if (interstzerno <= 0) {
					JOptionPane.showMessageDialog(null,
							"account is suspend mode because balance total withdrawal so your not collection amount.");
				} else {
					

					
					
					
					

					try {
						conn=DBConnection.getConnection();
						String interstcount="select Intrest from banksystem.recurring where AccountNo='"+textFieldAccountNo.getText()+"'";
						ps=conn.prepareStatement(interstcount);
						rs=ps.executeQuery();
						rs.first();
						double interstmain=rs.getDouble("Intrest");
						rs.close();
						ps.close();
						conn.close();
						
						conn = (Connection) DBConnection.getConnection();
						String quey = "select sum(Amount),Interst from banksystem.dailyrecurring GROUP BY SrNOMaster="
								+ textField_SrNoMaster.getText() + ";";
						ps = conn.prepareStatement(quey);
						rs = ps.executeQuery();
						while (rs.next()) {
							premamt = (rs.getDouble(1));
							

						}
						double amounttotalsum = premamt + Double.valueOf(textField_Amount.getText());
						double totalinters = amounttotalsum * interstmain / 100.0;
						total = amounttotalsum + totalinters;

					} catch (Exception ee) {
						System.err.println(ee.getMessage());
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
						String quey = "select SrNOMaster,max(SrNo) from banksystem.dailyrecurring GROUP BY SrNOMaster="
								+ textField_SrNoMaster.getText() + ";";
						ps = conn.prepareStatement(quey);
						rs = ps.executeQuery();
						while (rs.next()) {
							inc = rs.getInt(2) + 1;

						}

					} catch (Exception ee) {
						System.err.println(ee.getMessage());
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
						String in1 = "insert into banksystem.dailyrecurring(SrNOMaster,SrNo,Date,CustomerName,AccountNo,AgentName,MaturedAmt,Amount,Interst,InterestAmt,Duration,Days) values(?,"
								+ inc + ",?,?,?,?,?,?,?,?,?,?);";
						ps = conn.prepareStatement(in1);
						ps.setInt(1, Integer.parseInt(textField_SrNoMaster.getText()));
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						dt1 = dateChooserRecurring.getDate();
						String date = (String) sdf.format(dateChooserRecurring.getDate());
						java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());

						ps.setDate(2, (java.sql.Date) d);
						ps.setString(3, textFieldName.getText());
						ps.setString(4, textFieldAccountNo.getText());
						ps.setString(5, textFieldNameAgaint.getText());
						ps.setDouble(6, Double.valueOf(total));
						ps.setDouble(7, Double.valueOf(textField_Amount.getText()));
						ps.setDouble(8, Double.valueOf(textField_Interest.getText()));
						ps.setDouble(9, Double.valueOf(textField_InterestAmt.getText()));
						ps.setString(10, textFDuration.getText());
						ps.setString(11, textField_Days.getText());

						int i = ps.executeUpdate();
						if (i > 0) {
							JOptionPane.showMessageDialog(null, "Daily balance added.");
							totalamount();

						}
					} catch (Exception ee) {
						System.out.println(ee.getMessage());
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
						String quey = "select SrNOMaster,max(SrNo) from banksystem.dailyrecurring GROUP BY SrNOMaster="
								+ textField_SrNoMaster.getText() + ";";
						ps = conn.prepareStatement(quey);
						rs = ps.executeQuery();
						while (rs.next())

						{
							int srno = rs.getInt(2);
							String quey2 = "select * from banksystem.dailyrecurring where SrNo=" + srno + ";";
							ps1 = conn.prepareStatement(quey2);
							rs1 = ps1.executeQuery();
							while (rs1.next()) {
								double totalamunt = 0;
								conn = DBConnection.getConnection();
								String query = "Select sum(Amount) from banksystem.dailyrecurring where SrNOMaster="
										+ srno + ";";
								ps2 = conn.prepareStatement(query);
								rs2 = ps2.executeQuery();
								while (rs2.next()) {
									totalamunt = (rs2.getDouble(1));
								}

								int srno11 = rs1.getInt("SrNOMaster");

								double interst = rs1.getDouble("Interst");
								double intamt = rs1.getDouble("InterestAmt");
								double matamt = rs1.getDouble("MaturedAmt");
								String dur = rs1.getString("Duration");
								String day = rs1.getString("Days");
								

								SimpleDateFormat sdf12 = new SimpleDateFormat("yyyy-MM-dd");
								dt1 = dateChooserRecurring.getDate();
								String date12 = (String) sdf12.format(dateChooserRecurring.getDate());
								java.sql.Date d12 = new java.sql.Date(sdf12.parse(date12).getTime());

								String quey22 = "UPDATE banksystem.recurring set Updateamtdate='"+(java.sql.Date) d12+"', Amount=" + totalamunt + ", Intrest="
										+ interst + ", IntrestAmt=" + intamt + ", MaturedAmount=" + matamt
										+ ", Duration='" + dur + "', Days='" + day + "' where SrNoMaster=" + srno11
										+ "";
								ps3 = conn.prepareStatement(quey22);
								ps3.executeUpdate();
								textField_Amount.setText("");
								textField_InterestAmt.setText("");
								textField_MaturedAmount.setText("");

							}

						}
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
						} catch (Exception ewww) {
							System.out.println(ewww.getMessage());
						}
					}
					try {
						datashow();
						totalamount();
					} catch (Exception ee) {
						System.out.println(ee.getMessage());
					}
				}
			}
		});
		btnDailyCollection.setHorizontalAlignment(SwingConstants.LEADING);
		btnDailyCollection.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnDailyCollection.setBounds(191, 11, 140, 38);
		panel_Button.add(btnDailyCollection);

		JButton btnAddNew = new JButton("Add New");
		btnAddNew.setIcon(new ImageIcon(Recurring.class.getResource("/ImageButtonIcon/new.jpg")));
		btnAddNew.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Recurring rec = new Recurring();
				rec.setUndecorated(true);
				rec.setVisible(true);
			}
		});
		btnAddNew.setHorizontalAlignment(SwingConstants.LEADING);
		btnAddNew.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddNew.setBounds(50, 11, 131, 38);
		panel_Button.add(btnAddNew);

		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField_Interest.setEditable(true);
				textFieldOtherAmount.setEditable(true);
				btnEdit.setVisible(false);
				button_Update.setBounds(338, 11, 126, 38);
				button_Update.setVisible(true);
				
				
				Component[] com = panel_Info.getComponents();
				Component[] com1 = panel_info2.getComponents();
				textAreaNotes.setEnabled(true);
				for (int a = 0; a < com.length; a++) {
					com[a].setEnabled(true);

				}
				for (int a = 0; a < com1.length; a++) {
					com1[a].setEnabled(true);
				}
				
				
				
			}
		});
		btnEdit.setIcon(new ImageIcon(Recurring.class.getResource("/ImageButtonIcon/edit.png")));
		btnEdit.setToolTipText("");
		btnEdit.setHorizontalAlignment(SwingConstants.LEADING);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(338, 11, 126, 38);
		panel_Button.add(btnEdit);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(0, 370, 1154, 274);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 11, 1134, 252);
		panel_4.add(scrollPane_1);

		tableRecurring = new JTable();

		tableRecurring.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableRecurring.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				buttonSave.setVisible(false);
				btnDailyCollection.setVisible(true);
				int i = tableRecurring.getSelectedRow();
				model1 = (DefaultTableModel) tableRecurring.getModel();
				textField_SrNoMaster.setText(model1.getValueAt(i, 1).toString());
				textFieldSrNo.setText(model1.getValueAt(i, 2).toString());
				// dateChooserRecurring.setDateFormatString(model1.getValueAt(i,
				// 3).toString());
				textFieldAccountNo.setText(model1.getValueAt(i, 5).toString());
				textFieldName.setText(model1.getValueAt(i, 6).toString());
				textField_Interest.setText(model1.getValueAt(i, 7).toString());
				textFDuration.setText(model1.getValueAt(i, 8).toString());
				textField_Days.setText(model1.getValueAt(i, 9).toString());
				textFieldNameAgaint.setText(model1.getValueAt(i, 13).toString());
				textField_Address.setText(model1.getValueAt(i, 14).toString());
				textField_Age.setText(model1.getValueAt(i, 15).toString());
				textField_ContactNo.setText(model1.getValueAt(i, 16).toString());
				textAreaNotes.setText(model1.getValueAt(i, 17).toString());
				textFieldNomeny.setText(model1.getValueAt(i, 18).toString());
				textFieldNomenyOther.setText(model1.getValueAt(i, 19).toString());

				try {
					for (int ii = 0; ii <= tableRecurring.getRowCount(); ii++) {
						Boolean che = Boolean.valueOf(tableRecurring.getValueAt(ii, 0).toString());

						if (che) {
							String name = String.valueOf(tableRecurring.getValueAt(ii, 6));
							

							conn = DBConnection.getConnection();
							String query = "Select * from banksystem.recurring where CustomerName='" + name + "';";
							ps = conn.prepareStatement(query);
							rs = ps.executeQuery();
							rs.last();
							String srno = rs.getString("SrNoMaster");
							textField_SrNoMaster.setText(srno);
							String sr = rs.getString("SrNo");
							textFieldSrNo.setText(sr);
							String date1 = rs.getString("Date");
							DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							Date date = format.parse(date1);
							// dateChooserRecurring.setDate(date);

							String name2 = rs.getString("CustomerName");
							textFieldName.setText(name2);
							String accno = rs.getString("AccountNo");
							textFieldAccountNo.setText(accno);
							String address = rs.getString("Address");
							textField_Address.setText(address);
							String age = rs.getString("Age");
							textField_Age.setText(age);
							String contno = rs.getString("ContactNo");
							textField_ContactNo.setText(contno);
							String interse = rs.getString("Intrest");
							textField_Interest.setText(interse);
							String duration = rs.getString("Duration");
							textFDuration.setText(duration);
							String days = rs.getString("Days");
							textField_Days.setText(days);
							String note = rs.getString("Notes");
							textAreaNotes.setText(note);
							scrollPane_CoustName.setVisible(false);
							panel_info2.setVisible(true);
							panel_Button.setVisible(true);
							scrollPanenote.setVisible(true);

							// textField_Interest.setEnabled(false);
							comboBoxDouration.setEnabled(false);
							textField_Interest.setEditable(false);
							textFieldOtherAmount.setEditable(false);
							// textFDuration.setEnabled(false);

							datashow();
							totalamount();

						}
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
					che();
				} catch (Exception ww) {
				}
				
				try
				{
					
					Component[] com = panel_Info.getComponents();
					Component[] com1 = panel_info2.getComponents();
					textAreaNotes.setEnabled(false);
					for (int a = 0; a < com.length; a++) {
						com[a].setEnabled(false);

					}
					for (int a = 0; a < com1.length; a++) {
						com1[a].setEnabled(false);
					}
					textField_Amount.setEnabled(true);
					
					
					
					
				}
				catch(Exception ew)
				{
				}
				

			}

			private void che() {
				boolean s = false;
				boolean s1 = true;
				String inc = null;
				int row = 0;
				int rows = tableRecurring.getRowCount() - 1;

				for (int i = 0; i < tableRecurring.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableRecurring.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableRecurring.getSelectedRow();
						int col = tableRecurring.getSelectedColumn();
						tableRecurring.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableRecurring.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableRecurring.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableRecurring.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableRecurring.setValueAt((Object) s, 0, 0);
							tableRecurring.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableRecurring.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableRecurring.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Select", "SrNoMaster", "SrNo", "Date", "Up Amt Date", "A/C No", "Name", "Int %", "Balance", "Douration", "Days", "Interes Amt", "Matured Amt", "Name of Agent", "Address", "Age", "Contact No", "Note", "Nomeny Name", "Nomeny Re"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableRecurring.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableRecurring.getColumnModel().getColumn(0).setMinWidth(50);
		tableRecurring.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableRecurring.getColumnModel().getColumn(1).setMinWidth(0);
		tableRecurring.getColumnModel().getColumn(1).setMaxWidth(0);
		tableRecurring.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableRecurring.getColumnModel().getColumn(2).setMinWidth(50);
		tableRecurring.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(3).setMinWidth(80);
		tableRecurring.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(4).setMinWidth(80);
		tableRecurring.getColumnModel().getColumn(5).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(5).setMinWidth(80);
		tableRecurring.getColumnModel().getColumn(5).setMaxWidth(100);
		tableRecurring.getColumnModel().getColumn(6).setPreferredWidth(150);
		tableRecurring.getColumnModel().getColumn(6).setMinWidth(150);
		tableRecurring.getColumnModel().getColumn(6).setMaxWidth(150);
		tableRecurring.getColumnModel().getColumn(7).setPreferredWidth(50);
		tableRecurring.getColumnModel().getColumn(7).setMinWidth(50);
		tableRecurring.getColumnModel().getColumn(8).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(8).setMinWidth(80);
		tableRecurring.getColumnModel().getColumn(9).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(9).setMinWidth(80);
		tableRecurring.getColumnModel().getColumn(10).setPreferredWidth(0);
		tableRecurring.getColumnModel().getColumn(10).setMinWidth(0);
		tableRecurring.getColumnModel().getColumn(10).setMaxWidth(0);
		tableRecurring.getColumnModel().getColumn(11).setPreferredWidth(0);
		tableRecurring.getColumnModel().getColumn(11).setMinWidth(0);
		tableRecurring.getColumnModel().getColumn(11).setMaxWidth(0);
		tableRecurring.getColumnModel().getColumn(12).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(12).setMinWidth(80);
		tableRecurring.getColumnModel().getColumn(13).setPreferredWidth(150);
		tableRecurring.getColumnModel().getColumn(13).setMinWidth(150);
		tableRecurring.getColumnModel().getColumn(14).setPreferredWidth(150);
		tableRecurring.getColumnModel().getColumn(14).setMinWidth(150);
		tableRecurring.getColumnModel().getColumn(15).setPreferredWidth(50);
		tableRecurring.getColumnModel().getColumn(15).setMinWidth(50);
		tableRecurring.getColumnModel().getColumn(16).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(16).setMinWidth(80);
		tableRecurring.getColumnModel().getColumn(17).setPreferredWidth(150);
		tableRecurring.getColumnModel().getColumn(17).setMinWidth(150);
		tableRecurring.getColumnModel().getColumn(18).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(18).setMinWidth(80);
		tableRecurring.getColumnModel().getColumn(19).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(19).setMinWidth(80);
		datashowall();
		scrollPane_1.setViewportView(tableRecurring);

		JButton btnWithdrawal = new JButton("Withdral");
		btnWithdrawal.setIcon(new ImageIcon(Recurring.class.getResource("/ImageButtonIcon/widrawal1(1).png")));
		btnWithdrawal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double intamt = Double.valueOf(textField_Interest.getText());
				if (intamt <= 0) {
					JOptionPane.showMessageDialog(null,
							"account is suspend mode because balance total withdrawal so your not collection amount.");

				} else {

					try {
						if (model1.getRowCount() != 0) {

							SrNoMaster = textField_SrNoMaster.getText();
							acno = textFieldAccountNo.getText();
							cusname = textFieldName.getText();
							agname = textFieldNameAgaint.getText();
							conn = DBConnection.getConnection();
							String query2 = "SELECT * FROM banksystem.recurring where AccountNo='"
									+ textFieldAccountNo.getText() + "';";
							ps = conn.prepareStatement(query2);
							rs = ps.executeQuery();
							while (rs.next()) {
								SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
								String days = rs.getString("Date");
								double dayss = rs.getDouble("Days");

								Date date = new Date();
								String inputString1 = String.valueOf(days);
								String inputString2 = myFormat.format(date);

								Date date1 = myFormat.parse(inputString1);
								Date date2 = myFormat.parse(inputString2);
								long diff = date2.getTime() - date1.getTime();
								long totalday44 = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
								double totalday = Double.valueOf(totalday44);

								if (totalday <= dayss) {
									RecPremature prem = new RecPremature();
									prem.setVisible(true);
								} else {
									ReMature mat = new ReMature();
									mat.setVisible(true);
								}
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"account is suspend mode because balance total withdrawal");
						}

					} catch (Exception ee1) {
						System.out.println(ee1.getMessage());
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
		btnWithdrawal.setHorizontalAlignment(SwingConstants.LEADING);
		btnWithdrawal.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnWithdrawal.setBounds(10, 655, 161, 38);
		contentPane.add(btnWithdrawal);

		JButton btnView = new JButton("View Transaction");
		btnView.setVisible(false);
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					for (int i = 0; i <= tableRecurring.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableRecurring.getValueAt(i, 0).toString());

						if (che) {
							String name = String.valueOf(tableRecurring.getValueAt(i, 5));

							conn = DBConnection.getConnection();
							String query = "Select * from banksystem.recurring where CustomerName='" + name + "';";
							ps = conn.prepareStatement(query);
							rs = ps.executeQuery();
							rs.last();
							String srno = rs.getString("SrNoMaster");
							textField_SrNoMaster.setText(srno);
							String sr = rs.getString("SrNo");
							textFieldSrNo.setText(sr);
							String date1 = rs.getString("Date");
							DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							Date date = format.parse(date1);
							dateChooserRecurring.setDate(date);
							String name2 = rs.getString("CustomerName");
							textFieldName.setText(name2);
							String accno = rs.getString("AccountNo");
							textFieldAccountNo.setText(accno);
							String address = rs.getString("Address");
							textField_Address.setText(address);
							String age = rs.getString("Age");
							textField_Age.setText(age);
							String contno = rs.getString("ContactNo");
							textField_ContactNo.setText(contno);
							String interse = rs.getString("Intrest");
							textField_Interest.setText(interse);
							String duration = rs.getString("Duration");
							textFDuration.setText(duration);
							String days = rs.getString("Days");
							textField_Days.setText(days);
							String note = rs.getString("Notes");
							textAreaNotes.setText(note);
							scrollPane_CoustName.setVisible(false);
							panel_info2.setVisible(true);
							panel_Button.setVisible(true);
							scrollPanenote.setVisible(true);
							buttonSave.setVisible(false);
							btnDailyCollection.setVisible(true);
							textField_Interest.setEnabled(false);
							comboBoxDouration.setEnabled(false);
							textFDuration.setEnabled(false);
							datashow();

						}
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
		btnView.setIcon(new ImageIcon(Recurring.class.getResource("/ImageButtonIcon/View4.png")));
		btnView.setHorizontalAlignment(SwingConstants.LEADING);
		btnView.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnView.setBounds(361, 655, 198, 38);
		contentPane.add(btnView);

		button = new JButton("Report");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					java.io.InputStream file = getClass().getResourceAsStream("/Reports/TranRecurring.jrxml");
					accno = textFieldAccountNo.getText();
					HashMap para = new HashMap();
					para.put("Accno", accno);
					allinonereport r = new allinonereport("Recrring Report", file, para);

				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon(Recurring.class.getResource("/ImageButtonIcon/Report4d.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(183, 655, 153, 38);
		contentPane.add(button);

		lblTotalAmount = new JLabel("Total Amount:");
		lblTotalAmount.setBounds(858, 657, 127, 14);
		contentPane.add(lblTotalAmount);

		textFieldTotalAmtB = new JTextField();
		textFieldTotalAmtB.setForeground(Color.RED);
		textFieldTotalAmtB.setEditable(false);
		textFieldTotalAmtB.setColumns(10);
		textFieldTotalAmtB.setBounds(995, 655, 132, 20);
		contentPane.add(textFieldTotalAmtB);

	}

	public void increment() {
		try {

			conn = DBConnection.getConnection();
			ps1 = conn.prepareStatement("select max(SrNoMaster) from banksystem.recurring;");
			rs = ps1.executeQuery();
			while (rs.next()) {
				int val = (rs.getInt(1) + 1);
				val1 = String.valueOf(val);

			}
			textField_SrNoMaster.setText(val1);
			textFieldSrNo.setText(val1);
			
			textFieldAccountNo.setText("R" + val1);

			SimpleDateFormat insdf = new SimpleDateFormat("yyyy-MM-dd");
			Date indate = new Date();
			String instringdate = insdf.format(indate);
			dateChooserRecurring.setDate(insdf.parse(instringdate));

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
		textFDuration.setText("Other e.g. 3 Month..");
		textField_Address.setText("");
		textField_Age.setText("0");
		textField_Amount.setText("0");
		textField_ContactNo.setText("");
		textField_Days.setText("");
		textField_Interest.setText("");
		textField_MaturedAmount.setText("0");
		textFieldName.setText("");
		textAreaNotes.setText("");
		textField_InterestAmt.setText("0");
		textFieldNameAgaint.setText("");
		textFieldNomeny.setText("");
		textFieldNomenyOther.setText("");
		textFieldOtherAmount.setText("0");
		comboBoxDouration.setSelectedIndex(0);
		comboBoxNomeny.setSelectedIndex(0);

	}

	public void Calculation() {
		String ins = textField_Interest.getText();
		if (ins.equals("0")) {
			textField_MaturedAmount.setText(textField_Amount.getText());

		} else {

			double amount = Double.valueOf(textField_Amount.getText());
			double interst = Double.valueOf(textField_Interest.getText());
			double totla = amount * interst / 100.0;
			textField_InterestAmt.setText(String.valueOf(totla));
			textField_MaturedAmount.setText(String.valueOf(amount + totla));
		}
	}

	public void daycount(String str) {
		try
		{
		String number = "";
		String letter = "";
		String symbol = "";
		String firstLetter;
		textFDuration.setText(str);

		String[] currency = "48.50USD".split("(?<=\\d)(?=[a-zA-Z])");

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

			textField_Days.setText(String.valueOf(day));

		} else if (firstLetter.equals("M") || firstLetter.equals("m")) {
			double let = Double.valueOf(number);
			double day = let * 30;

			textField_Days.setText(String.valueOf(day));

		} else {
			double let = Double.valueOf(number);
			double day = let * 30;

			textField_Days.setText(String.valueOf(day));
		}
		}
		catch(Exception we)
		{
			we.printStackTrace();
		}
	}

	public void datashow() {

		model1 = (DefaultTableModel) tableRecurring.getModel();
		model1.setRowCount(0);
		try {
			conn = (Connection) DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.dailyrecurring where AccountNo='" + textFieldAccountNo.getText()
					+ "' or CustomerName='" + textFieldName.getText() + "'";
			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data00 = rs.getString("SrNOMaster");
				String data0 = rs.getString("SrNO");
				String data1 = rs.getString("Date");
				String data2 = rs.getString("Date");
				String data3 = rs.getString("AccountNo");
				String data4 = rs.getString("CustomerName");
				String data5 = rs.getString("Interst");
				String data6 = rs.getString("Amount");
				String data7 = rs.getString("Duration");
				String data8 = rs.getString("Days");
				String data9 = rs.getString("InterestAmt");
				String data10 = rs.getString("MaturedAmt");
				

				Object[] row = { Boolean.FALSE, data00, data0, data1, data2, data3, data4, data5, data6, data7, data8,
						data9, data10 };
				model1 = (DefaultTableModel) tableRecurring.getModel();
				model1.addRow(row);
				tableRecurring.getColumnModel().getColumn(0).setMinWidth(0);
				tableRecurring.getColumnModel().getColumn(0).setMaxWidth(0);
				tableRecurring.getColumnModel().getColumn(0).setWidth(0);
				
				
				tableRecurring.getColumnModel().getColumn(3).setMinWidth(0);
				tableRecurring.getColumnModel().getColumn(3).setMaxWidth(0);
				tableRecurring.getColumnModel().getColumn(3).setWidth(0);
				
				
				tableRecurring.getColumnModel().getColumn(13).setMinWidth(0);
				tableRecurring.getColumnModel().getColumn(13).setMaxWidth(0);
				tableRecurring.getColumnModel().getColumn(13).setWidth(0);
				tableRecurring.getColumnModel().getColumn(14).setMinWidth(0);
				tableRecurring.getColumnModel().getColumn(14).setMaxWidth(0);
				tableRecurring.getColumnModel().getColumn(14).setWidth(0);
				tableRecurring.getColumnModel().getColumn(15).setMinWidth(0);
				tableRecurring.getColumnModel().getColumn(15).setMaxWidth(0);
				tableRecurring.getColumnModel().getColumn(15).setWidth(0);
				tableRecurring.getColumnModel().getColumn(16).setMinWidth(0);
				tableRecurring.getColumnModel().getColumn(16).setMaxWidth(0);
				tableRecurring.getColumnModel().getColumn(16).setWidth(0);
				tableRecurring.getColumnModel().getColumn(17).setMinWidth(0);
				tableRecurring.getColumnModel().getColumn(17).setMaxWidth(0);
				tableRecurring.getColumnModel().getColumn(17).setWidth(0);
				tableRecurring.getColumnModel().getColumn(18).setMinWidth(0);
				tableRecurring.getColumnModel().getColumn(18).setMaxWidth(0);
				tableRecurring.getColumnModel().getColumn(18).setWidth(0);
				tableRecurring.getColumnModel().getColumn(19).setMinWidth(0);
				tableRecurring.getColumnModel().getColumn(19).setMaxWidth(0);
				tableRecurring.getColumnModel().getColumn(19).setWidth(0);

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

	public void datashowall() {
		try {
			conn = (Connection) DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.recurring order by SrNoMaster;";
			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data00 = rs.getString("SrNoMaster");
				String data0 = rs.getString("SrNo");
				String data1 = rs.getString("Date");
				String data2 = rs.getString("Updateamtdate");
				String data3 = rs.getString("AccountNo");
				String data4 = rs.getString("CustomerName");
				String data5 = rs.getString("Intrest");
				String data6 = rs.getString("Amount");
				String data7 = rs.getString("Duration");
				String data8 = rs.getString("Days");
				String data9 = rs.getString("IntrestAmt");
				String data10 = rs.getString("MaturedAmount");
				String data11 = rs.getString("Nameofagent");
				String data12 = rs.getString("Address");
				String data13 = rs.getString("Age");
				String data14 = rs.getString("ContactNo");
				String data15 = rs.getString("Notes");
				String data16 = rs.getString("NomenyName");
				String data17 = rs.getString("NomenyRelation");

				Object[] row = { Boolean.FALSE, data00, data0, data1, data2, data3, data4, data5, data6, data7, data8,
						data9, data10, data11, data12, data13, data14, data15, data16, data17 };
				model1 = (DefaultTableModel) tableRecurring.getModel();
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

	public void DurationIncrement() {
		try {

			conn = (Connection) DBConnection.getConnection();
			String show = "select * from banksystem.recurring order by SrNoMaster;";
			ps = conn.prepareStatement(show);
			rs = ps.executeQuery();
			while (rs.next()) {
				String date1 = rs.getString("Updateamtdate");
				String acno = rs.getString("AccountNo");
				double amount = rs.getDouble("Amount");
				double interst = rs.getDouble("Intrest");
				double maturityamt = rs.getDouble("MaturedAmount");
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

					String upsavin = "UPDATE banksystem.recurring set  Updateamtdate='" + (java.sql.Date) sqlDate
							+ "', Amount='" + aginmatuamtsum + "', Intrest='" + interst + "', MaturedAmount='"
							+ allsumamount + "' where AccountNo='" + acno + "'";
					ps1 = conn.prepareStatement(upsavin);
					int ii = ps1.executeUpdate();
					if (ii > 0) {

						int isrno1 = 0;
						int srnomaster1 = 0;
						String name = null;
						String agiengt = null;
						String maxno = "select max(SrNO),SrNOMaster,CustomerName,AgentName from banksystem.dailyrecurring where AccountNo='"
								+ acno + "';";
						ps1 = conn.prepareStatement(maxno);
						rs1 = ps1.executeQuery();
						while (rs1.next()) {
							isrno1 = rs1.getInt(1) + 1;
							srnomaster1 = rs1.getInt("SrNOMaster");
							name = rs1.getString("CustomerName");
							agiengt = rs1.getString("AgentName");

						}
						rs1.close();
						ps1.close();
						String insertdata = "insert into banksystem.dailyrecurring (SrNOMaster, SrNO, Date, CustomerName, AccountNo, AgentName, Amount, Interst, MaturedAmt) values(?,?,?,?,?,?,?,?,?);";
						ps2 = conn.prepareStatement(insertdata);
						ps2.setInt(1, srnomaster1);
						ps2.setInt(2, isrno1);
						ps2.setDate(3, (java.sql.Date) sqlDate);
						ps2.setString(4, name + "-" + " (Interst% added=" + interst + ")");
						ps2.setString(5, acno);
						ps2.setString(6, agiengt);
						ps2.setDouble(7, aginmatuamtsum);
						ps2.setDouble(8, interst);
						ps2.setDouble(9, allsumamount);
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
		int rowsCount = tableRecurring.getRowCount();
		double sum = 0;

		for (int i = 0; i < rowsCount; i++) {
			sum = sum + Double.valueOf(tableRecurring.getValueAt(i, 8).toString());
		}

		textFieldTotalAmtB.setText(String.valueOf(dff.format(sum)));

	}

}
