package it.unipv.ingsw.lasout.view.vault;

import it.unipv.ingsw.lasout.model.vault.paymentmethod.PaymentMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PaymentMethodTransactionDialog extends JDialog {
    
    private JList<PaymentMethod> paymentMethodList;
    private DefaultListModel<PaymentMethod> listModel;
    private JTextField amountField;
    private JButton confirmButton;
    private JButton cancelButton;
    private String operation; // "deposit" o "withdraw"
    
    public PaymentMethodTransactionDialog(Frame owner, String operation, List<PaymentMethod> paymentMethods) {
        super(owner, operation.equalsIgnoreCase("deposit") ? "Deposita Denaro" : "Preleva Denaro", true);
        this.operation = operation;
        initComponents(paymentMethods);
    }
    
    private void initComponents(List<PaymentMethod> paymentMethods) {
        setLayout(new BorderLayout(10,10));
        
        // Panel per il campo importo (in alto)
        JPanel amountPanel = new JPanel(new FlowLayout());
        amountPanel.add(new JLabel("Importo:"));
        amountField = new JTextField(10);
        amountPanel.add(amountField);
        add(amountPanel, BorderLayout.NORTH);
        
        // Panel centrale: lista dei metodi di pagamento con scroll
        listModel = new DefaultListModel<>();
        for(PaymentMethod pm : paymentMethods) {
            listModel.addElement(pm);
        }
        paymentMethodList = new JList<>(listModel);
        paymentMethodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(paymentMethodList);
        scrollPane.setPreferredSize(new Dimension(300,150));
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel inferiore: pulsanti Conferma e Annulla
        JPanel buttonPanel = new JPanel(new FlowLayout());
        confirmButton = new JButton("Conferma");
        cancelButton = new JButton("Annulla");
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(getOwner());
    }
    
    public PaymentMethod getSelectedPaymentMethod() {
        return paymentMethodList.getSelectedValue();
    }
    
    public double getAmount() {
        try {
            return Double.parseDouble(amountField.getText());
        } catch(NumberFormatException ex) {
            return -1;
        }
    }
    
    public void addConfirmListener(ActionListener l) {
        confirmButton.addActionListener(l);
    }
    
    public void addCancelListener(ActionListener l) {
        cancelButton.addActionListener(l);
    }
}
