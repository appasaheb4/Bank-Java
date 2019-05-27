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

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import CLASS.CalculatorGUI;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.MouseMotionAdapter;

public class LoanDurationUpComing extends JFrame {

	private JPanel contentPane;
	private JTable tableCustomerEndday;
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
					LoanDurationUpComing frame = new LoanDurationUpComing();
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
	public LoanDurationUpComing() {
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
		panel.setBounds(0, 0, 1154, 100);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Upcoming  due");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 39, 402, 58);

		panel.add(lblNewLabel);

		Date date = new Date();
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(844, 69, 196, 20);
		dateChooser.setDate(date);
		panel.add(dateChooser);

		JLabel label = new JLabel("Date:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(783, 69, 81, 17);
		panel.add(label);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculatorGUI cl = new CalculatorGUI("Bank System");
				cl.setSize(400, 350);
				cl.setFocusable(true);
				cl.setLocation(830, 0);
				cl.setVisible(true);
			}
		});
		button_1.setIcon(new ImageIcon(LoanDurationUpComing.class.getResource("/ImageButtonIcon/clac.png")));
		button_1.setOpaque(false);
		button_1.setForeground(Color.RED);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		button_1.setBounds(1080, 11, 64, 55);
		panel.add(button_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 111, 1134, 534);
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
		scrollPane.setBounds(10, 11, 1114, 512);
		panel_1.add(scrollPane);

		tableCustomerEndday = new JTable();
		tableCustomerEndday.addMouseListener(new MouseAdapter() {
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
				int rows = tableCustomerEndday.getRowCount() - 1;

				for (int i = 0; i < tableCustomerEndday.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableCustomerEndday.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableCustomerEndday.getSelectedRow();
						int col = tableCustomerEndday.getSelectedColumn();
						tableCustomerEndday.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableCustomerEndday.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableCustomerEndday.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableCustomerEndday.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableCustomerEndday.setValueAt((Object) s, 0, 0);
							tableCustomerEndday.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableCustomerEndday.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableCustomerEndday.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableCustomerEndday.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Select", "SrNo", "Date",
				"Up Amt Date", "A/C No", "Name", "Amount", "Int %", "Douration", "Total Days", "Contact No" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class, Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableCustomerEndday.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableCustomerEndday.getColumnModel().getColumn(0).setMinWidth(50);
		tableCustomerEndday.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableCustomerEndday.getColumnModel().getColumn(1).setMinWidth(50);
		tableCustomerEndday.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableCustomerEndday.getColumnModel().getColumn(3).setMinWidth(80);
		tableCustomerEndday.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableCustomerEndday.getColumnModel().getColumn(4).setMinWidth(80);
		tableCustomerEndday.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableCustomerEndday.getColumnModel().getColumn(5).setMinWidth(150);
		tableCustomerEndday.getColumnModel().getColumn(8).setPreferredWidth(80);
		tableCustomerEndday.getColumnModel().getColumn(8).setMinWidth(80);
		datashow();
		scrollPane.setViewportView(tableCustomerEndday);

		JButton button = new JButton("Report");
		button.setVisible(false);
		button.setIcon(new ImageIcon(LoanDurationUpComing.class.getResource("/ImageButtonIcon/Report4d.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(20, 656, 150, 38);
		contentPane.add(button);

		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon(LoanDurationUpComing.class.getResource("/ImageButtonIcon/Exit.png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setHorizontalAlignment(SwingConstants.LEADING);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(180, 656, 150, 38);
		contentPane.add(btnExit);
	}

	public void datashow() {
		model1 = (DefaultTableModel) tableCustomerEndday.getModel();
		model1.setRowCount(0);
		try {
			
			
			conn = (Connection) DBConnection.getConnection();
			String loanre = "select * from banksystem.loan order by Srnomaster;";
			ps = (PreparedStatement) conn.prepareStatement(loanre);
			rs = ps.executeQuery();
			while (rs.next()) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String updatedate = rs.getString("UpdateAmtDate");


				Date currentsysdate = new Date();
				String datedate = (String) sdf.format(currentsysdate);
				String shortdate = datedate.substring(8, 10);
				if (shortdate.equals("25") || shortdate.equals("26") || shortdate.equals("27") || shortdate.equals("28")
						|| shortdate.equals("29") || shortdate.equals("30") || shortdate.equals("31")) {

				String srno1 = rs.getString("Srnomaster");
				day = rs.getDouble("Days");
				date = rs.getString("UpdateAmtDate");

				
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
							model1 = (DefaultTableModel) tableCustomerEndday.getModel();
							model1.addRow(row);
						}
					}
					

				}
			}

		} catch (Exception ex) {
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
