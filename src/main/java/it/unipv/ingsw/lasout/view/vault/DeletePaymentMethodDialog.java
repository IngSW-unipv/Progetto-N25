package it.unipv.ingsw.lasout.view.vault;

import java.awt.BorderLayout;
import java.awt.DefaultFocusTraversalPolicy;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import it.unipv.ingsw.lasout.model.vault.Vault;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.PaymentMethod;

public class DeletePaymentMethodDialog extends JDialog{
	
	private JList<PaymentMethod> paymentMethodList;
	private DefaultListModel<PaymentMethod> listModel;
	private JButton deleteButton;
	private JButton cancelButton;
	private Vault vault;
	
	public DeletePaymentMethodDialog(Frame owner, Vault vault, List<PaymentMethod> methods) {
		super(owner, "Cancella Metodo di Pagamento", true);
		this.vault = vault;
		initComponents(methods);
	}
	
	private void initComponents(List<PaymentMethod> methods) {
		setLayout(new BorderLayout(10,10));
		
		//lista e popolamento
		listModel = new DefaultListModel<>();
		for(PaymentMethod pm : methods) {
			listModel.addElement(pm);
		}
		
		paymentMethodList = new JList<>(listModel);
		paymentMethodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(paymentMethodList);
		scrollPane.setPreferredSize(new Dimension(300, 150));
		add(scrollPane, BorderLayout.CENTER);	
		
		//Pannello pulsanti
		JPanel buttonPanel = new JPanel(new FlowLayout());
		deleteButton = new JButton("Rimuovi");
		cancelButton = new JButton("Rimuovi");
		buttonPanel.add(deleteButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(getOwner());
	}
	
	public PaymentMethod getSelectedPaymentMethod() {
		return paymentMethodList.getSelectedValue();
	}
	
	public void addDeleteListener(ActionListener l) {
		deleteButton.addActionListener(l);
	}
	
	public void addCancelListener(ActionListener l) {
		cancelButton.addActionListener(l);
	}
	
	public void removeSelectedPaymentMethod() {
		int selectIndex = paymentMethodList.getSelectedIndex();
		if(selectIndex != -1) {
			listModel.remove(selectIndex);
		}
	}
}
