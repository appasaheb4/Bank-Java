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

public class FDWithralAmtMaturity extends JDialog {

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

	public static Double val;
	public static Double interstin;
	public DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FDWithralAmtMaturity dialog = new FDWithralAmtMaturity();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FDWithralAmtMaturity() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {

					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.fd where AccountNumber='" + FDA_C.acno + "';";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						val = rs.getDouble("Amount");
						interstin = rs.getDouble("Interes");

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
					} catch (Exception ess) {
						System.out.println(ess.getMessage());
					}
				}

				try {

					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.fd where AccountNumber='" + FDA_C.acno + "';";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						String totalday = rs.getString("Days");
						textFieldTotalday.setText(totalday);
						double interst = rs.getDouble("Interes");
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
					} catch (Exception ess) {
						System.out.println(ess.getMessage());
					}
				}

			}
		});
		setBounds(205, 100, 513, 397);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Balance:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel.setBounds(10, 120, 137, 30);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblInterest = new JLabel("Mature Amount:");
			lblInterest.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblInterest.setBounds(10, 192, 137, 30);
			contentPanel.add(lblInterest);
		}
		{
			JLabel lblTotal = new JLabel("Amount Remaning:");
			lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTotal.setBounds(10, 274, 137, 30);
			contentPanel.add(lblTotal);
		}
		{
			textFieldBalance = new JTextField();
			textFieldBalance.setForeground(Color.RED);
			textFieldBalance.setEditable(false);
			textFieldBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
			textFieldBalance.setBounds(157, 125, 154, 25);
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

				}
			});
			textFieldMaturaAmt.setColumns(10);
			textFieldMaturaAmt.setBounds(157, 197, 154, 25);
			contentPanel.add(textFieldMaturaAmt);
		}
		{
			textFieldAmountRemaning = new JTextField();
			textFieldAmountRemaning.setForeground(Color.RED);
			textFieldAmountRemaning.setEditable(false);
			textFieldAmountRemaning.setFont(new Font("Tahoma", Font.BOLD, 11));
			textFieldAmountRemaning.setColumns(10);
			textFieldAmountRemaning.setBounds(157, 279, 154, 25);
			contentPanel.add(textFieldAmountRemaning);
		}
		{
			JLabel lblTotalDays = new JLabel("Total Days:");
			lblTotalDays.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTotalDays.setBounds(10, 48, 137, 30);
			contentPanel.add(lblTotalDays);
		}
		{
			textFieldTotalday = new JTextField();
			textFieldTotalday.setForeground(Color.RED);
			textFieldTotalday.setEditable(false);
			textFieldTotalday.setFont(new Font("Tahoma", Font.PLAIN, 11));
			textFieldTotalday.setColumns(10);
			textFieldTotalday.setBounds(157, 53, 154, 25);
			contentPanel.add(textFieldTotalday);

		}
		{
			JLabel lblRemain = new JLabel("Count Days:");
			lblRemain.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblRemain.setBounds(10, 84, 137, 30);
			contentPanel.add(lblRemain);
		}
		{
			textFieldRemainsDays = new JTextField();
			textFieldRemainsDays.setForeground(Color.RED);
			textFieldRemainsDays.setEditable(false);
			textFieldRemainsDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
			textFieldRemainsDays.setColumns(10);
			textFieldRemainsDays.setBounds(157, 89, 154, 25);
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
		lblInt.setBounds(10, 156, 137, 30);
		contentPanel.add(lblInt);

		textFInterst = new JTextField();
		textFInterst.setForeground(Color.RED);
		textFInterst.setEditable(false);
		textFInterst.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFInterst.setColumns(10);
		textFInterst.setBounds(157, 161, 154, 25);
		contentPanel.add(textFInterst);
		{
			JLabel lblWidrawAmount = new JLabel("Widraw Amount:");
			lblWidrawAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblWidrawAmount.setBounds(10, 233, 137, 30);
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
			textFieldWidtrawAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
			textFieldWidtrawAmount.setColumns(10);
			textFieldWidtrawAmount.setBounds(157, 238, 154, 25);
			contentPanel.add(textFieldWidtrawAmount);
		}

		Date date = new Date();
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(157, 17, 145, 20);
		dateChooser.setDate(date);
		contentPanel.add(dateChooser);

		JLabel label = new JLabel("Date:");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 17, 81, 17);
		contentPanel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooser.getDate();
							String date = (String) sdf.format(dateChooser.getDate());
							java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());

							conn = DBConnection.getConnection();
							String updatedata = "update banksystem.fd set Date='" + (java.sql.Date) d + "',Amount='" + 0
									+ "',Interes='" + 0 + "',IntsetAmt='" + 0 + "',Total='" + 0
									+ "' where AccountNumber='" + FDA_C.acno + "'";
							ps = conn.prepareStatement(updatedata);
							int ii1 = ps.executeUpdate();
							if (ii1 > 0) {
								JOptionPane.showMessageDialog(null, "Data are update.");
								FDA_C fd = new FDA_C();
								fd.dispose();
								fd.setUndecorated(true);
								fd.setVisible(true);
								dispose();
							}

						} catch (Exception es) {
							System.out.println(es.getMessage());
						} finally {
							try {

								ps.close();
								conn.close();
							} catch (Exception ess) {
								System.out.println(ess.getMessage());
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
}
