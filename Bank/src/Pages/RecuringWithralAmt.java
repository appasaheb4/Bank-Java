package Pages;

import Pages.FDA_C;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class RecuringWithralAmt extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldBalance;
	private JTextField textFieldMaturaAmt;
	private JTextField textFieldAmountRemaning;
	public static JTextField textFieldTotalday;
	private JTextField textFieldRemainsDays;
	public JLabel lblNewLabel_1;
	public JTextField textFieldSrNomaster;
	private JTextField textFieldENTERNEWINTERST;

	public Connection conn;
	public PreparedStatement ps;
	public PreparedStatement ps1;
	public PreparedStatement ps2;
	public String val1;
	public Statement st;
	public ResultSet rs;
	public JDateChooser dateChooserloangpage;
	java.util.Date dt1, dt2;
	private JTextField textFInterst;
	private JTextField textFieldWidtrawAmount;
	private JTextField textFieldNEWINTERST;
	private JTextField textFieldAmountAmount;
	int Srno = Integer.parseInt(Recurring.SrNoMaster);

	public static Double val;
	public static Double interstin;
	public DecimalFormat df = new DecimalFormat("#.##");
	private JLabel lblNewLabel_2;
	private JLabel lblTotalAmount;
	private JLabel lblNewLabel_3;
	public JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RecuringWithralAmt dialog = new RecuringWithralAmt();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RecuringWithralAmt() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				textFieldENTERNEWINTERST.requestFocus();

				try {
					String acno = Recurring.acno;

					conn = DBConnection.getConnection();
					String query = "Select sum(Amount),Interst from banksystem.dailyrecurring where AccountNo='" + acno
							+ "';";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						val = rs.getDouble(1);
						interstin = rs.getDouble("Interst");

					}

					double interstamt = val * interstin / 100.0;
					textFieldBalance.setText(String.valueOf(val));
					double alltotal = val + interstamt;
					textFieldMaturaAmt.setText(String.valueOf(alltotal));

				} catch (Exception eee) {
					System.err.println(eee.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ewww) {
						System.out.println(ewww.getMessage());
					}
				}
				try {
					int Srno = Integer.parseInt(Recurring.SrNoMaster);
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.recurring where SrNoMaster=" + Srno + ";";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						String totalday = rs.getString("Days");
						textFieldTotalday.setText(totalday);
						double interst = rs.getDouble("Intrest");
						textFInterst.setText(String.valueOf(interst));

						SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
						String days = rs.getString("Date");
						Date date = new Date();
						String inputString1 = String.valueOf(days);
						String inputString2 = myFormat.format(date);

						System.out.println(inputString1);
						System.out.println(inputString2);

						Date date1 = myFormat.parse(inputString1);
						Date date2 = myFormat.parse(inputString2);
						long diff = date2.getTime() - date1.getTime();
						long totalday44 = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
						double totalday14 = Double.valueOf(totalday44);

						textFieldRemainsDays.setText(String.valueOf(totalday14));
					}

				} catch (Exception eee) {
					System.err.println(eee.getMessage());
				} finally {
					try {
						rs.close();
						ps.close();
						conn.close();
					} catch (Exception ewww) {
						System.out.println(ewww.getMessage());
					}
				}

			}
		});
		setBounds(205, 100, 538, 459);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Balance:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel.setBounds(10, 114, 137, 30);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblInterest = new JLabel("Mature Amount:");
			lblInterest.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblInterest.setBounds(10, 186, 137, 30);
			contentPanel.add(lblInterest);
		}

		{
			JLabel lblTotal = new JLabel("Amount Remaning:");
			lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTotal.setBounds(10, 267, 137, 30);
			contentPanel.add(lblTotal);
		}
		{
			textFieldBalance = new JTextField();
			textFieldBalance.setForeground(Color.RED);
			textFieldBalance.setEditable(false);
			textFieldBalance.setFont(new Font("Tahoma", Font.PLAIN, 11));
			textFieldBalance.setBounds(157, 119, 154, 25);
			contentPanel.add(textFieldBalance);
			textFieldBalance.setColumns(10);
		}
		{
			textFieldMaturaAmt = new JTextField();
			textFieldMaturaAmt.setForeground(Color.RED);
			textFieldMaturaAmt.setEditable(false);
			textFieldMaturaAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
			textFieldMaturaAmt.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {

				}
			});
			textFieldMaturaAmt.setColumns(10);
			textFieldMaturaAmt.setBounds(157, 191, 154, 25);
			contentPanel.add(textFieldMaturaAmt);
		}
		{
			textFieldAmountRemaning = new JTextField();
			textFieldAmountRemaning.setForeground(Color.RED);
			textFieldAmountRemaning.setEditable(false);
			textFieldAmountRemaning.setFont(new Font("Tahoma", Font.PLAIN, 11));
			textFieldAmountRemaning.setColumns(10);
			textFieldAmountRemaning.setBounds(157, 272, 154, 25);
			contentPanel.add(textFieldAmountRemaning);
		}
		{
			JLabel lblTotalDays = new JLabel("Total Days:");
			lblTotalDays.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTotalDays.setBounds(10, 42, 137, 30);
			contentPanel.add(lblTotalDays);
		}
		{
			textFieldTotalday = new JTextField();
			textFieldTotalday.setEditable(false);
			textFieldTotalday.setFont(new Font("Tahoma", Font.PLAIN, 11));
			textFieldTotalday.setColumns(10);
			textFieldTotalday.setBounds(157, 47, 154, 25);
			contentPanel.add(textFieldTotalday);

		}
		{
			JLabel lblRemain = new JLabel("Count Days:");
			lblRemain.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblRemain.setBounds(10, 78, 137, 30);
			contentPanel.add(lblRemain);
		}
		{
			textFieldRemainsDays = new JTextField();
			textFieldRemainsDays.setEditable(false);
			textFieldRemainsDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
			textFieldRemainsDays.setColumns(10);
			textFieldRemainsDays.setBounds(157, 83, 154, 25);
			contentPanel.add(textFieldRemainsDays);
		}
		{
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(252, 11, 172, 35);
			contentPanel.add(lblNewLabel_1);
		}
		{
			textFieldSrNomaster = new JTextField();
			textFieldSrNomaster.setVisible(false);
			textFieldSrNomaster.setEnabled(false);
			textFieldSrNomaster.setBounds(328, 45, 109, 25);
			contentPanel.add(textFieldSrNomaster);
			textFieldSrNomaster.setColumns(10);
		}
		{
			JLabel lblInterestAmt = new JLabel("New Interst %:");
			lblInterestAmt.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblInterestAmt.setBounds(10, 228, 137, 30);
			contentPanel.add(lblInterestAmt);
		}
		{
			textFieldENTERNEWINTERST = new JTextField();
			textFieldENTERNEWINTERST.addKeyListener(new KeyAdapter() {

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

					double oldinterat = Double.valueOf(textFInterst.getText());
					double newinterst = Double.valueOf(textFieldENTERNEWINTERST.getText());
					if (newinterst <= oldinterat) {
						double newcalintert = oldinterat - newinterst;
						textFieldNEWINTERST.setText(String.valueOf(df.format(newcalintert)));

						double oldbal = Double.valueOf(textFieldBalance.getText());
						double intertamt = oldbal * newcalintert / 100;
						double allsumremamt = oldbal + intertamt;
						textFieldAmountRemaning.setText(String.valueOf(df.format(allsumremamt)));
					} else {
						JOptionPane.showMessageDialog(null, "Plz enter less interst from old interst.");
					}

				}
			});
			textFieldENTERNEWINTERST.setFont(new Font("Tahoma", Font.PLAIN, 11));
			textFieldENTERNEWINTERST.setColumns(10);
			textFieldENTERNEWINTERST.setBounds(157, 228, 154, 25);
			contentPanel.add(textFieldENTERNEWINTERST);
		}

		JLabel lblInt = new JLabel("Interst %:");
		lblInt.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInt.setBounds(10, 150, 137, 30);
		contentPanel.add(lblInt);

		textFInterst = new JTextField();
		textFInterst.setForeground(Color.RED);
		textFInterst.setEditable(false);
		textFInterst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFInterst.setColumns(10);
		textFInterst.setBounds(157, 155, 154, 25);
		contentPanel.add(textFInterst);
		{
			JLabel lblWidrawAmount = new JLabel("Widraw Amount:");
			lblWidrawAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblWidrawAmount.setBounds(10, 306, 137, 30);
			contentPanel.add(lblWidrawAmount);
		}
		{
			textFieldWidtrawAmount = new JTextField();
			textFieldWidtrawAmount.addKeyListener(new KeyAdapter() {

				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == '.') || (c == KeyEvent.VK_BACK_SPACE)
							|| (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					double remaingamt = Double.valueOf(textFieldAmountRemaning.getText());
					double withamt = Double.valueOf(textFieldWidtrawAmount.getText());
					if (withamt <= remaingamt) {
						double allremainsum = remaingamt - withamt;
						textFieldAmountAmount.setText(String.valueOf(df.format(allremainsum)));

					} else {
						JOptionPane.showMessageDialog(null, "Plz enter less amount form total remaining amount.");
					}
				}
			});
			textFieldWidtrawAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
			textFieldWidtrawAmount.setColumns(10);
			textFieldWidtrawAmount.setBounds(157, 311, 154, 25);
			contentPanel.add(textFieldWidtrawAmount);
		}
		{
			textFieldNEWINTERST = new JTextField();
			textFieldNEWINTERST.setForeground(Color.RED);
			textFieldNEWINTERST.setEditable(false);
			textFieldNEWINTERST.setFont(new Font("Tahoma", Font.PLAIN, 11));
			textFieldNEWINTERST.setColumns(10);
			textFieldNEWINTERST.setBounds(329, 228, 78, 25);
			contentPanel.add(textFieldNEWINTERST);
		}
		{
			textFieldAmountAmount = new JTextField();
			textFieldAmountAmount.setForeground(Color.RED);
			textFieldAmountAmount.setEditable(false);
			textFieldAmountAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
			textFieldAmountAmount.setColumns(10);
			textFieldAmountAmount.setBounds(157, 345, 154, 25);
			contentPanel.add(textFieldAmountAmount);
		}
		{
			lblNewLabel_2 = new JLabel("=");
			lblNewLabel_2.setBounds(314, 236, 25, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			lblTotalAmount = new JLabel("Total Amount:");
			lblTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTotalAmount.setBounds(10, 345, 137, 30);
			contentPanel.add(lblTotalAmount);
		}
		{
			lblNewLabel_3 = new JLabel("Update interst %:");
			lblNewLabel_3.setBounds(331, 203, 109, 14);
			contentPanel.add(lblNewLabel_3);
		}

		JLabel label = new JLabel("Date:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(10, 13, 81, 17);
		contentPanel.add(label);

		Date date = new Date();
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(157, 11, 154, 20);
		dateChooser.setDate(date);
		contentPanel.add(dateChooser);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooser.getDate();
							String date = (String) sdf4.format(dateChooser.getDate());
							java.sql.Date d = new java.sql.Date(sdf4.parse(date).getTime());

							Date datecur44 = new Date();
							java.sql.Date sqlDate = new java.sql.Date(datecur44.getTime());
							try {
								double amt = Double.valueOf(textFieldAmountAmount.getText());
								double interst = Double.valueOf(textFieldNEWINTERST.getText());
								double interstamt = amt * interst / 100;
								double allsumsum = amt + interstamt;

								conn = DBConnection.getConnection();
								String up = "UPDATE banksystem.recurring set Date='" + (java.sql.Date) sqlDate
										+ "',Amount=?, Intrest=?, IntrestAmt=?, MaturedAmount=? where Srnomaster="
										+ Srno + "";
								ps = conn.prepareStatement(up);
								ps.setDouble(1, Double.valueOf(textFieldAmountAmount.getText()));
								ps.setDouble(2, Double.valueOf(textFieldNEWINTERST.getText()));
								ps.setDouble(3, interstamt);
								ps.setDouble(4, allsumsum);
								int i = ps.executeUpdate();
								if (i > 0) {
									String del = "delete from banksystem.dailyrecurring where AccountNo='"
											+ Recurring.acno + "'";
									ps1 = (PreparedStatement) conn.prepareStatement(del);
									int ii = ps1.executeUpdate();
									if (ii > 0) {
										int mama = 0;
										String max = "Select max(SrNoMaster) from banksystem.dailyrecurring;";
										ps2 = (PreparedStatement) conn.prepareStatement(max);
										rs = ps2.executeQuery();
										while (rs.next()) {
											mama = rs.getInt(1) + 1;
										}

										double amt1 = Double.valueOf(textFieldAmountAmount.getText());
										double inteset = Double.valueOf(textFieldNEWINTERST.getText());
										double newinterst = amt1 * inteset / 100;
										double matuamt = amt1 + newinterst;

										String interstamt111 = String.valueOf(df.format(newinterst));
										String matuamt11 = String.valueOf(df.format(matuamt));
										ps = (PreparedStatement) conn.prepareStatement(
												"insert into banksystem.dailyrecurring(SrNOMaster, SrNO, Date, CustomerName, AccountNo, AgentName, Amount, Interst, InterestAmt, MaturedAmt) values("
														+ mama + "," + 1 + ",'" + (java.sql.Date) d + "','"
														+ Recurring.cusname + "','" + Recurring.acno + "','"
														+ Recurring.agname + "','" + textFieldAmountAmount.getText()
														+ "','" + textFieldNEWINTERST.getText() + "','" + newinterst
														+ "','" + matuamt + "')");
										int ii1 = ps.executeUpdate();
										if (ii1 > 0) {
											JOptionPane.showMessageDialog(null, "Data are Update.");
											Recurring re = new Recurring();
											re.dispose();
											re.setUndecorated(true);
											re.setVisible(true);
											dispose();
										}
									}

								}
							} catch (Exception eq1) {
								System.out.println(eq1.getMessage());
							} finally {
								try {
									rs.close();
									ps.close();
									ps1.close();
									ps2.close();
									conn.close();
								} catch (Exception ewww) {
									System.out.println(ewww.getMessage());
								}
							}

							try {

								dispose();

							} catch (Exception ee) {
								System.out.println(ee.getMessage());
							}
						} catch (Exception e1) {
							// TODO: handle exception
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

}
