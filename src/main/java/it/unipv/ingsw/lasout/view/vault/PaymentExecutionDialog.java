package it.unipv.ingsw.lasout.view.vault;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PaymentExecutionDialog extends JDialog{
	
	private JTextField amountField;
	private JComboBox<String> paymentModeCombo;
	private JTextField causaleField;
	private JButton confirmButton;
	private JButton cancelButton;
	
	public PaymentExecutionDialog(Frame owner) {
		super(owner, "Esegui Pagamento", true);
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new BorderLayout(10,10));
		
		//Pannello Centrale: form per importo, tipo e causale
		JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		formPanel.add(new JLabel("Importo ($):"));
		amountField = new JTextField(10);
		formPanel.add(amountField);
		
		formPanel.add(new JLabel("Modalità:"));
		
		paymentModeCombo = new JComboBox<>(new String[] {"Fisico", "Online"});
		formPanel.add(paymentModeCombo);
		
		formPanel.add(new JLabel("Casuale:"));
		causaleField = new JTextField(20);
		formPanel.add(causaleField);
		
		add(formPanel, BorderLayout.CENTER);
		
		//Pulsanti inferiori
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		confirmButton = new JButton("Conferma");
		cancelButton = new JButton("Annulla");		
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(getOwner());		
	}
	
	public double getAmount() {
		try {
			return Double.parseDouble(amountField.getText());
		} catch(NumberFormatException ex) {
			return -1;
		}
	}
	
	public String getPaymentMode() {
		return (String) paymentModeCombo.getSelectedItem();
	}
	
	public String getCausale() {
		return causaleField.getText();
	}
	
	public void addConfirmListener(ActionListener l) {
		confirmButton.addActionListener(l);
	}
	
	public void addCancelListener(ActionListener l) {
		cancelButton.addActionListener(l);
	}

}
