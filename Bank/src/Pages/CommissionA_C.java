package Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;

import CLASS.CalculatorGUI;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import java.awt.Cursor;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseMotionAdapter;

public class CommissionA_C extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNameofAgent;
	private JTextField textFieldAddress;
	private JTextField textFieldAgeofAgent;
	private JTextField textFieldContactNo;
	private JTable tableCommissionA_Cshowdata;
	public JTextArea textAreaNotes;

	public ResultSet rs;
	public ResultSet rs2;
	public ResultSet rs1;
	public DefaultTableModel model1;

	public Connection conn;
	public PreparedStatement ps;
	public PreparedStatement ps1;
	public PreparedStatement ps2;
	public String val1;
	public Statement st;

	java.util.Date dt1, dt2;
	private JTextField textFieldSrNo;
	private JTextField textFieldSrNoMaster;
	private JTextField textFieldAccountNumber;
	public JDateChooser dateChooserCommissionA_C ;

	public JRadioButton rdbtnSavingAc;

	public static int srno;
	public static String cusname;
	public static String datecomm;
	public static String acnocomm;
	public static String addcomm;
	public static String agecomm;
	public static String contactnocomm;
	public static String notecomm;
	public JButton button_3;
	public JScrollPane scrollPane_1;
	public JList<String> listName;
	public JScrollPane scrollPaneName;
	public String[] data = new String[1000];
	private JTextField textFieldInterst;
	public static String sainterst;
	public static String name;
	public DecimalFormat df=new DecimalFormat("#.##");
	private JTextField textFieldTotalAmtB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CommissionA_C frame = new CommissionA_C();
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
	public CommissionA_C() {
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
				textFieldNameofAgent.requestFocus();
				totalamount();
			}
		});

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1154, 76);
		contentPane.add(panel);

		JLabel lblCommissionAc = new JLabel("Commission A/C");
		lblCommissionAc.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCommissionAc.setBounds(22, 11, 276, 42);
		panel.add(lblCommissionAc);

		JLabel label_1 = new JLabel("Date:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(779, 50, 81, 17);
		panel.add(label_1);

		Date date = new Date();
		 dateChooserCommissionA_C = new JDateChooser();
		dateChooserCommissionA_C.setDateFormatString("dd-MM-yyyy");
		dateChooserCommissionA_C.setBounds(859, 51, 196, 20);
		dateChooserCommissionA_C.setDate(date);
		panel.add(dateChooserCommissionA_C);

		textFieldSrNo = new JTextField();
		textFieldSrNo.setVisible(false);
		textFieldSrNo.setEnabled(false);
		textFieldSrNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldSrNo.setColumns(10);
		textFieldSrNo.setBounds(206, 25, 187, 23);
		panel.add(textFieldSrNo);
		
		
		
		
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); //$NON-NLS-1$
	        getRootPane().getActionMap().put("Cancel", new AbstractAction(){ //$NON-NLS-1$
	            public void actionPerformed(ActionEvent e)
	            {
	            	close();
	            }

				private void close() {
					dispose();
					
				}
	        });

		textFieldSrNoMaster = new JTextField();
		textFieldSrNoMaster.setVisible(false);
		textFieldSrNoMaster.setEnabled(false);
		textFieldSrNoMaster.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldSrNoMaster.setColumns(10);
		textFieldSrNoMaster.setBounds(411, 25, 187, 23);
		increment();
		panel.add(textFieldSrNoMaster);

		textFieldAccountNumber = new JTextField();
		textFieldAccountNumber.setEditable(false);
		textFieldAccountNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		textFieldAccountNumber.setColumns(10);
		textFieldAccountNumber.setBounds(136, 53, 187, 23);
		increment();
		panel.add(textFieldAccountNumber);

		JLabel lblAccountNumber = new JLabel("Account Number:");
		lblAccountNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAccountNumber.setBounds(32, 56, 85, 17);
		panel.add(lblAccountNumber);
		
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
		button.setIcon(new ImageIcon(CommissionA_C.class.getResource("/ImageButtonIcon/clac.png")));
		button.setOpaque(false);
		button.setForeground(Color.RED);
		button.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBounds(1074, 4, 64, 55);
		panel.add(button);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 87, 1154, 99);
		contentPane.add(panel_1);

		JLabel lblNameOfAgent = new JLabel("Name of Agent:");
		lblNameOfAgent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNameOfAgent.setBounds(10, 9, 85, 17);
		panel_1.add(lblNameOfAgent);

		textFieldNameofAgent = new JTextField();
		textFieldNameofAgent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == 40) {
					listName.requestFocus();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

				try {

					conn = DBConnection.getConnection();
					String query = "select * from banksystem.commissiona_c where Name like '"
							+ textFieldNameofAgent.getText() + "%'";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					int i = 0;
					while (rs.next()) {
						String name = rs.getString("Name");

						data[i] = name;
						i++;

					}
					listName.setListData(data);
					listName.setVisibleRowCount(100);

					scrollPaneName.setVisible(true);
					listName.setVisible(true);
					scrollPane_1.setVisible(false);

					

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
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
		});
		textFieldNameofAgent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldNameofAgent.setColumns(10);
		textFieldNameofAgent.setBounds(114, 6, 187, 23);
		panel_1.add(textFieldNameofAgent);

		JLabel label_2 = new JLabel("Address:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(311, 9, 81, 17);
		panel_1.add(label_2);

		textFieldAddress = new JTextField();
		textFieldAddress.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				scrollPaneName.setVisible(false);
				listName.setVisible(false);
				scrollPane_1.setVisible(true);
			}
		});
		textFieldAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(369, 6, 209, 23);
		panel_1.add(textFieldAddress);

		JLabel label_3 = new JLabel("Age:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(588, 11, 36, 17);
		panel_1.add(label_3);

		textFieldAgeofAgent = new JTextField();
		textFieldAgeofAgent.setText("0");
		textFieldAgeofAgent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		textFieldAgeofAgent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldAgeofAgent.setColumns(10);
		textFieldAgeofAgent.setBounds(618, 8, 176, 23);
		panel_1.add(textFieldAgeofAgent);

		JLabel label_4 = new JLabel("Contact No:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(804, 11, 85, 17);
		panel_1.add(label_4);

		textFieldContactNo = new JTextField();
		textFieldContactNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '+') || (c == '-') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldContactNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldContactNo.setColumns(10);
		textFieldContactNo.setBounds(882, 8, 209, 23);
		panel_1.add(textFieldContactNo);

		JLabel label_5 = new JLabel("Note:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setBounds(10, 37, 59, 17);
		panel_1.add(label_5);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(114, 37, 464, 51);
		panel_1.add(scrollPane_1);

		textAreaNotes = new JTextArea();
		textAreaNotes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == 9) {
					rdbtnSavingAc.requestFocus();

				}
			}
		});
		scrollPane_1.setViewportView(textAreaNotes);

		scrollPaneName = new JScrollPane();
		scrollPaneName.setVisible(false);
		scrollPaneName.setBounds(114, 31, 187, 68);
		panel_1.add(scrollPaneName);

		listName = new JList<String>();
		listName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					textFieldNameofAgent.setText(listName.getSelectedValue());
					String accountnotop = textFieldNameofAgent.getText();

					scrollPaneName.setVisible(false);
					listName.setVisible(false);
					scrollPane_1.setVisible(true);

					try {
						conn = DBConnection.getConnection();
						String query = "Select * from banksystem.commissiona_c where Name=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldNameofAgent.getText());
						rs = ps.executeQuery();
						while (rs.next()) {

							String Srnomaster = rs.getString("SrNoMaster");
							textFieldSrNoMaster.setText(Srnomaster);
							String UpdateAmtDate = rs.getString("CommisstionPaidDate");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date dd = sdf.parse(UpdateAmtDate);
							dateChooserCommissionA_C.setDate(dd);
							String AccountNumber = rs.getString("Name");
							textFieldNameofAgent.setText(AccountNumber);
							String Name = rs.getString("AccountNo");
							textFieldAccountNumber.setText(Name);
							String Address = rs.getString("Address");
							textFieldAddress.setText(Address);
							String Age = rs.getString("Age");
							textFieldAgeofAgent.setText(Age);
							String ContactNo = rs.getString("ContactNo");
							textFieldContactNo.setText(ContactNo);

							String Days = rs.getString("Note");
							textAreaNotes.setText(Days);
							String SavingAcint = rs.getString("SavingAcint");
							textFieldInterst.setText(SavingAcint);

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
		});
		listName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				textFieldNameofAgent.setText(listName.getSelectedValue());
				String accountnotop = textFieldNameofAgent.getText();

				scrollPaneName.setVisible(false);
				listName.setVisible(false);
				scrollPane_1.setVisible(true);

				try {
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.commissiona_c where Name=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldNameofAgent.getText());
					rs = ps.executeQuery();
					while (rs.next()) {

						String Srnomaster = rs.getString("SrNoMaster");
						textFieldSrNoMaster.setText(Srnomaster);
						String UpdateAmtDate = rs.getString("CommisstionPaidDate");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date dd = sdf.parse(UpdateAmtDate);
						dateChooserCommissionA_C.setDate(dd);
						String AccountNumber = rs.getString("Name");
						textFieldNameofAgent.setText(AccountNumber);
						String Name = rs.getString("AccountNo");
						textFieldAccountNumber.setText(Name);
						String Address = rs.getString("Address");
						textFieldAddress.setText(Address);
						String Age = rs.getString("Age");
						textFieldAgeofAgent.setText(Age);
						String ContactNo = rs.getString("ContactNo");
						textFieldContactNo.setText(ContactNo);

						String Days = rs.getString("Note");
						textAreaNotes.setText(Days);
						
						String SavingAcint = rs.getString("SavingAcint");
						textFieldInterst.setText(SavingAcint);

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
		});
		listName.setVisible(false);
		scrollPaneName.setViewportView(listName);

		rdbtnSavingAc = new JRadioButton("Create a saving acount.");
		rdbtnSavingAc.setSelected(true);
		rdbtnSavingAc.setBounds(588, 65, 163, 23);
		panel_1.add(rdbtnSavingAc);
		
		JLabel label = new JLabel("Saving A/C Interst %:");
		label.setBounds(803, 69, 142, 14);
		panel_1.add(label);
		
		textFieldInterst = new JTextField();
		textFieldInterst.setText("1");
		textFieldInterst.setColumns(10);
		textFieldInterst.setBounds(928, 65, 108, 22);
		panel_1.add(textFieldInterst);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(0, 268, 1154, 386);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 1134, 364);
		panel_3.add(scrollPane);

		tableCommissionA_Cshowdata = new JTable();
		tableCommissionA_Cshowdata.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				try
				{
				int i = tableCommissionA_Cshowdata.getSelectedRow();
				model1 = (DefaultTableModel) tableCommissionA_Cshowdata.getModel();
				textFieldSrNoMaster.setText(model1.getValueAt(i, 1).toString());
				
				textFieldSrNo.setText(model1.getValueAt(i, 2).toString());
				//dateChooserCommissionA_C.setDateFormatString(model1.getValueAt(i, 3).toString());
				textFieldAccountNumber.setText(model1.getValueAt(i, 5).toString());
				textFieldNameofAgent.setText(model1.getValueAt(i, 6).toString());
				textFieldInterst.setText(model1.getValueAt(i, 9).toString());
				textFieldAddress.setText(model1.getValueAt(i, 10).toString());
				textFieldAgeofAgent.setText(model1.getValueAt(i, 11).toString());
				textFieldContactNo.setText(model1.getValueAt(i, 12).toString());
				textAreaNotes.setText(model1.getValueAt(i, 13).toString());
				
				
				
				}
				catch(Exception es4)
				{
					System.out.println(es4);
				}
				try
				{	
						for (int i = 0; i <tableCommissionA_Cshowdata.getRowCount(); i++) {
							Boolean che = Boolean.valueOf(tableCommissionA_Cshowdata.getValueAt(i, 0).toString());
							if (che) {
								
								
								conn = DBConnection.getConnection();
								String newname=textFieldNameofAgent.getText();
								String secounname = null;
								double alltoal = 0;
								double oldamount = 0;
								String upsadata = "select * from banksystem.saving;";
								ps = conn.prepareStatement(upsadata);
								rs = ps.executeQuery();
								while (rs.next()) {
									secounname = rs.getString("Name");
									
									if (secounname.equals(newname)) {
										rdbtnSavingAc.setEnabled(false);
										break;
									}
									else
									{
										rdbtnSavingAc.setEnabled(true);
										
									}
							
									
								}
								
								
							}
							}
					} catch (Exception es) {
						System.out.println(es.getMessage());
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
				
				try
				{
					che();
				}
				catch(Exception ew)
				{}
				
				try
				{
					if(rdbtnSavingAc.isEnabled()==true)
					{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						dt1 = dateChooserCommissionA_C.getDate();
						String date = (String) sdf.format(dateChooserCommissionA_C.getDate());
						java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());

						int reply = JOptionPane.showConfirmDialog(null, "Create a new saving account", "Saving account",
								JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							try
							{
							int maxxso44 = 0;
							conn=DBConnection.getConnection();
							String maxsr = "select max(SrnoMaster) from banksystem.saving;";
							ps = conn.prepareStatement(maxsr);
							rs = ps.executeQuery();
							while (rs.next()) {
								maxxso44 = rs.getInt(1) + 1;
							}
							

							String insa = "insert into banksystem.saving (SrnoMaster, Srno, Date,UpdateAmtDate, Name, AcountNumber, Address, Age, ContactNo,Note,Interist,OpningAmount) values(?,?,?,?,?,?,?,?,?,?,?,?);";
							ps2 = conn.prepareStatement(insa);
							ps2.setInt(1, maxxso44);
							ps2.setInt(2, maxxso44);
							ps2.setDate(3, (java.sql.Date) d);
							ps2.setDate(4, (java.sql.Date) d);
							ps2.setString(5, textFieldNameofAgent.getText());
							ps2.setString(6, "S" + maxxso44);
							ps2.setString(7, textFieldAddress.getText());
							ps2.setDouble(8, Double.valueOf(textFieldAgeofAgent.getText()));
							ps2.setString(9, textFieldContactNo.getText());
							ps2.setString(10, "This is Employee.");
							ps2.setDouble(11, Double.valueOf(textFieldInterst.getText()));
							ps2.setDouble(12, 0);
							int is = ps2.executeUpdate();
							if (is > 0) {

								int issrno77aa = 0;
								conn = (Connection) DBConnection.getConnection();
								String maxnoaa = "select max(SrNoMaster) from banksystem.savingtranscation;";
								ps1 = conn.prepareStatement(maxnoaa);
								rs1 = ps1.executeQuery();
								while (rs1.next()) {
									issrno77aa = rs1.getInt(1) + 1;

								}
								rs1.close();
								ps1.close();
								String insertdataaa = "insert into banksystem.savingtranscation (SrNoMaster, SrNo, Date,Name, AccountNo, TransactionParticulars,Amount) values(?,?,?,?,?,?,?);";
								ps2 = conn.prepareStatement(insertdataaa);
								ps2.setInt(1, issrno77aa);
								ps2.setInt(2, 1);
								SimpleDateFormat sdf4aa = new SimpleDateFormat("yyyy-MM-dd");
								dt1 = dateChooserCommissionA_C.getDate();
								String date4aa = (String) sdf4aa.format(dateChooserCommissionA_C.getDate());
								java.sql.Date d4s = new java.sql.Date(sdf4aa.parse(date4aa).getTime());
								ps2.setDate(3, (java.sql.Date) d4s);
								ps2.setString(4, textFieldNameofAgent.getText());
								ps2.setString(5, "S" + issrno77aa);
								ps2.setString(6, "Agent opening account");
								ps2.setDouble(7, 0);

								ps2.executeUpdate();
								ps2.close();
								
								JOptionPane.showMessageDialog(null,
										"Thank you for creating saving account \n" + "Name="
												+ textFieldNameofAgent.getText() + "\n" + "A/C No.=" + "S"
												+ issrno77aa);
							}
							}
							catch(Exception w)
							{
								System.out.println(w.getMessage());
							}
							finally{
								try
								{
									rs.close();
									rs1.close();
									ps.close();
									ps1.close();
									ps2.close();
									conn.close();
									
								}
								catch(Exception ew)
								{
									System.out.println(ew.getMessage());
								}
							}

							}
							
						}
						
							
				}
				catch(Exception ew)
				{}
				
				
			}

			private void che() {
				boolean s = false;
				boolean s1 = true;
				String inc = null;
				int row = 0;
				int rows = tableCommissionA_Cshowdata.getRowCount() - 1;

				for (int i = 0; i < tableCommissionA_Cshowdata.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableCommissionA_Cshowdata.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableCommissionA_Cshowdata.getSelectedRow();
						int col = tableCommissionA_Cshowdata.getSelectedColumn();
						tableCommissionA_Cshowdata.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableCommissionA_Cshowdata.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableCommissionA_Cshowdata.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableCommissionA_Cshowdata.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableCommissionA_Cshowdata.setValueAt((Object) s, 0, 0);
							tableCommissionA_Cshowdata.setValueAt((Object) s1, row, 0);
						}
					}

					else {
					}
				}
				
			}
		});
		tableCommissionA_Cshowdata.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableCommissionA_Cshowdata.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableCommissionA_Cshowdata.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Select", "SrNOMaster", "SrNo", "Date", "Update Date", "A/c No", "Name", "Amt", "Saving Status", "Saving Int %", "Address", "Age", "Contact No", "Notes"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableCommissionA_Cshowdata.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(0).setMinWidth(50);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(1).setPreferredWidth(0);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(1).setMinWidth(0);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(1).setMaxWidth(0);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(2).setMinWidth(50);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(3).setMinWidth(8);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(4).setPreferredWidth(80);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(4).setMinWidth(80);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(5).setPreferredWidth(80);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(5).setMinWidth(80);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(6).setPreferredWidth(150);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(6).setMinWidth(150);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(7).setPreferredWidth(80);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(7).setMinWidth(80);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(10).setPreferredWidth(150);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(10).setMinWidth(150);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(11).setPreferredWidth(80);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(11).setMinWidth(80);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(12).setPreferredWidth(80);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(12).setMinWidth(80);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(13).setPreferredWidth(150);
		tableCommissionA_Cshowdata.getColumnModel().getColumn(13).setMinWidth(150);
		showdata();
		scrollPane.setViewportView(tableCommissionA_Cshowdata);

		JButton button_1 = new JButton("Report");
		button_1.setIcon(new ImageIcon(CommissionA_C.class.getResource("/ImageButtonIcon/Report4d.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for (int i = 0; i <= tableCommissionA_Cshowdata.getRowCount(); i++) {
						Boolean che = Boolean.valueOf(tableCommissionA_Cshowdata.getValueAt(i, 0).toString());
						if (che) {
							java.io.InputStream file=getClass().getResourceAsStream("/Reports/Commisstion.jrxml");
							String accno4 = String.valueOf(tableCommissionA_Cshowdata.getValueAt(i, 5).toString());
							HashMap para = new HashMap();
							para.put("Acno", accno4);
							allinonereport r = new allinonereport("Agent Report",
										file,para);
								break;
							}
						

					}
				} catch (Exception ew) {
					ew.printStackTrace();
				}
			}
		});
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBounds(10, 666, 147, 38);
		contentPane.add(button_1);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(0, 197, 1154, 60);
		contentPane.add(panel_4);

		button_3 = new JButton("Save");
		button_3.setIcon(new ImageIcon(CommissionA_C.class.getResource("/ImageButtonIcon/Save.png")));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					conn = DBConnection.getConnection();
					String in = "insert into banksystem.commissiona_c(SrNoMaster, SrNo, Date,CommisstionPaidDate,Name, AccountNo, Address, Age, ContactNo, Note,SavingAcint) values(?,?,?,?,?,?,?,?,?,?,?);";
					ps = conn.prepareStatement(in);
					ps.setInt(1, Integer.parseInt(textFieldSrNoMaster.getText()));
					ps.setInt(2, Integer.parseInt(textFieldSrNoMaster.getText()));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserCommissionA_C.getDate();
					String date = (String) sdf.format(dateChooserCommissionA_C.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(3, (java.sql.Date) d);
					ps.setDate(4, (java.sql.Date) d);
					ps.setString(5, textFieldNameofAgent.getText());
					ps.setString(6, textFieldAccountNumber.getText());
					ps.setString(7, textFieldAddress.getText());
					ps.setDouble(8, Double.valueOf(textFieldAgeofAgent.getText()));
					ps.setString(9, textFieldContactNo.getText());
					ps.setString(10, textAreaNotes.getText());
					ps.setDouble(11, Double.valueOf(textFieldInterst.getText()));
					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are saved.");
						tabledataview();
						totalamount();

						try {
							int issrno77 = 0;
							conn = (Connection) DBConnection.getConnection();
							String maxno = "select max(SrNoMaster) from banksystem.commissiona_ctrancation;";
							ps1 = conn.prepareStatement(maxno);
							rs1 = ps1.executeQuery();
							while (rs1.next()) {
								issrno77 = rs1.getInt(1) + 1;

							}
							rs1.close();
							ps1.close();
							String insertdata = "insert into banksystem.commissiona_ctrancation (SrNoMaster, SrNo, Date, Name, AccountNo) values(?,?,?,?,?);";
							ps2 = conn.prepareStatement(insertdata);
							ps2.setInt(1, issrno77);
							ps2.setInt(2, 1);
							SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooserCommissionA_C.getDate();
							String date4 = (String) sdf4.format(dateChooserCommissionA_C.getDate());
							java.sql.Date d4 = new java.sql.Date(sdf.parse(date4).getTime());
							ps2.setDate(3, (java.sql.Date) d4);
							ps2.setString(4, textFieldNameofAgent.getText());
							ps2.setString(5, textFieldAccountNumber.getText());
							int iii = ps2.executeUpdate();
							ps2.close();
							if (iii > 0) {
								if (rdbtnSavingAc.isSelected()) {

									int maxxso44 = 0;
									String maxsr = "select max(SrnoMaster) from banksystem.saving;";
									ps1 = conn.prepareStatement(maxsr);
									rs1 = ps1.executeQuery();
									while (rs1.next()) {
										maxxso44 = rs1.getInt(1) + 1;
									}
									rs1.close();
									ps1.close();

									String insa = "insert into banksystem.saving (SrnoMaster, Srno, Date,UpdateAmtDate, Name, AcountNumber, Address, Age, ContactNo,Note,Interist,OpningAmount) values(?,?,?,?,?,?,?,?,?,?,?,?);";
									ps2 = conn.prepareStatement(insa);
									ps2.setInt(1, maxxso44);
									ps2.setInt(2, maxxso44);
									ps2.setDate(3, (java.sql.Date) d);
									ps2.setDate(4, (java.sql.Date) d);
									ps2.setString(5, textFieldNameofAgent.getText());
									ps2.setString(6, "S" + maxxso44);
									ps2.setString(7, textFieldAddress.getText());
									ps2.setDouble(8, Double.valueOf(textFieldAgeofAgent.getText()));
									ps2.setString(9, textFieldContactNo.getText());
									ps2.setString(10, "This is Employee.");
									ps2.setDouble(11, Double.valueOf(textFieldInterst.getText()));
									ps2.setDouble(12, 0);
									int is = ps2.executeUpdate();
									if (is > 0) {

										int issrno77aa = 0;
										conn = (Connection) DBConnection.getConnection();
										String maxnoaa = "select max(SrNoMaster) from banksystem.savingtranscation;";
										ps1 = conn.prepareStatement(maxnoaa);
										rs1 = ps1.executeQuery();
										while (rs1.next()) {
											issrno77aa = rs1.getInt(1) + 1;

										}
										rs1.close();
										ps1.close();
										String insertdataaa = "insert into banksystem.savingtranscation (SrNoMaster, SrNo, Date,Name, AccountNo, TransactionParticulars,Amount) values(?,?,?,?,?,?,?);";
										ps2 = conn.prepareStatement(insertdataaa);
										ps2.setInt(1, issrno77aa);
										ps2.setInt(2, 1);
										SimpleDateFormat sdf4aa = new SimpleDateFormat("yyyy-MM-dd");
										dt1 = dateChooserCommissionA_C.getDate();
										String date4aa = (String) sdf4aa.format(dateChooserCommissionA_C.getDate());
										java.sql.Date d4s = new java.sql.Date(sdf4aa.parse(date4aa).getTime());
										ps2.setDate(3, (java.sql.Date) d4s);
										ps2.setString(4, textFieldNameofAgent.getText());
										ps2.setString(5, "S" + issrno77aa);
										ps2.setString(6, "Agent opening account");
										ps2.setDouble(7,0);

										ps2.executeUpdate();
										ps2.close();
										
										JOptionPane.showMessageDialog(null,
												"Thank you for creating saving account \n" + "Name="
														+ textFieldNameofAgent.getText() + "\n" + "A/C No.=" + "S"
														+ issrno77aa);
										
										

									}
								}
							}

						} catch (Exception ee) {
							System.out.println(ee.getMessage());
						}
					}

					rs.close();
					ps.close();
					conn.close();

				} catch (Exception ee) {
					System.out.print(ee.getMessage());
				}
				finally
				{
					try
					{
						rs.close();
						rs1.close();
						ps.close();
						ps1.close();
						ps2.close();
						conn.close();
					}
					catch(Exception ew)
					{
						System.out.println(ew.getMessage());
					}
				}
				try
				{
					reset();
				}
				catch(Exception ew)
				{
					ew.printStackTrace();
				}

			}

			private void tabledataview() {

				String data0 = textFieldSrNoMaster.getText();
				String data1 = textFieldSrNo.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				dt1 = dateChooserCommissionA_C.getDate();
				String data2 = (String) sdf.format(dateChooserCommissionA_C.getDate());
				String data3 = (String) sdf.format(dateChooserCommissionA_C.getDate());
				String data4 = textFieldAccountNumber.getText();
				String data5 = textFieldNameofAgent.getText();
				String data6="0";
				String data7=textFieldInterst.getText();
				
				if (data7.equals("")||data7.equals("0"))
				{
				    data7="N";
				}
				else
				{
					data7="Y";
				}
				String data8=textFieldInterst.getText();
				String data9 = textFieldAddress.getText();
				String data10 = textFieldAgeofAgent.getText();
				String data11 = textFieldContactNo.getText();
				String data12 = textAreaNotes.getText();
				
				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8,data9,data10,data11,data12 };
				model1 = (DefaultTableModel) tableCommissionA_Cshowdata.getModel();
				model1.addRow(row);

			}
		});
		button_3.setHorizontalAlignment(SwingConstants.LEADING);
		button_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_3.setBounds(216, 11, 115, 38);
		panel_4.add(button_3);

		JButton button_4 = new JButton("Update");
		button_4.setIcon(new ImageIcon(CommissionA_C.class.getResource("/ImageButtonIcon/update.png")));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String up = "UPDATE banksystem.commissiona_c  set Date=?, Name=?,AccountNo=?, Address=?, Age=?, ContactNo=?, Note=? where SrNoMaster=?";
					ps = conn.prepareStatement(up);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooserCommissionA_C.getDate();
					String date = (String) sdf.format(dateChooserCommissionA_C.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					ps.setDate(1, (java.sql.Date) d);
					ps.setString(2, textFieldNameofAgent.getText());
					ps.setString(3, textFieldAccountNumber.getText());
					ps.setString(4, textFieldAddress.getText());
					ps.setDouble(5, Double.valueOf(textFieldAgeofAgent.getText()));
					ps.setString(6, textFieldContactNo.getText());
					ps.setString(7, textAreaNotes.getText());
					ps.setInt(8, Integer.parseInt(textFieldSrNoMaster.getText()));

					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are update.");
						reset();
						dispose();
						CommissionA_C sc = new CommissionA_C();
						sc.setUndecorated(true);
						sc.setVisible(true);
						increment();
					}

				} catch (Exception e2) {
					System.out.print(e2.getMessage());
				}
				finally
				{
					try
					{
						
						ps.close();
						conn.close();
					}
					catch(Exception ew)
					{
						System.out.println(ew.getMessage());
					}
				}

			}
		});
		button_4.setToolTipText("");
		button_4.setHorizontalAlignment(SwingConstants.LEADING);
		button_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_4.setBounds(341, 11, 126, 38);
		panel_4.add(button_4);

		JButton button_5 = new JButton("Delete");
		button_5.setIcon(new ImageIcon(CommissionA_C.class.getResource("/ImageButtonIcon/delete.jpg")));
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DBConnection.getConnection();
					String de1 = "delete from banksystem.commissiona_ctrancation where AccountNo='"+textFieldAccountNumber.getText()+"'";
					ps=conn.prepareStatement(de1);
					int ii=ps.executeUpdate();
					if(ii>0)
					{
					String de = "delete from banksystem.commissiona_c where SrNoMaster=?";
					ps = conn.prepareStatement(de);
					ps.setInt(1, Integer.parseInt(textFieldSrNoMaster.getText()));
					int i = ps.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Data are deleted.");
						reset();
						dispose();
						CommissionA_C fd = new CommissionA_C();
						fd.setUndecorated(true);
						fd.setVisible(true);
						increment();
					}

					

				}
				}catch (Exception qe) {
					System.out.print(qe.getMessage());
				}
				finally
				{
					try
					{
						ps.close();
						conn.close();
					}
					catch(Exception ew)
					{
						System.out.println(ew.getMessage());
					}
				}
			}
		});
		button_5.setHorizontalAlignment(SwingConstants.LEADING);
		button_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_5.setBounds(474, 11, 115, 38);
		panel_4.add(button_5);

		JButton button_6 = new JButton("Reset");
		button_6.setIcon(new ImageIcon(CommissionA_C.class.getResource("/ImageButtonIcon/Reset5.jpg")));
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		button_6.setHorizontalAlignment(SwingConstants.LEADING);
		button_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_6.setBounds(599, 11, 115, 38);
		panel_4.add(button_6);

		JButton button_7 = new JButton("Exit");
		button_7.setIcon(new ImageIcon(CommissionA_C.class.getResource("/ImageButtonIcon/Exit.png")));
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Welcome.frame.enable();
			}
		});
		button_7.setHorizontalAlignment(SwingConstants.LEADING);
		button_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_7.setBounds(724, 11, 115, 38);
		panel_4.add(button_7);

		JButton btnVies = new JButton("View Customer and Payment");
		btnVies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					for (int i = 0; i <= tableCommissionA_Cshowdata.getRowCount(); i++) {

						Boolean che = Boolean.valueOf(tableCommissionA_Cshowdata.getValueAt(i, 0).toString());

						if (che) {
							sainterst=String.valueOf((String) tableCommissionA_Cshowdata.getValueAt(i, 9));
							srno = Integer.parseInt((String) tableCommissionA_Cshowdata.getValueAt(i, 1));
							cusname = (String) (tableCommissionA_Cshowdata.getValueAt(i, 6).toString());
							datecomm = (String) (tableCommissionA_Cshowdata.getValueAt(i, 3).toString());
							acnocomm = (String) (tableCommissionA_Cshowdata.getValueAt(i, 5).toString());
							addcomm = (String) (tableCommissionA_Cshowdata.getValueAt(i, 10).toString());
							agecomm = (String) (tableCommissionA_Cshowdata.getValueAt(i, 11).toString());
							contactnocomm = (String) (tableCommissionA_Cshowdata.getValueAt(i, 12).toString());
							notecomm = (String) (tableCommissionA_Cshowdata.getValueAt(i, 13).toString());
							if (cusname != null) {
								AgentCustomerList ag = new AgentCustomerList();
								ag.setUndecorated(true);
								ag.setVisible(true);
							}
						}
					}

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			
			try {
				for (int i = 0; i <= tableCommissionA_Cshowdata.getRowCount(); i++) {
					Boolean che = Boolean.valueOf(tableCommissionA_Cshowdata.getValueAt(i, 0).toString());
					if (che) {
						 name = String.valueOf(tableCommissionA_Cshowdata.getValueAt(i, 6).toString());
						 
						
						 }
				}
			} catch (Exception ew) {
				ew.printStackTrace();
			}
			}

		});
		btnVies.setIcon(new ImageIcon(CommissionA_C.class.getResource("/ImageButtonIcon/View4.png")));
		btnVies.setHorizontalAlignment(SwingConstants.LEADING);
		btnVies.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVies.setBounds(167, 665, 323, 38);
		contentPane.add(btnVies);
		
		JLabel lblTotalAmount = new JLabel("Total Amount:");
		lblTotalAmount.setBounds(875, 668, 127, 14);
		contentPane.add(lblTotalAmount);
		
		textFieldTotalAmtB = new JTextField();
		textFieldTotalAmtB.setForeground(Color.RED);
		textFieldTotalAmtB.setEditable(false);
		textFieldTotalAmtB.setColumns(10);
		textFieldTotalAmtB.setBounds(959, 665, 132, 20);
		contentPane.add(textFieldTotalAmtB);
	}

	public void increment() {
		try {

			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("select max(SrNoMaster) from banksystem.commissiona_c;");
			 rs = ps.executeQuery();
			while (rs.next()) {
				int val = (rs.getInt(1) + 1);
				val1 = String.valueOf(val);
			}
			textFieldSrNoMaster.setText(val1);
			textFieldSrNo.setText(val1);
			textFieldAccountNumber.setText("Comm" + val1);
			
			SimpleDateFormat insdf=new SimpleDateFormat("yyyy-MM-dd");
			Date indate=new Date();
			String instringdate=insdf.format(indate);
		dateChooserCommissionA_C.setDate(insdf.parse(instringdate));

			

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

	public void reset() {
increment();
		textAreaNotes.setText("");
		textFieldAddress.setText("");
		textFieldAgeofAgent.setText("0");
		textFieldContactNo.setText("");
		textFieldNameofAgent.setText("");

	}

	public void showdata() {
		try {
			conn = DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.commissiona_c order by SrNoMaster;";
			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {
				String data0 = rs.getString("SrNoMaster");
				String data1 = rs.getString("SrNo");
				String data2 = rs.getString("Date");
				String data3=rs.getString("CommisstionPaidDate");
				String data4 = rs.getString("AccountNo");
				String data5 = rs.getString("Name");
				String data6=rs.getString("CommisstionAmt");
				String data7=null;
				double amount = rs.getDouble("SavingAcint");
				
				String data8=df.format(amount);
				if(data8.equals("")||data8.equals("0"))
				{
					data7="N";
					
				}
				else
				{
					data7="Y";
				}
				String data9 = rs.getString("Address");
				String data10 = rs.getString("Age");
				String data11 = rs.getString("ContactNo");
				String data12 = rs.getString("Note");
				

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6, data7, data8,data9,data10,data11,data12 };
				model1 = (DefaultTableModel) tableCommissionA_Cshowdata.getModel();
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
	public void totalamount() {
		int rowsCount = tableCommissionA_Cshowdata.getRowCount();
		double sum = 0;

		for (int i = 0; i < rowsCount; i++) {
			sum = sum + Double.valueOf(tableCommissionA_Cshowdata.getValueAt(i, 7).toString());
		}
		textFieldTotalAmtB.setText(String.valueOf(df.format(sum)));

	}
}
