package Pages;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ConnectException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class ScMatureIncrementDate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFDuration;
	private JTextField textField_Days;
	private JTextField textFieldAmount;
	private JTextField textFieldInterst;
	private JTextField textFieldInterstAmt;
	private JTextField textFieldMaturityAmt;
	public static Connection conn;
	public static ResultSet rs;
	public static ResultSet rs1;
	public static ResultSet rs2;
	public static PreparedStatement ps;
	public static PreparedStatement ps1;
	public static PreparedStatement ps2;
	public static PreparedStatement ps3;
	static java.util.Date dt1;
	public JCheckBox checkBoxDuration;
	public JComboBox comboBoxDouration;
	public JDateChooser dateChooser;
	public DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ScMatureIncrementDate dialog = new ScMatureIncrementDate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ScMatureIncrementDate() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				dateChooser.requestDefaultFocus();
				try {

					double Amount = 0;
					double interst = 0;
					double interstamt = 0;
					double matiamt = 0;

					conn = (Connection) DBConnection.getConnection();
					String query = "Select * from banksystem.schemea_c where AccountNo='" + SchemeA_C.acno + "';";

					ps = (PreparedStatement) conn.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						Amount = rs.getDouble("Amount");
						interst = rs.getDouble("SchPer");
					}
					interstamt = Amount * interst / 100;
					matiamt = Amount + interstamt;

					textFieldAmount.setText(String.valueOf(Amount));
					textFieldInterst.setText(String.valueOf(interst));
					textFieldInterstAmt.setText(String.valueOf(interstamt));
					textFieldMaturityAmt.setText(String.valueOf(matiamt));

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
		setBounds(205, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel label = new JLabel("Date:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(10, 28, 81, 17);
		contentPanel.add(label);

		Date date = new Date();
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(101, 26, 196, 20);
		dateChooser.setDate(date);
		contentPanel.add(dateChooser);

		JLabel label_1 = new JLabel("Duration:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(10, 56, 54, 17);
		contentPanel.add(label_1);

		comboBoxDouration = new JComboBox();
		comboBoxDouration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String st = comboBoxDouration.getSelectedItem().toString();
					daycount(st);
				} catch (Exception es) {
					System.out.println(es.getMessage());
				}
			}
		});
		comboBoxDouration.setModel(new DefaultComboBoxModel(new String[] { "Select", "1 Month", "2 Month", "3 Month\t",
				"6 Month\t", "9 Month", "1 Year", "2 Year\t", "3 Year", "4 Year", "5 Year" }));
		comboBoxDouration.setBounds(101, 53, 160, 23);
		contentPanel.add(comboBoxDouration);

		checkBoxDuration = new JCheckBox("");
		checkBoxDuration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click1();
			}

		});
		checkBoxDuration.setBounds(267, 53, 21, 23);
		contentPanel.add(checkBoxDuration);

		textFDuration = new JTextField();
		textFDuration.setEditable(false);
		textFDuration.setForeground(Color.BLACK);
		textFDuration.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFDuration.setText("");

			}
		});
		textFDuration.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String st1 = textFDuration.getText();
				daycount(st1);
			}
		});
		textFDuration.setText("other e.g. 3 Month..");
		textFDuration.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textFDuration.setColumns(10);
		textFDuration.setBounds(294, 53, 130, 23);
		contentPanel.add(textFDuration);

		textField_Days = new JTextField();
		textField_Days.setVisible(false);
		textField_Days.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textField_Days.setEnabled(false);
		textField_Days.setColumns(10);
		textField_Days.setBounds(330, 87, 94, 23);
		contentPanel.add(textField_Days);

		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAmount.setBounds(10, 90, 54, 17);
		contentPanel.add(lblAmount);

		textFieldAmount = new JTextField();
		textFieldAmount.setForeground(Color.RED);
		textFieldAmount.setEditable(false);
		textFieldAmount.setBounds(101, 87, 196, 23);
		contentPanel.add(textFieldAmount);
		textFieldAmount.setColumns(10);

		JLabel lblInterst = new JLabel("Interst %:");
		lblInterst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblInterst.setBounds(10, 121, 54, 17);
		contentPanel.add(lblInterst);

		textFieldInterst = new JTextField();
		textFieldInterst.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				clac();
			}
		});
		textFieldInterst.setForeground(Color.RED);
		textFieldInterst.setColumns(10);
		textFieldInterst.setBounds(101, 118, 196, 23);
		contentPanel.add(textFieldInterst);

		JLabel lblInterstAmt = new JLabel("Interst Amt:");
		lblInterstAmt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblInterstAmt.setBounds(10, 152, 81, 17);
		contentPanel.add(lblInterstAmt);

		textFieldInterstAmt = new JTextField();
		textFieldInterstAmt.setForeground(Color.RED);
		textFieldInterstAmt.setEditable(false);
		textFieldInterstAmt.setColumns(10);
		textFieldInterstAmt.setBounds(101, 149, 196, 23);
		contentPanel.add(textFieldInterstAmt);

		JLabel lblMaturityAmount = new JLabel("Maturity Amt:");
		lblMaturityAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMaturityAmount.setBounds(10, 183, 81, 17);
		contentPanel.add(lblMaturityAmount);

		textFieldMaturityAmt = new JTextField();
		textFieldMaturityAmt.setForeground(Color.RED);
		textFieldMaturityAmt.setEditable(false);
		textFieldMaturityAmt.setColumns(10);
		textFieldMaturityAmt.setBounds(101, 180, 196, 23);
		contentPanel.add(textFieldMaturityAmt);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							double amt = Double.valueOf(textFieldMaturityAmt.getText());
							double interst = Double.valueOf(textFieldInterst.getText());
							double allinter = amt * interst / 100;
							double allsumamt = amt + allinter;
							conn = (Connection) DBConnection.getConnection();
							String up = "UPDATE banksystem.schemea_c set Date=?, Amount=?, SchPer=?, SchAmt=?, Total=?, Duration=?, Days=? where AccountNo='"
									+ SchemeA_C.acno + "'";
							ps = (PreparedStatement) conn.prepareStatement(up);
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							dt1 = dateChooser.getDate();
							String date = (String) sdf.format(dateChooser.getDate());
							java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
							ps.setDate(1, (java.sql.Date) d);
							ps.setDouble(2, Double.valueOf(textFieldMaturityAmt.getText()));
							ps.setDouble(3, Double.valueOf(textFieldInterst.getText()));
							ps.setDouble(4, allinter);
							ps.setDouble(5, allsumamt);
							ps.setString(6, comboBoxDouration.getSelectedItem().toString());
							ps.setString(7, textField_Days.getText());
							int ii = ps.executeUpdate();
							if (ii > 0) {
								JOptionPane.showMessageDialog(null, "Data are update.");
								SchemeA_C sc = new SchemeA_C();
								sc.dispose();
								sc.setUndecorated(true);
								sc.setVisible(true);
								dispose();
							}
						} catch (Exception es) {
							System.out.println(es.getMessage());
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

	public void daycount(String str) {
		textFDuration.setText(str);

		String number = "";
		String letter = "";
		String symbol = "";
		String firstLetter;

		String[] currency = "48.50USD".split("(?<=\\d)(?=[a-zA-Z])");

		for (int i = 0; i < str.length(); i++) {
			char a = str.charAt(i);
			if (Character.isDigit(a)) {
				number = number + a;

			} else {
				letter = letter + a;
			}
		}
		firstLetter = Character.toString(letter.charAt(1));

		if (firstLetter.equals("Y") || firstLetter.equals("y")) {
			double let = Double.valueOf(number);
			double day = let * 365;

			textField_Days.setText(String.valueOf(day));

		} else if (firstLetter.equals("M") || firstLetter.equals("m")) {
			double let = Double.valueOf(number);
			double day = let * 30;

			textField_Days.setText(String.valueOf(day));

		} else {
			double let = Double.valueOf(number);
			double day = let * 30;

			textField_Days.setText(String.valueOf(day));
		}
	}

	public void click1() {
		if (!checkBoxDuration.isSelected()) {
			comboBoxDouration.setEnabled(true);
			textFDuration.setEditable(false);

		} else {
			comboBoxDouration.setEnabled(false);
			textFDuration.setEditable(true);
			comboBoxDouration.setSelectedIndex(0);

		}
	}

	public void clac() {
		double amt = Double.valueOf(textFieldAmount.getText());
		double interst = Double.valueOf(textFieldInterst.getText());
		double interstsum = amt * interst / 100;
		textFieldInterstAmt.setText(String.valueOf(df.format(interstsum)));
		double maturamt = amt + interstsum;
		textFieldMaturityAmt.setText(String.valueOf(df.format(maturamt)));
	}
}
