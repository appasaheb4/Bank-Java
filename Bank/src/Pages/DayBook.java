package Pages;

import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimerTask;

import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.jdbc.Statement;

import com.toedter.calendar.JDateChooser;

import CLASS.CalculatorGUI;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.BevelBorder;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseMotionAdapter;

public class DayBook extends JFrame {

	private JPanel contentPane;
	private JTable tableLoan;
	private JTable tableSaving;
	private JTable tableCurrent;

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
	public ResultSet rs;
	public ResultSet rs1;
	public ResultSet rs2;
	public ResultSet rs3;
	public DefaultTableModel model1;
	public JLabel lbltotalamount;

	static java.util.Date dt1;
	java.util.Date dt2;
	public static JDateChooser dateChooserDayBook;
	public DecimalFormat df = new DecimalFormat("#.##");
	private JTextField textFieldTotalWithdral;
	private JTextField textFieldTotalDeposite;
	private JTextField textFieldTotalBalance;
	private JTextField textFieldSearch;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JDateChooser dateChooserMinDate;
	public JDateChooser dateChooserMaxDate;

	public JRadioButton radioName;
	public JRadioButton radioDate;
	public JRadioButton radioBoth;
	private JTable tableReccring;
	private JTable tableFD;
	private JTable tableShare;
	private JTable tableScheme;
	private JTable tableExpenditure;
	private JTable tableEmployees;
	private JTable tableCommisstion;
	Date date4 = new Date();
	public JPanel panel_WDB;
	private JTextField textFieldTotalAmtB;
	public JPanel panel_NWDB;
	private JTextField textFieldtype;
	private JTextField textFieldOpnningAmt;
	private static JTextField textFieldClosingAmt;
	
	
	private JTextField textFieldSaving;
	private JTextField textFieldCurrent;
	private JTextField textFieldLoan;
	private JTextField textFieldFd;
	private JTextField textFieldRecurring;
	private JTextField textFieldShare;
	private JTextField textFieldScheme;
	private JTextField textFieldExp;
	private JTextField textFieldEmp;
	private JTextField textFieldAgent;
	public static JTextField textFieldallsumamt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DayBook frame = new DayBook();
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
	public DayBook() {
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
				textFieldSearch.requestFocus();
				oppningamtsn();
				
				Timer timer = new Timer(500, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						savingco();
						currentco();
						loanco();
						fdco();
						schemeco();
						shareco();
						expco();
						empco();
						agentco();
						allcal();
						oppningamtsn();
				
					}
				});
				timer.setRepeats(true);
				timer.setCoalesce(true);
				timer.setInitialDelay(0);
				timer.start();
				oppningamtsn();
				
				
			}
		});

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1154, 88);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Day Book");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 152, 66);
		panel.add(lblNewLabel);

		Date date = new Date();
		dateChooserDayBook = new JDateChooser();
		dateChooserDayBook.setDateFormatString("yyyy-MM-dd");
		dateChooserDayBook.setBounds(865, 57, 196, 20);
		dateChooserDayBook.setDate(date);
		panel.add(dateChooserDayBook);

		JLabel label = new JLabel("Date:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(801, 57, 81, 17);
		panel.add(label);

		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculatorGUI cl = new CalculatorGUI("Bank System");
				cl.setSize(400, 350);
				cl.setFocusable(true);
				cl.setLocation(830, 0);
				cl.setVisible(true);
			}
		});
		button_2.setIcon(new ImageIcon(DayBook.class.getResource("/ImageButtonIcon/clac.png")));
		button_2.setOpaque(false);
		button_2.setForeground(Color.RED);
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button_2.setContentAreaFilled(false);
		button_2.setBorderPainted(false);
		button_2.setBounds(1080, 11, 64, 55);
		panel.add(button_2);

		JTabbedPane tabbedPaneLoan = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneLoan.setBounds(10, 174, 1144, 395);
		contentPane.add(tabbedPaneLoan);

		JLayeredPane layeredPaneSaving = new JLayeredPane();
		layeredPaneSaving.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				try {
					model1 = (DefaultTableModel) tableSaving.getModel();
					model1.setRowCount(0);
					savingdatashow();

					textFieldSearch.setText("");
					radioName.setSelected(true);
					dateChooserMinDate.setDate(date4);
					dateChooserMaxDate.setDate(date4);
					selectra();

					textFieldtype.setText("Saving");
					calsaving();
					allcal();
					
					

				} catch (Exception es) {
				}
			}

			
		});

		layeredPaneSaving.setBorder(new LineBorder(new Color(0, 0, 0)));

		tabbedPaneLoan.addTab("Saving", null, layeredPaneSaving, null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(10, 11, 1119, 345);
		layeredPaneSaving.add(scrollPane_2);

		tableSaving = new JTable();

		tableSaving.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
				int rows = tableSaving.getRowCount() - 1;

				for (int i = 0; i < tableSaving.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableSaving.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableSaving.getSelectedRow();
						int col = tableSaving.getSelectedColumn();
						tableSaving.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableSaving.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableSaving.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableSaving.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableSaving.setValueAt((Object) s, 0, 0);
							tableSaving.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableSaving.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableSaving.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableSaving
				.setModel(
						new DefaultTableModel(new Object[][] {},
								new String[] { "Select", "Sr No", "Date", "Account NO", "Name",
										"TransactionParticulars", "Tan Mode", "Cheque No", "Amount", "Withdrawal",
										"Deposit", "Balance" }) {
							Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class,
									Object.class, Object.class, Object.class, Object.class, Object.class, Object.class,
									Object.class, Object.class };

							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
		tableSaving.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableSaving.getColumnModel().getColumn(0).setMinWidth(50);
		tableSaving.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableSaving.getColumnModel().getColumn(1).setMinWidth(0);
		tableSaving.getColumnModel().getColumn(1).setMaxWidth(0);
		tableSaving.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableSaving.getColumnModel().getColumn(2).setMinWidth(80);
		tableSaving.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableSaving.getColumnModel().getColumn(3).setMinWidth(80);
		tableSaving.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableSaving.getColumnModel().getColumn(4).setMinWidth(150);
		tableSaving.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableSaving.getColumnModel().getColumn(5).setMinWidth(150);
		tableSaving.getColumnModel().getColumn(6).setPreferredWidth(80);
		tableSaving.getColumnModel().getColumn(6).setMinWidth(80);
		tableSaving.getColumnModel().getColumn(7).setPreferredWidth(80);
		tableSaving.getColumnModel().getColumn(7).setMinWidth(80);
		tableSaving.getColumnModel().getColumn(8).setPreferredWidth(15);
		tableSaving.getColumnModel().getColumn(8).setMinWidth(0);
		tableSaving.getColumnModel().getColumn(8).setMaxWidth(0);
		tableSaving.getColumnModel().getColumn(9).setPreferredWidth(80);
		tableSaving.getColumnModel().getColumn(9).setMinWidth(80);
		tableSaving.getColumnModel().getColumn(10).setPreferredWidth(80);
		tableSaving.getColumnModel().getColumn(10).setMinWidth(80);
		tableSaving.getColumnModel().getColumn(11).setPreferredWidth(80);
		tableSaving.getColumnModel().getColumn(11).setMinWidth(80);
		savingdatashow();
		scrollPane_2.setViewportView(tableSaving);

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

		JLayeredPane layeredPaneCurrent = new JLayeredPane();
		layeredPaneCurrent.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				try {
					model1 = (DefaultTableModel) tableCurrent.getModel();
					model1.setRowCount(0);
					currentdatashow();

					textFieldSearch.setText("");
					radioName.setSelected(true);
					dateChooserMinDate.setDate(date4);
					dateChooserMaxDate.setDate(date4);
					selectra();
					textFieldtype.setText("Current");
					calcurrent();
					
					savingco();
					currentco();
					loanco();
					fdco();
					schemeco();
					shareco();
					expco();
					empco();
					agentco();
					allcal();

				} catch (Exception ew) {
				}

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				try {

				} catch (Exception ww) {
				}
			}
		});
		layeredPaneCurrent.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPaneLoan.addTab("Current", null, layeredPaneCurrent, null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 11, 1119, 345);
		layeredPaneCurrent.add(scrollPane_1);

		tableCurrent = new JTable();
		tableCurrent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
				int rows = tableCurrent.getRowCount() - 1;

				for (int i = 0; i < tableCurrent.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableCurrent.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableCurrent.getSelectedRow();
						int col = tableCurrent.getSelectedColumn();
						tableCurrent.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableCurrent.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableCurrent.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableCurrent.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableCurrent.setValueAt((Object) s, 0, 0);
							tableCurrent.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableCurrent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableCurrent.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableCurrent.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Select", "Sr No", "Date", "Account No", "Name",
						"Tran Particular", "Tran Mode", "Cheque No", "Amt", "Withdrawal", "Deposite", "Balance" }) {
					Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class,
							Object.class, Object.class, Object.class, Object.class, Object.class, Object.class,
							Object.class, Object.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		tableCurrent.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableCurrent.getColumnModel().getColumn(0).setMinWidth(50);
		tableCurrent.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableCurrent.getColumnModel().getColumn(1).setMinWidth(0);
		tableCurrent.getColumnModel().getColumn(1).setMaxWidth(0);
		tableCurrent.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableCurrent.getColumnModel().getColumn(2).setMinWidth(80);
		tableCurrent.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableCurrent.getColumnModel().getColumn(3).setMinWidth(80);
		tableCurrent.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableCurrent.getColumnModel().getColumn(4).setMinWidth(150);
		tableCurrent.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableCurrent.getColumnModel().getColumn(5).setMinWidth(150);
		tableCurrent.getColumnModel().getColumn(8).setPreferredWidth(15);
		tableCurrent.getColumnModel().getColumn(8).setMinWidth(0);
		tableCurrent.getColumnModel().getColumn(8).setMaxWidth(0);
		currentdatashow();
		scrollPane_1.setViewportView(tableCurrent);

		JLayeredPane layeredPaneLoan = new JLayeredPane();
		layeredPaneLoan.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				try {
					model1 = (DefaultTableModel) tableLoan.getModel();
					model1.setRowCount(0);
					loandatashow();

					textFieldSearch.setText("");
					radioName.setSelected(true);
					dateChooserMinDate.setDate(date4);
					dateChooserMaxDate.setDate(date4);
					selectra();

					textFieldtype.setText("Loan");
					calloan();
					
					savingco();
					currentco();
					loanco();
					fdco();
					schemeco();
					shareco();
					expco();
					empco();
					agentco();
					allcal();

				} catch (Exception ew) {
				}

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				try {

				} catch (Exception ee) {
				}
			}
		});

		layeredPaneLoan.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPaneLoan.addTab("Loan", null, layeredPaneLoan, null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 1119, 345);
		layeredPaneLoan.add(scrollPane);

		tableLoan = new JTable();
		tableLoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
				int rows = tableLoan.getRowCount() - 1;

				for (int i = 0; i < tableLoan.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableLoan.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableLoan.getSelectedRow();
						int col = tableLoan.getSelectedColumn();
						tableLoan.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableLoan.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableLoan.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableLoan.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableLoan.setValueAt((Object) s, 0, 0);
							tableLoan.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableLoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableLoan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableLoan.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Select", "Sr No", "Date", "Account NO", "Name", "Tran Particular", "Tran Mode", "Cheque No", "Loan of", "Withdrawal", "Deposite", "Balance"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableLoan.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableLoan.getColumnModel().getColumn(0).setMinWidth(50);
		tableLoan.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableLoan.getColumnModel().getColumn(1).setMinWidth(0);
		tableLoan.getColumnModel().getColumn(1).setMaxWidth(0);
		tableLoan.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableLoan.getColumnModel().getColumn(2).setMinWidth(80);
		tableLoan.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableLoan.getColumnModel().getColumn(3).setMinWidth(80);
		tableLoan.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableLoan.getColumnModel().getColumn(4).setMinWidth(150);
		tableLoan.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableLoan.getColumnModel().getColumn(5).setMinWidth(150);
		tableLoan.getColumnModel().getColumn(7).setPreferredWidth(80);
		tableLoan.getColumnModel().getColumn(7).setMinWidth(80);
		tableLoan.getColumnModel().getColumn(8).setPreferredWidth(80);
		tableLoan.getColumnModel().getColumn(8).setMinWidth(80);
		tableLoan.getColumnModel().getColumn(8).setMaxWidth(80);
		tableLoan.getColumnModel().getColumn(9).setPreferredWidth(0);
		tableLoan.getColumnModel().getColumn(9).setMinWidth(0);
		tableLoan.getColumnModel().getColumn(9).setMaxWidth(0);
		loandatashow();
		scrollPane.setViewportView(tableLoan);

		JLayeredPane layeredPaneRecrring = new JLayeredPane();
		layeredPaneRecrring.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				model1 = (DefaultTableModel) tableReccring.getModel();
				model1.setRowCount(0);
				recrringdatashow();

				textFieldSearch.setText("");
				radioName.setSelected(true);
				selectra();

				textFieldtype.setText("Recurring");

				calRecrring();
				
				savingco();
				currentco();
				loanco();
				fdco();
				schemeco();
				shareco();
				expco();
				empco();
				agentco();
				allcal();

			}
		});
		layeredPaneRecrring.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPaneLoan.addTab("Reccring", null, layeredPaneRecrring, null);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_3.setBounds(10, 11, 1119, 345);
		layeredPaneRecrring.add(scrollPane_3);

		tableReccring = new JTable();
		tableReccring.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
				int rows = tableReccring.getRowCount() - 1;

				for (int i = 0; i < tableReccring.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableReccring.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableReccring.getSelectedRow();
						int col = tableReccring.getSelectedColumn();
						tableReccring.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableReccring.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableReccring.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableReccring.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableReccring.setValueAt((Object) s, 0, 0);
							tableReccring.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableReccring.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableReccring.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableReccring.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Select", "Sr.No", "Date", "Account No", "Name",
						"Agent Name", "Amount", "Interst", "Interst Amt", "Maturity Amt", "Duration", "Days" }) {
					Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class,
							Object.class, Object.class, Object.class, Object.class, Object.class, Object.class,
							Object.class, Object.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		tableReccring.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableReccring.getColumnModel().getColumn(0).setMinWidth(50);
		tableReccring.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableReccring.getColumnModel().getColumn(1).setMinWidth(0);
		tableReccring.getColumnModel().getColumn(1).setMaxWidth(0);
		tableReccring.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableReccring.getColumnModel().getColumn(4).setMinWidth(150);
		tableReccring.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableReccring.getColumnModel().getColumn(5).setMinWidth(150);
		tableReccring.getColumnModel().getColumn(6).setPreferredWidth(15);
		tableReccring.getColumnModel().getColumn(6).setMinWidth(80);
		tableReccring.getColumnModel().getColumn(6).setMaxWidth(80);
		tableReccring.getColumnModel().getColumn(7).setPreferredWidth(50);
		tableReccring.getColumnModel().getColumn(7).setMinWidth(50);
		tableReccring.getColumnModel().getColumn(8).setPreferredWidth(80);
		tableReccring.getColumnModel().getColumn(8).setMinWidth(80);
		tableReccring.getColumnModel().getColumn(9).setPreferredWidth(80);
		tableReccring.getColumnModel().getColumn(9).setMinWidth(80);
		tableReccring.getColumnModel().getColumn(10).setPreferredWidth(80);
		tableReccring.getColumnModel().getColumn(10).setMinWidth(80);
		recrringdatashow();
		scrollPane_3.setViewportView(tableReccring);

		JLayeredPane layeredPaneFD = new JLayeredPane();
		layeredPaneFD.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				model1 = (DefaultTableModel) tableFD.getModel();
				model1.setRowCount(0);
				fdtableshow();

				textFieldSearch.setText("");
				radioName.setSelected(true);
				selectra();

				textFieldtype.setText("Fd");
				calfd();
				
				savingco();
				currentco();
				loanco();
				fdco();
				schemeco();
				shareco();
				expco();
				empco();
				agentco();
				allcal();

			}
		});
		layeredPaneFD.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPaneLoan.addTab("Fd", null, layeredPaneFD, null);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_4.setBounds(10, 11, 1119, 345);
		layeredPaneFD.add(scrollPane_4);

		tableFD = new JTable();
		tableFD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					che();
				} catch (Exception es) {
				}
			}

			private void che() {
				boolean s = false;
				boolean s1 = true;
				String inc = null;
				int row = 0;
				int rows = tableFD.getRowCount() - 1;

				for (int i = 0; i < tableFD.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableFD.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableFD.getSelectedRow();
						int col = tableFD.getSelectedColumn();
						tableFD.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableFD.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableFD.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableFD.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableFD.setValueAt((Object) s, 0, 0);
							tableFD.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableFD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableFD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableFD.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Select", "Sr.No", "Date", "Date",
				"Account No", "Name", "Amount", "Interst", "Intest Amt", "Total", "Other Amt", "Duration", "Days" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableFD.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableFD.getColumnModel().getColumn(0).setMinWidth(50);
		tableFD.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableFD.getColumnModel().getColumn(1).setMinWidth(0);
		tableFD.getColumnModel().getColumn(1).setMaxWidth(0);
		tableFD.getColumnModel().getColumn(2).setPreferredWidth(0);
		tableFD.getColumnModel().getColumn(2).setMinWidth(0);
		tableFD.getColumnModel().getColumn(2).setMaxWidth(0);
		tableFD.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableFD.getColumnModel().getColumn(4).setMinWidth(80);
		tableFD.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableFD.getColumnModel().getColumn(5).setMinWidth(150);
		tableFD.getColumnModel().getColumn(6).setPreferredWidth(15);
		tableFD.getColumnModel().getColumn(6).setMinWidth(80);
		tableFD.getColumnModel().getColumn(6).setMaxWidth(80);
		tableFD.getColumnModel().getColumn(7).setPreferredWidth(50);
		tableFD.getColumnModel().getColumn(7).setMinWidth(50);
		tableFD.getColumnModel().getColumn(8).setPreferredWidth(80);
		tableFD.getColumnModel().getColumn(8).setMinWidth(80);
		tableFD.getColumnModel().getColumn(9).setPreferredWidth(80);
		tableFD.getColumnModel().getColumn(9).setMinWidth(80);
		tableFD.getColumnModel().getColumn(10).setPreferredWidth(80);
		tableFD.getColumnModel().getColumn(10).setMinWidth(80);
		tableFD.getColumnModel().getColumn(11).setPreferredWidth(80);
		tableFD.getColumnModel().getColumn(11).setMinWidth(80);
		tableFD.getColumnModel().getColumn(12).setPreferredWidth(80);
		tableFD.getColumnModel().getColumn(12).setMinWidth(80);
		fdtableshow();
		scrollPane_4.setViewportView(tableFD);

		JLayeredPane layeredPaneShare = new JLayeredPane();
		layeredPaneShare.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				model1 = (DefaultTableModel) tableShare.getModel();
				model1.setRowCount(0);
				sharetableshow();

				textFieldSearch.setText("");
				radioName.setSelected(true);
				selectra();

				textFieldtype.setText("Share");
				calshare();
				
				savingco();
				currentco();
				loanco();
				fdco();
				schemeco();
				shareco();
				expco();
				empco();
				agentco();
				allcal();

			}
		});
		layeredPaneShare.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPaneLoan.addTab("Share", null, layeredPaneShare, null);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_5.setBounds(10, 11, 1119, 345);
		layeredPaneShare.add(scrollPane_5);

		tableShare = new JTable();
		tableShare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					che();
				} catch (Exception eww) {
				}
			}

			private void che() {
				boolean s = false;
				boolean s1 = true;
				String inc = null;
				int row = 0;
				int rows = tableShare.getRowCount() - 1;

				for (int i = 0; i < tableShare.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableShare.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableShare.getSelectedRow();
						int col = tableShare.getSelectedColumn();
						tableShare.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableShare.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableShare.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableShare.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableShare.setValueAt((Object) s, 0, 0);
							tableShare.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableShare.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableShare.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableShare.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Select", "Sr. No", "Date",
				"Account No", "Name", "Amount", "Interst", "Total Qty", "Total", "Other Amt" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableShare.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableShare.getColumnModel().getColumn(0).setMinWidth(50);
		tableShare.getColumnModel().getColumn(1).setPreferredWidth(80);
		tableShare.getColumnModel().getColumn(1).setMinWidth(80);
		tableShare.getColumnModel().getColumn(1).setMaxWidth(80);
		tableShare.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableShare.getColumnModel().getColumn(4).setMinWidth(150);
		tableShare.getColumnModel().getColumn(5).setPreferredWidth(15);
		tableShare.getColumnModel().getColumn(5).setMinWidth(80);
		tableShare.getColumnModel().getColumn(5).setMaxWidth(80);
		sharetableshow();
		scrollPane_5.setViewportView(tableShare);

		JLayeredPane layeredPaneScheme = new JLayeredPane();
		layeredPaneScheme.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				model1 = (DefaultTableModel) tableScheme.getModel();
				model1.setRowCount(0);
				schemetableshow();

				textFieldSearch.setText("");
				radioName.setSelected(true);
				selectra();

				textFieldtype.setText("Scheme");
				calschem();
				
				savingco();
				currentco();
				loanco();
				fdco();
				schemeco();
				shareco();
				expco();
				empco();
				agentco();
				allcal();

			}
		});
		layeredPaneScheme.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPaneLoan.addTab("Scheme", null, layeredPaneScheme, null);

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_6.setBounds(10, 11, 1119, 345);
		layeredPaneScheme.add(scrollPane_6);

		tableScheme = new JTable();
		tableScheme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
				int rows = tableScheme.getRowCount() - 1;

				for (int i = 0; i < tableScheme.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableScheme.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableScheme.getSelectedRow();
						int col = tableScheme.getSelectedColumn();
						tableScheme.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableScheme.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableScheme.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableScheme.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableScheme.setValueAt((Object) s, 0, 0);
							tableScheme.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableScheme.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableScheme.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableScheme.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Select", "Sr No", "Date", "Accnount No", "Name", "Sch Type", "Amount", "Interst",
						"Interst Amt", "Total", "Other Amt", "Duration", "Days" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableScheme.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableScheme.getColumnModel().getColumn(0).setMinWidth(50);
		tableScheme.getColumnModel().getColumn(1).setPreferredWidth(80);
		tableScheme.getColumnModel().getColumn(1).setMinWidth(80);
		tableScheme.getColumnModel().getColumn(1).setMaxWidth(80);
		tableScheme.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableScheme.getColumnModel().getColumn(4).setMinWidth(150);
		tableScheme.getColumnModel().getColumn(6).setPreferredWidth(15);
		tableScheme.getColumnModel().getColumn(6).setMinWidth(80);
		tableScheme.getColumnModel().getColumn(6).setMaxWidth(80);
		schemetableshow();
		scrollPane_6.setViewportView(tableScheme);

		JLayeredPane layeredPaneExpenditure = new JLayeredPane();
		layeredPaneExpenditure.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				model1 = (DefaultTableModel) tableExpenditure.getModel();
				model1.setRowCount(0);
				expdatashow();

				textFieldSearch.setText("");
				radioName.setSelected(true);
				selectra();

				textFieldtype.setText("Exp");

				calexp();
				
				savingco();
				currentco();
				loanco();
				fdco();
				schemeco();
				shareco();
				expco();
				empco();
				agentco();
				allcal();

			}
		});
		layeredPaneExpenditure.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPaneLoan.addTab("Expenditure", null, layeredPaneExpenditure, null);

		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_7.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_7.setBounds(10, 11, 1119, 345);
		layeredPaneExpenditure.add(scrollPane_7);

		tableExpenditure = new JTable();
		tableExpenditure.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					che();
				} catch (Exception ww) {
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
		tableExpenditure.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableExpenditure.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableExpenditure.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Select", "Sr No", "Date", "Account No", "Expenditure for", "Amount" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableExpenditure.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableExpenditure.getColumnModel().getColumn(0).setMinWidth(50);
		tableExpenditure.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableExpenditure.getColumnModel().getColumn(1).setMinWidth(0);
		tableExpenditure.getColumnModel().getColumn(1).setMaxWidth(0);
		tableExpenditure.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableExpenditure.getColumnModel().getColumn(4).setMinWidth(150);
		tableExpenditure.getColumnModel().getColumn(5).setPreferredWidth(80);
		tableExpenditure.getColumnModel().getColumn(5).setMinWidth(80);
		expdatashow();
		scrollPane_7.setViewportView(tableExpenditure);

		JLayeredPane layeredPaneEmployees = new JLayeredPane();
		layeredPaneEmployees.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				model1 = (DefaultTableModel) tableEmployees.getModel();
				model1.setRowCount(0);
				emptableshow();

				textFieldSearch.setText("");
				radioName.setSelected(true);
				selectra();

				textFieldtype.setText("Emp");
				calemp();
				
				savingco();
				currentco();
				loanco();
				fdco();
				schemeco();
				shareco();
				expco();
				empco();
				agentco();
				allcal();

			}
		});
		layeredPaneEmployees.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPaneLoan.addTab("Employees", null, layeredPaneEmployees, null);

		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_8.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_8.setBounds(10, 11, 1119, 345);
		layeredPaneEmployees.add(scrollPane_8);

		tableEmployees = new JTable();
		tableEmployees.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					che();
				} catch (Exception eww) {
				}
			}

			private void che() {
				boolean s = false;
				boolean s1 = true;
				String inc = null;
				int row = 0;
				int rows = tableEmployees.getRowCount() - 1;

				for (int i = 0; i < tableEmployees.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableEmployees.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableEmployees.getSelectedRow();
						int col = tableEmployees.getSelectedColumn();
						tableEmployees.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableEmployees.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableEmployees.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableEmployees.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableEmployees.setValueAt((Object) s, 0, 0);
							tableEmployees.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableEmployees.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableEmployees.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableEmployees.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Select", "Sr No", "Date", "Account No", "Name",
						"Adv Paid Amt", "Salary", "Leaves", "Leaves Amt", "Total Amt", "Tran Mode", "Cheque No" }) {
					Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class,
							Object.class, Object.class, Object.class, Object.class, Object.class, Object.class,
							Object.class, Object.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		tableEmployees.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableEmployees.getColumnModel().getColumn(0).setMinWidth(50);
		tableEmployees.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableEmployees.getColumnModel().getColumn(1).setMinWidth(0);
		tableEmployees.getColumnModel().getColumn(1).setMaxWidth(0);
		tableEmployees.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableEmployees.getColumnModel().getColumn(4).setMinWidth(150);
		emptableshow();
		scrollPane_8.setViewportView(tableEmployees);

		JLayeredPane layeredPaneCommistion = new JLayeredPane();
		layeredPaneCommistion.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				model1 = (DefaultTableModel) tableCommisstion.getModel();
				model1.setRowCount(0);
				commisstiontableshow();

				textFieldSearch.setText("");
				radioName.setSelected(true);
				selectra();

				textFieldtype.setText("Comm");
				calcomm();
				
				savingco();
				currentco();
				loanco();
				fdco();
				schemeco();
				shareco();
				expco();
				empco();
				agentco();
				allcal();

			}
		});
		layeredPaneCommistion.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPaneLoan.addTab("Commission(Agent)", null, layeredPaneCommistion, null);

		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_9.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_9.setBounds(10, 11, 1119, 345);
		layeredPaneCommistion.add(scrollPane_9);

		tableCommisstion = new JTable();
		tableCommisstion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					che();
				} catch (Exception ww) {
				}
			}

			private void che() {
				boolean s = false;
				boolean s1 = true;
				String inc = null;
				int row = 0;
				int rows = tableCommisstion.getRowCount() - 1;

				for (int i = 0; i < tableCommisstion.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableCommisstion.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableCommisstion.getSelectedRow();
						int col = tableCommisstion.getSelectedColumn();
						tableCommisstion.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableCommisstion.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableCommisstion.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableCommisstion.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableCommisstion.setValueAt((Object) s, 0, 0);
							tableCommisstion.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableCommisstion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableCommisstion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableCommisstion.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Select", "Sr No", "Date", "Account No", "Name", "Paid Amount", "All Cust  Amt" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableCommisstion.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableCommisstion.getColumnModel().getColumn(0).setMinWidth(50);
		tableCommisstion.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableCommisstion.getColumnModel().getColumn(1).setMinWidth(0);
		tableCommisstion.getColumnModel().getColumn(1).setMaxWidth(0);
		tableCommisstion.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableCommisstion.getColumnModel().getColumn(4).setMinWidth(150);
		commisstiontableshow();
		scrollPane_9.setViewportView(tableCommisstion);

		JLabel label_1 = new JLabel("Search for:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(10, 105, 96, 17);
		contentPane.add(label_1);

		radioName = new JRadioButton("Name");
		radioName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectra();
			}
		});
		buttonGroup.add(radioName);
		radioName.setSelected(true);
		radioName.setBounds(94, 104, 68, 23);
		contentPane.add(radioName);

		radioDate = new JRadioButton("Date");
		radioDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectra();
			}
		});
		buttonGroup.add(radioDate);
		radioDate.setBounds(164, 104, 68, 23);
		contentPane.add(radioDate);

		radioBoth = new JRadioButton("Both");
		radioBoth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectra();
			}
		});
		buttonGroup.add(radioBoth);
		radioBoth.setBounds(234, 104, 68, 23);
		contentPane.add(radioBoth);

		JLabel label_2 = new JLabel("Search:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(10, 139, 52, 17);
		contentPane.add(label_2);

		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String quey = textFieldSearch.getText().toLowerCase();
				filterloan(quey);

			}
		});
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSearch.setColumns(10);
		textFieldSearch.setBounds(72, 139, 230, 20);
		contentPane.add(textFieldSearch);

		JLabel label_3 = new JLabel("Date:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(312, 139, 52, 17);
		contentPane.add(label_3);

		dateChooserMinDate = new JDateChooser();
		dateChooserMinDate.setEnabled(false);
		dateChooserMinDate.setDateFormatString("yyyy-MM-dd");
		dateChooserMinDate.setBounds(357, 139, 105, 20);
		dateChooserMinDate.setDate(date);
		contentPane.add(dateChooserMinDate);

		JLabel label_4 = new JLabel("To");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(472, 142, 31, 17);
		contentPane.add(label_4);

		dateChooserMaxDate = new JDateChooser();
		dateChooserMaxDate.setEnabled(false);
		dateChooserMaxDate.setDateFormatString("yyyy-MM-dd");
		dateChooserMaxDate.setBounds(497, 139, 105, 20);
		dateChooserMaxDate.setDate(date);
		contentPane.add(dateChooserMaxDate);

		JButton button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model1 = (DefaultTableModel) tableSaving.getModel();
				model1.setRowCount(0);
				model1 = (DefaultTableModel) tableCurrent.getModel();
				model1.setRowCount(0);
				model1 = (DefaultTableModel) tableLoan.getModel();
				model1.setRowCount(0);
				model1 = (DefaultTableModel) tableReccring.getModel();
				model1.setRowCount(0);
				model1 = (DefaultTableModel) tableFD.getModel();
				model1.setRowCount(0);
				model1 = (DefaultTableModel) tableShare.getModel();
				model1.setRowCount(0);
				model1 = (DefaultTableModel) tableScheme.getModel();
				model1.setRowCount(0);
				model1 = (DefaultTableModel) tableExpenditure.getModel();
				model1.setRowCount(0);
				model1 = (DefaultTableModel) tableEmployees.getModel();
				model1.setRowCount(0);
				model1 = (DefaultTableModel) tableCommisstion.getModel();
				model1.setRowCount(0);

				try {
					conn = DBConnection.getConnection();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserMinDate.getDate();
					String datemin = (String) sdf.format(dateChooserMinDate.getDate());
					dt1 = dateChooserMaxDate.getDate();
					String datemax = (String) sdf.format(dateChooserMaxDate.getDate());
					String loqu = "SELECT * FROM banksystem.savingtranscation where Date between '" + datemin
							+ "' AND '" + datemax + "';";
					ps = conn.prepareStatement(loqu);
					rs = ps.executeQuery();
					int i1 = 0;
					while (rs.next()) {
						i1 = 5;
						String data0 = rs.getString("SrnoMaster");
						String data1 = rs.getString("Date");
						String data3 = rs.getString("Name");
						String data2 = rs.getString("AccountNo");
						String data4 = rs.getString("TransactionParticulars");
						String data5 = rs.getString("tranby");
						String data6 = rs.getString("chequeno");
						double amount = rs.getDouble("Amount");
						String data7 = df.format(amount);

						double Widtral = rs.getDouble("Widtral");
						String data8 = df.format(Widtral);
						double Depotie = rs.getDouble("Depotie");
						String data9 = df.format(Depotie);

						double inters = rs.getDouble("Balance");
						String data10 = df.format(inters);

						Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8,
								data9, data10 };
						model1 = (DefaultTableModel) tableSaving.getModel();
						model1.addRow(row);
						calsaving();
						

					}

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

				// ----------------------------------------Current--------------------------------------------------------------
				try {

					conn = DBConnection.getConnection();

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserMinDate.getDate();
					String datemin = (String) sdf.format(dateChooserMinDate.getDate());
					dt1 = dateChooserMaxDate.getDate();
					String datemax = (String) sdf.format(dateChooserMaxDate.getDate());

					String loqu11 = "SELECT * FROM banksystem.currenttranscation where Date between '" + datemin
							+ "' AND '" + datemax + "';";
					ps = conn.prepareStatement(loqu11);
					int i2 = 0;
					rs = ps.executeQuery();

					while (rs.next()) {
						i2 = 5;

						String data0 = rs.getString("SrNoMaster");
						String data1 = rs.getString("Date");
						String data3 = rs.getString("Name");
						String data2 = rs.getString("AccountNo");
						String data4 = rs.getString("TransactionParticulars");
						String data5 = rs.getString("tranby");
						String data6 = rs.getString("chequeno");

						double amount = rs.getDouble("Amount");
						String data7 = df.format(amount);
						double Withral = rs.getDouble("Withral");
						String data8 = df.format(Withral);
						double Deposite = rs.getDouble("Deposite");
						String data9 = df.format(Deposite);

						double amount4 = rs.getDouble("Balance");
						String data10 = df.format(amount4);

						Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8,
								data9, data10 };
						model1 = (DefaultTableModel) tableCurrent.getModel();
						model1.addRow(row);
						calcurrent();
					

					}

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

				// ===================================================Loan==========================================

				try {
					conn = DBConnection.getConnection();

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserMinDate.getDate();
					String datemin = (String) sdf.format(dateChooserMinDate.getDate());
					dt1 = dateChooserMaxDate.getDate();
					String datemax = (String) sdf.format(dateChooserMaxDate.getDate());

					String loqu4 = "SELECT * FROM banksystem.loantranscation where Date between '" + datemin + "' AND '"
							+ datemax + "';";
					ps = conn.prepareStatement(loqu4);
					int i3 = 0;
					rs = ps.executeQuery();

					while (rs.next()) {
						i3 = 5;
						String data0 = rs.getString("Srnomaster");
						String data1 = rs.getString("Date");
						String data4 = rs.getString("Name");
						String data2 = rs.getString("AccountNo");
						String data5 = rs.getString("TransactionParticulars");
						String data6 = rs.getString("TranBy");
						String data7 = rs.getString("chequeno");
						double aamoount = 0;
						String firstamount = "select * from banksystem.loantranscation where AccountNo='" + data2 + "'";
						ps1 = conn.prepareStatement(firstamount);
						rs1 = ps1.executeQuery();
						if (rs1.first()) {
							aamoount = rs1.getDouble("Balance");
						}
						String data8 = df.format(aamoount);

						double Withdral = rs.getDouble("Withdral");
						String data9 = df.format(Withdral);
						double Deposite = rs.getDouble("Deposite");
						String data10 = df.format(Deposite);

						double inters = rs.getDouble("Balance");
						String data11 = df.format(inters);

						Object[] row = { Boolean.FALSE, data0, data1, data2, data4, data5, data6, data7, data8, data9,
								data10, data11 };
						model1 = (DefaultTableModel) tableLoan.getModel();
						model1.addRow(row);
						calloan();
					

					}

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

				// ==================================================================Recrring============================

				try {
					conn = DBConnection.getConnection();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserMinDate.getDate();
					String datemin = (String) sdf.format(dateChooserMinDate.getDate());
					dt1 = dateChooserMaxDate.getDate();
					String datemax = (String) sdf.format(dateChooserMaxDate.getDate());
					String loqu = "SELECT * FROM banksystem.dailyrecurring where Date between '" + datemin + "' AND '"
							+ datemax + "';";
					ps = conn.prepareStatement(loqu);
					int i4 = 0;
					rs = ps.executeQuery();
					while (rs.next()) {
						i4 = 5;

						String data0 = rs.getString("SrNoMaster");
						String data1 = rs.getString("Date");
						String data2 = rs.getString("AccountNo");
						String data3 = rs.getString("CustomerName");
						String data4 = rs.getString("AgentName");

						double amout = rs.getDouble("Amount");
						String data5 = df.format(amout);
						double interst = rs.getDouble("Interst");
						String data6 = df.format(interst);
						double intamt = rs.getDouble("InterestAmt");
						String data7 = df.format(intamt);
						double mamount = rs.getDouble("MaturedAmt");
						String data8 = df.format(mamount);
						String data9 = rs.getString("Duration");
						String data10 = rs.getString("Days");
						Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8,
								data9, data10 };
						model1 = (DefaultTableModel) tableReccring.getModel();
						model1.addRow(row);
						calRecrring();
					

					}

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

				// ===========================================================Fd=========================================

				try {
					conn = DBConnection.getConnection();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserMinDate.getDate();
					String datemin = (String) sdf.format(dateChooserMinDate.getDate());
					dt1 = dateChooserMaxDate.getDate();
					String datemax = (String) sdf.format(dateChooserMaxDate.getDate());
					String loqu = "SELECT * FROM banksystem.fd where UpdateDate between '" + datemin + "' AND '"
							+ datemax + "';";
					ps = conn.prepareStatement(loqu);

					rs = ps.executeQuery();
					int i5 = 0;
					while (rs.next()) {
						i5 = 5;

						String data0 = rs.getString("SrNoMaster");
						String data1 = rs.getString("Date");
						String data2 = rs.getString("UpdateDate");
						String data4 = rs.getString("Name");
						String data3 = rs.getString("AccountNumber");

						double amout = rs.getDouble("Amount");
						String data6 = df.format(amout);
						double interst = rs.getDouble("Interes");
						String data7 = df.format(interst);
						double intamt = rs.getDouble("IntsetAmt");
						String data8 = df.format(intamt);
						double mamount = rs.getDouble("Total");
						String data9 = df.format(mamount);
						double otheramt = rs.getDouble("OtherAmount");
						String data10 = df.format(otheramt);
						String data11 = rs.getString("Duration");
						String data12 = rs.getString("Days");
						Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data6, data7, data8, data9,
								data10, data11, data12 };
						model1 = (DefaultTableModel) tableFD.getModel();
						model1.addRow(row);
						calfd();
						

					}

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

				// ===========================================================Share=======================================

				try {
					conn = DBConnection.getConnection();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserMinDate.getDate();
					String datemin = (String) sdf.format(dateChooserMinDate.getDate());
					dt1 = dateChooserMaxDate.getDate();
					String datemax = (String) sdf.format(dateChooserMaxDate.getDate());
					String loqu = "SELECT * FROM banksystem.shares where Date between '" + datemin + "' AND '" + datemax
							+ "';";
					ps = conn.prepareStatement(loqu);
					rs = ps.executeQuery();
					int i6 = 0;
					while (rs.next()) {
						i6 = 5;
						String data0 = rs.getString("SrNoMaster");
						String data1 = rs.getString("Date");
						String data3 = rs.getString("Name");
						String data2 = rs.getString("AccountNo");

						double amout = rs.getDouble("Amount");
						String data4 = df.format(amout);
						double interst = rs.getDouble("SchPer");
						String data5 = df.format(interst);
						double intamt = rs.getDouble("TotalQtyl");
						String data6 = df.format(intamt);
						double mamount = rs.getDouble("Total");
						String data7 = df.format(mamount);
						double otheramt = rs.getDouble("OtherAmount");
						String data8 = df.format(otheramt);

						Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8 };
						model1 = (DefaultTableModel) tableShare.getModel();
						model1.addRow(row);
						calshare();
						

					}

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

				// =============================================Scheme====================================================

				try {
					conn = DBConnection.getConnection();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserMinDate.getDate();
					String datemin = (String) sdf.format(dateChooserMinDate.getDate());
					dt1 = dateChooserMaxDate.getDate();
					String datemax = (String) sdf.format(dateChooserMaxDate.getDate());
					String loqu = "SELECT * FROM banksystem.schemea_c where Date between '" + datemin + "' AND '"
							+ datemax + "';";
					ps = conn.prepareStatement(loqu);
					int i7 = 0;
					rs = ps.executeQuery();
					while (rs.next()) {
						i7 = 5;
						String data0 = rs.getString("SrNoMaster");
						String data1 = rs.getString("Date");
						String data3 = rs.getString("Name");
						String data2 = rs.getString("AccountNo");
						String data4 = rs.getString("SchType");
						double amout = rs.getDouble("Amount");
						String data5 = df.format(amout);
						double interst = rs.getDouble("SchPer");
						String data6 = df.format(interst);
						double intamt = rs.getDouble("SchAmt");
						String data7 = df.format(intamt);
						double mamount = rs.getDouble("Total");
						String data8 = df.format(mamount);
						double otheramt = rs.getDouble("OtherAmount");
						String data9 = df.format(otheramt);

						String data10 = rs.getString("Duration");
						String data11 = rs.getString("Days");
						Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8,
								data9, data10, data11 };
						model1 = (DefaultTableModel) tableScheme.getModel();
						model1.addRow(row);
						calschem();
						

					}

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

				// ========================================================Expentidire============================

				try {
					conn = DBConnection.getConnection();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserMinDate.getDate();
					String datemin = (String) sdf.format(dateChooserMinDate.getDate());
					dt1 = dateChooserMaxDate.getDate();
					String datemax = (String) sdf.format(dateChooserMaxDate.getDate());
					String loqu = "SELECT * FROM banksystem.expenditure where Date between '" + datemin + "' AND '"
							+ datemax + "';";
					ps = conn.prepareStatement(loqu);
					rs = ps.executeQuery();
					int i8 = 0;
					while (rs.next()) {
						i8 = 5;

						String data0 = rs.getString("SrNoMaster");
						String data1 = rs.getString("Date");
						String data3 = rs.getString("Expfor");
						String data2 = rs.getString("AccountNo");

						double amout = rs.getDouble("Amount");
						String data4 = df.format(amout);

						Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4 };
						model1 = (DefaultTableModel) tableExpenditure.getModel();
						model1.addRow(row);
						calexp();
						

					}

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

				// ================================Employees==================================================================

				try {
					conn = DBConnection.getConnection();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserMinDate.getDate();
					String datemin = (String) sdf.format(dateChooserMinDate.getDate());
					dt1 = dateChooserMaxDate.getDate();
					String datemax = (String) sdf.format(dateChooserMaxDate.getDate());
					String loqu = "SELECT * FROM banksystem.emptrancation where Date between '" + datemin + "' AND '"
							+ datemax + "';";
					ps = conn.prepareStatement(loqu);
					int i9 = 0;
					rs = ps.executeQuery();
					while (rs.next()) {
						i9 = 0;
						String data0 = rs.getString("SrNoMaster");
						String data1 = rs.getString("Date");
						String data3 = rs.getString("Name");
						String data2 = rs.getString("AccountNo");
						double amout = rs.getDouble("AdvancePay");
						String data4 = df.format(amout);
						double intast = rs.getDouble("Salary");
						String data5 = df.format(intast);
						String data6 = rs.getString("Leaves");
						double amout1 = rs.getDouble("LeavesAmt");
						String data7 = df.format(amout1);
						double intast1 = rs.getDouble("TotalAmount");
						String data8 = df.format(intast1);
						String data9 = rs.getString("Mode");
						String data10 = rs.getString("ChequeNo");

						Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8,
								data9, data10 };
						model1 = (DefaultTableModel) tableEmployees.getModel();
						model1.addRow(row);
						calemp();
					

					}

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

				// ===================================Commisstion============================================

				try {
					conn = DBConnection.getConnection();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserMinDate.getDate();
					String datemin = (String) sdf.format(dateChooserMinDate.getDate());
					dt1 = dateChooserMaxDate.getDate();
					String datemax = (String) sdf.format(dateChooserMaxDate.getDate());
					String loqu = "SELECT * FROM banksystem.commissiona_ctrancation where Date between '" + datemin
							+ "' AND '" + datemax + "';";
					ps = conn.prepareStatement(loqu);
					int i10 = 0;
					rs = ps.executeQuery();
					while (rs.next()) {
						i10 = 5;
						String data0 = rs.getString("SrNoMaster");
						String data1 = rs.getString("Date");
						String data3 = rs.getString("Name");
						String data2 = rs.getString("AccountNo");

						double amout = rs.getDouble("PaidAmount");
						String data4 = df.format(amout);
						double interst = rs.getDouble("AllCustomerAmount");
						String data5 = df.format(interst);

						Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5 };
						model1 = (DefaultTableModel) tableCommisstion.getModel();
						model1.addRow(row);
						calcomm();
						
						

					}

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
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(615, 135, 115, 28);
		contentPane.add(button);

		JButton button_1 = new JButton("Report");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oppningamtsn();
				try {

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserMinDate.getDate();
					String mindate = (String) sdf.format(dateChooserMinDate.getDate());
					java.sql.Date dmin = new java.sql.Date(sdf.parse(mindate).getTime());

					dt1 = dateChooserMaxDate.getDate();
					String maxdate = (String) sdf.format(dateChooserMaxDate.getDate());
					java.sql.Date dmax = new java.sql.Date(sdf.parse(maxdate).getTime());
					java.io.InputStream file = getClass().getResourceAsStream("/Reports/DayBook.jrxml");

					HashMap para = new HashMap();
					para.put("MinDate", dmin);
					para.put("MaxDate", dmax);
					double colsingamt = Double.valueOf(textFieldallsumamt.getText());
					para.put("colsingamt", colsingamt);
					String opnningamount=textFieldOpnningAmt.getText();
					para.put("OpnningAmt", opnningamount);
					conn = (Connection) DBConnection.getConnection();
					JasperReport jr = JasperCompileManager.compileReport(file);
					JasperPrint jp = JasperFillManager.fillReport(jr, para, conn);
					//jp.setOrientation(OrientationEnum.PORTRAIT);
					 JasperViewer view=new JasperViewer(jp,false);
					 view.setBounds(180, 50, 1190, 740);
					 view.setVisible(true);
					
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

			}
		});
		button_1.setIcon(new ImageIcon(DayBook.class.getResource("/ImageButtonIcon/Report4d.png")));
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBounds(30, 580, 150, 38);
		contentPane.add(button_1);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showoppningamt();
				dispose();
				

			}
		});
		btnExit.setIcon(new ImageIcon(DayBook.class.getResource("/ImageButtonIcon/Exit.png")));
		btnExit.setHorizontalAlignment(SwingConstants.LEADING);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(30, 629, 150, 38);
		contentPane.add(btnExit);

		panel_WDB = new JPanel();
		panel_WDB.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_WDB.setBounds(524, 580, 309, 102);
		contentPane.add(panel_WDB);
		panel_WDB.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Total Withdrawal Amt:");
		lblNewLabel_1.setBounds(7, 14, 151, 14);
		panel_WDB.add(lblNewLabel_1);

		textFieldTotalWithdral = new JTextField();
		textFieldTotalWithdral.setForeground(Color.RED);
		textFieldTotalWithdral.setBounds(156, 8, 143, 20);
		panel_WDB.add(textFieldTotalWithdral);
		textFieldTotalWithdral.setEditable(false);
		textFieldTotalWithdral.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldTotalWithdral.setColumns(10);

		JLabel lblTotalInterst = new JLabel("Total Deposite Amt:");
		lblTotalInterst.setBounds(7, 42, 151, 14);
		panel_WDB.add(lblTotalInterst);

		textFieldTotalDeposite = new JTextField();
		textFieldTotalDeposite.setForeground(Color.RED);
		textFieldTotalDeposite.setBounds(156, 36, 143, 20);
		panel_WDB.add(textFieldTotalDeposite);
		textFieldTotalDeposite.setEditable(false);
		textFieldTotalDeposite.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldTotalDeposite.setColumns(10);

		JLabel lblTotalInterstAmt = new JLabel("Total Balance:");
		lblTotalInterstAmt.setBounds(7, 70, 151, 14);
		panel_WDB.add(lblTotalInterstAmt);

		textFieldTotalBalance = new JTextField();
		textFieldTotalBalance.setForeground(Color.RED);
		textFieldTotalBalance.setBounds(156, 64, 143, 20);
		panel_WDB.add(textFieldTotalBalance);
		textFieldTotalBalance.setEditable(false);
		textFieldTotalBalance.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldTotalBalance.setColumns(10);

		panel_NWDB = new JPanel();
		panel_NWDB.setLayout(null);
		panel_NWDB.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_NWDB.setBounds(843, 580, 309, 102);
		contentPane.add(panel_NWDB);

		lbltotalamount = new JLabel("Total Amount:");
		lbltotalamount.setBounds(38, 11, 261, 14);
		panel_NWDB.add(lbltotalamount);

		textFieldTotalAmtB = new JTextField();
		textFieldTotalAmtB.setForeground(Color.RED);
		textFieldTotalAmtB.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldTotalAmtB.setEditable(false);
		textFieldTotalAmtB.setColumns(10);
		textFieldTotalAmtB.setBounds(84, 58, 143, 20);
		panel_NWDB.add(textFieldTotalAmtB);

		textFieldtype = new JTextField();
		textFieldtype.setVisible(false);
		textFieldtype.setText("Saving");
		textFieldtype.setBounds(740, 149, 86, 20);
		contentPane.add(textFieldtype);
		textFieldtype.setColumns(10);

		textFieldOpnningAmt = new JTextField();
		textFieldOpnningAmt.setVisible(false);
		textFieldOpnningAmt.setBounds(961, 149, 86, 20);
		contentPane.add(textFieldOpnningAmt);
		textFieldOpnningAmt.setColumns(10);

		textFieldClosingAmt = new JTextField();
		textFieldClosingAmt.setVisible(false);
		textFieldClosingAmt.setText("0");
		textFieldClosingAmt.setColumns(10);
		textFieldClosingAmt.setBounds(1059, 149, 86, 20);
		contentPane.add(textFieldClosingAmt);
		
		textFieldSaving = new JTextField();
		textFieldSaving.setVisible(false);
		textFieldSaving.setText("0");
		textFieldSaving.setColumns(10);
		textFieldSaving.setBounds(850, 92, 86, 20);
		contentPane.add(textFieldSaving);
		
		textFieldCurrent = new JTextField();
		textFieldCurrent.setVisible(false);
		textFieldCurrent.setText("0");
		textFieldCurrent.setColumns(10);
		textFieldCurrent.setBounds(850, 119, 86, 20);
		contentPane.add(textFieldCurrent);
		
		textFieldLoan = new JTextField();
		textFieldLoan.setVisible(false);
		textFieldLoan.setText("0");
		textFieldLoan.setColumns(10);
		textFieldLoan.setBounds(851, 146, 86, 20);
		contentPane.add(textFieldLoan);
		
		textFieldFd = new JTextField();
		textFieldFd.setVisible(false);
		textFieldFd.setText("0");
		textFieldFd.setColumns(10);
		textFieldFd.setBounds(952, 91, 86, 20);
		contentPane.add(textFieldFd);
		
		textFieldRecurring = new JTextField();
		textFieldRecurring.setVisible(false);
		textFieldRecurring.setText("0");
		textFieldRecurring.setColumns(10);
		textFieldRecurring.setBounds(956, 122, 86, 20);
		contentPane.add(textFieldRecurring);
		
		textFieldShare = new JTextField();
		textFieldShare.setVisible(false);
		textFieldShare.setText("0");
		textFieldShare.setColumns(10);
		textFieldShare.setBounds(1054, 93, 86, 20);
		contentPane.add(textFieldShare);
		
		textFieldScheme = new JTextField();
		textFieldScheme.setVisible(false);
		textFieldScheme.setText("0");
		textFieldScheme.setColumns(10);
		textFieldScheme.setBounds(1052, 121, 86, 20);
		contentPane.add(textFieldScheme);
		
		textFieldExp = new JTextField();
		textFieldExp.setVisible(false);
		textFieldExp.setText("0");
		textFieldExp.setColumns(10);
		textFieldExp.setBounds(671, 91, 86, 20);
		contentPane.add(textFieldExp);
		
		textFieldEmp = new JTextField();
		textFieldEmp.setVisible(false);
		textFieldEmp.setText("0");
		textFieldEmp.setColumns(10);
		textFieldEmp.setBounds(760, 92, 86, 20);
		contentPane.add(textFieldEmp);
		
		textFieldAgent = new JTextField();
		textFieldAgent.setVisible(false);
		textFieldAgent.setText("0");
		textFieldAgent.setColumns(10);
		textFieldAgent.setBounds(747, 128, 86, 20);
		contentPane.add(textFieldAgent);
		
		textFieldallsumamt = new JTextField();
		textFieldallsumamt.setVisible(false);
		textFieldallsumamt.setText("0");
		textFieldallsumamt.setBounds(571, 99, 86, 20);
		contentPane.add(textFieldallsumamt);
		textFieldallsumamt.setColumns(10);
	}

	public void loandatashow() {

		try {
			conn = DBConnection.getConnection();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dt1 = dateChooserDayBook.getDate();
			String date = (String) sdf.format(dateChooserDayBook.getDate());
			String loqu = "SELECT * FROM banksystem.loantranscation where Date='" + date + "';";
			ps = conn.prepareStatement(loqu);
			rs = ps.executeQuery();

			while (rs.next()) {

				String data0 = rs.getString("Srnomaster");
				String data1 = rs.getString("Date");
				String data4 = rs.getString("Name");
				String data2 = rs.getString("AccountNo");
				double aamoount = 0;
				String firstamount = "select * from banksystem.loantranscation where AccountNo='" + data2 + "'";
				ps1 = conn.prepareStatement(firstamount);
				rs1 = ps1.executeQuery();
				if (rs1.first()) {
					aamoount = rs1.getDouble("Balance");
				}

				String data5 = rs.getString("TransactionParticulars");
				String data6 = rs.getString("TranBy");
				String data7 = rs.getString("chequeno");

				String data8 = df.format(aamoount);

				double Withdral = rs.getDouble("Withdral");
				String data9 = df.format(Withdral);
				double Deposite = rs.getDouble("Deposite");
				String data10 = df.format(Deposite);

				double inters = rs.getDouble("Balance");
				String data11 = df.format(inters);

				Object[] row = { Boolean.FALSE, data0, data1, data2, data4, data5, data6, data7, data8, data9, data10,
						data11 };
				model1 = (DefaultTableModel) tableLoan.getModel();
				model1.addRow(row);
				loanco();

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				rs1.close();
				ps.close();
				ps1.close();
				conn.close();
			} catch (Exception ew) {
				System.out.println(ew.getMessage());
			}
		}
	}

	public void savingdatashow() {

		try {
			conn = DBConnection.getConnection();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dt1 = dateChooserDayBook.getDate();
			String date = (String) sdf.format(dateChooserDayBook.getDate());
			String loqu = "SELECT * FROM banksystem.savingtranscation where Date='" + date + "';";
			ps = conn.prepareStatement(loqu);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data0 = rs.getString("SrnoMaster");
				String data1 = rs.getString("Date");
				String data3 = rs.getString("Name");
				String data2 = rs.getString("AccountNo");
				String data4 = rs.getString("TransactionParticulars");
				String data5 = rs.getString("tranby");
				String data6 = rs.getString("chequeno");
				double amount = rs.getDouble("Amount");
				String data7 = df.format(amount);

				double Widtral = rs.getDouble("Widtral");
				String data8 = df.format(Widtral);
				double Depotie = rs.getDouble("Depotie");
				String data9 = df.format(Depotie);

				double inters = rs.getDouble("Balance");
				String data10 = df.format(inters);

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10 };
				model1 = (DefaultTableModel) tableSaving.getModel();
				model1.addRow(row);
				savingco();

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
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

	public void currentdatashow() {

		try {
			conn = DBConnection.getConnection();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dt1 = dateChooserDayBook.getDate();
			String date = (String) sdf.format(dateChooserDayBook.getDate());
			String loqu = "SELECT * FROM banksystem.currenttranscation where Date='" + date + "';";
			ps = conn.prepareStatement(loqu);
			rs = ps.executeQuery();
			while (rs.next()) {

				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("Date");
				String data3 = rs.getString("Name");
				String data2 = rs.getString("AccountNo");
				String data4 = rs.getString("TransactionParticulars");
				String data5 = rs.getString("tranby");
				String data6 = rs.getString("chequeno");

				double amount = rs.getDouble("Amount");
				String data7 = df.format(amount);
				double Withral = rs.getDouble("Withral");
				String data8 = df.format(Withral);
				double Deposite = rs.getDouble("Deposite");
				String data9 = df.format(Deposite);

				double amount4 = rs.getDouble("Balance");
				String data10 = df.format(amount4);

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10 };
				model1 = (DefaultTableModel) tableCurrent.getModel();
				model1.addRow(row);
				currentco();

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
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

	public void selectra() {

		if (radioName.isSelected()) {

			textFieldSearch.setEnabled(true);
			dateChooserMinDate.setEnabled(false);
			dateChooserMaxDate.setEnabled(false);

		} else if (radioDate.isSelected()) {

			textFieldSearch.setEnabled(false);
			dateChooserMinDate.setEnabled(true);
			dateChooserMaxDate.setEnabled(true);

		} else {
			textFieldSearch.setEnabled(true);
			dateChooserMinDate.setEnabled(true);
			dateChooserMaxDate.setEnabled(true);

		}
	}

	public void filterloan(String quey) {
		try {
			TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model1);
			tableLoan.setRowSorter(tr);
			tableSaving.setRowSorter(tr);
			tableCurrent.setRowSorter(tr);
			tableCommisstion.setRowSorter(tr);
			tableEmployees.setRowSorter(tr);
			tableExpenditure.setRowSorter(tr);
			tableFD.setRowSorter(tr);
			tableReccring.setRowSorter(tr);
			tableScheme.setRowSorter(tr);
			tableShare.setRowSorter(tr);
			tr.setRowFilter(RowFilter.regexFilter(quey));
		} catch (Exception es) {
			System.out.println(es.getMessage());
		}

	}

	public void recrringdatashow() {

		try {
			conn = DBConnection.getConnection();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dt1 = dateChooserDayBook.getDate();
			String date = (String) sdf.format(dateChooserDayBook.getDate());

			String loqu = "SELECT * FROM banksystem.dailyrecurring where Date='" + date + "';";
			ps = conn.prepareStatement(loqu);
			rs = ps.executeQuery();
			while (rs.next()) {

				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("Date");
				String data2 = rs.getString("AccountNo");
				String data3 = rs.getString("CustomerName");
				String data4 = rs.getString("AgentName");

				double amout = rs.getDouble("Amount");
				String data5 = df.format(amout);
				double interst = rs.getDouble("Interst");
				String data6 = df.format(interst);
				double intamt = rs.getDouble("InterestAmt");
				String data7 = df.format(intamt);
				double mamount = rs.getDouble("MaturedAmt");
				String data8 = df.format(mamount);
				String data9 = rs.getString("Duration");
				String data10 = rs.getString("Days");
				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10 };
				model1 = (DefaultTableModel) tableReccring.getModel();
				model1.addRow(row);
				calRecrring();
				recurringco();

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
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

	public void fdtableshow() {

		try {
			conn = DBConnection.getConnection();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dt1 = dateChooserDayBook.getDate();
			String date = (String) sdf.format(dateChooserDayBook.getDate());
			String loqu = "SELECT * FROM banksystem.fd where UpdateDate='" + date + "';";
			ps = conn.prepareStatement(loqu);
			rs = ps.executeQuery();
			while (rs.next()) {

				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("Date");
				String data2 = rs.getString("UpdateDate");
				String data4 = rs.getString("Name");
				String data3 = rs.getString("AccountNumber");

				double amout = rs.getDouble("Amount");
				String data6 = df.format(amout);
				double interst = rs.getDouble("Interes");
				String data7 = df.format(interst);
				double intamt = rs.getDouble("IntsetAmt");
				String data8 = df.format(intamt);
				double mamount = rs.getDouble("Total");
				String data9 = df.format(mamount);
				double otheramt = rs.getDouble("OtherAmount");
				String data10 = df.format(otheramt);
				String data11 = rs.getString("Duration");
				String data12 = rs.getString("Days");
				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data6, data7, data8, data9, data10,
						data11, data12 };
				model1 = (DefaultTableModel) tableFD.getModel();
				model1.addRow(row);
				calfd();
				fdco();

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
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

	public void sharetableshow() {

		try {
			conn = DBConnection.getConnection();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dt1 = dateChooserDayBook.getDate();
			String date = (String) sdf.format(dateChooserDayBook.getDate());
			String loqu = "SELECT * FROM banksystem.shares where Date='" + date + "';";
			ps = conn.prepareStatement(loqu);
			rs = ps.executeQuery();
			while (rs.next()) {

				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("Date");
				String data3 = rs.getString("Name");
				String data2 = rs.getString("AccountNo");

				double amout = rs.getDouble("Amount");
				String data4 = df.format(amout);
				double interst = rs.getDouble("SchPer");
				String data5 = df.format(interst);
				double intamt = rs.getDouble("TotalQtyl");
				String data6 = df.format(intamt);
				double mamount = rs.getDouble("Total");
				String data7 = df.format(mamount);
				double otheramt = rs.getDouble("OtherAmount");
				String data8 = df.format(otheramt);

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8 };
				model1 = (DefaultTableModel) tableShare.getModel();
				model1.addRow(row);
				calshare();
				shareco();

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
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

	public void schemetableshow() {

		try {
			conn = DBConnection.getConnection();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dt1 = dateChooserDayBook.getDate();
			String date = (String) sdf.format(dateChooserDayBook.getDate());
			String loqu = "SELECT * FROM banksystem.schemea_c where Date='" + date + "';";
			ps = conn.prepareStatement(loqu);
			rs = ps.executeQuery();
			while (rs.next()) {

				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("Date");
				String data3 = rs.getString("Name");
				String data2 = rs.getString("AccountNo");
				String data4 = rs.getString("SchType");
				double amout = rs.getDouble("Amount");
				String data5 = df.format(amout);
				double interst = rs.getDouble("SchPer");
				String data6 = df.format(interst);
				double intamt = rs.getDouble("SchAmt");
				String data7 = df.format(intamt);
				double mamount = rs.getDouble("Total");
				String data8 = df.format(mamount);
				double otheramt = rs.getDouble("OtherAmount");
				String data9 = df.format(otheramt);

				String data10 = rs.getString("Duration");
				String data11 = rs.getString("Days");
				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11 };
				model1 = (DefaultTableModel) tableScheme.getModel();
				model1.addRow(row);
				calschem();
schemeco();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
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

	public void expdatashow() {

		try {
			conn = DBConnection.getConnection();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dt1 = dateChooserDayBook.getDate();
			String date = (String) sdf.format(dateChooserDayBook.getDate());
			String loqu = "SELECT * FROM banksystem.expenditure where Date='" + date + "';";
			ps = conn.prepareStatement(loqu);
			rs = ps.executeQuery();
			while (rs.next()) {

				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("Date");
				String data3 = rs.getString("Expfor");
				String data2 = rs.getString("AccountNo");

				double amout = rs.getDouble("Amount");
				String data4 = df.format(amout);

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4 };
				model1 = (DefaultTableModel) tableExpenditure.getModel();
				model1.addRow(row);
				calexp();
				expco();

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
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

	public void emptableshow() {

		try {
			conn = DBConnection.getConnection();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dt1 = dateChooserDayBook.getDate();
			String date = (String) sdf.format(dateChooserDayBook.getDate());
			String loqu = "SELECT * FROM banksystem.emptrancation where Date='" + date + "';";
			ps = conn.prepareStatement(loqu);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("Date");
				String data2 = rs.getString("AccountNo");
				String data3 = rs.getString("Name");

				double amout = rs.getDouble("AdvancePay");
				String data4 = df.format(amout);
				double intast = rs.getDouble("Salary");
				String data5 = df.format(intast);
				String data6 = rs.getString("Leaves");
				double amout1 = rs.getDouble("LeavesAmt");
				String data7 = df.format(amout1);
				double intast1 = rs.getDouble("TotalAmount");
				String data8 = df.format(intast1);
				String data9 = rs.getString("Mode");
				String data10 = rs.getString("ChequeNo");

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10 };
				model1 = (DefaultTableModel) tableEmployees.getModel();
				model1.addRow(row);
				calemp();
				empco();

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
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

	public void commisstiontableshow() {

		try {
			conn = DBConnection.getConnection();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dt1 = dateChooserDayBook.getDate();
			String date = (String) sdf.format(dateChooserDayBook.getDate());
			String loqu = "SELECT * FROM banksystem.commissiona_ctrancation where Date='" + date + "';";
			ps = conn.prepareStatement(loqu);
			rs = ps.executeQuery();
			while (rs.next()) {

				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("Date");
				String data2 = rs.getString("AccountNo");
				String data3 = rs.getString("Name");

				double amout = rs.getDouble("PaidAmount");
				String data4 = df.format(amout);
				double interst = rs.getDouble("AllCustomerAmount");
				String data5 = df.format(interst);

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5 };
				model1 = (DefaultTableModel) tableCommisstion.getModel();
				model1.addRow(row);
				calcomm();
				agentco();
				allcal();

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
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

	public void calsaving() {
		if (textFieldtype.getText().equals("Saving")) {

			panel_WDB.setVisible(true);
			panel_NWDB.setVisible(false);
			int rowsCount = tableSaving.getRowCount();
			double withdral = 0;
			double depostie = 0;
			double balance = 0;

			for (int i = 0; i < rowsCount; i++) {
				withdral = withdral + Double.valueOf(tableSaving.getValueAt(i, 9).toString());
				depostie = depostie + Double.valueOf(tableSaving.getValueAt(i, 10).toString());
				balance = balance + Double.valueOf(tableSaving.getValueAt(i, 11).toString());
			}
			textFieldTotalWithdral.setText(String.valueOf(df.format(withdral)));
			textFieldTotalDeposite.setText(String.valueOf(df.format(depostie)));
			textFieldTotalBalance.setText(String.valueOf(df.format(balance)));
		}

	}

	public void calcurrent() {
		if (textFieldtype.getText().equals("Current")) {

			panel_WDB.setVisible(true);
			panel_NWDB.setVisible(false);
			int rowsCount = tableCurrent.getRowCount();
			double withdral = 0;
			double depostie = 0;
			double balance = 0;

			for (int i = 0; i < rowsCount; i++) {
				withdral = withdral + Double.valueOf(tableCurrent.getValueAt(i, 9).toString());
				depostie = depostie + Double.valueOf(tableCurrent.getValueAt(i, 10).toString());
				balance = balance + Double.valueOf(tableCurrent.getValueAt(i, 11).toString());
			}
			textFieldTotalWithdral.setText(String.valueOf(df.format(withdral)));
			textFieldTotalDeposite.setText(String.valueOf(df.format(depostie)));
			textFieldTotalBalance.setText(String.valueOf(df.format(balance)));
		}
	}

	public void calloan() {
		if (textFieldtype.getText().equals("Loan")) {

			panel_WDB.setVisible(true);
			panel_NWDB.setVisible(false);
			int rowsCount = tableLoan.getRowCount();
			double withdral = 0;
			double depostie = 0;
			double balance = 0;

			for (int i = 0; i < rowsCount; i++) {
				withdral = withdral + Double.valueOf(tableLoan.getValueAt(i, 9).toString());
				depostie = depostie + Double.valueOf(tableLoan.getValueAt(i, 10).toString());
				balance = balance + Double.valueOf(tableLoan.getValueAt(i, 11).toString());
			}
			textFieldTotalWithdral.setText(String.valueOf(df.format(withdral)));
			textFieldTotalDeposite.setText(String.valueOf(df.format(depostie)));
			textFieldTotalBalance.setText(String.valueOf(df.format(balance)));
		}
	}

	public void calRecrring() {
		if (textFieldtype.getText().equals("Recurring")) {

			panel_WDB.setVisible(false);
			panel_NWDB.setVisible(true);
			int rowsCount = tableReccring.getRowCount();
			double sum = 0;

			for (int i = 0; i < rowsCount; i++) {
				sum = sum + Double.valueOf(tableReccring.getValueAt(i, 6).toString());
			}
			lbltotalamount.setText("Total Amount:");
			textFieldTotalAmtB.setText(String.valueOf(df.format(sum)));
		}

	}

	public void calfd() {
		if (textFieldtype.getText().equals("Fd")) {

			panel_WDB.setVisible(false);
			panel_NWDB.setVisible(true);
			int rowsCount = tableFD.getRowCount();
			double sum = 0;

			for (int i = 0; i < rowsCount; i++) {
				sum = sum + Double.valueOf(tableFD.getValueAt(i, 6).toString());
			}
			lbltotalamount.setText("Total Amount:");
			textFieldTotalAmtB.setText(String.valueOf(df.format(sum)));
		}

	}

	public void calshare() {
		if (textFieldtype.getText().equals("Share")) {

			panel_WDB.setVisible(false);
			panel_NWDB.setVisible(true);
			int rowsCount = tableShare.getRowCount();
			double sum = 0;

			for (int i = 0; i < rowsCount; i++) {
				sum = sum + Double.valueOf(tableShare.getValueAt(i, 6).toString());
			}
			lbltotalamount.setText("Total Amount:");
			textFieldTotalAmtB.setText(String.valueOf(df.format(sum)));
		}

	}

	public void calschem() {
		if (textFieldtype.getText().equals("Scheme")) {

			panel_WDB.setVisible(false);
			panel_NWDB.setVisible(true);
			int rowsCount = tableScheme.getRowCount();
			double sum = 0;

			for (int i = 0; i < rowsCount; i++) {
				sum = sum + Double.valueOf(tableScheme.getValueAt(i, 6).toString());
			}
			lbltotalamount.setText("Total Amount:");
			textFieldTotalAmtB.setText(String.valueOf(df.format(sum)));
		}

	}

	public void calemp() {
		if (textFieldtype.getText().equals("Emp")) {

			panel_WDB.setVisible(false);
			panel_NWDB.setVisible(true);
			int rowsCount = tableEmployees.getRowCount();
			double sum = 0;

			for (int i = 0; i < rowsCount; i++) {
				sum = sum + Double.valueOf(tableEmployees.getValueAt(i, 5).toString());
			}
			lbltotalamount.setText("Total Advanced Paid Amt:");
			textFieldTotalAmtB.setText(String.valueOf(df.format(sum)));
		}

	}

	public void calcomm() {
		if (textFieldtype.getText().equals("Comm")) {

			panel_WDB.setVisible(false);
			panel_NWDB.setVisible(true);
			int rowsCount = tableCommisstion.getRowCount();
			double sum = 0;

			for (int i = 0; i < rowsCount; i++) {
				sum = sum + Double.valueOf(tableCommisstion.getValueAt(i, 5).toString());
			}
			lbltotalamount.setText("Total Commition Paid Amt:");
			textFieldTotalAmtB.setText(String.valueOf(df.format(sum)));
		}

	}

	public void calexp() {
		if (textFieldtype.getText().equals("Exp")) {

			panel_WDB.setVisible(false);
			panel_NWDB.setVisible(true);
			int rowsCount = tableExpenditure.getRowCount();
			double sum = 0;

			for (int i = 0; i < rowsCount; i++) {
				sum = sum + Double.valueOf(tableExpenditure.getValueAt(i, 5).toString());
			}
			lbltotalamount.setText("Total  Amount:");
			textFieldTotalAmtB.setText(String.valueOf(df.format(sum)));
		}

	}

	public void savingco() {

		 double saving = 0;
		
		int rowsaving = tableSaving.getRowCount();

		for (int i = 0; i < rowsaving; i++) {
			saving = saving + Double.valueOf(tableSaving.getValueAt(i, 11).toString());

		}
		textFieldSaving.setText(String.valueOf(df.format(saving)));
		
	}
	public void currentco()
	{
	double current = 0;
		
		int rowcurrent = tableCurrent.getRowCount();
		for (int i = 0; i < rowcurrent; i++) {
			current = current + Double.valueOf(tableCurrent.getValueAt(i, 11).toString());
		}
		textFieldCurrent.setText(String.valueOf(df.format(current)));
	}
		
		
	public void loanco()
	{
		double loan = 0;
		
		int rowloan = tableLoan.getRowCount();
		for (int i = 0; i < rowloan; i++) {
			loan = loan + Double.valueOf(tableLoan.getValueAt(i, 10).toString());
		}
		textFieldLoan.setText(String.valueOf(df.format(loan)));
	}	
	

		
public void fdco()
{
	 
	 double fd = 0;
	 
		int rowfd = tableFD.getRowCount();

		for (int i = 0; i < rowfd; i++) {
			fd = fd + Double.valueOf(tableFD.getValueAt(i, 6).toString());
		}
		textFieldFd.setText(String.valueOf(df.format(fd)));
}
public void recurringco()
{
	double recurring = 0;
	
		int rowrecurring = tableReccring.getRowCount();

		for (int i = 0; i < rowrecurring; i++) {
			recurring = recurring + Double.valueOf(tableReccring.getValueAt(i, 6).toString());
		}
		textFieldRecurring.setText(String.valueOf(df.format(recurring)));
}
public void schemeco()
{
	 double scheme = 0;
	

		int rowscheme = tableScheme.getRowCount();

		for (int i = 0; i < rowscheme; i++) {
			scheme = scheme + Double.valueOf(tableScheme.getValueAt(i, 6).toString());
		}
		
		textFieldScheme.setText(String.valueOf(df.format(scheme)));
}

public void shareco()
{
	 double share = 0;
	
		int rowshare = tableShare.getRowCount();

		for (int i = 0; i < rowshare; i++) {
			share = share + Double.valueOf(tableShare.getValueAt(i, 8).toString());
		}
		
		textFieldShare.setText(String.valueOf(df.format(share)));
}

public void expco()
{
 double exp = 0;
	
	
		int rowexp = tableExpenditure.getRowCount();

		for (int i = 0; i < rowexp; i++) {
			exp = exp + Double.valueOf(tableExpenditure.getValueAt(i, 5).toString());
		}
		textFieldExp.setText(String.valueOf(df.format(exp)));
		
}
public void empco()
{

	 double emp = 0;
	
		int rowemp = tableEmployees.getRowCount();

		for (int i = 0; i < rowemp; i++) {
			emp = emp + Double.valueOf(tableEmployees.getValueAt(i, 5).toString());
		}
		textFieldEmp.setText(String.valueOf(df.format(emp)));
}
public void agentco()
{
	double agent = 0;
		int rowagent = tableCommisstion.getRowCount();

		for (int i = 0; i < rowagent; i++) {
			agent = agent + Double.valueOf(tableCommisstion.getValueAt(i, 5).toString());
		}
		textFieldAgent.setText(String.valueOf(df.format(agent)));

	}

public void allcal()
{
	double saving=Double.valueOf(textFieldSaving.getText());
	double current=Double.valueOf(textFieldCurrent.getText());
	double loam=Double.valueOf(textFieldLoan.getText());
	double recurrng=Double.valueOf(textFieldRecurring.getText());
	double fd=Double.valueOf(textFieldFd.getText());
	double scheme=Double.valueOf(textFieldScheme.getText());
	double share=Double.valueOf(textFieldShare.getText());
	double emp=Double.valueOf(textFieldEmp.getText());
	double agent=Double.valueOf(textFieldAgent.getText());
	double exp=Double.valueOf(textFieldExp.getText());
	double allcal=saving+current+loam+recurrng+fd+scheme+share;
	textFieldClosingAmt.setText(String.valueOf(df.format(allcal)));
	
}
public static void showoppningamt()
{
	ResultSet rslocal;
	try
	{
	conn=DBConnection.getConnection();
	int maxsono=0;
	String maxso="select max(Srno) from banksystem.openingandcolsingamt;";
	ps=conn.prepareStatement(maxso);
	rslocal=ps.executeQuery();
	while(rslocal.next())
	{
		maxsono=rslocal.getInt(1)+1;
		
	}
	
	rslocal.close();
	ps.close();
	
	String allsum="insert into banksystem.openingandcolsingamt values(?,?,?,?);";
	ps1=conn.prepareStatement(allsum);
	ps1.setInt(1, maxsono);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	dt1 = dateChooserDayBook.getDate();
	String date = (String) sdf.format(dateChooserDayBook.getDate());
	java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
	ps1.setDate(2, (java.sql.Date) d);
	ps1.setDate(3, (java.sql.Date) d);
	ps1.setDouble(4, Double.valueOf(textFieldallsumamt.getText()));
	ps1.executeUpdate();
	ps1.close();
	conn.close();
	
	}
	catch(Exception ew)
	{
		System.out.println(ew.getMessage());
	}
	

}



public void oppningamtsn()
{
	try
	{
	conn=DBConnection.getConnection();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	dt1 = dateChooserDayBook.getDate();
	String date = (String) sdf.format(dateChooserDayBook.getDate());
	java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
	
	Calendar cal = Calendar.getInstance();
	cal.setTime(d);
	cal.add(Calendar.DATE, -1);
	Date dateBefore30Days = cal.getTime();
	
	
	int srno=0;
	String data=null;
	double opnningamt=0;
	String currentdate=(String)sdf.format(dateBefore30Days).toString();
	
	String datashow="select * from banksystem.openingandcolsingamt where UpdateDate<'"+date+"';";
	ps=conn.prepareStatement(datashow);
	rs=ps.executeQuery();
	while(rs.next())
	{
		srno=rs.getInt("Srno");
		data=rs.getString("UpdateDate");
		opnningamt=rs.getDouble("opningamt");
		
	}
	textFieldOpnningAmt.setText(String.valueOf(df.format(opnningamt)));
	textFieldallsumamt.setText(String.valueOf(df.format(opnningamt+Double.valueOf(textFieldClosingAmt.getText()))));
	
	}
	catch(Exception ew)
	{
		System.out.println(ew.getMessage());
	}
	
	
}
}
