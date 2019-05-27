package Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class BackUp extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBackupname;
	
	public String path=null;
	public String path1;
	public String filename;
	private JTextField textFieldrename;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackUp frame = new BackUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the frame.
	 */
	public BackUp() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int x=e.getXOnScreen();
				int y=e.getYOnScreen();
				Welcome.lblLocation.setText("X:"+x+" "+"Y:"+y );
			}
		});
		

		setBounds(200, 50, 1170, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1184, 45);
		contentPane.add(panel);

		JLabel lblBackup = new JLabel("BackUp");
		lblBackup.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblBackup.setBounds(10, 11, 195, 27);
		panel.add(lblBackup);
		
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

		JButton button = new JButton("X");
		button.setForeground(Color.RED);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setOpaque(false);
		button.setFont(new Font("Tahoma", Font.PLAIN, 30));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBounds(990, -1, 69, 45);
		panel.add(button);

		JLabel lblNewLabel = new JLabel(
				"You can take a BackUp of your database by clicking on this Backup button.\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 96, 579, 17);
		contentPane.add(lblNewLabel);

		JLabel lblYouCanRestore = new JLabel(
				"You can Restore your database and get complete data by clicking on this Restore button.\r\n");
		lblYouCanRestore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYouCanRestore.setBounds(10, 237, 579, 17);
		contentPane.add(lblYouCanRestore);

		JLabel lblExitThisForm = new JLabel("Exit this form.\r\n");
		lblExitThisForm.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblExitThisForm.setBounds(10, 368, 756, 25);
		contentPane.add(lblExitThisForm);

		JButton btnNewButton = new JButton("BackUp");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton.setIcon(new ImageIcon(BackUp.class.getResource("/ImageButtonIcon/BackUp.jpg")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Process p=null;
				try
				{
					Runtime runtime=Runtime.getRuntime();
					p=runtime.exec("C:/Program Files (x86)/MySQL/MySQL Server 5.5/bin/mysqldump.exe -uroot -papple4 --add-drop-database -B banksystem -r"+path);
					int processComplete=p.waitFor();
					if(processComplete==0)
					{
						JOptionPane.showMessageDialog(null, "BackUp Creaated Succuss");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "BackUp not created");
						
					}
				}
				catch(Exception sq)
				{
					System.out.println(sq.getMessage());
				}
					
			

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(611, 141, 202, 54);
		contentPane.add(btnNewButton);

		JButton btnRestore = new JButton("Restore");
		btnRestore.setIcon(new ImageIcon(BackUp.class.getResource("/ImageButtonIcon/Restore2.jpg")));
		btnRestore.setHorizontalAlignment(SwingConstants.LEADING);
		btnRestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dbName = "banksystem";
	             String dbUser = "root";
	             String dbPass = "apple4";
				String [] restoreCmd=new String[]{"C:/Program Files (x86)/MySQL/MySQL Server 5.5/bin/mysql.exe", dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + path1};
			
																										
				Process process;
				try
				{
					process=Runtime.getRuntime().exec(restoreCmd);
					int processCom=process.waitFor();
					if(processCom==0)
					{
						JOptionPane.showMessageDialog(null,"Restored Succuss.");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Can't Resotore.");
					}
					
				}
				catch(Exception ee)
				{
					ee.printStackTrace();
				}
				
				
			}
		});
		btnRestore.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRestore.setBounds(611, 273, 202, 54);
		contentPane.add(btnRestore);
		
		textFieldBackupname = new JTextField();
		textFieldBackupname.setForeground(Color.BLUE);
		textFieldBackupname.setEditable(false);
		textFieldBackupname.setEnabled(false);
		textFieldBackupname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldBackupname.setBounds(10, 153, 397, 22);
		contentPane.add(textFieldBackupname);
		textFieldBackupname.setColumns(10);
		
		JButton btnBrowger = new JButton("Browse");
		btnBrowger.setHorizontalAlignment(SwingConstants.LEADING);
		btnBrowger.setIcon(new ImageIcon(BackUp.class.getResource("/ImageButtonIcon/Browse.png")));
		btnBrowger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc=new JFileChooser();
				fc.showOpenDialog(contentPane);
				String date=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				try
				{
					File f=fc.getSelectedFile();
					path=f.getAbsolutePath();
					path=path.replace('\\', '/');
					JOptionPane.showMessageDialog(null, path);
					path=path+"_"+date+".sql";
					textFieldBackupname.setText(path);
				}
				catch(Exception ee){}
				
				
				
				
			}
		});
		btnBrowger.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBrowger.setBounds(417, 150, 148, 36);
		contentPane.add(btnBrowger);
		
		textFieldrename = new JTextField();
		textFieldrename.setForeground(Color.BLUE);
		textFieldrename.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldrename.setEnabled(false);
		textFieldrename.setEditable(false);
		textFieldrename.setColumns(10);
		textFieldrename.setBounds(10, 291, 397, 22);
		contentPane.add(textFieldrename);
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"Cancel"); //$NON-NLS-1$
		getRootPane().getActionMap().put("Cancel", new AbstractAction() { //$NON-NLS-1$
			public void actionPerformed(ActionEvent e) {
				close();
			}

			private void close() {
				dispose();

			}
		});
		
		JButton buttonRerstiveButoon = new JButton("Browse");
		buttonRerstiveButoon.setIcon(new ImageIcon(BackUp.class.getResource("/ImageButtonIcon/Browse.png")));
		buttonRerstiveButoon.setHorizontalAlignment(SwingConstants.LEADING);
		buttonRerstiveButoon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc1=new JFileChooser();
				fc1.showOpenDialog(contentPane);
				try
				{
					File ff=fc1.getSelectedFile();
					path1=ff.getAbsolutePath();
					path1=path1.replace('\\', '/');
					textFieldrename.setText(path1);
				}
				catch(Exception es)
				{
					es.printStackTrace();
				}
				 
				
			}
		});
		buttonRerstiveButoon.setFont(new Font("Tahoma", Font.BOLD, 20));
		buttonRerstiveButoon.setBounds(417, 288, 148, 36);
		contentPane.add(buttonRerstiveButoon);
		
		JButton button_1 = new JButton("Exit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			
			}
		});
		button_1.setIcon(new ImageIcon(BackUp.class.getResource("/ImageButtonIcon/Exit.png")));
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button_1.setBounds(20, 404, 115, 38);
		contentPane.add(button_1);
	}
}
