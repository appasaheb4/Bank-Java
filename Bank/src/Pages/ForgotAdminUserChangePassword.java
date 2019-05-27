package Pages;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JPasswordField;

import com.mysql.jdbc.Statement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public class ForgotAdminUserChangePassword extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTextField textFieldusername;
	private JPasswordField passwordFieldNewPassword;
	public JPasswordField passwordFieldConPassword;

	public Connection conn = null;
	public PreparedStatement pre;
	public PreparedStatement update;
	public PreparedStatement update1;
	public ResultSet rs;
	public Statement st;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ForgotAdminUserChangePassword dialog = new ForgotAdminUserChangePassword();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ForgotAdminUserChangePassword() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String username = ForgotPassword.textFieldUseroksavename.getText();
				textFieldusername.setText(username);

			}

		});
		setBounds(300, 200, 401, 258);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 385, 51);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Change Password");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel.setBounds(120, 11, 200, 17);
				panel.add(lblNewLabel);
			}
		}
		{
			JLabel label = new JLabel("Username:");
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			label.setBounds(10, 73, 125, 17);
			contentPanel.add(label);
		}

		textFieldusername = new JTextField();
		textFieldusername.setEnabled(false);
		textFieldusername.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldusername.setBounds(152, 73, 197, 23);
		contentPanel.add(textFieldusername);
		textFieldusername.setColumns(10);

		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewPassword.setBounds(10, 113, 125, 17);
		contentPanel.add(lblNewPassword);

		passwordFieldNewPassword = new JPasswordField();
		passwordFieldNewPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordFieldNewPassword.setBounds(152, 113, 197, 23);
		contentPanel.add(passwordFieldNewPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConfirmPassword.setBounds(10, 147, 145, 17);
		contentPanel.add(lblConfirmPassword);

		passwordFieldConPassword = new JPasswordField();
		passwordFieldConPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String old = passwordFieldNewPassword.getText().toString();
				String con = passwordFieldConPassword.getText().toString();
				if (old.equals(con)) {

				} else {
					JOptionPane.showMessageDialog(null, "Plz enter new and confirm password correct.");
				}
			}
		});
		passwordFieldConPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordFieldConPassword.setBounds(152, 147, 197, 23);
		contentPanel.add(passwordFieldConPassword);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setIcon(
						new ImageIcon(ForgotAdminUserChangePassword.class.getResource("/ImageButtonIcon/ok.png")));
				okButton.setHorizontalAlignment(SwingConstants.LEADING);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String old = passwordFieldNewPassword.getText().toString();
							String con = passwordFieldConPassword.getText().toString();
							if (old.equals(con)) {

								conn = DBConnection.getConnection();
								st = (Statement) conn.createStatement();
								String username = textFieldusername.getText().toString();
								String password = passwordFieldConPassword.getText().toString();
								String query = "update banksystem.adminloginpage set Password= ? where Username=?";
								String query1 = "update banksystem.adminloginpage set Password= ? where Username=?";
								update1 = conn.prepareStatement(query);
								update = conn.prepareStatement(query1);
								update.setString(1, passwordFieldConPassword.getText().toString());
								update.setString(2, textFieldusername.getText());
								update1.setString(1, passwordFieldConPassword.getText().toString());
								update1.setString(2, textFieldusername.getText());

								int ii = update.executeUpdate();
								int i1 = update1.executeUpdate();
								if (ii > 0) {
									JOptionPane.showMessageDialog(null, "Password are changed.");
									dispose();
									LoginPage lg = new LoginPage();
									lg.setVisible(true);
								} else if (i1 > 0) {
									JOptionPane.showMessageDialog(null, "Password are changed.");
									dispose();
									LoginPage lg = new LoginPage();
									lg.setVisible(true);
								}

								else {
									JOptionPane.showMessageDialog(null, "Password are not changed");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Plz enter new and confirm password correct.");
							}

						} catch (Exception ee) {
							System.out.println(ee.getMessage());
						} finally {
							try {
								rs.close();
								update.close();
								update1.close();
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
				cancelButton.setIcon(
						new ImageIcon(ForgotAdminUserChangePassword.class.getResource("/ImageButtonIcon/cancel.png")));
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
