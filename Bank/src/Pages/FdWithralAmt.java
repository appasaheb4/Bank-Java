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

public class FdWithralAmt extends JDialog {

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
	String Srno = FDA_C.srnofd;

	public Double val;
	public Double interstin;
	public DecimalFormat df = new DecimalFormat("#.##");
	private JLabel lblNewLabel_2;
	private JLabel lblTotalAmount;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FdWithralAmt dialog = new FdWithralAmt();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FdWithralAmt() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
					textFieldENTERNEWINTERST.requestFocus();
					textFieldSrNomaster.setText(Srno);
					conn = DBConnection.getConnection();
					String query = "Select sum(Amount),Interes from banksystem.fd where SrNoMaster=" + Srno + ";";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						val = (rs.getDouble(1));
						interstin = rs.getDouble("Interes");

					}

					double interstamt = val * interstin / 100.0;
					textFieldBalance.setText(String.valueOf(val));
					double alltotal = val + interstamt;
					textFieldMaturaAmt.setText(String.valueOf(df.format(alltotal)));

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
					int Srno = Integer.parseInt(FDA_C.srnofd);
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.fd where SrNoMaster=" + Srno + ";";
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
						double day = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
						textFieldRemainsDays.setText(String.valueOf(day));
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

		setBounds(205, 100, 525, 412);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Basic Amount:");
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
			lblTotal.setBounds(10, 221, 137, 30);
			contentPanel.add(lblTotal);
		}
		{
			textFieldBalance = new JTextField();
			textFieldBalance.setForeground(Color.RED);
			textFieldBalance.setEditable(false);
			textFieldBalance.setFont(new Font("Dialog", Font.BOLD, 14));
			textFieldBalance.setBounds(157, 83, 154, 25);
			contentPanel.add(textFieldBalance);
			textFieldBalance.setColumns(10);
		}
		{
			textFieldMaturaAmt = new JTextField();
			textFieldMaturaAmt.setForeground(Color.RED);
			textFieldMaturaAmt.setEditable(false);
			textFieldMaturaAmt.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {

				}
			});
			textFieldMaturaAmt.setFont(new Font("Dialog", Font.BOLD, 14));

			textFieldMaturaAmt.setColumns(10);
			textFieldMaturaAmt.setBounds(157, 155, 154, 25);
			contentPanel.add(textFieldMaturaAmt);
		}
		{
			textFieldAmountRemaning = new JTextField();
			textFieldAmountRemaning.setForeground(Color.RED);
			textFieldAmountRemaning.setEditable(false);
			textFieldAmountRemaning.setFont(new Font("Dialog", Font.BOLD, 14));
			textFieldAmountRemaning.setColumns(10);
			textFieldAmountRemaning.setBounds(157, 226, 154, 25);
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
			textFieldTotalday.setFont(new Font("Tahoma", Font.BOLD, 14));
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
			textFieldRemainsDays.setFont(new Font("Tahoma", Font.BOLD, 14));
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
		{
			JLabel lblInterestAmt = new JLabel("New Interst %:");
			lblInterestAmt.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblInterestAmt.setBounds(10, 188, 137, 30);
			contentPanel.add(lblInterestAmt);
		}
		{
			textFieldENTERNEWINTERST = new JTextField();
			textFieldENTERNEWINTERST.addKeyListener(new KeyAdapter() {

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
					double interstold = Double.valueOf(textFInterst.getText());
					double newinnterst = Double.valueOf(textFieldENTERNEWINTERST.getText());
					if (newinnterst <= interstold) {
						double newcalinterst = interstold - newinnterst;
						textFieldNEWINTERST.setText(String.valueOf(df.format(newcalinterst)));
						double basicamt = Double.valueOf(textFieldBalance.getText());

						double interstcount = basicamt * newcalinterst / 100;
						double contsum = basicamt + interstcount;
						textFieldAmountRemaning.setText(String.valueOf(df.format(contsum)));

					} else {
						JOptionPane.showMessageDialog(null, "Plz enter interst less then original interst.");
					}
				}
			});

			textFieldENTERNEWINTERST.setFont(new Font("Dialog", Font.BOLD, 14));
			textFieldENTERNEWINTERST.setColumns(10);
			textFieldENTERNEWINTERST.setBounds(157, 188, 154, 25);
			contentPanel.add(textFieldENTERNEWINTERST);
		}

		JLabel lblInt = new JLabel("Interst %:");
		lblInt.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInt.setBounds(10, 114, 137, 30);
		contentPanel.add(lblInt);

		textFInterst = new JTextField();
		textFInterst.setForeground(Color.RED);
		textFInterst.setEditable(false);
		textFInterst.setFont(new Font("Dialog", Font.BOLD, 14));
		textFInterst.setColumns(10);
		textFInterst.setBounds(157, 119, 154, 25);
		contentPanel.add(textFInterst);
		{
			JLabel lblWidrawAmount = new JLabel("Widraw Amount:");
			lblWidrawAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblWidrawAmount.setBounds(10, 264, 137, 30);
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
					double remainamt = Double.valueOf(textFieldAmountRemaning.getText());
					double withamt = Double.valueOf(textFieldWidtrawAmount.getText());
					if (withamt <= remainamt) {
						double alltotalremainamt = remainamt - withamt;
						textFieldAmountAmount.setText(String.valueOf(df.format(alltotalremainamt)));

					} else {
						JOptionPane.showMessageDialog(null, "Plz enter less amount form total remaining amount.");
					}
				}
			});

			textFieldWidtrawAmount.setFont(new Font("Dialog", Font.BOLD, 14));
			textFieldWidtrawAmount.setColumns(10);
			textFieldWidtrawAmount.setBounds(157, 269, 154, 25);
			contentPanel.add(textFieldWidtrawAmount);
		}
		{
			textFieldNEWINTERST = new JTextField();
			textFieldNEWINTERST.setForeground(Color.RED);
			textFieldNEWINTERST.setEditable(false);
			textFieldNEWINTERST.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textFieldNEWINTERST.setColumns(10);
			textFieldNEWINTERST.setBounds(342, 188, 67, 25);
			contentPanel.add(textFieldNEWINTERST);
		}
		{
			textFieldAmountAmount = new JTextField();
			textFieldAmountAmount.setForeground(Color.RED);
			textFieldAmountAmount.setEditable(false);
			textFieldAmountAmount.setFont(new Font("Dialog", Font.BOLD, 14));
			textFieldAmountAmount.setColumns(10);
			textFieldAmountAmount.setBounds(157, 309, 154, 25);
			contentPanel.add(textFieldAmountAmount);
		}
		{
			lblNewLabel_2 = new JLabel("=");
			lblNewLabel_2.setBounds(321, 196, 24, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			lblTotalAmount = new JLabel("Total Amount:");
			lblTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblTotalAmount.setBounds(10, 309, 137, 30);
			contentPanel.add(lblTotalAmount);
		}
		{
			label = new JLabel("Update interst %:");
			label.setBounds(328, 166, 109, 14);
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						double reamt = Double.valueOf(textFieldAmountAmount.getText());
						double inrt = Double.valueOf(textFieldNEWINTERST.getText());
						double newamt = reamt * inrt / 100.0;
						String inamountss = String.valueOf(df.format(newamt));
						double cutsum = reamt + newamt;
						if (newamt == 0) {
							newamt = 0;
						}

						try {
							Date datecur44 = new Date();
							java.sql.Date sqlDate = new java.sql.Date(datecur44.getTime());
							conn = DBConnection.getConnection();
							String up = "UPDATE banksystem.fd set Date='" + (java.sql.Date) sqlDate
									+ "',Amount=?, Interes=?, IntsetAmt=?, Total=? where SrNoMaster=" + Srno + "";
							ps = conn.prepareStatement(up);
							ps.setDouble(1, Double.valueOf(textFieldAmountAmount.getText()));
							ps.setDouble(2, Double.valueOf(textFieldNEWINTERST.getText()));
							ps.setDouble(3, Double.valueOf(inamountss));
							ps.setDouble(4,
									Double.valueOf(textFieldAmountAmount.getText()) + Double.valueOf(inamountss));
							int i = ps.executeUpdate();
							if (i > 0) {
								JOptionPane.showMessageDialog(null, "Data are update");
								FDA_C fd = new FDA_C();
								fd.dispose();
								fd.setUndecorated(true);
								fd.setVisible(true);
								dispose();
							}

						} catch (Exception eq1) {
							System.out.println(eq1.getMessage());
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
