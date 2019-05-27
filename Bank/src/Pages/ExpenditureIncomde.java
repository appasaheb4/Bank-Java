package Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;

import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import java.awt.Cursor;
import javax.swing.ScrollPaneConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ExpenditureIncomde extends JFrame {

	private JPanel contentPane;
	private JTable tableExpentureIncome;
	private JTextField textFieldSearch;
	public JRadioButton radioName;
	public JRadioButton radioDate;
	public JRadioButton radioBoth;
	public JDateChooser dateChooserMinDate;
	public JDateChooser dateChooserMaxDate;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JDateChooser dateChooserExpentureIncome;
	java.util.Date dt1, dt2;

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
	public DecimalFormat df = new DecimalFormat("#.##");
	private JTextField textFieldTotalAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExpenditureIncomde frame = new ExpenditureIncomde();
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
	public ExpenditureIncomde() {
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
				textFieldSearch.requestFocus();
				cal();
			}
		});

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1154, 109);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Expenditure Income");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 32, 223, 66);
		panel.add(lblNewLabel);

		JLabel label_4 = new JLabel("Date:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(851, 78, 81, 17);
		panel.add(label_4);

		Date date = new Date();
		dateChooserExpentureIncome = new JDateChooser();
		dateChooserExpentureIncome.setDateFormatString("dd-MM-yyyy");
		dateChooserExpentureIncome.setBounds(948, 78, 196, 20);
		dateChooserExpentureIncome.setDate(date);
		panel.add(dateChooserExpentureIncome);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 228, 1154, 385);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 1134, 363);
		panel_1.add(scrollPane);

		tableExpentureIncome = new JTable();
		tableExpentureIncome.addMouseListener(new MouseAdapter() {
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
				int rows = tableExpentureIncome.getRowCount() - 1;

				for (int i = 0; i < tableExpentureIncome.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableExpentureIncome.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableExpentureIncome.getSelectedRow();
						int col = tableExpentureIncome.getSelectedColumn();
						tableExpentureIncome.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableExpentureIncome.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableExpentureIncome.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableExpentureIncome.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableExpentureIncome.setValueAt((Object) s, 0, 0);
							tableExpentureIncome.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableExpentureIncome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableExpentureIncome.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableExpentureIncome.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Select", "Sr No", "Date", "Name", "Account No", "Amount", "Note" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableExpentureIncome.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableExpentureIncome.getColumnModel().getColumn(0).setMinWidth(50);
		tableExpentureIncome.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableExpentureIncome.getColumnModel().getColumn(1).setMinWidth(50);
		tableExpentureIncome.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableExpentureIncome.getColumnModel().getColumn(2).setMinWidth(80);
		tableExpentureIncome.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableExpentureIncome.getColumnModel().getColumn(3).setMinWidth(150);
		tableExpentureIncome.getColumnModel().getColumn(5).setPreferredWidth(80);
		tableExpentureIncome.getColumnModel().getColumn(5).setMinWidth(80);
		tableExpentureIncome.getColumnModel().getColumn(6).setPreferredWidth(150);
		tableExpentureIncome.getColumnModel().getColumn(6).setMinWidth(150);
		expincome();
		scrollPane.setViewportView(tableExpentureIncome);

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

		JLabel label = new JLabel("Search for:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(22, 145, 96, 17);
		contentPane.add(label);

		radioName = new JRadioButton("Name");
		buttonGroup.add(radioName);
		radioName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				select();

			}
		});
		radioName.setSelected(true);
		radioName.setBounds(106, 144, 68, 23);
		contentPane.add(radioName);

		radioDate = new JRadioButton("Date");
		buttonGroup.add(radioDate);
		radioDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select();
			}
		});
		radioDate.setBounds(176, 144, 68, 23);
		contentPane.add(radioDate);

		radioBoth = new JRadioButton("Both");
		buttonGroup.add(radioBoth);
		radioBoth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select();
			}
		});
		radioBoth.setBounds(246, 144, 68, 23);
		contentPane.add(radioBoth);

		JLabel label_1 = new JLabel("Search:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(22, 177, 52, 17);
		contentPane.add(label_1);

		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String quey = textFieldSearch.getText().toLowerCase();
				filter(quey);
			}
		});
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSearch.setColumns(10);
		textFieldSearch.setBounds(84, 177, 230, 20);
		contentPane.add(textFieldSearch);

		JLabel label_2 = new JLabel("Date:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(324, 177, 52, 17);
		contentPane.add(label_2);

		dateChooserMinDate = new JDateChooser();
		dateChooserMinDate.setEnabled(false);
		dateChooserMinDate.setDateFormatString("dd-MM-yyyy");
		dateChooserMinDate.setBounds(369, 177, 105, 20);
		dateChooserMinDate.setDate(date);
		contentPane.add(dateChooserMinDate);

		JLabel label_3 = new JLabel("To");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(484, 180, 31, 17);
		contentPane.add(label_3);

		dateChooserMaxDate = new JDateChooser();
		dateChooserMaxDate.setEnabled(false);
		dateChooserMaxDate.setDateFormatString("dd-MM-yyyy");
		dateChooserMaxDate.setBounds(509, 177, 105, 20);
		dateChooserMaxDate.setDate(date);
		contentPane.add(dateChooserMaxDate);

		JButton button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model1 = (DefaultTableModel) tableExpentureIncome.getModel();
				model1.setRowCount(0);
				try {
					conn = DBConnection.getConnection();

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserMinDate.getDate();
					String datemin = (String) sdf.format(dateChooserMinDate.getDate());
					dt1 = dateChooserMaxDate.getDate();
					String datemax = (String) sdf.format(dateChooserMaxDate.getDate());

					String loqu = "SELECT * FROM banksystem.expenditureincome where Date between '" + datemin
							+ "' AND '" + datemax + "';";
					ps = conn.prepareStatement(loqu);
					rs = ps.executeQuery();
					while (rs.next()) {

						String data0 = rs.getString("SrNoMaster");
						String data1 = rs.getString("Date");
						String data2 = rs.getString("Name");
						String data3 = rs.getString("AccountNo");
						double amount = rs.getDouble("Amount");
						String data4 = df.format(amount);
						String data5 = rs.getString("Note");

						Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5 };
						model1 = (DefaultTableModel) tableExpentureIncome.getModel();
						model1.addRow(row);

					}

					cal();

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
		button.setBounds(627, 173, 115, 28);
		contentPane.add(button);

		JLabel lblNewLabel_1 = new JLabel("Total Amount:");
		lblNewLabel_1.setBounds(842, 628, 90, 14);
		contentPane.add(lblNewLabel_1);

		textFieldTotalAmount = new JTextField();
		textFieldTotalAmount.setEditable(false);
		textFieldTotalAmount.setBounds(942, 624, 202, 23);
		contentPane.add(textFieldTotalAmount);
		textFieldTotalAmount.setColumns(10);

		JButton button_1 = new JButton("Report");
		button_1.setIcon(new ImageIcon(ExpenditureIncomde.class.getResource("/ImageButtonIcon/Report4d.png")));
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBounds(22, 655, 141, 38);
		contentPane.add(button_1);

		JButton button_2 = new JButton("Exit");
		button_2.setIcon(new ImageIcon(ExpenditureIncomde.class.getResource("/ImageButtonIcon/Exit.png")));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Welcome.frame.enable();
			}
		});
		button_2.setHorizontalAlignment(SwingConstants.LEADING);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_2.setBounds(176, 655, 115, 38);
		contentPane.add(button_2);
	}

	public void select() {
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

	public void expincome() {

		try {
			conn = DBConnection.getConnection();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dt1 = dateChooserExpentureIncome.getDate();
			String date = (String) sdf.format(dateChooserExpentureIncome.getDate());
			String loqu = "SELECT * FROM banksystem.expenditureincome where Date='" + date + "';";
			ps = conn.prepareStatement(loqu);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("Date");
				String data2 = rs.getString("Name");
				String data3 = rs.getString("AccountNo");
				double amount = rs.getDouble("Amount");
				String data4 = df.format(amount);
				String data5 = rs.getString("Note");

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5 };
				model1 = (DefaultTableModel) tableExpentureIncome.getModel();
				model1.addRow(row);

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

	public void filter(String quey) {
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model1);
		tableExpentureIncome.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(quey));
	}

	public void cal() {
		int rowsCount = tableExpentureIncome.getRowCount();
		double sum = 0;
		double amttotal = 0;
		for (int i = 0; i < rowsCount; i++) {
			sum = sum + Double.valueOf(tableExpentureIncome.getValueAt(i, 5).toString());

		}
		textFieldTotalAmount.setText(String.valueOf(sum));
	}
}
