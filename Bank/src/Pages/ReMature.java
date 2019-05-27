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
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class ReMature extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReMature dialog = new ReMature();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReMature() {
		setBounds(205, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblYourFdIs = new JLabel("Your recrring is mature ,");
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
		
		JRadioButton rdbtnContinue = new JRadioButton("Continue");
		buttonGroup.add(rdbtnContinue);
		rdbtnContinue.setBounds(33, 106, 75, 23);
		contentPanel.add(rdbtnContinue);
		
		JRadioButton rdbtnNoContinue = new JRadioButton(" No Continue");
		buttonGroup.add(rdbtnNoContinue);
		rdbtnNoContinue.setBounds(110, 106, 109, 23);
		contentPanel.add(rdbtnNoContinue);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Yes");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						
					if(rdbtnNoContinue.isSelected())
					{
						dispose();
						RecuringWithralAmtMaturity nre =new RecuringWithralAmtMaturity();
						nre.setVisible(true);
					}
					else if(rdbtnContinue.isSelected())
					{
						dispose();
						ReMatureIncrementDate rei=new ReMatureIncrementDate();
						rei.setVisible(true);
						
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
