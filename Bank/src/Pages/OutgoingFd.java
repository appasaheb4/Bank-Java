package Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;

import CLASS.CalculatorGUI;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
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
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;

public class OutgoingFd extends JFrame {

	private JPanel contentPane;
	private JTable tableIncomeLoan;
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
	public JDateChooser dateChooserSearch1;
	public JDateChooser dateChooserSearch2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutgoingFd frame = new OutgoingFd();
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
	public OutgoingFd() {
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

				}
				textFieldTotalIntsert.setText(String.valueOf(sum));

			}
		});

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1157, 76);
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

		JLabel lblIncomeLoan = new JLabel("Outgoing Fd");
		lblIncomeLoan.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIncomeLoan.setBounds(22, 11, 276, 42);
		panel.add(lblIncomeLoan);

		JLabel label = new JLabel("Date:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(769, 49, 81, 17);
		panel.add(label);

		Date date4 = new Date();
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(860, 46, 196, 20);
		dateChooser.setDate(date4);
		panel.add(dateChooser);

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
		button.setIcon(new ImageIcon(OutgoingFd.class.getResource("/ImageButtonIcon/clac.png")));
		button.setOpaque(false);
		button.setForeground(Color.RED);
		button.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBounds(1084, 12, 64, 55);
		panel.add(button);

		Date date = new Date();

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 217, 1134, 307);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1114, 285);
		panel_1.add(scrollPane);

		tableIncomeLoan = new JTable();
		tableIncomeLoan.addMouseListener(new MouseAdapter() {
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
		tableIncomeLoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableIncomeLoan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableIncomeLoan.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Select", "SrNo", "Date", "Name", "Loan Interest", "Amount" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Double.class,
					Double.class };

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
		tableIncomeLoan.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableIncomeLoan.getColumnModel().getColumn(4).setMinWidth(80);
		tableIncomeLoan.getColumnModel().getColumn(5).setPreferredWidth(0);
		tableIncomeLoan.getColumnModel().getColumn(5).setMinWidth(0);
		tableIncomeLoan.getColumnModel().getColumn(5).setMaxWidth(0);
		showdata();
		scrollPane.setViewportView(tableIncomeLoan);

		JLabel lblNewLabel = new JLabel("Total Interest:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(656, 535, 117, 17);
		contentPane.add(lblNewLabel);

		JLabel lblTotalAmount = new JLabel("Total Amount:");
		lblTotalAmount.setVisible(false);
		lblTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalAmount.setBounds(656, 570, 117, 17);
		contentPane.add(lblTotalAmount);

		textFieldTotalIntsert = new JTextField();
		textFieldTotalIntsert.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldTotalIntsert.setBounds(783, 535, 153, 23);
		contentPane.add(textFieldTotalIntsert);
		textFieldTotalIntsert.setColumns(10);

		textFieldTotalAmount = new JTextField();
		textFieldTotalAmount.setVisible(false);
		textFieldTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldTotalAmount.setColumns(10);
		textFieldTotalAmount.setBounds(783, 570, 153, 23);
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

		dateChooserSearch1 = new JDateChooser();
		dateChooserSearch1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String quey = dateChooserSearch1.getDate().toString().toLowerCase();
				filter(quey);
			}
		});
		dateChooserSearch1.setEnabled(false);
		dateChooserSearch1.setBounds(396, 178, 105, 20);
		contentPane.add(dateChooserSearch1);
		dateChooserSearch1.setDateFormatString("dd-MM-yyyy");
		dateChooserSearch1.setDate(date);

		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTo.setBounds(511, 181, 31, 17);
		contentPane.add(lblTo);

		dateChooserSearch2 = new JDateChooser();
		dateChooserSearch2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				String query = dateChooserSearch2.getDate().toString().toLowerCase();
				filter(query);
			}
		});
		dateChooserSearch2.setEnabled(false);
		dateChooserSearch2.getCalendarButton().setLocation(84, 182);
		dateChooserSearch2.setDateFormatString("dd-MM-yyyy");
		dateChooserSearch2.setBounds(536, 178, 105, 20);
		dateChooserSearch2.setDate(date);
		contentPane.add(dateChooserSearch2);

		JButton button_1 = new JButton("Exit");
		button_1.setIcon(new ImageIcon(OutgoingFd.class.getResource("/ImageButtonIcon/Exit.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Welcome.frame.enable();
			}
		});
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBounds(22, 559, 115, 38);
		contentPane.add(button_1);

		JButton btnSearch = new JButton("Search");
		btnSearch.setHorizontalAlignment(SwingConstants.LEADING);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSearch.setBounds(654, 174, 115, 28);
		contentPane.add(btnSearch);
	}

	public void showdata() {
		try {
			conn = DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.fd order by SrNoMaster;";
			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("Date");
				String data2 = rs.getString("Name");
				double data3 = rs.getDouble("Interes");

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3 };
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
			dateChooserSearch1.setEnabled(false);
			dateChooserSearch2.setEnabled(false);
		} else if (rdbtnDate.isSelected()) {
			textFieldSearch.setEnabled(false);
			dateChooserSearch1.setEnabled(true);
			dateChooserSearch2.setEnabled(true);

		} else {

			textFieldSearch.setEnabled(true);
			dateChooserSearch1.setEnabled(true);
			dateChooserSearch2.setEnabled(true);

		}
	}

	public void filter(String quey) {
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model1);
		tableIncomeLoan.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(quey));
	}
}
