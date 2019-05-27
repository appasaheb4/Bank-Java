package Pages;

import java.awt.BorderLayout;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.jws.Oneway;
import javax.print.attribute.standard.JobKOctetsProcessed;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.ListSelectionModel;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import com.toedter.calendar.JDateChooser;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;



import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.Component;

import javax.swing.table.TableModel;
import javax.swing.JList;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

import java.awt.TextArea;

import java.awt.Choice;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseMotionAdapter;

public class AdminPage extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldMobileNo;
	private JTextField textFieldEmailId;
	private JTextField textFieldUserName;
	private JPasswordField passwordFieldPassword_1;
	private JPasswordField passwordFieldAccessPassword;
	private JTextField textFieldType;
	java.util.Date dt1, dt2;
	public DefaultTableModel model1;

	public Connection conn = null;
	public PreparedStatement pre;
	public PreparedStatement update;
	public ResultSet rs;
	public Statement st;
	private JPasswordField passwordFieldConfirmpassword;
	private JTextField textFieldHint;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JButton buttonUpdate;
	public JButton buttonDelete;
	private JTextField textFieldSrNo;
	public String val1;
	public PreparedStatement ps = null;
	public DefaultTableModel mymodel;
	private JTable tableAdmin;
	public JTextArea textAreaAddress;
	public JList<String> listname;
	public String[] data = new String[1000];
	public JScrollPane scrollPaneName;
	public JPanel panelName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void tableshow() {
		try {
			conn = DBConnection.getConnection();
			String qery = "select * from groarybillingsystem.adminloginpage; ";
			ps = conn.prepareStatement(qery);
			rs = ps.executeQuery();
			while (rs.next()) {
				String type = rs.getString("ColumnTypeName");
				if (type.equals("Admin")) {

					String data0 = rs.getString("SrNo");
					String data1 = rs.getString("Name");
					String data2 = rs.getString("Address");
					String data3 = rs.getString("MobileNo");
					String data4 = rs.getString("EmailId");
					String data5 = rs.getString("Username");
					String data6 = rs.getString("Date");

					mymodel.addRow(new Object[] { data0, data1, data2, data3, data4, data5, data6 });
				}

			}
		} catch (SQLException q) {
			System.out.println(q.getMessage());
		}
		finally
		{
			try
			{
				rs.close();
				ps.close();
				conn.close();
			}
			catch(Exception ew)
			{
				System.out.println(ew.getMessage());
			}
		}

	}

	public void increment() {
		try {

			conn = DBConnection.getConnection();
			 ps = conn.prepareStatement("select max(SrNo) from banksystem.adminloginpage;");
			 rs = ps.executeQuery();
			while (rs.next()) {
				int val = (rs.getInt(1) + 1);
				val1 = String.valueOf(val);

			}
			textFieldSrNo.setText(val1);

		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		}
		finally
		{
			try
			{
				rs.close();
				ps.close();
				conn.close();
			}
			catch(Exception ew)
			{
				System.out.println(ew.getMessage());
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public AdminPage() {
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
			public void windowOpened(WindowEvent e) {
				textFieldName.requestFocus();
			}
		});

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1184, 45);
		panel.setLayout(null);
		contentPane.add(panel);

		JLabel lblAdminPage = new JLabel("Admin Form");
		lblAdminPage.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAdminPage.setBounds(10, 11, 195, 27);
		panel.add(lblAdminPage);

		textFieldSrNo = new JTextField();
		textFieldSrNo.setEnabled(false);
		textFieldSrNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSrNo.setColumns(10);
		textFieldSrNo.setBounds(215, 10, 279, 28);
		textFieldSrNo.setVisible(false);
		increment();
		panel.add(textFieldSrNo);
		
		
		
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); //$NON-NLS-1$
	        getRootPane().getActionMap().put("Cancel", new AbstractAction(){ //$NON-NLS-1$
	            public void actionPerformed(ActionEvent e)
	            {
	            	close();
	            }

				private void close() {
					dispose();
					
				}
	        });
	        
	        

		Date date = new Date();
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(935, 18, 196, 20);
		dateChooser.setDate(date);
		panel.add(dateChooser);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 56, 1145, 134);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "New Admin Acount Create",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(10, 27, 45, 17);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.add(lblNewLabel);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(214, 22, 43, 14);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.add(lblAddress);

		JLabel lblMobileNo = new JLabel("Mobile No:");
		lblMobileNo.setBounds(725, 24, 73, 17);
		lblMobileNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.add(lblMobileNo);

		JLabel lblEmailId = new JLabel("Email  Id:");
		lblEmailId.setBounds(10, 55, 65, 17);
		lblEmailId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.add(lblEmailId);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 98, 74, 17);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(214, 102, 72, 17);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.add(lblPassword);

		JLabel lblAccessPwd = new JLabel("Access Pwd:");
		lblAccessPwd.setBounds(929, 96, 63, 17);
		lblAccessPwd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.add(lblAccessPwd);

		textFieldName = new JTextField();
		textFieldName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
//				String string = textFieldName.getText();
//				String[] parts = string.split(" ");
//				String part1 = parts[0];
//				textFieldUserName.setText(part1);

				try {

					conn = DBConnection.getConnection();
					String query = "select * from banksystem.adminloginpage where Name like '" + textFieldName.getText()
							+ "%'";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();
					int i = 0;
					while (rs.next()) {
						String name = rs.getString("Name");

						data[i] = name;
						i++;

					}
					listname.setListData(data);
					listname.setVisibleRowCount(100);

					panelName.setVisible(true);
					listname.setVisible(true);
					scrollPaneName.setVisible(true);
					textFieldEmailId.setVisible(false);
					textFieldUserName.setVisible(false);

					

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
				finally
				{
					try
					{
						rs.close();
						ps.close();
						conn.close();
					}
					catch(Exception ew)
					{
						System.out.println(ew.getMessage());
					}
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == 40) {
					listname.requestFocus();
				}
			}
		});
		textFieldName.setBounds(68, 22, 136, 20);
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldMobileNo = new JTextField();
		textFieldMobileNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '+') || (c == '-') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		textFieldMobileNo.setBounds(789, 25, 132, 20);
		textFieldMobileNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMobileNo.setColumns(10);
		panel_1.add(textFieldMobileNo);

		textFieldEmailId = new JTextField();
		textFieldEmailId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try
				{
					String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
					String emailAddress=textFieldEmailId.getText();
					 Boolean result = emailAddress.matches(emailreg);
					if(result == false){
						JOptionPane.showMessageDialog(null, "Enter correct email address.");
						textFieldEmailId.requestFocus();
					}
		                
		        }
		            catch(Exception ew){}
			}
		});

		textFieldEmailId.setBounds(68, 55, 136, 20);
		textFieldEmailId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldEmailId.setColumns(10);
		panel_1.add(textFieldEmailId);

		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(68, 98, 136, 20);
		textFieldUserName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldUserName.setColumns(10);
		panel_1.add(textFieldUserName);

		passwordFieldPassword_1 = new JPasswordField();
		passwordFieldPassword_1.setBounds(296, 98, 163, 20);
		passwordFieldPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.add(passwordFieldPassword_1);

		passwordFieldAccessPassword = new JPasswordField();
		passwordFieldAccessPassword.setBounds(1002, 94, 101, 20);
		passwordFieldAccessPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.add(passwordFieldAccessPassword);

		textFieldType = new JTextField();
		textFieldType.setEditable(false);
		textFieldType.setEnabled(false);
		textFieldType.setText("Admin");
		textFieldType.setBounds(752, 49, 199, 28);
		textFieldType.setVisible(false);
		panel_1.add(textFieldType);
		textFieldType.setColumns(10);

		JLabel lblConfirmPwd = new JLabel("Confirm Pwd:");
		lblConfirmPwd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblConfirmPwd.setBounds(465, 98, 73, 17);
		panel_1.add(lblConfirmPwd);

		passwordFieldConfirmpassword = new JPasswordField();
		passwordFieldConfirmpassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String pass = passwordFieldPassword_1.getText().toString();
				String conpass = passwordFieldConfirmpassword.getText().toString();
				if (pass.equals(conpass)) {

				} else {
					JOptionPane.showMessageDialog(null, "Plz enter new and confirm password are same.");
				}
			}
		});
		passwordFieldConfirmpassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		passwordFieldConfirmpassword.setBounds(545, 96, 173, 20);
		panel_1.add(passwordFieldConfirmpassword);

		JLabel lblConfirmPassword = new JLabel("Hint:");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblConfirmPassword.setBounds(725, 98, 35, 17);
		panel_1.add(lblConfirmPassword);

		textFieldHint = new JTextField();
		textFieldHint.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldHint.setColumns(10);
		textFieldHint.setBounds(789, 94, 132, 20);
		panel_1.add(textFieldHint);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(267, 27, 450, 62);
		panel_1.add(scrollPane_1);

		textAreaAddress = new JTextArea();
		textAreaAddress.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				panelName.setVisible(false);
				textFieldEmailId.setVisible(true);
				textFieldUserName.setVisible(true);
			}
		});
		textAreaAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == 9) {
					textFieldMobileNo.requestFocus();
				}
			}
		});
		scrollPane_1.setViewportView(textAreaAddress);

		panelName = new JPanel();
		panelName.setVisible(false);
		panelName.setBounds(68, 43, 136, 80);
		panel_1.add(panelName);
		panelName.setLayout(null);

		scrollPaneName = new JScrollPane();
		scrollPaneName.setVisible(false);
		scrollPaneName.setBounds(0, 0, 136, 80);
		panelName.add(scrollPaneName);

		listname = new JList<String>();
		listname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldName.setText(listname.getSelectedValue());
					
					panelName.setVisible(false);
					textFieldEmailId.setVisible(true);
					textFieldUserName.setVisible(true);
					
					
					try {
						conn = DBConnection.getConnection();
						String query = "Select * from banksystem.adminloginpage where Name=?;";
						ps = conn.prepareStatement(query);
						ps.setString(1, textFieldName.getText());
						rs = ps.executeQuery();
						while (rs.next()) {
							String Srnomaster = rs.getString("SrNo");
							textFieldSrNo.setText(Srnomaster);
							String AccountNumber = rs.getString("Name");
							textFieldName.setText(AccountNumber);
							String Name = rs.getString("Address");
							textAreaAddress.setText(Name);
							String Address = rs.getString("MobileNo");
							textFieldMobileNo.setText(Address);
							String Age = rs.getString("Emial");
							textFieldEmailId.setText(Age);
							String ContactNo = rs.getString("UserName");
							textFieldUserName.setText(ContactNo);
							
						}
					} catch (Exception es) {
					}
					finally
					{
						try
						{
							rs.close();
							ps.close();
							conn.close();
						}
						catch(Exception ew)
						{
							System.out.println(ew.getMessage());
						}
					}
					
				}
			}
		});
		listname.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textFieldName.setText(listname.getSelectedValue());
				
				panelName.setVisible(false);
				textFieldEmailId.setVisible(true);
				textFieldUserName.setVisible(true);
				
				
				try {
					conn = DBConnection.getConnection();
					String query = "Select * from banksystem.adminloginpage where Name=?;";
					ps = conn.prepareStatement(query);
					ps.setString(1, textFieldName.getText());
					rs = ps.executeQuery();
					while (rs.next()) {
						String Srnomaster = rs.getString("SrNo");
						JOptionPane.showMessageDialog(null, Srnomaster);
						
						String AccountNumber = rs.getString("Name");
						textFieldName.setText(AccountNumber);
						String Name = rs.getString("Address");
						textAreaAddress.setText(Name);
						String Address = rs.getString("MobileNo");
						textFieldMobileNo.setText(Address);
						String Age = rs.getString("Emial");
						textFieldEmailId.setText(Age);
						String ContactNo = rs.getString("UserName");
						textFieldUserName.setText(ContactNo);
						
					}
				} catch (Exception es) {
				}
				finally
				{
					try
					{
						rs.close();
						ps.close();
						conn.close();
					}
					catch(Exception ew)
					{
						System.out.println(ew.getMessage());
					}
				}
			}
		});
		listname.setVisible(false);
		scrollPaneName.setViewportView(listname);
		String date4 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 201, 1144, 57);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JButton btnSave = new JButton("Save");
		btnSave.setHorizontalAlignment(SwingConstants.LEADING);
		btnSave.setIcon(new ImageIcon(AdminPage.class.getResource("/ImageButtonIcon/Save.png")));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String pass = passwordFieldPassword_1.getText().toString();
					String conpass = passwordFieldConfirmpassword.getText().toString();
					if (textFieldName.getText().length() != 0 && pass.equals(conpass)) {

						conn = DBConnection.getConnection();
						String query = "insert into banksystem.adminloginpage values(?,?,?,?,?,?,?,?,?,?,?)";
						pre = conn.prepareStatement(query);
						pre.setInt(1, Integer.parseInt(textFieldSrNo.getText()));
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						dt1 = dateChooser.getDate();
						String date = (String) sdf.format(dateChooser.getDate());
						java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
						pre.setDate(2, (java.sql.Date) d);

						pre.setString(3, textFieldName.getText());
						pre.setString(4, textAreaAddress.getText().toString());
						pre.setString(5, textFieldMobileNo.getText());
						pre.setString(6, textFieldEmailId.getText());
						pre.setString(7, textFieldUserName.getText());
						pre.setString(8, passwordFieldConfirmpassword.getText().toString());
						pre.setString(9, passwordFieldAccessPassword.getText().toString());
						pre.setString(10, textFieldType.getText());
						pre.setString(11, textFieldHint.getText());

						int i = pre.executeUpdate();
						if (i > 0) {
							JOptionPane.showMessageDialog(null, "Data are saved.");
							tableshow();
							reset();
							pre.close();
							conn.close();

						}
					} else {
						JOptionPane.showMessageDialog(null, "Plz  enter password and confirm password are correct.");
					}

				} catch (Exception adm) {
					System.out.println(adm.getMessage());
				}
				finally
				{
					try
					{
						
						pre.close();
						conn.close();
					}
					catch(Exception ew)
					{
						System.out.println(ew.getMessage());
					}
				}
				
			}
			public void tableshow()
			{
				
				String data1 = textFieldSrNo.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				dt1 = dateChooser.getDate();
				String data2 = (String) sdf.format(dateChooser.getDate());
				String data3 = textFieldName.getText();
				String data4 = textAreaAddress.getText();
				String data5 = textFieldMobileNo.getText();
				String data6 = textFieldEmailId.getText();
				String data7 = textFieldUserName.getText();
				
				Object[] row = { Boolean.FALSE, data1, data2, data3, data4, data5, data6, data7 };
				model1 = (DefaultTableModel) tableAdmin.getModel();
				model1.addRow(row);
			
				
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBounds(272, 11, 115, 35);
		panel_2.add(btnSave);

		buttonUpdate = new JButton("Update");
		buttonUpdate.setIcon(new ImageIcon(AdminPage.class.getResource("/ImageButtonIcon/update.png")));
		buttonUpdate.setHorizontalAlignment(SwingConstants.LEADING);
		buttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					conn = DBConnection.getConnection();

					String query = "update banksystem.adminloginpage set Date=?, Name=?, Address=?, MobileNo=?, Emial=?, UserName=? where Name=?";
					update = conn.prepareStatement(query);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					dt1 = dateChooser.getDate();
					String date = (String) sdf.format(dateChooser.getDate());
					java.sql.Date d = new java.sql.Date(sdf.parse(date).getTime());
					update.setDate(1, (java.sql.Date) d);
					update.setString(2, textFieldName.getText());
					update.setString(3, textAreaAddress.getText());
					update.setString(4, textFieldMobileNo.getText());
					update.setString(5, textFieldEmailId.getText());
					update.setString(6, textFieldUserName.getText());
					update.setString(7, textFieldName.getText());
					int ii = update.executeUpdate();
					if (ii > 0) {
						JOptionPane.showMessageDialog(null, "Recoard are Updated.");
						dispose();
						AdminPage ad=new AdminPage();
						ad.setUndecorated(true);
						ad.setVisible(true);
					} 

				} catch (SQLException sq) {
					System.out.println(sq.getMessage());

				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally
				{
					try
					{
						
						update.close();
						conn.close();
					}
					catch(Exception ew)
					{
						System.out.println(ew.getMessage());
					}
				}
			

			}
		});
		buttonUpdate.setToolTipText("");
		buttonUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonUpdate.setBounds(397, 11, 126, 34);
		panel_2.add(buttonUpdate);

		buttonDelete = new JButton("Delete");
		buttonDelete.setIcon(new ImageIcon(AdminPage.class.getResource("/ImageButtonIcon/delete.jpg")));
		buttonDelete.setHorizontalAlignment(SwingConstants.LEADING);
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					conn = DBConnection.getConnection();

					String query = "delete from banksystem.adminloginpage  where Name='"+textFieldName.getText()+"'";
					update = conn.prepareStatement(query);

					int iu = update.executeUpdate();
					if (iu > 0) {
						JOptionPane.showMessageDialog(null, "Recoard are deleted.");
						dispose();
						AdminPage ad=new AdminPage();
						ad.setUndecorated(true);
						ad.setVisible(true);
					}

				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			
				finally
				{
					try
					{
						
						update.close();
						conn.close();
					}
					catch(Exception ew)
					{
						System.out.println(ew.getMessage());
					}
				}

			}
		});
		buttonDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonDelete.setBounds(528, 11, 115, 34);
		panel_2.add(buttonDelete);

		JButton button_4 = new JButton("Reset");
		button_4.setIcon(new ImageIcon(AdminPage.class.getResource("/ImageButtonIcon/Reset5.jpg")));
		button_4.setHorizontalAlignment(SwingConstants.LEADING);
		button_4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				reset();

			}
		});
		button_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_4.setBounds(653, 11, 115, 34);
		panel_2.add(button_4);

		JButton button_5 = new JButton("Exit");
		button_5.setHorizontalAlignment(SwingConstants.LEADING);
		button_5.setIcon(new ImageIcon(AdminPage.class.getResource("/ImageButtonIcon/Exit.png")));
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		button_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_5.setBounds(778, 11, 115, 34);
		panel_2.add(button_5);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBounds(0, 269, 1145, 424);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 1125, 387);
		panel_3.add(scrollPane);

		tableAdmin = new JTable();
		tableAdmin.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try
				{
					
					try
					{
					int i = tableAdmin.getSelectedRow();
					model1 = (DefaultTableModel) tableAdmin.getModel();
					textFieldSrNo.setText(model1.getValueAt(i, 1).toString());

					textFieldName.setText(model1.getValueAt(i, 3).toString());
					
					textAreaAddress.setText(model1.getValueAt(i, 4).toString());

					textFieldMobileNo.setText(model1.getValueAt(i, 5).toString());
					textFieldEmailId.setText(model1.getValueAt(i, 6).toString());
					textFieldUserName.setText(model1.getValueAt(i, 7).toString());
					
					
					
					}
					catch(Exception es4)
					{
						System.out.println(es4);
					}
				}
				catch(Exception es){}
				
				
				try {
					che();
				} catch (Exception ee) {
				}

			}

			private void che() {
				boolean s = false;
				boolean s1 = true;
				String inc = null;
				int row = 0;
				int rows = tableAdmin.getRowCount() - 1;

				for (int i = 0; i < tableAdmin.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(tableAdmin.getValueAt(i, 0).toString());
					if (isChecked) {
						inc = "select" + i;
						row = tableAdmin.getSelectedRow();
						int col = tableAdmin.getSelectedColumn();
						tableAdmin.setValueAt((Object) s, i, 0);
						if (inc.equals("select0")) {
							tableAdmin.setValueAt((Object) s1, 0, 0);
						} else if (i == rows) {
							tableAdmin.setValueAt((Object) s1, rows, 0);
						} else if (i != rows) {
							tableAdmin.setValueAt((Object) s, rows, 0);

						}

					} else if (i >= 0) {
						if (row != 0) {
							tableAdmin.setValueAt((Object) s, 0, 0);
							tableAdmin.setValueAt((Object) s1, row, 0);

						}
					}

					else {
					}
				}

			}
		});
		tableAdmin.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Select", "Sr NO", "Date", "Name", "Address", "Contact No", "Email-ID", "User Name" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableAdmin.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableAdmin.getColumnModel().getColumn(0).setMinWidth(50);
		tableAdmin.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableAdmin.getColumnModel().getColumn(1).setMinWidth(50);
		tableAdmin.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableAdmin.getColumnModel().getColumn(2).setMinWidth(80);
		tableAdmin.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableAdmin.getColumnModel().getColumn(3).setMinWidth(150);
		tableAdmin.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableAdmin.getColumnModel().getColumn(4).setMinWidth(100);
		tableAdmin.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableAdmin.getColumnModel().getColumn(5).setMinWidth(150);
		tableAdmin.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableAdmin.getColumnModel().getColumn(6).setMinWidth(100);
		tableAdmin.getColumnModel().getColumn(7).setPreferredWidth(80);
		tableAdmin.getColumnModel().getColumn(7).setMinWidth(80);
		showdata();
		scrollPane.setViewportView(tableAdmin);

	}

	public void reset() {
		increment();
		String fileArray = "";
		textFieldName.setText("");
		textAreaAddress.setText("");
		textAreaAddress.append(fileArray.toString());
		textFieldMobileNo.setText("");
		textFieldEmailId.setText("");
		textFieldUserName.setText("");
		passwordFieldPassword_1.setText("");
		passwordFieldPassword_1.setEnabled(true);
		passwordFieldAccessPassword.setText("");
		passwordFieldAccessPassword.setEnabled(true);
		textAreaAddress.setText("");
		passwordFieldConfirmpassword.setText("");
		textFieldHint.setText("");

		passwordFieldConfirmpassword.setEnabled(true);
		textFieldHint.setEnabled(true);
	}

	public void showdata() {
		try {

			conn = DBConnection.getConnection();
			String quey = "SELECT * FROM banksystem.adminloginpage order by SrNo;";
			ps = conn.prepareStatement(quey);
			rs = ps.executeQuery();
			while (rs.next()) {

				String data0 = rs.getString("SrNo");
				String data1 = rs.getString("Date");
				String data2 = rs.getString("Name");
				String data3 = rs.getString("Address");
				String data4 = rs.getString("MobileNo");
				String data5 = rs.getString("Emial");
				String data6 = rs.getString("UserName");

				Object[] row = { Boolean.FALSE, data0, data1, data2, data3, data4, data5, data6 };
				model1 = (DefaultTableModel) tableAdmin.getModel();
				model1.addRow(row);

			}
			
		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		}
		finally
		{
			try
			{
				
				rs.close();
				ps.close();
				conn.close();
			}
			catch(Exception ew)
			{
				System.out.println(ew.getMessage());
			}
		}
	}
}
