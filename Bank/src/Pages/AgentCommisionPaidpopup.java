package Pages;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;



import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.awt.Font;
import javax.swing.JRadioButton;

import java.awt.Component;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class AgentCommisionPaidpopup extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldAgentName;
	private JTextField textFieldallcustamt;
	private JTextField textFieldCommAmt;
	public Connection conn;
	public PreparedStatement ps;
	public PreparedStatement ps1;
	public PreparedStatement ps2;
	public PreparedStatement ps3;
	public String val1;
	public Statement st;
	public ResultSet rs;
	public ResultSet rs1;
	public ResultSet rs2;
	public JLabel lblchequeno;

	java.util.Date dt1, dt2;
	public JRadioButton radioAmountTranSaving;
	public static int maxso;
	public static String accnosa;
	public JDateChooser dateChooseagentpopup;
	private JLabel lblNewLabel;
	private JLabel lblDate;
	private JLabel lblTotalAllCustomer;
	private JLabel lblCommissionAmount;
	public JRadioButton rdbtnByCheque;
	public JRadioButton rdbtnByCash;
	private JTextField textFieldChequeNo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldMode;
	private JTextField textFieldInterst;
	DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AgentCommisionPaidpopup dialog = new AgentCommisionPaidpopup();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AgentCommisionPaidpopup() {
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

				textFieldAgentName.setText(AgentCustomerList.lblAgentName.getText());
				textFieldallcustamt.setText(AgentCustomerList.textFieldTotalPaidAmount.getText());

				dateChooseagentpopup.requestFocus();
			}
		});
		setBounds(300, 300, 461, 348);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblNewLabel = new JLabel("Agent Name:");
			lblNewLabel.setBounds(10, 24, 76, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			textFieldAgentName = new JTextField();
			textFieldAgentName.setForeground(Color.RED);
			textFieldAgentName.setEditable(false);
			textFieldAgentName.setFont(new Font("Tahoma", Font.BOLD, 14));
			textFieldAgentName.setBounds(158, 24, 176, 20);
			contentPanel.add(textFieldAgentName);
			textFieldAgentName.setColumns(10);
		}
		{
			lblDate = new JLabel("Date");
			lblDate.setBounds(10, 55, 76, 14);
			contentPanel.add(lblDate);
		}

		Date date = new Date();
		dateChooseagentpopup = new JDateChooser();
		dateChooseagentpopup.setDateFormatString("yyyy-MM-dd");
		dateChooseagentpopup.setBounds(158, 55, 176, 20);
		dateChooseagentpopup.setDate(date);
		contentPanel.add(dateChooseagentpopup);
		{
			lblTotalAllCustomer = new JLabel("Total All Customer Amt:");
			lblTotalAllCustomer.setBounds(10, 93, 139, 14);
			contentPanel.add(lblTotalAllCustomer);
		}
		{
			textFieldallcustamt = new JTextField();
			textFieldallcustamt.setForeground(Color.RED);
			textFieldallcustamt.setEditable(false);
			textFieldallcustamt.setFont(new Font("Tahoma", Font.BOLD, 14));
			textFieldallcustamt.setColumns(10);
			textFieldallcustamt.setBounds(158, 90, 176, 20);
			contentPanel.add(textFieldallcustamt);
		}
		{
			lblCommissionAmount = new JLabel("Commission Amount:");
			lblCommissionAmount.setBounds(10, 118, 139, 14);
			contentPanel.add(lblCommissionAmount);
		}
		{
			textFieldCommAmt = new JTextField();
			textFieldCommAmt.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					cal();
				}

				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == '.') || (c == '%') || (c == KeyEvent.VK_BACK_SPACE)
							|| (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			textFieldCommAmt.setColumns(10);
			textFieldCommAmt.setBounds(158, 118, 113, 20);
			contentPanel.add(textFieldCommAmt);
		}
		{
			radioAmountTranSaving = new JRadioButton("Transfer to saving account amount.");
			radioAmountTranSaving.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mode();
				}
			});
			radioAmountTranSaving.setSelected(true);
			buttonGroup.add(radioAmountTranSaving);

			radioAmountTranSaving.setBounds(89, 195, 234, 23);
			savedata();
			contentPanel.add(radioAmountTranSaving);
		}
		{
			rdbtnByCheque = new JRadioButton("By Cheque");
			rdbtnByCheque.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mode();
				}
			});
			buttonGroup.add(rdbtnByCheque);
			rdbtnByCheque.setBounds(89, 221, 89, 23);
			contentPanel.add(rdbtnByCheque);
		}
		{
			rdbtnByCash = new JRadioButton("By Cash");
			rdbtnByCash.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mode();
				}
			});
			buttonGroup.add(rdbtnByCash);
			rdbtnByCash.setBounds(89, 247, 76, 23);
			contentPanel.add(rdbtnByCash);
		}

		lblchequeno = new JLabel("Cheque No:");
		lblchequeno.setVisible(false);
		lblchequeno.setBounds(186, 226, 85, 14);
		contentPanel.add(lblchequeno);

		textFieldChequeNo = new JTextField();
		textFieldChequeNo.setVisible(false);
		textFieldChequeNo.setBounds(257, 226, 159, 20);
		contentPanel.add(textFieldChequeNo);
		textFieldChequeNo.setColumns(10);
		{
			textFieldMode = new JTextField();
			textFieldMode.setVisible(false);
			textFieldMode.setText("By Saving");
			textFieldMode.setBounds(287, 228, 148, 37);
			contentPanel.add(textFieldMode);
			textFieldMode.setColumns(10);
		}

		JLabel lblNewLabel_1 = new JLabel("Commition Interst%:");
		lblNewLabel_1.setBounds(10, 143, 138, 14);
		contentPanel.add(lblNewLabel_1);

		textFieldInterst = new JTextField();
		textFieldInterst.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == '%') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				cal1();
			}
		});
		textFieldInterst.setColumns(10);
		textFieldInterst.setBounds(158, 140, 113, 20);
		contentPanel.add(textFieldInterst);
		contentPanel.setFocusTraversalPolicy(new CLASS.FocusTraversalOnArray(
				new Component[] { textFieldAgentName, dateChooseagentpopup, textFieldCommAmt, radioAmountTranSaving,
						lblNewLabel, lblDate, dateChooseagentpopup.getCalendarButton(), lblTotalAllCustomer,
						textFieldallcustamt, lblCommissionAmount }));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int srno = CommissionA_C.srno;
							conn = DBConnection.getConnection();
							String up = "UPDATE banksystem.commissiona_c set  CommisstionPaidDate=?, CommisstionAmt=?, TotalCustomerAmt=? where SrNoMaster='"
									+ srno + "'";
							ps = conn.prepareStatement(up);
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooseagentpopup.getDate();
							String date = (String) sdf.format(dateChooseagentpopup.getDate());
							java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
							ps.setDate(1, (java.sql.Date) d);
							ps.setString(2, textFieldCommAmt.getText());
							ps.setString(3, textFieldallcustamt.getText());
							int ii = ps.executeUpdate();

							if (ii > 0) {
								int maxso = 0;
								conn = (Connection) DBConnection.getConnection();
								String maxsoq = "select max(SrNo) from banksystem.commissiona_ctrancation where SrNoMaster="
										+ srno + ";";
								ps = conn.prepareStatement(maxsoq);
								rs = ps.executeQuery();
								while (rs.next()) {
									maxso = rs.getInt(1) + 1;

								}
								rs.close();
								ps.close();

								String inser = "insert into banksystem.commissiona_ctrancation(SrNoMaster, SrNo, Date, Name, AccountNo, PaidAmount, AllCustomerAmount,Tranmode,ChequeNo) values(?,?,?,?,?,?,?,?,?);";
								ps1 = conn.prepareStatement(inser);
								ps1.setInt(1, CommissionA_C.srno);
								ps1.setInt(2, maxso);
								ps1.setDate(3, (java.sql.Date) d);
								ps1.setString(4, textFieldAgentName.getText());
								ps1.setString(5, CommissionA_C.acnocomm);
								ps1.setDouble(6, Double.valueOf(textFieldCommAmt.getText()));
								ps1.setDouble(7, Double.valueOf(textFieldallcustamt.getText()));
								ps1.setString(8, textFieldMode.getText());
								ps1.setString(9, textFieldChequeNo.getText());
								int iis = ps1.executeUpdate();
								if (iis > 0) {
									JOptionPane.showMessageDialog(null, "Thanks....");
									dispose();
									AgentCommistionTable at = new AgentCommistionTable();
									at.setUndecorated(true);
									at.setVisible(true);
								}

							}
						} catch (Exception ee) {
							System.out.println(ee.getMessage());
						} finally {
							try {
								rs.close();
								ps.close();
								ps1.close();
								conn.close();
							} catch (Exception ew) {
								System.out.println(ew.getMessage());
							}
						}

						try {

							conn = DBConnection.getConnection();
							int savingmaxsrno = 0;
							String maxsr = "select max(SrnoMaster) from banksystem.saving;";
							ps1 = conn.prepareStatement(maxsr);
							rs1 = ps1.executeQuery();
							while (rs1.next()) {
								savingmaxsrno = rs1.getInt(1) + 1;

							}
							rs1.close();
							ps1.close();
							if (radioAmountTranSaving.isSelected()) {
								String newname = CommissionA_C.cusname;

								String secounname = null;
								double oldamount = 0;
								double alltoal = 0;
								String upsadata = "select * from banksystem.saving order by SrnoMaster;";
								ps = conn.prepareStatement(upsadata);
								rs = ps.executeQuery();
								while (rs.next()) {
									secounname = rs.getString("Name");
									String accnosss = rs.getString("AcountNumber");

									if (secounname.equals(newname)) {

										oldamount = rs.getDouble("OpningAmount");
										alltoal = oldamount + Double.valueOf(textFieldCommAmt.getText());

										double intterst = Double.valueOf(CommissionA_C.sainterst);
										double interscount = alltoal * intterst / 100;
										double totalamt = alltoal + interscount;

										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
										dt1 = dateChooseagentpopup.getDate();
										String date = (String) sdf.format(dateChooseagentpopup.getDate());
										java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());

										String updata = "UPDATE banksystem.saving set  Date='" + d
												+ "',  OpningAmount='" + alltoal + "',Interist='" + intterst
												+ "',InterestPer='" + interscount + "',Total='" + totalamt
												+ "' where AcountNumber='" + accnosss + "'";
										ps3 = conn.prepareStatement(updata);
										int ss = ps3.executeUpdate();
										ps3.close();
										if (ss > 0) {
											try {
												int issrno77 = 0;
												int srnomaseertsa = 0;

												conn = (Connection) DBConnection.getConnection();

												String maxno77 = "select max(SrNo),SrNoMaster from banksystem.savingtranscation where AccountNo='"
														+ accnosss + "' group by SrNoMaster;";
												ps1 = conn.prepareStatement(maxno77);
												rs1 = ps1.executeQuery();

												while (rs1.next()) {
													issrno77 = rs1.getInt(1) + 1;
													srnomaseertsa = rs1.getInt("SrNoMaster");

												}
												rs1.close();
												ps1.close();
												String insertdata = "insert into banksystem.savingtranscation (SrNoMaster, SrNo, Date,Name, AccountNo, TransactionParticulars, Amount,Depotie,Balance) values(?,?,?,?,?,?,?,?,?);";
												ps2 = conn.prepareStatement(insertdata);
												ps2.setInt(1, srnomaseertsa);
												ps2.setInt(2, issrno77);
												SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
												dt1 = dateChooseagentpopup.getDate();
												String date4 = (String) sdf4.format(dateChooseagentpopup.getDate());
												java.sql.Date d4 = new java.sql.Date(sdf4.parse(date4).getTime());
												ps2.setDate(3, (java.sql.Date) d4);
												ps2.setString(4, textFieldAgentName.getText());
												ps2.setString(5, accnosss);
												ps2.setString(6, "Agent balance add");
												ps2.setDouble(7, Double.valueOf(textFieldCommAmt.getText()));
												ps2.setDouble(8, Double.valueOf(textFieldCommAmt.getText()));
												ps2.setDouble(9, alltoal);
												ps2.executeUpdate();
												ps2.close();
												break;

											} catch (Exception ee) {
												System.out.println(ee.getMessage());
											}

										}

									}

								}
							}
						} catch (Exception es) {
							System.out.println(es.getMessage());
						} finally {
							try {
								rs.close();
								rs1.close();
								ps.close();
								ps1.close();
								ps2.close();
								conn.close();
							} catch (Exception ew) {
								System.out.println(ew.getMessage());
							}
						}
						try {

							if (radioAmountTranSaving.isSelected()) {
								conn = DBConnection.getConnection();

								String newname = Employee.empname;
								String secounname = null;
								double alltoal = 0;
								double oldamount = 0;
								String upsadata = "select * from banksystem.saving;";
								ps = conn.prepareStatement(upsadata);
								rs = ps.executeQuery();
								while (rs.next()) {
									secounname = rs.getString("Name");

									if (!secounname.equals(newname)) {

										// JOptionPane.showMessageDialog(null,
										// "your saving account not create.");
										// break;
									}

								}
							}
						} catch (Exception ese) {
							System.out.println(ese.getMessage());
						} finally {
							try {
								rs.close();
								ps.close();
								conn.close();
							} catch (Exception ew) {
								System.out.println(ew.getMessage());
							}
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void savedata() {
		try {

			conn = DBConnection.getConnection();
			double amoutold = 0;
			String name = CommissionA_C.acnocomm;
			String upsadata = "select * from banksystem.saving;";
			ps = conn.prepareStatement(upsadata);
			rs = ps.executeQuery();
			while (rs.next()) {
				String secounname = rs.getString("AcountNumber");
				if (secounname.equals(name)) {
					amoutold = rs.getDouble("OpningAmount");
					radioAmountTranSaving.setEnabled(false);
				}

			}
			if (radioAmountTranSaving.isEnabled() == false) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				dt1 = dateChooseagentpopup.getDate();
				String date = (String) sdf.format(dateChooseagentpopup.getDate());
				String up = "UPDATE banksystem.saving set  Date='" + date + "',  OpningAmount='" + amoutold
						+ Double.valueOf(textFieldCommAmt.getText()) + "' where AcountNumber='" + CommissionA_C.acnocomm
						+ "'";
				ps1 = conn.prepareStatement(up);
				ps1.executeUpdate();
			} else if (radioAmountTranSaving.isSelected()) {
				int maxso11 = 0;
				String maxsno = "Select max(SrNoMaster) from banksystem.saving;";
				ps2 = conn.prepareStatement(maxsno);
				rs1 = ps2.executeQuery();
				while (rs1.next()) {
					maxso11 = rs1.getInt(1) + 1;
				}
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				dt1 = dateChooseagentpopup.getDate();
				String date21 = (String) sdf1.format(dateChooseagentpopup.getDate());
				String insedata = "insert into banksystem.saving (SrnoMaster, Srno, Date, Name, AcountNumber, OpningAmount, Nomeny) values(?,?,?,?,?);";
				ps3 = conn.prepareStatement(insedata);
				ps3.setInt(1, maxso11);
				ps3.setInt(2, maxso11);
				ps3.setString(3, date21);
				ps3.setString(4, textFieldAgentName.getText());
				ps3.setString(5, CommissionA_C.acnocomm);
				ps3.setDouble(6, Double.valueOf(textFieldCommAmt.getText()));
				ps3.setString(7, "This is Agent");
				ps3.executeUpdate();
			} else {

			}

		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		} finally {
			try {
				rs.close();
				rs1.close();
				ps.close();
				ps1.close();
				ps2.close();
				ps3.close();
				conn.close();
			} catch (Exception ew) {
				System.out.println(ew.getMessage());
			}
		}

	}

	public void mode() {
		if (rdbtnByCheque.isSelected()) {
			lblchequeno.setVisible(true);
			textFieldChequeNo.setVisible(true);
			textFieldMode.setText("Cheque");

		}

		else if (radioAmountTranSaving.isSelected()) {
			textFieldMode.setText("By Saving");
			lblchequeno.setVisible(false);
			textFieldChequeNo.setVisible(false);

		}

		else {
			textFieldMode.setText("By Cahs");
			lblchequeno.setVisible(false);
			textFieldChequeNo.setVisible(false);

		}
	}

	public void cal() {
		try {
			double totalmount = Double.valueOf(textFieldallcustamt.getText());
			double custamt = Double.valueOf(textFieldCommAmt.getText());
			double interstcount = (custamt / totalmount) * 100;
			textFieldInterst.setText(String.valueOf(df.format(interstcount)));
		} catch (Exception es) {
			System.out.println(es.getMessage());
		}

	}

	public void cal1() {
		try {
			double totalmount = Double.valueOf(textFieldallcustamt.getText());
			double inserrt = Double.valueOf(textFieldInterst.getText());

			double interstcount = totalmount * inserrt / 100;
			textFieldCommAmt.setText(String.valueOf(df.format( interstcount)));
		} catch (Exception es) {
			System.out.println(es.getMessage());
		}

	}
}
