package Pages;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class AgentCustomerList extends JFrame {

	private JPanel contentPane;
	private JTable tableAgentCustomerList;
	public static JLabel lblAgentName;

	public static Connection conn;
	public static PreparedStatement ps;
	public static PreparedStatement ps1;
	public String val1;
	public Statement st;
	public DefaultTableModel model1;
	public ResultSet rs = null;

	static java.util.Date dt1;
	java.util.Date dt2;

	public static String data0;
	public JButton btnPay;
	public static String data1;
	public static String data2;
	public static String data3;
	public static String data4;
	public static String data5;
	public static JTextField textFieldTotalPaidAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgentCustomerList frame = new AgentCustomerList();
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
	public AgentCustomerList() {
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

				lblAgentName.setText(CommissionA_C.cusname);
				try {
					double sum = 0;
					conn = DBConnection.getConnection();
					String query = "SELECT * FROM banksystem.dailyrecurring where AgentName='" + lblAgentName.getText()
							+ "';";
					System.out.println(lblAgentName.getText());
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						data0 = rs.getString("SrNoMaster");
						data1 = rs.getString("Date");
						data2 = rs.getString("CustomerName");
						data3 = rs.getString("Amount");
						data4 = rs.getString("Interst");
						data5 = rs.getString("MaturedAmt");

						Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5 };
						model1 = (DefaultTableModel) tableAgentCustomerList.getModel();
						model1.addRow(row);

					}
					tablesamedatanotshow();

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

				int rowsCount = tableAgentCustomerList.getRowCount();
				double sum = 0;
				double amttotal = 0;
				for (int i = 0; i < rowsCount; i++) {
					sum = sum + Double.valueOf(tableAgentCustomerList.getValueAt(i, 4).toString());

				}
				textFieldTotalPaidAmount.setText(String.valueOf(sum));
				
				
				
				try
				{
					double allcutamount=Double.valueOf(textFieldTotalPaidAmount.getText());
					if(allcutamount<=0)
					{
					btnPay.setEnabled(false);
					}
					else
					{
						
					}
					
				}
				catch(Exception ew)
				{
					System.out.println(ew.getMessage());
				}

			}
		});

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1154, 101);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Agent Customer List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(10, 11, 341, 65);
		panel.add(lblNewLabel);

		lblAgentName = new JLabel("");
		lblAgentName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAgentName.setBounds(577, 52, 330, 38);
		panel.add(lblAgentName);

		JLabel lblNewLabel_1 = new JLabel("Agent Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(450, 61, 118, 22);
		panel.add(lblNewLabel_1);

		JLabel label = new JLabel("Date:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(852, 70, 81, 17);
		panel.add(label);

		Date date = new Date();
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setBounds(948, 70, 196, 20);
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

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(0, 112, 1154, 511);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1134, 489);
		panel_1.add(scrollPane);

		tableAgentCustomerList = new JTable();
		tableAgentCustomerList.addMouseListener(new MouseAdapter() {
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
				int rows = tableAgentCustomerList.getRowCount() - 1;

				for (int i = 0; i < tableAgentCustomerList.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableAgentCustomerList.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableAgentCustomerList.getSelectedRow();
						int col = tableAgentCustomerList.getSelectedColumn();
						tableAgentCustomerList.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableAgentCustomerList.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableAgentCustomerList.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableAgentCustomerList.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableAgentCustomerList.setValueAt((Object) s, 0, 0);
							tableAgentCustomerList.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

			}
		});
		tableAgentCustomerList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableAgentCustomerList.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Select", "SrNo", "Date", "Name", "Total Paid Amt", "Interst", "Maturity Amt" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableAgentCustomerList.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableAgentCustomerList.getColumnModel().getColumn(0).setMinWidth(50);
		tableAgentCustomerList.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableAgentCustomerList.getColumnModel().getColumn(1).setMinWidth(50);
		tableAgentCustomerList.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableAgentCustomerList.getColumnModel().getColumn(2).setMinWidth(80);
		tableAgentCustomerList.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableAgentCustomerList.getColumnModel().getColumn(3).setMinWidth(150);
		tableAgentCustomerList.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableAgentCustomerList.getColumnModel().getColumn(4).setMinWidth(80);
		tableAgentCustomerList.getColumnModel().getColumn(5).setPreferredWidth(50);
		tableAgentCustomerList.getColumnModel().getColumn(5).setMinWidth(50);
		tableAgentCustomerList.getColumnModel().getColumn(6).setPreferredWidth(80);
		tableAgentCustomerList.getColumnModel().getColumn(6).setMinWidth(80);
		scrollPane.setViewportView(tableAgentCustomerList);

		JButton button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button.setIcon(new ImageIcon(AgentCustomerList.class.getResource("/ImageButtonIcon/Exit.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(280, 655, 115, 38);
		contentPane.add(button);

		JButton btnReport = new JButton("Report");
		btnReport.setVisible(false);
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnReport.setIcon(new ImageIcon(AgentCustomerList.class.getResource("/ImageButtonIcon/Report4d.png")));
		btnReport.setHorizontalAlignment(SwingConstants.LEADING);
		btnReport.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReport.setBounds(10, 655, 135, 38);
		contentPane.add(btnReport);

		 btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgentCommisionPaidpopup ac = new AgentCommisionPaidpopup();
				ac.setVisible(true);
			}
		});
		btnPay.setIcon(new ImageIcon(AgentCustomerList.class.getResource("/ImageButtonIcon/Pay.png")));
		btnPay.setHorizontalAlignment(SwingConstants.LEADING);
		btnPay.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPay.setBounds(155, 655, 115, 38);
		contentPane.add(btnPay);

		JLabel lblNewLabel_2 = new JLabel("Total Paid Amount:");
		lblNewLabel_2.setBounds(790, 634, 115, 14);
		contentPane.add(lblNewLabel_2);

		textFieldTotalPaidAmount = new JTextField();
		textFieldTotalPaidAmount.setBounds(929, 631, 182, 20);
		contentPane.add(textFieldTotalPaidAmount);
		textFieldTotalPaidAmount.setColumns(10);
	}

	public void tablesamedatanotshow() {
		try {
			for (int i = 0; i <= tableAgentCustomerList.getRowCount(); i++) {

				String srno = String.valueOf(tableAgentCustomerList.getValueAt(i, 1));

				String srno1 = String.valueOf(tableAgentCustomerList.getValueAt(i, 1));

				if (srno.equals(srno1)) {

				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}
}
