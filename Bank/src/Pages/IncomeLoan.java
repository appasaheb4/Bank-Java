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

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.BevelBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JRadioButton;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;

public class IncomeLoan extends JFrame {

	private JPanel contentPane;
	final private JTable tableIncomeLoan;
	public DefaultTableModel model1;

	private JTextField textFieldSrNo;
	public JTextField textFieldMasterSrno;
	public Connection conn;
	public PreparedStatement ps;
	public String val1;
	public Statement st;
	public JDateChooser dateChooserloangpage;
	java.util.Date dt1, dt2;
	public ResultSet rs;
	public JTextField textFieldTotalIntsert;
	public JTextField textFieldTotalAmount;
	private JTextField textFieldSearch;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JRadioButton rdbtnName;
	public JRadioButton rdbtnDate;
	public JRadioButton rdbtnBoth;
	public JDateChooser dateChooserMin;
	public JDateChooser dateChooserMax;
	public static boolean state = true;
	private boolean DEBUG = false;
	private JTextField textFieldInterstallamt;
	private JTextField textFieldSum;
	public DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IncomeLoan frame = new IncomeLoan();
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
	public IncomeLoan() {
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
				int rowsCount = tableIncomeLoan.getRowCount();
				double sum = 0;
				double amttotal = 0;
				for (int i = 0; i < rowsCount; i++) {
					sum = sum + Double.valueOf(tableIncomeLoan.getValueAt(i, 4).toString());
					amttotal = amttotal + Double.valueOf(tableIncomeLoan.getValueAt(i, 5).toString());
				}
				textFieldTotalIntsert.setText(String.valueOf(df.format(sum)));
				textFieldTotalAmount.setText(String.valueOf(df.format(amttotal)));
				intesrtamtmin();

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

		JLabel lblIncomeLoan = new JLabel("Income Loan");
		lblIncomeLoan.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIncomeLoan.setBounds(22, 11, 276, 42);
		panel.add(lblIncomeLoan);

		Date date = new Date();
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setBounds(844, 45, 196, 20);
		dateChooser.setDate(date);
		panel.add(dateChooser);

		JLabel label = new JLabel("Date:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(753, 45, 81, 17);
		panel.add(label);

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
		button.setIcon(new ImageIcon(IncomeLoan.class.getResource("/ImageButtonIcon/clac.png")));
		button.setOpaque(false);
		button.setForeground(Color.RED);
		button.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBounds(1080, 10, 64, 55);
		panel.add(button);

		Date date1 = new Date();

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 217, 1134, 307);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1114, 285);
		panel_1.add(scrollPane);

		tableIncomeLoan = new JTable();
		tableIncomeLoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableIncomeLoan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableIncomeLoan.setFillsViewportHeight(true);

		if (DEBUG) {
			tableIncomeLoan.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {

					printDebugData(tableIncomeLoan);
				}
			});
		}
		tableIncomeLoan.addMouseListener(new MouseAdapter() {
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
				int rows = tableIncomeLoan.getRowCount() - 1;

				for (int i = 0; i < tableIncomeLoan.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableIncomeLoan.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableIncomeLoan.getSelectedRow();
						int col = tableIncomeLoan.getSelectedColumn();
						tableIncomeLoan.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableIncomeLoan.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableIncomeLoan.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableIncomeLoan.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableIncomeLoan.setValueAt((Object) s, 0, 0);
							tableIncomeLoan.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableIncomeLoan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tableIncomeLoan.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Select", "SrNo", "Date", "Name", "Interest", "Interst Amt" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableIncomeLoan.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableIncomeLoan.getColumnModel().getColumn(0).setMinWidth(50);
		tableIncomeLoan.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableIncomeLoan.getColumnModel().getColumn(1).setMinWidth(50);
		tableIncomeLoan.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableIncomeLoan.getColumnModel().getColumn(2).setMinWidth(80);
		tableIncomeLoan.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableIncomeLoan.getColumnModel().getColumn(3).setMinWidth(150);
		tableIncomeLoan.getColumnModel().getColumn(4).setPreferredWidth(50);
		tableIncomeLoan.getColumnModel().getColumn(4).setMinWidth(50);
		tableIncomeLoan.getColumnModel().getColumn(5).setPreferredWidth(80);
		tableIncomeLoan.getColumnModel().getColumn(5).setMinWidth(80);
		tableIncomeLoan.getColumnModel().getColumn(5).setMaxWidth(100);
		showdata();
		// setSelectionEnableOrDisable(tableIncomeLoan);
		scrollPane.setViewportView(tableIncomeLoan);

		JLabel lblNewLabel = new JLabel("Total Interest:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(461, 549, 117, 17);
		contentPane.add(lblNewLabel);

		JLabel lblTotalAmount = new JLabel("Total Amount:");
		lblTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalAmount.setBounds(461, 586, 117, 17);
		contentPane.add(lblTotalAmount);

		textFieldTotalIntsert = new JTextField();
		textFieldTotalIntsert.setForeground(Color.RED);
		textFieldTotalIntsert.setEditable(false);
		textFieldTotalIntsert.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldTotalIntsert.setBounds(580, 546, 153, 23);
		contentPane.add(textFieldTotalIntsert);
		textFieldTotalIntsert.setColumns(10);

		textFieldTotalAmount = new JTextField();
		textFieldTotalAmount.setForeground(Color.RED);
		textFieldTotalAmount.setEditable(false);
		textFieldTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldTotalAmount.setColumns(10);
		textFieldTotalAmount.setBounds(580, 582, 153, 23);
		contentPane.add(textFieldTotalAmount);

		JLabel lblNewLabel_1 = new JLabel("Search:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(49, 178, 52, 17);
		contentPane.add(lblNewLabel_1);

		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String quey = textFieldSearch.getText().toLowerCase();
				filter(quey);
			}
		});
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSearch.setBounds(111, 178, 230, 20);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);

		JLabel lblSearchFor = new JLabel("Search for:");
		lblSearchFor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchFor.setBounds(49, 136, 96, 17);
		contentPane.add(lblSearchFor);

		rdbtnName = new JRadioButton("Name");
		rdbtnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectmode();
			}
		});
		rdbtnName.setSelected(true);
		buttonGroup.add(rdbtnName);
		rdbtnName.setBounds(133, 135, 68, 23);
		contentPane.add(rdbtnName);

		rdbtnDate = new JRadioButton("Date");
		rdbtnDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectmode();
			}
		});
		buttonGroup.add(rdbtnDate);
		rdbtnDate.setBounds(203, 135, 68, 23);
		contentPane.add(rdbtnDate);

		rdbtnBoth = new JRadioButton("Both");
		rdbtnBoth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectmode();
			}
		});
		buttonGroup.add(rdbtnBoth);
		rdbtnBoth.setBounds(273, 135, 68, 23);
		contentPane.add(rdbtnBoth);

		JLabel label_1 = new JLabel("Date:");
		label_1.setBounds(351, 178, 52, 17);
		contentPane.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		dateChooserMin = new JDateChooser();
		dateChooserMin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String quey = dateChooserMin.getDate().toString().toLowerCase();
				filter(quey);
			}
		});
		dateChooserMin.setEnabled(false);
		dateChooserMin.setBounds(396, 178, 105, 20);
		contentPane.add(dateChooserMin);
		dateChooserMin.setDateFormatString("dd-MM-yyyy");
		dateChooserMin.setDate(date);

		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTo.setBounds(511, 181, 31, 17);
		contentPane.add(lblTo);

		dateChooserMax = new JDateChooser();
		dateChooserMax.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				String query = dateChooserMax.getDate().toString().toLowerCase();
				filter(query);
			}
		});
		dateChooserMax.setEnabled(false);
		dateChooserMax.getCalendarButton().setLocation(84, 182);
		dateChooserMax.setDateFormatString("dd-MM-yyyy");
		dateChooserMax.setBounds(536, 178, 105, 20);
		dateChooserMax.setDate(date);
		contentPane.add(dateChooserMax);

		JButton button_1 = new JButton("Exit");
		button_1.setIcon(new ImageIcon(IncomeLoan.class.getResource("/ImageButtonIcon/Exit.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Welcome.frame.enable();
			}
		});
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBounds(10, 565, 115, 38);
		contentPane.add(button_1);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model1 = (DefaultTableModel) tableIncomeLoan.getModel();
				model1.setRowCount(0);
				try {
					conn = DBConnection.getConnection();

					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					dt1 = dateChooserMin.getDate();
					String datemin = (String) sdf.format(dateChooserMin.getDate());
					dt1 = dateChooserMax.getDate();
					String datemax = (String) sdf.format(dateChooserMax.getDate());

					String loqu = "SELECT * FROM banksystem.loan where Date between '" + datemin + "' AND '" + datemax
							+ "';";
					ps = conn.prepareStatement(loqu);
					rs = ps.executeQuery();
					while (rs.next()) {
						String data0 = rs.getString("Srnomaster");
						String data1 = rs.getString("date");
						String data2 = rs.getString("Name");
						double data3 = rs.getDouble("Interst");
						double data4 = rs.getDouble("Total");

						Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4 };
						model1 = (DefaultTableModel) tableIncomeLoan.getModel();
						model1.addRow(row);

					}

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
		btnSearch.setHorizontalAlignment(SwingConstants.LEADING);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSearch.setBounds(654, 174, 115, 28);
		contentPane.add(btnSearch);

		textFieldInterstallamt = new JTextField();
		textFieldInterstallamt.setForeground(Color.RED);
		textFieldInterstallamt.setEditable(false);
		textFieldInterstallamt.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldInterstallamt.setColumns(10);
		textFieldInterstallamt.setBounds(761, 582, 153, 23);
		contentPane.add(textFieldInterstallamt);

		JLabel lblNewLabel_2 = new JLabel("-");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(740, 588, 30, 14);
		contentPane.add(lblNewLabel_2);

		JLabel label_2 = new JLabel("=");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_2.setBounds(916, 587, 30, 14);
		contentPane.add(label_2);

		textFieldSum = new JTextField();
		textFieldSum.setForeground(Color.RED);
		textFieldSum.setEditable(false);
		textFieldSum.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldSum.setColumns(10);
		textFieldSum.setBounds(938, 582, 153, 23);
		contentPane.add(textFieldSum);
	}

	public void showdata() {
		try {
			conn = DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.loan order by Srnomaster;";
			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data0 = rs.getString("Srnomaster");
				String data1 = rs.getString("date");
				String data2 = rs.getString("Name");

				double interst = rs.getDouble("Interst");
				String data3 = df.format(interst);
				double interamt = rs.getDouble("InstersAmt");
				String data4 = df.format(interamt);

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4 };
				model1 = (DefaultTableModel) tableIncomeLoan.getModel();
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

	public void selectmode() {
		if (rdbtnName.isSelected()) {
			textFieldSearch.setEnabled(true);
			dateChooserMin.setEnabled(false);
			dateChooserMax.setEnabled(false);
		} else if (rdbtnDate.isSelected()) {
			textFieldSearch.setEnabled(false);
			dateChooserMin.setEnabled(true);
			dateChooserMax.setEnabled(true);

		} else {

			textFieldSearch.setEnabled(true);
			dateChooserMin.setEnabled(true);
			dateChooserMax.setEnabled(true);

		}
	}

	public void filter(String quey) {
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model1);
		tableIncomeLoan.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(quey));
	}

	public void intesrtamtmin() {
		double loaninserstamt = 0;
		double savingintestamt = 0;
		double recurringintesrtamt = 0;
		double fdinterstamt = 0;
		double schemesinterstamt = 0;
		double expentureinterstamt = 0;
		double allsum = 0;

		try {
			conn = DBConnection.getConnection();
			String loaninters = "select sum(InstersAmt) from banksystem.loan;";
			ps = conn.prepareStatement(loaninters);
			rs = ps.executeQuery();
			while (rs.next()) {
				loaninserstamt = rs.getDouble(1);
			}

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
		try {

			conn = DBConnection.getConnection();
			String loaninters = "select sum(InterestPer) from banksystem.saving;";
			ps = conn.prepareStatement(loaninters);
			rs = ps.executeQuery();
			while (rs.next()) {
				savingintestamt = rs.getDouble(1);
			}

		} catch (Exception e2) {
			System.out.println(e2.getMessage());
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
			String loaninters = "select sum(IntrestAmt) from banksystem.recurring;";
			ps = conn.prepareStatement(loaninters);
			rs = ps.executeQuery();
			while (rs.next()) {
				recurringintesrtamt = rs.getDouble(1);
			}

		} catch (Exception e3) {
			System.out.println(e3.getMessage());
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
			String loaninters = "select sum(IntsetAmt) from banksystem.fd;";
			ps = conn.prepareStatement(loaninters);
			rs = ps.executeQuery();
			while (rs.next()) {
				fdinterstamt = rs.getDouble(1);
			}

		} catch (Exception e4) {
			System.out.println(e4.getMessage());
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
			String loaninters = "select sum(SchPer) from banksystem.schemea_c;";
			ps = conn.prepareStatement(loaninters);
			rs = ps.executeQuery();
			while (rs.next()) {
				schemesinterstamt = rs.getDouble(1);
			}

		} catch (Exception e5) {
			System.out.println(e5.getMessage());
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
			String loaninters = "select sum(Amount) from banksystem.expenditure;";
			ps = conn.prepareStatement(loaninters);
			rs = ps.executeQuery();
			while (rs.next()) {
				expentureinterstamt = rs.getDouble(1);
			}

		} catch (Exception e6) {
			System.out.println(e6.getMessage());
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
			allsum = (savingintestamt + recurringintesrtamt + fdinterstamt + schemesinterstamt + expentureinterstamt);
			textFieldInterstallamt.setText(String.valueOf(df.format(allsum)));
			double amt = Double.valueOf(textFieldTotalAmount.getText());

			double sumsum = Double.valueOf(textFieldTotalAmount.getText())
					- Double.valueOf(textFieldInterstallamt.getText());
			textFieldSum.setText(String.valueOf(df.format(sumsum)));

		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		}
	}

	private void printDebugData(JTable table) {

		int numRows = table.getRowCount();

		int numCols = table.getColumnCount();
		javax.swing.table.TableModel model = table.getModel();

		System.out.println("Value of data: ");
		for (int i = 0; i < numRows; i++) {
			System.out.print("    row " + i + ":");
			for (int j = 0; j < numCols; j++) {
				System.out.print("  " + model.getValueAt(i, j));
			}
			System.out.println();
		}
		
	}
}