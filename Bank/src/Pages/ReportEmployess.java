package Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;


import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Cursor;
import javax.swing.ScrollPaneConstants;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;
import javax.swing.JComponent;

public class ReportEmployess extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearch;
	private JTable tableEmp;

	public static Connection conn;
	public static PreparedStatement ps;
	public static ResultSet rs;
	public JRadioButton radioName;
	public JRadioButton radioDate;
	public JRadioButton radioBoth;
	public JDateChooser dateChooserMinDate;
	public JDateChooser dateChooserMaxDate;
	public java.util.Date dt1;
	public java.util.Date dt2;
	public DecimalFormat df = new DecimalFormat("#.##");
	public String val1;
	public DefaultTableModel model1;
	public JButton btnViewTransaction;

	public static String name;

	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JCheckBox checkBoxseall;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportEmployess frame = new ReportEmployess();
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
	public ReportEmployess() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				textFieldSearch.requestFocus();
			}
		});
		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1154, 79);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Employee Report");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(39, 14, 256, 54);
		panel.add(lblNewLabel);

		JLabel label_4 = new JLabel("Date:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(844, 48, 81, 17);
		panel.add(label_4);

		Date date = new Date();
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(948, 48, 196, 20);
		dateChooser.setDate(date);
		panel.add(dateChooser);
		
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
		label.setBounds(10, 101, 96, 17);
		contentPane.add(label);

		radioName = new JRadioButton("Name");
		radioName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectra();
			}
		});
		buttonGroup.add(radioName);
		radioName.setSelected(true);
		radioName.setBounds(94, 100, 68, 23);
		contentPane.add(radioName);

		radioDate = new JRadioButton("Date");
		radioDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectra();
			}
		});
		buttonGroup.add(radioDate);
		radioDate.setBounds(164, 100, 68, 23);
		contentPane.add(radioDate);

		radioBoth = new JRadioButton("Both");
		radioBoth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectra();
			}
		});
		buttonGroup.add(radioBoth);
		radioBoth.setBounds(234, 100, 68, 23);
		contentPane.add(radioBoth);

		JLabel label_1 = new JLabel("Search:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(13, 133, 52, 17);
		contentPane.add(label_1);

		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String quey = textFieldSearch.getText().toLowerCase();
				filterloan(quey);
			}
		});
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSearch.setColumns(10);
		textFieldSearch.setBounds(75, 133, 230, 20);
		contentPane.add(textFieldSearch);

		JLabel label_2 = new JLabel("Date:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(315, 133, 52, 17);
		contentPane.add(label_2);

		Date date2 = new Date();
		dateChooserMinDate = new JDateChooser();
		dateChooserMinDate.setEnabled(false);
		dateChooserMinDate.setDateFormatString("yyyy-MM-dd");
		dateChooserMinDate.setBounds(360, 133, 105, 20);
		dateChooserMinDate.setDate(date2);
		contentPane.add(dateChooserMinDate);

		JLabel label_3 = new JLabel("To");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(475, 136, 31, 17);
		contentPane.add(label_3);

		dateChooserMaxDate = new JDateChooser();
		dateChooserMaxDate.setEnabled(false);
		dateChooserMaxDate.setDateFormatString("yyyy-MM-dd");
		dateChooserMaxDate.setBounds(500, 133, 105, 20);
		dateChooserMaxDate.setDate(date);
		contentPane.add(dateChooserMaxDate);

		JButton button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model1 = (DefaultTableModel) tableEmp.getModel();
				model1.setRowCount(0);
				try {
					conn = DBConnection.getConnection();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserMinDate.getDate();
					String datemin = (String) sdf.format(dateChooserMinDate.getDate());
					dt1 = dateChooserMaxDate.getDate();
					String datemax = (String) sdf.format(dateChooserMaxDate.getDate());
					String loqu = "SELECT * FROM banksystem.employees where Date between '" + datemin + "' AND '"
							+ datemax + "';";
					ps = conn.prepareStatement(loqu);
					rs = ps.executeQuery();
					while (rs.next()) {
						String data0 = rs.getString("SrNoMaster");
						String data1 = rs.getString("SrNo");
						String data2 = rs.getString("Date");
						String data3 = rs.getString("AccountNO");
						
						String data4 = rs.getString("Name");
						
						String data5 = rs.getString("Payment");
						String data6 = rs.getString("PaidAmount");
						String data7 = rs.getString("Days");
						String data8 = rs.getString("Leaves");
						String data9 = rs.getString("Address");
						String data10 = rs.getString("Age");
						String data11 = rs.getString("ContactNo");
						String data12 = rs.getString("Notes");

						Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8,
								data9, data10, data11, data12 };
						model1 = (DefaultTableModel) tableEmp.getModel();
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
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(618, 129, 115, 28);
		contentPane.add(button);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 176, 1134, 421);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 1114, 400);
		panel_1.add(scrollPane);

		tableEmp = new JTable();
		tableEmp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					for (int i = 0; i < tableEmp.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableEmp.getValueAt(i, 0).toString());
						if (che) {
							String accno4 = String.valueOf(tableEmp.getValueAt(i, 4).toString());

							name = String.valueOf(tableEmp.getValueAt(i, 4).toString());

						}
					}
				} catch (Exception ess) {
					System.out.print(ess.getMessage());
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
		tableEmp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableEmp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableEmp.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Select", "SrNoMaster", "SrNo", "JoinDate", "A/C No", "Name", "Payment", "PaidAmount", "Total Day", "Leaves", "Address", "Age", "Contact No", "Notes"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
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
		tableEmp.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableEmp.getColumnModel().getColumn(5).setMinWidth(150);
		tableEmp.getColumnModel().getColumn(6).setPreferredWidth(80);
		tableEmp.getColumnModel().getColumn(6).setMinWidth(80);
		tableEmp.getColumnModel().getColumn(7).setPreferredWidth(80);
		tableEmp.getColumnModel().getColumn(7).setMinWidth(80);
		tableEmp.getColumnModel().getColumn(8).setPreferredWidth(50);
		tableEmp.getColumnModel().getColumn(8).setMinWidth(50);
		tableEmp.getColumnModel().getColumn(10).setPreferredWidth(150);
		tableEmp.getColumnModel().getColumn(10).setMinWidth(150);
		tableEmp.getColumnModel().getColumn(12).setPreferredWidth(80);
		tableEmp.getColumnModel().getColumn(12).setMinWidth(80);
		tableEmp.getColumnModel().getColumn(13).setPreferredWidth(150);
		tableEmp.getColumnModel().getColumn(13).setMinWidth(150);
		showdata();
		scrollPane.setViewportView(tableEmp);

		JButton button_1 = new JButton("Report");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for (int i = 0; i <= tableEmp.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableEmp.getValueAt(i, 0).toString());
						if (che) {
							if (checkBoxseall.isSelected()) {

								java.io.InputStream file = getClass().getResourceAsStream("/Reports/Employeeall.jrxml");
								allinonereport r = new allinonereport("Agent Report", file);

								break;
							} else {
								String accno4 = String.valueOf(tableEmp.getValueAt(i, 4).toString());
								HashMap para = new HashMap();
								para.put("Acno", accno4);
								java.io.InputStream file = getClass().getResourceAsStream("/Reports/Employee.jrxml");
								allinonereport r = new allinonereport("Agent Report", file, para);
								break;
							}
						}

					}
				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		button_1.setIcon(new ImageIcon(ReportEmployess.class.getResource("/ImageButtonIcon/Report4d.png")));
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBounds(13, 655, 150, 38);
		contentPane.add(button_1);

		btnViewTransaction = new JButton("View Transaction");
		btnViewTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					for (int i = 0; i <= tableEmp.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableEmp.getValueAt(i, 0).toString());
						if (che) {
							name = String.valueOf(tableEmp.getValueAt(i, 4).toString());

							TranEmployees tcomm = new TranEmployees();
							tcomm.setUndecorated(true);
							tcomm.setVisible(true);
						}
					}
				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		btnViewTransaction
				.setIcon(new ImageIcon(ReportEmployess.class.getResource("/ImageButtonIcon/TrancationView.png")));
		btnViewTransaction.setHorizontalAlignment(SwingConstants.LEADING);
		btnViewTransaction.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnViewTransaction.setBounds(173, 655, 194, 38);
		contentPane.add(btnViewTransaction);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setIcon(new ImageIcon(ReportEmployess.class.getResource("/ImageButtonIcon/Exit.png")));
		btnExit.setHorizontalAlignment(SwingConstants.LEADING);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(377, 655, 129, 38);
		contentPane.add(btnExit);

		checkBoxseall = new JCheckBox("Select All");
		checkBoxseall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean s = false;
					boolean s1 = true;
					for (int i = 0; i < tableEmp.getRowCount(); i++) {
						Boolean isChecked = Boolean.valueOf(tableEmp.getValueAt(i, 0).toString());
						if (checkBoxseall.isSelected()) {
							tableEmp.setValueAt((Object) s1, i, 0);
						} else {
							tableEmp.setValueAt((Object) s, i, 0);
						}
					}

				} catch (Exception ww) {
				}
			}
		});
		checkBoxseall.setBounds(32, 604, 130, 23);
		contentPane.add(checkBoxseall);
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
				String data3 = rs.getString("AccountNO");
				
				String data4 = rs.getString("Name");
				
				String data5 = rs.getString("Payment");
				String data6 = rs.getString("PaidAmount");
				String data7 = rs.getString("Days");
				String data8 = rs.getString("Leaves");
				String data9 = rs.getString("Address");
				String data10 = rs.getString("Age");
				String data11 = rs.getString("ContactNo");
				String data12 = rs.getString("Notes");

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9,
						data10, data11, data12 };
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
			} catch (Exception ewww) {
				System.out.println(ewww.getMessage());
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
			tableEmp.setRowSorter(tr);
			tr.setRowFilter(RowFilter.regexFilter(quey));
		} catch (Exception es) {
			System.out.println(es.getMessage());
		}

	}
}
