package Pages;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;

public class ForgotPassword extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUsername;
	private JTextField textFieldHint;

	public Connection conn = null;
	public ResultSet rs;
	public Statement st;
	public ResultSet rs1;
	public Statement st1;
	public static JTextField textFieldUseroksavename;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ForgotPassword dialog = new ForgotPassword();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ForgotPassword() {

		setBounds(300, 200, 358, 247);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 46);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Forgot Password");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(142, 11, 159, 19);
		panel.add(lblNewLabel);
		{
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(0, 57, 316, 107);
			contentPanel.add(panel_1);
			panel_1.setLayout(null);
			{
				JLabel lblNewLabel_1 = new JLabel("Username:");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_1.setBounds(10, 11, 74, 17);
				panel_1.add(lblNewLabel_1);
			}

			textFieldUsername = new JTextField();
			textFieldUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
			textFieldUsername.setBounds(99, 11, 172, 23);
			panel_1.add(textFieldUsername);
			textFieldUsername.setColumns(10);

			JLabel lblHint = new JLabel("Hint:");
			lblHint.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblHint.setBounds(10, 50, 74, 17);
			panel_1.add(lblHint);

			textFieldHint = new JTextField();
			textFieldHint.setFont(new Font("Tahoma", Font.BOLD, 14));
			textFieldHint.setColumns(10);
			textFieldHint.setBounds(99, 50, 172, 23);
			panel_1.add(textFieldHint);

			textFieldUseroksavename = new JTextField();
			textFieldUseroksavename.setEnabled(false);
			textFieldUseroksavename.setBounds(99, 84, 148, 20);
			textFieldUseroksavename.setVisible(false);
			panel_1.add(textFieldUseroksavename);
			textFieldUseroksavename.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setIcon(new ImageIcon(ForgotPassword.class.getResource("/ImageButtonIcon/ok.png")));
				okButton.setHorizontalAlignment(SwingConstants.LEADING);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {

							conn = DBConnection.getConnection();
							st = (Statement) conn.createStatement();
							String username = textFieldUsername.getText().toString();
							String hint = textFieldHint.getText().toString();

							rs = st.executeQuery(
									"select Username,Hint,Emial,Password from banksystem.adminloginpage where Username='"
											+ username + "' and Hint='" + hint + "';");

							if (rs.next()) {

								try {
						                String recipient = "9404873151";
						                String message ="Password="+rs.getString("Password");
						                String username1 = "saily";
						                String password = "20826896";
						                String originator = "9021550005";

						                String requestUrl  = "http://bulksms.mysmsmantra.com:8080/WebSMS/SMSAPI.jsp?" +
						                					 "username=" + URLEncoder.encode(username1, "UTF-8") +
						                					 "&password=" + URLEncoder.encode(password, "UTF-8") +
						                					 "&recipient=" + URLEncoder.encode(recipient, "UTF-8") +
						                					 "&messagetype=SMS:TEXT" +
						                					 "&messagedata=" + URLEncoder.encode(message, "UTF-8") +
						                					 "&originator=" + URLEncoder.encode(originator, "UTF-8") +
						                					 "&serviceprovider=GSMModem1" +
						                					 "&responseformat=html";
						                URL url = new URL(requestUrl);
						                HttpURLConnection uc = (HttpURLConnection)url.openConnection();

						                System.out.println(uc.getResponseMessage());

						                uc.disconnect();

								  }catch(Exception ww)
								  {
									  System.out.println(ww.getMessage());
								  }
							}

						} catch (Exception ee) {
							System.out.println(ee.getMessage());
						} finally {
							try {
								rs.close();
								st.close();
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
				cancelButton.setIcon(new ImageIcon(ForgotPassword.class.getResource("/ImageButtonIcon/cancel.png")));
				cancelButton.setHorizontalAlignment(SwingConstants.LEADING);
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
