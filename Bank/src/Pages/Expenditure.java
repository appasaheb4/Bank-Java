package Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;

import CLASS.CalculatorGUI;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseMotionAdapter;

public class Expenditure extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldExpenditureForEx;
	private JTextField textFieldAmtExpenseExA;
	private JTable tableExpenditure;
	private JTextField textFieldSrNoEx;
	private JTextField textFieldSrNoMasterEx;

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
	public DefaultTableModel model1;
	public JTextArea textFieldNotesEx;

	public ResultSet rs;
	private JTextField textFieldAccountNumber;
	public JButton btnSave;
	public JList<String> listName;
	public JScrollPane scrollPaneName;
	public String[] data = new String[1000];
	private JTextField textFieldTotalAmtB;
	public DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Expenditure frame = new Expenditure();
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
	public Expenditure() {
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
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowOpened(WindowEvent e) {
				textFieldExpenditureForEx.requestFocus();
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

		JLabel lblFdAc = new JLabel("Expenditure:");
		lblFdAc.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFdAc.setBounds(22, 11, 276, 42);
		panel.add(lblFdAc);

		JLabel label_1 = new JLabel("Date:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(777, 45, 81, 17);
		panel.add(label_1);

		Date date = new Date();
		JDateChooser dateChooserfdacExpenditureFor = new JDateChooser();
		dateChooserfdacExpenditureFor.setDateFormatString("dd-MM-yyyy");
		dateChooserfdacExpenditureFor.setBounds(860, 45, 196, 20);
		dateChooserfdacExpenditureFor.setDate(date);
		panel.add(dateChooserfdacExpenditureFor);

		textFieldSrNoEx = new JTextField();
		textFieldSrNoEx.setVisible(false);
		textFieldSrNoEx.setEnabled(false);
		textFieldSrNoEx.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldSrNoEx.setColumns(10);
		textFieldSrNoEx.setBounds(174, 25, 209, 23);
		panel.add(textFieldSrNoEx);

		textFieldSrNoMasterEx = new JTextField();
		textFieldSrNoMasterEx.setVisible(false);
		textFieldSrNoMasterEx.setEnabled(false);
		textFieldSrNoMasterEx.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldSrNoMasterEx.setColumns(10);
		textFieldSrNoMasterEx.setBounds(425, 25, 209, 23);
		increment();
		panel.add(textFieldSrNoMasterEx);

		JLabel lblAccountNumber = new JLabel("Account Number:");
		lblAccountNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAccountNumber.setBounds(22, 56, 85, 17);
		panel.add(lblAccountNumber);

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

		textFieldAccountNumber = new JTextField();
		textFieldAccountNumber.setEditable(false);
		textFieldAccountNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldAccountNumber.setColumns(10);
		textFieldAccountNumber.setBounds(136, 53, 209, 23);
		increment();
		panel.add(textFieldAccountNumber);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculatorGUI cl = new CalculatorGUI("Bank System");
				cl.setSize(400, 350);
				cl.setFocusable(true);
				cl.setLocation(830, 0);
				cl.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(Expenditure.class.getResource("/ImageButtonIcon/clac.png")));
		button.setOpaque(false);
		button.setForeground(Color.RED);
		button.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBounds(1080, 11, 64, 55);
		panel.add(button);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 85, 1154, 89);
		contentPane.add(panel_1);

		JLabel lblExpenditureFor = new JLabel("Expenditure for:");
		lblExpenditureFor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblExpenditureFor.setBounds(10, 9, 85, 17);
		panel_1.add(lblExpenditureFor);

		textFieldExpenditureForEx = new JTextField();
		textFieldExpenditureForEx.addKeyListener(new KeyAdapter() {
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
					String query = "select * from banksystem.expenditure where Expfor like '"
							+ textFieldExpenditureForEx.getText() + "%'";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					int i = 0;
					while (rs.next()) {
						String name = rs.getString("Expfor");

						data[i] = name;
						i++;

					}
					listName.setListData(data);
					listName.setVisibleRowCount(100);

					scrollPaneName.setVisible(true);
					listName.setVisible(true);

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
		textFieldExpenditureForEx.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldExpenditureForEx.setColumns(10);
		textFieldExpenditureForEx.setBounds(97, 6, 209, 23);
		panel_1.add(textFieldExpenditureForEx);

		JLabel lblAmountOfExpense = new JLabel("Amount of Expense:");
		lblAmountOfExpense.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAmountOfExpense.setBounds(316, 9, 106, 17);
		panel_1.add(lblAmountOfExpense);

		textFieldAmtExpenseExA = new JTextField();
		textFieldAmtExpenseExA.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				scrollPaneName.setVisible(false);
			}
		});
		textFieldAmtExpenseExA.addKeyListener(new KeyAdapter() {
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
		textFieldAmtExpenseExA.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAmtExpenseExA.setColumns(10);
		textFieldAmtExpenseExA.setBounds(423, 6, 209, 23);
		panel_1.add(textFieldAmtExpenseExA);

		JLabel lblNote = new JLabel("Note:");
		lblNote.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNote.setBounds(642, 9, 47, 17);
		panel_1.add(lblNote);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(683, 6, 385, 72);
		panel_1.add(scrollPane_1);

		textFieldNotesEx = new JTextArea();
		textFieldNotesEx.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == 9) {
					btnSave.requestFocus();
				}
			}
		});
		scrollPane_1.setViewportView(textFieldNotesEx);

		scrollPaneName = new JScrollPane();
		scrollPaneName.setVisible(false);
		scrollPaneName.setBounds(97, 27, 209, 62);
		panel_1.add(scrollPaneName);

		listName = new JList<String>();
		listName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldExpenditureForEx.setText(listName.getSelectedValue());
					String accountnotop = textFieldExpenditureForEx.getText();

					scrollPaneName.setVisible(false);

					try {
						conn = DBConnection.getConnection();
						String query = "Select * from banksystem.expenditure where Expfor=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldExpenditureForEx.getText());
						rs = ps.executeQuery();
						while (rs.next()) {

							String Srnomaster = rs.getString("SrNoMaster");
							textFieldSrNoMasterEx.setText(Srnomaster);

							String UpdateAmtDate = rs.getString("Date");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date dd = sdf.parse(UpdateAmtDate);
							dateChooserfdacExpenditureFor.setDate(dd);

							String AccountNumber = rs.getString("Expfor");
							textFieldExpenditureForEx.setText(AccountNumber);

							String Name = rs.getString("AccountNo");
							textFieldAccountNumber.setText(Name);

							String Address = rs.getString("Amount");
							textFieldAmtExpenseExA.setText(Address);

							String Age = rs.getString("Note");
							textFieldNotesEx.setText(Age);

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

				textFieldExpenditureForEx.setText(listName.getSelectedValue());
				String accountnotop = textFieldExpenditureForEx.getText();

				scrollPaneName.setVisible(false);

				try {
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.expenditure where Expfor=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldExpenditureForEx.getText());
					rs = ps.executeQuery();
					while (rs.next()) {

						String Srnomaster = rs.getString("SrNoMaster");
						textFieldSrNoMasterEx.setText(Srnomaster);

						String UpdateAmtDate = rs.getString("Date");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date dd = sdf.parse(UpdateAmtDate);
						dateChooserfdacExpenditureFor.setDate(dd);

						String AccountNumber = rs.getString("Expfor");
						textFieldExpenditureForEx.setText(AccountNumber);

						String Name = rs.getString("AccountNo");
						textFieldAccountNumber.setText(Name);

						String Address = rs.getString("Amount");
						textFieldAmtExpenseExA.setText(Address);

						String Age = rs.getString("Note");
						textFieldNotesEx.setText(Age);

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
		listName.setVisible(false);
		scrollPaneName.setViewportView(listName);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(0, 185, 1154, 65);
		contentPane.add(panel_2);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String in = "insert into banksystem.expenditure values(?,?,?,?,?,?,?);";
					ps = conn.prepareStatement(in);
					ps.setInt(1, Integer.parseInt(textFieldSrNoMasterEx.getText()));
					ps.setInt(2, Integer.parseInt(textFieldSrNoEx.getText()));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserfdacExpenditureFor.getDate();
					String date = (String) sdf.format(dateChooserfdacExpenditureFor.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(3, (java.sql.Date) d);
					ps.setString(4, textFieldExpenditureForEx.getText());
					ps.setString(5, textFieldAccountNumber.getText());
					ps.setDouble(6, Double.valueOf(textFieldAmtExpenseExA.getText()));
					ps.setString(7, textFieldNotesEx.getText());
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
					} catch (Exception ew) {
						System.out.println(ew.getMessage());
					}
				}
				try {
					double otheramt = Double.valueOf(textFieldAmtExpenseExA.getText());
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
						dt1 = dateChooserfdacExpenditureFor.getDate();
						String date = (String) sdf.format(dateChooserfdacExpenditureFor.getDate());
						java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
						ps1.setDate(2, (java.sql.Date) d);
						ps1.setString(3, "It Self");
						ps1.setString(4, textFieldAccountNumber.getText());
						ps1.setDouble(5, Double.valueOf(textFieldAmtExpenseExA.getText()));
						ps1.setString(6, textFieldNotesEx.getText());
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

				String data0 = textFieldSrNoMasterEx.getText();
				String data1 = textFieldSrNoEx.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				dt1 = dateChooserfdacExpenditureFor.getDate();
				String data2 = (String) sdf.format(dateChooserfdacExpenditureFor.getDate());
				String data3 = textFieldExpenditureForEx.getText();
				String data4 = textFieldAccountNumber.getText();
				String data5 = textFieldAmtExpenseExA.getText();
				String data6 = textFieldNotesEx.getText();

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6 };
				model1 = (DefaultTableModel) tableExpenditure.getModel();
				model1.addRow(row);

			}
		});
		btnSave.setIcon(new ImageIcon(Expenditure.class.getResource("/ImageButtonIcon/Save.png")));
		btnSave.setHorizontalAlignment(SwingConstants.LEADING);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBounds(216, 11, 115, 38);
		panel_2.add(btnSave);

		JButton button_1 = new JButton("Update");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String up = "UPDATE banksystem.expenditure set Date=?, Expfor=?, Amount=?, Note=?,AccountNo=?  where SrNoMaster=?";
					ps = conn.prepareStatement(up);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserfdacExpenditureFor.getDate();
					String date = (String) sdf.format(dateChooserfdacExpenditureFor.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(1, (java.sql.Date) d);
					ps.setString(2, textFieldExpenditureForEx.getText());
					ps.setDouble(3, Double.valueOf(textFieldAmtExpenseExA.getText()));
					ps.setString(4, textFieldNotesEx.getText());
					ps.setString(5, textFieldAccountNumber.getText());
					ps.setInt(6, Integer.parseInt(textFieldSrNoMasterEx.getText()));
					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are update.");
						dispose();
						Expenditure exp = new Expenditure();
						exp.setUndecorated(true);
						exp.setVisible(true);
						increment();
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
		});
		button_1.setIcon(new ImageIcon(Expenditure.class.getResource("/ImageButtonIcon/update.png")));
		button_1.setToolTipText("");
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBounds(341, 11, 126, 38);
		panel_2.add(button_1);

		JButton button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String de = "delete from banksystem.expenditure where SrNomaster=?";
					ps = conn.prepareStatement(de);
					ps.setInt(1, Integer.parseInt(textFieldSrNoMasterEx.getText()));
					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are deleted.");
						dispose();
						Expenditure exp = new Expenditure();
						exp.setUndecorated(true);
						exp.setVisible(true);
						increment();
						reset();
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
		button_2.setIcon(new ImageIcon(Expenditure.class.getResource("/ImageButtonIcon/delete.jpg")));
		button_2.setHorizontalAlignment(SwingConstants.LEADING);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_2.setBounds(474, 11, 115, 38);
		panel_2.add(button_2);

		JButton button_3 = new JButton("Reset");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		button_3.setIcon(new ImageIcon(Expenditure.class.getResource("/ImageButtonIcon/Reset5.jpg")));
		button_3.setHorizontalAlignment(SwingConstants.LEADING);
		button_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_3.setBounds(599, 11, 115, 38);
		panel_2.add(button_3);

		JButton button_4 = new JButton("Exit");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Welcome.frame.enable();
			}
		});
		button_4.setIcon(new ImageIcon(Expenditure.class.getResource("/ImageButtonIcon/Exit.png")));
		button_4.setHorizontalAlignment(SwingConstants.LEADING);
		button_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_4.setBounds(724, 11, 115, 38);
		panel_2.add(button_4);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(0, 261, 1154, 339);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 1134, 317);
		panel_3.add(scrollPane);

		tableExpenditure = new JTable();
		tableExpenditure.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableExpenditure.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int i = tableExpenditure.getSelectedRow();
					model1 = (DefaultTableModel) tableExpenditure.getModel();
					textFieldSrNoMasterEx.setText(model1.getValueAt(i, 1).toString());
					textFieldSrNoEx.setText(model1.getValueAt(i, 2).toString());
					dateChooserfdacExpenditureFor.setDateFormatString(model1.getValueAt(i, 3).toString());
					textFieldExpenditureForEx.setText(model1.getValueAt(i, 4).toString());
					textFieldAmtExpenseExA.setText(model1.getValueAt(i, 5).toString());
					textFieldNotesEx.setText(model1.getValueAt(i, 6).toString());
				} catch (Exception ee) {
				}
				try {
					che();
				} catch (Exception ee) {
				}

			}

			private void che() {
				boolean s = false;
				boolean s1 = true;
				String inc = null;
				int row = 0;
				int rows = tableExpenditure.getRowCount() - 1;

				for (int i = 0; i < tableExpenditure.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableExpenditure.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableExpenditure.getSelectedRow();
						int col = tableExpenditure.getSelectedColumn();
						tableExpenditure.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableExpenditure.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableExpenditure.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableExpenditure.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableExpenditure.setValueAt((Object) s, 0, 0);
							tableExpenditure.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableExpenditure.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableExpenditure.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Select", "SrNoMaster", "SrNo", "Date", "Expeenditure For", "A/C No", "Amt", "Notes" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableExpenditure.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableExpenditure.getColumnModel().getColumn(0).setMinWidth(50);
		tableExpenditure.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableExpenditure.getColumnModel().getColumn(1).setMinWidth(0);
		tableExpenditure.getColumnModel().getColumn(1).setMaxWidth(0);
		tableExpenditure.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableExpenditure.getColumnModel().getColumn(2).setMinWidth(50);
		tableExpenditure.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableExpenditure.getColumnModel().getColumn(3).setMinWidth(80);
		tableExpenditure.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableExpenditure.getColumnModel().getColumn(4).setMinWidth(150);
		tableExpenditure.getColumnModel().getColumn(6).setPreferredWidth(80);
		tableExpenditure.getColumnModel().getColumn(6).setMinWidth(80);
		tableExpenditure.getColumnModel().getColumn(7).setPreferredWidth(150);
		tableExpenditure.getColumnModel().getColumn(7).setMinWidth(150);
		showdata();
		scrollPane.setViewportView(tableExpenditure);

		JButton button_6 = new JButton("Report");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_6.setIcon(new ImageIcon(Expenditure.class.getResource("/ImageButtonIcon/Report4d.png")));
		button_6.setHorizontalAlignment(SwingConstants.LEADING);
		button_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_6.setBounds(21, 611, 150, 38);
		contentPane.add(button_6);

		JLabel label = new JLabel("Total Amount:");
		label.setBounds(880, 614, 109, 14);
		contentPane.add(label);

		textFieldTotalAmtB = new JTextField();
		textFieldTotalAmtB.setForeground(Color.RED);
		textFieldTotalAmtB.setEditable(false);
		textFieldTotalAmtB.setColumns(10);
		textFieldTotalAmtB.setBounds(988, 611, 132, 20);
		contentPane.add(textFieldTotalAmtB);
	}

	public void increment() {
		try {

			conn = DBConnection.getConnection();
			ps1 = conn.prepareStatement("select max(SrNoMaster) from banksystem.expenditure;");
			rs = ps1.executeQuery();
			while (rs.next()) {
				int val = (rs.getInt(1) + 1);
				val1 = String.valueOf(val);

			}
			textFieldSrNoEx.setText(val1);
			textFieldSrNoMasterEx.setText(val1);
			textFieldAccountNumber.setText("Ex" + val1);

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
		textFieldExpenditureForEx.setText("");
		textFieldAmtExpenseExA.setText("");
		textFieldNotesEx.setText("");
	}

	public void showdata() {
		try {
			conn = DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.expenditure order by SrNomaster;";
			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("SrNo");
				String data2 = rs.getString("Date");
				String data3 = rs.getString("Expfor");
				String data4 = rs.getString("AccountNo");
				String data5 = rs.getString("Amount");
				String data6 = rs.getString("Note");
				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6 };
				model1 = (DefaultTableModel) tableExpenditure.getModel();
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

	public void totalamount() {
		int rowsCount = tableExpenditure.getRowCount();
		double sum = 0;

		for (int i = 0; i < rowsCount; i++) {
			sum = sum + Double.valueOf(tableExpenditure.getValueAt(i, 6).toString());
		}
		textFieldTotalAmtB.setText(String.valueOf(df.format(sum)));

	}
}
