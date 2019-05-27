package Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class EmployeeTrancation extends JFrame {

	private JPanel contentPane;
	private JTable tableEmptaratnion;
	public Connection conn;
	public PreparedStatement ps;
	public String val1;
	public Statement st;
	public DefaultTableModel model1;
	public ResultSet rs;
	private JTextField textFieldAdvancedPaytotal;
	private JTextField textFieldLeavestotal;
	private JTextField textFieldLeacesAmtTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeTrancation frame = new EmployeeTrancation();
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
	public EmployeeTrancation() {
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
				int rowsCount = tableEmptaratnion.getRowCount();
				double sum = 0.0;
				double amttotal = 0.0;
				double amttotal1 = 0.0;
				for (int i = 0; i <= rowsCount; i++) {

					sum = sum + Double.valueOf(tableEmptaratnion.getValueAt(i, 5).toString());
					amttotal = amttotal + Double.valueOf(tableEmptaratnion.getValueAt(i, 8).toString());
					amttotal1 = amttotal1 + Double.valueOf(tableEmptaratnion.getValueAt(i, 9).toString());

				}

				textFieldAdvancedPaytotal.setText(String.valueOf(sum));
				textFieldLeavestotal.setText(String.valueOf(amttotal));
				textFieldLeacesAmtTotal.setText(String.valueOf(amttotal1));

			}
		});

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1154, 131);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Agent Commission Amount Details");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(20, 35, 358, 85);
		panel.add(lblNewLabel);

		JLabel label = new JLabel("Date:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(852, 100, 81, 17);
		panel.add(label);

		Date date = new Date();
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setBounds(948, 100, 196, 20);
		dateChooser.setDate(date);
		panel.add(dateChooser);

		JLabel lblAgentName = new JLabel("\r\n");
		lblAgentName.setForeground(Color.BLACK);
		lblAgentName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAgentName.setBounds(429, 61, 318, 32);
		lblAgentName.setText("Employee name:" + Employee.empname);
		panel.add(lblAgentName);
		
		

		JLabel lblAccountNo = new JLabel();
		lblAccountNo.setForeground(Color.BLACK);
		lblAccountNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAccountNo.setBounds(429, 101, 318, 32);
		lblAccountNo.setText("Account Number:" + Employee.acnoemp);
		panel.add(lblAccountNo);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 142, 1154, 454);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1134, 432);
		panel_1.add(scrollPane);

		tableEmptaratnion = new JTable();
		tableEmptaratnion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try
				{
					che();
				}
				catch(Exception ee){}
			}

			private void che() {
				boolean s = false;
				boolean s1 = true;
				String inc = null;
				int row = 0;
				int rows = tableEmptaratnion.getRowCount() - 1;

				for (int i = 0; i < tableEmptaratnion.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableEmptaratnion.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableEmptaratnion.getSelectedRow();
						int col = tableEmptaratnion.getSelectedColumn();
						tableEmptaratnion.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableEmptaratnion.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableEmptaratnion.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableEmptaratnion.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableEmptaratnion.setValueAt((Object) s, 0, 0);
							tableEmptaratnion.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}

				
			}
		});
		tableEmptaratnion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableEmptaratnion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableEmptaratnion.setModel(
				new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Select", "SrNo", "Date", "Name", "Account No", "Advanced Pay", "Salary", "Total Rem Amt", "Leaves", "Leaves Amt", "Tran Mode"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableEmptaratnion.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableEmptaratnion.getColumnModel().getColumn(0).setMinWidth(50);
		tableEmptaratnion.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableEmptaratnion.getColumnModel().getColumn(2).setMinWidth(0);
		tableEmptaratnion.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableEmptaratnion.getColumnModel().getColumn(3).setMinWidth(150);
		tableEmptaratnion.getColumnModel().getColumn(3).setMaxWidth(100);
		tableEmptaratnion.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableEmptaratnion.getColumnModel().getColumn(4).setMinWidth(80);
		tableEmptaratnion.getColumnModel().getColumn(4).setMaxWidth(100);
		tableEmptaratnion.getColumnModel().getColumn(5).setPreferredWidth(80);
		tableEmptaratnion.getColumnModel().getColumn(5).setMinWidth(80);
		tableEmptaratnion.getColumnModel().getColumn(6).setPreferredWidth(80);
		tableEmptaratnion.getColumnModel().getColumn(6).setMinWidth(80);
		tableEmptaratnion.getColumnModel().getColumn(7).setPreferredWidth(80);
		tableEmptaratnion.getColumnModel().getColumn(7).setMinWidth(80);
		tableEmptaratnion.getColumnModel().getColumn(9).setPreferredWidth(80);
		tableEmptaratnion.getColumnModel().getColumn(9).setMinWidth(80);
		tableEmptaratnion.getColumnModel().getColumn(10).setPreferredWidth(80);
		tableEmptaratnion.getColumnModel().getColumn(10).setMinWidth(80);
		showdata();
		scrollPane.setViewportView(tableEmptaratnion);

		JButton button = new JButton("Report");
		button.setVisible(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setIcon(new ImageIcon(EmployeeTrancation.class.getResource("/ImageButtonIcon/Report4d.png")));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(10, 659, 135, 38);
		contentPane.add(button);

		JButton button_1 = new JButton("Exit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setIcon(new ImageIcon(EmployeeTrancation.class.getResource("/ImageButtonIcon/Exit.png")));
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBounds(155, 659, 115, 38);
		contentPane.add(button_1);

		JLabel lblNewLabel_1 = new JLabel("Advanced Pay Amt Total:");
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setBounds(694, 607, 175, 14);
		contentPane.add(lblNewLabel_1);

		textFieldAdvancedPaytotal = new JTextField();
		textFieldAdvancedPaytotal.setVisible(false);
		textFieldAdvancedPaytotal.setEditable(false);
		textFieldAdvancedPaytotal.setBounds(879, 604, 175, 20);
		contentPane.add(textFieldAdvancedPaytotal);
		textFieldAdvancedPaytotal.setColumns(10);

		JLabel lblCustomerAmount = new JLabel("Leaves Total:");
		lblCustomerAmount.setVisible(false);
		lblCustomerAmount.setBounds(694, 640, 175, 14);
		contentPane.add(lblCustomerAmount);

		textFieldLeavestotal = new JTextField();
		textFieldLeavestotal.setVisible(false);
		textFieldLeavestotal.setEditable(false);
		textFieldLeavestotal.setColumns(10);
		textFieldLeavestotal.setBounds(879, 637, 175, 20);
		contentPane.add(textFieldLeavestotal);

		textFieldLeacesAmtTotal = new JTextField();
		textFieldLeacesAmtTotal.setVisible(false);
		textFieldLeacesAmtTotal.setEditable(false);
		textFieldLeacesAmtTotal.setColumns(10);
		textFieldLeacesAmtTotal.setBounds(879, 665, 175, 20);
		contentPane.add(textFieldLeacesAmtTotal);

		JLabel lblLeavesAmtTotal = new JLabel("Leaves Amt Total:");
		lblLeavesAmtTotal.setVisible(false);
		lblLeavesAmtTotal.setBounds(694, 668, 175, 14);
		contentPane.add(lblLeavesAmtTotal);
	}

	public void showdata() {
		try {
			conn = DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.emptrancation where AccountNo='" + Employee.acnoemp + "';";
			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data0 = rs.getString("SrNo");
				String data1 = rs.getString("Date");
				String data2 = rs.getString("Name");
				String data3 = rs.getString("AccountNo");
				String data4 = rs.getString("AdvancePay");
				String data5 = rs.getString("Salary");
				String data6 = rs.getString("TotalAmount");
				String data7 = rs.getString("Leaves");
				String data8 = rs.getString("LeavesAmt");
				String data9 = rs.getString("Mode");

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8, data9 };
				model1 = (DefaultTableModel) tableEmptaratnion.getModel();
				model1.addRow(row);

			}
			
		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		}
		finally
		{
			try
			{
				rs.close();
				ps.close();
				conn.close();
			}
			catch(Exception ew)
			{
				System.out.println(ew.getMessage());
			}
		}
	}
}
