package Pages;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;

public class FdMature extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FdMature dialog = new FdMature();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FdMature() {
		setBounds(205, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblYourFdIs = new JLabel("Your Fd is mature ,");
			lblYourFdIs.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblYourFdIs.setBounds(10, 11, 414, 50);
			contentPanel.add(lblYourFdIs);
		}
		{
			JLabel label = new JLabel("Do you still want to widraw.");
			label.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label.setBounds(10, 58, 414, 50);
			contentPanel.add(label);
		}
		
		JRadioButton radioContinue = new JRadioButton("Continue");
		buttonGroup.add(radioContinue);
		radioContinue.setBounds(36, 115, 75, 23);
		contentPanel.add(radioContinue);
		
		JRadioButton radioNoContinue = new JRadioButton(" No Continue");
		buttonGroup.add(radioNoContinue);
		radioNoContinue.setBounds(113, 115, 109, 23);
		contentPanel.add(radioNoContinue);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Yes");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(radioContinue.isSelected())
						{
							dispose();
							FDMatureIncrementDate fdd=new FDMatureIncrementDate();
							fdd.setVisible(true);
							
						}
						else if(radioNoContinue.isSelected())
						{
							dispose();
							FDWithralAmtMaturity fdmm=new FDWithralAmtMaturity();
							fdmm.setVisible(true);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Plz select any one continue or no Continue.");
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
