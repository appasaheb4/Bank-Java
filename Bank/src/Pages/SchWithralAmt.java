package Pages;

import Pages.FDA_C;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
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

public class SchWithralAmt extends JDialog {

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
	public String val1;
	public Statement st;
	public ResultSet rs;
	public JDateChooser dateChooserloangpage;
	java.util.Date dt1, dt2;
	private JTextField textFInterst;
	private JTextField textFieldWidtrawAmount;
	private JTextField textFieldNEWINTERST;
	private JTextField textFieldAmountAmount;
	int Srno = Integer.parseInt(SchemeA_C.srnofd);

	public Double val;
	public Double interstin;
	public DecimalFormat df = new DecimalFormat("#.##");
	private JLabel lblTotalAmount;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SchWithralAmt dialog = new SchWithralAmt();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SchWithralAmt() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				textFieldWidtrawAmount.requestFocus();
				try {
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.schemea_c where AccountNo='" + SchemeA_C.acno + "';";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						val = rs.getDouble("Amount");
						interstin = rs.getDouble("SchPer");

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
					int Srno = Integer.parseInt(SchemeA_C.srnofd);
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.schemea_c where SrNoMaster=" + Srno + ";";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						String totalday = rs.getString("Days");
						textFieldTotalday.setText(totalday);
						double interst = rs.getDouble("SchPer");
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
		setBounds(100, 100, 552, 431);
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
			lblTotal.setBounds(10, 268, 137, 30);
			contentPanel.add(lblTotal);
		}
		{
			textFieldBalance = new JTextField();
			textFieldBalance.setForeground(Color.RED);
			textFieldBalance.setEditable(false);
			textFieldBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
			textFieldBalance.setBounds(161, 83, 154, 25);
			contentPanel.add(textFieldBalance);
			textFieldBalance.setColumns(10);
		}
		{
			textFieldMaturaAmt = new JTextField();
			textFieldMaturaAmt.setForeground(Color.RED);
			textFieldMaturaAmt.setEditable(false);
			textFieldMaturaAmt.setFont(new Font("Tahoma", Font.BOLD, 11));
			textFieldMaturaAmt.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					Calculation();
				}
			});
			textFieldMaturaAmt.setColumns(10);
			textFieldMaturaAmt.setBounds(161, 155, 154, 25);
			contentPanel.add(textFieldMaturaAmt);
		}
		{
			textFieldAmountRemaning = new JTextField();
			textFieldAmountRemaning.setForeground(Color.RED);
			textFieldAmountRemaning.setEditable(false);
			textFieldAmountRemaning.setFont(new Font("Tahoma", Font.BOLD, 11));
			textFieldAmountRemaning.setColumns(10);
			textFieldAmountRemaning.setBounds(161, 273, 154, 25);
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
			textFieldTotalday.setForeground(Color.RED);
			textFieldTotalday.setEditable(false);
			textFieldTotalday.setFont(new Font("Tahoma", Font.BOLD, 11));
			textFieldTotalday.setColumns(10);
			textFieldTotalday.setBounds(161, 11, 154, 25);
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
			textFieldRemainsDays.setForeground(Color.RED);
			textFieldRemainsDays.setEditable(false);
			textFieldRemainsDays.setFont(new Font("Tahoma", Font.BOLD, 11));
			textFieldRemainsDays.setColumns(10);
			textFieldRemainsDays.setBounds(161, 47, 154, 25);
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
			lblInterestAmt.setBounds(10, 232, 137, 30);
			contentPanel.add(lblInterestAmt);
		}
		{
			textFieldENTERNEWINTERST = new JTextField();
			textFieldENTERNEWINTERST.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {

					Calculation();
					totalamount();
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
			textFieldENTERNEWINTERST.setFont(new Font("Tahoma", Font.BOLD, 11));
			textFieldENTERNEWINTERST.setColumns(10);
			textFieldENTERNEWINTERST.setBounds(161, 234, 154, 25);
			contentPanel.add(textFieldENTERNEWINTERST);
		}

		JLabel lblInt = new JLabel("Interst %:");
		lblInt.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInt.setBounds(10, 114, 137, 30);
		contentPanel.add(lblInt);

		textFInterst = new JTextField();
		textFInterst.setForeground(Color.RED);
		textFInterst.setEditable(false);
		textFInterst.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFInterst.setColumns(10);
		textFInterst.setBounds(161, 119, 154, 25);
		contentPanel.add(textFInterst);
		{
			JLabel lblWidrawAmount = new JLabel("Widraw Amount:");
			lblWidrawAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblWidrawAmount.setBounds(10, 191, 137, 30);
			contentPanel.add(lblWidrawAmount);
		}
		{
			textFieldWidtrawAmount = new JTextField();
			textFieldWidtrawAmount.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {

					double one = Double.valueOf(textFieldBalance.getText());
					double two = Double.valueOf(textFieldWidtrawAmount.getText());
					double three = one - two;
					textFieldAmountAmount.setText(String.valueOf(three));
					if (one < two) {
						JOptionPane.showMessageDialog(null, "Plz enter less or euqal to balance amount.");
						textFieldWidtrawAmount.requestFocus();
					}

					Calculation();
					totalamount();

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
			textFieldWidtrawAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
			textFieldWidtrawAmount.setColumns(10);
			textFieldWidtrawAmount.setBounds(161, 196, 154, 25);
			contentPanel.add(textFieldWidtrawAmount);
		}
		{
			textFieldNEWINTERST = new JTextField();
			textFieldNEWINTERST.setForeground(Color.RED);
			textFieldNEWINTERST.setEditable(false);
			textFieldNEWINTERST.setFont(new Font("Tahoma", Font.PLAIN, 11));
			textFieldNEWINTERST.setColumns(10);
			textFieldNEWINTERST.setBounds(335, 234, 77, 25);
			contentPanel.add(textFieldNEWINTERST);
		}
		{
			textFieldAmountAmount = new JTextField();
			textFieldAmountAmount.setForeground(Color.RED);
			textFieldAmountAmount.setEditable(false);
			textFieldAmountAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
			textFieldAmountAmount.setColumns(10);
			textFieldAmountAmount.setBounds(161, 309, 154, 25);
			contentPanel.add(textFieldAmountAmount);
		}
		{
			lblTotalAmount = new JLabel("Total Amount:");
			lblTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTotalAmount.setBounds(10, 309, 137, 30);
			contentPanel.add(lblTotalAmount);
		}
		{
			lblNewLabel_2 = new JLabel("=");
			lblNewLabel_2.setBounds(321, 240, 23, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			lblNewLabel_3 = new JLabel("Update interst %:");
			lblNewLabel_3.setBounds(328, 215, 137, 14);
			contentPanel.add(lblNewLabel_3);
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
						double inrt = Double.valueOf(textFieldNEWINTERST.getText());
						double newamt = reamt * inrt / 100.0;
						if (newamt == 0) {
							newamt = 0;
						}

						try {
							double amt = Double.valueOf(textFieldAmountAmount.getText());
							double inter = Double.valueOf(textFieldNEWINTERST.getText());

							double newiner = amt * inter / 100;
							double sumsum = amt + newiner;

							conn = DBConnection.getConnection();
							String up = "UPDATE banksystem.schemea_c set Amount=?, SchPer=?, SchAmt=?, Total=? where AccountNo='"
									+ SchemeA_C.acno + "'";
							ps = conn.prepareStatement(up);
							ps.setDouble(1, Double.valueOf(textFieldAmountAmount.getText()));
							ps.setDouble(2, Double.valueOf(textFieldNEWINTERST.getText()));
							ps.setDouble(3, newiner);
							ps.setDouble(4, sumsum);
							int i = ps.executeUpdate();
							if (i > 0) {
								JOptionPane.showMessageDialog(null, "Thanks.....");
								SchemeA_C sc = new SchemeA_C();
								sc.dispose();
								sc.setUndecorated(true);
								sc.setVisible(true);
								dispose();
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

	public void Calculation() {
		String ins = textFieldENTERNEWINTERST.getText();
		if (ins.equals("0")) {
			textFieldNEWINTERST.setText(textFInterst.getText());

		} else {
			double inters = Double.valueOf(textFInterst.getText());
			double newinterst = Double.valueOf(textFieldENTERNEWINTERST.getText());
			double innewg = inters - newinterst;
			textFieldNEWINTERST.setText(String.valueOf(innewg));
			double with = Double.valueOf(textFieldWidtrawAmount.getText());
		}

	}

	public void totalamount() {
		double amount = Double.valueOf(textFieldBalance.getText());
		double rein = Double.valueOf(textFieldNEWINTERST.getText());
		double allrein = amount * rein / 100;
		double alltotalamt = amount + allrein;
		double with = Double.valueOf(textFieldWidtrawAmount.getText());
		double finalamt = alltotalamt - with;
		textFieldAmountAmount.setText(String.valueOf(df.format(finalamt)));

		double remain = amount - with;
		textFieldAmountRemaning.setText(String.valueOf(remain));

	}

}
