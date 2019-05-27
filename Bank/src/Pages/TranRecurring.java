package Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.Cursor;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TranRecurring extends JFrame {

	private JPanel contentPane;
	private JTable tableRecurring;
	
	
	public static Connection conn;
	public static PreparedStatement ps;
	public static ResultSet rs;
	public JRadioButton radioName;
	public JRadioButton radioDate;
	public JRadioButton radioBoth;
	public JDateChooser dateChooserMinDate;
	public JColorChooser dateChooserMaxDate;
	public java.util.Date dt1;
	public java.util.Date dt2;
	public DecimalFormat df = new DecimalFormat("#.##");
	public String val1;
	public DefaultTableModel model1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TranRecurring frame = new TranRecurring();
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
	public TranRecurring() {
	
		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 123, 1154, 488);
		contentPane.add(panel);
		panel.setLayout(null);
		
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
		scrollPane.setBounds(10, 21, 1134, 456);
		panel.add(scrollPane);
		
		tableRecurring = new JTable();
		tableRecurring.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableRecurring.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableRecurring.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Select", "SrNoMaster", "SrNo", "Date", "Up Amt Date", "Name", "A/C No", "Amount", "Interes", "Interes Amt", "Matured Amt", "Douration", "Days"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableRecurring.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableRecurring.getColumnModel().getColumn(0).setMinWidth(50);
		tableRecurring.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableRecurring.getColumnModel().getColumn(1).setMinWidth(0);
		tableRecurring.getColumnModel().getColumn(1).setMaxWidth(0);
		tableRecurring.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableRecurring.getColumnModel().getColumn(2).setMinWidth(50);
		tableRecurring.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(3).setMinWidth(80);
		tableRecurring.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(4).setMinWidth(80);
		tableRecurring.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableRecurring.getColumnModel().getColumn(5).setMinWidth(150);
		tableRecurring.getColumnModel().getColumn(5).setMaxWidth(150);
		tableRecurring.getColumnModel().getColumn(6).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(6).setMinWidth(80);
		tableRecurring.getColumnModel().getColumn(6).setMaxWidth(100);
		tableRecurring.getColumnModel().getColumn(7).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(7).setMinWidth(80);
		tableRecurring.getColumnModel().getColumn(8).setPreferredWidth(50);
		tableRecurring.getColumnModel().getColumn(8).setMinWidth(50);
		tableRecurring.getColumnModel().getColumn(9).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(9).setMinWidth(80);
		tableRecurring.getColumnModel().getColumn(10).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(10).setMinWidth(80);
		tableRecurring.getColumnModel().getColumn(11).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(11).setMinWidth(80);
		tableRecurring.getColumnModel().getColumn(12).setPreferredWidth(80);
		tableRecurring.getColumnModel().getColumn(12).setMinWidth(80);
		loandatashow();
		scrollPane.setViewportView(tableRecurring);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1154, 112);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Recrring Transaction");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 54, 349, 47);
		panel_1.add(lblNewLabel);
		
		JLabel label = new JLabel("Date:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(826, 81, 81, 17);
		panel_1.add(label);
		
		Date date = new Date();
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(930, 81, 196, 20);
		dateChooser.setDate(date);
		panel_1.add(dateChooser);
		
		JButton button = new JButton("Exit");
		button.setIcon(new ImageIcon(TranRecurring.class.getResource("/ImageButtonIcon/Exit.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(170, 655, 129, 38);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Report");
		button_1.setIcon(new ImageIcon(TranRecurring.class.getResource("/ImageButtonIcon/Report4d.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					java.io.InputStream file=getClass().getResourceAsStream("/Reports/TranRecurring.jrxml");
					
							String accno4 = ReportRecurring.accno;
							HashMap para = new HashMap();
							para.put("Accno", accno4);
							allinonereport r = new allinonereport("Recrring Report",
										file,para);
								
							
				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBounds(10, 655, 150, 38);
		contentPane.add(button_1);
	}
	public void loandatashow() {

		try {
			conn = (Connection) DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.dailyrecurring where AccountNo='"+ReportRecurring.accno+"'";
			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data00 = rs.getString("SrNOMaster");
				String data0 = rs.getString("SrNO");
				String data1 = rs.getString("Date");
				String data2 = rs.getString("Date");
				String data3 = rs.getString("CustomerName");
				String data4 = rs.getString("AccountNo");
				String data5 = rs.getString("Amount");
				String data6 = rs.getString("Interst");
				String data7 = rs.getString("InterestAmt");
				String data8 = rs.getString("MaturedAmt");
				String data9 = rs.getString("Duration");
				String data10 = rs.getString("Days");

				Object[] row = { Boolean.FALSE, data00, data0, data1, data2, data3, data4, data5, data6, data7, data8,
						data9, data10 };
				model1 = (DefaultTableModel) tableRecurring.getModel();
				model1.addRow(row);
				tableRecurring.getColumnModel().getColumn(0).setMinWidth(0);
				tableRecurring.getColumnModel().getColumn(0).setMaxWidth(0);
				tableRecurring.getColumnModel().getColumn(0).setWidth(0);


			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally{
			try
			{
			rs.close();
			ps.close();
			conn.close();
			}
			catch(Exception ewww)
			{
				System.out.println(ewww.getMessage());
			}
		}
	}
}
