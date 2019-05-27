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

public class ShareWithralAmt extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldBalance;
	private JTextField textFieldMaturaAmt;
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
	int Srno =Integer.parseInt(SharesA_C.srnofd);
	
	public Double val;
	public Double interstin;
	public JDateChooser dateChooser;
	public DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ShareWithralAmt dialog = new ShareWithralAmt();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ShareWithralAmt() {
	
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				dateChooser.requestDefaultFocus();
				try {
					conn = DBConnection.getConnection();
					String query = "Select sum(Amount),SchPer from banksystem.shares where SrNoMaster=" + Srno
							+ ";";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						val = (rs.getDouble(1));
						interstin = rs.getDouble("SchPer");

					}

					double interstamt = val * interstin / 100.0;
					textFieldBalance.setText(String.valueOf(val));
					textFInterst.setText(String.valueOf(interstin));
					double alltotal = val + interstamt;
					textFieldMaturaAmt.setText(String.valueOf(alltotal));
					

				} catch (Exception eee) {
					System.err.println(eee.getMessage());
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
		});
		setBounds(205, 100, 483, 233);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Balance:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel.setBounds(10, 48, 137, 30);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblInterest = new JLabel("Mature Amount:");
			lblInterest.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblInterest.setBounds(10, 120, 137, 30);
			contentPanel.add(lblInterest);
		}
		{
			textFieldBalance = new JTextField();
			textFieldBalance.setFont(new Font("Tahoma", Font.BOLD, 16));
			textFieldBalance.setEnabled(false);
			textFieldBalance.setBounds(157, 53, 154, 25);
			contentPanel.add(textFieldBalance);
			textFieldBalance.setColumns(10);
		}
		{
			textFieldMaturaAmt = new JTextField();
			textFieldMaturaAmt.setEnabled(false);
			textFieldMaturaAmt.setFont(new Font("Tahoma", Font.BOLD, 16));
			textFieldMaturaAmt.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
			
				}
			});
			textFieldMaturaAmt.setColumns(10);
			textFieldMaturaAmt.setBounds(157, 125, 154, 25);
			contentPanel.add(textFieldMaturaAmt);
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
		lblInt.setBounds(10, 84, 137, 30);
		contentPanel.add(lblInt);

		textFInterst = new JTextField();
		textFInterst.setEnabled(false);
		textFInterst.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFInterst.setColumns(10);
		textFInterst.setBounds(157, 89, 154, 25);
		contentPanel.add(textFInterst);
		
		JLabel label = new JLabel("Date:");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 20, 81, 17);
		contentPanel.add(label);
		
		Date date = new Date();
 dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(157, 17, 154, 20);
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
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooser.getDate();
							String date = (String) sdf.format(dateChooser.getDate());
							java.sql.Date d= new java.sql.Date(sdf.parse(date).getTime());
						
							conn = DBConnection.getConnection();
							String up = "UPDATE banksystem.shares set Date='"+(java.sql.Date)d+"',Amount='"+0+"', SchPer='"+0+"',Total='"+0+"' where AccountNo='"
									+ SharesA_C.acno + "'";
						
							ps = conn.prepareStatement(up);
							int i = ps.executeUpdate();
							if (i > 0) {
								JOptionPane.showMessageDialog(null, "Data are update.");
								dispose();
							}
							ps.close();
							conn.close();
						} catch (Exception eq1) {
							System.out.println(eq1.getMessage());
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
