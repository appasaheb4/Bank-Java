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

public class RecuringWithralAmtMaturity extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldBalance;
	private JTextField textFieldMaturaAmt;
	private JTextField textFieldAmountRemaning;
	public static JTextField textFieldTotalday;
	private JTextField textFieldRemainsDays;
	public JLabel lblNewLabel_1;
	public JTextField textFieldSrNomaster;

	public Connection conn;
	public PreparedStatement ps;
	public String val1;
	public Statement st;
	public ResultSet rs;
	public JDateChooser dateChooserloangpage;
	java.util.Date dt1, dt2;
	private JTextField textFInterst;
	private JTextField textFieldWidtrawAmount;
	int Srno = Integer.parseInt(Recurring.SrNoMaster);

	public static Double val;
	public static Double interstin;
	public DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RecuringWithralAmtMaturity dialog = new RecuringWithralAmtMaturity();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RecuringWithralAmtMaturity() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					String acno = Recurring.acno;
					double withdralamt = 0;
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
					textFieldWidtrawAmount.setText(String.valueOf(alltotal));
					textFieldAmountRemaning.setText("0");

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

						SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
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
		setBounds(205, 100, 526, 424);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Balance:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel.setBounds(10, 78, 137, 30);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblInterest = new JLabel("Mature Amount:");
			lblInterest.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblInterest.setBounds(10, 150, 137, 30);
			contentPanel.add(lblInterest);
		}

		{
			JLabel lblTotal = new JLabel("Amount Remaning:");
			lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTotal.setBounds(10, 232, 137, 30);
			contentPanel.add(lblTotal);
		}
		{
			textFieldBalance = new JTextField();
			textFieldBalance.setForeground(Color.RED);
			textFieldBalance.setEditable(false);
			textFieldBalance.setFont(new Font("Tahoma", Font.BOLD, 16));
			textFieldBalance.setBounds(157, 83, 154, 25);
			contentPanel.add(textFieldBalance);
			textFieldBalance.setColumns(10);
		}
		{
			textFieldMaturaAmt = new JTextField();
			textFieldMaturaAmt.setForeground(Color.RED);
			textFieldMaturaAmt.setEditable(false);
			textFieldMaturaAmt.setFont(new Font("Tahoma", Font.BOLD, 16));
			textFieldMaturaAmt.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {

				}
			});
			textFieldMaturaAmt.setColumns(10);
			textFieldMaturaAmt.setBounds(157, 155, 154, 25);
			contentPanel.add(textFieldMaturaAmt);
		}
		{
			textFieldAmountRemaning = new JTextField();
			textFieldAmountRemaning.setForeground(Color.RED);
			textFieldAmountRemaning.setEditable(false);
			textFieldAmountRemaning.setFont(new Font("Tahoma", Font.BOLD, 16));
			textFieldAmountRemaning.setColumns(10);
			textFieldAmountRemaning.setBounds(157, 237, 154, 25);
			contentPanel.add(textFieldAmountRemaning);
		}
		{
			JLabel lblTotalDays = new JLabel("Total Days:");
			lblTotalDays.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTotalDays.setBounds(10, 6, 137, 30);
			contentPanel.add(lblTotalDays);
		}
		{
			textFieldTotalday = new JTextField();
			textFieldTotalday.setEditable(false);
			textFieldTotalday.setFont(new Font("Tahoma", Font.BOLD, 16));
			textFieldTotalday.setColumns(10);
			textFieldTotalday.setBounds(157, 11, 154, 25);
			contentPanel.add(textFieldTotalday);

		}
		{
			JLabel lblRemain = new JLabel("Count Days:");
			lblRemain.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblRemain.setBounds(10, 42, 137, 30);
			contentPanel.add(lblRemain);
		}
		{
			textFieldRemainsDays = new JTextField();
			textFieldRemainsDays.setEditable(false);
			textFieldRemainsDays.setFont(new Font("Tahoma", Font.BOLD, 16));
			textFieldRemainsDays.setColumns(10);
			textFieldRemainsDays.setBounds(157, 47, 154, 25);
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

		JLabel lblInt = new JLabel("Interst %:");
		lblInt.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInt.setBounds(10, 114, 137, 30);
		contentPanel.add(lblInt);

		textFInterst = new JTextField();
		textFInterst.setForeground(Color.RED);
		textFInterst.setEditable(false);
		textFInterst.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFInterst.setColumns(10);
		textFInterst.setBounds(157, 119, 154, 25);
		contentPanel.add(textFInterst);
		{
			JLabel lblWidrawAmount = new JLabel("Widraw Amount:");
			lblWidrawAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblWidrawAmount.setBounds(10, 191, 137, 30);
			contentPanel.add(lblWidrawAmount);
		}
		{
			textFieldWidtrawAmount = new JTextField();
			textFieldWidtrawAmount.setForeground(Color.RED);
			textFieldWidtrawAmount.setEditable(false);
			textFieldWidtrawAmount.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {

					double one = Double.valueOf(textFieldBalance.getText());
					double two = Double.valueOf(textFieldWidtrawAmount.getText());
					double three = one - two;

					if (one < two) {
						JOptionPane.showMessageDialog(null, "Plz enter less or euqal to balance amount.");
						textFieldWidtrawAmount.requestFocus();
					}

				}

				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == '.') || (c == KeyEvent.VK_BACK_SPACE)
							|| (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			textFieldWidtrawAmount.setFont(new Font("Tahoma", Font.BOLD, 16));
			textFieldWidtrawAmount.setColumns(10);
			textFieldWidtrawAmount.setBounds(157, 196, 154, 25);
			contentPanel.add(textFieldWidtrawAmount);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						double reamt = Double.valueOf(textFieldAmountRemaning.getText());

						Date datecur44 = new Date();
						java.sql.Date sqlDate = new java.sql.Date(datecur44.getTime());

						try {
							conn = DBConnection.getConnection();
							String up = "UPDATE banksystem.recurring set Date='" + (java.sql.Date) sqlDate
									+ "' Amount='" + 0 + "', Intrest='" + 0 + "', IntrestAmt='" + 0
									+ "', MaturedAmount='" + 0 + "' where Srnomaster=" + Srno + "";
							ps = conn.prepareStatement(up);
							int i = ps.executeUpdate();
							if (i > 0) {
								JOptionPane.showMessageDialog(null, "Thanks.....");
								Recurring re = new Recurring();
								re.dispose();
								re.setUndecorated(true);
								re.setVisible(true);
							}

						} catch (Exception eq1) {
							System.out.println(eq1.getMessage());
						} finally {
							try {

								ps.close();
								conn.close();
							} catch (Exception ewww) {
								System.out.println(ewww.getMessage());
							}
						}

						try {
							conn = DBConnection.getConnection();
							String sel = "delete from banksystem.dailyrecurring where AccountNo='" + Recurring.acno
									+ "'";
							ps = conn.prepareStatement(sel);
							ps.executeUpdate();

						}

						catch (Exception eq1) {
							System.out.println(eq1.getMessage());
						} finally {
							try {

								ps.close();
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
