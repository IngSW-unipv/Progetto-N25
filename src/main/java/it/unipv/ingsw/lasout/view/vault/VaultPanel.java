package it.unipv.ingsw.lasout.view.vault;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import it.unipv.ingsw.lasout.model.transaction.Transaction;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.PaymentMethod;
import it.unipv.ingsw.lasout.view.LaColor;
import it.unipv.ingsw.lasout.view.mainview.MainUIView;

public class VaultPanel extends JPanel {

	private JLabel saldoLabel;
	private JButton executePaymentBtn;
    private JButton aggiungiMetodoBtn;
    private JButton removeMethodBtn;
    private JList<String> paymentMethodsList;
    private JList<Transaction> transactionsList;
    private JButton depositBtn;
    private JButton withdrawBtn;
    
    public VaultPanel(MainUIView mainUIview) {
        setLayout(new BorderLayout(10,10));
        setBackground(LaColor.SFONDO_SCURO);
        initComponents();
    }
    
    private void initComponents() {
    	// Pannello superiore: qui inseriamo il pulsante "Aggiungi Metodo di Pagamento"
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBackground(LaColor.SFONDO_CHIARO);
        
        executePaymentBtn = new JButton("Esegui pagamento");
        executePaymentBtn.setPreferredSize(new Dimension(160, 50));
        executePaymentBtn.setBackground(LaColor.BTN_SFONDO);
        topPanel.add(executePaymentBtn, BorderLayout.WEST);
        
        // Puoi mettere qui eventuali altri componenti (come il menu già esistente)
        // e posizionare il pulsante a destra.
        aggiungiMetodoBtn = new JButton("Aggiungi Metodo di Pagamento");
        // Imposta eventualmente lo stile o la trasparenza
        aggiungiMetodoBtn.setOpaque(false);
        aggiungiMetodoBtn.setBackground(LaColor.BTN_SFONDO);
        topPanel.add(aggiungiMetodoBtn, BorderLayout.EAST);
        
        add(topPanel, BorderLayout.NORTH);
        
        // CENTER: Saldo
        saldoLabel = new JLabel("Saldo: €0.00", SwingConstants.CENTER);
        saldoLabel.setFont(new Font("Arial", Font.BOLD, 28));
        saldoLabel.setForeground(Color.BLACK);
        add(saldoLabel, BorderLayout.CENTER);
        
        // EAST: Lista dei metodi di pagamento
        JPanel eastPanel = new JPanel(new BorderLayout());
        eastPanel.setBackground(LaColor.SFONDO_CHIARO);
        eastPanel.setPreferredSize(new Dimension(200, getHeight()));
        eastPanel.add(new JLabel("Metodi di Pagamento", SwingConstants.CENTER), BorderLayout.NORTH);
        paymentMethodsList = new JList<>(new DefaultListModel<>());
        eastPanel.add(new JScrollPane(paymentMethodsList), BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
        
        removeMethodBtn = new JButton("Rimuovi Metodo");
        removeMethodBtn.setBackground(LaColor.BTN_SFONDO);
        JPanel btnPanel = new JPanel (new FlowLayout());
        btnPanel.setBackground(LaColor.SFONDO);
        btnPanel.add(removeMethodBtn);
        eastPanel.add(btnPanel, BorderLayout.SOUTH);
        add(eastPanel, BorderLayout.EAST);
        
        // WEST: Lista delle transazioni
        JPanel westPanel = new JPanel(new BorderLayout());
        westPanel.setBackground(LaColor.SFONDO_CHIARO);
        westPanel.setPreferredSize(new Dimension(200, getHeight()));
        westPanel.add(new JLabel("Transazioni", SwingConstants.CENTER), BorderLayout.NORTH);
        transactionsList = new JList<>(new DefaultListModel<>());
        westPanel.add(new JScrollPane(transactionsList), BorderLayout.CENTER);
        add(westPanel, BorderLayout.WEST);
        
     // SOUTH: Pannello per i due nuovi pulsanti "Deposit" e "Withdraw"
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        southPanel.setBackground(LaColor.SFONDO_CHIARO);
        depositBtn = new JButton("Deposita Denaro");
        withdrawBtn = new JButton("Preleva Denaro");
        depositBtn.setPreferredSize(new Dimension(150, 50));
        withdrawBtn.setPreferredSize(new Dimension(150, 50));
        depositBtn.setBackground(LaColor.BTN_SFONDO);
        withdrawBtn.setBackground(LaColor.BTN_SFONDO);
        southPanel.add(depositBtn);
        southPanel.add(withdrawBtn);
        add(southPanel, BorderLayout.SOUTH);
    }
    
    public void addExecutePaymentListner(ActionListener l) {
    	executePaymentBtn.addActionListener(l);
    }
    
    public void updateSaldo(double saldo) {
        saldoLabel.setText("Saldo: €" + String.format("%.2f", saldo));
    }
    
    public void addAggiornaListener(ActionListener l) {
    }
    
    // Nuovo metodo getter per il pulsante di aggiunta
    public void addAggiungiMetodoListener(ActionListener l) {
        aggiungiMetodoBtn.addActionListener(l);
    }
    
    public JList<String> getPaymentMethodsList() {
        return paymentMethodsList;
    }
    
    public void updatePaymentMethodsList(ListModel<String> model) {
        paymentMethodsList.setModel(model);
    }
    
    public void addDepositListener(ActionListener l) {
        depositBtn.addActionListener(l);
    }
    
    public void addWithdrawListener(ActionListener l) {
        withdrawBtn.addActionListener(l);
    }
    
    public void addRemoveMethodListener(ActionListener l) {
    	removeMethodBtn.addActionListener(l);
    }
    
    public void updateTransactionList(ListModel<Transaction> model) {
    	transactionsList.setModel(model);
    }
}
