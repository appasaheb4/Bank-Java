package Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
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
import java.util.HashMap;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class AgentCommistionTable extends JFrame {

	private JPanel contentPane;
	private JTable tableAgentCommisstiontable;
	public Connection conn;
	public PreparedStatement ps;
	public String val1;
	public Statement st;
	public DefaultTableModel model1;
	public ResultSet rs;
	private JTextField textFieldAgentAmount;
	private JTextField textFieldCutomerAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgentCommistionTable frame = new AgentCommistionTable();
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
	public AgentCommistionTable() {
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
				int rowsCount = tableAgentCommisstiontable.getRowCount();
		        double sum = 0;
		        double amttotal=0;
		        for(int i = 0; i < rowsCount; i++){
		            sum = sum+Double.valueOf(tableAgentCommisstiontable.getValueAt(i,5).toString());
		            amttotal=amttotal+Double.valueOf(tableAgentCommisstiontable.getValueAt(i,6).toString());
		        }
		        textFieldAgentAmount.setText(String.valueOf(sum));
		        textFieldCutomerAmount.setText(String.valueOf(amttotal));
		      
			}
		});

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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
		lblAgentName.setText("Agent name:"+CommissionA_C.cusname);
		panel.add(lblAgentName);
		
		JLabel lblAccountNo = new JLabel();
		lblAccountNo.setForeground(Color.BLACK);
		lblAccountNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAccountNo.setBounds(429, 101, 318, 32);
		lblAccountNo.setText("Account Number:"+CommissionA_C.acnocomm);
		panel.add(lblAccountNo);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 142, 1154, 454);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1134, 432);
		panel_1.add(scrollPane);

		tableAgentCommisstiontable = new JTable();
		tableAgentCommisstiontable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try
				{
				che();	
				}
				
				catch(Exception es){}
			}

			private void che() {
				boolean s = false;
				boolean s1 = true;
				String inc = null;
				 int row=0;
				 int rows = tableAgentCommisstiontable.getRowCount()-1;
				
				for (int i = 0; i < tableAgentCommisstiontable.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableAgentCommisstiontable.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableAgentCommisstiontable.getSelectedRow();
						int col = tableAgentCommisstiontable.getSelectedColumn();
						tableAgentCommisstiontable.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableAgentCommisstiontable.setValueAt((Object) s1, 0, 0);
						}
						else if(i==rows)
						{
							tableAgentCommisstiontable.setValueAt((Object) s1, rows, 0);
						}
						else if(i!=rows)
						{
							tableAgentCommisstiontable.setValueAt((Object) s, rows, 0);
							
						}
						

					} else if (i >= 0) {
						if (row != 0) {
							tableAgentCommisstiontable.setValueAt((Object) s, 0, 0);
							tableAgentCommisstiontable.setValueAt((Object) s1, row, 0);
							
							
						}
					}

					else {
					}
				}
				
			}
		});
		tableAgentCommisstiontable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableAgentCommisstiontable
				.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Select", "SrNo", "Date", "Paid Amt", "Cust Amt", "Tran Mode", "Cheqe No"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableAgentCommisstiontable.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableAgentCommisstiontable.getColumnModel().getColumn(0).setMinWidth(50);
		tableAgentCommisstiontable.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableAgentCommisstiontable.getColumnModel().getColumn(1).setMinWidth(50);
		tableAgentCommisstiontable.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableAgentCommisstiontable.getColumnModel().getColumn(2).setMinWidth(80);
		tableAgentCommisstiontable.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableAgentCommisstiontable.getColumnModel().getColumn(3).setMinWidth(80);
		tableAgentCommisstiontable.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableAgentCommisstiontable.getColumnModel().getColumn(4).setMinWidth(80);
		showdata() ;
		scrollPane.setViewportView(tableAgentCommisstiontable);

		JButton button_1 = new JButton("Exit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setIcon(new ImageIcon(AgentCommistionTable.class.getResource("/ImageButtonIcon/Exit.png")));
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBounds(26, 655, 115, 38);
		contentPane.add(button_1);
		
		JLabel lblNewLabel_1 = new JLabel("Paid Agent Amount:");
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setBounds(743, 607, 126, 14);
		contentPane.add(lblNewLabel_1);
		
		
		
		
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
		
		
		
		
		
		textFieldAgentAmount = new JTextField();
		textFieldAgentAmount.setVisible(false);
		textFieldAgentAmount.setBounds(879, 604, 175, 20);
		contentPane.add(textFieldAgentAmount);
		textFieldAgentAmount.setColumns(10);
		
		JLabel lblCustomerAmount = new JLabel("Customer Amount:");
		lblCustomerAmount.setVisible(false);
		lblCustomerAmount.setBounds(743, 640, 126, 14);
		contentPane.add(lblCustomerAmount);
		
		textFieldCutomerAmount = new JTextField();
		textFieldCutomerAmount.setVisible(false);
		textFieldCutomerAmount.setColumns(10);
		textFieldCutomerAmount.setBounds(879, 637, 175, 20);
		contentPane.add(textFieldCutomerAmount);
	}

	public void showdata() {
		try {
			conn = DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.commissiona_ctrancation where AccountNo='"+CommissionA_C.acnocomm+"';";
			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
			
				
				String data1 = rs.getString("SrNo");
				String data2 = rs.getString("Date");
				String data3 = rs.getString("PaidAmount");
				String data4 = rs.getString("AllCustomerAmount");
				String data5 = rs.getString("Tranmode");
				String data6 = rs.getString("ChequeNo");
				
				

				Object[] row = { Boolean.FALSE, data1, data2,data3,data4,data5,data6};
				model1 = (DefaultTableModel) tableAgentCommisstiontable.getModel();
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
