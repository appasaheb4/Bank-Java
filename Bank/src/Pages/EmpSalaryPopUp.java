package Pages;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class EmpSalaryPopUp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldName;
	private JTextField textFieldSalary;
	private JTextField textFieldAdvancePay;
	private JTextField textFieldLeaves;
	private JTextField textFieldLeavesAmt;
	private JTextField textFieldTotalAmount;
	public Connection conn;
	public PreparedStatement ps;
	public PreparedStatement ps1;
	public PreparedStatement ps2;
	public PreparedStatement ps3;
	public String val1;

	public ResultSet rs;
	public ResultSet rs1;
	public ResultSet rs2;

	java.util.Date dt1, dt2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JRadioButton rdbtnTranSaving;
	public JRadioButton rdbtnPayByCheque;
	public JRadioButton rdbtnPayByCash;
	private JTextField textFieldMode;
	public static int maxso;
	public JDateChooser dateChooseremppopup;
	public static int maxxsonumber = 0;
	public static int val = 0;
	public JTextField textFieldChequeNo;
	public JLabel lblChequqNo;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel lblNewLabel_1;
	private JTextField textFieldInterst;
	DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EmpSalaryPopUp dialog = new EmpSalaryPopUp();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EmpSalaryPopUp() {
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
				dateChooseremppopup.requestDefaultFocus();
				textFieldName.setText(Employee.emp_name);
				textFieldSalary.setText(Employee.emp_salary);

				textFieldAdvancePay.setText(Employee.emp_adpay);
				textFieldLeaves.setText(Employee.emp_leaves);

			}
		});
		setBounds(100, 100, 508, 374);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(10, 30, 125, 14);
		contentPanel.add(lblNewLabel);

		textFieldName = new JTextField();
		textFieldName.setEditable(false);
		textFieldName.setBounds(146, 30, 156, 20);
		contentPanel.add(textFieldName);
		textFieldName.setColumns(10);
		{
			JLabel lblSalary = new JLabel("Salary:");
			lblSalary.setBounds(10, 58, 125, 14);
			contentPanel.add(lblSalary);
		}
		{
			textFieldSalary = new JTextField();
			textFieldSalary.setEditable(false);
			textFieldSalary.setColumns(10);
			textFieldSalary.setBounds(146, 58, 156, 20);
			contentPanel.add(textFieldSalary);
		}
		{
			JLabel lblAdvancedPay = new JLabel("Advanced Pay:");
			lblAdvancedPay.setBounds(10, 86, 125, 14);
			contentPanel.add(lblAdvancedPay);
		}
		{
			textFieldAdvancePay = new JTextField();
			textFieldAdvancePay.setColumns(10);
			textFieldAdvancePay.setBounds(146, 86, 156, 20);
			contentPanel.add(textFieldAdvancePay);
		}
		
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
		{
			JLabel lblLeaves = new JLabel("Leaves:");
			lblLeaves.setBounds(10, 114, 125, 14);
			contentPanel.add(lblLeaves);
		}
		{
			textFieldLeaves = new JTextField();
			textFieldLeaves.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					calc();
				}
			});
			textFieldLeaves.setColumns(10);
			textFieldLeaves.setBounds(146, 114, 156, 20);
			contentPanel.add(textFieldLeaves);
		}
		{
			JLabel lblLeavesAmount = new JLabel("Leaves Amount:");
			lblLeavesAmount.setBounds(10, 142, 125, 14);
			contentPanel.add(lblLeavesAmount);
		}
		{
			textFieldLeavesAmt = new JTextField();
			textFieldLeavesAmt.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					calc();
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
			textFieldLeavesAmt.setColumns(10);
			textFieldLeavesAmt.setBounds(146, 142, 78, 20);
			contentPanel.add(textFieldLeavesAmt);
		}
		{
			JLabel lblTotalAmount = new JLabel("Total Amount:");
			lblTotalAmount.setBounds(10, 170, 125, 14);
			contentPanel.add(lblTotalAmount);
		}
		{
			textFieldTotalAmount = new JTextField();
			textFieldTotalAmount.setEditable(false);
			textFieldTotalAmount.setColumns(10);
			textFieldTotalAmount.setBounds(146, 170, 156, 20);
			contentPanel.add(textFieldTotalAmount);
		}

		rdbtnTranSaving = new JRadioButton("Transfer to saving account amount.");
		buttonGroup_1.add(rdbtnTranSaving);
		rdbtnTranSaving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mode();
			}
		});
		rdbtnTranSaving.setSelected(true);
		rdbtnTranSaving.setBounds(146, 197, 286, 23);
		contentPanel.add(rdbtnTranSaving);

		rdbtnPayByCheque = new JRadioButton("Pay by cheque");
		buttonGroup_1.add(rdbtnPayByCheque);
		rdbtnPayByCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode();
			}
		});
		rdbtnPayByCheque.setBounds(146, 223, 115, 23);
		contentPanel.add(rdbtnPayByCheque);

		rdbtnPayByCash = new JRadioButton("Pay by cash");
		buttonGroup_1.add(rdbtnPayByCash);
		rdbtnPayByCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode();
			}
		});
		rdbtnPayByCash.setBounds(146, 249, 115, 23);
		contentPanel.add(rdbtnPayByCash);

		textFieldMode = new JTextField();
		textFieldMode.setVisible(false);
		textFieldMode.setEditable(false);
		textFieldMode.setText("Saving Account");
		textFieldMode.setBounds(10, 208, 131, 35);
		contentPanel.add(textFieldMode);
		textFieldMode.setColumns(10);

		JLabel label = new JLabel("Date:");
		label.setBounds(10, 3, 46, 20);
		contentPanel.add(label);

		Date date = new Date();
		dateChooseremppopup = new JDateChooser();
		dateChooseremppopup.setDateFormatString("yyyy-MM-dd");
		dateChooseremppopup.setBounds(145, 5, 157, 20);
		dateChooseremppopup.setDate(date);
		contentPanel.add(dateChooseremppopup);

		lblChequqNo = new JLabel("Cheque No:");
		lblChequqNo.setVisible(false);
		lblChequqNo.setBounds(267, 227, 73, 14);
		contentPanel.add(lblChequqNo);

		textFieldChequeNo = new JTextField();
		textFieldChequeNo.setVisible(false);
		textFieldChequeNo.setBounds(350, 227, 132, 20);
		contentPanel.add(textFieldChequeNo);
		textFieldChequeNo.setColumns(10);
		{
			lblNewLabel_1 = new JLabel("Interst%:");
			lblNewLabel_1.setVisible(false);
			lblNewLabel_1.setBounds(233, 145, 59, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			textFieldInterst = new JTextField();
			textFieldInterst.setVisible(false);
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
			textFieldInterst.setBounds(289, 141, 78, 20);
			contentPanel.add(textFieldInterst);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {

							if (rdbtnTranSaving.isSelected()) {
								conn = DBConnection.getConnection();

								String newname = Employee.emp_name;
								String secounname = null;
								double alltoal = 0;
								double oldamount = 0;
								String upsadata = "select * from banksystem.saving;";
								ps = conn.prepareStatement(upsadata);
								rs = ps.executeQuery();
								while (rs.next()) {
									secounname = rs.getString("Name");
									if (secounname.equals(newname)) {

										oldamount = rs.getDouble("OpningAmount");
										String acnosaving = rs.getString("AcountNumber");
										int srnomasetsavingss = rs.getInt("SrnoMaster");
										alltoal = oldamount + Double.valueOf(textFieldTotalAmount.getText());
										double intert = Double.valueOf(Employee.intert);
										double interstamt = alltoal * intert / 100;
										double alltotal = alltoal + interstamt;
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
										dt1 = dateChooseremppopup.getDate();
										String date = (String) sdf.format(dateChooseremppopup.getDate());
										java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
										String updata = "UPDATE banksystem.saving set  UpdateAmtDate='" + (java.sql.Date) d
												+ "',  OpningAmount='" + alltoal + "',Interist='" + intert
												+ "',InterestPer='" + interstamt + "',Total='" + alltotal
												+ "' where AcountNumber='" + acnosaving + "'";

										ps3 = conn.prepareStatement(updata);
										int ss = ps3.executeUpdate();
										
										if (ss > 0) {
											try {
												int issrno77 = 0;
												int srnomaseertsa = 0;

												conn = (Connection) DBConnection.getConnection();

												String maxno77 = "select max(SrNo),SrNoMaster from banksystem.savingtranscation where AccountNo='"
														+ acnosaving + "' group by SrNoMaster;";
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
												dt1 = dateChooseremppopup.getDate();
												String date4 = (String) sdf4.format(dateChooseremppopup.getDate());
												java.sql.Date d4 = new java.sql.Date(sdf4.parse(date4).getTime());
												ps2.setDate(3, (java.sql.Date) d4);
												ps2.setString(4, textFieldName.getText());
												ps2.setString(5, acnosaving);
												ps2.setString(6, "Employee balance add");
												ps2.setDouble(7, Double.valueOf(textFieldTotalAmount.getText()));
												ps2.setDouble(8, Double.valueOf(textFieldTotalAmount.getText()));
												ps2.setDouble(9, alltoal);
												ps2.executeUpdate();
												
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
								ps2.close();
								ps3.close();
								conn.close();
							} catch (Exception ew) {
								System.out.println(ew.getMessage());
							}
						}

						try {

							if (rdbtnPayByCash.isSelected() || rdbtnPayByCheque.isSelected()
									|| rdbtnTranSaving.isSelected()) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								dt1 = dateChooseremppopup.getDate();
								String date = (String) sdf.format(dateChooseremppopup.getDate());
								java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
								
								double amountold=0;
								conn=DBConnection.getConnection();
								String inread="select * from banksystem.employees where AccountNO='"+Employee.name+"'";
								ps1=conn.prepareStatement(inread);
								rs1=ps1.executeQuery();
								while(rs1.next())
								{
									amountold=rs1.getDouble("PaidAmount");
								}
								
								double alldoubleamount=amountold+Double.valueOf(textFieldTotalAmount.getText());
								String upamountinquery="UPDATE banksystem.employees set  UpdateDate='"+(java.sql.Date) d+"',PaidAmount='"+alldoubleamount+"' where AccountNO='"+Employee.acnoemp+"'";
								ps3=conn.prepareStatement(upamountinquery);
								int is=ps3.executeUpdate();
								
								if(is>0)
								{
									
									conn = DBConnection.getConnection();
								String srnomain = Employee.acnoemp;
								
								String maxsrosss = "Select max(SrNo) from banksystem.emptrancation where AccountNo='"
										+ srnomain + "'";
								ps = conn.prepareStatement(maxsrosss);
								rs = ps.executeQuery();
								
								while (rs.next()) {
									maxxsonumber = rs.getInt(1) + 1;
								}
								

								String inserdata = "insert into banksystem.emptrancation values(?,?,?,?,?,?,?,?,?,?,?,?);";
								ps3 = conn.prepareStatement(inserdata);
								ps3.setInt(1, Integer.valueOf(Employee.srnomaster));
								ps3.setInt(2, maxxsonumber);
								
								ps3.setDate(3, (java.sql.Date) d);
								ps3.setString(4, textFieldName.getText());
								ps3.setString(5, Employee.acnoemp);
								ps3.setDouble(6, Double.valueOf(textFieldSalary.getText()));
								ps3.setDouble(7, Double.valueOf(textFieldAdvancePay.getText()));
								ps3.setString(8, textFieldLeaves.getText());
								ps3.setDouble(9, Double.valueOf(textFieldLeavesAmt.getText()));
								ps3.setDouble(10, Double.valueOf(textFieldTotalAmount.getText()));
								ps3.setString(11, textFieldMode.getText());
								ps3.setString(12, textFieldChequeNo.getText());
								ps3.executeUpdate();
								JOptionPane.showMessageDialog(null, "Thanks..");
								dispose();
								EmployeeTrancation empt = new EmployeeTrancation();
								empt.setUndecorated(true);
								empt.setVisible(true);

							}
							}

						} catch (Exception ess) {
							System.out.println(ess.getMessage());
						} finally {
							try {
								rs.close();
								rs1.close();
								ps.close();
								ps1.close();
								ps3.close();
								conn.close();
							} catch (Exception ew) {
								System.out.println(ew.getMessage());
							}
						}

						try {

							if (rdbtnTranSaving.isSelected()) {
								conn = DBConnection.getConnection();

								String newname = Employee.emp_name;
								String secounname = null;
								double alltoal = 0;
								double oldamount = 0;
								String upsadata = "select * from banksystem.saving;";
								ps = conn.prepareStatement(upsadata);
								rs = ps.executeQuery();
								while (rs.next()) {
									secounname = rs.getString("Name");

									if (!secounname.equals(newname)) {
										// int
										// replay=JOptionPane.showConfirmDialog(null,
										// "Your savin account not create.\n\n
										// Your you saving account create");
										// if (replay == JOptionPane.YES_OPTION)
										// {
										// dispose();
										// Employee emp=new Employee();
										// emp.setUndecorated(true);
										// emp.setVisible(true);
										// }
										// JOptionPane.showMessageDialog(null,
										// "your saving account not create.");
										break;
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
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void calc() {
		double payment = Double.valueOf(textFieldSalary.getText());
		double avpay = Double.valueOf(textFieldAdvancePay.getText());
		double leamt = Double.valueOf(textFieldLeavesAmt.getText());
		double allamount = payment - (avpay + leamt);
		textFieldTotalAmount.setText(String.valueOf(allamount));
		cal();
	}

	public void mode() {
		if (rdbtnTranSaving.isSelected()) {
			textFieldMode.setText("Saving Account");
			lblChequqNo.setVisible(false);
			textFieldChequeNo.setVisible(false);

		} else if (rdbtnPayByCheque.isSelected()) {
			textFieldMode.setText("Cheque");
			lblChequqNo.setVisible(true);
			textFieldChequeNo.setVisible(true);

		} else {
			textFieldMode.setText("Cash");
			lblChequqNo.setVisible(false);
			textFieldChequeNo.setVisible(false);
		}
	}

	public void cal() {
		try {
			double totalmount = Double.valueOf(textFieldTotalAmount.getText());
			double custamt = Double.valueOf(textFieldSalary.getText());
			double interstcount = (custamt / totalmount) * 100;
			textFieldInterst.setText(String.valueOf(df.format(interstcount)));
		} catch (Exception es) {
			System.out.println(es.getMessage());
		}

	}

	public void cal1() {
		try {
			double totalmount = Double.valueOf(textFieldSalary.getText());
			double inserrt = Double.valueOf(textFieldInterst.getText());

			double interstcount = totalmount * inserrt / 100;
			textFieldTotalAmount.setText(String.valueOf(df.format(totalmount + interstcount)));
		} catch (Exception es) {
			System.out.println(es.getMessage());
		}

	}
}
