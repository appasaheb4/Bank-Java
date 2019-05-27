package Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JDateChooser;

import CLASS.CalculatorGUI;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import java.awt.Cursor;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Color;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;

public class LoanDurationEnd extends JFrame {

	private JPanel contentPane;
	private JTable tableDouover;

	public Connection conn;
	public PreparedStatement ps;
	public PreparedStatement ps1;
	public PreparedStatement ps2;
	public ResultSet rs;
	public ResultSet rs1;
	public Double day;
	public String date;

	public DefaultTableModel model1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanDurationEnd frame = new LoanDurationEnd();
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
	public LoanDurationEnd() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int x=e.getXOnScreen();
				int y=e.getYOnScreen();
				Welcome.lblLocation.setText("X:"+x+" "+"Y:"+y );
			}
		});

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1154, 100);
		contentPane.add(panel);

		JLabel lblDueOverThis = new JLabel("Due over");
		lblDueOverThis.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDueOverThis.setBounds(10, 39, 402, 58);
		panel.add(lblDueOverThis);

		Date datess = new Date();
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(828, 69, 196, 20);
		dateChooser.setDate(datess);
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

		JLabel label_1 = new JLabel("Date:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(777, 69, 81, 17);
		panel.add(label_1);

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
		button.setIcon(new ImageIcon(LoanDurationEnd.class.getResource("/ImageButtonIcon/clac.png")));
		button.setOpaque(false);
		button.setForeground(Color.RED);
		button.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBounds(1080, 11, 64, 55);
		panel.add(button);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 137, 1154, 485);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 1134, 463);
		panel_1.add(scrollPane);

		tableDouover = new JTable();
		tableDouover.addMouseListener(new MouseAdapter() {
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
				int rows = tableDouover.getRowCount() - 1;

				for (int i = 0; i < tableDouover.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableDouover.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableDouover.getSelectedRow();
						int col = tableDouover.getSelectedColumn();
						tableDouover.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableDouover.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableDouover.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableDouover.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableDouover.setValueAt((Object) s, 0, 0);
							tableDouover.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableDouover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableDouover.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableDouover.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Select", "SrNo", "Date",
				"Up Amt Date", "A/C No", "Name", "Amount", "Int %", "Douration", "Total Days", "Contact No" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class, Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableDouover.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableDouover.getColumnModel().getColumn(0).setMinWidth(50);
		tableDouover.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableDouover.getColumnModel().getColumn(1).setMinWidth(50);
		tableDouover.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableDouover.getColumnModel().getColumn(3).setMinWidth(80);
		tableDouover.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableDouover.getColumnModel().getColumn(4).setMinWidth(80);
		tableDouover.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableDouover.getColumnModel().getColumn(5).setMinWidth(150);
		tableDouover.getColumnModel().getColumn(8).setPreferredWidth(80);
		tableDouover.getColumnModel().getColumn(8).setMinWidth(80);
		datashow();
		scrollPane.setViewportView(tableDouover);
		
		JButton button_1 = new JButton("Exit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setIcon(new ImageIcon(LoanDurationEnd.class.getResource("/ImageButtonIcon/Exit.png")));
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBounds(170, 644, 150, 38);
		contentPane.add(button_1);
	}

	public void datashow() {
		model1 = (DefaultTableModel) tableDouover.getModel();
		model1.setRowCount(0);
		try {

			conn = (Connection) DBConnection.getConnection();
			String loanre = "select * from banksystem.loan order by Srnomaster;";
			ps = (PreparedStatement) conn.prepareStatement(loanre);
			rs = ps.executeQuery();
			while (rs.next()) {
				String srno1 = rs.getString("Srnomaster");
				day = rs.getDouble("Days");
				date = rs.getString("AmtPaidDate");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date datecur = new Date();
				String sysdate = (String) sdf.format(datecur);

				SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
				String inputString1 = date;
				String inputString2 = sysdate;
				Date date1 = myFormat.parse(inputString1);
				Date date2 = myFormat.parse(inputString2);
				long diff = date2.getTime() - date1.getTime();

				long totalday44 = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
				double totalday = Double.valueOf(totalday44);

				if (totalday > 30) {
					conn = (Connection) DBConnection.getConnection();
					String loqu = "SELECT * FROM banksystem.loan order by Srnomaster;";
					ps1 = (PreparedStatement) conn.prepareStatement(loqu);
					rs1 = ps1.executeQuery();
					while (rs1.next()) {
						String srno2 = rs1.getString("Srnomaster");
						if (srno1.equals(srno2)) {
							DecimalFormat df = new DecimalFormat("#.##");
							String data0 = rs1.getString("Srnomaster");
							String data1 = rs1.getString("AmtPaidDate");
							String data2 = rs1.getString("UpdateAmtDate");
							String data3 = rs1.getString("AccountNumber");
							String data4 = rs1.getString("Name");
							double dataamt = rs1.getDouble("Amount");
							String data5 = String.valueOf(df.format(dataamt));
							double interst = rs1.getDouble("Interst");
							String data6 = String.valueOf(df.format(interst));
							String data7 = rs1.getString("Duration");
							String data8 = String.valueOf(totalday);
							String data9 = rs1.getString("ContactNo");

							Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7,
									data8, data9 };
							model1 = (DefaultTableModel) tableDouover.getModel();
							model1.addRow(row);
						}
					}

				}

			}

		} catch (

		Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				rs.close();
				rs1.close();
				ps.close();
				ps1.close();
				conn.close();
			} catch (Exception ewww) {
				System.out.println(ewww.getMessage());
			}
		}

	}
}
