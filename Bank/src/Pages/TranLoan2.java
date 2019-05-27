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
import javax.swing.JComponent;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TranLoan2 extends JFrame {

	private JPanel contentPane;
	private JTable tableTrnaLoan;
	
	
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TranLoan2 frame = new TranLoan2();
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
	public TranLoan2() {
	
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
		
		tableTrnaLoan = new JTable();
		tableTrnaLoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableTrnaLoan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableTrnaLoan.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sr No", "Tran Date", "Tran Particulars", "Tran Mode", "Cheque No", "Amount", "Withdrawal", "Deposite", "Balance"
			}
		));
		tableTrnaLoan.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableTrnaLoan.getColumnModel().getColumn(0).setMinWidth(50);
		tableTrnaLoan.getColumnModel().getColumn(1).setPreferredWidth(80);
		tableTrnaLoan.getColumnModel().getColumn(1).setMinWidth(80);
		tableTrnaLoan.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableTrnaLoan.getColumnModel().getColumn(2).setMinWidth(150);
		tableTrnaLoan.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableTrnaLoan.getColumnModel().getColumn(3).setMinWidth(80);
		tableTrnaLoan.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableTrnaLoan.getColumnModel().getColumn(4).setMinWidth(80);
		tableTrnaLoan.getColumnModel().getColumn(5).setPreferredWidth(15);
		tableTrnaLoan.getColumnModel().getColumn(5).setMinWidth(0);
		tableTrnaLoan.getColumnModel().getColumn(5).setMaxWidth(0);
		tableTrnaLoan.getColumnModel().getColumn(6).setPreferredWidth(80);
		tableTrnaLoan.getColumnModel().getColumn(6).setMinWidth(80);
		tableTrnaLoan.getColumnModel().getColumn(7).setPreferredWidth(80);
		tableTrnaLoan.getColumnModel().getColumn(7).setMinWidth(80);
		tableTrnaLoan.getColumnModel().getColumn(8).setPreferredWidth(80);
		tableTrnaLoan.getColumnModel().getColumn(8).setMinWidth(80);
		loandatashow();
		scrollPane.setViewportView(tableTrnaLoan);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1154, 112);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Loan Transaction");
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
		button.setIcon(new ImageIcon(TranLoan2.class.getResource("/ImageButtonIcon/Exit.png")));
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
		button_1.setIcon(new ImageIcon(TranLoan2.class.getResource("/ImageButtonIcon/Report4d.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					java.io.InputStream file=getClass().getResourceAsStream("/Reports/TranLoan.jrxml");
							String accno4 = LoanA_C.accno;
							HashMap para = new HashMap();
							para.put("Accno", accno4);
							allinonereport r = new allinonereport("Loan Report",
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
			conn = DBConnection.getConnection();
			String show = "select * from banksystem.loantranscation where AccountNo='" + LoanA_C.accno + "'";
			ps = conn.prepareStatement(show);
			rs = ps.executeQuery();

			while (rs.next()) {

				String data0 = rs.getString("SrNo");
				String data1 = rs.getString("Date");
				String data2 = rs.getString("TransactionParticulars");
				String data3 = rs.getString("TranBy");
				String data4 = rs.getString("chequeno");
				
				double Amount = rs.getDouble("Amount");
				String data5=df.format(Amount);
				
				
				
				
				double Withdral = rs.getDouble("Withdral");
				String data6=df.format(Withdral);
				
				
				String data7 = "";
				double Balance = rs.getDouble("Balance");
				String data8=df.format(Balance);
				
				
				
				

				Object[] row = { data0, data1, data2, data3, data4, data5, data6, data7, data8 };
				model1 = (DefaultTableModel) tableTrnaLoan.getModel();
				model1.addRow(row);


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
